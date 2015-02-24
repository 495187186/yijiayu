package com.test.tougao.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.test.tougao.dao.DBHelp;
import com.test.tougao.dao.AdminFileDao;
import com.test.tougao.entity.AdminFile;

public class AdminUpfileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AdminUpfileServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = null;
		String msg = "";
		String endFileName = "";
		HttpSession session=request.getSession();
		String username = (String)session.getAttribute("username");
		String no=(String)session.getAttribute("account");
		
		String fileName = "";
		double size = 0;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		

		String path = getServletContext().getRealPath("/")+"uploadfiles";
		//String filepath = req.getRealPath("/");
		//System.out.println("上传路径："+filepath);
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
			System.out.println("创建文件夹成功");
		}

		factory.setSizeThreshold(20* 1024);
		factory.setRepository(new File(path));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8"); 
		upload.setSizeMax(20*1024*1024);  //设置上传文件的大小，此处为20M
		 
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date()) + "";

		try
		{
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
	
			for(FileItem item : list)
			{
				String name = item.getFieldName();
				
				//System.out.println(name + "???????????????????");

				if(item.isFormField())
				{					
					String value =item.getString();				
					value = new String(value.getBytes("ISO8859_1"),"utf-8");
					request.setAttribute(name, value);
				}
				else
				{
					//System.out.println("hello");
					String value = item.getName();

					int start = value.lastIndexOf("\\");
					fileName = value.substring(start + 1);
			
					request.setAttribute(name, fileName);
					
					size = (double)item.getSize();
					
					//System.out.println(fileName);
					String[] dots = fileName.split("\\.");
					for(int i = 0;i <= dots.length - 1;i++)
					{
						//System.out.println(dots[i]);
					}
					String afterdot = dots[dots.length - 1];
					endFileName = time + no + "." + afterdot;
					item.write(new File(path, fileName));
				}
			}
	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		String docname = (String)request.getAttribute("docname");
		String doctype = (String)request.getAttribute("doctype");
		String info = (String)request.getAttribute("info");
		
		String[] namess = endFileName.split(":");
		endFileName = "";
		for(int i = 0;i <= namess.length - 1;i++)
		{
			//System.out.println(namess[i]);
			endFileName += namess[i];
		}
		
		System.out.println(docname);
		System.out.println(doctype);
		System.out.println(info);
		System.out.println(time);
		System.out.println(fileName);
		System.out.println(size);
		System.out.println(endFileName);
		
		
		File endFile = new File(path + "//" + fileName);
		//System.out.println(endFile.isFile());
		if(endFile.isFile())
		{
			endFile.renameTo(new File(path + "//" + endFileName));
		}
		
		
		try
		{
			int n;
			AdminFileDao fileDao=new AdminFileDao();
			AdminFile file=null;
			
			//未上传过则添加
			String sql="insert into tg_aupfile(disk_id,file_name,file_uploader,doc_name,file_type,file_info,file_size,file_time) values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'))";
			String[] params = {endFileName,fileName,no,docname,doctype,info,size + "",time};
			
			file=fileDao.findByName(fileName.trim());
			if(file==null)//未上传过则添加
			{
				file=new AdminFile();
				file.setFileName(docname);
				file.setFileType(doctype);
				file.setFileUploader(no);
				file.setUploader(username.trim());
				file.setFileTime(time);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						
				file.setFileStatus("0");
				file.setFileCounter("1");
				file.setFileReward("0");
				file.setPersonNum("0");
				file.setIsReward("0");
				file.setIsAllot("0");
				file.setIsChoose("0");
				fileDao.add(file);//添加到论文表
				
				n = DBHelp.executeUpdate(sql, params);
			}else//已上传过则更新
			{
				int num=Integer.parseInt(file.getFileCounter())+1;
				file.setFileName(docname);
				file.setFileType(doctype);
				file.setFileUploader(no);
				file.setFileTime(time);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						
				file.setFileStatus("0");
				file.setFileCounter(num+"");//提交次数加1
				file.setFileReward("0");
				file.setPersonNum("0");
				file.setIsReward("0");
				file.setIsAllot("0");
				file.setIsChoose("0");
				fileDao.update(file);//添加到论文表
				
				n= DBHelp.executeUpdate(sql, params);
			}
			
			
			
			if(n == 1)
			{
				msg += "恭喜您，传递成功！";
				request.setAttribute("msg",msg);
				rd = request.getRequestDispatcher("admin/showsuccess.jsp");
				rd.forward(request, response);
			}
			else
			{
				msg += "传递失败！";
				request.setAttribute("msg",msg);
				rd = request.getRequestDispatcher("admin/showerror.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getStackTrace());
		}

		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

package com.test.tougao.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import com.test.tougao.dao.NoteDao;
import com.test.tougao.entity.Note;

public class Add2NoteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Add2NoteServlet() {
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

		doPost(request,response);
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
		

		String path = getServletContext().getRealPath("/")+"upnotes";
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
		
		String notename = (String)request.getAttribute("notename");
		String notewriter = (String)request.getAttribute("notewriter");
		String content = (String)request.getAttribute("content");
		
		String[] namess = endFileName.split(":");
		endFileName = "";
		for(int i = 0;i <= namess.length - 1;i++)
		{
			//System.out.println(namess[i]);
			endFileName += namess[i];
		}
		
		System.out.println(no);
		System.out.println(username);
		System.out.println(notename);
		System.out.println(content);
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
		try {
			int n=0;
			NoteDao noteDao=new NoteDao();
			Note note=new Note();
			note.setAdminNo(no);
			note.setAdminName(notewriter);
			note.setNoteName(notename);
			note.setNoteDate(time);
			note.setNoteContent(content);
			note.setDiskName(endFileName);
			note.setFileName(fileName);
			note.setFileSize(size+"");
			note.setIsAttach("1");
			n=noteDao.add(note);
			
			if(n>0){
				msg += "恭喜您，发布成功！";
				request.setAttribute("msg",msg);
				rd = request.getRequestDispatcher("admin/showsuccess.jsp");
				rd.forward(request, response);
			}else{
				msg += "对不起，发布失败！";
				request.setAttribute("msg",msg);
				rd = request.getRequestDispatcher("admin/showerror.jsp");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

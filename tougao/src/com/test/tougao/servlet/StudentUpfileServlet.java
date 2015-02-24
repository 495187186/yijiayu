package com.test.tougao.servlet;

import java.io.File;
import java.io.IOException;
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
import com.test.tougao.dao.PaperDao;
import com.test.tougao.dao.StudentDao;
import com.test.tougao.entity.Paper;
import com.test.tougao.entity.Student;



public class StudentUpfileServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		RequestDispatcher rd = null;
		String msg = "";
		String endFileName = "";
		HttpSession session=req.getSession();
		String username = (String)session.getAttribute("username");
		String no=(String)session.getAttribute("account");
		
		String fileName = "";
		double size = 0;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		

		String path = getServletContext().getRealPath("/")+"upfile";
		//String filepath = req.getRealPath("/");
		//System.out.println("上传路径："+filepath);
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
			System.out.println("创建文件夹成功");
		}

		factory.setSizeThreshold(20* 1024);
		factory.setRepository(new File(path));
		//factory.setSizeThreshold(4 * 1024 * 1024 * 1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8"); 
		upload.setSizeMax(20*1024*1024);  //设置上传文件的大小，此处为20M 
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date()) + "";

		try
		{
			List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
	
			for(FileItem item : list)
			{
				String name = item.getFieldName();
				
				//System.out.println(name + "???????????????????");

				if(item.isFormField())
				{					
					String value =item.getString();				
					value = new String(value.getBytes("ISO8859_1"),"utf-8");
					req.setAttribute(name, value);
				}
				else
				{
					//System.out.println("hello");
					String value = item.getName();

					int start = value.lastIndexOf("\\");
					fileName = value.substring(start + 1);
			
					req.setAttribute(name, fileName);
					
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
		
		String paperwriter = (String)req.getAttribute("paperwriter");
		String papername = (String)req.getAttribute("papername");
		String papertype = (String)req.getAttribute("papertype");
		String info = (String)req.getAttribute("info");
		
		String[] namess = endFileName.split(":");
		endFileName = "";
		for(int i = 0;i <= namess.length - 1;i++)
		{
			//System.out.println(namess[i]);
			endFileName += namess[i];
		}
		
		System.out.println(paperwriter);
		System.out.println(papername);
		System.out.println(papertype);
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
			PaperDao paperDao=new PaperDao();
			Paper paper=null;
			StudentDao studentDao=new StudentDao();
			Student student=studentDao.findByNo(no);
			
			//未上传过则添加
			String sql="insert into tg_supfile(disk_id,file_name,file_uploader,file_writer,paper_name,file_type,file_info,file_size,file_time) values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'))";
			String[] params = {endFileName,fileName,no,paperwriter,papername,papertype,info,size + "",time};
			
			paper=paperDao.findByName(papername.trim());
			if(paper==null)//未上传过则添加
			{
				paper=new Paper();
				paper.setCollegeName(student.getStudentCollege());
				paper.setPaperName(papername);
				paper.setPaperType(papertype);
				paper.setPaperUploader(no);
				paper.setUploader(username.trim());
				paper.setPaperWriter(paperwriter);
				paper.setPaperTime(time);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						
				paper.setPaperStatus("0");
				paper.setPaperCounter("1");
				paper.setPaperReward("0");
				paper.setPersonNum("0");
				paper.setIsReward("0");
				paper.setIsAllot("0");
				paper.setIsChoose("0");
				paperDao.add(paper);//添加到论文表
				
				n = DBHelp.executeUpdate(sql, params);
			}else//已上传过则更新
			{
				int num=Integer.parseInt(paper.getPaperCounter())+1;
				paper.setPaperName(papername);
				paper.setPaperType(papertype);
				paper.setPaperWriter(paperwriter);
				paper.setPaperTime(time);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						
				paper.setPaperStatus("0");
				paper.setPaperCounter(num+"");//提交次数加1
				paper.setPaperReward("0");
				paper.setPersonNum("0");
				paper.setIsReward("0");
				paper.setIsAllot("0");
				paper.setIsChoose("0");
				paperDao.update(paper);//添加到论文表
				
				n= DBHelp.executeUpdate(sql, params);
			}
			
			
			
			if(n == 1)
			{
				msg += "恭喜您，传递成功！";
				req.setAttribute("msg",msg);
				rd = req.getRequestDispatcher("student/showsuccess.jsp");
				rd.forward(req, resp);
			}
			else
			{
				msg += "传递失败！";
				req.setAttribute("msg",msg);
				rd = req.getRequestDispatcher("student/showerror.jsp");
				rd.forward(req, resp);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getStackTrace());
		}

	}
}

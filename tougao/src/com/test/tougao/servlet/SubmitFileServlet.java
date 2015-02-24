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

import com.test.tougao.dao.AdminFileDao;
import com.test.tougao.dao.AllotFileDao;
import com.test.tougao.dao.DocReviewedDao;
import com.test.tougao.dao.ExpertDao;
import com.test.tougao.dao.FileCountDao;
import com.test.tougao.dao.FileMessageDao;
import com.test.tougao.dao.ReviewFileDao;
import com.test.tougao.dao.ReviewedFileDao;
import com.test.tougao.dao.StaFileDao;
import com.test.tougao.entity.AdminFile;
import com.test.tougao.entity.AllotFile;
import com.test.tougao.entity.DocReviewed;
import com.test.tougao.entity.Expert;
import com.test.tougao.entity.FileCount;
import com.test.tougao.entity.FileMessage;
import com.test.tougao.entity.ReviewFile;
import com.test.tougao.entity.ReviewedFile;
import com.test.tougao.entity.StaFile;

public class SubmitFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SubmitFileServlet() {
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

        
		request.setCharacterEncoding("UTF-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date()) + "";
		HttpSession session=request.getSession();
		String username = (String)session.getAttribute("username");
		String no=(String)session.getAttribute("account");
		//System.out.println(time);
		DocReviewedDao docreviewedDao=new DocReviewedDao();
		DocReviewed docreviewed=null;
		ReviewFileDao reviewfileDao=new ReviewFileDao();
		ReviewFile reviewfile=null;
		ReviewedFileDao reviewedfileDao=new ReviewedFileDao();
		ReviewedFile reviewedfile=null;
		AdminFileDao adminfileDao=new AdminFileDao();
		AdminFile adminfile=null;
		FileCountDao filecountDao=new FileCountDao();
		FileCount filecount=null;
		ExpertDao expertDao=new ExpertDao();
		Expert expert=null;
		AllotFileDao allotfileDao=new AllotFileDao();
		List<AllotFile> allotfileList=null;
		AllotFile allotfile=null;
		StaFileDao stafileDao=new StaFileDao();
		StaFile stafile=null;
		FileMessageDao filemessageDao=new FileMessageDao();
		FileMessage message=null;
		
		
		int n=0;
		int count=0;
		RequestDispatcher rd = null;
		String msg="";
		String endFileName = "";
		String fileName = "";
		double size = 0;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = getServletContext().getRealPath("/")+"reviewfile";//request.getRealPath("/reviewfile");
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
		
		
		String expertno=(String)request.getAttribute("expertno");
 		String docname=(String)request.getAttribute("docname");
 		String doctype=(String)request.getAttribute("doctype");
 		String allottime=(String)request.getAttribute("allottime");
 		String select=(String)request.getAttribute("select");
 		String doclevel=(String)request.getAttribute("level");
 		String messagename=(String)request.getAttribute("messagename");
 		String docmessage=(String)request.getAttribute("message"); 
 		String docreward=(String)request.getAttribute("docreward"); 
		
		
		String[] namess = endFileName.split(":");
		endFileName = "";
		for(int i = 0;i <= namess.length - 1;i++)
		{
			//System.out.println(namess[i]);
			endFileName += namess[i];
		}
		
		File endFile = new File(path + "//" + fileName);
		//System.out.println(endFile.isFile());
		if(endFile.isFile())
		{
			endFile.renameTo(new File(path + "//" + endFileName));
		}
		
		System.out.println(time);
		System.out.println(fileName);
		System.out.println(size);
		System.out.println(endFileName);
		
		try {
			expert=expertDao.findByNo(expertno);
			expert.setPaperNum(Integer.parseInt(expert.getPaperNum())-1+"");//评审完一篇待审数量-1
			expertDao.update(expert); 
			
			reviewfile=reviewfileDao.findFile(expertno, docname.trim(), allottime);
			adminfile=adminfileDao.findFile(docname.trim(), reviewfile.getUploadDate().substring(0, reviewfile.getUploadDate().length()-2));
			reviewfile.setUploadDate(reviewfile.getUploadDate().substring(0,reviewfile.getUploadDate().length()-2));
			reviewfile.setAllotDate(allottime);
			reviewfile.setDocDeadline(reviewfile.getDocDeadline().substring(0,reviewfile.getDocDeadline().length()-2));
			reviewfile.setIsReviewed("1");
			reviewfileDao.update(reviewfile);
			
			allotfile=allotfileDao.findExpFile(docname.trim(), expert.getExpertName().trim(), reviewfile.getUploadDate());
			allotfile.setAllotTime(allottime);
			allotfile.setUploadTime(allotfile.getUploadTime().substring(0, allotfile.getUploadTime().length()-2));
			allotfile.setIsReviewed("2");//评审结束
			allotfileDao.update(allotfile);
			
			stafile=stafileDao.findByFileName(docname.trim());
			allotfileList=allotfileDao.findFiles(docname.trim(), reviewfile.getUploadDate().substring(0,reviewfile.getUploadDate().length()-2));
			for(int i=0;i<allotfileList.size();i++){
				if(allotfileList.get(i).getIsReviewed().equals("2")){
					count++;
					
				}
			}
			if(count==allotfileList.size()){//如果每篇材料分配的专家都评审完毕，则该材料审核结束
				adminfile.setFileTime(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
				adminfile.setFileStatus("2");//审核结束
				adminfileDao.update(adminfile);
				
				stafile.setUploadTime(stafile.getUploadTime().substring(0, stafile.getUploadTime().length()-2));
				stafile.setFileStatus("2");
				stafileDao.update(stafile);
			}
			
			filecount=new FileCount();
			filecount.setExpertNo(expertno);
			filecount.setDocName(docname.trim());
			filecount.setDocType(doctype);
			filecount.setReviewTime(time);
			filecount.setDocReward(docreward);
			filecount.setIsAgreed(select);
			filecountDao.add(filecount);
			
			reviewedfile=new ReviewedFile();
			reviewedfile.setExpertNo(expertno);
			reviewedfile.setDocName(docname.trim());
			reviewedfile.setDocType(doctype);
			reviewedfile.setAllotTime(allottime);
			reviewedfile.setReviewTime(time);
			reviewedfile.setDocReward(docreward);
			reviewedfile.setIsAgreed(select);
			reviewedfileDao.add(reviewedfile);
			
			message=new FileMessage();
			message.setExpertNo(expertno);
			message.setExpertName(expert.getExpertName().trim());
			message.setAdminNo(adminfile.getFileUploader());
			message.setFileName(docname.trim());
			message.setUploadTime(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
			message.setMessageName(messagename.trim());
			message.setMessageDate(time);
			message.setIsRead("0");//未读
			filemessageDao.add(message);
			
			docreviewed=new DocReviewed();
			docreviewed.setExpertNo(expertno);
			docreviewed.setDocName(docname);
			docreviewed.setDocType(doctype);
			docreviewed.setDocLevel(doclevel);
			docreviewed.setDiskName(endFileName);
			docreviewed.setFileName(fileName);
			docreviewed.setFileSize(size+"");
			docreviewed.setFileTime(time);
			docreviewed.setAllotTime(allottime);
			docreviewed.setSubmitTime(time);
			docreviewed.setMessageName(messagename.trim());
			docreviewed.setDocMessage(docmessage);
			docreviewed.setIsAgreed(select);
			n=docreviewedDao.add(docreviewed);
				
			if(n>0){
				 msg+="恭喜您，提交成功！";
				 request.setAttribute("msg",msg);
				 rd = request.getRequestDispatcher("expert/showsuccess.jsp");
				 rd.forward(request, response);
			}else{
				 msg+="对不起，提交失败！";//此时是dao类执行错误
				 request.setAttribute("msg",msg);
				 rd = request.getRequestDispatcher("expert/showerror.jsp");
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

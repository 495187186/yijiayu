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

import com.test.tougao.dao.AllotPaperDao;
import com.test.tougao.dao.ExpertDao;
import com.test.tougao.dao.PaperCountDao;
import com.test.tougao.dao.PaperDao;
import com.test.tougao.dao.PaperMessageDao;
import com.test.tougao.dao.ReviewPaperDao;
import com.test.tougao.dao.ReviewedDao;
import com.test.tougao.dao.ReviewedPaperDao;
import com.test.tougao.dao.StaPaperDao;
import com.test.tougao.entity.AllotPaper;
import com.test.tougao.entity.Expert;
import com.test.tougao.entity.Paper;
import com.test.tougao.entity.PaperCount;
import com.test.tougao.entity.PaperMessage;
import com.test.tougao.entity.ReviewPaper;
import com.test.tougao.entity.Reviewed;
import com.test.tougao.entity.ReviewedPaper;
import com.test.tougao.entity.StaPaper;

public class SubmitReviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SubmitReviewServlet() {
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
		ReviewedDao reviewedDao=new ReviewedDao();
		Reviewed reviewed=null;
		ReviewPaperDao reviewpaperDao=new ReviewPaperDao();
		ReviewPaper reviewpaper=null;
		ReviewedPaperDao reviewedpaperDao=new ReviewedPaperDao();
		ReviewedPaper reviewedpaper=null;
		PaperDao paperDao=new PaperDao();
		Paper paper=null;
		PaperCountDao papercountDao=new PaperCountDao();
		PaperCount papercount=null;
		ExpertDao expertDao=new ExpertDao();
		Expert expert=null;
		AllotPaperDao allotpaperDao=new AllotPaperDao();
		List<AllotPaper> allotpaperList=null;
		AllotPaper allotpaper=null;
		StaPaperDao stapaperDao=new StaPaperDao();
		StaPaper stapaper=null;
		PaperMessageDao papermessageDao=new PaperMessageDao();
		PaperMessage message=null;
		
		int n=0;
		int count=0;
		RequestDispatcher rd = null;
		String msg="";
		String endFileName = "";
		String fileName = "";
		double size = 0;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = getServletContext().getRealPath("/")+"reviewpaper";//request.getRealPath("/reviewpaper");
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
 		String papername=(String)request.getAttribute("papername");
 		String papertype=(String)request.getAttribute("papertype");
 		String allottime=(String)request.getAttribute("allottime");
 		String select=(String)request.getAttribute("select");
 		String paperlevel=(String)request.getAttribute("level");
 		String messagename=(String)request.getAttribute("messagename");
 		String papermessage=(String)request.getAttribute("message"); 
 		String paperreward=(String)request.getAttribute("paperreward"); 
		
		
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

			reviewpaper=reviewpaperDao.findPaper(expertno, papername.trim(), allottime);
			paper=paperDao.findPaper(papername.trim(), reviewpaper.getUploadDate().substring(0, reviewpaper.getUploadDate().length()-2));
			reviewpaper.setUploadDate(reviewpaper.getUploadDate().substring(0,reviewpaper.getUploadDate().length()-2));
			reviewpaper.setAllotDate(allottime);
			reviewpaper.setPaperDeadline(reviewpaper.getPaperDeadline().substring(0,reviewpaper.getPaperDeadline().length()-2));
			reviewpaper.setIsReviewed("1");
			reviewpaperDao.update(reviewpaper);
			
			allotpaper=allotpaperDao.findExpPaper(papername.trim(), expert.getExpertName().trim(), reviewpaper.getUploadDate());
			allotpaper.setAllotTime(allottime);
			allotpaper.setUploadTime(allotpaper.getUploadTime().substring(0, allotpaper.getUploadTime().length()-2));
			allotpaper.setIsReviewed("2");//评审结束
			allotpaperDao.update(allotpaper);
			
			stapaper=stapaperDao.findByPaperName(papername.trim());
			allotpaperList=allotpaperDao.findPapers(papername.trim(), reviewpaper.getUploadDate().substring(0,reviewpaper.getUploadDate().length()-2));
			for(int i=0;i<allotpaperList.size();i++){
				if(allotpaperList.get(i).getIsReviewed().equals("2")){
					count++;
					
				}
			}
			if(count==allotpaperList.size()){//如果每篇论文分配的专家都评审完毕，则该论文审核结束
				paper.setPaperTime(paper.getPaperTime().substring(0, paper.getPaperTime().length()-2));
				paper.setPaperStatus("2");//审核结束
				paperDao.update(paper);
				
				stapaper.setUploadTime(stapaper.getUploadTime().substring(0, stapaper.getUploadTime().length()-2));
				stapaper.setPaperStatus("2");
				stapaperDao.update(stapaper);
			}
			
			papercount=new PaperCount();
			papercount.setExpertNo(expertno);
			papercount.setPaperName(papername.trim());
			papercount.setPaperType(papertype);
			papercount.setPaperWriter(paper.getPaperWriter());
			papercount.setReviewTime(time);
			papercount.setPaperReward(paperreward);
			papercount.setIsAgreed(select);
			papercountDao.add(papercount);
			
			reviewedpaper=new ReviewedPaper();
			reviewedpaper.setExpertNo(expertno);
			reviewedpaper.setPaperName(papername.trim());
			reviewedpaper.setPaperType(papertype);
			reviewedpaper.setAllotTime(allottime);
			reviewedpaper.setReviewTime(time);
			reviewedpaper.setPaperReward(paperreward);
			reviewedpaper.setIsAgreed(select);
			reviewedpaperDao.add(reviewedpaper);
			
			message=new PaperMessage();
			message.setExpertNo(expertno);
			message.setExpertName(expert.getExpertName().trim());
			message.setStudentNo(paper.getPaperUploader());
			message.setPaperName(papername.trim());
			message.setPaperWriter(paper.getPaperWriter());
			message.setUploadTime(paper.getPaperTime().substring(0, paper.getPaperTime().length()-2));
			message.setMessageName(messagename.trim());
			message.setMessageDate(time);
			message.setIsRead("0");//未读
			papermessageDao.add(message);
			
			reviewed=new Reviewed();
			reviewed.setExpertNo(expertno);
			reviewed.setPaperName(papername.trim());
			reviewed.setPaperType(papertype);
			reviewed.setPaperLevel(paperlevel);
			reviewed.setDiskName(endFileName);
			reviewed.setFileName(fileName);
			reviewed.setFileSize(size+"");
			reviewed.setFileTime(time);
			reviewed.setAllotTime(allottime);
			reviewed.setSubmitTime(time);
			reviewed.setMessageName(messagename.trim());
			reviewed.setPaperMessage(papermessage);
			reviewed.setIsAgreed(select);
			n=reviewedDao.add(reviewed);
				
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

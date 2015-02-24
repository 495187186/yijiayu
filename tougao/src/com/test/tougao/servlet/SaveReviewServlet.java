package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.test.tougao.dao.ReviewTempDao;
import com.test.tougao.entity.ReviewTemp;

public class SaveReviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SaveReviewServlet() {
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
		String savetime = df.format(new Date()) + "";
		//System.out.println(time);
		ReviewTempDao reviewtempDao=new ReviewTempDao();
		ReviewTemp reviewtemp=null;
		int n=0;
		RequestDispatcher rd = null;
		String msg="";
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
		// Multipart form  
        if (isMultipart)  
        {  
            // Create a factory for disk-based file items  
            FileItemFactory factory = new DiskFileItemFactory();  
  
            // Create a new file upload handler  
            ServletFileUpload upload = new ServletFileUpload(factory);  
  
            try  
            {  
                // Parse the request  
                List<FileItem> items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
  
                // Process the uploaded items  
                Iterator<FileItem> iter = items.iterator();  
  
                // Parameters map  
                Map<String,String> params = new HashMap<String,String>();  
  
                // Do list  
                while (iter.hasNext())  
                {  
                    FileItem item = iter.next();  
                     
                    // Form Field  
                    if (item.isFormField())  
                    {  
                        // Field name  
                        String name = item.getFieldName();  
                         
                        // Set charset = UTF-8 Default = ISO-8859-1   
                        // Get field value  
                        String value = item.getString("utf-8");  
                          
                        // Put into map  
                        params.put(name, value.trim());  
                    }  
                }  
   
                String expertno=(String)params.get("expertno");
        		String papername=(String)params.get("papername");
        		String papertype=(String)params.get("papertype");
        		String allottime=(String)params.get("allottime");
        		String select=(String)params.get("select");
        		String paperlevel=(String)params.get("level");
        		String messagename=(String)params.get("messagename");
        		String papermessage=(String)params.get("message");
        		reviewtemp=reviewtempDao.findReviewTemp(expertno, papername, allottime);
    			if(reviewtemp==null){
    				reviewtemp=new ReviewTemp();
    				reviewtemp.setExpertNo(expertno);
    				reviewtemp.setPaperName(papername);
    				reviewtemp.setPaperType(papertype);
    				reviewtemp.setPaperLevel(paperlevel);
    				reviewtemp.setAllotTime(allottime);
    				reviewtemp.setSaveTime(savetime);
    				reviewtemp.setIsAgreed(select);
    				reviewtemp.setMessageName(messagename.trim());
    				reviewtemp.setPaperMessage(papermessage);
    				n=reviewtempDao.add(reviewtemp);
    				
    			}else{
    				reviewtemp.setPaperLevel(paperlevel);
    				reviewtemp.setAllotTime(allottime);
    				reviewtemp.setSaveTime(savetime);
    				reviewtemp.setIsAgreed(select);
    				reviewtemp.setMessageName(messagename.trim());
    				reviewtemp.setPaperMessage(papermessage);
    				n=reviewtempDao.update(reviewtemp);
    				
    			}
    			if(n>0){
   				 msg+="恭喜您，保存成功！";
   				 request.setAttribute("msg",msg);
   				 rd = request.getRequestDispatcher("expert/showsuccess.jsp");
   				 rd.forward(request, response);
   			    }else{
   				 msg+="对不起，保存失败！";//此时是dao类执行错误
   				 request.setAttribute("msg",msg);
   				 rd = request.getRequestDispatcher("expert/showerror.jsp");
   				 rd.forward(request, response);
   			    }
  
            }  
            catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}  
  
        }  
     // Simple form  
        else  
        {  
            // SetCharacterEncoding = UTF-8  
            request.setCharacterEncoding("utf-8");  
  
            String expertno=(String)request.getParameter("expertno");
    		String papername=(String)request.getParameter("papername");
    		String papertype=(String)request.getParameter("papertype");
    		String allottime=(String)request.getParameter("allottime");
    		String select=(String)request.getParameter("select");
    		String paperlevel=(String)request.getParameter("level");
    		String messagename=(String)request.getParameter("messagename");
    		String papermessage=(String)request.getParameter("message"); 
    		try {
    			
    			reviewtemp=reviewtempDao.findReviewTemp(expertno, papername, allottime);
    			if(reviewtemp==null){
    				reviewtemp=new ReviewTemp();
    				reviewtemp.setExpertNo(expertno);
    				reviewtemp.setPaperName(papername);
    				reviewtemp.setPaperType(papertype);
    				reviewtemp.setPaperLevel(paperlevel);
    				reviewtemp.setAllotTime(allottime);
    				reviewtemp.setSaveTime(savetime);
    				reviewtemp.setIsAgreed(select);
    				reviewtemp.setMessageName(messagename);
    				reviewtemp.setPaperMessage(papermessage);
    				n=reviewtempDao.add(reviewtemp);
    				
    			}else{
    				reviewtemp.setPaperLevel(paperlevel);
    				reviewtemp.setSaveTime(savetime);
    				reviewtemp.setIsAgreed(select);
    				reviewtemp.setMessageName(messagename);
    				reviewtemp.setPaperMessage(papermessage);
    				n=reviewtempDao.update(reviewtemp);
    				
    			}
    			if(n>0){
    				 msg+="恭喜您，保存成功！";
    				 request.setAttribute("msg",msg);
    				 rd = request.getRequestDispatcher("expert/showsuccess.jsp");
    				 rd.forward(request, response);
    			}else{
    				 msg+="对不起，保存失败！";//此时是dao类执行错误
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

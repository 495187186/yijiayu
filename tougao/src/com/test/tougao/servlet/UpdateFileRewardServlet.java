package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.tougao.dao.AdminFileDao;
import com.test.tougao.entity.AdminFile;

public class UpdateFileRewardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdateFileRewardServlet() {
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
		String docname=request.getParameter("docname");
		String reward=request.getParameter("reward");
		
		int n=0;
		RequestDispatcher rd = null;
		String msg="";
		AdminFileDao adminfileDao=new AdminFileDao();
		AdminFile adminfile=null;
		try {
			adminfile=adminfileDao.findByName(docname.trim());
			adminfile.setFileTime(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
			adminfile.setIsReward("1");
			adminfile.setFileReward(reward);
			n=adminfileDao.update(adminfile);
			if(n>0){
				msg+="恭喜您，提交成功！";
  				request.setAttribute("msg",msg);
  				rd = request.getRequestDispatcher("admin/showsuccess.jsp");
  				rd.forward(request, response);
			}else{
   				 msg+="对不起，提交失败！";//此时是dao类执行错误
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

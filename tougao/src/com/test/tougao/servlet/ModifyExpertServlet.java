package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.tougao.dao.ExpertDao;
import com.test.tougao.entity.Expert;

public class ModifyExpertServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ModifyExpertServlet() {
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
		
		String no=request.getParameter("no");
//		String name=request.getParameter("name");
//		String gender=request.getParameter("gender");
//		String title=request.getParameter("title");
//		String nation=request.getParameter("nation");
//		String birthday=request.getParameter("birthday");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String department=request.getParameter("department");
		
		int n=0;
		RequestDispatcher rd = null;
		ExpertDao expertDao=new ExpertDao();
		Expert expert=null;
        String msg="";
        
        try {
        	  expert= expertDao.findByNo(no);
			  expert.setExpertMobile(mobile);
			  expert.setExpertEmail(email);
			  expert.setExpertDepartment(department);
			  n=expertDao.update(expert);
			  if(n>0){
				  System.out.println("修改个人信息成功！");
				  msg+="恭喜您，修改成功！";
			      request.setAttribute("msg",msg);
				  rd = request.getRequestDispatcher("expert/showsuccess.jsp");
				  rd.forward(request, response);
			  }else{
				  System.out.println("修改失败！");
				  msg+="对不起，失败！";
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

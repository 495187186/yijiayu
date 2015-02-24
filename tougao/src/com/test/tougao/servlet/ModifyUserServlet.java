package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.tougao.dao.AdminDao;
import com.test.tougao.dao.ExpertDao;
import com.test.tougao.dao.StudentDao;
import com.test.tougao.entity.Admin;
import com.test.tougao.entity.Expert;
import com.test.tougao.entity.Student;

public class ModifyUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ModifyUserServlet() {
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
		String usertype=request.getParameter("usertype");
		AdminDao adminDao=new AdminDao();
		Admin admin=null;
		ExpertDao expertDao=new ExpertDao();
		Expert expert=null;
		StudentDao studentDao=new StudentDao();
		Student student=null;
		
		HttpSession session=request.getSession();
		RequestDispatcher rd = null;
		String msg="";
		try {
			if(usertype.equals("1")){//修改超管信息
			   admin=adminDao.findByNo(no);
			   if(admin==null){
				   System.out.println("该超管账户不存在！");
				   msg+="对不起，该超管账户不存在！";
				   request.setAttribute("msg",msg);
				   rd = request.getRequestDispatcher("admin/showerror.jsp");
				   rd.forward(request, response); 
			   }else{
				   System.out.println("修改超管信息！");
				   request.setAttribute("no",no);
				   rd = request.getRequestDispatcher("admin/modifyadmininfo.jsp");
				   rd.forward(request, response); 
			   }
			}else if(usertype.equals("2")){//修改专家信息
			   expert=expertDao.findByNo(no);
			   if(expert==null){
				   System.out.println("该专家账户不存在！");
				   msg+="对不起，该专家账户不存在！";
				   request.setAttribute("msg",msg);
				   rd = request.getRequestDispatcher("admin/showerror.jsp");
				   rd.forward(request, response); 
			   }else{
				   System.out.println("修改专家信息！");
				   request.setAttribute("no",no);
				   rd = request.getRequestDispatcher("admin/modifyexpertinfo.jsp");
				   rd.forward(request, response); 
			   }
			}else{//修改学生信息
			   student=studentDao.findByNo(no);
			   if(student==null){
				   System.out.println("该学生账户不存在！");
				   msg+="对不起，该学生账户不存在！";
				   request.setAttribute("msg",msg);
				   rd = request.getRequestDispatcher("admin/showerror.jsp");
				   rd.forward(request, response); 
			   }else{
				   System.out.println("修改学生信息！");
				   request.setAttribute("no",no);
				   rd = request.getRequestDispatcher("admin/modifystudentinfo.jsp");
				   rd.forward(request, response); 
			   }
			   
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

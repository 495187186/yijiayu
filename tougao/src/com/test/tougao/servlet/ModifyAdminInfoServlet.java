package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.tougao.dao.AdminDao;
import com.test.tougao.dao.UserDao;
import com.test.tougao.entity.Admin;
import com.test.tougao.entity.User;

public class ModifyAdminInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ModifyAdminInfoServlet() {
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
	    String name=request.getParameter("name");
	    String gender=request.getParameter("gender");
	    String mobile=request.getParameter("mobile").equals("null")?"":request.getParameter("mobile");
	    String email=request.getParameter("email").equals("null")?"":request.getParameter("email");
	    
	    int m,n=0;
		RequestDispatcher rd = null;
		String msg="";
	    UserDao userDao=new UserDao();
	    User user=null;
	    AdminDao adminDao=new AdminDao();
	    Admin admin=null;
	    try {
			admin = adminDao.findByNo(no);
			user=userDao.findByNo(no);
			admin.setAdminNo(no);
			admin.setAdminName(name);
			admin.setAdminGender(gender);
			admin.setAdminMobile(mobile);
			admin.setAdminEmail(email);
			m=adminDao.update(admin);
			user.setUser_name(name);//修改用户名
			n=userDao.update(user);
			if(m>0&&n>0){
				 msg+="恭喜您，修改超管信息成功！";
				 request.setAttribute("msg",msg);
				 rd = request.getRequestDispatcher("admin/showsuccess.jsp");
				 rd.forward(request, response); 
			}else{
				 msg+="对不起，修改失败！";
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

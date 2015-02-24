package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.tougao.bean.MD5Util;
import com.test.tougao.dao.UserDao;
import com.test.tougao.entity.User;

public class UpdateUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdateUserServlet() {
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
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int n=0;
		UserDao userDao = new UserDao();
		User user = null;
		RequestDispatcher rd = null;
		String msg="";
		request.setCharacterEncoding("UTF-8");//字符编码
		String id=request.getParameter("UserId");
		String password=request.getParameter("password");
		String role=request.getParameter("role");
		String active=request.getParameter("active");
		MD5Util md5Util=new MD5Util();
		String pwdmd5=md5Util.getMD5String(password.trim());
		
		try{
			user=userDao.findById(id);
			user.setUser_password(pwdmd5);
			user.setUser_role(role);
			user.setUser_active(active);
			n=userDao.update(user);
			if(n>0){
				msg+="恭喜您，修改成功！";
				request.setAttribute("msg",msg);
				rd = request.getRequestDispatcher("admin/manageresult.jsp");
				rd.forward(request, response);
			}else{
				msg+="修改失败！";//此时是dao类执行错误
				request.setAttribute("msg",msg);
				rd = request.getRequestDispatcher("admin/showerror.jsp");
				rd.forward(request, response);
			}
		}catch (SQLException e) {
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

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

public class ModifyStudentPwdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ModifyStudentPwdServlet() {
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
		RequestDispatcher rd = null;
		String msg="";
		request.setCharacterEncoding("UTF-8");//字符编码
		String account = request.getParameter("account");
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		String re_password = request.getParameter("re_password");
		UserDao userDao=new UserDao();
		User user=null;
		MD5Util md5Util=new MD5Util();
		String oldpwd=md5Util.getMD5String(old_password.trim());
	    String pwdmd5=null;
		
		try {
			user = userDao.findByNo(account);
			if(!user.getUser_password().equals(oldpwd))
			{
				 msg+="修改失败，原密码输入错误！";
				 request.setAttribute("msg",msg);
				 rd = request.getRequestDispatcher("student/showerror.jsp");
				 rd.forward(request, response);
			}else{
				if(new_password.length()<6){
					msg+="修改失败，新密码输入不能少于6位！";
					request.setAttribute("msg",msg);
					rd = request.getRequestDispatcher("student/showerror.jsp");
					rd.forward(request, response);
				}else{
			    if(!new_password.equals(re_password)){
			    	msg+="修改失败，两次输入新密码不一致！";
					request.setAttribute("msg",msg);
					rd = request.getRequestDispatcher("student/showerror.jsp");
					rd.forward(request, response);
			    }else{
			    	pwdmd5=md5Util.getMD5String(new_password.trim());
			    	user.setUser_password(pwdmd5);
					n=userDao.updatepassword(user);
					if(n>0){
						 msg+="恭喜您，密码修改成功！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("student/showsuccess.jsp");
						 rd.forward(request, response);
					}else{
						 msg+="对不起，密码修改失败！";//此时是dao类执行错误
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("student/showerror.jsp");
						 rd.forward(request, response);
					}
			     }
			    
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

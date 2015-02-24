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

import com.test.tougao.bean.MD5Util;
import com.test.tougao.dao.UserDao;
import com.test.tougao.entity.User;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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

        
        request.setCharacterEncoding("UTF-8");
        String account=request.getParameter("txtName");//账户
        String password=request.getParameter("txtPwd");//密码
        String code=request.getParameter("txtCode");//验证码
        
        RequestDispatcher rd = null;
        HttpSession session=request.getSession();
        String rand = session.getAttribute("rand").toString();//获取系统验证码
        
        UserDao userDao=new UserDao();
        User user=null;
        String msg="登录失败，";
        MD5Util md5Util=new MD5Util();
	    String pwdmd5=md5Util.getMD5String(password.trim());
        
        System.out.println("=====登录信息=====");
        try {
        	  System.out.println("账户："+account);
			  System.out.println("密码："+password);
			  user= userDao.findByNo(account);
			  if(user==null){
					 msg+="该用户不存在！";
					 request.setAttribute("msg",msg);
					 rd = request.getRequestDispatcher("loginerror.jsp");
					 rd.forward(request, response);
				}
			  else{
					if(!user.getUser_password().equals(pwdmd5)){
					 msg+="密码输入错误！";
					 request.setAttribute("msg",msg);
					 rd = request.getRequestDispatcher("loginerror.jsp");
					 rd.forward(request, response);
					}else{
						if(!rand.equalsIgnoreCase(code)){//不区分大小写
							msg+="验证码错误！";
							request.setAttribute("msg",msg);
							rd = request.getRequestDispatcher("loginerror.jsp");
							rd.forward(request, response);
						}else{
							if(user.getUser_active().equals("0")){
								msg+="用户状态未激活！";
								request.setAttribute("msg",msg);
								rd = request.getRequestDispatcher("loginerror.jsp");
								rd.forward(request, response);
							}else{
								if(user.getUser_role().equals("1")){
								    response.sendRedirect("admin/frame.jsp");
								}else if(user.getUser_role().equals("2")){
								    response.sendRedirect("expert/frame.jsp"); 
								}else{
									response.sendRedirect("student/frame.jsp");
								}
								session.setAttribute("account", user.getUser_account());
								session.setAttribute("username",user.getUser_name());
								session.setAttribute("role",user.getUser_role());
								
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

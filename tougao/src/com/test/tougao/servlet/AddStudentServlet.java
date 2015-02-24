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
import com.test.tougao.dao.StudentDao;
import com.test.tougao.dao.UserDao;
import com.test.tougao.entity.Student;
import com.test.tougao.entity.User;

public class AddStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AddStudentServlet() {
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
		String role=request.getParameter("role");
		String college=request.getParameter("college");
		String major=request.getParameter("major");
		String date=request.getParameter("date");
		String teacher=request.getParameter("teacher").equals("null")?"":request.getParameter("teacher");
		String mobile=request.getParameter("mobile").equals("null")?"":request.getParameter("mobile");
		String email=request.getParameter("email").equals("null")?"":request.getParameter("email");
		//System.out.println(college);
		//System.out.println(major);
		
		int m,n=0;
		RequestDispatcher rd = null;
		String msg="";
	    UserDao userDao=new UserDao();
	    User user=null;
	    StudentDao studentDao=new StudentDao();
	    Student student=null;
	    MD5Util md5Util=new MD5Util();
	    String pwdmd5=md5Util.getMD5String("111111");
	    
	    try {
	    	student = studentDao.findByNo(no);
			if(student!=null)
			{
				 msg+="添加失败，该用户已存在！";
				 request.setAttribute("msg",msg);
				 rd = request.getRequestDispatcher("admin/showerror.jsp");
				 rd.forward(request, response);
			}else{
				 user=new User();
				 student=new Student();
				 student.setStudentNo(no);
				 student.setStudentName(name);
				 student.setStudentGender(gender);
				 student.setStudentRole(role);
				 student.setStudentCollege(college);
				 student.setStudentMajor(major);
				 student.setStudentDate(date);
				 student.setStudentTutor(teacher);
				 student.setStudentMobile(mobile);
				 student.setStudentEmail(email);
				 m=studentDao.add(student);
				 user.setUser_account(no);
				 user.setUser_name(name);
				 user.setUser_password(pwdmd5);
				 user.setUser_active("0");
				 user.setUser_role("3");
				 n=userDao.add(user);
				 if(m>0&&n>0){
					 msg+="恭喜您，添加学生用户成功！";
					 request.setAttribute("msg",msg);
					 rd = request.getRequestDispatcher("admin/showsuccess.jsp");
					 rd.forward(request, response); 
				 }else{
					 msg+="对不起，添加失败！";
					 request.setAttribute("msg",msg);
					 rd = request.getRequestDispatcher("admin/showerror.jsp");
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

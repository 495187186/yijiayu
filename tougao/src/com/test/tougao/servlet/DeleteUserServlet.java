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
import com.test.tougao.dao.ExpertDao;
import com.test.tougao.dao.StudentDao;
import com.test.tougao.dao.UserDao;
import com.test.tougao.entity.Admin;
import com.test.tougao.entity.Expert;
import com.test.tougao.entity.Student;
import com.test.tougao.entity.User;

public class DeleteUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DeleteUserServlet() {
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
		int m,n=0;
		RequestDispatcher rd = null;
		String msg="";
	    UserDao userDao=new UserDao();
	    User user=null;
	    AdminDao adminDao=new AdminDao();
		Admin admin=null;
		ExpertDao expertDao=new ExpertDao();
		Expert expert=null;
		StudentDao studentDao=new StudentDao();
		Student student=null;
		
		try {
			if(usertype.equals("1")){//删除超管信息
			   admin=adminDao.findByNo(no);
			   if(admin==null){
				   System.out.println("该超管账户不存在！");
				   msg+="对不起，该超管账户不存在！";
				   request.setAttribute("msg",msg);
				   rd = request.getRequestDispatcher("admin/showerror.jsp");
				   rd.forward(request, response); 
			   }else{
				   user=userDao.findByNo(no);
				   m=userDao.delete(user.getUser_id());
				   n=adminDao.delete(admin.getAdminId());
				   if(m>0&&n>0){
						 msg+="恭喜您，删除超管成功！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("admin/showsuccess.jsp");
						 rd.forward(request, response); 
					}else{
						 msg+="对不起，删除失败！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("admin/showerror.jsp");
						 rd.forward(request, response);
					}
			   }
			}else if(usertype.equals("2")){//删除专家信息
			   expert=expertDao.findByNo(no);
			   if(expert==null){
				   System.out.println("该专家账户不存在！");
				   msg+="对不起，该专家账户不存在！";
				   request.setAttribute("msg",msg);
				   rd = request.getRequestDispatcher("admin/showerror.jsp");
				   rd.forward(request, response); 
			   }else{
				   user=userDao.findByNo(no);
				   m=userDao.delete(user.getUser_id());
				   n=expertDao.delete(expert.getExpertId());
				   if(m>0&&n>0){
						 msg+="恭喜您，删除专家成功！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("admin/showsuccess.jsp");
						 rd.forward(request, response); 
					}else{
						 msg+="对不起，删除失败！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("admin/showerror.jsp");
						 rd.forward(request, response);
					}
			   }
			}else{//删除学生信息
			   student=studentDao.findByNo(no);
			   if(student==null){
				   System.out.println("该学生账户不存在！");
				   msg+="对不起，该学生账户不存在！";
				   request.setAttribute("msg",msg);
				   rd = request.getRequestDispatcher("admin/showerror.jsp");
				   rd.forward(request, response); 
			   }else{
				   user=userDao.findByNo(no);
				   m=userDao.delete(user.getUser_id());
				   n=studentDao.delete(student.getStudentId());
				   if(m>0&&n>0){
						 msg+="恭喜您，删除学生成功！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("admin/showsuccess.jsp");
						 rd.forward(request, response); 
					}else{
						 msg+="对不起，删除失败！";
						 request.setAttribute("msg",msg);
						 rd = request.getRequestDispatcher("admin/showerror.jsp");
						 rd.forward(request, response);
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

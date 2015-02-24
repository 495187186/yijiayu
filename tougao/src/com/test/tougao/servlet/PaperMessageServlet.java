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

import com.test.tougao.dao.PaperMessageDao;
import com.test.tougao.dao.ReviewedDao;
import com.test.tougao.entity.PaperMessage;
import com.test.tougao.entity.Reviewed;

public class PaperMessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PaperMessageServlet() {
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

		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("Id");
		PaperMessageDao papermessageDao=new PaperMessageDao();
		PaperMessage papermessage=null;
		ReviewedDao reviewedDao=new ReviewedDao();
		Reviewed reviewed=null;
		
		RequestDispatcher rd = null;
        HttpSession session=request.getSession();

		try {
			papermessage=papermessageDao.findById(id);
			reviewed=reviewedDao.findReviewedMessage(papermessage.getMessageName(), papermessage.getMessageDate().substring(0, papermessage.getMessageDate().length()-2));
			session.setAttribute("message",reviewed);//将查到的信息放入session
			
			if(papermessage.getIsRead().equals("0")){//若未读则标记已读，否则不变
				papermessage.setMessageDate(papermessage.getMessageDate().substring(0, papermessage.getMessageDate().length()-2));
				papermessage.setUploadTime(papermessage.getUploadTime().substring(0, papermessage.getUploadTime().length()-2));
				papermessage.setIsRead("1");//已读
				papermessageDao.update(papermessage);
			}

			rd = request.getRequestDispatcher("student/message.jsp");
			rd.forward(request, response);
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

		doGet(request,response);
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

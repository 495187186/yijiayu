package com.test.tougao.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownHelpFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DownHelpFileServlet() {
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
		String fname=request.getParameter("FileName");
		
		//String downpath=request.getRealPath("/upfile");//这种方法过已过时
		String downpath = getServletContext().getRealPath("/")+"helpfiles"+"\\";
		System.out.println("下载文件路径："+downpath);
		try 
		{ 
			response.setHeader("Content-Disposition","attachment; filename=\"" + fname + "\"");//这句必须添加，否则下载时出现页面显示乱码
			fname = new String(fname.getBytes("UTF-8"),"UTF-8"); 
			OutputStream os = response.getOutputStream(); 
			FileInputStream fis = new FileInputStream(downpath+fname); 
	 
			BufferedInputStream bufIn = new BufferedInputStream(fis);
			BufferedOutputStream bufOut = new BufferedOutputStream(os);
			byte[] b = new byte[1024];
			int i = 0;
			while((i=bufIn.read(b))>0){ 
				bufOut.write(b,0,i);
			}
	        bufOut.flush();
	        bufOut.close();
	        bufIn.close();
			fis.close(); 
			os.flush(); 
			os.close(); 
		} catch ( Exception e ) {
			e.printStackTrace();
		} 	

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

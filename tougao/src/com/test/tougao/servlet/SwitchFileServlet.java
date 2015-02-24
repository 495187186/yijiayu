package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.tougao.dao.AdminFileDao;
import com.test.tougao.dao.AllotNumDao;
import com.test.tougao.entity.AdminFile;
import com.test.tougao.entity.AllotNum;

public class SwitchFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SwitchFileServlet() {
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

		request.setCharacterEncoding("UTF-8");//字符编码,注意页面中汉字搜索必须用此编码
        String result=request.getParameter("result");
        String fileid=request.getParameter("FileId");
        RequestDispatcher dispatcher=null;
        String msg="";
        int flag=0;//标记全部开放时是否有未定审稿费材料，若有则不能全部开放
        
        AdminFileDao adminfileDao=new AdminFileDao();
        List<AdminFile> list=null;
        AdminFile adminfile=null;
        AllotNumDao allotnumDao=new AllotNumDao();
        AllotNum allotnum;
		try {
			allotnum = allotnumDao.findByType("1");
			if(fileid==null||fileid.equals("")||fileid.equals("null")){//如果fileid为空则说明对全部材料记录操作
		         list=adminfileDao.findAll();
		         for(int j=0;j<list.size();j++){
		            if(list.get(j).getIsReward().equals("0")){//存在为确定审稿费的材料
		               flag=1;
		               break;
		            }
		         }
		         if(result.equals("1")){//开放要涉及指派人数清零，关闭直接关闭，其他的不变
		            if(flag==1){
		               msg+="未全部确定审稿费，暂不能全部开放！";
		               request.setAttribute("msg",msg);  								      
			           dispatcher=request.getRequestDispatcher("admin/showerror.jsp");//操作结果提示
			           dispatcher.forward(request, response);
		            
		            }else{
		                for(int i=0;i<list.size();i++){
		                adminfile=list.get(i);
		                if(adminfile.getPersonNum().equals(allotnum.getAllotNum())){//当论文的指派人数等于指派上限时开放清零
		                  adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
		                  adminfile.setFileStatus("0");
		                  adminfile.setPersonNum("0");
		                  adminfile.setIsAllot("0");
		                  adminfile.setIsChoose("1");
		                  adminfileDao.update(adminfile);
		                }else{
		                  adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
		                  adminfile.setIsChoose("1");
		                  adminfileDao.update(adminfile);
		                }   
		              }
		              msg+="恭喜您，操作成功！";
		              request.setAttribute("msg",msg);  								      
			          dispatcher=request.getRequestDispatcher("admin/modifyresult.jsp");//操作结果提示
			          dispatcher.forward(request, response);
		            }
		            
		         }else{//直接全部关闭
		            for(int i=0;i<list.size();i++){
		                adminfile=list.get(i);
		                adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
		                adminfile.setIsChoose("0");
		                adminfileDao.update(adminfile);
		            }
		            msg+="恭喜您，操作成功！";
		            request.setAttribute("msg",msg);  								      
			        dispatcher=request.getRequestDispatcher("admin/modifyresult.jsp");//操作结果提示
			        dispatcher.forward(request, response);
		         
		         }
		            
		       }else{//对单个记录修改
		         adminfile=adminfileDao.findById(fileid);
		         if(result.equals("1")){//开放要涉及指派人数清零，关闭直接关闭，其他的不变
		             if(adminfile.getIsReward().equals("0")){//查询是否确定审稿费，未确定则不能开放
		                msg+="未确定审稿费，暂不能开放！";
		                request.setAttribute("msg",msg);  								      
			            dispatcher=request.getRequestDispatcher("admin/showerror.jsp");//操作结果提示
			            dispatcher.forward(request, response);
		             
		             }else{
		                if(adminfile.getPersonNum().equals(allotnum.getAllotNum())){//当论文的指派人数等于指派上限时开放清零
		                  adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
		                  adminfile.setFileStatus("0");
		                  adminfile.setPersonNum("0");
		                  adminfile.setIsAllot("0");
		                  adminfile.setIsChoose("1");
		                  adminfileDao.update(adminfile);
		                }else{
		                  adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
		                  adminfile.setIsChoose("1");
		                  adminfileDao.update(adminfile);
		                }
		                msg+="恭喜您，操作成功！";
		                request.setAttribute("msg",msg);  								      
			            dispatcher=request.getRequestDispatcher("admin/modifyresult.jsp");//操作结果提示
			            dispatcher.forward(request, response);   
		             }
		             
		         }else{
		              adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
		              adminfile.setIsChoose("0");
		              adminfileDao.update(adminfile);
		              msg+="恭喜您，操作成功！";
		              request.setAttribute("msg",msg);  								      
			          dispatcher=request.getRequestDispatcher("admin/modifyresult.jsp");//操作结果提示
			          dispatcher.forward(request, response);
		         
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

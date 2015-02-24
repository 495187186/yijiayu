package com.test.tougao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.tougao.dao.AdminFileDao;
import com.test.tougao.dao.AllotFileDao;
import com.test.tougao.dao.AllotNumDao;
import com.test.tougao.dao.ExpertDao;
import com.test.tougao.dao.ReviewFileDao;
import com.test.tougao.dao.StaFileDao;
import com.test.tougao.entity.AdminFile;
import com.test.tougao.entity.AllotFile;
import com.test.tougao.entity.AllotNum;
import com.test.tougao.entity.Expert;
import com.test.tougao.entity.ReviewFile;
import com.test.tougao.entity.StaFile;

public class FileAllocationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public FileAllocationServlet() {
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
		String expertno=request.getParameter("expertList2");//获取专家id,可能是个数组
		String []files=request.getParameterValues("fileList2");//获取指派材料的id
		int flag=0,m=0;
		RequestDispatcher rd = null;
		String msg="";
		int n=0;//重复分配的篇数
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(date) + "";//系统分配时间
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.MONTH, 1);//月份加一//减一为负号
		String deadline=df.format(calendar.getTime()) + "";//审核截止日期:限期一个月
		System.out.println("审批截止日期："+deadline);
		
		AllotNumDao allotnumDao=new AllotNumDao();
		AllotNum allotnum=null;
		
		ExpertDao expertDao=new ExpertDao();
		Expert expert=null;
		
		AdminFileDao adminfileDao=new AdminFileDao();
		AdminFile adminfile=null;
		
		AllotFileDao allotfileDao=new AllotFileDao();
		AllotFile allotfile=null;
		List<AllotFile> list=null;
		
		ReviewFileDao reviewfileDao=new ReviewFileDao();
		ReviewFile reviewfile=null;
		
		StaFileDao stafileDao=new StaFileDao();
		StaFile stafile=null;
		
		try {
			allotnum=allotnumDao.findByType("1");//查询每篇材料的指派人数上限，超过上限则关闭
			expert=expertDao.findByNo(expertno);//查询要指派的专家
			
			for(int i=0;i<files.length;i++){
				adminfile=adminfileDao.findById(files[i]);//查询材料
				list=allotfileDao.findByExpertFileName(expert.getExpertName().trim(),adminfile.getFileName().trim());//查询该专家的所有分配论文
				for(int j=0;j<list.size();j++){//通过上传时间来区分每篇材料是否被重复指派
					if(list.get(j).getUploadTime().equals(adminfile.getFileTime())){//如果已指派过
						flag=1;//已指派
						n++;//重复数量+1
						break;
					}
				}
				if(flag==1){//如果重复指派
					flag=0;//标志置零，不指派
				}else{//未指派过，则指派
					//添加allotfile表，供超管查看
					allotfile=new AllotFile();
					allotfile.setFileName(adminfile.getFileName().trim());
					allotfile.setFileUploader(adminfile.getUploader().trim());
					allotfile.setExpertName(expert.getExpertName().trim());
					allotfile.setUploadTime(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
					allotfile.setAllotTime(time);
					allotfile.setFileReward(adminfile.getFileReward());
					allotfile.setFileFlag("0");
					allotfile.setIsReviewed("0");//刚指派，初始状态未审批
					allotfile.setIsAgreed("0");//初始状态不同意
					allotfileDao.add(allotfile);//添加数据
					
					//添加reviewfile表，供专家查看已分配的未审批材料
					reviewfile=reviewfileDao.findIsAllot(expertno, adminfile.getFileName().trim(), adminfile.getFileTime());
					if(reviewfile==null){//未分配过该专家
						reviewfile=new ReviewFile();
						reviewfile.setExpertNo(expert.getExpertNo());
						reviewfile.setDocName(adminfile.getFileName().trim());
						reviewfile.setDocType(adminfile.getFileType());
						reviewfile.setUploadDate(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
						reviewfile.setAllotDate(time);
						reviewfile.setDocDeadline(deadline);
						reviewfile.setDocReward(adminfile.getFileReward());
						reviewfile.setIsReviewed("0");
						reviewfile.setDocFlag("0");
						reviewfile.setIsAgreed("0");
						reviewfileDao.add(reviewfile);//添加数据
					}
					
					//添加统计结果表，供管理员查看
					stafile=stafileDao.findByFileName(adminfile.getFileName().trim());
					if(stafile==null){//未统计过
						stafile=new StaFile();
						stafile.setFileName(adminfile.getFileName().trim());
						stafile.setFileType(adminfile.getFileType());
						stafile.setFileUploader(adminfile.getUploader().trim());
						stafile.setUploadTime(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
						stafile.setAllotCounter(adminfile.getPersonNum());
						stafile.setFileReward(adminfile.getFileReward());
						stafile.setFileStatus("0");
						stafileDao.add(stafile);//添加数据
					}

					//更新file表
					adminfile.setFileTime(adminfile.getFileTime().substring(0, adminfile.getFileTime().length()-2));
					adminfile.setIsAllot("1");//已被指派
					adminfile.setPersonNum(Integer.parseInt(adminfile.getPersonNum())+1+"");//指派人数加1
					if(adminfile.getPersonNum().equals(allotnum.getAllotNum())){
						adminfile.setIsChoose("0");//当每篇材料的指派数量达到上限则关闭
					}
					adminfileDao.update(adminfile);
					
				}
				
			}
			expert.setPaperNum(Integer.parseInt(expert.getPaperNum())+files.length-n+"");//计算待审论文数量：原来的+现分配的
			m=expertDao.update(expert);//更新数据
			if(m>0){
				 msg+="恭喜您，分配成功！";
				 request.setAttribute("msg",msg);
				 rd = request.getRequestDispatcher("admin/showsuccess.jsp");
				 rd.forward(request, response);
			}else{
				 msg+="对不起，分配失败！";
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

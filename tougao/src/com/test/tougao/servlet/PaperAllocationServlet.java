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

import com.test.tougao.dao.AllotNumDao;
import com.test.tougao.dao.AllotPaperDao;
import com.test.tougao.dao.ExpertDao;
import com.test.tougao.dao.PaperDao;
import com.test.tougao.dao.ReviewPaperDao;
import com.test.tougao.dao.StaPaperDao;
import com.test.tougao.entity.AllotNum;
import com.test.tougao.entity.AllotPaper;
import com.test.tougao.entity.Expert;
import com.test.tougao.entity.Paper;
import com.test.tougao.entity.ReviewPaper;
import com.test.tougao.entity.StaPaper;

public class PaperAllocationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PaperAllocationServlet() {
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
		String []papers=request.getParameterValues("paperList2");//获取指派论文的id
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
		
		PaperDao paperDao=new PaperDao();
		Paper paper=null;
		
		AllotPaperDao allotpaperDao=new AllotPaperDao();
		AllotPaper allotpaper=null;
		List<AllotPaper> list=null;
		
		ReviewPaperDao reviewpaperDao=new ReviewPaperDao();
		ReviewPaper reviewpaper=null;
		
		StaPaperDao stapaperDao=new StaPaperDao();
		StaPaper stapaper=null;
		
		try {
			allotnum=allotnumDao.findByType("0");//查询每篇论文的指派人数上限，超过上限则关闭
			expert=expertDao.findByNo(expertno);//查询要指派的专家
			
			for(int i=0;i<papers.length;i++){
				paper=paperDao.findById(papers[i]);//查询论文
				list=allotpaperDao.findByExpertPaperName(expert.getExpertName().trim(),paper.getPaperName().trim());//查询该专家的所有分配论文
				for(int j=0;j<list.size();j++){//通过上传时间来区分每篇论文是否被重复指派
					if(list.get(j).getUploadTime().equals(paper.getPaperTime())){//如果已指派过
						flag=1;//已指派
						n++;//重复数量+1
						break;
					}
				}
				if(flag==1){//如果重复指派
					flag=0;//标志置零，不指派
				}else{//未指派过，则指派
					//添加allotpaper表，供超管查看
					allotpaper=new AllotPaper();
					allotpaper.setPaperName(paper.getPaperName().trim());
					allotpaper.setPaperUploader(paper.getUploader().trim());
					allotpaper.setPaperWriter(paper.getPaperWriter().trim());
					allotpaper.setExpertName(expert.getExpertName().trim());
					allotpaper.setUploadTime(paper.getPaperTime().substring(0, paper.getPaperTime().length()-2));
					allotpaper.setAllotTime(time);
					allotpaper.setPaperReward(paper.getPaperReward());
					allotpaper.setPaperFlag("0");
					allotpaper.setIsReviewed("0");//刚指派，初始状态未审批
					allotpaper.setIsAgreed("0");//初始状态不同意
					allotpaperDao.add(allotpaper);//添加数据
					
					//添加reviewpaper表，供专家查看已分配的未审批论文
					reviewpaper=reviewpaperDao.findIsAllot(expertno, paper.getPaperName().trim(), paper.getPaperTime());
					if(reviewpaper==null){//未分配过该专家
						reviewpaper=new ReviewPaper();
						reviewpaper.setExpertNo(expert.getExpertNo());
						reviewpaper.setPaperName(paper.getPaperName().trim());
						reviewpaper.setPaperType(paper.getPaperType());
						reviewpaper.setUploadDate(paper.getPaperTime().substring(0, paper.getPaperTime().length()-2));
						reviewpaper.setAllotDate(time);
						reviewpaper.setPaperDeadline(deadline);
						reviewpaper.setPaperReward(paper.getPaperReward());
						reviewpaper.setIsReviewed("0");
						reviewpaper.setPaperFlag("0");
						reviewpaper.setIsAgreed("0");
						reviewpaperDao.add(reviewpaper);//添加数据
					}
					
					//添加统计结果表，供管理员查看
					stapaper=stapaperDao.findByPaperName(paper.getPaperName().trim());
					if(stapaper==null){//未统计过
						stapaper=new StaPaper();
						stapaper.setPaperName(paper.getPaperName().trim());
						stapaper.setPaperType(paper.getPaperType());
						stapaper.setPaperUploader(paper.getUploader().trim());
						stapaper.setPaperWriter(paper.getPaperWriter().trim());
						stapaper.setUploadTime(paper.getPaperTime().substring(0, paper.getPaperTime().length()-2));
						stapaper.setAllotCounter(Integer.parseInt(paper.getPersonNum())+1+"");
						stapaper.setPaperReward(paper.getPaperReward());
						stapaper.setPaperStatus("0");
						stapaperDao.add(stapaper);//添加数据
					}

					//更新paper表
					paper.setPaperTime(paper.getPaperTime().substring(0, paper.getPaperTime().length()-2));
					paper.setIsAllot("1");//已被指派
					paper.setPersonNum(Integer.parseInt(paper.getPersonNum())+1+"");//指派人数加1
					if(paper.getPersonNum().equals(allotnum.getAllotNum())){
						paper.setIsChoose("0");//当每篇论文的指派数量达到上限则关闭
					}
					
					paperDao.update(paper);
					
				}
				
			}
			expert.setPaperNum(Integer.parseInt(expert.getPaperNum())+papers.length-n+"");//计算待审论文数量：原来的+现分配的
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

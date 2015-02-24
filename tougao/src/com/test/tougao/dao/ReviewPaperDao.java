package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.ReviewPaper;

public class ReviewPaperDao {
	//添加待审论文
	public int add(ReviewPaper reviewpaper) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_reviewpaper(expert_no,paper_name,paper_type,upload_date,allot_date,paper_deadline,paper_reward,if_reviewed,paper_flag,if_agreed) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {reviewpaper.getExpertNo(),reviewpaper.getPaperName(),reviewpaper.getPaperType(),reviewpaper.getUploadDate(),reviewpaper.getAllotDate(),reviewpaper.getPaperDeadline(),reviewpaper.getPaperReward(),reviewpaper.getIsReviewed(),reviewpaper.getPaperFlag(),reviewpaper.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改待审论文
	public int update(ReviewPaper reviewpaper)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_reviewpaper set expert_no=?,paper_name=?,paper_type=?,upload_date=to_date(?,'yyyy-mm-dd HH24:MI:SS'),allot_date=to_date(?,'yyyy-mm-dd HH24:MI:SS'),paper_deadline=to_date(?,'yyyy-mm-dd HH24:MI:SS'),paper_reward=?,if_reviewed=?,paper_flag=?,if_agreed=? where reviewpaper_id = ?";
		//参数对应JSP页面获取值
		String[] params = {reviewpaper.getExpertNo(),reviewpaper.getPaperName(),reviewpaper.getPaperType(),reviewpaper.getUploadDate(),reviewpaper.getAllotDate(),reviewpaper.getPaperDeadline(),reviewpaper.getPaperReward(),reviewpaper.getIsReviewed(),reviewpaper.getPaperFlag(),reviewpaper.getIsAgreed(),reviewpaper.getReviewPaperId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除待审论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_reviewpaper where reviewpaper_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询每个专家的待审论文
	 @SuppressWarnings("unchecked")
	 public ReviewPaper findById(String id)throws SQLException, ClassNotFoundException{
		 ReviewPaper reviewpaper = null;
		 String sql = "select * from tg_reviewpaper where reviewpaper_id = ?";	
		 String[] params = {id};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象reviewpaper=new ReviewPaper();
			 reviewpaper=new ReviewPaper();
	    	 reviewpaper.setReviewPaperId(map.get("reviewpaper_id").toString());
	    	 reviewpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewpaper.setPaperName(map.get("paper_name").toString());
			 reviewpaper.setPaperType(map.get("paper_type").toString());
			 reviewpaper.setUploadDate(map.get("upload_date").toString());
			 reviewpaper.setAllotDate(map.get("allot_date").toString());
			 reviewpaper.setPaperDeadline(map.get("paper_deadline").toString());
			 reviewpaper.setPaperReward(map.get("paper_reward").toString());
			 reviewpaper.setIsReviewed(map.get("if_reviewed").toString());
			 reviewpaper.setPaperFlag(map.get("paper_flag").toString());
			 reviewpaper.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewpaper;
	 }
	//查询每个专家的待审论文
	 @SuppressWarnings("unchecked")
	 public List<ReviewPaper> findReviewPaper(String expertno,String reviewed)throws SQLException, ClassNotFoundException{
		 List<ReviewPaper> list = new ArrayList<ReviewPaper>();
		 ReviewPaper reviewpaper = null;
		 String sql = "select * from tg_reviewpaper where expert_no=? and if_reviewed=?";	
		 String[] params = {expertno,reviewed};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewpaper=new ReviewPaper();
	    	 reviewpaper.setReviewPaperId(map.get("reviewpaper_id").toString());
	    	 reviewpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewpaper.setPaperName(map.get("paper_name").toString());
			 reviewpaper.setPaperType(map.get("paper_type").toString());
			 reviewpaper.setUploadDate(map.get("upload_date").toString());
			 reviewpaper.setAllotDate(map.get("allot_date").toString());
			 reviewpaper.setPaperDeadline(map.get("paper_deadline").toString());
			 reviewpaper.setPaperReward(map.get("paper_reward").toString());
			 reviewpaper.setIsReviewed(map.get("if_reviewed").toString());
			 reviewpaper.setPaperFlag(map.get("paper_flag").toString());
			 reviewpaper.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewpaper);
	     }
	     return list;
	 }
	//查询每个专家的待审论文
	 @SuppressWarnings("unchecked")
	 public ReviewPaper findPaper(String expertno,String papername,String allotdate)throws SQLException, ClassNotFoundException{
		 ReviewPaper reviewpaper = null;
		 String sql = "select * from tg_reviewpaper where expert_no=? and paper_name=? and allot_date=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,papername,allotdate};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象reviewpaper=new ReviewPaper();
			 reviewpaper=new ReviewPaper();
	    	 reviewpaper.setReviewPaperId(map.get("reviewpaper_id").toString());
	    	 reviewpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewpaper.setPaperName(map.get("paper_name").toString());
			 reviewpaper.setPaperType(map.get("paper_type").toString());
			 reviewpaper.setUploadDate(map.get("upload_date").toString());
			 reviewpaper.setAllotDate(map.get("allot_date").toString());
			 reviewpaper.setPaperDeadline(map.get("paper_deadline").toString());
			 reviewpaper.setPaperReward(map.get("paper_reward").toString());
			 reviewpaper.setIsReviewed(map.get("if_reviewed").toString());
			 reviewpaper.setPaperFlag(map.get("paper_flag").toString());
			 reviewpaper.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewpaper;
	 }
	//查询每个专家是否已分配过同一篇论文
	 @SuppressWarnings("unchecked")
	 public ReviewPaper findIsAllot(String expertno,String papername,String uploaddate)throws SQLException, ClassNotFoundException{
		 ReviewPaper reviewpaper = null;
		 String sql = "select * from tg_reviewpaper where expert_no=? and paper_name=? and upload_date=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,papername,uploaddate};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象reviewpaper=new ReviewPaper();
			 reviewpaper=new ReviewPaper();
	    	 reviewpaper.setReviewPaperId(map.get("reviewpaper_id").toString());
	    	 reviewpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewpaper.setPaperName(map.get("paper_name").toString());
			 reviewpaper.setPaperType(map.get("paper_type").toString());
			 reviewpaper.setUploadDate(map.get("upload_date").toString());
			 reviewpaper.setAllotDate(map.get("allot_date").toString());
			 reviewpaper.setPaperDeadline(map.get("paper_deadline").toString());
			 reviewpaper.setPaperReward(map.get("paper_reward").toString());
			 reviewpaper.setIsReviewed(map.get("if_reviewed").toString());
			 reviewpaper.setPaperFlag(map.get("paper_flag").toString());
			 reviewpaper.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewpaper;
	 }
	//查询每个专家的所有指派论文
	 @SuppressWarnings("unchecked")
	 public List<ReviewPaper> findReviewPaper(String expertno)throws SQLException, ClassNotFoundException{
		 List<ReviewPaper> list = new ArrayList<ReviewPaper>();
		 ReviewPaper reviewpaper = null;
		 String sql = "select * from tg_reviewpaper where expert_no=?";	
		 String[] params = {expertno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewpaper=new ReviewPaper();
	    	 reviewpaper.setReviewPaperId(map.get("reviewpaper_id").toString());
	    	 reviewpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewpaper.setPaperName(map.get("paper_name").toString());
			 reviewpaper.setPaperType(map.get("paper_type").toString());
			 reviewpaper.setUploadDate(map.get("upload_date").toString());
			 reviewpaper.setAllotDate(map.get("allot_date").toString());
			 reviewpaper.setPaperDeadline(map.get("paper_deadline").toString());
			 reviewpaper.setPaperReward(map.get("paper_reward").toString());
			 reviewpaper.setIsReviewed(map.get("if_reviewed").toString());
			 reviewpaper.setPaperFlag(map.get("paper_flag").toString());
			 reviewpaper.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewpaper);
	     }
	     return list;
	 }
	//查询每个专家的同意评审论文
	 @SuppressWarnings("unchecked")
	 public List<ReviewPaper> findAgreedPaper(String expertno,String isagreed)throws SQLException, ClassNotFoundException{
		 List<ReviewPaper> list = new ArrayList<ReviewPaper>();
		 ReviewPaper reviewpaper = null;
		 String sql = "select * from tg_reviewpaper where expert_no=? and if_agreed=?";	
		 String[] params = {expertno,isagreed};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewpaper=new ReviewPaper();
	    	 reviewpaper.setReviewPaperId(map.get("reviewpaper_id").toString());
	    	 reviewpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewpaper.setPaperName(map.get("paper_name").toString());
			 reviewpaper.setPaperType(map.get("paper_type").toString());
			 reviewpaper.setUploadDate(map.get("upload_date").toString());
			 reviewpaper.setAllotDate(map.get("allot_date").toString());
			 reviewpaper.setPaperDeadline(map.get("paper_deadline").toString());
			 reviewpaper.setPaperReward(map.get("paper_reward").toString());
			 reviewpaper.setIsReviewed(map.get("if_reviewed").toString());
			 reviewpaper.setPaperFlag(map.get("paper_flag").toString());
			 reviewpaper.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewpaper);
	     }
	     return list;
	 }

}

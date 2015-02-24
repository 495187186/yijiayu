package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.ReviewedPaper;

public class ReviewedPaperDao {
	//添加已审论文
	public int add(ReviewedPaper reviewedpaper) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_reviewedpaper(expert_no,paper_name,paper_type,allot_time,review_time,paper_reward,if_agreed) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {reviewedpaper.getExpertNo(),reviewedpaper.getPaperName(),reviewedpaper.getPaperType(),reviewedpaper.getAllotTime(),reviewedpaper.getReviewTime(),reviewedpaper.getPaperReward(),reviewedpaper.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改待审论文
	public int update(ReviewedPaper reviewedpaper)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_reviewedpaper set expert_no=?,paper_name=?,paper_type=?,allot_time=?,review_time=?,paper_reward=?,if_agreed=? where reviewedpaper_id = ?";
		//参数对应JSP页面获取值
		String[] params = {reviewedpaper.getExpertNo(),reviewedpaper.getPaperName(),reviewedpaper.getPaperType(),reviewedpaper.getAllotTime(),reviewedpaper.getReviewTime(),reviewedpaper.getPaperReward(),reviewedpaper.getIsAgreed(),reviewedpaper.getReviewedId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除待审论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_reviewedpaper where reviewedpaper_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//查询每个专家的已审论文
	 @SuppressWarnings("unchecked")
	 public List<ReviewedPaper> findReviewedPaper(String expertno)throws SQLException, ClassNotFoundException{
		 List<ReviewedPaper> list = new ArrayList<ReviewedPaper>();
		 ReviewedPaper reviewedpaper = null;
		 String sql = "select * from tg_reviewedpaper where expert_no=?";	
		 String[] params = {expertno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewedpaper=new ReviewedPaper();
	    	 reviewedpaper.setReviewedId(map.get("reviewedpaper_id").toString());
	    	 reviewedpaper.setExpertNo(map.get("expert_no").toString());
	    	 reviewedpaper.setPaperName(map.get("paper_name").toString());
	    	 reviewedpaper.setPaperType(map.get("paper_type").toString());
	    	 reviewedpaper.setAllotTime(map.get("allot_time").toString());
	    	 reviewedpaper.setReviewTime(map.get("review_time").toString());
	    	 reviewedpaper.setPaperReward(map.get("paper_reward").toString());
	    	 reviewedpaper.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewedpaper);
	     }
	     return list;
	 }
	

}

package com.test.tougao.dao;

import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.ReviewTemp;

public class ReviewTempDao {
	//添加已审论文
	public int add(ReviewTemp reviewtemp) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_reviewedtemp(expert_no,paper_name,paper_type,if_agreed,paper_level,allot_time,save_time,message_name,paper_message) values(?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {reviewtemp.getExpertNo(),reviewtemp.getPaperName(),reviewtemp.getPaperType(),reviewtemp.getIsAgreed(),reviewtemp.getPaperLevel(),reviewtemp.getAllotTime(),reviewtemp.getSaveTime(),reviewtemp.getMessageName(),reviewtemp.getPaperMessage()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改待审论文
	public int update(ReviewTemp reviewtemp)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_reviewedtemp set expert_no=?,paper_name=?,paper_type=?,if_agreed=?,paper_level=?,allot_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),save_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),message_name=?,paper_message=? where reviewedtemp_id = ?";
		//参数对应JSP页面获取值
		String[] params = {reviewtemp.getExpertNo(),reviewtemp.getPaperName(),reviewtemp.getPaperType(),reviewtemp.getIsAgreed(),reviewtemp.getPaperLevel(),reviewtemp.getAllotTime(),reviewtemp.getSaveTime(),reviewtemp.getMessageName(),reviewtemp.getPaperMessage(),reviewtemp.getReviewTempId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除待审论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_reviewedtemp where reviewedtemp_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//根据论文名和分配时间查询专家已保存的评语
	 @SuppressWarnings("unchecked")
	 public ReviewTemp findReviewTemp(String expertno,String papername,String allottime)throws SQLException, ClassNotFoundException{
		 ReviewTemp reviewtemp = null;
		 String sql = "select * from tg_reviewedtemp where expert_no=? and paper_name=? and allot_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,papername,allottime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewtemp=new ReviewTemp();
	    	 reviewtemp.setReviewTempId(map.get("reviewedtemp_id").toString());
	    	 reviewtemp.setExpertNo(map.get("expert_no").toString());
	    	 reviewtemp.setPaperName(map.get("paper_name").toString());
	    	 reviewtemp.setPaperType(map.get("paper_type").toString());
	    	 reviewtemp.setAllotTime(map.get("allot_time").toString());
	    	 reviewtemp.setIsAgreed(map.get("if_agreed").toString());
	    	 reviewtemp.setPaperLevel(map.get("paper_level").toString());
	    	 reviewtemp.setSaveTime(map.get("save_time").toString());
	    	 reviewtemp.setMessageName(map.get("message_name").toString());
	    	 reviewtemp.setPaperMessage(map.get("paper_message")==null?"":map.get("paper_message").toString());
	    	 
	     }
	     return reviewtemp;
	 }

}

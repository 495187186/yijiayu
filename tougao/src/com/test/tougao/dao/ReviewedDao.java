package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Reviewed;

public class ReviewedDao {
	//添加已审论文
	public int add(Reviewed reviewed) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_reviewed(expert_no,paper_name,paper_type,paper_level,disk_name,file_name,file_size,file_time,allot_time,submit_time,message_name,paper_message,if_agreed) values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {reviewed.getExpertNo(),reviewed.getPaperName(),reviewed.getPaperType(),reviewed.getPaperLevel(),reviewed.getDiskName(),reviewed.getFileName(),reviewed.getFileSize(),reviewed.getFileTime(),reviewed.getAllotTime(),reviewed.getSubmitTime(),reviewed.getMessageName(),reviewed.getPaperMessage(),reviewed.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据ID查询
	 @SuppressWarnings("unchecked")
	 public Reviewed findById(String id)throws SQLException, ClassNotFoundException{
		 Reviewed reviewed= null;
		 String sql = "select * from tg_reviewed where reviewed_id=?";	
		 String[] params = {id};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewed=new Reviewed();
	    	 reviewed.setReviewedId(map.get("reviewed_id").toString());
	    	 reviewed.setExpertNo(map.get("expert_no").toString());
	    	 reviewed.setPaperName(map.get("paper_name").toString());
	    	 reviewed.setPaperType(map.get("paper_type").toString());
	    	 reviewed.setDiskName(map.get("disk_name").toString());
	    	 reviewed.setFileName(map.get("file_name").toString());
	    	 reviewed.setFileSize(map.get("file_size").toString());
	    	 reviewed.setFileTime(map.get("file_time").toString());
	    	 reviewed.setAllotTime(map.get("allot_time").toString());
	    	 reviewed.setPaperLevel(map.get("paper_level").toString());
	    	 reviewed.setSubmitTime(map.get("submit_time").toString());
	    	 reviewed.setMessageName(map.get("message_name").toString());
	    	 reviewed.setPaperMessage(map.get("paper_message")==null?"":map.get("paper_message").toString());
	    	 reviewed.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewed;
	 }
	//根据论文名和提交评审时间查询专家已保存的评语
	 @SuppressWarnings("unchecked")
	 public Reviewed findReviewed(String expertno,String papername,String submittime)throws SQLException, ClassNotFoundException{
		 Reviewed reviewed= null;
		 String sql = "select * from tg_reviewed where expert_no=? and paper_name=? and submit_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,papername,submittime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewed=new Reviewed();
	    	 reviewed.setReviewedId(map.get("reviewed_id").toString());
	    	 reviewed.setExpertNo(map.get("expert_no").toString());
	    	 reviewed.setPaperName(map.get("paper_name").toString());
	    	 reviewed.setPaperType(map.get("paper_type").toString());
	    	 reviewed.setDiskName(map.get("disk_name").toString());
	    	 reviewed.setFileName(map.get("file_name").toString());
	    	 reviewed.setFileSize(map.get("file_size").toString());
	    	 reviewed.setFileTime(map.get("file_time").toString());
	    	 reviewed.setAllotTime(map.get("allot_time").toString());
	    	 reviewed.setPaperLevel(map.get("paper_level").toString());
	    	 reviewed.setSubmitTime(map.get("submit_time").toString());
	    	 reviewed.setMessageName(map.get("message_name").toString());
	    	 reviewed.setPaperMessage(map.get("paper_message")==null?"":map.get("paper_message").toString());
	    	 reviewed.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewed;
	 }
	//根据留言主题和留言日期查询专家已保存的评语
	 @SuppressWarnings("unchecked")
	 public Reviewed findReviewedMessage(String messagename,String messagedate)throws SQLException, ClassNotFoundException{
		 Reviewed reviewed= null;
		 String sql = "select * from tg_reviewed where message_name=? and submit_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {messagename,messagedate};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewed=new Reviewed();
	    	 reviewed.setReviewedId(map.get("reviewed_id").toString());
	    	 reviewed.setExpertNo(map.get("expert_no").toString());
	    	 reviewed.setPaperName(map.get("paper_name").toString());
	    	 reviewed.setPaperType(map.get("paper_type").toString());
	    	 reviewed.setDiskName(map.get("disk_name").toString());
	    	 reviewed.setFileName(map.get("file_name").toString());
	    	 reviewed.setFileSize(map.get("file_size").toString());
	    	 reviewed.setFileTime(map.get("file_time").toString());
	    	 reviewed.setAllotTime(map.get("allot_time").toString());
	    	 reviewed.setPaperLevel(map.get("paper_level").toString());
	    	 reviewed.setSubmitTime(map.get("submit_time").toString());
	    	 reviewed.setMessageName(map.get("message_name").toString());
	    	 reviewed.setPaperMessage(map.get("paper_message")==null?"":map.get("paper_message").toString());
	    	 reviewed.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewed;
	 }

}

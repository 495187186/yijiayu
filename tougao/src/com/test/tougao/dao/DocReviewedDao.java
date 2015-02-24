package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.DocReviewed;

public class DocReviewedDao {
	//添加已审论文
	public int add(DocReviewed docreviewed) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_docreviewed(expert_no,doc_name,doc_type,doc_level,disk_name,file_name,file_size,file_time,allot_time,submit_time,doc_message,if_agreed) values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {docreviewed.getExpertNo(),docreviewed.getDocName(),docreviewed.getDocType(),docreviewed.getDocLevel(),docreviewed.getDiskName(),docreviewed.getFileName(),docreviewed.getFileSize(),docreviewed.getFileTime(),docreviewed.getAllotTime(),docreviewed.getSubmitTime(),docreviewed.getDocMessage(),docreviewed.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据ID查询
	 @SuppressWarnings("unchecked")
	 public DocReviewed findById(String id)throws SQLException, ClassNotFoundException{
		 DocReviewed docreviewed= null;
		 String sql = "select * from tg_docreviewed where docreviewed_id";	
		 String[] params = {id};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 docreviewed=new DocReviewed();
	    	 docreviewed.setDocReviewedId(map.get("docreviewed_id").toString());
	    	 docreviewed.setExpertNo(map.get("expert_no").toString());
	    	 docreviewed.setDocName(map.get("doc_name").toString());
	    	 docreviewed.setDocType(map.get("doc_type").toString());
	    	 docreviewed.setDiskName(map.get("disk_name").toString());
	    	 docreviewed.setFileName(map.get("file_name").toString());
	    	 docreviewed.setFileSize(map.get("file_size").toString());
	    	 docreviewed.setFileTime(map.get("file_time").toString());
	    	 docreviewed.setAllotTime(map.get("allot_time").toString());
	    	 docreviewed.setDocLevel(map.get("doc_level").toString());
	    	 docreviewed.setSubmitTime(map.get("submit_time").toString());
	    	 docreviewed.setMessageName(map.get("message_name").toString());
	    	 docreviewed.setDocMessage(map.get("doc_message")==null?"":map.get("doc_message").toString());
	    	 docreviewed.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return docreviewed;
	 }
	//根据论文名和提交评审时间查询专家已保存的评语
	 @SuppressWarnings("unchecked")
	 public DocReviewed findReviewedDoc(String expertno,String docname,String submittime)throws SQLException, ClassNotFoundException{
		 DocReviewed docreviewed= null;
		 String sql = "select * from tg_docreviewed where expert_no=? and doc_name=? and submit_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,docname,submittime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 docreviewed=new DocReviewed();
	    	 docreviewed.setDocReviewedId(map.get("docreviewed_id").toString());
	    	 docreviewed.setExpertNo(map.get("expert_no").toString());
	    	 docreviewed.setDocName(map.get("doc_name").toString());
	    	 docreviewed.setDocType(map.get("doc_type").toString());
	    	 docreviewed.setDiskName(map.get("disk_name").toString());
	    	 docreviewed.setFileName(map.get("file_name").toString());
	    	 docreviewed.setFileSize(map.get("file_size").toString());
	    	 docreviewed.setFileTime(map.get("file_time").toString());
	    	 docreviewed.setAllotTime(map.get("allot_time").toString());
	    	 docreviewed.setDocLevel(map.get("doc_level").toString());
	    	 docreviewed.setSubmitTime(map.get("submit_time").toString());
	    	 docreviewed.setMessageName(map.get("message_name").toString());
	    	 docreviewed.setDocMessage(map.get("doc_message")==null?"":map.get("doc_message").toString());
	    	 docreviewed.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return docreviewed;
	 }
	//根据留言主题和留言日期查询专家已保存的评语
	 @SuppressWarnings("unchecked")
	 public DocReviewed findReviewedMessage(String messagename,String messagedate)throws SQLException, ClassNotFoundException{
		 DocReviewed docreviewed= null;
		 String sql = "select * from tg_docreviewed where message_name=? and submit_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {messagename,messagedate};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 docreviewed=new DocReviewed();
	    	 docreviewed.setDocReviewedId(map.get("docreviewed_id").toString());
	    	 docreviewed.setExpertNo(map.get("expert_no").toString());
	    	 docreviewed.setDocName(map.get("doc_name").toString());
	    	 docreviewed.setDocType(map.get("doc_type").toString());
	    	 docreviewed.setDiskName(map.get("disk_name").toString());
	    	 docreviewed.setFileName(map.get("file_name").toString());
	    	 docreviewed.setFileSize(map.get("file_size").toString());
	    	 docreviewed.setFileTime(map.get("file_time").toString());
	    	 docreviewed.setAllotTime(map.get("allot_time").toString());
	    	 docreviewed.setDocLevel(map.get("doc_level").toString());
	    	 docreviewed.setSubmitTime(map.get("submit_time").toString());
	    	 docreviewed.setMessageName(map.get("message_name").toString());
	    	 docreviewed.setDocMessage(map.get("doc_message")==null?"":map.get("doc_message").toString());
	    	 docreviewed.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return docreviewed;
	 }

}

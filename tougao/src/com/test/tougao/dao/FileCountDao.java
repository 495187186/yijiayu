package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.FileCount;

public class FileCountDao {
	//添加
	public int add(FileCount filecount) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_filecount(expert_no,doc_name,doc_type,review_time,doc_reward,if_agreed) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {filecount.getExpertNo(),filecount.getDocName(),filecount.getDocType(),filecount.getReviewTime(),filecount.getDocReward(),filecount.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(FileCount filecount)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_filecount set expert_no=?,doc_name=?,doc_type=?,review_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),doc_reward=?,if_agreed=? where filecount_id = ?";
		//参数对应JSP页面获取值
		String[] params = {filecount.getExpertNo(),filecount.getDocName(),filecount.getDocType(),filecount.getReviewTime(),filecount.getDocReward(),filecount.getIsAgreed(),filecount.getFileCountId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_filecount where filecount_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询评审的论文记录
	 @SuppressWarnings("unchecked")
	 public FileCount findFileCount(String expertno,String docname,String reviewtime)throws SQLException, ClassNotFoundException{
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and doc_name=? and review_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,docname,reviewtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
	    	 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return filecount;
	 }
	//查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findExpFile(String expertno)throws SQLException, ClassNotFoundException{
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=?";	
		 String[] params = {expertno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }
	//查询每个专家在某段时间内评审的论文
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findDateFile(String expertno,String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and review_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and review_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }
	//查询根据论文名每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findByDocName(String expertno,String docname)throws SQLException, ClassNotFoundException{
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and doc_name=?";	
		 String[] params = {expertno,docname};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }
	//查询根据论文类型查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findByFileType(String expertno,String doctype)throws SQLException, ClassNotFoundException{
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and doc_type=?";	
		 String[] params = {expertno,doctype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }
	//根据时间段和论文状态查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findByDateStatus(String expertno,String begintime,String endtime,String docstatus)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and review_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and review_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS') and if_agreed=?";	
		 String[] params = {expertno,begintime,endtime,docstatus};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }
	//根据时间段和论文类型查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findByDateType(String expertno,String begintime,String endtime,String doctype)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and review_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and review_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS') and doc_type=?";	
		 String[] params = {expertno,begintime,endtime,doctype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }
	//根据审核状态查询每个专家评审的论文（用以计算稿费）
	 @SuppressWarnings("unchecked")
	 public List<FileCount> findByFileStatus(String expertno,String isagreed)throws SQLException, ClassNotFoundException{
		 List<FileCount> list = new ArrayList<FileCount>();
		 FileCount filecount = null;
		 String sql = "select * from tg_filecount where expert_no=? and if_agreed=?";	
		 String[] params = {expertno,isagreed};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filecount=new FileCount();
	    	 filecount.setFileCountId(map.get("filecount_id").toString());
	    	 filecount.setExpertNo(map.get("expert_no").toString());
			 filecount.setDocName(map.get("doc_name").toString());
			 filecount.setDocType(map.get("doc_type").toString());
			 filecount.setReviewTime(map.get("review_time").toString());
			 filecount.setDocReward(map.get("doc_reward").toString());
			 filecount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(filecount);
	     }
	     return list;
	 }

}

package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.ReviewFile;

public class ReviewFileDao {
	//添加待审论文
	public int add(ReviewFile reviewfile) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_reviewfile(expert_no,doc_name,doc_type,upload_date,allot_date,doc_deadline,doc_reward,if_reviewed,doc_flag,if_agreed) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {reviewfile.getExpertNo(),reviewfile.getDocName(),reviewfile.getDocType(),reviewfile.getUploadDate(),reviewfile.getAllotDate(),reviewfile.getDocDeadline(),reviewfile.getDocReward(),reviewfile.getIsReviewed(),reviewfile.getDocFlag(),reviewfile.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改待审论文
	public int update(ReviewFile reviewfile)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_reviewfile set expert_no=?,doc_name=?,doc_type=?,upload_date=to_date(?,'yyyy-mm-dd HH24:MI:SS'),allot_date=to_date(?,'yyyy-mm-dd HH24:MI:SS'),doc_deadline=to_date(?,'yyyy-mm-dd HH24:MI:SS'),doc_reward=?,if_reviewed=?,doc_flag=?,if_agreed=? where reviewfile_id = ?";
		//参数对应JSP页面获取值
		String[] params = {reviewfile.getExpertNo(),reviewfile.getDocName(),reviewfile.getDocType(),reviewfile.getUploadDate(),reviewfile.getAllotDate(),reviewfile.getDocDeadline(),reviewfile.getDocReward(),reviewfile.getIsReviewed(),reviewfile.getDocFlag(),reviewfile.getIsAgreed(),reviewfile.getReviewFileId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除待审论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_reviewfile where reviewfile_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询每个专家的待审论文
	 @SuppressWarnings("unchecked")
	 public ReviewFile findById(String id)throws SQLException, ClassNotFoundException{
		 ReviewFile reviewfile = null;
		 String sql = "select * from tg_reviewfile where reviewfile_id = ?";	
		 String[] params = {id};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象reviewpaper=new ReviewPaper();
			 reviewfile=new ReviewFile();
	    	 reviewfile.setReviewFileId(map.get("reviewfile_id").toString());
	    	 reviewfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewfile.setDocName(map.get("doc_name").toString());
	    	 reviewfile.setDocType(map.get("doc_type").toString());
			 reviewfile.setUploadDate(map.get("upload_date").toString());
			 reviewfile.setAllotDate(map.get("allot_date").toString());
			 reviewfile.setDocDeadline(map.get("doc_deadline").toString());
			 reviewfile.setDocReward(map.get("doc_reward").toString());
			 reviewfile.setIsReviewed(map.get("if_reviewed").toString());
			 reviewfile.setDocFlag(map.get("doc_flag").toString());
			 reviewfile.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewfile;
	 }
	
	//查询每个专家的待审材料
	 @SuppressWarnings("unchecked")
	 public List<ReviewFile> findReviewFile(String expertno,String reviewed)throws SQLException, ClassNotFoundException{
		 List<ReviewFile> list = new ArrayList<ReviewFile>();
		 ReviewFile reviewfile = null;
		 String sql = "select * from tg_reviewfile where expert_no=? and if_reviewed=?";	
		 String[] params = {expertno,reviewed};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewfile=new ReviewFile();
	    	 reviewfile.setReviewFileId(map.get("reviewfile_id").toString());
	    	 reviewfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewfile.setDocName(map.get("doc_name").toString());
	    	 reviewfile.setDocType(map.get("doc_type").toString());
			 reviewfile.setUploadDate(map.get("upload_date").toString());
			 reviewfile.setAllotDate(map.get("allot_date").toString());
			 reviewfile.setDocDeadline(map.get("doc_deadline").toString());
			 reviewfile.setDocReward(map.get("doc_reward").toString());
			 reviewfile.setIsReviewed(map.get("if_reviewed").toString());
			 reviewfile.setDocFlag(map.get("doc_flag").toString());
			 reviewfile.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewfile);
	     }
	     return list;
	 }
	//查询每个专家的待审材料
	 @SuppressWarnings("unchecked")
	 public List<ReviewFile> findAgreedFile(String expertno,String isgreed)throws SQLException, ClassNotFoundException{
		 List<ReviewFile> list = new ArrayList<ReviewFile>();
		 ReviewFile reviewfile = null;
		 String sql = "select * from tg_reviewfile where expert_no=? and if_agreed=?";	
		 String[] params = {expertno,isgreed};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewfile=new ReviewFile();
	    	 reviewfile.setReviewFileId(map.get("reviewfile_id").toString());
	    	 reviewfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewfile.setDocName(map.get("doc_name").toString());
	    	 reviewfile.setDocType(map.get("doc_type").toString());
			 reviewfile.setUploadDate(map.get("upload_date").toString());
			 reviewfile.setAllotDate(map.get("allot_date").toString());
			 reviewfile.setDocDeadline(map.get("doc_deadline").toString());
			 reviewfile.setDocReward(map.get("doc_reward").toString());
			 reviewfile.setIsReviewed(map.get("if_reviewed").toString());
			 reviewfile.setDocFlag(map.get("doc_flag").toString());
			 reviewfile.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewfile);
	     }
	     return list;
	 }
	//查询每个专家的待审论文
	 @SuppressWarnings("unchecked")
	 public ReviewFile findFile(String expertno,String docname,String allotdate)throws SQLException, ClassNotFoundException{
		 ReviewFile reviewfile = null;
		 String sql = "select * from tg_reviewfile where expert_no=? and doc_name=? and allot_date=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,docname,allotdate};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象reviewpaper=new ReviewPaper();
			 reviewfile=new ReviewFile();
	    	 reviewfile.setReviewFileId(map.get("reviewfile_id").toString());
	    	 reviewfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewfile.setDocName(map.get("doc_name").toString());
	    	 reviewfile.setDocType(map.get("doc_type").toString());
			 reviewfile.setUploadDate(map.get("upload_date").toString());
			 reviewfile.setAllotDate(map.get("allot_date").toString());
			 reviewfile.setDocDeadline(map.get("doc_deadline").toString());
			 reviewfile.setDocReward(map.get("doc_reward").toString());
			 reviewfile.setIsReviewed(map.get("if_reviewed").toString());
			 reviewfile.setDocFlag(map.get("doc_flag").toString());
			 reviewfile.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewfile;
	 }
	//查询每个专家的待审论文
	 @SuppressWarnings("unchecked")
	 public ReviewFile findIsAllot(String expertno,String docname,String uploaddate)throws SQLException, ClassNotFoundException{
		 ReviewFile reviewfile = null;
		 String sql = "select * from tg_reviewfile where expert_no=? and doc_name=? and upload_date=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,docname,uploaddate};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象reviewpaper=new ReviewPaper();
			 reviewfile=new ReviewFile();
	    	 reviewfile.setReviewFileId(map.get("reviewfile_id").toString());
	    	 reviewfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewfile.setDocName(map.get("doc_name").toString());
	    	 reviewfile.setDocType(map.get("doc_type").toString());
			 reviewfile.setUploadDate(map.get("upload_date").toString());
			 reviewfile.setAllotDate(map.get("allot_date").toString());
			 reviewfile.setDocDeadline(map.get("doc_deadline").toString());
			 reviewfile.setDocReward(map.get("doc_reward").toString());
			 reviewfile.setIsReviewed(map.get("if_reviewed").toString());
			 reviewfile.setDocFlag(map.get("doc_flag").toString());
			 reviewfile.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return reviewfile;
	 }
	//查询每个专家的所有指派论文
	 @SuppressWarnings("unchecked")
	 public List<ReviewFile> findReviewFile(String expertno)throws SQLException, ClassNotFoundException{
		 List<ReviewFile> list = new ArrayList<ReviewFile>();
		 ReviewFile reviewfile = null;
		 String sql = "select * from tg_reviewfile where expert_no=?";	
		 String[] params = {expertno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewfile=new ReviewFile();
	    	 reviewfile.setReviewFileId(map.get("reviewfile_id").toString());
	    	 reviewfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewfile.setDocName(map.get("doc_name").toString());
	    	 reviewfile.setDocType(map.get("doc_type").toString());
			 reviewfile.setUploadDate(map.get("upload_date").toString());
			 reviewfile.setAllotDate(map.get("allot_date").toString());
			 reviewfile.setDocDeadline(map.get("doc_deadline").toString());
			 reviewfile.setDocReward(map.get("doc_reward").toString());
			 reviewfile.setIsReviewed(map.get("if_reviewed").toString());
			 reviewfile.setDocFlag(map.get("doc_flag").toString());
			 reviewfile.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewfile);
	     }
	     return list;
	 }

}

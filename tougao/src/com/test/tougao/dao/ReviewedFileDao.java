package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.ReviewedFile;

public class ReviewedFileDao {
	//添加已审论文
	public int add(ReviewedFile reviewedfile) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_reviewedfile(expert_no,doc_name,doc_type,allot_time,review_time,doc_reward,if_agreed) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {reviewedfile.getExpertNo(),reviewedfile.getDocName(),reviewedfile.getDocType(),reviewedfile.getAllotTime(),reviewedfile.getReviewTime(),reviewedfile.getDocReward(),reviewedfile.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改待审论文
	public int update(ReviewedFile reviewedfile)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_reviewedfile set expert_no=?,doc_name=?,doc_type=?,allot_time=?,review_time=?,doc_reward=?,if_agreed=? where reviewedfile_id = ?";
		//参数对应JSP页面获取值
		String[] params = {reviewedfile.getExpertNo(),reviewedfile.getDocName(),reviewedfile.getDocType(),reviewedfile.getAllotTime(),reviewedfile.getReviewTime(),reviewedfile.getDocReward(),reviewedfile.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除待审论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_reviewedfile where reviewedfile_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//查询每个专家的已审论文
	 @SuppressWarnings("unchecked")
	 public List<ReviewedFile> findReviewedFile(String expertno)throws SQLException, ClassNotFoundException{
		 List<ReviewedFile> list = new ArrayList<ReviewedFile>();
		 ReviewedFile reviewedfile = null;
		 String sql = "select * from tg_reviewedfile where expert_no=?";	
		 String[] params = {expertno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 reviewedfile=new ReviewedFile();
	    	 reviewedfile.setReviewedFileId(map.get("reviewedfile_id").toString());
	    	 reviewedfile.setExpertNo(map.get("expert_no").toString());
	    	 reviewedfile.setDocName(map.get("doc_name").toString());
	    	 reviewedfile.setDocType(map.get("doc_type").toString());
	    	 reviewedfile.setAllotTime(map.get("allot_time").toString());
	    	 reviewedfile.setReviewTime(map.get("review_time").toString());
	    	 reviewedfile.setDocReward(map.get("doc_reward").toString());
	    	 reviewedfile.setIsAgreed(map.get("if_agreed").toString());
	    	 
	    	 list.add(reviewedfile);
	     }
	     return list;
	 }

}

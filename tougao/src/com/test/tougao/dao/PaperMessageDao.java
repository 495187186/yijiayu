package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.PaperMessage;

public class PaperMessageDao {
	//添加
	public int add(PaperMessage papermessage) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_papermessage(expert_no,expert_name,student_no,paper_name,paper_writer,message_name,paper_time,message_date,if_read) values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {papermessage.getExpertNo(),papermessage.getExpertName(),papermessage.getStudentNo(),papermessage.getPaperName(),papermessage.getPaperWriter(),papermessage.getMessageName(),papermessage.getUploadTime(),papermessage.getMessageDate(),papermessage.getIsRead()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(PaperMessage papermessage)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_papermessage set expert_no=?,expert_name=?,student_no=?,paper_name=?,paper_writer=?,message_name=?,paper_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),message_date=to_date(?,'yyyy-mm-dd HH24:MI:SS'),if_read=? where papermessage_id = ?";
		//参数对应JSP页面获取值
		String[] params = {papermessage.getExpertNo(),papermessage.getExpertName(),papermessage.getStudentNo(),papermessage.getPaperName(),papermessage.getPaperWriter(),papermessage.getMessageName(),papermessage.getUploadTime(),papermessage.getMessageDate(),papermessage.getIsRead(),papermessage.getPaperMessageId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询
	 @SuppressWarnings("unchecked")
	 public PaperMessage findById(String id)throws SQLException, ClassNotFoundException{
		 PaperMessage papermessage = null;
		 String sql = "select * from tg_papermessage where papermessage_id=?";	
		 String[] params = {id};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
		     map = r.getRows()[0];//将查询结果赋值给map对象
	    	 papermessage=new PaperMessage();
	    	 papermessage.setPaperMessageId(map.get("papermessage_id").toString());
	    	 papermessage.setExpertNo(map.get("expert_no").toString());
	    	 papermessage.setExpertName(map.get("expert_name").toString());
	    	 papermessage.setStudentNo(map.get("student_no").toString());
	    	 papermessage.setPaperName(map.get("paper_name").toString());
	    	 papermessage.setPaperWriter(map.get("paper_writer").toString());
	    	 papermessage.setMessageName(map.get("message_name").toString());
	    	 papermessage.setUploadTime(map.get("paper_time").toString());
	    	 papermessage.setMessageDate(map.get("message_date").toString());
	    	 papermessage.setIsRead(map.get("if_read").toString());
			
	     }
	     return papermessage;
	 }
	
	//查询
	 @SuppressWarnings("unchecked")
	 public List<PaperMessage> findReadMessage(String no,String read)throws SQLException, ClassNotFoundException{
		 List<PaperMessage> list = new ArrayList<PaperMessage>();
		 PaperMessage papermessage = null;
		 String sql = "select * from tg_papermessage where student_no=? and if_read=?";	
		 String[] params = {no,read};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papermessage=new PaperMessage();
	    	 papermessage.setPaperMessageId(map.get("papermessage_id").toString());
	    	 papermessage.setExpertNo(map.get("expert_no").toString());
	    	 papermessage.setExpertName(map.get("expert_name").toString());
	    	 papermessage.setStudentNo(map.get("student_no").toString());
	    	 papermessage.setPaperName(map.get("paper_name").toString());
	    	 papermessage.setPaperWriter(map.get("paper_writer").toString());
	    	 papermessage.setMessageName(map.get("message_name").toString());
	    	 papermessage.setUploadTime(map.get("paper_time").toString());
	    	 papermessage.setMessageDate(map.get("message_date").toString());
	    	 papermessage.setIsRead(map.get("if_read").toString());
			 
	    	 list.add(papermessage);
	     }
	     return list;
	 }

}

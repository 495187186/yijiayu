package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.FileMessage;

public class FileMessageDao {
	//添加
	public int add(FileMessage filemessage) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_filemessage(expert_no,expert_name,admin_no,file_name,message_name,file_time,message_date,if_read) values(?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {filemessage.getExpertNo(),filemessage.getExpertName(),filemessage.getAdminNo(),filemessage.getFileName(),filemessage.getMessageName(),filemessage.getUploadTime(),filemessage.getMessageDate(),filemessage.getIsRead()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(FileMessage filemessage)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_filemessage set expert_no=?,expert_name=?,admin_no=?,file_name=?,message_name=?,file_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),message_date=to_date(?,'yyyy-mm-dd HH24:MI:SS'),if_read=? where filemessage_id = ?";
		//参数对应JSP页面获取值
		String[] params = {filemessage.getExpertNo(),filemessage.getExpertName(),filemessage.getAdminNo(),filemessage.getFileName(),filemessage.getMessageName(),filemessage.getUploadTime(),filemessage.getMessageDate(),filemessage.getIsRead(),filemessage.getFileMessageId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询
	 @SuppressWarnings("unchecked")
	 public FileMessage findById(String id)throws SQLException, ClassNotFoundException{
		 FileMessage filemessage = null;
		 String sql = "select * from tg_filemessage where filemessage_id=?";	
		 String[] params = {id};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
		     map = r.getRows()[0];//将查询结果赋值给map对象
	    	 filemessage=new FileMessage();
	    	 filemessage.setFileMessageId(map.get("filemessage_id").toString());
	    	 filemessage.setExpertNo(map.get("expert_no").toString());
	    	 filemessage.setExpertName(map.get("expert_name").toString());
	    	 filemessage.setAdminNo(map.get("admin_no").toString());
	    	 filemessage.setFileName(map.get("file_name").toString());
	    	 filemessage.setMessageName(map.get("message_name").toString());
	    	 filemessage.setUploadTime(map.get("file_time").toString());
	    	 filemessage.setMessageDate(map.get("message_date").toString());
	    	 filemessage.setIsRead(map.get("if_read").toString());
			
	     }
	     return filemessage;
	 }
	
	//查询
	 @SuppressWarnings("unchecked")
	 public List<FileMessage> findReadMessage(String no,String read)throws SQLException, ClassNotFoundException{
		 List<FileMessage> list = new ArrayList<FileMessage>();
		 FileMessage filemessage = null;
		 String sql = "select * from tg_filemessage where admin_no=? and if_read=?";	
		 String[] params = {no,read};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filemessage=new FileMessage();
	    	 filemessage.setFileMessageId(map.get("filemessage_id").toString());
	    	 filemessage.setExpertNo(map.get("expert_no").toString());
	    	 filemessage.setExpertName(map.get("expert_name").toString());
	    	 filemessage.setAdminNo(map.get("admin_no").toString());
	    	 filemessage.setFileName(map.get("file_name").toString());
	    	 filemessage.setMessageName(map.get("message_name").toString());
	    	 filemessage.setUploadTime(map.get("file_time").toString());
	    	 filemessage.setMessageDate(map.get("message_date").toString());
	    	 filemessage.setIsRead(map.get("if_read").toString());
			 
	    	 list.add(filemessage);
	     }
	     return list;
	 }

}

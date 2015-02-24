package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.StaFile;

public class StaFileDao {
	//添加
	public int add(StaFile stafile) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_stafile(file_name,file_type,file_uploader,upload_time,allot_counter,file_reward,file_status) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {stafile.getFileName(),stafile.getFileType(),stafile.getFileUploader(),stafile.getUploadTime(),stafile.getAllotCounter(),stafile.getFileReward(),stafile.getFileStatus()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(StaFile stafile)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_stafile set file_name=?,file_type=?,file_uploader=?,upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),allot_counter=?,file_reward=?,file_status=? where stafile_id = ?";
		//参数对应JSP页面获取值
		String[] params = {stafile.getFileName(),stafile.getFileType(),stafile.getFileUploader(),stafile.getUploadTime(),stafile.getAllotCounter(),stafile.getFileReward(),stafile.getFileStatus(),stafile.getStaFileId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//查询
	 @SuppressWarnings("unchecked")
	 public List<StaFile> findStaFile()throws SQLException, ClassNotFoundException{
		 List<StaFile> list = new ArrayList<StaFile>();
		 StaFile stafile = null;
		 String sql = "select * from tg_stafile";	
		 String[] params = {null};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stafile=new StaFile();
	    	 stafile.setStaFileId(map.get("stafile_id").toString());
	    	 stafile.setFileName(map.get("file_name").toString());
	    	 stafile.setFileType(map.get("file_type").toString());
	    	 stafile.setFileUploader(map.get("file_uploader").toString());
	    	 stafile.setUploadTime(map.get("upload_time").toString());
	    	 stafile.setAllotCounter(map.get("allot_counter").toString());
	    	 stafile.setFileReward(map.get("file_reward").toString());
	    	 stafile.setFileStatus(map.get("file_status").toString());
			 
	    	 list.add(stafile);
	     }
	     return list;
	 }
	//按材料名称查询
	 @SuppressWarnings("unchecked")
	 public StaFile findByFileName(String filename)throws SQLException, ClassNotFoundException{
		 StaFile stafile = null;
		 String sql = "select * from tg_stafile where file_name=?";	
		 String[] params = {filename};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象
	    	 stafile=new StaFile();
	    	 stafile.setStaFileId(map.get("stafile_id").toString());
	    	 stafile.setFileName(map.get("file_name").toString());
	    	 stafile.setFileType(map.get("file_type").toString());
	    	 stafile.setFileUploader(map.get("file_uploader").toString());
	    	 stafile.setUploadTime(map.get("upload_time").toString());
	    	 stafile.setAllotCounter(map.get("allot_counter").toString());
	    	 stafile.setFileReward(map.get("file_reward").toString());
	    	 stafile.setFileStatus(map.get("file_status").toString());
			 
	     }
	     return stafile;
	 }
	//按上传作者查询
	 @SuppressWarnings("unchecked")
	 public List<StaFile> findByFileUploader(String uploader)throws SQLException, ClassNotFoundException{
		 List<StaFile> list = new ArrayList<StaFile>();
		 StaFile stafile = null;
		 String sql = "select * from tg_stafile where file_uploader=?";	
		 String[] params = {uploader};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stafile=new StaFile();
	    	 stafile.setStaFileId(map.get("stafile_id").toString());
	    	 stafile.setFileName(map.get("file_name").toString());
	    	 stafile.setFileType(map.get("file_type").toString());
	    	 stafile.setFileUploader(map.get("file_uploader").toString());
	    	 stafile.setUploadTime(map.get("upload_time").toString());
	    	 stafile.setAllotCounter(map.get("allot_counter").toString());
	    	 stafile.setFileReward(map.get("file_reward").toString());
	    	 stafile.setFileStatus(map.get("file_status").toString());
			 
	    	 list.add(stafile);
	     }
	     return list;
	 }
	//按审批状态查询
	 @SuppressWarnings("unchecked")
	 public List<StaFile> findByFileStatus(String status)throws SQLException, ClassNotFoundException{
		 List<StaFile> list = new ArrayList<StaFile>();
		 StaFile stafile = null;
		 String sql = "select * from tg_stafile where file_status=?";	
		 String[] params = {status};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stafile=new StaFile();
	    	 stafile.setStaFileId(map.get("stafile_id").toString());
	    	 stafile.setFileName(map.get("file_name").toString());
	    	 stafile.setFileType(map.get("file_type").toString());
	    	 stafile.setFileUploader(map.get("file_uploader").toString());
	    	 stafile.setUploadTime(map.get("upload_time").toString());
	    	 stafile.setAllotCounter(map.get("allot_counter").toString());
	    	 stafile.setFileReward(map.get("file_reward").toString());
	    	 stafile.setFileStatus(map.get("file_status").toString());
			 
	    	 list.add(stafile);
	     }
	     return list;
	 }
	 
	 //按上传时间查询
	 @SuppressWarnings("unchecked")
	 public List<StaFile> findByUploadTime(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<StaFile> list = new ArrayList<StaFile>();
		 StaFile stafile = null;
		 String sql = "select * from tg_stafile where upload_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and upload_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stafile=new StaFile();
	    	 stafile.setStaFileId(map.get("stafile_id").toString());
	    	 stafile.setFileName(map.get("file_name").toString());
	    	 stafile.setFileType(map.get("file_type").toString());
	    	 stafile.setFileUploader(map.get("file_uploader").toString());
	    	 stafile.setUploadTime(map.get("upload_time").toString());
	    	 stafile.setAllotCounter(map.get("allot_counter").toString());
	    	 stafile.setFileReward(map.get("file_reward").toString());
	    	 stafile.setFileStatus(map.get("file_status").toString());
			 
	    	 list.add(stafile);
	     }
	     return list;
	 }
	//按材料类型查询
	 @SuppressWarnings("unchecked")
	 public List<StaFile> findByFileType(String filetype)throws SQLException, ClassNotFoundException{
		 List<StaFile> list = new ArrayList<StaFile>();
		 StaFile stafile = null;
		 String sql = "select * from tg_stafile where file_type=?";	
		 String[] params = {filetype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stafile=new StaFile();
	    	 stafile.setStaFileId(map.get("stafile_id").toString());
	    	 stafile.setFileName(map.get("file_name").toString());
	    	 stafile.setFileType(map.get("file_type").toString());
	    	 stafile.setFileUploader(map.get("file_uploader").toString());
	    	 stafile.setUploadTime(map.get("upload_time").toString());
	    	 stafile.setAllotCounter(map.get("allot_counter").toString());
	    	 stafile.setFileReward(map.get("file_reward").toString());
	    	 stafile.setFileStatus(map.get("file_status").toString());
			 
	    	 list.add(stafile);
	     }
	     return list;
	 }

}

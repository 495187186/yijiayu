package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.AllotFile;

public class AllotFileDao {
	//添加
	public int add(AllotFile allotfile) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_allotfile(file_name,file_uploader,expert_name,upload_time,allot_time,file_reward,file_flag,if_reviewed,if_agreed) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {allotfile.getFileName(),allotfile.getFileUploader(),allotfile.getExpertName(),allotfile.getUploadTime(),allotfile.getAllotTime(),allotfile.getFileReward(),allotfile.getFileFlag(),allotfile.getIsReviewed(),allotfile.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(AllotFile allotfile)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_allotfile set file_name=?,file_uploader=?,expert_name=?,upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),allot_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),file_reward=?,file_flag=?,if_reviewed=?,if_agreed=? where allotfile_id = ?";
		//参数对应JSP页面获取值
		String[] params = {allotfile.getFileName(),allotfile.getFileUploader(),allotfile.getExpertName(),allotfile.getUploadTime(),allotfile.getAllotTime(),allotfile.getFileReward(),allotfile.getFileFlag(),allotfile.getIsReviewed(),allotfile.getIsAgreed(),allotfile.getAllotFileId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询
	 @SuppressWarnings("unchecked")
	 public AllotFile findExpFile(String filename,String expertname,String uploadtime)throws SQLException, ClassNotFoundException{
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where file_name=? and expert_name=? and upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {filename,expertname,uploadtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
		     map = r.getRows()[0];//将查询结果赋值给map对象
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			
	     }
	     return allotfile;
	 }
	//查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findAllotFile()throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile";	
		 String[] params = {null};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	//按论文题目查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByFileName(String filename)throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where file_name=?";	
		 String[] params = {filename};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	//按上传作者查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByFileUploader(String uploader)throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where file_uploader=?";	
		 String[] params = {uploader};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	//按状态查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByFileStatus(String status)throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where if_reviewed=?";	
		 String[] params = {status};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	 
	//按专家姓名查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByExpertName(String expertname)throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where expert_name=?";	
		 String[] params = {expertname};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	//按专家姓名和论文名查询专家选指派的同一篇论文情况
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByExpertFileName(String expertname,String filename)throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where expert_name=? and file_name=?";	
		 String[] params = {expertname,filename};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	 //按上传时间查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByUploadTime(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where upload_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and upload_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	 //按分配时间查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findByAllotTime(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where allot_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and allot_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }
	//按材料名称和上传时间查询
	 @SuppressWarnings("unchecked")
	 public List<AllotFile> findFiles(String filename,String uploadtime)throws SQLException, ClassNotFoundException{
		 List<AllotFile> list = new ArrayList<AllotFile>();
		 AllotFile allotfile = null;
		 String sql = "select * from tg_allotfile where file_name=? and upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {filename,uploadtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotfile=new AllotFile();
	    	 allotfile.setAllotFileId(map.get("allotfile_id").toString());
	    	 allotfile.setFileName(map.get("file_name").toString());
	    	 allotfile.setFileUploader(map.get("file_uploader").toString());
	    	 allotfile.setExpertName(map.get("expert_name").toString());
	    	 allotfile.setUploadTime(map.get("upload_time").toString());
	    	 allotfile.setAllotTime(map.get("allot_time").toString());
	    	 allotfile.setFileReward(map.get("file_reward").toString());
	    	 allotfile.setFileFlag(map.get("file_flag").toString());
	    	 allotfile.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotfile.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotfile);
	     }
	     return list;
	 }

}

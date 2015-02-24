package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.AdminFile;

public class AdminFileDao {
	//添加论文
	public int add(AdminFile file) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_file(file_name,file_type,file_uploader,uploader,file_time,file_status,file_counter,file_reward,person_num,if_reward,if_allot,if_choose) values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {file.getFileName(),file.getFileType(),file.getFileUploader(),file.getUploader(),file.getFileTime(),file.getFileStatus(),file.getFileCounter(),file.getFileReward(),file.getPersonNum(),file.getIsReward(),file.getIsAllot(),file.getIsChoose()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改论文
	public int update(AdminFile file)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_file set file_name=?,file_type=?,file_uploader=?,uploader=?,file_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),file_status=?,file_counter=?,file_reward=?,person_num=?,if_reward=?,if_allot=?,if_choose=? where file_id = ?";
		//参数对应JSP页面获取值
		String[] params = {file.getFileName(),file.getFileType(),file.getFileUploader(),file.getUploader(),file.getFileTime(),file.getFileStatus(),file.getFileCounter(),file.getFileReward(),file.getPersonNum(),file.getIsReward(),file.getIsAllot(),file.getIsChoose(),file.getFileId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_file where file_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据ID查询每个论文
	@SuppressWarnings("unchecked")
	public AdminFile findById(String id)throws SQLException, ClassNotFoundException{
		AdminFile file=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_file where file_id = ?";
		String[] params = {id};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			file=new AdminFile();
			file.setFileId(map.get("file_id").toString());
			file.setFileName(map.get("file_name").toString());
			file.setFileType(map.get("file_type").toString());
			file.setFileUploader(map.get("file_uploader").toString());
			file.setUploader(map.get("uploader").toString());
			file.setFileTime(map.get("file_time").toString());
			file.setFileStatus(map.get("file_status").toString());
			file.setFileCounter(map.get("file_counter").toString());
			file.setFileReward(map.get("file_reward").toString());
			file.setPersonNum(map.get("person_num").toString());
			file.setIsReward(map.get("if_reward").toString());
			file.setIsAllot(map.get("if_allot").toString());
			file.setIsChoose(map.get("if_choose").toString());
			
		}
		   return file;
	}
	//根据论文名查询每个论文
	@SuppressWarnings("unchecked")
	public AdminFile findByName(String filename)throws SQLException, ClassNotFoundException{
		AdminFile file=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_file where file_name = ?";
		String[] params = {filename};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			file=new AdminFile();
			file.setFileId(map.get("file_id").toString());
			file.setFileName(map.get("file_name").toString());
			file.setFileType(map.get("file_type").toString());
			file.setFileUploader(map.get("file_uploader").toString());
			file.setUploader(map.get("uploader").toString());
			file.setFileTime(map.get("file_time").toString());
			file.setFileStatus(map.get("file_status").toString());
			file.setFileCounter(map.get("file_counter").toString());
			file.setFileReward(map.get("file_reward").toString());
			file.setPersonNum(map.get("person_num").toString());
			file.setIsReward(map.get("if_reward").toString());
			file.setIsAllot(map.get("if_allot").toString());
			file.setIsChoose(map.get("if_choose").toString());
			
		}
		   return file;
	}
	//根据论文名和上传时间查询每个论文
	@SuppressWarnings("unchecked")
	public AdminFile findFile(String filename,String uploadtime)throws SQLException, ClassNotFoundException{
		AdminFile file=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_file where file_name = ? and file_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";
		String[] params = {filename,uploadtime};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			file=new AdminFile();
			file.setFileId(map.get("file_id").toString());
			file.setFileName(map.get("file_name").toString());
			file.setFileType(map.get("file_type").toString());
			file.setFileUploader(map.get("file_uploader").toString());
			file.setUploader(map.get("uploader").toString());
			file.setFileTime(map.get("file_time").toString());
			file.setFileStatus(map.get("file_status").toString());
			file.setFileCounter(map.get("file_counter").toString());
			file.setFileReward(map.get("file_reward").toString());
			file.setPersonNum(map.get("person_num").toString());
			file.setIsReward(map.get("if_reward").toString());
			file.setIsAllot(map.get("if_allot").toString());
			file.setIsChoose(map.get("if_choose").toString());
			
		}
		   return file;
	}
	//查询可指派的材料：已定稿费且开放
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findSelectFile(String reward,String choose)throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where if_reward=? and if_choose=?";	
		 String[] params = {reward,choose};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//查询所有材料
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findAll()throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file";	
		 String[] params = {null};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//查询每个上传作者的论文
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findAdminFile(String adminno)throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where file_uploader=?";	
		 String[] params = {adminno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//查询每个上传作者的论文
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findByUploader(String uploader)throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where uploader=?";	
		 String[] params = {uploader};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	 //根据论文类别查论文
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findTypeFile(String adminno,String filetype)throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where file_uploader=? and file_type=?";	
		 String[] params = {adminno,filetype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//根据每个上传者时间区间查论文
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findDateFile(String adminno,String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where file_uploader=? and file_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and file_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {adminno,begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//查询已定审稿费材料
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findByReward(String isreward)throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where if_reward=?";	
		 String[] params = {isreward};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//查询已分配材料
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findByAllot(String isallot)throws SQLException, ClassNotFoundException{
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where if_allot=?";	
		 String[] params = {isallot};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }
	//根据时间区间查论文
	 @SuppressWarnings("unchecked")
	 public List<AdminFile> findByDate(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<AdminFile> list = new ArrayList<AdminFile>();
		 AdminFile file = null;
		 String sql = "select * from tg_file where and file_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and file_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 file=new AdminFile();
	    	 file.setFileId(map.get("file_id").toString());
			 file.setFileName(map.get("file_name").toString());
			 file.setFileType(map.get("file_type").toString());
			 file.setFileUploader(map.get("file_uploader").toString());
			 file.setUploader(map.get("uploader").toString());
			 file.setFileTime(map.get("file_time").toString());
			 file.setFileStatus(map.get("file_status").toString());
			 file.setFileCounter(map.get("file_counter").toString());
			 file.setFileReward(map.get("file_reward").toString());
			 file.setPersonNum(map.get("person_num").toString());
			 file.setIsReward(map.get("if_reward").toString());
			 file.setIsAllot(map.get("if_allot").toString());
			 file.setIsChoose(map.get("if_choose").toString());
	    	 list.add(file);
	     }
	     return list;
	 }

}

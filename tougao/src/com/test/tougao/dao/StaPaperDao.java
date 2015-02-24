package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.StaPaper;

public class StaPaperDao {
	//添加
	public int add(StaPaper stapaper) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_stapaper(paper_name,paper_type,paper_uploader,paper_writer,upload_time,allot_counter,paper_reward,paper_status) values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {stapaper.getPaperName(),stapaper.getPaperType(),stapaper.getPaperUploader(),stapaper.getPaperWriter(),stapaper.getUploadTime(),stapaper.getAllotCounter(),stapaper.getPaperReward(),stapaper.getPaperStatus()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(StaPaper stapaper)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_stapaper set paper_name=?,paper_type=?,paper_uploader=?,paper_writer=?,upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),allot_counter=?,paper_reward=?,paper_status=? where stapaper_id = ?";
		//参数对应JSP页面获取值
		String[] params = {stapaper.getPaperName(),stapaper.getPaperType(),stapaper.getPaperUploader(),stapaper.getPaperWriter(),stapaper.getUploadTime(),stapaper.getAllotCounter(),stapaper.getPaperReward(),stapaper.getPaperStatus(),stapaper.getStaPaperId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//查询
	 @SuppressWarnings("unchecked")
	 public List<StaPaper> findStaPaper()throws SQLException, ClassNotFoundException{
		 List<StaPaper> list = new ArrayList<StaPaper>();
		 StaPaper stapaper = null;
		 String sql = "select * from tg_stapaper";	
		 String[] params = {null};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stapaper=new StaPaper();
	    	 stapaper.setStaPaperId(map.get("stapaper_id").toString());
	    	 stapaper.setPaperName(map.get("paper_name").toString());
	    	 stapaper.setPaperType(map.get("paper_type").toString());
	    	 stapaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 stapaper.setPaperWriter(map.get("paper_writer").toString());
	    	 stapaper.setUploadTime(map.get("upload_time").toString());
	    	 stapaper.setAllotCounter(map.get("allot_counter").toString());
	    	 stapaper.setPaperReward(map.get("paper_reward").toString());
	    	 stapaper.setPaperStatus(map.get("paper_status").toString());
			 
	    	 list.add(stapaper);
	     }
	     return list;
	 }
	//按论文题目查询
	 @SuppressWarnings("unchecked")
	 public StaPaper findByPaperName(String papername)throws SQLException, ClassNotFoundException{
		 StaPaper stapaper = null;
		 String sql = "select * from tg_stapaper where paper_name=?";	
		 String[] params = {papername};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//将查询结果赋值给map对象
	    	 stapaper=new StaPaper();
	    	 stapaper.setStaPaperId(map.get("stapaper_id").toString());
	    	 stapaper.setPaperName(map.get("paper_name").toString());
	    	 stapaper.setPaperType(map.get("paper_type").toString());
	    	 stapaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 stapaper.setPaperWriter(map.get("paper_writer").toString());
	    	 stapaper.setUploadTime(map.get("upload_time").toString());
	    	 stapaper.setAllotCounter(map.get("allot_counter").toString());
	    	 stapaper.setPaperReward(map.get("paper_reward").toString());
	    	 stapaper.setPaperStatus(map.get("paper_status").toString());
			 
	     }
	     return stapaper;
	 }
	//按上传作者查询
	 @SuppressWarnings("unchecked")
	 public List<StaPaper> findByPaperUploader(String uploader)throws SQLException, ClassNotFoundException{
		 List<StaPaper> list = new ArrayList<StaPaper>();
		 StaPaper stapaper = null;
		 String sql = "select * from tg_stapaper where paper_uploader=?";	
		 String[] params = {uploader};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stapaper=new StaPaper();
	    	 stapaper.setStaPaperId(map.get("stapaper_id").toString());
	    	 stapaper.setPaperName(map.get("paper_name").toString());
	    	 stapaper.setPaperType(map.get("paper_type").toString());
	    	 stapaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 stapaper.setPaperWriter(map.get("paper_writer").toString());
	    	 stapaper.setUploadTime(map.get("upload_time").toString());
	    	 stapaper.setAllotCounter(map.get("allot_counter").toString());
	    	 stapaper.setPaperReward(map.get("paper_reward").toString());
	    	 stapaper.setPaperStatus(map.get("paper_status").toString());
			 
	    	 list.add(stapaper);
	     }
	     return list;
	 }
	 //按通讯作者查询
	 @SuppressWarnings("unchecked")
	 public List<StaPaper> findByPaperWriter(String paperwriter)throws SQLException, ClassNotFoundException{
		 List<StaPaper> list = new ArrayList<StaPaper>();
		 StaPaper stapaper = null;
		 String sql = "select * from tg_stapaper where paper_writer=?";	
		 String[] params = {paperwriter};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stapaper=new StaPaper();
	    	 stapaper.setStaPaperId(map.get("stapaper_id").toString());
	    	 stapaper.setPaperName(map.get("paper_name").toString());
	    	 stapaper.setPaperType(map.get("paper_type").toString());
	    	 stapaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 stapaper.setPaperWriter(map.get("paper_writer").toString());
	    	 stapaper.setUploadTime(map.get("upload_time").toString());
	    	 stapaper.setAllotCounter(map.get("allot_counter").toString());
	    	 stapaper.setPaperReward(map.get("paper_reward").toString());
	    	 stapaper.setPaperStatus(map.get("paper_status").toString());
			 
	    	 list.add(stapaper);
	     }
	     return list;
	 }
	 //按上传时间查询
	 @SuppressWarnings("unchecked")
	 public List<StaPaper> findByUploadTime(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<StaPaper> list = new ArrayList<StaPaper>();
		 StaPaper stapaper = null;
		 String sql = "select * from tg_stapaper where upload_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and upload_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stapaper=new StaPaper();
	    	 stapaper.setStaPaperId(map.get("stapaper_id").toString());
	    	 stapaper.setPaperName(map.get("paper_name").toString());
	    	 stapaper.setPaperType(map.get("paper_type").toString());
	    	 stapaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 stapaper.setPaperWriter(map.get("paper_writer").toString());
	    	 stapaper.setUploadTime(map.get("upload_time").toString());
	    	 stapaper.setAllotCounter(map.get("allot_counter").toString());
	    	 stapaper.setPaperReward(map.get("paper_reward").toString());
	    	 stapaper.setPaperStatus(map.get("paper_status").toString());
			 
	    	 list.add(stapaper);
	     }
	     return list;
	 }
	//按论文类型查询
	 @SuppressWarnings("unchecked")
	 public List<StaPaper> findByPaperType(String papertype)throws SQLException, ClassNotFoundException{
		 List<StaPaper> list = new ArrayList<StaPaper>();
		 StaPaper stapaper = null;
		 String sql = "select * from tg_stapaper where paper_type=?";	
		 String[] params = {papertype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 stapaper=new StaPaper();
	    	 stapaper.setStaPaperId(map.get("stapaper_id").toString());
	    	 stapaper.setPaperName(map.get("paper_name").toString());
	    	 stapaper.setPaperType(map.get("paper_type").toString());
	    	 stapaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 stapaper.setPaperWriter(map.get("paper_writer").toString());
	    	 stapaper.setUploadTime(map.get("upload_time").toString());
	    	 stapaper.setAllotCounter(map.get("allot_counter").toString());
	    	 stapaper.setPaperReward(map.get("paper_reward").toString());
	    	 stapaper.setPaperStatus(map.get("paper_status").toString());
			 
	    	 list.add(stapaper);
	     }
	     return list;
	 }
	
}

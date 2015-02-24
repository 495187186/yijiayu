package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.AllotPaper;

public class AllotPaperDao {
	//添加
	public int add(AllotPaper allotpaper) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_allotpaper(paper_name,paper_uploader,paper_writer,expert_name,upload_time,allot_time,paper_reward,paper_flag,if_reviewed,if_agreed) values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {allotpaper.getPaperName(),allotpaper.getPaperUploader(),allotpaper.getPaperWriter(),allotpaper.getExpertName(),allotpaper.getUploadTime(),allotpaper.getAllotTime(),allotpaper.getPaperReward(),allotpaper.getPaperFlag(),allotpaper.getIsReviewed(),allotpaper.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(AllotPaper allotpaper)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_allotpaper set paper_name=?,paper_uploader=?,paper_writer=?,expert_name=?,upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),allot_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),paper_reward=?,paper_flag=?,if_reviewed=?,if_agreed=? where allotpaper_id = ?";
		//参数对应JSP页面获取值
		String[] params = {allotpaper.getPaperName(),allotpaper.getPaperUploader(),allotpaper.getPaperWriter(),allotpaper.getExpertName(),allotpaper.getUploadTime(),allotpaper.getAllotTime(),allotpaper.getPaperReward(),allotpaper.getPaperFlag(),allotpaper.getIsReviewed(),allotpaper.getIsAgreed(),allotpaper.getAllotPaperId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询
	 @SuppressWarnings("unchecked")
	 public AllotPaper findExpPaper(String papername,String expertname,String uploadtime)throws SQLException, ClassNotFoundException{
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where paper_name=? and expert_name=? and upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {papername,expertname,uploadtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
		     map = r.getRows()[0];//将查询结果赋值给map对象
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			
	     }
	     return allotpaper;
	 }
	//查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findAllotPaper()throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper";	
		 String[] params = {null};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	//按论文题目查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByPaperName(String papername)throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where paper_name=?";	
		 String[] params = {papername};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	//按上传作者查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByPaperUploader(String uploader)throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where paper_uploader=?";	
		 String[] params = {uploader};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	 //按通讯作者查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByPaperWriter(String paperwriter)throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where paper_writer=?";	
		 String[] params = {paperwriter};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	//按专家姓名查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByExpertName(String expertname)throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where expert_name=?";	
		 String[] params = {expertname};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	//按专家姓名和论文名查询专家选指派的同一篇论文情况
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByExpertPaperName(String expertname,String papername)throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where expert_name=? and paper_name=?";	
		 String[] params = {expertname,papername};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	 //按上传时间查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByUploadTime(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where upload_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and upload_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	 //按分配时间查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findByAllotTime(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where allot_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and allot_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }
	//按论文题目和上传时间查询
	 @SuppressWarnings("unchecked")
	 public List<AllotPaper> findPapers(String papername,String uploadtime)throws SQLException, ClassNotFoundException{
		 List<AllotPaper> list = new ArrayList<AllotPaper>();
		 AllotPaper allotpaper = null;
		 String sql = "select * from tg_allotpaper where paper_name=? and upload_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {papername,uploadtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 allotpaper=new AllotPaper();
	    	 allotpaper.setAllotPaperId(map.get("allotpaper_id").toString());
	    	 allotpaper.setPaperName(map.get("paper_name").toString());
	    	 allotpaper.setPaperUploader(map.get("paper_uploader").toString());
	    	 allotpaper.setPaperWriter(map.get("paper_writer").toString());
	    	 allotpaper.setExpertName(map.get("expert_name").toString());
	    	 allotpaper.setUploadTime(map.get("upload_time").toString());
	    	 allotpaper.setAllotTime(map.get("allot_time").toString());
	    	 allotpaper.setPaperReward(map.get("paper_reward").toString());
	    	 allotpaper.setPaperFlag(map.get("paper_flag").toString());
	    	 allotpaper.setIsReviewed(map.get("if_reviewed").toString());
	    	 allotpaper.setIsAgreed(map.get("if_agreed").toString());
			 
	    	 list.add(allotpaper);
	     }
	     return list;
	 }

}

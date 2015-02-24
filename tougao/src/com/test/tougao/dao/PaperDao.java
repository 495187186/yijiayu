package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Paper;

public class PaperDao {
	//添加论文
	public int add(Paper paper) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_paper(college_name,paper_name,paper_type,paper_uploader,uploader,paper_writer,paper_time,paper_status,paper_counter,paper_reward,person_num,if_reward,if_allot,if_choose) values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {paper.getCollegeName(),paper.getPaperName(),paper.getPaperType(),paper.getPaperUploader(),paper.getUploader(),paper.getPaperWriter(),paper.getPaperTime(),paper.getPaperStatus(),paper.getPaperCounter(),paper.getPaperReward(),paper.getPersonNum(),paper.getIsReward(),paper.getIsAllot(),paper.getIsChoose()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改论文
	public int update(Paper paper)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_paper set college_name=?,paper_name=?,paper_type=?,paper_uploader=?,uploader=?,paper_writer=?,paper_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),paper_status=?,paper_counter=?,paper_reward=?,person_num=?,if_reward=?,if_allot=?,if_choose=? where paper_id = ?";
		//参数对应JSP页面获取值
		String[] params = {paper.getCollegeName(),paper.getPaperName(),paper.getPaperType(),paper.getPaperUploader(),paper.getUploader(),paper.getPaperWriter(),paper.getPaperTime(),paper.getPaperStatus(),paper.getPaperCounter(),paper.getPaperReward(),paper.getPersonNum(),paper.getIsReward(),paper.getIsAllot(),paper.getIsChoose(),paper.getPaperId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_paper where paper_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据ID查询每个论文
	@SuppressWarnings("unchecked")
	public Paper findById(String id)throws SQLException, ClassNotFoundException{
		Paper paper=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_paper where paper_id = ?";
		String[] params = {id};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			paper=new Paper();
			paper.setPaperId(map.get("paper_id").toString());
			paper.setCollegeName(map.get("college_name").toString());
			paper.setPaperName(map.get("paper_name").toString());
			paper.setPaperType(map.get("paper_type").toString());
			paper.setPaperUploader(map.get("paper_uploader").toString());
			paper.setUploader(map.get("uploader").toString());
			paper.setPaperWriter(map.get("paper_writer").toString());
			paper.setPaperTime(map.get("paper_time").toString());
			paper.setPaperStatus(map.get("paper_status").toString());
			paper.setPaperCounter(map.get("paper_counter").toString());
			paper.setPaperReward(map.get("paper_reward").toString());
			paper.setPersonNum(map.get("person_num").toString());
			paper.setIsReward(map.get("if_reward").toString());
			paper.setIsAllot(map.get("if_allot").toString());
			paper.setIsChoose(map.get("if_choose").toString());
			
		}
		   return paper;
	}
	//根据论文名查询每个论文
	@SuppressWarnings("unchecked")
	public Paper findByName(String papername)throws SQLException, ClassNotFoundException{
		Paper paper=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_paper where paper_name = ?";
		String[] params = {papername};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			paper=new Paper();
			paper.setPaperId(map.get("paper_id").toString());
			paper.setCollegeName(map.get("college_name").toString());
			paper.setPaperName(map.get("paper_name").toString());
			paper.setPaperType(map.get("paper_type").toString());
			paper.setPaperUploader(map.get("paper_uploader").toString());
			paper.setUploader(map.get("uploader").toString());
			paper.setPaperWriter(map.get("paper_writer").toString());
			paper.setPaperTime(map.get("paper_time").toString());
			paper.setPaperStatus(map.get("paper_status").toString());
			paper.setPaperCounter(map.get("paper_counter").toString());
			paper.setPaperReward(map.get("paper_reward").toString());
			paper.setPersonNum(map.get("person_num").toString());
			paper.setIsReward(map.get("if_reward").toString());
			paper.setIsAllot(map.get("if_allot").toString());
			paper.setIsChoose(map.get("if_choose").toString());
			
		}
		   return paper;
	}
	//根据论文名和上传时间查询每个论文
	@SuppressWarnings("unchecked")
	public Paper findPaper(String papername,String uploadtime)throws SQLException, ClassNotFoundException{
		Paper paper=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_paper where paper_name = ? and paper_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";
		String[] params = {papername,uploadtime};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			paper=new Paper();
			paper.setPaperId(map.get("paper_id").toString());
			paper.setCollegeName(map.get("college_name").toString());
			paper.setPaperName(map.get("paper_name").toString());
			paper.setPaperType(map.get("paper_type").toString());
			paper.setPaperUploader(map.get("paper_uploader").toString());
			paper.setUploader(map.get("uploader").toString());
			paper.setPaperWriter(map.get("paper_writer").toString());
			paper.setPaperTime(map.get("paper_time").toString());
			paper.setPaperStatus(map.get("paper_status").toString());
			paper.setPaperCounter(map.get("paper_counter").toString());
			paper.setPaperReward(map.get("paper_reward").toString());
			paper.setPersonNum(map.get("person_num").toString());
			paper.setIsReward(map.get("if_reward").toString());
			paper.setIsAllot(map.get("if_allot").toString());
			paper.setIsChoose(map.get("if_choose").toString());
			
		}
		   return paper;
	}
	//超管查询所有论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findAll()throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper";	
		 String[] params = {null};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
				
	    	 list.add(paper);
	     }
	     return list;
	 }
	
	//根据上传作者姓名查询论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findByUploader(String uploader)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where uploader=?";	
		 String[] params = {uploader};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
				
	    	 list.add(paper);
	     }
	     return list;
	 }
	
	//查询每个上传作者的论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findStuPaper(String studentno)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where paper_uploader=?";	
		 String[] params = {studentno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
				
	    	 list.add(paper);
	     }
	     return list;
	 }
	 //根据论文是否定价查论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findRewardPaper(String isreward)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where if_reward=?";	
		 String[] params = {isreward};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }
	 //根据指派查论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findAllotPaper(String status)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where paper_status=?";	
		 String[] params = {status};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }
	//根据学生指派查论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findStuAllotPaper(String studentno,String allot)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where paper_uploader=? and if_allot=?";	
		 String[] params = {studentno,allot};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }
	 
	 //根据论文类别查论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findTypePaper(String studentno,String papertype)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where paper_uploader=? and paper_type=?";	
		 String[] params = {studentno,papertype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }
	//根据每个上传者时间区间查论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findDatePaper(String studentno,String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where paper_uploader=? and paper_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and  paper_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {studentno,begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }
	//根据时间区间查询所有论文
	 @SuppressWarnings("unchecked")
	 public List<Paper> findAllDatePaper(String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where paper_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and  paper_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }
	 
	//查询可以指派的论文：开放且定稿费
	 @SuppressWarnings("unchecked")
	 public List<Paper> findSelectPaper(String isreward,String ischoose)throws SQLException, ClassNotFoundException{
		 List<Paper> list = new ArrayList<Paper>();
		 Paper paper = null;
		 String sql = "select * from tg_paper where if_reward=? and if_choose=?";	
		 String[] params = {isreward,ischoose};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 paper=new Paper();
			 paper.setPaperId(map.get("paper_id").toString());
			 paper.setCollegeName(map.get("college_name").toString());
			 paper.setPaperName(map.get("paper_name").toString());
			 paper.setPaperType(map.get("paper_type").toString());
			 paper.setPaperUploader(map.get("paper_uploader").toString());
			 paper.setUploader(map.get("uploader").toString());
			 paper.setPaperWriter(map.get("paper_writer").toString());
			 paper.setPaperTime(map.get("paper_time").toString());
			 paper.setPaperStatus(map.get("paper_status").toString());
			 paper.setPaperCounter(map.get("paper_counter").toString());
			 paper.setPaperReward(map.get("paper_reward").toString());
			 paper.setPersonNum(map.get("person_num").toString());
			 paper.setIsReward(map.get("if_reward").toString());
			 paper.setIsAllot(map.get("if_allot").toString());
			 paper.setIsChoose(map.get("if_choose").toString());
			 
	    	 list.add(paper);
	     }
	     return list;
	 }

}

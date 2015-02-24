package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.PaperCount;

public class PaperCountDao {
	//添加
	public int add(PaperCount papercount) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_papercount(expert_no,paper_name,paper_type,paper_writer,review_time,paper_reward,if_agreed) values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {papercount.getExpertNo(),papercount.getPaperName(),papercount.getPaperType(),papercount.getPaperWriter(),papercount.getReviewTime(),papercount.getPaperReward(),papercount.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(PaperCount papercount)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_papercount set expert_no=?,paper_name=?,paper_type=?,paper_writer=?,review_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),paper_reward=?,if_agreed=? where papercount_id = ?";
		//参数对应JSP页面获取值
		String[] params = {papercount.getExpertNo(),papercount.getPaperName(),papercount.getPaperType(),papercount.getPaperWriter(),papercount.getReviewTime(),papercount.getPaperReward(),papercount.getIsAgreed(),papercount.getPaperCountId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_papercount where papercount_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询评审的论文记录
	 @SuppressWarnings("unchecked")
	 public PaperCount findPaperCount(String expertno,String papername,String reviewtime)throws SQLException, ClassNotFoundException{
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and paper_name=? and review_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,papername,reviewtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
	    	 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return papercount;
	 }
	//查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findExpPaper(String expertno)throws SQLException, ClassNotFoundException{
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=?";	
		 String[] params = {expertno};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }
	//查询每个专家在某段时间内评审的论文
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findDatePaper(String expertno,String begintime,String endtime)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and review_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and review_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,begintime,endtime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }
	//查询根据论文名每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findByPaperName(String expertno,String papername)throws SQLException, ClassNotFoundException{
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and paper_name=?";	
		 String[] params = {expertno,papername};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }
	//查询根据论文类型查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findByPaperType(String expertno,String papertype)throws SQLException, ClassNotFoundException{
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and paper_type=?";	
		 String[] params = {expertno,papertype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }
	//查询根据通讯作者查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findByPaperWriter(String expertno,String paperwriter)throws SQLException, ClassNotFoundException{
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and paper_writer=?";	
		 String[] params = {expertno,paperwriter};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }
	//根据时间段和论文类型查询每个专家评审的论文
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findByDateType(String expertno,String begintime,String endtime,String papertype)throws SQLException, ClassNotFoundException{
		 begintime=begintime+" 00:00:00";
		 endtime=endtime+" 23:59:59";
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and review_time>=to_date(?,'yyyy-mm-dd HH24:MI:SS') and review_time<=to_date(?,'yyyy-mm-dd HH24:MI:SS') and paper_type=?";	
		 String[] params = {expertno,begintime,endtime,papertype};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }
	//根据审核状态查询每个专家评审的论文（用以计算稿费）
	 @SuppressWarnings("unchecked")
	 public List<PaperCount> findByPaperStatus(String expertno,String isagreed)throws SQLException, ClassNotFoundException{
		 List<PaperCount> list = new ArrayList<PaperCount>();
		 PaperCount papercount = null;
		 String sql = "select * from tg_papercount where expert_no=? and if_agreed=?";	
		 String[] params = {expertno,isagreed};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 papercount=new PaperCount();
			 papercount.setPaperCountId(map.get("papercount_id").toString());
			 papercount.setExpertNo(map.get("expert_no").toString());
			 papercount.setPaperName(map.get("paper_name").toString());
			 papercount.setPaperType(map.get("paper_type").toString());
			 papercount.setPaperWriter(map.get("paper_writer").toString());
			 papercount.setReviewTime(map.get("review_time").toString());
			 papercount.setPaperReward(map.get("paper_reward").toString());
			 papercount.setIsAgreed(map.get("if_agreed").toString());
			
	    	 list.add(papercount);
	     }
	     return list;
	 }

}

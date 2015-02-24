package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Expert;

public class ExpertDao {
	//添加用户
	public int add(Expert expert) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_expert(expert_no,expert_name,expert_gender,expert_title,expert_nation,expert_birthday,expert_mobile,expert_email,expert_department,paper_num) values(?,?,?,?,?,?,?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {expert.getExpertNo(),expert.getExpertName(),expert.getExpertGender(),expert.getExpertTitle(),expert.getExpertNation(),expert.getExpertBirthday(),expert.getExpertMobile(),expert.getExpertEmail(),expert.getExpertDepartment(),expert.getPaperNum()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改信息
	public int update(Expert expert)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_expert set expert_no=?,expert_name=?,expert_gender=?,expert_title=?,expert_nation=?,expert_birthday=?,expert_mobile=?,expert_email=?,expert_department=?,paper_num=? where expert_id = ?";
		//参数对应JSP页面获取值
		String[] params = {expert.getExpertNo(),expert.getExpertName(),expert.getExpertGender(),expert.getExpertTitle(),expert.getExpertNation(),expert.getExpertBirthday(),expert.getExpertMobile(),expert.getExpertEmail(),expert.getExpertDepartment(),expert.getPaperNum(),expert.getExpertId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除用户
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_expert where expert_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除用户
	public int deleteByNo(String no)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_expert where expert_no = ?";
		String[] params = {no};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据工号查询单个用户信息
	@SuppressWarnings("unchecked")
	public Expert findByNo(String no)throws SQLException, ClassNotFoundException{
		Expert expert=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_expert where expert_no = ?";
		String[] params = {no};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			expert=new Expert();
			expert.setExpertId(map.get("expert_id").toString());
			expert.setExpertNo(map.get("expert_no").toString());
			expert.setExpertName(map.get("expert_name").toString());
			expert.setExpertGender(map.get("expert_gender").toString());
			expert.setExpertTitle(map.get("expert_title").toString());
			expert.setExpertNation(map.get("expert_nation").toString());
			expert.setExpertBirthday(map.get("expert_birthday").toString());
			expert.setExpertMobile(map.get("expert_mobile").toString().equals("null")?"":map.get("expert_mobile").toString());
			expert.setExpertEmail(map.get("expert_email").toString().equals("null")?"":map.get("expert_email").toString());
			expert.setExpertDepartment(map.get("expert_department").toString().equals("")?"":map.get("expert_department").toString());
			expert.setPaperNum(map.get("paper_num").toString());
			 
		}
		   return expert;
	}
	//查询所有专家
	@SuppressWarnings("unchecked")
	public List<Expert> findAllExpert()throws SQLException, ClassNotFoundException{
		List<Expert> list=new ArrayList<Expert>();
		Expert expert=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_expert";
		String[] params = {null};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		 for(int i = 0;i <r.getRowCount();i++){
	    	map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
			expert=new Expert();
			expert.setExpertId(map.get("expert_id").toString());
			expert.setExpertNo(map.get("expert_no").toString());
			expert.setExpertName(map.get("expert_name").toString());
			expert.setExpertGender(map.get("expert_gender").toString());
			expert.setExpertTitle(map.get("expert_title").toString());
			expert.setExpertNation(map.get("expert_nation").toString());
			expert.setExpertBirthday(map.get("expert_birthday").toString());
			expert.setExpertMobile(map.get("expert_mobile").toString().equals("null")?"":map.get("expert_mobile").toString());
			expert.setExpertEmail(map.get("expert_email").toString().equals("null")?"":map.get("expert_email").toString());
			expert.setExpertDepartment(map.get("expert_department").toString().equals("")?"":map.get("expert_department").toString());
			expert.setPaperNum(map.get("paper_num").toString()); 
			
			list.add(expert);
		}
		   return list;
	}

}

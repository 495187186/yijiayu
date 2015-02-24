package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Admin;

public class AdminDao {
	//添加用户
	public int add(Admin admin) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_admin(admin_no,admin_name,admin_gender,admin_mobile,admin_email) values(?,?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {admin.getAdminNo(),admin.getAdminName(),admin.getAdminGender(),admin.getAdminMobile(),admin.getAdminEmail()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改信息
	public int update(Admin admin)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_admin set admin_no=?,admin_name=?,admin_gender=?,admin_mobile=?,admin_email=? where admin_id = ?";
		//参数对应JSP页面获取值
		String[] params = {admin.getAdminNo(),admin.getAdminName(),admin.getAdminGender(),admin.getAdminMobile(),admin.getAdminEmail(),admin.getAdminId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除用户
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_admin where admin_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除用户
	public int deleteByNo(String no)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_admin where admin_no = ?";
		String[] params = {no};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//根据工号查询单个用户信息
	@SuppressWarnings("unchecked")
	public Admin findByNo(String no)throws SQLException, ClassNotFoundException{
		Admin admin=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_admin where admin_no = ?";
		String[] params = {no};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			admin=new Admin();
			admin.setAdminId(map.get("admin_id").toString());
			admin.setAdminNo(map.get("admin_no").toString());
			admin.setAdminName(map.get("admin_name").toString());
			admin.setAdminGender(map.get("admin_gender").toString());
			admin.setAdminMobile(map.get("admin_mobile").toString().equals("null")?"":map.get("admin_mobile").toString());
			admin.setAdminEmail(map.get("admin_email").toString().equals("null")?"":map.get("admin_email").toString());
			 
		}
		   return admin;
	}

}

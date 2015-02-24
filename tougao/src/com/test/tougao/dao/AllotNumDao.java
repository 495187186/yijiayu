package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.AllotNum;

public class AllotNumDao {
	//添加
	public int add(AllotNum allotnum) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_allotnum(allot_type,allot_num) values(?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {allotnum.getAllotType(),allotnum.getAllotNum()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改
	public int update(AllotNum allotnum)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_allotnum set allot_type=?,allot_num=? where allotnum_id = ?";
		//参数对应JSP页面获取值
		String[] params = {allotnum.getAllotType(),allotnum.getAllotNum(),allotnum.getAllotNumId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}

	//查询
	@SuppressWarnings("unchecked")
	public AllotNum findByType(String type)throws SQLException, ClassNotFoundException{
		AllotNum allotnum=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_allotnum where allot_type = ?";
		String[] params = {type};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			allotnum=new AllotNum();
			allotnum.setAllotNumId(map.get("allotnum_id").toString());
			allotnum.setAllotType(map.get("allot_type").toString());
			allotnum.setAllotNum(map.get("allot_num").toString());
		}
		   return allotnum;
	}

}

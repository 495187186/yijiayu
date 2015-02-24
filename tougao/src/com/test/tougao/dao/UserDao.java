package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.User;

public class UserDao {
    //添加用户
	public int add(User user) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_user(user_account,user_name,user_active,user_role) values(?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {user.getUser_account(),user.getUser_name(),user.getUser_active(),user.getUser_role()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
   //修改登录信息
   public int update(User user)throws SQLException, ClassNotFoundException{
	   int n=0;
	   String sql = "update tg_user set user_account=?,user_name=?,user_password = ?,user_active=?,user_role=? where user_id = ?";
	   //参数对应JSP页面获取值
	   String[] params = {user.getUser_account(),user.getUser_name(),user.getUser_password(),user.getUser_active(),user.getUser_role(),user.getUser_id()};
	   n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
	   return n;
   }
   
   //用户修改自己的登陆密码
   public int updatepassword(User user)throws SQLException, ClassNotFoundException{
	   int n=0;
	   String sql = "update tg_user set user_password = ? where user_account = ?";
	   String[] params = {user.getUser_password(),user.getUser_account()};
	   n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
	   return n;
   }
   
   //删除用户
   public int delete(String id)throws SQLException, ClassNotFoundException{
	   int n=0;
	   String sql= "delete from tg_user where user_id = ?";
	   String[] params = {id};
	   n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
	   return n;
   }
   
   //根据数据库中序号查询单个用户信息
   @SuppressWarnings("unchecked")
   public User findById(String id)throws SQLException, ClassNotFoundException{
	   User user=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
	   String sql = "select * from tg_user where user_id = ?";
	   String[] params = {id};
	   //返回查询结果
	   Result r = DBHelp.executeQuery(sql, params);
	   Map map=null;
	   
	   if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
		   map = r.getRows()[0];//将查询结果赋值给map对象
		   user=new User();
		   user.setUser_id(map.get("user_id").toString());
		   user.setUser_account(map.get("user_account").toString());
		   user.setUser_name(map.get("user_name").toString());
		   user.setUser_password(map.get("user_password").toString());
		   user.setUser_active(map.get("user_active").toString());
		   user.setUser_role(map.get("user_role").toString());
	   }
	   return user;
	
   }
   
   //根据账户查询单个用户信息
   @SuppressWarnings("unchecked")
   public User findByNo(String account)throws SQLException, ClassNotFoundException{
	   User user=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
	   String sql = "select * from tg_user where user_account = ?";
	   String[] params = {account};
	   //返回查询结果
	   Result r = DBHelp.executeQuery(sql, params);
	   Map map=null;
	   
	   if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
		   map = r.getRows()[0];//将查询结果赋值给map对象
		   user=new User();
		   user.setUser_id(map.get("user_id").toString());
		   user.setUser_account(map.get("user_account").toString());
		   user.setUser_name(map.get("user_name").toString());
		   user.setUser_password(map.get("user_password").toString());
		   user.setUser_active(map.get("user_active").toString());
		   user.setUser_role(map.get("user_role").toString());
	   }
	   return user;
   }
   
   //根据姓名查询用户信息
   @SuppressWarnings("unchecked")
   public List<User> findByName(String name)throws SQLException, ClassNotFoundException{
	   List<User> list=new ArrayList<User>();
	   User user=null;
       String sql = "select * from tg_user where user_name=?";	
       String[] params = {name};
       Result r = DBHelp.executeQuery(sql, params);
    
       Map map = null;
    //循环将每条数据查出，然后添加到list中
       for(int i = 0;i <r.getRowCount();i++){
 	       map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
 	       user= new User();//初始化user
 	   
 	       user.setUser_id(map.get("user_id").toString());
		   user.setUser_account(map.get("user_account").toString());
		   user.setUser_name(map.get("user_name").toString());
		   user.setUser_password(map.get("user_password").toString());
		   user.setUser_active(map.get("user_active").toString());
		   user.setUser_role(map.get("user_role").toString());
 	   
 	      list.add(user);
      }
       return list;
       
    }
   
   //根据角色查询用户信息
   @SuppressWarnings("unchecked")
   public List<User> findByRole(String role)throws SQLException, ClassNotFoundException{
	   List<User> list=new ArrayList<User>();
	   User user=null;
       String sql = "select * from tg_user where user_role=?";	
       String[] params = {role};
       Result r = DBHelp.executeQuery(sql, params);
    
       Map map = null;
    //循环将每条数据查出，然后添加到list中
       for(int i = 0;i <r.getRowCount();i++){
 	       map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
 	       user= new User();//初始化user
 	   
 	       user.setUser_id(map.get("user_id").toString());
		   user.setUser_account(map.get("user_account").toString());
		   user.setUser_name(map.get("user_name").toString());
		   user.setUser_password(map.get("user_password").toString());
		   user.setUser_active(map.get("user_active").toString());
		   user.setUser_role(map.get("user_role").toString());
 	   
 	      list.add(user);
      }
       return list;
       
    }
    
   //根据状态查询用户信息
   @SuppressWarnings("unchecked")
   public List<User> findByActive(String active)throws SQLException, ClassNotFoundException{
	   List<User> list=new ArrayList<User>();
	   User user=null;
       String sql = "select * from tg_user where user_active=?";	
       String[] params = {active};
       Result r = DBHelp.executeQuery(sql, params);
 
       Map map = null;
       //循环将每条数据查出，然后添加到list中
       for(int i = 0;i <r.getRowCount();i++){
	       map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	       user= new User();//初始化user
	   
	       user.setUser_id(map.get("user_id").toString());
		   user.setUser_account(map.get("user_account").toString());
		   user.setUser_name(map.get("user_name").toString());
		   user.setUser_password(map.get("user_password").toString());
		   user.setUser_active(map.get("user_active").toString());
		   user.setUser_role(map.get("user_role").toString());
	   
	       list.add(user);
       }
       return list;
    
    }
   
   //查询所有用户信息
   @SuppressWarnings("unchecked")
   public List<User> findAllUser()throws SQLException, ClassNotFoundException{
	   List<User> list=new ArrayList<User>();
	   User user=null;
       String sql = "select * from tg_user";	
       String[] params = {null};
       Result r = DBHelp.executeQuery(sql, params);
 
       Map map = null;
       //循环将每条数据查出，然后添加到list中
       for(int i = 0;i <r.getRowCount();i++){
	       map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	       user= new User();//初始化user
	   
	       user.setUser_id(map.get("user_id").toString());
		   user.setUser_account(map.get("user_account").toString());
		   user.setUser_name(map.get("user_name").toString());
		   user.setUser_password(map.get("user_password").toString());
		   user.setUser_active(map.get("user_active").toString());
		   user.setUser_role(map.get("user_role").toString());
	   
	       list.add(user);
       }
       return list;
    
    }

}

package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Student;

public class StudentDao {
	//添加学生
	public int add(Student student) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_student(student_no,student_name,student_gender,student_role,student_college,student_major,student_date,student_tutor,student_mobile,student_email) values(?,?,?,?,?,?,?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {student.getStudentNo(),student.getStudentName(),student.getStudentGender(),student.getStudentRole(),student.getStudentCollege(),student.getStudentMajor(),student.getStudentDate(),student.getStudentTutor(),student.getStudentMobile(),student.getStudentEmail()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改信息
	public int update(Student student)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_student set student_no=?,student_name=?,student_gender=?,student_role=?,student_college=?,student_major=?,student_date=?,student_tutor=?,student_mobile=?,student_email=? where student_id = ?";
		//参数对应JSP页面获取值
		String[] params = {student.getStudentNo(),student.getStudentName(),student.getStudentGender(),student.getStudentRole(),student.getStudentCollege(),student.getStudentMajor(),student.getStudentDate(),student.getStudentTutor(),student.getStudentMobile(),student.getStudentEmail(),student.getStudentId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除用户
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_student where student_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除用户
	public int deleteByNo(String no)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_student where student_no = ?";
		String[] params = {no};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据学号查询单个学生信息
	@SuppressWarnings("unchecked")
	public Student findByNo(String no)throws SQLException, ClassNotFoundException{
		Student student=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_student where student_no = ?";
		String[] params = {no};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			student=new Student();
			student.setStudentId(map.get("student_id").toString());
			student.setStudentNo(map.get("student_no").toString());
			student.setStudentName(map.get("student_name").toString());
			student.setStudentGender(map.get("student_gender").toString());
			student.setStudentRole(map.get("student_role").toString());
			student.setStudentCollege(map.get("student_college").toString());
			student.setStudentMajor(map.get("student_major").toString());
			student.setStudentDate(map.get("student_date").toString());
			student.setStudentTutor(map.get("student_tutor").toString().equals("null")?"":map.get("student_tutor").toString());
			student.setStudentMobile(map.get("student_mobile").toString().equals("null")?"":map.get("student_mobile").toString());
			student.setStudentEmail(map.get("student_email").toString().equals("null")?"":map.get("student_email").toString());
			
		}
		   return student;
	}
	//查询学院所有学生
	 @SuppressWarnings("unchecked")
	 public List<Student> findCollegeStudent(String collegeid)throws SQLException, ClassNotFoundException{
		 List<Student> list = new ArrayList<Student>();
		 Student student = null;
		 String sql = "select * from tg_student where college_id=?";	
		 String[] params = {collegeid};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 student = new Student();
	    	 student.setStudentId(map.get("student_id").toString());
			 student.setStudentNo(map.get("student_no").toString());
			 student.setStudentName(map.get("student_name").toString());
			 student.setStudentGender(map.get("student_gender").toString());
			 student.setStudentRole(map.get("student_role").toString());
			 student.setStudentCollege(map.get("student_college").toString());
			 student.setStudentMajor(map.get("student_major").toString());
			 student.setStudentDate(map.get("student_date").toString());
			 student.setStudentTutor(map.get("student_tutor").toString().equals("null")?"":map.get("student_tutor").toString());
			 student.setStudentMobile(map.get("student_mobile").toString().equals("null")?"":map.get("student_mobile").toString());
			 student.setStudentEmail(map.get("student_email").toString().equals("null")?"":map.get("student_email").toString());
				
	    	 list.add(student);
	     }
	     return list;
	 }

}

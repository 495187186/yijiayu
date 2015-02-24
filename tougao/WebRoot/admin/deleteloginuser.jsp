<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.test.tougao.dao.UserDao"%>
<%@page import="com.test.tougao.dao.AdminDao"%>
<%@page import="com.test.tougao.dao.ExpertDao"%>
<%@page import="com.test.tougao.dao.StudentDao"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除登录用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
       String userid=request.getParameter("UserId");
       String userno=request.getParameter("UserNo");
       String usertype=request.getParameter("UserType");
       
       AdminDao adminDao=new AdminDao();
       ExpertDao expertDao=new ExpertDao();
       StudentDao studentDao=new StudentDao();
       UserDao dao=new UserDao();
       dao.delete(userid);//删除登录账户
       
       if(usertype.equals("1")){//删除超管账户信息
          adminDao.deleteByNo(userno.trim()); 
       }else if(usertype.equals("2")){//删除评审专家账户信息
          expertDao.deleteByNo(userno.trim());
       }else{//删除学生账户信息
          studentDao.deleteByNo(userno.trim());
       }
       
       response.sendRedirect("manageuser.jsp");
    %>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超级管理员结尾页</title>
    
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
    <jsp:useBean id="e" scope="session" class="com.test.tougao.bean.userAddOne"/>
    <jsp:useBean id="f" scope="page" class="com.test.tougao.bean.userDisplay"/>
   
    <center>
       <font size="2" color="3399ff">欢迎登录！</font>
       <font size="2" color="3399ff">
                   您是第
       <% 
          f.counter();
          for(int i=6;i>=0;i--) out.print(f.img[i]);
        %>
                   位访客
       </font>            
       <hr align="center" size=0.1 color="3399cc">
       <font size="2" color="3399cc">Copyright(c)<strong>哈尔滨工程大学</strong>&nbsp;&nbsp;2014-2020, All Rights Reserved</font><br>
       <font size="2" color="3399cc">地址：哈尔滨市南岗区南通大街145号&nbsp;&nbsp;邮编：150001&nbsp;&nbsp;联系我们：412713257@qq.com</font>
    </center>
  </body>
</html>

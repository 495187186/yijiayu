<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超级管理员标题页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
    <!--
     body {
	       margin-left: 0px;
	       margin-top:   0px;
	       margin-right: 0px;
	       margin-bottom: 0px;
          }
     a:link { 
           color: #000F0; 
           text-decoration:none;
          } 
     -->
    </style>
  </head>
  
  <body>
   <table width="100%" height="130"  border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" height="130" align="left" valign="top"><img src="images/top.jpg" width="100%" height="130" ></td>
  </tr>
  </table>
  </body>
</html>

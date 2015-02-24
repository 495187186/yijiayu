<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.test.tougao.dao.PaperCountDao"%>
<%@page import="com.test.tougao.entity.PaperCount"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>专家删除论文评审记录</title>
    
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
        request.setCharacterEncoding("UTF-8");
        String papercountid=request.getParameter("id");
        PaperCountDao papercountDao=new PaperCountDao();
        papercountDao.delete(papercountid);
        response.sendRedirect("managepaper.jsp");
     %>
  </body>
</html>

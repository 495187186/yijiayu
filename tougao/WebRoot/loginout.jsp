<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注销用户登录</title>
    
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
        RequestDispatcher rd = null;
        String msg="恭喜您，退出成功！";
        String loginUser = (String)session.getAttribute("account");
        if (loginUser != null || loginUser!=""|| loginUser.length()!=0)
           {
             session.setAttribute("account","");
             session.setAttribute("username","");
           } 
			 request.setAttribute("msg",msg);
			 rd = request.getRequestDispatcher("loginerror.jsp");
			 rd.forward(request, response);	
     %>
  </body>
</html>

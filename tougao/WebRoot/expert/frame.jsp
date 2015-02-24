<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String loginUser = (String)session.getAttribute("account");
if (loginUser == null || loginUser.length() == 0){//如果用户session失效，则返回登录首页	
	response.sendRedirect("../login.jsp");
   }	
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页布局</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
      <frameset rows="130,*,100"  cols="*" frameborder="no" border="0" framespacing="0">
         <frame name="top" scrolling="no" noresize="noresize" id="top" src="expert/top.jsp">
           <frameset cols="180,10,*" frameborder="no" border="0" framespacing="0" id="attachucp">
               <frame name="left"   scrolling="yes" src="expert/left.jsp" >
               <frame name="middle"  noresize  scrolling="no" src="expert/switch.jsp"> 
               <frame name="main" src="expert/main.jsp" scrolling="yes">
           </frameset>
        <frame name="border" scrolling="no" noresize="noresize" src="expert/footer.jsp">
     </frameset>
     <noframes>
       <body>
          <p>你的浏览器不支持框架，请升级你的浏览器</p>
       </body>
    </noframes>
</html>

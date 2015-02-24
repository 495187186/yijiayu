<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
    <!--
       td{font-size:10pt}
       a{text-decoration:none;color:#0000ff}
       a:hover{text-decoration:none;color:#808000 }
    -->
    </style>

  </head>
  
  <body>
    <%String message=request.getAttribute("msg").toString();%>
      <br>
      <table cellpadding="0" cellspacing="0" border="1" width="400" bordercolor="#3399cc" align="center" bgcolor="#ffffff">
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                     <td width="100%" align="center" background="images/td_bg.gif"><font size="4">提示信息</font></td>
                  </tr>
               </table>
            </td>
        </tr>
        <tr align="center"  bgcolor="#d5f1f2">
  	        <td height="150">
  	           <%
  	             out.println(message);
  		       %>
               <br>
               <br>
               <a href="javascript:window.parent.location.href='expert/frame.jsp'">返回首页</a>
  	        </td>
        </tr>
    </table>
  </body>
</html>

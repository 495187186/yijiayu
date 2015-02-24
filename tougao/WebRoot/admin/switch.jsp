<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超级管理员左右隐藏开关</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
    <script language="javascript">

       function switchSysBar(){
          if (parent.document.getElementById('attachucp').cols=="180,10,*"){
                 document.getElementById('leftbar').style.display="";
                 parent.document.getElementById('attachucp').cols="0,10,*";
                }
          else{ 
                 parent.document.getElementById('attachucp').cols="180,10,*";
                 document.getElementById('leftbar').style.display="none"
               }
       }
      function load(){
         if (parent.document.getElementById('attachucp').cols=="0,10,*"){
                 document.getElementById('leftbar').style.display="";
               }
       }

</script>
  </head>
  
  <body marginwidth="0" marginheight="0" bgcolor="#000000" onLoad="load()" topmargin="0" leftmargin="0">
   <center>
     <table height="100%" cellspacing="0" cellpadding="0" border="0" width="100%">
       <tbody>
         <tr>
            <td bgcolor="#3399cc" width="1"><img height="1" width="1" src="images/web_07.jpg"/></td>
            <td id="leftbar" bgcolor="#f5f4f4" style="display: none;">
               <a onClick="switchSysBar()" href="javascript:void(0);">
               <img height="90" border="0" width="9" alt="展开左侧菜单" src="images/web_08.jpg"/>
               </a>
            </td>
            <td id="rightbar" bgcolor="#f5f4f4">
               <a onClick="switchSysBar()" href="javascript:void(0);">
               <img height="90" border="0" width="9" alt="隐藏左侧菜单" src="images/web_09.jpg"/>
               </a>
            </td>
        </tr>
      </tbody>
    </table>
  </center>
 </body>
</html>

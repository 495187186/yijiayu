<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript"> 
       var times=6;  
       function clock() 
         { 
           window.setTimeout('clock()',1000); 
           times=times-1; 
           time.innerHTML =times; 
           if(times==0)  window.top.location.href = "login.jsp";
         } 
    </script>

    <style type="text/css">
    <!--
       .STYLE1 {color: #FF0000}
    -->
    </style>
    
    <style type="text/css">
    <!--
      td{font-size:12pt}
      a{text-decoration:none;color:#0000ff}
      a:hover{text-decoration:none;color:#808000 }
    -->
   </style>
    
  </head>
  
  <body onload="clock()">
    <br><br><br><br><br><br><br><br>
    <table cellpadding="0" cellspacing="0" border="1" width="400" bordercolor="#3399cc" align="center" bgcolor="#ffffff">
             <tr>
                <td height="25">
                  <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" background="images/selectbar.gif" bgcolor="#3399cc">
                     <tr align="center">
                        <td width="100%"><font size="4">提示信息</font></td>
                     </tr>
                  </table>
                </td>
             </tr>
                   
             <tr bgcolor="#d5f1f2"> 
                 <td>
                    <table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                           <td height=100 align="right" width=110><img src="images/error.jpg"></td>
                           <td align="left"><%=request.getAttribute("msg") %></td>
                       </tr>
                       <tr >
                          <td colspan=2>
                             <table border="0" width="100%" cellpadding="0" cellspacing="0">
                                <tr>
                                   <td class= "FontBlack STYLE1" align="right"> 该页面将在</td>
                                   <td align="center"><div class= "FontBlack STYLE1" id= "time"> 5 </div></td>
                                   <td class= "FontBlack STYLE1" align="left">秒后自动返回 </td>
                               </tr>
                             </table>
                          </td>
                       </tr>
                     </table>
                   <br>  
            </td>
        </tr> 
    </table> 
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择公告种类</title>
    
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
     BODY {
	        FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #d6dff7
          }
     td{font-size:11pt}
    
     
    -->
    </style>
	

  </head>
  
  <body>
    <form  name="selectnotetype"  action="SelectNoteTypeServlet" method="post" >
      <br>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>添加公告</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="20%" height="30" bgcolor="#d5f1f2"><div align="center">公告类型：</div></td>
                     <td width="80%" bgcolor="#d5f1f2" valign="middle" align="center">　 
                         
                         <input type="radio" name="notetype" value="0" checked>添加公告不上传附件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <input type="radio" name="notetype" value="1">添加公告并上传附件
                         
                     </td>
                  </tr>
                  
             </table>
                  
           <br>
           <div align="center"><input type="submit" value="确定"  name="submit" >
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="reset" value="取消" name="reset" onclick="javascript:history.back(-1);">
           </div>
       </td>
     </tr>          
   </table>
  </form>
  </body>
</html>

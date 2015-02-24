<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>下载学生使用帮助</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<STYLE type=text/css>
	   BODY {
	          FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #d6dff7
            }
          A {
	          FONT: 11pt 宋体; COLOR: #0000ff; TEXT-DECORATION: none
            }
    A:hover {
	          TEXT-DECORATION:none; COLOR: #808000 
            }
         TD {
	          FONT-SIZE: 11pt; LINE-HEIGHT: 15px; FONT-FAMILY: 宋体
            }
        TH  {
	          FONT-WEIGHT: bold; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(images/admin_bg_1.gif); COLOR: white; BACKGROUND-COLOR:#4455aa
	        }
	        
	
	</STYLE>

  </head>
  
  <body>
    <br>
    <br>
    <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
       <tr>
          <td height="25">
             <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                 <tr>
                     <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>欢迎光临帮助下载中心</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="50%" height="30" bgcolor="#d5f1f2"><div align="center">标题</div></td>
                     <td width="30%" height="30" bgcolor="#d5f1f2"><div align="center">文件大小</div></td>
                     <td width="20%" height="30" bgcolor="#d5f1f2"><div align="center">操作</div></td>
                  </tr>
                  <tr class="kbj">
                     <td width="50%" height="30" bgcolor="#d5f1f2"><div align="center">学生用户软件使用说明书</div></td>
                     <td width="30%" height="30" bgcolor="#d5f1f2"><div align="center">2.20M</div></td>
                     <td width="20%" height="30" bgcolor="#d5f1f2">
                        <div align="center">
                           <img height="16" width="16" src="images/download.png"><a href="DownHelpFileServlet?FileName=<%="studenthelp.doc" %>">下载</a>
                        </div>
                     </td>
                  </tr>
               </table>
               <br>
           </td>
        </tr>          
      </table>
  </body>
</html>

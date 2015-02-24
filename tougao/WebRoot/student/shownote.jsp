<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.NoteDao"%>
<%@page import="com.test.tougao.entity.Note"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看公告</title>
    
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
	        FONT-SIZE: 11pt; COLOR: #000000; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff
          }
     td{font-size:11pt}
     a{text-decoration:none;color:#0000ff}
     a:hover{text-decoration:none;color:#808000 }
     select {font-size:11pt}
     input {font-size:11pt}
     
    -->
    </style>

  </head>
  
  <body>
    <%
       request.setCharacterEncoding("UTF-8");
       String id=request.getParameter("NoteId");
       NoteDao noteDao=new NoteDao();
       Note note=noteDao.findById(id);
    %>
    <table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
	   <tr>
		  <td height="25">
			  <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				 <tr>
                    <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG><%=note.getNoteName() %></STRONG></FONT></td>
                 </tr>
			  </table>	
		  </td>
		</tr>	
		<tr bgcolor="#d5f1f2" class="bjl">
		   <td width="100%" height="30" align="center">
		                  发布者：<%=note.getAdminName() %>&nbsp;&nbsp;&nbsp;&nbsp;
		                  更新时间：<%=note.getNoteDate().substring(0,note.getNoteDate().length()-2) %>&nbsp;&nbsp;&nbsp;&nbsp;
		      <a href="javascript:history.back(-1);">返回</a>
		   </td>
		</tr>
		<tr bgcolor="#ffffff" class="bjl">
		   <td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#ffffff">
				  <tr bgcolor="#ffffff" class="bjl">
				     <td width="100%" align="left"><%=note.getNoteContent() %></td>
				  </tr>
				  <tr bgcolor="#ffffff" class="bjl">
				     <td width="100%" height="30" align="left"><a href="DownloadNoteServlet?NoteId=<%=note.getNoteId() %>"><%=note.getIsAttach().equals("0")?"":note.getDiskName() %></a></td>
				  </tr>
			   </table>
		   </td>
		</tr>
	 </table>
				 
  </body>
</html>

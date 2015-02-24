<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.ReviewedDao"%>
<%@page import="com.test.tougao.entity.Reviewed"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论文留言</title>
    
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
     <%
         request.setCharacterEncoding("UTF-8");
         Reviewed message=(Reviewed)session.getAttribute("message");
         String []s={"优","良","中","差"};
     %>
     <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
        <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>查看留言</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">论文题目</div></td>
                     <td width="56%" bgcolor="#d5f1f2" align="center"><%=message.getPaperName() %></td>
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">论文类型</div></td>
                     <td width="16%" bgcolor="#d5f1f2" align="center"><%=message.getPaperType().equals("0")?"毕业论文":"期刊论文" %></td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">指派日期</div></td>
                     <td width="56%" bgcolor="#d5f1f2" align="center"><%=message.getAllotTime().substring(0,message.getAllotTime().length()-2) %></td>
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">论文等级</div></td>
                     <td width="16%" bgcolor="#d5f1f2" align="center"><%=s[Integer.parseInt(message.getPaperLevel())] %></td>
                  </tr>
               </table>
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">留言主题</div></td>
                     <td colspan="3" bgcolor="#d5f1f2" align="center"><%=message.getMessageName() %></td>
                   
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">留言日期</div></td>
                     <td width="32%" bgcolor="#d5f1f2" align="center"><%=message.getSubmitTime().substring(0,message.getSubmitTime().length()-2) %></td>
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">附件</div></td>
                     <td width="40%" bgcolor="#d5f1f2" align="center"><a href="DownReviewPaperServlet?FileId=<%=message.getReviewedId() %>"><%=message.getDiskName() %></a></td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">留言内容</div></td>
                     <td colspan="3" bgcolor="#d5f1f2">　 
                        <textarea name = "info" style = "height:200px;width:500px;"/><%=message.getPaperMessage()==null?"":message.getPaperMessage() %></textarea> 
                     </td>
                  </tr>
             </table>
            
           <br>
           <div align="center">
               <a href="javascript:history.back(-1)"><font size="4">返回</font></a>
           </div>
       </td>
     </tr>          
   </table>
  </body>
</html>

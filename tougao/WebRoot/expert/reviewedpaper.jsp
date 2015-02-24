<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.ReviewedDao"%>
<%@page import="com.test.tougao.entity.Reviewed"%>
<%@page import="com.test.tougao.dao.ReviewPaperDao"%>
<%@page import="com.test.tougao.entity.ReviewPaper"%>
<%@page import="com.test.tougao.dao.StudentUpfileDao"%>
<%@page import="com.test.tougao.entity.StudentUpfile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已审论文</title>
    
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
	          FONT-SIZE: 11pt 宋体; COLOR: #0000ff; TEXT-DECORATION: none
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
       String expertno=request.getParameter("expertno");
       String papername=URLDecoder.decode(request.getParameter("papername"), "UTF-8");
       papername=new String(papername.getBytes("iso-8859-1"), "UTF-8");
       System.out.println(papername);
       String papertype=request.getParameter("papertype");
       String allotdate=request.getParameter("allotdate");
       String reviewtime=request.getParameter("reviewtime");
       System.out.println(reviewtime);
       ReviewedDao reviewedDao=new ReviewedDao();
       Reviewed reviewed=reviewedDao.findReviewed(expertno,papername.trim(),reviewtime);
       String[] level={"优","良","中","差"};
       ReviewPaperDao reviewpaperDao=new ReviewPaperDao();
       ReviewPaper reviewpaper=reviewpaperDao.findPaper(expertno,papername,allotdate);
       StudentUpfileDao studentupfileDao=new StudentUpfileDao();
       StudentUpfile studentupfile=studentupfileDao.findUpfile(papername.trim(),reviewpaper.getUploadDate().substring(0,reviewpaper.getUploadDate().length()-2));
    %>
      <br>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>已审论文</STRONG></FONT></td>
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
                     <td width="40%" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="papername" value="<%=reviewed.getPaperName() %>" readonly="readonly"/>
                     </td>
                     <td width="10%" height="30" bgcolor="#d5f1f2"><div align="center">类别</div></td>
                     <td width="36%" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="papertype" value="<%=papertype.equals("0")?"毕业论文":"期刊论文" %>" readonly="readonly"/>
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">评审结果</div></td>
                     <td width="40%" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="review" value="<%=reviewed.getIsAgreed().equals("0")?"拒绝评审":level[Integer.parseInt(reviewed.getPaperLevel())] %>" readonly="readonly"/>
                     </td>
                     <td width="10%" height="30" bgcolor="#d5f1f2"><div align="center">操作</div></td>
                     <td width="36%" bgcolor="#d5f1f2" align="center">
                       <img height="16" width="16" src="images/download.png"><a href="FileDownServlet?FileId=<%=studentupfile.getFileId() %>">下载</a>                      
                     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">评审留言</div></td>
                     <td width="86%" bgcolor="#d5f1f2" colspan="3">　 
                        <textarea name = "info" style = "height:200px;width:500px;" readonly="readonly"/><%=reviewed.getPaperMessage() %></textarea> 
                     </td>
                  </tr>
               </table>
               <br>
               <div align="center">
                   <a href="javascript:history.back(-1);">返回</a>
               </div>
       </td>
     </tr>          
   </table>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.DocReviewedDao"%>
<%@page import="com.test.tougao.entity.DocReviewed"%>
<%@page import="com.test.tougao.dao.ReviewFileDao"%>
<%@page import="com.test.tougao.entity.ReviewFile"%>
<%@page import="com.test.tougao.dao.DocFileDao"%>
<%@page import="com.test.tougao.entity.DocFile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已审材料</title>
    
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
       String docname=URLDecoder.decode(request.getParameter("docname"), "UTF-8");
       docname=new String(docname.getBytes("iso-8859-1"), "UTF-8");
       System.out.println(docname);
       String doctype=request.getParameter("doctype");
       String allotdate=request.getParameter("allotdate");
       String reviewtime=request.getParameter("reviewtime");
       System.out.println(reviewtime);
       DocReviewedDao docreviewedDao=new DocReviewedDao();
       DocReviewed docreviewed=docreviewedDao.findReviewedDoc(expertno,docname.trim(),reviewtime);
       String[] level={"优","良","中","差"};
       ReviewFileDao reviewfileDao=new ReviewFileDao();
       ReviewFile reviewfile=reviewfileDao.findFile(expertno,docname,allotdate);
       DocFileDao docfileDao=new DocFileDao();
       DocFile docfile=docfileDao.findUpfile(docname.trim(),reviewfile.getUploadDate().substring(0,reviewfile.getUploadDate().length()-2));
    %>
      <br>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>已审材料</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">材料名称</div></td>
                     <td width="40%" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="docname" value="<%=docreviewed.getDocName() %>" readonly="readonly"/>
                     </td>
                     <td width="10%" height="30" bgcolor="#d5f1f2"><div align="center">类别</div></td>
                     <td width="36%" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="doctype" value="<%=doctype.equals("0")?"学校建设":"教学项目" %>" readonly="readonly"/>
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">评审结果</div></td>
                     <td width="40%" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="review" value="<%=docreviewed.getIsAgreed().equals("0")?"拒绝评审":level[Integer.parseInt(docreviewed.getDocLevel())] %>" readonly="readonly"/>
                     </td>
                     <td width="10%" height="30" bgcolor="#d5f1f2"><div align="center">操作</div></td>
                     <td width="36%" bgcolor="#d5f1f2" align="center">
                       <img height="16" width="16" src="images/download.png"><a href="DownloadFileServlet?FileId=<%=docfile.getFileId() %>">下载</a>                      
                     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">评审留言</div></td>
                     <td width="86%" bgcolor="#d5f1f2" colspan="3">　 
                        <textarea name = "info" style = "height:200px;width:500px;" readonly="readonly"/><%=docreviewed.getDocMessage() %></textarea> 
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
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
    
    <title>每个论文的具体上传记录</title>
    
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
          String studentno=request.getParameter("studentno");
          String papername=URLDecoder.decode(request.getParameter("papername"), "UTF-8");
          papername=new String(papername.getBytes("iso-8859-1"), "UTF-8");
          String papertype=request.getParameter("papertype");
          String paperwriter=URLDecoder.decode(request.getParameter("paperwriter"), "UTF-8");
          paperwriter=new String(paperwriter.getBytes("iso-8859-1"), "UTF-8");
          
          StudentUpfileDao studentupfileDao=new StudentUpfileDao();
          
          List<StudentUpfile> list=studentupfileDao.findPaperNotes(studentno,papername.trim());
          
      %>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>查看论文提交记录</STRONG></FONT></td>
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
                     <td width="30%" bgcolor="#d5f1f2" align="center"> 
                       <input type="text" name = "filename" size="23" value="<%=papername %>" readonly="readonly"/>
                     </td>
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">论文类型</div></td>
                     <td width="14%" bgcolor="#d5f1f2" align="center">
                       <input type="text" name = "filename" size="8" value="<%=papertype.equals("0")?"毕业论文":"期刊论文" %>" readonly="readonly"/>
                     </td>
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">通讯作者</div></td>
                     <td width="14%" bgcolor="#d5f1f2" align="center">
                       <input type="text" name = "filename" size="8" value="<%=paperwriter %>" readonly="readonly"/>
                     </td>
                  </tr>
                 
               </table>
               
               <%
                   for(int i=0;i<list.size();i++){
                     StudentUpfile obj=list.get(i);
               %>
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="20%" height="30" bgcolor="#d5f1f2"><div align="center">上传日期</div></td>
                     <td width="30%" bgcolor="#d5f1f2" align="center"><input type="text" name="papername" size="22" value="<%=obj.getFileTime().substring(0,obj.getFileTime().length()-2) %>" readonly="readonly"/></td>
                     <td width="20%" height="30" bgcolor="#d5f1f2"><div align="center">操作</div></td>
                     <td width="30%" bgcolor="#d5f1f2" align="center">
                        <img height="16" width="16" src="images/download.png"><a href="FileDownServlet?FileId=<%=obj.getFileId() %>">下载</a>
                     </td>
                  </tr>  
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">备注</div></td>
                     <td colspan="3" bgcolor="#d5f1f2" align="center">
                        <textarea name = "info" style = "height:100px;width:488px;" readonly="readonly"/><%=obj.getFileInfo() %></textarea> 
                     </td>
                  </tr>
             </table>
            <%
               }
            %>
            <br>
            <div align="center">
                 <a href="javascript:history.go(-1);"><font size="4">返回</font></a>            
            </div>
       </td>
     </tr>          
   </table>
  </body>
</html>

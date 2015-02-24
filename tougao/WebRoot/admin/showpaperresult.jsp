<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.AllotPaperDao"%>
<%@page import="com.test.tougao.entity.AllotPaper"%>
<%@page import="com.test.tougao.dao.PaperDao"%>
<%@page import="com.test.tougao.entity.Paper"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看每篇论文的分配评审结果</title>
    
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
      A {
	          FONT: 12px 宋体; COLOR: #0000ff; TEXT-DECORATION: none
            }
     A:hover {
	          TEXT-DECORATION:none; COLOR: #808000 
            }
     td{font-size:11pt}
    -->
    </style>

  </head>
  
  <body>
    <%
        request.setCharacterEncoding("UTF-8");
        String papername=URLDecoder.decode(request.getParameter("papername"), "UTF-8");
        papername=new String(papername.getBytes("iso-8859-1"), "UTF-8");
        String []s={"未评审","评审中","评审完毕"};
        
        PaperDao paperDao=new PaperDao();
        Paper paper=paperDao.findByName(papername.trim());
        
        AllotPaperDao allotpaperDao=new AllotPaperDao();
        List<AllotPaper> list=allotpaperDao.findByPaperName(papername.trim());
    %>
    <table width="60%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc" bgcolor="#FFFFFF" >
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>论文指派评审结果</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <br>
			   <table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
			      <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="10%" height="30"><div align="center">论文题目</div></td>
					 <td width="50%" height="30" colspan="4"><div align="center"><%=paper.getPaperName() %></div></td>
					 <td width="10%" height="30"><div align="center">上传时间</div></td>
					 <td width="30%" height="30" colspan="3"><div align="center"><%=paper.getPaperTime().substring(0,paper.getPaperTime().length()-2) %></div></td>
				  </tr>
			   </table>
			   <table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="10%" height="30"><div align="center">上传作者</div></td>
					 <td width="15%" height="30"><div align="center"><%=paper.getUploader() %></div></td>
					 <td width="10%" height="30"><div align="center">通讯作者</div></td>
					 <td width="15%" height="30"><div align="center"><%=paper.getPaperWriter() %></div></td>
					 <td width="10%" height="30"><div align="center">论文类型</div></td>
					 <td width="15%" height="30"><div align="center"><%=paper.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="10%" height="30"><div align="center">上传次数</div></td>
					 <td width="15%" height="30"><div align="center"><%=paper.getPaperCounter() %></div></td>
				  </tr>
				</table>
				<br>
				<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				   <tr bgcolor="#ffffcc" class="bjl">
				      <td width="10%" height="30"><div align="center">序号</div></td>
					  <td width="25%" height="30"><div align="center">分配时间</div></td>
					  <td width="15%" height="30"><div align="center">指派专家</div></td>
					  <td width="25%" height="30"><div align="center">审稿费(单位：元)</div></td>
					  <td width="10%" height="30"><div align="center">状态</div></td>
					  <td width="15%" height="30"><div align="center">评审状态</div></td>
				   </tr>
				   <%
				      for(int i=0;i<list.size();i++){
                         AllotPaper obj=list.get(i);
				   %>
				      <tr bgcolor="#ffffff" class="bjl">
				         <td width="10%" height="30"><div align="center"><%=i+1 %></div></td>
					     <td width="25%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
					     <td width="15%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					     <td width="25%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
					     <td width="10%" height="30"><div align="center"><%=obj.getIsReviewed().equals("0")?"未评审":obj.getIsAgreed().equals("0")?"拒绝":"同意" %></div></td>
					     <td width="15%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>
				      </tr>
				   <%
				      }
				   %>
				   
			    </table>
			    <br>
			    <div align="center">
			        <a href="javascript:history.back(-1);"><font size="4">返回</font></a>
			    </div>
		     </td>
		 </tr>
     </table>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.ReviewedPaperDao"%>
<%@page import="com.test.tougao.entity.ReviewedPaper"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理已审论文</title>
    
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
<script language="javascript" >
function checkForm() 
{
  var form1 = document.myform;
  var id=form1.page.value;
   if (id=="") {
   window.alert("输入页号不能为空 ！");
   document.myform.page.focus(); 
   return false;
  }
  return true;
}
</script>

  </head>
  
  <body>
    <%
       request.setCharacterEncoding("UTF-8");
       String no=session.getAttribute("account").toString();
       ReviewedPaperDao reviewedpaperDao = new ReviewedPaperDao();
       
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<ReviewedPaper> list = null; 
       list = reviewedpaperDao.findReviewedPaper(no);
	   allRow = list.size();
       totalPage = (allRow % pageSize == 0) ? (allRow / pageSize) : (allRow / pageSize + 1);
       //取得待显示页码
       strPage = request.getParameter("page");
       //strPage = "1";
       if(strPage==null)
          {
            currentPage = 1;
          }
       else 
          {
            currentPage = java.lang.Integer.parseInt(strPage); 
            if(currentPage<1) currentPage = 1;
            if(currentPage>totalPage) currentPage = totalPage;                                 
          }
       start = (currentPage-1)*10;//当前页面的第一条记录位置
    %>
    <form action="expert/managereviewpaper.jsp"" method="post" name="myform">
	   <table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>全部已审论文列表</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="6%" height="30"><div align="center">序号</div></td>
					 <td width="30%" height="30"><div align="center">论文题目</div></td>
					 <td width="10%" height="30"><div align="center">论文类别</div></td>
					 <td width="15%" height="30"><div align="center">论文指派日期</div></td>
				     <td width="15%" height="30"><div align="center">论文审批日期</div></td>
				     <td width="14%" height="30"><div align="center">审稿费(单位：元)</div></td>
				     <td width="10%" height="30"><div align="center">稿件状态</div></td>			
				     
				  </tr>
				  <%
				     for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            ReviewedPaper obj = (ReviewedPaper)list.get(i);                           
                            if(i%2==0){
                         
                  %>
                         <tr bgcolor="#ffffff" class="bjl">
				  <%
				            }else{
				  %>
				         <tr bgcolor="#ffffcc" class="bjl">
				  <%
				           }
				  %> 
				     <td width="6%" height="30"><div align="center"><%=i+1 %></div></td>
					 <td width="30%" height="30"><div align="center"><a href="expert/reviewedpaper.jsp?expertno=<%=no %>&&papername=<%=URLEncoder.encode(obj.getPaperName(), "UTF-8") %>&&papertype=<%=obj.getPaperType() %>&&allotdate=<%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %>&&reviewtime=<%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2)%>"><%=obj.getPaperName() %></a></div></td>
					 <td width="10%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				     <td width="15%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>	
				     <td width="10%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>			
				     
				  </tr>
				  <%
				      }
				  %>
			   </table>
			</td>
		 </tr>
	   </table>
	   <%
	      if(flag==1){ 
	   %>
	   <p></p>
       <center>
       <%
         if(allRow!=0){//数据库中有记录时才显示下列导航栏
       %>
                     共<%=allRow %>项&nbsp;&nbsp;共<%=totalPage %>页&nbsp;&nbsp; 第<%=currentPage %>页&nbsp;&nbsp;
           <a href="expert/managereviewedpaper.jsp?page=<%="1" %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="expert/managereviewedpaper.jsp?page=<%=currentPage-1%>">上一页</a>
        <%
           }else{
        %>
                             上一页
        <%
           }  
        %>
           &nbsp;&nbsp;
        <%
           if(currentPage<totalPage){
        %>
           <a href="expert/managereviewedpaper.jsp?page=<%=currentPage+1 %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="expert/managereviewedpaper.jsp?page=<%=totalPage %>">尾页 </a>&nbsp;&nbsp;
                             转到第<input type="text" name="page" size="6"> 页
          <span><input class=buttonface type=submit value=确定  name=submit onClick="return checkForm()"></span>
       <%
        }
       %>
       </center>
       <%
        }
       %>
	   
	</form> 
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.ReviewFileDao"%>
<%@page import="com.test.tougao.entity.ReviewFile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理待审材料</title>
    
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
       ReviewFileDao reviewfileDao = new ReviewFileDao();
       
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<ReviewFile> list = null; 
       list = reviewfileDao.findReviewFile(no,"0");
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
    <form action="expert/managereviewfile.jsp"" method="post" name="myform">
	   <table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>全部待审材料列表</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="6%" height="30"><div align="center">序号</div></td>
					 <td width="25%" height="30"><div align="center">材料名称</div></td>
					 <td width="8%" height="30"><div align="center">材料类别</div></td>
					 <td width="15%" height="30"><div align="center">指派日期</div></td>
				     <td width="15%" height="30"><div align="center">截止日期</div></td>
				     <td width="10%" height="30"><div align="center">审稿费(篇/元)</div></td>	
				     <td width="5%" height="30"><div align="center">状态</div></td>
				     <td width="16%" height="30"><div align="center">操作</div></td>			
				     
				  </tr>
				  <%
				     for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            ReviewFile obj = (ReviewFile)list.get(i);                           
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
					 <td width="25%" height="30"><div align="center"><a href="expert/reviewfile.jsp?expertno=<%=no %>&&docname=<%=URLEncoder.encode(obj.getDocName(), "UTF-8") %>&&doctype=<%=obj.getDocType() %>&&uploaddate=<%=obj.getUploadDate().substring(0,obj.getUploadDate().length()-2) %>&&allotdate=<%=obj.getAllotDate().substring(0,obj.getAllotDate().length()-2) %>&&docreward=<%=obj.getDocReward() %>"><%=obj.getDocName() %></a></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getDocType().equals("0")?"学校建设":"教学项目" %></div></td>
					 <td width="15%" height="30"><div align="center"><%=obj.getAllotDate().substring(0,obj.getAllotDate().length()-2) %></div></td>
				     <td width="15%" height="30"><div align="center"><%=obj.getDocDeadline().substring(0,obj.getDocDeadline().length()-2) %></div></td>
				     <td width="10%" height="30"><div align="center"><%=obj.getDocReward() %></div></td>		
				     <td width="5%" height="30"><div align="center"><%=obj.getDocFlag().equals("0")?"无":obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="16%" height="30">
				        <div align="center">
				           <%
						      if(obj.getDocFlag().equals("0")){//还未同意或拒绝
						   %>
						         <img height="15" width="15" src="images/smile.gif"> 
				                 <a href="expert/fileagreeornot.jsp?result=<%="1" %>&&Id=<%=obj.getReviewFileId() %>" onclick="if(confirm('您确定同意审批吗?')==false)return false;">同意</a> 
				                 &nbsp; &nbsp;
				                 <img height="15" width="15" src="images/sad.gif">
						         <a href="expert/fileagreeornot.jsp?result=<%="0" %>&&Id=<%=obj.getReviewFileId() %>" onclick="if(confirm('您确定拒绝审批吗?')==false)return false;">拒绝</a>  
						   <%
						         }else{
						  
						   %>
						         <img height="15" width="15" src="images/undo.png">已选择
						   <%
						         } 
						   %>
				        </div>
				     </td>	
				     
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
           <a href="expert/managereviewfile.jsp?page=<%="1" %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="expert/managereviewfile.jsp?page=<%=currentPage-1%>">上一页</a>
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
           <a href="expert/managereviewfile.jsp?page=<%=currentPage+1 %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="expert/managereviewfile.jsp?page=<%=totalPage %>">尾页 </a>&nbsp;&nbsp;
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

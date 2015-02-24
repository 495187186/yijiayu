<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.FileMessageDao"%>
<%@page import="com.test.tougao.entity.FileMessage"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>未读材料留言信息</title>
    
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
       request.setCharacterEncoding("UTF-8");//字符编码,注意页面中汉字搜索必须用此编码
       String no=session.getAttribute("account").toString();
       
       FileMessageDao filemessageDao = new FileMessageDao();
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<FileMessage> list = null; 
       list = filemessageDao.findReadMessage(no,"0");//查询该用户的未读留言
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
     <form action="admin/unreadpapermessage.jsp"" method="post" name="myform">
	   <table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>未读留言列表</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="10%" height="30"><div align="center">序号</div></td>
					 <td width="30%" height="30"><div align="center">材料名称</div></td>
					 <td width="10%" height="30"><div align="center">评审专家</div></td>
					 <td width="15%" height="30"><div align="center">上传日期</div></td>
				     <td width="20%" height="30"><div align="center">留言主题</div></td>		
				     <td width="15%" height="30"><div align="center">留言日期</div></td>		
				  </tr>
				  <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            FileMessage obj = (FileMessage)list.get(i);                           
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
				       <td width="10%" height="30"><div align="center"><%=i+1 %></div></td>
					   <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					   <td width="10%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					   <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
				       <td width="20%" height="30"><div align="center"><a href="FileMessageServlet?Id=<%=obj.getFileMessageId() %>"><%=obj.getMessageName() %></a></div></td>		
				       <td width="15%" height="30"><div align="center"><%=obj.getMessageDate().substring(0,obj.getMessageDate().length()-2) %></div></td>		
				    </tr>
				  <%
				       }
				        
				  %>
			   </table>
			</td>
	      </tr>
	    </table>
	    
	   <p></p>
       <center>
       <%
         if(allRow!=0){//数据库中有记录时才显示下列导航栏
       %>
                     共<%=allRow %>项&nbsp;&nbsp;共<%=totalPage %>页&nbsp;&nbsp; 第<%=currentPage %>页&nbsp;&nbsp;
           <a href="admin/unreadpapermessage.jsp?page=<%="1" %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="admin/unreadpapermessage.jsp?page=<%=currentPage-1%>">上一页</a>
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
           <a href="admin/unreadpapermessage.jsp?page=<%=currentPage+1 %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="admin/unreadpapermessage.jsp?page=<%=totalPage %>">尾页 </a>&nbsp;&nbsp;
                             转到第<input type="text" name="page" size="6"> 页
          <span><input class=buttonface type=submit value=确定  name=submit onClick="return checkForm()"></span>
       <%
        }
       %>
       </center>
	 </form>
  </body>
</html>

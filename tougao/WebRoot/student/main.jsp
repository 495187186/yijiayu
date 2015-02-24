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
    
    <title>主页</title>
    
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
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff
}
A {
	FONT: 12px 宋体; COLOR: #3399FF; TEXT-DECORATION: none
}
A:hover {
	COLOR: #808000
}
TD {
	FONT-SIZE: 12px; LINE-HEIGHT: 30px; FONT-FAMILY: 宋体
}
TH {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(images/admin_bg_1.gif); COLOR: white; BACKGROUND-COLOR: 

#4455aa
}
TD.txlHeaderBackgroundAlternate {
	COLOR: #ffffff; BACKGROUND-COLOR: #799ae1
}
#TableTitleLink A:link {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:visited {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:active {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:hover {
	COLOR: #ffffff; TEXT-DECORATION: underline
}
TD.txlRow {
	BACKGROUND-COLOR: #dee5fa
}
TD.txlRowHighlight {
	BACKGROUND-COLOR: #d4def9
}
.tableBorder {
	BORDER-RIGHT: #6595d6 1px solid; BORDER-TOP: #6595d6 1px solid; BORDER-LEFT: #6595d6 1px solid; BORDER-BOTTOM: 

#6595d6 1px solid; BACKGROUND-COLOR: #ffffff
}
INPUT {
	FONT-SIZE: 12px; LINE-HEIGHT: 15px; FONT-FAMILY: Tahoma,Verdana,宋体
}
SELECT {
	FONT-SIZE: 12px; LINE-HEIGHT: 15px; FONT-FAMILY: Tahoma,Verdana,宋体
}
TEXTAREA {
	FONT-SIZE: 12px; LINE-HEIGHT: 15px; FONT-FAMILY: Tahoma,Verdana,宋体
}
</STYLE>

  </head>
  
  <body>
    <%
       request.setCharacterEncoding("UTF-8");
       NoteDao noteDao=new NoteDao();
       
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<Note> list = null; 
       list = noteDao.findAllNotes();
       if(list.size()==0){
          flag=0;
       }
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
    <table width=70%  border=0 align="center">
	  <tr>
		<td align=center>
		   <font face="仿宋_GB2312" size="5" color="#3399FF"> <strong>哈尔滨工程大学计算机学院外聘专家管理系统</strong></font>
           <hr width=70% align=center size=5>
		   <br>
		</td>
     </tr>
	 <tr>
	    <td align=center>
		  <font face="黑体" size="3" color="#0000a0"><strong><font size="4">公告信息</font>&nbsp;</strong></font>
		  <font size="2" color="#ff8000">NOTICE</font>
		  <br>
		  <hr width=30% size=3 >
		  <br>
		</td>
	  </tr>
    </table>
    <%
       if(list.size()==0){
    %>
      <br><br>
	  <table width=50%  border=0 align="center">
	     <tr>
			<td align=center>
			   <strong><font face="宋体" size="5" color="#3399FF">暂无公告信息</font></strong>
			         
		    </td>
	     </tr>
	  </table> 
	<%
	   }else{
	      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
             {
                Note obj = (Note)list.get(i); 
    %>
      <table width=70%  border=0 align="center">
        <tr>
		  <td width=50% height=5>&nbsp;&nbsp;<font face="宋体" size="2" color="#3399FF"><a href="admin/shownote.jsp?NoteId=<%=obj.getNoteId() %>"><%=obj.getNoteName() %></a></font></td>
		  <td width=20% align="center"><font face="宋体" size="2" color="#3399FF"><%=obj.getNoteDate().substring(0,obj.getNoteDate().length()-2) %></font></td>
		</tr>
      </table>  
      <hr width=70% size=0.2 align="center">
    <%
       }
      }
    %>
       <%
	      if(flag==1){ 
	   %>
	   <p></p>
       <center>
       <%
         if(allRow!=0){//数据库中有记录时才显示下列导航栏
       %>
                     共<%=allRow %>项&nbsp;&nbsp;共<%=totalPage %>页&nbsp;&nbsp; 第<%=currentPage %>页&nbsp;&nbsp;
           <a href="student/main.jsp?page=<%="1" %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="student/main.jsp?page=<%=currentPage-1%>">上一页</a>
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
           <a href="student/main.jsp?page=<%=currentPage+1 %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="student/main.jsp?page=<%=totalPage %>">尾页 </a>
       <%
        }
       %>
       </center>
       <%
        }
       %>
  </body>
</html>

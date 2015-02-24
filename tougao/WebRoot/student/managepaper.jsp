<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
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
    
    <title>查看已投递论文</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript" src="js/Calendar1.js"></script> 
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

<script language="javascript" >
function checkDate() 
{
  var form = document.searchpaper;
  var begindate=form.begindate.value;
  var enddate=form.enddate.value;
  var y_begin = begindate.substring(0,4);
  var m_begin = begindate.substring(5,7);
  var d_begin = begindate.substring(8,10);
  var y_end = enddate.substring(0,4);
  var m_end = enddate.substring(5,7);
  var d_end = enddate.substring(8,10);
  
  var check_in=new Date(y_begin,m_begin,d_begin); 
  var check_out=new Date(y_end,m_end,d_end); 
  var days_diff=(check_out.valueOf()-check_in.valueOf())/86400000; 
  
   if (days_diff<0) {
     window.alert("输入的起止日期不正确 ！");
   
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
       String papername = request.getParameter("papername");
       String begindate=request.getParameter("begindate");
       String enddate = request.getParameter("enddate");
       String papertype = request.getParameter("papertype");
       String paperallot = request.getParameter("paperallot");
       
       String []s={"未审核","审核中","审核完毕"};
       PaperDao paperDao = new PaperDao();
       Paper paper = new Paper();
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<Paper> list = null; 
       list = paperDao.findStuPaper(no);
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
    <form action="student/managepaper.jsp" method="post" name="searchpaper">
      <table width="96%"  border="1" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>搜索论文</STRONG></FONT></td>
                  </tr>
               </table>
            </td>
        </tr>
        <tr>
            <td>
               <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
                  <tr class="kbj">
                      <td width="7%" height="30" bgcolor="#d5f1f2" ><div align="center" >论文题目</div></td>
                      <td width="24%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <input name="papername" type="text"  id="papername" value="" size="27">
                          </div>
                      </td>
                      <td width="7%" height="30" bgcolor="#d5f1f2" ><div align="center" >日期区间</div></td>
                      <td width="22%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                                                                              从<input onfocus="calendar()" name="begindate" type="text"  id="begindate" value="" size="7">到<input onfocus="calendar()" name="enddate" type="text"  id="enddate" value="" size="7">  
                          </div>
                      </td>    
                      <td width="7%" height="30" bgcolor="#d5f1f2" ><div align="center" >论文类别</div></td>
                      <td width="10%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                             <select name="papertype" id="papertype">
                                 <option value="" selected>--请选择--</option>
                                 <option value="0">毕业论文</option>
                       	         <option value="1">期刊论文</option>
                             </select>
                          </div>
                      </td>   
                      <td width="7%" height="30" bgcolor="#d5f1f2" ><div align="center" >派审状态</div></td>
                      <td width="10%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                             <select name="paperallot" id="paperallot">
                                 <option value="" selected>--请选择--</option>
                                 <option value="0">未派审</option>
                       	         <option value="1">已派审</option>
                             </select>
                          </div>
                      </td>     
                      <td width="6%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                               <input type="submit" name="search" value="搜索" onclick="return checkDate();">
                          </div>
                      </td>
                   
                  </tr>
               </table>
            </td>
          </tr>
       </table>
    </form>
    
    <form action="student/managepaper.jsp"" method="post" name="myform">
	   <table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>全部投递论文列表</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="8%" height="30"><div align="center">序号</div></td>
					 <td width="34%" height="30"><div align="center">论文题目</div></td>
					 <td width="10%" height="30"><div align="center">论文类别</div></td>
					 <td width="18%" height="30"><div align="center">提交时间</div></td>
					 <td width="10%" height="30"><div align="center">派审状态</div></td>
				     <td width="10%" height="30"><div align="center">审核状态</div></td>
				     <td width="10%" height="30"><div align="center">提交次数</div></td>		
				     
				  </tr>
				  <%
			          //如果查询条件都为空则显示全部信息,否则根据条件显示
			         if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperallot==null||paperallot.equals("")||paperallot.equals("null"))){
			            flag=1;
                        
			       %>
			       <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            Paper obj = (Paper)list.get(i);                           
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
				      <td width="8%" height="30"><div align="center"><%=i+1 %></div></td>
					  <td width="34%" height="30"><div align="center"><a href="student/studentupnotes.jsp?studentno=<%=no %>&&papername=<%=URLEncoder.encode(obj.getPaperName(), "UTF-8") %>&&papertype=<%=obj.getPaperType() %>&&paperwriter=<%=URLEncoder.encode(obj.getPaperWriter(), "UTF-8") %>"><%=obj.getPaperName() %></a></div></td>
					  <td width="10%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					  <td width="18%" height="30"><div align="center"><%=obj.getPaperTime().substring(0,obj.getPaperTime().length()-2) %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getIsAllot().equals("0")?"未派审":"已派审" %></div></td>
				      <td width="10%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getPaperStatus())] %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getPaperCounter() %></div></td>
				    </tr>
				    <%
				        }
				       }else if((papername!=null||!papername.equals("")||!papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperallot==null||paperallot.equals("")||paperallot.equals("null"))){
			            paper=paperDao.findByName(papername);
			            if(paper==null){
			                flag = 0;
					        out.println("<script language=javascript>");
                            out.print("alert('查询结果为空，请输入正确的论文题目！');");
                            out.println("</script>");
			            }else{
					        flag = 0;
				    %>	
				    <tr bgcolor="#ffffcc" class="bjl">
				      <td width="8%" height="30"><div align="center"><%=1 %></div></td>
					  <td width="34%" height="30"><div align="center"><a href="student/studentupnotes.jsp?studentno=<%=no %>&&papername=<%=URLEncoder.encode(paper.getPaperName(), "UTF-8") %>&&papertype=<%=paper.getPaperType() %>&&paperwriter=<%=URLEncoder.encode(paper.getPaperWriter(), "UTF-8") %>"><%=paper.getPaperName() %></a></div></td>
					  <td width="10%" height="30"><div align="center"><%=paper.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					  <td width="18%" height="30"><div align="center"><%=paper.getPaperTime().substring(0,paper.getPaperTime().length()-2) %></div></td>
				      <td width="10%" height="30"><div align="center"><%=paper.getIsAllot().equals("0")?"未派审":"已派审" %></div></td>
				      <td width="10%" height="30"><div align="center"><%=s[Integer.parseInt(paper.getPaperStatus())] %></div></td>
				      <td width="10%" height="30"><div align="center"><%=paper.getPaperCounter() %></div></td>
				    </tr>
				    <%
				        }
				       }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype!=null||!papertype.equals("")||!papertype.equals("null"))&&(paperallot==null||paperallot.equals("")||paperallot.equals("null"))){
			            list=paperDao.findTypePaper(no,papertype);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       flag = 1;
					       allRow=list.size();
					       totalPage = (allRow % pageSize == 0) ? (allRow / pageSize) : (allRow / pageSize + 1);
					       strPage = request.getParameter("page");
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
				    <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            Paper obj = (Paper)list.get(i);                           
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
				      <td width="8%" height="30"><div align="center"><%=i+1 %></div></td>
					  <td width="34%" height="30"><div align="center"><a href="student/studentupnotes.jsp?studentno=<%=no %>&&papername=<%=URLEncoder.encode(obj.getPaperName(), "UTF-8") %>&&papertype=<%=obj.getPaperType() %>&&paperwriter=<%=URLEncoder.encode(obj.getPaperWriter(), "UTF-8") %>"><%=obj.getPaperName() %></a></div></td>
					  <td width="10%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					  <td width="18%" height="30"><div align="center"><%=obj.getPaperTime().substring(0,obj.getPaperTime().length()-2) %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getIsAllot().equals("0")?"未派审":"已派审" %></div></td>
				      <td width="10%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getPaperStatus())] %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getPaperCounter() %></div></td>
				    </tr>
				    <%
				        }
				       }
				      }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate!=null||!begindate.equals("")||!begindate.equals("null"))&&(enddate!=null||!enddate.equals("")||!enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperallot==null||paperallot.equals("")||paperallot.equals("null"))){
			            list=paperDao.findDatePaper(no,begindate,enddate);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       flag = 1;
					       allRow=list.size();
					       totalPage = (allRow % pageSize == 0) ? (allRow / pageSize) : (allRow / pageSize + 1);
					       strPage = request.getParameter("page");
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
				    <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            Paper obj = (Paper)list.get(i);                           
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
				      <td width="8%" height="30"><div align="center"><%=i+1 %></div></td>
					  <td width="34%" height="30"><div align="center"><a href="student/studentupnotes.jsp?studentno=<%=no %>&&papername=<%=URLEncoder.encode(obj.getPaperName(), "UTF-8") %>&&papertype=<%=obj.getPaperType() %>&&paperwriter=<%=URLEncoder.encode(obj.getPaperWriter(), "UTF-8") %>"><%=obj.getPaperName() %></a></div></td>
					  <td width="10%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					  <td width="18%" height="30"><div align="center"><%=obj.getPaperTime().substring(0,obj.getPaperTime().length()-2) %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getIsAllot().equals("0")?"未派审":"已派审" %></div></td>
				      <td width="10%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getPaperStatus())] %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getPaperCounter() %></div></td>
				    </tr>
				    <%
				        }
				       }
				      }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperallot!=null||!paperallot.equals("")||!paperallot.equals("null"))){
			            list=paperDao.findStuAllotPaper(no,paperallot);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       flag = 1;
					       allRow=list.size();
					       totalPage = (allRow % pageSize == 0) ? (allRow / pageSize) : (allRow / pageSize + 1);
					       strPage = request.getParameter("page");
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
				    <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            Paper obj = (Paper)list.get(i);                           
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
				      <td width="8%" height="30"><div align="center"><%=i+1 %></div></td>
					  <td width="34%" height="30"><div align="center"><a href="student/studentupnotes.jsp?studentno=<%=no %>&&papername=<%=URLEncoder.encode(obj.getPaperName(), "UTF-8") %>&&papertype=<%=obj.getPaperType() %>&&paperwriter=<%=URLEncoder.encode(obj.getPaperWriter(), "UTF-8") %>"><%=obj.getPaperName() %></a></div></td>
					  <td width="10%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					  <td width="18%" height="30"><div align="center"><%=obj.getPaperTime().substring(0,obj.getPaperTime().length()-2) %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getIsAllot().equals("0")?"未派审":"已派审" %></div></td>
				      <td width="10%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getPaperStatus())] %></div></td>
				      <td width="10%" height="30"><div align="center"><%=obj.getPaperCounter() %></div></td>
				    </tr>
				    <%
				           }
				         }
				       }else{
					     papername="";
					     begindate="";
					     enddate="";
					     papertype=""; 
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
           <a href="student/managepaper.jsp?page=<%="1" %>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperallot=<%=paperallot %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="student/managepaper.jsp?page=<%=currentPage-1%>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperallot=<%=paperallot %>">上一页</a>
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
           <a href="student/managepaper.jsp?page=<%=currentPage+1 %>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperallot<%=paperallot %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="student/managepaper.jsp?page=<%=totalPage %>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperallot=<%=paperallot %>">尾页 </a>&nbsp;&nbsp;
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

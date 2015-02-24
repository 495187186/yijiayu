<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.PaperCountDao"%>
<%@page import="com.test.tougao.entity.PaperCount"%>
<%@page import="com.test.tougao.dao.ReviewedDao"%>
<%@page import="com.test.tougao.entity.Reviewed"%>
<%@page import="com.test.tougao.dao.ReviewPaperDao"%>
<%@page import="com.test.tougao.entity.ReviewPaper"%>
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
    
    <title>统计已审论文结果</title>
    
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
       String paperwriter=request.getParameter("paperwriter");
       
       int reward=0;//共获审稿费
       PaperCountDao papercountDao=new PaperCountDao();
       PaperCount papercount=null;
       ReviewPaperDao reviewpaperDao = new ReviewPaperDao();
       List<ReviewPaper> reviewpaperList = reviewpaperDao.findReviewPaper(no);//查询所有分配的论文
       List<ReviewPaper> reviewedpaperList = reviewpaperDao.findReviewPaper(no,"1");//查询所有已评审的论文
       List<ReviewPaper> paperagreedList = reviewpaperDao.findAgreedPaper(no,"1");//查询所有已同意评审的论文
       for(int i=0;i<paperagreedList.size();i++){
           reward+=Integer.parseInt(paperagreedList.get(i).getPaperReward());//计算审稿费
       }
       ReviewedPaperDao reviewedpaperDao=new ReviewedPaperDao();
       int papercounter=0;//小计中同意评审数量
       int paperreward=0;//小计中获得的审稿费
       
       
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<PaperCount> list = null; 
       list = papercountDao.findExpPaper(no);
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
    <form action="expert/managepaper.jsp" method="post" name="searchpaper">
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
                      <td width="23%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <input name="papername" type="text"  id="papername" value="" size="27">
                          </div>
                      </td>
                      <td width="7%" height="30" bgcolor="#d5f1f2" ><div align="center" >评审日期</div></td>
                      <td width="21%" height="30" bgcolor="#d5f1f2" >
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
                      <td width="7%" height="30" bgcolor="#d5f1f2" ><div align="center" >通讯作者</div></td>
                      <td width="10%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <input name="paperwriter" type="text" id="paperwriter" value="" size="8">
                          </div>
                      </td>    
                      <td width="8%" height="30" bgcolor="#d5f1f2" >
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
    
    <form action="expert/managepaper.jsp" method="post" name="myform">
      <table width="96%"  border="2" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="30">
               <div align="center">
                  <FONT color=#000000><B>总计：</B></FONT>&nbsp;&nbsp;已收到论文：<FONT color=#ff0000><STRONG><%=reviewpaperList.size() %></STRONG></FONT> 篇，同意评审：<FONT color=#ff0000><STRONG><%=paperagreedList.size() %></STRONG></FONT> 篇，拒绝评审：<FONT color=#ff0000><STRONG><%=reviewpaperList.size()-paperagreedList.size() %></STRONG></FONT> 篇，已评审：<FONT color=#ff0000><STRONG><%=reviewedpaperList.size() %></STRONG></FONT> 篇，尚未评审：<FONT color=#ff0000><STRONG><%=reviewpaperList.size()-reviewedpaperList.size() %></STRONG></FONT> 篇，预计获审稿费：<FONT color=#ff0000><STRONG><%=reward %></STRONG></FONT> 元。
               </div>
            </td>
         </tr>
        
      </table>
      <br>
      <table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			   <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>统计已评审论文</STRONG></FONT></td>
                  </tr>
               </table>
			 </td>
		 </tr>			
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="6%" height="30"><div align="center">序号</div></td>
					 <td width="30%" height="30"><div align="center">论文名称</div></td>
					 <td width="8%" height="30"><div align="center">论文类别</div></td>
					 <td width="18%" height="30"><div align="center">评审时间</div></td>
				     <td width="8%" height="30"><div align="center">通讯作者</div></td>	
				     <td width="14%" height="30"><div align="center">审稿费(单位：元)</div></td>
				     <td width="6%" height="30"><div align="center">状态</div></td>
				     <td width="10%" height="30"><div align="center">操作</div></td>
				  </tr>
				  <%
			          //如果查询条件都为空则显示全部信息,否则根据条件显示
			         if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperwriter==null||paperwriter.equals("")||paperwriter.equals("null"))){
			             flag=1;
                         for(int j=0;j<list.size();j++){
                             if(list.get(j).getIsAgreed().equals("1")){
                                papercounter++;//计算小计的同意评审论文数
                                paperreward+=Integer.parseInt(list.get(j).getPaperReward());
                              }
                              
                          }
			      %>
			      <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            PaperCount obj = (PaperCount)list.get(i);                           
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
					 <td width="30%" height="30"><div align="center"><%=obj.getPaperName() %></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="18%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="8%" height="30"><div align="center"><%=obj.getPaperWriter() %></div></td>	
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
				     <td width="6%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="10%" height="30">
				        <div align="center">
				            <img src="images/web_12.gif" width="16" height="16">
				            <a href="expert/deletereviewnote.jsp?id=<%=obj.getPaperCountId() %>" onclick="return del()">删除</a>
				        </div>
				     </td>
				  </tr>
				  <%
				      }
				     }else if((papername!=null||!papername.equals("")||!papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperwriter==null||paperwriter.equals("")||paperwriter.equals("null"))){
			            list=papercountDao.findByPaperName(no,papername);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       for(int j=0;j<list.size();j++){
                             if(list.get(j).getIsAgreed().equals("1")){
                                papercounter++;//计算小计的同意评审论文数
                                paperreward+=Integer.parseInt(list.get(j).getPaperReward());
                              }
                              
                           }
					    
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
                            PaperCount obj = (PaperCount)list.get(i);                          
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
					 <td width="30%" height="30"><div align="center"><%=obj.getPaperName() %></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="18%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="8%" height="30"><div align="center"><%=obj.getPaperWriter() %></div></td>	
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
				     <td width="6%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="10%" height="30">
				        <div align="center">
				           
				            <img src="images/web_12.gif" width="16" height="16">
				            <a href="expert/deletereviewnote.jsp?id=<%=obj.getPaperCountId() %>" onclick="return del()">删除</a>
				        </div>
				     </td>
				  </tr>
				  <%
				        }
				      }
				     }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate!=null||!begindate.equals("")||!begindate.equals("null"))&&(enddate!=null||!enddate.equals("")||!enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperwriter==null||paperwriter.equals("")||paperwriter.equals("null"))){
			            list=papercountDao.findDatePaper(no,begindate,enddate);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       for(int j=0;j<list.size();j++){
                             if(list.get(j).getIsAgreed().equals("1")){
                                papercounter++;//计算小计的同意评审论文数
                                paperreward+=Integer.parseInt(list.get(j).getPaperReward());
                              }
                              
                           }
					    
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
                            PaperCount obj = (PaperCount)list.get(i);                          
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
					 <td width="30%" height="30"><div align="center"><%=obj.getPaperName() %></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="18%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="8%" height="30"><div align="center"><%=obj.getPaperWriter() %></div></td>	
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
				     <td width="6%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="10%" height="30">
				        <div align="center">
				           
				            <img src="images/web_12.gif" width="16" height="16">
				            <a href="expert/deletereviewnote.jsp?id=<%=obj.getPaperCountId() %>" onclick="return del()">删除</a>
				        </div>
				     </td>
				  </tr>
				  <%
				        }
				      }
				     }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype!=null||!papertype.equals("")||!papertype.equals("null"))&&(paperwriter==null||paperwriter.equals("")||paperwriter.equals("null"))){
			            list=papercountDao.findByPaperType(no,papertype);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       for(int j=0;j<list.size();j++){
                             if(list.get(j).getIsAgreed().equals("1")){
                                papercounter++;//计算小计的同意评审论文数
                                paperreward+=Integer.parseInt(list.get(j).getPaperReward());
                              }
                              
                           }
					       
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
                            PaperCount obj = (PaperCount)list.get(i);                          
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
					 <td width="30%" height="30"><div align="center"><%=obj.getPaperName() %></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="18%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="8%" height="30"><div align="center"><%=obj.getPaperWriter() %></div></td>	
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
				     <td width="6%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="10%" height="30">
				        <div align="center">
				            
				            <img src="images/web_12.gif" width="16" height="16">
				            <a href="expert/deletereviewnote.jsp?id=<%=obj.getPaperCountId() %>" onclick="return del()">删除</a>
				        </div>
				     </td>
				  </tr>
				  <%
				        }
				      }
				     }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(papertype==null||papertype.equals("")||papertype.equals("null"))&&(paperwriter!=null||!paperwriter.equals("")||!paperwriter.equals("null"))){
			            list=papercountDao.findByPaperWriter(no,paperwriter);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       for(int j=0;j<list.size();j++){
                             if(list.get(j).getIsAgreed().equals("1")){
                                papercounter++;//计算小计的同意评审论文数
                                paperreward+=Integer.parseInt(list.get(j).getPaperReward());
                              }
                              
                           }
					    
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
                            PaperCount obj = (PaperCount)list.get(i);                          
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
					 <td width="30%" height="30"><div align="center"><%=obj.getPaperName() %></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="18%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="8%" height="30"><div align="center"><%=obj.getPaperWriter() %></div></td>	
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
				     <td width="6%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="16%" height="30">
				        <div align="center">
				            
				            <img src="images/web_12.gif" width="16" height="16">
				            <a href="expert/deletereviewnote.jsp?id=<%=obj.getPaperCountId() %>" onclick="return del()">删除</a>
				        </div>
				     </td>
				  </tr>
				  <%
				        }
				      }
				     }else if((papername==null||papername.equals("")||papername.equals("null"))&&(begindate!=null||!begindate.equals("")||!begindate.equals("null"))&&(enddate!=null||!enddate.equals("")||!enddate.equals("null"))&&(papertype!=null||!papertype.equals("")||!papertype.equals("null"))&&(paperwriter==null||paperwriter.equals("")||paperwriter.equals("null"))){
			            list=papercountDao.findByDateType(no,begindate,enddate,papertype);
			            if(list.size()==0){
					       flag = 0;
					       out.println("<script language=javascript>");
                           out.print("alert('查询结果为空！');");
                           out.println("</script>");
					    }else{
					       for(int j=0;j<list.size();j++){
                             if(list.get(j).getIsAgreed().equals("1")){
                                papercounter++;//计算小计的同意评审论文数
                                paperreward+=Integer.parseInt(list.get(j).getPaperReward());
                              }
                              
                           }
					    
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
                            PaperCount obj = (PaperCount)list.get(i);                          
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
					 <td width="30%" height="30"><div align="center"><%=obj.getPaperName() %></div></td>
					 <td width="8%" height="30"><div align="center"><%=obj.getPaperType().equals("0")?"毕业论文":"期刊论文" %></div></td>
					 <td width="18%" height="30"><div align="center"><%=obj.getReviewTime().substring(0,obj.getReviewTime().length()-2) %></div></td>
				     <td width="8%" height="30"><div align="center"><%=obj.getPaperWriter() %></div></td>	
				     <td width="14%" height="30"><div align="center"><%=obj.getPaperReward() %></div></td>
				     <td width="6%" height="30"><div align="center"><%=obj.getIsAgreed().equals("1")?"同意":"拒绝" %></div></td>
				     <td width="10%" height="30">
				        
				            <img src="images/web_12.gif" width="16" height="16">
				            <a href="expert/deletereviewnote.jsp?id=<%=obj.getPaperCountId() %>" onclick="return del()">删除</a>
				        </div>
				     </td>
				  </tr>
				  <%
				        }
				      }
				     }else{
				        papername="";
				        begindate="";
				        enddate="";
				        papertype="";
				        paperwriter="";
				     
				     }
				  %>
			    </table>
			  </td>
			</tr>
		</table>
		<br>
        <table width="96%"  border="2" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
           <tr>
             <td height="30">
               <div align="center">
                  <FONT color=#000000><B>小计：</B></FONT>&nbsp;&nbsp;已评审论文：<FONT color=#ff0000><STRONG><%=list.size() %></STRONG></FONT> 篇，同意评审：<FONT color=#ff0000><STRONG><%=papercounter %></STRONG></FONT> 篇，拒绝评审：<FONT color=#ff0000><STRONG><%=list.size()-papercounter %></STRONG></FONT> 篇，已获审稿费：<FONT color=#ff0000><STRONG><%=paperreward %></STRONG></FONT> 元。
               </div>
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
           <a href="expert/managepaper.jsp?page=<%="1" %>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperwriter=<%=paperwriter%>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="expert/managepaper.jsp?page=<%=currentPage-1%>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperwriter=<%=paperwriter%>">上一页</a>
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
           <a href="expert/managepaper.jsp?page=<%=currentPage+1 %>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperwriter=<%=paperwriter%>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="expert/managepaper.jsp?page=<%=totalPage %>&&papername=<%=papername %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&papertype=<%=papertype %>&&paperwriter=<%=paperwriter%>">尾页 </a>&nbsp;&nbsp;
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
	 <SCRIPT type="text/javascript">
      function del()
      {
         if(confirm("是否确定删除本条记录？"))
         {
            return true;
         }
         return false;
      }
     </SCRIPT>
  </body>
</html>

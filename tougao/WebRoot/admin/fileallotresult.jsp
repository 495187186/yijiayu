<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.AllotFileDao"%>
<%@page import="com.test.tougao.entity.AllotFile"%>
<%@page import="com.test.tougao.dao.AdminFileDao"%>
<%@page import="com.test.tougao.entity.AdminFile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>材料指派结果</title>
    
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
  var form = document.searchfile;
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
       String docname = request.getParameter("docname");
       String uploader = request.getParameter("uploader");
       String status=request.getParameter("status");
       String expert=request.getParameter("expert");
       String begindate=request.getParameter("begindate");
       String enddate = request.getParameter("enddate");
    
       String []s={"未审批","审批中","审批完毕"};
       int fileallot=0;//已分配论文数量
       int filereward=0;//预付稿费
       AdminFileDao adminfileDao=new AdminFileDao();
       AdminFile adminfile=null;
       List<AdminFile> fileList = adminfileDao.findAll();//查询所有论文
       //遍历所有论文计算所有分配论文数量和已定稿费论文数量
       for(int i=0;i<fileList.size();i++){
           if(fileList.get(i).getIsAllot().equals("1")) fileallot++;
       }
       
       AllotFileDao allotfileDao=new AllotFileDao();
       List<AllotFile> allotfileList = allotfileDao.findAllotFile(); 
       //计算预付稿费
       for(int i=0;i<allotfileList.size();i++){
          filereward+=Integer.parseInt(allotfileList.get(i).getFileReward().trim());
       
       }
       
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<AllotFile> list=null;
       list = allotfileList;
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
    <form action="admin/fileallotresult.jsp" method="post" name="searchfile">
      <table width="98%" border="1" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>查询指派材料</STRONG></FONT></td>
                  </tr>
               </table>
            </td>
        </tr>
        <tr>
            <td>
               <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
                  <tr class="kbj">
                      <td width="6%" height="30" bgcolor="#d5f1f2" ><div align="center" >材料名称</div></td>
                      <td width="24%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                             <input name="docname" type="text"  id="docname" value="" size="28">
                          </div>
                      </td>
                      <td width="6%" height="30" bgcolor="#d5f1f2" ><div align="center" >上传作者</div></td>
                      <td width="8%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <input name="uploader" type="text"  id="uploader" value="" size="4">
                          </div>
                      </td>
                      
                      <td width="5%" height="30" bgcolor="#d5f1f2" ><div align="center" >专家</div></td>
                      <td width="8%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <input name="expert" type="text"  id="expert" value="" size="4">
                          </div>
                      </td>  
                      <td width="5%" height="30" bgcolor="#d5f1f2" ><div align="center" >状态</div></td>
                      <td width="7%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <select name="status">
                                   <option value="">请选择</option>
                                   <option value="0">未审批</option>
                                   <option value="1">审批中</option>
                                   <option value="2">审批完毕</option>
                              </select>
                          </div>
                      </td>         
                      <td width="6%" height="30" bgcolor="#d5f1f2" ><div align="center" >分配日期</div></td>
                      <td width="20%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                                                                              从<input onfocus="calendar()" name="begindate" type="text"  id="begindate" value="" size="7">到<input onfocus="calendar()" name="enddate" type="text"  id="enddate" value="" size="7">  
                          </div>
                      </td>   
                      <td width="5%" height="30" bgcolor="#d5f1f2" >
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
    
    <form action="admin/fileallotresult.jsp"" method="post" name="myform">
        <table width="98%" border="2" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
          <tr>
            <td height="30">
               <div align="center">
                  <FONT color=#000000><B>总计：</B></FONT>&nbsp;&nbsp;已收到材料：<FONT color=#ff0000><STRONG><%=fileList.size() %></STRONG></FONT> 篇，已指派：<FONT color=#ff0000><STRONG><%=fileallot %></STRONG></FONT> 篇，尚未指派：<FONT color=#ff0000><STRONG><%=fileList.size()-fileallot %></STRONG></FONT> 篇，预付审稿费：<FONT color=#ff0000><STRONG><%=filereward %></STRONG></FONT> 元。
               </div>
            </td>
         </tr>
        
       </table>
       <br>
	   <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>已分配材料列表</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="5%" height="30"><div align="center">序号</div></td>
					 <td width="30%" height="30"><div align="center">材料名称</div></td>
					 <td width="7%" height="30"><div align="center">上传作者</div></td>
					 <td width="8%" height="30"><div align="center">指派专家</div></td>
					 <td width="15%" height="30"><div align="center">上传时间</div></td>
					 <td width="15%" height="30"><div align="center">指派时间</div></td>
				     <td width="14%" height="30"><div align="center">审稿费(单位：篇/元)</div></td>		
				     <td width="6%" height="30"><div align="center">状态</div></td>	
				  </tr>
				  <%
			          //如果查询条件都为空则显示全部信息,否则根据条件显示
			         if((docname==null||docname.equals("")||docname.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(uploader==null||uploader.equals("")||uploader.equals("null"))&&(status==null||status.equals("")||status.equals("null"))&&(expert==null||expert.equals("")||expert.equals("null"))){
			             flag=1;
     
			      %>
			      <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            AllotFile obj = (AllotFile)list.get(i);                           
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
				        <td width="5%" height="30"><div align="center"><%=i+1 %></div></td>
					    <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					    <td width="7%" height="30"><div align="center"><%=obj.getFileUploader() %></div></td>
					    <td width="8%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				        <td width="14%" height="30"><div align="center"><%=obj.getFileReward() %></div></td>		
				        <td width="6%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>	
				     </tr>
				   <%
				       }
				      }else if((docname!=null||!docname.equals("")||!docname.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(uploader==null||uploader.equals("")||uploader.equals("null"))&&(status==null||status.equals("")||status.equals("null"))&&(expert==null||expert.equals("")||expert.equals("null"))){
				         list=allotfileDao.findByFileName(docname.trim());
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
                            AllotFile obj = (AllotFile)list.get(i);                           
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
				        <td width="5%" height="30"><div align="center"><%=i+1 %></div></td>
					    <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					    <td width="7%" height="30"><div align="center"><%=obj.getFileUploader() %></div></td>
					    <td width="8%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				        <td width="14%" height="30"><div align="center"><%=obj.getFileReward() %></div></td>		
				        <td width="6%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>	
				     </tr>
				  <%
				         }
				       }
				     }else if((docname==null||docname.equals("")||docname.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(uploader!=null||!uploader.equals("")||!uploader.equals("null"))&&(status==null||status.equals("")||status.equals("null"))&&(expert==null||expert.equals("")||expert.equals("null"))){
				         list=allotfileDao.findByFileUploader(uploader.trim());
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
                            AllotFile obj = (AllotFile)list.get(i);                           
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
				        <td width="5%" height="30"><div align="center"><%=i+1 %></div></td>
					    <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					    <td width="7%" height="30"><div align="center"><%=obj.getFileUploader() %></div></td>
					    <td width="8%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				        <td width="14%" height="30"><div align="center"><%=obj.getFileReward() %></div></td>		
				        <td width="6%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>	
				     </tr>
				  <%
				          }
				        }
				      }else if((docname==null||docname.equals("")||docname.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(uploader==null||uploader.equals("")||uploader.equals("null"))&&(status!=null||!status.equals("")||!status.equals("null"))&&(expert==null||expert.equals("")||expert.equals("null"))){
				         list=allotfileDao.findByFileStatus(status);
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
                            AllotFile obj = (AllotFile)list.get(i);                           
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
				        <td width="5%" height="30"><div align="center"><%=i+1 %></div></td>
					    <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					    <td width="7%" height="30"><div align="center"><%=obj.getFileUploader() %></div></td>
					    <td width="8%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				        <td width="14%" height="30"><div align="center"><%=obj.getFileReward() %></div></td>		
				        <td width="6%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>	
				     </tr>
				  <%
				         }
				       }
				     }else if((docname==null||docname.equals("")||docname.equals("null"))&&(begindate==null||begindate.equals("")||begindate.equals("null"))&&(enddate==null||enddate.equals("")||enddate.equals("null"))&&(uploader==null||uploader.equals("")||uploader.equals("null"))&&(status==null||status.equals("")||status.equals("null"))&&(expert!=null||!expert.equals("")||!expert.equals("null"))){
				         list=allotfileDao.findByExpertName(expert);
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
                            AllotFile obj = (AllotFile)list.get(i);                           
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
				        <td width="5%" height="30"><div align="center"><%=i+1 %></div></td>
					    <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					    <td width="7%" height="30"><div align="center"><%=obj.getFileUploader() %></div></td>
					    <td width="8%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				        <td width="14%" height="30"><div align="center"><%=obj.getFileReward() %></div></td>		
				        <td width="6%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>	
				     </tr>
				 <%
				        }
				      }
				    }else if((docname==null||docname.equals("")||docname.equals("null"))&&(begindate!=null||!begindate.equals("")||!begindate.equals("null"))&&(enddate!=null||!enddate.equals("")||!enddate.equals("null"))&&(uploader==null||uploader.equals("")||uploader.equals("null"))&&(status==null||status.equals("")||status.equals("null"))&&(expert==null||expert.equals("")||expert.equals("null"))){
				         list=allotfileDao.findByAllotTime(begindate,enddate);
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
                            AllotFile obj = (AllotFile)list.get(i);                           
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
				        <td width="5%" height="30"><div align="center"><%=i+1 %></div></td>
					    <td width="30%" height="30"><div align="center"><%=obj.getFileName() %></div></td>
					    <td width="7%" height="30"><div align="center"><%=obj.getFileUploader() %></div></td>
					    <td width="8%" height="30"><div align="center"><%=obj.getExpertName() %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getUploadTime().substring(0,obj.getUploadTime().length()-2) %></div></td>
					    <td width="15%" height="30"><div align="center"><%=obj.getAllotTime().substring(0,obj.getAllotTime().length()-2) %></div></td>
				        <td width="14%" height="30"><div align="center"><%=obj.getFileReward() %></div></td>		
				        <td width="6%" height="30"><div align="center"><%=s[Integer.parseInt(obj.getIsReviewed())] %></div></td>	
				     </tr>
				  <%
				          }
				        }
				      }else{
				          docname="";
				          begindate="";
				          enddate="";
				          uploader="";
				          status="";
				          expert="";

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
           <a href="admin/fileallotresult.jsp?page=<%="1" %>&&docname=<%=docname %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&uploader=<%=uploader %>&&status=<%=status %>&&expert=<%=expert %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="admin/fileallotresult.jsp?page=<%=currentPage-1%>&&docname=<%=docname %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&uploader=<%=uploader %>&&status=<%=status %>&&expert=<%=expert %>">上一页</a>
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
           <a href="admin/fileallotresult.jsp?page=<%=currentPage+1 %>&&docname=<%=docname %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&uploader=<%=uploader %>&&status=<%=status %>&&expert=<%=expert %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="admin/fileallotresult.jsp?page=<%=totalPage %>&&docname=<%=docname %>&&begindate=<%=begindate %>&&enddate=<%=enddate %>&&uploader=<%=uploader %>&&status=<%=status %>&&expert=<%=expert %>">尾页 </a>&nbsp;&nbsp;
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

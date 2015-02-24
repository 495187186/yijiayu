<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.UserDao"%>
<%@page import="com.test.tougao.entity.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理登陆用户</title>
    
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
      
       String account = request.getParameter("account");
       String username=request.getParameter("username");
       String role = request.getParameter("role");
       String active = request.getParameter("active");
       String []s={"","管理员","专家","学生"};
       UserDao userDao = new UserDao();
       User user = new User();
       int flag = 1;//是否显示首页、尾页等导航栏信息，单个查询时不显示
       int allRow;         //总记录数 
       int totalPage;        //总页数 
       int currentPage = 1;    //当前页 
       int pageSize = 10;        //每页记录数 
       int start;//当前显示页中第一条记录的位置
       String strPage;
       List<User> list = null; 
       list = userDao.findAllUser();
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
  
    <form action="admin/manageuser.jsp" method="post" name="searchuser">
      <table width="96%"  border="1" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>搜索用户</STRONG></FONT></td>
                  </tr>
               </table>
            </td>
        </tr>
        <tr>
            <td>
               <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
                  <tr class="kbj">
                      <td width="10%" height="30" bgcolor="#d5f1f2" ><div align="center" >账户</div></td>
                      <td width="12%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                              <input name="account" type="text"  id="account" value="" size="9">
                          </div>
                      </td>
                      <td width="10%" height="30" bgcolor="#d5f1f2" ><div align="center" >姓名</div></td>
                      <td width="12%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                               <input name="username" type="text"  id="username" value="" size="9"> 
                          </div>
                      </td>    
                      <td width="10%" height="30" bgcolor="#d5f1f2" ><div align="center" >角色</div></td>
                      <td width="12%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                             <select name="role" id="role">
                                <option value="" selected>---请选择---</option>
                                <option value="1">管理员 </option>
                                <option value="2">评审专家</option>
       					        <option value="3">学生 </option>
 
                             </select>
                          </div>
                      </td>     
                      <td width="10%" height="30" bgcolor="#d5f1f2" ><div align="center" >状态</div></td>
                      <td width="12%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                             <select name="active" id="active">
                                 <option value="" selected>--请选择-- </option>
                                 <option value="1">正常</option>
                       	         <option value="0">禁用</option>
                             </select>
                          </div>
                      </td>     
                      <td width="12%" height="30" bgcolor="#d5f1f2" >
                          <div align="center">
                               <input type="submit" name="search" value="搜索" >
                          </div>
                      </td>
                   
                  </tr>
               </table>
            </td>
          </tr>
       </table>
    </form>
    
    <form action="admin/manageuser.jsp" method="post" name="myform">
	   <table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#3399cc">
		  <tr>
			 <td height="25">
			    <table width="100%" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
				   <tr>
                      <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>全体用户列表</STRONG></FONT></td>
                  </tr>
			   </table>	
			 </td>
		 </tr>	
		 <tr>
			<td width="100%" align="center">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
				  <tr bgcolor="#d5f1f2" class="bjl">
				     <td width="12%" height="30"><div align="center">序号</div></td>
					 <td width="14%" height="30"><div align="center">账户</div></td>
					 <td width="14%" height="30"><div align="center">姓名</div></td>
					 <td width="12%" height="30"><div align="center">角色</div></td>
				     <td width="12%" height="30"><div align="center">状态</div></td>
				     <td width="12%" height="30"><div align="center">操作</div></td>		
				     <td width="24%" height="30">
				        <div align="center">
				            
                            <a href="admin/closeandopen.jsp?result=<%="0" %>&&role=<%="2" %>" onclick="if(confirm('您确定要禁用全部专家吗?')==false)return false;" >禁用全部专家</a>/
                            <a href="admin/closeandopen.jsp?result=<%="0" %>&&role=<%="3" %>" onclick="if(confirm('您确定要禁用全部学生吗?')==false)return false;" >禁用全部学生</a><br>
                            
                            <a href="admin/closeandopen.jsp?result=<%="1" %>&&role=<%="2" %>" onclick="if(confirm('您确定要开放全部专家吗?')==false)return false;" >开放全部专家</a>/
                            <a href="admin/closeandopen.jsp?result=<%="1" %>&&role=<%="3" %>" onclick="if(confirm('您确定要开放全部学生吗?')==false)return false;" >开放全部学生</a>

				        </div>
				     </td>		
				  </tr>
				   <%
			          //如果查询条件都为空则显示全部信息,否则根据条件显示
			         if((account==null||account.equals("")||account.equals("null"))&&(username==null||username.equals("")||username.equals("null"))&&(role==null||role.equals("")||role.equals("null"))&&(active==null||active.equals("")||active.equals("null"))){
			            flag=1;
                        
			       %>
			       <%
				      for(int i=start;i<((start+pageSize)>allRow?allRow:(start+pageSize));i++)
                         {
                            User obj = (User)list.get(i);                           
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
				            <td width="12%" height="23"><div align="center"><%=i+1 %></div></td>
						    <td width="14%" height="23"><div align="center"><%=obj.getUser_account() %></div></td>
					        <td width="14%" height="23"><div align="center"><%=obj.getUser_name() %></div></td>
					        <td width="12%" height="23"><div align="center"><%=s[Integer.parseInt(obj.getUser_role())] %></div></td>
					        <td width="12%" height="23"><div align="center"><%=obj.getUser_active().equals("1")?"正常":"禁用" %></div></td>
						    <td width="12%" height="30">
						       <div align="center">
						       <%
						          if(obj.getUser_active().equals("0")){
						       %>
						           <img height="15" width="15" src="images/smile.gif"> 
				                   <a href="admin/closeandopen.jsp?result=<%="1" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要开放该账户吗?')==false)return false;">开放</a> 
						       <%
						           }else{
						             
						       %>
						           <img height="15" width="15" src="images/sad.gif">
						           <a href="admin/closeandopen.jsp?result=<%="0" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要禁用该账户吗?')==false)return false;">禁用</a>  
						       <%
						           } 
						       %>
						       </div>
						    </td>	
						    <td width="24%" height="23">
						      <div align="center">
						        <img src="images/user_info.gif" width="20" height="16">
						        <%
						           if(obj.getUser_role().equals("1")){ 
						        %>
						        <a href="admin/admininfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else if(obj.getUser_role().equals("2")){
						        %>
						        <a href="admin/expertinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else{
						        %>
						        <a href="admin/studentinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }
						        %>
						        &nbsp;&nbsp;
						        <img src="images/user_edit.png" width="16" height="16">
						        <a href="javascript:window.open('updateuser.jsp?UserId=<%=obj.getUser_id() %>', 'newwindow', 'height=280, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');void(0);">
						                       修改</a>
						        &nbsp;&nbsp;
						        <img src="images/user_delete.png" width="16" height="16">
						        <a href="admin/deleteloginuser.jsp?UserId=<%=obj.getUser_id() %>&&UserNo=<%=obj.getUser_account() %>&&UserType=<%=obj.getUser_role() %>" onclick="return del()">
						                     删除</a>
						      </div>
						    </td>	
						 </tr>
						 <%
					      }
					     }
					     else if((account!=null||!account.equals("")||!account.equals("null"))&&(username==null||username.equals("")||username.equals("null"))&&(role==null||role.equals("")||role.equals("null"))&&(active==null||active.equals("")||active.equals("null"))) {
					     user = userDao.findByNo(account);
					     
					     if(user==null){
					        flag = 0;
					        out.println("<script language=javascript>");
                            out.print("alert('查询结果为空，请输入正确的账户！');");
                            out.println("</script>");
					     }else{
					        flag = 0;
					     %>
                            <tr bgcolor="#ffffcc" class="bjl">
					           <td width="12%" height="23"><div align="center"><%=1 %></div></td>
						       <td width="14%" height="23"><div align="center"><%=user.getUser_account() %></div></td>
					           <td width="14%" height="23"><div align="center"><%=user.getUser_name() %></div></td>
					           <td width="12%" height="23"><div align="center"><%=s[Integer.parseInt(user.getUser_role())] %></div></td>
					           <td width="12%" height="23"><div align="center"><%=user.getUser_active().equals("1")?"正常":"禁用" %></div></td>
						       <td width="12%" height="30">
						          <div align="center">
						          <%
						             if(user.getUser_active().equals("0")){
						          %>
						             <img height="15" width="15" src="images/smile.gif"> 
				                     <a href="admin/closeandopen.jsp?result=<%="1" %>&&UserId=<%=user.getUser_id() %>" onclick="if(confirm('您确定要开放该账户吗?')==false)return false;">开放</a> 
						          <%
						             }else{
						             
						          %>
						             <img height="15" width="15" src="images/sad.gif">
						             <a href="admin/closeandopen.jsp?result=<%="0" %>&&UserId=<%=user.getUser_id() %>" onclick="if(confirm('您确定要禁用该账户吗?')==false)return false;">禁用</a>  
						          <%
						             } 
						          %>
						        </div>
						      </td>	
						      <td width="24%" height="23">
						        <div align="center">
						           <img src="images/user_info.gif" width="20" height="16">
						           <%
						              if(user.getUser_role().equals("1")){ 
						           %>
						           <a href="admin/admininfo.jsp?UserNo=<%=user.getUser_account() %>">
						                               查看</a>
						           <%
						              }else if(user.getUser_role().equals("2")){
						           %>
						           <a href="admin/expertinfo.jsp?UserNo=<%=user.getUser_account() %>">
						                              查看</a>
						           <%
						              }else{
						           %>
						           <a href="admin/studentinfo.jsp?UserNo=<%=user.getUser_account() %>">
						                               查看</a>
						           <%
						              }
						           %>
						           &nbsp;&nbsp;
						           <img src="images/user_edit.png" width="16" height="16">
						           <a href="javascript:window.open('updateuser.jsp?UserId=<%=user.getUser_id() %>', 'newwindow', 'height=280, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');void(0);">
						                                  修改</a>
						             &nbsp;&nbsp;
						           <img src="images/user_delete.png" width="16" height="16">
						           <a href="admin/deleteloginuser.jsp?UserId=<%=user.getUser_id() %>&&UserNo=<%=user.getUser_account() %>&&UserType=<%=user.getUser_role() %>" onclick="return del()">
						                                  删除</a>
						       </div>
						    </td>	
						 </tr>
						 <%
					       }  
					     }
					      else if((username!=null||!username.equals("")||!username.equals("null"))&&(account==null||account.equals("")||account.equals("null"))&&(role==null||role.equals("")||role.equals("null"))&&(active==null||active.equals("")||active.equals("null"))){
					      list = userDao.findByName(username.trim());
					      if(list.size()==0){
					         flag = 0;
					         out.println("<script language=javascript>");
                             out.print("alert('查询结果为空，请输入正确的姓名！');");
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
                                  User obj = (User)list.get(i);                           
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
				             <td width="12%" height="23"><div align="center"><%=i+1 %></div></td>
						     <td width="14%" height="23"><div align="center"><%=obj.getUser_account() %></div></td>
					         <td width="14%" height="23"><div align="center"><%=obj.getUser_name() %></div></td>
					         <td width="12%" height="23"><div align="center"><%=s[Integer.parseInt(obj.getUser_role())] %></div></td>
					         <td width="12%" height="23"><div align="center"><%=obj.getUser_active().equals("1")?"正常":"禁用" %></div></td>
						     <td width="12%" height="30">
						       <div align="center">
						       <%
						          if(obj.getUser_active().equals("0")){
						       %>
						           <img height="15" width="15" src="images/smile.gif"> 
				                   <a href="admin/closeandopen.jsp?result=<%="1" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要开放该账户吗?')==false)return false;">开放</a> 
						       <%
						           }else{
						             
						       %>
						           <img height="15" width="15" src="images/sad.gif">
						           <a href="admin/closeandopen.jsp?result=<%="0" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要禁用该账户吗?')==false)return false;">禁用</a>  
						       <%
						           } 
						       %>
						       </div>
						     </td>	
						     <td width="24%" height="23">
						      <div align="center">
						        <img src="images/user_info.gif" width="20" height="16">
						        <%
						           if(obj.getUser_role().equals("1")){ 
						        %>
						        <a href="admin/admininfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else if(obj.getUser_role().equals("2")){
						        %>
						        <a href="admin/expertinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else{
						        %>
						        <a href="admin/studentinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }
						        %>
						        &nbsp;&nbsp;
						        <img src="images/user_edit.png" width="16" height="16">
						        <a href="javascript:window.open('updateuser.jsp?UserId=<%=obj.getUser_id() %>', 'newwindow', 'height=280, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');void(0);">
						                       修改</a>
						        &nbsp;&nbsp;
						        <img src="images/user_delete.png" width="16" height="16">
						        <a href="admin/deleteloginuser.jsp?UserId=<%=obj.getUser_id() %>&&UserNo=<%=obj.getUser_account() %>&&UserType=<%=obj.getUser_role() %>" onclick="return del()">
						                     删除</a>
						      </div>
						    </td>	
						 </tr>
					   <%
					     }
					   }
					  }
					  else if((username==null||username.equals("")||username.equals("null"))&&(account==null||account.equals("")||account.equals("null"))&&(role!=null||!role.equals("")||!role.equals("null"))&&(active==null||active.equals("")||active.equals("null"))){
					  list = userDao.findByRole(role);
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
                                  User obj = (User)list.get(i);                           
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
				             <td width="12%" height="23"><div align="center"><%=i+1 %></div></td>
						     <td width="14%" height="23"><div align="center"><%=obj.getUser_account() %></div></td>
					         <td width="14%" height="23"><div align="center"><%=obj.getUser_name() %></div></td>
					         <td width="12%" height="23"><div align="center"><%=s[Integer.parseInt(obj.getUser_role())] %></div></td>
					         <td width="12%" height="23"><div align="center"><%=obj.getUser_active().equals("1")?"正常":"禁用" %></div></td>
						     <td width="12%" height="30">
						       <div align="center">
						       <%
						          if(obj.getUser_active().equals("0")){
						       %>
						           <img height="15" width="15" src="images/smile.gif"> 
				                   <a href="admin/closeandopen.jsp?result=<%="1" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要开放该账户吗?')==false)return false;">开放</a> 
						       <%
						           }else{
						             
						       %>
						           <img height="15" width="15" src="images/sad.gif">
						           <a href="admin/closeandopen.jsp?result=<%="0" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要禁用该账户吗?')==false)return false;">禁用</a>  
						       <%
						           } 
						       %>
						       </div>
						     </td>	
						     <td width="24%" height="23">
						      <div align="center">
						        <img src="images/user_info.gif" width="20" height="16">
						        <%
						           if(obj.getUser_role().equals("1")){ 
						        %>
						        <a href="admin/admininfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else if(obj.getUser_role().equals("2")){
						        %>
						        <a href="admin/expertinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else{
						        %>
						        <a href="admin/studentinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }
						        %>
						        &nbsp;&nbsp;
						        <img src="images/user_edit.png" width="16" height="16">
						        <a href="javascript:window.open('updateuser.jsp?UserId=<%=obj.getUser_id() %>', 'newwindow', 'height=280, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');void(0);">
						                       修改</a>
						        &nbsp;&nbsp;
						        <img src="images/user_delete.png" width="16" height="16">
						        <a href="admin/deleteloginuser.jsp?UserId=<%=obj.getUser_id() %>&&UserNo=<%=obj.getUser_account() %>&&UserType=<%=obj.getUser_role() %>" onclick="return del()">
						                     删除</a>
						      </div>
						     </td>	
						 </tr>
					   <%
					     }
					   }
					  }
					  else if((username==null||username.equals("")||username.equals("null"))&&(account==null||account.equals("")||account.equals("null"))&&(role==null||role.equals("")||role.equals("null"))&&(active!=null||!active.equals("")||!active.equals("null"))){
					  list = userDao.findByActive(active);
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
                                  User obj = (User)list.get(i);                           
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
				             <td width="12%" height="23"><div align="center"><%=i+1 %></div></td>
						     <td width="14%" height="23"><div align="center"><%=obj.getUser_account() %></div></td>
					         <td width="14%" height="23"><div align="center"><%=obj.getUser_name() %></div></td>
					         <td width="12%" height="23"><div align="center"><%=s[Integer.parseInt(obj.getUser_role())] %></div></td>
					         <td width="12%" height="23"><div align="center"><%=obj.getUser_active().equals("1")?"正常":"禁用" %></div></td>
						     <td width="12%" height="30">
						       <div align="center">
						       <%
						          if(obj.getUser_active().equals("0")){
						       %>
						           <img height="15" width="15" src="images/smile.gif"> 
				                   <a href="admin/closeandopen.jsp?result=<%="1" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要开放该账户吗?')==false)return false;">开放</a> 
						       <%
						           }else{
						             
						       %>
						           <img height="15" width="15" src="images/sad.gif">
						           <a href="admin/closeandopen.jsp?result=<%="0" %>&&UserId=<%=obj.getUser_id() %>" onclick="if(confirm('您确定要禁用该账户吗?')==false)return false;">禁用</a>  
						       <%
						           } 
						       %>
						       </div>
						     </td>	
						     <td width="24%" height="23">
						      <div align="center">
						        <img src="images/user_info.gif" width="20" height="16">
						        <%
						           if(obj.getUser_role().equals("1")){ 
						        %>
						        <a href="admin/admininfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else if(obj.getUser_role().equals("2")){
						        %>
						        <a href="admin/expertinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }else{
						        %>
						        <a href="admin/studentinfo.jsp?UserNo=<%=obj.getUser_account() %>">
						                     查看</a>
						        <%
						           }
						        %>
						        &nbsp;&nbsp;
						        <img src="images/user_edit.png" width="16" height="16">
						        <a href="javascript:window.open('updateuser.jsp?UserId=<%=obj.getUser_id() %>', 'newwindow', 'height=280, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');void(0);">
						                       修改</a>
						        &nbsp;&nbsp;
						        <img src="images/user_delete.png" width="16" height="16">
						        <a href="admin/deleteloginuser.jsp?UserId=<%=obj.getUser_id() %>&&UserNo=<%=obj.getUser_account() %>&&UserType=<%=obj.getUser_role() %>" onclick="return del()">
						                     删除</a>
						      </div>
						    </td>	
						 </tr>
					   <%
					      }
					   
					     }
					    }
					    else{
					     account="";
					     username="";
					     role="";
					     active=""; 
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
           <a href="admin/manageuser.jsp?page=<%="1" %>&&account=<%=account %>&&username=<%=username %>&&role=<%=role %>&&active=<%=active %>">首页 </a>&nbsp;&nbsp;
        <%
           if(currentPage>1){
        %>
           <a href="admin/manageuser.jsp?page=<%=currentPage-1%>&&account=<%=account %>&&username=<%=username %>&&role=<%=role %>&&active=<%=active %>">上一页</a>
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
           <a href="admin/manageuser.jsp?page=<%=currentPage+1 %>&&account=<%=account %>&&username=<%=username %>&&role=<%=role %>&&active=<%=active %>">下一页 </a>
        <%
           }else{
        %>
                             下一页 
        <%
          }
        %>
          &nbsp;&nbsp;
          <a href="admin/manageuser.jsp?page=<%=totalPage %>&&account=<%=account %>&&username=<%=username %>&&role=<%=role %>&&active=<%=active %>">尾页 </a>&nbsp;&nbsp;
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
     if(confirm("该账户的信息也将删除，您确定要删除？"))
     {
       return true;
     }
     return false;
   }
  </SCRIPT>
  
  </body>
</html>

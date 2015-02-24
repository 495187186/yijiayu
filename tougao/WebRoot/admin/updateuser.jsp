<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>修改用户登录信息</title>
    
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
     td{font-size:10pt}
    -->
    </style>
    
<script language="javascript" >
function checkForm() 
{
  var form1 = document.updateuser;
  var account=form1.account.value;
  var name=form1.username.value;
  var password=form1.password.value;
  if (account==""&&name==""&&password=="") {
   window.alert("输入信息不能为空 ！");
   document.updateuser.account.focus(); 
   return false;
  }
  else if(account==""){
   window.alert("账户不能为空 ！");
   return false;
  }
  else if(name==""){
   window.alert("姓名不能为空 ！");
   return false;
  }
  else if(password==""){
   window.alert("密码不能为空 ！");
   return false;
  }
  
  if(password.length<6){
   window.alert("密码至少为6位！");
   return false;
  }
  return true;
}
</script>

<script language="javascript" >
function checkSelect(m,n)
{
  var form1 = document.updateuser;
  var s1 = form1.role;
  var s2 = form1.active;
  for(var i=0;i<s1.options.length;i++){
    if(s1.options[i].value==m){
     s1.options[i].selected="selected";
     break;
    }
   }
  for(var i=0;i<s2.options.length;i++){
    if(s2.options[i].value==n){
     s2.options[i].selected="selected";
     break;
    }
   }
}
</script>

<script language="javascript" >
function resetPassword()
{
  if(confirm("是否确定重置密码？"))
     {
       document.updateuser.password.value="111111";
       return true;
     }
     return false;
}

</script>
  
  </head>
  
  <%
    User user=null;
  	String id= request.getParameter("UserId") ;//session 获取的数据是object对象，必须强制转换成String类型
  	UserDao dao=new UserDao();
  	user=dao.findById(id);
  	
   %>
  <body onload="checkSelect(<%=user.getUser_role() %>,<%=user.getUser_active() %>);">
    <form name="updateuser" action="UpdateUserServlet?UserId=<%=user.getUser_id() %>" method="post" >
      <br>
      <table width="90%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" background="images/selectbar.gif" bgcolor="#3399cc">
                  <tr>
                     <td width="100%" align="center" background="images/td_bg.gif"><font size="4">修改登录信息</font></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="24%" height="25" bgcolor="#d5f1f2"><div align="center">&nbsp;账户</div></td>
                     <td width="76%" bgcolor="#d5f1f2">　 
                       <input type="text" name="account" value="<%=user.getUser_account() %>" size="25" readonly="readonly">
                       <FONT color=#ff0000>*</FONT>&nbsp;   
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="24%" height="25" bgcolor="#d5f1f2"><div align="center">&nbsp;姓名</div></td>
                     <td width="76%" bgcolor="#d5f1f2">　 
                       <input type="text" name="username" value="<%=user.getUser_name() %>" size="25" readonly="readonly"/>
                       <FONT color=#ff0000>*</FONT>&nbsp;  
                     </td>
                  </tr>
               </table>
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="24%" height="25" bgcolor="#d5f1f2"><div align="center">&nbsp;密码</div></td>
                     <td width="76%" bgcolor="#d5f1f2">　 
                         <input type="password" name="password" value="<%=user.getUser_password() %>"  size="20" readonly="readonly">
                         <input type="button" value="重置" onclick="return resetPassword()">
                     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="24%" height="25" bgcolor="#d5f1f2"><div align="center">&nbsp;角色</div></td>
                     <td width="76%" bgcolor="#d5f1f2">　 
                        <select name="role" id="role">
                           <option value="1">管理员</option>
                           <option value="2">专家</option>
                           <option value="3">学生</option>
                           
                        </select>
              			
				     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                      <td width="24%" height="25" bgcolor="#d5f1f2"><div align="center">&nbsp;状态</div></td>
                      <td width="76%" bgcolor="#d5f1f2">　
                         <select name="active" id="active">
                            <option value="0">禁用</option>
                            <option value="1">激活</option>
                         </select>
   		  			   
   		  		     </td>
                  </tr>
             </table>
                  
           <br>
           <div align="center">
                  <input type="submit" name="Submit" value="确定" onClick="return checkForm()">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="reset" name="Reset" value="取消">            
           </div>
       </td>
     </tr>         
   </table>
  </form>
  </body>
</html>

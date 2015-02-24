<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
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
     td{font-size:11pt}
    -->
    </style>
    
<script language="javascript" >
function checkForm() 
{
  var form1 = document.modifypassword;
  var oldpwd=form1.old_password.value;
  var newpwd=form1.new_password.value;
  var repwd=form1.re_password.value;
  if (oldpwd==""&&newpwd==""&&repwd=="") {
   window.alert("输入信息不能为空 ！");
   document.modifypassword.old_password.focus(); 
   return false;
  }
  else if(oldpwd==""){
   window.alert("原密码不能为空 ！");
   return false;
  }
  else if(newpwd==""){
   window.alert("新密码不能为空 ！");
   return false;
  }
  else if(repwd==""){
   window.alert("确认密码不能为空 ！");
   return false;
  }
  return true;
}
</script>

  </head>
  
  <body>
    <form  name="modifypassword"  action="ModifyAdminPwdServlet" method="post" >
      <br>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>修改登录密码</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;账户</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                       <input type="text" name="account" value="<%=session.getAttribute("account") %>" size="27" readonly="readonly"/>
                       &nbsp; （注:此项不可修改）
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;原密码</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                       <input type="password" name="old_password" size="28" >
                       <FONT color=#ff0000>*</FONT>
                     </td>
                  </tr>
               </table>
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;新密码</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                         <input type="password" name="new_password" size="28" > 
                         <FONT color=#ff0000>*</FONT> &nbsp;（注:新密码不能少于6位）
                     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;确认新密码</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　  
              			 <input type="password" name="re_password" size="28" >
              			 <FONT color=#ff0000>*</FONT>
				     </td>
                  </tr>
                  
             </table>
                  
           <br>
           <div align="center"><input type="submit" value="提交"  name="submit" onClick="return checkForm()" >
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="reset" value="重置" name="reset">
           </div>
       </td>
     </tr>          
   </table>
  </form>
  </body>
</html>

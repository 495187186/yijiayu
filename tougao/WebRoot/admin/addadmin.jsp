<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加超级管理员</title>
    
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
	          FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #d6dff7
            }
          A {
	          FONT: 12px 宋体; COLOR: #0000ff; TEXT-DECORATION: none
            }
    A:hover {
	          TEXT-DECORATION:none; COLOR: #808000 
            }
         TD {
	          FONT-SIZE: 11pt; LINE-HEIGHT: 15px; FONT-FAMILY: 宋体
            }
        TH  {
	          FONT-WEIGHT: bold; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(images/admin_bg_1.gif); COLOR: white; BACKGROUND-COLOR:#4455aa
	        }
	        
	
	</STYLE>
<script language="javascript" >
function checkForm() 
{
  var form1 = document.addadmin;
  var no=form1.no.value;
  var name=form1.name.value;
  var mobile=form1.mobile.value;
  var email=form1.email.value;
  
  if(no==""){
   window.alert("工号不能为空 ！");
   form1.no.focus(); 
   return false;
  }
  if(name==""){
   window.alert("姓名不能为空 ！");
   form1.name.focus(); 
   return false;
  }
  if(email!=""){
     if(form1.email.value.indexOf("@")==-1 ||(form1.email.value.indexOf(".")==-1))
		{
			alert("请填写正确的E-mali地址！");
		    form1.email.value="";
			form1.email.focus();
			return false;
		}
  }
  
  if(mobile!=""){
     if(mobile.length!=11)
       {
         window.alert("请输入11位的手机号码！");
         form1.mobile.value="";
	     form1.mobile.focus();
         return false;
       }
    if(!mobile.match("^[0-9]*$"))
       {
         alert('电话号码请输入数字！');
         form1.mobile.value="";
		 form1.mobile.focus();
         return false;
       }
  }
  
  return true;
}
</script>
	

  </head>
  
  <body>
     <br/>
     <form name="addadmin"  action="AddAdminServlet" method="post">
     <table cellSpacing=1 cellPadding=3 width="60%" align="center" bgColor=#6298e1 border=0>
        <tr>
            <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>填写个人资料</STRONG></FONT></td>
        </tr>
        <tr>
           <td bgcolor="#fffff">
               <br/>
               <table align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">工号：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%"> 
                           &nbsp;<input type="text" name="no" value="" size="27" />
                           <FONT color=#ff0000>*</FONT>（注:带有<FONT color=#ff0000>*</FONT> 标记的项必须填写）
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="name" value="" size="27" />
                          <FONT color=#ff0000>*</FONT>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">性别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" value="1" checked> 男&nbsp;&nbsp;
                           <input type="radio" name="gender" value="0"> 女
                       </td>
                   </tr>
                   
               </table>
               <br/>
               <table  align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">手机号码：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="mobile" value="" size="27"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">电子邮箱：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="email" value="" size="27"/>
                          
                       </td>
                   </tr>
                  
               </table>
               <br>
               <div align="center"><input type="submit" value="确定"  name="submit" onclick="return checkForm()">
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="reset" value="取消" name="reset" onclick="javascript:history.back(-1);">
               </div>
           </td>
        </tr>
     </table>
     </form>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
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
	          FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
            }
         TD {
	          FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
            }
    </STYLE>
    
<script type="text/javascript">
   function reLoadImage(){
      var dt=new Date();
	  document.getElementById("code").src="image.jsp?dt="+dt;
   } 
   
   function quitlogin()
   {
      var form=document.form1;
      form.reset();
      form.txtName.focus();
      return false;
   }
   
   function checklogin()
   {
      var loginuser = document.form1;
      if(loginuser.txtName.value==""){
         alert('请输入登录名！');
         return false;
      }
      if(loginuser.txtPwd.value==""){
         alert('请输入密码！');
         return false;
      }
      if(loginuser.txtCode.value==""){
         alert('请输入验证码！');
         return false;
      }
      loginuser.submit();
      return true;
   }
   
   function butOnClick() {
     if (window.event.keyCode==13) {
          var button = document.getElementById("login"); //login为button按钮的id
          button.click();
          return false;//为了防止浏览器捕捉到用户按下回车键而进行其他操作
       }
   }
   
</script>


  </head>
  
  <body>
    
    <FORM id=form1 name=form1 action="LoginServlet" method=post >

      
<DIV>
 <TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
   <TBODY>
      <TR>
        <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" border=0></TD></TR>
      <TR>
        <TD background=images/login_2.gif height=300>
           <TABLE height=300 cellPadding=0 width=900 border=0>
             <TBODY>
               <TR>
                  <TD colSpan=2 height=35></TD>
               </TR>
               <TR>
                   <TD width=360></TD>
                   <TD>
                       <TABLE cellSpacing=0 cellPadding=2 border=0>
                         <TBODY>
                           <TR>
                              <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                              <TD style="HEIGHT: 28px" width=150><INPUT id=txtName style="WIDTH: 130px" name=txtName onkeydown="javascript:butOnClick();"></TD>
                              <TD style="HEIGHT: 28px" width=370></TD>
                           </TR>
                           <TR>
                              <TD style="HEIGHT: 28px">登录密码：</TD>
                              <TD style="HEIGHT: 28px"><INPUT id=txtPwd style="WIDTH: 130px" type=password name=txtPwd onkeydown="javascript:butOnClick();"></TD>
                              <TD style="HEIGHT: 28px"></TD>
                           </TR>
                           <TR>
                              <TD style="HEIGHT: 28px">验证码：</TD>
                              <TD style="HEIGHT: 28px"><INPUT id=txtCode style="WIDTH: 130px" name=txtCode onkeydown="javascript:butOnClick();"></TD>
                              <TD style="HEIGHT: 28px"><img id="code" border=0 src="image.jsp">&nbsp;<a href="javascript:reLoadImage();"><font size=4 color="red">看不清?换一个</font></a></TD>
                           </TR>
                           <TR>
                              <TD style="HEIGHT: 18px"></TD>
                              <TD style="HEIGHT: 18px"></TD>
                              <TD style="HEIGHT: 18px"></TD>
                           </TR>
                           <TR>
                              
                              <TD colspan=2 align="center">
                                 <img id="login" onclick="return checklogin()" src="images/denglu.gif" style="cursor:hand">
                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                                 <img onclick="return quitlogin()"  src="images/chongzhi.gif" style="cursor:hand"> 
                              </TD>
                              <TD></TD>
                           </TR>
                        </TBODY>
                     </TABLE>
                  </TD>
                </TR>
             </TBODY>
           </TABLE>
         </TD>
       </TR>
      <TR>
         <TD><IMG src="images/login_3.jpg" border=0></TD>
      </TR>
    </TBODY>
   </TABLE>
  </DIV>

    </FORM>
  </body>
</html>

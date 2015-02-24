<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改个人信息</title>
    
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
	          FONT: 12px 宋体; COLOR: #000000; TEXT-DECORATION: none
            }
    A:hover {
	          COLOR: #428eff
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
  var form1 = document.modifyexpert;
  var mobile=form1.mobile.value;
  var email=form1.email.value;
  var department=form1.department.value;
  if(mobile==""){
   window.alert("手机号码不能为空 ！");
   document.modifyexpert.mobile.focus(); 
   return false;
  }
  else if(email==""){
   window.alert("电子邮箱不能为空！");
   document.modifyexpert.email.focus();
   return false;
  }
  else if(department==""){
   window.alert("工作单位不能为空！");
   document.modifyexpert.department.focus();
   return false;
  }
  
  if(form1.email.value.indexOf("@")==-1 ||(form1.email.value.indexOf(".")==-1))
		{
			alert("请填写正确的E-mali地址！");
		    form1.email.value="";
			form1.email.focus();
			return false;
		}
  
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
  return true;
}
</script>

  </head>
  
  <body>
    <%
         request.setCharacterEncoding("UTF-8");
         String no=request.getParameter("no");
         String name=URLDecoder.decode(request.getParameter("name"), "UTF-8");
         name=new String(name.getBytes("iso-8859-1"), "UTF-8");
         String gender=request.getParameter("gender");
         String title=request.getParameter("title");
         String nation=URLDecoder.decode(request.getParameter("nation"), "UTF-8");
         nation=new String(nation.getBytes("iso-8859-1"), "UTF-8");
         String birthday=request.getParameter("birthday");
         String mobile=request.getParameter("mobile");
         String email=request.getParameter("email");
         String department=URLDecoder.decode(request.getParameter("department"), "UTF-8");
         department= new String(department.getBytes("iso-8859-1"), "UTF-8");
         
         String []s={"讲师","副教授","教授","副研究员","研究员"};

     %>
     <br/>
     <form  name="modifyexpert"  action="ModifyExpertServlet" method="post" >
     <table cellSpacing=1 cellPadding=3 width="60%" align="center" bgColor=#6298e1 border=0>
        <tr>
            <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>修改个人资料</STRONG></FONT></td>
        </tr>
        <tr>
           <td bgcolor="#fffff">
               <br/>
               <table align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">工号：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%"> 
                           &nbsp;<input type="text" name="no" value="<%=no.trim() %>" size="27" readonly="readonly"/>
                           &nbsp;（注:带有<FONT color=#ff0000>*</FONT> 标记的项可修改）
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="name" value="<%=name.trim() %>" size="27" readonly="readonly"/>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">性别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="gender" value="<%=gender.trim().equals("0")?"女":"男" %>" size="27" readonly="readonly"/>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">职称：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="title" value="<%=s[Integer.parseInt(title.trim())] %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">国籍：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="nation" value="<%=nation.trim() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">出生日期：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="birthday" value="<%=birthday.trim() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
               </table>
               <br/>
               <table  align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">手机号码：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="mobile" value="<%=mobile.trim() %>" size="27" />
                          <FONT color=#ff0000>*</FONT>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">电子邮箱：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="email" value="<%=email.trim() %>" size="27" />
                          <FONT color=#ff0000>*</FONT>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">工作单位：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="department" value="<%=department.trim() %>" size="27" />
                          <FONT color=#ff0000>*</FONT>
                       </td>
                   </tr>
               </table>
               <br>
               <div align="center">
                  <input type="submit" value="确定"  name="submit" onclick="return checkForm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="reset" value="取消"  name="reset" onclick="javascript:history.back(-1);">
               </div>
           </td>
        </tr>
     </table>
     </form>
  </body>
</html>

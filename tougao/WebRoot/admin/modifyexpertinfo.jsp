<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.test.tougao.dao.ExpertDao"%>
<%@page import="com.test.tougao.entity.Expert"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改评审专家信息</title>
    
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
  var form1 = document.modifyexpert;
  var no=form1.no.value;
  var name=form1.name.value;
  var title=form1.title.value;
  var nation=form1.nation.value;
  var birthday=form1.birthday.value;
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
  if(title==""){
   window.alert("职称不能为空 ！");
   form1.title.focus(); 
   return false;
  }
  if(nation==""){
   window.alert("国籍不能为空 ！");
   form1.nation.focus(); 
   return false;
  }
  if(birthday==""){
   window.alert("出生日期不能为空 ！");
   form1.birthday.focus(); 
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
     <%
        request.setCharacterEncoding("UTF-8");
        String no=request.getAttribute("no").toString();
        ExpertDao expertDao=new ExpertDao();
        Expert expert=expertDao.findByNo(no);
     %>
     <br/>
     <form name="modifyexpert"  action="ModifyExpertInfoServlet" method="post">
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
                           &nbsp;<input type="text" name="no" value="<%=expert.getExpertNo() %>" size="27" />
                           <FONT color=#ff0000>*</FONT>（注:带有<FONT color=#ff0000>*</FONT> 标记的项不可修改）
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="name" value="<%=expert.getExpertName() %>" size="27" />
                         
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">性别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <input type="radio" name="gender" value="1" <%=expert.getExpertGender().equals("1")?"checked":"" %>> 男&nbsp;&nbsp;
                           <input type="radio" name="gender" value="0" <%=expert.getExpertGender().equals("0")?"checked":"" %>> 女
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">职称：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <select name="title">
                             <option value="" selected>-----请选择-----</option>
                             <option value="0" <%=expert.getExpertTitle().equals("0")?"selected":"" %>>讲师</option>
                             <option value="1" <%=expert.getExpertTitle().equals("1")?"selected":"" %>>副教授</option>
                             <option value="2" <%=expert.getExpertTitle().equals("2")?"selected":"" %>>教授</option>
                             <option value="3" <%=expert.getExpertTitle().equals("3")?"selected":"" %>>副研究员</option>
                             <option value="4" <%=expert.getExpertTitle().equals("4")?"selected":"" %>>研究员</option>
                          </select>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">国籍：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="nation" value="<%=expert.getExpertNation() %>" size="27" />
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">出生日期：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="birthday" value="<%=expert.getExpertBirthday() %>" size="27"/>
                                                                  （注:出生日期格式如2014-01-01）
                       </td>
                   </tr>
                   
               </table>
               <br/>
               <table  align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">手机号码：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="mobile" value="<%=expert.getExpertMobile() %>" size="27"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">电子邮箱：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="email" value="<%=expert.getExpertEmail() %>" size="27"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">工作单位：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="department" value="<%=expert.getExpertDepartment() %>" size="27" />
                          
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.lang.*"%>
<%@page import="com.test.tougao.entity.Expert"%>
<%@page import="com.test.tougao.dao.ExpertDao"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人资料</title>
    
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

  </head>
  
  <body>
     <%
         request.setCharacterEncoding("UTF-8");
         String account=session.getAttribute("account").toString();
         ExpertDao expertDao=new ExpertDao();
         Expert expert=expertDao.findByNo(account);
         String []s={"讲师","副教授","教授","副研究员","研究员"};
         
     %>
     <br/>
     <table cellSpacing=1 cellPadding=3 width="60%" align="center" bgColor=#6298e1 border=0>
        <tr>
            <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>我的个人资料</STRONG></FONT></td>
        </tr>
        <tr>
           <td bgcolor="#fffff">
               <br/>
               <table align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">工号：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%"> 
                           &nbsp;<input type="text" name="no" value="<%=expert.getExpertNo() %>" size="27" readonly="readonly"/>
                           
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="name" value="<%=expert.getExpertName() %>" size="27" readonly="readonly"/>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">性别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="gender" value="<%=expert.getExpertGender().equals("0")?"女":"男" %>" size="27" readonly="readonly"/>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">职称：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="title" value="<%=s[Integer.parseInt(expert.getExpertTitle())] %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">国籍：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="nation" value="<%=expert.getExpertNation() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">出生日期：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="birthday" value="<%=expert.getExpertBirthday() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
               </table>
               <br/>
               <table  align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">手机号码：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="mobile" value="<%=expert.getExpertMobile() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">电子邮箱：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="email" value="<%=expert.getExpertEmail() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">工作单位：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="department" value="<%=expert.getExpertDepartment() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
               </table>
               <br>
               <div align="center">
                  <a href="expert/modifyselfinfo.jsp?no=<%=expert.getExpertNo() %>&&name=<%=URLEncoder.encode(expert.getExpertName(), "UTF-8") %>&&gender=<%=expert.getExpertGender() %>&&title=<%=expert.getExpertTitle() %>
                     &&nation=<%=URLEncoder.encode(expert.getExpertNation(), "UTF-8") %>&&birthday=<%=expert.getExpertBirthday() %>&&mobile=<%=expert.getExpertMobile() %>&&email=<%=expert.getExpertEmail() %>
                     &&department=<%=URLEncoder.encode(expert.getExpertDepartment(), "UTF-8") %>"><font size=4>修改</font></a>
               </div>
               <br>
           </td>
        </tr>
     </table>
  </body>
</html>

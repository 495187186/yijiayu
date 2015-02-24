<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="com.test.tougao.entity.Student"%>
<%@page import="com.test.tougao.dao.StudentDao"%>
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
         StudentDao studentDao=new StudentDao();
         Student student=studentDao.findByNo(account);
         String []s={"博士后","博士","硕士","本科"};
         
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
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">学号：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%"> 
                           &nbsp;<input type="text" name="no" value="<%=student.getStudentNo() %>" size="27" readonly="readonly"/>
                           
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="name" value="<%=student.getStudentName() %>" size="27" readonly="readonly"/>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">性别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="gender" value="<%=student.getStudentGender().equals("0")?"女":"男" %>" size="27" readonly="readonly"/>
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">类型：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="role" value="<%=s[Integer.parseInt(student.getStudentRole())] %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">学院：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="college" value="<%=student.getStudentCollege() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">专业：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="major" value="<%=student.getStudentMajor() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">入学时间：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="date" value="<%=student.getStudentDate() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
               </table>
               <br/>
               <table  align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">导师姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="teacher" value="<%=student.getStudentTutor() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">手机号码：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="mobile" value="<%=student.getStudentMobile() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">电子邮箱：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="email" value="<%=student.getStudentEmail() %>" size="27" readonly="readonly"/>
                          
                       </td>
                   </tr>
                   
               </table>
               <br>
               <div align="center">
                  <a href="student/modifyselfinfo.jsp?no=<%=student.getStudentNo() %>&&name=<%=URLEncoder.encode(student.getStudentName(), "UTF-8") %>&&gender=<%=student.getStudentGender() %>&&role=<%=student.getStudentRole() %>
                     &&college=<%=URLEncoder.encode(student.getStudentCollege(), "UTF-8") %>&&major=<%=URLEncoder.encode(student.getStudentMajor(), "UTF-8") %>&&date=<%=student.getStudentDate() %>&&teacher=<%=URLEncoder.encode(student.getStudentTutor(), "UTF-8") %>
                     &&mobile=<%=student.getStudentMobile() %>&&email=<%=student.getStudentEmail() %>"><font size=4>修改</font></a>
               </div>
               <br>
           </td>
        </tr>
     </table>
  </body>
</html>

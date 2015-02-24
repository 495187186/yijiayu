<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.CollegeDao"%>
<%@page import="com.test.tougao.entity.College"%>
<%@page import="com.test.tougao.dao.MajorDao"%>
<%@page import="com.test.tougao.entity.Major"%>
<%@page import="com.test.tougao.dao.StudentDao"%>
<%@page import="com.test.tougao.entity.Student"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改学生用户信息</title>
    
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
  var form1 = document.modifystudent;
  var no=form1.no.value;
  var name=form1.name.value;
  var role=form1.role.value;
  var college=form1.college.value;
  var major=form1.major.value;
  var date=form1.date.value;
  var teacher=form1.teacher.value;
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
  if(role==""){
   window.alert("类型不能为空 ！");
   return false;
  }
  if(college==""){
   window.alert("学院不能为空 ！");
   return false;
  }
  if(major==""){
   window.alert("专业不能为空 ！");
   return false;
  }
  if(date==""){
   window.alert("入学时间不能为空 ！");
   form1.date.focus(); 
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
        request.setCharacterEncoding("UTF-8");//字符编码,注意页面中汉字搜索必须用此编码
        String no=request.getAttribute("no").toString();
        System.out.println(no);
        StudentDao studentDao=new StudentDao();
        Student student=studentDao.findByNo(no);
        String major=student.getStudentMajor();
        System.out.println(major);
        
        CollegeDao collegeDao=new CollegeDao();
        List<College> collegeList=collegeDao.findAllCollege();//查询所有学院
        MajorDao majorDao=new MajorDao();
        List<Major> majorList=null;
        int maxsize=0;
        int []array=new int[collegeList.size()];
        for(int i=0;i<collegeList.size();i++){
           majorList=majorDao.findCollegeMajor(collegeList.get(i).getCollegeId());//查询每个学院的专业
           array[i]=majorList.size();//每个学院有多少个专业
        }
        for(int j=0;j<collegeList.size();j++){//找出所有学院中最多专业的个数
          if(array[j]>maxsize){maxsize=array[j];}
        }
     %>
     <script language="JavaScript">
      function Redirect(x)
       {
        //获取一级菜单长度 
        var select1_len = document.modifystudent.college.options.length; 
        //把一级菜单都设为数组 
        var select1 = new Array(select1_len);
        for (var i=0; i<select1_len; i++) 
        { 
          select1[i] = new Array();
        } 
        
         //定义基本选项 
        select1[0][0] = new Option("-----请选择-----", ""); 
   <%
        String majorName=null;
        for(int i=0;i<collegeList.size();i++){
          majorList=majorDao.findCollegeMajor(collegeList.get(i).getCollegeId());//查询每个学院的专业
   %>
        select1[<%=i+1 %>][0] = new Option("-----请选择-----", "");
   <%   
        for(int j=1;j<=maxsize;j++){
           if(j>array[i]){
              majorName="";
           }else{
              majorName=majorList.get(j-1).getMajorName();
           }
           
   %>
        select1[0][<%=j %>]=new Option(" ", "");
        select1[<%=i+1 %>][<%=j %>] = new Option("<%=majorName %>", "<%=majorName %>");
   <%        
          }    
        }
   
   %>     
     
   	  for (var p=0;p<select1[x].length;p++)
		{
			document.modifystudent.major.options[p]=new Option(select1[x][p].text,select1[x][p].value);
		    if(document.modifystudent.major.options[p].value=="<%=major %>"){
		       
		       document.modifystudent.major.options[p].selected = true ;
		       document.modifystudent.major.options[p].text="<%=major %>";
		    }
		}
		
			document.modifystudent.major.options[0].selected = true ;
    } 
        
     </script>  
     
    
     <br/>
     <form name="modifystudent" action="ModifyStudentInfoServlet" method="post">
     <table cellSpacing=1 cellPadding=3 width="60%" align="center" bgColor=#6298e1 border=0>
        <tr>
            <td background="images/td_bg.gif" height=25 align="center"><FONT color=#000000><STRONG>填写个人资料</STRONG></FONT></td>
        </tr>
        <tr>
           <td bgcolor="#fffff">
               <br/>
               <table align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">学号：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%"> 
                           &nbsp;<input type="text" name="no" value="<%=student.getStudentNo() %>" size="27" />
                           <FONT color=#ff0000>*</FONT>（注:带有<FONT color=#ff0000>*</FONT> 标记的项不可修改）
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="name" value="<%=student.getStudentName() %>" size="27" />
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">性别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <input type="radio" name="gender" value="1" <%=student.getStudentGender().equals("1")?"checked":"" %>> 男&nbsp;&nbsp;
                           <input type="radio" name="gender" value="0" <%=student.getStudentGender().equals("0")?"checked":"" %>> 女
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">类别：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">&nbsp;
                          <select name="role">
                             <option value="" selected>--请选择--</option>
                             <option value="0" <%=student.getStudentRole().equals("0")?"selected":"" %>>博士后</option>
                             <option value="1" <%=student.getStudentRole().equals("1")?"selected":"" %>>博士</option>
                             <option value="2" <%=student.getStudentRole().equals("2")?"selected":"" %>>硕士</option>
                             <option value="3" <%=student.getStudentRole().equals("3")?"selected":"" %>>本科</option>
                          </select>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">学院：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">&nbsp;
                          <select name="college" onChange="Redirect(document.modifystudent.college.options.selectedIndex)">
                              <option value="" selected>--------请选择--------</option>
                                 <%
                                   for(int i=0;i<collegeList.size();i++){
                                 %>
                                   <option value="<%=collegeList.get(i).getCollegeName() %>" <%=student.getStudentCollege().equals(collegeList.get(i).getCollegeName())?"selected":"" %>><%=collegeList.get(i).getCollegeName() %> </option>
                                 <%
                                    }
                                 %>
                          </select>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">专业：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">&nbsp;
                          <select name="major">
                             <option value="" selected>-----请选择-----</option> 
                                  
                          </select>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">入学时间：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="date" value="<%=student.getStudentDate() %>" size="27"/>
                                                                  （注:入学时间格式如2014-01）
                       </td>
                   </tr>
                   
               </table>
               <br/>
               <table  align="center" cellspacing="1" cellSpacing=0 width="96%" bgcolor="#CCCCCC" border=0>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">导师姓名：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="teacher" value="<%=student.getStudentTutor() %>" size="27" />
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">手机号码：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="mobile" value="<%=student.getStudentMobile() %>" size="27"/>
                          
                       </td>
                   </tr>
                   <tr>
                       <td align="center" bgcolor="#d5f1f2" height="30" width="20%">电子邮箱：</td>
                       <td bgcolor="#d5f1f2" height="30" width="80%">
                          &nbsp;<input type="text" name="email" value="<%=student.getStudentEmail() %>" size="27"/>
                          
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

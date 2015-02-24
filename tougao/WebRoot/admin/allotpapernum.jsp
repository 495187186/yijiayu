<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.test.tougao.dao.AllotNumDao"%>
<%@page import="com.test.tougao.entity.AllotNum"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设置论文指派人数</title>
    
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
  var form1 = document.myform;
  var num=form1.num.value;
  if(num==""){
   window.alert("请填写指派数量！");
   form1.num.focus();
   return false;
  }
  if(num<=0){
   window.alert("请填写正数！");
   form1.num.value="";
   form1.num.focus();
   return false;
  }
  if(num>99){
   window.alert("请填写小于100的正数！");
   form1.num.value="";
   form1.num.focus();
   return false;
  }

  return true;
}
</script>

  </head>
  
  <body>
    <%
        AllotNumDao allotnumDao=new AllotNumDao();
        AllotNum allotnum=allotnumDao.findByType("0");
    %>
    <form name="myform" action="AllotPaperNumServlet" method="post" >
     <br>
      <table width="40%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>设置论文指派人数</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td colspan="2" height="30" bgcolor="#d5f1f2"><div align="center">当前每篇论文的指派人数上限为<%=allotnum.getAllotNum() %>人</div></td>
                  </tr>
                  <tr class="kbj">
                     <td width="35%" height="30" bgcolor="#d5f1f2"><div align="center">数量：</div></td>
                     <td width="65%" height="30" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="num" value="" size="27"/>
                     </td>
                  </tr>
               </table>
               <br>
               <div align="center">
                 <input type="submit" value="提交"  name="submit" onclick="return checkForm()" >
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <input type="reset" value="重置" name="reset">
               </div>
             </td>
          </tr>
       </table>
    </form>
  </body>
</html>

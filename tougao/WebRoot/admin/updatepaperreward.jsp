<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="com.test.tougao.dao.PaperDao"%>
<%@page import="com.test.tougao.entity.Paper"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加或更新论文的审稿费</title>
    
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
  var reward=form1.reward.value;
  if(reward==""){
   window.alert("请填写审稿费金额！");
   form1.reward.focus();
   return false;
  }
  if(reward<=0){
   window.alert("请填写正数！");
   form1.reward.value="";
   form1.reward.focus();
   return false;
  }
  return true;
}
</script>

  </head>
  
  <body>
    <%
        request.setCharacterEncoding("UTF-8");
        String papername=URLDecoder.decode(request.getParameter("papername"), "UTF-8");
        papername=new String(papername.getBytes("iso-8859-1"), "UTF-8");
        
        PaperDao paperDao=new PaperDao();
        Paper paper=paperDao.findByName(papername.trim());
        
    %>
    <form name="myform" action="UpdatePaperRewardServlet" method="post" >
     <br>
      <table width="40%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                     <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>确定论文审稿费</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="35%" height="30" bgcolor="#d5f1f2"><div align="center">论文名称：</div></td>
                     <td colspan="3" height="30" bgcolor="#d5f1f2" align="center"><%=papername %></td>
                  </tr>
                  <tr class="kbj">
                     <td width="35%" height="30" bgcolor="#d5f1f2"><div align="center">论文类别：</div></td>
                     <td width="25%" height="30" bgcolor="#d5f1f2" align="center"><%=paper.getPaperType().equals("0")?"毕业论文":"期刊论文" %></td>
                     <td width="20%" height="30" bgcolor="#d5f1f2"><div align="center">上传作者：</div></td>
                     <td width="20%" height="30" bgcolor="#d5f1f2" align="center"><%=paper.getUploader() %></td>
                  </tr>
                  <%
                      if(paper.getIsReward().equals("1")){
                  %>
                  <tr class="kbj">
                     <td colspan="4" height="30" bgcolor="#d5f1f2"><div align="center">当前论文的审稿费为<font color=#ff0000><%=paper.getPaperReward() %></font>元</div></td>
                  </tr>
                  <%
                      }
                  %>
                  <tr class="kbj">
                     <td width="35%" height="30" bgcolor="#d5f1f2"><div align="center">审稿费(单位：元)：</div></td>
                     <td colspan="3" height="30" bgcolor="#d5f1f2" align="center">
                        <input type="text" name="reward" value="" size="27"/>
                     </td>
                  </tr>
               </table>
               <input type="hidden" name="papername" value="<%=papername %>"/>
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

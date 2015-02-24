<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加不带附件公告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/fckeditor/fckeditor.js"></script> 
	
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
  var form1 = document.addnote;
  var notename=form1.notename.value;
  var content=form1.content.value;
 
  if(notename==""){
     window.alert("公告主题不能为空！");
     form1.paperwriter.focus();
     return false;
  }
  if(content==""){
     window.alert("公告内容不能为空！");
     return false;
  }
 
  return true;
}
</script>

  </head>
  
  <body>
    <%
        request.setCharacterEncoding("UTF-8");
        String no=session.getAttribute("account").toString();
        String name=session.getAttribute("username").toString();
    %>
    <form name="addnote" method="post"  action="Add1NoteServlet">
      <br>
      <table width="80%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>编辑公告</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;公告主题</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                       <input type = "text" name = "notename" id = "notename" size="35" title="公告主题字数不超过30字">
                       <FONT color=#ff0000>*</FONT>
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;发布作者</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                       <input type = "text" name = "notewriter" id = "notewriter" value="<%=name %>" readonly="readonly">
                     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;公告内容</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                        <textarea id="content" name = "content" style = "height:200px;width:500px;"/></textarea> 
                        <script type="text/javascript">
                           var oFCKeditor = new FCKeditor( 'content' ) ;
                           oFCKeditor.Config['ToolbarStartExpanded'] = true ;
                           oFCKeditor.BasePath = '<%=request.getContextPath() %>/fckeditor/' ;
                           oFCKeditor.ToolbarSet = 'Self' ;
                           oFCKeditor.Width = '93%' ;
                           oFCKeditor.Height = '500px' ;
                           oFCKeditor.Value = '' ;
                           oFCKeditor.ReplaceTextarea(); 
                           //oFCKeditor.Create() ;
                        </script>
                     </td>
                  </tr>
               </table>
               <input type="hidden" name="userno" value="<%=no %>"/>
           <br>
           <div align="center">
           <input type="submit" value="提交"  name="submit" onclick="return checkForm()" >
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="reset" value="重置" name="reset">
           </div>
       </td>
     </tr>          
   </table>
  </form>
  </body>
</html>

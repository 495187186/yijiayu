<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交论文</title>
    
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
  var form1 = document.upfile;
  var filename=form1.filename.value;
  var paperwriter=form1.paperwriter.value;
  var papername=form1.papername.value;
  var papertype=form1.papertype.value;
  if(filename==""){
   window.alert("请选择上传文件 ！");
   return false;
  }
  if(paperwriter==""){
   window.alert("请填写第一作者 ！");
   form1.paperwriter.focus(); 
   return false;
  }
  if(papername==""){
   window.alert("请填写论文题目！");
   form1.papername.focus();
   return false;
  }
  if(papertype==""){
   window.alert("请选择论文类型！");
   form1.papertype.focus();
   return false;
  }

  return true;
}
</script>

  </head>
  
  <body>
 			
 	<form name = "upfile" method= "post"  action = "studentupfile" enctype="multipart/form-data">
      <br>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>提交论文</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;文件</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                       <input type="file" name = "filename" />
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;第一作者</div></td>
                     <td width="86%" bgcolor="#d5f1f2">　 
                       <input type = "text" name = "paperwriter" id = "paperwriter">
                       <FONT color=#ff0000>*</FONT>
                     </td>
                  </tr>
               </table>
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;论文题目</div></td>
                     <td width="50%" bgcolor="#d5f1f2">&nbsp;&nbsp;&nbsp;<input type="text" name="papername" value="" size="30" />&nbsp;<FONT color=#ff0000>*</FONT></td>
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;论文类型</div></td>
                     <td width="22%" bgcolor="#d5f1f2">　 
                         <select name = "papertype">
                            <option value = "" selected = "selected">--请选择--</option>
 							<option value = "0">毕业论文</option>
 							<option value = "1">期刊论文</option>
 						</select>
 						<FONT color=#ff0000>*</FONT>
                     </td>
                  </tr>  
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;备注</div></td>
                     <td colspan="3" bgcolor="#d5f1f2">　 
                        <textarea id="info" name = "info" style = "height:200px;width:500px;"/></textarea> 
                        <script type="text/javascript">
                           var oFCKeditor = new FCKeditor( 'info' ) ;
                           oFCKeditor.Config['ToolbarStartExpanded'] = true ;
                           oFCKeditor.BasePath = '<%=request.getContextPath() %>/fckeditor/' ;
                           oFCKeditor.ToolbarSet = 'Self' ;
                           oFCKeditor.Width = '92%' ;
                           oFCKeditor.Height = '300px' ;
                           oFCKeditor.Value = '' ;
                           oFCKeditor.ReplaceTextarea(); 
                           //oFCKeditor.Create() ;
                        </script>
                     </td>
                  </tr>
             </table>
            
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

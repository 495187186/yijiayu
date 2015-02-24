<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.StudentUpfileDao"%>
<%@page import="com.test.tougao.entity.StudentUpfile"%>
<%@page import="com.test.tougao.dao.ReviewTempDao"%>
<%@page import="com.test.tougao.entity.ReviewTemp"%>
<%@page import="com.test.tougao.dao.ReviewPaperDao"%>
<%@page import="com.test.tougao.entity.ReviewPaper"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审核论文</title>
    
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
	          FONT-SIZE: 11pt 宋体; COLOR: #0000ff; TEXT-DECORATION: none
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
  function formsave(){
     var form1 = document.myform;
     var messagename=form1.messagename.value;
     if(messagename==""){
        window.alert("请填写留言主题！");
        form1.messagename.focus();
        return false;
     }
     form1.action="SaveReviewServlet";
     form1.submit();
  }
  function formsubmit(){
     var form1 = document.myform;
     var file=form1.filename.value;
     var messagename=form1.messagename.value;
     if(messagename==""){
        window.alert("请请填写留言主题！");
        form1.messagename.focus();
        return false;
     }
     if(file==""){
        window.alert("请上传修改意见附件！");
        return false;
     }
     form1.action="SubmitReviewServlet";
     form1.submit();
     return true;
  }
</script>
	

  </head>
  
  <body>
    <%
       request.setCharacterEncoding("UTF-8");
       String expertno=request.getParameter("expertno");
       String papername=URLDecoder.decode(request.getParameter("papername"), "UTF-8");
       papername=new String(papername.getBytes("iso-8859-1"), "UTF-8");
       String papertype=request.getParameter("papertype");
       String uploaddate=request.getParameter("uploaddate");
       String allotdate=request.getParameter("allotdate");
       String paperreward=request.getParameter("paperreward");
    
       ReviewPaperDao reviewpaperDao=new ReviewPaperDao();
       ReviewPaper reviewpaper=reviewpaperDao.findPaper(expertno,papername.trim(),allotdate);
       
       StudentUpfileDao studentupfileDao=new StudentUpfileDao();
       StudentUpfile studentupfile=studentupfileDao.findUpfile(papername.trim(),uploaddate);
       ReviewTempDao reviewtempDao=new ReviewTempDao();
       ReviewTemp reviewtemp=reviewtempDao.findReviewTemp(expertno,papername.trim(),allotdate);
       
    %>
    <form name="myform" method= "post" enctype="multipart/form-data">
      <br>
      <table width="60%" border="1" bordercolor="#3399ff" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                     <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>评审论文</STRONG></FONT></td>
                 </tr>
               </table>
            </td>
         </tr>
         <tr>
            <td bgcolor="#fffff"> 
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">论文题目</div></td>
                     <td width="40%" bgcolor="#d5f1f2" align="center">
                       <input type="text" name="papername" value="<%=papername.trim() %>" size="26" readonly="readonly"/>
                     </td>
                     <td width="10%" height="30" bgcolor="#d5f1f2"><div align="center">类别</div></td>
                     <td width="36%" bgcolor="#d5f1f2" align="center">
                       <input type="text" name="papertype" value="<%=papertype.equals("0")?"毕业论文":"期刊论文" %>" size="16" readonly="readonly"/>
                     </td>
                  </tr>
                  
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;上传备注</div></td>
                     <td width="86%" bgcolor="#d5f1f2" colspan="3">　 
                        <textarea name = "info" style = "height:200px;width:500px;"/><%=studentupfile==null?"":studentupfile.getFileInfo() %></textarea> 
                     </td>
                  </tr>
               </table>
               <br>
               <table width="96%"  border="0" align="center" cellspacing="1" bgcolor="#CCCCCC" class="pt_12_hei">
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">评审选择</div></td>
                     <td width="40%" bgcolor="#d5f1f2" align="center">
                         <input type="hidden" name="select" value="<%=reviewpaper.getIsAgreed() %>"/>
                         <%=reviewpaper.getPaperFlag().equals("0")?"未选择":reviewpaper.getIsAgreed().equals("1")?"同意评审":"拒绝评审" %>
                     </td>
                     <td width="10%" height="30" bgcolor="#d5f1f2"><div align="center">操作</div></td>
                     <td width="36%" bgcolor="#d5f1f2" align="center">
                       <img height="16" width="16" src="images/download.png"><a href="FileDownServlet?FileId=<%=studentupfile.getFileId() %>">下载</a>
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">评价标准</div></td>
                     <td width="86%" bgcolor="#d5f1f2" align="center" colspan="3">
                         <input type="radio" name="level" value="0" checked/>优&nbsp;&nbsp;&nbsp;&nbsp;
                         <input type="radio" name="level" value="1" <%=(reviewtemp!=null)&&reviewtemp.getPaperLevel().equals("1")?"checked":"" %>/>良&nbsp;&nbsp;&nbsp;&nbsp;
                         <input type="radio" name="level" value="2" <%=(reviewtemp!=null)&&reviewtemp.getPaperLevel().equals("2")?"checked":"" %>/>中&nbsp;&nbsp;&nbsp;&nbsp;
                         <input type="radio" name="level" value="3" <%=(reviewtemp!=null)&&reviewtemp.getPaperLevel().equals("3")?"checked":"" %>/>差
                     </td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">留言主题</div></td>
                     <td width="86%" bgcolor="#d5f1f2" colspan="3">&nbsp;&nbsp;&nbsp;<input type="text" name = "messagename" value="<%=reviewtemp==null?"":reviewtemp.getMessageName() %>" size="27" title="留言主题字数不超过30字"/></td>
                  </tr>
                  <tr class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">上传附件</div></td>
                     <td width="86%" bgcolor="#d5f1f2" colspan="3">　 
                       <input type="file" name = "filename" />（<FONT color=#ff0000>注:“保存”时不必上传附件</FONT> ）
                     </td>
                  </tr>
                  <tr bgcolor="#E4F3F8" class="kbj">
                     <td width="14%" height="30" bgcolor="#d5f1f2"><div align="center">&nbsp;评审留言</div></td>
                     <td width="86%" bgcolor="#d5f1f2" colspan="3">　 
                        <textarea id="message" name = "message" style = "height:200px;width:500px;"/><%=reviewtemp==null?"":reviewtemp.getPaperMessage() %></textarea> 
                        <script type="text/javascript">
                           var oFCKeditor = new FCKeditor( 'message' ) ;
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
             <input type="hidden" name="expertno" value="<%=expertno %>"/>
             <input type="hidden" name="papername" value="<%=papername %>"/>
             <input type="hidden" name="papertype" value="<%=papertype %>"/>
             <input type="hidden" name="allottime" value="<%=allotdate %>"/>
             <input type="hidden" name="paperreward" value="<%=paperreward %>"/>
           <br>
           <div align="center">
           <input type="submit" value="保存" name="save" onclick="return formsave()">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="submit" value="提交" name="submit" onclick="return formsubmit()" >
           </div>
       </td>
     </tr>          
   </table>
  </form>
  </body>
</html>

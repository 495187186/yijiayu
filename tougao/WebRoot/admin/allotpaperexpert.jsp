<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.ExpertDao"%>
<%@page import="com.test.tougao.entity.Expert"%>
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
    
    <title>指派论文评审专家</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/Calendar1.js"></script> 
	<style type="text/css">
	<!--
     BODY {
	        FONT-SIZE: 11pt; COLOR: #000000; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff
          }
     td{font-size:11pt}
     a{text-decoration:none;color:#0000ff}
     a:hover{text-decoration:none;color:#808000 }
     select {font-size:8pt}
     input {font-size:11pt}
     
    -->
    </style>
    
<script language="JavaScript">

	function chosen(){

		var	listLength1 = document.getElementById("expertList2").options.length;
		var listLength2 = document.getElementById("paperList2").options.length;
		var i,j;
		if(listLength1<=0||listLength1>1||listLength2<=0){
		   if(listLength1<=0){
		      window.alert("请选择准备的指派专家！");
              return false;
		   }
		   if(listLength1>1){
		      window.alert("每次只能指派一位专家！");
              return false;
		   }
		   if(listLength2<=0){
		      window.alert("请选择准备分配的论文！");
              return false;
		   }
		
		}else{
		
		   for( i=0;i<listLength1;i++)
		   {
    		   document.getElementById("expertList2").options[i].selected = true;
		   }

		   for( j=0;j<listLength2;j++)

		   {
    		   document.getElementById("paperList2").options[j].selected = true;
		   }
		   return true;
		}
	
	}

	function moveOption(e1, e2, flag,n){
	   var m=0;//判断选择重复的标志
	   if(flag==0){//>>>
	      
	      for(var i=0;i <e1.options.length;i++){
  	      if(e1.options[i].selected){
  	         var e = e1.options[i];
             
             if(n==0){
                for(var j=0;j<e2.options.length;j++){
                   if(e2.options[j].value==e.value){
                       m=1;
                       break;
                   }
                }
                if(m==0){
                   e2.options.add(new Option(e.text.substring(0,e.text.lastIndexOf('(')), e.value));
                   document.getElementById("expert2").value = parseInt(document.getElementById("expert2").value)+1; 
                }else{
                   window.alert("重复选择该专家！");
                   return false;
                }
             }else{
                for(var j=0;j<e2.options.length;j++){
                   if(e2.options[j].value==e.value){
                       m=1;
                       break;
                   }
                }
                if(m==0){
                   e2.options.add(new Option(e.text.substring(0,e.text.lastIndexOf(']')+1), e.value));
                   document.getElementById("paper2").value = parseInt(document.getElementById("paper2").value)+1; 
                }else{
                   window.alert("重复选择该论文！");
                   return false;
                }
             }
 	        }
	      }
	      //e2.options[e2.options.length-1].selected;
	   }else{//<<<
	      
	      for(var i=0;i <e2.options.length;i++){
  	      if(e2.options[i].selected){
             e2.remove(i);
  	         i=i-1;
  	         if(n==0){
  	            document.getElementById("expert2").value = parseInt(document.getElementById("expert2").value)-1;
 	            if(document.getElementById("expert2").value<0){
 	               document.getElementById("expert2").value=0;
  	              }
  	         
 	           }else{
 	              document.getElementById("paper2").value = parseInt(document.getElementById("paper2").value)-1;
 	              if(document.getElementById("paper2").value<0){
 	                document.getElementById("paper2").value=0;
  	              }
 	           
 	           }  
 	         }
	      }
	     // e2.options[e2.options.length-1].selected;
	   }
	  //document.getElementById('result').value=getvalue(document.getElementById('right'));

   }

</script>         

  </head>
  
  <body>
    <%
        request.setCharacterEncoding("UTF-8");
        PaperDao paperDao=new PaperDao();
        List<Paper> paperList=paperDao.findSelectPaper("1","1");//可指派:开放且定稿费
        Paper paper=null;
        
        ExpertDao expertDao=new ExpertDao();
        List<Expert> expertList=expertDao.findAllExpert();
        Expert expert=null;
        
    %>
    <form action="PaperAllocationServlet" method="post" name="myform" onSubmit="return chosen()">
      <table width="96%"  border="1" bordercolor="#3399cc" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" >
         <tr>
            <td height="25">
               <table width="100%" height="25"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#3399cc">
                  <tr>
                      <td align="center" background="images/td_bg.gif" ><FONT color=#000000><STRONG>指派论文评审专家</STRONG></FONT></td>
                  </tr>
               </table>
            </td>
        </tr>
        <tr>
            <td>
               <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
                  <tr class="kbj" bgcolor="#d5f1f2" align="center">
                      <td width="15%" height="260" >
                         <p>备选专家共(<input type="text" size="3" name="expert1" value="<%=expertList.size() %>" id="expert1" style="border-style: none; background-color: #d5f1f2; width: 10px;">)人</p>
                         
                         <div align="center" >
                            <select name="expertList1" size="10" style="width:130px ; height:190px" multiple>
                               <%
                                  for(int i=0;i<expertList.size();i++){
                                     expert=expertList.get(i);
                               %>
                               <option value="<%=expert.getExpertNo() %>"><%=expert.getExpertName() %>(待审<%=expert.getPaperNum() %>篇)</option>
                               <%
                                   }
                               %>
                            </select>
                          
                         </div>
                      </td>
                      <td width="10%" align="center">
                         <br>
                         <p>
                         <input type="button" value=">>> " onClick="moveOption(document.getElementById('expertList1'), document.getElementById('expertList2'), 0, 0)"/>
                         <input type="button" value="<<< " onClick="moveOption(document.getElementById('expertList1'), document.getElementById('expertList2'), 1, 0)"/>
                         </p>
                      </td>
                      <td width="12%" height="260" >
                         <p>指派专家(<input type="text" size="3" name="expert2" value="0" id="expert2" style="border-style: none; background-color: #d5f1f2; width: 10px;">)人</p>
                         <div align="center" >
                            <select name="expertList2" style="width:85px ; height:15px"></select>
                         </div>
                        <p></p>
                      </td>
                      <td width="5%" height="260" align="center">
                          <br>
                          <p></p>
                          <%="<===>" %>
                      </td>
                      <td width="12%" height="260" >
                         <p>分配论文(<input type="text" size="3" name="paper2" value="0" id="paper2" style="border-style: none; background-color: #d5f1f2; width: 10px;">)篇</p>
                          
                         <div align="center" >
                            <select name="paperList2" size="10" style="width:80px ; height:190px" multiple></select>
                          
                         </div>
                      </td>
                      <td width="10%" align="center">
                         <br>
                         <p>
                         <input type="button" value=">>> " onClick="moveOption(document.getElementById('paperList1'), document.getElementById('paperList2'), 1, 1)"/>
                         <input type="button" value="<<< " onClick="moveOption(document.getElementById('paperList1'), document.getElementById('paperList2'), 0, 1)"/>
                         </p>
                      </td>
                      <td width="36%" height="260" >
                         <p>备选论文共(<input type="text" size="3" name="paper1" value="<%=paperList.size() %>" id="paper1" style="border-style: none; background-color: #d5f1f2; width: 10px;">)篇</p>
                          
                         <div align="center" >
                            <select name="paperList1" size="10" style="width:350px ; height:190px" multiple>
                               <% 
                                  for(int i=0;i<paperList.size();i++){
                                     paper=paperList.get(i);
                                  
                               %>
                               <option value="<%=paper.getPaperId() %>" style="background-color:#FFFFFF; color:black">[<%=paper.getPaperId() %>]<%=paper.getPaperName() %>(已指派<%=paper.getPersonNum() %>人)</option>
                               <%
                                    }
                               %>
                            </select>
                         </div>
                      </td>
                  </tr>
               </table>
               <br/>
               <div align="center">
                  <input type="submit" name="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="reset" name="reset" value="取消" onclick="javascript:history.go(-1);"/>    
               </div>
               
            </td>
        </tr>
      </table>
    </form>
  </body>
</html>

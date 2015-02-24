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
    
    <title>导航栏</title>
    
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
	          BACKGROUND: #799ae1; MARGIN: 0px; FONT: 9pt 宋体
             }
        TABLE {
	          BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px
              }
          TD {
	          FONT: 12px 宋体
             }
         IMG {
	          BORDER-RIGHT: 0px; BORDER-TOP: 0px; VERTICAL-ALIGN: bottom; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px
             }
          A {
	          FONT: 12px 宋体; COLOR: #000000; TEXT-DECORATION: none
             }
     A:hover {
	           COLOR: #428eff; TEXT-DECORATION: underline
             }
    .sec_menu {
	         BORDER-RIGHT: white 1px solid; BACKGROUND: #d6dff7; OVERFLOW: hidden; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid
             }
    .menu_title {
	
             }
    .menu_title SPAN {
	         FONT-WEIGHT: bold; LEFT: 7px; COLOR: #215dc6; POSITION: relative; TOP: 2px
             }
    .menu_title2 {
 	
             }
    .menu_title2 SPAN {
	         FONT-WEIGHT: bold; LEFT: 8px; COLOR: #428eff; POSITION: relative; TOP: 2px
             }
    </STYLE>

<SCRIPT language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</SCRIPT>

  </head>
  
  <body>
    <%
       request.setCharacterEncoding("UTF-8");
       ExpertDao expertDao=new ExpertDao();
       Expert expert=null;
       String account=session.getAttribute("account").toString();
       String username=session.getAttribute("username").toString();
       String role=session.getAttribute("role").toString();
       String msg="";
       String num=null;
       String []title={"讲师","副教授","教授","副研究员","研究员"};
       expert=expertDao.findByNo(account);
       num=expert.getExpertTitle();
       if(role.equals("1")){
           msg="超级管理员";
       }else if(role.equals("2")){
           msg="评审专家";
       }else{
           msg="普通学生";
       }
    %>
    <TABLE cellSpacing=0 cellPadding=0 width="100%" align=left border=0>
      <TBODY>
        <TR>
          <TD vAlign=top bgColor=#799ae1>
             <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
               <TBODY>
                 <TR>
                   <TD vAlign=bottom height=42><IMG height=38 src="images/title.gif" width=158> </TD>
                 </TR>
               </TBODY>
             </TABLE>
             <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
               <TBODY>
                  <TR>
                     <TD class=menu_title onMouseOver="this.className='menu_title2';" onmouseout="this.className='menu_title';" background="" height=25>
                        <div align="center"><A href="expert/frame.jsp" target=_parent><img id="home" src="images/house.png" style="width:14px;height:14px;" alt="首页"></A>|<A href="loginout.jsp" target=_parent><B>退出</B></A></div>
                     </TD>
                  </TR>
                  <TR>
                      <TD align="center">
                         <DIV class=sec_menu style="WIDTH: 158px" align="center">
                            <TABLE cellSpacing=0 cellPadding=0 width=100% align=center border=0>
                               <TBODY>
                                  <TR>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="right" width="40%"><font size=3>账户：</font></TD>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="left" width="60%"><font size=3><%=account %></font></TD>
                                  </TR>
                                  <TR>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="right" width="40%"><font size=3>姓名：</font></TD>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="left" width="60%"><font size=3><%=username %></font></TD>
                                  </TR>
                                  <TR>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="right" width="40%"><font size=3>职称：</font></TD>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="left" width="60%"><font size=3><%=title[Integer.parseInt(num)] %></font></TD>
                                  </TR>
                                  <TR>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="right" width="40%"><font size=3>角色：</font></TD>
                                      <TD height=25 bgcolor="#d6dff7" style="LINE-HEIGHT: 150%" align="left" width="60%"><font size=3><%=msg %></font></TD>
                                  </TR>
                               </TBODY>
                            </TABLE>
                        </DIV>
                    </TD>
                  </TR>
 
                  <TR>
                       <TD height=20></TD>
                  </TR>
               </TBODY>
              </TABLE>
	          <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
                 <TBODY>
                   <TR>
                      <TD class=menu_title id=menuTitle1 
                          onmouseover="this.className='menu_title2';" onclick=showsubmenu(0) 
                          onmouseout="this.className='menu_title';" 
                          background=images/admin_left_1.gif 
                          height=25><span><B>用户中心</B></span></TD>
                   </TR>
                   <TR>
                      <TD id=submenu0 style="DISPLAY: none">
                        <DIV class=sec_menu style="WIDTH: 158px ">
                           <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                               <TBODY>
                                  <TR>
                                      <TD height=20><A href="expert/selfinfo.jsp" target=main>维护个人资料</A></TD>
                                   </TR>
                                   
                                   <TR>
                                      <TD height=20><A href="expert/modifypwd.jsp" target=main>修改用户密码</a></TD>
                                   </TR>
                                </TBODY>
                            </TABLE>
                        </DIV>
                        <DIV style="WIDTH: 158px">
                            <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                               <TBODY>
                                  <TR>
                                    <TD height=20></TD>
                                  </TR>
                               </TBODY>
                            </TABLE>
                        </DIV>
                     </TD>
                  </TR>
               </TBODY>
            </TABLE>

            <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
               <TBODY>
                  <TR>
                    <TD class=menu_title id=menuTitle1 
                        onmouseover="this.className='menu_title2';" onclick=showsubmenu(1) 
                        onmouseout="this.className='menu_title';" 
                        background=images/admin_left_2.gif height=25><SPAN>论文审批</SPAN> </TD>
                  </TR>
                  <TR>
                     <TD id=submenu1 style="DISPLAY: none">
                        <DIV class=sec_menu style="WIDTH: 158px">
                           <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                              <TBODY>
                                 <TR>
                                     <TD height=20><a href="expert/managereviewpaper.jsp" target=main>待审论文</a></TD>
                                 </TR>
                                 <TR>
                                     <TD height=20><a href="expert/managereviewedpaper.jsp" target=main>已审论文</a></TD>
                                 </TR>
                                 <TR>
                                     <TD height=20><a href="expert/managepaper.jsp" target=main>结果统计</a></TD>
                                 </TR>
                                 
                              </TBODY>
                           </TABLE>
                        </DIV>
                        <DIV style="WIDTH: 158px">
                           <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                              <TBODY>
                                 <TR>
                                     <TD height=20></TD>
                                 </TR>
                              </TBODY>
                           </TABLE>
                        </DIV>
                     </TD>
                  </TR>
              </TBODY>
           </TABLE>
	  
	       <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
              <TBODY>
                 <TR>
                    <TD class=menu_title id=menuTitle1 
                        onmouseover="this.className='menu_title2';" onclick=showsubmenu(2) 
                        onmouseout="this.className='menu_title';" 
                        background=images/admin_left_2.gif height=25><SPAN>材料审批</SPAN> 
                    </TD>
                 </TR>
                 <TR>
                    <TD id=submenu2 style="DISPLAY: none">
                       <DIV class=sec_menu style="WIDTH: 158px">
                           <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                              <TBODY>
                                 <TR>
                                    <TD height=20><a href="expert/managereviewfile.jsp" target=main>待审材料</a></TD>
                                 </TR>
                                 <TR>
                                    <TD height=20><a href="expert/managereviewedfile.jsp" target=main>已审材料</a></TD>
                                 </TR>
                                 <TR>
                                    <TD height=20><a href="expert/managefile.jsp" target=main>结果统计</a></TD>
                                 </TR>
                                 
                              </TBODY>
                            </TABLE>
                        </DIV>
                        <DIV style="WIDTH: 158px">
                            <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                               <TBODY>
                                  <TR>
                                     <TD height=20></TD>
                                  </TR>
                               </TBODY>
                            </TABLE>
                        </DIV>
                     </TD>
                  </TR>
               </TBODY>
            </TABLE>
	
	        <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
               <TBODY>
                  <TR>
                     <TD class=menu_title id=menuTitle1 
                         onmouseover="this.className='menu_title2';" onclick=showsubmenu(3) 
                         onmouseout="this.className='menu_title';" 
                         background=images/admin_left_2.gif height=25><SPAN>帮助中心</SPAN> </TD>
                  </TR>
                  <TR>
                     <TD id=submenu3 style="DISPLAY: none">
			           <DIV class=sec_menu style="WIDTH: 158px">
			              <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                             <TBODY>
                                
                                <TR>
                                   <TD height=20><a href="expert/downexperthelp.jsp" target=main>下载使用帮助</a></TD>
                                </TR>
				                
                             </TBODY>
			              </TABLE>
			            </DIV>
                        <DIV style="WIDTH: 158px">
                            <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                               <TBODY>
                                   <TR>
                                      <TD height=20></TD>
                                   </TR>
                               </TBODY>
                             </TABLE>
                         </DIV>
			           </TD>
                     </TR>
                  </TBODY>
               </TABLE>
	  
               <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
                  <TBODY>
                    <TR>
                       <TD class=menu_title id=menuTitle1 
                           onmouseover="this.className='menu_title2';" 
                           onmouseout="this.className='menu_title';" 
                           background=images/admin_left_9.gif 
                           height=25><SPAN>管理系统版权信息</SPAN></TD>
                    </TR>
                    <TR>
                        <TD>
                           <DIV class=sec_menu style="WIDTH: 158px">
                               <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
                                  <TBODY>
                                     <TR>
                                         <TD height=20 bgcolor="#D6DFF7" style="LINE-HEIGHT: 150%">版权:哈尔滨工程大学</TD>
                                     </TR>
                                     <TR>
                                         <TD height=20 bgcolor="#D6DFF7" style="LINE-HEIGHT: 150%">网站问题收集:<a href="mailto:lijingmei@hrbeu.edu.cn">请联系</a></TD>
                                     </TR>
                                  </TBODY>
                               </TABLE>
                            </DIV>
                         </TD>
                     </TR>
                  </TBODY>
              </TABLE>
           </TR>
        </TBODY>
     </TABLE>
  </body>
</html>

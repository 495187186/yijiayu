<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.test.tougao.dao.UserDao"%>
<%@page import="com.test.tougao.entity.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>开启和关闭用户登录状态</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
        request.setCharacterEncoding("UTF-8");//字符编码
        RequestDispatcher dispatcher=null;
        String msg="";
		String result=request.getParameter("result");
		String role = request.getParameter("role");
		String userid=request.getParameter("UserId");
		UserDao userDao = new UserDao();
		User user = null;
		List<User> list = null; 
		try{
			if(userid==null||userid.equals("")||userid.equals("null")){//如果userid为空则说明对全部立题记录操作
               list=userDao.findByRole(role);
               for(int i=0;i<list.size();i++){
                   user=list.get(i);
                   user.setUser_active(result);
                   userDao.update(user);
               }
            }else{//对单个记录修改
               user=userDao.findById(userid);
               user.setUser_active(result);
               userDao.update(user);
            }
             msg+="恭喜您，操作成功！";
             request.setAttribute("msg",msg);  								      
	         dispatcher=request.getRequestDispatcher("modifyresult.jsp");//操作结果提示
	         dispatcher.forward(request, response);

		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
     %>
  </body>
</html>

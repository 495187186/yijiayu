<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.test.tougao.dao.ReviewFileDao"%>
<%@page import="com.test.tougao.entity.ReviewFile"%>
<%@page import="com.test.tougao.dao.AdminFileDao"%>
<%@page import="com.test.tougao.entity.AdminFile"%>
<%@page import="com.test.tougao.dao.StaFileDao"%>
<%@page import="com.test.tougao.entity.StaFile"%>
<%@page import="com.test.tougao.dao.AllotFileDao"%>
<%@page import="com.test.tougao.entity.AllotFile"%>
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
    
    <title>同意或拒绝评审材料</title>
    
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
        request.setCharacterEncoding("UTF-8");
        String expertno=session.getAttribute("account").toString();
        String result=request.getParameter("result");
        String id=request.getParameter("Id");
        RequestDispatcher dispatcher=null;
        String msg="";
        
        ExpertDao expertDao=new ExpertDao();
        Expert expert=expertDao.findByNo(expertno);
        
        ReviewFileDao reviewfileDao=new ReviewFileDao();//专家待审论文表
        ReviewFile reviewfile=reviewfileDao.findById(id);
        
        
        AdminFileDao adminfileDao=new AdminFileDao();//论文表
        AdminFile adminfile=adminfileDao.findByName(reviewfile.getDocName().trim());
        
        
        StaFileDao stafileDao=new StaFileDao();//管理员查看论文统计表
        StaFile stafile=stafileDao.findByFileName(reviewfile.getDocName().trim());
        
        AllotFileDao allotfileDao=new AllotFileDao();//管理员指派论文表
        AllotFile allotfile=allotfileDao.findExpFile(reviewfile.getDocName().trim(),expert.getExpertName(),reviewfile.getUploadDate().substring(0,reviewfile.getUploadDate().length()-2));
        
        try{
           
           if(result.equals("0")){//若拒绝则评审完毕
             reviewfile.setUploadDate(reviewfile.getUploadDate().substring(0,reviewfile.getUploadDate().length()-2));
             reviewfile.setAllotDate(reviewfile.getAllotDate().substring(0,reviewfile.getAllotDate().length()-2));
             reviewfile.setDocDeadline(reviewfile.getDocDeadline().substring(0,reviewfile.getDocDeadline().length()-2));
             reviewfile.setDocFlag("1");
             reviewfile.setIsReviewed("1");
             reviewfile.setIsAgreed(result);
             reviewfileDao.update(reviewfile);
             
             allotfile.setUploadTime(allotfile.getUploadTime().substring(0,allotfile.getUploadTime().length()-2));
             allotfile.setAllotTime(allotfile.getAllotTime().substring(0,allotfile.getAllotTime().length()-2));
             allotfile.setFileFlag("1");
             allotfile.setIsReviewed("2");//评审完毕
             allotfile.setIsAgreed(result);
             allotfileDao.update(allotfile);
             
             expert.setPaperNum(Integer.parseInt(expert.getPaperNum())-1+"");//评审完一篇待审数量-1
			 expertDao.update(expert);
             
           }else{
             reviewfile.setUploadDate(reviewfile.getUploadDate().substring(0,reviewfile.getUploadDate().length()-2));
             reviewfile.setAllotDate(reviewfile.getAllotDate().substring(0,reviewfile.getAllotDate().length()-2));
             reviewfile.setDocDeadline(reviewfile.getDocDeadline().substring(0,reviewfile.getDocDeadline().length()-2));
             reviewfile.setDocFlag("1");
             reviewfile.setIsAgreed(result);
             reviewfileDao.update(reviewfile);
             
             allotfile.setUploadTime(allotfile.getUploadTime().substring(0,allotfile.getUploadTime().length()-2));
             allotfile.setAllotTime(allotfile.getAllotTime().substring(0,allotfile.getAllotTime().length()-2));
             allotfile.setFileFlag("1");
             allotfile.setIsReviewed("1");//有人同意则进入审批中
             allotfile.setIsAgreed(result);
             allotfileDao.update(allotfile);
             
           }
           
           stafile.setUploadTime(stafile.getUploadTime().substring(0,stafile.getUploadTime().length()-2));
           stafile.setFileStatus("1");
           stafileDao.update(stafile);
           
           adminfile.setFileTime(adminfile.getFileTime().substring(0,adminfile.getFileTime().length()-2));
           adminfile.setFileStatus("1");
           adminfileDao.update(adminfile);
           
           msg+="恭喜您，操作成功！";
           request.setAttribute("msg",msg);  								      
	       dispatcher=request.getRequestDispatcher("showsuccess.jsp");//操作结果提示
	       dispatcher.forward(request, response);
           
 
        }catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        
    
    %>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.test.tougao.dao.ReviewPaperDao"%>
<%@page import="com.test.tougao.entity.ReviewPaper"%>
<%@page import="com.test.tougao.dao.PaperDao"%>
<%@page import="com.test.tougao.entity.Paper"%>
<%@page import="com.test.tougao.dao.StaPaperDao"%>
<%@page import="com.test.tougao.entity.StaPaper"%>
<%@page import="com.test.tougao.dao.AllotPaperDao"%>
<%@page import="com.test.tougao.entity.AllotPaper"%>
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
    
    <title>同意或拒绝评审论文</title>
    
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
        
        ReviewPaperDao reviewpaperDao=new ReviewPaperDao();//专家待审论文表
        ReviewPaper reviewpaper=reviewpaperDao.findById(id);
        
        
        PaperDao paperDao=new PaperDao();//论文表
        Paper paper=paperDao.findByName(reviewpaper.getPaperName().trim());
        
        
        StaPaperDao stapaperDao=new StaPaperDao();//管理员查看论文统计表
        StaPaper stapaper=stapaperDao.findByPaperName(reviewpaper.getPaperName().trim());
        
        AllotPaperDao allotpaperDao=new AllotPaperDao();//管理员指派论文表
        AllotPaper allotpaper=allotpaperDao.findExpPaper(reviewpaper.getPaperName().trim(),expert.getExpertName(),reviewpaper.getUploadDate().substring(0,reviewpaper.getUploadDate().length()-2));
        
        try{
           
           if(result.equals("0")){//若拒绝则评审完毕
             reviewpaper.setAllotDate(reviewpaper.getAllotDate().substring(0,reviewpaper.getAllotDate().length()-2));
             reviewpaper.setPaperDeadline(reviewpaper.getPaperDeadline().substring(0,reviewpaper.getPaperDeadline().length()-2));
             reviewpaper.setUploadDate(reviewpaper.getUploadDate().substring(0,reviewpaper.getUploadDate().length()-2));
             reviewpaper.setPaperFlag("1");
             reviewpaper.setIsReviewed("1");
             reviewpaper.setIsAgreed(result);
             reviewpaperDao.update(reviewpaper);
             
             allotpaper.setAllotTime(allotpaper.getAllotTime().substring(0,allotpaper.getAllotTime().length()-2));
             allotpaper.setUploadTime(allotpaper.getUploadTime().substring(0,allotpaper.getUploadTime().length()-2));
             allotpaper.setPaperFlag("1");
             allotpaper.setIsReviewed("2");//评审完毕
             allotpaper.setIsAgreed(result);
             allotpaperDao.update(allotpaper);
             
             expert.setPaperNum(Integer.parseInt(expert.getPaperNum())-1+"");//评审完一篇待审数量-1
			 expertDao.update(expert);
             
           }else{
             reviewpaper.setAllotDate(reviewpaper.getAllotDate().substring(0,reviewpaper.getAllotDate().length()-2));
             reviewpaper.setPaperDeadline(reviewpaper.getPaperDeadline().substring(0,reviewpaper.getPaperDeadline().length()-2));
             reviewpaper.setUploadDate(reviewpaper.getUploadDate().substring(0,reviewpaper.getUploadDate().length()-2));
             reviewpaper.setPaperFlag("1");
             reviewpaper.setIsAgreed(result);
             reviewpaperDao.update(reviewpaper);
             
             allotpaper.setAllotTime(allotpaper.getAllotTime().substring(0,allotpaper.getAllotTime().length()-2));
             allotpaper.setUploadTime(allotpaper.getUploadTime().substring(0,allotpaper.getUploadTime().length()-2));
             allotpaper.setPaperFlag("1");
             allotpaper.setIsReviewed("1");//有人同意则进入审批中
             allotpaper.setIsAgreed(result);
             allotpaperDao.update(allotpaper);
             
           }
           
           stapaper.setUploadTime(stapaper.getUploadTime().substring(0,stapaper.getUploadTime().length()-2));
           stapaper.setPaperStatus("1");
           stapaperDao.update(stapaper);
           
           paper.setPaperTime(paper.getPaperTime().substring(0,paper.getPaperTime().length()-2));
           paper.setPaperStatus("1");
           paperDao.update(paper);
           
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

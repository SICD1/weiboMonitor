<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>">
    <meta charset="utf-8">
  </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <a href="background/manager">
	        <header class="templatemo-site-header" style="height:56px;">
	         <!--  <div class="square"></div> -->
	          <h1 style="font-family: 华文行楷;font-size: 27px;padding-top:10px;">微博舆情分析系统</h1>
	        </header>
        </a>
        <div class="profile-photo-container">
          <img src="images/profile-photo.jpg" alt="Profile Photo" class="img-responsive"></a>  
          <div class="profile-photo-overlay"></div>
        </div>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">
          <ul id="nav">
            <li><a href="<%=basePath%>background/manageusers?pageNo=1" class="active"><i class="fa fa-home fa-fw"></i>用户管理</a></li>
            <li><a href="<%=basePath%>background/kwmanage?pageNo=1"><i class="fa fa-bar-chart fa-fw"></i>关键词管理</a></li>
            <li><a href="<%=basePath%>background/stopwords/1"><i class="fa fa-database fa-fw"></i>停用词管理</a></li>
            <li><a href="<%=basePath%>background/weibosManager/1"><i class="fa fa-database fa-fw"></i>微博管理</a></li>
          </ul> 
        </nav>
      </div>
    </div>    
  </body>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	   $(function(){
	   $("a").click(function(){
	        $("a").removeClass("active");
	        $(this).addClass("active");
		   });
		});
	</script>
</html>
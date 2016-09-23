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
    <link href="css/openwindow.css" rel="stylesheet" type="text/css" />
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
	<style type="text/css">
		.input-group{
			margin-top:30px;
			margin-left:40px;
			margin-right:40px;
		}
		.form-group{
			height:40px;
		}
	</style>
    <script src="js/openwindow_js.js" type="text/javascript"></script>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/register.js"></script>

  </head>
  <body>  
      <!-- header --> 
       <div class="row">
         <nav class="templatemo-top-nav col-lg-12 col-md-12">
           <ul class="text-uppercase">
             <li><a class="active">微博,绽放你的每一秒</a></li>
             <li><a class="active">网上微博,网罗生活</a></li>
             <li><a class="active">微观风云,博览天下</a></li>
             <li><a class="active">微小世界，广博天空</a></li>
             <li><a class="active">尽在本平台</a></li>
           </ul>  
         </nav>

       </div>
  </body>
</html>
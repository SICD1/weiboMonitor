<%@ page language="java" import="java.util.*"
	import="com.sicdlib.dto.KeyWords" pageEncoding="UTF-8"%>
<%@ page import="com.sicdlib.dto.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	User user = (User)session.getAttribute("user");
	System.out.println(user);
	String user_name = "";
	String user_email = "";
	String user_telephone = "";
	boolean user_sex = true;
	if(user != null){
		user_name = user.getU_name();
		System.out.print(user_name);
		user_email = user.getU_email();
		user_telephone = user.getU_telephone();
		user_sex = (Boolean.parseBoolean(String.valueOf(user.getU_sex()))) ;
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8">
<title>关键词管理 - 微博舆情分析系统</title>
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
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
	<script type="text/javascript">
		overall_user_name = "";
		window.onload = function(){
			Alert('#but', {
				content : '<form action="modMyInfo" method="post"><center><h2 style="background-color:#39ADB4;color:white;padding:10px;margin-buttom:5px;width:101.1%;margin-left:-4px;">个人信息</h2></center><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div><input id="u_name" name="u_name" onblur="modInfo_checkUser();" type="text" value="<%=user_name%>" class="form-control" placeholder="username"></div><label id="u_name_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
				+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div><input id="u_pwd" name="u_pwd" type="password" onblur="return modInfo_checkPwd()" class="form-control" value="***" placeholder="******"></div><label id="u_pwd_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
				+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div><input id="u_r_pwd" name="u_r_pwd" onblur="return check_R_Pwd()" type="password" class="form-control" value="***" placeholder="******repeat password"></div><label id="u_r_pwd_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
				+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-mail fa-fw"></i></div><input id="u_mail" name="u_mail" value="<%=user_email%>" type="text" class="form-control" onblur="return modInfo_check_u_mail(<%=user_email%>)" placeholder="name@mail.com"></div><label id="u_mail_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label><div><input id="btn_send_code" type="button" value="发送邮箱验证" onclick="send_verfication_code()" class="templatemo-blue-button" style="margin-top:4px;display: none;line-height:inherit"/><input id="txt_verfication_code" type="text" value="" onblur="valid_mail_code()" class="form-control" style="width:160px;display: none;"/></div><label id="u_mail_verfication_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
				+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-telphone fa-fw"></i></div><input id="u_telphone" name="u_telphone" type="text" value="<%=user_telephone%>" class="form-control" onblur="return check_u_telphone()" placeholder="telphone"></div><label id="u_telphone_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
				+'<div class="col-lg-12 form-group"><div class="margin-right-15 templatemo-inline-block">性别：<input type="radio" name="u_sex" id="r4" value="true" checked=""><label for="r4" class="font-weight-400"><span></span>男</label></div><div class="margin-right-15 templatemo-inline-block"><input type="radio" name="u_sex" id="r5" value="false"><label for="r5" class="font-weight-400"><span></span>女</label></div></div>'
				+'<div class="form-group"><button type="submit" class="templatemo-blue-button width-100">修改个人信息</button></div></form>',
				width : '',
				height : '',
				top : '',
				left : '',
				sidebar : '',
				traction : '',
				fixed : 'fixed',
				close : 'close'
			});
		};
		//修改个人信息
		function modInfo_checkUser(){
			var u_name = $.trim($("#u_name").val());
			//为全局变量赋值

			alert(<%=user_name%> +":"+ u_name);
			if(<%=user_name%> !=  u_name) {
				if(u_name==""||u_name.length<6){
					$("#u_name_prompt").html("请输入6位数密码");
					$("#u_name_prompt").css("display","");
					return false;
				}else {
					$.ajax({
						type: 'post',
						url: 'user/isExistUser?u_name='+u_name,
						success:function(msg){
							if('success' == msg){
								$("#u_name_prompt").html("该用户名已被使用");
								$("#u_name_prompt").css("display","");
								return false;
							}
						}
					});
					$("#u_name_prompt").html("");
					$("#u_name_prompt").css("display","none");
					return true;
				}
			}
		}
	</script>
<script text="text/javascript">
			function show(tag){
	            var light=document.getElementById(tag);
	            //var fade=document.getElementById('fade');
	            light.style.display='block';
	            //fade.style.display='block';
	        }
	        function hide(tag){
	            var light=document.getElementById(tag);
	            //var fade=document.getElementById('fade');
	            light.style.display='none';
	            //fade.style.display='none';
	        }
</script>
</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<jsp:include page="/jsp/statics/left.jsp" />
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<%--<jsp:include page="/jsp/statics/header.jsp" />--%>
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
						<div class="templatemo-content-widget white-bg" style="border-radius:0px;box-shadow:0 0 0 0 ;border:0px;width:200px;height:80px;float:right;margin-top:-60px;margin-right:-10px;">
							<div class="media">
								<div class="media-left" id="but">
									<a>
										<img class="media-object img-circle" src="images/sunset.jpg" alt="Sunset">
									</a>
								</div>
								<div class="media-body">
									<h1 class="media-heading text-uppercase"><%=user_name%></h1>
									<a class="media-heading text-uppercase" href="<%=basePath%>login.jsp">注 销</a>
								</div>
							</div>
						</div>
			</div>
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<div class="templatemo-content-widget white-bg">
							<form action="background/keyWordsSearchByPage" method="post">
								<input type="hidden" name="pageNo" value="1"/>
								<span><input name="keywords_search" type="text"
									style="border-radius:0px;height:38px;width:60%;float:left;"
									class="form-control" id="inputEmail" placeholder="输入关键词"></span>
								<span><button type="submit" style="border-radius:0px;"
										class="templatemo-blue-button">搜 索</button></span>
							</form>
						</div>
						<table
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<td><a href="" class="white-text templatemo-sort-by">no<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">name<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">hotDegree<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">province<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">date<span
											class="caret"></span></a></td>
									<td colspan="2">manage</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="k" items="${page.result}" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td>${k.kw_name}</td>
										<td>${k.kw_hotDegree}</td>
										<td>${k.kw_province}</td>
										<td>${k.kw_date}</td>
										<td><a href="javascript:void(0)"
											onclick="show('light${status.index+1}')" class="templatemo-edit-btn">edit</a> <a
											href="<%=path%>/background/deleteKeyWords?id=${k.kw_id}" class="templatemo-edit-btn">delete</a></td>
									</tr>
									<div>
										<form action="background/editKeyWords" method="post">
											<div id="light${status.index+1}" class="white_content"
												style="border:0px;">
												<div class="close">
													<a href="javascript:void(0)"
														onclick="hide('light${status.index+1}')"> <img
														title="关闭" src="images/close.png"
														style="padding: 0px; margin: 0px; border: 0px;"></a>
												</div>
												<div class="con" sty>
													<center>
														<h3 style="margin-left:-40px">修改关键词信息</h3>
													</center>
													<input type="hidden" name="kw_id" value="${k.kw_id}" /> <input
														id="kw_name" name="kw_name" type="text"
														value="${k.kw_name}" onblur="return checkUser()"
														class="form-control"
														style="margin-top: 10px;width:220px;margin-left:20px"
														placeholder="关键词名称"> <input id="kw_hotDegree"
														name="kw_hotDegree" type="text" value="${k.kw_hotDegree}"
														onblur="return checkUser()" class="form-control"
														style="margin-top: 10px;width:220px;margin-left:20px"
														placeholder="关键词热度"> <input id="kw_province"
														name="kw_province" type="text" value="${k.kw_province}"
														onblur="return checkUser()" class="form-control"
														style="margin-top: 10px;width:220px;margin-left:20px"
														placeholder="关键省份"> <input id="kw_date"
														name="kw_date" type="text" value="${k.kw_date}"
														onblur="return checkUser()" class="form-control"
														style="margin-top: 10px;width:220px;margin-left:20px"
														placeholder="关键日期">
													<button type="submit"
														class="templatemo-blue-button width-100"
														style="margin-top:15px;width:100px;margin-left:60px">修
														改</button>
												</div>
											</div>
										</form>
								</c:forEach>
							</tbody>
						</table>

				<div class="pagination-wrap">
					<ul class="pagination">
						<li><a
							href="background/kwmanage/1">1</a></li>
						<li><a
							href="background/kwmanage/2">2</a></li>
						<li><a
							href="background/kwmanage/3">3</a></li>
						<li><a
							href="background/kwmanage/4">4</a></li>
						<li><a
							href="<%=basePath%>background/kwmanage/5">5</a></li>
						<li><a href="<%=basePath%>background/kwmanage/${page.nextPage}" aria-label="Next"> <span
								aria-hidden="true"><i class="fa fa-play"></i></span>
						</a></li>
					</ul>
				  </div>
				<div style="padding:50px;width:100%;height:10%;float:right;">
					<jsp:include page="/jsp/statics/foot.jsp"></jsp:include>
				</div>
			</div>
		</div>
		</div>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
	<script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script src="js/openwindow_js.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/register.js"></script>
	<!-- Templatemo Script -->
	<!-- <script type="text/javascript"
		src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script> -->
	<script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>
</body>
</html>
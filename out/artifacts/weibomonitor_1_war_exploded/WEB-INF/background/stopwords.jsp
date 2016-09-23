<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>停用词管理 - 微博舆情分析系统</title>

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
			if(<%=user_name%> != u_name) {
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
		};
	</script>
<script   type="text/javascript">
function show(tag){
    var light=document.getElementById(tag);
   // var fade=document.getElementById('fade');
    light.style.display='block';
   // fade.style.display='block';
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
		<div class="templatemo-sidebar">
			<jsp:include page="/jsp/statics/left.jsp" />
		</div>
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
					<div class="panel panel-default table-responsive"
						style="width:100%;">
						<div class="templatemo-content-widget white-bg">
						<table
							class="table table-striped table-bordered templatemo-user-table"
							style="width:100%">
							<thead style="width:100%">
								<tr style="width:100%">
									<td style="width:20%">No.</td>
									<td style="width:30%">停用词</td>
									<td style="width:25%">修改</td>
									<td style="width:25%">删除</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="cs" varStatus="vs">
									<tr>
										<td>${vs.index+1}</td>
										<td>${cs.csd_word }</td>
										<td><a href="javascript:void(0)"
											onclick="show('light${vs.index+1}')" class="templatemo-edit-btn">edit</a> <%--<td><button onclick="ck();" class="templatemo-edit-btn">Delete</button></td> --%>
										<td><a href="background/deleteStopwords?csd_id=${cs.csd_id}" class="templatemo-edit-btn">Delete</a></td>
									</tr>
									<div>
										<form action="background/editStopwords" method="post">
											<div id="light${vs.index+1}" class="white_content"
												style="border:0px;height:180px;">
												<div class="close">
													<a href="javascript:void(0)"
														onclick="hide('light${vs.index+1}')"> <img
														title="关闭" src="images/close.png"
														style="padding: 0px; margin: 0px; border: 0px;"> </a>
												</div>
												<div class="con" >
													<center>
														<h3 style="margin-left:-40px">修改停用词信息</h3>
													</center>
													<input type="hidden" name="pageNo" value="1">
													<input type="hidden" name="csd_id" value="${cs.csd_id}" /> 
													<input
														id="csd_word" name="csd_word" type="text"
														value="${cs.csd_word}" onblur="return checkUser()"
														class="form-control"
														style="margin-top: 10px;width:220px;margin-left:20px"
														placeholder="关键词名称"> 
													<button type="submit"
														class="templatemo-blue-button width-100"
														style="margin-top:15px;width:100px;margin-left:60px">修
														改</button>
												</div>
											</div>
										</form>
									</div>
								</c:forEach>
								</table>
								
							</tbody>
							
					</div>
					
					<div class="pagination-wrap">
									<ul class="pagination">
										<!-- 前一页 -->
										<%-- <a href="background/stopwords/${page.prePage}"
											aria-label="Next"> <span aria-hidden="true"><i
												class="fa fa-play"></i>
										</span> </a> --%>
										<li><a href="background/stopwords/1">1</a>
										</li>
										<li><a href="background/stopwords/2">2</a>
										</li>
										<li><a href="background/stopwords/3">3
												<span class="sr-only">(current)</span>
										</a>
										</li>
										<li><a href="background/stopwords/4">4</a>
										</li>
										<li><a href="background/stopwords/5">5</a>
										</li>
										<li><a href="background/stopwords/${page.nextPage}"
											aria-label="Next"> <span aria-hidden="true"><i
													class="fa fa-play"></i>
											</span> </a></li>
									</ul>
								</div>
					
					</div>
				</div>

				<div style="padding:50px;width:100%;height:10%;float:right;">
					<jsp:include page="/jsp/statics/foot.jsp"></jsp:include>
				</div>
				
			</div>
		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>
	</div>
</body>
</html>
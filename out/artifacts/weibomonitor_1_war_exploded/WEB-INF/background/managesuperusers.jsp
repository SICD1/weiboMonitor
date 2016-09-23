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
  	<base href="<%=basePath%>"/>
    <meta charset="utf-8"> 
    <title>管理员管理</title>
        <link href="css/openwindow.css" rel="stylesheet" type="text/css" />
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/templatemo-style.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
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
	            var fade=document.getElementById('fade');
	            light.style.display='block';
	            fade.style.display='block';
	        }
	        function hide(tag){
	            var light=document.getElementById(tag);
	            var fade=document.getElementById('fade');
	            light.style.display='none';
	            fade.style.display='none';
	        }
	        window.onload=function(){
	        	var flag =document.getElementsByName("u_sex");
	        	if(flag=='true'){
	        		document.getElementsByName("u_sex")[1].checked='checked';
	        	}
	        	else{
	        		document.getElementsByName("u_sex")[0].checked='checked';
	        	} 
	        	
	        }
		</script>
  </head>
  <body> 
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <jsp:include page="/jsp/statics/left.jsp"/>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
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
               <form action="background/userSearch" method="post">
                  <span><input name="search_userKey" type="text" style="border-radius:0px;height:38px;width:60%;float:left;" class="form-control" id="inputEmail" placeholder="输入关键词"></span>
                   <span><button type="submit" style="border-radius:0px;" class="templatemo-blue-button">搜 索</button></span>
                </form>
                </div>  
              <table class="table table-striped table-bordered templatemo-user-table">
              <thead>
                  <tr>
                    <td><a href="" class="white-text templatemo-sort-by">no. <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">name<span class="caret"></span></a></td>
					<td><a href="" class="white-text templatemo-sort-by">telephone<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">email<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">sex<span class="caret"></span></a></td>
                    <td colspan="2">Manage</td>
                  </tr>
                  </thead>
                  <tbody>
                   <c:forEach items="${page.result}" var="c" varStatus="status">
                       <tr>
                          <td>${status.index+1}</td>
                          <td>${c.u_name}</td>
                          <td>${c.u_telephone}</td>
                          <td>${c.u_email}</td>
                          <td>
                              ${c.u_sex}
                          </td>
                          <%-- <td>
                               <c:if test="${c.u_sex==true }">女</c:if>
                               <c:if test="${c.u_sex==false }">男</c:if>
                           </td>--%>
                          <td><a href="javascript:void(0)" onclick="show('light${status.index+1}')" class="templatemo-edit-btn">edit</a></td>
                          <td><a href="<%=path%>/background/deleteUser?id=${c.u_id}" class="templatemo-edit-btn">delete</a></td>
                      </tr>
                      <form action="background/editUser" method="post">

                          <input type="hidden" name="pageNo" value="1"/>

                          <div id="light${status.index+1}" class="white_content" style="border:0px;height:420px;">
                              <div class="close"><a href="javascript:void(0)" onclick="hide('light${status.index+1}')"> <img title="关闭" src="images/close.png" style="padding: 0px; margin: 0px; border: 0px;"></a></div>
                              <div class="con">
                                  <center><h3>修改用户信息</h3></center>
                                  <input type="hidden" name="u_id" value="${c.u_id}"/>
                                  <div class="form-group">
                                      <div class="input-group">
                                          <div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-user fa-fw" style="float:left;margin-left:-30px;"></i></div>
                                          <input id="u_name" name="u_name" type="text" value="${c.u_name}" onblur="return checkUser()" class="form-control" placeholder="username">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <div class="input-group">
                                          <div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-key fa-fw" style="float:left;margin-left:-30px;"></i></div>
                                          <input id="u_pwd" name="u_pwd" type="password" value="${c.u_pwd}" onblur="return checkUser()" class="form-control" placeholder="****">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <div class="input-group">
                                          <div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-telphone fa-fw" style="float:left;margin-left:-30px;"></i></div>
                                          <input id="u_telephone" name="u_telephone" type="text" value="${c.u_telephone}" onblur="return checkUser()" class="form-control" placeholder="telephone">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <div class="input-group">
                                          <div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-mail fa-fw" style="float:left;margin-left:-30px;"></i></div>
                                          <input id="u_email" name="u_email" type="text" value="${c.u_email}" onblur="return checkUser()" class="form-control" placeholder="email">
                                      </div>
                                  </div>
                                  <div class="margin-right-15 templatemo-inline-block">
                                      <input id="female${status.index+1}" type="radio" name="u_sex" value="true" checked="">
                                      <label for="female${status.index+1}" class="font-weight-400"><span></span>女</label>
                                  </div>
                                  <div class="margin-right-15 templatemo-inline-block">
                                      <input id="male${status.index+1}" type="radio" name="u_sex" value="false" checked="">
                                      <label for="male${status.index+1}" class="font-weight-400"><span></span>男</label>
                                  </div>
                                  <button type="submit" class="templatemo-blue-button width-100">修 改</button>
                              </div>
                          </div>
                      </form>
                   </c:forEach>
                  </tbody>
                  <div id="fade" class="black_overlay" style=""></div>
              </table>
            </div>
          </div>
      </div>
    </div>
          </table>
       <div class="pagination-wrap">
            <ul class="pagination">
                <li><a href="background/managesuperusers?pageNo=1">1</a></li>
                <li><a href="background/managesuperusers?pageNo=2">2</a></li>
                <li><a href="background/managesuperusers?pageNo=3">3 <span class="sr-only">(current)</span></a></li>
                <li><a href="background/managesuperusers?pageNo=4">4</a></li>
                <li><a href="background/managesuperusers?pageNo=5">5</a></li>
                <li>
                    <a href="background/managesuperusers?pageNo=${page.nextPage+1}" aria-label="Next">
                        <span aria-hidden="true"><i class="fa fa-play"></i></span>
                    </a>
                </li>
            </ul>
          </div>          
         <jsp:include page="/jsp/statics/foot.jsp"/>
    <!-- JS -->
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="js/openwindow_js.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/register.js"></script>
    <script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>
        </div>
        </div>
  </body>
</html>
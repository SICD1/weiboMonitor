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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>微博管理 - 微博舆情分析系统</title>

    <link rel='stylesheet' href='css/reset.css'>
    <link rel='stylesheet' href='http://libs.useso.com/js/jqueryui/1.10.4/css/jquery-ui.min.css'>
    <script src='js/prefixfree.min.js'></script>
    <link rel='stylesheet prefetch' href='css/icons.css'>
    <style class="cp-pen-styles">#container {
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: 15px;
        font-family: 'Helvetica', Arial, sans-serif;
        font-size: 90%;
        line-height: normal;
        font-smoothing: antialiased;
    }

    #frame {
        position: relative;
        width: 100%;
        height: 100%;
    }
    #frame .frame {
        position: relative;
        overflow: hidden;
    }
    #frame .frame .inset .image {
        cursor: pointer;
        cursor: zoom-in;
        background-position: center;
        background-size: cover;
    }
    #frame .frame .inset .info .title {
        font-size:15px;
        cursor: pointer;
        display: inline-block;
        font-weight: bold;
    }
    #frame .frame .inset .info .description {
        font-size:18px;
        display: block;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    #frame .frame .inset .info [class*='icon-'] {
        cursor: pointer;
        margin-left: 10px;
        display: inline-block;
        font-weight: bold;
    }
    #frame .frame .inset .info [class*='icon-']:before {
        margin-right: 5px;
        font-weight: normal;
    }

    .grid {
        display: flex;
        flex-flow: row wrap;
        color: #FFF;
    }
    .grid .frame {
        width: 250px;
        height: 400px;
        flex: 1 0 250px;
    }
    .grid .frame .inset {
        position: absolute;
        height: 400px;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: 10px;
    }
    .grid .frame .inset .image {
        position:absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
    }
    .grid .frame .inset .info {
        position: absolute;
        height:110px;
        left: 0;
        right: 0;
        bottom: 0;
        padding: 0px;
        background: rgba(53, 45, 45, 0.81);
        overflow: hidden;
    }
    .grid .frame .inset .info .shares {
        display: block;
        font-size: 110%;
        text-align: right;
        padding-right:10px;
    }

    .list {
        color: #333;
    }
    .list .frame {
        height: 75px;
        line-height: 75px;
        box-shadow: inset 0 1px #EEE;
    }
    .list .frame:first-child {
        box-shadow: none;
    }
    .list .frame .inset .image {
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        width: 55px;
        height: 55px;
        margin: auto 10px;
    }
    .list .frame .inset .info {
        position: absolute;
        top: 0;
        left: 75px;
        bottom: 0;
    }
    .list .frame .inset .info .title {
        float: left;
        font-size:15px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .list .frame .inset .info .description {
        font-size:18px;
        float: left;
        max-width: 40%;
        margin-left: 15px;
    }
    .list .frame .inset .info .shares {
        float: left;
        margin-left:10px;

        font-size: 150%;

    }

    .list .frame .inset .info .delete_btn{
        float:right;
        margin-right:80px;
    }
    </style>

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
    <script>
        var ck = function()
        {
            var x = confirm("Delete?");
        }

        function edit()
        {

        }
    </script>
</head>
<body>
<!-- Left column -->
<div class="templatemo-flex-row">
    <div class="templatemo-sidebar">
        <jsp:include page="/jsp/statics/left.jsp"/>
    </div>
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
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-content-widget no-padding">
                <div class="templatemo-content-widget white-bg">
                    <main id='container'>
                        <div class="templatemo-content-widget white-bg">
                            <header id='header'>
                                <span>视图:</span>
                                <button class='view-list' style="background-color:#39ADB4;color:white; height:40px;width:100px;border:0; margin-right:20px;">列表</button>
                                <button class='view-grid' style="height:40px;width:100px;border:0;">网格</button>
                            </header>
                        </div>
                        <ol class='list' id='frame'>
                            <c:forEach items="${page.result}" var="wb" >
                                <li class='frame' style="flex:1 0 440px;">
                                    <div class='inset'>
                                        <div class='image'></div>
                                        <div class='info' style="width:100%;">
                                            <div class='title'>${wb.wb_createdAt}</div>
                                            <div class='description'>${wb.wb_content}</div>
                                            <div class='shares'>
                                                <div class='icon-lik likes'>${wb.wb_likeNum}</div>
                                                <div class='icon-ask comments'>${wb.wb_commentsNum }</div>
                                            </div>
                                            <div class="delete_btn" style="">
                                                <a href="background/deleteWeibo?wb_id=${wb.wb_id}"
                                                   class="templatemo-edit-btn">Delete</a>
                                            </div>
                                        </div>

                                    </div>

                                </li>

                            </c:forEach>
                        </ol>
                    </main>

                </div>

                <div class="pagination-wrap">
                    <ul class="pagination">
                        <li><a href="background/weibosManager/1">1</a></li>
                        <li><a href="background/weibosManager/2">2</a></li>
                        <li><a href="background/weibosManager/3">3</a></li>
                        <li><a href="background/weibosManager/4">4</a></li>
                        <li><a href="background/weibosManager/5">5</a></li>
                        <li><a href="background/weibosManager/${page.nextPage}" aria-label="Next"><span aria-hidden="true"><i class="fa fa-play"></i></span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- JS -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
<script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->

<script src='http://libs.useso.com/js/jquery/1.11.0/jquery.min.js'>
</script><script src='http://libs.useso.com/js/jqueryui/1.10.4/jquery-ui.min.js'></script>
<script>$('#header button').on('click',function(){
    if ( $(this).hasClass('view-list') ) {
        $('.view-list').css("background-color","#39ADB4");
        $('.view-list').css("color","white");

        $('.view-grid').css("background-color","");
        $('.view-grid').css("color","");
        $('#frame').removeClass('grid').addClass('list');
    } else if ( $(this).hasClass('view-grid') ) {
        $('.view-grid').css("background-color","#39ADB4");
        $('.view-grid').css("color","white");

        $('.view-list').css("background-color","");
        $('.view-list').css("color","");
        $('#frame').removeClass('list').addClass('grid');
    }
});

$('.frame').each(function(){
    var images = ['images/demo/rmrb.jpg','images/demo/rmrb.jpg','images/demo/rmrb.jpg',
        'images/demo/rmrb.jpg'];
    $(this).find('.image')
            .css({ 'background-image': 'url('+images[Math.floor(Math.random()*images.length)]+')' });



});

</script>

<script>
    $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();
    });
</script>

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
<%@ page language="java" import="java.util.*"
          pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<HTML>
<HEAD>
    <title>关键词管理 - 微博舆情分析系统</title>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/ztreecss/demo.css" type="text/css">
    <link rel="stylesheet" href="css/ztreecss/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.excheck.js"></script>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };

        var zNodes =[
            { id:1, pId:0, name:"普通用户", open:true},

           { id:11, pId:1, name:"今日热点",open:true},
            { id:111, pId:11, name:"展示", checked:true},
            { id:112, pId:11, name:"删除"},
            { id:113, pId:11, name:"修改"},
            { id:114, pId:11, name:"添加"},

            { id:12, pId:1, name:"最近要闻", open:true},
            { id:121, pId:12, name:"展示", checked:true},
            { id:122, pId:12, name:"删除"},
            { id:123, pId:12, name:"修改"},

            { id:13, pId:1, name:"地域分布图", open:true},
            { id:131, pId:13, name:"展示", checked:true},
            { id:132, pId:13, name:"删除"},
            { id:133, pId:13, name:"修改"},

            { id:14, pId:1, name:"特定关键词地图", open:true},
            { id:141, pId:14, name:"展示", checked:true},
            { id:142, pId:14, name:"删除"},
            { id:143, pId:14, name:"修改"},

            { id:15, pId:1, name:"搜索功能", open:true},
            { id:151, pId:15, name:"提供", checked:true},
            { id:152, pId:15, name:"隐藏"},


//            { id:14, pId:1, name:"随意勾选 1-3"},
//            { id:2, pId:0, name:"随意勾选 2", open:true},
//            { id:21, pId:2, name:"随意勾选 2-1"},
//            { id:22, pId:2, name:"随意勾选 2-2", open:true},
//            { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
//            { id:222, pId:22, name:"随意勾选 2-2-2"},
//            { id:223, pId:22, name:"随意勾选 2-2-3"},
//            { id:23, pId:2, name:"随意勾选 2-3", checked:false}
        ];

        var clearFlag = false;
        function onCheck(e, treeId, treeNode) {
            count();
            if (clearFlag) {
                clearCheckedOldNodes();
            }
        }
        function clearCheckedOldNodes() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getChangeCheckedNodes();
            for (var i=0, l=nodes.length; i<l; i++) {
                nodes[i].checkedOld = nodes[i].checked;
            }
        }
        function count() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    checkCount = zTree.getCheckedNodes(true).length,
                    nocheckCount = zTree.getCheckedNodes(false).length,
                    changeCount = zTree.getChangeCheckedNodes().length;
            $("#checkCount").text(checkCount);
            $("#nocheckCount").text(nocheckCount);
            $("#changeCount").text(changeCount);

        }
        function createTree() {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            count();
            clearFlag = $("#last").attr("checked");
        }

        $(document).ready(function(){
            createTree();
            $("#init").bind("change", createTree);
            $("#last").bind("change", createTree);
        });


        function onCheck(e,treeId,treeNode){
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
                    nodes=treeObj.getCheckedNodes(true),
                    v="";
            var menu_id = 0;
            for(var i=0;i<nodes.length;i++){
                v+=nodes[i].name + ",";
                //alert(nodes[i].id); //获取选中节点的值
                menu_id = menu_id + nodes[i].id+",";
            }
            $("#hid_checked_menu").val(menu_id);

        }

        function getMenuId() {
            alert($("#hid_checked_menu").val());

            $.ajax({
                type:'post',
                url:'menuPriRoleAssign',
                success:function (msg) {
                    alert(1);
                }
            });
        }

        //-->
    </SCRIPT>
</HEAD>

<BODY>



<div class="templatemo-flex-row">


    <div class="templatemo-flex-row">
        <div class="templatemo-sidebar">
            <a href="background/manager">
                <header class="templatemo-site-header">
                    <!--  <div class="square"></div> -->
                    <h1 style="font-family: 华文行楷;font-size: 27px;padding-top:10px; color: black">微博舆情分析系统</h1>
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
        </div>
        </div>


        <div class="templatemo-content-widget white-bg">
            <div class="panel-heading border-radius-10" style="background-color:#39ADB4;border-radius: 8px;">
                <h2 style="color:white">权限管理(用户类型：普通用户)</h2>
            </div>


                <div class="content_wrap">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>
                <input type="text" id="hid_checked_menu" name="hid_checked_menu"/>
                <input type="submit" onclick="getMenuId()"  value="提交"/>


        </div>

    </div>
</div>


</BODY>
</HTML>
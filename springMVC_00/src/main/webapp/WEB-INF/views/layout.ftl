<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>党员信息管理系统</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="/user/selectAll">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/user/loginOut">退了</a></li>
        </ul>
    </div>

    <#include "sidebar.ftl"/>
    <#--    <div class="layui-side layui-bg-black">-->
<#--        <div class="layui-side-scroll">-->
<#--            <!-- 左侧导航区域（可配合layui已有的垂直导航） &ndash;&gt;-->
<#--            <ul class="layui-nav layui-nav-tree"  lay-filter="test">-->
<#--                <li class="layui-nav-item layui-nav-itemed">-->
<#--                    <a class="" href="javascript:;">系统管理</a>-->
<#--                    <dl class="layui-nav-child">-->
<#--                        <dd><a href="/user/lookUser">用户列表</a></dd>-->
<#--                        <dd><a href="/user/lookUserGrade">成绩列表</a></dd>-->
<#--                        <dd><a href="/menu/lookMenu">菜单管理</a></dd>-->
<#--                        <dd><a href="">超链接</a></dd>-->
<#--                    </dl>-->
<#--                </li>-->
<#--                <li class="layui-nav-item">-->
<#--                    <a href="javascript:;">解决方案</a>-->
<#--                    <dl class="layui-nav-child">-->
<#--                        <dd><a href="javascript:;">列表一</a></dd>-->
<#--                        <dd><a href="javascript:;">列表二</a></dd>-->
<#--                        <dd><a href="">超链接</a></dd>-->
<#--                    </dl>-->
<#--                </li>-->
<#--                <li class="layui-nav-item"><a href="">云市场</a></li>-->
<#--                <li class="layui-nav-item"><a href="">发布商品</a></li>-->
<#--            </ul>-->
<#--        </div>-->
<#--    </div>-->

    <div class="layui-body">
        <!-- 内容主体区域 -->

        <div style="padding: 15px;">内容主体区域</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../../layui/layui.js"></script>
<script>
    //JavaScript代码区域
    var that = this;
    layui.use('element', function(){
        var element = layui.element;
    });

    window.onload=function(){
        $.ajax({
            url:'/menu/selectAll',
            type:'get',
            contentType:'json/application,charset=utf-8',
            dataType:'json',
            success:function (data) {
                that.menu=data.data
            }
        })
    }
</script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</body>
</html>
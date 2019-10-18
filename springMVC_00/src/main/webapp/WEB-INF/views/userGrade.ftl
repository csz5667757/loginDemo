<!DOCTYPE html>
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


    <!--    <div class="layui-side layui-bg-black">-->
<!--        <div class="layui-side-scroll">-->
<!--            &lt;!&ndash; 左侧导航区域（可配合layui已有的垂直导航） &ndash;&gt;-->
<!--            <ul class="layui-nav layui-nav-tree" lay-filter="test">-->
<!--                <li class="layui-nav-item layui-nav-itemed">-->
<!--                    <a class="" href="javascript:;">系统管理</a>-->
<!--                    <dl class="layui-nav-child">-->
<!--                        <dd><a href="/user/lookUser">用户列表</a></dd>-->
<!--                        <dd><a href="/user/lookUserGrade">成绩列表</a></dd>-->
<!--                        <dd><a href="/menu/lookMenu">菜单管理</a></dd>-->
<!--                        <dd><a href="">超链接</a></dd>-->
<!--                    </dl>-->
<!--                </li>-->
<!--                <li class="layui-nav-item">-->
<!--                    <a href="javascript:;">解决方案</a>-->
<!--                    <dl class="layui-nav-child">-->
<!--                        <dd><a href="javascript:;">列表一</a></dd>-->
<!--                        <dd><a href="javascript:;">列表二</a></dd>-->
<!--                        <dd><a href="">超链接</a></dd>-->
<!--                    </dl>-->
<!--                </li>-->
<!--                <li class="layui-nav-item"><a href="">云市场</a></li>-->
<!--                <li class="layui-nav-item"><a href="">发布商品</a></li>-->
<!--            </ul>-->
<!--        </div>-->
<!--    </div>-->

    <div class="layui-body">
        <table class="layui-hide" id="grade" lay-filter="grade"></table>
    </div>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-form-item"style="margin-top:10px">
            <input type="text" class="layui-input-block" style="margin-left:0px" placeholder="搜索各科成绩前n名" id="top">
            <button class="layui-btn layui-btn-sm" lay-event="sousuo">搜索</button>
            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        </div>
    </script>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../../layui/layui.js"></script>
<script>
    layui.use(['table', 'layer','element'], function () {
        var element = layui.element;
        var table = layui.table;
        var $ = layui.jquery, layer = layui.layer;
        table.render({
            elem: '#grade'
            , url: '/grade'
            , toolbar: '#toolbarDemo'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {type: 'radio'}
                , {field: 'id', width: 200, title: 'ID', sort: true}
                , {field: 'name', width: 200, title: '用户名'}
                , {field: 'subject', width: 200, title: '学科'}
                , {field: 'grade', width: 200, title: '分数', sort: true}
            ]]
        });
        //头工具栏事件
        table.on('toolbar(grade)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;  //获取选中行数据
                    layer.alert(JSON.stringify(data));
                    break;
                case 'sousuo':
                    var n = document.getElementById("top").value;
                    var tableIns = table.render({
                        elem: '#grade'
                        , toolbar: '#toolbarDemo'
                        , cols: [[
                            {type: 'radio'}
                            , {field: 'id', width: 200, title: 'ID', sort: true}
                            , {field: 'name', width: 200, title: '用户名'}
                            , {field: 'subject', width: 200, title: '学科'}
                            , {field: 'grade', width: 200, title: '分数', sort: true}
                        ]] //设置表头
                        , url: '/topNgrade' //设置异步接口
                        , id: 'grade'
                    });
                    tableIns.reload({
                        where: { //设定异步数据接口的额外参数，任意设
                            top: n
                        }
                    });
            }
        });
    });
</script>
</body>
</html>
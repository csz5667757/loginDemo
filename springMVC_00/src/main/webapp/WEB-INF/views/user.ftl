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
                <a href="../denglu">其它系统</a>
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
<#--            <ul class="layui-nav layui-nav-tree" lay-filter="test">-->
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
        <table class="layui-hide" id="test" lay-filter="test"></table>
    </div>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">新增用户</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除用户</button>
            <button data-method="offset" data-type="auto" class="layui-btn layui-btn-sm" id="layer">居中弹出</button>
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
            elem: '#test'
            , url: '/user/selectAll'
            , toolbar: '#toolbarDemo'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {type: 'radio'}
                , {field: 'id', width: 200, title: 'ID', sort: true}
                , {field: 'username', width: 200, title: '用户名'}
                , {field: 'password', width: 200, title: '密码', sort: true}
                , {field: 'name', width: 200, title: '姓名'}
                , {field: 'address', title: '地址', width: '30%', minWidth: 300} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ]]
        });
        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;  //获取选中行数据
                    layer.alert(JSON.stringify(data));
                    break;
                case 'add':
                    layer.open({
                        title: "添加用户"
                        , btnAlign: 'c' //按钮居中
                        , shade: 0//不显示遮罩
                        , area: '500px;'
                        , content: "<form class='layui-form' action='/user/addUserTable' method='post'>" +
                            "<div class='layui-form-item'>" +
                            "<label  class='layui-form-label' >用户名：</label>" + "<div class='layui-input-block'>" +
                            "<input class='layui-input' name='username' required></div></div>" +

                            "<div class='layui-form-item'>" +
                            "<label class='layui-form-label' style=''>密码：</label>" + "<div class='layui-input-block'>" +
                            "<input name='password' type='password' class='layui-input' required></div></div>" +

                            "<div class='layui-form-item'>" +
                            "<label class='layui-form-label'>姓名：</label>" + "<div class='layui-input-block'>" +
                            "<input name='name' class='layui-input' required></div></div>" +

                            "<div class='layui-form-item'>" +
                            "<label class='layui-form-label'>地址：</label>" + "<div class='layui-input-block'>" +
                            "<input name='address'class='layui-input' required></div></div>" +

                            "<button class='layui-btn layui-btn-primary'  type='submit' style='margin-top: 10px;: 20px; margin-left:200px'>提交</button>" +
                            "</form>"
                    })
                    break;
                case 'delete':
                    var data = checkStatus.data;
                    if (data.length == 0) {
                        layer.alert("请选择一个用户删除");
                        break;
                    }
                    var id = data[0].id;
                    $.ajax({
                        url: "/user/deleteUserById",
                        type: "GET",
                        contentType: "application/json,charset=utf-8",
                        dataType: "JSON",
                        data: {id: id},
                        success: function (data) {
                            layer.open({
                                title: "结果",
                                content: "删除成功"
                            });
                            table.reload("test");
                        }
                    })
            }
            ;
        });

        //触发事件
        var active = {
            setTop: function () {
                var that = this;
            }
            , offset: function (othis) {
                var type = othis.data('type')
                    , text = othis.text();
                layer.open({
                    type: 1
                    , offset: type
                    , id: 'layerDemo' + type //防止重复弹出
                    , content: '<div style="padding: 20px 20px">' + text + '</div>'
                    , btn: '关闭全部'
                    , btnAlign: 'c' //按钮居中
                    , shade: 0
                    , yes: function () {
                        layer.closeAll();
                    }
                });
            }
        };

        $('#layer').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });
</script>
</body>
</html>
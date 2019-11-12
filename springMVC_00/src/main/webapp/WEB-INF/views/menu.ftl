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
<#--            <ul class="layui-nav layui-nav-tree" lay-filter="test">-->
<#--                <li class="layui-nav-item"><a href="">首页</a></li>-->
<#--                <#list menu as menus>-->
<#--                    <#if menus?? && menus.menu_type ==1>-->
<#--                        <li class="layui-nav-item  ${(menus?? && menus.id==4)?string('layui-nav-itemed','')}" >-->
<#--&lt;#&ndash;                            ${(item.external?? && item.external)?string('target="_blank"','')}&ndash;&gt;-->
<#--                            <a>${menus.menu_name}</a>-->
<#--                            <dl class="layui-nav-child">-->
<#--                                <#list son as sons>-->
<#--                                    <#if sons?? && sons.pid ==menus.id>-->
<#--                                        <dd><a href="${sons.url}">${sons.menu_name}</a></dd>-->
<#--                                    </#if>-->
<#--                                </#list>-->
<#--                            </dl>-->
<#--                        </li>-->
<#--                    </#if>-->
<#--                </#list>-->
<#--                &lt;#&ndash;                <li class="layui-nav-item">&ndash;&gt;-->
<#--                &lt;#&ndash;                    <a href="javascript:;">解决方案</a>&ndash;&gt;-->
<#--                &lt;#&ndash;                    <dl class="layui-nav-child">&ndash;&gt;-->
<#--                &lt;#&ndash;                        <dd><a href="javascript:;">列表一</a></dd>&ndash;&gt;-->
<#--                &lt;#&ndash;                        <dd><a href="javascript:;">列表二</a></dd>&ndash;&gt;-->
<#--                &lt;#&ndash;                        <dd><a href="">超链接</a></dd>&ndash;&gt;-->
<#--                &lt;#&ndash;                    </dl>&ndash;&gt;-->
<#--                &lt;#&ndash;                </li>&ndash;&gt;-->
<#--                &lt;#&ndash;                <li class="layui-nav-item"><a href="">云市场</a></li>&ndash;&gt;-->
<#--                &lt;#&ndash;                <li class="layui-nav-item"><a href="">发布商品</a></li>&ndash;&gt;-->
<#--            </ul>-->
<#--        </div>-->
<#--    </div>-->

    <div class="layui-body">
        <table class="layui-hide" id="menu" lay-filter="menu">
        </table>
    </div>


    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
            <button class="layui-btn layui-btn-sm" lay-event="add">新增菜单</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除菜单</button>
            <button data-method="offset" data-type="auto" class="layui-btn layui-btn-sm" id="layer">居中弹出</button>
        </div>
    </script>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../../layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table'], function () {
        var element = layui.element;
        var table = layui.table;
        table.render({
            elem: "#menu"
            , url: "/menu/selectAll"
            ,toolbar:"#toolbarDemo"
            , cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', width: 200, title: "id", sort: true},
                {field: 'menu_name', width: 200, title: "菜单名称"},
                {field: 'menu_type', width: 200, title: "菜单类型"},
                {field: 'pid', width: 200, title: "父级菜单id"},
                {fixed: 'right', width: 178, align: 'center', toolbar: '#barDemo'}
            ]]
        })

        //监听工具条
        table.on('tool(menu)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('真的删除"'+data.menu_name+'"么', function (index) {
                    $.ajax({
                        conentType:"json/application,charset=utf-8"
                        ,url:"/menu/deleteMenuById"
                        ,dataType:"json"
                        ,type:"POST"
                        ,data:{
                            id:data.id
                        }
                    })
                    // obj.del();
                    layer.close(index);
                    table.reload("menu");
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    title: "编辑菜单"
                    , btnAlign: 'c' //按钮居中
                    , shade: 0//不显示遮罩
                    ,btn:null
                    , area: '500px;'
                    , content: "<form class='layui-form' action='/menu/UpdateMenu' method='post'>" +
                        "<div class='layui-form-item'>" +
                        "<label  class='layui-form-label' >菜单名称：</label>" + "<div class='layui-input-block'>" +
                        "<input class='layui-input' name='menu_name' required "+"value="+JSON.stringify(data.menu_name) +"></div></div>" +

                        "<div class='layui-form-item'>" +
                        "<label class='layui-form-label' style=''>菜单类型：</label>" + "<div class='layui-input-block'>" +
                        "<input name='menu_type' type='text' class='layui-input' required "+"value="+JSON.stringify(data.menu_type) +"></div></div>" +

                        "<div class='layui-form-item'>" +
                        "<label class='layui-form-label'>父菜单id：</label>" + "<div class='layui-input-block'>" +
                        "<input name='pid' class='layui-input' "+"value="+JSON.stringify(data.pid) +"></div></div>" +

                        "<div class='layui-form-item'>" +
                        "<label class='layui-form-label'>url：</label>" + "<div class='layui-input-block'>" +
                        "<input name='url'class='layui-input' "+"value="+JSON.stringify(data.url) +">" +
                        "<input name='id' class='layui-input' type='hidden' value="+JSON.stringify(data.id)+">"+
                        "</div></div>" +

                        "<button class='layui-btn layui-btn-primary'  type='submit' style='margin-top: 10px;: 20px; margin-left:200px'>提交</button>" +
                        "</form>"
                    ,success:function (layero,index) {
                        console.log(layero);
                    }
                })
            }
        });

        //头工具栏事件
        table.on('toolbar(menu)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;  //获取选中行数据
                    layer.alert(JSON.stringify(data));
                    break;
                case 'add':
                    layer.open({
                        title: "添加菜单"
                        , btnAlign: 'c' //按钮居中
                        , shade: 0//不显示遮罩
                        ,btn:null
                        , area: '500px;'
                        , content: "<form class='layui-form' action='/menu/AddMenu' method='post'>" +
                            "<div class='layui-form-item'>" +
                            "<label  class='layui-form-label' >菜单名称：</label>" + "<div class='layui-input-block'>" +
                            "<input class='layui-input' name='menu_name' required></div></div>" +

                            "<div class='layui-form-item'>" +
                            "<label class='layui-form-label' style=''>菜单类型：</label>" + "<div class='layui-input-block'>" +
                            "<input name='menu_type' type='text' class='layui-input' required></div></div>" +

                            "<div class='layui-form-item'>" +
                            "<label class='layui-form-label'>父菜单id：</label>" + "<div class='layui-input-block'>" +
                            "<input name='pid' class='layui-input' ></div></div>" +

                            "<div class='layui-form-item'>" +
                            "<label class='layui-form-label'>url：</label>" + "<div class='layui-input-block'>" +
                            "<input name='url'class='layui-input' ></div></div>" +

                            "<button class='layui-btn layui-btn-primary'  type='submit' style='margin-top: 10px;: 20px; margin-left:200px'>提交</button>" +
                            "</form>"
                    })
                    break;
            };
        });

        $('.barDemo .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>
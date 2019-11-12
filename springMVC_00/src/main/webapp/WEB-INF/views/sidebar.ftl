<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item"><a href="">首页</a></li>
            <#list menu as menus>
                <#if menus?? && menus.menu_type ==1>
                    <li class="layui-nav-item  ${(menus?? && menus.id==4)?string('layui-nav-itemed','')}" >
                        <#--                            ${(item.external?? && item.external)?string('target="_blank"','')}-->
                        <a>${menus.menu_name}</a>
                        <dl class="layui-nav-child">
                            <#list son as sons>
                                <#if sons?? && sons.pid ==menus.id>
                                    <dd><a href="${sons.url}">${sons.menu_name}</a></dd>
                                </#if>
                            </#list>
                        </dl>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>
</div>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/11 0011
  Time: 下午 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/denglu" method="post">
        <label>用户名：</label>
        <input name="username" type="text" id="username">
        <label>密码：</label>
        <input name="password" type="text" id="password">
<%--        <label>学号：</label>--%>
<%--        <input name="id" type="text">--%>
        <button type="submit">提交</button>
    </form>
</body>
</html>
<script>

</script>

<style type="text/css">
    form {
        text-align: center;
        font-size: 20px;
    }
</style>
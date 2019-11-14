<%--
  Created by IntelliJ IDEA.
  User: Mos
  Date: 2019/10/24
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看管理员信息</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrapValidator.css" />

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="bootstrap/js/jquery.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrapValidator.js"></script>
    <style>
        body{
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="panel-heading h1 bg-success text-info">查看管理员信息</div>
        <div class="panel-body">
            <form class="form-horizontal">
                <table class="table table-striped text-center">
                    <tr>
                        <td>管理员姓名</td>
                        <td>${sessionScope.admin.aname}</td>
                    </tr>
                    <tr>
                        <td>电话号码</td>
                        <td>${sessionScope.admin.asphone}</td>
                    </tr>
                    <tr>
                        <td>用户名</td>
                        <td>${sessionScope.admin.aacount}</td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td>${sessionScope.admin.apassword}</td>
                    </tr>
                </table>
            </form>
        </div>
    </>
</body>
</html>

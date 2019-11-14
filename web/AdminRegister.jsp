<%--
  Created by IntelliJ IDEA.
  User: Mos
  Date: 2019/10/24
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员注册</title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrapValidator.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="bootstrap/js/jquery.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/bootstrapValidator.js"></script>
    <style>
        body{
            margin-top: 30px;
            background-image: url(img/back.png);
            background-repeat: repeat-x;
        }
        .h3{
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container text-center">
    <div>
        <h3 class="h3">管理员注册</h3>
        <form class="form-horizontal" action="BookMangerServlet?action=AdminRegister" method="post" id="adminRes" enctype="multipart/form-data">
            <div class="form-group">
                <label for="adminpic" class="control-label col-sm-4">选择头像:</label>
                <div class="col-sm-4">
                    <input type="file" name="adminpic" id="adminpic" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <label for="aname" class="control-label col-sm-4">姓名：</label>
                <div class="col-sm-4">
                    <input type="text" id="aname" name="aname" class="form-control" placeholder="姓名" />
                </div>
            </div>
            <div class="form-group">
                <label for="aacount" class="control-label col-sm-4">账户名：</label>
                <div class="col-sm-4">
                    <input type="text" id="aacount" name="aacount" class="form-control" placeholder="账户名" />
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="control-label col-sm-4">密码：</label>
                <div class="col-sm-4">
                    <input type="password" id="password" name="apassword" class="form-control" placeholder="密码" />
                </div>
            </div>
            <div class="form-group">
                <label for="relpassword" class="control-label col-sm-4">确认密码：</label>
                <div class="col-sm-4">
                    <input type="password" id="relpassword" name="relapassword" class="form-control" placeholder="密码" />
                </div>
            </div>
            <div class="form-group">
                <label for="tel" class="control-label col-sm-4">手机号码：</label>
                <div class="col-sm-4">
                    <input type="text" id="tel" name="asphone" class="form-control" placeholder="手机号码" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4 col-sm-offset-4">
                    <button type="submit" class="btn btn-success form-control">注册</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3">
                    <label><a href="AdminLogin.jsp">登录</a></label>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $(function () {
       $("#adminRes").bootstrapValidator({
           feedbackIcons: {
               valid: 'glyphicon glyphicon-ok',
               invalid: 'glyphicon glyphicon-remove',
               validating: 'glyphicon glyphicon-refresh'
           },
           fields: {
               aname: {
                   validators: {
                       notEmpty: {
                           message: '姓名不能为空'
                       }
                   }
               },
               aacount: {
                   validators: {
                       notEmpty: {
                           message: '用户名不能为空'
                       },
                       stringLength: {
                           min:4,
                           max:18,
                           message: '用户名长度必须在4到18位之间'
                       },
                       regexp: {
                           regexp: /^[a-zA-Z0-9_]+$/,
                           message: '用户名只能包含字母、数字和下划线'
                       },
                       remote: {
                           type: 'POST',  //请求方式 get post
                           url: 'BookMangerServlet?action=verifyAacount', //发送的Servlet路径
                           message: '用户名已存在',
                           date: {
                               name: $('#aacount').val()
                           },
                           delay: 500 //0.5秒刷新
                       }
                   }
               },
               apassword: {
                   validators: {
                       notEmpty: {
                           message: '密码不能为空'
                       },
                       regexp: {
                           regexp: /^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)/,
                           message: '密码必须包含大小写字母、数字或下划线'
                       },
                       stringLength: {
                           min: 6,
                           max: 18,
                           message: '密码长度必须在6到18位之间'
                       },
                       different: {
                           field: 'aacount',
                           message: '密码不能和用户名相同'
                       }
                   }
               },
               relapassword: {
                   validators: {
                       notEmpty: {
                           message: '确认密码不能为空'
                       },
                       identical: {
                           field: 'apassword',
                           message: '两次密码不一致'
                       }
                   }
               },
               asphone: {
                   validators: {
                       notEmpty: {
                           message: '电话号码不能为空'
                       },
                       regexp: {
                           regexp:/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/,
                           message: '这好像不是一个电话号码'
                       }
                   }
               }
           }
       });
    });
</script>
</html>

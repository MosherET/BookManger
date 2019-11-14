<%--
  Created by IntelliJ IDEA.
  User: Mos
  Date: 2019/10/23
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!--1.h5文档-->
    <meta charset="utf-8">
    <!--2.移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!--3.引入核心css-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css">
    <!--4.引入jquery-->
    <script src="bootstrap/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <!--引入bootstrap核心js-->
    <script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="bootstrap/js/bootstrapValidator.js" type="text/javascript" charset="utf-8"></script>
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
    <h3 class="h3">管理员登录</h3>
    <div>
        <form action="BookMangerServlet?action=AdminLogin" id="adminRegister" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="aacount" class="col-md-4 control-label">用户名：</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="aacount" name="aacount" placeholder="手机号/用户名/邮箱" />
                </div>
            </div>
            <div class="form-group">
                <label for="pwd" class="col-md-4 control-label">密码：</label>
                <div class="col-md-4">
                    <input type="password" class="form-control" name="apassword" id="pwd" placeholder="密码" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-4 col-md-offset-4 text-left">
                    <div class="checkbox">
                        <label><input type="checkbox" />记住密码 </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4 col-md-offset-4">
                    <input type="submit" class="btn btn-success form-control" value="登录" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4 col-md-offset-4">
                    <div>
                        <label class="col-md-offset-8"><a href="AdminRegister.jsp">注册|</a></label>
                        <label><a href="#">忘记密码？</a></label>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="alert alert-warning alert-dismissible" role="alert">
        <c:if test="${sessionScope.msg != null}">
            <p>${sessionScope.msg}</p>
        </c:if>
    </div>
</div>
</body>
<script>
    $(function () {
       $("#adminRegister").bootstrapValidator({
           feedbackIcons: {
               valid: 'glyphicon glyphicon-ok',
               invalid: 'glyphicon glyphicon-remove',
               validating: 'glyphicon glyphicon-refresh'
           },
           fields: {
             aacount: {
                 validators: {
                     notEmpty: {
                         message: '用户名不能为空'
                     }
                 }
             },
             apassword: {
                 validators: {
                     notEmpty: {
                         message: '密码不能为空'
                     }
                 }
             }
         }
       });
    });
</script>
</html>


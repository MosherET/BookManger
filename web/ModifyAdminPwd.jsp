<%--
  Created by IntelliJ IDEA.
  User: Mos
  Date: 2019/10/25
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改管理员密码</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrapValidator.css"/>

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
        <div class="panel-heading h1 bg-success text-info">修改管理员密码</div>
        <div class="panel-body">
            <form class="form-horizontal" action="BookMangerServlet?action=ModifyApassword" method="post" id="modifyPwd">
                <div class="form-group">
                    <label class="control-label col-sm-4" for="oldPassword">旧密码</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="旧密码" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4" for="newPassword">新密码</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="新密码" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4" for="relPassword">确认密码</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="relPassword" name="relPassword" placeholder="确认密码" />
                    </div>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-info">修改</button>
                </div>

            </form>
        </div>
    </div>
</body>
<script>
    $(function () {
        $("#modifyPwd").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                oldPassword: {
                    validators: {
                        notEmpty: {
                            message: '旧密码不能为空'
                        },
                        remote: {
                            url: 'BookMangerServlet?action=verifyApassword',
                            message: '密码错误',
                            type: 'POST',
                            delay: 1000,
                            data:function (validator) {
                                return {
                                    act: 'admin_registered',
                                    oldPassword: $("input[oldPassword='oldPassword']").val
                                };
                            }
                        }
                    }
                },
                newPassword: {
                    validators: {
                        notEmpty: {
                            message: '新密码不能为空'
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
                            field: 'oldPassword',
                            message: '新密码不能和旧密码相同'
                        }
                    }
                },
                relPassword: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical: {
                            field: 'newPassword',
                            message: '两次密码不一致'
                        }
                    }
                }
            }
        });
    });
</script>
</html>

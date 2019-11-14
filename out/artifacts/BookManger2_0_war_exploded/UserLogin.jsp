<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--1.h5文档-->
    <meta charset="utf-8">
    <!--2.移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户登录</title>
    <!--3.引入核心css-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css">
    <!--4.引入jquery-->
    <script src="bootstrap/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <!--引入bootstrap核心js-->
    <script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="bootstrap/js/bootstrapValidator.js" type="text/javascript" charset="utf-8"></script>
    <style>
        body {
            margin-top: 30px;
            background-image: url(img/back.png);
            background-repeat: repeat-x;
        }

        .header {
            margin-bottom: 20px;
        }

    </style>

    <script type="text/javascript">
        $(function () {

            //获取表单
            $("#userLogin").bootstrapValidator({

                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },

                //书写校验
                fields: {
                    //校验用户名
                    uacount: {
                        //用户名校验规则
                        validators: {
                            notEmpty: {message: '用户名不能为空'},
                            stringLength: {min: 1, max: 20, message: '用户名长度必须在1~20之间'},
                            remote:{
                                type:'post',
                                url:'BookMangerServlet?action=checkAccountExist',
                                message:'用户不存在',
                                delay:500

                            }
                        }
                    },

                    //验证密码
                    upassword: {
                        //用户验证密码规则
                        validators: {
                            notEmpty: {message: '密码不能为空'},
                            stringlength: {min: 4, max: 32, message: '密码长度必须在4~32位之间'}
                        }
                    }
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="mainForm">
        <div class="header"><h3 class="text-center">用户登录</h3></div>
        <div>
            <form action="BookMangerServlet?action=UserLogin" id="userLogin" method="post" class="form-horizontal">
                <div class="form-group">
                    <label for="uacount" class="col-md-4 control-label">用户名：</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="uacount" name="uacount" placeholder="手机号/用户名/邮箱"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="upassword" class="col-md-4 control-label">密码：</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="upassword" name="upassword" placeholder="密码"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="checkbox">
                            <label><input type="checkbox"/>记住密码 </label>
                            <label class="col-md-offset-6"><a href="#">忘记密码？</a></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4 col-md-offset-4">
                        <input type="submit" class="btn btn-success form-control" value="登录">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="text-right">
                            <label class="col-md-offset-8"><a href="UserRegister.html">注册 | </a></label>
                            <label><a href="#">忘记密码？</a></label>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- 出错显示的信息框 -->
        <div class="alert alert-warning alert-dismissible col-md-4 col-md-offset-4" id="erroBox" role="alert">
            <button type="button" class="close" data-dismiss="alert" >
                <span>&times;</span>
            </button>
            <strong id="">${login_erro}</strong>
        </div>
    </div>


</div>
</body>
</html>

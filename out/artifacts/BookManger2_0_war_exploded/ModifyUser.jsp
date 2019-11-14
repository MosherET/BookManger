<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="bootstrap/js/jquery.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/bootstrapValidator.js" type="text/javascript" charset="utf-8"></script>
    <style>
        body {
            margin-top: 30px;
        }


    </style>
    <script>
        $(function () {


            //获取表单
            $("#updateUser").bootstrapValidator({

                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },

                //书写校验
                fields: {

                    //校验用户姓名
                    uname: {
                        //用户名校验规则
                        validators: {
                            notEmpty: {message: '用户名不能为空'},
                            stringLength: {min: 2, max: 5, message: '用户名长度必须在2~5之间'}
                        }
                    },

                    //校验用户名
                    uacount: {
                        //用户名校验规则
                        validators: {
                            notEmpty: {message: '用户名不能为空'},
                            stringLength: {min: 1, max: 20, message: '用户名长度必须在1~20之间'}
                            // remote: {
                            //     type: 'POST',
                            //     url: 'userServlet?action=checkAccount',
                            //     message: '用户名已经被使用，请重新输入',
                            //     delay: 2000
                            // }
                        }
                    },

                    //验证密码
                    upassword: {
                        //用户验证密码规则
                        validators: {
                            notEmpty: {message: '密码不能为空'},//非空
                            stringlength: {min: 4, max: 32, message: '密码长度必须在4~32位之间'},//长度限制
                        }
                    },

                    //验证手机号
                    usphone: {
                        //手机号验证规则
                        validators:{
                            notEmpty:{message:'手机号不能为空'},
                            regexp:{messqge:'请输入有效手机号',regexp:/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/}
                        }
                    },

                    //验证日期
                    regdate: {
                        //日期验证规则
                        validators: {
                            notEmpty: {message: '日期不能为空'}
                        }
                    },

                    //验证头像
                    photo: {
                        //验证头像规则
                        validators: {
                            notEmpty: {message: '请上传头像'}
                        }
                    }
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="panel-heading h1 bg-success text-info">修改用户</div>
    <div class="panel-body">
        <div class="">
            <form class="form-horizontal text-center" enctype="multipart/form-data" id="updateUser" action="BookMangerServlet?action=UpdateUser" method="post">
                <input type="hidden" name="currentPage" value="${page.currentPage}">
                <div class="form-group">
                    <label for="uid" class="col-sm-4 control-label">用户编号</label>
                    <div class="input-group col-sm-8">
                        <input type="text" class="form-control" id="uid" name="uid" value="${user.uid}" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="uname" class="col-sm-4 control-label">真实姓名</label>
                    <div class="input-group col-sm-8">
                        <input type="text" class="form-control" id="uname" name="uname" value="${user.uname}" placeholder="请输入姓名"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="uacount" class="col-sm-4 control-label">用户名</label>
                    <div class="input-group col-sm-8">
                        <input type="text" class="form-control" id="uacount" name="uacount" value="${user.uacount}" readonly="readonly" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="upassword" class="col-sm-4 control-label">密码</label>
                    <div class="input-group col-sm-8">
                        <input type="password" class="form-control" id="upassword" name="upassword"  placeholder="请输入密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="usphone" class="col-sm-4 control-label">手机号码</label>
                    <div class="input-group col-sm-8">
                        <input type="text" class="form-control" id="usphone" name="usphone" value="${user.usphone}" placeholder="请输入手机号"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="regdate" class="col-sm-4 control-label">注册日期</label>
                    <div class="input-group col-sm-8">
                        <input type="date" class="form-control" id="regdate" name="regdate" value="${user.regdate}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="photo" class="col-sm-4 control-label">头像：</label>
                    <div class="input-group col-sm-8 ">
                        <input type="file" id="photo" name="photo" class="form-control" value="${user.touxiang}" />
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-warning" type="submit">修改信息</button>
                    <button class="btn btn-primary" type="reset">重新填写</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

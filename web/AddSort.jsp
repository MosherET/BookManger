<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!--1.文档要是html5<!DOCTYPE html>-->
    <meta charset="utf-8">
    <!--2.移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!--3.引入核心的css-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css">
    <!--4.引入jQuery-->
    <script src="bootstrap/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <!--5.引入bootstrap核心的js-->
    <script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="bootstrap/js/bootstrapValidator.js" type="text/javascript" charset="utf-8"></script>
    <style>
        body {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel-heading h1 bg-success text-info">添加分类</div>
    <div class="panel-body">
        <div>
            <form action="BookMangerServlet?action=AddSort" id="add" method="post" class="form-horizontal">
                <div class="form-group">
                    <label for="fname" class="control-label col-sm-4">分类名称</label>
                    <div class="col-sm-4">
                        <input id="fname" type="text" name="fname" class="form-control" placeholder="请输入分类名称"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 col-md-offset-4">
                        <button class="btn btn-success form-control" type="submit">添加</button>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary form-control" type="reset">重填</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $('#add').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                fname: {
                    validators: {
                        notEmpty: {
                            message: "输入不能为空"
                        },
                        stringLength: {
                            min: 2,
                            message: "名称长度必须大于2"
                        },
                        remote: {
                            type: 'POST',  //请求方式 get post
                            url: 'BookMangerServlet?action=verifyFname', //发送的Servlet路径
                            message: '名称已存在，请换一个。',
                            date: {
                                name: $('#fname').val()
                            },
                            delay: 500 //0.5秒刷新
                        }
                    }
                }
            }
        });
    });
</script>
</html>

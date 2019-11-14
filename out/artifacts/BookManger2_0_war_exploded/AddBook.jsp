<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oracle图书管理系统前台页面</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrapValidator.css"/>

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="bootstrap/js/jquery.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrapValidator.js"></script>
    <style type="text/css">
        .header {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .content {
            margin: 0 auto;
        }

        .mybtn {
            margin-left: 20px;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            $("#addbook").bootstrapValidator({

                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },

                fields: {
                    bookname: {
                        validators: {
                            notEmpty: {
                                message: '书名不能为空'
                            }
                        }
                    },
                    bookprice: {
                        validators: {
                            notEmpty: {
                                message: '价格不能为空'
                            },
                            greaterThan: {value: 0}
                        }
                    },
                    publish: {
                        validators: {
                            notEmpty: {
                                message: '出版社不能为空'
                            }
                        }
                    }
                }
            });
        });
    </script>

    <script type="text/javascript">
        $(function () {
            $('#img').change(function () {
                var file = this.files[0];
                var r = new FileReader();
                r.readAsDataURL(file);
                $(r).load(function () {
                    var img = document.getElementById("pic");            //图片路径设置为读取的图片
                    img.src = this.result;
                })
            })
        })
    </script>

</head>
<body>
<div class="container">
    <div class="panel-heading h1 bg-success text-info">添加图书</div>
    <div class="panel-body">
        <div class="content">
            <form action="BookMangerServlet?action=AddBook" method="post" class="form-horizontal col-sm-offset-3"
                  id="addbook" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-2 control-label">图书封面</label>
                    <div class="col-sm-7 col-sm-offset-4">

                        <img id="pic" name="pic" alt="" style="max-width:100%; height: 20%; width: 20%;"/>

                        <input id="img" name="img" type="file" accept="image/*"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">图书分类</label>
                    <div class="col-sm-7">
                        <select name="fid" id="" class="form-control">
                            <c:forEach items="${sessionScope.fNameMap}" var="entry">
                                <option value="${entry.key}">${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">图书名称</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="bookname" placeholder="请输入图书名称">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">图书价格</label>
                    <div class="col-sm-7">
                        <input type="number" name="bookprice" class="form-control" placeholder="请输入图书价格" step="0.01"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">出版社</label>
                    <div class="col-sm-7">
                        <input type="text" name="publish" class="form-control" placeholder="请输入图书出版社"/>
                    </div>
                </div>
                <div class="text-center col-sm-offset-1">
                    <button type="submit" class="btn btn-info col-sm-4 mybtn">添加</button>
                    <button type="reset" class="btn btn-default col-sm-4 mybtn" onclick="location.href='ViewBookAdd'">
                        取消
                    </button>
                </div>
                <input type="hidden" id="add" value="${sessionScope.add}"/>
            </form>
        </div>
    </div>

    <script type="text/javascript">
        var a = document.getElementById("add").value;
        if (a == "-1") {
        } else if (a == "0") {
            alert("添加失败");
        } else if (a == "1") {
            alert("添加成功");
        }
    </script>
        <%
			session.setAttribute("add", -1);
			%>
</body>
</html>

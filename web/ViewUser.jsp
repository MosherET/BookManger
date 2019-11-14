<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
    <!-- Bootstrap -->
    <script type="text/javascript" src="bootstrap/js/jquery.js" charset="UTF-8"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js" charset="UTF-8"></script>
</head>
<style>
    .touxaing {
        max-width: 40px;
    }

    .content {
        height: 380px;
    }
    .disabled {
        pointer-events: none;
        cursor: default;
        opacity: 0.6;
    }
    td{
        height: 50px;
        vertical-align: middle !important;
    }
</style>
<script>
    function conf() {
        if (confirm("确认删除该用户吗？")){
            return true;
        }
        return false;
    }
    $(function () {
        var info = $("#info").val();
        if (info == 1) {
            alert("该用户有未归还的图书！")
        }
    });
</script>
<body>
<div class="container">
    <div class="panel-heading h1 bg-success text-info">查看用户信息</div>
    <div>
        <div>
            <div style="float: left">
                <a class="btn btn-info" href="BookMangerServlet?action=outputAll">导出所有学生</a>
                <a class="btn btn-info" href="BookMangerServlet?action=outputPage&pageSize=5&currentPage=${page.currentPage}">导出本页学生</a>
            </div>
            <div style="float: right">
                <h4>共${page.totalCount}条记录，共${page.totalPage}页</h4>
            </div>
        </div>
    </div>
    <div class="content">
        <table class="table table-bordered table-condensed table-responsive">
            <tr class="text-center bg-primary">
                <td>编号</td>
                <td>姓名</td>
                <td>用户名</td>
                <td>密码</td>
                <td>手机</td>
                <td>注册时间</td>
                <td>头像</td>
                <td>删除</td>
                <td>修改</td>
            </tr>
            <c:forEach items="${page.list}" var="user">
                <tr class="text-center">
                    <td>${user.uid}</td>
                    <td>${user.uname}</td>
                    <td>${user.uacount}</td>
                    <td>${user.upassword}</td>
                    <td>${user.usphone}</td>
                    <td>${user.regdate}</td>
                    <td><img src="uploadFile/userimg/${user.touxiang}" class="touxaing"></td>
                    <td><a class="btn btn-danger btn-block" href="BookMangerServlet?action=DelUser&uid=${user.uid}&currentPage=${page.currentPage}" onclick="return conf()">删除</a>
                    </td>
                    <td><a href="BookMangerServlet?action=getUser&uid=${user.uid}&currentPage=${page.currentPage}" class="btn btn-warning btn-block">修改</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="form-group row">
        <label class="control-label col-md-3">没有找到用户，点击这里<a href="AddUser.jsp">添加用户</a></label>
    </div>
    <div class="text-center">
        <ul class="pagination">
            <li class="previous"><a href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=1">
                <span aria-hidden="true"></span>首页</a>
            </li>
            <c:if test="${page.currentPage - 1 < 1}">
                <li class="previous disabled"><a
                        href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${page.currentPage - 1}"><span aria-hidden="true">&laquo;</span>
                    上一页</a></li>
            </c:if>
            <c:if test="${page.currentPage - 1 >= 1}">
                <li class="previous"><a
                        href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${page.currentPage - 1}"><span
                        aria-hidden="true">&laquo;</span> 上一页</a></li>
            </c:if>
            <c:choose>
                <c:when test="${page.totalPage <= 10}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${page.totalPage}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${page.currentPage -4}"/>
                    <c:set var="end" value="${page.currentPage + 5}"/>
                    <c:if test="${page.currentPage - 4< 1}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="10"/>
                    </c:if>
                    <c:if test="${page.currentPage + 5 > page.totalPage}">
                        <c:set var="begin" value="${page.totalPage - 9}"/>
                        <c:set var="end" value="${page.totalPage}"/>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="${begin}" end="${end}" var="i">
                <c:if test="${page.currentPage == i}">
                    <li class="active"><a href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${i}">${i}</a></li>
                </c:if>
                <c:if test="${page.currentPage != i}">
                    <li><a href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${i}">${i}</a></li>
                </c:if>
            </c:forEach>

            <c:if test="${page.currentPage > page.totalPage}">
                <li class="next disabled"><a
                        href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${page.currentPage + 1}">下一页 <span
                        aria-hidden="true">&raquo;</span></a></li>
            </c:if>
            <c:if test="${page.currentPage < page.totalPage}">
                <li class="next"><a href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${page.currentPage + 1}">下一页
                    <span aria-hidden="true">&raquo;</span></a></li>
            </c:if>
            <li class="next"><a href="BookMangerServlet?action=FenYe&pageSize=5&currentPage=${page.totalPage}">尾页<span aria-hidden="true"></span></a></li>
        </ul>
        <input type="hidden" id="info" value="${info}">
    </div>
    <% request.getSession().removeAttribute("info");%>

</body>
</html>

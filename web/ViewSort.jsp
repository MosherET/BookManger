<%@ page import="com.mos.domain.Sort" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mos.service.SortService" %>
<%@ page import="com.mos.service.Impl.SortServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Mos
  Date: 2019/10/23
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!--1.文档要是html5<!DOCTYPE html>-->
    <meta charset="utf-8">
    <!--2.移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看分类</title>
    <!--3.引入核心的css-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <!--4.引入jQuery-->
    <script src="bootstrap/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <!--5.引入bootstrap核心的js-->
    <script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <style>
        body{
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel-heading h1 bg-success text-info">查看分类</div>
    <div class="panel-body">
        <form action="" method="post" class="form-horizontal">
            <table class="table table-bordered table-responsive text-center">
                <tr class="bg-primary">
                    <th>编号</th>
                    <th>分类名称</th>
                    <th>删除</th>
                    <th>修改</th>
                </tr>
                <%
                    SortService sortService = new SortServiceImpl();
                    List<Sort> sortList = sortService.viewAll();
                    session.setAttribute("sortList", sortList);
                    System.out.println(sortList);
                %>
                <c:forEach var="l" items="${sortList}">
                    <tr>
                        <td id="fid">${l.fid}</td>
                        <td>${l.fname}</td>
                        <td><a href="BookMangerServlet?action=DelSort&fid=${l.fid}" id="del"  class="btn btn-danger btn-sm" onclick="javascript: return delSort()">删除</a></td>
                        <td><a href="BookMangerServlet?action=ShowModifySort&fid=${l.fid}&fname=${l.fname}" class="btn btn-warning btn-sm">修改</a></td>
                    </tr>
                </c:forEach>

                <tr class="text-left">
                    <td colspan="4">
                        没有你要的分类？您可以点击这里
                        <a href="AddSort.jsp">添加分类</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
<script>
    var msg = "<%=session.getAttribute("msg1")%>";
    if(msg == 1) {
        alert("该分类下还有图书，不能删除");
    }
    function delSort(fid) {
        var rs = confirm("确认要删除吗？");
        if(rs == true) {
            return true;
        } else {
            return false;
        }
    }
</script>
<%
    session.setAttribute("msg1", 0);
%>
</html>

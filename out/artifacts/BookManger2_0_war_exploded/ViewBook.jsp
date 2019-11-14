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

</head>
<body>
<div class="container">
    <div class="panel-heading h1 bg-success text-info">查看图书信息</div>
    <div class="panel-body">
        <div>
            <ul class="nav nav-pills">
                <li role="presentation"><label type="text" class="form-control">图书分类</label></li>
                <li role="presentation">
                    <select name="fid" id="" class="form-control" class="col-sm-4">
                        <c:forEach items="${sessionScope.map}" var="entry">
                            <option value="${entry.key}">${entry.value}</option>
                        </c:forEach>
                    </select>
                </li>
                <li role="presentation"><input type="text" class="form-control" id="shuming" placeholder="书名"></li>
                <li role="presentation"><input type="text" class="form-control" id="jieshuren" placeholder="借书人"></li>
                <li role="presentation"><label type="text" class="form-control">图书状态</label></li>
                <li role="presentation">
                    <select name="state" id="state" class="form-control" class="col-sm-4" placeholder="图书状态">
                        <option value="未借出">未借出</option>
                        <option value="借出">借出</option>
                    </select>
                </li>
                <li role="presentation"><input type="button" class="form-control btn btn-success" id="sousuo"
                                               value="搜索图书"></li>
            </ul>
        </div>
    </div>
    <table class="table table-bordered table-condensed table-responsive">
        <tr class="text-center bg-primary">
            <td>封面</td>
            <td>编号</td>
            <td>分类名称</td>
            <td>图书名称</td>
            <td>单价(元)</td>
            <td>出版社</td>
            <td>状态</td>
            <td>借书人</td>
            <td>删除</td>
            <td>修改</td>
        </tr>
        <c:forEach items="${pb.beanList}" var="book">
            <tr class="text-center">
                <td style="vertical-align: middle !important;text-align: center;width:100px"><img src="${book.pic}"
                                                                                                  style="max-width:100%; height: 100px; width: 100px;"/>
                </td>
                <td style="vertical-align: middle !important;text-align: center;width:50px">${book.bid}</td>
                <td style="vertical-align: middle !important;text-align: center;width:100px">${book.fname}</td>
                <td style="vertical-align: middle !important;text-align: center">${book.bname}</td>
                <td style="vertical-align: middle !important;text-align: center">${book.price}</td>
                <td style="vertical-align: middle !important;text-align: center">${book.press}</td>
                <td style="vertical-align: middle !important;text-align: center;width:80px">${book.state}</td>
                <td style="vertical-align: middle !important;text-align: center;width:100px">${book.uname}</td>
                <td style="vertical-align: middle !important;text-align: center ;width:80px"><a class="btn btn-danger "
                                                                                                href="BookMangerServlet?action=DelBook&id=${book.bid}&oldpic=${book.pic}&state=${book.state}"
                                                                                                onclick="javascript: return delBook()">删除</a>
                </td>
                <td style="vertical-align: middle !important;text-align: center ;width:80px">
                    <button class="btn btn-warning "
                            onclick="location.href='BookMangerServlet?action=ViewModifyBook&id=${book.bid}'">修改
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="panel-footer">
        <a href="AddBook.jsp" class="btn btn-info">添加图书</a>
        <a href="BookMangerServlet?action=DownBookInfo" class="btn btn-info">导出图书信息</a>
        <a href="BookMangerServlet?action=DownBenyeInfo&pageNow=${pb.pageNow}" class="btn btn-info">导出本页信息</a>
        <br/>

        <div class="text-center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="BookMangerServlet?action=fenyeView&pageNow=1">首页</a></li>
                    <c:if test="${pb.pageNow>1}">
                        <li><a href="BookMangerServlet?action=fenyeView&pageNow=${pb.pageNow-1}">上一页</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${pb.pages<=10 }">
                            <c:set var="begin" value="1"></c:set>
                            <c:set var="end" value="${pb.pages }"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${pb.pageNow-5 }"></c:set>
                            <c:set var="end" value="${pb.pageNow+4 }"></c:set>
                            <c:if test="${begin<=1 }">
                                <c:set var="begin" value="1"></c:set>
                                <c:set var="end" value="10"></c:set>
                            </c:if>
                            <c:if test="${end>=pb.pages }">
                                <c:set var="begin" value="${pb.pages-9 }"></c:set>
                                <c:set var="end" value="${pb.pages }"></c:set>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${begin }" end="${end }">
                        <c:choose>
                            <c:when test="${pb.pageNow == i}">
                                <li class="active"><a href="BookMangerServlet?action=fenyeView&pageNow=${i}">${i}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="BookMangerServlet?action=fenyeView&pageNow=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${pb.pageNow<pb.pages}">
                        <li><a href="BookMangerServlet?action=fenyeView&pageNow=${pb.pageNow+1}">下一页</a></li>
                    </c:if>
                    <li><a href="BookMangerServlet?action=fenyeView&pageNow=${pb.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
        <br/>
        <form action="BookMangerServlet?action=fenyeView" class="form-horizontal" id="jump" name="jump">
            <div class="text-center text-info form-group">
                <input type="hidden" id="last" name="last" value="${pb.pages}"/>
                <label class="control-label">第</label>
                <input type="number" step="1" name="pageNow" id="pageNow" max="${pb.pages}" min="1"/>
                <button type="submit" class="btn btn-primary btn-xs">跳转</button>
            </div>
        </form>
        <br/>
        <div class="text-right text-warning">
            <p>第${pb.pageNow}页/共${pb.pages}页</p>
        </div>

    </div>
</div>

<input type="hidden" id="del" value="${sessionScope.del}"/>
<script type="text/javascript">
    var a = document.getElementById("del").value;
    if (a == "-1") {
    } else if (a == "0") {
        alert("当前图书已借出,无法删除!");
    } else if (a == "1") {
        alert("删除成功!");
    }
</script>

<input type="hidden" id="update" value="${sessionScope.update}"/>
<script type="text/javascript">
    var a = document.getElementById("update").value;
    if (a == "-1") {
    } else if (a == "0") {
        alert("修改失败");
    } else if (a == "1") {
        alert("修改成功");
    }

    function delBook() {
        var msg = confirm("真的要删除吗？");
        if (msg == true) {
            return true;
        } else {
            return false;
        }
    }
</script>
<%
    session.setAttribute("update", -1);
    session.setAttribute("del", -1);
%>

</body>
</html>

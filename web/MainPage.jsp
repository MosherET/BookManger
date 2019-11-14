<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>黄金屋图书管理系统</title>

	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />

	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="bootstrap/js/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<style type="text/css">
		.header {
			margin-top: 20px;
		}

		.content {
			margin-top: 30px;
			margin-bottom: 30px;
		}
		#top1{
			margin-bottom: 44px;
		}
	</style>
</head>
<body>
<div class="container-fluid text-center header">
	<h2>黄金屋图书借阅系统</h2>
</div>
<div class="container content">
	<div id="top1">
		<div style="float: left">
			<form class="form-inline" action="mainPage?action=ViewMain" method="post">
				<div class="form-group">
					<label for="fName">分类</label>
					<select id="fName" class="form-control" name="fname">
						<option><--请选择--></option>
						<c:forEach var="fname" items="${sessionScope.fnameMap}" >
							<option>${fname.value}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="bookName">图书名</label>
					<input type="text" class="form-control" placeholder="图书名" id="bookName" name="bname" value="${condition.bookName[0]}">
				</div>
				<button type="submit" class="btn btn-info">查询</button>
			</form>
		</div>
		<div style="float: right;">
			<span>${sessionScope.userLogin.uname}，欢迎你</span>
			<a class="btn btn-info" href="mainPage?action=exit">退出</a>
		</div>
	</div>
	<div class="text-center">
		<table class="table table-bordered table-hover">
			<tr>
				<th class="text-center">编号</th>
				<th class="text-center">图书封面</th>
				<th class="text-center">分类名称</th>
				<th class="text-center">图书名称</th>
				<th class="text-center">图书价格</th>
				<th class="text-center">图书出版社</th>
				<th class="text-center">状态</th>
				<th class="text-center">借/还</th>
			</tr>
			<c:forEach items="${sessionScope.pb.list}" var="book">
				<tr class="text-center">
					<td style="vertical-align: middle !important;text-align: center;width:50px">${book.bid}</td>
					<td style="vertical-align: middle !important;text-align: center;width:100px"><img src="${book.pic}" style="max-width:100%; height: 100px; width: 100px;" /></td>
					<td style="vertical-align: middle !important;text-align: center;width:100px">${book.fname}</td>
					<td style="vertical-align: middle !important;text-align: center">${book.bname}</td>
					<td style="vertical-align: middle !important;text-align: center">${book.price}</td>
					<td style="vertical-align: middle !important;text-align: center">${book.press}</td>
					<td style="vertical-align: middle !important;text-align: center;width:80px">${book.state}</td>
					<td style="vertical-align: middle !important;text-align: center ;width:80px">
						<c:if test="${book.state == '借出'}">
							<a type="button" class="btn btn-success" href="mainPage?action=ReturnBook&bid=${book.bid}&uid=${sessionScope.userLogin.uid}">还书</a>
						</c:if>
						<c:if test="${book.state == '未借出'}">
							<a type="button" class="btn btn-info" href="mainPage?action=BorrowBook&bid=${book.bid}&uid=${sessionScope.userLogin.uid}">借书</a>
						</c:if>

					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="text-center">
		<ul class="pagination pagination-lg">
			<c:if test="${sessionScope.pb.currentPage > 1}">
				<li><a href="mainPage?action=ViewMain">首页</a></li>
			</c:if>
			<c:choose>
				<c:when test="${sessionScope.pb.totalPage <= 10}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${sessionScope.pb.totalPage}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="begin" value="${sessionScope.pb.currentPage-5}"></c:set>
					<c:set var="end" value="${sessionScope.pb.currentPage+4 }"></c:set>
					<c:if test="${begin<=1 }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="10"></c:set>
					</c:if>
					<c:if test="${end>=pb.pages }">
						<c:set var="begin" value="${pb.totalPage-9 }"></c:set>
						<c:set var="end" value="${pb.totalPage }"></c:set>
					</c:if>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${begin }" end="${end }">
				<c:choose>
					<c:when test="${sessionScope.pb.currentPage == i}">
						<li class="active"><a href="mainPage?action=ViewMain&currentPage=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="mainPage?action=ViewMain&currentPage=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${sessionScope.pb.currentPage < sessionScope.pb.totalPage}">
				<li><a href="mainPage?action=ViewMain&currentPage=${sessionScope.pb.currentPage+1}">下一页</a></li>
			</c:if>
			<li><a href=mainPage?action=ViewMain&currentPage=${sessionScope.pb.totalPage}">尾页</a></li>
		</ul>
	</div>
</div>
<div class="text-center">
	<p>Corpright © Orcale公司版权所有</p>
</div>
</body>

<script>
	var msg = <%=session.getAttribute("reBookMsg")%>;
	var msg1 = <%=session.getAttribute("borBookMsg")%>;
	if (msg == 1) {
		alert("这本书不是你借的哦！");
	} else if (msg == 2) {
		alert("还书成功！");
	}
	if (msg1 == 1) {
		alert("借出成功！");
	}
</script>
<%
	session.setAttribute("reBookMsg", 0);
	session.setAttribute("borBookMsg", 0);
%>
</html>

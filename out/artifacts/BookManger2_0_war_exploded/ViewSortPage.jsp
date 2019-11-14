<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
		<!--4.引入jQuery-->
		<script src="bootstrap/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<!--5.引入bootstrap核心的js-->
		<script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	    <style>
	    .container{
				margin-top: 30px;
			}
		
		.font-big:hover{
		    font-size:3em;
		    color:#3399FF;
		}
	    </style>
	</head>
	<body>
		<div class="container">
			 <div class="panel-heading h1 bg-success text-info">查看分类</div>
			<form action="${pageContext.request.contextPath}/BookMangerServlet?action=ViewSortPage"  method="post" class="form-horizontal text-center">
			<table class="table  table-striped table-condensed table-hover">
				<tr bgcolor="steelblue">
					
					<td>编号</td>
					<td>分类名称</td>
					<td>修改</td>
					<td>删除</td>
				</tr>
				
				<c:forEach items="${sessionScope.pb.list}" var="ls">
				<tr>
					<td>${ls.fid }</td>
					<td>${ls.fname }</td>
					<td>
		 	 			<a class="btn btn-warning btn-block" href="BookMangerServlet?action=ShowModifySort&fid=${ls.fid}&fname=${ls.fname}">修改</a>
		 	 		</td>
		 	 		<td>
		 	 		   <a class="btn btn-primary btn-block" href="BookMangerServlet?action=DelSort&fid=${ls.fid}" onclick="javascript: return delSort()">删除</a>
		 	 		</td>
				</tr>	
				</c:forEach>
			   
			  <tr>
				<td colspan="4" class="text-right">
			     <ul class="pagination">
			     
			      <li>
                        <a href="${pageContext.request.contextPath}/BookMangerServlet?action=ViewSortPage&currentPage=${pb.currentPage - 1}&rows=${pb.rows}">
                            <span aria-hidden="true">前一页</span>
                        </a>
                    </li>
                    <c:forEach var="i" begin="1" end="${pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/BookMangerServlet?action=ViewSortPage&currentPage=${i}&rows=${pb.rows}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="${pageContext.request.contextPath}/BookMangerServlet?action=ViewSortPage&currentPage=${pb.currentPage + 1}&rows=${pb.rows}">
                            <span aria-hidden="true">后一页</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px;">
                                                                     共${pb.totalCount}条记录，共${pb.totalPage}页
                   </span>
                </ul>
			    </td>
			  </tr>
			  <tr>
			  <td colspan="4" class="text-left">
					没有你要的分类？您可以点击这里
					<a href="AddSort.jsp" class="btn has-success font-big">添加分类</a>
				</td>
			  </tr>
			  
			</table>	
		    </form>
		</div>
	</body>
<script>
	var msg = "<%=session.getAttribute("msg1")%>";
	if(msg == 1) {
		alert("该分类下还有图书，不能删除");
	}
	function delSort() {
		var msg = confirm("真的要删除吗？");
		if(msg == true) {
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

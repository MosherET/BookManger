<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="bootstrap/js/jquery.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="bootstrap/js/bootstrap.js"></script>
		<style>
			body{
				margin-top: 0px;
				/*background-color: #1b6d85;*/
				background-color: rgba(27, 0, 59, 0.83);
				color: #d98952;
			}
			.header{
				width: 100%;
			}
			.myimg{
				width: 50px;
				height: 50px;
				margin-top: 10px;
				margin-bottom: 10px;
			}

		</style>
	</head>
	<body>
		<div class="container">
			<div class="header">
				<div class="header">
					<div class="text-center">
						<img src="${sessionScope.admin.adminPic}" class="img-rounded myimg img-circle"><br />
						<c:if test="${sessionScope.admin == null}">
							<p>未登录</p>
						</c:if>
						<c:if test="${sessionScope.admin != null}">
							<p>您好，${sessionScope.admin.aname}</p>
						</c:if>
					</div>
					<!--<div class="mytitle text-center"><p>xxx,欢迎回来</p></div>-->
				</div>
				<div class="row">
					<div class="">
						<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
							<div class="panel">
								<div class="panel-heading panel-primary " role="tab" id="headingOne">
									<h4 class="panel-title">
										<a class="glyphicon glyphicon-th-list" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne"></a>
										<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
											分类管理
										</a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									<div class="list-group">
										<a href="BookMangerServlet?action=ViewSortPage&currentPage=1&rows=6" class="list-group-item list-group-item-info" target="right">查看分类</a>
										<a href="AddSort.jsp" class="list-group-item list-group-item-info" target="right">添加分类</a>
									</div>
								</div>
								<!--<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="list-group">
                                            <a href="ViewSort.html" class="list-group-item list-group-item-info" target="right">查看分类</a>
                                            <a href="AddSort.html" class="list-group-item list-group-item-info" target="right">添加分类</a>
                                    </div>
                                </div>-->
							</div>

							<div class="panel">
								<div class="panel-heading" role="tab" id="headingTwo">
									<h4 class="panel-title">
										<a class="glyphicon glyphicon-th-list"  role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"></a>
										<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
											图书管理
										</a>
									</h4>
								</div>
								<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									<div class="list-group">
										<a href="BookMangerServlet?action=Book" class="list-group-item list-group-item-info" target="right">查看图书</a>
										<a href="AddBook.jsp" class="list-group-item list-group-item-info" target="right">添加图书</a>
									</div>
								</div>
							</div>

							<div class="panel">
								<div class="panel-heading" role="tab" id="headingThree">
									<h4 class="panel-title">
										<a class="glyphicon glyphicon-th-list" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree"></a>
										<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
											用户管理
										</a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
									<div class="list-group">
										<a href="BookMangerServlet?action=FenYe" class="list-group-item list-group-item-info" target="right">查看用户</a>
										<a href="AddUser.jsp" class="list-group-item list-group-item-info" target="right">添加用户</a>
									</div>
								</div>
							</div>

							<div class="panel">
								<div class="panel-heading" role="tab" id="headingFour">
									<h4 class="panel-title">
										<a class="glyphicon glyphicon-th-list"  role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour"></a>
										<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
											管理员管理
										</a>
									</h4>
								</div>
								<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
									<div class="list-group">
										<a href="ViewAdmin.jsp" class="list-group-item list-group-item-info" target="right">查看管理员</a>
										<a href="ModifyAdminPwd.jsp" class="list-group-item list-group-item-info" target="right">修改密码</a>
										<a href="BookMangerServlet?action=ExitSystem" class="list-group-item list-group-item-info" onclick="exit()" >退出系统</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

<script>
	function exit() {
		var browserName = navigator.appName;
		var browserVer = parseInt(navigator.appVersion);
		//alert(browserName + " : "+browserVer);

		//document.getElementById("flashContent").innerHTML = "<br>&nbsp;<font face='Arial' color='blue' size='2'><b> You have been logged out of the Game. Please Close Your Browser Window.</b></font>";

		if(browserName == "Microsoft Internet Explorer"){
			var ie7 = (document.all && !window.opera && window.XMLHttpRequest) ? true : false;
			if (ie7)
			{
				//This method is required to close a window without any prompt for IE7 & greater versions.
				window.open('','_parent','');
				window.close();
			}
			else
			{
				//This method is required to close a window without any prompt for IE6
				this.focus();
				self.opener = this;
				self.close();
			}
		}else{
			//For NON-IE Browsers except Firefox which doesnt support Auto Close
			try{
				this.focus();
				self.opener = this;
				self.close();
			}
			catch(e){

			}

			try{
				window.open('','_self','');
				window.close();
			}
			catch(e){

			}
		}
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>恭喜你注册成功</title>
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="bootstrap/js/jquery.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="bootstrap/js/bootstrap.js"></script>
		<style>
			body{
				margin-top: 30px;
			}
			#second{
				color: #2aabd2;
			}
		</style>
		<script>
			$(function () {
				var s = 5
				function showTime() {
					s--;
					if (s <= 0){
						location.href="UserLogin.jsp";
					}
					var time = document.getElementById("second");
					time.innerHTML = s + "...";
				}
				setInterval(showTime,1000);

			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="panel-heading h3 bg-success text-center">
				${register.uname}恭喜你注册成功，<span id="second">5...</span>后自动返回登陆，若没返回请点击
				<a href="UserLogin.jsp">立即登录</a>
			</div>
		</div>
	</body>
</html>

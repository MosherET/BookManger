<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!--1.h5文档-->
		<meta charset="utf-8">
		<!--2.移动设备优先-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<!--3.引入核心css-->
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css">
		<!--4.引入jquery-->
		<script src="bootstrap/js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<!--引入bootstrap核心js-->
		<script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<script src="bootstrap/js/bootstrapValidator.js" type="text/javascript" charset="utf-8"></script>
		<style>
			.container{
				width:600px ;
				margin-top: 200px;
			      }
		</style>
	</head>

	<body>
		<div class="container">

			<div class="panel-heading bg-success text-info text-center"><h1>修改分类</h1></div>
			<div class="panel-body">
				<div class="text-center">
					<form action="BookMangerServlet?action=ModifySort" method="post" id="modify" class="form-horizontal">
					<input type="hidden" name="fid" value="${modifysort.fid}" />
						<div class="form-group">
							<label  for="fname" class="col-md-4 control-label">分类名称</label>
							<div class="col-md-4">
								<input type="text" name="fname" id="fname" class="form-control" placeholder="${modifysort.fname }" />
							</div>
						</div>
						<div class="form-group">
			             <div class="col-md-4 col-md-offset-4">
		                  <button type="submit" class="btn btn-success">修改</button>
		                  <button type="reset" class="btn btn-info" onclick="location.href='BookMangerServlet?action=ModifySortServlet&fid=${modifysort.fid}&fname=${modifysort.fname}'">重填</button>
			            </div>
		              </div> 
					</form>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function () {
			$('#modify').bootstrapValidator({
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

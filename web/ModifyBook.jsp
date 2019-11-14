<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Oracle图书管理系统前台页面</title>

		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrapValidator.css" />

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

			.mybtn {				margin-left: 20px;
			}
		</style>
		
		<script type="text/javascript">
		$(function(){
		$("#modify").bootstrapValidator({
			feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
			
	        fields: {
	            bookName: {
	                validators: {
	                    notEmpty: {
	                        message: '书名不能为空'
	                   			 }
	                	}
	            },
	            bookPrice: {
	                validators: {
	                    notEmpty: {
	                        message: '价格不能为空'
	                   			 },
	            		greaterThan:{value:0}
	                	}
	            },
	            press: {
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
			$(function(){
			    $('#img').change(function(){
			        var file = this.files[0];
			        var r = new FileReader();
			        r.readAsDataURL(file);
			        $(r).load(function(){
			            var img = document.getElementById("pic");            //图片路径设置为读取的图片
			            img.src = this.result;
			        })
			    })
			})
			</script>
		
	</head>
	<body>
		<div class="container">
			<div class="panel-heading h1 bg-success text-info">修改图书</div>
			<div class="panel-body">
				<div class="content">
					<form class="form-horizontal col-sm-offset-3"  id="modify" action="BookMangerServlet?action=ModifyBook" method="post" enctype="multipart/form-data">
					<input type="hidden" id="oldpic" name="oldpic" value="${book.pic}"/>
						<div class="form-group">
							<label for="bookName" class="col-sm-2 control-label">图书封面</label>
							<div class="col-sm-7 col-sm-offset-4">
								<input type="hidden" id="pic2" name="pic2" value="${book.pic}"/>
								<img src="${book.pic}" id="pic" name="pic" style="max-width:100%; height: 20%; width: 20%;" />
								<input id="img" name="img" type="file" accept="image/*"  value="${book.pic}"/>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">图书编号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="bid" name="bid" readonly="readonly" value="${book.bid}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="bookSort" class="col-sm-2 control-label">请选择分类</label>
							<div class="col-sm-7">
								<select name="fid" id="fid" class="form-control">
								 <c:forEach items="${sessionScope.fNameMap}" var="entry">
							     	<option value="${entry.key}" <c:if test="${book.fid==entry.key}">selected="selected"</c:if> >${entry.value}
							     </c:forEach>	
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="bookName" class="col-sm-2 control-label">图书名称</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="bname" value="${book.bname}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="bookPrice" class="col-sm-2 control-label">图书价格</label>
							<div class="col-sm-7">
								<input type="number" class="form-control" step="0.01" name="price" value="${book.price}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="press" class="col-sm-2 control-label">出版社</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="press" value="${book.press}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="status" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-7">
								<label class="radio-inline">
									<input type="radio" name="state" id="state" value="未借出"  <c:if test="${book.state == '未借出'}">
                            checked="checked"
                            </c:if> />未借出
								</label>
								<label class="radio-inline">
									<input type="radio" name="state" id="state" value="借出"  <c:if test="${book.state == '借出'}">
                            checked="checked"
                            </c:if> />借出
								</label>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">借书人</label>
							<div class="col-sm-7">
								<select name="uid" id="uid" class="form-control">
								 <c:forEach items="${requestScope.uname}" var="entry">
							     	<option value="${entry.key}" <c:if test="${book.uid==entry.key}">selected="selected"</c:if> >${entry.value}
							     </c:forEach>	
								</select>
							</div>
						</div>
						<div class="text-center col-sm-offset-1" >
							<button class="btn btn-info col-sm-4 mybtn" type="submit">修改信息</button>&nbsp;&nbsp;
							<button class="btn btn-primary col-sm-4 mybtn"  onclick="location.href='ViewModifyBook?id=${book.bid}'">重新填写</button>
						</div>
						
					</form>
				</div>
			</div>
		</div>
		 	
	</body>
</html>

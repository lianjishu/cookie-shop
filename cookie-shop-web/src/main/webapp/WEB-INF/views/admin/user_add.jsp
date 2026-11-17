<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>客户添加</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/bootstrap.css" />
	<style>
		body {
			background: #f5f7fa;
		}

		.admin-form-page {
			max-width: 1180px;
			margin: 0 auto 40px;
		}

		.admin-form-card {
			background: #fff;
			border: 1px solid #e5e5e5;
			border-radius: 6px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
			padding: 20px 22px 24px;
		}

		.admin-form-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 20px;
			border-bottom: 1px solid #eee;
			padding-bottom: 12px;
		}

		.admin-form-header h3 {
			margin: 0;
			font-size: 22px;
			font-weight: 600;
		}

		.admin-form-title {
			display: flex;
			align-items: baseline;
			gap: 12px;
		}

		.user-add-form {
			max-width: 760px;
			margin: 0 auto;
		}

		.user-add-form .form-group {
			margin-left: 0;
			margin-right: 0;
		}

		.user-add-form .control-label {
			text-align: right;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="header.jsp"></jsp:include>

	<c:if test="${!empty failMsg }">
		<div class="alert alert-danger">${failMsg }</div>
	</c:if>

	<div class="admin-form-page">
		<div class="admin-form-card">
			<div class="admin-form-header">
				<div class="admin-form-title">
					<h3>客户添加</h3>
					<span class="text-muted">添加新的客户信息</span>
				</div>
			</div>

			<form class="form-horizontal user-add-form" action="${pageContext.request.contextPath}/admin/user_add" method="post">
				<div class="form-group">
					<label for="input_username" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_username" name="username" required="required" value="${u.username }" />
					</div>
				</div>
				<div class="form-group">
					<label for="input_email" class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_email" name="email" required="required" value="${u.email }"/>
					</div>
				</div>
				<div class="form-group">
					<label for="input_password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_password" name="password" required="required" value="${u.password }"/>
					</div>
				</div>
				<div class="form-group">
					<label for="input_name" class="col-sm-2 control-label">收货人</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_name" name="name" value="${u.name }"/>
					</div>
				</div>
				<div class="form-group">
					<label for="input_phone" class="col-sm-2 control-label">电话</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_phone" name="phone" value="${u.phone }" />
					</div>
				</div>
				<div class="form-group">
					<label for="input_address" class="col-sm-2 control-label">地址</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_address" name="address" value="${u.address }"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">提交保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
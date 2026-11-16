<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>客户修改</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
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

		.user-edit-form {
			max-width: 760px;
			margin: 0 auto;
		}

		.user-edit-form .form-group {
			margin-left: 0;
			margin-right: 0;
		}

		.user-edit-form .control-label {
			text-align: right;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="/admin/header.jsp"></jsp:include>

	<div class="admin-form-page">
		<div class="admin-form-card">
			<div class="admin-form-header">
				<div class="admin-form-title">
					<h3>客户修改</h3>
					<span class="text-muted">修改客户收货信息</span>
				</div>
			</div>

			<form class="form-horizontal user-edit-form" action="${pageContext.request.contextPath}/admin/user_edit" method="post">
				<input type="hidden" name="id" value="${u.id }">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<p class="form-control-static">${u.username }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-10">
						<p class="form-control-static">${u.email }</p>
					</div>
				</div>
				<div class="form-group">
					<label for="input_name" class="col-sm-2 control-label">收货人</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_name" name="name" value="${u.name }">
					</div>
				</div>
				<div class="form-group">
					<label for="input_phone" class="col-sm-2 control-label">电话</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_phone" name="phone" value="${u.phone }">
					</div>
				</div>
				<div class="form-group">
					<label for="input_address" class="col-sm-2 control-label">地址</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_address" name="address" value="${u.address }">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">提交修改</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
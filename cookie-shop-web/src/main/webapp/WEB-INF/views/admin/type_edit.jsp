<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>类目编辑</title>
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

		.type-edit-form {
			max-width: 500px;
			margin: 0 auto;
		}

		.type-edit-form .form-group {
			margin-left: 0;
			margin-right: 0;
		}

		.type-edit-form .control-label {
			text-align: right;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="header.jsp"></jsp:include>

	<div class="admin-form-page">
		<div class="admin-form-card">
			<div class="admin-form-header">
				<div class="admin-form-title">
					<h3>类目编辑</h3>
					<span class="text-muted">修改类目名称</span>
				</div>
			</div>

			<form class="form-horizontal type-edit-form" action="${pageContext.request.contextPath}/admin/type_edit" method="post">
				<input type="hidden" name="id" value="${param.id }">
				<div class="form-group">
					<label for="input_name" class="col-sm-3 control-label">类目名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="input_name" name="name" value="${param.name }" required="required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-success">提交修改</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
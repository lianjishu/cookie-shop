<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
			 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>类目列表</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
	<style>
		body {
			background: #f5f7fa;
		}

		.type-list-page {
			max-width: 1180px;
			margin: 0 auto 40px;
		}

		.type-list-card {
			background: #fff;
			border: 1px solid #e5e5e5;
			border-radius: 6px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
			padding: 20px 22px 24px;
			transition: transform .25s, box-shadow .25s;
		}
		.type-list-card:hover {
			transform: translateY(-3px);
			box-shadow: 0 6px 16px rgba(0,0,0,.12);
		}

		.type-list-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 16px;
			border-bottom: 1px solid #eee;
			padding-bottom: 12px;
		}

		.type-list-header h3 {
			margin: 0;
			font-size: 22px;
			font-weight: 600;
		}
		.type-list-title {
			display: flex;
			align-items: baseline;
			gap: 12px;
		}

		.type-add-form {
			margin-bottom: 14px;
		}

		.type-add-form .form-control {
			width: 360px;
			max-width: 100%;
		}

		.type-table {
			margin-bottom: 0;
			background: #fff;
		}

		.type-table > thead > tr > th {
			background: #f8f8f8;
			color: #333;
			font-weight: 600;
			vertical-align: middle;
			text-align: center;
		}

		.type-table > tbody > tr > td {
			vertical-align: middle;
			text-align: center;
		}

		.type-actions .btn {
			margin-right: 6px;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="header.jsp"></jsp:include>

	<div class="type-list-page">
		<div class="type-list-card">
			<div class="type-list-header">
				<div class="type-list-title">
				<h3>类目管理</h3>
				<span class="text-muted">管理商品分类信息</span>
				</div>
			</div>

			<form class="form-inline type-add-form" method="post" action="${pageContext.request.contextPath}/admin/type_add">
				<div class="form-group">
					<input type="text" class="form-control" id="input_name" name="name" placeholder="输入类目名称" required="required">
				</div>
				<input type="submit" class="btn btn-warning" value="添加类目"/>
			</form>

			<c:if test="${!empty msg }">
				<div class="alert alert-success">${msg }</div>
			</c:if>
			<c:if test="${!empty failMsg }">
				<div class="alert alert-danger">${failMsg }</div>
			</c:if>

			<table class="table table-bordered table-hover table-condensed type-table">
				<thead>
				<tr>
					<th width="12%">ID</th>
					<th width="58%">名称</th>
					<th width="30%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="t">
					<tr>
						<td>${t.id }</td>
						<td>${t.name }</td>
						<td class="type-actions">
							<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/admin/type_edit?id=${t.id }&name=${t.encodeName }">修改</a>
							<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/admin/type_delete?id=${t.id }">删除</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty list}">
					<tr>
						<td colspan="3" class="text-center text-muted">暂无类目，请先添加类目</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>

</div>
</body>
</html>
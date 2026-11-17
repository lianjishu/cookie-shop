<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>客户列表</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
	<style>
		body {
			background: #f5f7fa;
		}

		.admin-list-page {
			max-width: 1120px;
			margin: 0 auto 40px;
		}

		.admin-list-card {
			background: #fff;
			border: 1px solid #e5e5e5;
			border-radius: 6px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
			padding: 20px 22px 24px;
			transition: transform .25s, box-shadow .25s;
		}

		.admin-list-card:hover {
			transform: translateY(-3px);
			box-shadow: 0 6px 16px rgba(0,0,0,.12);
		}
		.admin-list-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 16px;
			border-bottom: 1px solid #eee;
			padding-bottom: 12px;
		}

		.admin-list-header h3 {
			margin: 0;
			font-size: 22px;
			font-weight: 600;
		}

		.admin-list-title {
			display: flex;
			align-items: baseline;
			gap: 12px;
		}

		.admin-table {
			margin-bottom: 0;
			background: #fff;
		}

		.admin-table > thead > tr > th {
			background: #f8f8f8;
			color: #333;
			font-weight: 600;
			vertical-align: middle;
			text-align: center;
		}

		.admin-table > tbody > tr > td {
			vertical-align: middle;
			text-align: center;
		}

		.admin-actions .btn {
			margin: 0 4px 6px 0;
		}

		.admin-pagination {
			margin-top: 16px;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="header.jsp"></jsp:include>

	<div class="admin-list-page">
		<div class="admin-list-card">
			<div class="admin-list-header">
				<div class="admin-list-title">
					<h3>客户管理</h3>
					<span class="text-muted">管理客户账户与收货信息</span>
				</div>
				<a class="btn btn-warning" href="user_add.jsp">添加客户</a>
			</div>

			<c:if test="${!empty msg }">
				<div class="alert alert-success">${msg }</div>
			</c:if>
			<c:if test="${!empty failMsg }">
				<div class="alert alert-danger">${failMsg }</div>
			</c:if>

			<table class="table table-bordered table-hover table-condensed admin-table">
				<thead>
				<tr>
					<th width="6%">ID</th>
					<th width="12%">用户名</th>
					<th width="18%">邮箱</th>
					<th width="10%">收件人</th>
					<th width="12%">电话</th>
					<th width="22%">地址</th>
					<th width="20%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${p.list }" var="u">
					<tr>
						<td>${u.id }</td>
						<td>${u.username }</td>
						<td>${u.email }</td>
						<td>${u.name }</td>
						<td>${u.phone }</td>
						<td>${u.address }</td>
						<td class="admin-actions">
							<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/admin/user_reset.jsp?id=${u.id }&username=${u.username }&email=${u.email }">重置密码</a>
							<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/admin/user_editshow?id=${u.id }">修改</a>
							<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/admin/user_delete?id=${u.id }">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<div class="admin-pagination">
				<jsp:include page="/page.jsp">
					<jsp:param value="/admin/user_list" name="url"/>
				</jsp:include>
			</div>
		</div>
	</div>
</div>
</body>
</html>
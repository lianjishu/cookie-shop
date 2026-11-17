<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>后台管理</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/>
<style>
	body {
		background: #f5f7fa;
	}

	.admin-list-page {
		max-width: 1180px;
		margin: 0 auto 40px;
	}

	.admin-list-card {
		background: #fff;
		border: 1px solid #e5e5e5;
		border-radius: 6px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
		padding: 20px 22px 24px;
			transition: transform .25s, box-shadow .25s;
		transition: transform .25s, box-shadow .25s;
	}
	.admin-list-card:hover {
		transform: translateY(-3px);
		box-shadow: 0 6px 16px rgba(0,0,0,.12);
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

	.admin-stats {
		display: flex;
		gap: 20px;
		margin-bottom: 24px;
	}

	.stat-card {
		flex: 1;
		background: #f8f9fa;
		border-radius: 6px;
		padding: 20px;
		text-align: center;
	}

	.stat-card h4 {
		margin: 0 0 8px;
		color: #666;
		font-size: 14px;
	}

	.stat-num {
		font-size: 28px;
		font-weight: 600;
		color: #333;
		margin: 0;
	}

	.admin-quick-actions {
		display: flex;
		justify-content: space-around;
		gap: 20px;
		flex-wrap: wrap;
		margin-top: 8px;
	}

	.admin-quick-actions .btn {
		min-width: 140px;
		padding: 10px 24px;
		font-size: 15px;
	}

	.alert-success {
		margin-top: 16px;
	}

	.el-message {
		position: fixed;
		top: 16px;
		left: 50%;
		transform: translateX(-50%);
		z-index: 2001;
		min-width: 380px;
		box-sizing: border-box;
		border-radius: 4px;
		border: 1px solid #e4e7ed;
		padding: 10px 20px;
		display: flex;
		align-items: center;
		background-color: #f0f9eb;
		opacity: 0;
		transition: opacity 0.3s, transform 0.3s;
		pointer-events: none;
	}

	.el-message--success {
		border-color: #c2e7b0;
		background-color: #f0f9eb;
	}

	.el-message.is-center {
		justify-content: center;
	}

	.el-message--visible {
		opacity: 1;
	}

	.el-message__icon {
		margin-right: 8px;
		font-size: 16px;
		color: #67c23a;
	}

	.el-message__icon svg {
		width: 16px;
		height: 16px;
	}

	.el-message__content {
		font-size: 14px;
		color: #67c23a;
		margin: 0;
		line-height: 1;
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
					<h3>后台管理</h3>
					<span class="text-muted">欢迎使用蛋糕店后台管理系统</span>
				</div>
			</div>

			<div class="admin-stats">
				<div class="stat-card">
					<h4>商品总数</h4>
					<p class="stat-num">${goodsCount}</p>
				</div>
				<div class="stat-card">
					<h4>订单总数</h4>
					<p class="stat-num">${orderCount}</p>
				</div>
				<div class="stat-card">
					<h4>用户总数</h4>
					<p class="stat-num">${userCount}</p>
				</div>
			</div>

			<div class="admin-quick-actions">
				<a href="${pageContext.request.contextPath}/admin/goods_add" class="btn btn-warning">添加商品</a>
				<a href="${pageContext.request.contextPath}/admin/order_list" class="btn btn-primary">查看订单</a>
				<a href="${pageContext.request.contextPath}/admin/user_list" class="btn btn-info">管理用户</a>
				<a href="${pageContext.request.contextPath}/admin/type_list" class="btn btn-default">管理类目</a>
			</div>
		</div>
	</div>
</div>

<c:if test="${justLoggedIn}">
<div id="message_1" class="el-message el-message--success is-center" role="alert" style="top: 16px; z-index: 2001;">
	<i class="el-icon el-message__icon el-message-icon--success">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024"><path fill="currentColor" d="M512 64a448 448 0 1 1 0 896 448 448 0 0 1 0-896m-55.808 536.384-99.52-99.584a38.4 38.4 0 1 0-54.336 54.336l126.72 126.72a38.27 38.27 0 0 0 54.336 0l262.4-262.464a38.4 38.4 0 1 0-54.272-54.336z"></path></svg>
	</i>
	<p class="el-message__content">登录成功</p>
</div>

<script>
(function() {
	var msg = document.getElementById('message_1');
	if (msg) {
		setTimeout(function() {
			msg.classList.add('el-message--visible');
		}, 100);
		setTimeout(function() {
			msg.classList.remove('el-message--visible');
			setTimeout(function() {
				msg.remove();
			}, 300);
		}, 3000);
	}
})();
</script>
</c:if>
</body>
</html>
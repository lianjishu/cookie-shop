<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>订单列表</title>
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

		.admin-tabs {
			margin-bottom: 16px;
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

		.admin-table p {
			margin: 0 0 4px;
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
					<h3>订单管理</h3>
					<span class="text-muted">查看订单状态并处理发货流程</span>
				</div>
			</div>

			<ul role="tablist" class="nav nav-tabs admin-tabs">
				<li <c:if test="${status==0 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/order_list">全部订单</a></li>
				<li <c:if test="${status==1 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/order_list?status=1">未付款</a></li>
				<li <c:if test="${status==2 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/order_list?status=2">已付款</a></li>
				<li <c:if test="${status==3 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/order_list?status=3">配送中</a></li>
				<li <c:if test="${status==4 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/order_list?status=4">已完成</a></li>
			</ul>

			<table class="table table-bordered table-hover table-condensed admin-table">
				<thead>
				<tr>
					<th width="5%">ID</th>
					<th width="7%">总价</th>
					<th width="18%">商品详情</th>
					<th width="19%">收货信息</th>
					<th width="10%">订单状态</th>
					<th width="10%">支付方式</th>
					<th width="10%">下单用户</th>
					<th width="11%">下单时间</th>
					<th width="10%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${p.list }" var="order">
					<tr>
						<td>${order.id }</td>
						<td>${order.total }</td>
						<td>
							<c:forEach items="${order.itemList }" var="item">
								<p>${item.goodsName }(${item.price }) x ${item.amount}</p>
							</c:forEach>
						</td>
						<td>
							<p>${order.name }</p>
							<p>${order.phone }</p>
							<p>${order.address }</p>
						</td>
						<td>
							<p>
								<c:if test="${order.status==2 }"><span style="color:red;">已付款</span></c:if>
								<c:if test="${order.status==3 }"><span style="color:green;">已发货</span></c:if>
								<c:if test="${order.status==4 }"><span style="color:black;">已完成</span></c:if>
							</p>
						</td>
						<td>
							<p>
								<c:if test="${order.paytype==1 }">微信</c:if>
								<c:if test="${order.paytype==2 }">支付宝</c:if>
								<c:if test="${order.paytype==3 }">货到付款</c:if>
							</p>
						</td>
						<td>${order.user.username }</td>
						<td>${order.datetime }</td>
						<td class="admin-actions">
							<c:if test="${order.status==2 }">
								<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/admin/order_status?id=${order.id }&status=3">发货</a>
							</c:if>
							<c:if test="${order.status==3 }">
								<a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/admin/order_status?id=${order.id }&status=4">完成</a>
							</c:if>
							<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/admin/order_delete?id=${order.id }&pageNumber=${p.pageNumber}&status=${status}">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<div class="admin-pagination">
				<jsp:include page="/page.jsp">
					<jsp:param value="/admin/order_list" name="url"/>
					<jsp:param value="&status=${status}" name="param"/>
				</jsp:include>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>我的订单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp">
		<jsp:param name="flag" value="5"></jsp:param>
	</jsp:include>
	<!--//header-->

	<c:if test="${param.msg=='review_success'}">
		<div class="alert alert-success text-center" style="margin:10px;">评价提交成功！</div>
	</c:if>
	<c:if test="${param.msg=='review_fail'}">
		<div class="alert alert-danger text-center" style="margin:10px;">评价提交失败！</div>
	</c:if>
	<c:if test="${param.msg=='already_reviewed'}">
		<div class="alert alert-warning text-center" style="margin:10px;">该订单已评价过！</div>
	</c:if>

	<style>
		.order-table th { background: #f5f5f5; font-weight: 600; text-align: center; vertical-align: middle; }
		.order-table td { vertical-align: middle; text-align: center; }
		.order-table .price { color: #e4393c; font-weight: bold; font-size: 15px; }
		.order-table .info-label { color: #999; font-size: 12px; }
		.status-badge { display: inline-block; padding: 3px 10px; border-radius: 12px; font-size: 12px; color: #fff; }
		.status-cancel { background: #ccc; }
		.status-wait { background: #f0ad4e; }
		.status-send { background: #5bc0de; }
		.status-done { background: #5cb85c; }
		.action-btns { display: flex; gap: 6px; justify-content: center; }
		.action-btns a { padding: 4px 12px; border-radius: 4px; font-size: 12px; text-decoration: none; color: #fff; }
		.btn-pay { background: #e4393c; }
		.btn-pay:hover { background: #c1272d; color: #fff; text-decoration: none; }
		.btn-cancel { background: #999; }
		.btn-cancel:hover { background: #777; color: #fff; text-decoration: none; }
	</style>

	<div class="cart-items">
		<div class="container">
			<h2>我的订单</h2>

			<c:choose>
				<c:when test="${empty orderList}">
					<div class="alert alert-warning">暂无订单，<a href="${pageContext.request.contextPath}/goods_list">去购物</a></div>
				</c:when>
				<c:otherwise>
					<table class="table table-bordered order-table">
						<tr>
							<th>订单号</th>
							<th>商品总价</th>
							<th>收货人</th>
							<th>联系电话</th>
							<th>收货地址</th>
							<th>状态</th>
							<th>支付方式</th>
							<th>下单时间</th>
							<th>操作</th>
						</tr>

						<c:forEach items="${orderList}" var="order">
							<tr>
								<td>#${order.id}</td>
								<td class="price">¥ ${order.total}</td>
								<td>${order.name}</td>
								<td>${order.phone}</td>
								<td>${order.address}</td>
								<td>
									<c:choose>
										<c:when test="${order.status==0}"><span class="status-badge status-cancel">已取消</span></c:when>
										<c:when test="${order.status==1}"><span class="status-badge status-wait">待付款</span></c:when>
										<c:when test="${order.status==2}"><span class="status-badge status-send">待发货</span></c:when>
										<c:when test="${order.status==3}"><span class="status-badge status-send">已发货</span></c:when>
										<c:when test="${order.status==4}"><span class="status-badge status-done">已完成</span></c:when>
										<c:otherwise><span class="status-badge status-cancel">未知</span></c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${order.paytype==1}">微信支付</c:when>
										<c:when test="${order.paytype==2}">支付宝</c:when>
										<c:otherwise>货到付款</c:otherwise>
									</c:choose>
								</td>
								<td>${order.datetime}</td>
								<td>
									<c:choose>
										<c:when test="${order.status==1}">
											<div class="action-btns">
												<a class="btn-pay" href="${pageContext.request.contextPath}/goPay?orderId=${order.id}">去支付</a>
												<a class="btn-cancel" href="${pageContext.request.contextPath}/order_cancel?orderId=${order.id}" onclick="return confirm('确定取消该订单吗？')">取消</a>
											</div>
										</c:when>
										<c:when test="${order.status==4}">
											<c:choose>
												<c:when test="${reviewedMap[order.id]}">
													<span class="label label-default">已评价</span>
												</c:when>
												<c:otherwise>
													<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/review?orderId=${order.id}&action=add">评价</a>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!--footer-->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--//footer-->
</body>
</html>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>支付结果</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="cart-items">
		<div class="container">
			<h2>支付结果</h2>

			<c:choose>
				<c:when test="${!empty order}">
					<div class="alert alert-success">
						<h4>请您完成支付</h4>
						<p>订单号: <strong>${order.id}</strong></p>
						<p>订单金额: <strong>¥ ${order.total}</strong></p>
						<p>收货人: ${order.name}</p>
						<p>收货地址: ${order.address}</p>
					</div>
					<div class="alert alert-info">
						<p><strong>重要提示：</strong></p>
						<p>由于您使用的是本地开发环境，支付宝异步通知(notify_url)暂时无法正常工作。</p>
						<p>支付完成后，订单状态需等待管理员确认或手动刷新页面更新。</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-warning">
						<p>未找到订单信息，请到 <a href="${pageContext.request.contextPath}/order_list">我的订单</a> 查看</p>
					</div>
				</c:otherwise>
			</c:choose>

			<p>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/order_list">查看我的订单</a>
				<a class="btn btn-default" href="${pageContext.request.contextPath}/goods_list">继续购物</a>
			</p>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
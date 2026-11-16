<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>订单提交成功</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp"></jsp:include>
	<!--//header-->

	<div class="cart-items">
		<div class="container">
			<h2>订单提交成功</h2>

			<c:if test="${!empty orderId}">
				<div class="alert alert-success">
					<p>您的订单已提交成功！</p>
					<p>订单号: <strong>${orderId}</strong></p>
				</div>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/goPay?orderId=${orderId}">去支付</a>
			</c:if>

			<c:if test="${!empty msg}">
				<div class="alert alert-warning">${msg}</div>
			</c:if>

			<p>
				<a class="btn btn-success" href="${pageContext.request.contextPath}/order_list">查看我的订单</a>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/goods_list">继续购物</a>
			</p>
		</div>
	</div>

	<!--footer-->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--//footer-->
</body>
</html>
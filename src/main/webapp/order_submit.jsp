<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>确认订单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp"></jsp:include>
	<!--//header-->

	<div class="cart-items">
		<div class="container">
			<h2>确认订单</h2>

			<c:choose>
				<c:when test="${empty cartList}">
					<div class="alert alert-warning">购物车为空，<a href="${pageContext.request.contextPath}/goods_list">去购物</a></div>
				</c:when>
				<c:otherwise>
					<h3>购物车商品</h3>
					<table class="table table-bordered">
						<tr>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:set var="total" value="0"/>
						<c:forEach items="${cartList}" var="item">
							<tr>
								<td>${item.goods.name}</td>
								<td>¥ ${item.goods.price}</td>
								<td>${item.amount}</td>
								<td>¥ ${item.goods.price * item.amount}</td>
							</tr>
							<c:set var="total" value="${total + item.goods.price * item.amount}"/>
						</c:forEach>
						<tr>
							<td colspan="3" style="text-align:right;"><strong>总计:</strong></td>
							<td><strong>¥ ${total}</strong></td>
						</tr>
					</table>

					<hr>

					<h2>确认收货信息</h2>
					<form class="form-horizontal" action="${pageContext.request.contextPath}/goPay" method="post" id="payform">
						<div class="row">
							<label class="control-label col-md-2">收货人</label>
							<div class="col-md-6">
								<input type="text" class="form-control" name="name" value="${user.name}" required="required"><br>
							</div>
						</div>
						<div class="row">
							<label class="control-label col-md-2">收货电话</label>
							<div class="col-md-6">
								<input type="text" class="form-control" name="phone" value="${user.phone}" required="required"><br>
							</div>
						</div>
						<div class="row">
							<label class="control-label col-md-2">收货地址</label>
							<div class="col-md-6">
								<input type="text" class="form-control" name="address" value="${user.address}" required="required"><br>
							</div>
						</div>

						<br><hr><br>

						<h2>选择支付方式</h2>
						<h3>支付金额: ¥ ${total}</h3><br><br>

						<div class="col-sm-6 col-md-4 col-lg-3">
							<label>
								<div class="thumbnail">
									<input type="radio" name="paytype" value="1" checked="checked" />
									<img src="${pageContext.request.contextPath}/images/wechat.jpg" alt="微信支付">
								</div>
							</label>
						</div>
						<div class="col-sm-6 col-md-4 col-lg-3">
							<label>
								<div class="thumbnail">
									<input type="radio" name="paytype" value="2" />
									<img src="${pageContext.request.contextPath}/images/alipay.jpg" alt="支付宝支付">
								</div>
							</label>
						</div>
						<div class="clearfix"></div>
						<div class="register-but text-center">
							<input type="submit" class="btn btn-success btn-lg" value="确认订单">
							<div class="clearfix"></div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!--footer-->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--//footer-->
</body>
</html>
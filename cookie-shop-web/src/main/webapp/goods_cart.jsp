<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>购物车</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp">
		<jsp:param name="flag" value="7"></jsp:param>
	</jsp:include>
	<!--//header-->

	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
			<h2>我的购物车</h2>

			<c:choose>
				<c:when test="${empty cartList}">
					<div class="alert alert-warning">购物车为空</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${cartList}" var="item">
						<div class="cart-header col-md-6">
							<div class="cart-sec simpleCart_shelfItem">
								<div class="cart-item cyc">
									<a href="${pageContext.request.contextPath}/goods_detail?id=${item.goods_id}">
										<img src="${pageContext.request.contextPath}${item.goods.cover}" class="img-responsive">
									</a>
								</div>
								<div class="cart-item-info">
									<h3><a href="${pageContext.request.contextPath}/goods_detail?id=${item.goods_id}">${item.goods.name}</a></h3>
									<h3><span>单价: ¥ ${item.goods.price}</span></h3>
									<h3><span>数量: ${item.amount}</span></h3>
									<h3><span>小计: ¥ ${item.goods.price * item.amount}</span></h3>
									<a class="btn btn-info" href="javascript:buy(${item.goods_id});">增加</a>
									<a class="btn btn-warning" href="javascript:lessen(${item.id});">减少</a>
									<a class="btn btn-danger" href="javascript:deletes(${item.id});">删除</a>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<div class="cart-header col-md-12">
				<hr>
				<button class="btn btn-success btn-lg" style="margin-left:74%" data-toggle="modal" data-target="#orderModal">提交订单</button>
			</div>

			<!-- 收货信息模态框 -->
			<div class="modal fade" id="orderModal" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<form id="orderForm" method="post" action="${pageContext.request.contextPath}/order_submit">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">填写收货信息</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label>收货人</label>
									<input type="text" class="form-control" name="name" required>
								</div>
								<div class="form-group">
									<label>联系电话</label>
									<input type="text" class="form-control" name="phone" required>
								</div>
								<div class="form-group">
									<label>收货地址</label>
									<input type="text" class="form-control" name="address" required>
								</div>
								<div class="form-group">
									<label>支付方式</label>
									<select class="form-control" name="paytype">
										<option value="1">微信支付</option>
										<option value="2">支付宝</option>
									</select>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-success">确认提交</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--//cart-items-->

	<!--footer-->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--//footer-->
</body>
</html>
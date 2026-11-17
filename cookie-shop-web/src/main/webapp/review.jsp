<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>评价订单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<style>
		.review-form { max-width: 600px; margin: 30px auto; padding: 30px; background: #fff; border-radius: 6px; box-shadow: 0 2px 8px rgba(0,0,0,.08); }
		.star-rating { direction: rtl; display: inline-flex; font-size: 28px; }
		.star-rating input { display: none; }
		.star-rating label { color: #DDD; cursor: pointer; padding: 0 2px; }
		.star-rating input:checked ~ label,
		.star-rating label:hover,
		.star-rating label:hover ~ label { color: #FFD700; }
	</style>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="review-form">
			<h3>评价订单 #${order.id}</h3>
			<hr>
			<p><strong>商品：</strong>
				<c:forEach items="${items}" var="item" varStatus="s">
					${item.goodsName}${s.last ? '' : '、'}
				</c:forEach>
			</p>
			<p><strong>总价：</strong>¥ ${order.total}</p>
			<hr>

			<form action="${pageContext.request.contextPath}/review" method="post">
				<input type="hidden" name="orderId" value="${order.id}">

				<div class="form-group">
					<label>评分</label>
					<div class="star-rating">
						<input type="radio" id="star5" name="rating" value="5" required><label for="star5">&#9733;</label>
						<input type="radio" id="star4" name="rating" value="4"><label for="star4">&#9733;</label>
						<input type="radio" id="star3" name="rating" value="3"><label for="star3">&#9733;</label>
						<input type="radio" id="star2" name="rating" value="2"><label for="star2">&#9733;</label>
						<input type="radio" id="star1" name="rating" value="1"><label for="star1">&#9733;</label>
					</div>
				</div>

				<div class="form-group" style="margin-top:15px;">
					<label for="content">评价内容</label>
					<textarea class="form-control" id="content" name="content" rows="5" placeholder="请分享您的使用感受..." required maxlength="500"></textarea>
				</div>

				<button type="submit" class="btn btn-primary" style="margin-top:15px;">提交评价</button>
				<a href="${pageContext.request.contextPath}/order_list" class="btn btn-default" style="margin-top:15px;">返回订单</a>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

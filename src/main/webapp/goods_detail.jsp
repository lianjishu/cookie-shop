<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>商品详情</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/flexslider.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
	<script>
		$(function() {
		  $('.flexslider').flexslider({
			animation: "slide",
			controlNav: "thumbnails"
		  });
		});
	</script>
</head>
<body>

	<!--header-->
	<jsp:include page="header.jsp"></jsp:include>
	<!--//header-->

	<div class="single">
		<div class="container">
			<div class="single-grids">
				<div class="col-md-4 single-grid">
					<div class="flexslider">
						<ul class="slides">
							<li data-thumb="${pageContext.request.contextPath}${goods.cover}">
								<div class="thumb-image"> <img src="${pageContext.request.contextPath}${goods.cover}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
							<li data-thumb="${pageContext.request.contextPath}${goods.image1}">
								 <div class="thumb-image"> <img src="${pageContext.request.contextPath}${goods.image1}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
							<li data-thumb="${pageContext.request.contextPath}${goods.image2}">
							   <div class="thumb-image"> <img src="${pageContext.request.contextPath}${goods.image2}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 single-grid simpleCart_shelfItem">
					<h3>${goods.name}</h3>
					<c:if test="${not empty type}">
						<div class="tag">
							<p>分类 : <a href="${pageContext.request.contextPath}/goods_list?type_id=${type.id}">${type.name}</a></p>
						</div>
					</c:if>
					<p>${goods.intro}</p>
					<div class="galry">
						<div class="prices">
							<h5 class="item_price">¥ ${goods.price}</h5>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="btn_form">
						<a href="javascript:;" class="add-cart item_add" onclick="buy(${goods.id})">加入购物车</a>
					</div>
				</div>
				<div class="col-md-4 single-grid1">
					<h3>商品分类</h3>
					<ul>
						<li><a href="${pageContext.request.contextPath}/goods_list">全部系列</a></li>
						<c:forEach items="${typeList}" var="t">
							<li><a href="${pageContext.request.contextPath}/goods_list?type_id=${t.id}">${t.name}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>

			<!-- 评价区域 -->
			<div class="reviews-section" style="margin-top:50px;">
				<h3>商品评价 <small>(共 ${reviewCount} 条)</small></h3>
				<div class="rating-stars">
					<span>平均评分：</span>
					<c:forEach var="i" begin="1" end="5">
						<span style="color:${i <= Math.round(avgRating) ? '#FFD700' : '#DDD'};font-size:20px;">&#9733;</span>
					</c:forEach>
					<span style="margin-left:5px;">${avgRating}</span>
				</div>

				<c:if test="${reviews != null && !reviews.isEmpty()}">
					<div class="reviews-list">
						<c:forEach items="${reviews}" var="review">
							<div class="review-item" style="border-bottom:1px solid #eee;padding:15px 0;">
								<div class="review-rating">
									<c:forEach var="i" begin="1" end="5">
										<span style="color:${i <= review.rating ? '#FFD700' : '#DDD'};">&#9733;</span>
									</c:forEach>
								</div>
								<p style="margin:10px 0;">${review.content}</p>
								<p style="color:#999;font-size:12px;">评价时间：${review.created_at}</p>
							</div>
						</c:forEach>
					</div>
				</c:if>

				<c:if test="${reviews == null || reviews.isEmpty()}">
					<div class="alert alert-info" style="margin-top:20px;">
						暂无评价
					</div>
				</c:if>
			</div>

		</div>
	</div>

	<!--footer-->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--//footer-->
</body>
</html>
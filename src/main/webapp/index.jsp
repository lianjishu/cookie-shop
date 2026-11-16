<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>蛋糕商城首页</title>
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
    <jsp:param name="flag" value="1"></jsp:param>
</jsp:include>
<!--banner-->

<div class="banner">
    <div class="container">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <c:if test="${not empty goodsList}">
            <!-- Indicators -->
            <ol class="carousel-indicators" id="olnum">
                <c:forEach items="${goodsList}" var="g" varStatus="status">
                    <c:choose>
                        <c:when test="${status.first}">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        </c:when>
                        <c:otherwise>
                            <li data-target="#carousel-example-generic" data-slide-to="${status.index}"></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox" id="lunbotu" style="width: 1242px; height: 432px;">
                <c:forEach items="${goodsList}" var="g" varStatus="status">
                    <c:choose>
                        <c:when test="${status.first}">
                            <div class="item active">
                                <h2 class="hdng"><a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">${g.name}</a><span></span></h2>
                                <p>精选推荐</p>
                                <a class="banner_a" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                                <div class="banner-text">
                                    <a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">
                                        <img src="${pageContext.request.contextPath}${g.cover}" alt="${g.name}" width="350" height="350">
                                    </a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="item">
                                <h2 class="hdng"><a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">${g.name}</a><span></span></h2>
                                <p>精选推荐</p>
                                <a class="banner_a" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                                <div class="banner-text">
                                    <a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">
                                        <img src="${pageContext.request.contextPath}${g.cover}" alt="${g.name}" width="350" height="350">
                                    </a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            </c:if>
        </div>
    </div>
</div>

<!--//banner-->

<div class="subscribe2"></div>

<!--gallery-->
<div class="gallery">
    <div class="container">
        <c:if test="${not empty types}">
        <div class="alert alert-info">商品分类</div>
        <div class="gallery-grids">
            <c:forEach items="${typeList}" var="t">
                <div class="col-md-3 gallery-grid ">
                    <a href="${pageContext.request.contextPath}/goods_list?type_id=${t.id}">
                        <div class="more-product"><span> </span></div>
                        <div class="product-img">
                            <img src="${pageContext.request.contextPath}/picture/1-1.jpg" class="img-responsive" alt="${t.name}">
                        </div>
                    </a>
                    <div class="galy-info">
                        <p><a href="${pageContext.request.contextPath}/goods_list?type_id=${t.id}">${t.name}</a></p>
                    </div>
                </div>
            </c:forEach>
        </div>
        </c:if>

        <c:if test="${not empty goodsList}">
        <div class="clearfix"></div>
        <div class="alert alert-danger">热门商品</div>
        <div class="gallery-grids">
            <c:forEach items="${goodsList}" var="g">
                <div class="col-md-3 gallery-grid ">
                    <a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">
                        <img src="${pageContext.request.contextPath}${g.cover}" class="img-responsive" alt="${g.name}"/>
                    </a>
                    <div class="gallery-info">
                        <p>
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            <a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">查看详情</a>
                        </p>
                        <a class="shop" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                        <div class="clearfix"></div>
                    </div>
                    <div class="galy-info">
                        <p>${g.type_name} > ${g.name}</p>
                        <div class="galry">
                            <div class="prices">
                                <h5 class="item_price">¥ ${g.price}</h5>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        </c:if>
    </div>
</div>
<!--//gallery-->

<!--subscribe-->
<div class="subscribe"></div>
<!--//subscribe-->


<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
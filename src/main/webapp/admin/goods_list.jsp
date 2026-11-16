<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
			 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品列表</title>
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

		.admin-table > tbody > tr > td.goods-image-cell {
			text-align: left;
		}

		.admin-table p {
			margin: 0;
		}

		.goods-cover {
			border-radius: 4px;
			border: 1px solid #eee;
		}

		.admin-actions .btn {
			margin: 0 4px 6px 0;
		}

		.recommend-actions {
			margin-bottom: 2px;
		}

		.admin-pagination {
			margin-top: 16px;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="/admin/header.jsp"></jsp:include>

	<div class="admin-list-page">
		<div class="admin-list-card">
			<div class="admin-list-header">
				<div class="admin-list-title">
					<h3>商品管理</h3>
					<span class="text-muted">管理商品信息与首页推荐</span>
				</div>
				<a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/goods_add">添加商品</a>
			</div>

			<ul role="tablist" class="nav nav-tabs admin-tabs">
				<li <c:if test="${type==0 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list">全部商品</a></li>
				<li <c:if test="${type==1 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list?type=1">条幅推荐</a></li>
				<li <c:if test="${type==2 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list?type=2">热销推荐</a></li>
				<li <c:if test="${type==3 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list?type=3">新品推荐</a></li>
			</ul>

			<table class="table table-bordered table-hover table-condensed admin-table">
				<thead>
				<tr>
					<th width="5%">ID</th>
					<th width="10%">图片</th>
					<th width="12%">名称</th>
					<th width="20%">介绍</th>
					<th width="8%">价格</th>
					<th width="10%">类目</th>
					<th width="35%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${p.list }" var="g">
					<tr>
						<td>${g.id }</td>
						<td class="goods-image-cell"><a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}" target="_blank"><img class="goods-cover" src="${pageContext.request.contextPath}${g.cover}" width="100px" height="100px"></a></td>
						<td><a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}" target="_blank">${g.name}</a></td>
						<td>${g.intro}</td>
						<td>${g.price}</td>
						<td>${g.type.name}</td>
						<td class="admin-actions">
							<p class="recommend-actions">
								<c:choose>
									<c:when test="${g.isScroll }">
										<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${g.id }&method=remove&typeTarget=1&pageNumber=${p.pageNumber}&type=${type}">移出条幅</a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${g.id }&method=add&typeTarget=1&pageNumber=${p.pageNumber}&type=${type}">加入条幅</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${g.isHot }">
										<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${g.id }&method=remove&typeTarget=2&pageNumber=${p.pageNumber}&type=${type}">移出热销</a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${g.id }&method=add&typeTarget=2&pageNumber=${p.pageNumber}&type=${type}">加入热销</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${g.isNew }">
										<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${g.id }&method=remove&typeTarget=3&pageNumber=${p.pageNumber}&type=${type}">移出新品</a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${g.id }&method=add&typeTarget=3&pageNumber=${p.pageNumber}&type=${type}">加入新品</a>
									</c:otherwise>
								</c:choose>
							</p>
							<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/admin/goods_editshow?id=${g.id }&pageNumber=${p.pageNumber}&type=${type}">修改</a>
							<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/admin/goods_delete?id=${g.id }&pageNumber=${p.pageNumber}&type=${type}">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<div class="admin-pagination">
				<jsp:include page="/page.jsp">
					<jsp:param value="/admin/goods_list" name="url"/>
					<jsp:param value="&type=${type }" name="param"/>
				</jsp:include>
			</div>
		</div>
	</div>
</div>
</body>
</html>

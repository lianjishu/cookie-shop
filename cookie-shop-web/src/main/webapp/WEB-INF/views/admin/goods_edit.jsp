<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
			 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>商品编辑</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/bootstrap.css" />
	<style>
		body {
			background: #f5f7fa;
		}

		.admin-form-page {
			max-width: 1180px;
			margin: 0 auto 40px;
		}

		.admin-form-card {
			background: #fff;
			border: 1px solid #e5e5e5;
			border-radius: 6px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
			padding: 20px 22px 24px;
		}

		.admin-form-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 20px;
			border-bottom: 1px solid #eee;
			padding-bottom: 12px;
		}

		.admin-form-header h3 {
			margin: 0;
			font-size: 22px;
			font-weight: 600;
		}

		.admin-form-title {
			display: flex;
			align-items: baseline;
			gap: 12px;
		}

		.goods-edit-form {
			max-width: 760px;
			margin: 0 auto;
		}

		.goods-edit-form .form-group {
			margin-left: 0;
			margin-right: 0;
		}

		.goods-edit-form .control-label {
			text-align: right;
		}

		.goods-preview-image {
			border: 1px solid #eee;
			border-radius: 4px;
			margin-right: 12px;
		}

		.file-tip {
			display: inline-block;
			margin-left: 8px;
			color: #777;
		}
	</style>
</head>
<body>
<div class="container">

	<jsp:include page="header.jsp"></jsp:include>

	<div class="admin-form-page">
		<div class="admin-form-card">
			<div class="admin-form-header">
				<div class="admin-form-title">
					<h3>商品编辑</h3>
					<span class="text-muted">修改商品基础信息、图片与类目</span>
				</div>
			</div>

			<form class="form-horizontal goods-edit-form" action="${pageContext.request.contextPath}/admin/goods_edit" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${g.id }"/>
				<input type="hidden" name="cover" value="${g.cover }"/>
				<input type="hidden" name="image1" value="${g.image1 }"/>
				<input type="hidden" name="image2" value="${g.image2 }"/>
				<input type="hidden" name="pageNumber" value="${param.pageNumber }"/>
				<input type="hidden" name="type" value="${param.type }"/>
				<div class="form-group">
					<label for="input_name" class="col-sm-2 control-label">名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_name" name="name" value="${g.name }" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="input_price" class="col-sm-2 control-label">价格</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_price" name="price" value="${g.price }">
					</div>
				</div>
				<div class="form-group">
					<label for="input_intro" class="col-sm-2 control-label">介绍</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_intro" name="intro" value="${g.intro }">
					</div>
				</div>
				<div class="form-group">
					<label for="input_stock" class="col-sm-2 control-label">库存</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_stock" name="stock" value="${g.stock }">
					</div>
				</div>
				<div class="form-group">
					<label for="input_cover" class="col-sm-2 control-label">封面图片</label>
					<div class="col-sm-10">
						<img class="goods-preview-image" src="${pageContext.request.contextPath }${g.cover }" width="100" height="100"/>
						<input type="file" name="cover" id="input_cover">
						<span class="file-tip">推荐尺寸: 500 * 500</span>
					</div>
				</div>
				<div class="form-group">
					<label for="input_image1" class="col-sm-2 control-label">详情图片1</label>
					<div class="col-sm-10">
						<img class="goods-preview-image" src="${pageContext.request.contextPath }${g.image1 }" width="100" height="100"/>
						<input type="file" name="image1" id="input_image1">
						<span class="file-tip">推荐尺寸: 500 * 500</span>
					</div>
				</div>
				<div class="form-group">
					<label for="input_image2" class="col-sm-2 control-label">详情图片2</label>
					<div class="col-sm-10">
						<img class="goods-preview-image" src="${pageContext.request.contextPath }${g.image2 }" width="100" height="100"/>
						<input type="file" name="image2" id="input_image2">
						<span class="file-tip">推荐尺寸: 500 * 500</span>
					</div>
				</div>
				<div class="form-group">
					<label for="select_topic" class="col-sm-2 control-label">类目</label>
					<div class="col-sm-10">
						<select class="form-control" id="select_topic" name="typeid">
							<c:forEach items="${typeList }" var="t">
								<option <c:if test="${t.id==g.type.id }">selected="selected"</c:if> value="${t.id }">${t.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">提交修改</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
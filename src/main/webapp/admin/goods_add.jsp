<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>商品添加</title>
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

	<jsp:include page="/admin/header.jsp"></jsp:include>

	<div class="admin-form-page">
		<div class="admin-form-card">
			<div class="admin-form-header">
				<div class="admin-form-title">
					<h3>商品添加</h3>
					<span class="text-muted">添加新的商品信息、图片与类目</span>
				</div>
			</div>

			<form class="form-horizontal goods-edit-form" action="${pageContext.request.contextPath}/admin/goods_add" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="input_name" class="col-sm-2 control-label">名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_name" name="name" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="input_price" class="col-sm-2 control-label">价格</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_price" name="price">
					</div>
				</div>
				<div class="form-group">
					<label for="input_intro" class="col-sm-2 control-label">介绍</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_intro" name="intro">
					</div>
				</div>
				<div class="form-group">
					<label for="input_stock" class="col-sm-2 control-label">库存</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_stock" name="stock">
					</div>
				</div>
				<div class="form-group">
					<label for="input_cover" class="col-sm-2 control-label">封面图片</label>
					<div class="col-sm-10">
						<input type="file" name="cover" id="input_cover" required="required">
						<span class="file-tip">推荐尺寸: 500 * 500</span>
					</div>
				</div>
				<div class="form-group">
					<label for="input_image1" class="col-sm-2 control-label">详情图片1</label>
					<div class="col-sm-10">
						<input type="file" name="image1" id="input_image1" required="required">
						<span class="file-tip">推荐尺寸: 500 * 500</span>
					</div>
				</div>
				<div class="form-group">
					<label for="input_image2" class="col-sm-2 control-label">详情图片2</label>
					<div class="col-sm-10">
						<input type="file" name="image2" id="input_image2" required="required">
						<span class="file-tip">推荐尺寸: 500 * 500</span>
					</div>
				</div>
				<div class="form-group">
					<label for="select_topic" class="col-sm-2 control-label">类目</label>
					<div class="col-sm-10">
						<select class="form-control" id="select_topic" name="typeid">
							<c:forEach items="${typeList }" var="t">
								<option value="${t.id }">${t.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">提交保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
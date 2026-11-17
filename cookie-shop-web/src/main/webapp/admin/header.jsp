<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .admin-nav-card {
        background: #fff;
        border: 1px solid #e5e5e5;
        border-radius: 6px;
        box-shadow: 0 2px 8px rgba(0,0,0,.05);
        padding: 0 24px;
        margin-bottom: 24px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 56px;
        transition: transform .25s, box-shadow .25s;
    }
    .admin-nav-card:hover {
        transform: translateY(-3px);
        box-shadow: 0 6px 16px rgba(0,0,0,.12);
    }
    .admin-nav-brand {
        font-size: 18px;
        font-weight: 600;
        color: #333;
        text-decoration: none;
    }
    .admin-nav-brand:hover {
        color: #333;
        text-decoration: none;
    }
    .admin-nav-links {
        list-style: none;
        display: flex;
        gap: 8px;
        margin: 0;
        padding: 0;
    }
    .admin-nav-links li a {
        display: block;
        padding: 8px 16px;
        color: #555;
        border-radius: 4px;
        text-decoration: none;
        font-size: 14px;
        transition: background .2s, color .2s;
    }
    .admin-nav-links li a:hover {
        background: #f5f7fa;
        color: #333;
    }
    .admin-nav-links li a.nav-exit {
        color: #e74c3c;
    }
    .admin-nav-links li a.nav-exit:hover {
        background: #fdf0ef;
    }
</style>
<div class="admin-nav-card">
    <a class="admin-nav-brand" href="${pageContext.request.contextPath}/admin/index">蛋糕店后台</a>
    <ul class="admin-nav-links">
        <li><a href="${pageContext.request.contextPath}/index">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/order_list">订单管理</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/user_list">客户管理</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/goods_list">商品管理</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/type_list">类目管理</a></li>
        <li><a href="${pageContext.request.contextPath}/user_logout" class="nav-exit">退出</a></li>
    </ul>
</div>
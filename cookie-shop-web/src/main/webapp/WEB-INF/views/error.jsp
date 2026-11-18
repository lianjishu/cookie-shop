<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%--
  全局错误页：由 GlobalExceptionHandler 在捕获「非业务异常」时返回视图名 "error" 渲染。
  刻意不依赖 header.jsp / footer.jsp，避免页面片段自身的 EL 取值在异常场景下再出问题。
--%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>页面出错了 - 甜心蛋糕</title>
    <style>
        * { box-sizing: border-box; }
        body {
            margin: 0; min-height: 100vh; display: flex; align-items: center; justify-content: center;
            background: #faf7f2; color: #3d3a36;
            font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft YaHei", sans-serif;
        }
        .box { text-align: center; padding: 48px 32px; }
        .code { font-size: 72px; font-weight: 700; color: #d9c7a8; letter-spacing: 2px; }
        h1 { font-size: 22px; font-weight: 500; margin: 8px 0 12px; }
        p { color: #8a8378; margin: 0 0 24px; }
        a {
            display: inline-block; padding: 10px 28px; border-radius: 24px;
            background: #c9a86a; color: #fff; text-decoration: none;
        }
        a:hover { background: #b6965c; }
    </style>
</head>
<body>
<div class="box">
    <div class="code">${empty errorStatus ? 'Oops' : errorStatus}</div>
    <h1>页面无法正常显示</h1>
    <p>可能是链接或参数不对，也可能是服务暂时不可用；请返回首页重试。</p>
    <a href="${pageContext.request.contextPath}/index">返回首页</a>
</div>
</body>
</html>
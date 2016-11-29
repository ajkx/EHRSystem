<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/9
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/topic.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/templatemo_style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/container.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/tree.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.pjax.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-treeview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>

</head>
<body>
<jsp:include page="../nav.jsp"/>
<div style="position: absolute;left:0;right: 0;bottom: 0;top: 61px">
<jsp:include page="../menu.jsp"/>
<div id="content" style="position:absolute;left:220px;right: 0;bottom: 0;top: 0px;">
    <div style="height:100%;width:100%;" id="main-content"></div>
</div>
</div>
</body>
</html>

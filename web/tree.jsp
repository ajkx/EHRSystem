<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/23
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/easyui.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.easyui.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>--%>
</head>
<body>
<h2>Lazy Load Tree Nodes</h2>
<p>Get full hierarchical tree data but lazy load nodes level by level.</p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" style="padding:5px">
    <ul class="easyui-tree" data-options="url:'/tree',method:'get',loadFilter:myLoadFilter"></ul>
</div>
</body>
</html>

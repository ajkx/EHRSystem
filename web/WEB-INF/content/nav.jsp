<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/11
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/nav.css" />
</head>

<body>
<nav class="navbar">
    <div class="logo-panel">
        <a class="logo"><img src="${pageContext.request.contextPath}/static/image/logo.png" height="60px" /></a>
    </div>

    <ul class="usual-panel">
        <li>
            <a class="quickicon" href="#">
                <i class="fa fa-paper-plane-o"></i>
                <span>项目</span>
            </a>
        </li>
        <li>
            <a class="quickicon" href="#">
                <i class="fa fa-paper-plane-o"></i>
                <span>项目</span>
            </a>
        </li>
        <li>
            <a class="quickicon" href="#">
                <i class="fa fa-paper-plane-o"></i>
                <span>项目</span>
            </a>
        </li>
    </ul>

    <div class="right-panel">
        <div class="middle-panel">
            <input type="text" class="form-control input-sm searchinput" name="searchinput" placeholder="Search..." />
        </div>
        <div class="userinfo dropdown">
            <a class="portrait" href="#"><img src="${pageContext.request.contextPath}/static/image/touxiang.png" width="40px" height="40px" /></a>
            <i class="fa fa-angle-down" style="font-size: 20px;font-weight: bold;color:#6F6F6F"></i>
            <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="#menu7">Menu7</a></li>
                <li><a href="#menu8">Menu8</a></li>
            </ul>
        </div>
    </div>
</nav>
<hr style="border-top:1px solid #DFDEDA"/>
</body>

</html>
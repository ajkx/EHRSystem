<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/11
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${ctx}/static/css/nav.css" />
</head>

<body>

<div class="modal fade" id="changepassword-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width:500px">
        <div class="modal-content" id="changepassword-modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    修改密码
                </h4>
            </div>
            <div class="modal-body ">
                <form class="form-horizontal" role="form" action="/changepassword" method="post" onsubmit="return ajaxSubmit(this)">
                    <%--eidtlist存放创建或者修改的编辑字段集合--%>
                    <input type="hidden" class="form-control" id="topicid" name="id" value=""/>
                    <div class="form-group">
                        <div class="control-wrapper">
                            <label for="name">原密码</label>
                            <input type="text" class="form-control" id="name" name="currentPassword" value="" required>
                        </div>
                        <div class="control-wrapper">
                            <label for="name">新密码</label>
                            <input class="form-control" rows="3" name="password"/>
                        </div>
                        <div class="control-wrapper">
                            <label for="name">确认密码</label>
                            <input class="form-control" rows="3" name="confirmPassword"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-wrapper">
                            <button type="submit" style="float: right" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                    <hr>
                </form>
            </div>
        </div>
    </div>
</div>


<nav class="navbar">
    <div class="logo-panel">
        <%--<a class="logo"><img src="${ctx}/static/image/logo.png" height="60px" /></a>--%>
        <span class="logo-text">考勤管理系统</span>
    </div>

    <%--<ul class="usual-panel">--%>
        <%--<li>--%>
            <%--<a class="quickicon" href="#">--%>
                <%--<i class="fa fa-paper-plane-o"></i>--%>
                <%--<span>项目</span>--%>
            <%--</a>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<a class="quickicon" href="#">--%>
                <%--<i class="fa fa-paper-plane-o"></i>--%>
                <%--<span>项目</span>--%>
            <%--</a>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<a class="quickicon" href="#">--%>
                <%--<i class="fa fa-paper-plane-o"></i>--%>
                <%--<span>项目</span>--%>
            <%--</a>--%>
        <%--</li>--%>
    <%--</ul>--%>

    <div class="right-panel">
        <div class="middle-panel">
            <input type="text" class="form-control input-sm searchinput" name="searchinput" placeholder="Search..." />
        </div>
        <div class="userinfo dropdown">
            <a class="portrait" href="#"><img src="${ctx}/static/image/touxiang.png" width="40px" height="40px" /></a>
            <i class="fa fa-angle-down" style="font-size: 20px;font-weight: bold;color:#6F6F6F"></i>
            <ul class="dropdown-menu dropdown-menu-right my-dropdown-menu">
                <li><a href="#menu7" data-target="#changepassword-modal" data-toggle="modal" >修改密码</a></li>
                <li><a href="#menu8">修改头像</a></li>
            </ul>
        </div>
    </div>
</nav>
<hr style="border-top:1px solid #DFDEDA"/>
</body>

</html>
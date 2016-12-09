<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/7
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/base.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/login.css">

    <script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.form.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>

    <meta name="author" content="ajkx">
    <script type="text/javascript">
        function ajaxSubmit(){
            $('.form-signin').ajaxSubmit({
               type:'post',
                url:'/login',
                data:$(this).serialize(),
                dataType:'json',
                error:function(){ console.log("error");},
                success:function (data) {
                    if(data.msg == "success"){
                        console.log("success");
                        location.replace(data.url);
                    }else{
                        if(data.detail == "unknow"){
                            console.log("用户名或密码错误");
                        }else if( data.detail == "locked"){
                            console.log("账号锁定，请联系系统管理员！");
                        }
                    }
                }
            });
            return false;
        }
    </script>
</head>

<body>
<div class="index-main">
    <div class="index-main-body">
        <div class="index-header">
            <h1 class="logo-text">绣冬</h1>
            <h2 class="subtitle">Make the world a better place</h2>
        </div>
        <div class="desk-front">
            <div class="index-tab-navs">
                <div class="navs-slider" data-active-index="1">
                    <a href="#signup" id="regist" data-toggle="tab">注册</a>
                    <a href="#signin" id="login" class="active" data-toggle="tab">登录</a>
                    <span class="navs-slider-bar"></span>
                </div>
            </div>
            <div class="tab-content">
                <div class="tab-pane active" id="signin">
                    <form class="form-signin" action="/index" name="signin" method="post" onsubmit="return ajaxSubmit()">
                        <div class="group-inputs">
                            <div class="input-wrapper">
                                <label for="account" class="sr-only">Account</label>
                                <input type="text" name="name" id="account" class="form-control" placeholder="账号" required
                                       autofocus>
                            </div>
                            <div class="input-wrapper">
                                <label for="password" class="sr-only">Password</label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="密码" required>
                            </div>
                        </div>
                        <div class="button-wrapper">
                            <Button class="sign-button" type="submit">登录</Button>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="remember"/>记住我
                            </label>
                            <a class="uable-login" href="#">无法登录?</a>
                        </div>
                    </form>

                </div>

                <div class="tab-pane" id="signup">
                    <form class="form-signup group-input" name="signup" action="/regist" method="post">
                        <div class="group-inputs">
                            <div class="input-wrapper">
                                <label for="username" class="sr-only">username</label>
                                <input type="text" id="username" class="form-control" placeholder="姓名"
                                       autofocus>
                            </div>
                            <div class="input-wrapper">
                                <label for="regist-account" class="sr-only">Account</label>
                                <input type="text" name="name" id="regist-account" class="form-control" placeholder="登录账号" required>
                            </div>
                            <div class="input-wrapper">
                                <label for="regist-password" class="sr-only">Password</label>
                                <input type="password" name="password" id="regist-password" class="form-control"
                                       placeholder="密码 ( 不少于6位 )" required>
                            </div>
                        </div>
                        <div class="button-wrapper">
                            <Button class="sign-button" type="submit">注册</Button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<!--背景粒子效果-->
<div id="particles-js">
</div>

<script src="${ctx}/static/js/particles.min.js"></script>
<script src="${ctx}/static/js/login.js"></script>
</body>

</html>

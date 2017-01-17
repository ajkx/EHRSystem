<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/16
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>
<script>
    $(document).ready(function () {
        var changeId;
        $('.operation-icon').hide();
        $('.table-body').hover(function () {
            $(this).find('.operation-icon').show();
        }, function () {
            $(this).find('.operation-icon').hide();
        });
    });
</script>
<div id="container">
    <div id="topic-container">
        <div class="topic-header">
            <span>${topic}</span>
            <div class="sort-div">
                排序
                <i class="dropdown-icon fa fa-caret-down"></i>
            </div>
        </div>
        <div class="topic-content">
            <div>
                <div class="table-row table-header">
                    <ul>
                        <li>权限名</li>
                        <li>描述</li>
                        <li>相关角色</li>
                    </ul>
                </div>
                <c:forEach items="${list}" var="per">
                    <div class="table-row table-body">
                        <ul>
                            <li>${per.name}</li>
                            <li>${per.description}</li>
                            <li>
                                <c:forEach items="${per.roles}" var="set">
                                    <a>${set.name}</a>
                                </c:forEach>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

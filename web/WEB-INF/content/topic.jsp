<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/22
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
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
        <div class="topic-bar">
            <button type="button" class="create-btn btn btn-success btn-sm" onclick="showEditModal('${url}/edit')">
                新增${simplename}</button>
        </div>
        <div class="topic-content">
            <div>
                <div class="table-row table-header">
                    <ul>
                        <c:forEach items="${map}" var="map" begin="0" end="0">
                            <c:forEach items="${map.value}" var="submap">
                                <li>${submap.key}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
            <c:forEach items="${map}" var="listmap">
                <div class="table-row table-body">
                    <ul>
                        <c:forEach items="${listmap.value}" var="submap">
                            <li>${submap.value}</li>
                        </c:forEach>
                        <li style="float: right">
                            <div class="operation-icon">
                            <button type="button" class="update-btn btn btn-warning btn-sm"
                                    onclick="showEditModal('${url}/${listmap.key}')">编辑
                            </button>
                            <button type="button" class="delete-btn btn btn-danger btn-sm"
                                    onclick="showDelModal('${url}/delete/${listmap.key}')">删除
                            </button>
                            </div>
                        </li>
                    </ul>
                </div>
            </c:forEach>
            </div>
        </div>
    </div>
</div>

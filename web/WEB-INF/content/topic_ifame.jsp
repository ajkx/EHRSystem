<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/22
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${topic}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/container.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/topic.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>
<body>
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
            <button type="button" class="create-btn btn btn-success btn-sm" data-toggle="modal" data-target="#createModal">新增${simplename}</button>
        </div>
        <div class="topic-content">
            <table class="table table-hover">
                <colgroup>
                    <%--namelist存放对象显示哪些字段集合--%>
                    <c:forEach items="${map.keySet()}">
                        <col width="20%" />
                    </c:forEach>
                </colgroup>
                <thead>
                <tr>
                    <c:forEach items="${map}" var="map" begin="0" end="0">
                        <c:forEach items="${map.value}" var="submap">
                            <th>${submap.key}</th>
                        </c:forEach>
                    </c:forEach>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%--list存放传递的实体对象--%>
                <c:forEach items="${map}" var="listmap">
                    <tr class="info-row">
                        <c:forEach items="${listmap.value}" var="submap">
                            <td>${submap.value}</td>
                        </c:forEach>
                        <td>
                            <div class="operation-icon">
                                <button type="button" data-topic-id="${listmap.key}" class="update-btn btn btn-warning btn-sm">编辑</button>
                                <button type="button" data-topic-id="${listmap.key}" class="delete-btn btn btn-danger btn-sm">删除</button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    ${topic}信息添加
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" action="/${url}/create" method="post">
                    <input type="hidden" class="form-control" id="topicid" name="id">
                    <%--eidtlist存放创建或者修改的编辑字段集合--%>
                    <c:forEach items="${editlist}" var="editlist">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="control-wrapper">
                                    <label for="${editlist}" class="control-label fa-label"><i class="glyphicon glyphicon-home"></i></label>
                                    <input type="text" class="form-control" id="${editlist}" placeholder="${editlist}" name="${editlist}" autocomplete="off">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="form-group">
                        <div class="col-md-12">
                            <div class="control-wrapper">
                                <input type="submit" value="确认" style="float: right" class="btn btn-info">
                            </div>
                        </div>
                    </div>
                    <hr>

                </form>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<%--确认模态框--%>
<div class="modal fade" id="confirmModal">
    <div class="modal-dialog">
        <div class="confirm-content">
            <div class="text-confirm">
                你确定要删除这条数据吗？删除不可恢复！
            </div>
            <div style="text-align:center">
                <button type="button" class="btn-gray btn-confirm">确认
                </button>
                <button type="button" class="btn-gray btn-cancel" data-dismiss="modal">
                    取消
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>

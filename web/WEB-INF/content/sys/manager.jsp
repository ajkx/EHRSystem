<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/16
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>

<div class="topic-toolbar">
    <a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"
       onclick="showEditModal('${url}/edit')">新增${simplename}</a>
</div>
<div style="" class="topic-content ant-table">
    <table>
        <colgroup>
            <col width="25%">
            <col width="25%">
            <col width="25%">
            <col width="25%">
        </colgroup>
        <thead class="ant-table-thead">
        <tr>
            <th>账号</th>
            <th>所属员工</th>
            <th>所有角色</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="ant-table-tbody">
        <c:forEach items="${list}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.hrmResource.name}</td>
                <td>
                    <c:forEach items="${user.roleids}" var="set">
                        <a>${set.name}</a>
                    </c:forEach>
                </td>
                <td>
                    <div>
                        <a href="javascript:void(0)" onclick="showEditModal('${url}/${user.id}')">编辑</a>
                        <span class="ant-divider"></span>
                        <a href="javascript:void(0)" onclick="showDelModal('${url}/delete/${user.id}')">删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

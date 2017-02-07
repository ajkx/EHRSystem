<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/7
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<div class="topic-toolbar">
    <a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"
       onclick="showEditModal('${url}/edit')">新增${simplename}</a>
</div>
<div style="" class="topic-content ant-table">
    <table>
        <colgroup>
            <col width="33%">
            <col width="33%">
            <col width="33%">
            <%--<col width="20%">--%>
        </colgroup>
        <thead class="ant-table-thead">
        <tr>
            <th>员工名称</th>
            <th>描述</th>
            <th>相关角色</th>
            <%--<th>操作</th>--%>
        </tr>
        </thead>
        <tbody class="ant-table-tbody">
        <c:forEach items="${list}" var="per">
            <tr>
                <td>${per.name}</td>
                <td>${per.description}</td>
                <td>
                    <c:forEach items="${per.roles}" var="set">
                        <a>${set.name}</a>
                    </c:forEach>
                </td>
                    <td>
                    <div>
                    <a href="javascript:void(0)" onclick="showEditModal('${url}/${per.id}')">编辑</a>
                    <span class="ant-divider"></span>
                    <a href="javascript:void(0)" onclick="showDelModal('${url}/delete/${per.id}')">删除</a>
                    </div>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


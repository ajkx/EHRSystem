<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/1
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp"%>
<div>
    ${entity.name}<br/>
    ${entity.id}<br/>
    ${entity.cancel}<br/>
        ${entity.subcomid}<br/>
        ${entity.supid}<br/>
    <c:forEach items="${list}" var="list">
        ${list.name}<br/>
        ${list.id}<br/>
        ${list.cancel}<br/>
        ${list.subcomid}<br/>
        ${list.supid}<br/>
    </c:forEach>
</div>
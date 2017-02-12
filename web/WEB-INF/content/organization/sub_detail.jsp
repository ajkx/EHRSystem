<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/1
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>
<div class="detail-container">
    <div class="main-info" style="margin-bottom: 15px">
        <h3 class="" style="margin-bottom: 15px">${entity.name}</h3>
        <p style="color: rgb(153, 153, 153); margin-bottom: 15px">${entity.desc}</p>
        <c:if test="${entity.parent != ''}">
            <p style="margin-bottom: 15px">上级分部:  &nbsp;&nbsp;<a href="${entity.parent}">${entity.parent.name}</a></p>
        </c:if>
    </div>
    <div>
        <c:if test="${not empty list_sub}">

            <table style="margin-bottom: 15px; float:left">
                <thead style="margin-bottom: 15px">
                    <tr>
                        <th>下属分部</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list_sub}" var="sub">
                        <tr  style="margin: 5px 0">
                            <td style="padding: 8px">
                                <a href="${sub.id}">
                                    <c:choose>
                                        <c:when test="${sub.cancel}">
                                            <s>${sub.name}</s>
                                        </c:when>
                                        <c:otherwise>
                                            ${sub.name}
                                        </c:otherwise>
                                    </c:choose>

                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

        <table style="float:left">
            <thead style="margin-bottom: 15px">
            <tr>
                <th>下属部门</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list_dep}" var="dep">
                <tr  style="margin: 5px 0">
                    <td  style="padding: 8px"><a href="${dep.id}">
                        <c:choose>
                            <c:when test="${dep.cancel}">
                                <s>${dep.name}</s>
                            </c:when>
                            <c:otherwise>
                                ${dep.name}
                            </c:otherwise>
                        </c:choose>

                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
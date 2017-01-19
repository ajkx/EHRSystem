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
        <h5 class="" style="margin-bottom: 15px">${entity.name}</h5>
        <p style="color: rgb(153, 153, 153);"></p>
        <c:if test="${entity.subcomid != ''}">
            <p style="margin-bottom: 15px">上级分部:  &nbsp;&nbsp;<a href="${entity.subcomid}">${entity.subcomid.name}</a></p>
        </c:if>
        <c:if test="${entity.supid != ''}">
            <p style="margin-bottom: 15px">上级部门:  &nbsp;&nbsp;<a href="${entity.supid}">${entity.supid.name}</a></p>
        </c:if>
    </div>
    <div>
        <table>
            <thead style="margin-bottom: 15px">
            <tr>
                <th>下属部门</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="dep">
                <tr style="margin: 5px 0">
                    <td style="padding: 8px">
                        <a href="${dep.id}">
                            <c:choose>
                                <c:when test="${dep.cancel}">
                                    <s>${dep.name}</s>
                                </c:when>
                                <c:otherwise>
                                    ${dep.name}
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
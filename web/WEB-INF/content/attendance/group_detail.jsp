<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/2/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>

<div style="margin-top: 15px; width: 1150px; background: rgb(255, 255, 255);">
    <div>
        <div class="" style="margin: 20px 0px 30px; opacity: 1; visibility: visible; transform: translateX(0px);">
            <form class="ant-form ant-form-horizontal">
                <input type="hidden" name="id" value="${group.id}"/>
                <input type="hidden" name="groupType" id="groupType"
                       value="${group.groupType == null ? 1 : group.groupType}"/>
                <input type="hidden" name="isAuto" id="autoRest" value="${group.auto == true ? 1 : 0}"/>
                <input type="hidden" name="monday" id="monday" value="${group.monday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="tuesday" id="tuesday" value="${group.tuesday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="wednesday" id="wednesday" value="${group.wednesday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="thursday" id="thursday" value="${group.thursday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="friday" id="friday" value="${group.friday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="saturday" id="saturday" value="${group.saturday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="sunday" id="sunday" value="${group.sunday.rest == true ? "" : group.monday.id}"/>
                <input type="hidden" name="resources" id="resourceIds" value="<c:forEach items="${group.resources}" var="resource">${resource.id},</c:forEach>"/>
                <input type="hidden" id="currentNode" value=""/>

                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤组名称</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control "><span class="ant-input-wrapper">
                            <input type="text" placeholder="请输入考勤组名称" class="ant-input ant-input-lg" name="name"
                                   value="${group.name}" autocomplete="off"></span></div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤人员</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control ">
                            <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg" onclick="chooseModal(this,'/resource/modal/list')" data-index="resourceIds"><span>请选择</span></button>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤类型</label></div>
                    <div class="ant-col-18">
                        <div class="ant-form-item-control ">
                            <div class="ant-radio-group ant-radio-group-large">
                                <label style="font-weight: 500;" class="ant-radio-wrapper ant-radio-wrapper-checked">
                                    <span class="ant-radio <c:if test="${group.groupType == null || group.groupType == 1}">ant-radio-checked</c:if> ">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="1" value="on">
                                    </span>
                                    <span>固定班制 (每天考勤时间一样)</span>
                                </label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${group.groupType == 2}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="2" value="on">
                                    </span><span>排班制 (自定义设置考勤时间)</span>
                                </label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${group.groupType == 3}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="3" value="on">
                                    </span>
                                    <span>自由工时（不设置班次，随时打卡）</span>
                                </label>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item type1" style="<c:if test="${group.groupType != null && group.groupType != 1}">display:none;</c:if> ">
                    <div class="ant-col-2 ant-form-item-label"><label class="">工作日设置</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <div>
                                <div>
                                    <div style="margin-bottom: 5px;">
                                        快捷设置班次：<span id="currentSchedule" data-id=""></span><a data-index="all" style="margin-left: 10px;" href="javascript:void(0)"
                                     onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></div>
                                    <div class=" clearfix">
                                        <div class="">
                                            <div class="ant-spin"><span class="ant-spin-dot"></span>
                                                <div class="ant-spin-text">加载中...</div>
                                            </div>
                                            <div class="ant-spin-container">
                                                <div class="ant-table ant-table-middle ant-table-scroll-position-left">
                                                    <div class="ant-table-content">
                                                        <div class=""><span><div class="ant-table-body"><table class=""><colgroup><col><col
                                                                style="width: 70px; min-width: 70px;"><col
                                                                style="width: 300px; min-width: 300px;"><col
                                                                style="width: 120px; min-width: 120px;"></colgroup><thead
                                                                class="ant-table-thead"><tr><th
                                                                class="ant-table-selection-column"><span><label
                                                                class="ant-checkbox-wrapper"><span class="ant-checkbox"><span
                                                                class="ant-checkbox-inner"></span>
                                                            <input
                                                                    type="checkbox" id="checkAll"
                                                                    class="ant-checkbox-input" value="off"></span>
                                                                </label>
                                                                </span>
                                                                </th>
                                                                <th class=""><span>工作日</span></th>
                                                                <th class=""><span>班次时间段</span></th>
                                                                <th class=""><span>操作</span></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody class="ant-table-tbody">
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.monday != null && group.monday.rest != true}">ant-checkbox-checked</c:if> "><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                            <input type="checkbox"
                                                                                   class="ant-checkbox-input"
                                                                                   data-input="monday" data-check="true"
                                                                                   value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周一

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.monday != null && group.monday.rest != true}">
                                                                                    ${monday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td class=""><a data-index="monday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.tuesday != null && group.tuesday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="tuesday"
                                                                                       data-check="true"
                                                                                       value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周二

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.tuesday != null && group.tuesday.rest != true}">
                                                                                    ${tuesday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="tuesday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.wednesday != null && group.wednesday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="wednesday"
                                                                                       data-check="true"
                                                                                       value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周三

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.wednesday != null && group.wednesday.rest != true}">
                                                                                    ${wednesday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="wednesday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.thursday != null && group.thursday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="thursday"
                                                                                       data-check="true"
                                                                                       value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周四

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.thursday != null && group.thursday.rest != true}">
                                                                                    ${thursday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="thursday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.friday != null && group.friday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="friday"
                                                                                       data-check="true"
                                                                                       value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周五

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.friday != null && group.friday.rest != true}">
                                                                                    ${friday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="friday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.saturday != null && group.saturday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="saturday"
                                                                                       data-check="true"
                                                                                       value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周六

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.saturday != null && group.saturday.rest != true}">
                                                                                    ${saturday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="saturday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${group.sunday != null && group.sunday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="sunday"
                                                                                       data-check="true"
                                                                                       value="off"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周日

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${group.sunday != null && group.sunday.rest != true}">
                                                                                    ${sunday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="sunday" href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'schedule/modal/list')">更改班次</a></td>
                                                                    </tr>
                                                                </tbody>
                                                                </table>
                                                        </div>
                                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="margin-top: 5px;">
                                        <label class="ant-checkbox-wrapper">
                                            <span class="ant-checkbox">
                                                <span class="ant-checkbox-inner"></span>
                                                <input type="checkbox" id="isAuto" class="ant-checkbox-input"
                                                       value="off">
                                            </span>
                                        </label>
                                        法定节假日自动排休

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item type1">
                    <div class="ant-col-2 ant-form-item-label"><label class="">特殊日期</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <div>
                                <div>
                                    <button type="button" class="ant-btn ant-btn-ghost"><span>添 加</span></button>
                                    <span style="margin-left: 12px; font-size: 12px; color: rgb(153, 153, 153);">必须打卡的日期</span>
                                </div>
                                <div style="width: 550px; padding-top: 10px; padding-bottom: 10px;">
                                    <div></div>
                                </div>
                                <div>
                                    <button type="button" class="ant-btn ant-btn-ghost"><span>添 加</span></button>
                                    <span style="margin-left: 12px; font-size: 12px; color: rgb(153, 153, 153);">不用打卡的日期</span>
                                </div>
                                <div style="width: 550px; padding-top: 10px; padding-bottom: 0px;">
                                    <div></div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item type2" style="<c:if test="${group.groupType == null || group.groupType != 2}">display:none;</c:if>">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤班次</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg"><span>选择班次</span></button>
                            <div style="margin-top: 15px; margin-bottom: -10px;"></div>
                        </div>
                    </div>
                </div>
                <div class="ant-row" style="margin: 24px 0px;">
                    <div class="ant-col-16 ant-col-offset-8">
                        <button type="button" class="ant-btn ant-btn-primary"><span>保存设置</span></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('input[type="radio"]').click(function () {
            var type = $(this).attr("data-type");
            var groupType = $('#groupType');
            if (groupType.val() == type) {
                return;
            }
            $('.ant-radio-checked').removeClass('ant-radio-checked');
            $(this).parent().addClass('ant-radio-checked');
            groupType.val(type);
            console.log(type);
            switch (parseInt(type)) {
                case 1:
                    $('.type1').css("display", "block");
                    $('.type2').css("display", "none");
                    break;
                case 2:
                    $('.type1').css("display", "none");
                    $('.type2').css("display", "block");
                    break;
                case 3:
                    $('.type1').css("display", "none");
                    $('.type2').css("display", "none");
                    break;
            }
        });
        $('input[data-check="true"]').click(function () {
            var node = $(this);
            var nodeStr = node.attr('data-input');
            if (node.val() == 'on') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("off");
                $('#' + nodeStr).val("");
                node.parents(".ant-table-selection-column").siblings(".scheduleName").text("休息");
            } else {
                node.parent().addClass(' ant-checkbox-checked');
                node.val("on");
                $('#' + nodeStr).val($("#currentSchedule").attr("data-id"));
                var text = $("#currentSchedule").text();
                node.parents(".ant-table-selection-column").siblings(".scheduleName").text(text == "" ? "休息" : text);
        }
        });
        $('#checkAll').click(function () {
            var checks = $('input[data-check="true"]');
            var node = $(this);
            if (node.val() == 'on') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val('off');
                checks.each(function(){
                    $(this).parent().removeClass('ant-checkbox-checked');
                    $(this).val("off");
                    var nodeStr = $(this).attr('data-input');
                    $("#" + nodeStr).val("");
                    $(this).parents(".ant-table-selection-column").siblings(".scheduleName").text("休息");
                });
            } else {
                node.parent().addClass('ant-checkbox-checked');
                node.val("on");
                var text = $("#currentSchedule").text();
                checks.each(function(){
                    $(this).parent().addClass('ant-checkbox-checked');
                    $(this).val('on');
                    var nodeStr = $(this).attr('data-input');
                    $("#" + nodeStr).val($("#currentSchedule").attr("data-id"));
                    $(this).parents(".ant-table-selection-column").siblings(".scheduleName").text(text == "" ? "休息" : text);
                });

            }
        });
        $("#isAuto").click(function () {
            var auto = $(this);
            if (auto.val() == 'on') {
                auto.parent().removeClass("ant-checkbox-checked");
                auto.val("off");
                $("#autoRest").val(0);
            } else {
                auto.parent().addClass("ant-checkbox-checked");
                auto.val("on");
                $("#autoRest").val(1);
            }
        });

    });
</script>

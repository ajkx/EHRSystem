<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/1/4
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<div class="modal-dialog" style="width:700px">
    <div class="modal-content" id="edit-modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <div class="modal-title" id="myModalLabel">
                ${topic}
            </div>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" id="modal-form" role="form" action="${action}" method="post"
                  onsubmit="return submitForm()">
                <div class="div-group">
                    <span>班次名称</span>
                    <input type="text" class="form-control u-input"
                           style="width: 140px; margin-right: 20px; margin-left: 10px" name="name" value="${schedule.name}"/>
                </div>
                <div style="color: rgb(196, 196, 196); margin-top: 10px; margin-bottom: 30px; margin-left: 60px;">
                    最多6个字符（中英文或数字），首个字符会作为班次简称
                </div>
                <input type="hidden" name="scheduleType" id="sheduletype" value="${schedule.scheduleType}"/>
                <input type="hidden" name="acrossDay" id="acrossDay" value="${schedule.acrossDay == true ? 1 : 0}"/>
                <div class="div-group">
                    <span style="margin-right: 20px;">设置该班次一天内上下班的次数</span>
                    <div class="btn-group">
                        <div class="div-component " style="border-top-right-radius: 0;
border-bottom-right-radius: 0" onclick="setScheduleType(1,this)">1天1次上下班
                        </div>
                        <div class="div-component" style="border-radius: 0;border-left: 0;border-right:0"
                             onclick="setScheduleType(2,this)">1天2次上下班
                        </div>
                        <div class="div-component" style="border-top-left-radius: 0;
border-bottom-left-radius: 0" onclick="setScheduleType(3,this)">1天3次上下班
                        </div>
                    </div>
                </div>
                <div style="margin-top: 15px;">
                    <div class="div-group" id="oneSchedule">
                        <div style="width: 275px; display: inline-block;">
                            <span style="width: 200px; margin-right: 20px;">第1次上下班</span>
                            上班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick" value="" name="first_up"
                                       style="width:100px" readonly>
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            下班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick" value="" name="first_down"
                                    style="width:100px" readonly><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                    <div class="div-group" id="twoSchedule" style="display: none;">
                        <div style="width: 275px; display: inline-block;">
                            <span style="width: 200px; margin-right: 20px;">第2次上下班</span>
                            上班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick" value="" name="second_up"
                                       style="width:100px" readonly>
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            下班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick" value="" name="second_down"
                                    style="width:100px" readonly><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                    <div class="div-group" id="threeSchedule" style="display: none;">
                        <div style="width: 275px; display: inline-block;">
                            <span style="width: 200px; margin-right: 20px;">第3次上下班</span>
                            上班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick" value="" name="third_up"
                                       style="width:100px" readonly>
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            下班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick" value="" name="third_down"
                                    style="width:100px" readonly><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                </div>
                <div class="div-group">
                    <input type="hidden" name="timecount"/>
                    合计工作时长9小时0分钟<span style="color: rgb(196, 196, 196);">（考勤统计工作时长及请假出差外出统计，会以此时间为准）</span>
                </div>

                <div class="div-group">
                    <div style="width:14px;height: 14px;display: inline-block;margin-right: 8px">
                    <input type="checkbox" class="icheckbox"/>
                    </div>
                    <span>下班不用打卡（开启后，下班不打卡也不会记作异常)</span>
                </div>
                <div class="div-group" style="margin-top: 20px; border-top-color: rgb(228, 228, 228); border-top-width: 1px; border-top-style: solid;"></div>
                <div class="div-group" style="color: rgb(148, 148, 148);">弹性时间设置</div>
                <div class="div-group">
                    <span>上班打卡时长</span>
                    <input type="number" class="form-control u-input input-number" name="scope_up" style="width:80px; margin-left: 20px" min="0" max="1440" value="30" autocomplete="off"/>
                </div>
                <div class="div-group">
                    <span>下班打卡时长</span>
                    <input type="number" class="form-control u-input input-number" name="scope_down" style="width:80px; margin-left: 20px" min="0" max="1440" value="30" autocomplete="off"/>
                </div>
                <div class="div-group">
                    <span>迟到/早退限定时长（超过则记作旷工）</span>
                    <input type="number" class="form-control u-input input-number" name="offsetTime" style="width:80px; margin-left: 20px" min="0" max="1440" value="30" autocomplete="off"/>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="u-btn u-btn-lg" type="button">取 消</button>
            <button class="u-btn u-btn-primary u-btn-lg" type="submit" style="margin-left: 8px">确 定</button>
        </div>
    </div>
</div>

<script>
    var input = $('.timepick-group');
    input.clockpicker({
        autoclose: true
    });

    var checkbox = $('.icheckbox');
    checkbox.iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });
</script>
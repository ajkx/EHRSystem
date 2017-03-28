<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<div class="modal-dialog" style="width:500px">
    <div class="modal-content" id="edit-modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                ${topic}
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" role="form" action="${action}" method="post"
                  onsubmit="return submitForm()">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <input type="hidden" class="form-control" id="topicid" name="id" value="${map.id}"/>
                <div class="form-group">
                    <div class="control-wrapper">
                        <label for="name">名称<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" id="name" name="name" value="${map.name}" required>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">加班类型</label>
                        <select class="form-control">
                            <option>平时加班</option>
                            <option>周末加班</option>
                            <option>节日加班</option>
                        </select>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">加班开始时间<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" id="date" name="date" value="${map.name}" required>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">加班结束时间<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" id="endDate" name="endDate" value="${map.name}" required>
                    </div>
                    <div class="div-group">
                        <input type="hidden" name="timecount"/>
                        合计工作时长<span id="timeCount">9小时0分钟</span>
                        <span style="color: rgb(196, 196, 196);">（考勤统计工作时长，会以此时间为准）</span>
                    </div>

                </div>
                <div class="form-group">
                    <div class="control-wrapper">
                        <input type="submit" value="保存" style="float: right" class="btn btn-primary">
                    </div>
                </div>
                <hr>
            </form>
        </div>
    </div>
</div>
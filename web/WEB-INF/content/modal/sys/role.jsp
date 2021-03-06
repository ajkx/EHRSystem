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
                        <label for="name">所有操作者</label>
                        <input type="hidden" class="form-control mainid" name="resources" value="${map.resources}">
                        <div class="create_input_div" onclick="showMulitSelectList(this,'resource',event)">
                            <c:forEach items="${list}" var="map">
                                <span class="smallSpan" label-id="${map.id}">${map.name}</span>
                            </c:forEach>
                            <i class="fa fa-times" style="float: right;margin-top: 4px;display:none" title="取消"
                               onclick="clearSelectList(this,event)"></i>
                        </div>
                        <div class="selectlist" style="display : none">
                            <ul>
                            </ul>
                        </div>
                    </div>

                    <div class="control-wrapper">
                        <label for="name">所有权限</label>
                        <input type="hidden" class="form-control mainid" name="permissions" value="${map.permissions}">
                        <div class="create_input_div" onclick="showMulitSelectList(this,'permission',event)">
                            <c:forEach items="${list}" var="map">
                                <span class="smallSpan" label-id="${map.id}">${map.name}</span>
                            </c:forEach>
                            <i class="fa fa-times" style="float: right;margin-top: 4px;display:none" title="取消"
                               onclick="clearSelectList(this,event)"></i>
                        </div>
                        <div class="selectlist" style="display : none">
                            <ul>
                            </ul>
                        </div>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">详细信息</label>
                        <textarea class="form-control" rows="3" name="description">${map.description}</textarea>
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
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
    <form class="form-horizontal" id="modal-form" role="form" action="${action}" method="post" onsubmit="return submitForm()">
        <%--eidtlist存放创建或者修改的编辑字段集合--%>
            <input type="hidden" class="form-control" id="topicid" name="id" value="${map.id}"/>
            <div class="form-group">
                <div class="control-wrapper">
                    <label for="name">名称<span class="not_empty_tips">*</span></label>
                    <input type="text" class="form-control" id="name" name="name" value="${map.name}" required>
                </div>
                <div class="control-wrapper">
                    <label for="name">地址<span class="not_empty_tips">*</span></label>
                    <input type="text" class="form-control" id="address" name="address" value="${map.address}" required>
                </div>
                <div class="control-wrapper">
                    <label for="name">邮编</label>
                    <input type="text" class="form-control" id="postcode" name="postcode" value="${map.postcode}" >
                </div>
                <div class="control-wrapper">
                    <label for="name">城市</label>
                    <input type="text" class="form-control" id="city" name="city" value="${map.city}" >
                </div>
                <div class="control-wrapper">
                    <label for="name">国家</label>
                    <input type="text" class="form-control" id="country" name="country" value="${map.country}">
                </div>
                <div class="control-wrapper">
                    <label for="name">电话</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${map.phone}">
                </div>
                <div class="control-wrapper">
                    <label for="name">传真</label>
                    <input type="text" class="form-control" id="fax" name="fax" value="${map.fax}">
                </div>
            </div>
        <div class="form-group">
            <div class="control-wrapper">
                <input type="submit" value="保存" style="float: right;" class="btn btn-primary">
            </div>
        </div>
        <hr>
    </form>
</div>
</div>
    </div>
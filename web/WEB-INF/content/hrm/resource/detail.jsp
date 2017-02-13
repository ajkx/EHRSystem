<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/13
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<div class="col-xs-12" style="margin-top: 50px;">
    <div class="panel panel-default">
        <div class="panel-body" style="height: 110px;padding: 35px 15px 15px 15px;">
            <img id="resourceImg" class="img-thumbnail" src="${ctx}/static/image/defaultUser.png" />
            <div class="row">
                <div class="col-xs-12">
                    <span style="font-size:14px; color:#4A4A4A; margin-left:170px; ">${resource.name}</span>
                </div>
            </div>
            <div class="col-xs-12">
                <c:choose>
                    <c:when test="${resource.status == '离职'}">
                        <a class="ant-btn w100 float-right">返聘</a>
                    </c:when>
                    <c:otherwise>
                        <a class="ant-btn w100 float-right">通用审批</a>
                        <a class="ant-btn w100 float-right">合同</a>
                        <a class="ant-btn w100 float-right">离职</a>
                        <a class="ant-btn w100 float-right">调动</a>
                        <a class="ant-btn w100 float-right">转正</a>
                        <a class="ant-btn w100 float-right">考勤</a>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>

    <div class="row rowContent">
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color:#FD6B6C;">
                    <div class="row">
                        <div class="col-xs-12">
                            档案
                            <span><a href="#" style="color: white;">详情 &gt;</a></span>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 detail-info">
                            <div>姓名：${resource.name}</div>
                            <div>工号：${resource.workCode}</div>
                            <div>性别：${resource.sex}</div>
                            <div>部门：${resource.department.name}</div>
                            <div>手机号：${resource.mobile}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color:#FBA068;">
                    <div class="row">
                        <div class="col-xs-12">
                            简历
                            <span id="goMyResume">详情 &gt;</span>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 detail-info">
                            <div>学校名称：</div>
                            <div>公司名称：</div>
                            <div>&nbsp;</div>
                            <div>&nbsp;</div>
                            <div>&nbsp;</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color:#58C665;">
                    <div class="row">
                        <div class="col-xs-12">
                            调动记录
                            <span id="goMyTransferRecord">详情 &gt;</span>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 detail-info">

                            <div>&nbsp;&nbsp;</div>

                            <div>&nbsp;&nbsp;</div>

                            <div>&nbsp;&nbsp;</div>

                            <div>&nbsp;&nbsp;</div>

                            <div>&nbsp;&nbsp;</div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row rowContent">
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color:#03A9F4;">
                    <div class="row">
                        <div class="col-xs-12">
                            考勤记录
                            <span id="goMyContractInfo">详情 &gt;</span>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 detail-info">

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color:#7099D1;">
                    <div class="row">
                        <div class="col-xs-12">
                            合同信息
                            <span id="goMyProfession">详情 &gt;</span>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 detail-info">

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color:#B99AFA;">
                    <div class="row">
                        <div class="col-xs-12">
                            职业资格
                            <span id="goMySalaryPwd">详情 &gt;</span>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 detail-info">

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                            <div>&nbsp;</div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



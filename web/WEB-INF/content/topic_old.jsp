<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/22
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<script>
    $(function(){
        var search = $('#searchinput');
        search.bind("keydown",function(e){
           if(e.keyCode == "13"){
               searchData();
           }
        });
    });
    function searchData(){
        $.ajax()
    }
</script>
<div class="topic-toolbar">
    <a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"
       onclick="showEditModal('${url}/edit')">新增${simplename}</a>
    <div class="ant-search-input-wrapper" style="width: 200px; float: right;margin-bottom: 10px">
        <span class="ant-input-group ant-search-input">
            <div class="ant-select ant-select-combobox ant-select-enabled">
                <div class="ant-select-selection ant-select-selection--single" role="combobox"
                     aria-autocomplete="list" aria-haspopup="true" aria-expanded="false">
                    <div class="ant-select-selection__rendered">
                        <ul>
                            <li class="ant-select-search ant-select-search--inline">
                                <div class="ant-select-search__field__wrap">
                                    <input placeholder="请输入名称" class="ant-select-search__field"
                                           id="searchinput">
                                    <span class="ant-select-search__field__mirror">
                                    </span>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="ant-input-group-wrap">
                <button type="button" class="ant-btn ant-search-btn" style="margin-top: 0px;"
                        onclick="searchData()">
                    <i class="fa fa-search" style="margin-top: 0px;">
                    </i>
                </button>
            </div>
        </span>
    </div>
    <div class="clearfix" style="clear: both"></div>
</div>

    <div style="" class="topic-content ant-table">
        <table>
            <colgroup>
                <c:forEach items="${map}" var="map" begin="0" end="0">
                    <c:forEach items="${map.value}" var="submap">
                        <col width="${width}">
                    </c:forEach>
                    <col width="${width}">
                </c:forEach>
            </colgroup>
            <thead class="ant-table-thead">
            <tr>
                <c:forEach items="${map}" var="map" begin="0" end="0">
                    <c:forEach items="${map.value}" var="submap">
                        <th>${submap.key}</th>
                    </c:forEach>
                    <th>操作</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody class="ant-table-tbody">
            <c:forEach items="${map}" var="listmap">
                <tr>
                    <c:forEach items="${listmap.value}" var="submap">
                        <td>${submap.value}</td>
                    </c:forEach>
                    <td>
                        <div>
                            <a href="javascript:void(0)" onclick="showEditModal('${url}/${listmap.key}')">编辑</a>
                            <span class="ant-divider"></span>
                            <a href="javascript:void(0)" onclick="showDelModal('${url}/delete/${listmap.key}')">删除</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

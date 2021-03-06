<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/20
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>

<script>
    $(function () {
        var nodeStr = $('#currentNode').val();
        var currentList = $('#'+nodeStr).val();
        //初始化已选的员工的li
        if (typeof(currentList) != "undefined" && currentList != "") {
            var ul = $("#resource-list");
            $.get("/resource/list/array", {resourceStr: currentList}, function (result) {
                if (result.status) {
                    $(result.data).each(function (index, element) {
                        addTag(element.id, element.name);
                    });
                }
            });
        }
        //初始化组织架构树
        $.get("/organization/tree", {type: "checked"}, function (data) {
            $('#organization-tree').treeview({
                //开通超链接效果
                enableLinks: false,
                backColor: '#fff',
                showBorder: false,
                levels: 1,
                showIcon: false,
                showCheckbox:true,
                onhoverColor: '#ecf6fd',
                selectedBackColor: '#d2eafb',
                selectedColor: '#666',
                expandIcon: 'fa fa-caret-right',
                collapseIcon: 'fa fa-caret-down',
                color: '#666',
                data: data,
                onNodeSelected: function (event, node) {
                    searchResource(node.href);
                },
                onNodeChecked: function(event, node) {
                    var href = node.href;
                    var index = href.lastIndexOf("/");
                    var id = href.substring(index + 1, href.length);
                    if(href.indexOf("subcompany") > 0){
                        addTag("s"+id,node.text,"ant-tag-red",node);
                    }else if(href.indexOf("department") > 0){
                        addTag("d"+id,node.text,"ant-tag-green",node);
                    }
                },
                onNodeUnchecked: function (event, node) {
                    var href = node.href;
                    var index = href.lastIndexOf("/");
                    var id = href.substring(index + 1, href.length);
                    if(href.indexOf("subcompany") > 0){
                        id = "s"+id;
                    }else if(href.indexOf("department") > 0){
                        id = "d" + id;
                    }
                    var currentLi = $('#resource-list li[data-id="' + id + '"]');
                    if (currentLi.length > 0) {
                        currentLi.remove();
                    }
                }
            });
        });
        //初始化checkAll的全选事件
        $(".checkAll").click(function () {
            var node = $(this).children("i");
            var resourceList = $("#resource-list-choose").children("li");
            if (node.hasClass("fa-check-square")) {
                node.removeClass("fa-check-square");
                resourceList.each(function (index, element) {
                    var i = $(element).find('i');
                    var id = $(element).attr("data-id");
                    i.removeClass("fa-check-square");
                    var currentLi = $('#resource-list li[data-id="' + id + '"]');
                    currentLi.remove();
                });
            } else {
                node.addClass("fa-check-square");
                resourceList.each(function (index, element) {
                    var i = $(element).find('i');
                    var id = $(element).attr("data-id");
                    var name = $(element).attr("data-name");
                    if (!i.hasClass('fa-check-square')) {
                        i.addClass("fa-check-square");
                        addTag(id, name,'ant-tag-blue',i);
                    }
                });
            }
        });

        //绑定搜索框的enter和图标点击事件
        var search = $('#resourceInput');
        search.bind("keydown",function(e){
            if(e.keyCode == "13"){
                var value = search.val();
                value = Trim(value, "g");
                value = CastChar(value);
                searchTree(value);
                searchResource("/resource/list/name",value);
            }
        });
        var searchBtn = $('.search-input .fa-search');
        searchBtn.click(function(){
            var value = search.val();
            value = Trim(value, "g");
            value = CastChar(value);
            searchTree(value);
            searchResource("/resource/list/name",search.val());
        });

    });

    //搜索树
    function searchTree(value){
        var values = value.split(",");
        //拼接搜索表达式
        var pattern = "";
        for(var i = 0; i < values.length; i++) {
            if(values[i] == ""){
                continue;
            }
            pattern += values[i] + "|";
        }
        pattern = pattern.substring(0, pattern.length - 1);
        //树菜单搜索
        $('#organization-tree').treeview('search',[pattern,
            {
                ignoreCase: true,
                exactMatch: false,
                revealResults: true,
            }]);
    }
    //点击确定按钮，隐藏摸态框并将数据添加到相应的元素上
    function setResources() {
        $('#edit-modal').modal('hide');
        var nodeStr = $('#currentNode').val();
        var list = $("#resource-list").children("li");
        var value = "";
        var resCount = 0;
        var resText = "";
        var subCount = 0;
        var subText = "";
        var depCount = 0;
        var depText = "";
        list.each(function(index,element) {

            var temp = $(element).attr("data-id");
            if(temp.indexOf("s") >= 0){
                subCount++;
                subText += $(element).children("span").text()+" ";
            }else if(temp.indexOf("d") >= 0){
                depCount++;
                depText += $(element).children("span").text()+" ";
            }else{
                resCount++;
                console.log("r" + resCount);
            }
            value += temp+",";
        });
        if(resCount > 0){
            resText = resCount + "人"
        }
        var text = subText + depText + resText;
        if(text == "")text = "全公司";
        value = value.substring(0, value.length - 1);
        resourceCallBack(value,text);
//
//        $("#" + nodeStr).val(value);
//        $('button[data-index="'+nodeStr+'"]').children("span").text(count+"人");
    }

    //执行清除操作
    function clearData(){
        //清空组织架构树的清理
        $('#organization-tree').treeview('uncheckAll', { silent: true });
        //清空待选列表
        $("#resource-list-choose").empty();
        //清空已选列表
        $('#resource-list').empty();
        $('.checkAll').children('i').removeClass('fa-check-square');
        resourceClearCallBack();
    }

    function searchResource(url,value) {

        $.get(url,{name:value}, function (result) {
            if (result.status) {
                //获取当前激活的面板下的ul,并清空元素
                var ul = $('#resource-list-choose');
                ul.empty();
                //移除全选的点击状态
                var i = $(".checkAll").children("i");
                i.removeClass("fa-check-square");
                var index1 = 0;
                //遍历返回的数据
                $(result.data).each(function (index, element) {
                    //添加员工待选列表
                    index1 = addResourceList(ul, element,index1);
                });
                //如果新搜索的人员都是已选的，即checkAll为true
                if(index1 == result.data.length && index1 != 0){
                    i.addClass("fa-check-square");
                }
                //绑定checkDiv点击事件
                bindCheckDiv();
            } else {
                toastr.error(result.msg);
            }
        });
    }
    //resourceList里的删除图标的点击方法
    function removeTag(node) {
        //获取点击的人员的ID
        var id = $(node).parent().attr("data-id");
        //删除所在的li
        $(node).parent().remove();

        //移除组织架构面板里的待选列表的对应li
        var li1 = $("#resource-list-choose li[data-id='" + id + "']");
        if (li1.length > 0) {
            li1.find("i").removeClass('fa-check-square');
        }
    }

    //绑定checkbox的点击事件
    function bindCheckDiv() {
        //绑定激活面板下的checkdiv
        $("#resource-list-choose .check-div").click(function () {
            var node = $(this).children('i');
            var id = $(this).parent().attr("data-id");
            var name = $(this).parent().attr("data-name");
            //判断当前的点击状态
            if (node.hasClass("fa-check-square")) {
                node.removeClass("fa-check-square");
                //获取对应的存数据的li 并且删除
                var currentLi = $('#resource-list li[data-id="' + id + '"]');
                if (currentLi.length > 0) {
                    currentLi.remove();
                }
            } else {
                node.addClass("fa-check-square");
                addTag(id, name,'ant-tag-blue',node);
            }
        });
    }
    function addResourceList(ul, element,index) {
        var li = $("<li></li>").attr("data-id", element.id).attr("data-name", element.name);
        var span = $("<span></span>").text(element.name);
        var span2 = $("<span class='span-name'></span>").text(element.department);
        var div = $("<div class='check-div'></div>");
        var i = $("<i class='fa fa-square-o'></i>");
        //根据currentLi是否为空判断可选列表是否已选
        var currentLi = $('#resource-list li[data-id="' + element.id + '"]');
        if (currentLi.length > 0) {
            i.addClass("fa-check-square");
            index++;
        }
        div.append(i);
        li.append(span);
        li.append(span2);
        li.append(div);
        ul.append(li);
        return index;
    }


    function addTag(id, name,color,node) {
        var ul = $("#resource-list");
        var li = $("<li class='ant-tag'></li>").attr("data-id", id).addClass(color);
        var span = $("<span></span>").text(name);
        var i = $("<i class='fa fa-times'></i>").click(function () {
            removeTag(this);
            if(typeof(node.href) ==  "undefined"){
                node.removeClass("fa-check-square");
            }else{
                $('#organization-tree').treeview('uncheckNode',[node.nodeId,{silent : true}]);
            }
        });
        li.append(span);
        li.append(i);
        ul.append(li);
    }
</script>
<div class="modal-dialog" style="width: 600px;height:550px;">
    <div class="modal-content" id="resource-content">
        <div class="modal-header">
            <span class="title">部门/员工列表</span>
            <i class="fa fa-times" data-dismiss="modal"
               style="display: block;position: absolute;top: 12px;right: 0;bottom: 0;width: 45px;font-size: 18px;cursor: pointer;"></i>
        </div>
        <div class="modal-body" style="padding: 0;">
            <div style="padding:10px">
                <div style="width: 570px; height: 440px;">
                    <ul id="resource-list"
                        style="height:80px;background: #fff;border: dashed 1px #e0e0e0;margin-bottom: 8px;overflow: auto;">
                    </ul>
                    <div class="select-menu">
                        <div class="search-input" style="width: 100%; display: block;">
                            <input id="resourceInput" placeholder="输入员工姓名或部门名称，多个条件用逗号,分割">
                            <i class="fa fa-search"></i>
                        </div>
                    </div>
                    <div id="resource-tab" class="tab-content" style="border: solid 1px #e0e0e0;border-bottom:none;border-top: none;">
                        <div id="organizationPanel" style="position: relative;height: 320px;">
                            <div class="resource-menu">
                                <div id="organization-tree" style="padding: 5px 0">
                                </div>
                            </div>
                            <ul class="resource-header"
                                style="border-left: solid 1px #e0e0e0;position: absolute;top: 0;right: 0;left: 50%;background-color: rgb(247, 247, 247)">
                                <li style="padding: 0 30px 0 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;position: relative;line-height: 30px;font-size: 14px;">
                                    <span>姓名</span>
                                    <span style="position: absolute;top: 0;left: 39%;">部门</span>
                                    <div class="checkAll check-div"
                                         style="position: absolute;top: 0;right: 5px;cursor: pointer;">
                                        <i class="fa fa-square-o">
                                        </i>
                                    </div>
                                </li>
                            </ul>
                            <ul id="resource-list-choose">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="u-btn u-btn-lg" data-dismiss="modal" type="button" onclick="clearData()">清 除</button>
            <button class="u-btn u-btn-lg" data-dismiss="modal" type="button">取 消</button>
            <button class="u-btn u-btn-primary u-btn-lg" style="margin-left: 8px" onclick="setResources()">确 定</button>
        </div>
    </div>

</div>
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
        var currentList = $('#resourceIds').val();
        //初始化已选的员工的li
        if(currentList != ""){
            var ul = $("#resource-list");
            $.get("/resource/list/array",{resourceStr:currentList},function(result){
                if(result.status){
                    $(result.data).each(function(index,element) {
                        addTag(element.id, element.name);
                    });
                }
            });
        }
        //初始化组织架构树
        $.get("/organization/tree",{type:"resource"},function(data){
            $('#organization-tree').treeview({
                //开通超链接效果
                enableLinks:false,
                backColor: '#fff',
                showBorder: false,
                levels: 1,
                showIcon:true,
                onhoverColor:'#ecf6fd',
                selectedBackColor:'#d2eafb',
                selectedColor:'#666',
                expandIcon:'fa fa-caret-right',
                collapseIcon:'fa fa-caret-down',
                color:'#666',
                data:data,
                onNodeSelected:function(event,node){
                    searchResource(node.href);
                }
            });
        });
        //初始化tabpane的事件
        $('a[data-toggle="tab"]').on('show.bs.tab',function(e){
            $(".select-menu .active").removeClass('active');
            $(this).addClass('active');
            var id = $(this).attr("href");
            //切换面板判断待选列表存不存在
            var lis = $(id).children(".resource-list").children('li');
            lis.each(function(index,element) {
                var i = $(element).find('i');
                //如果是在前一个面板新增的，则将待选列表的对应的i加上class
                if(!i.hasClass("fa-check-square")){
                    var currentLi = $('#resource-list li[data-id="'+$(element).attr("data-id")+'"]');
                    if(currentLi.length > 0 ){
                        i.addClass("fa-check-square");
                    }
                }

            });
        });
        //初始化checkAll的全选事件
        $(".checkAll").click(function () {
            console.log("a");
            var node = $(this).children("i");
            var resourceList = $(this).parents('.resource-header').siblings(".resource-list").children("li");
            if(node.hasClass("fa-check-square")){
                node.removeClass("fa-check-square");
                resourceList.each(function(index,element) {
                    var i = $(element).find('i');
                    var id = $(element).attr("data-id");
                    i.removeClass("fa-check-square");
                    var currentLi = $('#resource-list li[data-id="'+id+'"]');
                    currentLi.remove();
                });
            }else{
                node.addClass("fa-check-square");
                resourceList.each(function(index,element) {
                    var i = $(element).find('i');
                    var id = $(element).attr("data-id");
                    var name = $(element).attr("data-name");
                    if(!i.hasClass('fa-check-square')){
                        i.addClass("fa-check-square");
                        addTag(id, name);
                    }
                });
            }
        });
    });
    function setData() {
        $('#edit-modal').modal('hide');
    }
    function searchResource(url){
        $.get(url,function(result){
            if(result.status){
                //获取当前激活的面板下的ul,并清空元素
                var ul = $('#resource-tab .tab-pane.active .resource-list');
                ul.empty();
                //遍历返回的数据
                $(result.data).each(function(index,element){
                    //添加员工待选列表
                    addResourceList(ul, element);
                });
                //绑定checkDiv点击事件
                bindCheckDiv();
            }else{
                toastr.error(result.msg);
            }
        });
    }

    //resourceList里的删除图标的点击方法
    function removeTag(node){
        //获取点击的人员的ID
        var id = $(node).parent().attr("data-id");
        //删除所在的li
        $(node).parent().remove();

        //移除组织架构面板里的待选列表的对应li
        var li1 = $("#resource-list1 li[data-id='"+id+"']");
        if(li1.length > 0) {
            li1.find("i").removeClass('fa-check-square');
        }
        //移除搜索面板里的待选列表对应li
        var li2 = $("#resource-list2 li[data-id='"+id+"']");
        if(li2.length > 0) {
            li2.find("i").removeClass('fa-check-square');
        }
    }

    //绑定checkbox的点击事件
    function bindCheckDiv(){
        //绑定激活面板下的checkdiv
        $("#resource-tab .tab-pane.active .resource-list .check-div").click(function(){
            var node = $(this).children('i');
            var id = $(this).parent().attr("data-id");
            var name = $(this).parent().attr("data-name");
            //判断当前的点击状态
            if (node.hasClass("fa-check-square")) {
                node.removeClass("fa-check-square");
                //获取对应的存数据的li 并且删除
                var currentLi = $('#resource-list li[data-id="'+id+'"]');
                if(currentLi.length > 0 ){
                    currentLi.remove();
                }
            } else {
                node.addClass("fa-check-square");
                addTag(id, name);
            }
        });
    }
    function addResourceList(ul,element){
        var li = $("<li></li>").attr("data-id",element.id).attr("data-name",element.name);
        var span = $("<span></span>").text(element.name);
        var span2 = $("<span class='span-name'></span>").text(element.department);
        var div = $("<div class='check-div'></div>");
        var i = $("<i class='fa fa-square-o'></i>");
        //根据currentLi是否为空判断可选列表是否已选
        var currentLi = $('#resource-list li[data-id="'+element.id+'"]');
        if(currentLi.length > 0){
            i.addClass("fa-check-square");
        }
        div.append(i);
        li.append(span);
        li.append(span2);
        li.append(div);
        ul.append(li);
    }
    function addTag(id,name){
        var ul = $("#resource-list");
        var li = $("<li class='ant-tag ant-tag-blue'></li>").attr("data-id", id);
        var span = $("<span></span>").text(name);
        var i = $("<i class='fa fa-times'></i>").click(function(){
            removeTag(this);
            //这是闭包吗 保留了node的引用
//                    node.click();
        });
        li.append(span);
        li.append(i);
        ul.append(li);
    }
</script>
<div class="modal-dialog" style="width: 600px;height:550px;">
    <div class="modal-content" id="resource-content">
        <div class="modal-header">
            <span class="title">员工列表</span>
            <i class="fa fa-times" data-dismiss="modal"
               style="display: block;position: absolute;top: 12px;right: 0;bottom: 0;width: 45px;font-size: 18px;cursor: pointer;"></i>
        </div>
        <div class="modal-body" style="padding: 0;">
            <div style="padding:10px">
                <div style="width: 570px; height: 440px;">
                    <ul id="resource-list" style="height:80px;background: #fff;border: dashed 1px #e0e0e0;margin-bottom: 8px;overflow: auto;">
                    </ul>
                    <div class="select-menu">
                        <a class="active" href="#organizationPanel" data-toggle="tab">架构</a>
                        <a href="#searchPanel" data-toggle="tab">搜索</a>
                    </div>
                    <div id="resource-tab" class="tab-content" style="border: solid 1px #e0e0e0;border-top: none;">
                        <div class="tab-pane fade in active" id="organizationPanel"
                             style="position: relative;height: 320px;">
                            <div class="resource-menu">
                                <div id="organization-tree" style="padding: 5px 0">
                                </div>
                            </div>
                            <ul class="resource-header" style="border-left: solid 1px #e0e0e0;position: absolute;top: 0;right: 0;left: 50%;background-color: rgb(247, 247, 247)">
                                <li style="padding: 0 30px 0 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;position: relative;line-height: 30px;font-size: 14px;">
                                <span>姓名</span>
                                <span style="position: absolute;top: 0;left: 37.5%;">部门</span>
                                <div class="checkAll check-div" style="position: absolute;top: 0;right: 5px;cursor: pointer;">
                                    <i class="fa fa-square-o">
                                    </i>
                                </div>
                                </li>
                            </ul>
                            <ul class="resource-list" id="resource-list1">
                            </ul>
                        </div>
                        <div class="tab-pane fade" id="searchPanel" style="position: relative;height: 320px;">
                            <div class="resource-menu">
                                <div style="padding: 5px 0">

                                </div>
                            </div>
                            <ul class="resource-header" style="border-left: solid 1px #e0e0e0;position: absolute;top: 0;right: 0;left: 50%;background-color: rgb(247, 247, 247)">
                                <li style="padding: 0 30px 0 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;position: relative;line-height: 30px;font-size: 14px;">
                                    <span>姓名</span>
                                    <span style="position: absolute;top: 0;left: 37.5%;">部门</span>
                                    <div class="checkAll check-div" style="position: absolute;top: 0;right: 5px;cursor: pointer;">
                                        <i class="fa fa-square-o">
                                        </i>
                                    </div>
                                </li>
                            </ul>
                            <ul class="resource-list" id="resource-list2">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="u-btn u-btn-lg" data-dismiss="modal" type="button">取 消</button>
            <button class="u-btn u-btn-primary u-btn-lg" style="margin-left: 8px" onclick="setData()">确 定</button>
        </div>
    </div>

</div>
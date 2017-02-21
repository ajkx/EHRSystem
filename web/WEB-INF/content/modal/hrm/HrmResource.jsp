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
    function setData() {
        $('#edit-modal').modal('hide');
    }
    function searchResource(url){
        $.get(url,function(result){
            if(result.status){
                var ul = $('#resource-list1');
                ul.empty();
                var resourceList = $('#resource-list li');
                $(result.data).each(function(index,element){
                    var li = $("<li></li>").attr("data-id",element.id).attr("data-name",element.name);
                    var span = $("<span></span>").text(element.name);
                    var span2 = $("<span class='span-name'></span>").text(element.department);
                    var div = $("<div class='check-div'></div>");
                    var i = $("<i class='fa fa-square-o'></i>");
                    //根据上面部分的resourceList判断可选列表是否已选
                    resourceList.each(function(index,currentLi){
                        if($(currentLi).attr("data-id") == element.id){
                            i.addClass("fa-check-square");
                        }
                    });
                    div.append(i);
                    li.append(span);
                    li.append(span2);
                    li.append(div);
                    ul.append(li);
                });
                $('.check-div').click(function () {
                    var node = $(this).children('i');
                    var id = $(this).parent().attr("data-id");
                    var name = $(this).parent().attr("data-name");
                    var resourceList = $('#resource-list li');
                    if (node.hasClass("fa-check-square")) {
                        node.removeClass("fa-check-square");
                        resourceList.each(function(index,currentLi){
                            if($(currentLi).attr("data-id") == id) {
                                $(currentLi).remove();
                            }
                        });
                    } else {
                        node.addClass("fa-check-square");
                        var ul = $("#resource-list");
                        var li = $("<li class='ant-tag ant-tag-blue'></li>").attr("data-id", id);
                        var span = $("<span></span>").text(name);
                        var i = $("<i class='fa fa-times'></i>").click(function(){
                            //这是闭包吗 保留了node的引用
                            node.click();
                        });
                        li.append(span);
                        li.append(i);
                        ul.append(li);
                    }
                });
            }else{
                toastr.error(result.msg);
            }
        });
    }
    $(function () {
        var currentList = $('#resourceIds').val();
        if(currentList != ""){
            $.get("/resource/list/customer",{resourceStr:currentList},function(result){

            });
        }
        $.get("/organization/tree",{type:"resource"},function(data){
            $('#organization-tree').treeview({
                //开通超链接效果
                enableLinks:false,
                backColor: '#fff',
//            backColor: '#F7F6F2',
                showBorder: false,
                levels: 1,
                showIcon:true,
//            onhoverColor:'#F2F1ED',
//            selectedBackColor:'#E4E3DF',
//            selectedColor:'#414140',
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
        $('a[data-toggle="tab"]').on('show.bs.tab',function(e){
            $("a.active").removeClass('active');
            $(this).addClass('active');
        });
    })
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
                        <li class="ant-tag ant-tag-blue">
                            <span>qqa</span>
                            <i class="fa fa-times"></i>
                        </li>
                    </ul>
                    <div class="select-menu">
                        <a class="active" href="#organizationPanel" data-toggle="tab">架构</a>
                        <a href="#searchPanel" data-toggle="tab">搜索</a>
                    </div>
                    <div class="tab-content" style="border: solid 1px #e0e0e0;border-top: none;">
                        <div class="tab-pane fade in active" id="organizationPanel"
                             style="position: relative;height: 320px;">
                            <div class="resource-menu">
                                <div id="organization-tree" style="padding: 5px 0">

                                </div>
                            </div>
                            <ul class="resource-list" id="resource-list1">
                            </ul>
                        </div>
                        <div class="tab-pane fade" id="searchPanel" style="position: relative;height: 320px;">
                            <div class="resource-menu">
                                <div style="padding: 5px 0">

                                </div>
                            </div>
                            <ul class="resource-list" id="resource-list2">
                                <li>
                                    <span>aaa</span>
                                    <div class="check-div">
                                        <i class="fa fa-square-o"></i>
                                    </div>
                                </li>
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
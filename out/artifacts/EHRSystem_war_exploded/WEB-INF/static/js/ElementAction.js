/**
 * Created by ajkx_du on 2016/12/27.
 */

//显示职务类别的方法
function showJobGroups(){
    var ul = $('#jobgroup-list ul');
    var span = $('#jobgroup-name').find('span');
    if(ul.find('li').length > 0){
        $('#jobgroup-list').css('display','block');
    }else{
        $.get("/jobgroup/list",function(result){
            if(result.length > 0){
                $(result).each(function(index,element){
                    $('<li><?li>').text(element.name).appendTo(ul)
                        .click(function(event){
                            //选择框赋值
                            span.text(element.name);
                            //input标签赋值
                            $('#groupid').val(element.id);
                            //显示remove图标
                            $('.fa-times').css('display','inline');
                            //隐藏显示列表
                            $('#jobgroup-list').css('display','none');
                        });
                });
                $('#jobgroup-list').css('display','block');
            }else{
                console.log("无数据");
            }
        });
    }

}

function clearJobGroups(event){
    $('#jobgroup-name').find('span').text("");
    $('#groupid').val("");
    $('.fa-times').css('display','none');
    event.stopPropagation();
}



function setScheduleType(value,node){
    $('#sheduletype').val(value);
    $(node).siblings().removeClass("scheduletype");
    var nodes = $(node).parent().children();
    switch(value){
        case 1:
            nodes.last().css("border-left","1px solid #ccc");
            $('#twoSchedule').css("display","none");
            $('#threeSchedule').css("display","none");
            break;
        case 2:
            nodes.first().css("border-right","1px solid #2CB7F5");
            nodes.last().css("border-left","1px solid #2CB7F5");
            $('#twoSchedule').css("display","block");
            $('#threeSchedule').css("display","none");
            break;
        case 3:
            nodes.first().css("border-right","1px solid #ccc");
            $('#twoSchedule').css("display","block");
            $('#threeSchedule').css("display","block");
            break;
    }
    $(node).addClass("scheduletype");
}
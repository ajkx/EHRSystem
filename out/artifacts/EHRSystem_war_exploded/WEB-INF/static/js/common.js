/**
 * Created by ajkx on 2016/11/25.
 */
$(function () {

    //初始化a标记的pjax事件
    $(document).pjax('a[data-pjax]','#main-content');
    //每次载入页面完成时进行一次pjax请求
    $.pjax({url:'', container: '#main-content'});

    //绑定模态框的隐藏事件
    $('edit-modal').on("hidden.bs.modal", function (e) {
        //重新载入当前页面
        $.pjax({url: location.href, container: '#main-content'});
        //移除模态框元素
        $('#edit-modal').find("#edit-modal-content").children().detach();
    });
});

toastr.options = {
    closeButton: false,
    debug: false,
    progressBar: false,
    positionClass: "toast-top-right",
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "3000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

//弹出修改的模态框
function showEditModal(url){
    $('#edit-modal').load(url,function(resp){
        $('#edit-modal').modal("show");
    });
}

function showDelModal(url){
    $('#confirmModal').modal('show');
    $('#del-btn').attr('data-del-url',url);
}

function deleteData(){
    console.log("data");
    $.get($('#del-btn').attr('data-del-url'),function(data){
        $('#confirmModal').modal("hide");
        if(data.status) {
            $.pjax({url:location.href,container:'#main-content'});
            console.log("删除成功");
        }else{
            console.log("删除失败，原因未知");
        }
    });
}

function submitForm(){
    var form = $('#modal-form');
    $.ajax({
       url:form.attr('action'),
       type:"GET",
       dataType:"json",
       data:form.serialize(),
       success:function(result) {
           if(result.status) {
               $('#edit-modal').modal('hide');
               toastr.success(result.msg);
               $.pjax({url:location.href,container:'#main-content'});
           }else{
               toastr.error(result.msg);
           }
       },
       error:function(xhr,status) {
           alert("数据传输错误" + status + ",请联系系统管理员");
       }
    });

    return false;
}

function ajaxSubmit(node){
    var form = $(node);
    console.log("ajax");
    $.ajax({
        url:form.attr('action'),
        type:"POST",
        dataType:"json",
        data:form.serialize(),
        success:function (result) {
            // window.location.href = "/login";
            console.log(result.status);
        }
    })
}
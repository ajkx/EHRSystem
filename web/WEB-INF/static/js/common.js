/**
 * Created by ajkx on 2016/11/25.
 */
$(function () {

    //初始化a标记的pjax事件
    $(document).pjax('a[data-pjax]','#main-content');
    //每次载入页面完成时进行一次pjax请求
    $.pjax({url:'', container: '#main-content'});
});
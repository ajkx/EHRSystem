<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/9
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/tree.js"></script>--%>

<script>
$('#treeview').ready(function(){
    $(document).pjax('a','#detail-content');
    $.get("/organization/tree",function(data1){
        $('#treeview').treeview({
            //开通超链接效果
            enableLinks:true,
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
            data:data1
            //右边显示提示信息
        });
    });
})
</script>

<div id="treeview"></div>
<div id="detail-content">
</div>

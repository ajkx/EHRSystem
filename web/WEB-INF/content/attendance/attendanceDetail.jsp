<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/17
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>



<script>

    $('table[grid-manager="main"]').GM({
        gridManagerName: 'test',
        useDefaultStyle: false,
        supportAjaxPage: true,
        supportDrag: false,
        supportAutoOrder: false,
        supportCheckbox:false,
        disableCache: true,
        supportAdjust: false,
        supportSorting: true,
        isCombSorting: true,
        ajax_url: '/detail/list',
        ajax_type: 'GET',
        pageSize: 10,
        height:"auto",
        columnData: [
            {key: 'name',
                text: '姓名',
                width: '120px',
                remind: '',
            },
            {key: 'department',
                text: '部门',
                width: '140px',
                remind: '',
            },
            {key: 'workCode',
                text: '工号',
                width: '120px',
                remind: '',
            },
            {key: 'date',
                text: '日期',
                width: '120px',
                remind: '',
            },
            {key: 'schedule',
                text: '班次',
                width: '130px',
                remind: '',
            },
            {key: 'attendanceType',
                text: '考勤结果',
                width: '100px',
                remind: '',
            },
            {key: 'setting_first_up',
                text: '上班1',
                width: '80px',
                remind: '',
            },
            {key: 'actual_first_up',
                text: '上班1',
                width: '80px',
                remind: '',
            },
            {key: 'setting_first_down',
                text: '下班1',
                width: '80px',
                remind: '',
            },
            {key: 'actual_first_down',
                text: '下班1',
                remind: '',
                width: '80px',
            },
            {key: 'setting_second_up',
                text: '上班2',
                remind: '',
                width: '80px',
            },
            {key: 'actual_second_up',
                text: '上班2',
                remind: '',
                width: '80px',
            },
            {key: 'setting_second_down',
                text: '下班2',
                remind: '',
                width: '80px',
            },
            {key: 'actual_second_down',
                text: '下班2',
                remind: '',
                width: '80px',
            },
            {key: 'setting_third_up',
                text: '上班3',
                remind: '',
                width: '80px',
            },
            {key: 'actual_third_up',
                text: '上班3',
                remind: '',
                width: '80px',
            },
            {key: 'setting_third_down',
                text: '下班3',
                remind: '',
                width: '80px',
            },
            {key: 'actual_third_down',
                text: '下班3',
                remind: '',
                width: '80px',
            },
            {key: 'lateTime',
                text: '迟到时间',
                remind: '',
                width: '120px',
            },
            {key: 'earlyTime',
                text: '早退时间',
                remind: '',
                width: '120px',
            },
            {key: 'absenteeismTime',
                text: '旷工时间',
                remind: '',
                width: '120px',
            },
            {key: 'ot_normal',
                text: '平时加班',
                remind: '',
                width: '120px',
            },
            {key: 'ot_weekend',
                text: '周末加班',
                remind: '',
                width: '120px',
            },
            {key: 'ot_festival',
                text: '节日加班',
                remind: '',
                width: '120px',
            },
            {key: 'setting_time',
                text: '规定出勤时间',
                remind: '',
                width: '120px',
            },
            {key: 'actual_time',
                text: '实际出勤时间',
                remind: '',
                width: '120px',
            }
        ],
        ajax_success:function () {
            $('tr').css("height", "39px");
            $('td').css("padding", "10px 8px");
        }
    });

    $(function() {
        $(".table-div").addClass("ant-table");
        $("table").addClass("table-bordered");
        $("table").css("border-collapse","collapse");


        var tr = $("<tr></tr>");
        for(var i = 0; i < 26; i++) {
//            if(i == 6){
//                tr.append($("<th></th>").text("上班1").attr("colspan","2"));
//                tr.append($("<th></th>").text("").
//                i++;
//            }
//            else if(i == 8){
//                tr.append($("<th></th>").text("上班2").attr("colspan","2").css("width","100px"));
//                i++;
//            }
//            else if(i == 10){
//                tr.append($("<th></th>").text("上班3").attr("colspan","2").css("width","100px"));
//                i++;
//            }else{
//                tr.append($("<th></th>"));
//            }
            if(i > 5 && i < 17){
                tr.append($("<th></th>").text("打卡时间"));
                tr.append($("<th></th>").text("打卡结果"));
                i++;
            }else{
                tr.append($("<th></th>"));
            }
        }
        $('thead').append(tr);
        //让表头th下边框为1px并且居中
        $('th').css("border-bottom-width","1px").css("text-align","center").css("padding","10px 8px");
        $('th div').css("text-align","center");
        $('table').css("text-align","center");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
        $('tr').css("height", "39px");

        var trs = $("thead tr[]")
    });

</script>
<div class="topic-toolbar">
</div>
<div>
    <table grid-manager="main"></table>
</div>


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
        supportCheckbox: false,
        disableCache: true,
        supportAdjust: false,
        supportSorting: true,
        isCombSorting: true,
        ajax_url: '/detail/list/detail',
        ajax_type: 'GET',
        pageSize: 10,
        height: "auto",
        columnData: [
            {
                key: 'name',
                text: '姓名',
                width: '120px',
                remind: '',
            },
            {
                key: 'department',
                text: '部门',
                width: '140px',
                remind: '',
            },
            {
                key: 'workCode',
                text: '工号',
                width: '120px',
                remind: '',
            },
            {
                key: 'date',
                text: '日期',
                width: '120px',
                remind: '',
            },
            {
                key: 'schedule',
                text: '班次',
                width: '130px',
                remind: '',
            },
            {
                key: 'attendanceType',
                text: '考勤结果',
                width: '100px',
                remind: '',
            },
            {
                key: 'setting_first_up',
                text: '上班1',
                width: '160px',
                remind: '',
            },
            {
                key: 'actual_first_up',
                text: '上班1',
                width: '80px',
                remind: '',
            },
            {
                key: 'setting_first_down',
                text: '下班1',
                width: '160px',
                remind: '',
            },
            {
                key: 'actual_first_down',
                text: '下班1',
                remind: '',
                width: '80px',
            },
            {
                key: 'setting_second_up',
                text: '上班2',
                remind: '',
                width: '160px',
            },
            {
                key: 'actual_second_up',
                text: '上班2',
                remind: '',
                width: '80px',
            },
            {
                key: 'setting_second_down',
                text: '下班2',
                remind: '',
                width: '160px',
            },
            {
                key: 'actual_second_down',
                text: '下班2',
                remind: '',
                width: '80px',
            },
            {
                key: 'setting_third_up',
                text: '上班3',
                remind: '',
                width: '160px',
            },
            {
                key: 'actual_third_up',
                text: '上班3',
                remind: '',
                width: '80px',
            },
            {
                key: 'setting_third_down',
                text: '下班3',
                remind: '',
                width: '160px',
            },
            {
                key: 'actual_third_down',
                text: '下班3',
                remind: '',
                width: '80px',
            },
            {
                key: 'lateTime',
                text: '迟到时间',
                remind: '',
                width: '120px',
                template:function(lateTime,rowObject){
                    return Math.round(lateTime/60000);
                }
            },
            {
                key: 'earlyTime',
                text: '早退时间',
                remind: '',
                width: '120px',
                template:function(earlyTime,rowObject){
                    return Math.round(earlyTime/60000);
                }
            },
            {
                key: 'absenteeismTime',
                text: '旷工时间',
                remind: '',
                width: '120px',
                template:function(absenteeismTime,rowObject){
                    return Math.round(absenteeismTime/60000);
                }
            },
            {
                key: 'ot_normal',
                text: '平时加班',
                remind: '',
                width: '120px',
                template:function(ot_normal,rowObject){
                    return Math.round(ot_normal/60000);
                }
            },
            {
                key: 'ot_weekend',
                text: '周末加班',
                remind: '',
                width: '120px',
                template:function(ot_weekend,rowObject){
                    return Math.round(ot_weekend/60000);
                }
            },
            {
                key: 'ot_festival',
                text: '节日加班',
                remind: '',
                width: '120px',
                template:function(ot_festival,rowObject){
                    return Math.round(ot_festival/60000);
                }
            },
            {
                key: 'setting_time',
                text: '规定出勤时间',
                remind: '',
                width: '120px',
                template:function(setting_time,rowObject){
                    return Math.round(setting_time/60000);
                }
            },
            {
                key: 'actual_time',
                text: '实际出勤时间',
                remind: '',
                width: '120px',
                template:function(actual_time,rowObject){
                    return Math.round(actual_time/60000);
                }
            }
        ],
        ajax_success: function () {
            $('tr').css("height", "39px");
            $('td').css("padding", "10px 8px");
        }
    });

    $(function () {
        $(".table-div").addClass("ant-table");
        $("table").addClass("table-bordered");
        $("table").css("border-collapse", "collapse");


        var tr = $("<tr></tr>");
        for (var i = 0; i < 12; i++) {
            tr.append($("<th></th>").text("打卡时间"));
            tr.append($("<th></th>").text("打卡结果"));
            i++;
        }
        $('thead').append(tr);
        //让表头th下边框为1px并且居中
        $('th').css("border-bottom-width", "1px").css("text-align", "center").css("padding", "10px 8px");
        $('th div').css("text-align", "center");
        $('table').css("text-align", "center");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
        $('tr').css("height", "39px");

        var trs = $("thead tr:first");
        var tds = trs.children();
        for (var i = 0; i < tds.length; i++) {
            if (i == 6 || i == 8 || i == 10 || i == 12 || i == 14 || i == 16) {
                $(tds[i]).attr("colspan", "2");
                $(tds[i + 1]).remove();
                i++;
            }
            else {
                $(tds[i]).attr("rowspan", "2");
            }
        }

        //初始化日期选择器
        var datePicker = $('.datetimepicker');
        datePicker.datetimepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            startView: 2,
            minView: 2,
            maxView: 4,
            language: 'zh-CN',
            todayBtn: true,
            clearBtn:false,
            timezone: "中国标准时间",
        }).on('changeDate',function(e){
            searchData();
        });
    });

    function resourceCallBack(value,text){
        if(text.length > 10){
            text = text.substring(0, 9) + "....";
        }
        $('#resourceBtn').text(text);
        $('#resourceStr').val(value);
        searchData();

    }

    //选择人员的回调清空方法
    function resourceClearCallBack(){
        $('#resourceBtn').text('全公司');
        $('#resourceStr').val('');
        searchData();
    }

    //搜索
    function searchData(){
        var query = {
            beginDate: document.querySelector('[name="beginDate"]').value,
            endDate: document.querySelector('[name="endDate"]').value,
            resources: document.querySelector('[name="resources"]').value,
        }
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid', function () {
            console.log('搜索成功...');
        });
    }
</script>
<div class="topic-toolbar">
</div>
<div>
    <div style="margin-bottom: 18px">
        <div>
            <div class="app-statistics-detail-index-date-member">
                <div class="dtui-date-member">
                    <span>时间：</span>
                     <span class="ant-calendar-picker" style="width: 110px; margin-top: 0px;">
                            <span>
                            <input readonly="" value="${beginDate}" name="beginDate" placeholder="请选择开始日期"
                                   class="ant-input datetimepicker">
                            </span>
                    </span>
                    <span style="margin:0 8px">至</span>
                     <span class="ant-calendar-picker" style="width: 110px; margin-top: 0px;">
                            <span>
                            <input readonly="" value="${endDate}" name="endDate" placeholder="请选择结束日期"
                                   class="ant-input datetimepicker">
                            </span>
                    </span>
                    <input type="hidden" value="" name="resources" id="resourceStr">
                    <span style="margin-left: 50px;">部门/人员：</span>
                    <button type="button" id="resourceBtn" class="ant-btn" onclick="chooseResource('/organization/modal/list','attendanceDetail');">
                        <span>全公司</span>
                    </button>
                    <div class="detail-line"></div>
                </div>
            </div>
            <div>
                <button type="button" class="ant-btn ant-btn-primary">
                    <span>导出每日统计表</span>
                </button>
            </div>
        </div>
    </div>
    <table grid-manager="main"></table>
</div>
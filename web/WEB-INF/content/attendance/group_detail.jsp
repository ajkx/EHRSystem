<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/2/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>


<div style="margin-top: 15px; width: 1150px; background: rgb(255, 255, 255);">
    <div>
        <div class="" style="margin: 20px 0px 30px; opacity: 1; visibility: visible; transform: translateX(0px);">
            <form class="ant-form ant-form-horizontal">
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤组名称</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control "><span class="ant-input-wrapper"><input type="text" placeholder="请输入考勤组名称" class="ant-input ant-input-lg" name="name" value=""></span></div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤人员</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control "><button type="button" class="ant-btn ant-btn-ghost ant-btn-lg"><span>请选择</span></button></div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤类型</label></div>
                    <div class="ant-col-18">
                        <div class="ant-form-item-control ">
                            <div class="ant-radio-group ant-radio-group-large"><label style="font-weight: 500;" class="ant-radio-wrapper ant-radio-wrapper-checked"><span class="ant-radio ant-radio-checked ant-radio ant-radio-checked ant-radio-checked-1"><span class="ant-radio-inner"></span><input type="radio" class="ant-radio-input" value="on"></span><span>固定班制 (每天考勤时间一样)</span></label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper"><span class="ant-radio"><span class="ant-radio-inner"></span><input type="radio" class="ant-radio-input" value="on"></span><span>排班制 (自定义设置考勤时间)</span></label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper"><span class="ant-radio"><span class="ant-radio-inner"></span><input type="radio" class="ant-radio-input" value="on"></span><span>自由工时（不设置班次，随时打卡）</span></label>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">工作日设置</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <div>
                                <div>
                                    <div style="margin-bottom: 5px;">
                                        快捷设置班次：班次<a style="margin-left: 10px;">更改班次</a></div>
                                    <div class=" clearfix">
                                        <div class="">
                                            <div class="ant-spin"><span class="ant-spin-dot"></span>
                                                <div class="ant-spin-text">加载中...</div>
                                            </div>
                                            <div class="ant-spin-container">
                                                <div class="ant-table ant-table-middle ant-table-scroll-position-left">
                                                    <div class="ant-table-content">
                                                        <div class=""><span><div class="ant-table-body"><table class=""><colgroup><col><col style="width: 70px; min-width: 70px;"><col style="width: 300px; min-width: 300px;"><col style="width: 120px; min-width: 120px;"></colgroup><thead class="ant-table-thead"><tr><th class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span>
                                                            <input
                                                                    type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                </label>
                                                                </span>
                                                                </th>
                                                                <th class=""><span>工作日</span></th>
                                                                <th class=""><span>班次时间段</span></th>
                                                                <th class=""><span>操作</span></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody class="ant-table-tbody">
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周一

                                                                        </td>
                                                                        <td class="">
                                                                           休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="1">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周二
                                                                            
                                                                        </td>
                                                                        <td class="">
                                                                            休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="2">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周三
                                                                            
                                                                        </td>
                                                                        <td class="">
                                                                            休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="3">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周四
                                                                            
                                                                        </td>
                                                                        <td class="">
                                                                            休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="4">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周五
                                                                            
                                                                        </td>
                                                                        <td class="">
                                                                            休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="5">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周六
                                                                            
                                                                        </td>
                                                                        <td class="">
                                                                            休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="6">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>

                                                                            周日
                                                                            
                                                                        </td>
                                                                        <td class="">
                                                                            休息
                                                                            
                                                                        </td>
                                                                        <td class=""><a data-index="7">更改班次</a></td>
                                                                    </tr>
                                                                </tbody>
                                                                </table>
                                                        </div>
                                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="margin-top: 5px;"><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><span class="ant-checkbox-inner"></span><input type="checkbox" class="ant-checkbox-input" value="on"></span></label>
                                        法定节假日自动排休
                                        
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">特殊日期</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <div>
                                <div><button type="button" class="ant-btn ant-btn-ghost"><span>添 加</span></button><span style="margin-left: 12px; font-size: 12px; color: rgb(153, 153, 153);">必须打卡的日期</span></div>
                                <div style="width: 550px; padding-top: 10px; padding-bottom: 10px;">
                                    <div></div>
                                </div>
                                <div><button type="button" class="ant-btn ant-btn-ghost"><span>添 加</span></button><span style="margin-left: 12px; font-size: 12px; color: rgb(153, 153, 153);">不用打卡的日期</span></div>
                                <div style="width: 550px; padding-top: 10px; padding-bottom: 0px;">
                                    <div></div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item"><div class="ant-col-2 ant-form-item-label"><label class="">考勤班次</label></div><div class="ant-col-14"><div class="ant-form-item-control "><button type="button" class="ant-btn ant-btn-ghost ant-btn-lg"><span>选择班次</span></button><div style="margin-top: 15px; margin-bottom: -10px;"></div></div></div></div>
                <div class="ant-row" style="margin: 24px 0px;">
                    <div class="ant-col-16 ant-col-offset-8"><button type="button" class="ant-btn ant-btn-primary"><span>保存设置</span></button></div>
                </div>
            </form>
        </div>
    </div>
</div>

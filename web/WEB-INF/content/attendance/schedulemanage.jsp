<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/1/6
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>
<div id="container">
    <div>排班管理</div>
    <br/>
    <div>
        <input class="form-control date-month-picker" type="text" style="width: 100px;display: inline-block"/>
        <button class="btn" style="margin-left: 20px">筛选排班人员</button>
        <button class="btn" style="margin-left: 20px">查询排班情况</button>
    </div>
    <div style="margin-top: 10px">
        <h1 style="float: left">2017.01</h1>
        <h5 style="float: left">班次说明:</h5>
        <div style="float: left; margin-left: 10px; line-height: 20px; padding-top: 16px; padding-bottom: 10px;">
            <div style="background-color: #0F88EB"><span class="ant-tag-text">A: 09:00-18:00 </span>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px;">
        <table class="">
            <colgroup>
                <col style="width: 65px; min-width: 65px;">
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead class="ant-table-thead">
            <tr>
                <th class=""><span><!-- react-text: 234 -->姓名<!-- /react-text --></span></th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 30px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 240 -->元
                    <!-- /react-text --><br>
                    <!-- react-text: 242 -->旦<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 30px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 249 -->2
                    <!-- /react-text --><br><!-- react-text: 251 -->一<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 30px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 258 -->3
                    <!-- /react-text --><br><!-- react-text: 260 -->二<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 30px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 267 -->4
                    <!-- /react-text --><br><!-- react-text: 269 -->三<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 30px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 276 -->5
                    <!-- /react-text --><br><!-- react-text: 278 -->四<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 285 -->6
                    <!-- /react-text --><br><!-- react-text: 287 -->五<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 294 -->7
                    <!-- /react-text --><br>
                    <!-- react-text: 296 -->六<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 303 -->8
                    <!-- /react-text --><br>
                    <!-- react-text: 305 -->日<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 312 -->9
                    <!-- /react-text --><br><!-- react-text: 314 -->一<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 321 -->10
                    <!-- /react-text --><br><!-- react-text: 323 -->二<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 330 -->11
                    <!-- /react-text --><br><!-- react-text: 332 -->三<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 339 -->12
                    <!-- /react-text --><br><!-- react-text: 341 -->四<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 348 -->13
                    <!-- /react-text --><br><!-- react-text: 350 -->五<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 357 -->14
                    <!-- /react-text --><br><!-- react-text: 359 -->六<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 366 -->15
                    <!-- /react-text --><br><!-- react-text: 368 -->日<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 375 -->16
                    <!-- /react-text --><br><!-- react-text: 377 -->一<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 384 -->17
                    <!-- /react-text --><br><!-- react-text: 386 -->二<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 393 -->18
                    <!-- /react-text --><br><!-- react-text: 395 -->三<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 402 -->19
                    <!-- /react-text --><br><!-- react-text: 404 -->四<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 411 -->20
                    <!-- /react-text --><br><!-- react-text: 413 -->五<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 420 -->21
                    <!-- /react-text --><br><!-- react-text: 422 -->六<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 429 -->22
                    <!-- /react-text --><br><!-- react-text: 431 -->日<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 438 -->23
                    <!-- /react-text --><br><!-- react-text: 440 -->一<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 447 -->24
                    <!-- /react-text --><br><!-- react-text: 449 -->二<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 456 -->25
                    <!-- /react-text --><br><!-- react-text: 458 -->三<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 465 -->26
                    <!-- /react-text --><br><!-- react-text: 467 -->四<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 474 -->除
                    <!-- /react-text --><br>
                    <!-- react-text: 476 -->夕<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 483 -->春
                    <!-- /react-text --><br>
                    <!-- react-text: 485 -->节<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 492 -->29
                    <!-- /react-text --><br><!-- react-text: 494 -->日<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 501 -->30
                    <!-- /react-text --><br><!-- react-text: 503 -->一<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
                <th class=""><span><div style="cursor: pointer;"><button
                        style="border: 0px; width: 29px; outline-width: 2px;  height: 50px; background-color: rgb(255, 255, 255);"><p
                        style="color: rgb(104, 104, 104); text-align: center;"><!-- react-text: 510 -->31
                    <!-- /react-text --><br><!-- react-text: 512 -->二<!-- /react-text --></p><div
                        style=";"></div></button></div></span>
                </th>
            </tr>
            </thead>
            <tbody class="ant-table-tbody">
            <tr class="ant-table-row  ant-table-row-level-0">
                <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>
                    <!-- react-empty: 518 -->
                    <div style="margin: -4px 0px -10px;">
                        <button style="border: 0px; width: 64px; outline-width: 2px; color: rgb(104, 104, 104); margin: 0px -6px 0px -8px; height: 36px; background-color: rgb(255, 255, 255);">
                            <p style="margin-left: 8px; float: left; text-align: left;">asdas</p>
                            <div style=""></div>
                        </button>
                    </div>
                </td>
                <td class="">
                    <button style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">
                        .
                    </button>
                </td>
                <td class="">
                    <button style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">
                        .
                    </button>
                </td>
                <td class="">
                    <button style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">
                        .
                    </button>
                </td>
                <td class="">
                    <button style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">
                        .
                    </button>
                </td>
                <td class="">
                    <button style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">
                        .
                    </button>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
                <td class=""><span><button
                        style="border: 0px; width: 30px; outline-width: 2px; color: rgb(255, 255, 255); line-height: 35px; text-align: center; margin: -8px -9px -8px -7px; height: 35px; background-color: rgb(255, 255, 255);">.</button></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div style="margin-top: 30px">
        <table class="">
            <colgroup>
                <col style="width: 67px; min-width: 67px;">
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead class="ant-table-thead">
            <tr>
                <th class=""><span><!-- react-text: 668 -->统计<!-- /react-text --></span></th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">1</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">2</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">3</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">4</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">5</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">6</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">7</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">8</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">9</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">10</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">11</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">12</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">13</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">14</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">15</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">16</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">17</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">18</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">19</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">20</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">21</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">22</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">23</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">24</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">25</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">26</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">27</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">28</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">29</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">30</div></span>
                </th>
                <th class=""><span><div
                        style="line-height: 34px; text-align: center; width: 18px; margin: -8px -1px; height: 34px; color: rgb(104, 104, 104);">31</div></span>
                </th>
            </tr>
            </thead>
            <tbody class="ant-table-tbody">
            <tr class="ant-table-row  ant-table-row-level-0">
                <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>
                    <!-- react-empty: 766 -->
                    <div style="margin: 0px -3px;"><span
                            style="padding: 1px 3px; margin-right: 2px; color: rgb(255, 255, 255); background-color: rgb(45, 183, 245);">A</span>
                        <!-- react-text: 769 -->班人数<!-- /react-text --></div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
            </tr>
            <tr class="ant-table-row  ant-table-row-level-0">
                <td class=""><span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;"></span>
                    <!-- react-empty: 835 -->
                    <div style="margin: 0px -3px;"><span
                            style="padding: 1px 3px; margin-right: 2px; color: rgb(255, 255, 255); background-color: rgb(184, 184, 184);">休</span>
                        <!-- react-text: 838 -->班人数<!-- /react-text --></div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
                <td class="">
                    <div style="line-height: 34px; text-align: center; margin: -8px -7px; height: 34px; color: rgb(104, 104, 104);">
                        0
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script>
    var month_picker = $('.date-month-picker');
    month_picker.datetimepicker({
        format: 'yyyy-mm',
        autoclose: true,
        startView: 3,
        minView: 3,
        maxView: 3,
        language: 'zh-CN',
        todayBtn: true,
        timezone: "中国标准时间",
    }).on("changeDate",function(){
        console.log($(this).val());
    });

</script>

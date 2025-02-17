<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>填报日历 - 爱尔医院</title>
    <link type='text/css' rel='stylesheet' href='${base}/static/js/lib/fullcalendar/lib/fullcalendar.css'/>
    <script type="text/javascript" src="${base}/static/js/lib/fullcalendar/lib/fullcalendar.js"></script>
    <script src='${base}/static/js/lib/fullcalendar/lib/locales/zh-cn.js'></script>
    <script type="text/javascript">


    </script>

    <style type="text/css">
        .fc .fc-daygrid-day-number {

            font-size: 16px;
            font-weight: 600;
        }
        .fc th  {
            line-height: 30px;
            font-size: 16px;
            font-weight: normal;
            /*background-color: #5AC1F1;*/
        }
        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }
        .fc-scrollgrid-sync-table>tbody>tr:last-child{
            display: none;
        }
        /*.fc-daygrid-day-frame {*/
        /*    background-color:#FFF1DF ;*/
        /*}*/
        /*mainbody{position: absolute;top:0px;left:0;right:0;bottom:0px;}*/
        .mainfooter {
            padding: 0px 10px 0;
            text-align: right;
        }

        .cont-grid {
            overflow: hidden;
            zoom: 1;
            border-bottom: 5px solid #E8EBF2;
            bottom: 0px
        }

        .cont-l {
            width: 300px;
        }

        .cont-r .detailsGridWrap {
            margin-left: 300px;
        }

        .navGridWrap {
            border-right: 5px solid #E8EBF2;
        }

        .detailsGridWrap {
        }

        .h2-title {
            line-height: 40px;
            color: #00A0E9;
            font-size: 20px;
        }

        .soform input.so-rangeDate {
            width: 180px;
        }

        .navGridWrap .datagrid-row .datagrid-cell {
            font-size: 14px !important;
        }

        .fc .fc-button-primary {
            color: #fff;
            color: var(--fc-button-text-color, #fff);
            background-color: #00A0E9;
            background-color: var(--fc-button-bg-color, #00A0E9);
            border-color: #00A0E9;
            border-color: var(--fc-button-border-color, #00A0E9);
        }

        .fc .fc-button-primary:disabled {
            color: #fff;
            color: var(--fc-button-text-color, #fff);
            background-color: #00A0E9;
            background-color: var(--fc-button-bg-color, #00A0E9);
            border-color: #00A0E9;
            border-color: var(--fc-button-border-color, #00A0E9);
        }

        .fc .fc-daygrid-day.fc-day-today {
            background-color: rgba(255, 50, 40, 0.15);
            background-color: var(--fc-today-bg-color, rgba(255, 50, 40, 0.15));
        }

        .fc-toolbar-title {
            color: #00A0E9;
        }

        .legend-wrap {
            height: 600px;
        }

        .legend-text {
            left: 140px;
            top: 5px;
            position: relative;
            font-size: 16px;
        }

        .legend {
            width: 120px;
            height: 40px;
            /*background-color: red;*/
            margin: 20px;
            top: 400px;
            position: relative;
        }
    </style>

</head>

<body>
<div class="searchHead bob-line">
    <h1 class="h2-title"><em class="icon-user-circle" style="padding: 0 5px 0 10px"></em><span
                id="title">${userName}(${currentTitle})</span><span id="hospName"></span></h1>
    [#--    <form id="sbox" class="soform form-enter">--]
    [#--        <input type="text" class="hide" name="projectId" value="${project.id}">--]
    [#--        <label class="lab-inline">房间描述：</label><input type="text" class="txt inline" name="roomDesc" value="">--]

    [#--        <button id="so-nav" type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#navGridBox',scope:'#sbox'}">查 询</button>--]
    [#--        <button id="so-nav" type="button" class="btn btn-small btn-primary so-all" onclick='$grid.reload("#navGridBox",{projectId:"${project.id}",})'>全部房间</button>--]
    [#--        <!-- <button type="reset" class="btn btn-small">清 除</button> -->--]
    [#--    </form>--]
</div>
<div class="cont-grid cont-fly">

    <div class="cont-r">

        <div class="detailsGridWrap" style="padding: 20px">
            <h2 class="h2-title-a"><span id="roomText"></span></h2>
            <div class="row">
                <div class="calendar-wrap p8">
                    <div id="calendar"></div>
                </div>
                <div class="legend-wrap p4">
                    <div class="hidden btn btn-large" style="margin-top: 5rem" id="ahisHistory">AHIS系统数据刷新</div>
                    <div class="legend" style="background-color: #E2F9FF">
                        <div class="legend-text">已填写</div>
                    </div>
                    <div class="legend" style="background-color: #FFF1DF">
                        <div class="legend-text">未填写</div>
                    </div>
                    <div class="legend" style="background-color: #FFE4DD">
                        <div class="legend-text">不允许填写</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="cont-l">
        <div class="navGridWrap">
            <div id="navGridBox"></div>
        </div>
    </div>

</div>


</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    require(["lodash", "easygridEdit","moment", "pub"], function (_, $e,moment) {
        var calendarData;
        var loadLast60days=function (userCode,hospId){
            $ajax.post({
                url:"${base}/ui/amcs/jtr/doctorStatistics/last60Days",
                data: {userCode: userCode,hospId: hospId},
                calltip: false,
                sync:true,
                callback : function(rst){

                    calendarData=rst

                },

            })
        }

        var yesterday=moment().day(moment().day() - 1).startOf('day').valueOf();
        var calendarEl = document.getElementById('calendar');
        var loadCalendar=function (){
            var calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'prev,next',
                    center: 'title',
                    right: ''
                    //right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
                },
                // contentHeight: '600',
                // height:600,
                initialDate: moment($store.get("selectDate")).format("YYYY-MM-DD"),
                navLinks: false, // can click day/week names to navigate views
                businessHours: false, // display business hours
                editable: false,
                selectable: true,
                locale: 'zh-cn',
                showNonCurrentDates:true,
                validRange: {
                    start: moment(moment().month(moment().month() - 1).startOf('month').valueOf()).format("YYYY-MM-DD"),
                    end: moment(moment().month(moment().month()+1).startOf('month').valueOf()).format("YYYY-MM-DD")
                },
                dateClick: function (info) {
                    // alert($store.get("hospId")+'Clicked on: ' + info.dateStr);
                    // alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
                    // alert('Current view: ' + info.view.type);
                    // // change the day's background color just for fun
                    // info.dayEl.style.backgroundColor = 'red';



                    if (info.date > yesterday) {
                        $pop.alert('所选日期不允许修改');
                        return
                    }
                    $pop.iframePop({
                        title: "医生信息填报",
                        content: "${base}/ui/amcs/jtr/doctorStatistics/fillIn?hospId=" + $store.get("hospId") + "&statisticsDate=" + info.dateStr+"&hospName="+$store.get("hospName"),
                        area: ['100%', '100%'],
                        end:function () {
                            loadCalendar()
                        }
                    }, '');
                },
                events: [
                    {
                        start: moment().format("YYYY-MM-DD"),
                        end: '2099-12-31',
                        overlap: false,
                        display: 'background',
                        color: 'rgba(255,51,0,0.45)'
                    },
                    {
                        start:moment(moment().month(moment().month() - 1).startOf('month').valueOf()).format("YYYY-MM-DD"),
                        end: moment().format("YYYY-MM-DD"),

                        overlap: false,
                        display: 'background',
                        color: '#dad579'
                    }
                ]

            });

            calendar.render();
            $(".fc-button-primary").on('click', function () {
                $store.set("selectDate",calendar.getDate());
                }
            )
            loadLast60days("${userCode}",$store.get("hospId"))
            for (i in calendarData){
                calendar.addEvent({
                    start: calendarData[i].statisticsDate,
                    end: calendarData[i].statisticsDate,
                    overlap: false,
                    display: 'background',
                    color: 'rgb(130,242,255)'
                })
            }

        }


        $grid.newGrid("#navGridBox", {
            // fitParent: true,
            pagination: false,
            fitColumns: false,
            rownumbers: false,
            remoteSort: false,
            sortName:'mainSign',
            sortOrder:'desc',
            resizeHandle: 'left',
            queryParams: {
                "userCode": "${userCode}"
            },


            columns: [[
                {title: 'id', field: 'id', hidden: true},

                {
                    title: '医院名称', field: 'hospName', width: 295, formatter: function (v, row, index) {
                        if (row.ahisSign) {
                            return v + "(AHIS)";
                        } else {
                            return v;
                        }
                    },
                    styler: function(index,row){
                        if (!row.usingSign){
                            return "background-color:#FFE4DD"
                        }else if(row.mainSign){
                            return "color:#00A0E9;font-weight:bold"
                        }

                    }
                },
                {title:'mainSign',field:'mainSign',hidden: true}
            ]],
            showFooter: false,
            onSelect: function (index, row) {
                $("#hospName").text("——" + row.hospName)

                $store.set("hospId", row.hospId)
                $store.set("hospName", row.hospName)

                [#--$ajax.post("${base}/ui/amcs/jtr/doctorStatistics/getByHospId",{'hospId':row.hospId},false,false,true,true).done(function (rst){--]
                [#--    debugger--]
                [#--    }--]

                [#--)--]

                $ajax.post({
                    url:"${base}/ui/amcs/jtr/doctorStatistics/getByHospId",//请求地址
                    data : {'hospId':row.hospId},//请求数据
                    tip : false,//提交是否需要确认，true或string需要确认
                    jsonData : false,//是否采用jsonData格式提交
                    sync : false,//是否同步方式提交
                    type : 'post',//采用post方式提交
                    loadingBar : true,//loading进度条
                    loadingMask : true,//进行异步请求中，是否显示mask
                    calltip : false,//提交成功后显示请求成功信息
                    success : function(rst){},//请求成功后，code===200或者201返回事件
                    callback : function(rst){
                        if(rst.ahisOnlineDate){
                            $("#ahisHistory").removeClass("hidden")
                            $store.set("startDate", rst.ahisOnlineDate.slice(0,10))
                        }else{
                            $("#ahisHistory").addClass("hidden")
                            $store.set("startDate", "")
                        }
                    },//请求成功后返回事件
                    cancelback : function(){},//确认框点取消返回事件
                    errback : function(req, textStatus, errorThrown){
                        $("#ahisHistory").addClass("hidden")
                        $store.set("startDate", "")
                    }//出现错误时返回事件
                });

                loadCalendar()


            },
            onBeforeLoad: function (param) {
                return true;
            },
            onLoadSuccess: function (data) {

                $(".navGridWrap .datagrid-body tr:first").click();
            },
            url: '${base}/ui/amcs/jtr/position/getAll',
            // height: 'auto',
            offset: -5
        });
        $("#ahisHistory").click(function (){
            $pop.iframePop({
                title: "历史数据刷新",
                content: "${base}/ui/amcs/jtr/doctorStatistics/historyHandle",
                postData:{
                    "hospId":$store.get("hospId"),
                    "hospName":$store.get("hospName"),
                    "startDate":$store.get("startDate"),
                },
                area: ['800px', '400px'],
                end:function () {
                    loadCalendar()
                }
            }, '');
        })

    });

</script>
</body>

</html>

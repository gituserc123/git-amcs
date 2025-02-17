<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>AHIS历史数据刷新 - 爱尔医院</title>
    <link type='text/css' rel='stylesheet' href='${base}/static/js/lib/fullcalendar/lib/fullcalendar.css'/>
    <script type="text/javascript" src="${base}/static/js/lib/fullcalendar/lib/fullcalendar.js"></script>
    <script src='${base}/static/js/lib/fullcalendar/lib/locales/zh-cn.js'></script>
    <script type="text/javascript">


    </script>


</head>

<body>
<div class="tabCont tabCont-0">
    <form class="soform formA form-validate pad-t10" id="dateChoose">


        <div class="row">
            <div class="p12">
                <h2 class="h2-title-a">
            <span class="s-title">由于某些因素（如没按时做手术登记，提交病案等）影响，有些医院刷新数据可能不及时或存在一定偏差，故增加手动刷新系统数据功能。
                此功能会刷新AHIS上线后所选时间段的系统数据，如果手动修改填报过某几天的数据，这几天数据不会受此功能影响，以手动填报值为准。为保证系统性能，一次最多只能选择90天进行刷新。
                <p style="color:darkred" id="hospNames">
                </p></span>
                </h2>
            </div>
        </div>
        <div class="row">

            <div class="p8">
                <div class="item-one solab-lb">
                    <label class="lab-item solab-lb">开始时间-结束日期:</label>
                    <input id="dateRange" type="text"
                           class="txt  so-rangeDate txt-rangeDate required" name="dateRange"
                           data-opt=""
                           style="width: 100%" noNull="必填">

                </div>
            </div>
            <div class="p4">
                <input id="submit" type="button" class="btn btn-primary" name="btnSubmit" noclosepop="true" value="提交"/>
            </div>
        </div>
    </form>
</div>

</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    require(["lodash", "moment", "pub"], function (_, moment) {
        var parentData=$store.getPostData();
        if (!parentData.hospId){
            $("#dateChoose").addClass("hidden")
            $pop.alert("请从填报日历页面完成历史数据刷新！")
        } else if (!parentData.startDate){
            $("#dateChoose").addClass("hidden")
            $pop.alert("医院没上线AHIS,不能刷新！")
        }
        $("#hospNames").text("当前刷新医院:"+parentData["hospName"]+"(上线日期："+parentData.startDate+")")
      /*  $ajax.post({
            url:"${base}/ui/amcs/jtr/position/getAll",//请求地址
            data:{"userCode": "${userCode}"},
            tip : false,//提交是否需要确认，true或string需要确认
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            loadingBar : true,//loading进度条
            loadingMask : true,//进行异步请求中，是否显示mask
            calltip : false,//提交成功后显示请求成功信息
            success : function(rst){
            },//请求成功后，code===200或者201返回事件
            callback : function(rst){
                data=_.filter(rst,{"ahisSign":1,"usingSign":1})

                if(data.length==0){
                    $("#dateChoose").remove();

                    $("#ahisDiv").removeClass("hidden");
                }else{
                    d=_.map(data,"hospName")
                    str="只能刷新使用AHIS系统的医院数据，您可刷新医院如下："+_.join(d,",")
                    $("#hospNames").text(str)
                }
            },//请求成功后返回事件
            cancelback : function(){},//确认框点取消返回事件
            errback : function(req, textStatus, errorThrown){}//出现错误时返回事件
        });*/
        var dateV = {
            yesterday : [
                moment().subtract(1, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            week : [
                moment().subtract(7, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            threeMonth : [
                moment().subtract(91, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            thisMonth : [
                moment().startOf('month'),
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            prevMonth : [
                moment().subtract(1, 'month').startOf('month'),
                moment().subtract(1, 'month').endOf('month')
            ]
        }
        var ranges ={
            '昨天': dateV.yesterday,
            '最近一周': dateV.week,
            '当月': dateV.thisMonth,
            '上个月': dateV.prevMonth,
            '最近90天': dateV.threeMonth,
        } ;
        $form.rangeDate('#dateRange',{

            val:([moment().subtract(1, 'days'),moment().subtract(1, 'days')]), // 可以为 dateV 中任意键值 ，也可以为 数组 ([startDate,endDate])，如下2个代码示例
            auto : true,//auto 为false 会显示确定按钮
            opens:'center',
            maxSpan:{days: 90},
            showDropdowns:true,
            minDate: moment(parentData["startDate"]),
            autoApply : true,//日期范围不用确认，自动选择
            linkedCalendars: false,//两个日历不一起联动，保证可以选择两个月以上的日期
            diyRanges:ranges,
            maxDate:moment().subtract(1, 'days'),
            timePicker: false, // 显示时间选择器
            // init : function () {
            //     setTimeout(function (){$('.ranges li[data-range-key="今天"]').remove()},100)
            // }
        })

        // $('#dateRange').daterangepicker({maxDate:moment().subtract(1, 'days'),startDate:moment().subtract(1, 'days')})

        $("#submit").click(function () {
            $pop.confirm('确认要刷新' + $('#dateRange').data('daterangepicker').startDate.format('YYYY-MM-DD') + '到' + $('#dateRange').data('daterangepicker').endDate.format('YYYY-MM-DD') + '的系统生成数据？', function () {

                $ajax.post({
                    url:"${base}/ui/amcs/jtr/doctorStatistics/startJob",//请求地址
                    data : {
                        beginDate:$('#dateRange').data('daterangepicker').startDate.format('YYYY-MM-DD'),
                        endDate:$('#dateRange').data('daterangepicker').endDate.format('YYYY-MM-DD'),
                        hospId:parentData.hospId
                    },//请求数据
                    tip : false,//提交是否需要确认，true或string需要确认
                    jsonData : false,//是否采用jsonData格式提交
                    sync : false,//是否同步方式提交
                    type : 'post',//采用post方式提交
                    loadingBar : true,//loading进度条
                    loadingMask : true,//进行异步请求中，是否显示mask
                    calltip : true,//提交成功后显示请求成功信息
                    success : function(rst){
                        $pop.success('刷新成功，请到“医生报表”页面查看刷新后数据！');
                    },//请求成功后，code===200或者201返回事件
                    callback : function(rst){

                        },//请求成功后返回事件
                    cancelback : function(){},//确认框点取消返回事件
                    errback : function(req, textStatus, errorThrown){}//出现错误时返回事件
                });
                return true;//return true关闭窗口
            }, function () {

                return true;//return true关闭窗口
            });


        })

    })

</script>
</body>

</html>

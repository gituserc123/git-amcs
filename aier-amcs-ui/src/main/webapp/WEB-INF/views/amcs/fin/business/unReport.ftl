<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>本月未填报医院 - 爱尔医院</title>
    [#include '/WEB-INF/views/common/include_resources.ftl']
</head>
<body>
<div class="searchHead">
    <input type="button" class="btn btn-primary" id="unReport" value="本月未上报"/>
    <input type="button" class="btn" id="reporting" value="本月上报中"/>
    <input type="button" class="btn" id="return" value="本月退回"/>
    <input type="button" class="btn" id="noPrice" value="本年未报价格政策"/>
    <input type="button" class="btn" id="noDrgDip" value="当季未填报DRG、DIP病种政策"/>
</div>
<div class="cont-grid">
    <div id="gridBox"></div>
</div>
</body>
[#include '/WEB-INF/views/common/include_js.ftl']
<script id="formTem" type="text/html">
    <form id="infoForm" class="soform form-validate pad-t20 pad-r20 form-enter"
          data-opt="{beforeCallback:'submitEditForm'}" autocomplete="off">
        <input class="hide" type="text" name="hospId" id="hospId">
    </form>
</script>
<script type="text/javascript">

    requirejs(["pub"], function () {

        $grid.newGrid("#gridBox", {
            tools: [
                [
                [#--    {--]
                [#--    iconCls: "plus_sign",--]
                [#--    text: "初始化月报",--]
                [#--    title: "初始化月报",--]
                [#--    click: function () {--]
                [#--        $ajax.post("${base}/ui/service/biz/amcs/fin/finMonthMain/new",{hospId:'${hospId}'},"是否初始化当月数据，注意，将按目前的统筹区配置初始化月报，初始化后不能撤回，请确认已经完成医院医保统筹区配置！",false).then(res => {--]
                [#--            $('#gridBox').datagrid('reload');--]
                [#--        });--]
                [#--    }--]
                [#--}--]
                ]
            ],
            queryParams:{unReportType:"unReport"},
            checkOnSelect: false,
            selectOnCheck: false,
            singleSelect: false,
            ctrlSelect: true,
            fitColumns: false,
            pagination:false,
            columns: [
                [

                    {title: "医院名称", field: "hospName", hidden: false, width: 300},


                ]
            ],
            onLoadSuccess: function (data) {


            },
            url: "${base}/ui/service/biz/amcs/fin/finMonthMain/unReport",
        });

        //所有btn图标都去掉btn-primary类
        function removeBtnPrimary($btn) {
            $(".btn").removeClass("btn-primary");
            $btn.addClass("btn-primary");
        }

        $("#unReport").click(function () {
            $grid.reload("#gridBox", {unReportType:"unReport"});
            removeBtnPrimary($(this));
        });
        $("#reporting").click(function () {
            $grid.reload("#gridBox", {unReportType:"reporting"});
            removeBtnPrimary($(this));
        });
        $("#return").click(function () {
            $grid.reload("#gridBox", {unReportType:"return"});
            removeBtnPrimary($(this));
        });
        $("#noPrice").click(function () {
            $grid.reload("#gridBox", {unReportType:"noPrice"});
            removeBtnPrimary($(this));
        });
        $("#noDrgDip").click(function () {
            $grid.reload("#gridBox", {unReportType:"noDrgDip"});
            removeBtnPrimary($(this));
        });

    });
</script>

</html>

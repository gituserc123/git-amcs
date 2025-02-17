<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>泪道转诊 - 爱尔医院</title>
    <style type="text/css">
        /*.mainbody{position: absolute;top:0px;left:0;right:0;bottom:50px;}*/
        .mainfooter {
            padding: 0px 10px 0;
            text-align: right;
        }

        .cont-grid {
            overflow: hidden;
            zoom: 1;
            border-bottom: 5px solid #E8EBF2;
        }

        .cont-l {
            width: 1000px;
        }

        .navGridWrap {
            border-right: 5px solid #E8EBF2;
        }

        .h2-title {
            line-height: 25px;
            color: #00A0E9;
            font-size: 14px;
        }

        .h2-title-a {
            padding: 22px 10px 6px;
        }

        .soform input.so-rangeDate {
            width: 180px;
        }
    </style>

</head>

<body>
<div class="searchHead bob-line">
    <form id="sbox" class="soform form-enter">
        <label class="lab-inline">患者名称</label><input type="text" class="txt inline txt-validate" id="patientName" name="patientName"
                                                     autocomplete="off" validType="maxlength[10,'搜索框不能超过10个字符']">
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#navGridBox',scope:'#sbox'}">
            查
            询
        </button>
    </form>
</div>
<div class="cont-grid">
         <div id="navGridBox"></div>
</div>

<div class="mainfooter">

</div>

</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    require(['lodash',"pub"], function (_) {

        $grid.newGrid("#navGridBox", {
            // fitParent: true,
            pagination: true,
            fitColumns: false,
            singleSelect:true,
            tools: [
                [
                    {
                        iconCls: 'plus',//图标
                        text: '新增',//文字
                        click: function () {
                            var a= $("#navGridBox").datagrid("getSelections");
                            if(_.isEmpty(a)){
                                $pop.alert.err('请选择患者！');
                                return false;
                            }
                            $pop.confirm('是否保存所有选中项目',function(){
                                $ajax.post({
                                    url: "${base}/ui/amcs/cat/lacrimal/batchSave",
                                    data: a,
                                    jsonData: true,
                                    tip: "是否提交？",
                                    calltip: true,
                                    success: function (rst) {
                                        setTimeout($pop.closePop({refreshGrid:true}),1000);
                                    }
                                });
                                return true;//return true关闭窗口
                            },function(){
                                return true;//return true关闭窗口
                            });
                        }
                    }
                ]
            ],
            columns: [[
                {field: 'ck',checkbox:true},
                {title: 'ID', field: 'id', hidden: true, width: 150},
                {title: 'patientId', field: 'patientId', hidden: true, width: 100},
                {title: '就诊日期', field: 'checkDate', width: 150},
                {title: '患者姓名', field: 'patientName', width: 60},
                // {title: 'OD返流', field: 'odBackflow', width: 80},
                // {title: 'OD不入喉', field: 'odInthroat', width: 100},
                // {title: 'OD分泌物', field: 'odSecretion', width: 80},
                // {title: 'OS返流', field: 'osBackflow', width: 80},
                // {title: 'OS不入喉', field: 'osInthroat', width: 100},
                // {title: 'OS分泌物', field: 'osSecretion', width: 80},
                // {title: '首诊科室', field: 'fstDeptName', width: 100},
                // {title: '首诊医生', field: 'fstDoctorName', width: 100},
                // {title: '首诊医生处置情况', field: 'fstTreatInfo', width: 110},
                // {title: '泪道医生姓名', field: 'glaucomaDoctorName', width: 100},
                // {title: '泪道医生诊断结果', field: 'glaucomaResult', width: 130},
                // {title: '是否治疗泪道', field: 'isTreat', width: 100},
                // {title: '未治疗原因', field: 'untreatReason', width: 100}
            ]],
            rowStyler: function (index, row) {

            },
            queryParams: {

            },
            onBeforeLoad: function (param) {
                return true;
            },
            onLoadSuccess: function (data) {

            },
            onSelect: function (index, row) {

            },
            url: '${base}/ui/amcs/cat/lacrimal/getHisAll',
            offset: -5
        });

    });

</script>
</body>

</html>

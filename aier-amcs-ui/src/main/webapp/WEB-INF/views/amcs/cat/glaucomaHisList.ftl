<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>青光眼转诊 - 爱尔医院</title>
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
            singleSelect:false,
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
                                    url: "${base}/ui/amcs/cat/glaucoma/batchSave",
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
                {title: '就诊日期', field: 'outpatientDate', width: 150},
                {title: '患者姓名', field: 'patientName', width: 60},
                {title: 'OD眼压', field: 'odPressure', hidden: false, width: 80},
                {title: 'OS眼压', field: 'osPressure', width: 100},
                {title: '首诊科室（转出）', field: 'fstOutDeptName', width: 100},
                {title: '首诊医生（转出）', field: 'fstOutDoctorName', width: 100},
                {title: '首诊医生处置情况（转出）', field: 'outTreatInfo', width: 150,formatter: function (v, row, index) {
                        if(v==1){return "直接转诊";}else if(v==2){return "排青后转诊";}else if(v==3){return "排青未转诊";}else if(v==4){return "未做处置";}
                }},
                {title: '接诊医生（转入）', field: 'inDoctorName', width: 100},
                {title: '接诊医生诊断结果（转入）', field: 'inMedicalResult', width: 150,formatter: function (v, row, index) {
                        if(v==1){return "先天性青光眼";}else if(v==2){return "急性闭角型青光眼";}else if(v==3){return "原发性开角型青光眼";}else if(v==4){return "慢性闭角型青光眼";}else if(v==5){return "继发性青光眼";}else if(v==6){return "非青光眼";}else{return v;}
                }},
                {title: '接诊医生处置情况（转入）', field: 'inTreatInfo', width: 150,formatter: function (v, row, index) {
                        if(v==1){return "已药物治疗";}else if(v==2){return "已手术治疗";}else if(v==3){return "已激光治疗";}else if(v==4){return "未治疗";}
                }},
                {title: '未治疗原因', field: 'untreatReason', width: 100}
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
            url: '${base}/ui/amcs/cat/glaucoma/getHisAll',
            offset: -5
        });

    });

</script>
</body>

</html>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>集团医保查询 - 爱尔医院Ahis管理系统</title>
    <link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon"/>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
        .mainContBox .searchHead {
            margin-top: 0;
        }
    </style>
</head>
<body>

<div class="wrapper">
    <h2 class="h2-pageTitle">模块管理</h2>

    <div class="sideBarBox">
        <div class="sideTreeC">
            <ul id="ul-moduleTree"></ul>
        </div>
    </div>

    <div class="mainContBox">
        <div class="soform searchHead">
            <form id="sbox" class="form-inline" method="post" action="#">
                <span class="fl"><label class="lab-inline">年份：</label>
                    <select class="drop drop-sl-v easyui-combobox w-120" id="year" name="year" data-options="clearIcon:true,editable:false,valueField:'valueCode',textField:'valueDesc'"></select>
                </span>
                <span class="fl"><label class="lab-inline">月份：</label>
                <select class="drop wp-5"  id="month" name="month">
                    <option value="" ></option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08">8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
                </span>
                <label class="lab-inline">医保类别：</label>
                [@ui_select id="insuranceType"  class="drop easyui-combobox w-120"  uiShowDefault=true  name="insuranceType"  tag="@fin@INSURANCE_TYPE" dataOptions="" filterkey="firstSpell" /]
                <label class="lab-inline">卫健委定级：</label>
                [@ui_select id="healthCommissionLevel"  class="drop easyui-combobox w-120"  uiShowDefault=true  name="healthCommissionLevel"  tag="@fin@LEVEL" dataOptions="" filterkey="firstSpell" /]
                <label class="lab-inline">医保结算等级：</label>
                [@ui_select id="insuranceSettlementLevel"  class="drop easyui-combobox w-120"  uiShowDefault=true  name="insuranceSettlementLevel"  tag="@fin@LEVEL" dataOptions="" filterkey="firstSpell" /]
                <label class="lab-inline">应收医保款回款率：</label>
                <select class="drop w-100"  id="" name="receivableCollectionRateSelectValue">
                    <option value="" ></option>
                    <option value="1">大于等于95%</option>
                    <option value="2">90%≤X<95%</option>
                    <option value="3">80%≤X<90%</option>
                    <option value="4">60%≤X<80%</option>
                    <option value="5">60%以下</option>
                </select>
                <label class="lab-inline">DIP节余/超支：</label>
                <select class="drop w-100"  id="" name="dipBo">
                    <option value="" ></option>
                    <option value="1">正→结余</option>
                    <option value="2">负→超支</option>
                </select>
                <label class="lab-inline">DRG节余/超支：</label>
                <select class="drop w-100"  id="" name="drgBo">
                    <option value="" ></option>
                    <option value="1">正→结余</option>
                    <option value="2">负→超支</option>
                </select>
                <label class="lab-inline">按人头节余/超支：</label>
                <select class="drop w-100"  id="" name="perBo">
                    <option value="" ></option>
                    <option value="1">正→结余</option>
                    <option value="2">负→超支</option>
                </select>             &nbsp;&nbsp;
                <button type="button" class="btn btn-small btn-primary so-search" data-opt="">查 询</button>
                <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
            </form>
        </div>

        <div class="">
            <div id="gridBox"></div>
        </div>

    </div>
</div>
<div class="none"></div>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['pub'], function () {
        var popW = ($(window).width()) + 'px';
        $(window).resize(function () {
            popW = ($(window).width()) + 'px';
        });

        const currentYear = new Date().getFullYear();
        const preYear = currentYear - 1;
        const prePreYear = preYear - 1;
        $('#year').combobox('loadData', JSON.parse('[{"valueCode":'+currentYear+',"valueDesc":'+currentYear+'},{"valueCode":'+preYear+',"valueDesc":'+preYear+'},{"valueCode":'+prePreYear+',"valueDesc":'+prePreYear+'}]'));

        $('#month').combobox({
            onChange: function(newValue,oldValue){
                if(!$('#year').combobox('getValue')){
                    $('#year').combobox('setValue',currentYear);
                }
            }
        });

        $grid.newGrid("#gridBox", {
            pagination: true,
            fitColumns: false,
            columns:[[
                {title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
                        let opStr = '';
                        opStr = '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
                        return opStr;
                    }
                },
                {title:'id',field:'id',width:80,hidden:true},
                {title:'上报时间', field:'createDate', width:120, format:'yyyy-MM-dd'},
                {title:'省区', field:'hospParent', hidden: false, width:80},
                {title:'医院', field:'hospName', hidden: false, width:150},
                {title:'医院类型', field:'investNature', hidden: false, width:80,formatter:function (v, row, index) {
                        if(v=='10'){return '上市';}else if(v=='11'){return '合伙';}else{return '';}
                    }},
                {title:'医院等级', field:'ehrLevel', hidden: false, width:80},
                {title:'统筹区', field:'poolingArea', hidden: false, width:100},
                {title:'卫健委定级', field:'healthCommissionLevelDesc', width:120},
                {title:'医保结算等级', field:'insuranceSettlementLevelDesc', width:120},
                {title:'医保类别', field:'insuranceTypeDesc', hidden: false, width:150},
                {title: "状态", field: "status", hidden: false, width: 100,formatter(v,row,index){
                        if(v==1){return "上报中";}else if(v==5){return "省区审核";}else if(v==6){return "省区审核完成";}else{return "退回";}
                    }},
                {title:'应收医保款期末余额', field:'receivableBalanceEndPeriod', hidden: false, width:115},
                {title:'应收医保款回款率', field:'receivableCollectionRate', hidden: false, width:110},
                {title:'坏账核销金额(万元)', field:'insuranceBadDebtAmt', hidden: false, width:110},
                {title:'坏账核销情况原因', field:'insuranceBadDebtCause', hidden: false, width:110},
                {title:'慈善核销金额(万元)', field:'charityBadDebtAmt', hidden: false, width:110},
                {title:'慈善核销金额原因', field:'charityBadDebtCause', hidden: false, width:110},
                {title:'年医保扣款金额(万元)', field:'penaltyDeductionAmount', hidden: false, width:125},
                {title:'年医保罚款金额(万元)', field:'penaltyFeeAmount', hidden: false, width:125},
                {title:'住院结算政策', field:'hospSettlementPolicyDesc', hidden: false, width:180},
                {title:'医保预留金比例', field:'agreementReserveRatio', hidden: false, width:100},
                {title:'自费率考核指标', field:'selfPaymentRate', hidden: false, width:100},
                {title:'自费率超标原因', field:'reasonsForExceeding', hidden: false, width:100},
                {title:'服务限制性条款', field:'designatedServiceAgreement', hidden: false, width:100},
                {title:'是否医保局备案', field:'isAgreement', hidden: false, width:100,formatter(v,row,index){
                        if(v==1){return "是";}else if(v==2){return "否";}else{return "";}
                }},
                {title:'未备案原因', field:'unfiledAgreementReasons', hidden: false, width:100},
                {title:'是否优免行为', field:'existenceAgreedStandards', hidden: false, width:80,formatter(v,row,index){
                        if(v==1){return "是";}else if(v==2){return "否";}else{return "";}
                }},
                {title:'是否出具回执', field:'isWrittenAck', hidden: false, width:100,formatter(v,row,index){
                        if(v==1){return "是";}else if(v==0){return "否";}else{return "";}
                }},
                {title:'日间手术病种', field:'daySurgeryInHospital', hidden: false, width:100},
                {title:'日间手术结算政策', field:'daySurgerySettlementPolicy', hidden: false, width:100},
                {title:'门诊统筹结算政策', field:'outpUnifiedSettlementPolicy', hidden: false, width:100},
                {title:'特(慢)结算政策', field:'specialSettlementPolicy', hidden: false, width:100},
                {title:'其他事项', field:'otherIssues', hidden: false, width:100},
                {title:'总额控制截至填报月预计节余/超支总金额', field:'totalBalanceOrOverspend', hidden: false, width:160},
                {title:'单病种截至填报月预计节余/超支总金额', field:'sglBalanceOrDeficitAmount', hidden: false, width:160},
                {title:'DIP截至填报月预计节余/超支总金额', field:'dipBalanceOrOverspend', hidden: false, width:160,
                    formatter: function(val,row,index){
                        if(val < 0){return '<span class="red">'+val+'</span>';}else{return val;}
                    }
                },
                {title:'DRG截至填报月预计节余/超支总金额', field:'drgBalanceOrOverspend', hidden: false, width:160,
                    formatter: function(val,row,index){
                        if(val < 0){return '<span class="red">'+val+'</span>';}else{return val;}
                    }
                },
                {title:'按人头付费截至填报月预计节余/超支总金额', field:'perBalanceOrDeficitAmount', hidden: false, width:160},
                {title:'按项目年度总指标', field:'projTotalIndicator', hidden: false, width:160},
                {title:'按项目截至填报月已使用医保总额', field:'projActualUsedAmount', hidden: false, width:160},
                {title:'按项目超总额原因', field:'projExceedReason', hidden: false, width:160},
                {title:'按项目超总额整改措施', field:'projRectificationMeasures', hidden: false, width:160},
                {title:'按项目是否书面反馈', field:'projFeedbackToManagement', hidden: false, width:160,formatter(v,row,index){
                        if(v=="1"){return "是";}else if(v=="0"){return "否";}else{return "";}
                    }},
                {title:'总额超总额整改措施', field:'advRectificationMeasures', hidden: false, width:160},
                {title:'总额是否书面反馈', field:'advFeedbackToManagement', hidden: false, width:160,formatter(v,row,index){
                        if(v=="1"){return "是";}else if(v=="0"){return "否";}else{return "";}
                    }},
                {title:'DIP超总额整改措施', field:'dipRectificationMeasures', hidden: false, width:160},
                {title:'DIP是否书面反馈', field:'dipFeedbackToManagement', hidden: false, width:160,formatter(v,row,index){
                        if(v=="1"){return "是";}else if(v=="0"){return "否";}else{return "";}
                    }},
                {title:'DRG超总额整改措施', field:'drgRectificationMeasures', hidden: false, width:160},
                {title:'DRG是否书面反馈', field:'drgFeedbackToManagement', hidden: false, width:160,formatter(v,row,index){
                        if(v=="1"){return "是";}else if(v=="0"){return "否";}else{return "";}
                    }},
            ]],
            rowStyler : function(index, row) {

            },
            onBeforeLoad: function(param){

            },
            onLoadSuccess : function (data) {
                $('.s-op-review').click(function () {
                    var ix = $(this).attr('rel');
                    var row = data.rows[ix];
                    var formData = $('#sbox').sovals();
                    var node = $('#ul-moduleTree').tree('getSelected');
                    $pop.iframePop({
                        title: '医保政策查看',//标题
                        content: '${base}/ui/service/biz/amcs/fin/finInsMain/reportTabs?mainId='+row.id+'&viewFlag=99',//请求地址
                        area: ['100%', '100%'],//窗口大小
                        // postData : {mainId:row.id},//往子页面传值
                        end : function(iframeSendData){
                            //关闭执行函数，子页面可通过 $pop.closePop 返回参数
                            if(node){
                                if(node.pid=='0'){
                                    if(node.id=='99'){
                                        formData.hospId=null;
                                        formData.province=null;
                                    }else{
                                        formData.hospId=null;
                                        formData.province=node.id;
                                    }
                                }else{
                                    formData.hospId=node.hospId;
                                    formData.province=null;
                                }
                            }
                            $grid.load('#gridBox',formData);
                        },
                        sureback : function (iframeSendData){
                        }
                    },'#gridBox');//刷新grid #gridBox
                });
            },
            queryParams : {

            },
            url: '${base}/ui/service/biz/amcs/fin/finInsMain/queryList',
            offset : -50
        });

        $(".so-search").click(function () {
            var formData = $('#sbox').sovals();
            var node = $('#ul-moduleTree').tree('getSelected');
            if(node){
                if(node.pid=='0'){
                    if(node.id=='99'){
                        formData.hospId=null;
                        formData.province=null;
                    }else{
                        formData.hospId=null;
                        formData.province=node.id;
                    }
                }else{
                    formData.hospId=node.hospId;
                    formData.province=null;
                }
            }
            $grid.load('#gridBox',formData);
        });

        $(".s-export").click(function ($e) {
            var formData = $('#sbox').sovals();
            var node = $('#ul-moduleTree').tree('getSelected');
            if(node){
                if(node.pid=='0'){
                    if(node.id=='99'){
                        formData.hospId=null;
                        formData.province=null;
                    }else{
                        formData.hospId=null;
                        formData.province=node.id;
                    }
                }else{
                    formData.hospId=node.hospId;
                    formData.province=null;
                }
            };
            $pop.confirm('您确认想要导出记录吗？',function(r){
                if (r){
                    var url = '${base}/ui/service/biz/amcs/fin/finInsMain/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    downloadExcelProcess(url);
                }
            });
        });

        $('#ul-moduleTree').tree({
            animate: true,
            lines: true,
            url: '${base}/ui/service/biz/amcs/fin/finInsGroupQuery/tree',
            flatData: true,
            onClick: function (node) {
                var formData = $('#sbox').sovals();
                if(node.pid=='0'){
                    if(node.id=='99'){
                        formData.hospId=null;
                        formData.province=null;
                    }else{
                        formData.hospId=null;
                        formData.province=node.id;
                    }
                }else{
                    formData.hospId=node.hospId;
                    formData.province=null;
                }
                $grid.load('#gridBox',formData);
            },
            onLoadSuccess:function(){
                $('#ul-moduleTree').tree('collapseAll');
            }
        });

    });

    [#include "/WEB-INF/views/amcs/fin/business/subReport/finExport_js.ftl"]

</script>

</body>
</html>
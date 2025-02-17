<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医保政策查询 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            [#if empType==1]
            <label class="lab-inline">省区：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true">
            [/#if]
            </select>
            [#if empType==1 || empType==2]
            <label class="lab-inline">医院：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
            </select>
            [/#if]
            <label class="lab-inline">上报日期：</label>
            <input id="reportDate" name="reportDate" type="text" class="txt so-rangeDate" />
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
            </select>
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
            <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
            <!-- <button type="button" class="btn btn-small btn-primary s-export-detail">导出明细</button> -->
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", "moment", 'pub'], function (_, $e,moment) {
        this.reloadEventData();

    	$grid.newGrid("#gridBox",{
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
                {title:'DIP超支整改措施', field:'dipRectificationMeasures', hidden: false, width:160},
                {title:'DIP是否书面反馈', field:'dipFeedbackToManagement', hidden: false, width:160,formatter(v,row,index){
                        if(v=="1"){return "是";}else if(v=="0"){return "否";}else{return "";}
                }},
                {title:'DRG超支整改措施', field:'drgRectificationMeasures', hidden: false, width:160},
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
                    $pop.iframePop({
                        title: '医保政策查看',//标题
                        content: '${base}/ui/service/biz/amcs/fin/finInsMain/reportTabs?mainId='+row.id+'&viewFlag=99',//请求地址
                        area: ['100%', '100%'],//窗口大小
                        // postData : {mainId:row.id},//往子页面传值
                        end : function(iframeSendData){
                            //关闭执行函数，子页面可通过 $pop.closePop 返回参数
                            $grid.load('#gridBox',{hospId:'${hospId}',monthId:'${monthId}'});
                        },
                        sureback : function (iframeSendData){

                        }
                    },'#gridBox');//刷新grid #gridBox
				});
      		},
      		url: '${base}/ui/service/biz/amcs/fin/finInsMain/queryList',
      		offset : -50
  		});

        $(".s-export").click(function ($e) {
            var formData = $('#sbox').sovals();
            $pop.confirm('您确认想要导出记录吗？',function(r){
                if (r){
                    var url = '${base}/ui/service/biz/amcs/fin/finInsMain/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    downloadExcelProcess(url);
                }
            });
        });

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
            ],
            prevYear: [
                moment().subtract(1, 'years'),
                moment()
            ]
        };

        var ranges ={
            '昨天': dateV.yesterday,
            '最近一周': dateV.week,
            '当月': dateV.thisMonth,
            '上个月': dateV.prevMonth,
            '最近90天': dateV.threeMonth,
            '最近一年': dateV.prevYear,
        };

        $form.rangeDate('#reportDate',{
            auto : false,//auto 为false 会显示确定按钮
            maxSpan:{days: 1095},
            ranges:ranges,
        });

    });

    var showMask = function () {
        var wrap = $(".cont-grid");
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: window.width,
            height: window.height
        }).appendTo(wrap);
    };
    var rmMask = function () {
        var wrap = $(".cont-grid");
        wrap.find("div.datagrid-mask").remove();
    }

    function reloadEventData() {
        [#if empType==1]
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
                $('#province').combobox('loadData', rst);
            });
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
                $('#hospId').combobox('loadData', rst);
            });
            $('#province').combobox({
                onChange: function(v){
                    let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                    $('#hospId').combobox('reload', url);
                }
            });
        [/#if]
        [#if empType==2]
        var paramInsi = {insiId:'${insiId}'};
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
            $('#hospId').combobox('loadData', rst);
        });
        [/#if]


    };

    function downloadExcelProcess(url){
        // 开启遮罩
        showMask();
        //$pop.alert('文件正在导出，请耐心等待....');
        $pop.msg('文件正在导出，请耐心等待....',3000,{icon:2});
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = "blob"; // 返回类型blob
        // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
        xhr.onload = function() {
            // 请求完成
            if (this.status === 200) {
                var type = xhr.getResponseHeader('Content-Type');
                var blob = new Blob([this.response], {
                    type: type
                });
                var fileName = xhr.getResponseHeader("content-disposition");
                fileName = decodeURI(fileName.split(";")[1].split("filename=")[1].trim('"'));
                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    // For IE>=IE10
                    window.navigator.msSaveBlob(blob, fileName);
                } else {
                    // For chrome, firefox
                    var URL = window.URL || window.webkitURL;
                    var objectUrl = URL.createObjectURL(blob);
                    if (fileName) {
                        // 创建一个a标签用于下载
                        var a = document.createElement('a');
                        a.download = fileName;
                        a.href = objectUrl;
                        $("body").append(a);
                        a.click();
                        $(a).remove();
                    } else {
                        window.location = objectUrl;
                    }
                }
            } else {
                $pop.alert('导出失败！');
            }
            // 关闭遮罩代码
            rmMask();
        };
        // 发送ajax请求
        xhr.send();
    }
</script>
</html>
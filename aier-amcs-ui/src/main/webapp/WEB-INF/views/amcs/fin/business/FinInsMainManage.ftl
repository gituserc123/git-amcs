<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>医保政策主表管理 - 爱尔医院</title>
	[#include '/WEB-INF/views/common/include_resources.ftl']
</head>
<body>
<div class="cont-grid">
	<div id="gridBox"></div>
</div>
</body>
[#include '/WEB-INF/views/common/include_js.ftl']
<script id="formTem" type="text/html">
	<form id="infoForm" class="soform form-validate pad-t20 pad-r20 form-enter"
		  data-opt="{beforeCallback:'submitEditForm'}" autocomplete="off">

		<p class="row-btn center">
			<input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>
			<input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
		</p>
	</form>
</script>
<script type="text/javascript">
	requirejs(["pub"], function () {
		var formPop = null;

		$grid.newGrid("#gridBox", {
/*			tools: [
				[{
					iconCls: "plus_sign",
					text: "新增",
					title: "新增",
					click: function () {
						formPop = $pop.popTemForm({
							title: '新增',
							temId: 'formTem',
							area: ['800px', '350px']
						});
					}
				}]
			],*/
			queryParams:{monthId:'${monthId}'},
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			ctrlSelect: true,
			fitColumns: false,
			pagination:false,
			columns: [
				[

					{
						title: "操作",
						field: "op",
						width: 60,
						formatter: function (v, row, index) {
							return '<span class="s-op s-op-edit " title="月报填写" rel="' + index + '"><svg style="top:2px;position: relative" class="icon" viewBox="128 128 768 768" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3984" width="14" height="14"><path d="M312.1 591.5c3.1 3.1 8.2 3.1 11.3 0l101.8-101.8 86.1 86.2c3.1 3.1 8.2 3.1 11.3 0l226.3-226.5c3.1-3.1 3.1-8.2 0-11.3l-36.8-36.8c-3.1-3.1-8.2-3.1-11.3 0L517 485.3l-86.1-86.2c-3.1-3.1-8.2-3.1-11.3 0L275.3 543.4c-3.1 3.1-3.1 8.2 0 11.3l36.8 36.8z" p-id="3985" fill="#1296db"></path><path d="M904 160H548V96c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v64H120c-17.7 0-32 14.3-32 32v520c0 17.7 14.3 32 32 32h356.4v32L311.6 884.1c-3.7 2.4-4.7 7.3-2.3 11l30.3 47.2v0.1c2.4 3.7 7.4 4.7 11.1 2.3L512 838.9l161.3 105.8c3.7 2.4 8.7 1.4 11.1-2.3v-0.1l30.3-47.2c2.4-3.7 1.3-8.6-2.3-11L548 776.3V744h356c17.7 0 32-14.3 32-32V192c0-17.7-14.3-32-32-32z m-40 512H160V232h704v440z" p-id="3986" fill="#1296db"></path></svg></span>';
						}
					},
					{title: "ID", field: "id", hidden: true, width: 100},
						// {title: "月主表ID", field: "monthId", hidden: true, width: 100},
						{title: "医保统筹区", field: "poolingArea", hidden: false, width: 100},
						{title: "医保类别", field: "typeName", hidden: false, width: 100},
						{title: "医保坏账核销情况原因", field: "insuranceBadDebtCause", hidden: false, width: 100},
						{title: "慈善核销金额（万元）", field: "charityBadDebtAmt", hidden: false, width: 100},
						{title: "慈善坏账核销情况原因", field: "charityBadDebtCause", hidden: false, width: 100},
						{title: "年医保扣罚款情况扣款金额（万元）", field: "penaltyDeductionAmount", hidden: false, width: 100},
						{title: "年医保扣罚款情况罚款金额（万元）", field: "penaltyFeeAmount", hidden: false, width: 100},
						// {title: "住院结算政策（码表:SETTLEMENT_POLICY）", field: "hospSettlementPolicy", hidden: false, width: 100},
						{title: "医保协议规定预留金比例（%）", field: "agreementReserveRatio", hidden: false, width: 100},
						{title: "自费率考核指标（≤%）", field: "selfPaymentRate", hidden: false, width: 100},
						{title: "自费率超标原因", field: "reasonsForExceeding", hidden: false, width: 100},
						{title: "定点服务协议限制性条款", field: "designatedServiceAgreement", hidden: false, width: 100},
						{title: "慈善协议是否到当地医保局备案(1是，2否）", field: "isAgreement", hidden: false, width: 100},
						{title: "未备案原因", field: "unfiledAgreementReasons", hidden: false, width: 100},
						{title: "是否存在超协议标准优免行为(1是，2否）", field: "existenceAgreedStandards", hidden: false, width: 100},
						{title: "医院实际开展日间手术的病种", field: "daySurgeryInHospital", hidden: false, width: 100},
						{title: "日间手术结算政策", field: "daySurgerySettlementPolicy", hidden: false, width: 100},
						{title: "门诊统筹结算政策", field: "outpUnifiedSettlementPolicy", hidden: false, width: 100},
						{title: "特（慢）结算政策", field: "specialSettlementPolicy", hidden: false, width: 100},
						{title: "其他事项", field: "otherIssues", hidden: false, width: 100},
						// {title: "创建者ID", field: "creator", hidden: false, width: 100},
						// {title: "创建时间", field: "createDate", hidden: false, width: 100},
						// {title: "修改者ID", field: "modifer", hidden: false, width: 100},
						// {title: "修改时间", field: "modifyDate", hidden: false, width: 100},
						// {title: "医院ID", field: "hospId", hidden: false, width: 100},


						{title: "卫健委定级(码表：LEVEL)", field: "healthCommissionLevel", hidden: false, width: 100},
						{title: "医保结算等级(码表：LEVEL)", field: "insuranceSettlementLevel", hidden: false, width: 100},
						{title: "应收医保款期末余额（万元）", field: "receivableBalanceEndPeriod", hidden: false, width: 100},
						{title: "应收医保款回款率", field: "receivableCollectionRate", hidden: false, width: 100},
						{title: "医保坏账核销金额（万元）", field: "insuranceBadDebtAmt", hidden: false, width: 100}

				]
			],
			onLoadSuccess: function (data) {
				$('.s-op-edit').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];

					$pop.iframePop({
						title: '医保政策填报',//标题
						content: '${base}/ui/service/biz/amcs/fin/finInsMain/reportTabs?mainId='+row.id,//请求地址
						area: ['100%', '100%'],//窗口大小
						// postData : {mainId:row.id},//往子页面传值
						end : function(iframeSendData){
							//关闭执行函数，子页面可通过 $pop.closePop 返回参数
							$grid.load('#gridBox',{hospId:'${hospId}',monthId:'${monthId}'});
						},
						sureback : function (iframeSendData){

							//表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
						}
					},'#gridBox');//刷新grid #gridBox

					// formPop = $pop.popTemForm({
					// 	title: '编辑',
					// 	temId: 'formTem',
					// 	data: row,
					// 	// area: ['800px', '350px'],
					// 	onPop: function () {
					// 	}
					// });
				});
			},
			url: "${base}/ui/service/biz/amcs/fin/finInsMain/getList",
		});

		window.submitEditForm = function (opt, $form, data) {
			//window.console&&console.log(opt,$form,data);
			$ajax.post({
				url: '${base}/ui/service/biz/amcs/fin/finInsMain/save',
				data: data,
				tip: '你确定提交吗？',
				success: function (rst) {
					$pop.close(formPop);
					$('#gridBox').datagrid('reload');
				}
			});
			return false;
		}

	});
</script>

</html>

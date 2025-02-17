<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>医保系统相关人员表管理 - 爱尔医院</title>
	[#include '/WEB-INF/views/common/include_resources.ftl']
</head>
<body>
<div class="searchHead">
	<form id="sbox" class="soform form-enter">
			<label class="lab-inline">省区：</label>
			<select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true">
			</select>
			<label class="lab-inline">医院：</label>
			<select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
			</select>
			<button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
			<button type="button" class="btn btn-small btn-primary s-export">导 出</button>
	</form>
</div>
<div class="cont-grid">
	<div id="gridBox"></div>
</div>
</body>
[#include '/WEB-INF/views/common/include_js.ftl']
<script id="formTem" type="text/html">

</script>
<script type="text/javascript">
	requirejs(["pub"], function () {
		this.reloadEventData();
		var formPop = null;

		$grid.newGrid("#gridBox", {
			tools: [
				[]
			],
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			ctrlSelect: true,
			pagination:false,
			fitColumns: false,
			columns: [
				[
						{title: "ID", field: "id", hidden: true, width: 100},
						{title: "省区", field:"hospParent", hidden: false, width:80},
						{title: "医院", field: "hospName", hidden: false, width: 120},
						{title: "医院类型", field:"investNature", hidden: false, width:80,formatter:function (v, row, index) {
							if(v=='10'){return '上市';}else if(v=='11'){return '合伙';}else{return '';}
						}},
					    {title: "医院等级", field:'ehrLevel', hidden: false, width:80},
						{title: "修改人", field: "modiferName", hidden: false, width: 100},
						{title: "修改时间", field: "modifyDate", hidden: false, width: 100},
						{title: "财务负责人", field: "finPerInCharge", hidden: false, width: 100},
						{title: "职务", field: "posi", hidden: false, width: 100},
						{title: "联系方式", field: "contInfo", hidden: false, width: 100},
						{title: "医保负责人", field: "medInsPerInCharge", hidden: false, width: 100},
						{title: "医保联系方式", field: "insContInfo", hidden: false, width: 100},
						{title: "医保专员1", field: "medInsSpe1", hidden: false, width: 100},
						{title: "医保专员1联系方式", field: "spe1ContInfo", hidden: false, width: 100},
						{title: "医保专员2", field: "medInsSpe2", hidden: false, width: 100},
						{title: "医保专员2联系方式", field: "spe2ContInfo", hidden: false, width: 100},
						{title: "医保专员3", field: "medInsSpe3", hidden: false, width: 100},
						{title: "医保专员3联系方式", field: "spe3ContInfo", hidden: false, width: 100},
						{title: "价格负责人", field: "priPerInCharge", hidden: false, width: 100},
						{title: "价格联系方式", field: "priContInfo", hidden: false, width: 100},
						{title: "价格专干1", field: "priSpe1", hidden: false, width: 100},
						{title: "价格专干1联系方式", field: "spe1PriContInfo", hidden: false, width: 100},
						{title: "价格专干2", field: "priSpe2", hidden: false, width: 100},
						{title: "价格专干2联系方式", field: "spe2PriContInfo", hidden: false, width: 100},
						{title: "价格专干3", field: "priSpe3", hidden: false, width: 100},
						{title: "价格专干3联系方式", field: "spe3PriContInfo", hidden: false, width: 100}

				]
			],
			onLoadSuccess: function (data) {
				$('.s-op-edit').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];

					formPop = $pop.popTemForm({
						title: '编辑',
						temId: 'formTem',
						data: row,
						area: ['800px', '350px'],
						onPop: function () {
						}
					});
				});
			},
			url: "${base}/ui/amcs/fin/config/person/list",
		});

		$(".s-export").click(function ($e) {
			var formData = $('#sbox').sovals();
			$pop.confirm('您确认想要导出记录吗？',function(r){
				if (r){
					var url = '${base}/ui/amcs/fin/config/person/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
					downloadExcelProcess(url);
				}
			});
		});

		// window.submitEditForm = function (opt, $form, data) {
		// 	//window.console&&console.log(opt,$form,data);
		// 	$ajax.post({
		// 		url: '/ui/service/biz/amcs/fin/person/save',
		// 		data: data,
		// 		tip: '你确定提交吗？',
		// 		success: function (rst) {
		// 			$pop.close(formPop);
		// 			$('#gridBox').datagrid('reload');
		// 		}
		// 	});
		// 	return false;
		// }

	});

	[#include "/WEB-INF/views/amcs/fin/business/subReport/finExport_js.ftl"]

	function reloadEventData() {
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
	};
</script>

</html>

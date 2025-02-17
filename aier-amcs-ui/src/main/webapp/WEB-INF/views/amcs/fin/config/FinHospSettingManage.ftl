<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>财务医院设置管理 - 爱尔医院</title>
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
		<input class="hide" type="text" name="id" id="id">
		<input class="hide" type="text" name="hospId" id="hospId"  value="${hospId}"/>

		<div class="row">



		
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">医保统筹区</label>
					<input class="txt txt-validate" type="text" name="poolingArea" id="poolingArea" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">医保类别</label>
					[@ui_select id="insuranceType" name="insuranceType" tag="@fin@INSURANCE_TYPE" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value=""/]
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">启用标识</label>
					<input class="easyui-checkbox" type="checkbox"  id="s-usingSign"  checked   >
					<input class="txt txt-validate  hidden" type="text" name="usingSign" id="usingSign"  value="1"/>
				</div>
			</div>

		</div>
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
			tools: [
				[{
					iconCls: "plus_sign",
					text: "新增",
					title: "新增",
					click: function () {
						formPop = $pop.popTemForm({
							title: '新增',
							temId: 'formTem',
							area: ['800px', '350px'],
							onPop:function (){
								$("#s-usingSign").click(function (e){
									if(e.target.checked){
										$("#usingSign").val(1)
									}else{
										$("#usingSign").val(0)
									}
								})
							}
						});
					}
				}]
			],
			queryParams:{hospId:'${hospId}'},
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
							return '<span class="s-op s-op-edit icon-edit" title="编辑" rel="' + index + '"></span>';
						}
					},
						{title: "主键ID", field: "id", hidden: true, width: 100},
						{title: "医院ID", field: "hospId", hidden: true, width: 100},
						{title: "医保统筹区", field: "poolingArea", hidden: false, width: 100},
						{title: "医保类别", field: "typeName", hidden: false, width: 100},
						{title: "启用标识", field: "usingSign", hidden: false, width: 100,formatter(v,row,index){
							if (v){
								return "是"
							}else{
								return "否"
							}
							}},
						{title: "修改人", field: "modiferName", hidden: false, width: 100},
						{title: "修改日期", field: "modifyDate", hidden: false, width: 150}

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
							if(!row.usingSign){
								$("#s-usingSign").removeAttr("checked")
							}
							$("#s-usingSign").click(function (e){
								if(e.target.checked){
									$("#usingSign").val(1)
								}else{
									$("#usingSign").val(0)
								}
							})
						}
					});
				});
			},
			url: "${base}/ui/service/biz/amcs/fin/finHospSetting/getList",
		});

		window.submitEditForm = function (opt, $form, data) {
			//window.console&&console.log(opt,$form,data);
			$ajax.post({
				url: '${base}/ui/service/biz/amcs/fin/finHospSetting/save',
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

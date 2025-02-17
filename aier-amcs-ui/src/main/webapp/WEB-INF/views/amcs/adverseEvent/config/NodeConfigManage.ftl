<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>不良事件节点配置表管理 - 爱尔医院</title>
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
		<input class="hide" type="text" name="nodeValue" id="nodeValue">


		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">事件编码</label>
					<input class="txt txt-validate" type="text" name="eventCode" id="eventCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">事件类型</label>
					<input class="txt txt-validate" type="text" name="eventType" id="eventType" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">事件名</label>
					<input class="txt txt-validate" type="text" name="eventName" id="eventName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>



		</div>
		<div class="row">
			<div class="p12">
				<div class="item-one">
					<label class="lab-item">事件URL</label>
					<input class="txt txt-validate" type="text" name="eventUrl" id="eventUrl" validType="maxlength[100]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			[#list Nodes as item]
			<div class="p2">
				<div class="item-one solab-s">
					<label class="lab-item solab-s">${item.getEnumDesc()}：</label>
					<input class="itemvalue" itemvalue="${item.getEnumCode()}" type="checkbox" name="${item}" value="1" checked >
				</div>
			</div>
			[/#list]
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
							area: ['800px', '350px']
						});
					}
				}]
			],
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			pagination:false,

			ctrlSelect: true,
			fitColumns: false,
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
						{title: "ID", field: "id", hidden: false, width: 100,hidden:true},
					{title: "事件编码", field: "eventCode", hidden: false, width: 100},
						{title: "事件类型", field: "eventType", hidden: false, width: 100},
					    {title: "事件名称", field: "eventName", hidden: false, width: 160},
						// {title: "事件值", field: "nodeValue", hidden: false, width: 60},
						// {title: "创建者ID", field: "creator", hidden: false, width: 100},
						// {title: "创建时间", field: "createDate", hidden: false, width: 100},
						// {title: "修改者ID", field: "modifer", hidden: false, width: 100},
						// {title: "修改时间", field: "modifyDate", hidden: false, width: 100},
						[#list Nodes as item]
						{title: "${item.getEnumDesc()}", field: "${item}", hidden: false, width: 60,formatter(v,row,index){
							if(v){
								return "✔️"
							}else{
								return "❌"
							}
							}},
						[/#list]
					{title: "事件URL", field: "eventUrl", hidden: false, width: 300},

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
			url: "${base}/ui/amcs/adverse/eventConfig/getAll",
		});

		window.submitEditForm = function (opt, $form, data) {
			//window.console&&console.log(opt,$form,data);
			var a=0;
			$(".itemvalue").each(function(o){
				if ($(this).prop("checked")){
					a+= parseInt($(this).attr('itemvalue'));
				}
				})
			data.nodeValue=a;
			$ajax.post({
				url: '${base}/ui/amcs/adverse/eventConfig/save',
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

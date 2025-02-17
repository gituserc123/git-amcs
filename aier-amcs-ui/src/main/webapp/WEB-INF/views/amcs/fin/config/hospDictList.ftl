<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>财务字典配置 - 爱尔医院</title>
	[#include '/WEB-INF/views/common/include_resources.ftl']
</head>
<body>
<div class="searchHead">
	<form id="sbox" class="soform form-enter">

		<label class="lab-inline" >字典类型：</label>
		<select class="drop easyui-combobox txt-validate required"  style="width: 100px" type="text" name="paraType" id="paraType"  noNull="必填">
			<option value="POOLING_AREA" selected>医保统筹区</option>
			[#--						<option value="INSURANCE_TYPE">医保类型</option>--]
		</select>

		<button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
	</form>
</div>
<div class="cont-grid">
	<div id="gridBox"></div>
</div>
</body>
[#include '/WEB-INF/views/common/include_js.ftl']
<script id="formTem" type="text/html">
	<form id="infoForm" class="soform form-validate pad-t20 pad-r20 form-enter"
		  data-opt="{beforeCallback:'submitEditForm'}" autocomplete="off">
		<input class="hide" type="text" name="id" id="id">
		<input class="hide" type="text" name="typeDesc" id="typeDesc" value="医保统筹区"/>
		<input class="hide" type="text" name="hospId" id="hospId"  value="${hospId!}"/>
		<div class="row">
			<div class="p12">
				<div class="item-one">
					<label class="lab-item">字典类型：</label>
					<select class="drop easyui-combobox txt-validate required"  style="width: 100%" type="text" name="typeCode" id="typeCode" data-options="onChange:function(){
					$('#typeDesc').setVal($('#typeCode option:selected').text())
			}" noNull="必填">
						<option value="POOLING_AREA" selected>医保统筹区</option>
[#--						<option value="INSURANCE_TYPE">医保类型</option>--]
					</select>
				</div>

			</div>

		</div>
		<div class="row">

[#--			<div class="p3">--]
[#--				<div class="item-one">--]
[#--					<label class="lab-item">类型描述</label>--]
[#--					<input class="txt txt-validate" type="text" name="typeDesc" id="typeDesc" validType="maxlength[50]" noNull="必填" value=""/>--]
[#--				</div>--]
[#--			</div>--]
		
		
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">值编码</label>
					<input class="txt txt-validate" type="text" name="valueCode" id="valueCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">值描述</label>
					<input class="txt txt-validate" type="text" name="valueDesc" id="valueDesc" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>




		</div>
		<div class="row">
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">拼音码</label>
					<input class="txt txt-validate" type="text" name="firstSpell" id="firstSpell" placeholder="留空系统生成" value=""/>
				</div>
			</div>
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">排序</label>
					<input class="txt txt-validate" type="text" name="orders" id="order"  value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">启用标识</label>
					<input class="easyui-checkbox" type="checkbox"  id="s-usingSign"  checked   >


					<input class="txt txt-validate hidden" type="text" name="usingSign" id="usingSign"  value="1"/>
				</div>
			</div>
		</div>

[#--		<div class="row">--]
[#--			<div class="p12">--]
[#--				<div class="item-one">--]
[#--					<label class="lab-item">事件URL</label>--]
[#--					<input class="txt txt-validate" type="text" name="eventUrl" id="eventUrl" validType="maxlength[100]" noNull="必填" value=""/>--]
[#--				</div>--]
[#--			</div>--]
[#--		</div>--]

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
							onPop:function () {
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
				}
				]
			],
			queryParams: {"paraType":$("#paraType").getVal(),"filter":""},
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
							htmlstr='<span class="s-op s-op-edit icon-edit" title="编辑" rel="' + index + '"></span>';
							htmlstr+='&nbsp;&nbsp;<span class="s-op s-op-del icon-del" title="删除" rel="' + index + '"></span>';
							return htmlstr;
						}
					},
						{title: "ID", field: "id", hidden: false, width: 100,hidden:true},
					{title: "类型", field: "typeDesc", hidden: false, width: 150},
					{title: "描述", field: "valueDesc", hidden: false, width: 150},
					{title: "拼音码", field: "firstSpell", hidden: false, width: 150},
						{title: "编码", field: "valueCode", hidden: false, width: 100},
					{title: "排序", field: "orders", hidden: false, width: 160},
					{title: "启用标识", field: "usingSign", hidden: false, width: 160},
						// {title: "事件值", field: "nodeValue", hidden: false, width: 60},
						// {title: "创建者ID", field: "creator", hidden: false, width: 100},
						// {title: "创建时间", field: "createDate", hidden: false, width: 100},
						// {title: "修改者ID", field: "modifer", hidden: false, width: 100},
						// {title: "修改时间", field: "modifyDate", hidden: false, width: 100},

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
							// $("#typeCode").val($("#paraType").getVal());
						}
					});
				});
				$('.s-op-del').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];

					$ajax.post({
						url: '${base}/ui/amcs/fin/dict/del',
						data: {id:row["id"]},

						tip: '你确定删除吗？',
						success: function (rst) {
							$('#gridBox').datagrid('reload');
						}
					});
				});
			},
			url: "${base}/ui/amcs/fin/dict/getList",
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
				url: '${base}/ui/amcs/fin/dict/save',
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

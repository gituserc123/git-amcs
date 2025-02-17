<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>传染病参数 - 爱尔医院</title>
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
			<input class="hide" type="text" name="hospId" id="hospId">
			<input class="hide" type="text" name="usingSign" id="usingSign">
			<input class="hide" type="text" name="typeCode" id="typeCode">


		<div class="row">   
			
		 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">账号</label>
					<input class="txt txt-validate   " type="text" name="valueDesc" id="valueDesc" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div> 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">账号密码</label>
					<input class="txt txt-validate  " type="text" name="remark" id="remark" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div> 
				<div class="p4">
				<div class="item-one">
					<label class="lab-item">录入人证件号</label>
					<input class="txt txt-validate  " type="text" name="typeDesc" id="typeDesc" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div> 
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">授权码</label>
					<input class="txt txt-validate  " type="text" name="valueEnglish" id="valueEnglish" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>  
			<!--div class="p4">
				<div class="item-one">
					<label class="lab-item">启停标识（1启用0停用）</label>
					<input class="txt txt-validate  " type="text" name="usingSign" id="usingSign" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div-->
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">报告单位编码</label>
					<input class="txt txt-validate   " type="text" name="leveles" id="leveles" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">报告单位名称</label>
					<input class="txt txt-validate   " type="text" name="valueCode" id="valueCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div> 
				<div class="item-one">
					<label class="lab-item">上传地址</label>
					<input class="txt txt-validate " type="text" name="valueRemark" id="valueRemark" validType="maxlength[200]" noNull="必填" value=""/>
				</div>
		 		<div class="item-one">
					<label class="lab-item">查询地址</label>
					<input class="txt txt-validate " type="text" name="url" id="url" validType="maxlength[200]" noNull="必填" value=""/>
				</div>
			 
		</div>
		<p class="row-btn center">
		 [@shiro.hasPermission name = "AmcsIdrDict:save"]
			<input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>
		[/@shiro.hasPermission] 
			<input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
		</p>
	</form>
</script>
<script type="text/javascript">
	requirejs(["pub"], function () {
		var formPop = null;
	//$("#firstcase").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNoEnum"});
	
		$grid.newGrid("#gridBox", {
			 
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
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
						{title: "ID", field: "id", hidden: true, width: 100},
						{title: "字典类型编码", field: "typeCode", hidden: false, width: 100},
						{title: "录入人证件号", field: "typeDesc", hidden: false, width: 100}, 
						{title: "报告单位编码", field: "leveles", hidden: false, width: 100},
						{title: "报告单位名称", field: "valueCode", hidden: false, width: 100},
						{title: "账号", field: "valueDesc", hidden: false, width: 100},
						{title: "账号密码", field: "remark", hidden: false, width: 100},
					 
						{title: "授权码", field: "valueEnglish", hidden: false, width: 100},
						{title: "医院ID", field: "hospId", hidden: false, width: 100},
						{title: "上传地址", field: "valueRemark", hidden: false, width: 300},
						{title: "查询地址", field: "url", hidden: false, width: 300},
					   {title: "修改者ID", field: "modifer", hidden: false, width: 100},
						{title: "修改时间", field: "modifyDate", hidden: false, width: 100}
 	

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
						area: ['800px', '250px'],
						onPop: function () {
						}
					});
				});
			},
			url: "${base}/ui/amcs/idr/amcsIdrDictType/getAmcsIdrDictType?typeCode=token&usingSign=1",
		});

		window.submitEditForm = function (opt, $form, data) {
			//window.console&&console.log(opt,$form,data);
			   $ajax.post('${base}/ui/amcs/idr/amcsIdrDictType/save',JSON.stringify(data),"您确定要保存吗?",true).done(function (rst) {
     	   		if(rst.code==='200'||rst.code==='201'){ 
					$pop.close(formPop);
					$('#gridBox').datagrid('reload');
				}
			});
			return false;
		}

	});
</script>

</html>

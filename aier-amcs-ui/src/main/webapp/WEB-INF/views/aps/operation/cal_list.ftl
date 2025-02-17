<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>人资手术计算结果表管理 - 爱尔医院</title>
	[#include '/WEB-INF/views/common/include_resources.ftl']
</head>
<body>

<div class="searchHead">
	<form id="sbox" class="soform form-enter">
		<label class="lab-inline">医院:</label>
		<select class="easyui-combogrid inline" id="hospList" name="hospId" style="width:220px;" required
				data-options="
                            clearIcon: true,
                            delay: 500,    	  // 输入时延迟查询毫秒数
                            panelHeight:'auto',
                            panelMaxHeight:182,
                            mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
                            panelWidth:360,
                            fitColumns:true,
                            showHeader: false,
                            idField:'hospId',
                            textField:'name',
                            limitToList : true,
                            reversed : true,
                            //required : true,
                            url:'${base}/ui/aps/dcgOprCalResult/getHospByPlat',
                            queryParams: {
                            },
                            onBeforeLoad: function(param){
								param.key = param.q;
								if(!param.key){
									return false
								}
							},onSelect:function(record){
                                console.log(record)
                                console.log(  $('#hospList').combobox('getValue' ) );
                                console.log(  $('#hospList').combobox('getText' ) );
                            },

                            columns:[[
                                {field:'hospId',title:'医院id',width:'50px'},
                                {field:'name',title:'医院名称',width:'400px'},
                            ]]">
		</select>

		<label class="lab-inline">患者:</label>
		<input class="txt inline w-200" easyui-combogrid" type="text" name="patientName" id="operationCode"/>
		<label class="lab-inline">结算日期：</label>
		<input type="text" id="oprDate" class="txt so-rangeDate w-180" name="oprDate" data-opt="{val:'month',opens:'left'}">
		<button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
		[#--<button type="button" class="btn btn-small btn-primary btn-cal"  data-opt="{grid:'#gridBox',scope:'#sbox'}">导出</button>--]

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

		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">id</label>
					<input class="txt txt-validate" type="text" name="id" id="id" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">医院id</label>
					<input class="txt txt-validate" type="text" name="hospId" id="hospId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">修改人</label>
					<input class="txt txt-validate" type="text" name="modifer" id="modifer" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">修改日期</label>
					<input class="txt txt-validate" type="text" name="modifyDate" id="modifyDate" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">患者id</label>
					<input class="txt txt-validate" type="text" name="patientId" id="patientId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">患者姓名</label>
					<input class="txt txt-validate" type="text" name="patientName" id="patientName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">眼别</label>
					<input class="txt txt-validate" type="text" name="eyeTypeName" id="eyeTypeName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术开始日期</label>
					<input class="txt txt-validate" type="text" name="oprStartDate" id="oprStartDate" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">叠加手术code</label>
					<input class="txt txt-validate" type="text" name="mergeOprCode" id="mergeOprCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">叠加手术名称</label>
					<input class="txt txt-validate" type="text" name="mergeOprName" id="mergeOprName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算后绩效术式</label>
					<input class="txt txt-validate" type="text" name="calOprShushi" id="calOprShushi" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算后手术等级</label>
					<input class="txt txt-validate" type="text" name="calOprGrade" id="calOprGrade" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算后护理等级</label>
					<input class="txt txt-validate" type="text" name="calNurGrade" id="calNurGrade" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算时间</label>
					<input class="txt txt-validate" type="text" name="calTime" id="calTime" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算备注</label>
					<input class="txt txt-validate" type="text" name="calRemarks" id="calRemarks" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算结果</label>
					<input class="txt txt-validate" type="text" name="calResult" id="calResult" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">组合手术分类</label>
					<input class="txt txt-validate" type="text" name="zhOprClassify" id="zhOprClassify" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">组合手术分类名称</label>
					<input class="txt txt-validate" type="text" name="zhOprClassifyName" id="zhOprClassifyName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">组合优先级</label>
					<input class="txt txt-validate" type="text" name="zhPriority" id="zhPriority" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">组合病种code</label>
					<input class="txt txt-validate" type="text" name="zhDiseaseCode" id="zhDiseaseCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">组合病种名称</label>
					<input class="txt txt-validate" type="text" name="zhDiseaseName" id="zhDiseaseName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术收入</label>
					<input class="txt txt-validate" type="text" name="oprPrice" id="oprPrice" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">药物收入</label>
					<input class="txt txt-validate" type="text" name="dragPrice" id="dragPrice" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">治疗收入</label>
					<input class="txt txt-validate" type="text" name="treatPrice" id="treatPrice" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">总收入</label>
					<input class="txt txt-validate" type="text" name="totalPrice" id="totalPrice" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">麻醉方式名称</label>
					<input class="txt txt-validate" type="text" name="anesthesiaName" id="anesthesiaName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术医生id</label>
					<input class="txt txt-validate" type="text" name="surgeonId" id="surgeonId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术医生姓名</label>
					<input class="txt txt-validate" type="text" name="surgeonName" id="surgeonName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术科室id</label>
					<input class="txt txt-validate" type="text" name="surgeonDeptId" id="surgeonDeptId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">中间key </label>
					<input class="txt txt-validate" type="text" name="tempKey" id="tempKey" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">中间优先级</label>
					<input class="txt txt-validate" type="text" name="tempPriority" id="tempPriority" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">中间值</label>
					<input class="txt txt-validate" type="text" name="tempValue" id="tempValue" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">错误值  0时为正常，>0 时异常 </label>
					<input class="txt txt-validate" type="text" name="errorCode" id="errorCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算后 病种名称</label>
					<input class="txt txt-validate" type="text" name="calDiseaseName" id="calDiseaseName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术科室名称</label>
					<input class="txt txt-validate" type="text" name="surgeonDeptName" id="surgeonDeptName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">结算日期</label>
					<input class="txt txt-validate" type="text" name="transDate" id="transDate" validType="maxlength[50]" noNull="必填" value=""/>
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
					iconCls: "download_alt",
					text: "导出",
					title: "导出",
					click: function () {
						var infoRows = $('#gridBox').datagrid('getRows');
						if(infoRows.length <= 0){
							alert("记录为0 无法导出，请先查询数据");
						}else{
							var sData = $('#sbox').sovals();
							$util.down({
								url: "${base}/ui/aps/dcgOprCalResult/exportExcel",
								data: sData
							});
						}

					}
				}]
			],
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			ctrlSelect: true,
			fitColumns: false,
			columns: [
				[
					// {
					// 	title: "操作",
					// 	field: "op",
					// 	width: 60,
					// 	formatter: function (v, row, index) {
					// 		return '<span class="s-op s-op-edit icon-edit" title="编辑" rel="' + index + '"></span>';
					// 	}
					// },
						// {title: "id", field: "id", hidden: false, width: 100},
						{title: "医院id", field: "hospId", hidden: false, width: 60},
						// {title: "修改人", field: "modifer", hidden: false, width: 100},
						// {title: "修改日期", field: "modifyDate", hidden: false, width: 100},
						{title: "患者id", field: "patientId", hidden: false, width: 100},
						{title: "患者姓名", field: "patientName", hidden: false, width: 100},
						{title: "眼别", field: "eyeTypeName", hidden: false, width: 50},
						{title: "手术开始日期", field: "oprStartDate", hidden: false, width: 100},
						{title: "错误值", field: "errorCode", hidden: false, width: 30},
						{title: "叠加手术code", field: "mergeOprCode", hidden: false, width: 100},
						{title: "叠加手术名称", field: "mergeOprName", hidden: false, width: 100},
						{title: "计算后绩效术式", field: "calOprShushi", hidden: false, width: 100},
						{title: "优先级", field: "tempPriority", hidden: false, width: 50},
						{title: "手术等级", field: "calOprGrade", hidden: false, width: 30},
						{title: "护理等级", field: "calNurGrade", hidden: false, width: 30},
						{title: "计算时间", field: "calTime", hidden: false, width: 100},
						{title: "计算备注", field: "calRemarks", hidden: false, width: 100},
						{title: "计算结果", field: "calResult", hidden: false, width: 100},
						{title: "组合手术分类", field: "zhOprClassify", hidden: false, width: 100},
						{title: "组合手术分类名称", field: "zhOprClassifyName", hidden: false, width: 100},
						{title: "组合优先级", field: "zhPriority", hidden: false, width: 100},
						// {title: "组合病种code", field: "zhDiseaseCode", hidden: false, width: 100},
						// {title: "组合病种名称", field: "zhDiseaseName", hidden: false, width: 100},
						{title: "手术收入", field: "oprPrice", hidden: false, width: 100},
                        {title: "手术应收", field: "oprAr", hidden: false, width: 100},
						// {title: "药物收入", field: "dragPrice", hidden: false, width: 100},
						// {title: "治疗收入", field: "treatPrice", hidden: false, width: 100},
						// {title: "总收入", field: "totalPrice", hidden: false, width: 100},
						{title: "麻醉方式", field: "anesthesiaName", hidden: false, width: 100},
						// {title: "手术医生id", field: "surgeonId", hidden: false, width: 100},
						{title: "手术医生", field: "surgeonName", hidden: false, width: 100},
						// {title: "手术科室id", field: "surgeonDeptId", hidden: false, width: 100},
						{title: "中间key ", field: "tempKey", hidden: false, width: 100},
						{title: "中间值", field: "tempValue", hidden: false, width: 100},
						{title: "计算后病种名称", field: "calDiseaseName", hidden: false, width: 100},
                    {title: "病种code", field: "diseaseCode", hidden: false, width: 100},
                    {title: "晶体材料code", field: "lensCode", hidden: false, width: 100},
                    {title: "晶体材料名称", field: "lensName", hidden: false, width: 100},
                    // {title: "功能性晶体标识", field: "funcLensTag", hidden: false, width: 100},
                    {title: "晶体材料规格", field: "lensSpecif", hidden: false, width: 100},
                    {title: "第1助手ID", field: "asst1Id", hidden: false, width: 100},
                    {title: "第1助手姓名", field: "asst1Name", hidden: false, width: 100},
                    {title: "第2助手ID", field: "asst2Id", hidden: false, width: 100},
                    {title: "第2助手姓名", field: "asst2Name", hidden: false, width: 100},
                    {title: "巡回护士1ID", field: "circuitNurseId1", hidden: false, width: 100},
                    {title: "巡回护士1姓名", field: "circuitNurseName1", hidden: false, width: 100},
                    {title: "巡回护士2ID", field: "circuitNurseId2", hidden: false, width: 100},
                    {title: "巡回护士2姓名", field: "circuitNurseName2", hidden: false, width: 100},
                    {title: "巡回护士3ID", field: "circuitNurseId3", hidden: false, width: 100},
                    {title: "巡回护士3姓名", field: "circuitNurseName3", hidden: false, width: 100},
                    {title: "器械护士1ID", field: "apparatusNurseId1", hidden: false, width: 100},
                    {title: "器械护士1姓名", field: "apparatusNurseName1", hidden: false, width: 100},
                    {title: "器械护士2ID", field: "apparatusNurseId2", hidden: false, width: 100},
                    {title: "器械护士2姓名", field: "apparatusNurseName2", hidden: false, width: 100},
                    {title: "器械护士3ID", field: "apparatusNurseId3", hidden: false, width: 100},
                    {title: "器械护士3姓名", field: "apparatusNurseName3", hidden: false, width: 100},
                    {title: "跟台护士1ID", field: "followNurseId1", hidden: false, width: 100},
                    {title: "跟台护士1姓名", field: "followNurseName1", hidden: false, width: 100},
                    {title: "跟台护士2ID", field: "followNurseId2", hidden: false, width: 100},
                    {title: "跟台护士2姓名", field: "followNurseName2", hidden: false, width: 100},
                    {title: "洗眼护士1ID", field: "eyeWashNurseId1", hidden: false, width: 100},
                    {title: "洗眼护士1姓名", field: "eyeWashNurseName1", hidden: false, width: 100},
                    {title: "洗眼护士2ID", field: "eyeWashNurseId2", hidden: false, width: 100},
                    {title: "洗眼护士2姓名", field: "eyeWashNurseName2", hidden: false, width: 100},
						// {title: "手术科室名称", field: "surgeonDeptName", hidden: false, width: 100},
						// {title: "结算日期", field: "transDate", hidden: false, width: 100}

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
			url: "${base}/ui/aps/dcgOprCalResult/page",
		});

		//--------导出数据--------------
		$('.btn-export').click(function () {
			$ajax.post({
				url: '${base}/ui/aps/dcgOprCalResult/exportExcel',
				dataType: 'json',
				contentType: 'application/json;charset=utf-8',
				type: 'POST',
				data: map,
				jsonData: true,
				callback: function (rst) {
					$pop.msg.success('重新计算任务已经提交,请稍后再查询结果', {time: 1500});
				}
			});

		});
		//---------重新计算按钮--------------
		$('.btn-cal').click(function () {

			debugger
			var hospId=$('#hospList').combobox('getValue' );
			var myText=$('#hospList').combobox('getText' );


			var oprDate = $("#oprDate").val();
			oprDate=oprDate.substr( 0,10 );
			if(hospId.length >0 && oprDate.length>8 ){
				alert("重新计算 "+myText + " \n结算日期为:"+ oprDate +" 的手术记录");
				var map = {
					hospId: hospId,
					oprDateBegin: oprDate
				};

				$ajax.post({
					url: '${base}/ui/aps/dcgOprCalResult/calOprData',
					dataType: 'json',
					contentType: 'application/json;charset=utf-8',
					type: 'POST',
					data: map,
					jsonData: true,
					callback: function (rst) {
						$pop.msg.success('重新计算任务已经提交,请稍后再查询结果', {time: 1500});
					}
				});
			}else{
				alert(" 请选择医院 和 起始时间 ");
			}

		});

		window.submitEditForm = function (opt, $form, data) {
			//window.console&&console.log(opt,$form,data);
			$ajax.post({
				url: '/ui/aps/dcgOprCalResult/save',
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

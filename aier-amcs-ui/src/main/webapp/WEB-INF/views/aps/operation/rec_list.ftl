<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>人资手术记录表管理 - 爱尔医院</title>
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
		<input type="text" class="txt so-rangeDate w-180" name="oprDate" data-opt="{val:'month',opens:'left'}">
		<button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
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
					<label class="lab-item">ID</label>
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
					<label class="lab-item">修改时间</label>
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
					<label class="lab-item">诊疗服务编码 T_DCH_MEDICAL_SERVICE</label>
					<input class="txt txt-validate" type="text" name="serviceCode" id="serviceCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">集团诊疗服务编码</label>
					<input class="txt txt-validate" type="text" name="groupServiceCode" id="groupServiceCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">诊疗服务名称</label>
					<input class="txt txt-validate" type="text" name="serviceName" id="serviceName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">集团财务分类编码</label>
					<input class="txt txt-validate" type="text" name="financeCode" id="financeCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术申请单号 HIS.T_OPR_APPLY J</label>
					<input class="txt txt-validate" type="text" name="applyNumber" id="applyNumber" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">1门诊2住院</label>
					<input class="txt txt-validate" type="text" name="oprType" id="oprType" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">眼别</label>
					<input class="txt txt-validate" type="text" name="eyeTypeName" id="eyeTypeName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">就诊号</label>
					<input class="txt txt-validate" type="text" name="visitNumber" id="visitNumber" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">申请科室id</label>
					<input class="txt txt-validate" type="text" name="applyDeptId" id="applyDeptId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">申请科室名称</label>
					<input class="txt txt-validate" type="text" name="applyDeptName" id="applyDeptName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">申请医生ID</label>
					<input class="txt txt-validate" type="text" name="applyDoctorId" id="applyDoctorId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">申请医生名称</label>
					<input class="txt txt-validate" type="text" name="applyDoctorName" id="applyDoctorName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">结算日期T_TRANS_OUTP_RECORD  结算表</label>
					<input class="txt txt-validate" type="text" name="transDate" id="transDate" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">结算单号</label>
					<input class="txt txt-validate" type="text" name="transNumber" id="transNumber" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">结算金额</label>
					<input class="txt txt-validate" type="text" name="transAmount" id="transAmount" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">结算状态 （1正常 2冲红3已退费）</label>
					<input class="txt txt-validate" type="text" name="transState" id="transState" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">切口等级名称HIS.T_OPR_APPLY_RESULT F </label>
					<input class="txt txt-validate" type="text" name="woundHealingGradeName" id="woundHealingGradeName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">愈合类别名称</label>
					<input class="txt txt-validate" type="text" name="healingCategoryName" id="healingCategoryName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">麻醉方式名称（码表：anesthesia_mode） </label>
					<input class="txt txt-validate" type="text" name="anesthesiaName" id="anesthesiaName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">麻醉医师ID</label>
					<input class="txt txt-validate" type="text" name="analgesistId" id="analgesistId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">麻醉医师姓名</label>
					<input class="txt txt-validate" type="text" name="analgesistName" id="analgesistName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术结果（1成功2失败）</label>
					<input class="txt txt-validate" type="text" name="oprResult" id="oprResult" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术医生ID</label>
					<input class="txt txt-validate" type="text" name="surgeonId" id="surgeonId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术医生姓名</label>
					<input class="txt txt-validate" type="text" name="surgeonName" id="surgeonName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术医生科室ID</label>
					<input class="txt txt-validate" type="text" name="surgeonDeptId" id="surgeonDeptId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术医生科室名称</label>
					<input class="txt txt-validate" type="text" name="surgeonDeptName" id="surgeonDeptName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">第一助手ID</label>
					<input class="txt txt-validate" type="text" name="asst1Id" id="asst1Id" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">第一助手姓名</label>
					<input class="txt txt-validate" type="text" name="asst1Name" id="asst1Name" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">第2助手ID</label>
					<input class="txt txt-validate" type="text" name="asst2Id" id="asst2Id" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">第2助手姓名</label>
					<input class="txt txt-validate" type="text" name="asst2Name" id="asst2Name" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">巡回护士1ID</label>
					<input class="txt txt-validate" type="text" name="circuitNurseId1" id="circuitNurseId1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">巡回护士1姓名</label>
					<input class="txt txt-validate" type="text" name="circuitNurseName1" id="circuitNurseName1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">巡回护士2ID</label>
					<input class="txt txt-validate" type="text" name="circuitNurseId2" id="circuitNurseId2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">巡回护士2姓名</label>
					<input class="txt txt-validate" type="text" name="circuitNurseName2" id="circuitNurseName2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">巡回护士3ID</label>
					<input class="txt txt-validate" type="text" name="circuitNurseId3" id="circuitNurseId3" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">巡回护士3姓名</label>
					<input class="txt txt-validate" type="text" name="circuitNurseName3" id="circuitNurseName3" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">器械护士1ID</label>
					<input class="txt txt-validate" type="text" name="apparatusNurseId1" id="apparatusNurseId1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">器械护士1姓名</label>
					<input class="txt txt-validate" type="text" name="apparatusNurseName1" id="apparatusNurseName1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">器械护士2ID</label>
					<input class="txt txt-validate" type="text" name="apparatusNurseId2" id="apparatusNurseId2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">器械护士2姓名</label>
					<input class="txt txt-validate" type="text" name="apparatusNurseName2" id="apparatusNurseName2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">器械护士3ID</label>
					<input class="txt txt-validate" type="text" name="apparatusNurseId3" id="apparatusNurseId3" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">器械护士3姓名</label>
					<input class="txt txt-validate" type="text" name="apparatusNurseName3" id="apparatusNurseName3" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">跟台护士1ID</label>
					<input class="txt txt-validate" type="text" name="followNurseId1" id="followNurseId1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">跟台护士1姓名</label>
					<input class="txt txt-validate" type="text" name="followNurseName1" id="followNurseName1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">跟台护士2ID</label>
					<input class="txt txt-validate" type="text" name="followNurseId2" id="followNurseId2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">跟台护士2姓名</label>
					<input class="txt txt-validate" type="text" name="followNurseName2" id="followNurseName2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">洗眼护士1ID</label>
					<input class="txt txt-validate" type="text" name="eyeWashNurseId1" id="eyeWashNurseId1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">洗眼护士1姓名</label>
					<input class="txt txt-validate" type="text" name="eyeWashNurseName1" id="eyeWashNurseName1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">洗眼护士2ID</label>
					<input class="txt txt-validate" type="text" name="eyeWashNurseId2" id="eyeWashNurseId2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">洗眼护士2姓名</label>
					<input class="txt txt-validate" type="text" name="eyeWashNurseName2" id="eyeWashNurseName2" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">实际手术开始时间</label>
					<input class="txt txt-validate" type="text" name="actualOprStartTime" id="actualOprStartTime" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">实际手术结束时间</label>
					<input class="txt txt-validate" type="text" name="actualOprEndTime" id="actualOprEndTime" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">诊断编码T_DCH_PATIENT_DIAGNOSTIC</label>
					<input class="txt txt-validate" type="text" name="diagCode" id="diagCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">诊断名称</label>
					<input class="txt txt-validate" type="text" name="diagName" id="diagName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">病种分类编码</label>
					<input class="txt txt-validate" type="text" name="diseaseCode" id="diseaseCode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">病种分类名称</label>
					<input class="txt txt-validate" type="text" name="diseaseName" id="diseaseName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">备注</label>
					<input class="txt txt-validate" type="text" name="remarks" id="remarks" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">计算结果</label>
					<input class="txt txt-validate" type="text" name="calResult" id="calResult" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术间id</label>
					<input class="txt txt-validate" type="text" name="oprRoomId" id="oprRoomId" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术间名称</label>
					<input class="txt txt-validate" type="text" name="oprRoomName" id="oprRoomName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">手术收入</label>
					<input class="txt txt-validate" type="text" name="oprIncome" id="oprIncome" validType="maxlength[50]" noNull="必填" value=""/>
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
					<label class="lab-item">患者性别</label>
					<input class="txt txt-validate" type="text" name="sexName" id="sexName" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p3">
				<div class="item-one">
					<label class="lab-item">患者手机号</label>
					<input class="txt txt-validate" type="text" name="tel1" id="tel1" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">患者身份证</label>
					<input class="txt txt-validate" type="text" name="idNumber" id="idNumber" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>


			<div class="p3">
				<div class="item-one">
					<label class="lab-item">患者年龄</label>
					<input class="txt txt-validate" type="text" name="patientAge" id="patientAge" validType="maxlength[50]" noNull="必填" value=""/>
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
			// tools: [
			// 	[{
			// 		iconCls: "plus_sign",
			// 		text: "新增",
			// 		title: "新增",
			// 		click: function () {
			// 			formPop = $pop.popTemForm({
			// 				title: '新增',
			// 				temId: 'formTem',
			// 				area: ['800px', '350px']
			// 			});
			// 		}
			// 	}]
			// ],
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
					// 	{title: "ID", field: "id", hidden: false, width: 100},
						{title: "医院id", field: "hospId", hidden: false, width: 60},

						// {title: "修改人", field: "modifer", hidden: false, width: 100},
						// {title: "修改时间", field: "modifyDate", hidden: false, width: 100},
						{title: "患者id", field: "patientId", hidden: false, width: 100},
						{title: "姓名", field: "patientName", hidden: false, width: 100},
						{title: "诊疗服务编码", field: "serviceCode", hidden: false, width: 100},
						// {title: "集团诊疗服务编码", field: "groupServiceCode", hidden: false, width: 100},
						{title: "诊疗服务名称", field: "serviceName", hidden: false, width: 100},
						// {title: "集团财务分类编码", field: "financeCode", hidden: false, width: 100},
						{title: "手术申请单", field: "applyNumber", hidden: false, width: 100},
						// {title: "1门诊2住院", field: "oprType", hidden: false, width: 100},
						{title: "眼别", field: "eyeTypeName", hidden: false, width: 40},
						{title: "就诊号", field: "visitNumber", hidden: false, width: 100},
						// {title: "申请科室id", field: "applyDeptId", hidden: false, width: 100},
						{title: "申请科室名称", field: "applyDeptName", hidden: false, width: 100},
						// {title: "申请医生ID", field: "applyDoctorId", hidden: false, width: 100},
						{title: "申请医生名称", field: "applyDoctorName", hidden: false, width: 100},
						{title: "结算日期", field: "transDate", hidden: false, width: 100},
						{title: "结算单号", field: "transNumber", hidden: false, width: 100},
						// {title: "结算金额", field: "transAmount", hidden: false, width: 100},
						{title: "结算状态:1正常2冲红3退费）", field: "transState", hidden: false, width: 30},
						// {title: "切口等级名称 ", field: "woundHealingGradeName", hidden: false, width: 100},
						// {title: "愈合类别名称", field: "healingCategoryName", hidden: false, width: 100},
						{title: "麻醉方式", field: "anesthesiaName", hidden: false, width: 100},
						// {title: "麻醉医师ID", field: "analgesistId", hidden: false, width: 100},
						{title: "麻醉医师姓名", field: "analgesistName", hidden: false, width: 100},
						{title: "手术结果", field: "oprResult", hidden: false, width: 30},
						// {title: "手术医生ID", field: "surgeonId", hidden: false, width: 100},
						{title: "手术医生姓名", field: "surgeonName", hidden: false, width: 100},
						// {title: "手术医生科室ID", field: "surgeonDeptId", hidden: false, width: 100},
						{title: "手术医生科室名称", field: "surgeonDeptName", hidden: false, width: 100},
						// {title: "第一助手ID", field: "asst1Id", hidden: false, width: 100},
						{title: "第一助手姓名", field: "asst1Name", hidden: false, width: 100},
						// {title: "第2助手ID", field: "asst2Id", hidden: false, width: 100},
						{title: "第2助手姓名", field: "asst2Name", hidden: false, width: 100},
						// {title: "巡回护士1ID", field: "circuitNurseId1", hidden: false, width: 100},
						{title: "巡回护士1姓名", field: "circuitNurseName1", hidden: false, width: 100},
						// {title: "巡回护士2ID", field: "circuitNurseId2", hidden: false, width: 100},
						{title: "巡回护士2姓名", field: "circuitNurseName2", hidden: false, width: 100},
						// {title: "巡回护士3ID", field: "circuitNurseId3", hidden: false, width: 100},
						{title: "巡回护士3姓名", field: "circuitNurseName3", hidden: false, width: 100},
						// {title: "器械护士1ID", field: "apparatusNurseId1", hidden: false, width: 100},
						{title: "器械护士1姓名", field: "apparatusNurseName1", hidden: false, width: 100},
						// {title: "器械护士2ID", field: "apparatusNurseId2", hidden: false, width: 100},
						{title: "器械护士2姓名", field: "apparatusNurseName2", hidden: false, width: 100},
						// {title: "器械护士3ID", field: "apparatusNurseId3", hidden: false, width: 100},
						{title: "器械护士3姓名", field: "apparatusNurseName3", hidden: false, width: 100},
						// {title: "跟台护士1ID", field: "followNurseId1", hidden: false, width: 100},
						{title: "跟台护士1姓名", field: "followNurseName1", hidden: false, width: 100},
						// {title: "跟台护士2ID", field: "followNurseId2", hidden: false, width: 100},
						{title: "跟台护士2姓名", field: "followNurseName2", hidden: false, width: 100},
						// {title: "洗眼护士1ID", field: "eyeWashNurseId1", hidden: false, width: 100},
						{title: "洗眼护士1姓名", field: "eyeWashNurseName1", hidden: false, width: 100},
						// {title: "洗眼护士2ID", field: "eyeWashNurseId2", hidden: false, width: 100},
						{title: "洗眼护士2姓名", field: "eyeWashNurseName2", hidden: false, width: 100},
						{title: "实际手术开始时间", field: "actualOprStartTime", hidden: false, width: 100},
						{title: "实际手术结束时间", field: "actualOprEndTime", hidden: false, width: 100},
						{title: "诊断编码", field: "diagCode", hidden: false, width: 100},
						{title: "诊断名称", field: "diagName", hidden: false, width: 100},
						{title: "病种分类编码", field: "diseaseCode", hidden: false, width: 100},
						{title: "病种分类名称", field: "diseaseName", hidden: false, width: 100},
						// {title: "备注", field: "remarks", hidden: false, width: 100},
						// {title: "计算结果", field: "calResult", hidden: false, width: 100},
						// {title: "手术间id", field: "oprRoomId", hidden: false, width: 100},
						{title: "手术间名称", field: "oprRoomName", hidden: false, width: 100},
						{title: "手术收入", field: "oprIncome", hidden: false, width: 100},

						{title: "性别", field: "sexName", hidden: false, width: 30},
						{title: "手机号", field: "tel1", hidden: false, width: 100},
						{title: "身份证", field: "idNumber", hidden: false, width: 100},
						{title: "年龄", field: "patientAge", hidden: false, width: 60}

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
			url: "${base}/ui/aps/dcgOprRecord/page",

		});

		window.submitEditForm = function (opt, $form, data) {
			//window.console&&console.log(opt,$form,data);
			$ajax.post({
				url: '/ui/based/dcgOprRecord/save',
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

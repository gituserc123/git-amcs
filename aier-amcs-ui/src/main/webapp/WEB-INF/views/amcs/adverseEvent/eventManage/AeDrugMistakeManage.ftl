<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>给药错误事件</title>
	[#include '/WEB-INF/views/common/include_resources.ftl']
	[#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
</head>

<body>

[#include "/WEB-INF/views/amcs/adverseEvent/common/tab.ftl"]
<div class="tabCont tabCont-0">
	<form class="soform formA form-validate pad-t10" id="outpForm">
		[#include "/WEB-INF/views/amcs/adverseEvent/common/upper.ftl"]
	</form>
	<form class="soform formA form-validate">

		<h2 class="h2-title-a">
			<span class="s-title">事件描述</span>
		</h2>
		<hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

		<div class="row">
			<div class="p3">
				<div class="item-one d-date-mistake hide">
					<label class="lab-item">医嘱给药频次：</label>
					[@ui_select  uiShowDefault=false id="docAdvDrugFreq" name="docAdvDrugFreq" tag="@ae@doc_adv_drug_freq"  style="width:50%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('4', v)>-1){
                                $('#docAdvDrugFreqRemark').show();
                            };
                            if($.inArray('4', v)==-1){
                                $('#docAdvDrugFreqRemark').hide();
                            }
                        })" filterkey="firstSpell"/]
					<textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="docAdvDrugFreqRemark" name="docAdvDrugFreqRemark" placeholder="请填写其他">${ae.docAdvDrugFreqRemark!}</textarea>
				</div>
			</div>
			<div class="p3">
				<div class="item-one solab-lb d-date-mistake hide">
					<label class="lab-item">医嘱给药时间：</label>
					<input id="docAdvDrugTime" class="txt txt-validate so-date" type="text" name="docAdvDrugTime" data-opt="required:true, maxDate:'${sysdate}',format:'yyyy-MM-dd HH:mm:ss',type:'date'" noNull="请填写日期"  value="${ae.docAdvDrugTime!}"  />

				</div>
			</div>
			<div class="p3">
				<div class="item-one solab-lb d-date-mistake hide">
					<label class="lab-item">错误给药时间：</label>
					<input id="mistakeDrugTime" class="txt txt-validate so-date" type="text" name="mistakeDrugTime" data-opt="editable:false,required:true, maxDate:'${sysdate}',format:'yyyy-MM-dd HH:mm:ss',type:'date'" noNull="请填写日期"  value="${ae.mistakeDrugTime!}"  />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p6 ">
				<div class="item-one d-mistake hide">
					<label class="lab-item">医嘱药物：</label>
					[@ui_select  uiShowDefault=false id="docAdvDrug" name="docAdvDrug" tag="@ae@doc_adv_drug"  style="width:100%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('5', v)>-1){
                                $('#docAdvDrugRemark').show();
                            };
                            if($.inArray('5', v)==-1){
                                $('#docAdvDrugRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
					<textarea class="txt txt-validate adaptiveTextarea" style="width:25%;" type="text" id="docAdvDrugRemark" name="docAdvDrugRemark" placeholder="请填写其他">${ae.docAdvDrugRemark!}</textarea>
				</div>
			</div>
			<div class="p6">
				<div class="item-one d-mistake hide">
					<label class="lab-item">错误药物：</label>
					[@ui_select  uiShowDefault=false id="mistakeDrug" name="mistakeDrug" tag="@ae@doc_adv_drug"  style="width:100%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('5', v)>-1){
                                $('#mistakeDrugRemark').show();
                            };
                            if($.inArray('5', v)==-1){
                                $('#mistakeDrugRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
					<textarea class="txt txt-validate adaptiveTextarea" style="width:25%;" type="text" id="mistakeDrugRemark" name="mistakeDrugRemark" placeholder="请填写其他">${ae.mistakeDrugRemark!}</textarea>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="p3">
				<div class="item-one d-way-mistake hide">
					<label class="lab-item">医嘱给药途径：</label>
					<input class="txt txt-validate patient-info " dataOptions="required:ture" noNull="医嘱给药途径必填" type="text" id="docAdvDrugWay" name="docAdvDrugWay" value="${ae.docAdvDrugWay!}" />
				</div>
			</div>
			<div class="p3">
				<div class="item-one d-way-mistake hide">
					<label class="lab-item">错误给药途径：</label>
					<input class="txt txt-validate patient-info " dataOptions="required:ture" noNull="错误给药途径必填" type="text" id="mistakeDrugWay" name="mistakeDrugWay" value="${ae.mistakeDrugWay!}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p3">
				<div class="item-one d-situation hide">
					<label class="lab-item">医嘱给药情况：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="医嘱给药情况必填" type="text" id="docAdvDrugSituation" name="docAdvDrugSituation" value="${ae.docAdvDrugSituation!}" />
				</div>
			</div>
			<div class="p3">
				<div class="item-one d-situation hide">
					<label class="lab-item">实际执行情况：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="实际执行情况必填" type="text" id="actualExecSituation" name="actualExecSituation" value="${ae.actualExecSituation!}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p3">
				<div class="item-one d-speed hide">
					<label class="lab-item">医嘱给药速度：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="医嘱给药速度必填" type="text" id="docAdvDrugSpeed" name="docAdvDrugSpeed" value="${ae.docAdvDrugSpeed!}" />
				</div>
			</div>
			<div class="p3">
				<div class="item-one d-speed hide">
					<label class="lab-item">实际给药速度：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="实际给药速度必填" type="text" id="actualDrugSpeed" name="actualDrugSpeed" value="${ae.actualDrugSpeed!}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p3">
				<div class="item-one d-dosage hide">
					<label class="lab-item">医嘱给药剂量：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="医嘱给药剂量必填" type="text" id="docAdvDrugDosage" name="docAdvDrugDosage" value="${ae.docAdvDrugDosage!}" />
				</div>
			</div>
			<div class="p3">
				<div class="item-one d-dosage hide">
					<label class="lab-item">实际给药剂量：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="实际给药剂量必填" type="text" id="actualDrugDosage" name="actualDrugDosage" value="${ae.actualDrugDosage!}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p3">
				<div class="item-one d-dosage-form hide">
					<label class="lab-item">医嘱给药剂型：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="医嘱给药剂型必填" type="text" id="docAdvDrugDosageForm" name="docAdvDrugDosageForm" value="${ae.docAdvDrugDosageForm!}" />
				</div>
			</div>
			<div class="p3">
				<div class="item-one d-dosage-form hide">
					<label class="lab-item">实际给药剂型：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="实际给药剂型必填" type="text" id="actualDrugDosageForm" name="actualDrugDosageForm" value="${ae.actualDrugDosageForm!}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p3">
				<div class="item-one solab-lc d-bedname hide">
					<label class="lab-item">医嘱给药床号及姓名：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="医嘱给药床号及姓名必填" type="text" id="docAdvDrugBedName" name="docAdvDrugBedName" value="${ae.docAdvDrugBedName!}" />
				</div>
			</div>
			<div class="p3">
				<div class="item-one solab-lc d-bedname hide">
					<label class="lab-item">实际给药床号及姓名：</label>
					<input class="txt txt-validate patient-info" dataOptions="required:ture" noNull="实际给药床号及姓名必填" type="text" id="actualDrugBedName" name="actualDrugBedName" value="${ae.actualDrugBedName!}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p6">
				<div class="item-one d-other hide">
					<label class="lab-item">其他：</label>
					<input class="txt txt-validate patient-info" type="text" id="eventOtherText" name="eventOtherText" validType="maxlength[80,'不能超过80个字符']" value="${ae.eventOtherText!}" />
				</div>
			</div>
			<div class="p6">
				<div class="item-one d-other hide">
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">药物名称：</label>
					<input class="txt txt-validate patient-info" noNull="药物名称必填" dataOptions="required:ture" type="text" id="drugName" name="drugName" value="${ae.drugName!}" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p12">
				<div class="item-one">
					<label class="lab-item">详细情况：</label>
					<textarea class="txt txt-validate adaptiveTextarea required"  style="width:1000px" cols="100" row="10" type="text" id="eventDesc"  noNull="请填写事件详细情况" name="eventDesc" placeholder="患者基本信息、主诉，入院时间，诊断，主要诊疗过程，何时何地发生什么？有什么后果？ 给了什么处理？预后如何？" value="">${ae.eventDesc!}</textarea>
				</div>
			</div>
		</div>
	</form>
	<form class="soform formA form-validate">
		[#include "/WEB-INF/views/amcs/adverseEvent/common/bottom.ftl"]
	</form>
</div>
<div class="tabCont tabCont-1 tabContHide tab-flow"></div>
<div class="tabCont tabCont-2 tabContHide tab-report"></div>
<div class="tabCont tabCont-3 tabContHide tab-img pad-t10"></div>
<div class="tabCont tabCont-4 tabContHide tab-review"></div>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
	requirejs(["eventReview", "eventTableGroup", "eventFlowchart", "uploadImages", "eventImage",'myupload', 'pub'], function (eventReview, eventTableGroup, eventFlowchart, uploadImages, eventImage) {
		//暂存前执行事件 false中断保存
		window.beforeStash = function(){
			return true;
		}
		//保存前执行事件 false中断保存
		window.beforeSubmit = function(){
			return true;
		}

		$('#gradeTwoB').combobox({
			onSelect: function(v){
				// 给药日期错误
				if(v.valueCode=='121'){
					$('.d-date-mistake').show();
					$('#docAdvDrugFreqRemark').css('height', '');
					$('#docAdvDrugTime').css('height', '');
					$('#mistakeDrugTime').css('height', '');
					$('#docAdvDrugFreq').combobox('enableValidation');
					$('#docAdvDrugTime').validatebox('enableValidation');
					$('#mistakeDrugTime').validatebox('enableValidation');
				}else{
					$('.d-date-mistake').hide();
					$('#docAdvDrugFreq').combobox('disableValidation');
					$('#docAdvDrugTime').validatebox('disableValidation');
					$('#mistakeDrugTime').validatebox('disableValidation');
				}
				// 药物错误
				if(v.valueCode=='122'){
					$('.d-mistake').show();
					$('#docAdvDrugRemark').css('height', '');
					$('#mistakeDrugRemark').css('height', '');
					$('#docAdvDrug').combobox('enableValidation');
					$('#mistakeDrug').combobox('enableValidation');
				}else{
					$('.d-mistake').hide();
					$('#docAdvDrug').combobox('disableValidation');
					$('#mistakeDrug').combobox('disableValidation');
				}
				// 给药途径错误
				if(v.valueCode=='123'){
					$('.d-way-mistake').show();
					$('#docAdvDrugWay').validatebox('enableValidation');
					$('#mistakeDrugWay').validatebox('enableValidation');
				}else{
					$('.d-way-mistake').hide();
					$('#docAdvDrugWay').validatebox('disableValidation');
					$('#mistakeDrugWay').validatebox('disableValidation');
				}
				// 遗漏给药
				if(v.valueCode=='124'){
					$('.d-situation').show();
					$('#docAdvDrugSituation').validatebox('enableValidation');
					$('#actualExecSituation').validatebox('enableValidation');
				}else{
					$('.d-situation').hide();
					$('#docAdvDrugSituation').validatebox('disableValidation');
					$('#actualExecSituation').validatebox('disableValidation');
				}
				// 输液速度错误
				if(v.valueCode=='125'){
					$('.d-speed').show();
					$('#docAdvDrugSpeed').validatebox('enableValidation');
					$('#actualDrugSpeed').validatebox('enableValidation');
				}else{
					$('.d-speed').hide();
					$('#docAdvDrugSpeed').validatebox('disableValidation');
					$('#actualDrugSpeed').validatebox('disableValidation');
				}
				// 剂量错误
				if(v.valueCode=='126'){
					$('.d-dosage').show();
					$('#docAdvDrugDosage').validatebox('enableValidation');
					$('#actualDrugDosage').validatebox('enableValidation');
				}else{
					$('.d-dosage').hide();
					$('#docAdvDrugDosage').validatebox('disableValidation');
					$('#actualDrugDosage').validatebox('disableValidation');
				}
				// 剂型错误
				if(v.valueCode=='127'){
					$('.d-dosage-form').show();
					$('#docAdvDrugDosageForm').validatebox('enableValidation');
					$('#actualDrugDosageForm').validatebox('enableValidation');
				}else{
					$('.d-dosage-form').hide();
					$('#docAdvDrugDosageForm').validatebox('disableValidation');
					$('#actualDrugDosageForm').validatebox('disableValidation');
				}
				// 患者错误
				if(v.valueCode=='128'){
					$('.d-bedname').show();
					$('#docAdvDrugBedName').validatebox('enableValidation');
					$('#actualDrugBedName').validatebox('enableValidation');
				}else{
					$('.d-bedname').hide();
					$('#docAdvDrugBedName').validatebox('disableValidation');
					$('#actualDrugBedName').validatebox('disableValidation');
				}
				// 其他
				if(v.valueCode=='129'){
					$('.d-other').show();
				}else{
					$('.d-other').hide();
				}
				// console.log('drugPage:');
				// console.log(v);
			}
		});

		[#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
		[#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
		[#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]


		var baseExam = {
			init : function (){
				if(eval('${ae.docAdvDrug}')==null){
					$('#docAdvDrugRemark').hide();
				}else{
					$('#docAdvDrug').combobox('setValue',eval('${ae.docAdvDrug}'));
					if($.inArray('5', eval('${ae.docAdvDrug}'))>-1 || eval('${ae.docAdvDrug}')=='5'){
						$('#docAdvDrugRemark').show();
					}else{
						$('#docAdvDrugRemark').hide();
					}
				}
				if(eval('${ae.mistakeDrug}')==null){
					$('#mistakeDrugRemark').hide();
				}else{
					$('#mistakeDrug').combobox('setValue',eval('${ae.mistakeDrug}'));
					if($.inArray('5', eval('${ae.mistakeDrug}'))>-1 || eval('${ae.mistakeDrug}')=='5'){
						$('#mistakeDrugRemark').show();
					}else{
						$('#mistakeDrugRemark').hide();
					}
				}
				if(eval('${ae.docAdvDrugFreq}')==null){
					$('#docAdvDrugFreqRemark').hide();
				}else{
					$('#docAdvDrugFreq').combobox('setValue',eval('${ae.docAdvDrugFreq}'));
					if(eval('${ae.docAdvDrugFreq}')=='4'){
						$('#docAdvDrugFreqRemark').show();
					}else{
						$('#docAdvDrugFreqRemark').hide();
					}
				}
			},

		};

		$(function () {
			//初始化表单
			baseExam.init();
			if($.isEmptyObject('${ae.id}') && !$.isEmptyObject('${ae.patientNo}')){
				queryPatientInfo('${ae.patientNo}');
			}

		});
		

	})
</script>

</body>

</html>

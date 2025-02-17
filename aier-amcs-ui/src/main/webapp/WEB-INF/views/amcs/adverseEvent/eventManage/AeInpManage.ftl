<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>住院患者不良事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
    <style type="text/css">
        .relative{position:relative;}
    </style>
</head>

<body>

    [#include "/WEB-INF/views/amcs/adverseEvent/common/tab.ftl"]
<div class="tabCont tabCont-0">
    <form class="soform formA form-validate pad-t10" id="outpForm">
            [#include "/WEB-INF/views/amcs/adverseEvent/common/upper.ftl"]
    </form>
  <form class="soform formA form-validate">

    <h2 class="h2-title-a">
            <span class="s-title">事件经过和疾病诊断</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">入院时间：</label>
                <input id="admissionDate" class="txt txt-validate so-date" type="text" style="width:100px" name="admissionDate" dataOptions="editable:false,required:ture, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写入院时间" value="[#if ae.admissionDate??]${ae.admissionDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            <span class="fl">
                <label class="lab-inline">出院时间：</label>
                <input id="dischargeDate" class="txt txt-validate so-date" type="text" style="width:100px" name="dischargeDate" value="[#if ae.dischargeDate??]${ae.dischargeDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            <span class="fl">
                <label class="lab-inline">治疗时间：</label>
                <input id="treatDate" class="txt txt-validate so-date" type="text" style="width:100px" name="treatDate" dataOptions="editable:false,required:false, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'"  value="[#if ae.treatDate??]${ae.treatDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            [#--<span class="fl">
                <label class="lab-inline">切口类别：</label>
				[@ui_select id="cutType"  class="easyui-combobox"  name="cutType"  tag="@ae@cut_type" style="width:100px;"  dataOptions="limitToList:true,required:true,reversed:true,editable:false" filterkey="firstSpell" value="${ae.cutType!}" /]
            </span>--]
            <span class="fl">
                <label class="lab-inline">非计划情况：</label>
				[@ui_select id="unplan"  class="easyui-combobox"  name="unplan"  tag="@ae@unplan" style="width:120px;"  dataOptions="limitToList:true,required:false,reversed:true,editable:false" filterkey="firstSpell" value="${ae.unplan!}" /]
            </span>
        </div>

        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">门诊诊断：</label>
                <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写入门诊断" style="width:1000px" id="outpDiagName" name="outpDiagName" maxlength="600" cols="200" row="10">${ae.outpDiagName!}</textarea>
            </span>
        </div>

        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">住院诊断：</label>
                <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写住院诊断" style="width:1000px" id="inpDiagName" name="inpDiagName" maxlength="600" cols="200" row="10">${ae.inpDiagName!}</textarea>
            </span>
        </div>

        <div style="font-size: 14px;display: inline-block;" class="mar-l30 mar-b10">
            <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
            入院视力
            <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 30px;color:#00A0E9">VOD</label>
                </span>
                <span class="fl w-80">
					<select class="drop drop-sl-k easyui-combobox" style="width:100%" data-Options="editable:false,required:true" name="admissionVodType" id="admissionVodType" value="${ae.admissionVodType!}">
						<option value="1" selected="selected">数值</option>
						<option value="2">描述</option>
					</select>
                </span>
                <span class="fl w-120">
					<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="admissionVod" name="admissionVod" value="${ae.admissionVod!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#admissionVodName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="admissionVodName" name="admissionVodName" value="${ae.admissionVodName!}"/>
                </span>
            </div>
            <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px; ">
				<span class="fl">
					<label class="lab-inline" style="width: 30px;color:#00A0E9">VOS</label>
				</span>
                <span class="fl w-80">
					<select class="drop drop-sl-k easyui-combobox" style="width:100%" data-Options="editable:false,required:true" name="admissionVosType" id="admissionVosType" value="${ae.admissionVosType!}">
						<option value="1" selected="selected">数值</option>
						<option value="2">描述</option>
					</select>
                </span>
                <span class="fl w-120">
					<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="admissionVos" name="admissionVos" value="${ae.admissionVos!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#admissionVosName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="admissionVosName" name="admissionVosName" value="${ae.admissionVosName!}"/>
                </span>
            </div>
        </div>
        <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
            <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
            入院眼压
            <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                </span>
                <div class="fl">
                    <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="admissionIopTypeOd" id="admissionIopTypeOd" data-options="valueField:'valueCode',textField:'valueDesc',required:true" value="${ae.admissionIopTypeOd!}">
                    </select>
                </div>
                <div class="admission-eyePressW-0 fl relative">
                    <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="admissionIopOd" id="admissionIopOd1" data-options="min:1,max:100,precision:1,required:true" value="${ae.admissionIopOd!}"/>
                    <em class="em-at em-dropAt">mmHg</em>
                </div>
                <div class="admission-eyePressW-1 fl">
                    <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="admissionIopOd" id="admissionIopOd2" value="${ae.admissionIopOd!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#admissionIopOdName').val(record.valueDesc);
					}">
                    </select>
                    <input class="txt" type="hidden" id="admissionIopOdName" name="admissionIopOdName" value="${ae.admissionIopOdName!}"/>
                </div>
            </div>
            <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
				<span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
				</span>
                <div class="fl">
                    <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="admissionIopTypeOs" id="admissionIopTypeOs" data-options="valueField:'valueCode',textField:'valueDesc',required:true" value="${ae.admissionIopTypeOs!}">
                    </select>
                </div>
                <div class="admission-eyePressWOs-0 fl relative">
                    <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="admissionIopOs" id="admissionIopOs1" data-options="min:1,max:100,precision:1,required:true" value="${ae.admissionIopOs!}"/>
                    <em class="em-at em-dropAt">mmHg</em>
                </div>
                <div class="admission-eyePressWOs-1 fl">
                    <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="admissionIopOs" id="admissionIopOs2" value="${ae.admissionIopOs!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#admissionIopOsName').val(record.valueDesc);
					}">
                    </select>
                    <input class="txt" type="hidden" id="admissionIopOsName" name="admissionIopOsName" value="${ae.admissionIopOsName!}"/>
                </div>
            </div>
        </div>

        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">拟施治疗（手术、注药、打激光等）：</label>
                <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" id="intendedTreatment" name="intendedTreatment" maxlength="600" cols="200" row="10">${ae.intendedTreatment!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">实施治疗（手术、注药、打激光等）：</label>
                <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" id="implementTreatment" name="implementTreatment" maxlength="600" cols="200" row="10">${ae.implementTreatment!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl w-200">
                <label class="lab-inline">是否植入晶体：</label>
                <label class="lab-val"><input type="radio" class="rad reason" name="isIol" value="0"   [#if '${ae.isIol }' == 0 ] checked="checked" [/#if] />否</label>
                <label class="lab-val"><input type="radio" class="rad reason" name="isIol" value="1"  [#if '${ae.isIol }' == 1 ] checked="checked" [/#if]  />是</label>
            </span>
            <span class="fl w-260">
                <label class="lab-inline">植入晶体类型：</label>
                <select class="drop easyui-combobox" style="width:50%;" name="iolTypeName" id="iolTypeName" data-options="editable:false, required:true" data-options="value:'${ae.iolTypeName}'">
                    <option value="双焦晶体">双焦晶体</option>
                    <option value="多焦晶体">多焦晶体</option>
                    <option value="散光晶体">散光晶体</option>
                    <option value="矫正散光晶体">矫正散光晶体</option>
                    <option value="PMMA普通晶体">PMMA普通晶体</option>
                    <option value="三合一晶体">三合一晶体</option>
                    <option value="普通折叠晶体">普通折叠晶体</option>
                    <option value="非球面晶体">非球面晶体</option>
                    <option value="人工晶体">人工晶体</option>
                    <option value="ICL">ICL</option>
                </select>
            </span>
            <span class="fl">
                <label class="lab-inline">切口类别：</label>
				[@ui_select id="cutType"  class="easyui-combobox"  name="cutType"  tag="@ae@cut_type" style="width:100px;"  dataOptions="limitToList:true,required:true,reversed:true,editable:false" filterkey="firstSpell" value="${ae.cutType!}" /]
            </span>
            <span class="fl w-300">
                <label class="lab-inline">手术/治疗医生：</label>
                 [#if "${pageType!}" == 1||"${pageType!}" == 0]
                	<input  type="hidden" id="operDoctName" name="operDoctName" value="${ae.operDoctName}" /> 
                	<select class="drop drop-doctorIda required" style="width:50%;" data-options="required:true" id="operDoctCode" name="operDoctCode" value="${ae.operDoctCode}"></select>
                [#else]
                	[#if "${pageType!}" == 5]
                		<input class="txt txt-validate" style="width:50%;" type="text" value="****" readonly="readonly"/>
                	[#else]
                		<input class="txt txt-validate" type="text" style="width:50%;" name="operDoctName" value="${ae.operDoctName!}" readonly="readonly"/>
                	[/#if]
                [/#if]
            </span>
        </div>
        <div id="div_out_hosp">
            <div style="font-size: 14px;display: inline-block;" class="mar-l30 mar-b10 ">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                出院视力
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
			<span class="fl">
				<label class="lab-inline" style="width: 30px;color:#00A0E9">VOD</label>
			</span>
                    <span class="fl w-80">
				<select class="drop drop-sl-k easyui-combobox discharge-vo" style="width:100%" name="dischargeVodType" id="dischargeVodType" value="${ae.dischargeVodType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
                    <span class="fl w-120">
				<select class="drop drop-sl-v easyui-combobox farEyesight discharge-vo" style="width:100%" id="dischargeVod" name="dischargeVod" value="${ae.dischargeVod!}"
                        data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#dischargeVodName').val(record.valueDesc);
				}">
				</select>
				<input class="txt discharge-vo" type="hidden" id="dischargeVodName" name="dischargeVodName" value="${ae.dischargeVodName!}"/>
			</span>
                </div>
                <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
			<span class="fl">
				<label class="lab-inline" style="width: 30px;color:#00A0E9">VOS</label>
			</span>
                    <span class="fl w-80">
				<select class="drop drop-sl-k easyui-combobox discharge-vo" style="width:100%" name="dischargeVosType" id="dischargeVosType" value="${ae.dischargeVosType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
                    <span class="fl w-120">
				<select class="drop drop-sl-v easyui-combobox farEyesight discharge-vo" style="width:100%" id="dischargeVos" name="dischargeVos" value="${ae.dischargeVos!}"
                        data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#dischargeVosName').val(record.valueDesc);
				}">
				</select>
				<input class="txt discharge-vo" type="hidden" id="dischargeVosName" name="dischargeVosName" value="${ae.dischargeVosName!}"/>
			</span>
                </div>
            </div>
            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                出院眼压
                <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                </span>
                    <div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType discharge-vo" style="width:100px" name="dischargeIopTypeOd" id="dischargeIopTypeOd" data-options="valueField:'valueCode',textField:'valueDesc'" value="${ae.dischargeIopTypeOd!}">
                        </select>
                    </div>
                    <div class="discharge-eyePressW-0 fl relative">
                        <input class="txt txt-pressExamNum easyui-numberspinner discharge-vo" type="text" style="width:200px" name="dischargeIopOd" id="dischargeIopOd1" data-options="min:1,max:100,precision:1" value="${ae.dischargeIopOd!}"/>
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="discharge-eyePressW-1 fl">
                        <select class="drop easyui-combobox drop-pressExam easyui-combobox discharge-vo" style="width:200px" name="dischargeIopOd" id="dischargeIopOd2" value="${ae.dischargeIopOd!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#dischargeIopOdName').val(record.valueDesc);
					}">
                        </select>
                        <input class="txt discharge-vo" type="hidden" id="dischargeIopOdName" name="dischargeIopOdName" value="${ae.dischargeIopOdName!}"/>
                    </div>
                </div>
                <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
				<span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
				</span>
                    <div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType discharge-vo" style="width:100px" name="dischargeIopTypeOs" id="dischargeIopTypeOs" data-options="valueField:'valueCode',textField:'valueDesc'" value="${ae.dischargeIopTypeOs!}">
                        </select>
                    </div>
                    <div class="discharge-eyePressWOs-0 fl relative">
                        <input class="txt txt-pressExamNum easyui-numberspinner discharge-vo" type="text" style="width:200px" name="dischargeIopOs" id="dischargeIopOs1" data-options="min:1,max:100,precision:1" value="${ae.dischargeIopOs!}"/>
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="discharge-eyePressWOs-1 fl">
                        <select class="drop easyui-combobox drop-pressExam easyui-combobox discharge-vo" style="width:200px" name="dischargeIopOs" id="dischargeIopOs2" value="${ae.dischargeIopOs!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#dischargeIopOsName').val(record.valueDesc);
					}">
                        </select>
                        <input class="txt discharge-vo" type="hidden" id="dischargeIopOsName" name="dischargeIopOsName" value="${ae.dischargeIopOsName!}"/>
                    </div>
                </div>
            </div>
        </div>
        <div id="div_cur_hosp">
            <div style="font-size: 14px;display: inline-block;" class="mar-l30 mar-b10 ">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                当前视力
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
			<span class="fl">
				<label class="lab-inline" style="width: 30px;color:#00A0E9">VOD</label>
			</span>
                    <span class="fl w-80">
				<select class="drop drop-sl-k easyui-combobox current-vo" style="width:100%" name="currentVodType" id="currentVodType" value="${ae.currentVodType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
                    <span class="fl w-120">
				<select class="drop drop-sl-v easyui-combobox farEyesight current-vo" style="width:100%" id="currentVod" name="currentVod" value="${ae.currentVod!}"
                        data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#currentVodName').val(record.valueDesc);
				}">
				</select>
				<input class="txt current-vo" type="hidden" id="currentVodName" name="currentVodName" value="${ae.currentVodName!}"/>
			</span>
                </div>
                <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
			<span class="fl">
				<label class="lab-inline" style="width: 30px;color:#00A0E9">VOS</label>
			</span>
                    <span class="fl w-80">
				<select class="drop drop-sl-k easyui-combobox current-vo" style="width:100%" name="currentVosType" id="currentVosType" value="${ae.currentVosType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
                    <span class="fl w-120">
				<select class="drop drop-sl-v easyui-combobox farEyesight current-vo" style="width:100%" id="currentVos" name="currentVos" value="${ae.currentVos!}"
                        data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#currentVosName').val(record.valueDesc);
				}">
				</select>
				<input class="txt current-vo" type="hidden" id="currentVosName" name="currentVosName" value="${ae.currentVosName!}"/>
			</span>
                </div>
            </div>
            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                当前眼压
                <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                </span>
                    <div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType current-vo" style="width:100px" name="currentIopTypeOd" id="currentIopTypeOd" data-options="valueField:'valueCode',textField:'valueDesc'" value="${ae.currentIopTypeOd!}">
                        </select>
                    </div>
                    <div class="current-eyePressW-0 fl relative">
                        <input class="txt txt-pressExamNum easyui-numberspinner current-vo" type="text" style="width:200px" name="currentIopOd" id="currentIopOd1" data-options="min:1,max:100,precision:1" value="${ae.currentIopOd!}"/>
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="current-eyePressW-1 fl">
                        <select class="drop easyui-combobox drop-pressExam easyui-combobox current-vo" style="width:200px" name="currentIopOd" id="currentIopOd2" value="${ae.currentIopOd!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#currentIopOdName').val(record.valueDesc);
					}">
                        </select>
                        <input class="txt current-vo" type="hidden" id="currentIopOdName" name="currentIopOdName" value="${ae.currentIopOdName!}"/>
                    </div>
                </div>
                <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
				<span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
				</span>
                    <div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType current-vo" style="width:100px" name="currentIopTypeOs" id="currentIopTypeOs" data-options="valueField:'valueCode',textField:'valueDesc'" value="${ae.currentIopTypeOs!}">
                        </select>
                    </div>
                    <div class="current-eyePressWOs-0 fl relative">
                        <input class="txt txt-pressExamNum easyui-numberspinner current-vo" type="text" style="width:200px" name="currentIopOs" id="currentIopOs1" data-options="min:1,max:100,precision:1" value="${ae.currentIopOs!}"/>
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="current-eyePressWOs-1 fl">
                        <select class="drop easyui-combobox drop-pressExam easyui-combobox current-vo" style="width:200px" name="currentIopOs" id="currentIopOs2" value="${ae.currentIopOs!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#currentIopOsName').val(record.valueDesc);
					}">
                        </select>
                        <input class="txt current-vo" type="hidden" id="currentIopOsName" name="currentIopOsName" value="${ae.currentIopOsName!}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-120">目前情况：</label>
				<textarea class="txta txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写目前情况" id="currSituation" name="currSituation" maxlength="600" cols="200" row="10">${ae.currSituation!}</textarea>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-120">患者及家属诉求：</label>
				<textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写患者及家属诉求" id="patientAppeals" name="patientAppeals" maxlength="600" cols="200" row="10">${ae.patientAppeals!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-120">事件经过描述：</label>
				<textarea class="txta txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写事件经过描述" id="eventText" name="eventText" cols="200" row="10">${ae.eventDesc!}${ae.eventDescOne!}${ae.eventDescTwo!}</textarea>
            </span>
        </div>
        <input class="txt" type="hidden" id="eventDesc" name="eventDesc" value=""/>
        <input class="txt" type="hidden" id="eventDescOne" name="eventDescOne" value=""/>
        <input class="txt" type="hidden" id="eventDescTwo" name="eventDescTwo" value=""/>
        <input class="txt" type="hidden" id="cutTypeName" name="cutTypeName" value=""/>
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
        requirejs(["eventReview", "eventTableGroup","eventFlowchart","uploadImages","eventImage",'myupload','pub', "btnSearch"], function (eventReview, eventTableGroup,eventFlowchart,uploadImages,eventImage) {
            // 设置是否植入晶体事件
            if('${ae.isIol!}' == 0) {
				$("#iolTypeName").combobox('disable').combobox('clear');
			}else{
				if('${ae.iolTypeName!}'){
				    $("#iolTypeName").combobox('setValue', '${ae.iolTypeName!}');
				}else{
					$("#iolTypeName").combobox('clear');
				}
			};
            
            $("input[name='isIol']").click(function(){
            	var v = $("input[name='isIol']");
				if(v[0].checked == true){
					// 否
					$("#iolTypeName").combobox('clear').combobox('disable');
				}else{
					//是
					$("#iolTypeName").combobox('enable');
				}

			});
            //暂存前执行事件 false中断保存
            window.beforeStash = function(){
                if($("#eventText").val()){
                    var str = $("#eventText").val();
                    var splitLen = Math.ceil(str.length / 3);
                    var result = []; //结果将会存储到 长度为 3 的数组里
                    for (var i = 0; i < 3; i++) {
                        result.push(str.substr(0, splitLen));
                        str = str.substr(splitLen, str.length+1);
                    }
                    $("#eventDesc").val(result[0]);
                    $("#eventDescOne").val(result[1]);
                    $("#eventDescTwo").val(result[2]);
                }else{
                    $("#eventText").val(null);
                    $("#eventDesc").val(null);
                    $("#eventDescOne").val(null);
                    $("#eventDescTwo").val(null);
                }

                // 切口类别名称
                $("#cutTypeName").val($('#cutType').combobox('getText')?$('#cutType').combobox('getText'):'');
                return true
            }

            //保存前执行事件 false中断保存
            window.beforeSubmit = function(){
                if($("#eventText").val()){
                    var str = $("#eventText").val();
                    var splitLen = Math.ceil(str.length / 3);
                    var result = []; //结果将会存储到 长度为 3 的数组里
                    for (var i = 0; i < 3; i++) {
                        result.push(str.substr(0, splitLen));
                        str = str.substr(splitLen, str.length+1);
                    }
                    $("#eventDesc").val(result[0]);
                    $("#eventDescOne").val(result[1]);
                    $("#eventDescTwo").val(result[2]);
                }else{
                    $("#eventText").val(null);
                    $("#eventDesc").val(null);
                    $("#eventDescOne").val(null);
                    $("#eventDescTwo").val(null);
                }
                
                //判断实施手术是否填写，填写的话必须填写手术医生
				if($("#implementTreatment").val()){
					if(!$('#operDoctName').val()&&('${pageType!}' == 1||'${pageType!}' == 0)){
						$pop.alert('请选择手术医生!');
						return false;
					}
				}

                // 切口类别名称
                $("#cutTypeName").val($('#cutType').combobox('getText')?$('#cutType').combobox('getText'):'');
                return true;
            }

            [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
            [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
            [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]

            /**
             * 从接口获取数据后表单赋值
             * */
            window.afterSerachPatient=function (){
                // 住院接口数据
                var inDomain=window.inDomain;

                // 入院时间
                $("#admissionDate").setVal(!!inDomain?inDomain.inpTime.substr(0,10):"");
                // 门诊诊断
                $('#outpDiagName').setVal(!!inDomain.outpDiagName?inDomain.outpDiagName:"");
                // 住院诊断
                $('#inpDiagName').setVal(!!inDomain.diagName?inDomain.diagName:"");
                // 入院视力vod   类型（OD） 1数值 2描述
                $('#admissionVodType').setVal(!!inDomain.inpVisionOdType?inDomain.inpVisionOdType:"");
                // 入院视力vos   类型（OS） 1数值 2描述
                $('#admissionVosType').setVal(!!inDomain.inpVisionOsType?inDomain.inpVisionOsType:"");
                // 入院视力vod
                $('#admissionVod').setVal(!!inDomain.inpVisionOd?inDomain.inpVisionOd:"");
                // 入院视力vos
                $('#admissionVos').setVal(!!inDomain.inpVisionOs?inDomain.inpVisionOs:"");
                // 出院时间
                if(inDomain.dischargeTime){
                    $("#dischargeDate").setVal(inDomain?inDomain.dischargeTime.substr(0,10):"");
                    // 出院视力类型
                    $('#dischargeVodType').setVal(!!inDomain.dischargeVisionOdType?inDomain.dischargeVisionOdType:"");
                    $('#dischargeVosType').setVal(!!inDomain.dischargeVisionOsType?inDomain.dischargeVisionOsType:"");
                    // 出院视力
                    $('#dischargeVod').setVal(!!inDomain.dischargeVisionOd?inDomain.dischargeVisionOd:"");
                    $('#dischargeVos').setVal(!!inDomain.dischargeVisionOs?inDomain.dischargeVisionOs:"");
                    // 出院眼压
                    //$('#dischargeIopOd').setVal(!!inDomain.dischargeVisionOd?inDomain.dischargeVisionOd:"");
                    //$('#dischargeIopOs').setVal(!!inDomain.dischargeVisionOs?inDomain.dischargeVisionOs:"");

                    setValue();
                }
                // 拟施治疗
                $('#intendedTreatment').setVal(!!inDomain.operPlan?inDomain.operPlan:"");
                // 实施治疗
                $('#implementTreatment').setVal(!!inDomain.oper?inDomain.oper:"");
                // 切口类别
                $("#cutType").combobox('setValue', !!inDomain.cutType?inDomain.cutType:"");
                
               	var emrUrl =inDomain.emrUrl;
				if(emrUrl){
					$('.btn-aemr').removeClass('hide');
					$('.btn-aemr').click(function () {
						window.open(emrUrl,'_blank');
					});
				}else{
					$('.btn-aemr').addClass('hide');
				}

                //手术信息数据
                var oprData = window.opr;
                if(oprData) {
					if(oprData.crystalTypeName){
						$("input[name='isIol'][value='1']").prop("checked",true);
						$("#iolTypeName").combobox('enable')
						$("#iolTypeName").combobox('setValue', oprData.crystalTypeName);
					}else{
						$("input[name='isIol'][value='0']").prop("checked",true);
					}
                    
					$('#operDoctCode').combobox('setValue', oprData.operDoctCode);
					$('#operDoctName').val(oprData.operDoctName)
                }

            }

            /**远视力列表*/
            var farEyesight = null;
            /**近视力列表*/
            var nearEyesight = null;
            /**眼压类型*/
            var iopType = null;
            /**指测眼压结果*/
            var fingerTonometry = null;

            var param = {paraType : ['far_eyesight','near_eyesight','eyesight_desc', 'iop_type', 'finger_tonometry']};
            $ajax.postSync('${base}/ui/base/dict/getMoreList',param,false,false).done(function (rst) {
                var dict = rst.data;
                farEyesight=[dict.far_eyesight, dict.eyesight_desc];
                nearEyesight=dict.near_eyesight;
                iopType=dict.iop_type;
                fingerTonometry=dict.finger_tonometry;
                //console.log('farEyesight=',farEyesight);
            });

            var setValue=function(){
                //debugger;
                var dischargeDate = $("#dischargeDate").val();
                if(dischargeDate){
                    //有值的情况 隐藏 当前
                    $("#div_cur_hosp").hide();
                    $("#currentVodType").combobox('disable');
                    $("#currentVod").combobox('disable');
                    $("#currentVodName").prop("disabled",true);
                    $("#currentVosType").combobox('disable');
                    $("#currentVos").combobox('disable');
                    $("#currentVosName").prop("disabled",true);
                    $("#currentIopTypeOd").combobox('disable');
                    $("#currentIopOd1").numberspinner('disable');
                    $("#currentIopOd2").combobox('disable');
                    $("#currentIopOdName").prop("disabled",true);
                    $("#currentIopTypeOs").combobox('disable');
                    $("#currentIopOs1").combobox('disable');
                    $("#currentIopOs2").combobox('disable');
                    $("#currentIopOsName").prop("disabled",true);

                    $("#div_out_hosp").show();

                    $("#dischargeVodType").combobox('enable');
                    $("#dischargeVod").combobox('enable');
                    $("#dischargeVodName").prop("disabled",false);
                    $("#dischargeVosType").combobox('enable');
                    $("#dischargeVos").combobox('enable');
                    $("#dischargeVosName").prop("disabled",false);
                    if('${ae.dischargeVodType!}'){
                        $('#dischargeVodType').setVal('${ae.dischargeVodType!}')
                    }
                    if('${ae.dischargeVod!}'){
                        $('#dischargeVod').setVal('${ae.dischargeVod!}')
                    }
                    if('${ae.dischargeVodName!}'){
                        $('#dischargeVodName').setVal('${ae.dischargeVodName!}')
                    }
                    if('${ae.dischargeVosType!}') {
                        $('#dischargeVosType').setVal('${ae.dischargeVosType!}')
                    }
                    if('${ae.dischargeVos!}'){
                        $('#dischargeVos').setVal('${ae.dischargeVos!}')
                    }
                    if('${ae.dischargeVosName!}'){
                        $('#dischargeVosName').setVal('${ae.dischargeVosName!}')
                    }

                    $("#dischargeIopTypeOd").combobox('enable');
                    var dischargeIopTypeOd = '${ae.dischargeIopTypeOd!}';
                    if(dischargeIopTypeOd){
                        $('#dischargeIopTypeOd').setVal(dischargeIopTypeOd);
                    }

                    if(dischargeIopTypeOd ==='05'){
                        $('.discharge-eyePressW-0').hide();
                        $('.discharge-eyePressW-1').show();
                        $('#dischargeIopOd1').numberspinner('disable');
                        $('#dischargeIopOd2').combobox('enable');
                        if('${ae.dischargeIopOd!}'){
                            $('#dischargeIopOd2').setVal('${ae.dischargeIopOd!}')
                        }
                    }else{
                        $('.discharge-eyePressW-0').show();
                        $('.discharge-eyePressW-1').hide();
                        $('#dischargeIopOd1').numberspinner('enable');
                        if('${ae.dischargeIopOd!}'){
                            $('#dischargeIopOd1').setVal('${ae.dischargeIopOd!}');
                        }
                        $('#dischargeIopOd2').combobox('disable');
                    }

                    $("#dischargeIopOdName").prop("disabled",false);
                    if('${ae.dischargeIopOdName!}'){
                        $('#dischargeIopOdName').setVal('${ae.dischargeIopOdName!}')
                    }

                    $("#dischargeIopTypeOs").combobox('enable');
                    var dischargeIopTypeOs = '${ae.dischargeIopTypeOs!}';
                    if(dischargeIopTypeOs){
                        $('#dischargeIopTypeOs').setVal(dischargeIopTypeOs);
                    }

                    if(dischargeIopTypeOs ==='05'){
                        $('.discharge-eyePressWOs-0').hide();
                        $('.discharge-eyePressWOs-1').show();
                        $('#dischargeIopOs1').numberspinner('disable');
                        $('#dischargeIopOs2').combobox('enable');
                        if('${ae.dischargeIopOs!}'){
                            $('#dischargeIopOs2').setVal('${ae.dischargeIopOs!}')
                        }
                    }else{
                        $('.discharge-eyePressWOs-0').show();
                        $('.discharge-eyePressWOs-1').hide();
                        $('#dischargeIopOs1').numberspinner('enable');
                        if('${ae.dischargeIopOs!}'){
                            $('#dischargeIopOs1').setVal('${ae.dischargeIopOs!}');
                        }
                        $('#dischargeIopOs2').combobox('disable');
                    }

                    $("#dischargeIopOsName").prop("disabled",false);
                    if('${ae.dischargeIopOsName!}'){
                        $('#dischargeIopOsName').setVal('${ae.dischargeIopOsName!}')
                    }
                }else{
                    //没值的情况 隐藏出院
                    $("#div_out_hosp").hide();
                    $("#dischargeVodType").combobox('disable');
                    $("#dischargeVod").combobox('disable');
                    $("#dischargeVodName").prop("disabled",true);
                    $("#dischargeVosType").combobox('disable');
                    $("#dischargeVos").combobox('disable');
                    $("#dischargeVosName").prop("disabled",true);
                    $("#dischargeIopTypeOd").combobox('disable');
                    $("#dischargeIopOd1").numberspinner('disable');
                    $("#dischargeIopOd2").combobox('disable');
                    $("#dischargeIopOdName").prop("disabled",true);
                    $("#dischargeIopTypeOs").combobox('disable');
                    $("#dischargeIopOs1").numberspinner('disable');
                    $("#dischargeIopOs2").combobox('disable');
                    $("#dischargeIopOsName").prop("disabled",true);

                    $("#div_cur_hosp").show();

                    $("#currentVodType").combobox('enable');
                    $("#currentVod").combobox('enable');
                    $("#currentVodName").prop("disabled",false);
                    $("#currentVosType").combobox('enable');
                    $("#currentVos").combobox('enable');
                    $("#currentVosName").prop("disabled",false);
                    if('${ae.currentVodType!}'){
                        $('#currentVodType').setVal('${ae.currentVodType!}')
                    }
                    if('${ae.currentVod!}'){
                        $('#currentVod').setVal('${ae.currentVod!}')
                    }
                    if('${ae.currentVodName!}'){
                        $('#currentVodName').setVal('${ae.currentVodName!}')
                    }
                    if('${ae.currentVosType!}'){
                        $('#currentVosType').setVal('${ae.currentVosType!}')
                    }
                    if('${ae.currentVos!}'){
                        $('#currentVos').setVal('${ae.currentVos!}')
                    }
                    if('${ae.currentVosName!}'){
                        $('#currentVosName').setVal('${ae.currentVosName!}')
                    }

                    $("#currentIopTypeOd").combobox('enable');
                    var currentIopTypeOd = '${ae.currentIopTypeOd!}';
                    if(currentIopTypeOd){
                        $('#currentIopTypeOd').setVal(currentIopTypeOd);
                    }
                    if(currentIopTypeOd ==='05'){
                        $('.current-eyePressW-0').hide();
                        $('.current-eyePressW-1').show();
                        $('#currentIopOd1').numberspinner('disable');
                        $('#currentIopOd2').combobox('enable')
                        if('${ae.currentIopOd!}'){
                            $('#currentIopOd2').setVal('${ae.currentIopOd!}')
                        }
                    }else{
                        $('.current-eyePressW-0').show();
                        $('.current-eyePressW-1').hide();
                        $('#currentIopOd1').numberspinner('enable');
                        if('${ae.currentIopOd!}'){
                            $('#currentIopOd1').setVal('${ae.currentIopOd!}')
                        }
                        $('#currentIopOd2').combobox('disable');
                    }

                    $("#currentIopOdName").prop("disabled",false);
                    if('${ae.currentIopOdName!}'){
                        $('#currentIopOdName').setVal('${ae.currentIopOdName!}')
                    }
                    $("#currentIopTypeOs").combobox('enable');
                    var currentIopTypeOs = '${ae.currentIopTypeOs!}';
                    if(currentIopTypeOs){
                        $('#currentIopTypeOs').setVal(currentIopTypeOs);
                    }

                    if(currentIopTypeOs ==='05'){
                        $('.current-eyePressWOs-0').hide();
                        $('.current-eyePressWOs-1').show();
                        $('#currentIopOs1').numberspinner('disable');
                        $('#currentIopOs2').combobox('enable')
                        if('${ae.currentIopOs!}'){
                            $('#currentIopOs2').setVal('${ae.currentIopOs!}')
                        }
                    }else{
                        $('.current-eyePressWOs-0').show();
                        $('.current-eyePressWOs-1').hide();
                        $('#currentIopOs1').numberspinner('enable');
                        if('${ae.currentIopOs!}'){
                            $('#currentIopOs1').setVal('${ae.currentIopOs!}')
                        }
                        $('#currentIopOs2').combobox('disable');
                    }
                    $("#currentIopOsName").prop("disabled",false);
                    if('${ae.currentIopOsName!}'){
                        $('#currentIopOsName').setVal('${ae.currentIopOsName!}')
                    }
                }

                if('${ae.admissionVodType}'){
                    $('#admissionVodType').setVal('${ae.admissionVodType}')
                }
                if('${ae.admissionVod!}'){
                    $('#admissionVod').setVal('${ae.admissionVod!}')
                }
                if('${ae.admissionVodName!}'){
                    $('#admissionVodName').setVal('${ae.admissionVodName!}')
                }
                if('${ae.admissionVosType!}'){
                 $('#admissionVosType').setVal('${ae.admissionVosType!}')
                }
                if('${ae.admissionVos!}'){
                    $('#admissionVos').setVal('${ae.admissionVos!}')
                }
                if('${ae.admissionVosName!}'){
                    $('#admissionVosName').setVal('${ae.admissionVosName!}')
                }

                var admissionIopTypeOd = '${ae.admissionIopTypeOd!}';
                if(admissionIopTypeOd){
                    $('#admissionIopTypeOd').setVal(admissionIopTypeOd);
                }
                if('${ae.admissionIopOdName!}'){
                    $('#admissionIopOdName').setVal('${ae.admissionIopOdName!}')
                }
                if(admissionIopTypeOd ==='05'){
                    $('.admission-eyePressW-0').hide();
                    $('.admission-eyePressW-1').show();
                    $('#admissionIopOd1').numberspinner('disable');
                    $('#admissionIopOd2').combobox('enable')
                    if('${ae.admissionIopOd!}'){
                        $('#admissionIopOd2').setVal('${ae.admissionIopOd!}')
                    }
                }else{
                    $('.admission-eyePressW-0').show();
                    $('.admission-eyePressW-1').hide();
                    $('#admissionIopOd1').numberspinner('enable');
                    if('${ae.admissionIopOd!}'){
                        $('#admissionIopOd1').setVal('${ae.admissionIopOd!}')
                    }
                    $('#admissionIopOd2').combobox('disable');
                }
                // "拒测","测不出", "病情原因未测", "眼球缺如" 时校验取消
                if( admissionIopTypeOd ==='96' || admissionIopTypeOd ==='97' || admissionIopTypeOd ==='98' || admissionIopTypeOd ==='99' ){
                    $('#admissionIopOd1').numberspinner('disableValidation');
                }else{
                    $('#admissionIopOd1').numberspinner('enableValidation');
                }
                var admissionIopTypeOs = '${ae.admissionIopTypeOs!}';
                if(admissionIopTypeOs){
                    $('#admissionIopTypeOs').setVal(admissionIopTypeOs);
                }
                if('${ae.admissionIopOsName!}'){
                    $('#admissionIopOsName').setVal('${ae.admissionIopOsName!}')
                }
                if(admissionIopTypeOs ==='05'){
                    $('.admission-eyePressWOs-0').hide();
                    $('.admission-eyePressWOs-1').show();
                    $('#admissionIopOs1').numberspinner('disable');
                    $('#admissionIopOs2').combobox('enable')
                    if('${ae.admissionIopOs!}'){
                        $('#admissionIopOs2').setVal('${ae.admissionIopOs!}')
                    }
                }else{
                    $('.admission-eyePressWOs-0').show();
                    $('.admission-eyePressWOs-1').hide();
                    $('#admissionIopOs1').numberspinner('enable');
                    if('${ae.admissionIopOs!}'){
                        $('#admissionIopOs1').setVal('${ae.admissionIopOs!}')
                    }
                    $('#admissionIopOs2').combobox('disable');
                }
                // "拒测","测不出", "病情原因未测", "眼球缺如" 时校验取消
                if( admissionIopTypeOd ==='96' || admissionIopTypeOd ==='97' || admissionIopTypeOd ==='98' || admissionIopTypeOd ==='99' ){
                    $('#admissionIopOs1').numberspinner('disableValidation');
                }else{
                    $('#admissionIopOs1').numberspinner('enableValidation');
                }

                if('${ae.operDoctCode!}'){
					$('#operDoctCode').combobox('setValue','${ae.operDoctCode!}');
					$('#operDoctName').val('${ae.operDoctName!}')
				}else{
					$('#operDoctCode').combobox('setValue','');
				}
            }

            var baseExam = {
                init : function (){
                    var me = this;
                    $('.farEyesight').combobox('loadData',farEyesight[0]);
                    $('.drop-eyePressExamType').combobox('loadData',iopType);
                    $('.drop-pressExam').combobox('loadData',fingerTonometry);
                    me.farVisionFn();//远视力切换数据
                    me.eyePressFn();//眼压检查
                    $('.drop-eyePressExamType').combobox('setValue','01');//眼压检查默认第一个值
                    setValue();
                },
                farVisionFn : function (){//远视力切换数据
                    $('.drop-sl-k').combobox({
                        onChange : function (val){
                            if (!val){
                                $(this).combobox('setValue','1');//值空
                            }
                            else{
                                var $v = $(this).parents('.p6').find('.drop-sl-v');
                                $v.combobox('setValue','');//值空
                                $v.combobox('loadData',farEyesight[val-1]);//loadData
                            }
                        }
                    });
                },
                eyePressFn : function (){//眼压检查
                    $('#admissionIopTypeOd').combobox({
                        onChange : function (val){
                            if(val ==='05'){
                                $('.admission-eyePressW-0').hide();
                                $('.admission-eyePressW-1').show();
                                $('#admissionIopOd1').numberspinner('disable');
                                $('#admissionIopOd2').combobox('enable')
                            }else{
                                $('.admission-eyePressW-0').show();
                                $('.admission-eyePressW-1').hide();
                                $('#admissionIopOd1').numberspinner('enable');
                                $('#admissionIopOd2').combobox('disable');
                            }
                            // "拒测","测不出", "病情原因未测", "眼球缺如" 时校验取消
                            if( val ==='96' || val ==='97' || val ==='98' || val ==='99' ){
                                $('#admissionIopOd1').numberspinner('disableValidation');
                            }else{
                                $('#admissionIopOd1').numberspinner('enableValidation');
                            }
                            $form.formAEnterFun();
                        }
                    });
                    $('#admissionIopTypeOs').combobox({
                        onChange : function (val){
                            if(val ==='05'){
                                $('.admission-eyePressWOs-0').hide();
                                $('.admission-eyePressWOs-1').show();
                                $('#admissionIopOs1').numberspinner('disable');
                                $('#admissionIopOs2').combobox('enable')
                            }else{
                                $('.admission-eyePressWOs-0').show();
                                $('.admission-eyePressWOs-1').hide();
                                $('#admissionIopOs1').numberspinner('enable');
                                $('#admissionIopOs2').combobox('disable');
                            }
                            // "拒测","测不出", "病情原因未测", "眼球缺如" 时校验取消
                            if( val ==='96' || val ==='97' || val ==='98' || val ==='99' ){
                                $('#admissionIopOs1').numberspinner('disableValidation');
                            }else{
                                $('#admissionIopOs1').numberspinner('enableValidation');
                            }
                            $form.formAEnterFun();
                        }
                    });

                    $('#dischargeIopTypeOd').combobox({
                        onChange : function (val){
                            if(val ==='05'){
                                $('.discharge-eyePressW-0').hide();
                                $('.discharge-eyePressW-1').show();
                                $('#dischargeIopOd1').numberspinner('disable');
                                $('#dischargeIopOd2').combobox('enable')
                            }else{
                                $('.discharge-eyePressW-0').show();
                                $('.discharge-eyePressW-1').hide();
                                $('#dischargeIopOd1').numberspinner('enable');
                                $('#dischargeIopOd2').combobox('disable');
                            }
                            $form.formAEnterFun();
                        }
                    });
                    $('#dischargeIopTypeOs').combobox({
                        onChange : function (val){
                            if(val ==='05'){
                                $('.discharge-eyePressWOs-0').hide();
                                $('.discharge-eyePressWOs-1').show();
                                $('#dischargeIopOs1').numberspinner('disable');
                                $('#dischargeIopOs2').combobox('enable')
                            }else{
                                $('.discharge-eyePressWOs-0').show();
                                $('.discharge-eyePressWOs-1').hide();
                                $('#dischargeIopOs1').numberspinner('enable');
                                $('#dischargeIopOs2').combobox('disable');
                            }
                            $form.formAEnterFun();
                        }
                    });

                    $('#currentIopTypeOd').combobox({
                        onChange : function (val){
                            if(val ==='05'){
                                $('.current-eyePressW-0').hide();
                                $('.current-eyePressW-1').show();
                                $('#currentIopOd1').numberspinner('disable');
                                $('#currentIopOd2').combobox('enable')
                            }else{
                                $('.current-eyePressW-0').show();
                                $('.current-eyePressW-1').hide();
                                $('#currentIopOd1').numberspinner('enable');
                                $('#currentIopOd2').combobox('disable');
                            }
                            $form.formAEnterFun();
                        }
                    });
                    $('#currentIopTypeOs').combobox({
                        onChange : function (val){
                            if(val ==='05'){
                                $('.current-eyePressWOs-0').hide();
                                $('.current-eyePressWOs-1').show();
                                $('#currentIopOs1').numberspinner('disable');
                                $('#currentIopOs2').combobox('enable')
                            }else{
                                $('.current-eyePressWOs-0').show();
                                $('.current-eyePressWOs-1').hide();
                                $('#currentIopOs1').numberspinner('enable');
                                $('#currentIopOs2').combobox('disable');
                            }
                            $form.formAEnterFun();
                        }
                    });
                }
            };

            $(function () {
                //初始化表单
                baseExam.init();
                if(!'${ae.id}'){
	                if(!$.isEmptyObject('${ae.patientNo}')){
						queryPatientInfo('${ae.patientNo}');
					}
				}
            });

            $form.soDate("#dischargeDate",{
                onpicked:function(obj){
                    //有值的情况 隐藏 当前
                    $("#div_cur_hosp").hide();
                    $("#currentVodType").combobox('disable');
                    $("#currentVod").combobox('disable');
                    $("#currentVodName").prop("disabled",true);
                    $("#currentVosType").combobox('disable');
                    $("#currentVos").combobox('disable');
                    $("#currentVosName").prop("disabled",true);
                    $("#currentIopTypeOd").combobox('disable');
                    $("#currentIopOd1").numberspinner('disable');
                    $("#currentIopOd2").combobox('disable');
                    $("#currentIopOdName").prop("disabled",true);
                    $("#currentIopTypeOs").combobox('disable');
                    $("#currentIopOs1").combobox('disable');
                    $("#currentIopOs2").combobox('disable');
                    $("#currentIopOsName").prop("disabled",true);

                    $("#div_out_hosp").show();

                    $("#dischargeVodType").combobox('enable');
                    $("#dischargeVod").combobox('enable');
                    $("#dischargeVodName").prop("disabled",false);
                    $("#dischargeVosType").combobox('enable');
                    $("#dischargeVos").combobox('enable');
                    $("#dischargeVosName").prop("disabled",false);
                    $('#dischargeVodType').setVal('${ae.dischargeVodType!}')
                    $('#dischargeVod').setVal('${ae.dischargeVod!}')
                    $('#dischargeVodName').setVal('${ae.dischargeVodName!}')
                    $('#dischargeVosType').setVal('${ae.dischargeVosType!}')
                    $('#dischargeVos').setVal('${ae.dischargeVos!}')
                    $('#dischargeVosName').setVal('${ae.dischargeVosName!}')

                    $("#dischargeIopTypeOd").combobox('enable');
                    var dischargeIopTypeOd = '${ae.dischargeIopTypeOd!}';
                    $('#dischargeIopTypeOd').setVal(dischargeIopTypeOd);

                    if(dischargeIopTypeOd ==='05'){
                        $('.discharge-eyePressW-0').hide();
                        $('.discharge-eyePressW-1').show();
                        $('#dischargeIopOd1').numberspinner('disable');
                        $('#dischargeIopOd2').combobox('enable');
                        $('#dischargeIopOd2').setVal('${ae.dischargeIopOd!}')
                    }else{
                        $('.discharge-eyePressW-0').show();
                        $('.discharge-eyePressW-1').hide();
                        $('#dischargeIopOd1').numberspinner('enable');
                        $('#dischargeIopOd1').setVal('${ae.dischargeIopOd!}');
                        $('#dischargeIopOd2').combobox('disable');
                    }

                    $("#dischargeIopOdName").prop("disabled",false);
                    $('#dischargeIopOdName').setVal('${ae.dischargeIopOdName!}')

                    $("#dischargeIopTypeOs").combobox('enable');
                    var dischargeIopTypeOs = '${ae.dischargeIopTypeOs!}';
                    $('#dischargeIopTypeOs').setVal(dischargeIopTypeOs);

                    if(dischargeIopTypeOs ==='05'){
                        $('.discharge-eyePressWOs-0').hide();
                        $('.discharge-eyePressWOs-1').show();
                        $('#dischargeIopOs1').numberspinner('disable');
                        $('#dischargeIopOs2').combobox('enable');
                        $('#dischargeIopOs2').setVal('${ae.dischargeIopOs!}')
                    }else{
                        $('.discharge-eyePressWOs-0').show();
                        $('.discharge-eyePressWOs-1').hide();
                        $('#dischargeIopOs1').numberspinner('enable');
                        $('#dischargeIopOs1').setVal('${ae.dischargeIopOs!}');
                        $('#dischargeIopOs2').combobox('disable');
                    }

                    $("#dischargeIopOsName").prop("disabled",false);
                    $('#dischargeIopOsName').setVal('${ae.dischargeIopOsName!}')

                },
                oncleared:function(){
                    //没值的情况 隐藏出院
                    $("#div_out_hosp").hide();
                    $("#dischargeVodType").combobox('disable');
                    $("#dischargeVod").combobox('disable');
                    $("#dischargeVodName").prop("disabled",true);
                    $("#dischargeVosType").combobox('disable');
                    $("#dischargeVos").combobox('disable');
                    $("#dischargeVosName").prop("disabled",true);
                    $("#dischargeIopTypeOd").combobox('disable');
                    $("#dischargeIopOd1").numberspinner('disable');
                    $("#dischargeIopOd2").combobox('disable');
                    $("#dischargeIopOdName").prop("disabled",true);
                    $("#dischargeIopTypeOs").combobox('disable');
                    $("#dischargeIopOs1").combobox('disable');
                    $("#dischargeIopOs2").combobox('disable');
                    $("#dischargeIopOsName").prop("disabled",true);

                    $("#div_cur_hosp").show();

                    $("#currentVodType").combobox('enable');
                    $("#currentVod").combobox('enable');
                    $("#currentVodName").prop("disabled",false);
                    $("#currentVosType").combobox('enable');
                    $("#currentVos").combobox('enable');
                    $("#currentVosName").prop("disabled",false);
                    $('#currentVodType').setVal('${ae.currentVodType!}')
                    $('#currentVod').setVal('${ae.currentVod!}')
                    $('#currentVodName').setVal('${ae.currentVodName!}')
                    $('#currentVosType').setVal('${ae.currentVosType!}')
                    $('#currentVos').setVal('${ae.currentVos!}')
                    $('#currentVosName').setVal('${ae.currentVosName!}')

                    $("#currentIopTypeOd").combobox('enable');
                    var currentIopTypeOd = '${ae.currentIopTypeOd!}';
                    $('#currentIopTypeOd').setVal(currentIopTypeOd);
                    if(currentIopTypeOd ==='05'){
                        $('.current-eyePressW-0').hide();
                        $('.current-eyePressW-1').show();
                        $('#currentIopOd1').numberspinner('disable');
                        $('#currentIopOd2').combobox('enable')
                        $('#currentIopOd2').setVal('${ae.currentIopOd!}')
                    }else{
                        $('.current-eyePressW-0').show();
                        $('.current-eyePressW-1').hide();
                        $('#currentIopOd1').numberspinner('enable');
                        $('#currentIopOd1').setVal('${ae.currentIopOd!}')
                        $('#currentIopOd2').combobox('disable');
                    }

                    $("#currentIopOdName").prop("disabled",false);
                    $('#currentIopOdName').setVal('${ae.currentIopOdName!}')

                    $("#currentIopTypeOs").combobox('enable');
                    var currentIopTypeOs = '${ae.currentIopTypeOs!}';
                    $('#currentIopTypeOs').setVal(currentIopTypeOs);

                    if(currentIopTypeOs ==='05'){
                        $('.current-eyePressWOs-0').hide();
                        $('.current-eyePressWOs-1').show();
                        $('#currentIopOs1').numberspinner('disable');
                        $('#currentIopOs2').combobox('enable')
                        $('#currentIopOs2').setVal('${ae.currentIopOs!}')
                    }else{
                        $('.current-eyePressWOs-0').show();
                        $('.current-eyePressWOs-1').hide();
                        $('#currentIopOs1').numberspinner('enable');
                        $('#currentIopOs1').setVal('${ae.currentIopOs!}')
                        $('#currentIopOs2').combobox('disable');
                    }
                    $("#currentIopOsName").prop("disabled",false);
                    $('#currentIopOsName').setVal('${ae.currentIopOsName!}')
                }
            });
            

        })
    </script>
</body>

</html>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>非计划再手术上报</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
    <style type="text/css">
        .relative{position:relative;}
        .label-wrap {
            display: inline-block;
            width: 80px; /* Adjust the width as needed */
            white-space: normal; /* Allow wrapping */
            line-height: 1.5 !important;
            margin-left: 10px !important;
        }
        .right-align {
            margin-left: 35px !important;
        }
        .textbox-width {
            width: 1000px !important;
        }
        .textbox-2h {
            height: 55px;
        }
        .readonly-color {
            background-color: #f2f2f2 !important;
        }
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
            <span class="s-title">术前申报</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">入院时间：</label>
                <input id="admissionDate" class="txt txt-validate so-date" type="text" style="width:100px" name="admissionDate" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写入院时间" value="[#if ae.admissionDate??]${ae.admissionDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            <span class="fl">
                <label class="lab-inline">非计划情况：</label>
                <input id="unplSituation" class="txt txt-validate" type="text" style="width:200px" name="unplSituation" value="非计划再手术"  readonly />
                <input class="txt" type="hidden" name="unplan" value="2"/>
            </span>
        </div>

        <div style="font-size: 14px;display: inline-block;" class="mar-l30 mar-b10 admissionVDiv">
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
        <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10 admissionIopDiv">
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

        [#if ae.reportTimes == 1 && (pageType == 0 || pageType == 1)]
            <div class="clearfix mar-l20 mar-b10">
                <button type="button" id="preOprBtn" class="btn btn-small btn-small-b btn-primary readonly" >选择首次手术信息</button>
            </div>
        [/#if]
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline right-align">目前诊断：</label>
				<textarea class="txt txt-validate adaptiveTextarea readonly-color"  dataOptions="required:true" noNull="请选择手术信息" style="width:1000px" id="currDiagnosis" name="currDiagnosis" maxlength="200" cols="200" row="10" readonly>${ae.currDiagnosis!}</textarea>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline right-align">病情摘要：</label>
				<textarea class="txt txt-validate adaptiveTextarea textbox-width"  dataOptions="required:true" noNull="请填写病情摘要"  id="condSummary" name="condSummary" maxlength="2000" cols="200" row="10">${ae.condSummary!}</textarea>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">首次手术名称：</label>
                <textarea id="firstSurgName" class="txt txt-validate adaptiveTextarea textbox-width readonly-color"  dataOptions="required:true, editable:false" noNull="请选择手术信息" type="text"  name="firstSurgName" readonly>${ae.firstSurgName!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">首次手术时间：</label>
                <input id="firstSurgDate" class="txt txt-validate readonly-color" type="text" style="width:200px" name="firstSurgDate" value="${ae.firstSurgDate}"  readonly />
            </span>
            <span class="fl">
                <label class="lab-inline">首次手术医生：</label>
                [#if "${pageType!}" == 1||"${pageType!}" == 0]
                    <select class="drop drop-doctorId w-100" style="width:100%;" id="firstSurgDoctName" name="firstSurgDoctName" value="${ae.firstSurgDoctName}" readonly></select>
                [#else]
                    <input class="txt txt-validate w-100"  type="text" name="firstSurgDoctName" value="${ae.firstSurgDoctName!}" readonly="readonly"/>
                [/#if]
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl w-200">
                <label class="lab-inline">是否植入晶体：</label>
                <label class="lab-val"><input type="radio" class="rad reason" name="isIol" value="0"   [#if '${ae.isIol }' == 0 ] checked="checked" [/#if] />否</label>
                <label class="lab-val"><input type="radio" class="rad reason" name="isIol" value="1"   [#if '${ae.isIol }' == 1 ] checked="checked" [/#if]  />是</label>
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
            <div class="fl w-300">
                <label class="lab-inline">麻醉方式:</label>
                <input id="anesMethod" class="txt txt-validate readonly-color" type="text" style="width:200px" name="anesMethod" value="${ae.anesMethod}"  readonly />
            </div>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline label-wrap">首次手术情况及术后情况：</label>
                <textarea class="txt txt-validate adaptiveTextarea textbox-2h textbox-width"  dataOptions="required:true" noNull="请填写首次手术情况及术后情况"  id="firstSurgSit" name="firstSurgSit" maxlength="2000" cols="200" row="10">${ae.firstSurgSit!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline label-wrap">再次手术原因和目的：</label>
                <textarea class="txt txt-validate adaptiveTextarea textbox-2h textbox-width"  dataOptions="required:true" noNull="请填写再次手术原因和目的" style="width:1000px" id="reopReason" name="reopReason" maxlength="2000" cols="200" row="10">${ae.reopReason!}</textarea>
            </span>
        </div>
        [#if ae.reportTimes == 1 && pageType == 0]
            <div class="clearfix mar-l20 mar-b10">
                <button type="button" id="planOprBtn" class="btn btn-small btn-small-b btn-primary readonly">选择拟再次手术信息</button>
            </div>
        [/#if]
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline" style="margin-left: -5px;">拟再次手术名称：</label>
                <textarea id="planReopName" class="txt txt-validate adaptiveTextarea textbox-width" dataOptions="required:true" type="text" style="width:200px" name="planReopName">${ae.planReopName!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline" style="margin-left: -5px;">拟再次手术时间：</label>
                <input id="planReopDate" class="txt txt-validate so-date" type="text" style="width:200px" name="planReopDate" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd HH:mm:00',type:'date'" noNull="请填写首次手术时间" value="[#if ae.planReopDate??]${ae.planReopDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            <span class="fl">
                <label class="lab-inline">拟再次手术医生：</label>
                [#if "${pageType!}" == 1||"${pageType!}" == 0]
                    <select class="drop drop-doctorId w-100"  style="width:100%;" id="planReopDoctName" name="planReopDoctName" value="${ae.planReopDoctName}"></select>
                [#else]
                    <input class="txt txt-validate w-100" type="text" name="planReopDoctName" value="${ae.planReopDoctName!}" readonly="readonly"/>
                [/#if]
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline label-wrap">手术中风险防范措施：</label>
                <textarea class="txt txt-validate adaptiveTextarea textbox-width textbox-2h"  dataOptions="required:true" noNull="请填写首次手术情况及术后情况"  id="riskPrevMeasure" name="riskPrevMeasure" maxlength="2000" cols="200" row="10">${ae.riskPrevMeasure!}</textarea>
            </span>
        </div>

       <!-- 只有进展上报时才显示下面的信息，也就是reportTimes > 1时才显示 -->
       [#if ae.reportTimes > 1]
       <h2 class="h2-title-a">
            <span class="s-title">术后情况</span>
        </h2>

        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        [#if pageType == 0]
        <div class="clearfix mar-l20 mar-b10">
           <button type="button" class="btn btn-small btn-small-b btn-primary readonly" id="postOprBtn">选择再次手术信息</button>
        </div>
        [/#if]
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline right-align">目前情况：</label>
                <textarea class="txt txt-validate adaptiveTextarea textbox-width"  dataOptions="required:true" noNull="请填写目前情况"  id="currSituation" name="currSituation" maxlength="600" cols="200" row="10">${ae.currSituation!}</textarea>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">再次手术名称：</label>
                <textarea  class="txt txt-validate adaptiveTextarea textbox-width" dataOptions="required:true"  noNull="请填写再次手术名称" id="reopSurgName" name="reopSurgName">${ae.reopSurgName!}</textarea>
            </span>
       </div>
       <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">再次手术时间：</label>
                <input id="reopSurgDate" class="txt txt-validate so-date" type="text" style="width:200px" name="reopSurgDate" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写首次手术时间" value="[#if ae.reopSurgDate??]${ae.reopSurgDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            <span class="fl">
                <label class="lab-inline">再次手术医生：</label>
                [#if "${pageType!}" == 1||"${pageType!}" == 0]
                    <select class="drop drop-doctorId w-100" data-Options="editable:false,required:true" style="width:100%;" id="reopSurgDoctName" name="reopSurgDoctName" value="${ae.reopSurgDoctName}"></select>
                [#else]
                    <input class="txt txt-validate w-100" type="text" name="reopSurgDoctName" value="${ae.reopSurgDoctName!}" readonly="readonly"/>
                [/#if]
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl w-200">
                <label class="lab-inline">患者是否知情：</label>
                <label class="lab-val"><input type="radio"  name="isConsent" value="0"    [#if '${ae.isConsent }' == 0 ] checked="checked" [/#if]  />否</label>
                <label class="lab-val"><input type="radio"  name="isConsent" value="1"   [#if '${ae.isConsent }' == 1 ] checked="checked" [/#if]   />是</label>
            </span>
            <span class="fl w-200">
                <label class="lab-inline">患者是否理解：</label>
                <label class="lab-val"><input type="radio" name="isUnderst" value="0"   [#if '${ae.isUnderst }' == 0 ] checked="checked" [/#if] />否</label>
                <label class="lab-val"><input type="radio" name="isUnderst" value="1"  [#if '${ae.isUnderst }' == 1 ] checked="checked" [/#if]  />是</label>
            </span>
            <span class="fl w-200">
                <label class="lab-inline">是否为医源性：</label>
                <label class="lab-val"><input type="radio" name="isIatrogenic" value="0"   [#if '${ae.isIatrogenic }' == 0 ] checked="checked" [/#if] />否</label>
                <label class="lab-val"><input type="radio" name="isIatrogenic" value="1"  [#if '${ae.isIatrogenic }' == 1 ] checked="checked" [/#if]  />是</label>
            </span>
            <span class="fl w-300">
                <label class="lab-inline">是否达到再次手术目的：</label>
                <label class="lab-val"><input type="radio" name="isGoalAchieved" value="0"   [#if '${ae.isGoalAchieved }' == 0 ] checked="checked" [/#if] />否</label>
                <label class="lab-val"><input type="radio" name="isGoalAchieved" value="1"  [#if '${ae.isGoalAchieved }' == 1 ] checked="checked" [/#if]  />是</label>
            </span>
        </div>
        <div style="font-size: 14px;display: inline-block;" class="mar-l30 mar-b10 postVDiv">
            <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
            术后视力
            <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 30px;color:#00A0E9">VOD</label>
                </span>
                <span class="fl w-80">
					<select class="drop drop-sl-k easyui-combobox" style="width:100%" data-Options="editable:false,required:true" name="postVodType" id="postVodType" value="${ae.postVodType!}">
						<option value="1" selected="selected">数值</option>
						<option value="2">描述</option>
					</select>
                </span>
                <span class="fl w-120">
					<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="postVod" name="postVod" value="${ae.postVod!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#postVodName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="postVodName" name="postVodName" value="${ae.postVodName!}"/>
                </span>
            </div>
            <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px; ">
				<span class="fl">
					<label class="lab-inline" style="width: 30px;color:#00A0E9">VOS</label>
				</span>
                <span class="fl w-80">
					<select class="drop drop-sl-k easyui-combobox" style="width:100%" data-Options="editable:false,required:true" name="postVosType" id="postVosType" value="${ae.postVosType!}">
						<option value="1" selected="selected">数值</option>
						<option value="2">描述</option>
					</select>
                </span>
                <span class="fl w-120">
					<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="postVos" name="postVos" value="${ae.postVos!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#postVosName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="postVosName" name="postVosName" value="${ae.postVosName!}"/>
                </span>
            </div>
        </div>
        <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10 postIopDiv">
            <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
            术后眼压
            <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                </span>
                <div class="fl">
                    <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="postIopTypeOd" id="postIopTypeOd" data-options="valueField:'valueCode',textField:'valueDesc',required:true" value="${ae.postIopTypeOd!}">
                    </select>
                </div>
                <div class="post-eyePressW-0 fl relative">
                    <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="postIopOd" id="postIopOd1" data-options="min:1,max:100,precision:1,required:true" value="${ae.postIopOd!}"/>
                    <em class="em-at em-dropAt">mmHg</em>
                </div>
                <div class="post-eyePressW-1 fl">
                    <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="postIopOd" id="postIopOd2" value="${ae.postIopOd!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#postIopOdName').val(record.valueDesc);
					}">
                    </select>
                    <input class="txt" type="hidden" id="postIopOdName" name="postIopOdName" value="${ae.postIopOdName!}"/>
                </div>
            </div>
            <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
				<span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
				</span>
                <div class="fl">
                    <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="postIopTypeOs" id="postIopTypeOs" data-options="valueField:'valueCode',textField:'valueDesc',required:true" value="${ae.postIopTypeOs!}">
                    </select>
                </div>
                <div class="post-eyePressWOs-0 fl relative">
                    <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="postIopOs" id="postIopOs1" data-options="min:1,max:100,precision:1,required:true" value="${ae.postIopOs!}"/>
                    <em class="em-at em-dropAt">mmHg</em>
                </div>
                <div class="post-eyePressWOs-1 fl">
                    <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="postIopOs" id="postIopOs2" value="${ae.postIopOs!}"
                            data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#postIopOsName').val(record.valueDesc);
					}">
                    </select>
                    <input class="txt" type="hidden" id="postIopOsName" name="postIopOsName" value="${ae.postIopOsName!}"/>
                </div>
            </div>
        </div>
       [/#if]
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline label-wrap">患者及家属诉求：</label>
                <textarea class="txt txt-validate adaptiveTextarea textbox-width textbox-2h"  dataOptions="required:true" noNull="请填写患者及家属诉求"  id="patientAppeals" name="patientAppeals" maxlength="2000" cols="200" row="10">${ae.patientAppeals!}</textarea>
            </span>
        </div>
    </form>
    <form class="soform formA form-validate">
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom.ftl"]

    </form>
    <script id="chooseOprTem" type="text/html">
        <div class="cont-grid" style="flex: 1;border-left: 5px solid #dddddd;">
            <div id="oprGrid"></div>
        </div>
    </script>
</div>
<div class="tabCont tabCont-1 tabContHide tab-flow"></div>
<div class="tabCont tabCont-2 tabContHide tab-report"></div>
<div class="tabCont tabCont-3 tabContHide tab-img pad-t10"></div>
<div class="tabCont tabCont-4 tabContHide tab-review"></div>
[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(["eventReview", "eventTableGroup","eventFlowchart","uploadImages","eventImage",'myupload','pub', "btnSearch"], function (eventReview, eventTableGroup,eventFlowchart,uploadImages,eventImage) {
        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]
        window.afterSerachInpnumber=function (){
            // 住院接口数据
            var inDomain=window.inDomain;

            // 入院时间
            $("#admissionDate").setVal(!!inDomain?inDomain.inpTime.substr(0,10):"");
            // 入院视力vod   类型（OD） 1数值 2描述
            $('#admissionVodType').setVal(!!inDomain.inpVisionOdType?inDomain.inpVisionOdType:"");
            // 入院视力vos   类型（OS） 1数值 2描述
            $('#admissionVosType').setVal(!!inDomain.inpVisionOsType?inDomain.inpVisionOsType:"");
            // 入院视力vod
            $('#admissionVod').setVal(!!inDomain.inpVisionOd?inDomain.inpVisionOd:"");
            // 入院视力vos
            $('#admissionVos').setVal(!!inDomain.inpVisionOs?inDomain.inpVisionOs:"");
            //入院眼压类型OD
            $('#admissionIopTypeOd').setVal(!!inDomain.inpTonometryOdType?inDomain.inpTonometryOdType:"");
            //入院眼压值OD
            $('#admissionIopOd1').setVal(!!inDomain.inpIopOd?inDomain.inpIopOd:"");
            $('#admissionIopOd2').setVal(!!inDomain.inpIopOd?inDomain.inpIopOd:"");
            //入院眼压类型OS
            $('#admissionIopTypeOs').setVal(!!inDomain.inpTonometryOsType?inDomain.inpTonometryOsType:"");
            //入院眼压值OS
            $('#admissionIopOs1').setVal(!!inDomain.inpIopOs?inDomain.inpIopOs:"");
            $('#admissionIopOs2').setVal(!!inDomain.inpIopOs?inDomain.inpIopOs:"");
        }

        //保存前执行事件 false中断保存
        window.beforeSubmit = function(){
            //判断患者是否知情、患者是否理解、是否为医源性、是否达到再次手术目的等是否选中
            var requiredFields = [];
            if('${ae.reportTimes}' > 1){
                requiredFields.push({ name: 'isConsent', message: '请选择患者是否知情' });
                requiredFields.push({ name: 'isUnderst', message: '请选择患者是否理解' });
                requiredFields.push({ name: 'isIatrogenic', message: '请选择是否为医源性' });
                requiredFields.push({ name: 'isGoalAchieved', message: '请选择是否达到再次手术目的'});
            }

            for (var i = 0; i < requiredFields.length; i++) {
                var field = requiredFields[i];
                if ($("input[name='" + field.name + "']:checked").val() == null) {
                    $pop.alert(field.message);
                    return false; // 终止 beforeSubmit 函数
                }
            }

        }

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
        $('.drop-doctorId').combobox({
            //首拼码检索
            limitToList:true,
            reversed:true,
            panelMaxHeight:200,
            panelHeight:'auto',
            filter: function(q, row){
                return (row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1  || row['name']&&row['name'].indexOf(q) >-1 )
            },
            onSelect : function (record) {
            },
            onLoadSuccess: function () {
                $("#firstSurgDoctName").combobox('setValue', '${ae.firstSurgDoctName}');
                $("#planReopDoctName").combobox('setValue', '${ae.planReopDoctName}');
                $("#reopSurgDoctName").combobox('setValue', '${ae.reopSurgDoctName}');
            },
            loader : function(param, success, error) {
                $.ajax({
                    type : 'post',
                    url: '${base}/ui/sys/staff/getHospStaff?_easyui_=COMB',
                    dataType : 'json',
                    contentType : 'application/json;charset=utf-8', // 设置请求头信息
                    data : JSON.stringify(param),
                    success : function(result) {
                        //查询成功，将医生信息添加到集合中， 以便新增操作时，直接给医生列表绑定值
                        result.unshift({id:'',name:'',firstSpell:''});
                        success(result);
                    }
                });
            },
            queryParams:{staffType:1,isDistinct:true},
            method : "post",
            valueField: "name",
            textField: "name"
        });

        $("#preOprBtn").click(function(){
            popOprTem("pre");
        });

        $("#postOprBtn").click(function(){
            popOprTem("post");
        });

        $("#planOprBtn").click(function(){
            popOprTem("plan");
        });



        function popOprTem(oprType) {
            let oprPopTem = $pop.popTemForm({
                title: "手术选择",
                temId: 'chooseOprTem',
                zIndex: 2,
                area: ['800px', '520px'],
                end: function () {

                },
                onPop: function ($p) {
                    $grid.newGrid("#oprGrid",{
                        pagination : false,
                        fitColumns : true,
                        height: '480x',
                        method:'post',
                        columns:[[
                            {title:'id',field:'id',checkbox:false, hidden:true},
                            {title:'手术名称',field:'oprName',width:80},
                            {title:'是否非计划',field:'isUnplanned',width:40, formatter:function(value,row,index){
                                    return value == 1 ? '是' : '否';
                                }},
                            {title:'手术时间',field:'oprDate',width:80,format:'yyyy-MM-dd HH:mm:00'},
                            {title:'申请单号',field:'applyNumber',width:120}
                        ]],
                        onLoadSuccess : function (data) {
                        },
                        onSelect : function (val,record){
                            //选择好相应的行后，把行中的数据赋值给下面相应的输入框，然后关闭弹出框
                            if(oprType == 'pre') {
                                //获取诊断信息
                                $ajax.post({
                                    url: '${base}/ui/service/biz/amcs/adverse/opr/getByInpNumberAndApplyNumber?applyNumber=' + record.applyNumber +'&inpNumber=' + $("#patientNo").val(),
                                    success: function (rst) {
                                        $("#currDiagnosis").val(rst.data.diag);
                                    }
                                });

                                //根据inpNumber和applyNumber查询手术信息
                                $ajax.post({
                                    url:'${base}/ui/service/biz/amcs/adverse/opr/getDetailByApplyNumber?applyNumber=' + record.applyNumber,
                                    success:function(rst){
                                        let operData = rst.data.oper;
                                        //设置首次手术信息
                                        $("#firstSurgName").val(operData.details);
                                        $("#firstSurgDate").val(operData.operationEndStr);
                                        $("#firstSurgDoctName").combobox('setValue', operData.operDoctName);
                                        $("#anesMethod").val(operData.anesthesiaModeNames);

                                    }
                                })
                            }else if(oprType == 'plan'){
                                $ajax.post({
                                    url:'${base}/ui/service/biz/amcs/adverse/opr/getDetailByApplyNumber?applyNumber=' + record.applyNumber,
                                    success:function(rst){
                                        let planData = rst.data.planOper;
                                        //设置首次手术信息
                                        $("#planReopName").val(planData.details);
                                        $("#planReopDate").val(planData.operDateStr);
                                        $("#planReopDoctName").combobox('setValue', planData.operDoctName);

                                    }
                                })
                            } else{
                                 $ajax.post({
                                    url:'${base}/ui/service/biz/amcs/adverse/opr/getDetailByApplyNumber?applyNumber=' + record.applyNumber,
                                    success:function(rst){
                                         let operData = rst.data.oper;
                                        $("#reopSurgName").val(operData.details);
                                        $("#reopSurgDate").val(operData.operationEndStr);
                                        $("#reopSurgDoctName").combobox('setValue', operData.operDoctName);
                                    }
                                 });
                            }

                            $pop.close(oprPopTem);
                        },
                        onLoadSuccess : function (data) {
                        },
                        url:'${base}/ui/service/biz/amcs/adverse/opr/selectOprQueryListPage?inpNumber=' + $("#patientNo").val(),
                        offset :-30
                    });
                }
            });
        };

        var param = { paraType: ['far_eyesight', 'near_eyesight', 'eyesight_desc', 'iop_type', 'finger_tonometry'] };
        var farEyesight = {};
        var nearEyesight = {};
        var iopType = {};
        var fingerTonometry = {};

        $ajax.postSync('${base}/ui/base/dict/getMoreList', param, false, false).done(function (rst) {
            var dict = rst.data;
            farEyesight = [dict.far_eyesight, dict.eyesight_desc];
            nearEyesight = dict.near_eyesight;
            iopType = dict.iop_type;
            fingerTonometry = dict.finger_tonometry;
        });

        function setFieldValue(field) {
            if (field.value) {
                $(field.id).setVal(field.value);
            }
        }

        var setValue = function () {
            // Add any necessary value setting logic here
            var fields = [
                // 入院视力及入院眼压
                { id: '#admissionVodType', value: '${ae.admissionVodType}' },
                { id: '#admissionVod', value: '${ae.admissionVod!}' },
                { id: '#admissionVodName', value: '${ae.admissionVodName!}' },
                { id: '#admissionVosType', value: '${ae.admissionVosType!}' },
                { id: '#admissionVos', value: '${ae.admissionVos!}' },
                { id: '#admissionVosName', value: '${ae.admissionVosName!}' },
                { id: '#admissionIopTypeOd', value: '${ae.admissionIopTypeOd!}' },
                { id: '#admissionIopTypeOs', value: '${ae.admissionIopTypeOs!}' },
                { id: '#admissionIopOd1', value: '${ae.admissionIopOd!}' },
                { id: '#admissionIopOd2', value: '${ae.admissionIopOd!}' },
                { id: '#admissionIopOs1', value: '${ae.admissionIopOs!}' },
                { id: '#admissionIopOs2', value: '${ae.admissionIopOs!}' }
            ];

            // 增加术后视力及术后眼压
            if('${ae.reportTimes}' > 1) {
                fields = fields.concat([
                    { id: '#postVodType', value: '${ae.postVodType}' },
                    { id: '#postVod', value: '${ae.postVod!}' },
                    { id: '#postVodName', value: '${ae.postVodName!}' },
                    { id: '#postVosType', value: '${ae.postVosType}' },
                    { id: '#postVos', value: '${ae.postVos!}' },
                    { id: '#postVosName', value: '${ae.postVosName!}' },
                    { id: '#postIopTypeOd', value: '${ae.postIopTypeOd!}' },
                    { id: '#postIopTypeOs', value: '${ae.postIopTypeOs!}' },
                    { id: '#postIopOd1', value: '${ae.postIopOd!}' },
                    { id: '#postIopOd2', value: '${ae.postIopOd!}' },
                    { id: '#postIopOs1', value: '${ae.postIopOs!}' },
                    { id: '#postIopOs2', value: '${ae.postIopOs!}' }
                ]);
            }

            fields.forEach(setFieldValue);
            // 处理入院眼压
            handleIopType('#admissionIopTypeOd',  '#admissionIopOd1', '#admissionIopOd2', '.admission-eyePressW-0', '.admission-eyePressW-1', '${ae.admissionIopOd!}');
            handleIopType('#admissionIopTypeOs',  '#admissionIopOs1', '#admissionIopOs2', '.admission-eyePressWOs-0', '.admission-eyePressWOs-1', '${ae.admissionIopOs!}');
            // 处理术后眼压
            if('${ae.reportTimes}' > 1){
                handleIopType('#postIopTypeOd', '#postIopOd1', '#postIopOd2', '.post-eyePressW-0', '.post-eyePressW-1', '${ae.postIopOd!}');
                handleIopType('#postIopTypeOs', '#postIopOs1', '#postIopOs2', '.post-eyePressWOs-0', '.post-eyePressWOs-1', '${ae.postIopOs!}');
            }

        };

        function handleIopType(iopTypeSelector, iop1, iop2, eyePressW0, eyePressW1, iopValue) {
            var iopType = $(iopTypeSelector).combobox('getValue');
            //为空时直接返回
            if (!iopType) {
                return;
            }
            if (iopType === '05') {
                $(eyePressW0).hide();
                $(eyePressW1).show();
                $(iop1).numberspinner('disable');
                $(iop2).combobox('enable');
                if (iopValue) {
                    $(iop2).setVal(iopValue);
                }
            } else {
                $(eyePressW0).show();
                $(eyePressW1).hide();
                $(iop1).numberspinner('enable');
                if (iopValue) {
                    $(iop1).numberspinner('setValue', iopValue);
                }
                $(iop2).combobox('disable');
            }
            if (iopType === '96' || iopType === '97' || iopType === '98' || iopType === '99') {
                $(iop1).numberspinner('disableValidation');
            } else {
                $(iop1).numberspinner('enableValidation');
            }
        }

        var baseExam = {
            init: function () {
                this.loadComboboxData();
                this.initFarVision();
                this.initEyePress();
                this.initComponent();
                setValue();
            },
            loadComboboxData: function () {
                $('.farEyesight').combobox('loadData', farEyesight[0]);
                $('.drop-eyePressExamType').combobox('loadData', iopType);
                $('.drop-pressExam').combobox('loadData', fingerTonometry);
                $('.drop-eyePressExamType').combobox('setValue', '01'); // Default value for eye pressure exam
            },
            initFarVision: function () {
                $('.drop-sl-k').combobox({
                    onChange: function (val) {
                        if (!val) {
                            $(this).combobox('setValue', '1'); // Set default value if empty
                        } else {
                            var $v = $(this).parents('.p6').find('.drop-sl-v');
                            $v.combobox('setValue', ''); // Clear value
                            $v.combobox('loadData', farEyesight[val - 1]); // Load data based on selection
                        }
                    }
                });
            },
            initEyePress: function () {
                //处理入院眼压
                handleIopType('#admissionIopTypeOd', '#admissionIopOd1', '#admissionIopOd2', '.admission-eyePressW-0', '.admission-eyePressW-1', '${ae.admissionIopOd!}');
                handleIopType('#admissionIopTypeOs', '#admissionIopOs1', '#admissionIopOs2', '.admission-eyePressWOs-0', '.admission-eyePressWOs-1', '${ae.admissionIopOs!}');

                //当改变眼压类型时，处理眼压输入框的显示和隐藏,用handleIopType方法
                $('#admissionIopTypeOd').combobox({
                    onChange: function() {
                        handleIopType('#admissionIopTypeOd', '#admissionIopOd1', '#admissionIopOd2', '.admission-eyePressW-0', '.admission-eyePressW-1', '${ae.admissionIopOd!}');
                    }
                });
                $('#admissionIopTypeOs').combobox({
                    onChange: function() {
                        handleIopType('#admissionIopTypeOs', '#admissionIopOs1', '#admissionIopOs2', '.admission-eyePressWOs-0', '.admission-eyePressWOs-1', '${ae.admissionIopOs!}');
                    }
                });

                //处理术后眼压,只有进展上报时才显示
                if('${ae.reportTimes}' > 1){
                    handleIopType('#postIopTypeOd',  '#postIopOd1', '#postIopOd2', '.post-eyePressW-0', '.post-eyePressW-1', '${ae.postIopOd!}');
                    handleIopType('#postIopTypeOs',  '#postIopOs1', '#postIopOs2', '.post-eyePressWOs-0', '.post-eyePressWOs-1', '${ae.postIopOs!}');
                    $('#postIopTypeOd').combobox({
                        onChange: function() {
                            handleIopType('#postIopTypeOd', '#postIopOd1', '#postIopOd2', '.post-eyePressW-0', '.post-eyePressW-1', '${ae.postIopOd!}');
                        }
                    });
                    $('#postIopTypeOs').combobox({
                        onChange: function() {
                            handleIopType('#postIopTypeOs', '#postIopOs1', '#postIopOs2', '.post-eyePressWOs-0', '.post-eyePressWOs-1', '${ae.postIopOs!}');
                        }
                    });
                }
            },
            initComponent: function () {
                //basicid不为空且上报次数为1，显示打印按钮
                if('${ae.basicId!}' && '${ae.reportTimes}' == 1){
                    $(".fix-print-btn").removeClass("hidden");
                }

            }
        };

        $(function () {
            baseExam.init();
            if(!'${ae.id}'){
                if(!$.isEmptyObject('${ae.patientNo}')){
                    queryPatientInfo('${ae.patientNo}');
                }
            }
        });
    });
</script>
</body>

</html>
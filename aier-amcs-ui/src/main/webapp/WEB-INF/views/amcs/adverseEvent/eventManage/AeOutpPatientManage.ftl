<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>门诊患者不良事件(手术患者)</title>
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
                <label class="lab-inline">门诊时间：</label>
                <input id="visitDate" class="txt txt-validate so-date" autocomplete="off" type="text" style="width:100px" name="visitDate" dataOptions="editable:false,required:ture, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写入院时间" value="[#if ae.visitDate??]${ae.visitDate?date("yyyy-MM-dd")}[/#if]"  />
            </span>
            <span class="fl">
                <label class="lab-inline">非计划情况：</label>
				[@ui_select id="unplan"  class="easyui-combobox"  name="unplan"  tag="@ae@unplan" style="width:120px;"  dataOptions="limitToList:true,required:false,reversed:true,editable:false" filterkey="firstSpell" value="${ae.unplan!}" /]
            </span>
        </div>
		
		<div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">门诊诊断：</label>
                <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写入门诊断" style="width:1000px" id="outpDiagName" name="outpDiagName" maxlength="500" cols="200" row="10">${ae.outpDiagName!}</textarea>
            </span>
        </div>
		
        <div style="font-size: 14px;display: inline-block;" class="mar-l30 mar-b10">
            <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
            门诊视力
            <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 30px;color:#00A0E9">VOD</label>
                </span>
				<span class="fl w-80">
					<select class="drop drop-sl-k easyui-combobox" style="width:100%" data-Options="editable:false,required:true" name="outpVodType" id="outpVodType" value="${ae.outpVodType!}">
						<option value="1" selected="selected">数值</option>
						<option value="2">描述</option>
					</select>
                </span>
                <span class="fl w-120">
					<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="outpVod" name="outpVod" value="${ae.outpVod!}"
						data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#outpVodName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="outpVodName" name="outpVodName" value="${ae.outpVodName!}"/>
                </span>
            </div>
            <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px; ">
				<span class="fl">
					<label class="lab-inline" style="width: 30px;color:#00A0E9">VOS</label>
				</span>
				<span class="fl w-80">
					<select class="drop drop-sl-k easyui-combobox" style="width:100%" data-Options="editable:false,required:true" name="outpVosType" id="outpVosType" value="${ae.outpVosType!}">
						<option value="1" selected="selected">数值</option>
						<option value="2">描述</option>
					</select>
                </span>
                <span class="fl w-120">
					<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="outpVos" name="outpVos" value="${ae.outpVos!}"
						data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#outpVosName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="outpVosName" name="outpVosName" value="${ae.outpVosName!}"/>
                </span>
            </div>
        </div>
        <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
            <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
            门诊眼压
            <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
                <span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                </span>
				<div class="fl">
					<select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="outpIopTypeOd" id="outpIopTypeOd" data-options="valueField:'valueCode',textField:'valueDesc',required:true" value="${ae.outpIopTypeOd!}">
					</select>
				</div>
				<div class="outp-eyePressW-0 fl relative">
					<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="outpIopOd" id="outpIopOd1" data-options="min:1,max:100,precision:1,required:true" value="${ae.outpIopOd!}"/>
					<em class="em-at em-dropAt">mmHg</em>
				</div>
				<div class="outp-eyePressW-1 fl">
					<select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="outpIopOd" id="outpIopOd2" value="${ae.outpIopOd!}"
						data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#outpIopOdName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="outpIopOdName" name="outpIopOdName" value="${ae.outpIopOdName!}"/>
				</div>
            </div>
            <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
				<span class="fl">
					<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
				</span>
				<div class="fl">
					<select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="outpIopTypeOs" id="outpIopTypeOs" data-options="valueField:'valueCode',textField:'valueDesc',required:true" value="${ae.outpIopTypeOs!}">
					</select>
				</div>
				<div class="outp-eyePressWOs-0 fl relative">
					<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="outpIopOs" id="outpIopOs1" data-options="min:1,max:100,precision:1,required:true" value="${ae.outpIopOs!}"/>
					<em class="em-at em-dropAt">mmHg</em>
				</div>
				<div class="outp-eyePressWOs-1 fl">
					<select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="outpIopOs" id="outpIopOs2" value="${ae.outpIopOs!}"
						data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,required:true,clearIcon:true,filter:function (q,row){
						var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
						var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
						return l || n;
					  },onSelect:function(record){
						$('#outpIopOsName').val(record.valueDesc);
					}">
					</select>
					<input class="txt" type="hidden" id="outpIopOsName" name="outpIopOsName" value="${ae.outpIopOsName!}"/>
				</div>
            </div>
        </div>
		
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">拟施手术：</label>
                <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" id="intendedOperation" name="intendedOperation" maxlength="500" cols="200" row="10">${ae.intendedOperation!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline">实施手术：</label>
                <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" id="implementOperation" name="implementOperation" maxlength="500" cols="200" row="10">${ae.implementOperation!}</textarea>
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
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-120">目前情况：</label>
				<textarea class="txta txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写目前情况" id="currSituation" name="currSituation" maxlength="500" cols="200" row="10">${ae.currSituation!}</textarea>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-120">患者及家属诉求：</label>
				<textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写患者及家属诉求" id="patientAppeals" name="patientAppeals" maxlength="500" cols="200" row="10">${ae.patientAppeals!}</textarea>
            </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-120">事件经过描述：</label>
				<textarea class="txta txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写事件经过描述" id="eventText" name="eventText" maxlength="5000" cols="200" row="10">${ae.eventDesc!}${ae.eventDescOne!}${ae.eventDescTwo!}</textarea>
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
        requirejs(["eventReview", "eventTableGroup","eventFlowchart","uploadImages","eventImage",'myupload','pub'], function (eventReview, eventTableGroup,eventFlowchart,uploadImages,eventImage) {
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
        	**/
	        window.afterSerachPatient=function (){
	            //门诊接口数据
	            var opDomain=window.opDomain;
	
	            $("#visitDate").setVal(!!opDomain.regDate?opDomain.regDate.substr(0,10):"")
	            $('#outpDiagName').setVal(!!opDomain.diagName?opDomain.diagName:"")
	            $('#outpVod').setVal(!!opDomain.dvaOd?opDomain.dvaOd:"")
	            $('#outpVos').setVal(!!opDomain.dvaOs?opDomain.dvaOs:"")
	
	            $('#outpIopOd1').setVal(!!opDomain.iopOs?opDomain.iopOs:"")
	            $('#outpIopOs1').setVal(!!opDomain.iopOd?opDomain.iopOd:"")

				//住院接口数据
				var inDomain=window.inDomain;
				if(inDomain){
					// 切口类别
					$("#cutType").combobox('setValue', !!inDomain.cutType?inDomain.cutType:"");
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
				$('#outpVodType').setVal('${ae.outpVodType!}')
				$('#outpVod').setVal('${ae.outpVod!}')
				$('#outpVodName').setVal('${ae.outpVodName!}')
				$('#outpVosType').setVal('${ae.outpVosType!}')
				$('#outpVos').setVal('${ae.outpVos!}')
				$('#outpVosName').setVal('${ae.outpVosName!}')
				
				$('#outpIopTypeOd').setVal('${ae.outpIopTypeOd!}')
				$('#outpIopOd1').setVal('${ae.outpIopOd!}')
				$('#outpIopOd2').setVal('${ae.outpIopOd!}')
				$('#outpIopOdName').setVal('${ae.outpIopOdName!}')
				$('#outpIopTypeOs').setVal('${ae.outpIopTypeOs!}')
				$('#outpIopOs1').setVal('${ae.outpIopOs!}')
				$('#outpIopOs2').setVal('${ae.outpIopOs!}')
				$('#outpIopOsName').setVal('${ae.outpIopOsName!}')
				
				$('#currentVodType').setVal('${ae.currentVodType!}')
				$('#currentVod').setVal('${ae.currentVod!}')
				$('#currentVodName').setVal('${ae.currentVodName!}')
				$('#currentVosType').setVal('${ae.currentVosType!}')
				$('#currentVos').setVal('${ae.currentVos!}')
				$('#currentVosName').setVal('${ae.currentVosName!}')
				
				$('#currentIopTypeOd').setVal('${ae.currentIopTypeOd!}')
				$('#currentIopOd1').setVal('${ae.currentIopOd!}')
				$('#currentIopOd2').setVal('${ae.currentIopOd!}')
				$('#currentIopOdName').setVal('${ae.currentIopOdName!}')
				$('#currentIopTypeOs').setVal('${ae.currentIopTypeOs!}')
				$('#currentIopOs1').setVal('${ae.currentIopOs!}')
				$('#currentIopOs2').setVal('${ae.currentIopOs!}')
				$('#currentIopOsName').setVal('${ae.currentIopOsName!}')

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
					$('#outpIopTypeOd').combobox({
						onChange : function (val){
							if(val ==='05'){
								$('.outp-eyePressW-0').hide();
								$('.outp-eyePressW-1').show();
								$('#outpIopOd1').numberspinner('disable');
								$('#outpIopOd2').combobox('enable')
							}else{
								$('.outp-eyePressW-0').show();
								$('.outp-eyePressW-1').hide();
								$('#outpIopOd1').numberspinner('enable');
								$('#outpIopOd2').combobox('disable');
							}
							// "拒测","测不出", "病情原因未测", "眼球缺如" 时校验取消
							if( val ==='96' || val ==='97' || val ==='98' || val ==='99' ){
								$('#outpIopOd1').numberspinner('disableValidation');
							}else{
								$('#outpIopOd1').numberspinner('enableValidation');
							}
							$form.formAEnterFun();
						}
					});
					$('#outpIopTypeOs').combobox({
						onChange : function (val){
							if(val ==='05'){
								$('.outp-eyePressWOs-0').hide();
								$('.outp-eyePressWOs-1').show();
								$('#outpIopOs1').numberspinner('disable');
								$('#outpIopOs2').combobox('enable')
							}else{
								$('.outp-eyePressWOs-0').show();
								$('.outp-eyePressWOs-1').hide();
								$('#outpIopOs1').numberspinner('enable');
								$('#outpIopOs2').combobox('disable');
							}
							// "拒测","测不出", "病情原因未测", "眼球缺如" 时校验取消
							if( val ==='96' || val ==='97' || val ==='98' || val ==='99' ){
								$('#outpIopOs1').numberspinner('disableValidation');
							}else{
								$('#outpIopOs1').numberspinner('enableValidation');
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
               		$('.drop-eyePressExamType').combobox('setValue','01');//眼压检查默认第一个值
               		if(!$.isEmptyObject('${ae.patientNo}')){
						queryPatientInfo('${ae.patientNo}');
					}
            	}
			});

        })
    </script>
</body>

</html>
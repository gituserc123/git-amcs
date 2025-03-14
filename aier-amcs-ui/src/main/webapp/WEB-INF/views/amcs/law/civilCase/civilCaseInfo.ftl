<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>民事案件信息录入</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <link rel="stylesheet" type="text/css" href="${base}/static/js/app/eventTab/eventFlowchart.css">
    <style>
        .s-preview,.s-op-del-att {
            display: inline-block;
            max-width: 200px;      /* 根据实际需要调整 */
            overflow: hidden;
            text-overflow: ellipsis;
            vertical-align: middle;
            font-size: 16px;
        }
        /* 自定义滚动条样式 */
        [class^="attach-div"]::-webkit-scrollbar {
            height: 5px; /* 设置滚动条高度 */
        }

        /* 滚动条轨道 */
        [class^="attach-div"]::-webkit-scrollbar-track {
            background: #f1f1f1; /* 轨道背景色 */
            border-radius: 3px; /* 圆角 */
        }

        /* 滚动条滑块 */
        [class^="attach-div"]::-webkit-scrollbar-thumb {
            background: #888; /* 滑块颜色 */
            border-radius: 3px; /* 圆角 */
        }

        /* 鼠标悬停时滑块颜色 */
        [class^="attach-div"]::-webkit-scrollbar-thumb:hover {
            background: #555; /* 滑块悬停颜色 */
        }
    </style>
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
</head>
<body>
[#include "/WEB-INF/views/amcs/law/common/tab.ftl"]
<div class="tabCont tabCont-0">
<form id="civilCaseForm" class="soform formA form-validate pad-t10 commonDataForm" data-opt="{beforeCallback:'submitCivilCaseForm'}">
    <input type="hidden" id="id" name="id" value="${bizId!}" />
    <input type="hidden" id="infoUrl" name="infoUrl" value="${base}/ui/amcs/law/civilCase/info" />
    <input type="hidden" id="saveUrl" name="saveUrl" value="${base}/ui/amcs/law/civilCase/save" />
    <input type="hidden" id="initCommitUrl" name="initCommitUrl" value="${base}/ui/amcs/law/civilCase/initCommit" />
    <input type="hidden" id="flowCommitUrl" name="flowCommitUrl" value="${base}/ui/amcs/law/civilCase/flowCommit" />
    <input type="hidden" id="bizType" name="bizType" value="1" />
    <input type="hidden" id="bizCode" name="bizCode" value="CIVIL_CASE" />
    <input type="hidden" id="tabWindowTitle" name="tabWindowTitle" value="民事诉讼仲裁案" />

    <!-- 当事人基本信息 -->
    <h2 class="h2-title-a" style="width: 50%;">
        <span class="s-title" style="padding-right: 12px;">当事人基本信息</span>
        [#if curNodeName != ''] <span style="display: inline-block; color: red;">当前审批节点:${curNodeName!}</span> [/#if]
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">原告（申请人）全称：</label>
                <input class="txt txt-validate" type="text" id="plaintiffName" name="plaintiffName" value="${bizEntity.plaintiffName!}" autocomplete="off" data-options="required:true" noNull="请填写原告全称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">被告（被申请人）全称：</label>
                <input class="txt txt-validate" type="text" id="defendantName" name="defendantName" value="${bizEntity.defendantName!}" autocomplete="off" data-options="required:true" noNull="请填写被告全称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">机构名称：</label>
                <input class="txt txt-validate" type="text" id="instName" name="instName" value="${bizEntity.instName!}" autocomplete="off" data-options="required:true" noNull="请填写机构名称"/>
            </div>
        </div>
    </div>
    <div class="row" style="margin-bottom: 2px;display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.1%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">案涉对方主体企业法人营业执照/自然人身份证：</label>
        </div>
        <div style="flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton1" class="btn s-op-upload" rel="business-license" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-business-license"  rel="案涉对方主体企业法人营业执照/自然人身份证" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;">
        </div>
    </div>
    <!-- 案件基本情况 -->
    <h2 class="h2-title-a">
        <span class="s-title">案件基本情况</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">案件类别：</label>
                [@ui_select  uiShowDefault=false id="caseCategory" name="caseCategory" tag="@law@case_category"  style="width:65%;" class="easyui-combobox" dataOptions="required:true,
                    onChange:((v)=>{
                        if(v == '1') {
                            $('.litigationPhase-div').css('visibility','visible');
                            $('.litigationPhase-div').show();
                            $('.arbitrationPhase-div').css('visibility','hidden');
                            $('#litigationPhase').combobox('enableValidation');
                            $('#arbitrationPhase').combobox('disableValidation');
                        } else if(v == '2') {
                            $('.litigationPhase-div').css('visibility','hidden');
                            $('.litigationPhase-div').hide();
                            $('.arbitrationPhase-div').css('visibility','visible');
                            $('#litigationPhase').combobox('disableValidation');
                            $('#arbitrationPhase').combobox('enableValidation');
                        } else {
                            $('.litigationPhase-div').css('visibility','hidden');
                            $('.arbitrationPhase-div').css('visibility','hidden');
                            $('#litigationPhase').combobox('disableValidation');
                            $('#arbitrationPhase').combobox('disableValidation');
                        }
                    })" filterkey="firstSpell"/]
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">案件类型：</label>
                [@ui_select  uiShowDefault=false id="caseTypeOne" name="caseTypeOne" tag="@law@case_type_one"  style="width:85%;" class="easyui-combobox" dataOptions="required:true,
                    onChange:((v)=>{
                        $ajax.postSync('${base}/ui/amcs/law/dictType/selectSubDicts?typeCode=case_type_one&valueCode=' + v,null,false,false).done(function (rst) {
                            $('#caseTypeTwo').combobox('loadData', rst);
                        });
                        let caseTypeTwo = $('#caseTypeTwo').combobox('getValue');
                        if(v=='9' && caseTypeTwo=='5'){
                            $('#caseTypeTwoDesc').show();
                        }else{
                            $('#caseTypeTwoDesc').hide();
                        }
                    })" filterkey="firstSpell"/]
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">案由：</label>
                <select class="drop easyui-combobox" style="width:45%;" name="caseTypeTwo" id="caseTypeTwo" data-options="valueField:'valueCode',textField:'valueDesc',editable:false, required:true, value:'${bizEntity.caseTypeTwo}',
                    onChange:function(v){
                        let caseTypeOne = $('#caseTypeOne').combobox('getValue');
                        if(caseTypeOne=='9' && v=='5'){
                            $('#caseTypeTwoDesc').show();
                        }else{
                            $('#caseTypeTwoDesc').hide();
                        }
                    }">
                </select>
                <input class="txt txt-validate hide" style="width:50%;" type="text" id="caseTypeTwoDesc" name="caseTypeTwoDesc" placeholder="请填写其他" >${ae.caseTypeTwoDesc!}</input>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">案号：</label>
                <input class="txt txt-validate" type="text" id="caseNo" name="caseNo" value="${bizEntity.caseNo!}" autocomplete="off" data-options="required:true" noNull="请填写案号"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">涉案金额：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="involvedAmount" name="involvedAmount"
                       value="${bizEntity.involvedAmount!}" validType="maxlength[10]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/><em class="em-at em-dropAt">元</em>
            </div>
        </div>
        <div class="p6 litigationPhase-div" style="visibility: hidden;position: absolute;right: 0px">
            <div class="item-one solab-l">
                <label class="lab-item">诉讼阶段：</label>
                [@ui_select  uiShowDefault=false id="litigationPhase" name="litigationPhase" tag="@law@litigation_phase"  style="width:44%;" class="easyui-combobox" dataOptions="required:true,
                    onChange:function(v){
                        if( v=='5'){
                            $('#litigationPhaseDesc').show();
                        }else{
                            $('#litigationPhaseDesc').hide();
                        }
                    }" filterkey="firstSpell"/]
                <input class="txt txt-validate hide" style="width:49%;" type="text" id="litigationPhaseDesc" name="litigationPhaseDesc" placeholder="请填写其他" >${ae.litigationPhaseDesc!}</input>
            </div>
        </div>
        <div class="p6 arbitrationPhase-div" style="visibility: hidden;position: absolute;right: 0px">
            <div class="item-one solab-l">
                <label class="lab-item">仲裁阶段：</label>
                [@ui_select  uiShowDefault=false id="arbitrationPhase" name="arbitrationPhase" tag="@law@arbitration_phase"  style="width:44%;" class="easyui-combobox" dataOptions="required:true,
                    onChange:function(v){
                        if( v=='3'){
                            $('#arbitrationPhaseDesc').show();
                        }else{
                            $('#arbitrationPhaseDesc').hide();
                        }
                    }" filterkey="firstSpell"/]
                <input class="txt txt-validate hide" style="width:49%;" type="text" id="arbitrationPhaseDesc" name="arbitrationPhaseDesc" placeholder="请填写其他" >${ae.arbitrationPhaseDesc!}</input>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">案情简介：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="caseDesc" name="caseDesc" placeholder="请填写案情简介">${bizEntity.caseDesc!}</textarea>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">诉讼进展：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="litigationProgress" name="litigationProgress" placeholder="请填写诉讼进展">${bizEntity.litigationProgress!}</textarea>
            </div>
        </div>
    </div>
    <div class="row" style="margin-bottom: 2px;display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">起诉状：</label>
        </div>
        <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton2" class="btn s-op-upload" rel="complaint-attachment" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-complaint-attachment"  rel="起诉状" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
        </div>
    </div>
    <div class="row" style="margin-bottom: 2px;display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">开庭传票或者预立案、诉前调解相关文书：</label>
        </div>
        <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton2" class="btn s-op-upload" rel="summons-file" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-summons-file"  rel="开庭传票或者预立案、诉前调解相关文书" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
        </div>
    </div>
    <div class="row" style="margin-bottom: 2px;display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">举证通知书：</label>
        </div>
        <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton2" class="btn s-op-upload" rel="evidence-notice-file" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-evidence-notice-file"  rel="举证通知书" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
        </div>
    </div>
    <div class="row" style="margin-bottom: 2px;display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">证据材料：</label>
        </div>
        <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton2" class="btn s-op-upload" rel="evidence-materials" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-evidence-materials"  rel="证据材料" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
        </div>
    </div>
    <div class="row" style="margin-bottom: 5px;display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">判决书、裁定书、裁决书：</label>
        </div>
        <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton2" class="btn s-op-upload" rel="judgment-file" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-judgment-file"  rel="判决书、裁定书、裁决书" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">收案法院名称：</label>
                <input class="txt txt-validate" type="text" id="courtName" name="courtName" value="${bizEntity.courtName!}" autocomplete="off" data-options="required:true" noNull="请填写申报单位名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">承办人/经办人姓名：</label>
                <input class="txt txt-validate" type="text" id="handlerName" name="handlerName" value="${bizEntity.handlerName!}" autocomplete="off" data-options="required:true" noNull="请填写申报人名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">承办人/经办人联系电话：</label>
                <input class="txt txt-validate  easyui-numberbox" style="width:100%;" type="text" id="handlerPhone" name="handlerPhone" value="${bizEntity.handlerPhone!}" autocomplete="off" data-options="required:true,min:0,precision:0" noNull="请填写申报人联系方式"/>
            </div>
        </div>
    </div>

    <!-- 申报单位陈述 -->
    <h2 class="h2-title-a">
        <span class="s-title">申报单位陈述</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">申报单位名称：</label>
                <input class="txt txt-validate" type="text" id="applyUnit" name="applyUnit" value="${bizEntity.applyUnit!}" autocomplete="off" data-options="required:true" noNull="请填写申报单位名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">申报人名称：</label>
                <input class="txt txt-validate" type="text" id="applicantName" name="applicantName" value="${bizEntity.applicantName!}" autocomplete="off" data-options="required:true" noNull="请填写申报人名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">申报人联系方式：</label>
                <input class="txt txt-validate" type="text" id="applicantPhone" name="applicantPhone" value="${bizEntity.applicantPhone!}" autocomplete="off" data-options="required:true" noNull="请填写申报人联系方式"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">诉讼、仲裁案件产生的原因及经过：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="caseReasonProcess" name="caseReasonProcess" placeholder="请填写案件产生的原因及经过">${bizEntity.caseReasonProcess!}</textarea>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">申报单位初步处理意见及诉求：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="opinionRequirement" name="opinionRequirement" placeholder="请填写处理意见及诉求">${bizEntity.opinionRequirement!}</textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">是否涉及网络舆情：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="isPublicOpinion" id="isPublicOpinion" data-options="editable:false, required:true, value:'${bizEntity.isPublicOpinion}'">
                    <option value="Y">是</option>
                    <option value="N">否</option>
                </select>
            </div>
        </div>
    </div>
</form>
</div>
<div class="tabCont tabCont-1 tabContHide tab-flow">

</div>
<div class="none">
    <div id="fileUrlDiv"></div>
</div>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['lodash','template','myupload','export','lawOpinionList','pub'], function (_,template,myupload,exportFn,lawOpinionList) {
        if(eval('${bizEntity.caseCategory}')!=null){
            $('#caseCategory').combobox('setValue',eval('${bizEntity.caseCategory}'));
            if(eval('${bizEntity.caseCategory}') == '1'){
                $('.litigationPhase-div').css('visibility','visible');
                $('.litigationPhase-div').show();
                $('#litigationPhase').combobox('enableValidation');
                $('.arbitrationPhase-div').css('visibility','hidden');
                $('#arbitrationPhase').combobox('disableValidation');
            }else if (eval('${bizEntity.caseCategory}') == '2') {
                $('.litigationPhase-div').css('visibility','hidden');
                $('.litigationPhase-div').hide();
                $('#litigationPhase').combobox('disableValidation');
                $('.arbitrationPhase-div').css('visibility','visible');
                $('#arbitrationPhase').combobox('enableValidation');
            }else{
                $('.litigationPhase-div').css('visibility','hidden');
                $('.arbitrationPhase-div').css('visibility','hidden');
                $('#litigationPhase').combobox('disableValidation');
                $('#arbitrationPhase').combobox('disableValidation');
            }
        }
        if(eval('${bizEntity.caseTypeOne}')!=null){
            $('#caseTypeOne').combobox('setValue',eval('${bizEntity.caseTypeOne}'));
        }
        if(eval('${bizEntity.caseTypeTwo}')!=null){
            $('#caseTypeTwo').combobox('setValue',eval('${bizEntity.caseTypeTwo}'));
        }
        if(eval('${bizEntity.litigationPhase}')!=null){
            $('#litigationPhase').combobox('setValue',eval('${bizEntity.litigationPhase}'));
        }
        if(eval('${bizEntity.arbitrationPhase}')!=null){
            $('#arbitrationPhase').combobox('setValue',eval('${bizEntity.arbitrationPhase}'));
        }

        if(eval('${bizEntity.caseTypeOne}') =='9' && eval('${bizEntity.caseTypeTwo}')=='5'){
            $('#caseTypeTwoDesc').show();
        }else{
            $('#caseTypeTwoDesc').hide();
        }

        if(eval('${bizEntity.litigationPhase}') =='5'){
            $('#litigationPhaseDesc').show();
        }else{
            $('#litigationPhaseDesc').hide();
        }

        if(eval('${bizEntity.arbitrationPhase}')=='3'){
            $('#arbitrationPhaseDesc').show();
        }else{
            $('#arbitrationPhaseDesc').hide();
        }

        [#include "/WEB-INF/views/amcs/law/common/tab_js.ftl"]

    });
</script>
</body>
</html>
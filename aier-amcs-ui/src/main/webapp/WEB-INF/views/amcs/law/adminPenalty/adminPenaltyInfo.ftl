<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>行政处罚信息录入</title>
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
    <form id="adminPenaltyForm" class="soform formA form-validate pad-t10 commonDataForm">
        <input type="hidden" id="id" name="id" value="${bizId!}" />
        <input type="hidden" id="infoUrl" name="infoUrl" value="${base}/ui/amcs/law/adminPenalty/info" />
        <input type="hidden" id="saveUrl" name="saveUrl" value="${base}/ui/amcs/law/adminPenalty/save" />
        <input type="hidden" id="initCommitUrl" name="initCommitUrl" value="${base}/ui/amcs/law/adminPenalty/initCommit" />
        <input type="hidden" id="flowCommitUrl" name="flowCommitUrl" value="${base}/ui/amcs/law/adminPenalty/flowCommit" />
        <input type="hidden" id="bizType" name="bizType" value="3" />
        <input type="hidden" id="bizCode" name="bizCode" value="ADMIN_PENALTY" />
        <input type="hidden" id="tabWindowTitle" name="tabWindowTitle" value="行政处罚" />

        <!-- 机构信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">机构信息</span>
            [#if curNodeName != ''] <span style="display: inline-block; color: red;">当前审批节点:${curNodeName!}</span> [/#if]
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">机构名称：</label>
                    <input class="txt txt-validate" type="text" id="instName" name="instName"
                           value="${bizEntity.instName!}" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">上级机构名称：</label>
                    <input class="txt txt-validate" type="text" id="superInstName" name="superInstName"
                           value="${bizEntity.superInstName!}"/>
                </div>
            </div>
        </div>

        <!-- 行政处罚信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">行政处罚信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">受处罚单位/个人：</label>
                    <input class="txt txt-validate" type="text" id="partyName" name="partyName"
                           value="${bizEntity.partyName!}" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">行政机关全称：</label>
                    <input class="txt txt-validate" type="text" id="authorityName" name="authorityName"
                           value="${bizEntity.authorityName!}" data-options="required:true"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">文书类型：</label>
                    [@ui_select id="documentType" name="documentType" tag="@law@document_type"  style="width:45%;"
                    dataOptions="required:true,onChange:function(v){
                        if(v == '10'){
                            $('#documentTypeOther').show();
                        }else{
                            $('#documentTypeOther').hide();
                        }
                    }"/]
                    <input class="txt txt-validate ${(bizEntity.documentType == '10')?then('','hide')}"
                           type="text" id="documentTypeOther" name="documentTypeOther"  style="width:50%;"
                           value="${bizEntity.documentTypeOther!}" placeholder="请输入其他类型"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">文书编号：</label>
                    <input class="txt txt-validate" type="text" id="documentNo" name="documentNo"
                           value="${bizEntity.documentNo!}" data-options="required:true"/>
                </div>
            </div>
        </div>

        <div class="row" style="margin-bottom: 10px;display: flex; align-items: center; width: 100%;">
            <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
                <label class="lab-item" style="white-space: nowrap;position:relative;">文书附件：</label>
            </div>
            <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
                <button type="button" id="addButton2" class="btn s-op-upload" rel="document-attachment" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="attach-div-document-attachment"  rel="文书附件" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
            </div>
        </div>
        <!-- 日期信息 -->
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">受案日期：</label>
                    <input class="txt so-date required txt-validate" style="width:100%" id="filingDate" name="filingDate"
                           value="[#if bizEntity.filingDate??]${bizEntity.filingDate?string("yyyy-MM-dd")}[/#if]"  data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">处罚日期：</label>
                    <input class="txt so-date required txt-validate" style="width:100%" id="penaltyDate" name="penaltyDate"
                           value="[#if bizEntity.penaltyDate??]${bizEntity.penaltyDate?string("yyyy-MM-dd")}[/#if]" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">收文日期：</label>
                    <input class="txt so-date required txt-validate" style="width:100%" id="receiveDate" name="receiveDate"
                           value="[#if bizEntity.receiveDate??]${bizEntity.receiveDate?string("yyyy-MM-dd")}[/#if]" />
                </div>
            </div>
        </div>

        <!-- 案件信息 -->
        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">处罚事由：</label>
                    [@ui_select id="penaltyReason" name="penaltyReason" tag="@law@penalty_reason"  style="width:45%;"
                    dataOptions="required:true,onChange:function(v){
                        if(v == '15'){
                            $('#penaltyReasonOther').show();
                        }else{
                            $('#penaltyReasonOther').hide();
                        }
                    }"/]
                    <input class="txt txt-validate ${(bizEntity.penaltyReason == '15')?then('','hide')}"
                           type="text" id="penaltyReasonOther" name="penaltyReasonOther"  style="width:49%;"
                           value="${bizEntity.penaltyReasonOther!}" placeholder="请输入其他事由"/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">处罚类别：</label>
                    [@ui_select id="penaltyCategory" name="penaltyCategory" tag="@law@penalty_category"  style="width:45%;"
                    dataOptions="required:true,onChange:function(v){
                        if(v == '12'){
                            $('#penaltyCategoryOther').show();
                        }else{
                            $('#penaltyCategoryOther').hide();
                        }
                    }"/]
                    <input class="txt txt-validate ${(bizEntity.penaltyCategory == '12')?then('','hide')}"
                           type="text" id="penaltyCategoryOther" name="penaltyCategoryOther"  style="width:49%;"
                           value="${bizEntity.penaltyCategoryOther!}" placeholder="请输入其他类别"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">处罚依据：</label>
                    <textarea class="txta adaptiveTextarea" id="penaltyBasis" name="penaltyBasis">${bizEntity.penaltyBasis!}</textarea>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">处罚措施：</label>
                    <textarea class="txta adaptiveTextarea" id="penaltyMeasures" name="penaltyMeasures">${bizEntity.penaltyMeasures!}</textarea>
                </div>
            </div>
        </div>

        <!-- 申报信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">申报单位陈述</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">申报单位：</label>
                    <input class="txt txt-validate" type="text" id="applyUnit" name="applyUnit"
                           value="${bizEntity.applyUnit!}" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">申报人：</label>
                    <input class="txt txt-validate" type="text" id="applicantName" name="applicantName"
                           value="${bizEntity.applicantName!}" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">联系方式：</label>
                    <input class="txt txt-validate" type="text" id="applicantPhone" name="applicantPhone"
                           value="${bizEntity.applicantPhone!}" data-options="required:true,validType:'mobile'"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">已采取的措施：</label>
                    <textarea class="txta adaptiveTextarea" id="handlingMeasures" name="handlingMeasures">${bizEntity.handlingMeasures!}</textarea>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">处理意见及诉求：</label>
                    <textarea class="txta adaptiveTextarea" id="opinionRequirement" name="opinionRequirement">${bizEntity.opinionRequirement!}</textarea>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">是否涉及舆情：</label>
                    <select class="easyui-combobox"  style="width:100%;" data-options="required:true">
                        <option value="Y" ${(bizEntity.isPublicOpinion == 'Y')?then('selected','')}>是</option>
                        <option value="N" ${(bizEntity.isPublicOpinion == 'N')?then('selected','')}>否</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="tabCont tabCont-1 tabContHide tab-flow"></div>

<div class="none">
    <div id="fileUrlDiv"></div>
</div>

[#include "/WEB-INF/views/common/include_js.ftl"]
<script>
    requirejs(['lodash','template','myupload','export','lawOpinionList','pub'], function (_,template,myupload,exportFn,lawOpinionList) {

        if(eval('${bizEntity.documentType}')!=null){
            $('#documentType').combobox('setValue',eval('${bizEntity.documentType}'));
        }
        if(eval('${bizEntity.penaltyReason}')!=null){
            $('#penaltyReason').combobox('setValue',eval('${bizEntity.penaltyReason}'));
        }
        if(eval('${bizEntity.penaltyCategory}')!=null){
            $('#penaltyCategory').combobox('setValue',eval('${bizEntity.penaltyCategory}'));
        }

        [#include "/WEB-INF/views/amcs/law/common/tab_js.ftl"]

    });
</script>
</body>
</html>
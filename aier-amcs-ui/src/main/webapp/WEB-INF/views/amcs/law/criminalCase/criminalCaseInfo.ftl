<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>刑事案件信息录入</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <link rel="stylesheet" href="${base}/static/js/app/eventTab/eventFlowchart.css">
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
    <form id="criminalCaseForm" class="soform formA form-validate pad-t10 commonDataForm" data-opt="{beforeCallback:'submitCriminalCaseForm'}">
        <!-- 隐藏字段 -->
        <input type="hidden" id="id" name="id" value="${bizId!}">
        <input type="hidden" id="infoUrl" name="infoUrl" value="${base}/ui/amcs/law/criminalCase/info">
        <input type="hidden" id="saveUrl" name="saveUrl" value="${base}/ui/amcs/law/criminalCase/save">
        <input type="hidden" id="initCommitUrl" name="initCommitUrl" value="${base}/ui/amcs/law/criminalCase/initCommit">
        <input type="hidden" id="flowCommitUrl" name="flowCommitUrl" value="${base}/ui/amcs/law/criminalCase/flowCommit">
        <input type="hidden" id="bizType" name="bizType" value="2" />
        <input type="hidden" id="bizCode" name="bizCode" value="CRIMINAL_CASE">
        <input type="hidden" id="tabWindowTitle" name="tabWindowTitle" value="刑事案件">

        <!-- 案件基本信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">案件基本信息</span>
            [#if curNodeName != '']
                <span style="color: red;">当前审批节点：${curNodeName!}</span>
            [/#if]
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <!-- 犯罪嫌疑人 -->
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">犯罪嫌疑人全称：</label>
                    <input class="txt txt-validate" type="text" id="suspectName" name="suspectName" value="${bizEntity.suspectName!}" autocomplete="off" data-options="required:true" noNull="请填写犯罪嫌疑人全称">
                </div>
            </div>

            <!-- 主办公安机关 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">主办公安机关：</label>
                    <input class="txt txt-validate" type="text" id="handlingPsb" name="handlingPsb" value="${bizEntity.handlingPsb!}" autocomplete="off" data-options="required:true" noNull="请填写主办公安机关名称">
                </div>
            </div>
        </div>

        <div class="row">
            <!-- 主办检察院 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">主办检察院：</label>
                    <input class="txt txt-validate" type="text" id="prosecutorOffice" name="prosecutorOffice" value="${bizEntity.prosecutorOffice!}" autocomplete="off" data-options="required:true" noNull="请填写主办检察院名称">
                </div>
            </div>

            <!-- 主审法院 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">主审法院：</label>
                    <input class="txt txt-validate" type="text" id="courtName" name="courtName" value="${bizEntity.courtName!}" autocomplete="off" data-options="required:true" noNull="请填写主审法院名称">
                </div>
            </div>
        </div>

        <!-- 案件类型与罪名 -->
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">案件类型：</label>
                    [@ui_select id="caseType" name="caseType" tag="@law@criminal_case_type" class="easyui-combobox"   style="width:100%;" dataOptions="required:true,
                    onChange:((v)=>{
                        $ajax.postSync('${base}/ui/amcs/law/dictType/selectSubDicts?typeCode=criminal_case_type&valueCode=' + v,null,false,false).done(function (rst) {
                            $('#chargeName').combobox('loadData', rst);
                        });
                        let chargeName = $('#chargeName').combobox('getValue');
                        if(v=='6' && chargeName=='1'){
                            $('#chargeNameDesc').show();
                        }else{
                            $('#chargeNameDesc').hide();
                        }
                    })" filterkey="firstSpell"/]
                </div>
            </div>

            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">罪名：</label>
                    [@ui_select id="chargeName" name="chargeName" tag="@law@charge_name"  style="width:36%;" class="easyui-combobox" dataOptions="required:true,
                        onChange:function(v){
                            let caseType = $('#caseType').combobox('getValue');
                            if(v=='1' && caseType=='6'){
                                $('#chargeNameDesc').show();
                            }else{
                                $('#chargeNameDesc').hide();
                            }
                        }" filterkey="firstSpell"/]
                    <input class="txt txt-validate hide" type="text" id="chargeNameDesc" style="width:45%;" name="chargeNameDesc" placeholder="请填写其他罪名描述" value="${bizEntity.chargeNameDesc!}">
                </div>
            </div>
        </div>

        <!-- 案号与涉案金额 -->
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">案号：</label>
                    <input class="txt txt-validate" type="text" id="caseNo" name="caseNo" value="${bizEntity.caseNo!}" autocomplete="off" data-options="required:true" noNull="请填写案号">
                </div>
            </div>

            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">涉案金额：</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="involvedAmount" name="involvedAmount" value="${bizEntity.involvedAmount!}" data-options="min:0,precision:2,required:true">
                    <em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">诉讼阶段：</label>
                    [@ui_select id="litigationPhase" name="litigationPhase" tag="@law@criminal_litigation_phase" class="easyui-combobox" dataOptions="required:true" filterkey="firstSpell"/]
                </div>
            </div>
        </div>

        <!-- 案件描述 -->
        <div class="row">
            <div class="p9">
                <div class="item-one solab-l">
                    <label class="lab-item">案件描述：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" id="caseDesc" name="caseDesc" placeholder="请详细描述案件经过">${bizEntity.caseDesc!}</textarea>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p9">
                <div class="item-one solab-l">
                    <label class="lab-item">当前状态：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" id="currentStatus" name="currentStatus" placeholder="填写当前案件进展">${bizEntity.currentStatus!}</textarea>
                </div>
            </div>
        </div>

        <!-- 备注 -->
        <div class="row">
            <div class="p9">
                <div class="item-one solab-l">
                    <label class="lab-item">备注：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" id="remarks" name="remarks" placeholder="其他需要说明的事项">${bizEntity.remarks!}</textarea>
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
    requirejs(['lodash', 'template', 'myupload', 'export', 'lawOpinionList', 'pub'],function(_, template, myupload, exportFn, lawOpinionList) {

        if(eval('${bizEntity.caseType}')!=null){
            $('#caseType').combobox('setValue',eval('${bizEntity.caseType}'));
        }
        if(eval('${bizEntity.chargeName}')!=null){
            $('#chargeName').combobox('setValue',eval('${bizEntity.chargeName}'));
        }
        if(eval('${bizEntity.litigationPhase}')!=null){
            $('#litigationPhase').combobox('setValue',eval('${bizEntity.litigationPhase}'));
        }

        if(eval('${bizEntity.caseType}') =='6' && eval('${bizEntity.chargeName}')=='1'){
            $('#chargeNameDesc').show();
        }else{
            $('#chargeNameDesc').hide();
        }

        [#include "/WEB-INF/views/amcs/law/common/tab_js.ftl"]

    });
</script>
</body>
</html>
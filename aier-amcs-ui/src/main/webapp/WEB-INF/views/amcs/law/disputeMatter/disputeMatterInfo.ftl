[#assign base = request.contextPath]
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>纠纷事项录入</title>
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
    <form id="disputeMatterForm" class="soform formA form-validate pad-t10 commonDataForm">
        <input type="hidden" id="id" name="id" value="${bizId!}" />
        <input type="hidden" id="infoUrl" name="infoUrl" value="${base}/ui/amcs/law/disputeMatter/info" />
        <input type="hidden" id="saveUrl" name="saveUrl" value="${base}/ui/amcs/law/disputeMatter/save" />
        <input type="hidden" id="initCommitUrl" name="initCommitUrl" value="${base}/ui/amcs/law/disputeMatter/initCommit" />
        <input type="hidden" id="flowCommitUrl" name="flowCommitUrl" value="${base}/ui/amcs/law/disputeMatter/flowCommit" />
        <input type="hidden" id="bizType" name="bizType" value="4" />
        <input type="hidden" id="bizCode" name="bizCode" value="DISPUTE_MATTER" />
        <input type="hidden" id="tabWindowTitle" name="tabWindowTitle" value="纠纷事项" />

        <!-- 机构信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">机构信息</span>
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

        <!-- 纠纷事项信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">纠纷事项信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">对方全称：</label>
                    <input class="txt txt-validate" type="text" id="opponentFullName" name="opponentFullName"
                           value="${bizEntity.opponentFullName!}" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">我方全称：</label>
                    <input class="txt txt-validate" type="text" id="ourFullName" name="ourFullName"
                           value="${bizEntity.ourFullName!}" data-options="required:true"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">纠纷事项产生的原因及经过：</label>
                    <textarea class="txta adaptiveTextarea" id="disputeCauseProcess" name="disputeCauseProcess">${bizEntity.disputeCauseProcess!}</textarea>
                </div>
            </div>
        </div>

        <!-- 申报单位信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">申报单位信息</span>
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
                    <select class="easyui-combobox"  style="width:100%;" name="isPublicOpinion" id="isPublicOpinion" data-options="editable:false, required:true, value:'${bizEntity.isPublicOpinion}'">
                        <option value="Y">是</option>
                        <option value="N">否</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">网络舆情发展情况：</label>
                    <textarea class="txta adaptiveTextarea" id="opinionDevelopment" name="opinionDevelopment">${bizEntity.opinionDevelopment!}</textarea>
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

        [#include "/WEB-INF/views/amcs/law/common/tab_js.ftl"]

    });
</script>
</body>
</html>
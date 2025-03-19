<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>外聘法律顾问信息录入</title>
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
<div id="op-div" style="position: fixed;
        z-index: 1;
        top: 40%;
        right: 0;
        display: ${opDivDisplay!};
        flex-direction: column;">
    <!-- 填写意见 -->
    <button type="button" class="btn btn-large btn-primary fix-opinion-btn" style="display: ${initBtnDisplay!};">填写意见</button>
</div>
<div style="height: 34px;"></div>
<ul class="tabs likeTabs-advisor" style="position: fixed;width: 100%;top:0;z-index: 999;">
    <li class="tabs-first tabs-selected" rel="0">
        <a href="#" class="tabs-inner"><span class="tabs-title">基本信息</span></a>
    </li>
    <li rel="1">
        <a href="#" class="tabs-inner"><span class="tabs-title">审核意见</span></a>
    </li>
</ul>
<div class="tabCont tabCont-0">
    <form id="externalLegalAdvisorForm" class="soform formA form-validate pad-t10 commonDataForm" data-opt="{beforeCallback:'submitExternalLegalAdvisorForm'}">
        <input type="hidden" id="id" name="id" value="${bizId!}" />
        <input type="hidden" id="infoUrl" name="infoUrl" value="${base}/ui/amcs/law/externalLegalAdvisor/info" />
        <input type="hidden" id="saveUrl" name="saveUrl" value="${base}/ui/amcs/law/externalLegalAdvisor/save" />
        <input type="hidden" id="bizType" name="bizType" value="5" />
        <input type="hidden" id="bizCode" name="bizCode" value="EXTERNAL_LEGAL_ADVISOR" />
        <input type="hidden" id="tabWindowTitle" name="tabWindowTitle" value="外聘法律顾问信息" />

        <!-- 基本信息 -->
        <h2 class="h2-title-a" style="width: 50%;">
            <span class="s-title" style="padding-right: 12px;">基本信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">申报单位名称：</label>
                    <input class="txt txt-validate" type="text" id="applyUnit" name="applyUnit" value="${bizEntity.applyUnit!}" autocomplete="off" data-options="required:true" noNull="请填写申报单位名称"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">申报人姓名：</label>
                    <input class="txt txt-validate" type="text" id="applicantName" name="applicantName" value="${bizEntity.applicantName!}" autocomplete="off" data-options="required:true" noNull="请填写申报人姓名"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">机构名称：</label>
                    <input class="txt txt-validate" type="text" id="instName" name="instName" value="${bizEntity.instName!}" autocomplete="off" data-options="" />
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">律师事务所全称：</label>
                    <input class="txt txt-validate" type="text" id="lawFirmName" name="lawFirmName" value="${bizEntity.lawFirmName!}" autocomplete="off" data-options="required:true" noNull="请填写律师事务所全称"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">统一社会信用代码：</label>
                    <input class="txt txt-validate" type="text" id="unifiedCreditCode" name="unifiedCreditCode" value="${bizEntity.unifiedCreditCode!}" autocomplete="off" data-options="required:true" noNull="请填写统一社会信用代码"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">注册地址：</label>
                    <input class="txt txt-validate" type="text" id="registeredAddress" name="registeredAddress" value="${bizEntity.registeredAddress!}" autocomplete="off" data-options="required:true" noNull="请填写注册地址"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">主办律师姓名：</label>
                    <input class="txt txt-validate" type="text" id="hostLawyerName" name="hostLawyerName" value="${bizEntity.hostLawyerName!}" autocomplete="off" data-options="required:true" noNull="请填写主办律师姓名"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">主办律师联系方式：</label>
                    <input class="txt txt-validate" type="text" id="hostLawyerContact" name="hostLawyerContact" value="${bizEntity.hostLawyerContact!}" autocomplete="off" data-options="required:true" noNull="请填写主办律师联系方式"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lb">
                    <label class="lab-item">擅长领域：</label>
                    [@ui_select  uiShowDefault=false id="expertiseField" name="expertiseField" tag="@law@expertise_field"  style="width:37.4%;" class="easyui-combobox" dataOptions="required:true,
                    onChange:function(v){
                        if( v=='7'){
                            $('#expertiseOther').show();
                        }else{
                            $('#expertiseOther').hide();
                        }
                    }" filterkey="firstSpell"/]
                    <input class="txt txt-validate hide" style="width:40%;" type="text" id="expertiseOther" name="expertiseOther" placeholder="请填写其他" >${bizEntity.expertiseOther!}</input>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lb">
                    <label class="lab-item">服务类型：</label>
                    [@ui_select  uiShowDefault=false id="serviceType" name="serviceType" tag="@law@service_type"  style="width:37.4%;" class="easyui-combobox" dataOptions="required:true,
                    onChange:function(v){
                        if( v=='4'){
                            $('#serviceTypeOther').show();
                        }else{
                            $('#serviceTypeOther').hide();
                        }
                    }" filterkey="firstSpell"/]
                    <input class="txt txt-validate hide" style="width:40%;" type="text" id="serviceTypeOther" name="serviceTypeOther" placeholder="请填写其他" >${bizEntity.serviceTypeOther!}</input>
                </div>
            </div>
        </div>
        <!-- 附件上传 -->
        <div class="row" style="margin-bottom: 2px;display: flex; align-items: center; width: 100%;">
            <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
                <label class="lab-item" style="white-space: nowrap;position:relative;">资质证明文件：</label>
            </div>
            <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
                <button type="button" id="addButton1" class="btn s-op-upload" rel="qualification-proof" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="attach-div-qualification-proof"  rel="资质证明文件" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
            </div>
        </div>
        <div class="row" style="margin-bottom: 4px;display: flex; align-items: center; width: 100%;">
            <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
                <label class="lab-item" style="white-space: nowrap;position:relative;">保密协议/服务汇总报告：</label>
            </div>
            <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
                <button type="button" id="addButton1" class="btn s-op-upload" rel="confAgreement-svcReport" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="attach-div-confAgreement-svcReport"  rel="保密协议/服务汇总报告" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">服务内容与范围：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="serviceScope" name="serviceScope" placeholder="请填写服务内容与范围">${bizEntity.serviceScope!}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">备注信息：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="remark" name="remark" placeholder="请填写备注信息">${bizEntity.remark!}</textarea>
                </div>
            </div>
        </div>
        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="提 交"/>
            <input type="button" class="btn btn-cancel btn-cancel-cus" name="btnCancel" value="取 消"/>
        </p>
    </form>
</div>
<div class="tabCont tabCont-1 tabContHide tab-flow">

</div>
<div class="none">
    <div id="fileUrlDiv"></div>
</div>
<script id="opinionFormTemAdvisor" type="text/html">
    <form id="infoForm" class="soform form-validate solab-l form-enter" data-opt="{beforeCallback:'submitFlowDataForm'}">
        <div class="row">
            <div class="item-one" style="padding-left: 47px;margin-top: 10px;">
                <label class="lab-item" style="text-align: left;">提交意见:</label>
                <div class="p12">
                    <textarea class="txta txt-validate" id="opinion" name="opinion" maxlength="1000"></textarea>
                </div>
            </div>
        </div>
        <p class="row-btn center">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>
            <input type="button" class="btn btn-cancel-pop" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['lodash','template','myupload','export','lawOpinionList','pub'], function (_,template,myupload,exportFn,lawOpinionList) {
        /*全局变量*/
        var opinionFormPopAdvisor = null;

        if(eval('${bizEntity.expertiseField}')!=null){
            $('#expertiseField').combobox('setValue',eval('${bizEntity.expertiseField}'));
        }
        if(eval('${bizEntity.serviceType}')!=null){
            $('#serviceType').combobox('setValue',eval('${bizEntity.serviceType}'));
        }

        if(eval('${bizEntity.expertiseField}') =='7'){
            $('#expertiseOther').show();
        }else{
            $('#expertiseOther').hide();
        }

        if(eval('${bizEntity.serviceType}') =='4'){
            $('#serviceTypeOther').show();
        }else{
            $('#serviceTypeOther').hide();
        }

        window.$tabLi_advisor = $('.likeTabs-advisor li');
        $tabLi_advisor.click(function () {
            var ix = $tabLi_advisor.index(this);
            if (!$(this).hasClass('tabs-selected')) {
                $tabLi_advisor.removeClass('tabs-selected');
                $(this).addClass('tabs-selected');
                $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
                if (ix == 1) {
                    // TODO grid重新加载
                    $('#op-div').css('visibility', 'hidden');
                    [#if bizId != null && bizId != '']
                    let objParams = {'bizId': '${bizId!}'};
                    lawOpinionList.init(objParams,`${base}/ui/amcs/law/externalLegalAdvisor/getLawOpinionList`);
                    [/#if]
                } else {
                    var valDisplay = $('#op-div').attr('display');
                    if (valDisplay && valDisplay == 'none') {
                        $('#op-div').css('visibility', 'hidden');
                    } else {
                        $('#op-div').css('visibility', 'visible');
                    }
                    $(".tab-flow").empty();
                }
            }
        });

        [#include "/WEB-INF/views/amcs/law/common/tab_js.ftl"]

        window.submitExternalLegalAdvisorForm = function (opt, $form, data) {
            // 选择提交节点框显示的情况下，提交要检验必选
            if ($('.commonDataForm').form('validate')) {
                const attachArray = [];
                let attachFlag = true;
                // 遍历所有以 "attach-div-" 开头的 <div> 元素
                $('[class^="attach-div-"]').each(function () {
                    // 获取当前 div 的 rel 属性值
                    let relValue = $(this).attr('rel');

                    // 查找当前 <div> 内部的所有 <span class="s-preview"> 节点
                    const sPreviews = $(this).find('.s-preview');

                    // 检查是否存在 s-previews
                    /* if (sPreviews.length === 0) {
                        // 如果没有 s-preview，弹出提示
                        $pop.warning(relValue + '必须至少上传一个附件！');
                        attachFlag = false;
                        return false; // 跳过后续处理
                    } */

                    // 遍历每个 s-preview 节点
                    sPreviews.each(function () {
                        const attach = {};
                        attach.fileId = $(this).attr('rel'); // 获取 rel 属性值
                        attach.fileName = $(this).attr('filename'); // 获取 filename 属性值
                        attach.weburl = $(this).attr('weburl'); // 获取 weburl 属性值
                        attach.fileSize = $(this).attr('filesize'); // 获取 filesize 属性值
                        attach.fileType = $(this).attr('filetype'); // 获取 filetype 属性值
                        attach.filePath = $(this).attr('filepath'); // 获取 filepath 属性值
                        attach.attachCode = $(this).attr('attachcode'); // 获取 attachcode 属性值

                        // 将当前对象推入数组
                        attachArray.push(attach);
                    });
                });

                if (!attachFlag) {
                    return false;
                }

                data.attachs = attachArray;

                $ajax.post('${base}/ui/amcs/law/externalLegalAdvisor/save', data, {
                    jsonData: true,
                    tip: '确认提交？',
                }).done(function (rst) {
                    location.href = data.infoUrl + '?bizId=' + rst.bizId;
                    $pop.success('保存成功!');
                });
            }
            return false;
        };

        $(".fix-opinion-btn").click(function () {
            let opinionData = {};
            opinionFormPopAdvisor = $pop.popTemForm({
                title: '提交意见',
                temData: opinionData,
                temId: 'opinionFormTemAdvisor',
                area: ['500px', '280px'],
                onPop: function (formData, $form) {//提交前处理
                    $(".btn-cancel-pop").click(function () {
                        layer.close(opinionFormPopAdvisor);
                    });
                    return true;
                },
                beforeSubmit: function (formData, $form) {//提交前处理
                    return true;
                },
                afterSubmit: function (rst, $formBox) {

                }
            });
        });

        window.submitFlowDataForm = function (opt, $form, data) {
            let bizFormData = $('.commonDataForm').sovals();
            data.bizId = bizFormData.id;
            $ajax.post('${base}/ui/amcs/law/externalLegalAdvisor/saveOpinion', data, {
                jsonData: true,
                tip: '确认提交？',
            }).done(function (rst) {
                $pop.close(opinionFormPopAdvisor);
                $pop.closeTabWindow(bizFormData.tabWindowTitle);
            });
        }

        $(".btn-cancel-cus").click(function () {
            let bizFormData = $('.commonDataForm').sovals();
            $pop.closeTabWindow(bizFormData.tabWindowTitle);
        });

    });
</script>
</body>
</html>
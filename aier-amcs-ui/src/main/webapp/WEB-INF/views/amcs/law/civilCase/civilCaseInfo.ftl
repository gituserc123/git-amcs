<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>民事案件信息录入</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
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
</head>
<body>
<form id="civilCaseForm" class="soform formA form-validate pad-t20" data-opt="{beforeCallback:'submitCivilCaseForm'}">
    <input type="hidden" id="id" name="id" value="${civilCaseId!}"/>

    <!-- 当事人基本信息 -->
    <h2 class="h2-title-a">
        <span class="s-title">当事人基本信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">原告（申请人）全称：</label>
                <input class="txt txt-validate" type="text" id="plaintiffName" name="plaintiffName" value="${civilCase.plaintiffName!}" autocomplete="off" data-options="required:true" noNull="请填写原告全称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">被告（被申请人）全称：</label>
                <input class="txt txt-validate" type="text" id="defendantName" name="defendantName" value="${civilCase.defendantName!}" autocomplete="off" data-options="required:true" noNull="请填写被告全称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">机构名称：</label>
                <input class="txt txt-validate" type="text" id="instName" name="instName" value="${civilCase.instName!}" autocomplete="off" data-options="required:true" noNull="请填写机构名称"/>
            </div>
        </div>
    </div>
    <div class="row" style="display: flex; align-items: center; width: 100%;">
        <div style="flex-shrink: 0; padding: 0 0 0 1.1%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">案涉对方主体企业法人营业执照/自然人身份证</label>
        </div>
        <div style="flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton1" class="btn s-op-upload" rel="business-license" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle;">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-business-license"  rel="案涉对方主体企业法人营业执照/自然人身份证" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;">
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px;display: flex; align-items: center; width: 100%;">
        <div style="display: inline;flex-shrink: 0; padding: 0 0 0 1.1%;">
            <label class="lab-item" style="white-space: nowrap;position:relative;">起诉状</label>
        </div>
        <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
            <button type="button" id="addButton2" class="btn s-op-upload" rel="complaint-attachment" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle;">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="attach-div-complaint-attachment"  rel="起诉状" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
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
                    })" filterkey="firstSpell"/]
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">案由：</label>
                <select class="drop easyui-combobox" style="width:45%;" name="caseTypeTwo" id="caseTypeTwo" data-options="valueField:'valueCode',textField:'valueDesc',editable:false, required:true, value:'${civilCase.caseTypeTwo}',
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
                <input class="txt txt-validate" type="text" id="caseNo" name="caseNo" value="${civilCase.caseNo!}" autocomplete="off" data-options="required:true" noNull="请填写案号"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">涉案金额：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="involvedAmount" name="involvedAmount"
                       value="${civilCase.involvedAmount!}" validType="maxlength[10]" autocomplete="off"
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
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="caseDesc" name="caseDesc" placeholder="请填写案情简介">${civilCase.caseDesc!}</textarea>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">诉讼进展：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="litigationProgress" name="litigationProgress" placeholder="请填写诉讼进展">${civilCase.litigationProgress!}</textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">收案法院名称：</label>
                <input class="txt txt-validate" type="text" id="courtName" name="courtName" value="${civilCase.courtName!}" autocomplete="off" data-options="required:true" noNull="请填写申报单位名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">承办人/经办人姓名：</label>
                <input class="txt txt-validate" type="text" id="handlerName" name="handlerName" value="${civilCase.handlerName!}" autocomplete="off" data-options="required:true" noNull="请填写申报人名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">承办人/经办人联系电话：</label>
                <input class="txt txt-validate  easyui-numberbox" style="width:100%;" type="text" id="handlerPhone" name="handlerPhone" value="${civilCase.handlerPhone!}" autocomplete="off" data-options="required:true,min:0,precision:0" noNull="请填写申报人联系方式"/>
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
                <input class="txt txt-validate" type="text" id="applyUnit" name="applyUnit" value="${civilCase.applyUnit!}" autocomplete="off" data-options="required:true" noNull="请填写申报单位名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">申报人名称：</label>
                <input class="txt txt-validate" type="text" id="applicantName" name="applicantName" value="${civilCase.applicantName!}" autocomplete="off" data-options="required:true" noNull="请填写申报人名称"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">申报人联系方式：</label>
                <input class="txt txt-validate" type="text" id="applicantPhone" name="applicantPhone" value="${civilCase.applicantPhone!}" autocomplete="off" data-options="required:true" noNull="请填写申报人联系方式"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">诉讼、仲裁案件产生的原因及经过：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="caseReasonProcess" name="caseReasonProcess" placeholder="请填写案件产生的原因及经过">${civilCase.caseReasonProcess!}</textarea>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-l">
                <label class="lab-item">申报单位初步处理意见及诉求：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%;" id="opinionRequirement" name="opinionRequirement" placeholder="请填写处理意见及诉求">${civilCase.opinionRequirement!}</textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">是否涉及网络舆情：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="isPublicOpinion" id="isPublicOpinion" data-options="editable:false, required:true, value:'${civilCase.isPublicOpinion}'">
                    <option value="Y">是</option>
                    <option value="N">否</option>
                </select>
            </div>
        </div>
    </div>

    <p class="row-btn pad-t5">
        <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="提 交"/>
        <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
    </p>
</form>

<div class="none">
    <div id="fileUrlDiv"></div>
</div>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['lodash','template','myupload',"export",'pub'], function (_,template,myupload,exportFn) {
        if(eval('${civilCase.caseCategory}')!=null){
            $('#caseCategory').combobox('setValue',eval('${civilCase.caseCategory}'));
            if(eval('${civilCase.caseCategory}') == '1'){
                $('.litigationPhase-div').css('visibility','visible');
                $('.litigationPhase-div').show();
                $('#litigationPhase').combobox('enableValidation');
                $('.arbitrationPhase-div').css('visibility','hidden');
                $('#arbitrationPhase').combobox('disableValidation');
            }else if (eval('${civilCase.caseCategory}') == '2') {
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
        if(eval('${civilCase.caseTypeOne}')!=null){
            $('#caseTypeOne').combobox('setValue',eval('${civilCase.caseTypeOne}'));
        }
        if(eval('${civilCase.caseTypeTwo}')!=null){
            $('#caseTypeTwo').combobox('setValue',eval('${civilCase.caseTypeTwo}'));
        }
        if(eval('${civilCase.litigationPhase}')!=null){
            $('#litigationPhase').combobox('setValue',eval('${civilCase.litigationPhase}'));
        }
        if(eval('${civilCase.arbitrationPhase}')!=null){
            $('#arbitrationPhase').combobox('setValue',eval('${civilCase.arbitrationPhase}'));
        }

        if(eval('${civilCase.caseTypeOne}') =='9' && eval('${civilCase.caseTypeTwo}')=='5'){
            $('#caseTypeTwoDesc').show();
        }else{
            $('#caseTypeTwoDesc').hide();
        }

        if(eval('${civilCase.litigationPhase}') =='5'){
            $('#litigationPhaseDesc').show();
        }else{
            $('#litigationPhaseDesc').hide();
        }

        if(eval('${civilCase.arbitrationPhase}')=='3'){
            $('#arbitrationPhaseDesc').show();
        }else{
            $('#arbitrationPhaseDesc').hide();
        }

        //$('.s-op-del-att').css('visibility','hidden');
        // 附件删除 使用事件委托绑定 .s-op-del-att 的点击事件
        $('[class^="attach-div-"]').on('click', '.s-op-del-att', function (event) {
            // 找到当前删除图标所在的 span 节点
            const currentDeleteSpan = $(this);
            // 找到对应的预览 span 节点
            const previewSpan = currentDeleteSpan.prev('.s-preview');
            var fileId = $(this).attr('rel');
            var filePath = $(this).attr('filePath');
            $pop.confirm('确定删除吗？', function (r) {
                if (r){
                    // 后端删除操作
                    $ajax.post({
                        url: '${base}/ui/amcs/law/civilCase/deleteAttach?fileId='+ fileId + '&filePath=' + filePath + '&bizId=' + '${civilCaseId!}',
                        jsonData: true,
                        tip: '你确定删除此附件吗？',
                        callback: function (rst) {
                            $grid.reload('#gridBox');
                        }
                    });
                    // 移除当前删除图标和对应的预览 span 节点
                    currentDeleteSpan.remove();
                    previewSpan.remove();
                }
            });
        });

        // 假设 attachs 已经通过 ${attachs!"[]"} 转换为 JavaScript 数组
        let attachs = ${attachs!"[]"};

        // 判断 attachs 是否为空且长度大于零
        if (attachs && attachs.length > 0) {
            // 遍历 attachs 数组
            attachs.forEach(jsData => {
                // 生成 HTML 代码
                const html = `<span class="s-preview blue hand" title="预览" rel="` + jsData.fileId + `" fileName="` + jsData.fileName + `" webUrl="` + jsData.webUrl + `" fileSize="` + jsData.fileSize + `" fileType="` + jsData.fileType + `" filePath="` + jsData.filePath + `" attachCode="` + jsData.attachCode + `">` + jsData.fileName + `</span><span class="s-op s-op-del-att icon-del" style="color: red; margin-right: 10px;" title="删除" rel="` + jsData.fileId + `" filePath="` + jsData.filePath + `"></span>`;

                // 动态生成目标容器选择器：以 attach-div- 开头并拼接 attachCode
                const targetSelector = `.attach-div-` + jsData.attachCode;
                const $targetDiv = $(targetSelector);

                // 将生成的 HTML 插入到目标容器中
                $targetDiv.append(html.replace(/\n\s+/g, '')); // 移除换行和缩进
            });
        }

        // 附件上传
        $('.s-op-upload').click(function () {
            var attachCode = $(this).attr('rel');
            // 将row.id传递给上传组件
            upload.options.formData={
                bizId: '${civilCaseId!}',
                attachCode: attachCode
            };
            $('#fileUrlDiv').find('input').click();
        });

        // 附件预览 使用事件委托绑定 .s-preview 的点击事件
        $('[class^="attach-div-"]').on('click', '.s-preview', function (event) {
            // 获取当前点击的 .s-preview 元素
            var webUrl = $(this).attr('webUrl');
            if (webUrl) {
                $pop.iframePop({
                    title: '附件预览',
                    content: encodeURI(webUrl),
                    area: ['800px', '640px']
                });
                return false;
            }
        });

        // 自定义逻辑
        window.submitCivilCaseForm = function (opt, $form, data) {
            // 初始化一个空数组来存储结果
            const attachArray = [];

            let attachFlag = true;

            // 遍历所有以 "attach-div-" 开头的 <div> 元素
            $('[class^="attach-div-"]').each(function () {
                // 获取当前 div 的 rel 属性值
                let relValue = $(this).attr('rel');

                // 查找当前 <div> 内部的所有 <span class="s-preview"> 节点
                const sPreviews = $(this).find('.s-preview');

                // 检查是否存在 s-previews
                if (sPreviews.length === 0) {
                    // 如果没有 s-preview，弹出提示
                    $pop.warning(relValue + '必须至少上传一个附件！');
                    attachFlag=false;
                    return false; // 跳过后续处理
                }

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

            if(!attachFlag){
                return false;
            }

            data.attachs = attachArray;

            $ajax.post({
                url: '${base}/ui/amcs/law/civilCase/save',
                data: data,
                tip: '你确定提交吗？',
                jsonData : true,//是否采用jsonData格式提交
                sync : false,//是否同步方式提交
                type : 'post',//采用post方式提交
                loadingMask : true,//进行异步请求中，是否显示mask
                calltip : true,//提交成功后显示请求成功信息
                success: function (rst) {
                    $pop.closePop();
                    /*if(rst.code==='200'||rst.code==='201'){
                        $pop.closePop();
                        window.parent.window.reflash = true;
                    }*/
                },//请求成功后，code===200或者201返回事件
                callback : function(rst){},//请求成功后返回事件
                cancelback : function(){},//确认框点取消返回事件
                errback : function(req, textStatus, errorThrown){
                    //$pop.alert('保存失败111！');
                }//出现错误时返回事件
            });
            return false;
        };


        var upload = $("#fileUrlDiv").powerWebUpload({
            auto: true,
            fileNumLimit: 1,
            btnTxt: '附件上传',
            multiple: false,
            upOpt: {
                accept: {extensions: 'doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar'},
                dupliacate: true,
                server: '${base}/ui/amcs/law/civilCase/upload'
            },
            uploadSuccess: function (file, req) {
                $pop.close(uploadLoading);
                try {
                    const jsData = JSON.parse(req);

                    // 生成动态html
                    //const html=`<span class="s-preview blue hand"title="预览"rel="${req.fileId}"fileName="${req.fileName}"webUrl="${req.fileUrl}"fileSize="${req.fileSize}"fileType="${req.fileType}"filePath="${req.filePath}"attachCode="${req.attachCode}">${req.fileName}</span><span class="s-op s-op-del-att icon-del"style="color: red;"title="删除"rel="${req.fileId}"></span>`;
                    const html=`<span class="s-preview blue hand" title="预览" rel="`+ jsData.fileId+`" fileName="` + jsData.fileName + `" webUrl="` + jsData.fileUrl + `" fileSize="` + jsData.fileSize+`" fileType="` + jsData.fileType + `" filePath="` + jsData.filePath + `" attachCode="` + jsData.attachCode + `">` + jsData.fileName + `</span><span class="s-op s-op-del-att icon-del" style="color: red;margin-right: 10px;" title="删除" rel="` + jsData.fileId + `" filePath="` + jsData.filePath + `"></span>`;
                    // 动态生成目标容器选择器：以 attach-div- 开头并拼接 attachCode
                    //const targetSelector = `.attach-div-${req.attachCode}`;
                    const targetSelector = `.attach-div-` + jsData.attachCode;
                    const $targetDiv = $(targetSelector);

                    // 插入到容器末尾
                    $targetDiv.append(html.replace(/\n\s+/g, '')); // 移除换行和缩进
                } catch (e) {
                    console.error("解析 JSON 失败：", e);
                }
                $pop.msg.success('上传成功！');
                if(req.code=='500'){
                    $pop.alert(req.msg);
                }
                upload.removeFile(file);
            },
            beforeUpload: function (file) {
                // 允许的文件扩展名列表
                const allowedExtensions = ['xls', 'doc', 'docx', 'ppt', 'pptx', 'xlsx', 'pdf', 'jpg', 'png', 'gif', 'txt', 'zip', 'rar'];
                // 检查文件扩展名是否在允许的列表中
                if (!allowedExtensions.some(ext => file.ext.indexOf(ext) !== -1)) {
                    $pop.msg('上传格式类型不符，请上传doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar格式文件，其他格式暂不支持~');
                }else{
                    uploadLoading = $pop.load(1,{shade:0.1});
                }
            },
            uploadError: function (file, req) {
                console.log(req);
                $pop.msg('文件上传失败！');
                $pop.close(uploadLoading);
                upload.removeFile(file);
            }
        });
    });
</script>
</body>
</html>
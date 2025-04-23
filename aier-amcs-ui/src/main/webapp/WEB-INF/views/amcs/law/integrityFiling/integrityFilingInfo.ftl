<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>廉洁信息录入</title>
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
    <form id="integrityFilingForm" class="soform formA form-validate pad-t10 commonDataForm" data-opt="{beforeCallback:'submitEditForm'}">
        <!-- 隐藏字段 -->
        <input type="hidden" id="id" name="id" value="${bizId!}">
        <input type="hidden" id="bizType" name="bizType" value="6" />
        <input type="hidden" id="bizCode" name="bizCode" value="INTEGRITY_FILING">
        <input type="hidden" id="infoUrl" name="infoUrl" value="${base}/ui/amcs/law/integrityfiling/info" />

        <!-- 案件基本信息 -->
        <h2 class="h2-title-a">
            <span class="s-title">基本信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <!-- 编号 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">编号：</label>
                    <input class="txt txt-validate" type="text" id="eventSn" name="eventSn" value="${bizEntity.eventSn!}" autocomplete="off" data-options="required:true" noNull="请填写编号">
                </div>
            </div>

            <!-- 填报机构 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">填报机构：</label>
                    <input class="txt txt-validate" type="text" id="instName" name="instName" value="${bizEntity.instName!}" autocomplete="off" data-options="required:true" noNull="请填写填报机构">
                </div>
            </div>
        </div>

        <div class="row">
            <!-- 填报人 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">填报人：</label>
                    <input class="txt txt-validate" type="text" id="reporterName" name="reporterName" value="${bizEntity.reporterName!}" autocomplete="off" data-options="required:true" noNull="请填写填报人">
                </div>
            </div>

            <!-- 填报时间 -->
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">填报时间：</label>
                    <input type="text" id="courtName" class="txt so-date txt-validate " style="width:100%;" data-options="maxDate:new Date(),format:'yyyy-MM-dd'" name="courtName" value="[#if bizEntity.courtName??]${bizEntity.courtName?string("yyyy-MM-dd")}[#else]${sysdate?date("yyyy-MM-dd")}[/#if]" />
                </div>
            </div>
        </div>

        <!-- 类型 -->
        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">类型：</label>
                    <select class="drop easyui-combobox" style="width:50%;" name="eventType" id="eventType" data-options="editable:false, required:true, value:'${bizEntity.eventType}',
                        onChange:function(v){
                            if(v=='3'){
                                $('#eventTypeOther').show();
                            }else{
                                $('#eventTypeOther').hide();
                            }
                        }" >
                        <option value="1" [#if bizEntity.eventType==1]selected="selected"[/#if] >患者红包</option>
                        <option value="2" [#if bizEntity.eventType==2]selected="selected"[/#if] >供应商不当利益</option>
                        <option value="3" [#if bizEntity.eventType==2]selected="selected"[/#if] >其他</option>
                    </select>
                    <input class="txt txt-validate hide" type="text" id="eventTypeOther" style="width:45%;" name="eventTypeOther" placeholder="请填写其他" value="${bizEntity.eventTypeOther!}">
                </div>
            </div>
        </div>
        <!-- 事件发生时间 -->
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">事件发生时间：</label>
                    <input type="text" id="occurrenceTime" class="txt so-date txt-validate " style="width:100%;" data-options="maxDate:new Date(),format:'yyyy-MM-dd',required:true" name="occurrenceTime" value="[#if bizEntity.occurrenceTime??]${bizEntity.occurrenceTime?string("yyyy-MM-dd")}[/#if]" />
                </div>
            </div>
        </div>
        <!-- 事件发生经过 -->
        <div class="row">
            <div class="p9">
                <div class="item-one solab-l">
                    <label class="lab-item">事件发生经过：</label>
                    <textarea class="txta txt-validate adaptiveTextarea required" id="eventDescription" name="eventDescription" data-options="required:true" placeholder="请详细事件发生经过">${bizEntity.eventDescription!}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">金额：</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="amount" name="amount" value="${bizEntity.amount!}" data-options="min:0,precision:2,required:true">
                    <em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-l">
                    <label class="lab-item">患者/供应商名称：</label>
                    <input class="txt txt-validate" type="text" id="relatedPartyName" name="relatedPartyName" value="${bizEntity.relatedPartyName!}" autocomplete="off" data-options="required:true" noNull="请填写患者/供应商名称">
                </div>
            </div>
        </div>
        <!-- 处理结果 -->
        <div class="row">
            <div class="p9">
                <div class="item-one solab-l">
                    <label class="lab-item">处理结果：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" id="handlingResult" name="handlingResult" data-options="required:true" placeholder="请填写处理结果过">${bizEntity.handlingResult!}</textarea>
                </div>
            </div>
        </div>
        <div class="row" style="margin-bottom: 10px;display: flex; align-items: center; width: 100%;">
            <div style="flex-shrink: 0; padding: 0 0 0 1.5%;">
                <label class="lab-item" style="white-space: nowrap;position:relative;">附件：</label>
            </div>
            <div style="display: inline;flex-shrink: 0;margin-left: 10px;">
                <button type="button" id="addButton2" class="btn s-op-upload" rel="integrity-attachment" style="width: 70px;padding: 1px 5px;border-radius: 5px;vertical-align: middle; display: ${attachAddBtnDisplay!};">附件上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="attach-div-integrity-attachment"  rel="廉洁附件" style="flex: 1; min-width: 200px; white-space: nowrap; overflow-x: auto;height: 40px;line-height: 40px;">
            </div>
        </div>
        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存"/>
            <input type="button" class="btn btn-cancel-cus" name="btnCancel" value="取 消"/>
        </p>
    </form>

<div class="none">
    <div id="fileUrlDiv"></div>
</div>

[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', 'template', 'myupload', 'export', 'pub'],function(_, template, myupload, exportFn) {

        if($("#instName").val()){
            $("#instName").attr("readonly", "readonly");
        };
        if($("#eventSn").val()){
            $("#eventSn").attr("readonly", "readonly");
        };

        /** 附件上传组件初始化  --start--           **/
        // 附件删除 使用事件委托绑定 .s-op-del-att 的点击事件
        $('[class^="attach-div-"]').on('click', '.s-op-del-att', function (event) {
            // 找到当前删除图标所在的 span 节点
            const currentDeleteSpan = $(this);
            // 找到对应的预览 span 节点
            const previewSpan = currentDeleteSpan.prev('.s-preview');
            var fileId = $(this).attr('rel');
            var filePath = $(this).attr('filePath');
            $pop.confirm('确定删除吗？', function (r) {
                if (r) {
                    // 后端删除操作
                    $ajax.post({
                        url: '${base}/ui/amcs/law/baseui/deleteAttach?fileId=' + fileId + '&filePath=' + filePath + '&bizId=' + '${bizId!}',
                        jsonData: true,
                        // tip: '你确定删除此附件吗？',
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
            const delBtnVisible = '${attachDelBtnVisible!}' == 'hidden' ? 'hidden' : 'visible';
            // 遍历 attachs 数组
            attachs.forEach(jsData => {
                // 生成 HTML 代码
                const html = `<span class="s-preview blue hand" title="预览" rel="` + jsData.fileId + `" fileName="` + jsData.fileName + `" webUrl="` + jsData.webUrl + `" fileSize="` + jsData.fileSize + `" fileType="` + jsData.fileType + `" filePath="` + jsData.filePath + `" attachCode="` + jsData.attachCode + `">` + jsData.fileName + `</span><span class="s-op s-op-del-att icon-del" style="color: red; margin-right: 10px;visibility:` + delBtnVisible + `;" title="删除" rel="` + jsData.fileId + `" filePath="` + jsData.filePath + `"></span>`;

                // 动态生成目标容器选择器：以 attach-div- 开头并拼接 attachCode
                const targetSelector = `.attach-div-` + jsData.attachCode;
                const $targetDiv = $(targetSelector);

                // 将生成的 HTML 插入到目标容器中
                $targetDiv.append(html.replace(/\n\s+/g, '')); // 移除换行和缩进
            });
        };

        // 附件上传
        $('.s-op-upload').click(function () {
            let formData = $('.commonDataForm').sovals();
            var attachCode = $(this).attr('rel');
            // 将row.id传递给上传组件
            upload.options.formData = {
                bizId: '${bizId!}',
                bizCode: formData.bizCode,
                bizType: formData.bizType,
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

        var upload = $("#fileUrlDiv").powerWebUpload({
            auto: true,
            fileNumLimit: 1,
            btnTxt: '附件上传',
            multiple: false,
            upOpt: {
                accept: {extensions: 'doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar'},
                dupliacate: true,
                server: '${base}/ui/amcs/law/baseui/upload'
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
        /** 附件上传组件初始化    --end--           **/

        window.submitEditForm = function (opt, $form, data) {
            let formData = $('.commonDataForm').sovals();
            // 附件处理
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

            /*if (!attachFlag) {
                return false;
            }*/

            formData.attachs = attachArray;

            $ajax.post('${base}/ui/amcs/law/integrityfiling/save', formData, {
                jsonData: true,
                tip: false
            }).done(function (rst) {
                location.href = formData.infoUrl + '?bizId=' + rst.bizId;
                $pop.success('保存成功!');
            });

            return false;
        };

        $(".btn-cancel-cus").click(function () {
            $pop.closePop();
        });

    });
</script>
</body>
</html>
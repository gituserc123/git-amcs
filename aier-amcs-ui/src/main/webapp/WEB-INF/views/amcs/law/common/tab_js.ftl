
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

    /*全局变量*/
    var opinionFormPop = null;
    var rejectFormTemPop = null;

    window.$tabLi = $('.likeTabs li');
    $tabLi.click(function () {
        var ix = $tabLi.index(this);
        if (!$(this).hasClass('tabs-selected')) {
            $tabLi.removeClass('tabs-selected');
            $(this).addClass('tabs-selected');
            $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
            if (ix == 1) {
                // TODO grid重新加载
                $('#op-div').css('visibility', 'hidden');
                [#if bizId != null && bizId != '']
                let objParams = {'bizId': '${bizId!}'};
                lawOpinionList.init(objParams);
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

    /*
    暂存按钮事件
    */
    $(".fix-stash-btn").click(function () {
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

        if (!attachFlag) {
            return false;
        }

        formData.attachs = attachArray;

        $ajax.post(formData.saveUrl, formData, {
            jsonData: true,
            tip: false
        }).done(function (rst) {
            location.href = formData.infoUrl + '?bizId=' + rst.bizId;
            $pop.success('保存成功!');
        });
    });

    /**
     * 初始提交按钮事件
     */
    $(".fix-init-btn").click(function () {
        // 1.校验表单
        if ($('.commonDataForm').form('validate')) {
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

            if (!attachFlag) {
                return false;
            }

            formData.attachs = attachArray;

            $ajax.post(formData.initCommitUrl, formData, {
                jsonData: true,
                tip: false
            }).done(function (rst) {
                $pop.success('保存成功', function (index) {
                    $pop.closeTabWindow(formData.tabWindowTitle);
                });
            });
        }
    });

    /**
     * 流程提交按钮事件
     */
    $(".fix-save-btn").click(function () {
        // TODO 判断是否需要保存表单
        [#if isSaveBizForm=='true']
        if ($('.commonDataForm').form('validate')) {
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
            });

            if (!attachFlag) {
                return false;
            }
        }
        [/#if]

        let opinionData = {};
        opinionFormPop = $pop.popTemForm({
            title: '提交意见',
            temData: opinionData,
            temId: 'opinionFormTem',
            area: ['500px', '280px'],
            onPop: function (formData, $form) {//提交前处理
                $(".btn-cancel-pop").click(function () {
                    layer.close(opinionFormPop);
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
        [#if nodeSelectEle=='true']
        // 选择提交节点框显示的情况下，提交要检验必选
        [/#if]

        let bizFormData = $('.commonDataForm').sovals();
        data.bizForm = bizFormData;
        $ajax.post(bizFormData.flowCommitUrl, data, {
            jsonData: true,
            tip: '确认提交？',
        }).done(function (rst) {
            $pop.close(opinionFormPop);
            $pop.closeTabWindow(bizFormData.tabWindowTitle);
        });
    }

    /**
     * 流程退回按钮事件
     */
    $(".fix-back-btn").click(function () {
        let bizFormData = $('.commonDataForm').sovals();
        let rejectData = {};
        rejectFormTemPop = $pop.popTemForm({
            title: '退回意见',
            temData: rejectData,
            temId: 'rejectFormTem',
            area: ['500px', '280px'],
            onPop: function (formData, $form) {
                $(".btn-cancel-pop").click(function () {
                    layer.close(rejectFormTemPop);
                });
                $ajax.postSync('${base}/ui/amcs/law/baseui/getPreNode?bizId=' + bizFormData.id, null, false, false).done(function (rst) {
                    $('#commitNode').combobox('loadData', rst);
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

    window.rejectFlowDataForm = function (opt, $form, data) {
        // 选择提交节点框显示的情况下，提交要检验必选
        if ($('.clsRejectForm').form('validate')) {
            let bizFormData = $('.commonDataForm').sovals();
            let commitNodeName = $('#commitNode').combobox('getText');
            data.bizForm = bizFormData;
            data.commitNodeName = commitNodeName;
            $ajax.post('${base}/ui/amcs/law/baseui/flowReject', data, {
                jsonData: true,
                tip: '确认退回？',
            }).done(function (rst) {
                $pop.close(rejectFormTemPop);
                $pop.closeTabWindow(bizFormData.tabWindowTitle);
            });
        }

    };


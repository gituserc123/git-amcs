<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>信息、行政、公共安全事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
    <style type="text/css">
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
                <span class="s-title">事件描述</span>
            </h2>
            <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

            <div class="row">
                <div class="p12">
                    <div class="item-one">
                        <label class="lab-item solab-s">详细情况：</label>
                        <textarea class="txt txt-validate required adaptiveTextarea" name="detailInfoText" id="detailInfoText" dataOptions="required:ture" noNull="请填写详细情况"
                                  validType="" style="height: 50px;text-indent: 0" >${ae.detailInfo!}${ae.detailInfoOne!}${ae.detailInfoTwo!}</textarea>
                    </div>
                </div>
            </div>
            <input class="txt" type="hidden" id="detailInfo" name="detailInfo" value=""/>
            <input class="txt" type="hidden" id="detailInfoOne" name="detailInfoOne" value=""/>
            <input class="txt" type="hidden" id="detailInfoTwo" name="detailInfoTwo" value=""/>
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

            //暂存前执行事件 false中断保存
            window.beforeStash = function(){
                if($("#detailInfoText").val()){
                    var str = $("#detailInfoText").val();
                    var splitLen = Math.ceil(str.length / 3);
                    var result = []; //结果将会存储到 长度为 3 的数组里
                    for (var i = 0; i < 3; i++) {
                        result.push(str.substr(0, splitLen));
                        str = str.substr(splitLen, str.length+1);
                    }
                    $("#detailInfo").val(result[0]);
                    $("#detailInfoOne").val(result[1]);
                    $("#detailInfoTwo").val(result[2]);
                }else{
                    $("#eventText").val(null);
                    $("#detailInfo").val(null);
                    $("#detailInfoOne").val(null);
                    $("#detailInfoTwo").val(null);
                }
                return true;
            }

            //保存前执行事件 false中断保存
            window.beforeSubmit = function(){
                if($("#detailInfoText").val()){
                    var str = $("#detailInfoText").val();
                    var splitLen = Math.ceil(str.length / 3);
                    var result = []; //结果将会存储到 长度为 3 的数组里
                    for (var i = 0; i < 3; i++) {
                        result.push(str.substr(0, splitLen));
                        str = str.substr(splitLen, str.length+1);
                    }
                    $("#detailInfo").val(result[0]);
                    $("#detailInfoOne").val(result[1]);
                    $("#detailInfoTwo").val(result[2]);
                }else{
                    $("#detailInfoText").val(null);
                    $("#detailInfo").val(null);
                    $("#detailInfoOne").val(null);
                    $("#detailInfoTwo").val(null);
                }
                return true;
            }

            [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
            [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
            [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]

            $('.patient-inp').each(function() {
                var inputElement = $(this);
                // 获取 data-options 属性的值并转换为对象
                var options = inputElement.validatebox('options');
                // 检查 options 是否存在
                if (options) {
                    // 删除 required 属性
                    if (options.required) {
                        options.required = false;
                    }
                    // 重新初始化 validatebox 组件以应用新的配置
                    inputElement.validatebox(options);
                }
            });
            $('.patient-inp-textbox').each(function() {
                var inputElement = $(this);
                // 获取 data-options 属性的值并转换为对象
                var options = inputElement.combobox('textbox').validatebox('options');
                // 检查 options 是否存在
                if (options) {
                    // 删除 required 属性
                    if (options.required) {
                        options.required = false;
                    }
                    // 重新初始化 validatebox 组件以应用新的配置
                    inputElement.combobox('textbox').validatebox(options);
                }
            });
        })
    </script>
</body>

</html>
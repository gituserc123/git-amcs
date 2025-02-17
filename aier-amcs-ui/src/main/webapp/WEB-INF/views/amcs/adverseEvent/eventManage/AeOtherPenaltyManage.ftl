<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>行政处罚事件</title>
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
            <div class="row">
	             <div class="p3">
		            <div class="item-one">
		                <label class="lab-item">处罚金额</label>
		                    <input class="txt easyui-validatebox inp-penalty" type="text" validType="pNumber['金额必须为正数']" name="penaltyAmount"   data-options="precision:2" value="${ae.penaltyAmount}" />
		                <em class="em-at em-dropAt">元</em>
		            </div>
		        </div>
	        </div>
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
                        <textarea class="txt txt-validate required adaptiveTextarea" name="detailInfo" id="detailInfo" dataOptions="required:ture" noNull="请填写详细情况"
                              placeholder="何时何地何种原因被当地行政部门处罚"    validType="" style="height: 50px;text-indent: 0" >${ae.detailInfo}</textarea>
                    </div>
                </div>
            </div>
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
            [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
            [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
            [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]
            
            $("#patient-info-wrapper").remove()
            

        })
    </script>
</body>

</html>
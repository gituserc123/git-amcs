<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>标本采集不良事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
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
            <div class="p4">
                <div class="item-one">
                    <label class="lab-item">标本采集类别：</label>
                    [@ui_select id="sampleGatherType" name="sampleGatherType" tag="@ae@sample_gather_type"  uiShowDefault=false  style="width:50%;" dataOptions="editable:false,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('7', v)>-1){
                                $('#sampleGatherTypeRemark').show();
                            };
                            if($.inArray('7', v)==-1){
                                $('#sampleGatherTypeRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="sampleGatherTypeRemark" name="sampleGatherTypeRemark" placeholder="请填写其他" >${ae.sampleGatherTypeRemark!}</textarea>
                </div>
            </div>
            <div class="p4">
                <div class="item-one">
                </div>
            </div>
            <div class="p4">
                <div class="item-one">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one">
                    <label class="lab-item solab-ld">标本不合格原因：</label>
                    <textarea class="txt txt-validate required adaptiveTextarea" name="sampleUnqualifiedReason"
                              id="sampleUnqualifiedReason"
                              validType="" style="height: 50px;text-indent: 0"  />${ae.sampleUnqualifiedReason!}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one">
                    <label class="lab-item">标本采集时间：</label>
                    <input id="sampleGatherTime" type="text" class="txt txt-validate so-date" style="width:100%;"
                           data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd HH:mm'}" name="sampleGatherTime"
                           validType="commonDate" noNull="标本采集时间"  value="${ae.sampleGatherTime!}"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one">
                    <label class="lab-item">标本送检时间：</label>
                    <input id="sampleSendTime" type="text" class="txt txt-validate so-date" style="width:100%;"
                           data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd HH:mm'}" name="sampleSendTime" validType="commonDate"
                           noNull="标本送检时间"  value="${ae.sampleSendTime!}"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one">
                    <label class="lab-item">发现错误时间：</label>
                    <input id="findMistakeTime" type="text" class="txt txt-validate so-date" style="width:100%;"
                           data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd HH:mm'}" name="findMistakeTime"
                           validType="commonDate" noNull="发现错误时间"  value="${ae.findMistakeTime!}"/>
                </div>
            </div>
            <div class="row">
                <div class="p12">
                    <div class="item-one solab-s">
                        <label class="lab-item solab-s">详细情况：</label>
                        <textarea class="txt txt-validate required adaptiveTextarea" name="detailInfo" id="detailInfo"
                                  validType="" style="height: 50px;text-indent: 0" placeholder="患者基本信息、主诉，入院时间，诊断，主要诊疗过程，何时何地发生什么？有什么后果？ 给了什么处理？预后如何？" >${ae.detailInfo!}</textarea>
                    </div>
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
    requirejs(["eventReview", "eventTableGroup", "eventFlowchart", "uploadImages", "eventImage",'myupload', 'pub'], function (eventReview, eventTableGroup, eventFlowchart, uploadImages, eventImage) {
        //暂存前执行事件 false中断保存
        window.beforeStash = function(){
            return true;
        }
        //保存前执行事件 false中断保存
        window.beforeSubmit = function(){
            return true;
        }
        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]
        $('#contactType').combobox({
            onChange: function (newValue, oldValue) {
                let brand = $('#contactType').combobox('getValue');
                filtercontactBrand(brand);
            }
        });

        function filtercontactBrand(brand) {
            let url = base + '/ui/amcs/dict/getAeDict?type=contact_brand&filter=' + brand;
            $('#contactBrand').combobox('reload', url);
        }

        var baseExam = {
            init : function (){
                if(eval('${ae.sampleGatherType}')==null){
                    $('#sampleGatherTypeRemark').hide();
                }else{
                    $('#sampleGatherType').combobox('setValue',eval('${ae.sampleGatherType}'));
                    if($.inArray('7', eval('${ae.sampleGatherType}'))>-1 || eval('${ae.sampleGatherType}')=='7'){
                        $('#sampleGatherTypeRemark').show();
                    }else{
                        $('#sampleGatherTypeRemark').hide();
                    }
                }
            },
        };
        $(function () {
            //初始化表单
            baseExam.init();
            if($.isEmptyObject('${ae.id}') && !$.isEmptyObject('${ae.patientNo}')){
				queryPatientInfo('${ae.patientNo}');
			}

        });
        

    })
</script>
</body>

</html>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>跌倒/坠床事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
    <style>
        .layui-layer-close1{
            background-position: -188px -40px !important;
        }
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
            <div class="p6" style="width: 43.93617020799999%;">
                <div class="item-one"  style="padding-left: 58px;">
                	<label class="lab-item" style="text-align: left;left: 45px;">就诊类型：</label>
                	<label class="lab-val" style="margin: 0px 0px 0px 50px;"><input type="radio" class="radType" name="visitType" value="1" [#if ae.visitType == 1] checked=checked [/#if]/>门诊</label>
                    <label class="lab-val" style="margin: 0px 0px 0px 5px;"><input type="radio" class="radType" name="visitType" value="2" [#if ae.visitType == 2||!ae.visitType] checked=checked [/#if]/>住院</label>
                    <label class="lab-item" style="text-align: left;left: 240px;top: 1px;line-height: 26px;color: rgb(102, 102, 102);">跌倒对象：</label>
                    <label class="lab-val" style="margin: 0px 0px 0px 100px;"><input type="radio" class="radioFallPerson" name="fallPerson" value="1" [#if ae.fallPerson == 1||!ae.fallPerson] checked=checked [/#if]/>患者本人</label>
                    <label class="lab-val" style="margin: 0px 0px 0px 5px;"><input type="radio" class="radioFallPerson" name="fallPerson" value="2" [#if ae.fallPerson == 2||!ae.fallPerson] checked=checked [/#if]/>患者家属</label>
                    <label class="lab-val" style="margin: 0px 0px 0px 5px;"><input type="radio" class="radioFallPerson" name="fallPerson" value="3" [#if ae.fallPerson == 3||!ae.fallPerson] checked=checked [/#if]/>员工</label>
                </div>
            </div>
            <div class="p2" style="margin-left: 0px;">
                <div class="item-one solab-ld" style="padding-left: 135px;">
                    <label class="lab-item"  style="text-align: left;width: 133px;">跌倒前评估为高危患者：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="prefallevalSign" name="prefallevalSign" data-options="required:true"  value="${ae.prefallevalSign!}">
                        <option value="1" [#if ae.prefallevalSign==1]selected="selected"[/#if] >是</option>
                        <option value="0" [#if ae.prefallevalSign==0]selected="selected"[/#if] >否</option>
                    </select>
                </div>
            </div>
            <div class="p4" style="margin-left: 0px;float: right;">
                <div class="item-one solab-lc" style="padding-left: 105px;">
                    <label class="lab-item" style="left: -67px;">风险评估工具：</label>
                    [#--<select id='riskEvalTools' name='riskEvalTools' style="width:200px;height:50px;" multiple="multiple">
                        　　　　<option value="1">爱尔眼科医院防范患者跌倒（坠床）评估记录表</option>
                        　　　　<option value="2">患者自理能力的评估表</option>
                        　　　　<option value="3">其他（）</option>
                    </select>--]
                    [@ui_select  uiShowDefault=false id="riskEvalTools" name="riskEvalTools" tag="@ae@risk_eval_tools"  style="width:65%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('3', v)>-1){
                                $('#riskEvalToolsRemark').show();
                            };
                            if($.inArray('3', v)==-1){
                                $('#riskEvalToolsRemark').hide();
                            }
                        })" filterkey="firstSpell"/]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:25%;" type="text" id="riskEvalToolsRemark" name="riskEvalToolsRemark" placeholder="请填写其他" >${ae.riskEvalToolsRemark!}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">护理级别是否与评估一致：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="nursingLevelSign" name="nursingLevelSign" data-options="required:true" value="${ae.nursingLevelSign!}">
                        <option value="1" [#if ae.nursingLevelSign=='1']selected="selected"[/#if] >是</option>
                        <option value="0" [#if ae.nursingLevelSign=='0']selected="selected"[/#if] >否</option>
                    </select>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lc">
                    <label class="lab-item">是否按要求进行护理巡视：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="nursingCheckSign" name="nursingCheckSign" data-options="required:true" value="${ae.nursingCheckSign!}">
                        <option value="1" [#if ae.nursingCheckSign=='1']selected="selected"[/#if]>是</option>
                        <option value="0" [#if ae.nursingCheckSign=='0']selected="selected"[/#if]>否</option>
                    </select>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
				    <label class="lab-item">病人近一年跌倒≥1次(不含本次)：</label>
                    [@ui_select id="patientFallOneYear" name="patientFallOneYear" tag="@ae@patient_fall_one_year"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.patientFallOneYear!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">最近1次事件风险评估距事件发生时间：</label>
                    [@ui_select id="recentEvalFallTime" name="recentEvalFallTime" tag="@ae@recent_eval_fall_time"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.recentEvalFallTime!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lc">
                    <label class="lab-item">最近一次风险评估得分分级：</label>
                    [@ui_select id="recentEvalScore" name="recentEvalScore" tag="@ae@recent_eval_score"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.recentEvalScore!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lc">
                    <label id="qlable"   onClick="(function (){
                    $pop.tips(`跌倒造成伤害程度级别说明：<br/>
                        0级:跌倒或坠床后，评估无损伤症状或体征<br/>
                        1级：跌倒/坠床导致血肿、擦伤、疼痛，需要冰敷、包扎、伤口清洁、肢体抬高、局部用药等。<br/>
                        2级:跌倒/坠床导致肌肉或关节损伤,需要缝合、使用皮肤胶、夹板固定等。<br/>
                        3级:跌倒/坠床导致骨折、意识丧失、神经或内部损伤，需要手术、石膏、牵引等。<br/>
                        死亡：因跌倒受伤而死亡（而不是由于引起跌倒的生理事件本身而致死)。`,'#qlable',{time: 0,closeBtn: true,maxWidth: 600});
                    })()" class="lab-item">跌倒造成的伤害程度<span style="padding-left: 2px"  ><i class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                    [@ui_select id="fallInjuryLevel" name="fallInjuryLevel" tag="@ae@fall_injury_level"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.fallInjuryLevel!}"  /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">事件发生前的独立活动能力：</label>
                    [@ui_select id="preEventAdl" name="preEventAdl" tag="@ae@pre_event_adl"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.preEventAdl!}" /]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">当事人发生前意识状态：</label>
                    [@ui_select id="preEventAwareStatus" name="preEventAwareStatus" tag="@ae@event_aware_status"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.preEventAwareStatus!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one  solab-lc">
                    <label class="lab-item">当事人发生后意识状态：</label>
                    [@ui_select id="postEventAwareStatus" name="postEventAwareStatus" tag="@ae@event_aware_status"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.postEventAwareStatus!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one  solab-ld">
                    <label class="lab-item">患者活动状态：</label>
                    [@ui_select id="patientAdl" name="patientAdl" tag="@ae@patient_adl"  style="width:50%;" dataOptions="required:true,editable:false,
                        onChange:((v)=>{
                            if($.trim(v) == '12'){
                                $('#patientAdlRemark').show();
                            }else{
                                $('#patientAdlRemark').hide();
                            }
                        })" filterkey="firstSpell" value="${ae.patientAdl!}" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="patientAdlRemark" name="patientAdlRemark" placeholder="请填写其他" >${ae.patientAdlRemark!}</textarea>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">事件发生时有无其他人员在场：</label>
                    [@ui_select  uiShowDefault=false id="eventPresentPerson" name="eventPresentPerson" tag="@ae@event_present_person"  style="width:50%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('5', v)>-1){
                                $('#eventPresentPersonRemark').show();
                            };
                            if($.inArray('5', v)==-1){
                                $('#eventPresentPersonRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="eventPresentPersonRemark" name="eventPresentPersonRemark" placeholder="请填写其他" >${ae.eventPresentPersonRemark!}</textarea>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">跌倒(坠床)发生时有无约束：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="fallConstraintSign" name="fallConstraintSign" data-options="required:true" value="${ae.fallConstraintSign!}">
                        <option value="1" [#if ae.fallConstraintSign=='1']selected="selected"[/#if]>是</option>
                        <option value="0" [#if ae.fallConstraintSign=='0']selected="selected"[/#if]>否</option>
                    </select>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lc">
                    <label class="lab-item">期间是否有陪护：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="inHospCareSign" name="inHospCareSign" data-options="required:true" value="${ae.inHospCareSign!}">
                        <option value="1" [#if ae.inHospCareSign=='1']selected="selected"[/#if]>是</option>
                        <option value="0" [#if ae.inHospCareSign=='0']selected="selected"[/#if]>否</option>
                    </select>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lc">
                    <label class="lab-item">陪护人员是否具备陪护能力：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="carePersonSign" name="carePersonSign" data-options="required:true" value="${ae.carePersonSign!}">
                        <option value="1" [#if ae.carePersonSign=='1']selected="selected"[/#if]>是</option>
                        <option value="0" [#if ae.carePersonSign=='0']selected="selected"[/#if]>否</option>
                    </select>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">该患者跌倒/坠床第几次：</label>
                    [@ui_select id="fallTimes" name="fallTimes" tag="@ae@fall_times"  style="width:100%;" dataOptions="required:true,editable:false" filterkey="firstSpell" value="${ae.fallTimes!}" /]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-ld">
                    <label class="lab-item">跌倒危险因素：</label>
                    [@ui_select  uiShowDefault=false id="fallDangerFactor" name="fallDangerFactor" tag="@ae@fall_danger_factor"  style="width:50%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('5', v)>-1){
                                $('#fallDangerFactorRemark').show();
                            };
                            if($.inArray('5', v)==-1){
                                $('#fallDangerFactorRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="fallDangerFactorRemark" name="fallDangerFactorRemark" placeholder="请填写其他" ></textarea>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">跌倒前用药情况：</label>
                    [@ui_select  uiShowDefault=false id="preFallCondition" name="preFallCondition" tag="@ae@pre_fall_condition"  style="width:50%;" class="easyui-combobox" dataOptions="required:true,multiple:true,
                        onChange:((v)=>{
                            if($.inArray('9', v)>-1){
                                $('#preFallConditionRemark').show();
                            };
                            if($.inArray('9', v)==-1){
                                $('#preFallConditionRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="preFallConditionRemark" name="preFallConditionRemark" placeholder="请填写其他" ></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-ld">
                    <label class="lab-item">跌倒后院内处置情况：</label>
                    <textarea class="txt txt-validate adaptiveTextarea required" type="text" id="postFallCondition" name="postFallCondition" placeholder="" value="">${ae.postFallCondition!}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-ld">
                    <label class="lab-item">详细情况：</label>
                    <textarea class="txt txt-validate adaptiveTextarea required"  style="width:1000px" cols="100" row="10" type="text" id="eventDesc"  noNull="请填写事件详细情况" name="eventDesc" placeholder="患者基本信息、主诉，入院时间，诊断，主要诊疗过程，何时何地发生什么？有什么后果？ 给了什么处理？预后如何？" value="">${ae.eventDesc!}</textarea>
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

        $('.radType').click(function(){
            let radio = $(this).val();
            if (radio == '1') {
				$('#riskEvalTools').combobox('disable');
				$('#riskEvalTools').combobox('setValue','');
				$('#nursingLevelSign').combobox('setValue','');
				$('#nursingLevelSign').combobox('disable');
				$('#nursingCheckSign').combobox('setValue','');
				$('#nursingCheckSign').combobox('disable');
				$('#recentEvalFallTime').combobox('setValue','');
				$('#recentEvalFallTime').combobox('disable');
				$('#recentEvalScore').combobox('setValue','');
				$('#recentEvalScore').combobox('disable');
				$('#patientFallOneYear').combobox('enable');
				$('#patientFallOneYear').combobox('setValue','${ae.patientFallOneYear!}');
            }
            if (radio == '2') {
				$('#riskEvalTools').combobox('enable');
				$('#riskEvalTools').combobox('setValue',eval('${ae.riskEvalTools!}'));
				$('#nursingLevelSign').combobox('enable');
				$('#nursingLevelSign').combobox('setValue','${ae.nursingLevelSign!}');
				$('#nursingCheckSign').combobox('enable');
				$('#nursingCheckSign').combobox('setValue','${ae.nursingCheckSign!}');
				$('#recentEvalFallTime').combobox('enable');
				$('#recentEvalFallTime').combobox('setValue','${ae.recentEvalFallTime!}');
				$('#recentEvalScore').combobox('enable');
				$('#recentEvalScore').combobox('setValue','${ae.recentEvalScore!}');
				$('#patientFallOneYear').combobox('disable');
				$('#patientFallOneYear').combobox('setValue','');
            }       
        })


        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]     
        [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]
        $('#contactType').combobox({
            onChange: function(newValue,oldValue){
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
                if(eval('${ae.riskEvalTools}')==null){
                    $('#riskEvalToolsRemark').hide();
                }else{
                    $('#riskEvalTools').combobox('setValue',eval('${ae.riskEvalTools}'));
                    if($.inArray('3', eval('${ae.riskEvalTools}'))>-1 || eval('${ae.riskEvalTools}')=='3'){
                        $('#riskEvalToolsRemark').show();
                    }else{
                        $('#riskEvalToolsRemark').hide();
                    }
                }
                if(eval('${ae.patientAdl}')==null){
                    $('#patientAdlRemark').hide();
                }else{
                    $('#patientAdl').combobox('setValue',eval('${ae.patientAdl}'));
                    if(eval('${ae.patientAdl}')=='12'){
                        $('#patientAdlRemark').show();
                    }else{
                        $('#patientAdlRemark').hide();
                    }
                }
                if(eval('${ae.eventPresentPerson}')==null){
                    $('#eventPresentPersonRemark').hide();
                }else{
                    $('#eventPresentPerson').combobox('setValue',eval('${ae.eventPresentPerson}'));
                    if(eval('${ae.eventPresentPerson}')=='5'){
                        $('#eventPresentPersonRemark').show();
                    }else{
                        $('#eventPresentPersonRemark').hide();
                    }
                }
                if(eval('${ae.fallDangerFactor}')==null){
                    $('#fallDangerFactorRemark').hide();
                }else{
                    $('#fallDangerFactor').combobox('setValue',eval('${ae.fallDangerFactor}'));
                    if(eval('${ae.fallDangerFactor}')=='5'){
                        $('#fallDangerFactorRemark').show();
                    }else{
                        $('#fallDangerFactorRemark').hide();
                    }
                }
                if(eval('${ae.preFallCondition}')==null){
                    $('#preFallConditionRemark').hide();
                }else{
                    $('#preFallCondition').combobox('setValue',eval('${ae.preFallCondition}'));
                    if(eval('${ae.preFallCondition}')=='9'){
                        $('#preFallConditionRemark').show();
                    }else{
                        $('#preFallConditionRemark').hide();
                    }
                }
                if(eval('${ae.prefallevalSign}') == null){
                    $('#prefallevalSign').combobox('setText','');
                }
                if(eval('${ae.nursingLevelSign}') == null){
                    $('#nursingLevelSign').combobox('setText','');
                }
                if(eval('${ae.nursingCheckSign}') == null){
                    $('#nursingCheckSign').combobox('setText','');
                }
                if(eval('${ae.fallConstraintSign}') == null){
                    $('#fallConstraintSign').combobox('setText','');
                }
                if(eval('${ae.inHospCareSign}') == null){
                    $('#inHospCareSign').combobox('setText','');
                }
                if(eval('${ae.carePersonSign}') == null){
                    $('#carePersonSign').combobox('setText','');
                }
                /*if(eval('${ae.staffYears}')==null){
                    $('.staffYearsInEye').css('display','none');
                }else{
                    $('.staffYearsInEye').css('display','');
                }*/

            },
        };

        $(function () {
            //初始化表单 fallType
            baseExam.init();
			var visitType ='${ae.visitType!}';
            var fallType ='${ae.fallPerson!}';
            if(!fallType){
                $('.radioFallPerson').eq(0).click();
            }
			if(visitType == 1){
				$('.radType').eq(0).click();
			}else{
				$('.radType').eq(1).click();
			}
			if($.isEmptyObject('${ae.id}') && !$.isEmptyObject('${ae.patientNo}')){
				queryPatientInfo('${ae.patientNo}');
			}
        });
        

    })
</script>
</body>

</html>
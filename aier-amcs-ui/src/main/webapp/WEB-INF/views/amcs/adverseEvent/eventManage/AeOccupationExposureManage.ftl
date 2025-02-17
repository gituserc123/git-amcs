<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>血/体液职业暴露事件</title>
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
                    <label class="lab-item">锐器类型：</label>
                    [@ui_select id="sharpObjType" name="sharpObjType" tag="@ae@sharpObjType"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.sharpObjType!}" /]
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lb">
                    <label class="lab-item">锐器名称（选)：</label>
                    [@ui_select id="sharpObjName" name="sharpObjName"  class="easyui-combobox" tag="@ae@sharpObjName"  style="width:50%;" dataOptions="required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '11'){
                                $('#sharpObjNameRemark').show();
                            }else{
                                $('#sharpObjNameRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="sharpObjNameRemark" name="sharpObjNameRemark" placeholder="请填写其他" >${ae.sharpObjNameRemark!}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">关联操作(选)：</label>
                    [@ui_select id="relaOperation" name="relaOperation"  class="easyui-combobox" tag="@ae@relaOperation"  style="width:50%;" dataOptions="required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '18'){
                                $('#relaOperationRemark').show();
                            }else{
                                $('#relaOperationRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="relaOperationRemark" name="relaOperationRemark" placeholder="请填写其他" >${ae.relaOperationRemark!}</textarea>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">发生方式：</label>
                    [@ui_select id="occurForm" name="occurForm" tag="@ae@occurForm"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.occurForm!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">锐器是否被污染：</label>
                    [@ui_select id="soPolluteFlag" name="soPolluteFlag" tag="@ae@soPolluteFlag"  style="width:100%;" dataOptions="editable:false,required:true,
                        /*
                        onChange:((v)=>{
                            if($.trim(v) == '1'){
                                $('.patient-info').validatebox('disableValidation');
                                $('.patient-info').attr('disabled',true);
                                $('#pHbsag').combobox('disableValidation');
                                $('#pHbsag').combobox('disable');
                                $('#pHbsab').combobox('disableValidation');
                                $('#pHbsab').combobox('disable');
                                $('#pHcv').combobox('disableValidation');
                                $('#pHcv').combobox('disable');
                                $('#pHiv').combobox('disableValidation');
                                $('#pHiv').combobox('disable');
                                $('#pSyphilisAb').combobox('disableValidation');
                                $('#pSyphilisAb').combobox('disable');
                                $('#pSyphilisReagin').combobox('disableValidation');
                                $('#pSyphilisReagin').combobox('disable');
                                $('#pInjectionDrug').combobox('disableValidation');
                                $('#pInjectionDrug').combobox('disable');
                                $('#pHaemophilia').combobox('disableValidation');
                                $('#pHaemophilia').combobox('disable');
                                $('#pHaemophilia').combobox('disableValidation');
                                $('#pRelaBehavior').combobox('disableValidation');
                                $('#pRelaBehavior').combobox('disable');
                            }else{
                                $('.patient-info').attr('disabled',false);
                                $('.patient-info').validatebox('enableValidation');
                                $('#pHbsag').combobox({disabled:false});
                                $('#pHbsag').combobox('enableValidation');
                                $('#pHbsab').combobox({disabled:false});
                                $('#pHbsab').combobox('enableValidation');
                                $('#pHcv').combobox({disabled:false});
                                $('#pHcv').combobox('enableValidation');
                                $('#pHiv').combobox({disabled:false});
                                $('#pHiv').combobox('enableValidation');
                                $('#pSyphilisAb').combobox({disabled:false});
                                $('#pSyphilisAb').combobox('enableValidation');
                                $('#pSyphilisReagin').combobox({disabled:false});
                                $('#pSyphilisReagin').combobox('enableValidation');
                                $('#pInjectionDrug').combobox({disabled:false});
                                $('#pInjectionDrug').combobox('enableValidation');
                                $('#pHaemophilia').combobox({disabled:false});
                                $('#pHaemophilia').combobox('enableValidation');
                                $('#pRelaBehavior').combobox({disabled:false});
                                $('#pRelaBehavior').combobox('enableValidation');
                            }
                        }) */" filterkey="firstSpell" value="${ae.soPolluteFlag!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">锐器伤后是否进行了定期追踪和检测：</label>
                    <select class="drop easyui-combobox required" style="width:100%;" id="soTraceCheck" name="soTraceCheck" data-options="required:true"  value="${ae.soTraceCheck!}">
                    [#--<select class="drop easyui-combobox required" style="width:100%;" id="soTraceCheck" name="soTraceCheck" data-options="editable:false, required:true">--]
                        <option value="1"  [#if ae.soTraceCheck==1] selected="selected" [/#if] >是</option>
                        <option value="0"  [#if ae.soTraceCheck==0] selected="selected" [/#if] >否</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">粘膜接触部位：</label>
                    [@ui_select id="mucosaTouchPart" name="mucosaTouchPart" tag="@ae@mucosaTouchPart"  style="width:50%;" dataOptions="editable:false,required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '4'){
                                $('#mucosaTouchPartRemark').show();
                            }else{
                                $('#mucosaTouchPartRemark').hide();
                            }
                        })" filterkey="firstSpell" value="${ae.mucosaTouchPart!}"  /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="mucosaTouchPartRemark" name="mucosaTouchPartRemark" placeholder="请填写其他" >${ae.mucosaTouchPartRemark!}</textarea>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">粘膜接触物质：</label>
                    [@ui_select id="mucosaTouchMaterial" name="mucosaTouchMaterial" tag="@ae@mucosaTouchMaterial"  style="width:50%;" dataOptions="editable:false,required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '2'){
                                $('#mucosaTouchMaterialRemark').show();
                            }else{
                                $('#mucosaTouchMaterialRemark').hide();
                            }
                        })" filterkey="firstSpell" value="${ae.mucosaTouchMaterial!}" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="mucosaTouchMaterialRemark" name="mucosaTouchMaterialRemark" placeholder="请填写其他" >${ae.mucosaTouchMaterialRemark!}</textarea>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">粘膜接触关联操作：</label>
                    [@ui_select id="mucosaTouchOpertion" name="mucosaTouchOpertion" tag="@ae@mucosaTouchOpertion"  style="width:50%;" dataOptions="editable:false,required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '4'){
                                $('#mucosaTouchOpertionRemark').show();
                            }else{
                                $('#mucosaTouchOpertionRemark').hide();
                            }
                        })" filterkey="firstSpell" value="${ae.mucosaTouchOpertion!}" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="mucosaTouchOpertionRemark" name="mucosaTouchOpertionRemark" placeholder="请填写其他" >${ae.mucosaTouchOpertionRemark!}</textarea>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">保护措施：</label>
                    [@ui_select id="protectMeasure" name="protectMeasure" tag="@ae@protectMeasure"  style="width:50%;" dataOptions="editable:false,required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '4'){
                                $('#protectMeasureRemark').show();
                            }else{
                                $('#protectMeasureRemark').hide();
                            }
                        })" filterkey="firstSpell" value="${ae.protectMeasure!}" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="protectMeasureRemark" name="protectMeasureRemark" placeholder="请填写其他" >${ae.protectMeasureRemark!}</textarea>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p12">
                <div class="item-one">
                    <label class="lab-item">详细情况：</label>
                    <textarea class="txt txt-validate adaptiveTextarea required" style="width:1000px" cols="100" row="10"  type="text" id="eventDesc"  noNull="请填写事件详细情况" name="eventDesc" placeholder="" value="">${ae.eventDesc!}</textarea>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                </div>
            </div>
        </div>

        <h2 class="h2-title-a">
            <span class="s-title">受害者资料</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p6">
                <div class="item-one solab-ld">
                    <label class="lab-item">事件发生后受影响的对象：</label>
                    [@ui_select id="postEffectObj" name="postEffectObj" tag="@ae@postEffectObj"  uiShowDefault=false  style="width:50%;" dataOptions="editable:false,multiple:true,required:true,
                        onChange:((v)=>{
                            if($.inArray('4', v)>-1){
                                $('#postEffectObjRemark').show();
                            };
                            if($.inArray('4', v)==-1){
                                $('#postEffectObjRemark').hide();
                            }
                        })" filterkey="firstSpell" /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="postEffectObjRemark" name="postEffectObjRemark" placeholder="请填写其他" >${ae.postEffectObjRemark!}</textarea>
                </div>
            </div>
            <div class="p6">
            </div>
        </div>

        <h2 class="h2-title-a">
            <span class="s-title">患者病史信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">科室：</label>
                    <input class="txt txt-validate patient-info  easyui-validatebox" type="text" id="patientDepartment" name="patientDepartment" dataOptions="editable:false" value="${ae.patientDepartment!}" />
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">采集时间：</label>
                    <input id="pCollectTime" class="txt txt-validate so-date patient-info" type="text" name="pCollectTime" data-opt="editable:false,maxDate:new Date(),format:'yyyy-MM-dd',type:'date',
                        onpicked:function(obj){
                                $('#bloodSendDate').validatebox('enableValidation');
                                $('#pHbsag').combobox('enableValidation');
                                $('#pHbsab').combobox('enableValidation');
                                $('#pHcv').combobox('enableValidation');
                                $('#pHiv').combobox('enableValidation');
                                $('#pSyphilisAb').combobox('enableValidation');
                                $('#pSyphilisReagin').combobox('enableValidation');
                                $('#pInjectionDrug').combobox('enableValidation');
                                $('#pHaemophilia').combobox('enableValidation');
                                $('#pRelaBehavior').combobox('enableValidation');
                           },
                           oncleared:function(){
                                $('#bloodSendDate').validatebox('disableValidation');
                                $('#pHbsag').combobox('disableValidation');
                                $('#pHbsab').combobox('disableValidation');
                                $('#pHcv').combobox('disableValidation');
                                $('#pHiv').combobox('disableValidation');
                                $('#pSyphilisAb').combobox('disableValidation');
                                $('#pSyphilisReagin').combobox('disableValidation');
                                $('#pInjectionDrug').combobox('disableValidation');
                                $('#pHaemophilia').combobox('disableValidation');
                                $('#pRelaBehavior').combobox('disableValidation');
                           }" value="[#if ae.pCollectTime??]${ae.pCollectTime?date("yyyy-MM-dd")}[/#if]"  />
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">血标本送检日期*：</label>
                    <input id="bloodSendDate" class="txt txt-validate so-date required patient-info" type="text" name="bloodSendDate" noNull="请填写日期" dataOptions="editable:false,required:true,maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" value="[#if ae.bloodSendDate??]${ae.bloodSendDate?date("yyyy-MM-dd")}[/#if]" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">HBsAg乙肝表面抗原：</label>
                    [@ui_select id="pHbsag" name="pHbsag" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pHbsag!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">HBsAb乙肝表面抗体：</label>
                    [@ui_select id="pHbsab" name="pHbsab" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pHbsab!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">HCV丙肝：</label>
                    [@ui_select id="pHcv" name="pHcv" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pHcv!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">HIV艾滋病：</label>
                    [@ui_select id="pHiv" name="pHiv" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pHiv!}" /]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">梅毒特异性抗体：</label>
                    [@ui_select id="pSyphilisAb" name="pSyphilisAb" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pSyphilisAb!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">梅毒快速血浆反应素：</label>
                    [@ui_select id="pSyphilisReagin" name="pSyphilisReagin" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pSyphilisReagin!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">静脉吸毒：</label>
                    [@ui_select id="pInjectionDrug" name="pInjectionDrug" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pInjectionDrug!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">血友病：</label>
                    [@ui_select id="pHaemophilia" name="pHaemophilia" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pHaemophilia!}" /]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">相关性行为：</label>
                    [@ui_select id="pRelaBehavior" name="pRelaBehavior" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.pRelaBehavior!}" /]
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
            <div class="p3">
                <div class="item-one">
                </div>
            </div>
        </div>

        <h2 class="h2-title-a">
            <span class="s-title">发生暴露的工作人员信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        [#--<div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">姓名：</label>
                    <input class="txt txt-validate" type="text" id="wName" name="wName" value="" />
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
            <div class="p3">
                <div class="item-one">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">性别：</label>
                    [@ui_select id="wSex" name="wSex" tag="sex" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value=""/]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">年龄：</label>
                    <input class="easyui-numberbox required" noNull="必填" type="text" id="wAge" name="wAge"
                           data-options="missingMessage:'必填',required:true,min:0,max:100,precision:0" style="width:100%"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">专业技术职称：</label>
                    <input class="txt txt-validate" type="text" id="wTechTitle" name="wTechTitle" value="" />
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">职称获得时间：</label>
                    <input id="wTechTitleTime" class="txt txt-validate so-date" type="text" name="wTechTitleTime" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写日期"  value=""  />
                </div>
            </div>
        </div>--]
        <div class="row">
            [#--<div class="p3">
                <div class="item-one">
                    <label class="lab-item">工作人员工别：</label>
                    [@ui_select id="wJobType" name="wJobType" tag="@ae@wJobType"  style="width:100%;" dataOptions="editable:false" filterkey="firstSpell" value="" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">工作人员身份：</label>
                    [@ui_select id="wIdentity" name="wIdentity" tag="@ae@wIdentity"  style="width:100%;" dataOptions="editable:false" filterkey="firstSpell" value="" /]
                </div>
            </div>--]
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">是否接种乙肝疫苗：</label>
                    <select class="drop easyui-combobox" style="width:100%;" id="wHbvSign" name="wHbvSign" data-options="required:true,value:'${ae.wHbvSign}'">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-ld">
                    <label class="lab-item">既往乙肝抗体情况：</label>
                    <select class="drop easyui-combobox" style="width:100%;" id="wHbsabSign" name="wHbsabSign" data-options="required:true,value:'${ae.wHbsabSign}'">
                        <option value="1"  [#if ae.wHbsabSign==1] selected="selected" [/#if]>有</option>
                        <option value="0"  [#if ae.wHbsabSign==0] selected="selected" [/#if]>无</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">HBsAg乙肝表面抗原：</label>
                    [@ui_select id="wHbsag" name="wHbsag" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wHbsag!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">HBsAb乙肝表面抗体：</label>
                    [@ui_select id="wHbsab" name="wHbsab" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wHbsab!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">HCV丙肝：</label>
                    [@ui_select id="wHcv" name="wHcv" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wHcv!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">HIV艾滋病：</label>
                    [@ui_select id="wHiv" name="wHiv" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wHiv!}" /]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">梅毒特异性抗体：</label>
                    [@ui_select id="wSyphilisAb" name="wSyphilisAb" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wSyphilisAb!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">梅毒快速血浆反应素：</label>
                    [@ui_select id="wSyphilisReagin" name="wSyphilisReagin" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wSyphilisReagin!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">静脉吸毒：</label>
                    [@ui_select id="wInjectionDrug" name="wInjectionDrug" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wInjectionDrug!}" /]
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">血友病：</label>
                    [@ui_select id="wHaemophilia" name="wHaemophilia" tag="@ae@medicalSelect"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.wHaemophilia!}" /]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-lb">
                    <label class="lab-item">采集时间：</label>
                    <input id="wCollectTime" class="txt txt-validate so-date" type="text" name="wCollectTime" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写日期" value="[#if ae.wCollectTime??]${ae.wCollectTime?date("yyyy-MM-dd")}[/#if]"  />
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lb">
                    <label class="lab-item">暴露后的处理办法：</label>
                    [@ui_select id="postExposeDispose" name="postExposeDispose"  class="easyui-combobox" tag="@ae@post_expose_dispose"  uiShowDefault=false  style="width:50%;" dataOptions="editable:false,multiple:true,required:true,
                        onChange:((v)=>{
                            if($.inArray('5', v)>-1){
                                $('#postExposeDisposeRemark').show();
                            };
                            if($.inArray('5', v)==-1){
                                $('#postExposeDisposeRemark').hide();
                            }
                        })" filterkey="firstSpell"  /]
                    <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="postExposeDisposeRemark" name="postExposeDisposeRemark" placeholder="请填写其他" >${ae.postExposeDisposeRemark!}</textarea>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
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
        $('#gradeTwoA').combobox({
            onSelect: function(v){
                console.log(v);
                if($('#eventCode').val()=='303'){
                    if(v.valueCode=='41'){
                        // 锐器伤
                        $('#sharpObjType').combobox('enable');
                        $('#sharpObjName').combobox('enable');
                        $('#relaOperation').combobox('enable');
                        $('#occurForm').combobox('enable');
                        $('#soPolluteFlag').combobox('enable');
                        $('#soTraceCheck').combobox('enable');
                    }else{
                        $('#sharpObjType').combobox('disable');
                        $('#sharpObjName').combobox('disable');
                        $('#relaOperation').combobox('disable');
                        $('#occurForm').combobox('disable');
                        $('#soPolluteFlag').combobox('disable');
                        $('#soTraceCheck').combobox('disable');
                    }
                    if(v.valueCode=='42'){
                        // 粘膜接触及其他
                        $('#mucosaTouchPart').combobox('enable');
                        $('#mucosaTouchMaterial').combobox('enable');
                        $('#mucosaTouchOpertion').combobox('enable');
                        $('#protectMeasure').combobox('enable');
                    }else{
                        $('#mucosaTouchPart').combobox('disable');
                        $('#mucosaTouchMaterial').combobox('disable');
                        $('#mucosaTouchOpertion').combobox('disable');
                        $('#protectMeasure').combobox('disable');
                    }
                }
            }
        });

        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
        window.eventFlowchart = eventFlowchart;      
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
                $('#sharpObjNameRemark').hide();
                $('#postEffectObjRemark').hide();
                //$('#postExposeDisposeRemark').hide();
                $('#sharpObjType').combobox('enable');
                $('#sharpObjName').combobox('enable');
                $('#relaOperation').combobox('enable');
                $('#occurForm').combobox('enable');
                $('#soPolluteFlag').combobox('enable');
                $('#soTraceCheck').combobox('enable');
                $('#mucosaTouchPart').combobox('enable');
                $('#mucosaTouchMaterial').combobox('enable');
                $('#mucosaTouchOpertion').combobox('enable');
                $('#protectMeasure').combobox('enable');
                //$('#soTraceCheck').combobox('setText','');
                //$('#wHbvSign').combobox('setText','');
                //$('#wHbsabSign').combobox('setText','');


                if(eval('${ae.sharpObjName}')==null){
                    $('#sharpObjNameRemark').hide();
                }else{
                    $('#sharpObjName').combobox('setValue',eval('${ae.sharpObjName}'));
                    if(eval('${ae.sharpObjName}')=='11'){
                        $('#sharpObjNameRemark').show();
                    }else{
                        $('#sharpObjNameRemark').hide();
                    }
                }
                if(eval('${ae.relaOperation}')==null){
                    $('#relaOperationRemark').hide();
                }else{
                    $('#relaOperation').combobox('setValue',eval('${ae.relaOperation}'));
                    if(eval('${ae.relaOperation}')=='18'){
                        $('#relaOperationRemark').show();
                    }else{
                        $('#relaOperationRemark').hide();
                    }
                }

                if(eval('${ae.mucosaTouchPart}')==null){
                    $('#mucosaTouchPartRemark').hide();
                }else{
                    $('#mucosaTouchPart').combobox('setValue',eval('${ae.mucosaTouchPart}'));
                    if(eval('${ae.mucosaTouchPart}')=='4'){
                        $('#mucosaTouchPartRemark').show();
                    }else{
                        $('#mucosaTouchPartRemark').hide();
                    }
                }

                if(eval('${ae.mucosaTouchMaterial}')==null){
                    $('#mucosaTouchMaterialRemark').hide();
                }else{
                    $('#mucosaTouchMaterial').combobox('setValue',eval('${ae.mucosaTouchMaterial}'));
                    if(eval('${ae.mucosaTouchMaterial}')=='2'){
                        $('#mucosaTouchMaterialRemark').show();
                    }else{
                        $('#mucosaTouchMaterialRemark').hide();
                    }
                }

                if(eval('${ae.mucosaTouchOpertion}')==null){
                    $('#mucosaTouchOpertionRemark').hide();
                }else{
                    $('#mucosaTouchOpertion').combobox('setValue',eval('${ae.mucosaTouchOpertion}'));
                    if(eval('${ae.mucosaTouchOpertion}')=='4'){
                        $('#mucosaTouchOpertionRemark').show();
                    }else{
                        $('#mucosaTouchOpertionRemark').hide();
                    }
                }

                if(eval('${ae.protectMeasure}')==null){
                    $('#protectMeasureRemark').hide();
                }else{
                    $('#protectMeasure').combobox('setValue',eval('${ae.protectMeasure}'));
                    if(eval('${ae.protectMeasure}')=='4'){
                        $('#protectMeasureRemark').show();
                    }else{
                        $('#protectMeasureRemark').hide();
                    }
                }

                if(eval('${ae.postExposeDispose}')==null){
                    $('#postExposeDisposeRemark').hide();
                }else{
                    $('#postExposeDispose').combobox('setValue',eval('${ae.postExposeDispose}'));
                    if($.inArray('5', eval('${ae.postExposeDispose}'))>-1 || eval('${ae.postExposeDispose}')=='5'){
                        $('#postExposeDisposeRemark').show();
                    }else{
                        $('#postExposeDisposeRemark').hide();
                    }
                }

                if(eval('${ae.postEffectObj}')==null){
                    $('#postEffectObjRemark').hide();
                }else{
                    $('#postEffectObj').combobox('setValue',eval('${ae.postEffectObj}'));
                    if($.inArray('4', eval('${ae.postEffectObj}'))>-1 || eval('${ae.postEffectObj}')=='4'){
                        $('#postEffectObjRemark').show();
                    }else{
                        $('#postEffectObjRemark').hide();
                    }
                }

                if(eval('${ae.soTraceCheck}')==null){
                    $('#soTraceCheck').combobox('setValue',null);
                }

                /*
                if($.trim('${ae.soPolluteFlag}') == '1'){
                    $('.patient-info').validatebox('disableValidation');
                    $('.patient-info').attr('disabled',true);
                    $('#pHbsag').combobox('disableValidation');
                    $('#pHbsag').combobox('disable');
                    $('#pHbsab').combobox('disableValidation');
                    $('#pHbsab').combobox('disable');
                    $('#pHcv').combobox('disableValidation');
                    $('#pHcv').combobox('disable');
                    $('#pHiv').combobox('disableValidation');
                    $('#pHiv').combobox('disable');
                    $('#pSyphilisAb').combobox('disableValidation');
                    $('#pSyphilisAb').combobox('disable');
                    $('#pSyphilisReagin').combobox('disableValidation');
                    $('#pSyphilisReagin').combobox('disable');
                    $('#pInjectionDrug').combobox('disableValidation');
                    $('#pInjectionDrug').combobox('disable');
                    $('#pHaemophilia').combobox('disableValidation');
                    $('#pHaemophilia').combobox('disable');
                    $('#pHaemophilia').combobox('disableValidation');
                    $('#pRelaBehavior').combobox('disableValidation');
                    $('#pRelaBehavior').combobox('disable');
                }else{
                    $('.patient-info').attr('disabled',false);
                    $('.patient-info').validatebox('enableValidation');
                    $('#pHbsag').combobox({disabled:false});
                    $('#pHbsag').combobox('enableValidation');
                    $('#pHbsab').combobox({disabled:false});
                    $('#pHbsab').combobox('enableValidation');
                    $('#pHcv').combobox({disabled:false});
                    $('#pHcv').combobox('enableValidation');
                    $('#pHiv').combobox({disabled:false});
                    $('#pHiv').combobox('enableValidation');
                    $('#pSyphilisAb').combobox({disabled:false});
                    $('#pSyphilisAb').combobox('enableValidation');
                    $('#pSyphilisReagin').combobox({disabled:false});
                    $('#pSyphilisReagin').combobox('enableValidation');
                    $('#pInjectionDrug').combobox({disabled:false});
                    $('#pInjectionDrug').combobox('enableValidation');
                    $('#pHaemophilia').combobox({disabled:false});
                    $('#pHaemophilia').combobox('enableValidation');
                    $('#pRelaBehavior').combobox({disabled:false});
                    $('#pRelaBehavior').combobox('enableValidation');
                }
                */

                if(eval('${ae.pCollectTime}')==null){
                    $('#bloodSendDate').validatebox('disableValidation');
                    $('#pHbsag').combobox('disableValidation');
                    $('#pHbsab').combobox('disableValidation');
                    $('#pHcv').combobox('disableValidation');
                    $('#pHiv').combobox('disableValidation');
                    $('#pSyphilisAb').combobox('disableValidation');
                    $('#pSyphilisReagin').combobox('disableValidation');
                    $('#pInjectionDrug').combobox('disableValidation');
                    $('#pHaemophilia').combobox('disableValidation');
                    $('#pRelaBehavior').combobox('disableValidation');
                }else{
                    $('#bloodSendDate').validatebox('enableValidation');
                    $('#pHbsag').combobox('enableValidation');
                    $('#pHbsab').combobox('enableValidation');
                    $('#pHcv').combobox('enableValidation');
                    $('#pHiv').combobox('enableValidation');
                    $('#pSyphilisAb').combobox('enableValidation');
                    $('#pSyphilisReagin').combobox('enableValidation');
                    $('#pInjectionDrug').combobox('enableValidation');
                    $('#pHaemophilia').combobox('enableValidation');
                    $('#pRelaBehavior').combobox('enableValidation');
                }
            }
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
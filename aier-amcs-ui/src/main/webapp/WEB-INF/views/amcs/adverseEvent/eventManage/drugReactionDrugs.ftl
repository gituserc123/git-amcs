<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>药品不良反应事件报告 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">

</style>

<script type="text/javascript">
</script>
<body>
<form id="drugReactionDrugsForm" class="soform formA form-validate pad-t20 drugReactionDrugs"
      data-opt="{beforeCallback:'submitEditForm'}">
    <input class="txt hide" type="text" id="id" name="id" value="${reactId!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${hospId!}" />
    <input type="hidden" id="instId" name="instId" value="${instId!}" />
    <input type="hidden" id="url" name="url" value="${react.url!}" />
    <h2 class="h2-title-a">
        <span class="s-title">基本信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-s">
                <label class="lab-item">类型：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="typeLevel" id="v" data-options="editable:false, required:true, value:'${react.typeLevel}'" >
                    <option value="1"  [#if react.typeLevel==1]selected="selected"[/#if] >新的</option>
                    <option value="2"  [#if react.typeLevel==2]selected="selected"[/#if] >严重</option>
                    <option value="3"  [#if react.typeLevel==3]selected="selected"[/#if] >一般</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">是否构成纠纷：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="dispute" id="dispute" data-options="editable:false, required:true, value:'${react.dispute}',
                    onChange:function(v){
                        if(v=='1'){
                            $pop.msg('该药品不良反应已构成纠纷，请同时上报不良事件!');
                        }
                    }" >
                    <option value="1" [#if react.dispute==1]selected="selected"[/#if] >是</option>
                    <option value="2" [#if react.dispute==2]selected="selected"[/#if] >否</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-s">
                <label class="lab-item">医院名称：</label>
                <input class="txt txt-validate" type="text" id="hospName" name="hospName" value="${react.hospName!}" autocomplete="off" data-options=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-s">
                <label class="lab-item">科室：</label>
                <input class="txt txt-validate" type="text" id="department" name="department" value="${react.department!}" autocomplete="off"  dataOptions="required:ture" noNull="请填写科室"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">报告国家日期：</label>
                <input type="text" id="reportingCountries" class="txt so-date txt-validate " style="width:100%;" data-options="maxDate:new Date(),format:'yyyy-MM-dd'" name="reportingCountries" value="[#if react.reportingCountries??]${react.reportingCountries?string("yyyy-MM-dd")}[/#if]" />
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">上报集团日期：</label>
                <input type="text" id="reportGroup" class="txt so-date required txt-validate " style="width:100%;"  noNull="请选择上报集团日期" data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" name="reportGroup" value="[#if react.reportGroup??]${react.reportGroup?string("yyyy-MM-dd")}[#else]${sysdate?date("yyyy-MM-dd")}[/#if]" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one item-group solab-l">
                <label class="lab-item" id="patitentNoLabel">病案/住院/门诊号</label>
                <input class="txt txt-validate wp-70 required" type="text" name="medicalRecordNumber"  id="medicalRecordNumber" value="${react.medicalRecordNumber }" data-options="required:true" validType=many['fnValid["patientNoChk"]'] />
                <input type="button" class="btn btn-warning btn-small wp-25 fix-search-btn" name="btnSearch" value="查询" /><button type="button" class="btn btn-aemr hide">相关病历</button>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">患者姓名：</label>
                <input class="txt txt-validate" type="text" id="patientName" name="patientName" value="${react.patientName!}" autocomplete="off"  dataOptions="required:ture" noNull="请填写患者姓名"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">性别：</label>
                <input class="txt txt-validate" type="text" id="sex" name="sex" value="${react.sex!}" autocomplete="off"  dataOptions="required:ture" noNull="请填写性别"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">出生日期：</label>
                <input class="txt txt-validate" type="text" id="birthDate" name="birthDate" value="${react.birthDate!}" autocomplete="off"  dataOptions="required:ture" noNull="请填写出生日期"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">民族：</label>
                <input class="txt txt-validate" type="text" id="nationality" name="nationality" value="${react.nationality!}" autocomplete="off"  dataOptions="required:ture" noNull="请填写民族"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">体重（KG）：</label>
                <input class="txt txt-validate" type="text" id="bodyWeight" name="bodyWeight" value="${react.bodyWeight!}" validType="number['请填写正确的数字']" autocomplete="off"  dataOptions="required:ture" noNull="请填写体重"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">联系方式：</label>
                <input class="txt txt-validate" type="text" id="contactDetails" name="contactDetails" value="${react.contactDetails!}" autocomplete="off"  dataOptions="required:ture" noNull="请填写联系方式"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">不良反应/事件名称：</label>
                <!-- <input class="txt txt-validate" type="text" id="adverseReactionsName" name="adverseReactionsName" value="${react.adverseReactionsName!}" autocomplete="off" data-options="required:true" noNull="请填写不良反应/事件名称" /> -->
                [@ui_select id="adverseReactionsCode" name="adverseReactionsCode"  class="easyui-combobox" tag="@ae@adverse_reactions_name"  style="width:50%;" dataOptions="required:true,
                        onChange:((v)=>{
                            if($.trim(v) == '51'){
                                $('#adverseReactionsName').show();
                            }else{
                                $('#adverseReactionsName').hide();
                            }
                        })" filterkey="firstSpell" /]
                <textarea class="txt txt-validate adaptiveTextarea" style="width:45%;" type="text" id="adverseReactionsName" name="adverseReactionsName" placeholder="请填写其他" >${react.adverseReactionsName!}</textarea>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">不良反应/事件发生时间：</label>
                <input type="text" id="occurDate" class="txt so-date required txt-validate " style="width:100%;"  noNull="请选择发生时间" data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" name="occurDate" value="[#if react.occurDate??]${react.occurDate?string("yyyy-MM-dd")}[/#if]" />
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">家族药品不良反应/事件：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="familyMedicine" id="familyMedicine" data-options="editable:false, required:true, value:'${react.familyMedicine}'" >
                    <option value="1"  [#if react.familyMedicine==1]selected="selected"[/#if] >有</option>
                    <option value="2"  [#if react.familyMedicine==2]selected="selected"[/#if] >无</option>
                    <option value="3"  [#if react.familyMedicine==3]selected="selected"[/#if] >不详</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">既往药品不良反应/事件情况：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="pastDrugs" id="pastDrugs" data-options="editable:false, required:true, value:'${react.pastDrugs}'" >
                    <option value="1"  [#if react.pastDrugs==1]selected="selected"[/#if] >有</option>
                    <option value="2"  [#if react.pastDrugs==2]selected="selected"[/#if] >无</option>
                    <option value="3"  [#if react.pastDrugs==3]selected="selected"[/#if] >不详</option>
                </select>
            </div>
        </div>
    </div>
    <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
                <label class="lab-inline w-80">不良反应/事件过程描述：</label>
				<textarea class="txta txt-validate adaptiveTextarea" style="width:1000px" dataOptions="required:ture" noNull="请填写事件经过描述" id="adverseReactionsDescription"
                          name="adverseReactionsDescription" cols="200" row="10" placeholder="因何使用何种药物，用法用量及给药途径，出现何种不良反应（包括症状、体征、临床检验等），处理情况及患者愈后情况及结果。">${react.adverseReactionsDescription!}</textarea>
            </span>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">怀疑药品</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="cont-grid-sm-drugs">
        <div id="gridBox-1"></div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">共用药品</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="cont-grid-am-drugs">
        <div id="gridBox-2"></div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">其他信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">不良反应/事件的结果：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="adverseResults" id="adverseResults" data-options="editable:false,required:true, value:'${react.adverseResults}',
                    onChange:function(v){
						if(v=='4'){
                            $('.performance-cls').show();
					        $('#performance').validatebox('enableValidation');
						    $('.death-cls').hide();
					        $('#causeOfDeath').validatebox('disableValidation');
					        $('#deathDate').validatebox('disableValidation');
						}else if(v=='5'){
                            $('.death-cls').show();
					        $('#causeOfDeath').validatebox('enableValidation');
					        $('#deathDate').validatebox('enableValidation');
						    $('.performance-cls').hide();
					        $('#performance').validatebox('disableValidation');
						}else{
						    $('.performance-cls').hide();
					        $('#performance').validatebox('disableValidation');
						    $('.death-cls').hide();
					        $('#causeOfDeath').validatebox('disableValidation');
					        $('#deathDate').validatebox('disableValidation');
						}
					}" >
                    <option value="1" [#if react.adverseResults==1]selected="selected"[/#if] >治愈</option>
                    <option value="2" [#if react.adverseResults==2]selected="selected"[/#if] >好转</option>
                    <option value="3" [#if react.adverseResults==3]selected="selected"[/#if] >未好转</option>
                    <option value="4" [#if react.adverseResults==4]selected="selected"[/#if] >有后遗症</option>
                    <option value="5" [#if react.adverseResults==5]selected="selected"[/#if] >死亡</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l performance-cls hide">
                <label class="lab-item">表现：</label>
                <input class="txt txt-validate " type="text" id="performance" name="performance" value="${react.performance!}" autocomplete="off" dataOptions="required:true" noNull="表现必填"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">反应/事件是否消失或减轻：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="easeReact" id="easeReact" data-options="editable:false, required:true, value:'${react.easeReact}'" >
                    <option value="1"  [#if react.easeReact==1]selected="selected"[/#if] >是</option>
                    <option value="2"  [#if react.easeReact==2]selected="selected"[/#if] >否</option>
                    <option value="3"  [#if react.easeReact==3]selected="selected"[/#if] >不明</option>
                    <option value="4"  [#if react.easeReact==4]selected="selected"[/#if] >未停药或未减量</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label class="lab-item">是否再次出现同样反应/事件：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="sameReactAgain" id="sameReactAgain" data-options="editable:false, required:true, value:'${react.sameReactAgain}'" >
                    <option value="1"  [#if react.sameReactAgain==1]selected="selected"[/#if] >是</option>
                    <option value="2"  [#if react.sameReactAgain==2]selected="selected"[/#if] >否</option>
                    <option value="3"  [#if react.sameReactAgain==3]selected="selected"[/#if] >不明</option>
                    <option value="4"  [#if react.sameReactAgain==4]selected="selected"[/#if] >未再使用</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one death-cls hide">
                <label class="lab-item">直接死因：</label>
                <textarea class="txt txt-validate  adaptiveTextarea"  dataOptions="required:true" noNull="请填写直接死因" id="causeOfDeath" name="causeOfDeath" maxlength="200" cols="100" row="50">${react.causeOfDeath!}</textarea>
            </div>
        </div>
        <div class="p4">
            <div class="item-one death-cls hide">
                <label class="lab-item">死亡时间：</label>
                <input type="text" id="deathDate" class="txt so-date required txt-validate" style="width:100%;"  noNull="请选择死亡时间" data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" name="deathDate" value="[#if react.deathDate??]${react.deathDate?string('yyyy-MM-dd')}[/#if]" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one">
                <label class="lab-item">原患疾病：</label>
                <textarea class="txt txt-validate  adaptiveTextarea"  dataOptions="required:true" noNull="请填写原患疾病" id="originalDisease" name="originalDisease" maxlength="500" cols="100" row="50">${react.originalDisease!}</textarea>
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-l">
                <label class="lab-item ">对原患疾病影响：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="impactOnDisease" id="impactOnDisease" data-options="editable:false, required:true, value:'${react.impactOnDisease}'" >
                    <option value="1" [#if react.impactOnDisease==1]selected="selected"[/#if] >不明显</option>
                    <option value="2" [#if react.impactOnDisease==2]selected="selected"[/#if] >病程延长</option>
                    <option value="3" [#if react.impactOnDisease==3]selected="selected"[/#if] >病情加重</option>
                    <option value="4" [#if react.impactOnDisease==4]selected="selected"[/#if] >导致后遗症</option>
                    <option value="5" [#if react.impactOnDisease==5]selected="selected"[/#if] >导致死亡</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p4">
            <div class="item-one solab-lb">
                <label class="lab-item">国内有无类似不良反应：</label>
                <select class="drop easyui-combobox" style="width:50%;" name="similarInChina" id="similarInChina" data-options="editable:false, required:true, value:'${react.similarInChina}'" >
                    <option value="1" [#if react.similarInChina==1]selected="selected"[/#if] >有</option>
                    <option value="2" [#if react.similarInChina==2]selected="selected"[/#if] >无</option>
                    <option value="3" [#if react.similarInChina==3]selected="selected"[/#if] >不详</option>
                </select>
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-lb">
                <label class="lab-item">国外有无类似不良反应：</label>
                <select class="drop easyui-combobox" style="width:50%;" name="similarAbroad" id="similarAbroad" data-options="editable:false, required:true, value:'${react.similarAbroad}'" >
                    <option value="1" [#if react.similarAbroad==1]selected="selected"[/#if] >有</option>
                    <option value="2" [#if react.similarAbroad==2]selected="selected"[/#if] >无</option>
                    <option value="3" [#if react.similarAbroad==3]selected="selected"[/#if] >不详</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p4">
            <div class="item-one solab-lb">
                <label class="lab-item">关联性评价-报告人评价：</label>
                <select class="drop easyui-combobox" style="width:50%;" name="reportEvaluation" id="reportEvaluation" data-options="editable:false, required:true, value:'${react.reportEvaluation}'" >
                    <option value="1" [#if react.reportEvaluation==1]selected="selected"[/#if] >肯定</option>
                    <option value="2" [#if react.reportEvaluation==2]selected="selected"[/#if] >很可能</option>
                    <option value="3" [#if react.reportEvaluation==3]selected="selected"[/#if] >可能</option>
                    <option value="4" [#if react.reportEvaluation==4]selected="selected"[/#if] >可能无关</option>
                    <option value="5" [#if react.reportEvaluation==5]selected="selected"[/#if] >待评价</option>
                    <option value="6" [#if react.reportEvaluation==6]selected="selected"[/#if] >无法评价</option>
                </select>
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-lb">
                <label class="lab-item">关联性评价-报告人签名：</label>
                <input class="txt txt-validate " type="text" id="autograph1" name="autograph1" value="${react.autograph1!}" autocomplete="off" dataOptions="required:true" noNull="请填写报告人签名" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p4">
            <div class="item-one solab-lb">
                <label class="lab-item ">关联性评价-医院评价：</label>
                <select class="drop easyui-combobox" style="width:50%;" name="hospitalEvaluation" id="hospitalEvaluation" data-options="editable:false, required:true, value:'${react.hospitalEvaluation}'" >
                    <option value="1" [#if react.hospitalEvaluation==1]selected="selected"[/#if] >肯定</option>
                    <option value="2" [#if react.hospitalEvaluation==2]selected="selected"[/#if] >很可能</option>
                    <option value="3" [#if react.hospitalEvaluation==3]selected="selected"[/#if] >可能</option>
                    <option value="4" [#if react.hospitalEvaluation==4]selected="selected"[/#if] >可能无关</option>
                    <option value="5" [#if react.hospitalEvaluation==5]selected="selected"[/#if] >待评价</option>
                    <option value="6" [#if react.hospitalEvaluation==6]selected="selected"[/#if] >无法评价</option>
                </select>
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-lc">
                <label class="lab-item">医院药品不良反应负责人签名：</label>
                <input class="txt txt-validate " type="text" id="autograph2" name="autograph2" value="${react.autograph2!}" autocomplete="off" dataOptions="required:true" noNull="请填写医院药品不良反应负责人签名" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">报告人职业：</label>
                <select class="drop easyui-combobox" style="width:50%;" name="occupationReporter" id="occupationReporter" data-options="editable:false, required:true, value:'${react.occupationReporter}'" >
                    <option value="1" [#if react.occupationReporter==1]selected="selected"[/#if] >医生</option>
                    <option value="2" [#if react.occupationReporter==2]selected="selected"[/#if] >药师</option>
                    <option value="3" [#if react.occupationReporter==3]selected="selected"[/#if] >护士</option>
                    <option value="4" [#if react.occupationReporter==4]selected="selected"[/#if] >其他</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">报告人职务：</label>
                <input class="txt txt-validate " type="text" id="titleReporter" name="titleReporter" value="${react.titleReporter!}" autocomplete="off" dataOptions="required:true" noNull="报告人职务" />
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">报告人签名：</label>
                <input class="txt txt-validate " type="text" id="signatureReporter" name="signatureReporter" value="${react.signatureReporter!}" autocomplete="off" dataOptions="required:true" noNull="请填报告人签名" />
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">报告人电话：</label>
                <input class="txt txt-validate" type="text" id="telephone" name="telephone" value="${react.telephone!}" validType="mobile['请填写正确的电话号码']" autocomplete="off"  dataOptions="required:ture" noNull="请填写电话"/>
            </div>
        </div>
    </div>
    <p class="row-btn pad-t5">
        [#if empType==3]
        <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="提 交"/>
        [/#if]
        <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
    </p>
</form>

<script id="editReactionHisDrugsDtl" type="text/html">
    <form id="drugReactionHisDrugsDtlForm" class="soform formA form-validate pad-t20 drugReactionHisDrugsDtl"
          data-opt="{beforeCallback:'submitReactionHisDrugsDtlForm'}">
        <input class="txt hide" type="text" id="id" name="id" value=""/>
        <input class="txt hide" type="text" id="drugType" name="drugType" value=""/>
        <input class="txt hide" type="text" id="typeDrug" name="typeDrug" value=""/>
        <input class="txt hide" type="text" id="gridIndex" name="gridIndex" value=""/>
        <input class="txt hide" type="text" id="hisDrugId" name="hisDrugId" value=""/>
        <input class="txt hide" type="text" id="commonNameDesc" name="commonNameDesc" value=""/>
        <input class="txt hide" type="text" id="usageUnitDesc" name="usageUnitDesc" value=""/>
        <input class="txt hide" type="text" id="hospId" name="hospId" value="${hospId!}" />
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">通用名称：</label>
                    <select class="form-control easyui-combogrid required" style="width:100%;" id="commonName" type="text" name="commonName" data-options="required:true" />
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">商品名称：</label>
                    <input id="tradeName" class="txt txt-validate" type="text" name="tradeName" value=""
                           autocomplete="off" validType="" data-options="{delay:500}"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">生产厂家：</label>
                    <input id="manufacturer" class="txt txt-validate required" type="text" name="manufacturer" value=""
                           noNull="生产厂家" autocomplete="off" validType="" data-options="{delay:500}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">国药准字：</label>
                    <input id="medicalApprovalNum" class="txt txt-validate required" type="text" name="medicalApprovalNum" value=""
                           noNull="国药准字" autocomplete="off" validType="" data-options=""/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">批号：</label>
                    <input id="batchNumber" class="txt txt-validate required" type="text" name="batchNumber" value=""
                           noNull="批号" autocomplete="off" validType="" data-options="{delay:500}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">频次：</label>
                    <!-- <input id="defaultFreq" class="txt txt-validate" type="text" name="defaultFreq" value="" autocomplete="off" validType="" data-options="{delay:500}"/> -->
                    <select class="form-control easyui-combogrid" style="width:100%;" id="defaultFreq" type="text" name="defaultFreq" dataOptions="required:true" />
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">用量：</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="usageDrug" id="usageDrug" style="width:30%" data-options="precision:2,required:true" value=""/>
                    <select class="drop drop-sl-v easyui-combobox w-150" name="usageUnit" id="usageUnit"  data-options="required:true, valueField:'valueCode',textField:'valueDesc',clearIcon:true"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-l">
                    <label class="lab-item">用药开始时间：</label>
                    <!-- <input id="medicationTime" class="txt txt-validate required" type="text" name="medicationTime" value="" noNull="用药起止时间" autocomplete="off" validType="" data-options="{delay:500}"/> -->
                    <input type="text" id="medicationBeginTime" class="txt so-date required txt-validate " style="width:100%;"  noNull="请选择用药开始时间" data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" type="text" name="medicationBeginTime" value="" />
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-l">
                    <label class="lab-item">用药结束时间：</label>
                    <input type="text" id="medicationEndTime" class="txt so-date required txt-validate " style="width:100%;"  noNull="用药结束时间" data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" type="text" name="medicationEndTime" value="" />
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item">用药原因：</label>
                    <input id="medicationReason" class="txt txt-validate required" type="text" name="medicationReason" value=""
                           noNull="用药原因" autocomplete="off" validType="" data-options="{delay:500}"/>
                </div>
            </div>
        </div>
        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存"/>
            <input type="button" class="btn btn-cancel-cus" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['lodash','pub'], function (_) {

        if(eval('${react.adverseResults!}')==null){
            $('.performance-cls').hide();
            $('#performance').validatebox('disableValidation');
            $('.death-cls').hide();
            $('#causeOfDeath').validatebox('disableValidation');
            $('#deathDate').validatebox('disableValidation');
        }else{
            if(eval('${react.adverseResults!}')=='4'){
                $('.performance-cls').show();
                $('#performance').validatebox('enableValidation');
                $('.death-cls').hide();
                $('#causeOfDeath').validatebox('disableValidation');
                $('#deathDate').validatebox('disableValidation');
            }else if(eval('${react.adverseResults!}')=='5'){
                $('.death-cls').show();
                $('#causeOfDeath').validatebox('enableValidation');
                $('#deathDate').validatebox('enableValidation');
                $('.performance-cls').hide();
                $('#performance').validatebox('disableValidation');
            }else{
                $('.performance-cls').hide();
                $('#performance').validatebox('disableValidation');
                $('.death-cls').hide();
                $('#causeOfDeath').validatebox('disableValidation');
                $('#deathDate').validatebox('disableValidation');
            }
        };

        if(eval('${react.adverseReactionsCode}')==null){
            $('#adverseReactionsName').hide();
        }else{
            $('#adverseReactionsCode').combobox('setValue',eval('${react.adverseReactionsCode}'));
            if(eval('${react.adverseReactionsCode}')=='51'){
                $('#adverseReactionsName').show();
            }else{
                $('#adverseReactionsName').hide();
            }
        }

        var emrUrl ='${react.url!}';
        if(emrUrl){
            $('.btn-aemr').removeClass('hide');
            $('.btn-aemr').click(function () {
                window.open(emrUrl,'_blank');
            });
        }else{
            $('.btn-aemr').addClass('hide');
        }

        if($("#hospName").val()){
            $("#hospName").attr("readonly", "readonly");
        };

        function doSearch(q,data,searchList,ele){
            ele.combogrid('grid').datagrid('loadData', []);
            if(q == ""){
                ele.combogrid('grid').datagrid('loadData', data);
                return;
            }
            var rows = [];
            $.each(data,function(i,obj){
                for(var p in searchList){
                    var v = obj[searchList[p]];
                    if (!!v && v.toString().indexOf(q) >= 0){
                        rows.push(obj);
                        break;
                    }
                }
            });
            if(rows.length == 0){
                ele.combogrid('grid').datagrid('loadData', []);
                return;
            }
            ele.combogrid('grid').datagrid('loadData', rows);
        };

        window.patientNoChk = function (patientNo) {
            var res = {};
            var reg = new RegExp("'");
            if (reg.test(patientNo)) {
                res.result = false;
                res.msg = "住院/门诊号不能包含字符'";
            } else {
                res.result = true;
            }
            return res;
        };

        //查询按钮点击
        $(".fix-search-btn").click(function () {
            let patientNo = $("#medicalRecordNumber").val();
            if (patientNo == null || patientNo == "") {
                $pop.alert('请输入查询信息');
                return;
            }
            //门诊接口
            if (patientNo.startsWith("MZ")) {
                queryOpDomain(patientNo);
            //住院接口
            } else if (patientNo.startsWith("ZY")) {
                queryInDomain(patientNo);
            }else{
                $ajax.post('${base}/ui/amcs/adverse/event/api/getMedicalNumber', { "medialNumber": patientNo }, { jsonData: false, tip: false, sync: true, loadingBar: true, loadingMask: true }).done(function (rstData) {
                    if (!$.isEmptyObject(rstData)) {
                        if(rstData.length == 1) {
                            queryInDomain(rstData[0].inpNumber);
                        }else{
                            $('#patientNo').combogrid({
                                panelWidth:480,
                                panelHeight:'auto',
                                panelMaxHeight:182,
                                delay: 200,
                                width:170,
                                fitColumns:true,
                                idField:'medicalNumber',
                                textField:'medicalNumber',
                                multiple:false,//下拉多选
                                data: rstData,
                                columns:[[
                                    {field:'patientId',title:'patientId',hidden:true},
                                    {field:'inpNumber',title:'住院编号',hidden:true},
                                    {field:'medicalNumber',title:'病案号',width:80,titletip:true},
                                    {field:'inpTimes',title:'次数'},
                                    {field:'patientName',title:'姓名',width:40,titletip:true},
                                    {field:'plvhTime',title:'出院时间',width:40,titletip:true}
                                ]],
                                onSelect : function (val,record){
                                    queryInDomain(record.inpNumber);
                                    setTimeout(function() {
                                        $('#medicalRecordNumber').combo('destroy');
                                        $('#patitentNoLabel').after("<input class=\"txt txt-validate wp-70 required\" type=\"text\" name=\"patientNo\" id=\"patientNo\"  data-options=\"required:true\" validType=many['fnValid[\"patientNoChk\"]'] />");
                                        $('#medicalRecordNumber').val(patientNo);
                                    }, 1000);
                                },
                                required:true,
                            });
                            $('#medicalRecordNumber').combo('showPanel');
                        }
                    }

                });
            };
        });

        function queryOpDomain(opNumber){
            $ajax.post('${base}/ui/amcs/adverse/event/api/getOpDomain', { "opNumber": opNumber }, { jsonData: false, tip: false, sync: false, loadingBar: true, loadingMask: true }).done(function (rst) {
                if(rst.emrUrl){
                    $("#url").val(rst.emrUrl);
                    $('.btn-aemr').removeClass('hide');
                    $('.btn-aemr').click(function () {
                        window.open(rst.emrUrl,'_blank');
                    });
                }else{
                    $('.btn-aemr').addClass('hide');
                }
                loadPatientInfo(rst.patientId);
            });
        };

        function queryInDomain(inNumber){
            $ajax.post('${base}/ui/amcs/adverse/event/api/getInDomain', { "inNumber": inNumber }, { jsonData: false, tip: false, sync: false, loadingBar: true, loadingMask: true }).done(function (rst) {
                if(rst.emrUrl){
                    $("#url").val(rst.emrUrl);
                    $('.btn-aemr').removeClass('hide');
                    $('.btn-aemr').click(function () {
                        window.open(rst.emrUrl,'_blank');
                    });
                }else{
                    $('.btn-aemr').addClass('hide');
                }
                loadPatientInfo(rst.patientId);
            });
        };

        function loadPatientInfo(patientId){
            if(!patientId){
                $pop.alert('查询不到患者信息,请检查门诊号/住院号是否正确!!!');
            }
            $ajax.post('${base}/ui/service/biz/amcs/adverse/common/getPatientInfo', { "patientId": patientId }, { jsonData: false, tip: false, sync: true, loadingBar: true, loadingMask: true }).done(function (rst) {
                if ($.isEmptyObject(rst)) {
                    $("#patientName").val('');
                    $("#sex").val('');
                    $("#birthDate").val('');
                    //$("#patientId").val('');
                    return;
                } else {
                    $("#birthDate").val(rst.birthday.substr(0, 10));
                    $("#sex").val(rst.sexName);
                    $("#patientName").val(rst.name);
                    $("#nationality").val(rst.nationName);
                    $("#contactDetails").val(rst.tel1);
                }
            });
        };

        $grid.newGrid("#gridBox-1", {
            pagination: false,
            fitColumns: false,
            tools: [
                [
                    [#if empType==3]
                    {
                        iconCls: 'plus',//图标
                        text: '新增HIS药品',//文字
                        click: function (){
                            var reactDrugFreq;
                            var hisDrugData;
                            window.formHisDrugsPop = $pop.popTemForm({
                                title: "新增HIS药物",
                                temId: 'editReactionHisDrugsDtl',
                                area: ['1000px', '280px'],
                                temData: {},
                                zIndex: 2,
                                grid: '#gridBox-1',
                                onPop: function ($p) {
                                    $('form.drugReactionHisDrugsDtl').form('reset');
                                    $(".btn-cancel-cus").click(function () {
                                        $pop.close(formHisDrugsPop);
                                    });
                                    $("#drugType").val(1);
                                    $ajax.post({
                                        url: '${base}/ui/amcs/adverse/drugreaction/getAllDrugs',
                                        sync:true
                                    }).success (function (rst) {
                                            hisDrugData = rst;
                                        }
                                    );
                                    $ajax.post({
                                        url: '${base}/ui/amcs/adverse/aeDict/getList?paraType=react_drug_freq&filter=',
                                        sync:true
                                    }).success (function (rst) {
                                            reactDrugFreq = rst;
                                        }
                                    );
                                    $ajax.postSync('${base}/ui/amcs/adverse/aeDict/getList?paraType=react_usage_unit&filter=',null,false,false).done(function (rst) {
                                        $('#usageUnit').combobox('loadData', rst);
                                    });
                                    $('#commonName').combogrid({
                                        panelWidth:500,
                                        delay: 500,//用户输入请求间隙时间
                                        //mode: 'remote',//根据输入向远端请求
                                        //url: JSON.stringify(gridData),//请求url
                                        idField: 'id',//输入框返回value
                                        textField: 'drugsDesc',//输入框返回字符
                                        striped: true,
                                        required: true,
                                        editable: true,
                                        columns: [[//grid设置
                                            {field:'drugsDesc',title:'通用名称',width:100},
                                            {field:'tradeName',title:'商品名称',width:80},
                                            {field:'manufacName',title:'生产厂家',width:80},
                                            {field:'approvalNumber',title:'国药准字',width:80},
                                        ]],
                                        onSelect:function (idx,item) {
                                            $('#tradeName').val(item.tradeName);
                                            $('#commonNameDesc').val(item.drugsDesc);
                                            $('#manufacturer').val(item.manufacName);
                                            $('#medicalApprovalNum').val(item.approvalNumber);
                                            $('#hisDrugId').val(item.id);
                                        },
                                        onChange:function (idx,item) {
                                        },
                                        keyHandler: {
                                            up: function() {},
                                            down: function() {},
                                            enter: function() {},
                                            query: function(q) {
                                                doSearch(q,hisDrugData,['drugsDesc','firstSpell'],$(this));
                                                $('#commonName').combogrid('setValue', q);
                                            }
                                        }
                                    });
                                    $('#defaultFreq').combogrid({
                                        panelWidth:500,
                                        delay: 500,//用户输入请求间隙时间
                                        //mode: 'remote',//根据输入向远端请求
                                        //url: JSON.stringify(gridData),//请求url
                                        idField: 'valueCode',//输入框返回value
                                        textField: 'valueDesc',//输入框返回字符
                                        striped: true,
                                        required: true,
                                        editable: true,
                                        columns: [[//grid设置
                                            {field:'valueCode',title:'字典值',width:60},
                                            {field:'valueDesc',title:'字典描述',width:100},
                                            {field:'firstSpell',title:'首拼',width:80},
                                        ]],
                                        onSelect:function (idx,item) {
                                        },
                                        onChange:function (idx,item) {
                                        },
                                        keyHandler: {
                                            up: function() {},
                                            down: function() {},
                                            enter: function() {},
                                            query: function(q) {
                                                doSearch(q,reactDrugFreq,['valueDesc','firstSpell'],$(this));
                                                $('#defaultFreq').combogrid('setValue', q);
                                            }
                                        }
                                    });
                                    $("#commonName").combogrid("grid").datagrid("loadData", _.take(hisDrugData,100));
                                    $("#defaultFreq").combogrid("grid").datagrid("loadData", reactDrugFreq);
                                },
                                end: function () {
                                },
                                beforeSubmit: function (opt, $form, formData) {
                                    return true;
                                }
                            });
                        }
                    }
                    [/#if]
                ]
            ],
            columns: [
                [
                    [#if empType==3]
                    {
                        title: '操作',
                        field: 'op',
                        width: 50,
                        formatter: function(v, row, index) {
                            var chkHtml = '';
                            chkHtml += '<span rel=' + index  + ' class="s-op s-op-edit  icon-edit" title="编辑" ></span>';
                            chkHtml += '&nbsp;&nbsp;<span class="s-op s-op-del icon-del" title="删除此项目" rel="'+index+'"></span>';
                            return chkHtml;
                        }
                    },
                    [/#if]
                    { title: "药物id", field: "id", hidden: true,width: 80 },
                    { title: "HIS药物id", field: "hisDrugId", hidden: true,width: 80 },
                    { title: "类型", field: "typeDrug", hidden: true, width: 80 },
                    { title: "频次", field: "defaultFreq",hidden: true,  width: 60 },
                    { title: "用量", field: "usageDrug", hidden: true, width: 50 },
                    { title: "通用名称", field: "commonName", width: 180 },
                    { title: "商品名称", field: "tradeName", width: 80 },
                    { title: "生产厂家", field: "manufacturer", width: 180 },
                    { title: "国药准字", field: "medicalApprovalNum", width: 150 },
                    { title: "批号", field: "batchNumber", width: 150 },
                    { title: "频次", field: "defaultFreqDesc", width: 120 },
                    { title: "用量", field: "usageUnitDesc", width: 100,formatter: function (v, row, index) {return row.usageDrug+v}  },
                    { title: "用药开始时间", field: "medicationBeginTime", width: 100,formatter: function (v, row, index) {return v.substring(0,10)} },
                    { title: "用药结束时间", field: "medicationEndTime", width: 100,formatter: function (v, row, index) {return v.substring(0,10)} },
                    { title: "用药原因", field: "medicationReason", width: 180 }
                ]
            ],
            onClickRow:function (index, row) {

            },
            queryParams: {
                typeDrug: 1,
                reactId:'${reactId}',
            },
            url: '${base}/ui/amcs/adverse/drugreaction/findDrugsList',
            height: 200,
            width: '100%',
            offset: -5
        });

        $grid.newGrid("#gridBox-2", {
            pagination: false,
            fitColumns: false,
            tools: [
                [
                    [#if empType==3]
                    {
                        iconCls: 'plus',//图标
                        text: '新增HIS药品',//文字
                        click: function (){
                            var reactDrugFreq;
                            var hisDrugData;
                            window.formHisDrugsPop = $pop.popTemForm({
                                title: "新增HIS药物",
                                temId: 'editReactionHisDrugsDtl',
                                area: ['1000px', '280px'],
                                temData: {},
                                zIndex: 2,
                                grid: '#gridBox-1',
                                onPop: function ($p) {
                                    $('form.drugReactionHisDrugsDtl').form('reset');
                                    $(".btn-cancel-cus").click(function () {
                                        $pop.close(formHisDrugsPop);
                                    });
                                    $("#drugType").val(2);
                                    $ajax.post({
                                        url: '${base}/ui/amcs/adverse/drugreaction/getAllDrugs',
                                        sync:true
                                    }).success (function (rst) {
                                            hisDrugData = rst;
                                        }
                                    );
                                    $ajax.post({
                                        url: '${base}/ui/amcs/adverse/aeDict/getList?paraType=react_drug_freq&filter=',
                                        sync:true
                                    }).success (function (rst) {
                                            reactDrugFreq = rst;
                                        }
                                    );
                                    $ajax.postSync('${base}/ui/amcs/adverse/aeDict/getList?paraType=react_usage_unit&filter=',null,false,false).done(function (rst) {
                                        $('#usageUnit').combobox('loadData', rst);
                                    });
                                    $('#commonName').combogrid({
                                        panelWidth:500,
                                        delay: 500,//用户输入请求间隙时间
                                        //mode: 'remote',//根据输入向远端请求
                                        //url: JSON.stringify(gridData),//请求url
                                        idField: 'id',//输入框返回value
                                        textField: 'drugsDesc',//输入框返回字符
                                        striped: true,
                                        required: true,
                                        editable: true,
                                        columns: [[//grid设置
                                            {field:'drugsDesc',title:'通用名称',width:100},
                                            {field:'tradeName',title:'商品名称',width:80},
                                            {field:'drugsDesc',title:'通用名称',width:100},
                                            {field:'tradeName',title:'商品名称',width:80},
                                            {field:'manufacName',title:'生产厂家',width:80},
                                            {field:'approvalNumber',title:'国药准字',width:80},
                                        ]],
                                        onSelect:function (idx,item) {
                                            $('#tradeName').val(item.tradeName);
                                            $('#commonNameDesc').val(item.drugsDesc);
                                            $('#manufacturer').val(item.manufacName);
                                            $('#medicalApprovalNum').val(item.approvalNumber);
                                            $('#hisDrugId').val(item.id);
                                        },
                                        onChange:function (idx,item) {

                                        },
                                        keyHandler: {
                                            up: function() {},
                                            down: function() {},
                                            enter: function() {},
                                            query: function(q) {
                                                doSearch(q,hisDrugData,['drugsDesc','firstSpell'],$(this));
                                                $('#commonName').combogrid('setValue', q);
                                            }
                                        }
                                    });
                                    $('#defaultFreq').combogrid({
                                        panelWidth:500,
                                        delay: 500,//用户输入请求间隙时间
                                        //mode: 'remote',//根据输入向远端请求
                                        //url: JSON.stringify(gridData),//请求url
                                        idField: 'valueCode',//输入框返回value
                                        textField: 'valueDesc',//输入框返回字符
                                        striped: true,
                                        required: true,
                                        editable: true,
                                        columns: [[//grid设置
                                            {field:'valueCode',title:'字典值',width:60},
                                            {field:'valueDesc',title:'字典描述',width:100},
                                            {field:'firstSpell',title:'首拼',width:80},
                                        ]],
                                        onSelect:function (idx,item) {
                                        },
                                        onChange:function (idx,item) {
                                        },
                                        keyHandler: {
                                            up: function() {},
                                            down: function() {},
                                            enter: function() {},
                                            query: function(q) {
                                                doSearch(q,reactDrugFreq,['valueDesc','firstSpell'],$(this));
                                                $('#defaultFreq').combogrid('setValue', q);
                                            }
                                        }
                                    });
                                    $("#commonName").combogrid("grid").datagrid("loadData", _.take(hisDrugData,100));
                                    $("#defaultFreq").combogrid("grid").datagrid("loadData", reactDrugFreq);
                                },
                                end: function () {
                                },
                                beforeSubmit: function (opt, $form, formData) {
                                    return true;
                                }
                            });
                        }
                    }
                    [/#if]
                ]
            ],
            columns: [
                [
                    [#if empType==3]
                    {
                        title: '操作',
                        field: 'op',
                        width: 50,
                        formatter: function(v, row, index) {
                            var chkHtml = '';
                            chkHtml += '<span rel=' + index  + ' class="s-op s-op-edit  icon-edit" title="编辑" ></span>';
                            chkHtml += '&nbsp;&nbsp;<span class="s-op s-op-del icon-del" title="删除此项目" rel="'+index+'"></span>';
                            return chkHtml;
                        }
                    },
                    [/#if]
                    { title: "药物id", field: "id", hidden: true,width: 80 },
                    { title: "HIS药物id", field: "hisDrugId", hidden: true,width: 80 },
                    { title: "类型", field: "typeDrug", hidden: true,width: 80 },
                    { title: "频次", field: "defaultFreq", hidden: true, width: 100 },
                    { title: "用量", field: "usageDrug", hidden: true, width: 50 },
                    { title: "通用名称", field: "commonName", width: 180 },
                    { title: "商品名称", field: "tradeName", width: 80 },
                    { title: "生产厂家", field: "manufacturer", width: 180 },
                    { title: "国药准字", field: "medicalApprovalNum", width: 150 },
                    { title: "批号", field: "batchNumber", width: 150 },
                    { title: "频次", field: "defaultFreqDesc", width: 120 },
                    { title: "用量", field: "usageUnitDesc", width: 100,formatter: function (v, row, index) {return row.usageDrug+v}  },
                    { title: "用药开始时间", field: "medicationBeginTime", width: 100,formatter: function (v, row, index) {return v.substring(0,10)} },
                    { title: "用药结束时间", field: "medicationEndTime", width: 100,formatter: function (v, row, index) {return v.substring(0,10)} },
                    { title: "用药原因", field: "medicationReason", width: 180 }
                ]
            ],
            onClickRow:function (index, row) {

            },
            queryParams: {
                typeDrug: 2,
                reactId:'${reactId!}',
            },
            url: '${base}/ui/amcs/adverse/drugreaction/findDrugsList',
            height: 200,
            width: '100%',
            offset: -5
        });

        // $(".cont-grid-sm-drugs .datagrid-toolbar .item-group").first().click(function ($e) {});
        // $(".cont-grid-am-drugs .datagrid-toolbar .item-group").first().click(function ($e) {});

        $('.cont-grid-sm-drugs').on('click', '.s-op-edit', function () {
            var index = $(this).attr("rel");
            var rowData = $('#gridBox-1').datagrid("getRows")[index];
            window.formHisDrugsPop = $pop.popTemForm({
                title: "修改药物",
                temId: 'editReactionHisDrugsDtl',
                area: ['1000px', '280px'],
                temData: {},
                zIndex: 2,
                grid: '#gridBox-1',
                onPop: function ($p) {
                    $("#drugType").val(1);
                    $("#gridIndex").val(index);
                    $('#hisDrugId').val(rowData.hisDrugId);
                    $(".btn-cancel-cus").click(function () {
                        $pop.close(formHisDrugsPop);
                    });
                    var reactDrugFreq;
                    var hisDrugData;
                    $ajax.post({
                        url: '${base}/ui/amcs/adverse/drugreaction/getAllDrugs',
                        sync:true
                    }).success (function (rst) {
                            hisDrugData = rst;
                        }
                    );
                    $ajax.post({
                        url: '${base}/ui/amcs/adverse/aeDict/getList?paraType=react_drug_freq&filter=',
                        sync:true
                    }).success (function (rst) {
                            reactDrugFreq = rst;
                        }
                    );
                    $ajax.postSync('${base}/ui/amcs/adverse/aeDict/getList?paraType=react_usage_unit&filter=',null,false,false).done(function (rst) {
                        $('#usageUnit').combobox('loadData', rst);
                    });
                    $('#commonName').combogrid({
                        panelWidth:500,
                        delay: 500,//用户输入请求间隙时间
                        //mode: 'remote',//根据输入向远端请求
                        //url: JSON.stringify(gridData),//请求url
                        idField: 'id',//输入框返回value
                        textField: 'drugsDesc',//输入框返回字符
                        striped: true,
                        required: true,
                        editable: true,
                        columns: [[//grid设置
                            {field:'drugsDesc',title:'通用名称',width:100},
                            {field:'tradeName',title:'商品名称',width:80},
                            {field:'manufacName',title:'生产厂家',width:80},
                            {field:'approvalNumber',title:'国药准字',width:80},
                        ]],
                        onSelect:function (idx,item) {
                            $('#tradeName').val(item.tradeName);
                            $('#manufacturer').val(item.manufacName);
                            $('#commonNameDesc').val(item.drugsDesc);
                            $('#medicalApprovalNum').val(item.approvalNumber);
                            $('#hisDrugId').val(item.id);
                        },
                        onChange:function (idx,item) {
                        },
                        keyHandler: {
                            up: function() {},
                            down: function() {},
                            enter: function() {},
                            query: function(q) {
                                doSearch(q,hisDrugData,['drugsDesc','firstSpell'],$(this));
                                $('#commonName').combogrid('setValue', q);
                            }
                        }
                    });
                    $('#defaultFreq').combogrid({
                        panelWidth:500,
                        delay: 500,//用户输入请求间隙时间
                        //mode: 'remote',//根据输入向远端请求
                        //url: JSON.stringify(gridData),//请求url
                        idField: 'valueCode',//输入框返回value
                        textField: 'valueDesc',//输入框返回字符
                        striped: true,
                        required: true,
                        editable: true,
                        columns: [[//grid设置
                            {field:'valueCode',title:'字典值',width:60},
                            {field:'valueDesc',title:'字典描述',width:100},
                            {field:'firstSpell',title:'首拼',width:80},
                        ]],
                        onSelect:function (idx,item) {
                        },
                        onChange:function (idx,item) {
                        },
                        keyHandler: {
                            up: function() {},
                            down: function() {},
                            enter: function() {},
                            query: function(q) {
                                doSearch(q,reactDrugFreq,['valueDesc','firstSpell'],$(this));
                                $('#defaultFreq').combogrid('setValue', q);
                            }
                        }
                    });
                    //$("#commonName").combogrid("grid").datagrid("loadData", _.take(hisDrugData,100));
                    $("#commonName").combogrid("grid").datagrid("loadData", hisDrugData);
                    $("#defaultFreq").combogrid("grid").datagrid("loadData", reactDrugFreq);
                    $('form.drugReactionHisDrugsDtl').form('load',rowData);
                    $('#commonName').combogrid('setValue', rowData.hisDrugId);
                    $('#defaultFreq').combogrid('setValue', rowData.defaultFreq);
                    $('#usageUnit').combobox('setValue', rowData.usageUnit);
                },
                end: function () {
                },
                beforeSubmit: function (opt, $form, formData) {
                    return true;
                }
            });
        });

        $('.cont-grid-am-drugs').on('click', '.s-op-edit', function () {
            var index = $(this).attr("rel");
            var rowData = $('#gridBox-2').datagrid("getRows")[index];
            var reactDrugFreq;
            var hisDrugData;
            window.formHisDrugsPop = $pop.popTemForm({
                title: "修改药物",
                temId: 'editReactionHisDrugsDtl',
                area: ['1000px', '280px'],
                temData: {},
                zIndex: 2,
                grid: '#gridBox-2',
                onPop: function ($p) {
                    $("#drugType").val(2);
                    $("#gridIndex").val(index);
                    $('#hisDrugId').val(rowData.hisDrugId);
                    $(".btn-cancel-cus").click(function () {
                        $pop.close(formHisDrugsPop);
                    });
                    $ajax.post({
                        url: '${base}/ui/amcs/adverse/drugreaction/getAllDrugs',
                        sync:true
                    }).success (function (rst) {
                            hisDrugData = rst;
                        }
                    );
                    $ajax.post({
                        url: '${base}/ui/amcs/adverse/aeDict/getList?paraType=react_drug_freq&filter=',
                        sync:true
                    }).success (function (rst) {
                            reactDrugFreq = rst;
                        }
                    );
                    $ajax.postSync('${base}/ui/amcs/adverse/aeDict/getList?paraType=react_usage_unit&filter=',null,false,false).done(function (rst) {
                        $('#usageUnit').combobox('loadData', rst);
                    });
                    $('#commonName').combogrid({
                        panelWidth:500,
                        delay: 500,//用户输入请求间隙时间
                        //mode: 'remote',//根据输入向远端请求
                        //url: JSON.stringify(gridData),//请求url
                        idField: 'id',//输入框返回value
                        textField: 'drugsDesc',//输入框返回字符
                        striped: true,
                        required: true,
                        editable: true,
                        columns: [[//grid设置
                            {field:'drugsDesc',title:'通用名称',width:100},
                            {field:'tradeName',title:'商品名称',width:80},
                            {field:'manufacName',title:'生产厂家',width:80},
                            {field:'approvalNumber',title:'国药准字',width:80},
                        ]],
                        onSelect:function (idx,item) {
                            $('#tradeName').val(item.tradeName);
                            $('#manufacturer').val(item.manufacName);
                            $('#commonNameDesc').val(item.drugsDesc);
                            $('#medicalApprovalNum').val(item.approvalNumber);
                            $('#hisDrugId').val(item.id);
                        },
                        onChange:function (idx,item) {
                        },
                        keyHandler: {
                            up: function() {},
                            down: function() {},
                            enter: function() {},
                            query: function(q) {
                                doSearch(q,hisDrugData,['drugsDesc','firstSpell'],$(this));
                                $('#commonName').combogrid('setValue', q);
                            }
                        }
                    });
                    $('#defaultFreq').combogrid({
                        panelWidth:500,
                        delay: 500,//用户输入请求间隙时间
                        //mode: 'remote',//根据输入向远端请求
                        //url: JSON.stringify(gridData),//请求url
                        idField: 'valueCode',//输入框返回value
                        textField: 'valueDesc',//输入框返回字符
                        striped: true,
                        required: true,
                        editable: true,
                        columns: [[//grid设置
                            {field:'valueCode',title:'字典值',width:60},
                            {field:'valueDesc',title:'字典描述',width:100},
                            {field:'firstSpell',title:'首拼',width:80},
                        ]],
                        onSelect:function (idx,item) {
                        },
                        onChange:function (idx,item) {
                        },
                        keyHandler: {
                            up: function() {},
                            down: function() {},
                            enter: function() {},
                            query: function(q) {
                                doSearch(q,reactDrugFreq,['valueDesc','firstSpell'],$(this));
                                $('#defaultFreq').combogrid('setValue', q);
                            }
                        }
                    });
                    $("#commonName").combogrid("grid").datagrid("loadData", hisDrugData);
                    $("#defaultFreq").combogrid("grid").datagrid("loadData", reactDrugFreq);
                    $('form.drugReactionHisDrugsDtl').form('load',rowData);
                    $('#commonName').combogrid('setValue', rowData.hisDrugId);
                    $('#defaultFreq').combogrid('setValue', rowData.defaultFreq);
                    $('#usageUnit').combobox('setValue', rowData.usageUnit);
                },
                end: function () {
                },
                beforeSubmit: function (opt, $form, formData) {
                    return true;
                }
            });
        });

        $('.cont-grid-sm-drugs').on('click', '.s-op-del', function () {
            var index = $(this).attr("rel");
            var rowData = $('#gridBox-1').datagrid("getRows")[index];
            $pop.confirm('是否删除该记录？', function (r) {//确定
                if(rowData.id){
                    $ajax.post('${base}/ui/amcs/adverse/drugreaction/delete', {id:rowData.id}).done(function (rst) {});
                }
                $('#gridBox-1').datagrid('deleteRow',index);
                $('#gridBox-1').datagrid("loadData",$('#gridBox-1').datagrid("getRows"));
                return true;
            }, function () {//取消
                return true;
            });
        });

        $('.cont-grid-am-drugs').on('click', '.s-op-del', function () {
            var index = $(this).attr("rel");
            var rowData = $('#gridBox-2').datagrid("getRows")[index];
            $pop.confirm('是否删除该记录？', function (r) {//确定
                if(rowData.id){
                    $ajax.post('${base}/ui/amcs/adverse/drugreaction/delete', {id:rowData.id}).done(function (rst) {});
                }
                $('#gridBox-2').datagrid('deleteRow',index);
                $('#gridBox-2').datagrid("loadData",$('#gridBox-2').datagrid("getRows"));
                return true;
            }, function () {//取消
                return true;
            });
        });

        window.submitEditForm = function (opt, $form, data) {
            var formSmDrugs = $('#gridBox-1').datagrid('getRows');
            var formAmDrugs = $('#gridBox-2').datagrid('getRows');
            data.smDrugReactDrugs = formSmDrugs;
            data.amDrugReactDrugs = formAmDrugs;
            if(data.id=='1'){
                data.id=null;
            }
            $ajax.post({
                url: '${base}/ui/amcs/adverse/drugreaction/save',
                data: data,
                tip: '你确定提交吗？',
                jsonData : true,//是否采用jsonData格式提交
                sync : false,//是否同步方式提交
                type : 'post',//采用post方式提交
                loadingMask : true,//进行异步请求中，是否显示mask
                calltip : true,//提交成功后显示请求成功信息
                success: function (rst) {
                    $pop.closePop();
                    $('#gridBox').datagrid('reload');
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

        window.submitReactionHisDrugsDtlForm = function (opt, $form, data) {
            data.typeDrug=data.drugType;
            data.commonName=$("#commonName").combogrid("getText");
            data.defaultFreq=$("#defaultFreq").combogrid("grid").datagrid('getSelected').valueCode;
            data.defaultFreqDesc=$("#defaultFreq").combogrid("grid").datagrid('getSelected').valueDesc;
            data.usageUnitDesc=$('#usageUnit').combobox('getText');
            if(data.drugType=='1'){
                if(data.gridIndex){
                    $('#gridBox-1').datagrid('updateRow',{index: data.gridIndex,row: data});
                }else{
                    $('#gridBox-1').datagrid('appendRow',data);
                }
            }else if(data.drugType=='2'){
                if(data.gridIndex){
                    $('#gridBox-2').datagrid('updateRow',{index: data.gridIndex,row: data});
                }else{
                    $('#gridBox-2').datagrid('appendRow',data);
                }
            }
            $pop.close(window.formHisDrugsPop);
            return false;
        };

    });

</script>



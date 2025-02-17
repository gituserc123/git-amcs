<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>角膜接触镜不良事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
    <style>
        .layui-layer-close1{
            background-position: -188px -40px !important;
        }
        .relative{position: relative;}
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
            <span class="s-title">事件经过和疾病诊断</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">


        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">门诊时间</label>
                    <input id="visitDate" type="text"
                           class="txt  so-date txt-validate" name="visitDate"
                           data-opt="maxDate:new Date(),required:true"
                           style="width: 100%" noNull="必填" value="${ae.visitDate!}">
                </div>
            </div>


            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">不良反应级别</label>
                    [@ui_select id="adverseLevel" name="adverseLevel" tag="@ae@adverse_level"  style="width:100%" dataOptions="editable:false" filterkey="firstSpell"  /]


                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                    <label id="qlable" class="lab-item">门诊诊断<span style="padding-left: 2px"  ><i  class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                    [@ui_select id="visitDiagnose" name="visitDiagnose"   style="width:100%" dataOptions="editable:false" filterkey="firstSpell" value="${ae.visitDiagnose!}" /]
                    <input id="visitDiagnoseRemark" type="text" class="txt  easyui-validatebox txt-validate" name="visitDiagnoseRemark" placeholder="其他诊断请自行填报" style="width: 100%" value="${ae.visitDiagnoseRemark!}">
                </div>
            </div>
        </div>

        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">本次就诊基本检查信息</span>
            </div>



                <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10 p6">
                    <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                    裸眼远视力
                    <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
                    <span class="fl">
                      <label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                    </span>
                        <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="nakedFarVodType" id="nakedFarVodType" value="${ae.nakedFarVodType}">
                       <option value="1" selected="selected">数值</option>
                       <option value="2">描述</option>
                      </select>
                    </span>
                        <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="nakedFarVod" name="nakedFarVod" value="${ae.nakedFarVod!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#nakedFarVodName').val(record.valueDesc);
						}" value="${ae.nakedFarVod!}">
						</select>
						<input class="txt" type="hidden" id="nakedFarVodName" name="nakedFarVodName" value="${ae.nakedFarVodName!}"/>
                    </span>
                    </div>
                    <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
					<span class="fl">
                        <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                    </span>
                        <span class="fl w-80">
						<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="nakedFarVosType" id="nakedFarVosType" value="${ae.nakedFarVosType!}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
                        <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="nakedFarVos" name="nakedFarVos"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#nakedFarVosName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="nakedFarVosName" name="nakedFarVosName" value="${ae.nakedFarVosName!}"/>
					</span>
                    </div>
                </div>
                <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                    <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                    眼压
                    <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
					</span>
                        <div class="fl">
                            <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="iopTypeOd" id="iopTypeOd" data-options="valueField:'valueCode',textField:'valueDesc'">
                            </select>
                        </div>
                        <div class="s-eyePressW-0 fl relative">
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="iopOd" id="iopOd1" data-options="min:1,max:100,precision:1" />
                            <em class="em-at em-dropAt">mmHg</em>
                        </div>
                        <div class="s-eyePressW-1 fl">
                            <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="iopOd" id="iopOd2"
                                    data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#iopOdName').val(record.valueDesc);
						}">
                            </select>
                            <input class="txt" type="hidden" id="iopOdName" name="iopOdName" value=""/>
                        </div>
                    </div>
                    <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
                <span class="fl">
                    <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                </span>
                        <div class="fl">
                            <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="iopTypeOs" id="iopTypeOs" data-options="valueField:'valueCode',textField:'valueDesc'">
                            </select>
                        </div>
                        <div class="s-eyePressWOs-0 fl relative">
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="iopOs" id="iopOs1" value="" data-options="min:1,max:100,precision:1" />
                            <em class="em-at em-dropAt">mmHg</em>
                        </div>
                        <div class="s-eyePressWOs-1 fl">
                            <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="iopOs" id="iopOs2"
                                    data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#iopOsName').val(record.valueDesc);
						}">
                            </select>
                            <input class="txt" type="hidden" id="iopOsName" name="iopOsName" value=""/>
                        </div>
                    </div>
                </div>





        </div>

        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">镜片信息</span>
            </div>
            <div class="row">
                <div class="p3">
                    <div class="item-one">
                        <label class="lab-item">镜片种类</label>
                        [@ui_select id="contactType" name="contactType" tag="@ae@contact_lens_type"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.contactType!}" /]
                    </div>
                </div>
                <div class="p3">
                    <div class="item-one">
                        <label class="lab-item">镜片品牌</label>
                        [@ui_select id="contactBrand" name="contactBrand" tag="@ae@contact_brand"  style="width:100%;" dataOptions="editable:false,required:true,onChange:((v)=>{

                            if(v==28){
                            $('#contactBrandRemark').validatebox('readonly',false)
                            }else{
                            $('#contactBrandRemark').setVal('')
                            $('#contactBrandRemark').validatebox('readonly',true)
                            }
                            })" filterkey="firstSpell" value="${ae.contactBrand!}" /]
                        <input id="contactBrandRemark" type="text"
                               class="txt  easyui-validatebox txt-validate" name="contactBrandRemark"
                               data-options="readonly:true" placeholder="其他请自行填报"
                               style="width: 100%" value="${ae.contactBrandRemark!}" >
                    </div>
                </div>
                <div class="p3">
                    <div class="item-one">
                        <label class="lab-item">镜片设计</label>
                        [@ui_select id="contactDesign" name="contactDesign" tag="@ae@contact_design"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.contactDesign!}" /]
                    </div>
                </div>
            </div>
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">镜片目前情况</span>
            </div>
            <div class="row">
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item">镜片完整度(OD)</label>
                        [@ui_select id="lensIntegrityOd" name="lensIntegrityOd" tag="@ae@lens_integrity"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.lensIntegrityOd!}" /]

                    </div>
                </div>
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item">镜片完整度(OS)</label>
                        [@ui_select id="lensIntegrityOs" name="lensIntegrityOs" tag="@ae@lens_integrity"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.lensIntegrityOs!}" /]

                    </div>
                </div>
            </div>
            <div class="row">
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item">镜片划痕(OD)</label>
                        [@ui_select id="lensScratchsOd" name="lensScratchsOd" tag="@ae@lens_scratches"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.lensScratchsOd!}" /]

                    </div>
                </div>
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item">镜片划痕(OS)</label>
                        [@ui_select id="lensScratchsOs" name="lensScratchsOs" tag="@ae@lens_scratches"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.lensScratchsOs!}" /]

                    </div>
                </div>
            </div>
            <div class="row">
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item">镜片清洁度(OD)</label>
                        [@ui_select id="lensCleaningOd" name="lensCleaningOd" tag="@ae@lens_cleaning"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.lensCleaningOd!}" /]

                    </div>
                </div>
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item">镜片清洁度(OS)</label>
                        [@ui_select id="lensCleaningOs" name="lensCleaningOs" tag="@ae@lens_cleaning"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.lensCleaningOs!}" /]

                    </div>
                </div>
            </div>
        </div>

        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">戴镜前检查信息</span>
            </div>
            <div style="font-size: 14px" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                综合验光结果
            </div>
            <div class="clearfix mar-l20 mar-b10 p6">
                    <span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
					</span>
                <span class="fl">
						<label class="lab-inline">球镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgQjOd" id="jcYgQjOd" value="" data-options="min:-70,max:70,precision:2" value="${ae.jcYgQjOd!}" />
					</span>
                <span class="fl">
						<label class="lab-inline">柱镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgZjOd" id="jcYgZjOd" value="" data-options="min:-20,max:20,precision:2" />
					</span>
                <span class="fl">
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgCwOd" id="jcYgCwOd" value="" data-options="min:0,max:180,precision:0" />
					</span>
                <span class="fl">
						<label class="lab-inline">矫正视力：</label>
					</span>
                <span class="fl">
						<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="jcYgJzslOdType" id="jcYgJzslOdType">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
                <span class="s-sl-v fl">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcYgJzslOd" name="jcYgJzslOd"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcYgJzslOdName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcYgJzslOdName" name="jcYgJzslOdName" value=""/>
					</span>
            </div>
            <div class="clearfix mar-l20 mar-b10 p6">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
					</span>
                <span class="fl">
						<label class="lab-inline">球镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgQjOs" id="jcYgQjOs" value="" data-options="min:-70,max:70,precision:2" />
					</span>
                <span class="fl">
						<label class="lab-inline">柱镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgZjOs" id="jcYgZjOs" value="" data-options="min:-20,max:20,precision:2" />
					</span>
                <span class="fl">
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgCwOs" id="jcYgCwOs" value="" data-options="min:0,max:180,precision:0" />
					</span>
                <span class="fl">
						<label class="lab-inline">矫正视力：</label>
					</span>
                <span class="fl">
						<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="jcYgJzslOsType" id="jcYgJzslOsType">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
                <span class="s-sl-v fl">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcYgJzslOs" name="jcYgJzslOs"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcYgJzslOsName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcYgJzslOsName" name="jcYgJzslOsName" value=""/>
					</span>
            </div>
            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                角膜平K
                <div class="clearfix mar-l10 mar-b10 mar-t10" style="font-size: 12px;">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
					</span>
                    <span class="fl">
						<label class="lab-inline">值：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzOd" id="jcJmpkzOd" value="" data-options="min:0,max:70,precision:2" />
						@
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzwOd" id="jcJmpkzwOd" value="" data-options="min:0,max:180,precision:2" />
					</span>
                </div>
                <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
					<span class="fl">
                        <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                    </span>
                    <span class="fl">
						<label class="lab-inline">值：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzOs" id="jcJmpkzOs" value="" data-options="min:0,max:70,precision:2" />
						@
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzwOs" id="jcJmpkzwOs" value="" data-options="min:0,max:180,precision:2" />
                    </span>
                </div>
            </div>



        </div>
        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">戴镜基本信息</span>
            </div>
            <div class="row">
                <div class="p3">
                    <div class="item-one solab-l">
                        <label class="lab-item solab-lb">初次戴镜日期:</label>
                        <input id="firstWear" type="text"
                               class="txt  so-date txt-validate" name="firstWear"
                               data-opt="maxDate:new Date(),required:true"
                               style="width: 100%" noNull="必填" value="${ae.firstWear!}">

                    </div>
                </div>
                <div class="p3">
                    <div class="item-one solab-lb">
                        <label class="lab-item">镜片左右颜色是否正确：</label>
                        [@ui_select id="colorSign" name="colorSign" tag="@amcs@com.aier.cloud.api.amcs.enums.YesOrNoEnum"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.colorSign!}" /]

                    </div>
                </div>
                <div class="p3">
                    <div class="item-one solab-lb">
                        <label class="lab-item">镜片是否超期：</label>
                        [@ui_select id="expireSign" name="expireSign" tag="@amcs@com.aier.cloud.api.amcs.enums.YesOrNoEnum"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.expireSign!}" /]

                    </div>
                </div>
                <div class="p3">
                    <div class="item-one solab-lb">
                        <label class="lab-item">是否按期复查：</label>
                        [@ui_select id="reviewSign" name="reviewSign" tag="@amcs@com.aier.cloud.api.amcs.enums.YesOrNoAndOtherEnum"  style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.reviewSign!}" /]

                    </div>
                </div>
            </div>

        </div>
        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">事件经过描述</span>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-l">
                    <label class="lab-item">就诊经过：</label>
                    <textarea maxlength="1300" class="txt txt-validate required adaptiveTextarea" required="true" type="text" name="procDescr" placeholder="" >${ae.procDescr!}</textarea>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-l">
                    <label class="lab-item">病情归转：</label>
                    [@ui_select id="convertStatus" name="convertStatus" tag="@ae@convert_status"  style="width:100%;" dataOptions="editable:false,required:true,
onChange:((v)=>{
                            if(v==3){
                            $('#convertRemark').validatebox('readonly',false)
                            }else{
                            $('#convertRemark').setVal('')
                            $('#convertRemark').validatebox('readonly',true)
                            }
                            })" filterkey="firstSpell" value="${ae.convertStatus!}" /]
                    <input id="convertRemark" type="text"
                           class="txt  easyui-validatebox txt-validate" name="convertRemark"
                           data-options="readonly:true" placeholder="其他请自行填报"
                           style="width: 100%" value="${ae.convertRemark!}">
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-l">
                    <label class="lab-item">事件状态：</label>
                    [@ui_select id="eventStatus" name="eventStatus" tag="@ae@event_status"  style="width:100%;" dataOptions="editable:false,required:true,
onChange:((v)=>{
                            if(v==3){
                            $('#eventStatusRemark').validatebox('readonly',false)
                            }else{
                            $('#eventStatusRemark').setVal('')
                            $('#eventStatusRemark').validatebox('readonly',true)
                            }
                            })" filterkey="firstSpell" value="${ae.eventStatus!}" /]
                    <input id="eventStatusRemark" type="text"
                           class="txt  easyui-validatebox txt-validate" name="eventStatusRemark"
                           data-options="readonly:true" placeholder="其他请自行填报"
                           style="width: 100%" value="${ae.eventStatusRemark!}">
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-l">
                    <label class="lab-item">满意度：</label>
                    [@ui_select id="satisfaction" name="satisfaction" tag="@ae@satisfaction"  style="width:100%;" dataOptions="editable:false,required:true,
onChange:((v)=>{
                            if(v==4){
                            $('#satisfactionRemark').validatebox('readonly',false)
                            }else{
                            $('#satisfactionRemark').setVal('')
                            $('#satisfactionRemark').validatebox('readonly',true)
                            }
                            })" filterkey="firstSpell" value="${ae.satisfaction!}" /]
                    <input id="satisfactionRemark" type="text"
                           class="txt  easyui-validatebox txt-validate" name="satisfactionRemark"
                           data-options="readonly:true" placeholder="其他请自行填报"
                           style="width: 100%" value="${ae.satisfactionRemark!}" >
                </div>
            </div>
        </div>
        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">目前情况</span>

            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-l">
                    <label class="lab-item">治疗及用药情况：</label>
                    <textarea maxlength="500"  class="txt txt-validate required adaptiveTextarea"  type="text" name="grugRemak" placeholder="" >${ae.grugRemak!}</textarea>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-l">
                    <label class="lab-item">症状及体征：</label>
                    <textarea maxlength="500"  class="txt txt-validate required adaptiveTextarea"  type="text" name="vitalSign" placeholder="">${ae.vitalSign!}</textarea>
                </div>
            </div>

        </div>
        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">患者诉求</span>

            </div>
        </div>
        <div class="row">
            <div class="p12">


                    <textarea maxlength="500"  class="txt txt-validate required adaptiveTextarea"  type="text" name="demand" placeholder="">${ae.demand!}</textarea>

            </div>

        </div>
    </form>


    <form class="soform formA form-validate">
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom.ftl"]
    </form>
    <form class="soform formA form-validate">
        <div>
            <div class="mar-l20">
                <div style="font-size: 14px;" class="mar-b10">
                    <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                    <span class="pad-l10">原因分析</span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">与镜片配适相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason1" value="1与镜片配适相关:::偏紧" /> 偏紧</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason1" value="1与镜片配适相关:::偏松" /> 偏松</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason1" value="1与镜片配适相关:::偏位" /> 偏位</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason1" value="1与镜片配适相关:::其它" /> 其它</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">护理不当相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason2" value="2护理不当相关:::护理产品使用不当" /> 护理产品使用不当</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason2" value="2护理不当相关:::戴镜前未搓洗镜片" /> 戴镜前未搓洗镜片</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason2" value="2护理不当相关:::未定期使用AB液" /> 未定期使用AB液</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">摘戴镜不当相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason3" value="3摘戴镜不当相关:::摘镜前未充分润滑镜片" /> 摘镜前未充分润滑镜片</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason3" value="3摘戴镜不当相关:::戴镜前未滴润眼液" /> 戴镜前未滴润眼液</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">手卫生相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason4" value="4手卫生相关:::操作前未按正确方法洗手" /> 操作前未按正确方法洗手</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason4" value="4手卫生相关:::指甲未修剪到位" /> 指甲未修剪到位</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">未定期更换护理产品相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason5" value="5未定期更换护理产品相关:::未定期更换镜盒" /> 未定期更换镜盒</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason5" value="5未定期更换护理产品相关:::未定期更换吸棒" /> 未定期更换吸棒</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">镜片超期佩戴相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason6" value="6镜片超期佩戴相关" /></label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">未按要求定期进行复查相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason7" value="7未按要求定期进行复查相关" /></label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">未即时停戴镜片相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason8" value="8未即时停戴镜片相关:::出现感冒发烧咳嗽等全身不适时未及时停戴" /> 出现感冒发烧咳嗽等全身不适时未及时停戴</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason8" value="8未即时停戴镜片相关:::出现眼红眼痛等眼部不适时未及时停戴" /> 出现眼红眼痛等眼部不适时未及时停戴</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">其它</label>
                    <input maxlength="400" class="txt txt-validate w-490 reasontxt" type="text" name="reason9" value=""/>
                </span>
                </div>
            </div>
        </div>
        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">整改意见及结果</span>
            </div>
        </div>
        <div class="row">
            <div class="p12">

                    <textarea maxlength="500"  id="opinion" class="txt txt-validate required adaptiveTextarea"  type="text" name="opinion" placeholder="" >${ae.opinion!}</textarea>

            </div>
        </div>
        <input id="reason" class="hidden txt txt-validate" name="reason"/>
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
            compositionData()
            return true
        }

        $('#adverseLevel').combobox({
            onChange: function(newValue,oldValue){
                //不良反应级别改变时，调整相应的门诊整段提示信息，同时需要同步事件分级，是使其与事件分级二级I对应
                $("#qlable").off("click");
                if(newValue == 1){
                    $('#qlable').click(function(){
                        $pop.tips(`角膜压痕(镜片轮廓在角膜或者结膜上出现压痕)<br/>
                                角膜隐窝(戴镜时镜片下存在多量微小气泡，摘镜后角膜受压处出现小微凹)<br/>
                                1级角膜点状上皮病变(无刺激症状，限于浅表上皮点状染色，范围≤15%角膜面积)<br/>
                                角膜色素环(角膜基质层内铁质沉积形成的棕色环，边界清楚，通常先出现在下方，逐渐形成全周，无任何不适症状)<br/>
                                反应性结膜充血(常见于初戴者，戴镜后异物感较强，轻度畏光、流泪，不同程度的结膜充血)<br/>
                                慢性结膜炎(异物感、眼痒、轻度畏光流泪，不同程度的睑结膜、球结膜充血，伴或不伴有乳头、滤泡及分泌物)<br/>
                                轻度干眼(裂隙灯显微镜下检查无明显眼表损伤体征（角膜荧光素染色<5个），泪膜破裂时间在5s及以上)<br/>
                                视力不良(使用接触镜未达到理想裸眼视力和（或）矫正视力，视物模糊)<br/>
                                视力波动(使用接触镜后矫正视力或裸眼视力不稳定，不同时段视物清晰或模糊)<br/>
                                重影(视物时存在虚边，接触镜相关的重影多为单眼，常由与瞳孔区对应的散光和高阶像差引起)<br/>
                                眩光(是指视野中由于不适宜亮度分布或在空间或时间方面存在极端亮度对比，产生人眼无法适应的光亮感觉，降低了物体可见度的视觉条件，以致引起视觉不舒适甚至短暂丧失视力的视觉现象)<br/>
                                `,'#qlable',{time: 0, closeBtn: true,maxWidth: 600})
                    });
                    //修改事件分级
                    $("#gradeOne").combobox('setValue', 25);
                    $("#gradeTwoA").combobox('setValue', 111);
                }else if(newValue == 2){
                    $('#qlable').click(function(){
                        $pop.tips(`感染性结膜炎(畏光、流泪、分泌物增多，结膜充血、水肿，结膜囊可见分泌物，严重者可伴有眼睑的充血水肿、疼痛和视物模糊)<br/>
                                急性过敏性结膜炎(主诉在配戴接触镜后或使用某种药物后即可出现明显的眼红、眼痒和刺痛感，周边皮肤亦可出现充血、水肿或湿疹。检查可见球结膜及睑结膜充血，严重者可伴有结膜乳头、滤泡)
                                紧镜综合症(可有严重刺激感、眼红、眼痛，角膜上皮或基质水肿，浅层角膜上皮染色，结膜或角膜上有镜片印痕，有镜片粘附或摘镜偏紧史)
                                角膜新生血管(角膜缘血管侵入角膜周边部≥1mm，一般位于浅层)
                                角膜水肿(出现不同程度视力下降、畏光、流泪等症状，表现为上皮水肿或基质水肿)
                                2级角膜点状上皮病变(伴或不伴有刺激症状，角膜中央散在的点状染色或周边3:00和9:00方位的点状染色，可累及角膜上皮深层，范围为16%～30%角膜面积)
                                3级角膜点状上皮病变(有刺激症状，累及角膜上皮深层，角膜基质快速局限性着色，范围为31%～45%角膜面积)
                                4级角膜点状上皮病变(有刺激症状，直径超过2mm的密集团块染色，累及角膜上皮深层，角膜基质快速弥漫性着色，范围>45%角膜面积)
                                无菌性周边角膜浸润(角膜周边浅基质层出现直径1~2mm孤立的灰白色圆形混浊浸润病灶，伴或不伴有溃疡、刺激症状较轻。刮片培养阴性)
                                中度干眼(裂隙灯显微镜下检查角膜损伤范围不超过2个象限和（或）角膜荧光素染色点≥5个且<30个，泪膜破裂时间在2s及以上)
                                重度干眼(裂隙灯显微镜下检查角膜损伤范围2个象限及以上和（或）角膜荧光素染色点≥30个，泪膜破裂时间<2s，角膜荧光素染点融合成粗点、片状或伴有丝状物)
                                `,'#qlable',{time: 0,closeBtn: true,maxWidth: 600})
                    });
                    //修改事件分级
                    $("#gradeOne").combobox('setValue', 25);
                    $("#gradeTwoA").combobox('setValue', 112);
                }else if(newValue == 3){
                    //修改事件分级
                    $("#gradeOne").combobox('setValue', 25);
                    $("#gradeTwoA").combobox('setValue', 113);

                }else{
                    $("#gradeOne").combobox('setValue', '');
                    $("#gradeTwoA").combobox('setValue', '');
                }
                filterDiagnose(newValue);
            }
        });
        function filterDiagnose(level) {
            let url = base + '/ui/amcs/dict/getAeDict?type=visit_diagnose&filter=' + level;
            $('#visitDiagnose').combobox('reload', url);
        }




        //保存前执行事件 false中断保存
        window.beforeSubmit = function(){
            //不良反应级别填写Ⅲ级时，事件分类一级必须填写医院感染事件
            /*
            if($("#adverseLevel").val() == 3) {
            	if($('#gradeOne').combobox('getValue') != 3) {
            		$pop.msg('此事件为Ⅲ级不良反应事件，请将事件分类一级改为“医院感染事件“');
            		return false;
            	}
            }
            */
            compositionData()
            return true;
        }
        //组装数据
        const compositionData=(()=>{
            $("#reason").setVal(getRadioFormVal())
            console.log("组装数据")
        })

        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
        $('#common_reason').remove();
        $('#common_opinion').remove();

        /**
         * 从接口获取数据后表单赋值
        * */
        window.afterSerachPatient=function (){
            //门诊接口数据
            var opDomain=window.opDomain;
            $("#visitDate").setVal(!!opDomain?opDomain.regDate.substr(0,10):"")
            [#--$('#nakedFarVodType').setVal('${opDomain.refractiveBlVO.}')--]
            $('#nakedFarVod').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.scdOd:"")
            [#--$('#nakedFarVosType').setVal('${ae.nakedFarVosType!}')--]
            $('#nakedFarVos').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.scdOs:"")
            [#--$('#iopTypeOd').setVal('${ae.iopTypeOd!}')--]
            [#--$('#iopTypeOs').setVal('${ae.iopTypeOs!}')--]
            $('#iopOd1').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.iopOd:"")
            $('#iopOd2').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.iopOd:"")
            $('#iopOs1').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.iopOs:"")
            $('#iopOs2').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.iopOs:"")

            $('#jcYgQjOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.sphOd:"")
            $('#jcYgQjOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.sphOs:"")
            $('#jcYgZjOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.cylOd:"")
            $('#jcYgZjOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.cylOs:"")
            $('#jcYgCwOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.axisOd:"")
            $('#jcYgCwOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.axisOs:"")

            [#--$('#jcYgJzslOdType').setVal('${ae.jcYgJzslOdType!}')--]
            $('#jcYgJzslOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.dvaOd:"")

            [#--$('#jcYgJzslOsType').setVal('${ae.jcYgJzslOsType!}')--]
            $('#jcYgJzslOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.dvaOs:"")

            $('#jcJmpkzOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkValueOd:"")
            $('#jcJmpkzwOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkAxOd:"")

            $('#jcJmpkzOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkValueOs:"")
            $('#jcJmpkzwOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkAxOs:"")

        }

        /**
         * 单选框 切换选中状态
         * @param selector 选择器
         */
        function radioTogger(selector){
            $(selector).click(function(){
                var $this = $(this);
                if(!!$this.attr("checked")){
                    $this.attr("checked",false);
                }else{
                    $this.attr("checked",true);
                }
            });
        }

        /**
         * 获取单选框表单值
         */
        function getRadioFormVal(){
            var radioArr = [];
            $(".reason:checked").each(function(){
                radioArr.push($(this).val())
            });
            var otherVal = $(".reasontxt").val();
            !!otherVal && radioArr.push("9其它:::"+otherVal);
            return radioArr.join("$,$");
        }

        /**
         * 回显 单选框 选中值
         * @param str 已存的表单值
         */
        function setRadioView(str){
            var radioVal = str;
            var radioarr = radioVal.split('$,$');
            radioarr.forEach(function(item){
                var index = item.charAt(0);
                if(index == 9){
                    $(".reasontxt").val(item.replace(/^9其它:::/,""));
                }else{
                    var select ="[name='reason"+index+"'][value='"+item+"']";
                    $(select).attr("checked",true);
                }
            });
        }
        radioTogger(".reason");//单选框toggle选中状态
         // setRadioView('1与镜片配适相关:::偏紧$,$2护理不当相关:::未定期使用AB液$,$9其它:::这里是备注内容$,$6镜片超期佩戴相关');//回显 单选框 默认选中值  $,$  为分隔符
        [#--setRadioView(${ae.reason})--]

        // setRadioView('1与镜片配适相关:::偏松$,$2护理不当相关:::戴镜前未搓洗镜片$,$3摘戴镜不当相关:::戴镜前未滴润眼液$,$4手卫生相关:::指甲未修剪到位$,$5未定期更换护理产品相关:::未定期更换吸棒$,$6镜片超期佩戴相关$,$7未按要求定期进行复查相关$,$8未即时停戴镜片相关:::出现感冒发烧咳嗽等全身不适时未及时停戴')
        [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]
        $('#contactType').combobox({
            onChange: function (newValue, oldValue) {
                let brand = $('#contactType').combobox('getValue');
                filtercontactBrand(brand);
            }
        });

        function filtercontactBrand(brand) {
            let url = base + '/ui/amcs/dict/getAeDictFilters?type=contact_brand&filters=' + brand + ',999';
            $('#contactBrand').combobox('reload', url);

        }




        /**远视力列表*/
        var farEyesight = null;
        /**近视力列表*/
        var nearEyesight = null;
        /**眼压类型*/
        var iopType = null;
        /**指测眼压结果*/
        var fingerTonometry = null;

        var param = {paraType : ['far_eyesight','near_eyesight','eyesight_desc', 'iop_type', 'finger_tonometry']};
        $ajax.postSync('${base}/ui/base/dict/getMoreList',param,false,false).done(function (rst) {
            var dict = rst.data;
            farEyesight=[dict.far_eyesight, dict.eyesight_desc];
            nearEyesight=dict.near_eyesight;
            iopType=dict.iop_type;
            fingerTonometry=dict.finger_tonometry;
            //console.log('farEyesight=',farEyesight);
        });
        var setValue=function(){

            $('#nakedFarVodType').setVal('${ae.nakedFarVodType!}')
            $('#nakedFarVod').setVal('${ae.nakedFarVod!}')
            $('#nakedFarVosType').setVal('${ae.nakedFarVosType!}')
            $('#nakedFarVos').setVal('${ae.nakedFarVos!}')
            $('#iopTypeOd').setVal('${ae.iopTypeOd!}')
            $('#iopTypeOs').setVal('${ae.iopTypeOs!}')
            $('#iopOd1').setVal('${ae.iopOd!}')
            $('#iopOd2').setVal('${ae.iopOd!}')
            $('#iopOs1').setVal('${ae.iopOs!}')
            $('#iopOs2').setVal('${ae.iopOs!}')

            $('#jcYgQjOd').setVal('${ae.jcYgQjOd!}')
            $('#jcYgQjOs').setVal('${ae.jcYgQjOs!}')
            $('#jcYgZjOd').setVal('${ae.jcYgZjOd!}')
            $('#jcYgZjOs').setVal('${ae.jcYgZjOs!}')
            $('#jcYgCwOd').setVal('${ae.jcYgCwOd!}')
            $('#jcYgCwOs').setVal('${ae.jcYgCwOs!}')

            $('#jcYgJzslOdType').setVal('${ae.jcYgJzslOdType!}')
            $('#jcYgJzslOd').setVal('${ae.jcYgJzslOd!}')

            $('#jcYgJzslOsType').setVal('${ae.jcYgJzslOsType!}')
            $('#jcYgJzslOs').setVal('${ae.jcYgJzslOs!}')

            $('#jcJmpkzOd').setVal('${ae.jcJmpkzOd!}')
            $('#jcJmpkzwOd').setVal('${ae.jcJmpkzwOd!}')

            $('#jcJmpkzOs').setVal('${ae.jcJmpkzOs!}')
            $('#jcJmpkzwOs').setVal('${ae.jcJmpkzwOs!}')

            $('#adverseLevel').combobox('setValue','${ae.adverseLevel!}')

        }

        var baseExam = {

            init : function (){

                setRadioView('${ae.reason}');
                var me = this;
                $('.farEyesight').combobox('loadData',farEyesight[0]);
                $('.drop-eyePressExamType').combobox('loadData',iopType);
                $('.drop-pressExam').combobox('loadData',fingerTonometry);
                me.farVisionFn();//远视力切换数据
                me.eyePressFn();//眼压检查
                setValue();



            },
            farVisionFn : function (){//远视力切换数据
                $('.drop-sl-k').combobox({
                    onChange : function (val){
                        if (!val){
                            $(this).combobox('setValue','1');//值空
                        }
                        else{
                            var $v = $(this).parents('.clearfix').find('.drop-sl-v');
                            $v.combobox('setValue','');//值空
                            $v.combobox('loadData',farEyesight[val-1]);//loadData
                        }
                    }
                });
            },
            eyePressFn : function (){//眼压检查
                $('#iopTypeOd').combobox({
                    onChange : function (val){
                        if(val ==='05'){
                            $('.s-eyePressW-0').hide();
                            $('.s-eyePressW-1').show();
                            $('#iopOd1').numberspinner('disable');
                            $('#iopOd2').combobox('enable')
                        }else{
                            $('.s-eyePressW-0').show();
                            $('.s-eyePressW-1').hide();
                            $('#iopOd1').numberspinner('enable');
                            $('#iopOd2').combobox('disable');
                        }
                        $form.formAEnterFun();
                    }
                });
                $('#iopTypeOs').combobox({
                    onChange : function (val){
                        if(val ==='05'){
                            $('.s-eyePressWOs-0').hide();
                            $('.s-eyePressWOs-1').show();
                            $('#iopOs1').numberspinner('disable');
                            $('#iopOs2').combobox('enable')
                        }else{
                            $('.s-eyePressWOs-0').show();
                            $('.s-eyePressWOs-1').hide();
                            $('#iopOs1').numberspinner('enable');
                            $('#iopOs2').combobox('disable');
                        }
                        $form.formAEnterFun();
                    }
                });
            }
        };

        $(function () {
            //初始化表单
            baseExam.init();
            if(!'${ae.id}'){
            	$('.drop-eyePressExamType').combobox('setValue','01');//眼压检查默认第一个值
                if(!$.isEmptyObject('${ae.patientNo}')){
					queryPatientInfo('${ae.patientNo}');
				}
			}

        });
        


    })
</script>
</body>

</html>
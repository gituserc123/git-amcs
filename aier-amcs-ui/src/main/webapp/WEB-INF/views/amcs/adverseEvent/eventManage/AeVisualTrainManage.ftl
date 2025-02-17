<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>视觉训练不良事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
 <style>
        .common-table .no-shadow{box-shadow: none;}
        .not-allow{
            background-color: #eeeeee;
            cursor: not-allowed;
        }
        .common-table {
            text-align: center;
            background-color: #e8f6fd;
            border:solid #d3d3d3;
            border-width:1px 0px 0px 1px;
            margin-left:50px;
        }.common-table td {
             height: 26px;
             border:solid #d3d3d3;
             border-width:0px 1px 1px 0px;
         }
        .common-table td input,.common-table td .textbox{
            border: 0;
        }
        .common-table>thead{font-weight: bold;}
        .common-table>thead, .common-table tr > td:first-child, .common-table .hasBG{
            color:#000000;
            background-color: #e8f6fd;
        }
        .common-table .textbox.numberbox,
        .common-table .textbox.combo,
        .common-table .textbox-text.validatebox-text.validatebox-readonly,
        .common-table .textbox-text.validatebox-text{width: 100% !important;}
        .common-table .item-group .textbox.numberbox{width: 50px !important;}
        .common-table input[type="text"]:focus{position: static;}
		.examination-form h4 { margin-bottom: 5px; }
        .examination-form .row { margin:0 0 10px 0; }
        .item-nomargin .lab-inline {  margin: 0; }
        .bottom-line { border-bottom: 5px solid #dfe2e6; }

        .cont-grid-fixL{position: relative;padding-left: 300px;}
        .cont-grid-fix-l{position: absolute;left:0;top:80px;bottom:0;width:350px;}
        .cont-grid-fix-l .area-a{position: absolute;top:0;left:0;bottom:0;right:0;}
        .cont-grid-fix-l .area-b{position: absolute;top:50%;left:0;bottom:0;right:0;}
        .datagrid-view-top {  position: absolute; top: 0; bottom: 50%; left: 0; right: 0; border-bottom: 5px solid #dfe2e6;  }
        .datagrid-view-bottom {  position: absolute; bottom: 0; top: 50%; left: 0; right: 0;  }
		.mask-historyCont{position: absolute;top:0px;left:0;right:0;bottom:0;background-color: #eee;filter:alpha(opacity=60);opacity:0.6;z-index:15;}
		.mask-hide{display: none;}
        .cont-grid-fix-r{position: absolute;left:350px;top:80px;bottom:0;right:0;}
        .width-fixed { width: 1130px; }

        .basicexam_table td {  padding: 5px 10px; }
        .patient_list { position: absolute; top: 20px;}
        .h2-form{overflow: hidden;}
        .h2-form .s-t{float: left;width:50%;text-indent:3.7em;font-size:1.18em;color:#00A0E9;}

        .item-one{padding-bottom:4px;}
        .lightPosBox{display:none;width:120px;height:120px;border-right:1px solid #eee;border-bottom: 1px solid #eee;}
        .colorBox{display:none;}
        .s-oc{display: -moz-inline-stack;display:inline-block;*display:inline;*zoom:1;width:39px;height:39px;text-align: center;line-height:39px;cursor:hand;cursor:pointer;font-size:1.5em;font-family:"Microsoft YaHei";font-weight: bold;}
        .s-oc:hover{color:#c00;}
        .lightPosBox .s-oc{float: left;border-left:1px solid #eee;border-top: 1px solid #eee;}
        .lightPosBox .s-oc:hover{background-color: #eee;}
        /*.row-colorF,.row-colorF .lab-item{line-height:42px;}*/
        .row-colorF .s-oc{line-height:20px;height:20px;}
        /*.s-sl-v{float: left;width:60%;}*/
        .em-dropAt{right:40px;top:0;}

        .eyePressItem{padding-bottom:8px;}
        .eyePressItem .item-one{height:26px;min-height:20px;overflow: hidden;padding-bottom:0;}
		
		.s-eyePressW-0{position:relative;}
		.s-eyePressWOs-0{position:relative;}
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
        <div class="mar-l20">
            <div style="font-size: 14px;" class="mar-b10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">患者建档时基本检查信息</span>
            </div>

            <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline">患者训练建档日期：</label>
					<input id="trainingFileDate" autocomplete="off" class="txt txt-validate so-date" type="text" style="width:100px" name="trainingFileDate" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写患者训练建档日期"  value='[#if ae.trainingFileDate??]${ae.trainingFileDate?date("yyyy-MM-dd")}[/#if]'  />
				</span>
                <span class="fl">
                    <label class="lab-inline">诊断：</label>
					[@ui_select id="diag"  class="easyui-combobox"  name="diag"  tag="@ae@diag_type" style="width:100%;"  dataOptions="limitToList:true,required:true,reversed:true,editable:false" filterkey="firstSpell" value="${ae.diag!}"/]
                </span>
                <span class="fl">
                    <label class="lab-inline">缴费次数：</label>
                    <input class="txt inline txt-validate wp-75 required" type="text" style="width:100px" id="payTimes" name="payTimes" noNull="请填写缴费次数"  data-options="required:true" placeholder="" value="${ae.payTimes!}" maxlength="2" validType="sInt['次数必须是正整数']" />
                </span>
                <span class="fl">
                    <label class="lab-inline">缴费费用：</label>
					<input  class="txt txt-change txt-validate required" type="text" id="payFee"  name="payFee" value="${ae.payFee!}" style="width:100px" data-options="precision:2,required:true" validType="range[0,1000000,'金额不在有效范围']" noNull="请填写缴费费用"/>
                </span>
                <span class="fl">
                    <label class="lab-inline">最后一次训练时间：</label>
                	<input id="lastTrainingDate" autocomplete="off" class="txt txt-validate so-date" type="text" style="width:100px" name="lastTrainingDate" dataOptions="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" noNull="请填写最后一次训练时间"  value="[#if ae.lastTrainingDate??]${ae.lastTrainingDate?date("yyyy-MM-dd")}[/#if]"  />
				</span>
            </div>

            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                裸眼远视力
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6"  style="font-size: 12px;">
                    <span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                    </span>
                    <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcLyyVodType" id="jcLyyVodType" value="${ae.jcLyyVodType}">
                       <option value="1" selected="selected">数值</option>
                       <option value="2">描述</option>
                      </select>
                    </span>
                    <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcLyyVod" name="jcLyyVod" value="${ae.jcLyyVod!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcLyyVodName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcLyyVodName" name="jcLyyVodName" value="${ae.jcLyyVodName}"/>
                    </span>
                </div>
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6"  style="font-size: 12px;">
                    <span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                    </span>
                    <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcLyyVosType" id="jcLyyVosType" value="${ae.jcLyyVosType}">
                       <option value="1" selected="selected">数值</option>
                       <option value="2">描述</option>
                      </select>
                    </span>
                    <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcLyyVos" name="jcLyyVos" value="${ae.jcLyyVos!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcLyyVosName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcLyyVosName" name="jcLyyVosName" value="${ae.jcLyyVosName}"/>
                    </span>
                </div>
            </div>
            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                裸眼近视力
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                    <span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                    </span>
                    <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcLyjVodType" id="jcLyjVodType" value="${ae.jcLyjVodType}">
                       <option value="1" selected="selected">数值</option>
                       <option value="2">描述</option>
                      </select>
                    </span>
                    <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcLyjVod" name="jcLyjVod" value="${ae.jcLyjVod}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcLyjVodName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcLyjVodName" name="jcLyjVodName" value="${ae.jcLyjVodName}"/>
                    </span>
                </div>
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                    <span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                    </span>
                    <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcLyjVosType" id="jcLyjVosType" value="${ae.jcLyjVosType}">
                       <option value="1" selected="selected">数值</option>
                       <option value="2">描述</option>
                      </select>
                    </span>
                    <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcLyjVos" name="jcLyjVos" value="${ae.jcLyjVos}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcLyjVosName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcLyjVosName" name="jcLyjVosName" value="${ae.jcLyjVosName}"/>
                    </span>
                </div>
            </div>




            <div style="font-size: 14px;" class="mar-l10 mar-b10 p6">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                屈光矫正
            </div>
            <div style="border: 1px dashed #cccccc;display: inline-block;padding-right: 20px;">
                <div style="display: inline-block;">
                    <p style="color: #00a0e9;font-size: 14px;" class="pad-l30">框架镜</p>
                    <div class="clearfix mar-l20 mar-b10 p6">
                        <span class="fl">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline">球镜：</label>
                                <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcQgjzKjjQjVod" id="jcQgjzKjjQjVod" value="${ae.jcQgjzKjjQjVod}" data-options="min:-70,max:70,precision:2" />
                            </span>
                        <span class="fl">
                            <label class="lab-inline">柱镜：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcQgjzKjjZjVod" id="jcQgjzKjjZjVod" value="${ae.jcQgjzKjjZjVod}" data-options="min:-20,max:20,precision:2" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline">轴位：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcQgjzKjjCwVod" id="jcQgjzKjjCwVod" value="${ae.jcQgjzKjjCwVod}" data-options="min:-20,max:20,precision:2" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline">矫正视力：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcQgjzKjjJzslVodType" id="jcQgjzKjjJzslVodType" value="${ae.jcQgjzKjjJzslVodType}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcQgjzKjjJzslVod" name="jcQgjzKjjJzslVod" value="${ae.jcQgjzKjjJzslVod}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#jcQgjzKjjJzslVodName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="jcQgjzKjjJzslVodName" name="jcQgjzKjjJzslVodName" value="${ae.jcQgjzKjjJzslVodName}"/>
						</span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10 p6">
                        <span class="fl">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline">球镜：</label>
                                <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcQgjzKjjQjVos" id="jcQgjzKjjQjVos" value="${ae.jcQgjzKjjQjVos}" data-options="min:-70,max:70,precision:2" />
                            </span>
                        <span class="fl">
                            <label class="lab-inline">柱镜：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcQgjzKjjZjVos" id="jcQgjzKjjZjVos" value="${ae.jcQgjzKjjZjVos}" data-options="min:-20,max:20,precision:2" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline">轴位：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcQgjzKjjCwVos" id="jcQgjzKjjCwVos" value="${ae.jcQgjzKjjCwVos}" data-options="min:-20,max:20,precision:2" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline">矫正视力：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcQgjzKjjJzslVosType" id="jcQgjzKjjJzslVosType" value="${ae.jcQgjzKjjJzslVosType}">
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcQgjzKjjJzslVos" name="jcQgjzKjjJzslVos" value="${ae.jcQgjzKjjJzslVos}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#jcQgjzKjjJzslVosName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="jcQgjzKjjJzslVosName" name="jcQgjzKjjJzslVosName" value="${ae.jcQgjzKjjJzslVosName}"/>
						</span>
                    </div>
                </div>
                <div style="display: inline-block;border-left: 1px dashed #cccccc;" class="mar-l20 mar-t10 mar-b10">
                    <p style="color: #00a0e9;font-size: 14px;" class="pad-l30">角膜塑形镜</p>
                    <div class="clearfix mar-l20 mar-b10 p6">
                        <span class="fl">
                            <label class="lab-inline fl">OD矫正视力：</label>
							<span class="fl w-80">
							    <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcJmsxjJzslVodType" id="jcJmsxjJzslVodType" value="${ae.jcJmsxjJzslVodType}">
							    <option value="1" selected="selected">数值</option>
							    <option value="2">描述</option>
							</select>
							</span>
							<span class="fl w-120">
								<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcJmsxjJzslVod" name="jcJmsxjJzslVod" value="${ae.jcJmsxjJzslVod}"
									data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
									var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
									var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
									return l || n;
								  },onSelect:function(record){
									$('#jcJmsxjJzslVodName').val(record.valueDesc);
								}">
							</select>
							<input class="txt" type="hidden" id="jcJmsxjJzslVodName" name="jcJmsxjJzslVodName" value="${ae.jcJmsxjJzslVodName}"/>
							</span>
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10 p6">
                        <span class="fl">
                            <label class="lab-inline fl">OS矫正视力：</label>
							<span class="fl w-80">
							  <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="jcJmsxjJzslVosType" id="jcJmsxjJzslVosType" value="${ae.jcJmsxjJzslVosType}">
							   <option value="1" selected="selected">数值</option>
							   <option value="2">描述</option>
							  </select>
							</span>
							<span class="fl w-120">
								<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcJmsxjJzslVos" name="jcJmsxjJzslVos" value="${ae.jcJmsxjJzslVos}"
									data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
									var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
									var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
									return l || n;
								  },onSelect:function(record){
									$('#jcJmsxjJzslVosName').val(record.valueDesc);
								}">
								</select>
								<input class="txt" type="hidden" id="jcJmsxjJzslVosName" name="jcJmsxjJzslVosName" value="${ae.jcJmsxjJzslVosName}"/>
							</span>
                        </span>
                    </div>
                </div>
            </div>

            <div style="font-size: 14px;" class="mar-l10 mar-b10 mar-t10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                双眼视功能(根据患者诊断填写尽可能完整)
            </div>
            <div style="border: 1px dashed #cccccc;display: inline-block;padding-right: 20px;padding-top: 20px;">
                <div class="clearfix mar-l20 mar-b10">
                    <span class="fl">
                        <label class="lab-inline">视远斜视度：</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcSysgnSyxsd" id="jcSysgnSyxsd" value="${ae.jcSysgnSyxsd}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">NRA：</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcSysgnNra" id="jcSysgnNra" value="${ae.jcSysgnNra}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">视近斜视度</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcSysgnSjxsd" id="jcSysgnSjxsd" value="${ae.jcSysgnSjxsd}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">BCC</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcSysgnBcc" id="jcSysgnBcc" value="${ae.jcSysgnBcc}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">AC/A</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcSysgnAca" id="jcSysgnAca" value="${ae.jcSysgnAca}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">PRA</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcSysgnPra" id="jcSysgnPra" value="${ae.jcSysgnPra}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">集合近点</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcW4dJhjd" id="jcW4dJhjd" value="${ae.jcW4dJhjd}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">立体视</label>
                        <input class="txt inline txt-validate" type="text" style="width:100px" name="jcW4dLts" id="jcW4dLts" value="${ae.jcW4dLts}" data-options="" />
                    </span>
                </div>
                <div style="display: inline-block;">
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline w-100" style="color:#00A0E9">调节幅度</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OD：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcDjfdOd" id="jcDjfdOd" value="${ae.jcDjfdOd}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OS：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcDjfdOs" id="jcDjfdOs" value="${ae.jcDjfdOs}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OU：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcDjfdOu" id="jcDjfdOu" value="${ae.jcDjfdOu}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline w-100" style="color:#00A0E9">调节灵敏度</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OD：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcDjlmdOd" id="jcDjlmdOd" value="${ae.jcDjlmdOd}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OS：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcDjlmdOs" id="jcDjlmdOs" value="${ae.jcDjlmdOs}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OU：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcDjlmdOu" id="jcDjlmdOu" value="${ae.jcDjlmdOu}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline w-100" style="color:#00A0E9">Worth 4 dots</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">33cm</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcW4d33cm" id="jcW4d33cm" value="${ae.jcW4d33cm}" data-options="" />
						</span>
                        <span class="fl">
                            <label class="lab-inline w-40">1m</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcW4d1m" id="jcW4d1m" value="${ae.jcW4d1m}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">3m</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcW4d3m" id="jcW4d3m" value="${ae.jcW4d3m}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">同视机检查</label>
                        </span>
                    </div>
                    <div class="clearfix mar-l40 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">一级</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90 text-right">主观斜视角</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcTsjjcYjZgxsj" id="jcTsjjcYjZgxsj" value="${ae.jcTsjjcYjZgxsj}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90">客观斜视角REF</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcTsjjcYjKgxsjref" id="jcTsjjcYjKgxsjref" value="${ae.jcTsjjcYjKgxsjref}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90">科幻斜视角LEF</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcTsjjcYjKgxsjlef" id="jcTsjjcYjKgxsjlef" value="${ae.jcTsjjcYjKgxsjlef}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l40 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">二级</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90 text-right">融合点</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcTsjjcEjRhd" id="jcTsjjcEjRhd" value="${ae.jcTsjjcEjRhd}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90">融像范围</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcTsjjcEjRxfw" id="jcTsjjcEjRxfw" value="${ae.jcTsjjcEjRxfw}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l40 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">三级</label>
                        </span>
                        <span class="fl pl-108">
							<input class="txt inline txt-validate" type="text" style="width:100px" name="jcTsjjcSj" id="jcTsjjcSj" value="${ae.jcTsjjcSj}" data-options="" />
                        </span>
                    </div>
                </div>

            </div>
            <div style="font-size: 14px;" class="mar-b10 mar-t10">
                <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                <span class="pad-l10">目前情况</span>
            </div>
            <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline">患者已训练次数：</label>
                    <input class="txt inline txt-validate wp-75 required" type="text" style="width:100px" id="mqqkYxlcs" name="mqqkYxlcs" noNull="请填写患者已训练次数" placeholder="" value="${ae.mqqkYxlcs}" maxlength="2" validType="sInt['次数必须是正整数']" />
                </span>
                <span class="fl">
                    <label class="lab-inline">剩余训练次数：</label>
                    <input class="txt inline txt-validate wp-75 required" type="text" style="width:100px" id="mqqkSyxlcs" name="mqqkSyxlcs" noNull="请填写剩余训练次数" placeholder="" value="${ae.mqqkSyxlcs}" maxlength="2" validType="sInt['次数必须是正整数']" />
                </span>
                <span class="fl">
                    <label class="lab-inline">训练频率：</label>
					[@ui_select id="mqqkXlpl"  class="easyui-combobox"  name="mqqkXlpl"  tag="@ae@train_frequency" style="width:100px;"  dataOptions="limitToList:true,required:true,reversed:true,editable:false" filterkey="firstSpell" value="${ae.mqqkXlpl}"/]
                </span>
            </div>
            <div style="font-size: 14px;" class="mar-l10 mar-b10 p6">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                最后一次复查结果
            </div>
            <div style="border: 1px dashed #cccccc;display: inline-block;padding-right: 20px;padding-top: 20px;">
                <div class="clearfix mar-l20 mar-b10">
                    <span class="fl">
                        <label class="lab-inline">视远斜视度：</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkSysgnSyxsd" id="mqqkSysgnSyxsd" value="${ae.mqqkSysgnSyxsd}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">NRA：</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkSysgnNra" id="mqqkSysgnNra" value="${ae.mqqkSysgnNra}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">视近斜视度</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkSysgnSjxsd" id="mqqkSysgnSjxsd" value="${ae.mqqkSysgnSjxsd}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">BCC</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkSysgnBcc" id="mqqkSysgnBcc" value="${ae.mqqkSysgnBcc}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">AC/A</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkSysgnAca" id="mqqkSysgnAca" value="${ae.mqqkSysgnAca}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">PRA</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkSysgnPra" id="mqqkSysgnPra" value="${ae.mqqkSysgnPra}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">集合近点</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkW4dJhjd" id="mqqkW4dJhjd" value="${ae.mqqkW4dJhjd}" data-options="" />
                    </span>
                    <span class="fl">
                        <label class="lab-inline">立体视</label>
						<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkW4dLts" id="mqqkW4dLts" value="${ae.mqqkW4dLts}" data-options="" />
                    </span>
                </div>
                <div style="display: inline-block;">
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl mar-b10 mar-t10">
                            <label class="lab-inline w-100" style="color:#00A0E9">裸眼远视力</label>
                        </span>
						<div class="clearfix mar-l10 mar-b10 mar-t10 p6 fl"  style="font-size: 12px;">
                        <span class="fl">
                            <label class="lab-inline w-40">OD：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="mqqkLyyVodType" id="mqqkLyyVodType" value="${ae.mqqkLyyVodType}">
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="mqqkLyyVod" name="mqqkLyyVod" value="${ae.mqqkLyyVod}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#mqqkLyyVodName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="mqqkLyyVodName" name="mqqkLyyVodName" value="${ae.mqqkLyyVodName}"/>
						</span>
						</div>
						<div class="clearfix mar-l10 mar-b10 mar-t10 p6 fl"  style="font-size: 12px;">
                        <span class="fl">
                            <label class="lab-inline w-40">OS：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="mqqkLyyVosType" id="mqqkLyyVosType" value="${ae.mqqkLyyVosType}">
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="mqqkLyyVos" name="mqqkLyyVos" value="${ae.mqqkLyyVos}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#mqqkLyyVosName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="mqqkLyyVosName" name="mqqkLyyVosName" value="${ae.mqqkLyyVosName}"/>
						</span>
						</div>
                    </div>
				</div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl mar-b10 mar-t10">
                            <label class="lab-inline w-100" style="color:#00A0E9">裸眼近视力</label>
                        </span>
						<div class="clearfix mar-l10 mar-b10 mar-t10 p6 fl"  style="font-size: 12px;">
                        <span class="fl">
                            <label class="lab-inline w-40">OD：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="mqqkLyjVodType" id="mqqkLyjVodType" value="${ae.mqqkLyjVodType}">
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="mqqkLyjVod" name="mqqkLyjVod" value="${ae.mqqkLyjVod}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#mqqkLyjVodName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="mqqkLyjVodName" name="mqqkLyjVodName" value="${ae.mqqkLyjVodName}"/>
						</span>
						</div>
						<div class="clearfix mar-l10 mar-b10 mar-t10 p6 fl"  style="font-size: 12px;">
                        <span class="fl">
                            <label class="lab-inline w-40">OS：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="mqqkLyjVosType" id="mqqkLyjVosType" value="${ae.mqqkLyjVosType}">
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="mqqkLyjVos" name="mqqkLyjVos" value="${ae.mqqkLyjVos}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#mqqkLyjVosName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="mqqkLyjVosName" name="mqqkLyjVosName" value="${ae.mqqkLyjVosName}"/>
						</span>
						</div>
                    </div>
                    <div style="display: inline-block;">
                        <p style="color: #00a0e9;font-size: 14px;" class="pad-l30">屈光检查</p>
                        <div class="clearfix mar-l20 mar-b10 p6">
                        <span class="fl mar-l20">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                        </span>
                            <span class="fl">
                            <label class="lab-inline">球镜：</label>
                                <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="mqqkQgjzKjjQjVod" id="mqqkQgjzKjjQjVod" value="${ae.mqqkQgjzKjjQjVod}" data-options="min:-70,max:70,precision:2" />
                            </span>
                            <span class="fl">
                            <label class="lab-inline">柱镜：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="mqqkQgjzKjjZjVod" id="mqqkQgjzKjjZjVod" value="${ae.mqqkQgjzKjjZjVod}" data-options="min:-20,max:20,precision:2" />
                        </span>
                            <span class="fl">
                            <label class="lab-inline">轴位：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="mqqkQgjzKjjCwVod" id="mqqkQgjzKjjCwVod" value="${ae.mqqkQgjzKjjCwVod}" data-options="min:-20,max:20,precision:2" />
                        </span>
                            <span class="fl">
                            <label class="lab-inline">矫正视力：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="mqqkQgjzKjjJzslVodType" id="mqqkQgjzKjjJzslVodType" value="${ae.mqqkQgjzKjjJzslVodType}" > 
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="mqqkQgjzKjjJzslVod" name="mqqkQgjzKjjJzslVod" value="${ae.mqqkQgjzKjjJzslVod}" 
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#mqqkQgjzKjjJzslVodName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="mqqkQgjzKjjJzslVodName" name="mqqkQgjzKjjJzslVodName" value="${ae.mqqkQgjzKjjJzslVodName}" />
						</span>
                        </div>
                        <div class="clearfix mar-l20 mar-b10 p6">
                        <span class="fl mar-l20">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                        </span>
                            <span class="fl">
                            <label class="lab-inline">球镜：</label>
                                <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="mqqkQgjzKjjQjVos" id="mqqkQgjzKjjQjVos" value="${ae.mqqkQgjzKjjQjVos}" data-options="min:-70,max:70,precision:2" />
                            </span>
                            <span class="fl">
                            <label class="lab-inline">柱镜：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="mqqkQgjzKjjZjVos" id="mqqkQgjzKjjZjVos" value="${ae.mqqkQgjzKjjZjVos}" data-options="min:-20,max:20,precision:2" />
                        </span>
                            <span class="fl">
                            <label class="lab-inline">轴位：</label>
                            <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="mqqkQgjzKjjCwVos" id="mqqkQgjzKjjCwVos" value="${ae.mqqkQgjzKjjCwVos}" data-options="min:-20,max:20,precision:2" />
                        </span>
                            <span class="fl">
                            <label class="lab-inline">矫正视力：</label>
                        </span>
						<span class="fl w-80">
							<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="mqqkQgjzKjjJzslVosType" id="mqqkQgjzKjjJzslVosType" value="${ae.mqqkQgjzKjjJzslVosType}">
								<option value="1" selected="selected">数值</option>
								<option value="2">描述</option>
							</select>
						</span>
						<span class="fl w-120">
							<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="mqqkQgjzKjjJzslVos" name="mqqkQgjzKjjJzslVos" value="${ae.mqqkQgjzKjjJzslVos}"
								data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
								var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
								var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
								return l || n;
							  },onSelect:function(record){
								$('#mqqkQgjzKjjJzslVosName').val(record.valueDesc);
							}">
							</select>
							<input class="txt" type="hidden" id="mqqkQgjzKjjJzslVosName" name="mqqkQgjzKjjJzslVosName" value="${ae.mqqkQgjzKjjJzslVosName}"/>
						</span>
                        </div>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline w-100" style="color:#00A0E9">调节幅度</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OD：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkDjfdOd" id="mqqkDjfdOd" value="${ae.mqqkDjfdOd}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OS：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkDjfdOs" id="mqqkDjfdOs" value="${ae.mqqkDjfdOs}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OU：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkDjfdOu" id="mqqkDjfdOu" value="${ae.mqqkDjfdOu}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline w-100" style="color:#00A0E9">调节灵敏度</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OD：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkDjlmdOd" id="mqqkDjlmdOd" value="${ae.mqqkDjlmdOd}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OS：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkDjlmdOs" id="mqqkDjlmdOs" value="${ae.mqqkDjlmdOs}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">OU：</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkDjlmdOu" id="mqqkDjlmdOu" value="${ae.mqqkDjlmdOu}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline w-100" style="color:#00A0E9">Worth 4 dots</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">33cm</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkW4d33cm" id="mqqkW4d33cm" value="${ae.mqqkW4d33cm}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">1m</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkW4d1m" id="mqqkW4d1m" value="${ae.mqqkW4d1m}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-40">3m</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkW4d3m" id="mqqkW4d3m" value="${ae.mqqkW4d3m}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">同视机检查</label>
                        </span>
                    </div>
                    <div class="clearfix mar-l40 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">一级</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90 text-right">主观斜视角</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkTsjjcYjZgxsj" id="mqqkTsjjcYjZgxsj" value="${ae.mqqkTsjjcYjZgxsj}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90">客观斜视角REF</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkTsjjcYjKgxsjref" id="mqqkTsjjcYjKgxsjref" value="${ae.mqqkTsjjcYjKgxsjref}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90">科幻斜视角LEF</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkTsjjcYjKgxsjlef" id="mqqkTsjjcYjKgxsjlef" value="${ae.mqqkTsjjcYjKgxsjlef}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l40 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">二级</label>
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90 text-right">融合点</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkTsjjcEjRhd" id="mqqkTsjjcEjRhd" value="${ae.mqqkTsjjcEjRhd}" data-options="" />
                        </span>
                        <span class="fl">
                            <label class="lab-inline w-90">融像范围</label>
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkTsjjcEjRxfw" id="mqqkTsjjcEjRxfw" value="${ae.mqqkTsjjcEjRxfw}" data-options="" />
                        </span>
                    </div>
                    <div class="clearfix mar-l40 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="color:#00A0E9">三级</label>
                        </span>
                        <span class="fl pl-108">
							<input class="txt inline txt-validate" type="text" style="width:100px" name="mqqkTsjjcSj" id="mqqkTsjjcSj" value="${ae.mqqkTsjjcSj}" data-options="" />
                        </span>
                    </div>
                </div>
            </div>
            <div class="clearfix mar-l20 mar-b10 mar-t10">
                <span class="fl">
                    <label class="lab-inline  w-60">患者及家属诉求：</label>
					<textarea class="txt txt-validate adaptiveTextarea"  id="patientAppeals" style="width:1000px" name="patientAppeals" maxlength="600" cols="200" row="10">${ae.patientAppeals}</textarea>
                </span>
            </div>
	        <div class="clearfix mar-l20 mar-b10">
	            <span class="fl">
	              <label class="lab-inline w-60">事件经过：</label>
	              <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" required="true" noNull="必填" id="procDescr" name="procDescr" maxlength="1300" cols="200" row="10">${ae.procDescr!}</textarea>
	          	</span>
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
                    <label class="lab-inline bold w-150">诊断不当</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason1" value="1诊断不当:::诊断不当" /> 诊断不当</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">训练内容不当相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason2" value="2训练内容不当相关:::产品使用不当" /> 产品使用不当</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason2" value="2训练内容不当相关:::训练师操作不当" /> 训练师操作不当</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">患者依从度不当相关</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason3" value="3患者依从度不当相关:::日常未戴镜" /> 日常未戴镜</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason3" value="3患者依从度不当相关:::日常未遮盖" /> 日常未遮盖</label>
					<label class="lab-val"><input type="radio" class="rad reason" name="reason3" value="3患者依从度不当相关:::训练频率低" /> 训练频率低</label>
                    <label class="lab-val"><input type="radio" class="rad reason" name="reason3" value="3患者依从度不当相关:::训练不认真" /> 训练不认真</label>
                </span>
                </div>
            </div>
            <div class="row mar-t15">
                <div class="p12">
                <span class="fl">
                    <label class="lab-inline bold w-150">其它</label>
                    <input class="txt txt-validate w-490 reasontxt" type="text" name="reason4" value=""/>
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
				<textarea   class="txt txt-validate required adaptiveTextarea"  type="text" name="opinion" placeholder=""  maxlength="600" cols="200">${ae.opinion!}</textarea>
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
    requirejs(["eventReview", "eventTableGroup","eventFlowchart","uploadImages","eventImage",'myupload','pub'], function (eventReview, eventTableGroup,eventFlowchart,uploadImages,eventImage) {
		[#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
		[#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
		[#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]
		
	    /**
     	* 从接口获取数据后表单赋值
    	**/
        window.afterSerachPatient=function (){
            //门诊接口数据
            var opDomain=window.opDomain;

			$('#jcLyyVod').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.scdOd:"")
            $('#jcLyyVos').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.scdOs:"")
			
			$('#jcLyjVod').setVal(!!opDomain.nvaOd?opDomain.nvaOd:"")
            $('#jcLyjVos').setVal(!!opDomain.nvaOs?opDomain.nvaOs:"")
			
			$('#jcQgjzKjjJzslVod').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.dvaOd:"")
            $('#jcQgjzKjjJzslVos').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.dvaOs:"")
        }
		
		//暂存前执行事件 false中断保存
		window.beforeStash = function(){
			compositionData()
			return true
		}

		//保存前执行事件 false中断保存
		window.beforeSubmit = function(){
			compositionData()
			return true;
		}
		//组装数据
		const compositionData=(()=>{
			$("#reason").setVal(getRadioFormVal())
			console.log("组装数据")
		})
		
		$('#common_reason').remove();
		$('#common_opinion').remove();
   

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

            $('#jcLyyVodType').setVal('${ae.jcLyyVodType!}')
            $('#jcLyyVod').setVal('${ae.jcLyyVod!}')
            $('#jcLyyVodName').setVal('${ae.jcLyyVodName!}')
            $('#jcLyyVosType').setVal('${ae.jcLyyVosType!}')
			$('#jcLyyVos').setVal('${ae.jcLyyVos!}')
            $('#jcLyyVosName').setVal('${ae.jcLyyVosName!}')

            $('#jcLyjVodType').setVal('${ae.jcLyjVodType!}')
            $('#jcLyjVod').setVal('${ae.jcLyjVod!}')
            $('#jcLyjVodName').setVal('${ae.jcLyjVodName!}')
            $('#jcLyjVosType').setVal('${ae.jcLyjVosType!}')
            $('#jcLyjVos').setVal('${ae.jcLyjVos!}')
            $('#jcLyjVosName').setVal('${ae.jcLyjVosName!}')
			
			$('#jcQgjzKjjJzslVodType').setVal('${ae.jcQgjzKjjJzslVodType!}')
            $('#jcQgjzKjjJzslVod').setVal('${ae.jcQgjzKjjJzslVod!}')
            $('#jcQgjzKjjJzslVodName').setVal('${ae.jcQgjzKjjJzslVodName!}')
            $('#jcQgjzKjjJzslVosType').setVal('${ae.jcQgjzKjjJzslVosType!}')
            $('#jcQgjzKjjJzslVos').setVal('${ae.jcQgjzKjjJzslVos!}')
            $('#jcQgjzKjjJzslVosName').setVal('${ae.jcQgjzKjjJzslVosName!}')
			
            $('#jcJmsxjJzslVodType').setVal('${ae.jcJmsxjJzslVodType!}')
            $('#jcJmsxjJzslVod').setVal('${ae.jcJmsxjJzslVod!}')
            $('#jcJmsxjJzslVodName').setVal('${ae.jcJmsxjJzslVodName!}')
            $('#jcJmsxjJzslVosType').setVal('${ae.jcJmsxjJzslVosType!}')
            $('#jcJmsxjJzslVos').setVal('${ae.jcJmsxjJzslVos!}')
            $('#jcJmsxjJzslVosName').setVal('${ae.jcJmsxjJzslVosName!}')

            $('#mqqkLyyVodType').setVal('${ae.mqqkLyyVodType!}')
            $('#mqqkLyyVod').setVal('${ae.mqqkLyyVod!}')
            $('#mqqkLyyVodName').setVal('${ae.mqqkLyyVodName!}')
            $('#mqqkLyyVosType').setVal('${ae.mqqkLyyVosType!}')
            $('#mqqkLyyVos').setVal('${ae.mqqkLyyVos!}')
            $('#mqqkLyyVosName').setVal('${ae.mqqkLyyVosName!}')
			
			$('#mqqkLyjVodType').setVal('${ae.mqqkLyjVodType!}')
            $('#mqqkLyjVod').setVal('${ae.mqqkLyjVod!}')
            $('#mqqkLyjVodName').setVal('${ae.mqqkLyjVodName!}')
            $('#mqqkLyjVosType').setVal('${ae.mqqkLyjVosType!}')
            $('#mqqkLyjVos').setVal('${ae.mqqkLyjVos!}')
            $('#mqqkLyjVosName').setVal('${ae.mqqkLyjVosName!}')

			$('#mqqkQgjzKjjJzslVodType').setVal('${ae.mqqkQgjzKjjJzslVodType!}')
            $('#mqqkQgjzKjjJzslVod').setVal('${ae.mqqkQgjzKjjJzslVod!}')
            $('#mqqkQgjzKjjJzslVodName').setVal('${ae.mqqkQgjzKjjJzslVodName!}')
            $('#mqqkQgjzKjjJzslVosType').setVal('${ae.mqqkQgjzKjjJzslVosType!}')
            $('#mqqkQgjzKjjJzslVos').setVal('${ae.mqqkQgjzKjjJzslVos!}')
            $('#mqqkQgjzKjjJzslVosName').setVal('${ae.mqqkQgjzKjjJzslVosName!}')

        }

		var baseExam = {
			init : function (){
				setRadioView('${ae.reason}');
				var me = this;
				$('.farEyesight').combobox('loadData',farEyesight[0]);
				me.farVisionFn();//远视力切换数据
				setValue();
			},
			farVisionFn : function (){//远视力切换数据
				$('.drop-sl-k').combobox({
					onChange : function (val){
						if (!val){
							$(this).combobox('setValue','1');//值空
						}
						else{
							var $v = $(this).parents('.p6').find('.drop-sl-v');
							$v.combobox('setValue','');//值空
							$v.combobox('loadData',farEyesight[val-1]);//loadData
						}
					}
				});
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
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>框架眼镜不良事件</title>
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
                <span class="s-title">事件经过和处理结果</span>
            </h2>
            <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

            <div class="mar-l20">
                <div style="font-size: 14px;" class="mar-b10">
                  <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
                  <span class="pad-l10">患者基本检查信息</span>
                </div>
              <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                裸眼远视力
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                    <span class="fl">
                      <label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                    </span>
                    <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="nakedFarVodType" id="nakedFarVodType" value="${ae.nakedFarVodType!}">
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
						}">
						</select>
						<input class="txt" type="hidden" id="nakedFarVodName" name="nakedFarVodName" value="${ae.nakedFarVodName!}"/>
                    </span>
                </div>
                <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
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
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="nakedFarVos" name="nakedFarVos" value="${ae.nakedFarVos!}"
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
                        <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="iopTypeOd" id="iopTypeOd" value="${ae.iopTypeOd!}" data-options="valueField:'valueCode',textField:'valueDesc'">
                        </select>
                    </div>
                    <div class="s-eyePressW-0 fl">
                        <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="iopOd" id="iopOd1" value="${ae.iopOd!}" data-options="min:1,max:100,precision:1" />
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="s-eyePressW-1 fl">
						<select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="iopOd" id="iopOd2" value="${ae.iopOd!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#iopOdName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="iopOdName" name="iopOdName" value="${ae.iopOdName!}"/>
                    </div>
                </div>
                <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
                <span class="fl">
                    <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                </span>
					<div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="iopTypeOs" id="iopTypeOs" value="${ae.iopTypeOs!}" data-options="valueField:'valueCode',textField:'valueDesc'">
                        </select>
                    </div>
                    <div class="s-eyePressWOs-0 fl">
                        <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px" name="iopOs" id="iopOs1" value="${ae.iopOs!}" data-options="min:1,max:100,precision:1" />
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="s-eyePressWOs-1 fl"> 
						<select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px" name="iopOs" id="iopOs2" value="${ae.iopOs!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#iopOsName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="iopOsName" name="iopOsName" value="${ae.iopOsName!}"/>
                    </div>
                </div>
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
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzOd" id="jcJmpkzOd" value="${ae.jcJmpkzOd!}" data-options="min:0,max:70,precision:2" />
						@
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzwOd" id="jcJmpkzwOd" value="${ae.jcJmpkzwOd!}" data-options="min:0,max:180,precision:2" />
					</span>
                </div>
                <div class="clearfix mar-l10 mar-b10" style="font-size: 12px;">
					<span class="fl">
                        <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                    </span>
					<span class="fl">
						<label class="lab-inline">值：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzOs" id="jcJmpkzOs" value="${ae.jcJmpkzOs!}" data-options="min:0,max:70,precision:2" />
						@
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:40%" name="jcJmpkzwOs" id="jcJmpkzwOs" value="${ae.jcJmpkzwOs!}" data-options="min:0,max:180,precision:2" />
                    </span>
                </div>
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
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgQjOd" id="jcYgQjOd" value="${ae.jcYgQjOd!}" data-options="min:-70,max:70,precision:2" />
					</span>
                    <span class="fl">
						<label class="lab-inline">柱镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgZjOd" id="jcYgZjOd" value="${ae.jcYgZjOd!}" data-options="min:-20,max:20,precision:2" />
					</span>
					<span class="fl">
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgCwOd" id="jcYgCwOd" value="${ae.jcYgCwOd!}" data-options="min:0,max:180,precision:0" />
					</span>
					<span class="fl">
						<label class="lab-inline">矫正视力：</label>
					</span>
					<span class="fl">
						<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="jcYgJzslOdType" id="jcYgJzslOdType" value="${ae.jcYgJzslOdType!}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
					<span class="s-sl-v fl">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcYgJzslOd" name="jcYgJzslOd" value="${ae.jcYgJzslOd!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcYgJzslOdName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcYgJzslOdName" name="jcYgJzslOdName" value="${ae.jcYgJzslOdName!}"/>
					</span>
                </div>
                <div class="clearfix mar-l20 mar-b10 p6">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
					</span>
					<span class="fl">
						<label class="lab-inline">球镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgQjOs" id="jcYgQjOs" value="${ae.jcYgQjOs!}" data-options="min:-70,max:70,precision:2" />
					</span>
                    <span class="fl">
						<label class="lab-inline">柱镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgZjOs" id="jcYgZjOs" value="${ae.jcYgZjOs!}" data-options="min:-20,max:20,precision:2" />
					</span>
					<span class="fl">
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcYgCwOs" id="jcYgCwOs" value="${ae.jcYgCwOs!}" data-options="min:0,max:180,precision:0" />
					</span>
                    <span class="fl">
						<label class="lab-inline">矫正视力：</label>
					</span>
					<span class="fl">
						<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="jcYgJzslOsType" id="jcYgJzslOsType" value="${ae.jcYgJzslOsType!}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
					<span class="s-sl-v fl">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcYgJzslOs" name="jcYgJzslOs" value="${ae.jcYgJzslOs!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcYgJzslOsName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcYgJzslOsName" name="jcYgJzslOsName" value="${ae.jcYgJzslOsName!}"/>
					</span>
                </div>
				<div style="font-size: 14px" class="mar-l10 mar-b10">
					<span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
					配镜处方
				</div>
                <div class="clearfix mar-l20 mar-b10 p6">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
					</span>
					<span class="fl">
						<label class="lab-inline">球镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcPjcfQjOd" id="jcPjcfQjOd" value="${ae.jcPjcfQjOd!}" data-options="min:-70,max:70,precision:2" />				
					</span>
					<span class="fl">
						<label class="lab-inline">柱镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcPjcfZjOd" id="jcPjcfZjOd" value="${ae.jcPjcfZjOd!}" data-options="min:-20,max:20,precision:2" />
					</span>
					<span class="fl">
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcPjcfCwOd" id="jcPjcfCwOd" value="${ae.jcPjcfCwOd!}" data-options="min:0,max:180,precision:0" />
					</span>
					<span class="fl">
						<label class="lab-inline">矫正视力：</label>
					</span>
					<span class="fl">
						<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="jcPjcfJzslOdType" id="jcPjcfJzslOdType" value="${ae.jcPjcfJzslOdType!}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
					<span class="s-sl-v fl">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcPjcfJzslOd" name="jcPjcfJzslOd" value="${ae.jcPjcfJzslOd!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcPjcfJzslOdName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcPjcfJzslOdName" name="jcPjcfJzslOdName" value="${ae.jcPjcfJzslOdName!}"/>
					</span>
					<span class="fl">
						<label class="lab-inline">下加光：</label>
						<input class="txt txt-validate w-120" type="text" name="jcPjcfXjgOd" id="jcPjcfXjgOd" value="${ae.jcPjcfXjgOd!}"/>
					</span>
					<span class="fl">
						<label class="lab-inline">单侧瞳距(mm)：</label>
						<input class="txt txt-validate w-120" type="text" name="jcPjcfDctjOd" id="jcPjcfDctjOd" value="${ae.jcPjcfDctjOd!}"/>
					</span>
              </div>
              <div class="clearfix mar-l20 mar-b10 p6">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
					</span>
					<span class="fl">
						<label class="lab-inline">球镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcPjcfQjOs" id="jcPjcfQjOs" value="${ae.jcPjcfQjOs!}" data-options="min:-70,max:70,precision:2" />
					</span>
					<span class="fl">
						<label class="lab-inline">柱镜：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcPjcfZjOs" id="jcPjcfZjOs" value="${ae.jcPjcfZjOs!}" data-options="min:-20,max:20,precision:2" />
					</span>
					<span class="fl">
						<label class="lab-inline">轴位：</label>
						<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="jcPjcfCwOs" id="jcPjcfCwOs" value="${ae.jcPjcfCwOs!}" data-options="min:0,max:180,precision:0" />
					</span>
					<span class="fl">
						<label class="lab-inline">矫正视力：</label>
					</span>
					<span class="fl">
						<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="jcPjcfJzslOsType" id="jcPjcfJzslOsType" value="${ae.jcPjcfJzslOsType!}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
					<span class="s-sl-v fl">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="jcPjcfJzslOs" name="jcPjcfJzslOs" value="${ae.jcPjcfJzslOs!}"
							data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#jcPjcfJzslOsName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="jcPjcfJzslOsName" name="jcPjcfJzslOsName" value="${ae.jcPjcfJzslOsName!}"/>
					</span>
					<span class="fl">
						<label class="lab-inline">下加光：</label>
						<input class="txt txt-validate w-120" type="text" name="jcPjcfXjgOs" id="jcPjcfXjgOs" value="${ae.jcPjcfXjgOs!}"/>
					</span>
					<span class="fl">
						<label class="lab-inline">单侧瞳距(mm)：</label>
						<input class="txt txt-validate w-120" type="text" name="jcPjcfDctjOs" id="jcPjcfDctjOs" value="${ae.jcPjcfDctjOs!}"/>
					</span>
              </div>
              <div style="font-size: 14px" class="mar-l10 mar-b10">
					<span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
					配镜参数及信息
              </div>
              <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">R</label>
                        </span>
                <span class="fl">
                            <label class="lab-inline">瞳高(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsTgR" id="jcPjcsTgR" value="${ae.jcPjcsTgR!}"/>
                        </span>
                <span class="fl">
                            <label class="lab-inline">镜片类型：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsJplxR" id="jcPjcsJplxR" value="${ae.jcPjcsJplxR!}"/>
                        </span>
                <span class="fl">
                            <label class="lab-inline">镜片直径(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsJpzjR" id="jcPjcsJpzjR" value="${ae.jcPjcsJpzjR!}"/>
                        </span>
                <span class="fl">
                            <label class="lab-inline">镜架尺寸：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsJjccR" id="jcPjcsJjccR" value="${ae.jcPjcsJjccR!}"/>
                        </span>
              </div>
              <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">L</label>
                        </span>
                <span class="fl">
                            <label class="lab-inline">瞳高(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsTgL" id="jcPjcsTgL" value="${ae.jcPjcsTgL!}"/>
                        </span>
                <span class="fl">
                            <label class="lab-inline">镜片类型：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsJplxL" id="jcPjcsJplxL" value="${ae.jcPjcsJplxL!}"/>
                        </span>
                <span class="fl">
                            <label class="lab-inline">镜片直径(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsJpzjL" id="jcPjcsJpzjL" value="${ae.jcPjcsJpzjL!}"/>
                        </span>
                <span class="fl">
                            <label class="lab-inline">镜架尺寸：</label>
                            <input class="txt txt-validate w-120" type="text" name="jcPjcsJjccL" id="jcPjcsJjccL" value="${ae.jcPjcsJjccL!}"/>
                        </span>
              </div>
            </div>

      <div class="mar-l20">
        <div style="font-size: 14px;" class="mar-b10">
          <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
          <span class="pad-l10">患者投诉原因</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
          <span class="fl">
              <label class="lab-inline">事件起因：</label>
              [@ui_select id="eventCause" noNull="必填"  class="easyui-combobox"  name="eventCause"  tag="@ae@event_cause" style="width:100%;"  dataOptions="limitToList : true,required:true,reversed:true,editable:false" filterkey="firstSpell" value="${ae.eventCause!}"/]
          </span>
          <span class="fl">
              <label class="lab-inline">事件的结果：</label>
              [@ui_select id="eventResult" noNull="必填" class="easyui-combobox"  name="eventResult"  tag="@ae@event_result" style="width:100%;"  dataOptions="limitToList : true,required:true,reversed:true,editable:false" filterkey="firstSpell" value="${ae.eventResult!}"/]
          </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
              <label class="lab-inline w-60">患者及家长诉求：</label>
              <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" required="true" noNull="必填" id="patientAppeal" name="patientAppeal" maxlength="600" cols="200" row="10">${ae.patientAppeal!}</textarea>
          	</span>
        </div>
      </div>

      <div class="mar-l20">
        <div style="font-size: 14px;" class="mar-b10">
          <i class="icon icon-wuliuxinxi" style="color: #00a0e9;font-weight: bold;"></i>
          <span class="pad-l10">处理过程</span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
            <span class="fl">
              <label class="lab-inline w-60">事件经过：</label>
              <textarea class="txt txt-validate adaptiveTextarea" style="width:1000px" required="true" noNull="必填" id="procDescr" name="procDescr" maxlength="1300" cols="200" row="10">${ae.procDescr!}</textarea>
          	</span>
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
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clYgQjOd" id="clYgQjOd" value="${ae.clYgQjOd!}" data-options="min:-70,max:70,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">柱镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clYgZjOd" id="clYgZjOd" value="${ae.clYgZjOd!}" data-options="min:-20,max:20,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">轴位：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clYgCwOd" id="clYgCwOd" value="${ae.clYgCwOd!}" data-options="min:0,max:180,precision:0" />
			</span>
			<span class="fl">
				<label class="lab-inline">矫正视力：</label>
			</span>
			<span class="fl">
				<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="clYgJzslOdType" id="clYgJzslOdType" value="${ae.clYgJzslOdType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
			<span class="s-sl-v fl">
				<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="clYgJzslOd" name="clYgJzslOd" value="${ae.clYgJzslOd!}"
					data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#clYgJzslOdName').val(record.valueDesc);
				}">
				</select>
				<input class="txt" type="hidden" id="clYgJzslOdName" name="clYgJzslOdName" value="${ae.clYgJzslOdName!}"/>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10 p6">
			<span class="fl">
				<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
			</span>
			<span class="fl">
				<label class="lab-inline">球镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clYgQjOs" id="clYgQjOs" value="${ae.clYgQjOs!}" data-options="min:-70,max:70,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">柱镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clYgZjOs" id="clYgZjOs" value="${ae.clYgZjOs!}" data-options="min:-20,max:20,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">轴位：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clYgCwOs" id="clYgCwOs" value="${ae.clYgCwOs!}" data-options="min:0,max:180,precision:0" />
			</span>
			<span class="fl">
				<label class="lab-inline">矫正视力：</label>
			</span>
			<span class="fl">
				<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="clYgJzslOsType" id="clYgJzslOsType" value="${ae.clYgJzslOsType!}" >
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
			<span class="s-sl-v fl">
				<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="clYgJzslOs" name="clYgJzslOs" value="${ae.clYgJzslOs!}" 
					data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#clYgJzslOsName').val(record.valueDesc);
				}">
				</select>
				<input class="txt" type="hidden" id="clYgJzslOsName" name="clYgJzslOsName" value="${ae.clYgJzslOsName!}" />
			</span>
        </div>
        <div style="font-size: 14px" class="mar-l10 mar-b10">
          <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
          配镜处方
        </div>
        <div class="clearfix mar-l20 mar-b10 p6">
			<span class="fl">
				<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
			</span>
			<span class="fl">
				<label class="lab-inline">球镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clPjcfQjOd" id="clPjcfQjOd" value="${ae.clPjcfQjOd!}" data-options="min:-70,max:70,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">柱镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clPjcfZjOd" id="clPjcfZjOd" value="${ae.clPjcfZjOd!}" data-options="min:-20,max:20,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">轴位：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clPjcfCwOd" id="clPjcfCwOd" value="${ae.clPjcfCwOd!}" data-options="min:0,max:180,precision:0" />
			</span>
			<span class="fl">
				<label class="lab-inline">矫正视力：</label>
			</span>
			<span class="fl">
				<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="clPjcfJzslOdType" id="clPjcfJzslOdType" value="${ae.clPjcfJzslOdType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
			<span class="s-sl-v fl">
				<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="clPjcfJzslOd" name="clPjcfJzslOd" value="${ae.clPjcfJzslOd!}"
					data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#clPjcfJzslOdName').val(record.valueDesc);
				}">
				</select>
				<input class="txt" type="hidden" id="clPjcfJzslOdName" name="clPjcfJzslOdName" value="${ae.clPjcfJzslOdName!}"/>
			</span>
			<span class="fl">
				<label class="lab-inline">下加光：</label>
				<input class="txt txt-validate w-120" type="text" name="clPjcfXjgOd" value="${ae.clPjcfXjgOd!}"/>
			</span>
			<span class="fl">
				<label class="lab-inline">单侧瞳距(mm)：</label>
				<input class="txt txt-validate w-120" type="text" name="clPjcfDctjOd" value="${ae.clPjcfDctjOd!}"/>
			</span>
        </div>
        <div class="clearfix mar-l20 mar-b10 p6">
			<span class="fl">
				<label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
			</span>
            <span class="fl">
			<label class="lab-inline">球镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clPjcfQjOs" id="clPjcfQjOs" value="${ae.clPjcfQjOs!}" data-options="min:-70,max:70,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">柱镜：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clPjcfZjOs" id="clPjcfZjOs" value="${ae.clPjcfZjOs!}" data-options="min:-20,max:20,precision:2" />
			</span>
			<span class="fl">
				<label class="lab-inline">轴位：</label>
				<input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100px" name="clPjcfCwOs" id="clPjcfCwOs" value="${ae.clPjcfCwOs!}" data-options="min:0,max:180,precision:0" />
			</span>
			<span class="fl">
				<label class="lab-inline">矫正视力：</label>
			</span>
			<span class="fl">
				<select class="drop drop-sl-k easyui-combobox" style="width:100px" name="clPjcfJzslOsType" id="clPjcfJzslOsType" value="${ae.clPjcfJzslOsType!}">
					<option value="1" selected="selected">数值</option>
					<option value="2">描述</option>
				</select>
			</span>
			<span class="s-sl-v fl">
				<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="clPjcfJzslOs" name="clPjcfJzslOs" value="${ae.clPjcfJzslOs!}"
					data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
					var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
					var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
					return l || n;
				  },onSelect:function(record){
					$('#clPjcfJzslOsName').val(record.valueDesc);
				}">
				</select>
				<input class="txt" type="hidden" id="clPjcfJzslOsName" name="clPjcfJzslOsName" value="${ae.clPjcfJzslOsName!}"/>
			</span>
			<span class="fl">
				<label class="lab-inline">下加光：</label>
				<input class="txt txt-validate w-120" type="text" name="clPjcfXjgOs" value="${ae.clPjcfXjgOs!}"/>
			</span>
            <span class="fl">
				<label class="lab-inline">单侧瞳距(mm)：</label>
				<input class="txt txt-validate w-120" type="text" name="clPjcfDctjOs" value="${ae.clPjcfDctjOs!}"/>
			</span>
        </div>
        <div style="font-size: 14px" class="mar-l10 mar-b10">
          <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
          配镜参数及信息
        </div>
        <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">R</label>
                        </span>
          <span class="fl">
                            <label class="lab-inline">瞳高(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsTgR" value="${ae.clPjcsTgR!}"/>
                        </span>
          <span class="fl">
                            <label class="lab-inline">镜片类型：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsJplxR" value="${ae.clPjcsJplxR!}"/>
                        </span>
          <span class="fl">
                            <label class="lab-inline">镜片直径(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsJpzjR" value="${ae.clPjcsJpzjR!}"/>
                        </span>
          <span class="fl">
                            <label class="lab-inline">镜架尺寸：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsJjccR" value="${ae.clPjcsJjccR!}"/>
                        </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
                        <span class="fl">
                            <label class="lab-inline" style="width: 20px;color:#00A0E9">L</label>
                        </span>
          <span class="fl">
                            <label class="lab-inline">瞳高(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsTgL" value="${ae.clPjcsTgL!}"/>
                        </span>
          <span class="fl">
                            <label class="lab-inline">镜片类型：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsJplxL" value="${ae.clPjcsJplxL!}"/>
                        </span>
          <span class="fl">
                            <label class="lab-inline">镜片直径(mm)：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsJpzjL" value="${ae.clPjcsJpzjL!}"/>
                        </span>
          <span class="fl">
                            <label class="lab-inline">镜架尺寸：</label>
                            <input class="txt txt-validate w-120" type="text" name="clPjcsJjccL" value="${ae.clPjcsJjccL!}"/>
                        </span>
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
			<!--
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
            -->
			<div class="row mar-t15">
				<div class="p12">
					<span class="fl">
						<label class="lab-inline bold w-80">患方</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason11" value="11患方:::沟通原因" /> 沟通原因</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason11" value="11患方:::价格原因" /> 价格原因</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason11" value="11患方:::使用原因" /> 使用原因</label>
					</span>
				</div>
			</div>
			<div class="row mar-t15">
				<div class="p12">
					<span class="fl">
						<label class="lab-inline bold w-80" id="slable12" onClick="(function (){
							$pop.tips(`镜片质量(顶焦度超标、配戴不适、光心偏、镜片直径、柱镜轴位超标、镜片外观、镜片膜色、中心厚度不合格、包装袋标识)<br/>
									   镜架质量(镜架断裂、镜架脱焊、镜架镀层剥脱或生锈、配件丢失、配件损坏、镜架尺寸)
								`,'#slable12',{time: 3000,closeBtn: true,maxWidth: 600});})()">
							产品质量<span style="padding-left: 2px"  ><i  class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason12" value="12产品质量:::镜片延时送货" /> 镜片延时送货</label>
						<label class="lab-val" id="slable"  >
							<input type="radio" class="rad reason" name="reason12" value="12产品质量:::镜片质量" /> 镜片质量
						</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason12" value="12产品质量:::镜架延时送货" /> 镜架延时送货</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason12" value="12产品质量:::镜架质量" /> 镜架质量</label>
					</span>
				</div>
			</div>
			<div class="row mar-t15">
				<div class="p12">
					<span class="fl">
						<label class="lab-inline bold w-80">技术能力</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::验光师验光度数偏差" /> 验光师验光度数偏差</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::验光师配镜处方偏差" /> 验光师配镜处方偏差</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::配镜师镜片推荐错误" /> 配镜师镜片推荐错误</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::配镜师镜架推荐错误" /> 配镜师镜架推荐错误</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::配镜师配镜参数错误" /> 配镜师配镜参数错误</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::配镜师开单错误" /> 配镜师开单错误</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::质检员质检错误" /> 质检员质检错误</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason13" value="13技术能力:::取镜员取镜错误" /> 取镜员取镜错误</label>
					</span>
				</div>
			</div>
			<div class="row mar-t15">
				<div class="p12">
					<span class="fl">
						<label class="lab-inline bold w-80">服务能力</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason14" value="14服务能力:::验光师服务能力投诉" /> 验光师服务能力投诉</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason14" value="14服务能力:::配镜师服务能力投诉" /> 配镜师服务能力投诉</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason14" value="14服务能力:::质检员服务能力投诉" /> 质检员服务能力投诉</label>
						<label class="lab-val"><input type="radio" class="rad reason" name="reason14" value="14服务能力:::取镜员服务能力投诉" /> 取镜员服务能力投诉</label>
					</span>
				</div>
			</div>
            <div class="row mar-t15">
                <div class="p12">
					<span class="fl">
						<label class="lab-inline bold w-80">其它</label>
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
                 <textarea   class="txt txt-validate required adaptiveTextarea"  type="text" name="opinion" placeholder="" maxlength="600" cols="200">${ae.opinion!}</textarea>
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

				$('#nakedFarVod').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.scdOd:"")
				$('#nakedFarVos').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.scdOs:"")
				
				$('#iopOd1').setVal(!!opDomain.iopOd?opDomain.iopOd:"")
				$('#iopOs1').setVal(!!opDomain.iopOs?opDomain.iopOs:"")
				
				$('#jcJmpkzOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkValueOd:"")
				$('#jcJmpkzOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkValueOs:"")
				$('#jcJmpkzwOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkAxOd:"")
				$('#jcJmpkzwOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.fkAxOs:"")
				
				$('#jcYgQjOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.sphOd:"")
				$('#jcYgQjOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.sphOd:"")
				$('#jcYgZjOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.cylOd:"")
				$('#jcYgZjOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.cylOs:"")
				$('#jcYgCwOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.axisOd:"")
				$('#jcYgCwOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.axisOs:"")
				$('#jcYgJzslOd').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.dvaOd:"")
				$('#jcYgJzslOs').setVal(!!opDomain.refractiveBlVO?opDomain.refractiveBlVO.dvaOs:"")
				
				$('#jcPjcfQjOd').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.sphOd:"")
				$('#jcPjcfQjOs').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.sphOs:"")
				$('#jcPjcfZjOd').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.cylOd:"")
				$('#jcPjcfZjOs').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.cylOs:"")
				$('#jcPjcfCwOd').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.axisOd:"")
				$('#jcPjcfCwOs').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.axisOs:"")
				$('#jcPjcfJzslOd').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.dvaOd:"")
				$('#jcPjcfJzslOs').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.dvaOs:"")
				$('#jcPjcfXjgOd').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.addOd:"")
				$('#jcPjcfXjgOs').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.addOs:"")
				$('#jcPjcfDctjOd').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.dpdOd:"")
				$('#jcPjcfDctjOs').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.dpdOs:"")
				
				$('#jcPjcsTgR').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.pupilHeightOd:"")
				$('#jcPjcsTgL').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.pupilHeightOs:"")
				
				$('#jcPjcsJplxR').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.recipeTypeValue:"")
				$('#jcPjcsJplxL').setVal(!!opDomain.salesBasicBlVO?opDomain.salesBasicBlVO.recipeTypeValue:"")
				
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
					if(!isNaN(item.charAt(1))){
						index += item.charAt(1);
					}
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
		
				$('#nakedFarVodType').setVal('${ae.nakedFarVodType!}')
				$('#nakedFarVod').setVal('${ae.nakedFarVod!}')
				$('#nakedFarVodName').setVal('${ae.nakedFarVodName!}')
				$('#nakedFarVosType').setVal('${ae.nakedFarVosType!}')
				$('#nakedFarVos').setVal('${ae.nakedFarVos!}')
				$('#nakedFarVosName').setVal('${ae.nakedFarVosName!}')
				
				$('#iopTypeOd').setVal('${ae.iopTypeOd!}')
				$('#iopOd1').setVal('${ae.iopOd!}')
				$('#iopOd2').setVal('${ae.iopOd!}')
				$('#iopOdName').setVal('${ae.iopOdName!}')
				
				$('#iopTypeOs').setVal('${ae.iopTypeOs!}')
				$('#iopOs1').setVal('${ae.iopOs!}')
				$('#iopOs2').setVal('${ae.iopOs!}')
				$('#iopOsName').setVal('${ae.iopOsName!}')

				$('#jcYgJzslOdType').setVal('${ae.jcYgJzslOdType!}')
				$('#jcYgJzslOd').setVal('${ae.jcYgJzslOd!}')
				$('#jcYgJzslOdName').setVal('${ae.jcYgJzslOdName!}')
				$('#jcYgJzslOsType').setVal('${ae.jcYgJzslOsType!}')
				$('#jcYgJzslOs').setVal('${ae.jcYgJzslOs!}')
				$('#jcYgJzslOsName').setVal('${ae.jcYgJzslOsName!}')
				
				$('#jcPjcfJzslOdType').setVal('${ae.jcPjcfJzslOdType!}')
				$('#jcPjcfJzslOd').setVal('${ae.jcPjcfJzslOd!}')
				$('#jcPjcfJzslOdName').setVal('${ae.jcPjcfJzslOdName!}')
				$('#jcPjcfJzslOsType').setVal('${ae.jcPjcfJzslOsType!}')
				$('#jcPjcfJzslOs').setVal('${ae.jcPjcfJzslOs!}')
				$('#jcPjcfJzslOsName').setVal('${ae.jcPjcfJzslOsName!}')
				
				$('#clYgJzslOdType').setVal('${ae.clYgJzslOdType!}')
				$('#clYgJzslOd').setVal('${ae.clYgJzslOd!}')
				$('#clYgJzslOdName').setVal('${ae.clYgJzslOdName!}')
				$('#clYgJzslOsType').setVal('${ae.clYgJzslOsType!}')
				$('#clYgJzslOs').setVal('${ae.clYgJzslOs!}')
				$('#clYgJzslOsName').setVal('${ae.clYgJzslOsName!}')

				$('#clPjcfJzslOdType').setVal('${ae.clPjcfJzslOdType!}')
				$('#clPjcfJzslOd').setVal('${ae.clPjcfJzslOd!}')
				$('#clPjcfJzslOdName').setVal('${ae.clPjcfJzslOdName!}')
				$('#clPjcfJzslOsType').setVal('${ae.clPjcfJzslOsType!}')
				$('#clPjcfJzslOs').setVal('${ae.clPjcfJzslOs!}')
				$('#clPjcfJzslOsName').setVal('${ae.clPjcfJzslOsName!}')

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
								var $v = $(this).parents('.p6').find('.drop-sl-v');
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
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>DIP管理分析 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
    .maline {
        margin-right: 10px;
        margin-left: 10px;
        margin-bottom: 7px;
        margin-top: 0;
    }
    .mapad {
        padding-top: 3px;
    }
</style>

<script type="text/javascript">
</script>
<body>
<h2 class="h2-title-a">
    <span class="s-title">基本信息</span>
</h2>
<hr class="maline" style="border-color:#b2def4">
<form id="finMaMainFormEmp" class="soform formA form-validate pad-t10 finMaMainFormEmp" xmlns="http://www.w3.org/1999/html">
    <input type="hidden" id="id" name="id" value="${emp.id!}"/>
    <input type="hidden" id="maIdentifier" name="maIdentifier" value="${emp.maIdentifier!}"/>
    <input type="hidden" id="insuranceType" name="insuranceType" value="1"/>
    <input type="hidden" id="settlementMethod" name="settlementMethod" value="DIP"/>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医院名称：</label>
                <input class="txt txt-validate" type="text" id="hospName" name="hospName"
                       value="${emp.hospName!}" validType="maxlength[40]" autocomplete="off" readonly="true" data-options="required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医院省区：</label>
                <input class="txt txt-validate" type="text" id="hospParent" name="hospParent"
                       value="${emp.hospParent!}" validType="maxlength[40]" autocomplete="off" readonly="true" data-options="required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医院类型：</label>
                <input class="txt txt-validate" type="text" id="investNature" name="investNature"
                       value="${emp.investNature!}" validType="maxlength[40]" autocomplete="off" readonly="true" data-options="required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医院等级：</label>
                <input class="txt txt-validate" type="text" id="ehrLevel" name="ehrLevel"
                       value="${emp.ehrLevel!}" validType="maxlength[20]" autocomplete="off" readonly="true" data-options="required:true"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保结算等级：</label>
                [@ui_select id="insuranceSettlementLevel" name="insuranceSettlementLevel" tag="@fin@LEVEL" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell"  value="${emp.insuranceSettlementLevel!}" /]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">卫健委定级：</label>
                [@ui_select id="healthCommissionLevel" name="healthCommissionLevel" tag="@fin@LEVEL" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell"  value="${emp.healthCommissionLevel!}" /]
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">结算政策是否已明确：</label>
                <select class="drop easyui-combobox txt-validate required  w-100" noNull="请选择" id="mispClearFlag" name="mispClearFlag" data-options="required:true,value:'${emp.mispClearFlag!}'">
                    <option value="" ></option>
                    <option value="1" [#if emp.mispClearFlag==1] selected="selected" [/#if] >是</option>
                    <option value="2" [#if emp.mispClearFlag==2] selected="selected" [/#if] >否</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
    </div>
</form>
<form id="finMaMainFormRDT" class="soform formA form-validate pad-t10 finMaMainFormRDT hidden" style="display: none" xmlns="http://www.w3.org/1999/html">
    <input type="hidden" id="id" name="id" value="${rdt.id!}"/>
    <input type="hidden" id="maIdentifier" name="maIdentifier" value="${rdt.maIdentifier!}"/>
    <input type="hidden" id="insuranceType" name="insuranceType" value="2"/>
    <input type="hidden" id="settlementMethod" name="settlementMethod" value="DIP"/>
</form>
<h2 class="h2-title-a">
    <span class="s-title">老年性白内障结算标准（元）</span>
</h2>
<hr class="maline" style="border-color:#b2def4">
<span class="s-title" style="margin-left: 50px;margin-top: 0px;margin-bottom: 7px;font-size: larger;color: #00A0E9;">职工</span>
<form id="DIP_EMP_ARC_001" class="soform formA form-validate mapad DIP_EMP_ARC_001">
    <input type="hidden" id="id" name="id" value="${DIP_EMP_ARC_001.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_EMP_ARC_001.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_EMP_ARC_001"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="ARC"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="老年性白内障"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（不含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_ARC_001_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
				    onChange:function(newValue,oldValue){
                       $('#DIP_EMP_ARC_001 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（不含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_ARC_001_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_ARC_001 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">单眼（不含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_EMP_ARC_001.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">双眼（不含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_EMP_ARC_001.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<form id="DIP_EMP_ARC_002" class="soform formA form-validate mapad DIP_EMP_ARC_002">
    <input type="hidden" id="id" name="id" value="${DIP_EMP_ARC_002.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_EMP_ARC_002.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_EMP_ARC_002"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="ARC"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="老年性白内障"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_ARC_002_diagCode" name="diagCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_ARC_002 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_ARC_002_operationCode" name="operationCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_ARC_002 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">单眼（含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEyeyan" name="singleEye"
                       value="${DIP_EMP_ARC_002.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">双眼（含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_EMP_ARC_002.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<span class="s-title" style="margin-left: 50px;margin-top: 0px;margin-bottom: 7px;font-size: larger;color: #00A0E9;">居民</span>
<form id="DIP_RDT_ARC_001" class="soform formA form-validate mapad DIP_RDT_ARC_001">
    <input type="hidden" id="id" name="id" value="${DIP_RDT_ARC_001.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_RDT_ARC_001.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_RDT_ARC_001"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="ARC"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="老年性白内障"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（不含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_ARC_001_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_ARC_001 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（不含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_ARC_001_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_ARC_001 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">单眼（不含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_RDT_ARC_001.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">双眼（不含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_RDT_ARC_001.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<form id="DIP_RDT_ARC_002" class="soform formA form-validate mapad DIP_RDT_ARC_002">
    <input type="hidden" id="id" name="id" value="${DIP_RDT_ARC_002.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_RDT_ARC_002.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_RDT_ARC_002"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="ARC"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="老年性白内障"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_ARC_002_diagCode" name="diagCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_ARC_002 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（含飞白）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_ARC_002_operationCode" name="operationCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_ARC_002 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">单眼（含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_RDT_ARC_002.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">双眼（含飞白）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_RDT_ARC_002.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<h2 class="h2-title-a">
    <span class="s-title">翼状胬肉结算标准（元）</span>
</h2>
<hr class="maline" style="border-color:#b2def4">
<span class="s-title" style="margin-left: 50px;margin-top: 0px;margin-bottom: 7px;font-size: larger;color: #00A0E9;">职工</span>
<form id="DIP_EMP_PRGM_001" class="soform formA form-validate mapad DIP_EMP_PRGM_001">
    <input type="hidden" id="id" name="id" value="${DIP_EMP_PRGM_001.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_EMP_PRGM_001.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_EMP_PRGM_001"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="PRGM"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="翼状胬肉"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（不含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_PRGM_001_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_PRGM_001 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（不含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_PRGM_001_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_PRGM_001 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（不含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_EMP_PRGM_001.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（不含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_EMP_PRGM_001.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<form id="DIP_EMP_PRGM_002" class="soform formA form-validate mapad DIP_EMP_PRGM_002">
    <input type="hidden" id="id" name="id" value="${DIP_EMP_PRGM_002.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_EMP_PRGM_002.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_EMP_PRGM_002"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="PRGM"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="翼状胬肉"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_PRGM_002_diagCode" name="diagCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_PRGM_002 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_PRGM_002_operationCode" name="operationCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_PRGM_002 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_EMP_PRGM_002.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_EMP_PRGM_002.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<span class="s-title" style="margin-left: 50px;margin-top: 0px;margin-bottom: 7px;font-size: larger;color: #00A0E9;">居民</span>
<form id="DIP_RDT_PRGM_001" class="soform formA form-validate mapad DIP_RDT_PRGM_001">
    <input type="hidden" id="id" name="id" value="${DIP_RDT_PRGM_001.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_RDT_PRGM_001.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_RDT_PRGM_001"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="PRGM"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="翼状胬肉"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（不含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_PRGM_001_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_PRGM_001 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（不含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_PRGM_001_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_PRGM_001 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（不含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_RDT_PRGM_001.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（不含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_RDT_PRGM_001.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<form id="DIP_RDT_PRGM_002" class="soform formA form-validate mapad DIP_RDT_PRGM_002">
    <input type="hidden" id="id" name="id" value="${DIP_RDT_PRGM_002.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_RDT_PRGM_002.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_RDT_PRGM_002"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="PRGM"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="翼状胬肉"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_PRGM_002_diagCode" name="diagCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_PRGM_002 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（含羊膜移植）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_PRGM_002_operationCode" name="operationCode" data-options="editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_PRGM_002 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_RDT_PRGM_002.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（含羊膜移植）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_RDT_PRGM_002.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<h2 class="h2-title-a">
    <span class="s-title">眼底病结算标准（元）</span>
</h2>
<hr class="maline" style="border-color:#b2def4">
<span class="s-title" style="margin-left: 50px;margin-top: 0px;margin-bottom: 7px;font-size: larger;color: #00A0E9;">职工</span>
<form id="DIP_EMP_FD_001" class="soform formA form-validate mapad DIP_EMP_FD_001">
    <input type="hidden" id="id" name="id" value="${DIP_EMP_FD_001.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_EMP_FD_001.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_EMP_FD_001"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="FD"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="眼底病"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（眼底注药）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_FD_001_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_FD_001 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（眼底注药）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_FD_001_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_FD_001 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（眼底注药）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_EMP_FD_001.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（眼底注药）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_EMP_FD_001.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<form id="DIP_EMP_FD_002" class="soform formA form-validate mapad DIP_EMP_FD_002">
    <input type="hidden" id="id" name="id" value="${DIP_EMP_FD_002.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_EMP_FD_002.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_EMP_FD_002"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="FD"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="眼底病"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（眼底玻切）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_FD_002_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_FD_002 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（眼底玻切）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_EMP_FD_002_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_EMP_FD_002 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（眼底玻切）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_EMP_FD_002.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（眼底玻切）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_EMP_FD_002.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<span class="s-title" style="margin-left: 50px;margin-top: 0px;margin-bottom: 7px;font-size: larger;color: #00A0E9;">居民</span>
<form id="DIP_RDT_FD_001" class="soform formA form-validate mapad DIP_RDT_FD_001">
    <input type="hidden" id="id" name="id" value="${DIP_RDT_FD_001.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_RDT_FD_001.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_RDT_FD_001"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="FD"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="眼底病"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（眼底注药）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_FD_001_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_FD_001 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（眼底注药）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_FD_001_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_FD_001 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（眼底注药）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_RDT_FD_001.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（眼底注药）：</label>
                <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_RDT_FD_001.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<form id="DIP_RDT_FD_002" class="soform formA form-validate mapad DIP_EMP_FD_004">
    <input type="hidden" id="id" name="id" value="${DIP_RDT_FD_002.id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${DIP_RDT_FD_002.hospId!}"/>
    <input type="hidden" id="busiSign" name="busiSign" value="DIP_RDT_FD_002"/>
    <input type="hidden" id="medicalItemCode" name="medicalItemCode" value="FD"/>
    <input type="hidden" id="medicalItemName" name="medicalItemName" value="眼底病"/>
    <input type="hidden" id="diagName" name="diagName" value="" />
    <input type="hidden" id="operationName" name="operationName" value="" />
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊断名称（眼底玻切）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_FD_002_diagCode" name="diagCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_FD_002 #diagName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">手术名称（眼底玻切）：</label>
                <select class="drop easyui-combobox" style="width:100%;" id="DIP_RDT_FD_002_operationCode" name="operationCode" data-options="editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,
                    onChange:function(newValue,oldValue){
                        $('#DIP_RDT_FD_002 #operationName').val($(this).combobox('getText'));
                }"></select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">单眼（眼底玻切）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="singleEye" name="singleEye"
                       value="${DIP_RDT_FD_002.singleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2,required:true"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">双眼（眼底玻切）：</label>
                <input class="txt txt-validate easyui-numberbox" style="width:100%;" type="text" id="coupleEye" name="coupleEye"
                       value="${DIP_RDT_FD_002.coupleEye!}" validType="maxlength[20]" autocomplete="off"
                       data-options="min:0,precision:2"/>
            </div>
        </div>
    </div>
</form>
<p class="row-btn pad-t5">
    <input type="button" class="btn btn-primary" name="btnSubmit" value="保 存"/>
    <input type="button" class="btn btn-cancel-cus" name="btnCancel" value="取 消"/>
</p>
<div class="mar-10" style="font-size: 18px">
    <h1>填报说明</h1>
    <p>①“老年性白内障结算标准”统计诊断为“老年性白内障”、手术为“白内障超声乳化+人工晶状体置入术”分组，若分组中包含“飞秒激光辅助”的医院则在附表中单独填报；</p>
    <p>②“翼状胬肉结算标准”统计诊断为“翼状胬肉”、手术为“翼状胬肉切除伴自体干细胞移植术”或“翼状胬肉切除伴结膜移植术”或“翼状胬肉切除伴结膜瓣遮盖术”或“翼状胬肉切除，结膜移植术”分组（二联术式，主表中结算标准不含羊膜移植术），若分组中包含“羊膜移植术”的医院则在附表中单独填报；</p>
    <p>③“眼底注药结算标准”统计诊断为“黄斑和后极变性”或“糖尿病伴有眼的并发症”或“视网膜其他特指的疾患”（诊断三选一）、手术为“玻璃体药物注射术”分组；</p>
    <p>④“眼底玻切结算标准”统计诊断为“视网膜脱离”或“玻璃体积血”或“黄斑和后极变性”（诊断三选一）、手术为“后入路玻璃体切割术””分组（不含玻白联合术式）。</p>
    <p> 以上均统计单眼结算标准，若有双眼分组，医院需在附表中详细填报；职工、居民结算标准一致时，"结算标准"填相同数值即可。</p>
    <br/>
    <p style="font-weight: bold">其它填报问题，请联系集团财务医保及价格管理部</p>
</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['lodash', 'pub'], function (_) {

        var paramDict = {'fstLevel':'01','usingSign':1};
        $ajax.postSync('${base}/ui/amcs/fin/ma/dict/getMaDictList',paramDict,false,false).done(function (rst) {
            $('#DIP_EMP_ARC_001_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '001'));
            $('#DIP_EMP_ARC_001_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '002'));
            $('#DIP_EMP_ARC_002_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '001'));
            $('#DIP_EMP_ARC_002_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '003'));
            $('#DIP_RDT_ARC_001_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '001'));
            $('#DIP_RDT_ARC_001_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '002'));
            $('#DIP_RDT_ARC_002_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '001'));
            $('#DIP_RDT_ARC_002_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '001' && item.thdLevel === '003'));
            $('#DIP_EMP_PRGM_001_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '001'));
            $('#DIP_EMP_PRGM_001_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '002'));
            $('#DIP_EMP_PRGM_002_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '001'));
            $('#DIP_EMP_PRGM_002_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '003'));
            $('#DIP_RDT_PRGM_001_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '001'));
            $('#DIP_RDT_PRGM_001_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '002'));
            $('#DIP_RDT_PRGM_002_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '001'));
            $('#DIP_RDT_PRGM_002_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '002' && item.thdLevel === '003'));
            $('#DIP_EMP_FD_001_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '001'));
            $('#DIP_EMP_FD_001_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '002'));
            $('#DIP_EMP_FD_002_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '003'));
            $('#DIP_EMP_FD_002_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '004'));
            $('#DIP_RDT_FD_001_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '001'));
            $('#DIP_RDT_FD_002_diagCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '003'));
            $('#DIP_RDT_FD_001_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '002'));
            $('#DIP_RDT_FD_002_operationCode').combobox('loadData',rst.filter(item => item.fstLevel === '01' && item.sndLevel === '003' && item.thdLevel === '004'));

            $('#DIP_EMP_ARC_001_diagCode').combobox('setValue', '${DIP_EMP_ARC_001.diagCode}');
            $('#DIP_EMP_ARC_001_operationCode').combobox('setValue', '${DIP_EMP_ARC_001.operationCode}');
            $('#DIP_EMP_ARC_002_diagCode').combobox('setValue', '${DIP_EMP_ARC_002.diagCode}');
            $('#DIP_EMP_ARC_002_operationCode').combobox('setValue', '${DIP_EMP_ARC_002.operationCode}');
            $('#DIP_RDT_ARC_001_diagCode').combobox('setValue', '${DIP_RDT_ARC_001.diagCode}');
            $('#DIP_RDT_ARC_001_operationCode').combobox('setValue', '${DIP_RDT_ARC_001.operationCode}');
            $('#DIP_RDT_ARC_002_diagCode').combobox('setValue', '${DIP_RDT_ARC_002.diagCode}');
            $('#DIP_RDT_ARC_002_operationCode').combobox('setValue', '${DIP_RDT_ARC_002.operationCode}');
            $('#DIP_EMP_PRGM_001_diagCode').combobox('setValue', '${DIP_EMP_PRGM_001.diagCode}');
            $('#DIP_EMP_PRGM_001_operationCode').combobox('setValue', '${DIP_EMP_PRGM_001.operationCode}');
            $('#DIP_EMP_PRGM_002_diagCode').combobox('setValue', '${DIP_EMP_PRGM_002.diagCode}');
            $('#DIP_EMP_PRGM_002_operationCode').combobox('setValue', '${DIP_EMP_PRGM_002.operationCode}');
            $('#DIP_RDT_PRGM_001_diagCode').combobox('setValue', '${DIP_RDT_PRGM_001.diagCode}');
            $('#DIP_RDT_PRGM_001_operationCode').combobox('setValue', '${DIP_RDT_PRGM_001.operationCode}');
            $('#DIP_RDT_PRGM_002_diagCode').combobox('setValue', '${DIP_RDT_PRGM_002.diagCode}');
            $('#DIP_RDT_PRGM_002_operationCode').combobox('setValue', '${DIP_RDT_PRGM_002.operationCode}');
            $('#DIP_EMP_FD_001_diagCode').combobox('setValue', '${DIP_EMP_FD_001.diagCode}');
            $('#DIP_EMP_FD_001_operationCode').combobox('setValue', '${DIP_EMP_FD_001.operationCode}');
            $('#DIP_EMP_FD_002_diagCode').combobox('setValue', '${DIP_EMP_FD_002.diagCode}');
            $('#DIP_EMP_FD_002_operationCode').combobox('setValue', '${DIP_EMP_FD_002.operationCode}');
            $('#DIP_RDT_FD_001_diagCode').combobox('setValue', '${DIP_RDT_FD_001.diagCode}');
            $('#DIP_RDT_FD_002_diagCode').combobox('setValue', '${DIP_RDT_FD_002.diagCode}');
            $('#DIP_RDT_FD_001_operationCode').combobox('setValue', '${DIP_RDT_FD_001.operationCode}');
            $('#DIP_RDT_FD_002_operationCode').combobox('setValue', '${DIP_RDT_FD_002.operationCode}');

        });

        function bangClick(e){
            e.preventDefault(); // 阻止按钮默认的提交行为

            var formCheck = true;
            $("form").each(function() {
                if(!$(this).form('validate')){
                    formCheck = false;
                }
            });

            if(formCheck){
                // 创建一个数组来保存所有表单的数据
                var formDataArray = [];

                // 遍历所有的form，获取它们的数据
                $("form").each(function() {
                    //var formData = $(this).serializeArray(); // 获取单个form的数据
                    var formData = $(this).sovals();
                    formDataArray.push(formData); // 将单个form的数据添加到数组中
                });

                // 使用AJAX将数据发送到服务器
                $ajax.post({
                    url: '${base}/ui/amcs/fin/dip/ma/saveDip',
                    data: JSON.stringify(formDataArray),
                    tip: '你确定提交吗？',
                    jsonData: true,//是否采用jsonData格式提交
                    sync: false,//是否同步方式提交
                    type: 'post',//采用post方式提交
                    loadingMask: true,//进行异步请求中，是否显示mask
                    calltip: true,//提交成功后显示请求成功信息
                    success: function (rst) {
                        window.location.href='${base}/ui/amcs/fin/dip/ma/maDipForm';
                    },//请求成功后，code===200或者201返回事件
                    callback: function (rst) {
                    },//请求成功后返回事件
                    cancelback: function () {
                    },//确认框点取消返回事件
                    errback: function (req, textStatus, errorThrown) {
                        //$pop.alert('保存失败111！');
                    }//出现错误时返回事件
                });
            }
        }

        // 当提交按钮被点击时执行
        $("input[name='btnSubmit']").click(function(e) {
            $("input[name='btnSubmit']").on("click", bangClick);
            bangClick(e);
        });

    });

</script>

<!--
        $('#finMaMainFormEmp #insuranceSettlementLevel').combobox({
            onChange: function(newValue,oldValue){
                $('#finMaMainFormRDT #insuranceSettlementLevel').combobox('setValue', newValue);
            }
        });

        $('#finMaMainFormEmp #healthCommissionLevel').combobox({
            onChange: function(newValue,oldValue){
                $('#finMaMainFormRDT #healthCommissionLevel').combobox('setValue', newValue);
            }
        });

        $('#finMaMainFormEmp #mispClearFlag').combobox({
            onChange: function(newValue,oldValue){
                $('#finMaMainFormRDT #mispClearFlag').combobox('setValue', newValue);
            }
        });

        $('#finMaMainFormEmp #poolingArea').change(function (){
            $('#finMaMainFormRDT #poolingArea').val($(this).val());
        });
-->



<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>病种列表 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">

</style>

<script type="text/javascript">
</script>
<body>
<div class="searchHead bob-line">
    <form id="sbox" class="soform form-enter">
        <label class="lab-inline">医保结算方式：</label>
        <select class="drop drop-sl-v easyui-combobox  w-150" name="queryFstLevelVal" id="queryFstLevelVal"  data-options="">
            <option value="" ></option>
            <option value="01">DIP</option>
            <option value="02">DRG</option>
        </select>
        <label class="lab-inline">病种大类名称：</label>
        <select class="drop drop-sl-v easyui-combobox  w-150" name="querySndLevelVal" id="querySndLevelVal"  data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true">
        </select>
        <label class="lab-inline">病种小类名称：</label>
        <select class="drop drop-sl-v easyui-combobox  w-150" name="queryThdLevelVal" id="queryThdLevelVal"  data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true">
        </select>
        <label class="lab-inline">病种名称：</label><input type="text" class="txt inline" id="queryValueDesc" name="queryValueDesc" value="">
        <label class="lab-inline">包含停用</label><input id="containStop" class="rad" type="checkbox" name="containStop">
        <input type="text" class="hide txt inline" id="s-usingSign" name="usingSign" value=1
               autocomplete="off">
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查
            询
        </button>
    </form>
</div>

<div class="cont-grid">
    <div id="gridBox"></div>
</div>

<script id="editDiseaseVal" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form id="mainForm" class="soform formA form-validate pad-t20 labeldict" method="post"
          action="${base}/ui/amcs/fin/ma/dict/save">
        <input class="txt hide" type="text" id="id" name="id" value=""/>
        <input class="txt hide" type="text" id="fstLevelVal" name="fstLevelVal" value=""/>
        <input class="txt hide" type="text" id="sndLevelVal" name="sndLevelVal" value=""/>
        <input class="txt hide" type="text" id="thdLevelVal" name="thdLevelVal" value=""/>
        <div class="row">
            <div class="p4">
                <div class="item-one">
                    <label class="lab-item solab-l">医保结算方式：</label>
                    <select class="drop drop-sl-v easyui-combobox  w-150" name="fstLevel" id="fstLevel"  data-options="required:true">
                        <option value="" ></option>
                        <option value="01">DIP</option>
                        <option value="02">DRG</option>
                    </select>
                </div>
            </div>
            <div class="p4">
                <div class="item-one">
                    <label class="lab-item solab-l">病种大类名称：</label>
                    <select class="drop drop-sl-v easyui-combobox  w-150" name="sndLevel" id="sndLevel"  data-options="valueField:'valueCode',textField:'valueDesc',required:true,clearIcon:true"></select>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-c">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item solab-l">病种小类名称：</label>
                    <select class="drop drop-sl-v easyui-combobox  w-150" name="thdLevel" id="thdLevel"  data-options="valueField:'valueCode',textField:'valueDesc',required:true,clearIcon:true"></select>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-s">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-s">
                    <label class="lab-item solab-c">病种名称：</label>
                    <input class="txt txt-validate" type="text" id="valueDesc" name="valueDesc" value="" autocomplete="off" data-options="required:true"/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-s">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p3">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s">启用标识：</label>
                    [@ui_select uiShowDefault=false class="drop easyui-combobox" value="" id="usingSign" name="usingSign" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.UsingSignEnum" filterkey="firstSpell" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]
                </div>
            </div>
        </div>

        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存"/>
            <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>

</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">

    requirejs(['pub'], function () {
        this.initSelect('queryFstLevelVal','querySndLevelVal','queryThdLevelVal');

         $grid.newGrid("#gridBox", {
            pagination: true,
            fitColumns: false,
            tools: [
                [
                    {
                        iconCls: 'plus',//图标
                        text: '新增',//文字
                    }
                ]
            ],
            columns: [[
                {title: 'ID', field: 'id', hidden: true,width: 150,},
                {
                    title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                        var chkHtml = '';
                        chkHtml += '<span rel=' + row.id + ' relIndex=' + index  + ' class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
                        return chkHtml;
                    }
                },
                {title: '医保结算方式', field: 'fstLevelVal', width: 100},
                {title: '病种大类名称', field: 'sndLevelVal', width: 100},
                {title: '病种小类名称', field: 'thdLevelVal', width: 150},
                {title: '病种名称', field: 'valueDesc', width: 300},
            ]],
            rowStyler: function (index, row) {
                if (row.usingSign === 0) {
                    return 'color:red';
                }
            },
            queryParams: {
                usingSign: 1
            },
            onBeforeLoad: function (param) {
                return true;
            },
            onLoadSuccess: function (data) {

            },
            url: '${base}/ui/amcs/fin/ma/dict/getMaDictList',
            // height: 'auto',
            offset: -5
        });

        $(".s-tool").click(function ($e) {
            $pop.popTemForm({
                title: "新增病种",
                temId: 'editDiseaseVal',
                area: ['810px', '300px'],
                temData: {},
                zIndex: 2,
                grid: '#gridBox',
                onPop: function ($p) {
                    //$("input[type=radio][name=usingSign]:eq(0)").attr("checked",true);
                    initSelect('fstLevel','sndLevel','thdLevel');
                },
                end: function () {
                },
                beforeSubmit: function (opt, $form, formData) {
                    return true;
                }
            });
        });

        $('.cont-grid').on('click', '.s-op-edit', function () {
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            var p = $pop.popTemForm({
                title: "编辑病种",
                temId: 'editDiseaseVal',
                area: ['800px', '300px'],
                temData: rowData,
                data: rowData,
                zIndex: 2,
                grid: '#gridBox',
                onPop: function ($p) {
                    if (rowData.fstLevel == '01') {
                        $('#sndLevel').combobox('loadData', eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"老年性白内障","valueCode":"001",},{"valueDesc":"翼状胬肉","valueCode":"002",},{"valueDesc":"眼底病","valueCode":"003",}]'));
                        if (rowData.sndLevel == '001') {
                            $('#thdLevel').combobox('loadData', eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"诊断名称","valueCode":"001",},{"valueDesc":"手术名称(不含飞白)","valueCode":"002",},{"valueDesc":"手术名称(含飞白)","valueCode":"003",}]'));
                        }
                        if (rowData.sndLevel == '002') {
                            $('#thdLevel').combobox('loadData', eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"诊断名称","valueCode":"001",},{"valueDesc":"手术名称(不含羊膜移植)","valueCode":"002",},{"valueDesc":"手术名称(含羊膜移植)","valueCode":"003",}]'));
                        }
                        if (rowData.sndLevel == '003') {
                            $('#thdLevel').combobox('loadData', eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"诊断名称(眼底注药)","valueCode":"001",},{"valueDesc":"手术名称(眼底注药)","valueCode":"002",},{"valueDesc":"诊断名称(眼底玻切)","valueCode":"003",},{"valueDesc":"手术名称(眼底玻切)","valueCode":"004",}]'));
                        }
                    }
                    if (rowData.fstLevel == '02') {
                        $('#sndLevel').combobox('loadData', eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"老年性白内障","valueCode":"001",},{"valueDesc":"翼状胬肉","valueCode":"002",},{"valueDesc":"眼底注药","valueCode":"003",},{"valueDesc":"眼底玻切","valueCode":"004",}]'));
                        if(rowData.sndLevel=='001'){
                            $('#thdLevel').combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                        if(rowData.sndLevel=='002'){
                            $('#thdLevel').combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                        if(rowData.sndLevel=='003'){
                            $('#thdLevel').combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                        if(rowData.sndLevel=='004'){
                            $('#thdLevel').combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                    }
                    $("#fstLevel").combobox('readonly', true);
                    $("#sndLevel").combobox('readonly', true);
                    $("#thdLevel").combobox('readonly', true);
                },
                end: function () {
                },
                beforeSubmit: function (opt, $form, formData) {
                    return true;
                }
            });
        });

        $("#containStop").click(function ($e) {
            if ($(this).prop("checked")) {
                $("#s-usingSign").val("");
            } else {
                $("#s-usingSign").val(1);
            }
        });

    });


    function initSelect(paramFstLevelVal,paramSndLevelVal,paramThdLevelVal){
        $('#'+paramFstLevelVal).combobox({
            onChange: function(newValue,oldValue){
                var sVal = $(this).children('option:selected').val();
                $('#'+paramSndLevelVal).combobox('setValue','');
                $('#'+paramThdLevelVal).combobox('setValue','');
                $('#'+paramThdLevelVal).combobox('loadData','');
                if(sVal){
                    if(sVal=='01'){
                        $('#'+paramSndLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"老年性白内障","valueCode":"001",},{"valueDesc":"翼状胬肉","valueCode":"002",},{"valueDesc":"眼底病","valueCode":"003",}]'));
                    }
                    if(sVal=='02'){
                        $('#'+paramSndLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"老年性白内障","valueCode":"001",},{"valueDesc":"翼状胬肉","valueCode":"002",},{"valueDesc":"眼底注药","valueCode":"003",},{"valueDesc":"眼底玻切","valueCode":"004",}]'));
                    }
                }
                if($('#fstLevelVal')){
                    $('#fstLevelVal').val($('#'+paramFstLevelVal).combobox('getText'));
                }
            }
        });

        $('#'+paramSndLevelVal).combobox({
            onChange: function(newValue,oldValue){
                var fstLevelVal=$('#'+paramFstLevelVal).combobox('getValue');
                var sVal = $(this).combobox('getValue');;
                $('#'+paramThdLevelVal).combobox('setValue','');
                if(fstLevelVal=='01'){
                    if(sVal){
                        if(sVal=='001'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"诊断名称","valueCode":"001",},{"valueDesc":"手术名称(不含飞白)","valueCode":"002",},{"valueDesc":"手术名称(含飞白)","valueCode":"003",}]'));
                        }
                        if(sVal=='002'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"诊断名称","valueCode":"001",},{"valueDesc":"手术名称(不含羊膜移植)","valueCode":"002",},{"valueDesc":"手术名称(含羊膜移植)","valueCode":"003",}]'));
                        }
                        if(sVal=='003'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"诊断名称(眼底注药)","valueCode":"001",},{"valueDesc":"手术名称(眼底注药)","valueCode":"002",},{"valueDesc":"诊断名称(眼底玻切)","valueCode":"003",},{"valueDesc":"手术名称(眼底玻切)","valueCode":"004",}]'));
                        }
                    }
                }else if(fstLevelVal=='02'){
                    if(sVal){
                        if(sVal=='001'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                        if(sVal=='002'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                        if(sVal=='003'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                        if(sVal=='004'){
                            $('#'+paramThdLevelVal).combobox('loadData',eval('[{"valueDesc":"","valueCode":"",},{"valueDesc":"DRG分组","valueCode":"001",},]'));
                        }
                    }
                }

                if($('#sndLevelVal')){
                    $('#sndLevelVal').val($('#'+paramSndLevelVal).combobox('getText'));
                }
            }
        });

        $('#'+paramThdLevelVal).combobox({
            onChange: function (newValue,oldValue){
                if($('#thdLevelVal')){
                    $('#thdLevelVal').val($('#'+paramThdLevelVal).combobox('getText'));
                }
            }
        });

    }

</script>



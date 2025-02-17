<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>青光眼转诊 - 爱尔医院</title>
    <style type="text/css">
        /*.mainbody{position: absolute;top:0px;left:0;right:0;bottom:50px;}*/
        .mainfooter {
            padding: 0px 10px 0;
            text-align: right;
        }

        .cont-grid {
            overflow: hidden;
            zoom: 1;
            border-bottom: 5px solid #E8EBF2;
        }

        .cont-l {
            width: 1000px;
        }

        .navGridWrap {
            border-right: 5px solid #E8EBF2;
        }

        .h2-title {
            line-height: 25px;
            color: #00A0E9;
            font-size: 14px;
        }

        .h2-title-a {
            padding: 22px 10px 6px;
        }

        .soform input.so-rangeDate {
            width: 180px;
        }

        .textbox {
            position: relative;
            right: -50px;
        }

        .combo {
            position: relative;
            right: -77px;
        }
    </style>

</head>

<body>
<div class="searchHead bob-line">
    <form id="sbox" class="soform form-enter">
        <input type="text" class="hide" name="listChangeId" value="">
       <label class="lab-inline">患者名称</label><input type="text" class="txt inline txt-validate"  id="patientName" name="patientName"
                                                     autocomplete="off" validType="maxlength[10,'搜索框不能超过10个字符']">
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#navGridBox',scope:'#sbox'}">
            查
            询
        </button>
    </form>
</div>
<div class="cont-grid cont-fly">
        <div class="navGridWrap">
            <div id="navGridBox"></div>
        </div>
</div>

<div class="mainfooter">

</div>

<script id="editGlaucoma" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form class="soform formA form-validate form-enter pad-t20 glaucomaForm" method="post"
          action="${base}/ui/amcs/cat/glaucoma/saveGlaucoma">
        <input class="txt hide" type="text" id="id" name="id" value=""/>
        <input class="txt hide" type="text" id="patientId" name="patientId" value=""/>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">患者姓名：</label>
                    <input class="txt txt-validate required" type="text" id="patientName" name="patientName" value="" autocomplete="off" noNull="患者姓名必填"  style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">患者电话：</label>
                    <!-- <input class="txt txt-validate required" type="text" id="mobilePhone" name="mobilePhone" value="" maxlength="20" placeholder="电话号码" noNull="请输入电话号码" />
                    <input id="mobilePhone" name="mobilePhone" class="easyui-textbox" style="width: 200px;" data-options="prompt:'请输入正确的电话号码码',validType:'telNum'" /> -->
                    <input type="text" class="easyui-numberbox" id="mobilePhone" name="mobilePhone"  data-options="prompt:'请输入正确的手机号码', validType:'phoneNum'" noNull="电话号码必填" >
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">就诊日期：</label>
                    <input id="outpatientDate" class="txt txt-validate so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="outpatientDate"  noNull="请选择就诊日期"  style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">眼压值(OD)：</label>
                    <input id="odPressure" class="easyui-numberbox required" type="text" name="odPressure" noNull="眼压值必填" value=0 data-options="missingMessage:'必填',required:true,min:0,max:100"
                           style="width:60%;position: relative;right: -40px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">眼压值(OS)：</label>
                    <input id="osPressure" class="easyui-numberbox required" type="text" name="osPressure" noNull="眼压值必填" value=0 data-options="missingMessage:'必填',required:true,min:0,max:100"
                           style="width:60%;position: relative;right: -40px;"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">首诊科室(转出)：</label>
                    <input id="fstOutDeptName" class="txt txt-validate" type="text" name="fstOutDeptName" value=""  style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 60%">首诊医生(转出)：</label>
                    <input class="txt txt-validate" type="text" id="fstOutDoctorName" name="fstOutDoctorName" value="" autocomplete="off" style="width:60%;position: relative;right: -77px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 60%">首诊医生处置情况(转出)：</label>
                    <select class="drop easyui-combobox w-90"  id="outTreatInfo" name="outTreatInfo">
                        <option value="">-请选择-</option>
                        <option value="1">直接转诊</option>
                        <option value="2">排青后转诊</option>
                        <option value="3">排青未转诊</option>
                        <option value="4">未做处置</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">接诊医生(转入)：</label>
                    <input id="inDoctorName" class="txt txt-validate" type="text" name="inDoctorName" value="" style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 60%">接诊医生处置情况(转入)：</label>
                    <select class="drop easyui-combobox w-90"  id="inTreatInfo" name="inTreatInfo">
                        <option value="">-请选择-</option>
                        <option value="1">已药物治疗</option>
                        <option value="2">已手术治疗</option>
                        <option value="3">已激光治疗</option>
                        <option value="4">未治疗</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">接诊医生诊断结果(转入)：</label>
                    <textarea class="txt txt-validate" id="inMedicalResult" name="inMedicalResult" validType=""  style="height: 50px;text-indent: 0;width:60%;position: relative;right: -50px;"/>
                    <!-- <input class="txt txt-validate required" type="text" id="inMedicalResult" name="inMedicalResult" value="" autocomplete="off" noNull="诊断结果必填"  style="width:60%;position: relative;right: -77px;"/>
                        <select class="drop easyui-combobox w-90"  id="inMedicalResult" name="inMedicalResult">
                        <option value="">-请选择-</option>
                        <option value="1">先天性青光眼</option>
                        <option value="2">急性闭角型青光眼</option>
                        <option value="3">原发性开角型青光眼</option>
                        <option value="4">慢性闭角型青光眼</option>
                        <option value="5">继发性青光眼</option>
                        <option value="6">非青光眼</option>
                    </select> -->
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">未治疗原因：</label>
                    <textarea class="txt txt-validate" id="untreatReason" name="untreatReason" validType=""  style="height: 50px;text-indent: 0;width:60%;position: relative;right: -50px;"/>
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
    require(["pub"], function () {

        $grid.newGrid("#navGridBox", {
            // fitParent: true,
            pagination: true,
            fitColumns: false,
            tools: [
                [
                    {
                        iconCls: 'plus',//图标
                        text: '新增',//文字
                        click: function () {
                            var p = $pop.popTemForm({
                                title: "新增",
                                temId: 'editGlaucoma',
                                area: ['800px', '400px'],
                                temData: {},
                                zIndex: 2,
                                grid: '#navGridBox',
                                singleSelect: true,

                                onPop: function ($p) {

                                },
                                end: function () {

                                },
                                beforeSubmit: function (opt, $form, formData) {
                                    return true;
                                }
                            });
                        }
                    },
                    {
                        iconCls: 'box-add',//图标
                        text: '查询HIS',//文字
                        click: function () {
                            $pop.iframePop({
                                title:"查询HIS",
                                content:"${base}/ui/amcs/cat/glaucoma/getHisGlaucomaPage"
                            },'#navGridBox')
                        }
                    }
                ]
            ],
            columns: [[
                {
                    title: '操作', field: 'op', width: 100, formatter: function (v, row, index) {
                        var chkHtml = '';
                        chkHtml += '<span rel=' + row.id + ' relIndex=' + index + ' class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
                        chkHtml += '&nbsp;&nbsp;<span rel=' + row.id + ' relIndex=' + index + ' class="s-op s-op-del  icon-del" title="删除" aier="edit" ></span>';
                        return chkHtml;
                    }
                },
                {title: 'ID', field: 'id', hidden: true, width: 150},
                {title: '就诊日期', field: 'outpatientDate', width: 150},
                {title: '患者姓名', field: 'patientName', width: 60},
                {title: '患者电话', field: 'mobilePhone', width: 100},
                {title: 'OD眼压', field: 'odPressure', hidden: false, width: 80},
                {title: 'OS眼压', field: 'osPressure', width: 100},
                {title: '首诊科室（转出）', field: 'fstOutDeptName', width: 100},
                {title: '首诊医生（转出）', field: 'fstOutDoctorName', width: 100},
                {title: '首诊医生处置情况（转出）', field: 'outTreatInfo', width: 150,formatter: function (v, row, index) {
                    if(v==1){return "直接转诊";}else if(v==2){return "排青后转诊";}else if(v==3){return "排青未转诊";}else if(v==4){return "未做处置";}
                }},
                {title: '接诊医生（转入）', field: 'inDoctorName', width: 100},
                {title: '接诊医生诊断结果（转入）', field: 'inMedicalResult', width: 150,formatter: function (v, row, index) {
                        if(v==1){return "先天性青光眼";}else if(v==2){return "急性闭角型青光眼";}else if(v==3){return "原发性开角型青光眼";}else if(v==4){return "慢性闭角型青光眼";}else if(v==5){return "继发性青光眼";}else if(v==6){return "非青光眼";}else{return v;}
                }},
                {title: '接诊医生处置情况（转入）', field: 'inTreatInfo', width: 150,formatter: function (v, row, index) {
                        if(v==1){return "已药物治疗";}else if(v==2){return "已手术治疗";}else if(v==3){return "已激光治疗";}else if(v==4){return "未治疗";}
                }},
                {title: '未治疗原因', field: 'untreatReason', width: 100}
            ]],
            rowStyler: function (index, row) {

            },
            queryParams: {

            },
            onBeforeLoad: function (param) {
                return true;
            },
            onLoadSuccess: function (data) {

            },
            onSelect: function (index, row) {

            },
            url: '${base}/ui/amcs/cat/glaucoma/getAll',
            offset: -5
        });

        $('.cont-grid').on('click', '.s-op-edit', function () {
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#navGridBox").datagrid("getRows")[idx];
            var p = $pop.popTemForm({
                title: "编辑",
                temId: 'editGlaucoma',
                area: ['800px', '450px'],
                temData: rowData,
                data: rowData,
                zIndex: 2,
                grid: '#navGridBox',
                onPop: function ($p) {

                },
                end: function () {
                    //$pop.close(p)
                },
                beforeSubmit: function (opt, $form, formData) {
                    return true;
                }
            });
        });

        $('.cont-grid').on('click', '.s-op-del', function () {
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#navGridBox").datagrid("getRows")[idx];
            $pop.confirm('是否删除该记录？', function () {//确定
                $ajax.post('${base}/ui/amcs/cat/glaucoma/delGlaucoma', rowData).done(function (rst) {
                    $("#navGridBox").datagrid("reload");
                    $("#navGridBox").datagrid("clearSelections");
                });
                return true;
            }, function () {//取消
                return true;
            });
        });

        $.extend($.fn.validatebox.defaults.rules, {
            phoneNum: { //验证手机号
                validator: function(value, param){
                    return /^1[3-9]+\d{9}$/.test(value);
                },
                message: '请输入正确的手机号码。'
            },
            telNum:{ //既验证手机号，又验证座机号
                validator: function(value, param){
                    return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^(()|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
                },
                message: '请输入正确的电话号码。'
            }
        });

    });

</script>
</body>

</html>

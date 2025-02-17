<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>泪道转诊 - 爱尔医院</title>
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

    </style>

</head>

<body>
<div class="searchHead bob-line">
    <form id="sbox" class="soform form-enter">
       <label class="lab-inline">患者名称</label><input type="text" class="txt inline txt-validate" id="patientName" name="patientName"
                                                     autocomplete="off" validType="maxlength[10,'搜索框不能超过10个字符']">
        <!-- <label class="lab-inline">包含停用</label><input id="containStop" class="rad" type="checkbox" name="containStop"> -->
        <!-- <input type="text" class="hide txt inline" id="s-usingSign" name="usingSign" value=1 autocomplete="off"> -->
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

<script id="editLacrimal" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form class="soform formA form-validate form-enter pad-t20 lacrimalForm" method="post"
          action="${base}/ui/amcs/cat/lacrimal/saveLacrimal">
        <input class="txt hide" type="text" id="id" name="id" value=""/>
        <input class="txt hide" type="text" id="patientId" name="patientId" value=""/>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">患者姓名：</label>
                    <input class="txt txt-validate required" type="text" id="patientName" name="patientName" value="" autocomplete="off" noNull="患者姓名必填" style="width: 60%;position: relative;right: -50px;" />
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">就诊日期：</label>
                    <input id="checkDate" class="txt txt-validate so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="checkDate" noNull="请选择就诊日期" style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">OD返流(+)：</label>
                    <!-- <input id="odBackflow" class="txt txt-validate " type="text" name="odBackflow" style="width:60%;position: relative;right: -50px;"/> -->
                    <select class="drop easyui-combobox w-90 required"  id="odBackflow" name="odBackflow">
                        <option value="">-请选择-</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">OD不入咽(+)：</label>
                    <!-- <input id="odInthroat" class="txt txt-validate " type="text" name="odInthroat" style="width:60%;position: relative;right: -50px;"/> -->
                    <select class="drop easyui-combobox w-90 required"  id="odInthroat" name="odInthroat">
                        <option value="">-请选择-</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">OD分泌物(+)：</label>
                    <!-- <input id="odSecretion" class="txt txt-validate " type="text" name="odSecretion" style="width:60%;position: relative;right: -50px;"/> -->
                    <select class="drop easyui-combobox w-90 required"  id="odSecretion" name="odSecretion">
                        <option value="">-请选择-</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">OS返流(+)：</label>
                    <!-- <input id="osBackflow" class="txt txt-validate " type="text" name="osBackflow" style="width:60%;position: relative;right: -50px;"/> -->
                    <select class="drop easyui-combobox w-90 required"  id="osBackflow" name="osBackflow">
                        <option value="">-请选择-</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">OS不入咽(+)：</label>
                    <!-- <input id="osInthroat" class="txt txt-validate " type="text" name="osInthroat" style="width:60%;position: relative;right: -50px;"/> -->
                    <select class="drop easyui-combobox w-90 required"  id="osInthroat" name="osInthroat">
                        <option value="">-请选择-</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">OS分泌物(+)：</label>
                    <!-- <input id="osSecretion" class="txt txt-validate " type="text" name="osSecretion" style="width:60%;position: relative;right: -50px;"/> -->
                    <select class="drop easyui-combobox w-90 required"  id="osSecretion" name="osSecretion">
                        <option value="">-请选择-</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">首诊科室：</label>
                    <input id="fstDeptName" class="txt txt-validate" type="text" name="fstDeptName" value="" style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">首诊医生：</label>
                    <input class="txt txt-validate" type="text" id="fstDoctorName" name="fstDoctorName" value="" autocomplete="off" style="width:60%;position: relative;right: -50px;" />
                </div>
            </div>
            <div class="p4">
                <!-- <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">首诊医生处置情况：</label>
                    <select class="drop easyui-combobox w-90"  id="fstTreatInfo" name="fstTreatInfo">
                        <option value="">-请选择-</option>
                        <option value="1">直接转诊</option>
                        <option value="2">排青后转诊</option>
                        <option value="3">排青未转诊</option>
                        <option value="4">未做处置</option>
                    </select>
                </div> -->
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">首诊医生处置情况：</label>
                    <textarea class="txt txt-validate" title="100字以内" id="fstTreatInfo" name="fstTreatInfo" validType="" style="height: 50px;text-indent: 0;width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">泪道医生姓名：</label>
                    <input id="glaucomaDoctorName" class="txt txt-validate" type="text" name="glaucomaDoctorName" value="" style="width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">是否转泪道科：</label>
                    <select class="drop easyui-combobox w-90"  id="isTreat" name="isTreat">
                        <option value="">-请选择-</option>
                        <option value="1">直接转诊</option>
                        <option value="2">接诊后转诊</option>
                        <option value="3">未转诊</option>
                    </select>
                </div>
            </div>
            <div class="p4">
                <!-- <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">泪道医生诊断结果：</label>
                    <select class="drop easyui-combobox w-90"  id="glaucomaResult" name="glaucomaResult">
                        <option value="">-请选择-</option>
                        <option value="1">先天性青光眼</option>
                        <option value="2">急性闭角型青光眼</option>
                        <option value="3">原发性开角型青光眼</option>
                        <option value="4">慢性闭角型青光眼</option>
                        <option value="5">继发性青光眼</option>
                        <option value="6">非青光眼</option>
                    </select>
                </div> -->
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">泪道医生诊断结果：</label>
                    <textarea class="txt txt-validate" title="100字以内" id="glaucomaResult" name="glaucomaResult" validType="" style="height: 50px;text-indent: 0;width:60%;position: relative;right: -50px;"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one solab-s">
                    <label class="lab-item solab-s" style="width: 120px;">未治疗原因：</label>
                    <textarea class="txt txt-validate" title="100字以内" id="untreatReason" name="untreatReason" validType="" style="height: 50px;text-indent: 0;width:60%;position: relative;right: -50px;"/>
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
                                temId: 'editLacrimal',
                                area: ['800px', '500px'],
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
                                content:"${base}/ui/amcs/cat/lacrimal/getHisLacrimalPage"
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
                        if(row.patientId){
                            chkHtml += '&nbsp;&nbsp;<span rel=' + row.patientId + ' relIndex=' + index + ' class="s-op s-op-update  icon-user" title="HIS更新" aier="edit" ></span>';
                        }
                        return chkHtml;
                    }
                },
                {title: 'ID', field: 'id', hidden: true, width: 150},
                {title: 'patientId', field: 'patientId', hidden: true, width: 100},
                {title: '就诊日期', field: 'checkDate', width: 150},
                {title: '患者姓名', field: 'patientName', width: 60},
                {title: 'OD返流', field: 'odBackflow', width: 80,formatter: function (v, row, index) {
                        if(v==1){return "是";}else if(v==2){return "否";}
                }},
                {title: 'OD不入咽', field: 'odInthroat', width: 100,formatter: function (v, row, index) {
                        if(v==1){return "是";}else if(v==2){return "否";}
                }},
                {title: 'OD分泌物', field: 'odSecretion', width: 80,formatter: function (v, row, index) {
                        if(v==1){return "是";}else if(v==2){return "否";}
                }},
                {title: 'OS返流', field: 'osBackflow', width: 80,formatter: function (v, row, index) {
                        if(v==1){return "是";}else if(v==2){return "否";}
                }},
                {title: 'OS不入咽', field: 'osInthroat', width: 100,formatter: function (v, row, index) {
                        if(v==1){return "是";}else if(v==2){return "否";}
                }},
                {title: 'OS分泌物', field: 'osSecretion', width: 80,formatter: function (v, row, index) {
                        if(v==1){return "是";}else if(v==2){return "否";}
                }},
                {title: '首诊科室', field: 'fstDeptName', width: 100},
                {title: '首诊医生', field: 'fstDoctorName', width: 100},
                {title: '首诊医生处置情况', field: 'fstTreatInfo', width: 110,formatter: function (v, row, index) {
                        if(v==1){return "直接转诊";}else if(v==2){return "排青后转诊";}else if(v==3){return "排青未转诊";}else if(v==4){return "未做处置";}else{return v;}
                }},
                {title: '泪道医生姓名', field: 'glaucomaDoctorName', width: 100},
                {title: '泪道医生诊断结果', field: 'glaucomaResult', width: 130,formatter: function (v, row, index) {
                        if(v==1){return "先天性青光眼";}else if(v==2){return "急性闭角型青光眼";}else if(v==3){return "原发性开角型青光眼";}else if(v==4){return "慢性闭角型青光眼";}else if(v==5){return "继发性青光眼";}else if(v==6){return "非青光眼";}else{return v;}
                }},
                {title: '是否转泪道科', field: 'isTreat', width: 100,formatter: function (v, row, index) {
                        if(v==1){return "直接转诊";}else if(v==2){return "接诊后转诊";}else if(v==3){return "未转诊";}
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
            url: '${base}/ui/amcs/cat/lacrimal/getAll',
            offset: -5
        });

        $('.cont-grid').on('click', '.s-op-edit', function () {
            debugger;
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#navGridBox").datagrid("getRows")[idx];
            var p = $pop.popTemForm({
                title: "编辑",
                temId: 'editLacrimal',
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

        $('.cont-grid').on('click', '.s-op-update', function () {
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#navGridBox").datagrid("getRows")[idx];
            $pop.confirm('是否更新HIS转诊信息？', function () {//确定
                $ajax.post('${base}/ui/amcs/cat/lacrimal/queryHisLacrimalInfo', rowData).done(function (rst) {
                    $("#navGridBox").datagrid("reload");
                    $("#navGridBox").datagrid("clearSelections");
                });
                return true;
            }, function () {//取消
                return true;
            });
        });

        $('.cont-grid').on('click', '.s-op-del', function () {
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#navGridBox").datagrid("getRows")[idx];
            $pop.confirm('是否删除该记录？', function () {//确定
                $ajax.post('${base}/ui/amcs/cat/lacrimal/delLacrimal', rowData).done(function (rst) {
                    $("#navGridBox").datagrid("reload");
                    $("#navGridBox").datagrid("clearSelections");
                });
                return true;
            }, function () {//取消
                return true;
            });
        });


    });

</script>
</body>

</html>

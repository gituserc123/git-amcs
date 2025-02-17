<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医院医保检查列表 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
    .rowsBtn {
        margin-left: 15px;
        margin-bottom: 5px;
    }
    .desc-box .textbox .textbox-text {white-space: pre-wrap; height:auto !important}
    .desc-box .textbox .textbox-text:focus { height:auto !important}

    .likeTabs{width:auto;}
    .likeTabs .tab-last{border-right:0;}
    .tabs li a.tabs-inner{padding:0 30px;}
    .tabContBox{position: absolute;left:0;right:0;top:0px;bottom:0px;overflow: hidden;}

    .tabCont{position: absolute;left:0;right:0;top:34px;bottom:5px;}
    .tabContHide{visibility: hidden;}

    .cont-grid{overflow: hidden;zoom: 1;}
    .table-header{
        padding: 10px;
        text-align: center;
        background-color: #e5f4fb;
    }
</style>

<script type="text/javascript">
</script>
<body>
<div class="tabContBox">
    <ul class="tabs likeTabs">
        <li class="tabs-first tabs-selected">
            <a href="#" class="tabs-inner"><span class="tabs-title">医保检查统计表</span></a>
        </li>
        <li  rel="0">
            <a href="#" class="tabs-inner"><span class="tabs-title">填报说明</span></a>
        </li>
    </ul>
    <div class="tabCont tabCont-6 ">
        <div class="searchHead bob-line">
            <form id="sbox" class="soform form-enter">
                [#if empType==1]
                <label class="lab-inline">省区：</label>
                <select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true">
                </select>
                [/#if]
                [#if empType==1 || empType==2]
                <label class="lab-inline">医院：</label>
                <select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
                </select>
                [/#if]
                <label class="lab-inline">上报日期：</label>
                <!--<input id="checkDate" name="checkDate" type="text" class="txt so-rangeDate" />  -->
                <input type="text" class="txt so-rangeDate txt-validate" id="checkDate" name="checkDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true}"/>
                <label class="lab-inline">是否结案：</label>
                <select class="drop w-100"  id="caseClosed" name="caseClosed">
                    <option value="" ></option>
                    <option value="1">是</option>
                    <option value="2">否</option>
                </select>
                <label class="lab-inline">是否上传附件：</label>
                <select class="drop w-100"  id="isAttach" name="isAttach">
                    <option value="" ></option>
                    <option value="1">是</option>
                    <option value="2">否</option>
                </select>
                <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查
                    询
                </button>
            </form>
        </div>
        <div class="cont-grid">
            <div id="gridBox"></div>
        </div>
        <div class="none">
            <div id="fileUrlDiv"></div>
        </div>
    </div>
    <div class="tabCont tabCont-0 tabContHide" >
        <div class="mar-10" style="font-size: 18px">
            <h1>填报说明</h1>
            <p>1、此表仅统计医院<span style="color: red;">接受外部职能机构（如医保局）的检查情况</span>，不包括集团、省区、医院内部检查情况。</p>
            <p>
                2、此表由医院接受检查后填报检查相关情况，<span style="color: red;">每月5日前由省区医保负责人确认无误后通知省区对接人</span>，如遇特殊情况或医院接受医保检查涉规金额较大或情节较严重的，应及时填报或告知省区对接人。此表统计金额单位为“<span style="color: red;">元</span>”。
            </p>
            <p>3、“违规扣款”指医院被医保机构责令退回的医保违规款项；“罚款”指除医保违规扣款本金外的行政罚款；“违约金”指除医保违规扣款本金外的违约金扣款。此三项一般情况下有医保机构正式扣罚款文件或通知，<span style="color: red;">医院填报此三栏时需附医保机构正式文件或通知</span>。
            </p>
            <p>
                4、“自查自纠退款”指医院接受医保机构接受检查，按医保机构要求进行的医保退回款项或抵减应收医保款款项。此项退款一般情况下无医保机构正式文件或通知，<span style="color: red;">医院填报此栏时需附银行退款记录或退款说明</span>。
            </p>
            <p>5、“其他处罚”包括医保部门曝光、暂停医保服务、解除医保服务、吊销医疗机构执业许可证、法定代表人或主要负责人5年内禁止从事定点医药机构管理活动、违反其他法律及行政法规被有关主管部门依法处理等。<span style="color: red;">医院填报此栏时需附医保机构正式文件或通知</span>。
            </p>
            <p>6、<span style="color: red;">上传的附件限定格式为图片及PDF，其他格式文档请转换为图片及PDF后再行上传。</span>
            </p>
            <br/>
            <p style="font-weight: bold">其它填报问题，请联系集团财务医保及价格管理部</p>
        </div>
    </div>
</div>
<script id="editHospInsuranceCheck" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form id="mainForm" class="soform formA form-validate pad-t20 hospInsuranceCheckCls" method="post"
          action="${base}/ui/amcs/fin/hospInsuranceCheck/save">
        <input class="txt hide" type="text" id="id" name="id" value=""/>
        <input class="txt hide" type="text" name="hospId" value="{{hospId}}" id="hospId"/>
        <h2 class="h2-title-a">
            <span class="s-title">基本信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">医院名称：</label>
                    <input class="txt txt-validate" type="text" id="hospName" name="hospName"
                           value="" validType="maxlength[40]" autocomplete="off" readonly="true" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">医院省区：</label>
                    <input class="txt txt-validate" type="text" id="hospParent" name="hospParent"
                           value="" validType="maxlength[40]" autocomplete="off" readonly="true" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">医院类型：</label>
                    <input class="txt txt-validate" type="text" id="investNature" name="investNature"
                           value="" validType="maxlength[40]" autocomplete="off" readonly="true" data-options="required:true"/>
                </div>
            </div>
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">医院等级：</label>
                    <input class="txt txt-validate" type="text" id="ehrLevel" name="ehrLevel"
                           value="" validType="maxlength[20]" autocomplete="off" readonly="true" data-options="required:true"/>
                </div>
            </div>
        </div>

        <h2 class="h2-title-a">
            <span class="s-title">医保检查</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">检查时间</label>
                    <input type="text" id="checkDate" class="txt so-date required txt-validate" data-options="required:true, maxDate:'${sysdate}',format:'yyyy-MM-dd',type:'date'" name="checkDate" value="" />
                </div>
            </div>
            <div class="p6">
                <div class="item-one desc-box">
                    <label class="lab-item">检查机构或人员</label>
                    <input id="checkInstPerson" name="checkInstPerson" type="text" class="easyui-textbox" style="height:50px; width: 100%;"   data-options="required:true,multiline:true,validType:['length[0,50]']" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one desc-box" style="height: 100px;">
                    <label class="lab-item ">检查时间范围及检查内容</label>
                    <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="检查数据时间段及检查内容"
                              style="width: 100%;height: 100%" id="checkContent" name="checkContent" maxlength="200" cols="200"
                              row="10"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one desc-box">
                    <label class="lab-item ">医院涉规问题</label>
                    <input id="regulatoryIssues" name="regulatoryIssues" type="text" class="easyui-textbox" style="height:50px; width: 100%"  data-options="required:true,multiline:true,validType:['length[0,1501]']"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">医院涉规金额</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="regulatoryAmount" name="regulatoryAmount"
                           value="" validType="maxlength[10]" autocomplete="off"
                           data-options="min:0,precision:2,required:true"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one desc-box">
                    <label class="lab-item">医院申诉情况</label>
                    <input id="appealStatus" name="appealStatus" type="text" class="easyui-textbox" style="height:50px; width: 100%;"   data-options="required:true,multiline:true,validType:['length[0,500]']" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-l">
                    <label class="lab-item">是否结案</label>
                    <label class="lab-val"><input class="rad" type="radio" value="1" name="caseClosed" />是</label>
                    <label class="lab-val"><input class="rad" type="radio" value="2" name="caseClosed" />否</label>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-l">
                </div>
            </div>
        </div>

        <h2 class="h2-title-a">
            <span class="s-title">最终结案结果</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">违规扣款</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="deductionAmount" name="deductionAmount"
                           value="" validType="maxlength[10]" autocomplete="off"
                           data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                // 合计
                                let fineAmount = parseFloat($('#fineAmount').numberbox('getValue')) || 0;
                                let penaltyAmount = parseFloat($('#penaltyAmount').numberbox('getValue')) || 0;
                                let selfRefundAmount = parseFloat($('#selfRefundAmount').numberbox('getValue')) || 0;
                                $('#sumAmount').numberbox('setValue', parseFloat(v) + fineAmount + penaltyAmount + selfRefundAmount);
                                // $('#sumAmount').numberbox('setValue',parseFloat(v) + parseFloat($('#fineAmount').numberbox('getValue')) + parseFloat($('#penaltyAmount').numberbox('getValue')) + parseFloat($('#selfRefundAmount').numberbox('getValue')));
                           })"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">罚款</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="fineAmount" name="fineAmount"
                           value="" validType="maxlength[10]" autocomplete="off"
                           data-options="min:0,precision:2,
                           onChange:((v)=>{
                                // 合计
                                let deductionAmount = parseFloat($('#deductionAmount').numberbox('getValue')) || 0;
                                let penaltyAmount = parseFloat($('#penaltyAmount').numberbox('getValue')) || 0;
                                let selfRefundAmount = parseFloat($('#selfRefundAmount').numberbox('getValue')) || 0;
                                $('#sumAmount').numberbox('setValue', parseFloat(v) + deductionAmount + penaltyAmount + selfRefundAmount);
                                // $('#sumAmount').numberbox('setValue',parseFloat(v) + parseFloat($('#deductionAmount').numberbox('getValue')) + parseFloat($('#penaltyAmount').numberbox('getValue')) + parseFloat($('#selfRefundAmount').numberbox('getValue')));
                           })"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">违约金</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="penaltyAmount" name="penaltyAmount"
                           value="" validType="maxlength[10]" autocomplete="off"
                           data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                // 合计
                                let deductionAmount = parseFloat($('#deductionAmount').numberbox('getValue')) || 0;
                                let fineAmount = parseFloat($('#fineAmount').numberbox('getValue')) || 0;
                                let selfRefundAmount = parseFloat($('#selfRefundAmount').numberbox('getValue')) || 0;
                                $('#sumAmount').numberbox('setValue', parseFloat(v) + deductionAmount + fineAmount + selfRefundAmount);
                                // $('#sumAmount').numberbox('setValue',parseFloat(v) + parseFloat($('#deductionAmount').numberbox('getValue')) + parseFloat($('#fineAmount').numberbox('getValue')) + parseFloat($('#selfRefundAmount').numberbox('getValue')));
                           })"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">自查自纠退款</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="selfRefundAmount" name="selfRefundAmount"
                           value="" validType="maxlength[10]" autocomplete="off"
                           data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                // 合计
                                let deductionAmount = parseFloat($('#deductionAmount').numberbox('getValue')) || 0;
                                let fineAmount = parseFloat($('#fineAmount').numberbox('getValue')) || 0;
                                let penaltyAmount = parseFloat($('#penaltyAmount').numberbox('getValue')) || 0;
                                $('#sumAmount').numberbox('setValue', parseFloat(v) + deductionAmount + fineAmount + penaltyAmount);
                                // $('#sumAmount').numberbox('setValue',parseFloat(v) + parseFloat($('#deductionAmount').numberbox('getValue')) + parseFloat($('#fineAmount').numberbox('getValue')) + parseFloat($('#penaltyAmount').numberbox('getValue')));
                           })"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">合计</label>
                    <input class="txt txt-validate easyui-numberbox"  style="width:100%;" type="text" id="sumAmount" name="sumAmount"
                           value="" validType="maxlength[10]" autocomplete="off"
                           data-options="min:0,precision:2,required:true"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one desc-box">
                    <label class="lab-item ">其他处罚</label>
                    <input id="otherPenalty" name="otherPenalty" type="text" class="easyui-textbox" style="height:50px; width: 100%"  data-options="multiline:true,validType:['length[0,500]']"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p12">
                <div class="item-one desc-box">
                    <label class="lab-item ">备注</label>
                    <input id="comments" name="comments" type="text" class="easyui-textbox" style="height:50px; width: 100%"  data-options="multiline:true,validType:['length[0,1000]']"/>
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
[#include '/WEB-INF/views/common/include_export.ftl']

<script type="text/javascript">

    requirejs(['template','myupload',"export",'pub'], function (template,myupload,exportFn) {

        this.reloadEventData();

        var tabIndex = 0;
        var $tabLi = $('.likeTabs li');
        //页签点击事件
        $tabLi.click(function() {
            var $this = this;
            var ix = $tabLi.index(this);
            if (tabIndex !== ix) {
                tabIndex = ix;
                $tabLi.removeClass('tabs-selected');
                $($this).addClass('tabs-selected');
                $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
            }
        });

         $grid.newGrid("#gridBox", {
            pagination: true,
            fitColumns: false,
            tools: [
                [
                    [#if empType==3]
                    {
                        iconCls: 'plus',//图标
                        text: '新增',//文字
                        click: function () {
                            addInsuranceCheck();
                        }
                    },
                    [/#if]
                    [#if empType==1 || empType==3]
                        {
                            iconCls: 'plus_sign',//图标
                            text: '导出',//文字
                            click: function () {
                                exportInsuranceCheck();
                            }
                        }
                    [/#if]
                ]
            ],
            columns: [[
                {title: 'ID', field: 'id', hidden: true,width: 150,},
                {
                    title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                        var chkHtml = '';
                        [#if empType==3]
                        chkHtml += '<span rel=' + row.id + ' relIndex=' + index  + ' class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
                        chkHtml += '&nbsp;&nbsp;<span class="s-op s-op-upload icon-upload" title="附件上传" rel="'+row.id+'"></span>';
                        [/#if]
                        [#if empType==1 || empType==3]
                        chkHtml += '&nbsp;&nbsp;<span class="s-op s-op-batch-download icon-download_alt" title="批量下载" rel="'+row.id+'"></span>';
                        [/#if]
                        return chkHtml;
                    }
                },
                {title: '省区', field: 'hospParent', width: 100},
                {title: '医院名称', field: 'hospName', width: 120},
                {title: '检查时间', field: 'checkDate', width: 100,formatter: function (value,row,index) {
                        return value ? new Date(value).toISOString().split('T')[0] : '';
                    }},
                {title: '检查机构', field: 'checkInstPerson', width: 150},
                {title: '检查时间范围及检查内容', field: 'checkContent', width: 155},
                {title: '医院涉规问题', field: 'regulatoryIssues', width: 100},
                {title: '医院涉规金额(元)', field: 'regulatoryAmount', width: 115},
                {title: '医院申诉情况', field: 'appealStatus', width: 150},
                {title: '是否结案', field: 'caseClosed', width: 80,formatter: function (value,row,index) {
                        if (value=='1') { return '是'; } else{return '否';}}},
                {title: '附件', field: 'instCheckAttaches', width: 150,formatter: function (value,row,index) {
                    if (value && value.length) {
                        var returnS = '';
                        value.forEach(function (i, s) {
                            returnS += '<span class="s-preview blue hand" title="预览" rel="' + i.id + '"webUrl="' + i.webUrl + '">附件' + (s + 1) + '</span>&nbsp;'
                            [#if empType==3]
                            returnS += '<span class="s-op s-op-del-att icon-del" style="color: red" title="删除" rel="' + i.id + '"></span>&nbsp;&nbsp;'
                            [/#if]
                        })
                        return returnS;
                    }
                }},
                {title: '合计(元)', field: 'sumAmount', width: 80},
                {title: '违规扣款(元)', field: 'deductionAmount', width: 100},
                {title: '罚款(元)', field: 'fineAmount', width: 80},
                {title: '违约金(元)', field: 'penaltyAmount', width: 80},
                {title: '自查自纠退款(元)', field: 'selfRefundAmount', width: 120},
                {title: '其他处罚', field: 'otherPenalty', width: 150},
                {title: '备注', field: 'comments', width: 150},
                {title: '创建人', field: 'creatorName', width: 150},
                {title: '创建时间', field: 'createDate', width: 150},
                {title: '最新更新人', field: 'modiferName', width: 150},
                {title: '最新更新时间', field: 'modifyDate', width: 150},
            ]],
            rowStyler: function (index, row) {},
            queryParams: {},
            onBeforeLoad: function (param) {
                return true;
            },
            onLoadSuccess: function (data) {
                //附件上传
                $('.s-op-upload').click(function () {
                    var id = $(this).attr('rel');
                    // 将row.id传递给上传组件
                    upload.options.formData={
                        bizId: id
                    };
                    $('#fileUrlDiv').find('input').click();
                });
                //附件预览
                $('.s-preview').click(function (data) {
                    var id = $(this).attr('rel');
                    var webUrl = $(this).attr('webUrl');
                    if (webUrl) {
                        $pop.iframePop({
                            title: '附件预览',
                            content : encodeURI(webUrl),
                            area: ['800px', '640px']
                        });
                        return false;
                    }
                });
                //附件删除
                $('.s-op-del-att').click(function () {
                    var id = $(this).attr('rel');
                    $ajax.post({
                        url: '${base}/ui/amcs/fin/hospInsuranceCheck/deleteAttach?id='+id,
                        jsonData: true,
                        tip: '你确定删除此附件吗？',
                        callback: function (rst) {
                            $grid.reload('#gridBox');
                        }
                    });
                });
            },
            url: '${base}/ui/amcs/fin/hospInsuranceCheck/getAll',
            // height: 'auto',
            offset: -5
        });

        // $(".s-tool").eq(0).click(function ($e) {
         function addInsuranceCheck(){
            let hospInfo;
            $ajax.postSync('${base}/ui/amcs/fin/hospInsuranceCheck/getHospInfo',{},false,false).done(function (rst) {
                hospInfo = rst;
            });
            $pop.popTemForm({
                title: "新增",
                temId: 'editHospInsuranceCheck',
                area: ['1200px', '850px'],
                temData: hospInfo,
                zIndex: 2,
                grid: '#gridBox',
                onPop: function ($p) {
                    $(".hospInsuranceCheckCls").form('load', hospInfo);
                    $('#sumAmount').numberbox('readonly', true);
                },
                end: function () {

                },
                beforeSubmit: function (opt, $form, formData) {
                    if (!$('input[name="caseClosed"]:checked').val()) {
                        $pop.alert('请选择是否结案');
                        return false;
                    }
                    return true;
                }
            });
        };

        // $(".s-tool").eq(1).click(function ($e) {
        function exportInsuranceCheck(){
            var $form = $('.form-enter');
            var sData = $form.sovals();
            $ajax.postSync('${base}/ui/amcs/fin/hospInsuranceCheck/getAllEntity',sData,false,false).done(function (rst) {
                exportFn.gridToExcel({
                    grid: '#gridBox',
                    data: rst,
                    fileName: '医保检查下载'
                });
            });
        };

        $('.cont-grid').on('click', '.s-op-edit', function () {
            var id = $(this).attr('rel');
            var idx = $(this).attr('relIndex');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            var p = $pop.popTemForm({
                title: "编辑",
                temId: 'editHospInsuranceCheck',
                area: ['1200px', '850px'],
                temData: rowData,
                data: rowData,
                zIndex: 2,
                grid: '#gridBox',
                onPop: function ($p) {
                    $('#sumAmount').numberbox('readonly', true);
                },
                end: function () {
                },
                beforeSubmit: function (opt, $form, formData) {
                    if (!$('input[name="caseClosed"]:checked').val()) {
                        $pop.alert('请选择是否结案');
                        return false;
                    }
                    return true;
                }
            });
        });

        var upload = $("#fileUrlDiv").powerWebUpload({
            auto: true,
            fileNumLimit: 1,
            btnTxt: '附件上传',
            multiple: false,
            upOpt: {
                accept: {extensions: 'doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar'},
                dupliacate: true,
                server: '${base}/ui/amcs/fin/hospInsuranceCheck/upload'
            },
            uploadSuccess: function (file, req) {
                $pop.close(uploadLoading);
                if(req.code == '201'){
                    $pop.msg.success(req.msg);
                }
                if(req.code=='500'){
                    $pop.alert(req.msg);
                }
                upload.removeFile(file);
                $grid.reload('#gridBox');
            },
            beforeUpload: function (file) {
                // 允许的文件扩展名列表
                const allowedExtensions = ['xls', 'doc', 'docx', 'ppt', 'pptx', 'xlsx', 'pdf', 'jpg', 'png', 'gif', 'txt', 'zip', 'rar'];
                // 检查文件扩展名是否在允许的列表中
                if (!allowedExtensions.some(ext => file.ext.indexOf(ext) !== -1)) {
                    $pop.msg('上传格式类型不符，请上传doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar格式文件，其他格式暂不支持~');
                }else{
                    uploadLoading = $pop.load(1,{shade:0.1});
                }
            },
            uploadError: function (file, req) {
                console.log(req);
                $pop.msg('文件上传失败！');
                $pop.close(uploadLoading);
                upload.removeFile(file);
                $grid.reload('#gridBox');
            }
        });

        $("#containStop").click(function ($e) {
            if ($(this).prop("checked")) {
                $("#s-usingSign").val("");
            } else {
                $("#s-usingSign").val(1);
            }
        });

        $('.cont-grid').on('click', ".s-op-batch-download",function ($e) {
            var id = $(this).attr('rel');
            $pop.confirm('您确认想要导出附件吗？',function(r){
                if (r){
                    var url = '${base}/ui/amcs/fin/hospInsuranceCheck/downloadAttach?id='+id;
                    downloadExcelProcess(url);
                }
            });
        });

        var showMask = function () {
            var wrap = $(".cont-grid");
            $("<div class=\"datagrid-mask\"></div>").css({
                display: "block",
                width: window.width,
                height: window.height
            }).appendTo(wrap);
        };
        var rmMask = function () {
            var wrap = $(".cont-grid");
            wrap.find("div.datagrid-mask").remove();
        }

        function downloadExcelProcess(url){
            // 开启遮罩
            showMask();
            //$pop.alert('文件正在导出，请耐心等待....');
            $pop.msg('文件正在导出，请耐心等待....',3000,{icon:2});
            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.responseType = "blob"; // 返回类型blob
            // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
            xhr.onload = function() {
                // 请求完成
                if (this.status === 200) {
                    var type = xhr.getResponseHeader('Content-Type');
                    var blob = new Blob([this.response], {
                        type: type
                    });
                    var fileName = xhr.getResponseHeader("content-disposition");
                    fileName = decodeURI(fileName.split(";")[1].split("filename=")[1].trim('"'));
                    if (typeof window.navigator.msSaveBlob !== 'undefined') {
                        // For IE>=IE10
                        window.navigator.msSaveBlob(blob, fileName);
                    } else {
                        // For chrome, firefox
                        var URL = window.URL || window.webkitURL;
                        var objectUrl = URL.createObjectURL(blob);
                        if (fileName) {
                            // 创建一个a标签用于下载
                            var a = document.createElement('a');
                            a.download = fileName;
                            a.href = objectUrl;
                            $("body").append(a);
                            a.click();
                            $(a).remove();
                        } else {
                            window.location = objectUrl;
                        }
                    }
                } else {
                    $pop.alert('导出失败！');
                }
                // 关闭遮罩代码
                rmMask();
            };
            // 发送ajax请求
            xhr.send();
        };

    });

    function reloadEventData() {
        [#if empType==1]
        $ajax.postSync('${base}/ui/amcs/fin/hospInsuranceCheck/getProvince',null,false,false).done(function (rst) {
            $('#province').combobox('loadData', rst);
        });
        /*$ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
            $('#hospId').combobox('loadData', rst);
        });*/
        $('#province').combobox({
            onChange: function(v){
                if(v){
                    let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                    $('#hospId').combobox('reload', url);
                }
            }
        });
        [/#if]
        [#if empType==2]
        var paramInsi = {insiId:'${insiId}'};
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
            $('#hospId').combobox('loadData', rst);
        });
        [/#if]

    };

</script>



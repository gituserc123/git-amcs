<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医院医保检查省区权限列表 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
    .rowsBtn {
        margin-left: 15px;
        margin-bottom: 5px;
    }
    .desc-box .textbox .textbox-text {white-space: pre-wrap; height:auto !important}
    .desc-box .textbox .textbox-text:focus { height:auto !important}
</style>

<script type="text/javascript">
</script>
<body>
<div class="searchHead bob-line">
    [#--<div class="baseToobar topToobar hide">
        <span class="s-tool op-addIndicator"><b class="icon icon-plus"></b>新增</span>
    </div>--]

    <form id="sbox" class="soform form-enter">
        <label class="lab-inline">姓名：</label>
        <input type="text" class="txt inline" name="staffName" value="">
        <label class="lab-inline">工号：</label>
        <input type="text" class="txt inline" name="staffCode" value="">
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查
            询
        </button>
    </form>
</div>

<div class="cont-grid">
    <div id="gridBox"></div>
</div>

</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">

    requirejs(['template','myupload',"export",'pub'], function (template,myupload,exportFn) {


         $grid.newGrid("#gridBox", {
            pagination: false,
            fitColumns: false,
            tools: [
                [
                    [#if empType==1]
                    {
                        iconCls: 'plus',//图标
                        text: '新增',//文字
                    }
                    [/#if]
                ]
            ],
            columns: [[
                {
                    title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                        var chkHtml = '';
                        [#if empType==1]
                        chkHtml += '<span relIndex=' + index  + ' class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
                        [/#if]
                        return chkHtml;
                    }
                },
                {title: '工号', field: 'staffCode', width: 120},
                {title: '姓名', field: 'staffName', width: 150},
                {title: '省区权限', field: 'provinceNames', width: 150},
            ]],
            rowStyler: function (index, row) {},
            queryParams: {},
            onBeforeLoad: function (param) {
                return true;
            },
            onLoadSuccess: function (data) {

            },
            url: '${base}/ui/amcs/fin/hospInsuranceCheck/getAuthLists',
            // height: 'auto',
            offset: -5
        });

        $(".s-tool").eq(0).click(function ($e) {

            $pop.iframePop({
                title: '新增省区权限',//标题
                content: '${base}/ui/amcs/fin/hospInsuranceCheck/editCheckAuth',//请求地址
                area: ['800px', '900px'],//窗口大小
                // postData : {mainId:row.id},//往子页面传值
                end : function(iframeSendData){
                    //关闭执行函数，子页面可通过 $pop.closePop 返回参数
                    $grid.load('#gridBox');
                },
                sureback : function (iframeSendData){

                    //表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
                }
            },'#gridBox');


        });

        $('.cont-grid').on('click', '.s-op-edit', function () {
            var idx = $(this).attr('relIndex');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            $pop.iframePop({
                title: '编辑省区权限',//标题
                content: '${base}/ui/amcs/fin/hospInsuranceCheck/editCheckAuth?staffCode=' + rowData.staffCode,//请求地址
                area: ['800px', '900px'],//窗口大小
                // postData : {mainId:row.id},//往子页面传值
                end : function(iframeSendData){
                    //关闭执行函数，子页面可通过 $pop.closePop 返回参数
                    $grid.load('#gridBox');
                },
                sureback : function (iframeSendData){

                    //表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
                }
            },'#gridBox');
        });

    });

</script>



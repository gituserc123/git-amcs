<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>刑事案件登记 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
        <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
    </form>
</div>
<div class="cont-grid">
    <div id="gridBox"></div>
</div>

[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(["lodash", "easygridEdit", "pub"], function (_, $e) {

        $grid.newGrid("#gridBox", {
            pagination: true,
            fitColumns: false,
            tools: [],
            columns: [[
                {title: "操作", field: "op", width: 100, formatter: function(v, row, index) {
                        let opStr = '';
                        opStr += '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
                        return opStr;
                    }},
                // 基础信息
                {title: '机构ID', field: 'instId', width: 80, hidden: true},
                {title: '机构名称', field: 'instName', width: 120},
                {title: '省区/上级机构ID', field: 'superInstId', width: 100, hidden: true},
                {title: '省区/上级机构', field: 'superInstName', width: 120},

                // 案件信息
                {title: '犯罪嫌疑人名称', field: 'suspectName', width: 120},
                {title: '案件主办公安机关', field: 'handlingPsb', width: 150},
                {title: '案件主办检察院', field: 'prosecutorOffice', width: 150},
                {title: '案件主审法院', field: 'courtName', width: 150},
                {title: '案件类型', field: 'caseType', width: 100},
                {title: '罪名', field: 'chargeName', width: 120},
                {title: '罪名其他描述', field: 'chargeNameDesc', width: 150},
                {title: '案号', field: 'caseNo', width: 120},
                {title: '案件描述', field: 'caseDesc', width: 200},

                // 金额与阶段
                {title: '涉案金额', field: 'involvedAmount', width: 100, formatter: function(v) {
                        return v ? '￥' + v.toFixed(2) : '';
                    }},
                {title: '诉讼阶段', field: 'litigationPhase', width: 120},
                {title: '当前状态', field: 'currentStatus', width: 200},

                // 备注
                {title: '备注', field: 'remarks', width: 200},

                // 时间信息
                {title: '创建时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},

                // 其他信息
                {title: '是否网络舆情', field: 'isPublicOpinion', width: 100, formatter: function(v) {
                        return v === 'Y' ? '是' : '否';
                    }}
            ]],
            rowStyler: function(index, row) {},
            onBeforeLoad: function(param) {},
            onLoadSuccess: function(data) {},
            url: '${base}/ui/amcs/law/criminalCase/queryCriminalCaseList' // 根据实际接口路径修改
        });

        $(".cont-grid").on("click",".s-op-review",function(e){
            var idx = $(this).attr('rel');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            let url = '${base}/ui/amcs/law/criminalCase/info?bizId=' + rowData.id;
            let title = '刑事案件';
            $pop.newTabWindow(title, url,false);
        });

    });

</script>
</body>
</html>
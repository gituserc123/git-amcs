<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>法务查询 - 外聘法律顾问信息</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
        <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
    </form>
</div>
<div class="cont-grid">
    <div id="gridBox"></div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(["lodash", "easygridEdit", "pub"], function (_, $e) {

        $grid.newGrid("#gridBox", {
            pagination: true,
            fitColumns: false,
            tools: [],
            columns: [[
                {title: "操作", field: "op", width: 50, formatter: function(v, row, index) {
                        let opStr = '';
                        opStr += '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
                        return opStr;
                    }},
                // 基础信息
                {title: '机构ID', field: 'instId', width: 80, hidden: true},
                {title: '机构名称', field: 'instName', width: 120},
                {title: '省区/上级机构ID', field: 'superInstId', width: 100, hidden: true},
                {title: '省区/上级机构', field: 'superInstName', width: 120},

                // 外聘法律顾问信息
                {title: '律师事务所全称', field: 'lawFirmName', width: 150},
                {title: '统一社会信用代码', field: 'unifiedCreditCode', width: 150},
                {title: '注册地址', field: 'registeredAddress', width: 150},
                {title: '主办律师姓名', field: 'hostLawyerName', width: 120},
                {title: '主办律师联系方式', field: 'hostLawyerContact', width: 120},

                // 擅长领域
                {title: '擅长领域', field: 'expertiseField', width: 120, hidden: true},
                {title: '擅长领域', field: 'expertiseFieldTxt', width: 120},

                // 服务类型
                {title: '服务类型', field: 'serviceType', width: 120, hidden: true},
                {title: '服务类型', field: 'serviceTypeTxt', width: 120},

                // 服务内容与范围
                {title: '服务内容与范围', field: 'serviceScope', width: 200},

                // 备注信息
                {title: '备注信息', field: 'remark', width: 200},

                // 时间信息
                {title: '创建时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},
            ]],
            rowStyler: function(index, row) {},
            onBeforeLoad: function(param) {},
            onLoadSuccess: function(data) {},
            url: '${base}/ui/amcs/law/externalLegalAdvisor/queryExternalLegalAdvisorList' // 根据实际接口路径修改
        });

        $(".cont-grid").on("click",".s-op-review",function(e){
            var idx = $(this).attr('rel');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            let url = '${base}/ui/amcs/law/externalLegalAdvisor/info?bizId=' + rowData.id;
            let title = '外聘法律顾问信息';
            $pop.newTabWindow(title, url, false);
        });

    });
</script>
</html>
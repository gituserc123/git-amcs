<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>纠纷事项信息录入</title>
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

                // 纠纷事项信息
                {title: '对方全称', field: 'opponentFullName', width: 150},
                {title: '我方全称', field: 'ourFullName', width: 150},
                {title: '申报单位', field: 'applyUnit', width: 150},
                {title: '申报人', field: 'applicantName', width: 100},
                {title: '申报人电话', field: 'applicantPhone', width: 120},

                // 纠纷详情
                {title: '纠纷原因及经过', field: 'disputeCauseProcess', width: 200},
                {title: '是否涉及舆情', field: 'isPublicOpinion', width: 100, formatter: function(v) {
                        return v === 'Y' ? '是' : '否';
                    }},
                {title: '舆情发展情况', field: 'opinionDevelopment', width: 200},
                {title: '处理措施', field: 'handlingMeasures', width: 200},
                {title: '处理意见及诉求', field: 'opinionRequirement', width: 200},

                // 时间信息
                {title: '创建时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '开始时间', field: 'startDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '结束时间', field: 'endDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '修改时间', field: 'modifyDate', width: 120, format: 'yyyy-MM-dd'}
            ]],
            rowStyler: function(index, row) {},
            onBeforeLoad: function(param) {},
            onLoadSuccess: function(data) {},
            url: '${base}/ui/amcs/law/disputeMatter/queryDisputeMatterList' // 根据实际接口路径修改
        });

        $(".cont-grid").on("click",".s-op-review",function(e){
            var idx = $(this).attr('rel');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            let url = '${base}/ui/amcs/law/disputeMatter/info?bizId=' + rowData.id;
            let title = '纠纷事项';
            $pop.newTabWindow(title, url,false);
        });

    });

</script>
</body>
</html>
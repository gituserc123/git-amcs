<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>行政处罚信息录入</title>
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

                // 行政处罚信息
                {title: '受处罚单位/个人', field: 'partyName', width: 150},
                {title: '行政机关全称', field: 'authorityName', width: 150},
                {title: '处罚告知单位', field: 'penalizedUnit', width: 150},
                {title: '文书类型', field: 'documentType', width: 100},
                {title: '文书类型其他', field: 'documentTypeOther', width: 120},
                {title: '文书编号', field: 'documentNo', width: 120},
                {title: '受案日期', field: 'filingDate', width: 100, format: 'yyyy-MM-dd'},
                {title: '处罚日期', field: 'penaltyDate', width: 100, format: 'yyyy-MM-dd'},
                {title: '收文日期', field: 'receiveDate', width: 100, format: 'yyyy-MM-dd'},

                // 案件信息
                {title: '案件名称', field: 'caseName', width: 150},
                {title: '处罚事由', field: 'penaltyReason', width: 100},
                {title: '处罚事由其他', field: 'penaltyReasonOther', width: 120},
                {title: '处罚类别', field: 'penaltyCategory', width: 100},
                {title: '处罚类别其他', field: 'penaltyCategoryOther', width: 120},
                {title: '处罚依据', field: 'penaltyBasis', width: 200},
                {title: '处罚措施', field: 'penaltyMeasures', width: 200},

                // 涉案信息
                {title: '涉案背景', field: 'caseBackground', width: 200},
                {title: '事件经过', field: 'caseProcess', width: 200},
                {title: '涉案金额（万元）', field: 'involvedAmount', width: 120, formatter: function(v) {
                        return v ? '￥' + v.toFixed(2) : '';
                    }},
                {title: '当前状态', field: 'currentStatus', width: 150},

                // 申报信息
                {title: '申报单位', field: 'applyUnit', width: 150},
                {title: '申报人', field: 'applicantName', width: 100},
                {title: '申报人电话', field: 'applicantPhone', width: 120},
                {title: '已采取的措施', field: 'handlingMeasures', width: 200},
                {title: '处理意见及诉求', field: 'opinionRequirement', width: 200},

                // 其他信息
                {title: '是否涉及舆情', field: 'isPublicOpinion', width: 100, formatter: function(v) {
                        return v === 'Y' ? '是' : '否';
                    }},
                {title: '备注', field: 'remarks', width: 200},
                {title: '创建时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'}
            ]],
            rowStyler: function(index, row) {},
            onBeforeLoad: function(param) {},
            onLoadSuccess: function(data) {},
            url: '${base}/ui/amcs/law/adminPenalty/queryAdminPenaltyList' // 根据实际接口路径修改
        });

        $(".cont-grid").on("click",".s-op-review",function(e){
            var idx = $(this).attr('rel');
            var rowData = $("#gridBox").datagrid("getRows")[idx];
            let url = '${base}/ui/amcs/law/adminPenalty/info?bizId=' + rowData.id;
            let title = '行政处罚';
            $pop.newTabWindow(title, url,false);
        });

    });

</script>
</body>
</html>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>法务查询 - 爱尔医院</title>
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

                // 案件信息
                {title: '案件类别', field: 'caseCategory', width: 100, hidden: true},
                {title: '案件类别', field: 'caseCategoryTxt', width: 100},
                {title: '案件类型一级', field: 'caseTypeOne', width: 120, hidden: true},
                {title: '案件类型二级', field: 'caseTypeTwo', width: 120, hidden: true},
                {title: '案件类型', field: 'caseTypeOneTxt', width: 120},
                {title: '案由', field: 'caseTypeTwoTxt', width: 120},
                {title: '案号', field: 'caseNo', width: 120},
                {title: '案情简介', field: 'caseDesc', width: 200},

                // 金额与阶段
                {title: '涉案金额', field: 'involvedAmount', width: 100, formatter: function(v) {
                        return v ? '￥' + v.toFixed(2) : '';
                    }},
                {title: '诉讼阶段', field: 'litigationPhase', width: 120, hidden: true},
                {title: '诉讼阶段', field: 'litigationPhaseTxt', width: 120},
                {title: '诉讼进展', field: 'litigationProgress', width: 120},
                {title: '仲裁阶段', field: 'arbitrationPhase', width: 120, hidden: true},
                {title: '仲裁阶段', field: 'arbitrationPhaseTxt', width: 150},

                // 法院与承办人
                {title: '收案法院', field: 'courtName', width: 150},
                {title: '承办人姓名', field: 'handlerName', width: 100},
                {title: '承办人电话', field: 'handlerPhone', width: 120},

                // 申报信息
                {title: '申报单位', field: 'applyUnit', width: 150},
                {title: '申报人', field: 'applicantName', width: 100},
                {title: '申报人电话', field: 'applicantPhone', width: 120},
                // 时间信息
                {title: '创建时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},
                // 其他信息
                {title: '是否网络舆情', field: 'isPublicOpinion', width: 100, formatter: function(v) {
                        return v === 'Y' ? '是' : '否';
                    }},
                {title: '处理意见', field: 'opinionRequirement', width: 200},
                {title: '案情经过', field: 'caseReasonProcess', width: 200}
            ]],
            rowStyler: function(index, row) {},
            onBeforeLoad: function(param) {},
            onLoadSuccess: function(data) {},
            url: '${base}/ui/amcs/law/civilCase/queryCivilCaseList' // 根据实际接口路径修改
        });

		$(".cont-grid").on("click",".s-op-review",function(e){
			var idx = $(this).attr('rel');
			var rowData = $("#gridBox").datagrid("getRows")[idx];
            let url = '${base}/ui/amcs/law/civilCase/info?bizId=' + rowData.id;
            let title = '民事诉讼仲裁案';
            $pop.newTabWindow(title, url,false);
		});


    });



</script>
</html>
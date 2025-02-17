<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title> 科室不良事件审核 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
        	<label class="lab-inline">患者姓名：</label>
            <input type="text" class="txt inline" name="patientName" value="">
            <label class="lab-inline">上报日期：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="reportDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}" />
			<label class="lab-inline">发生日期：</label>
			<input type="text" class="txt so-rangeDate txt-validate" name="eventDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {
    	$grid.newGrid("#gridBox",{
            pagination: true,
            fitColumns: false,
	    	columns:[[
				{title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
				        let opStr = '';
				          opStr = '<span class="s-op s-op-review icon-yinzng1" title="审核" rel="' + index + '"></span>';
						return opStr;
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
		        {title:'eventId',field:'eventId',width:80,hidden:true},
		        {title:'node',field:'node',width:80,hidden:true},
		        {title:'上报时间', field:'createDate', width:120, format:'yyyy-MM-dd'},
                {title:'发生时间',field:'eventDate',width:80,format:'yyyy-MM-dd'},
		        {title:'事件名称', field:'eventName', hidden: false, width:200},
                {title:'省区', field:'hospParent', hidden: false, width:80},
                {title:'医院名称', field:'hospName', width:150},
                {title:'事件分级', field:'eventLevel', hidden: false, width:100},
                {title:'事件分类一级', field:'gradeOne', hidden: false, width:150},
                {title:'事件分类二级', field:'gradeTwoA', hidden: true, width:150},
                {title:'患者姓名', field:'patientName', hidden: false, width:80},
                {title:'事件类型', field:'eventTypeName', width:120},
                {title:'上报人', field:'creatorName', hidden: false, width:80},
                {title:'上报人主岗科室', field:'ehrAiStandDeptidDescr', hidden: false, width:100},
                {title:'所在节点',field:'nodeName',width:110,formatter:function (v,row,index){
                        if(row.isArchived){
                            return "上报未审核(已归档)"
                        }else{
                            return v
                        }
                    }},
                {title:'上报次数',field:'maxReportTimes',width:80},
                {title:'是否完结', field:'finishSign', hidden: false, width:60,formatter: function (v, row, index) { if(v==1) return '是'; else return '否';}},
                {title:'状态',field:'status',width:80, formatter: function (v, row, index) {
                        if (v == 2) {
                            return "取消";
                        } else {
                            return "正常";
                        }
                    }
                },
                {title: '集团查阅',field: 'isGroupReview',width: 80 ,formatter: function (v,row,index){
                        return ['<span class="red">未查阅</span>','<span class="green">已查阅</span>'][v];
                    }},
                {title:'省区审核通过时间', field:'pAuditDate', width:105, format:'yyyy-MM-dd'},
		    ]],
            rowStyler : function(index, row) {
                if(row.isEhrDeptReview == 1){
                    return "background-color: #ABEBC6";
                }
            },
		    onBeforeLoad: function(param){
            },
		    onLoadSuccess : function (data) {
		    	$('.s-op-review').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
                    let urlSuffix;
                    debugger;
                    if(row.isEhrDeptReview && row.isEhrDeptReview!=''){
                        urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node + '&page_type=1&modify=true&isEhrDeptReview=1';
                    }else{
                        //if(typeof(row.isEhrDeptReview) == 'undefined' || row.isEhrDeptReview==null){}
                        urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node + '&page_type=1&modify=true&isEhrDeptReview=0';
                    }

					if(row.id != ''){
                        let title = row.eventTypeName;
                        let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
      		},
      		url: '${base}/ui/service/biz/amcs/adverse/dept/findListReview',
  		});
    });

   
</script>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title> 科室不良事件查询 - 爱尔医院</title>
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
				          opStr = '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
						return opStr;
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
		        {title:'eventId',field:'eventId',width:80,hidden:true},
		        {title:'node',field:'node',width:80,hidden:true},
		        {title:'上报时间', field:'createDate', width:120, format:'yyyy-MM-dd'},
                {title:'事件分类一级', field:'gradeOne', hidden: false, width:150},
                {title:'事件分类二级', field:'gradeTwoA', hidden: false, width:150},
		        {title:'事件名称', field:'eventName', hidden: false, width:200},
                {title:'上报人', field:'creatorName', hidden: false, width:80},
                {title:'患者姓名', field:'patientName', hidden: false, width:80},
                {title:'亚专科', field:'subspecialty', hidden: false, width:80},
                {title:'表单分类', field:'eventCode', hidden: false, width:80,formatter:function (v, row, index) {
                        if($.inArray(v, ['101','102','103','104','105','106','107','108'])>-1){
                            return '医疗类';
                        }
                        if($.inArray(v, ['201','202','203','204','205','206','207','208'])>-1){
                            return '护理类';
                        }
                        if($.inArray(v, ['301','302','303','304','305','306','307','308'])>-1){
                            return '院感类';
                        }
                }},
                {title:'患者性别', field:'patientSex', hidden: false, width:60,formatter: function (v, row, index) { if(v==1) return '男'; else return '女';}},
                {title:'患者年龄', field:'patientAge', hidden: false, width:60},
                {title:'事件分级', field:'eventLevel', hidden: false, width:100},
		        {title:'发生日期', field:'eventDate', width:120,format:'yyyy-MM-dd'},
                {title:'上报次数',field:'maxReportTimes',width:80},
                {title:'是否完结', field:'finishSign', hidden: false, width:60,formatter: function (v, row, index) { if(v==1) return '是'; else return '否';}},
                {title:'是否构成纠纷', field:'disputeSign', hidden: false, width:80,formatter: function (v, row, index) { if(v==1) return '是'; else return '否';}},
		    ]],
            rowStyler : function(index, row) {
            },
		    onBeforeLoad: function(param){
            },
		    onLoadSuccess : function (data) {
		    	$('.s-op-review').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node + '&page_type=9&showOperate=0&showLast=true';
					
					if(row.id != ''){
					    let title = '查看';
                        if($.inArray(row.eventCode, ['101','102','103','104','105','106','107','108'])>-1){
                            title = '医疗类查看';
                        }
                        if($.inArray(row.eventCode, ['201','202','203','204','205','206','207','208'])>-1){
                            title = '护理类查看';
                        }
                        if($.inArray(row.eventCode, ['301','302','303','304','305','306','307','308'])>-1){
                            title = '院感类查看';
                        }
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
      		},
      		url: '${base}/ui/service/biz/amcs/adverse/dept/findList',
  		});
    });

   
</script>
</html>
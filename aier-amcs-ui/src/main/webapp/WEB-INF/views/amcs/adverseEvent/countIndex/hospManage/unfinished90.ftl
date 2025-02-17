<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>未完结(超过90天)列表 - 爱尔医院</title>

[#include "/WEB-INF/views/common/include_resources.ftl"]
  <style type="text/css">
.searchHead{}
.cont-grid{overflow: hidden;zoom: 1;}
.cont-l{float: left;width:40%;margin:0 0 0 10px;}
.cont-r{float: right;width:58%;margin:0 10px 0 0;}
.navGridWrap{border:1px solid #ddd;border-top: 0;}
.detailsGridWrap{border-top: 0;}
.h2-title{line-height:25px;color: #00A0E9;}
.tree-file{margin-top: 3px;}
</style>
</head>

<body>
<!--<div class="searchHead bob-line">-->
<!--</div>-->
  <div class="cont-grid">
      <div id="gridBox"></div>
  </div>
</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {
 let parentParam = $store.get('parentDataCountIndex');
	if(!$.isEmptyObject(parentParam)){
	    parentParam.pageType = '${pageType}';
	    parentParam.rebackStatus = 1;
 };
 $grid.newGrid("#gridBox",{
  tools:[
        ],
          url:'${base}/ui/amcs/countIndex/unfinished90HospManageGrid',
	  	  queryParams: parentParam,
          idField:'id',
          fitColumns : false,
          pagination : true,
          // checkbox: true,
          // singleSelect : false,
          columns:[[
            {title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
			        let opStr = '';
			          opStr = '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
					return opStr;
				}
			},
            {title:'id',field:'id',width:80, hidden: true},
            {title:'事件名称',field:'eventname',width:350},
			{title:'姓名',field:'patientname',width:100},
			{title:'年龄(岁)',field:'patientage',width:60},
			{title:'就诊号',field:'patientno',width:130},
            {title:'上报人',field:'creatorName',width:80},
            {title:'上报时间',field:'createdate',width:150},
            {title:'发生时间',field:'eventdate',width:100, format: 'yyyy-MM-dd'}
          ]],
          onLoadSuccess : function (data) {
		    	$('.s-op-review').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventcode +'&id=' + row.id + '&node=' + row.node + '&page_type=9&showOperate=0';
					
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
          offset : 0
      });
});


</script>
</body>

</html>

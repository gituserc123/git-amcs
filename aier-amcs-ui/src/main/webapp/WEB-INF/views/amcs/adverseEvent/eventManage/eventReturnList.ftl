<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>住院患者不良事件 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input class="txt" name="pageType" value="1" type="hidden" />
            <input class="txt" name="rebackStatus" value="1" type="hidden" />
            <label class="lab-inline">事件类型：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="eventType" id="eventType" data-options="valueField:'eventCode',textField:'eventName',clearIcon:true">
            </select>
            <label class="lab-inline">上报日期：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="reportDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>
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
        this.reloadEventType();
        
         window.popMsg = function(msg, tmpid){
        	$pop.tips(msg,'#'+tmpid,{time: 0,closeBtn: true,maxWidth: 600});
        }

    	$grid.newGrid("#gridBox",{
	    	columns:[[
				{title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
				        return '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
		        {title:'eventCode',field:'eventCode',width:80,hidden:true},
		        {title:'eventId',field:'eventId',width:80,hidden:true},
		        {title:'node',field:'node',width:80,hidden:true},
		        {title:'页面地址', field:'eventUrl', width:120,hidden:true},
		        {title:'意见',field:'opinion',width:80,hidden:true},
		        {title:'事件名称', field:'eventName', hidden: false, width:200},
		        {title:'退回状态', field:'rebackName', width:200},
		        {title:'事件类型', field:'eventTypeName', width:120},
		        {title:'发生时间', field:'eventDate', width:120, format:'yyyy-MM-dd'},
		        {title:'上报人', field:'creatorName', width:80},
		        {title:'上报时间', field:'createDate', width:120, format:'yyyy-MM-dd'},
		        {title:'回退人', field:'auditName', width:80},
		        {title:'回退时间', field:'auditDate', width:120, format:'yyyy-MM-dd'},
		        {title:'回退节点', field:'nodeName', width:120},
		        {title:'退回意见', field:'tip', width:120,formatter: function(value,row,index){
		        				var tmpId = "tmp_opioin_"+index;
								return "<span style='cursor:pointer;color:blue;text-decoration:underline;' id='"+tmpId+"' onclick='popMsg(\""+row.opinion+"\",\""+tmpId+"\")'>审核意见</span>"
							}
				 }
		    ]],
		    onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {
		    	$('.s-op-review').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id +'&page_type=1' + '&showOperate=true&sourceType=1';
					if(row.id != ''){
					    let title = row.eventTypeName;
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
      		},
      		queryParams : {pageType: 1},
      		url: '${base}/ui/service/biz/amcs/adverse/common/findReturnList',
      		offset : -50
  		}); 
    
    });
    
    function reloadEventType() {
   		$ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',null,false,false).done(function (rst) {
    		$('#eventType').combobox('loadData', rst.rows);
    	});
    
    };
</script>
</html>
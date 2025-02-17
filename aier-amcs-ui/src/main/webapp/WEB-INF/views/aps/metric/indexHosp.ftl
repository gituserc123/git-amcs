<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>人资绩效指标定义表</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.searchHead{}
.sideBarBox,.mainContBox{top:50px;}
.mainfooter{padding:12px 10px 0;text-align: right;}
.cont-grid{overflow: hidden;zoom: 1;border-bottom: 5px solid #dfe2e6;}
.cont-l{float: left;width:40%;margin:0 0 0 10px;}
.cont-r{float: right;width:58%;margin:0 10px 0 0;}
.navGridWrap{border:1px solid #ddd;border-top: 0;}
.detailsGridWrap{border-top: 0;}
.h2-title{line-height:25px;color: #00A0E9;}
</style>
</head>

<body>
	<div class="searchHead bob-line">
      <form id="sbox" class="soform form-enter">
      	[#if hospId == 0]
 		  <label class="lab-inline">医院</label>
          <select id="hospId" name="hospId" style="width:200px;" required="true"></select>
		[/#if]
          <label class="lab-inline">指标启用标识</label>
          <select class="drop easyui-combobox w-90" id="usingSign" name="usingSign">
            <option value="1"  selected="selected">启用</option>
            <option value="0">停用</option>
          </select>
          
          <label class="lab-inline">是否配置</label>
          <select class="drop easyui-combobox w-90" id="pFlag" name="pFlag">
            <option value=""  selected="selected"></option>
            <option value="1" >是</option>
            <option value="0">否</option>
          </select>
          <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox1'}">查 询</button>
      </form>
	</div>

	<div class="sideBarBox">
	    <div class="sideTreeC">
	      <ul id="ul-kindTree"></ul>
	    </div>
	</div>
	
	<div class="mainContBox">
	    <div id="gridBox"></div>
	</div>

</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {
	var doSearch = true;
	var selectInst = {AHIS_HOSP:${hospId}}
	var selectNode = {id:'aps_config_1001'}
	[#if hospId == 0]
	var selectInst ={AHIS_HOSP:-1};
	[/#if]

	$('#ul-kindTree').tree({
    	animate : true,
     	lines : true,
     	url:'${base}/ui/amcs/aps/metric/getTree',
      	flatData: true,
		onClick : function (node) {
			selectNode = node;
			$grid.load('#gridBox',{typeCode:node.id});
		}
	});
	
	var editIndex = null;
	$grid.newGrid("#gridBox",{
        tools:[
          [ /* 
            {iconCls:'plus_sign',text:'新增',url:'${base}/ui/amcs/aps/metric/page/addMetric',popHeight:550,popWidth:900,title:'新增'},
           
            {iconCls:'edit',text:'编辑',url:'${base}/ui/amcs/aps/metric/detail?id={id}',notNull:'请 <strong class="red">选择</strong> 需要编辑的项！',popHeight:580,popWidth:990,title:'材料字典详情【集团端】'},
            
            
            {iconCls:'play22',text:'启用',check:true,url:'${base}/ui/amcs/aps/metric/enable?ids={id}',notNull:'请 <strong class="red">勾选</strong> 需要启用的一项或多项！', ajax:true,ajaxMsg:'你确定启用这些项吗？'},
            
            
        	{iconCls:'pause2',text:'停用',check:true,url:'${base}/ui/amcs/aps/metric/disable?ids={id}',notNull:'请 <strong class="red">勾选</strong> 需要停用的一项或多项！', ajax:true,ajaxMsg:'你确定停用这些项吗？'},
        	*/
          ]
          ],
          // fit: true,
          checkOnSelect : false,
          selectOnCheck : false,
          singleSelect : true,
          // ctrlSelect : true,
          // pagination : false,
          fitColumns : false,
          columns:[[
            {title:'id',field:'id',hidden:true},
            {title:'操作',field:'op',width:100,formatter : function(val,row,index){
                return '<span class="s-op s-op-run icon-play22" rel="'+row.id+'" title="计分规则">计分规则</span>'+
                  /* '<span class="s-op s-op-run icon-play22" rel="'+row.id+'" title="启用"></span>&nbsp; ' +
                  '<span class="s-op s-op-pause icon-pause2" rel="'+row.id+'" title="停用"></span>&nbsp; '+
                  '<span class="s-op s-op-export icon-file_excel" rel="'+row.id+'" title="导出"></span>'+ */
                  '';
              }},
              
            {title: "id", field: "id", hidden: true, width: 100},
			{title: "指标编码", field: "metricCode", hidden: false, width: 100},
			{title: "指标名称", field: "metricName", hidden: false, width: 150},
			 {title:'正式配置',field:'pFlag',width:60,formatter: function(val,row,index){
				 return val==="1"?'<button type="button" class="btn btn-small btn-primary so-search"  onclick=\'aierInit(1,"'+row.pRuleId+'")\'>有</button>':'<span class="red">无</span>';
            }},
			{title: "指标计算频率", field: "ruleFrequency", hidden: false, width: 100},
			{title: "指标计算拓展类型", field: "ruleExtType", hidden: false, width: 110},
			{title: "单位", field: "unitName", hidden: false, width: 60},
			//{title: "集团默认分值", field: "defaultPoint", hidden: false, width: 110},

            //{title:'正式配置',field:'pFlag1',width:60,formatter: function(val,row,index){
            //  return val==="1"?'<span class="green">有</span>':'<span class="red">无</span>';
            //}},
			{title: "备注", field: "remarks", hidden: false, width: 600,align:'left'},
			//{title: "修改人", field: "modifer", hidden: false, width: 100},
			//{title: "修改时间", field: "modifyDate", hidden: false, width: 140},
          ]],
          onLoadSuccess : function (data) {
              $('.s-op-edit').click(function(){
                var id = $(this).attr('rel');
                $pop.iframePop({
                  title : '编辑',
                  content : '${base}/ui/amcs/aps/metric/page/detail?id='+id, 
                  area : ['900px','280px']
                },'#gridBox');
              });

             $('.s-op-run').click(function(){
               var id = $(this).attr('rel');
              $pop.iframePop({
                title : '计分规则',
                content : '${base}/ui/amcs/aps/metric/page/ruleManageHosp?id='+id + '&hospId='+selectInst.AHIS_HOSP,
                area : ['99%','99%']
              },'#gridBox');
             });

              $('.s-op-pause').click(function(){
                var id = $(this).attr('rel');
                $ajax.post('${base}/ui/amcs/aps/metric/disable',{ids:id},'你确定停用这项吗？');
              });

              $('.s-op-export').click(function(){
                var id = $(this).attr('rel');
              });
          },
          onBeforeLoad : function(param){
          	param.usingSign = $('#usingSign').val();
        	param.pFlag = $('#pFlag').val();
        	param.typeCode = selectNode.id;
          	if($('#sbox').form('validate')){
	          	param.hospId = selectInst.AHIS_HOSP;
	          	if(param.hospId == -1){
	          		//$pop.alert('请先选择医院！')
	          		
	          		return false;
	          	};
	        }else{
	        	//$grid.loadData('#gridBox',[]);
	        	$('#gridBox').datagrid('loadData',[]);
	        	return false
	        }
          },
          url:'${base}/ui/amcs/aps/metric/getList',
          // height: 'auto',
          offset :-52
      });
      
    $('#hospId').combogrid({
		delay: 300,    	  
		mode: 'remote',   
		panelWidth:'480px', 
		clearIcon:true,
		fitColumns:true,  
		idField:'ID',   
		textField:'NAME', 
		url: '${base}/ui/amcs/aps/metric/autoComplete/getHosp',
        queryParams: {q:'${hospId}'},
		onSelect: function(v,record){
			selectInst = record;
			$grid.load('#gridBox',{});
		},
		onBeforeLoad: function(param){
			if(!param.q){return false}
		},
		columns:[[    
			{field:'NAME',title:'名称',width:'250px',align:'left',titletip:true},
			{field:'PARENT_NAME',title:'上级',width:'200px',align:'left',titletip:true},
		]]  
    });
      
  	window.aierInit = function(type, ruleId){
        $pop.iframePop({
            title : '规则明细维护',
            content : '${base}/ui/amcs/aps/hospRule/page/editRuleDetailHosp?ruleId='+ruleId+'&hospId='+selectInst.AHIS_HOSP,
            area : ['98%','98%']
          },'#gridBoxRule');
	}
      
      

});

</script>
</body>

</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>人资绩效计分规则表</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.searchHead{}
.sideBarBox{top:50px;width:100%}
.mainContBox{top:50px;left:100%;}
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
          <label class="lab-inline">状态：</label>
          <select class="drop easyui-combobox w-90" id="usingSign" name="usingSign">
            <option value="3" ></option>
            <option value="1" selected="selected">可用</option>
            <option value="0">禁用</option>
          </select>
          <label class="lab-inline">指标名称：</label>
        <input class="txt txt-validate required unedit" type="text" name="metricName" style="width:150px;" value="${bean.metricName}" readonly />
        <label class="lab-inline">计分拓展类型：</label>
        <input class="txt txt-validate required unedit" type="text" name="ruleExtTypeName" style="width:100px;" value="${bean.ruleExtTypeName}" readonly />
        <label class="lab-inline">计算频率：</label>
        <input class="txt txt-validate required unedit" type="text" name="ruleFrequencyName" style="width:60px;" value="${bean.ruleFrequencyName}" readonly />
   
          <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBoxRule',scope:'#sbox'}">查 询</button>
      </form>
	</div>

	<div class="sideBarBox">
	    <div id="gridBoxRule"></div>
	</div>
	
	<div class="mainContBox">
	    <div id="gridBoxDetail"></div>
	</div>

</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['easygridEdit','pub'],function () {
	var doSearch = true;
	
	$grid.newGrid("#gridBoxRule",{
        tools:[
          [
            {iconCls:'plus_sign',text:'新增规则',url:'${base}/ui/amcs/aps/rule/page/addRule?metricId=${id}',popHeight:300,popWidth:900,title:'新增规则'},
           /*  
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
          pagination : false,
          fitColumns : false,
          columns:[[
            {title:'id',field:'id',hidden:true},
            {title:'操作',field:'op',width:50,formatter : function(val,row,index){
                return '<span class="s-op s-op-edit icon-edit" rel="'+row.id+'" title="编辑"></span>'+
                '&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon icon-file_text_alt s-setting blue hand" title="编辑明细" rel="'+row.id+'"></span>'+
                  /* '<span class="s-op s-op-run icon-play22" rel="'+row.id+'" title="启用"></span>&nbsp; ' +
                  '<span class="s-op s-op-pause icon-pause2" rel="'+row.id+'" title="停用"></span>&nbsp; '+
                  '<span class="s-op s-op-export icon-file_excel" rel="'+row.id+'" title="导出"></span>'+ */
                  '';
              }},
              
            {title: "id", field: "id", hidden: true, width: 100},
			{title: "规则名称", field: "ruleName", hidden: false, width: 100},
			{title: "版本", field: "ruleVersion", hidden: false, width: 100},
			{title: "规则使用场景", field: "isDefaultRuleName", hidden: false, width: 100},
			{title: "生效时间", field: "ruleBeginDate", hidden: false, width: 130},
			//{title: "失效时间", field: "ruleEndDate", hidden: false, width: 100},
			{title: "修改人", field: "modifer", hidden: false, width: 100},
			{title: "修改时间", field: "modifyDate", hidden: false, width: 130},
			{title: "备注", field: "remarks", hidden: false, width: 400},
            //{title:'启用标识',field:'usingSign',width:60,formatter: function(val,row,index){
           //   return val?'<span class="green">启用</span>':'<span class="red">停用</span>';
           // }},
			//{title: "指标编码", field: "metricCode", hidden: false, width: 100},
			//{title: "指标名称", field: "metricCodeName", hidden: false, width: 100},
			//{title: "备注", field: "remarks", hidden: false, width: 300},
          ]],
          rowStyler : function(index, record) {
            if(record.isDefaultRule===1){
                return 'background-color:#06f351';
            }else if(record.isDefaultRule===2){
                return 'background-color:#ffcaca';
            }
          },
          onBeforeLoad : function(param){
          	if(!param.usingSign){
          		param.usingSign = '1'
          	}
          },
          onLoadSuccess : function (data) {
          	$('.s-op-edit').click(function(){
                var id = $(this).attr('rel');
                $pop.iframePop({
                  title : '编辑规则',
                  content : '${base}/ui/amcs/aps/rule/page/editRule?id='+id, 
                  area : ['900px','300px']
                },'#gridBoxRule');
              });

             $('.s-setting').click(function(){
               var id = $(this).attr('rel');
              $pop.iframePop({
                title : '规则明细维护',
                content : '${base}/ui/amcs/aps/rule/page/editRuleDetail?ruleId='+id,
                area : ['98%','98%']
              },'#gridBoxRule');
             });

              $('.s-op-pause').click(function(){
                var id = $(this).attr('rel');
                $ajax.post('${base}/ui/amcs/aps/metric/disable',{ids:id},'你确定停用这项吗？');
              });

              $('.s-op-export').click(function(){
                var id = $(this).attr('rel');
              });
          },
          url:'${base}/ui/amcs/aps/metric/getRuleVersion?metricId='+'${id}',
          // height: 'auto',
          offset :-52
      });
	
	var editIndex = null;
	$grid.newGrid("#gridBoxDetail",{
        tools:[
          [
            {iconCls:'plus_sign',text:'新增收费项目',url:'${base}/ui/amcs/aps/metric/page/addRuleVersion',popHeight:550,popWidth:900,title:'新增收费项目'},
           /*  
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
            {title:'操作',field:'op',width:160,formatter : function(val,row,index){
                return '<span class="s-op s-op-edit icon-edit" rel="'+row.id+'" title="编辑">编辑</span>'+
                	   '&nbsp;&nbsp;&nbsp;&nbsp;<span class="s-op s-op-run icon-play22" rel="'+row.id+'" title="计分规则">计分规则</span>'+
                  /* '<span class="s-op s-op-run icon-play22" rel="'+row.id+'" title="启用"></span>&nbsp; ' +
                  '<span class="s-op s-op-pause icon-pause2" rel="'+row.id+'" title="停用"></span>&nbsp; '+
                  '<span class="s-op s-op-export icon-file_excel" rel="'+row.id+'" title="导出"></span>'+ */
                  '';
              }},
              
            {title: "id", field: "id", hidden: true, width: 100},
			{title: "指标编码", field: "metricCode", hidden: false, width: 150},
			{title: "指标名称", field: "metricName", hidden: false, width: 150},
			//{title: "首拼码", field: "firstSpell", hidden: false, width: 100},
			{title: "指标计算频率", field: "ruleFrequency", hidden: false, width: 100},
			{title: "指标计算拓展类型", field: "ruleExtType", hidden: false, width: 110},
			{title: "修改人", field: "modifer", hidden: false, width: 100},
			{title: "修改时间", field: "modifyDate", hidden: false, width: 140},
            {title:'启用标识',field:'usingSign',width:60,formatter: function(val,row,index){
              return val?'<span class="green">启用</span>':'<span class="red">停用</span>';
            }},
			{title: "备注", field: "remarks", hidden: false, width: 300},
          ]],
          onBeforeLoad : function(param){
        	  if(!doSearch){
        		  doSearch = true;
        		  return false;
        	  }
        	  /* if(param.usingSign == null)
        		  param.usingSign = 1; */
          },
          onLoadSuccess : function (data) {
              
          },
          url:'${base}/ui/amcs/aps/metric/getList',
          // height: 'auto',
          offset :-52
      });

});

</script>
</body>

</html>

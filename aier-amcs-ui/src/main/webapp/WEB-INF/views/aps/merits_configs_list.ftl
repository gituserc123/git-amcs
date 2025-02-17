<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>绩效联合术式配置 - 爱尔医疗云平台</title>
<link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon" />
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.mainContBox .searchHead{margin-top:0;}
</style>
</head>
<body>
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
    	<label class="lab-inline">联合术式：</label> 
        <input class="txt inline w-200" easyui-combogrid" type="text" name="meritsLevelId" id="meritsLevelId"/>
		<label class="lab-inline">启停标识：</label>
        [@ui_select id="usingSign" name="usingSign" tag="com.aier.cloud.basic.api.domain.enums.UsingSignEnum"
        style="width:80px;" dataOptions="editable:false,limitToList:true" uiShowDefault="false"][/@ui_select]
        <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
    </form>
</div>
<div class="wrapper">
  <h2 class="h2-pageTitle">绩效联合术式配置</h2>
  <div>
    <div class="">
      <div id="gridBox"></div>
    </div>
  </div>
</div>
<div class="none"></div>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {
var popW = ($(window).width()-200)+'px';
  $(window).resize(function () {
    popW = ($(window).width()-200)+'px';
  });

var toolbars =
[
 [@shiro.hasPermission name = "ApsDcgMeritsConfigs:save"]
  [{iconCls:'plus_sign',text:'新增', click:function(){
	$pop.iframePop({
		  title:'新增-绩效联合术式配置',
		  content : 'create',
		  area : ['560px','360px']
		},['#gridBox']);
  }}],
 [/@shiro.hasPermission]
  []
];

  $grid.newGrid("#gridBox",{
    tools:toolbars,
    fitColumns : false,
    rownumbers : false,
    checkOnSelect : false,
    selectOnCheck : false,
    columns:[[
       {title:'id',field:'id',hidden:true},
       {title:'操作',field:'op',width:120,formatter: function (value,row,index) {
          return '[@shiro.hasPermission name = "ApsDcgMeritsConfigs:edit"]<span class="s-op s-op-edit icon-edit" rel="'+row.id+'" title="编辑"></span>　[/@shiro.hasPermission]';
       }}
      ,{title:'联合术式',field:'meritsLevelName',width:300,align:'left'}
      ,{title:'联合主手术分类',field:'mainOperName',width:200,align:'left'}
      ,{title:'联合次手术分类',field:'lessOperName',width:200,align:'left'}
      ,{title:'启停标识',field:'usingSign',align:'center',width:80,formatter: function (value,row,index) {
        	if (value=='1') {
        		return '启用';
        	} else{
        		return '停用';
        	}
	    }}
    ]],
    onLoadSuccess : function (data) {
      $('.s-op-edit').click(function () {
        var id = $(this).attr('rel');
        $pop.iframePop({
          title:'修改-绩效联合术式配置',
          content : 'update/'+id,
          area : ['620px','360px']
        },['#gridBox']);
      });
     
    },
    offset : -2,
    url:'${base}/ui/aps/meritsConfigs/page'
  });
  
  $('#meritsLevelId').combogrid({
        panelWidth: 600,
        pagination: true,
        pageSize:10,
        striped: true,
        clearIcon: true,
        idField: 'ID',
        textField: 'OPERATIONCLASSIFY',
        limitToList: true,
        minRemoteKeyLen: 1,
        mode: 'remote',//添加后不能发远程请求，不添加不能输汉字
        url: '${base}/ui/aps/meritsConfigs/query',
        queryParams: {q: '', rows: 10},
        columns: [[
		    {field:'OPERATIONCLASSIFY',title:'手术分类',width:220},
		    {field:'DISEASETYPENAME',title:'病种',width:120},
		    {field:'OPERATIONGRADE',title:'手术等级',width:80},
		    {field:'PRIORITY',title:'优先级',width:80},
		    {field:'ANESTHESIASIGN',title:'是否全麻+1',width:120,formatter: function (value,row,index) {
	        	if (value=='1') {
	        		return '是';
	        	} else{
	        		return '否';
	        	}
		    }}
        ]]
    });
});
</script>
</body>
</html>

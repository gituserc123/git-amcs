<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>手术项目对应手术分级配置 - 爱尔医疗云平台</title>
<link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon" />
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.mainContBox .searchHead{margin-top:0;}
</style>
</head>
<body>
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
    	<label class="lab-inline">诊疗项目名称：</label> 
        <input class="txt inline w-200" easyui-combogrid" type="text" name="operationItem" id="operationItem"/>
        <label class="lab-inline">诊疗项目编码：</label> 
        <input class="txt inline w-200" easyui-combogrid" type="text" name="operationCode" id="operationCode"/>
        <label class="lab-inline">手术等级：</label>
        <select id="operationGrade" class="easyui-combobox" name="operationGrade" style="width:100px;">  
            <option value="">-请选择-</option> 
		    <option value="1">1</option>   
		    <option value="2">2</option>
		    <option value="3">3</option>   
		    <option value="4">4</option>     
		 </select>
		 <label class="lab-inline">病种：</label>
        [@ui_select id="diseaseType" name="diseaseType" tag="performance_diseases"
           style="width:100px;" dataOptions="editable:false,limitToList:true" ][/@ui_select]
		 <label class="lab-inline">启停标识：</label>
          [@ui_select id="usingSign" name="usingSign" tag="com.aier.cloud.basic.api.domain.enums.UsingSignEnum"
           style="width:80px;" dataOptions="editable:false,limitToList:true" uiShowDefault="false"][/@ui_select]
        <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
    </form>
</div>
<div class="wrapper">
  <h2 class="h2-pageTitle">手术项目对应手术分级配置</h2>
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
 [@shiro.hasPermission name = "ApsDcgOperGrade:save"]
  [{iconCls:'plus_sign',text:'新增', click:function(){
	$pop.iframePop({
		  title:'新增-手术项目对应手术分级配置',
		  content : 'create',
		  area : ['700px','300px']
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
          return '[@shiro.hasPermission name = "ApsDcgOperGrade:edit"]<span class="s-op s-op-edit icon-edit" rel="'+row.id+'" title="编辑"></span>　[/@shiro.hasPermission]';
       }}
      ,{title:'诊疗项目编码',field:'operationCode',width:200,align:'left'}
      ,{title:'诊疗项目名称',field:'operationItem',width:300,align:'left'}
      ,{title:'手术等级',field:'operationGrade',width:80,align:'center'}
      ,{title:'手术分类',field:'operationClassify',width:200,align:'center'}
      ,{title:'人资病种分类 ',field:'diseaseTypeName',align:'center',width:80}
      ,{title:'财务末级分类 ',field:'financeTypeName',align:'center',width:200}
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
          title:'修改-手术项目对应手术分级配置',
          content : 'update/'+id,
          area : ['700px','300px']
        },['#gridBox']);
      });
     
    },
    offset : -2,
    url:'${base}/ui/aps/operGrade/page'
  });
});
</script>
</body>
</html>

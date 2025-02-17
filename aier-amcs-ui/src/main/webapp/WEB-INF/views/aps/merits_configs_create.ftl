<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>绩效联合术式配置 - 爱尔医疗云平台</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
</head>

<body>
<div class="pad-20">
  <form class="soform form-validate form-enter solab-l mar-t15 mar-b10" method="post" action="${base}/ui/aps/meritsConfigs/create">
    <div class="row">
      <div class="p12"><div class="item-one">
          <label class="lab-item">联合术式分类：</label>
          <input class="txt easyui-combogrid" type="text" name="meritsLevelId" id="meritsLevelId" style="width: 100%;"/>
      </div></div>
    </div>
    <div class="row">
      <div class="p12"><div class="item-one">
          <label class="lab-item">联合主手术分类：</label>
          <input class="txt easyui-combogrid" type="text" name="mainOperation" id="mainOperation" style="width: 100%;"/>
      </div></div>
    </div>
    <div class="row">
      <div class="p12"><div class="item-one">
          <label class="lab-item">联合次手术分类：</label>
          <input class="txt easyui-combogrid" type="text" name="lessOperation" id="lessOperation" style="width: 100%;"/>
      </div></div>
    </div>
    <div class="row">
      <div class="p12"><div class="item-one">
          <label class="lab-item">启停标识：</label>
          [@ui_select id="usingSign" name="usingSign" tag="com.aier.cloud.basic.api.domain.enums.UsingSignEnum"
           style="width:100%;" dataOptions="editable:false,limitToList:true" uiShowDefault="true" uiShowFirst="true"][/@ui_select]
      </div></div>
    </div>
    <p class="row-btn">
      <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存" />
      <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
    </p>
  </form>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
require(["editor", "pub"], function (editor) {
        $('#meritsLevelId').combogrid({
            panelWidth: 550,
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
			    {field:'OPERATIONGRADE',title:'手术等级',width:120},
			    {field:'PRIORITY',title:'优先级',width:120},
			    {field:'ANESTHESIASIGN',title:'是否全麻+1',width:120,formatter: function (value,row,index) {
		        	if (value=='1') {
		        		return '是';
		        	} else{
		        		return '否';
		        	}
			    }}
            ]]
        });
        
        $('#mainOperation').combogrid({
            panelWidth: 550,
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
			    {field:'OPERATIONGRADE',title:'手术等级',width:120},
			    {field:'PRIORITY',title:'优先级',width:120},
			    {field:'ANESTHESIASIGN',title:'是否全麻+1',width:120,formatter: function (value,row,index) {
		        	if (value=='1') {
		        		return '是';
		        	} else{
		        		return '否';
		        	}
			    }}
            ]]
        });
        
        $('#lessOperation').combogrid({
            panelWidth: 550,
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
			    {field:'OPERATIONGRADE',title:'手术等级',width:120},
			    {field:'PRIORITY',title:'优先级',width:120},
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


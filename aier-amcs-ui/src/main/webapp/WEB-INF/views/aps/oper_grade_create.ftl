<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>手术项目对应手术分级配置 - 爱尔医疗云平台</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
  <style>
    .disable{
        background-color: #f3f3f3;
        color: #999;
        border: 1px solid #d3d3d3;
        box-shadow: none;
        -webkit-box-shadow: none;
    }
  </style>
</head>
<body>
<div class="pad-20 pad-b0">
  <form class="soform form-validate form-enter solab-l mar-b10" method="post" action="${base}/ui/aps/operGrade/create">
    <div class="row">
      <div class="p6"><div class="item-one">
          <label class="lab-item">手术项目：</label>
          <input class="txt easyui-combogrid" type="text" name="operationItem" id="operationItem" style="width: 100%;"/>
      </div></div>
      <div class="p6"><div class="item-one">
          <label class="lab-item">手术编码：</label>
          <input class="txt disable" type="text" name="operationCode" id="operationCode" style="width: 100%;" readonly="true"/>
      </div></div>
    </div>
    <div class="row">
      <div class="p6"><div class="item-one">
          <label class="lab-item">手术分类：</label>
          <input class="txt easyui-combogrid" type="text" name="operationClassify" id="operationClassify" style="width: 100%;"/>
      </div></div>
      <div class="p6"><div class="item-one">
          <label class="lab-item">手术等级：</label>
		  <input class="txt disable" type="text" name="operationGrade" id="operationGrade" style="width: 100%;" readonly="true"/>
      </div></div>
    </div>
    <div class="row">
      <div class="p6"><div class="item-one">
          <label class="lab-item">病种：</label>
           [@ui_select id="diseaseType" name="diseaseType" tag="performance_diseases"
           style="width:100%;" dataOptions="readonly:true,editable:false,limitToList:true" uiShowDefault="false"][/@ui_select]
      </div></div>
      <div class="p6"><div class="item-one">
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
        $('#operationItem').combogrid({
            panelWidth: 600,
            panelMaxHeight:170,
            pagination: true,
            pageSize:10,
            striped: true,
            clearIcon: true,
            idField: 'groupServiceName',
            textField: 'groupServiceName',
            limitToList: true,
            minRemoteKeyLen: 1,
            mode: 'remote',//添加后不能发远程请求，不添加不能输汉字
            url: '${base}/ui/aps/operGrade/query',
            queryParams: {q: '', rows: 10},
            columns: [[
			    {field:'groupServiceName',title:'手术项目',width:220},
			    {field:'groupServiceCode',title:'手术编码',width:120},
			    {field:'financeCode',title:'财务编码',width:120},
			    {field:'groupPrice',title:'集团指导价',width:120}
            ]],
            onSelect: function (v, record) {
                $('#operationCode').val(record.groupServiceCode);
            }
        });
        
         $('#operationClassify').combogrid({
            panelWidth: 600,
            panelMaxHeight:170,
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
            ]],
            
            onSelect: function (v, record) {
                $('#operationGrade').val(record.OPERATIONGRADE);
                $('#diseaseType').combobox('setValue',record.DISEASETYPE);
            }
        });
});
</script>			
</body>
</html>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>省区手术分类统计 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input class="txt" type="hidden" name="surgType" id="surgType"  />

            <label class="lab-inline">省区：</label>
            [#if "${isGroup!}" == 1]
            	<select class="drop drop-sl-v easyui-combobox  w-150 province" name="provId"   data-options="valueField:'id',textField:'name',clearIcon:true"></select>
            [#else]
            	<input class="txt txt-validate w-150" type="text" name="hospName" value="${instName!}" readonly="readonly" data-options="editable:false"/>
            	<input type="hidden" name="provId" value="${instId!}" />
            [/#if]

            <label class="lab-inline">手术分类</label>
            <input class="drop easyui-combotree w-200 surgType" data-options="textField:'name', valueField:'code', flatData:true,multiple:true,onlyLeafCheck:false,panelWidth:'240px',panelMinHeight:'320px'" />
           
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
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
            $('.province').combobox('loadData', rst);
        });

        $ajax.postSync('${base}/ui/amcs/coll/hosp/docsurg/findOprTypeList', null, false, true).done(function (rst) {
            $('.surgType').combotree('loadData', rst);
        });

        $('.surgType').combotree({
            onChange: function(v){
                let codeValues = [];
                $.each(v, function(index, value){
                    let node = $('.surgType').combotree('tree').tree('find', value);
                    codeValues.push(node.code);
                });
                $("#surgType").val(codeValues);
            }
        });
        let url = "${base}/ui/amcs/coll/hosp/docsurg/findCountByProv";
        if('${isHosp}' == '1') {
        	url = '';
        };
        let param = {};
        if('${isGroup!}' == 0){
        	param = {'provId': '${instId}'};
        }
    	$grid.newGrid("#gridBox",{
            columns:[[
            {title:'省区', field:'provinceName', width:80},
            {title:'手术分类', field:'surgTypeName', width:120},
            {title:'授权人数', field:'count', width:80}
            ]],
            queryParams : param,
            url: url,
            offset : -50
  		});
    
   });


</script>
</html>
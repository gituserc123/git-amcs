<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>查看用户权限 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.roleTreeGridBox{}
.roleTreeGridBox .datagrid-row{height:20px;}
.roleTreeGridBox .datagrid-header-row{height:25px;}
.roleTreeGridBox .chk,.roleTreeGridBox .chk-all{margin-top:0;vertical-align: middle;width:16px;height:16px;}
.ul-powerTree .tree-file{margin-top:7px;}
</style>
</head>

<body>

<div class="roleTreeGridBox ul-powerTree">
    <div id="gridBox"></div>
</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {
    $grid.newGrid('#gridBox',{
    	pagination:false,
		url:'${base}/ui/sys/report/getStaffReport?id=${id}',
		idField:'id',
		fitColumns : false,
		flatData : false,
		columns:[[
		    {title:'报表名称',field:'NAME',width:'30%',align:'left'},
		    {title:'报表路径',field:'PATH',width:'70%',align:'left'},
		]],
		loadFilter : function (data) {
			var ndata = new Array();
			for(var i = 0; i < data.length; i ++){
	    		if(data[i].state){
	    			ndata.push(data[i])
	    		}
	    	}
	    	return ndata;
		},
		onLoadSuccess : function (row,data) {
		}
	});
});

</script>
</body>

</html>

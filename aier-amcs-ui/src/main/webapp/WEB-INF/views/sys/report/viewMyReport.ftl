<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>我的报表</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.cont-grid{overflow: hidden;zoom: 1;}
.pop-addOneItem {width: 496px;position:absolute;left:0px;top:-4000px;}
.layui-layer .pop-addOneItem {position: relative;top: 0;}
</style>
</head>
<div></div>
<body>
<div class="cont-grid">
		<div class="searchHead">
	          <form id="sbox" class="soform form-enter">
	                <input class="txt inline w-250" type="text" name="key" placeholder="报表名称/报表地址">
	                <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
	            </form>
	      </div>
	<div class="cont-grid">
		<div id="gridBox"></div>
	</div>
  </div>

</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
require(["pub"],function(){

    $grid.newGrid("#gridBox",{
        columns:[[
		  {title:'操作',field:'editPermit',width:'64px',formatter: function(value,row,index){
			  var chkHtml = '<span class="s-op icon-eye icon-edit" title="打开"></span>';
			  return chkHtml;
			}},
          {title:'编码',field:'TEXT',width:'200px',align:'left'},
          {title:'路径',field:'PATH',width:'250px',align:'left'},
          {title:'中文名称',field:'NAME',width:'200px',align:'left'},
          {title:'参数',field:'PARAM',width:'580px',align:'left'},
          {title:'上次修改时间',field:'MODIFYDATE',width:'200px'}
        ]],
        onLoadSuccess : function (data) {
          var formData = data.rows;
        },
        url:'${base}/ui/sys/report/getMyReport',
        pagination:true,
        // height: 'auto',
        offset :0
    });

    //绑定修改事件
    $('.cont-grid').on('click','.icon-eye',function() {
		var row = $('#gridBox').datagrid('getSelected');
		var title = row.NAME;
		if(!title || title == ''){
			title = row.TEXT;
		}
		$pop.newTabWindow(title, 'fr?url='+row.TEXT+'&'+row.PARAM)
          return false;
      });


});

</script>
</body>

</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>编辑用户报表权限</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]

<style type="text/css">
.treeWrap{position:relative;height:360px;overflow:auto;padding:0 10px;}
</style>
</head>

<body>
<div class="wrapper">
<div class="cont-grid">
	<div class="item-group pad-10">
		<input class="txt txt-search easyui-textbox" style="width:250px;" type="text" name="key" value="" data-options="clearIcon:true" /><button type="button" class="btn btn-small btn-primary btn-search">搜索</button>
		<label class='lab-val'><input type='checkbox'  id='all' value='' /> 全选</label>
	</div>
	<div class="treeWrap">
	    <ul id="tree"></ul>
	  </div>
</div>
<p class="row-btn center bot-line-sub">
  <input type="button" class="btn btn-primary btn-submitRole" name="btnSubmit" value="保 存" />
  <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
</p>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
requirejs(['pub'],function () {
	
	var isEdit = '${isEdit}'
	$('.btn-search').click(function (){
	  var v = $('.txt-search').textbox('getValue');
	  $("#tree").tree("search", v);
	});
	
    $('#all').click(function () {
		var roots = $('#tree').tree('getRoots');
		var val = $('#all').prop('checked')
		for(var i in roots){
			if(val){
				$('#tree').tree('check', roots[i].target);
			}else{
				$('#tree').tree('uncheck', roots[i].target);
			}
		}
    });

	$('#tree').tree({	
		animate : false,
		lines : true,
	    checkbox: true,
        onlyLeafCheck : true,
        //flatData:true,
		url:'${base}/ui/sys/reportGroup/getReportGroupHosp?id=${id}',
		onBeforeSelect: function (node) {
		},
		onClick : function (node) {
		},
		onSelect: function (node) {
			var cknodes = $('#tree').tree("getChecked");
			if (node.checked) {
				$('#tree').tree('uncheck', node.target);
			} else {
				$('#tree').tree('check', node.target);
			}
		},
		onLoadSuccess: function (node, data) {
		}
	});
        $('.btn-submitRole').click(function () {
        	var checked = $('#tree').tree('getChecked');
            var chkIds = [];
            $.each(checked,function (i,v) {
              chkIds.push(v.id);
            });
            
            var sendData = {
		     	id : '${id}',
		     	ids : chkIds
		    };
		    
            $ajax.post('${base}/ui/sys/reportGroup/updateReportPerm',JSON.stringify(sendData),true, true).done(function (rst) {
                setTimeout(function () {
		           $pop.closePop({refreshGrid:true});
		          }, 400);
            });

        });
    });
</script>

</html>

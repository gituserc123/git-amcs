<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>报表配置</title>
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
	          		[#if !shiroUser.isHosp]
	          		<label class="lab-inline">医院/总部：</label>
					<select id="institution" name="id" style="width:150px;"></select>
					[/#if]
					<label class="lab-inline">报表名称</label>
	                <input class="txt inline w-250" type="text" name="key" placeholder="报表名称">
	                <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
	            </form>
	      </div>
	<div class="cont-grid">
		<div id="gridBox"></div>
	</div>
  </div>


	<div class="pop-addOneItem">
		<form id="updateForm" class="soform form-validate form-enter pad-t30" method="post"
			action="" data-opt="">
			<input name="id" type="hidden" />
			<input name="text" type="hidden" />
			<div class="row">
				<div class="p11">
					<div class="item-one">
						<label class="lab-item">编码：</label> 
						<input class="txt" name="text" type="text" readonly="readonly" disabled="disabled"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="p11">
					<div class="item-one">
						<label class="lab-item">路径：</label> 
						<input class="txt" name="path" type="text" readonly="readonly" disabled="disabled"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="p11">
					<div class="item-one">
						<label class="lab-item">中文名称：</label> 
						<input class="txt txt-validate" id="name" type="text" name="name" maxLength="50"  />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="p11">
					<div class="item-one">
						<label class="lab-item">参数：</label> 
						<input class="txt txt-validate" id="param" type="text" name="param" maxLength="200"  />
					</div>
				</div>
			</div>
			<!-- <div class="row">
				<div class="p11">
					<div class="item-one">
						<label class="lab-item">更新同类报表：</label> 
						<input type="checkbox" class="chk chk-module" name="type" checked="checked"/>
					</div>
				</div>
			</div> -->
			<p class="row-btn center">
				<input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="确定" />
				<input type="button" class="btn btn-closePop" name="btnCancel" value="取 消" />
			</p>
		</form>
	</div>

</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
require(["pub"],function(){

    $grid.newGrid("#gridBox",{
        // method:'post',
        columns:[[
          [@shiro.hasPermission name = "Report:edit"]
		  {title:'编辑',field:'editPermit',width:'64px',formatter: function(value,row,index){
			  var chkHtml = '<span class="s-op s-op-edit icon-edit" title="修改"></span>';
			  return chkHtml;
			}},
  		  [/@shiro.hasPermission]
          {title:'中文名称',field:'name',width:'200px',align:'left',titletip:true},
          {title:'编码',field:'text',width:'200px',align:'left',titletip:true},
          {title:'路径',field:'path',width:'250px',align:'left',titletip:true},
          {title:'参数',field:'param',width:'200px',align:'left',titletip:true},
          {title:'医院名称',field:'hospName',width:'280px',align:'left',titletip:true},
          {title:'上次修改人',field:'modiferName',width:'80px',titletip:true},
          {title:'上次修改时间',field:'modifyDate',width:'200px',titletip:true},
        ]],
        onLoadSuccess : function (data) {
          var formData = data.rows;
        },
        url:'${base}/ui/sys/report/getList',
        pagination:true,
        // height: 'auto',
        offset :0
    });
    
    $('#institution').combogrid({
		delay: 500,    	  
		mode: 'remote',   
		panelWidth:250,  
		fitColumns:true,  
		clearIcon:true,
		idField:'ID',   
		textField:'NAME', 
		url:'${base}/ui/sys/autoComplete/query',
		queryParams: {
			tag:'sys.institution',   
		},
		onSelect: function(v,record){
		},
		columns:[[    
			{field:'NAME',title:'名称',width:100},
			{field:'SHORT_NAME',title:'简称',width:60}
		]]  
	});
    
    var id;
	function open(row){
		$pop.popForm({
			title: '编辑',
			target : $('.pop-addOneItem'),
			area : ['500px','300px'],
			beforePop : function ($formBox) {
				$('#updateForm').form('load',row);
			},
			beforeSubmit : function (formData,$form,popid) {
			    var  data = $form.sovals();
			    var param = {};
			    param.detail = data.param;
			    param.name = data.name;
			    param.id = data.id;
			    param.type = data.type
			    param.code = data.text;
			    $ajax.post('${base}/ui/sys/report/updateReport',JSON.stringify(param), true, true).done(function(rst){
			    	$('#gridBox').datagrid('reload');    
			        layer.close(popid);
			    });          
			
			}
	    });
	}
	
	function getValue(confCode){
    	if(Object.prototype.toString.call(confCode)=='[object Array]'){
    		return confCode[0];
    	}else{
    		return confCode;
    	}
    }

    window.checkConfig = function (value) {
// 		var rt;
//     	$ajax.postSync('${base}/ui/based/config/check',{id:id, value:value}).done(function (rst) {
//    			rt = rst.data;
//    	    });
//         return rt;
    }

    //绑定修改事件
    $('.cont-grid').on('click','.s-op-edit',function() {
		var row = $('#gridBox').datagrid('getSelected');
		id = row.id;
		  open(row);
          return false;
      });




});

</script>
</body>

</html>

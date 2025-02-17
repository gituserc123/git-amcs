<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>区卫数据上报日志</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.cont-grid{overflow: hidden;zoom: 1;}
.pop-addOneItem {width: 496px;position:absolute;left:0px;top:-4000px;}
.layui-layer .pop-addOneItem {position: relative;top: 0;}
.showContent .layui-layer-content{margin:25px 0 10px;padding:0px 10px 5px 15px;max-height:400px;max-width:600px;}
.showContent .contentbox{}
</style>
</head>
<div></div>
<body>
<div class="cont-grid">
		<div class="searchHead">
	          <form id="sbox" class="soform form-enter">
	          <label class="lab-inline">区卫中心：</label>
	          [@ui_select id="centerCode" style="width:250px;" name="code" class="easyui-combobox drop " valueField="centerCode" textField="centerName" filterkey="" url="${base}/ui/sys/interfaceJob/getCenterCode"  dataOptions="limitToList:true,reversed:true,clearIcon:true,required:true,
		          	onLoadSuccess: function (data) {
						if (data && data.length>0) {
						   $('#centerCode').combobox('setValue',data[0].centerCode);
						}
					},
					onSelect: function (r) {
						if (r) {
						   $('#hospId').combobox('reload','${base}/ui/sys/interfaceJob/getHospId?code='+r.centerCode);  
						}
					},
	          "/]

	          <label class="lab-inline">对接医院：</label>
	          [@ui_select id="hospId" style="width:200px;" name="id" class="easyui-combobox drop " valueField="hospId" textField="hospName" filterkey="hospName" url="${base}/ui/sys/interfaceJob/getHospId"  dataOptions="limitToList:true,reversed:true,required:true,
      		        onLoadSuccess: function (data) {
						if (data && data.length>0) {
						   $('#hospId').combobox('setValue',data[0].hospId);
						}
					},
	          "/]
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
		  {title:'操作',field:'editPermit',width:'60px',formatter: function(value,row,index){
		  	var chkHtml = '';
		  	
			  	chkHtml += '<span ref="'+row.jobClass+'" class="s-op s-op-ig  icon-eye" title="查看sql"></span>&nbsp;&nbsp;&nbsp;&nbsp;';
			  	chkHtml += '<span ref="'+row.jobClass+'" ref1="'+row.hospId+'" ref2="'+row.jobName+'" class="s-op s-op-edit  icon-rocket2" title="手动调度"></span>&nbsp;&nbsp;&nbsp;&nbsp;';
			  return chkHtml;
			}},
          //{title:'区卫中心编码',field:'centerCode',width:'80px',titletip:true},
          {title:'区卫中心名称',field:'centerName',width:'200px',titletip:true,align:'left'},
          {title:'医院ID',field:'hospId',width:'60px',titletip:true},
          {title:'医院名称',field:'hospName',width:'180px',titletip:true,align:'left'},
          {title:'接口名称',field:'jobName',width:'200px',titletip:true,align:'left'},
          {title:'接口方法',field:'jobClass',width:'370px',titletip:true,align:'left'},
          {title:'序号',field:'jobOrder',width:'60px',titletip:true},
        ]],
        rowStyler : function(index, record) {
        },
        onBeforeLoad: function (param) {
            if (!param.id) {
                return false;
            }
        },
        onLoadSuccess : function (data) {
			//绑定修改事件
			$('.s-op-edit').on('click',function() {
				var jobClass = $(this).attr('ref');
				var hospId = $(this).attr('ref1');
				var jobName = $(this).attr('ref2');
              $pop.iframePop({
                title :'作业手动调度',
                content : '${base}/ui/sys/interfaceJob/jobRun?jobClass='+jobClass+'&hospId='+hospId+'&jobName='+encodeURI(jobName), //加载用户已授权角色
                area : ['650px','400px']
              },'#gridBox-1');
			});
			
			$('.s-op-ig').on('click',function() {
				var jobClass = $(this).attr('ref');
				$ajax.postJson('${base}/ui/sys/interfaceJob/getSql?jobClass='+jobClass ,{}).done(function(rst){
					$pop.open({
		                skin : 'showContent',
		                title : null,
		                shadeClose : true,
		                btn : null,
		                area : ['auto','auto'],
		                content : '<div class="contentbox"><pre>'+rst.data+'</pre></div>'
		            });
				}); 
			});
        },
        queryParams : $('#sbox').sovals(),
        url:'${base}/ui/sys/interfaceJob/getJobList',
        onClickCell : function(index, field, value){
	        if(field=='jobExecParam' || field=='jobExecResult'|| field=='oldJson'){
	        	var vv = value;
	        	try{
		        	vv = JSON.parse(value)
		        	vv = JSON.stringify(vv, null, 4);
		        }catch(err){
		        }
	            $pop.open({
	                skin : 'showContent',
	                title : null,
	                shadeClose : true,
	                btn : null,
	                area : ['auto','auto'],
	                content : '<div class="contentbox"><pre>'+vv+'</pre></div>'
	            });
	        }
	    },
        pagination:false,
        // height: 'auto',
        offset :0
    });
    



});

</script>
</body>

</html>

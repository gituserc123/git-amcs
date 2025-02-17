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
				<label class="lab-inline">执行时间：</label>
            <input class="txt txt-validate txt-rangeDate w-185" type="text" name="date" value="" />
	          <label class="lab-inline">状态：</label>
       	 	  [@ui_select value="" name="type" tag="com.aier.cloud.basic.api.domain.enums.JobEnums$JobStatus" class="drop comb-dept easyui-combobox required" style="width:120px;" required=false dataOptions="editable:false"/]
             <label class="lab-inline">接口名称：</label><input class="txt inline w-250" type="text" name="name" placeholder="请输入接口名称">
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

    function searchGrad (){
        sData = $('#sbox').sovals();
        // sData[sysType+'OnlineDateBegin'] = sData.onlineDateBegin;
        // sData[sysType+'OnlineDateEnd'] = sData.onlineDateEnd;
       $('#gridBox').datagrid('load',sData);
    };

    $form.rangeDate('.txt-rangeDate',{
        val:'',
        opens:'center',
        auto : false,
        init : function (s,e,label) {
            searchGrad();
        }
    });

    $grid.newGrid("#gridBox",{
        columns:[[
		  {title:'操作',field:'editPermit',width:'60px',formatter: function(value,row,index){
		  	var chkHtml = '';
			  	chkHtml += '<span ref="'+row.jobClass+'" class="s-op s-op-ig  icon-eye" title="查看sql"></span>&nbsp;&nbsp;&nbsp;&nbsp;';
		  	if(row.jobStatus == 0 || row.jobStatus == 2){
			  	chkHtml += '<span ref="'+row.id+'" class="s-op s-op-edit  icon-rocket2" title="手动调度"></span>&nbsp;&nbsp;&nbsp;&nbsp;';
			  }
			  return chkHtml;
			}},
          //{title:'区卫中心编码',field:'centerCode',width:'80px',titletip:true},
          {title:'区卫中心名称',field:'centerName',width:'200px',titletip:true,align:'left'},
          {title:'医院ID',field:'hospId',width:'60px',titletip:true},
          {title:'医院名称',field:'hospName',width:'180px',titletip:true,align:'left'},
          {title:'接口名称',field:'jobName',width:'200px',titletip:true,align:'left'},
          //{title:'序号',field:'jobOrder',width:'30px',titletip:true},
          {title:'最后执行时间',field:'jobDate',width:'150px',titletip:true},
          {title:'状态',field:'jobStatusName',width:'80px',titletip:true},
          {title:'耗时(毫秒)',field:'jobExecTime',width:'80px',titletip:true},
          {title:'执行参数<b class="orange">(点击查看详情)</b>',field:'jobExecParam',width:'200px',align:'left'},
          {title:'日志详情<b class="orange">(点击查看详情)</b>',field:'jobExecResult',width:'200px',align:'left'},
          {title:'手动同步人',field:'updatorName',width:'130px'},
          //{title:'手动同步时间',field:'updateDate',width:'150px',titletip:true},
        ]],
        rowStyler : function(index, record) {
            if(record.jobStatus== 0){
                return 'background-color:#ACD6FF';
            }else if(record.jobStatus== 2){
                return 'background-color:#FFD2D2';
            }
        },
        onLoadSuccess : function (data) {
			//绑定修改事件
			$('.s-op-edit').on('click',function() {
				//var row = $('#gridBox').datagrid('getSelected');
				var id = $(this).attr('ref');
				$ajax.postJson('${base}/ui/sys/interfaceJob/updateValue?id='+id,{},"确定立即上报?").done(function(rst){
					$grid.reload('#gridBox');
				}); 
			});
			
			$('.s-op-ig').on('click',function() {
				//var row = $('#gridBox').datagrid('getSelected');
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
        url:'${base}/ui/sys/interfaceJob/getForList',
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
        pagination:true,
        // height: 'auto',
        offset :0
    });
    



});

</script>
</body>

</html>

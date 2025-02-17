<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
<title>传染病查询-爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.cont-grid{overflow: hidden;zoom: 1;}
.cont-l{width:820px;}
.cont-r .detailsGridWrap{margin-left:820px;}
.navGridWrap{border-right: 5px solid #dfe2e6;}
.navGridWrap .datagrid-body{overflow-x: hidden;}
.detailsGridWrap{}
.datagrid{border-top: 1px solid #ddd;}
.cont-grid .panel-htop{border:1px solid #ddd;border-top: 0;}
.soform input.so-rangeDate{width:180px;}
</style>
</head>

<body>    
[#include "/WEB-INF/views/common/include_js.ftl"]

<div class="searchHead bob-line"> 
     <form id="sbox" class="soform form-enter">
     <span class="fl">
         <label class="lab-val" style="margin-left:50px"> 疾病诊断：</label>
             <input class="drop easyui-combotree w-100" data-options=" flatData:true,multiple:true,onlyLeafCheck:false,panelWidth:'220px',panelMinHeight:'320px'" type="text" id="diseasecode" name="diseasecode" />
      
		 <!-- input class="drop easyui-combotree diseasecode w-220" id="diseasecode" name="diseasecode" />  -->
	  </span>
      <span class="fl">
       <label class="lab-inline">日期：</label>
        <input type="text" class="txt so-rangeDate" name="time" data-opt="{opens:'left',val:'week'}"/>
       </span>
      <span class="fl">
      
        <label class="lab-inline">报告状态：</label>  
		  <select class="drop easyui-combobox w-100 "  id="status" name="status" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${auditstate}"> </select> 
   </span>
      <span class="fl">
   
    	<label class="lab-inline">患者姓名</label>
		<input class="txt w-100" type="text" name="patientname" id="patientname"    />
		  </span>
      <span class="fl">
   
       	<label class="lab-inline">证件号码</label>
		<input class="txt w-140" type="text" name="idcardcode" id="idcardcode"  />
		  </span> 
		  [@shiro.hasPermission name = "AmcsIDRsearch:view"]
        <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox',scope:'#sbox'}"> 查询</button> 
      [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:audit"]
      
       <button type="button" class="btn btn-small btn-primary btn-audit"  >
            审核
        </button>
       [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:auditUn"]
  <button type="button" class="btn btn-small btn-primary btn-auditUn"  >
            反审核
        </button>
       [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:delete"]
      <button type="button" class="btn btn-small btn-primary  btn-del"  >
            删除
        </button> 
        [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:upload"]
   <button type="button" class="btn btn-small btn-primary btn-upload"  >
          上传初报
        </button>
         [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:uploadMod"]
  <button type="button" class="btn btn-small btn-primary btn-uploadMod"  >
          上传修订
        </button> 
         [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:uploadAud"]
  <button type="button" class="btn btn-small btn-primary btn-uploadAud"  >
           上传审核
        </button>
         [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:uploadRes"]
  <button type="button" class="btn btn-small btn-primary btn-uploadRes"  >
          上传恢复
        </button>
         [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:uploadDel"]
  <button type="button" class="btn btn-small btn-primary btn-uploadDel"  >
         上传删除
        </button> 
		   [/@shiro.hasPermission]
        [@shiro.hasPermission name = "AmcsIDRsearch:uploadSeach"]
   <button type="button" class="btn btn-small btn-primary btn-uploadSeach"  >
         上传查询
        </button> 
	   [/@shiro.hasPermission] 
 </form>
</div> 
 
 
        <div id="gridBox"></div>
    
  


<script type="text/javascript">
require(["pub"],function(){
	    var curRow = null;
	    $("#status").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AuditStateEnum"});
	
		$('#diseasecode').combotree({
			idFiled: 'valueCode',
			parentField: 'leveles',
			textFiled: 'valueDesc',
			//animate : true, 
			///multiple:true,
			//flatData:true,multiple:true,onlyLeafCheck:false,
	       // panelWidth:'200px',
	       // panelHeight:'auto',
	       // panelMaxHeight:'200px',
	       // lines : true,
	       // clearIcon :true,
	       // onlyLeafCheck : true,
	       // flatData: true,
	        url:'${base}/ui/amcs/idr/amcsIdrDictType/getTypeCodeForList?typeCode=DiseaseName',
	         
		});
	   

      $grid.newGrid("#gridBox",{
          // fitParent: true,
           
          pagination : false,
          fitColumns : true,
           checkOnSelect : true,
           selectOnCheck : true,
            singleSelect : false,
            // ctrlSelect : true,
            pagination : false,
            // showFooter : true,
           
            remoteSort: false,
          columns:[[
           {title:'id',field:'id',checkbox: true},
            {title:'操作',field:'op',width:40,formatter:function (value,row,index) {
              return '<span class="icon icon-eye s-showDetails blue hand" title="查看" rel="'+row.id+'" ></span>'
            }},
           
            {title:'卡片ID',titletip:true,field:'cardid',width:100,titletip:true},
            {title:'状态',field:'nationname',width:30,titletip:true,titletip:true},
            {title:'姓名', field:'patientname',width:60},
            {title:'出生日期',field:'birthdate',format:'yyyy-MM-dd',titletip:true,width:40},
            {title:'性别',field:'gendername',titletip:true,width:20},
            {title:'现住址',field:'livingaddressattributionname',titletip:true,width:100,titletip:true},
            {title:'报告单位',field:'orgname',titletip:true,width:140,titletip:true},
            {title:'疾病诊断',field:'diseasename',width:40,titletip:true  },
            {title:'病例分类',field:'caseclassificationname',width:30,titletip:true},
            {title:'发病日期',field:'onsetdate',format:'yyyy-MM-dd',width:40,titletip:true},
            {title:'诊断日期',field:'diagnosisdate',format:'yyyy-MM-dd',width:40,titletip:true},
            {title:'报告日期',field:'cardfillingtime',format:'yyyy-MM-dd',width:40,titletip:true},
            {title:'XML流水号',field:'eventid',width:40,titletip:true  }
           
       ]],
          queryParams : $('#sbox').sovals(),
          
      
         onBeforeLoad : function(param){ 
          	if(!param.timeBegin ){
    	  		return false;
    	 	  }
              if(param.diseasecode && Array.isArray(param.diseasecode)){
                  param.diseasecode = param.diseasecode.join(',');
              } 
           	
          }, 
          onLoadSuccess : function (data) {
            $('.s-showDetails').click(function () {//查看详情
              var rel = $(this).attr('rel');
              $pop.iframePop({
                title : '传染病上报',
                content : '${base}/ui/amcs/idr/amcsIDReditor/getAmcsIDReditor?id='+rel,
                end : function(){
                	$('#gridBox').datagrid('reload');
                }
              });
            });
         
          },
          
          url:'${base}/ui/amcs/idr/amcsIDRsearch/getMainByCondForList',
          // height: 'auto',
          offset :0
      });

   
      
      

      $('.btn-audit').click(function () { //调用审核
              //window.console && console.log($('.btn-audit').text);
     	      // debugger; 
      	  prepareData("审核","auditById");

        });
 	$('.btn-auditUn').click(function () { //调用 
      	  prepareData("反审核","auditUn");
        });
      $('.btn-del').click(function () { //调用 
      	  prepareData("删除","del");

        });
     $('.btn-upload').click(function () { //调用 
      	  prepareData("上传初报","upload");

        });
     $('.btn-uploadMod').click(function () { //调用 
      	  prepareData(" 上传修订","uploadMod");

        });
     $('.btn-uploadAud').click(function () { //调用 
      	  prepareData("上传审核","uploadAud");

        });
     $('.btn-uploadRes').click(function () { //调用 
      	  prepareData("上传恢复","uploadRes");

        });
     $('.btn-uploadDel').click(function () { //调用 
      	  prepareData("上传删除","uploadDel");

        });
      $('.btn-uploadSeach').click(function () { //调用 
      	  prepareData("上传查询","uploadSeach");

        });
 
     var prepareData = function(sName,sUrl){ 
            var checkedRows = $('#gridBox').datagrid('getChecked'); 
            if(checkedRows.length == 0){
                $pop.msg("请选择患者！");
                return false;
            } 
            var reqArr = [];
			 for(var i = 0;i<checkedRows.length;i++) { 
			 	reqArr.push(checkedRows[i].id);
			 
			 }
			reqArr = reqArr.join(',');
            $pop.confirm('是否批量'+sName+'？',function () {
            
               $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/'+sUrl,{id:reqArr}).done(function (rst) {
             			debugger; 
             		 $('#gridBox').datagrid('reload');
               });
            });
  		 }
   

});

</script>
</body>

</html>

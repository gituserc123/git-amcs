<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>授权管理</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.mainContBox .searchHead{margin-top:0;padding:10px 0 5px 10px;}
.likeTabs{width:auto;}
.likeTabs .tab-last{border-right:0;}
.tabs li a.tabs-inner{padding:0 45px;}

.tabCont{position: absolute;left:0;right:0;top:34px;bottom:5px;overflow: hidden;}
.tabContHide{visibility: hidden;}
.cont-grid{overflow: hidden;zoom: 1;border-bottom: 5px solid #dfe2e6;}
.h2-title{line-height:25px;color: #00A0E9;}
.h2-title-a{background-color: #f9f9f9;}
.btn-dataSync,.btn-dataSync:focus{position: absolute;top:4px;right:5px;}

.el-tag-open {
    background-color: rgba(103,194,58,.1);
    border-color: rgba(103,194,58,.2);
    color: #67c23a;
    display: inline-block;
    padding: 0 10px;
    height: 22px;
    line-height: 20px;
    font-size: 12px;
    border-radius: 4px;
    box-sizing: border-box;
    border: 1px solid rgba(103,194,58,.2);
    white-space: nowrap;
}
.el-tag-closed {    
	background-color: rgba(245,108,108,.1);
    border-color: rgba(245,108,108,.2);
    color: #f56c6c;
    display: inline-block;
    padding: 0 10px;
    height: 22px;
    line-height: 20px;
    font-size: 12px;
    border-radius: 4px;
    box-sizing: border-box;
    border: 1px solid rgba(103,194,58,.2);
    white-space: nowrap;
}

</style>
</head>

<body>
    <ul class="tabs likeTabs">
      <li class="tabs-first tabs-selected" rel="0"><a href="#" class="tabs-inner"><span class="tabs-title">按医院</span></a></li>
      <!-- <li  rel="1"><a href="#" class="tabs-inner"><span class="tabs-title">按医院</span></a></li> -->
    </ul>
    [@shiro.hasPermission name = "PlatformInst:edit"]
    <button id="reg" type="button" class="btn btn-small btn-primary btn-dataSync">注册机构</button>
    [/@shiro.hasPermission]
    <div class="tabCont tabCont-0">
          <div class="searchHead">
              <form id="sbox2" class="soform form-enter">
                    <input type="hidden" class="txt" name="init" value="1" />
                    <input class="txt inline w-150" type="text" name="key" placeholder="名称、AHIS编号">
                    <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox-1',scope:'#sbox2'}">查 询</button>
                </form>
          </div>
          <div id="gridBox-1"></div>
    </div>
    
    <!-- <div class="tabCont tabCont-1 tabContHide">
          <div id="gridBox-0"></div>
     </div> -->

</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
requirejs(['pub'],function () {

  var tabIndex = 0;
  var tabInit = ['',''];
  var $roleId = $('#roleId');
  var $txtDeptTree = $('#txt-deptTree');
  var $txtDeptTreeStaff = $('#txt-deptTree-staff');
  var roleName = null;
  var $tabLi = $('.likeTabs li');
    var tabInitE = [function one() {
    	tabInit[0] = true;
	},function two () {
	  tabInit[1] = true;
	  $('#gridBox-1').datagrid('load',{init:1});
	}];
	
	$tabLi.click(function () {
		var ix = $tabLi.index(this);
		tabIndex = $(this).attr('rel');
		$tabLi.removeClass('tabs-selected');
		$(this).addClass('tabs-selected');
		$('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
		if (!tabInit[ix]){//初始化
		    tabInitE[ix]();
		}
		return false;
	});
	
	      //注册
      $('#reg').click(function () {
		$pop.iframePop({
			area :['500px', '320px'],
			title : '注册机构',
			content : "${base}/ui/sys/syncHr/pop/regInst",
			end : function(){
  				$('#gridBox-1').datagrid('load',{init:1});
			}
		},'#gridBox-1');
      });
      $grid.newGrid("#gridBox-1",{
          checkOnSelect : false,
          selectOnCheck : false,
          fitColumns : false,
          pagination:true,
          columns:[[
            {title:'id',field:'ID',hidden:true},
            [@shiro.hasPermission name = "PlatformInst:edit"]
            //{title:'操作',field:'op',formatter :function (v,row,index) {
             	//var chkHtml = '<span class="icon-key2 s-op-edit" rel="'+row.AHIS_HOSP+'" title="授予应用"></span>';
			  	//return chkHtml;
           // }},
			[/@shiro.hasPermission]
            {title:'AHIS编号',field:'AHIS_HOSP',width:80,titletip:true},
            {title:'名称',field:'NAME',width:200, align:'left',titletip:true,},
            {title:'上级机构',field:'PARENT_NAME',width:80, align:'left',titletip:true,},
            {title:'人资编号',field:'INST_CODE',width:80,titletip:true},
            {title:'备注',field:'REMARKS',width:150,titletip:true},
        	[#list apps as vo]
        	{title:'${vo.NAME}',field:'${vo.CODE}',width:130,formatter: function(val,row,index){
            	return ['<button type="button" class="btn btn-small btn-danger so-search"  onclick=\'aierInit(1,'+row.AHIS_HOSP+',"${vo.CODE}")\'>停用</button>',
            			'<button type="button" class="btn btn-small btn-primary so-search"  onclick=\'aierInit(0,'+row.AHIS_HOSP+',"${vo.CODE}")\'>启用</button>'][val];
            	//return ['<span class="el-tag-closed s-op-edit">关闭</span>','<span class="el-tag-open s-op-edit">开启</span>'][val];
            }},
            [/#list]
          ]],
          onBeforeLoad : function (param) {
              if(!param.init){
                return false;
              }
          },
          
          onLoadSuccess : function (data) {
           $('.s-op-edit').click(function () {
              var id = $(this).attr('rel');
              var instid = $(this).attr('longsrc');
              $pop.iframePop({
                title :'授予医院',
                content : '${base}/ui/sys/platformInst/viewUpdatePerm?id='+id, //加载用户已授权角色
                area : ['600px','500px']
              },'#gridBox-1');
            });
          },
          url:'${base}/ui/sys/platformInst/getHospList?_easyui_=GRID',
          offset :-36
      });
      

	window.aierInit = function(type, ahisHosp, code){
		var param = {};
		param.type = type
		param.id = ahisHosp;
		param.code = code;
		var msg = type==1?'确定要【启用】此医院'+code+'的权限吗？':'确定要【停用】此医院'+code+'的权限吗？'
		$ajax.postJson('${base}/ui/sys/platformInst/updateInstPerm',JSON.stringify(param),msg).done(function(rst){
   			$('#gridBox-1').datagrid('load',{init:1});
		});    
	}
   $('#gridBox-1').datagrid('load',{init:1});
});

</script>
</body>

</html>

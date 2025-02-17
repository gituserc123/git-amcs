<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>报表授权管理</title>
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

</style>
</head>

<body>
    <ul class="tabs likeTabs">
      <li class="tabs-first tabs-selected" rel="0"><a href="#" class="tabs-inner"><span class="tabs-title">按用户</span></a></li>
      <li  rel="1"><a href="#" class="tabs-inner"><span class="tabs-title">按报表</span></a></li>
    </ul>
    <div class="tabCont tabCont-0">
          <div class="searchHead">
              <form id="sbox2" class="soform form-enter">
                    <input type="hidden" class="txt" name="init" value="1" />
                    <label class="lab-inline">医院：</label>
                    ${shiroUser.instName!}
                    <label class="lab-inline">科室：</label>
                    <input id="txt-deptTree-staff" class="txt w-150" type="text" name="dept"/>
                    <input class="txt inline w-150" type="text" name="keyword" placeholder="工号、姓名、手机号码">
                    <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox-1',scope:'#sbox2'}">查 询</button>
                </form>
          </div>
          <div id="gridBox-1"></div>
    </div>
    
    <div class="tabCont tabCont-1 tabContHide">
          <div id="gridBox-0"></div>
     </div>

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
	
	$txtDeptTreeStaff.combotree({
		animate : true, 
        panelWidth:'200px',
        panelHeight:'auto',
        panelMaxHeight:'200px',
        lines : true,
        clearIcon :true,
        onlyLeafCheck : true,
        flatData: true,
        url:'${base}/ui/sys/authorize/hospital/getTreeByParent?parentId=${shiroUser.instId}'
	});
	

      $grid.newGrid("#gridBox-1",{
          checkOnSelect : false,
          selectOnCheck : false,
          fitColumns : false,
          pagination:true,
          columns:[[
            {title:'id',field:'id',hidden:true},
            {title:'操作',field:'op',formatter :function (v,row,index) {
             	var chkHtml = '<span class="s-op icon-eye s-op-look" rel="'+row.id+'" title="查看报表权限"></span>';
             	[@shiro.hasPermission name = "ReportPerm:perm"]
             	chkHtml += '&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-edit s-op-edit" rel="'+row.id+'" title="编辑报表权限"></span>';
             	[/@shiro.hasPermission]
			  	return chkHtml;
            }},
            {title:'所属机构',field:'instname',width:150},
            {title:'姓名',field:'staffname',width:120,titletip:true},
            {title:'工号',field:'code',width:120},
            {title:'联系方式',field:'tel',width:100},
            {title:'角色名称',field:'rolename',width:220,titletip:true},
            {title:'所属科室/部门',field:'deptname',width:320,titletip:true}
            
          ]],
          onBeforeLoad : function (param) {
              if(!param.init){
                return false;
              }
          },
          
          onLoadSuccess : function (data) {
            $('.s-op-look').click(function () {
              var id = $(this).attr('rel');
              $pop.iframePop({
                title :'查看报表权限',
                content : '${base}/ui/sys/report/viewGetPerm?id='+id,
                area : ['50%','90%']
              });
            });
          
           $('.s-op-edit').click(function () {
              var id = $(this).attr('rel');
              var instid = $(this).attr('longsrc');
              $pop.iframePop({
                title :'编辑报表权限',
                content : '${base}/ui/sys/report/viewUpdatePerm?id='+id, //加载用户已授权角色
                area : ['500px','500px']
              },'#gridBox-1');
            });
          },
          url:'${base}/ui/sys/authorize/hospital/searchStaffAuthorize',
          offset :-36
      });
      
    $grid.newGrid("#gridBox-0",{
        // method:'post',
        columns:[[
          [@shiro.hasPermission name = "Report:edit"]
		  {title:'编辑',field:'editPermit',width:'64px',formatter: function(value,row,index){
			  var chkHtml = '<span class="s-op icon-key2" rel="'+row.id+'" title="授权"></span>';
			  return chkHtml;
			}},
  		  [/@shiro.hasPermission]
          {title:'中文名称',field:'name',width:'200px',align:'left'},
          {title:'编码',field:'text',width:'200px',align:'left'},
          {title:'路径',field:'path',width:'250px',align:'left'},
          {title:'参数',field:'param',width:'480px',align:'left'},
          {title:'上次修改人',field:'modiferName',width:'80px'},
          {title:'上次修改时间',field:'modifyDate',width:'200px'},
        ]],
        onLoadSuccess : function (data) {
          $('.icon-key2').click(function () {
              var id = $(this).attr('rel');
              $pop.iframePop({
                title :'编辑报表权限',
                content : '${base}/ui/sys/report/viewReportPerm?id='+id, //加载用户已授权角色
                area : ['500px','500px']
              },'#gridBox-0');
          });
        },
        url:'${base}/ui/sys/report/getList',
        pagination:true,
        // height: 'auto',
      	offset :-36
    });

   $('#gridBox-1').datagrid('load',{init:1});
});

</script>
</body>

</html>

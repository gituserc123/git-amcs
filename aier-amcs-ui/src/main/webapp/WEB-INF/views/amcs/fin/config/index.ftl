<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>省区角色管理 - 爱尔医院Ahis管理系统</title>
<link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon" />
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.mainContBox .searchHead{margin-top:0;}
</style>
</head>
<body>

<div class="wrapper">
  <h2 class="h2-pageTitle">模块管理</h2>
  
  <div class="sideBarBox">
    <div class="sideTreeC">
      <ul id="ul-moduleTree"></ul>
    </div>
  </div>

  <div class="mainContBox">
    <div class="soform searchHead">
      <form id="sbox" class="form-inline" method="post" action="#">
        <input type="hidden" id="roleId" name="roleId" value=""/>
        <input type="hidden" id="provinceId" name="provinceId" value=""/>
        <span class="fl"><label class="lab-inline">工号：</label><input type="text" id="staffCode" class="txt w-120" name="staffCode"></span>
        <span class="fl"><label class="lab-inline">姓名：</label><input type="text" id="staffName" class="txt w-120" name="staffName"></span>
        &nbsp;&nbsp;
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
      </form>
    </div>

    <div class="">
      <input type="hidden" id="roleName" name="roleName" value=""/>
      <input type="hidden" id="provinceName" name="provinceName" value=""/>
      <div id="gridBox"></div>
    </div>

  </div>
</div>
<div class="none"></div>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {
var popW = ($(window).width())+'px';
  $(window).resize(function () {
    popW = ($(window).width())+'px';
  });

var toolbars =
[
  [{iconCls:'plus_sign',text:'新增', click:function(){
    var roleId = $('#roleId').val();
    var roleName = $('#roleName').val();
    var provinceId = $('#provinceId').val();
    var provinceName = $('#provinceName').val();
    if (undefined == roleId || roleId == ''){
        $pop.alert("请在左侧的角色树上选择需要新增的用户的角色(非角色组)！");
        return false;
    }
	$pop.iframePop({
		  title: provinceName+'-新增-当前角色:'+roleName,
		  content : 'createView?roleId='+roleId+"&roleName="+roleName+"&provinceId="+provinceId+"&provinceName="+provinceName,
		  area : ['500px', '500px']
		},['#gridBox']);
  }}],
  []
];

  $grid.newGrid("#gridBox",{
        tools:toolbars,
        fitColumns : false,
        rownumbers : false,
        checkOnSelect : false,
        selectOnCheck : false,
        columns:[[
           {title:'id',field:'id',hidden:true},
           {title:'操作',field:'op',width:120,formatter: function (value,row,index) {
               return '<span class="s-op s-op-del icon-del"  rel="'+row.id+'" title="删除"></span>';
             }}
          ,{title:'省区',field:'provinceName',width:120}
          ,{title:'岗位',field:'roleName',width:120}
          ,{title:'工号',field:'staffCode',width:120}
          ,{title:'姓名',field:'staffName',width:120}
          ,{title:'创建人',field:'creator',width:120}
          ,{title:'创建时间',field:'createDate',width:140}
        ]],
        onBeforeLoad: function(params){
          params.provinceId = $("#provinceId").val();
          params.roleId = $("#roleId").val()?$("#roleId").val():"0";
          params.staffCode = $("#staffCode").val();
          params.staffName = $("#staffName").val();
        },
        onLoadSuccess : function (data) {
          $('.s-op-del').click(function () {
            var id = $(this).attr('rel');
            $ajax.post('${base}/ui/amcs/fin/config/remove/'+id,null,'确定删除此省区角色吗？').done(function (rst) {
              if (rst) {
                $("#gridBox").datagrid('reload');
              }else{
                $pop.alert("省区角色删除失败!");
              }
            });
          });
        }
        ,offset : -2
        ,url: '${base}/ui/amcs/fin/config/list'
      });

[#--  [@shiro.hasPermission name = "Module:view"]--]
    $('#ul-moduleTree').tree({
        animate : true,
        lines : true,
        url:'${base}/ui/amcs/fin/config/tree',
        flatData: true,
        onClick : function (node) {
          var id = node.id;
          var name = node.name;
          var pid = node.pid
          if (node.isRoleNode!=1){
            $('#roleId').val('');
            return;
          }
          var parent = $('#ul-moduleTree').tree('getParent', node.target);
          $('#provinceId').val(pid);
          $('#provinceName').val(parent?parent.name:"");
          $('#roleId').val(id);
          $('#roleName').val(name);
          $("#gridBox").datagrid('reload');
        }
    });
[#--   [/@shiro.hasPermission]--]

});
</script>

</body>
</html>

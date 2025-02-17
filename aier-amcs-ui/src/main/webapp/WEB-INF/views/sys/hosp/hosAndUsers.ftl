<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>医院机构人员</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.mainContBox .searchHead{margin-top:0;padding:10px 0 5px 10px;}
.likeTabs{width:auto;}
.likeTabs .tab-last{border-right:0;}
.tabs li a.tabs-inner{padding:0 45px;}

.tabCont{position: absolute;left:0;right:0;top:34px;bottom:5px;}
.tabContHide{visibility: hidden;}
.cont-grid{overflow: hidden;zoom: 1;border-bottom: 5px solid #dfe2e6;}
.btn-dataSync,.btn-dataSync:focus{position: absolute;top:4px;right:5px;}
</style>
</head>

<body>
<div class="sideBarBox">
    <input id="instId" type="hidden" class="txt" name="instId" value="112" />
    <div class="sideTreeC">
      <ul id="ul-kindTree"></ul>
    </div>
</div>


<div class="mainContBox">
    <ul class="tabs likeTabs">
      <li class="tabs-first tabs-selected" rel="0"><a href="${base}/static/#" class="tabs-inner"><span class="tabs-title">部门</span></a></li>
      <li rel="1"><a href="#" class="tabs-inner"><span class="tabs-title">用户</span></a></li>
    </ul>
    <button type="button" class="btn btn-small btn-primary btn-dataSync">数据同步</button>
      <div class="tabCont tabCont-0"><div id="gridBox-0"></div></div>
      <div class="tabCont tabCont-1 tabContHide">
        <div class="searchHead">
            <form id="sbox" class="soform form-enter">
                  <input id="instIdB" type="hidden" class="txt" name="instId" value="" />
                  <input class="txt inline w-250" type="text" name="staffKey" id="staffKey" value="" placeholder="姓名、工号、手机号码">
                  <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox-1',scope:'#sbox'}">查 询</button>
              </form>
        </div>
        <div id="gridBox-1"></div>
      </div>
</div>

</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {

      var tabIndex = 0;
      var tabinstId = ['',''];
      var instId = $('#instId').val();
      $('#instIdB').val(instId);
      $('#ul-kindTree').tree({
        animate : true,
        lines : true,
        url:'${base}/ui/sys/hosp/getForTree',
        flatData: true,
        onClick : function (node) {
          // window.console && console.log(node);
          instId = node.id;
          changeOrgLoadGrid();
        }
      });

      var $tabLi = $('.likeTabs li');
      $tabLi.click(function () {
          var ix = $tabLi.index(this);
          tabIndex = $(this).attr('rel');
          $tabLi.removeClass('tabs-selected');
          $(this).addClass('tabs-selected');
          $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
          changeOrgLoadGrid();
          return false;
      });


      $grid.newGrid("#gridBox-0",{
          // fit: true,
          checkOnSelect : false,
          selectOnCheck : false,
          // singleSelect : false,
          // ctrlSelect : true,
          // pagination : false,
          fitColumns : false,
          columns:[[    
	        {field:'instCode',title:'机构代码',width:100,align:'center'}, 
	        {field:'name',title:'机构名称',width:300,align:'center'},
	        {field:'instAddr',title:'机构地址',width:300,align:'center'},
	        {field:'instTypeName',title:'机构类型',width:100,align:'center'},
	        {field:'parentName',title:'上级机构',width:300,align:'center'},
	      ]],
          onBeforeLoad : function (param) {
              if(!param.instId){
                return false;
              }
          },
          onLoadSuccess : function (data) {

          },
		  url:'${base}/ui/sys/hosp/getInstByParent',
          // height: 'auto',
          offset :-36
      });

      $grid.newGrid("#gridBox-1",{
          // fit: true,
          checkOnSelect : false,
          selectOnCheck : false,
          // singleSelect : false,
          // ctrlSelect : true,
          // pagination : false,
          fitColumns : false,
         columns:[[    
	        {field:'CODE',title:'工号',width:100,align:'center'}, 
	        {field:'NAME',title:'姓名',width:100,align:'center'},
	        {field:'SEX',title:'性别',width:35,align:'center'},
	        {field:'DEPT_NAME',title:'部门',width:200,align:'center'},
	        {field:'TEL',title:'手机号',width:100,align:'center'},
	        {field:'INSTITUTION_ID',title:'所属机构',width:200,align:'center'},
	        {field:'LASTLOGINTIME',title:'最后登录时间',width:130,align:'center'},
	        {field:'LOCKED',title:'是否锁定',width:60,align:'center'},
	        {field:'STATUS',title:'是否启用',width:60,align:'center'},
	    ]],
          onBeforeLoad : function (param) {
              if(!param.instId){
                return false;
              }
          },
          onLoadSuccess : function (data) {

          },
           url:'${base}/ui/sys/staff/getStaffByCondition',
          // height: 'auto',
          offset :-36
      });


      function changeOrgLoadGrid () {
        // window.console && console.log(tabIndex,tabinstId);
          if(tabinstId[tabIndex]!==instId){
              tabinstId[tabIndex] = instId;
              $('#instIdB').val(instId);
              $grid.load('#gridBox-'+tabIndex,{instId:instId});
          }
      }

      changeOrgLoadGrid();

      $('.btn-dataSync').click(function () {
        $ajax.post('${base}/ui/common/shiro/getPerm',{
	    	'clazz':$('#staffKey').val(),
	    }).done(function(rst){
	    });     
      });
});

</script>
</body>

</html>

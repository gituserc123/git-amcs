<!DOCTYPE html>
<html>
<head>
<title>欢迎使用爱尔眼科职能管控平台</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon" />
[#include "/WEB-INF/views/common/include_resources.ftl"]
<link rel='stylesheet' type='text/css' media="all" href='${base}/static/css/indexAll.css?v=e4683' />
[#--<link type="text/css" rel="stylesheet" media="all" href='${base}/static/css/festival/spring.css?v=9ce37'/>--]
<style type="text/css">
body{background-color:#0d81dc;}
/*body,.panel-fit body{position: relative;max-width:1360px;margin:0 auto;}*/
/*.wrapper{position: relative;max-width:1300px;min-width:1100px;margin:0 auto;overflow: auto;height:600px;}*/
#tabs .panel{position: relative;}
.tabs li{overflow: hidden;border:1px solid #C9CED6;border-bottom:0;}
.tabs li.tabs-selected{border-color:#169FE4;}
.layui-layer-msg .layui-layer-content{background-color:#f9f4e5;overflow: hidden;}
.layui-layer-msg{border: 1px solid #fde0c4;overflow: hidden;}
.panel-header, .panel-body{border:0;}
.drop-dept{background-color:#1b84d8;border:0px;box-shadow:none;color:#fff;height:22px;position:relative;top:-2px;}
.li-mainnav-AEMRO{display:none;}

div.mainnav{right:0;}
.tabs{border-top:0;}

.newChangePfm{height:35px;}
.h1-logo.canChangePfm.newChangePfm:after{top:30%;}
.h1-logo.newChangePfm:hover:after{visibility: hidden;}
.ul-plats{position: absolute;left:0;top:44px;padding:2px 0 0;
    background-color:rgba(255,255,255,0.05); display: none;border-radius: 0 0 3px 0;
    overflow: hidden;}
/*.newChangePfm:hover .ul-plats{display: block;}*/
.ul-plats .li-i{width:256px;height:57px;background:url(${base}/static/images/home/nplat.png) no-repeat 0 0;border-top:1px solid #a5c8fc;color:#0A50BA;}
.ul-plats .li-i:hover{background-position: 0 -60px;color:#4928B6;}
.ul-plats .i-t,.ul-plats a.i-t{
    background:url(${base}/static/images/home/nplat.png) no-repeat -280px 0;
    display: block;
    line-height:53px;width:170px;padding:0 0 0 92px;
    font-size: 15px;cursor: pointer;
}
.ul-plats .i-0 .i-t{background-position: -280px 0;}
.ul-plats .i-1 .i-t{background-position: -280px -60px;}
.ul-plats .i-2 .i-t{background-position: -280px -120px;}
.ul-plats .i-3 .i-t{background-position: -280px -180px;}
.ul-plats .s-t{display: inline-block;width:152px;height:38px;line-height:38px;vertical-align: middle;text-align: center;border-radius:4px;
    font-weight: bold;
    background-color: rgba(255,255,255,0.7);}
.ul-plats .i-t:hover{opacity: 0.9;}
</style>
</head>
<body class="easyui-layout">
    <div class="watermarkMask">

    </div>
  <ul class="ul-searchList"></ul>
  <!-- header -->
  <div id="topHead" class="header" data-options="region:'north'">
  <h1 class="h1-logo">
  	[#if profile=='uat' || profile=='sit' || profile=='hotfix' || profile=='local']
	  <img class="img-logo" src="${base}/static/images/flat-logo.png" height="30" width="332" alt="欢迎使用爱尔眼科职能管控平台" />
	  [#else]
	  <img class="img-logo" src="${base}/static/images/flat-logo.png" height="30" width="332" alt="欢迎使用爱尔眼科职能管控平台" />
	  [/#if]
      <ul class="ul-plats">
          [#list clouds as c]
              [#if c.current]
              [#--                 <li class="li-i i-${c_index}"><span class="i-t"><strong class="s-t">${c.name}</strong></span></li>--]
              [#else]
                  <li class="li-i li-has i-${c_index}"><span class="i-t" href="${c.url}" rel="${c.code}"><strong class="s-t">${c.name}</strong></span></li>
              [/#if]
          [/#list]
      </ul>
  </h1>
  <div class="userInfo">
     <div class="userInfoBox"><div class="div-userInfo" title="">...</div>
        <ul class="ul-userquick">
            <li class="li-userquick"><span class="s-userquick s-repairPass" rel="${base}/updatePwd">修改密码</span></li>
            <li class="li-userquick"><span class="s-userquick a-loginOut" rel="${base}/ssoLogout">退出系统</span></li>
        </ul>
     </div>
    </div>

  <div id="msgBox" class="msgBox"><div class="userMsgBox"></div></div>

    <div class="searchBox">
      <div class="searchCont"><input type="text" class="txt txt-search" name="txt" placeholder="导航搜索" /><button class="btn-search"><em class="em-icon icon-search2"></em></button>
      </div>
    </div>
	<div class="userSet">
        <div class="s-date" id="sysTime">${.now?string("yyyy-MM-dd")}   </div>
        <div class="s-welcome"><span class="s-w-info">${login_user.instName} -</span>
            <div class="allCompanyNav">
              <div class="nowCompany" title="点击弹窗编辑切换部门">${nowDept}</div>
            </div>
            <em class="s-w-info em-userName">- [@shiro.principal/] </em></div>
    </div>
  </div>
  [#if menus??]
  [#else]
      <script>location.href = '${base}/login'</script>
  [/#if]
  <div id="mainCont" class="mainCont" data-options="region:'center'">
	<span class="s-winResize"></span>

    <div id="tabs" class="easyui-tabs" fit="true" border="false" width="100%">
        <div title="功能导航" class="content-doc">
          <div class="mainnav mainnavIntend">
[#--          <div class="mainnav">--]
           <ul class="ul-mainnav">
<!-- 		    <li class="li-mainnav"><span class="s-mainnav s-mainnav-now" rel="#subnav-my"><em class="em-icon icon-shoucb"></em><em class="em-nav">我的工作台</em></span></li> -->
		     [#list platforms as p]
		      [#if p.code != 'adsp']
		      	[#if (p.hasModules)]
		     		<li class="li-mainnav-p li-mainnav-${p.name}">
	                 <h3 class="h3-mainNavTitle">${p.name}</h3>
	                 <ul class="ul-mainav-sub">
	                 	[#list menus as levelOneModule]
	                 		[#if levelOneModule.grade == 1 && levelOneModule.platformCode == p.code && levelOneModule.display]
	                    	<li class="li-mainnav"><span class="s-mainnav" rel="#${levelOneModule.id}"><em class="em-icon ${levelOneModule.icon!""}"></em><em class="em-nav">${levelOneModule.moduleName}</em></span></li>
	                    	[/#if]
	                    [/#list]
	                 </ul>
	             	</li>
		     	[/#if]
		      [/#if]
		      [/#list]
             </ul>

              <div class="subnavBox">

<!--                 <div id="subnav-my" class="subnav" style="display:block;"> -->
<!--                   <div class="subnav-m"> -->
<!--                       <div class="menuNavBox"> -->
<!--                           <span class="s-quickEdit icon-edit" title="编辑快捷图标"></span> -->
<!--                           <div class="favMenuNav"> -->
<!--  -->
<!--                           </div> -->
<!--                       </div> -->
<!--                   </div> -->
<!--                 </div> -->
                [#list menus as levelOneModule]
                <div id="${levelOneModule.id}" class="subnav"><div class="subnav-m">
                  [#if levelOneModule.display]
                  	[#list levelOneModule.children as levelTwoModule]
                  		[#if levelTwoModule.display]
		                    <h4 class="h4-title"><a name="subnav-12" href="#" class="a-title">${levelTwoModule.moduleName}</a></h4>
		                    <ul class="ul-subnav ">
		                    [#list levelTwoModule.children as levelThreeModule]
		                      [#if levelThreeModule.display]
		                      <li class="li-subnav"><span class="s-subnav" rel="${levelThreeModule.url}"><strong class="s-itemIcon fs16 [#if levelThreeModule.icon]${levelThreeModule.icon}[#else]icon-box[/#if]"></strong><em class="em-title">${levelThreeModule.moduleName}</em></span><em class="em-icon em-favor icon-star-empty" name="${levelThreeModule.id}"></em></li>
		                      [/#if]
		                    [/#list]
		                    </ul>
	                    [/#if]
                    [/#list]
                  [/#if]
                  </div>
                </div>
                [/#list]
              </div>
           </div>
        </div>
    </div>
</div>

<!-- mainTab rightMenu -->
<div id="mm" class="easyui-menu" style="width:150px;">
  <div id="refresh">刷新</div>
  <div class="menu-sep"></div>
  <div id="close">关闭</div>
  <div id="closeall">全部关闭</div>
  <div id="closeother">除此之外全部关闭</div>
</div>

<!-- mainTab rightMenu -->
<div id="mm2" class="easyui-menu" style="width:150px;">
  <div id="up">向前</div>
  <div id="down">向后</div>
  <div id="first">置顶</div>
  <div id="last">置底</div>
  <div class="menu-sep"></div>
  <div id="del">删除</div>
  <div id="exit">退出</div>
</div>

<script type='text/javascript' src='${base}/static/js/jquery-1.11.3.min.js?v=ef3d5'></script>
<script type='text/javascript' src='${base}/static/js/lib/easyui/jquery.easyui.min.js?v=e2447'></script>
<script type='text/javascript' src='${base}/static/js/lib/layer/layer.min.js?v=a80ae'></script>
<script type='text/javascript' src='${base}/static/js/lib/template.js?v=05ca4'></script>

[#if openCloud]
<script id="tem-changePlatforms" type="text/html">
  <div class="changePlatforms">
      <h2 class="h2-title">爱尔眼科门户</h2>
      <ul class="ul-platforms">
      	  [#list clouds as c]
      	  [#if c.current]
          <li class="li-i i-${c_index}"><span class="i-t"><em class="em-i"></em><strong class="s-t">${c.name}</strong></span></li>
          [#elseif c.url == null]
          <li class="li-i i-${c_index} li-unuse"><span class="i-t"><em class="em-i"></em><strong class="s-t">${c.name}</strong></span><span class="s-buildingTip"><em class="em-building">建设中...</em></span></li>
          [#else]
          <li class="li-i li-has i-${c_index}"><a class="i-t" href="${c.url}"><em class="em-i"></em><strong class="s-t">${c.name}</strong></a></li>
          [/#if]
          [/#list]
      </ul>
  </div>
</script>
[/#if]
  <script id="tem-changeDept" type="text/html">
    <div class="changeDept soform pad-t30 pad-l10">
        <div class="row">
            <div class="p11"><div class="item-one">
                <label class="lab-item"> 医院/集团 ：</label>
                <select class="drop drop-hospital" name="institution" id="institution" disabled="disabled">
                    <option value="" selected="selected">请选择医院/集团</option>
                </select>
            </div></div>
        </div>

        <div class="row">
            <div class="p11"><div class="item-one">
                    <label class="lab-item"> 科室/部门 ：</label>
                    <select class="drop" name="dept" id="dept" disabled="disabled">
                        <option value="" selected="selected">请选择科室/部门</option>
                    </select>
                </div></div>
        </div>
        <div class="p-btn center">
            <button class="btn btn-primary btn-submit" type="button" style="margin-right:5px;">提交</button>
            <button class="btn btn-cancel" type="button">取消</button>
        </div>

    </div>
  </script>
<!-- old -->

[#-- {type: 1, desc : '医嘱', size: 2,data: [] }--]
<script id="tem-msgTitle" type="text/html">
{{each biz as one i}}
<span class="s-msgTitle{{ if one.cur}} s-msgTitle-now{{/if}}">{{one.desc}} <em class="em-num">(<b>{{one.size}}</b>)</em></span>
{{/each}}
</script>


<script id="tem-popFavItem" type="text/html">
    <form class="soform form-favItem form-validate form-enter pad-t20">
        <input class="txt hide" type="text" name="moduleId" value="{{moduleId}}" />

        <div class="row">
            <div class="p11"><div class="item-one">
                    <label class="lab-item">选择分类：</label>
                    <input class="txt required" id="moduleCategorize" style="width:100%;" name="moduleCategorize" type="text" />
                </div></div>
        </div>

        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-saveFav" name="btnSubmit" value="保 存" />
            <input type="button" class="btn btn-closePopFav" name="btnCancel" value="取 消" />
        </p>
    </form>
</script>

<script id="tem-favItem" type="text/html">
{{each menu as one k}}
    <h4 class="h4-title"><span class="a-title">{{k}}</span></h4>
    <ul class="ul-hnav ul-menuNav">
        {{each one as item}}
        <li class="li-hnav">
            <span class="s-hnav" rel="{{item.url}}">
                <em class="em-icon {{item.icon?item.icon: 'icon-box'}}"></em>
                <em class="em-nav">{{item.moduleName}}</em>
            </span>
            <em class="em-del-item icon-minus_sign" rel="{{item.id}}"></em><div class="delMaskHandler" rel="{{item.id}}"></div>
            <em class="em-icon em-favor icon-star-empty" name="{{item.id}}"></em>
        </li>
        {{/each}}
    </ul>
{{/each}}
 </script>

<!-- Import flash websocket  JavaScript Libraries. -->
<script type='text/javascript'>
  [#if profile=='uat' || profile=='sit' || profile=='hotfix' || profile=='local']
  [#else]
   window.console.log = function(msg){}
  [/#if]
  [#if profile=='local']
  window.isDev = true;
  [/#if]
    var baseUrl = '${base}';
    var sysNowTime = '${.now?string("yyyy-MM-dd HH:mm:ss")}';
    var staffcode = '${login_user.loginCode}';
    var openCloud = '${openCloud}';
    var cloudsSize = [#if openCloud && clouds??]'${clouds?size}';[#else]'0';[/#if]
    var platforms = {};
    var userNowInstId = '${login_user.instId}';
    var userNowDeptId = '${login_user.deptId}';
    var userNowDept = '${nowDept}';

    [#list platforms as p]
    platforms['${p.code}'] = '${p.url}'
	[/#list]
    try {//如果菜单JSON解析错误，返回空数组
      var searchMenus = JSON.parse('${searchMenus}');//用于菜单搜索
    }catch(e){
      console.log('菜单数据格式解析错误，无法返回正确的搜索菜单！');
      var searchMenus = [];
    }
</script>
<script type='text/javascript' src='${base}/static/js/plus/watermark.js?v=a246d'></script>
<script type='text/javascript' src='${base}/static/js/index.js?v=e98b5'></script>
<script type="text/javascript">
$(function () {
[#if isChangePassword == false]
   eyeIndex.popRepairPass('${base}/updatePwd', true);
[/#if]

  //添加水印
  var globalWater = true;
  if(globalWater){
    watermark.init({
      [#--watermark_txt: "${login_user.instName}<br />${login_user.deptName}<br />[@shiro.principal/]"--]
      watermark_txt: "[@shiro.principal/]<br />${login_user.loginCode}"
    });
  }

});
</script>
</body>
</html>

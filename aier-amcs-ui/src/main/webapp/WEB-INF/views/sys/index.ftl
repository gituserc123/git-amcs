<!DOCTYPE html>
<html>
<head>
<title>欢迎使用爱尔医疗云平台</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel='icon' href='${base}/static/images/logo.ico' type='image/x-icon' />
<link rel='stylesheet' type='text/css' media='all' href='${base}/static/js/lib/easyui/themes/gray/easyui.css' />
<link rel='stylesheet' type='text/css' media='all' href='${base}/static/css/indexAll.css?57cd7' />
<style type="text/css">
/*body,.panel-fit body{position: relative;max-width:1360px;margin:0 auto;}*/
/*.wrapper{position: relative;max-width:1300px;min-width:1100px;margin:0 auto;overflow: auto;height:600px;}*/
#tabs .panel{position: relative;}
.tabs li{overflow: hidden;border:1px solid #C9CED6;border-bottom:0;}
.tabs li.tabs-selected{border-color:#169FE4;}
.layui-layer-msg .layui-layer-content{background-color:#f9f4e5;overflow: hidden;}
.layui-layer-msg{border: 1px solid #fde0c4;overflow: hidden;}
.panel-header, .panel-body{border:0;}
.drop-dept{background-color:#1b84d8;border:0px;box-shadow:none;color:#fff;height:22px;position:relative;top:-2px;}

.s-routnav{display: block;cursor:hand;cursor:pointer;padding:5px 0 5px 17px;line-height:28px;color:#111;font-size: 1.18em;}
.s-routnav .em-icon{display: -moz-inline-stack;display:inline-block;*display:inline;*zoom:1;width:20px;font-size:1.2em;color: #0993e6;position: relative;top:1px;}


</style>
</head>
<body class="easyui-layout">
<ul class="ul-searchList"></ul>
  <!-- header -->
  <div id="topHead" class="header" data-options="region:'north'">
    <h1 class="h1-logo showtip">
    [#if profile=='uat' || profile=='sit' || profile=='hotfix']
    	<img id="indexLogo" class="img-logo" src="${base}/static/images/flat-logo-s.png" height="25" width="340" alt="欢迎使用爱尔管控云平台" />
    [#else]
    	<img id="indexLogo" class="img-logo" src="${base}/static/images/flat-logo.png" height="25" width="340" alt="欢迎使用爱尔管控云平台" />
    [/#if]
    </h1>
  <div class="userInfo">
     <div class="userInfoBox"><div class="div-userInfo" title="">...</div>
        <ul class="ul-userquick">
            <li class="li-userquick"><span class="s-userquick s-repairPass" rel="${base}/updatePwd">修改密码</span></li>
            <li class="li-userquick"><span class="s-userquick a-loginOut" rel="${base}/logout">退出系统</span></li>
        </ul>
     </div>
    </div>
    <div class="searchBox">
      <div class="searchCont"><input type="text" class="txt txt-search" name="txt" placeholder="导航搜索" /><button class="btn-search"><em class="em-icon icon-search2"></em></button>
      </div>

    </div>
    <div class="userSet"><span class="s-welcome">${login_user.instName} - ${login_user.deptName} - <em class="em-userName"> [@shiro.principal/]</em></span><span class="s-date" id="sysTime">${login_user.projectName}</span></div>
  </div>
  [#if menus??]
  [#else]
      <script>location.href = '${base}/login'</script>
  [/#if]
  <div id="mainCont" class="mainCont" data-options="region:'center'">
	<span class="s-winResize"></span>
    <div id="tabs" class="easyui-tabs" fit="true" border="false" width="100%">
        <div title="功能导航" class="content-doc">
          <div class="mainnav">
               <ul class="ul-mainnav">
                <li class="li-mainnav li-mainnav-b"><span class="s-mainnav s-mainnav-now" rel="#subnav-my"><em class="em-icon icon-home"></em><em class="em-nav">我的快捷</em></span></li>

				 [#list menus as levelOneModule]
				 	[#if levelOneModule.grade == 1]
				 	<li class="li-mainnav"><span class="s-mainnav" rel="#${levelOneModule.id}"><em class="em-icon ${levelOneModule.icon!""}"></em><em class="em-nav">${levelOneModule.moduleName}</em></span></li>
				 	[/#if]
				 [/#list]
               </ul>
[#--              <div class="eyelogo"><img class="img-eyelogo" src="${base}/static/images/eyelogo.png" width="88" height="29" alt="" /></div>--]
              <div class="subnavBox">
                <div id="subnav-my" class="subnav" style="display:block;">
                  <div class="subnav-m">
                      <div class="menuNavBox">
                          <span class="s-quickEdit icon-edit" title="编辑快捷图标"></span>
                        <h4 class="h4-title"><span class="a-title">快捷菜单</span></h4>
                        <ul class="ul-hnav ul-menuNav ul-subnav-c">
                         </ul>
                      </div>
                  </div>
                </div>

			 [#list menus as levelOneModule]
				<div id="${levelOneModule.id}" class="subnav"><div class="subnav-m">
				[#if levelOneModule.display]
					[#list levelOneModule.children as levelTwoModule]
						[#if levelTwoModule.display]
						<h4 class="h4-title"><a name="subnav-12" href="#" class="a-title">${levelTwoModule.moduleName}</a></h4>
						<ul class="ul-subnav ul-subnav-b">
                            [#list levelTwoModule.children as levelThreeModule]
                                [#if levelThreeModule.outurl && levelThreeModule.display]
                                    <li class="li-subnav"><span class="s-subnav" rel="${levelThreeModule.url}"><strong class="s-itemIcon fs16 [#if levelThreeModule.icon]${levelThreeModule.icon}[#else]icon-box[/#if]"></strong><em class="em-title">${levelThreeModule.moduleName}</em></span><em class="em-icon em-favor icon-star-empty" name="${levelThreeModule.id}"></em></li>
                                [#elseif !levelThreeModule.outurl && levelThreeModule.display]
                                    <li class="li-subnav"><span class="s-subnav" rel="${base}/${levelThreeModule.url}"><strong class="s-itemIcon fs16 [#if levelThreeModule.icon]${levelThreeModule.icon}[#else]icon-box[/#if]"></strong><em class="em-title">${levelThreeModule.moduleName}</em></span><em class="em-icon em-favor icon-star-empty" name="${levelThreeModule.id}"></em></li>
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
[#--           <div class="index-area">--]
[#--               <div class="handlerAreaSide"></div>--]
[#--              <div class="area-b">--]
[#--                <h3 class="h3-list h3-list-1">--]
[#--                <span class="s-title">消息通知</span>--]
[#--                </h3>--]
[#--                <ul class="ul-c">--]
[#--                  <li><a class="a-title" href="#" title="怀化爱尔迎丰眼视光诊所已成功上线"><span>[医院] 2018-8-26号 怀化爱尔迎丰眼视光诊所已成功上线</span><span class="s-info">2018-08-27</span></a></li>--]
[#--                  <li><a class="a-title" href="#" title="珠海爱尔眼科门诊部已成功上线"><span>[医院] 2018-8-28号 珠海爱尔眼科门诊部已成功上线</span><span class="s-info">2018-08-27</span></a></li>--]
[#--                </ul>--]
[#--              </div>--]
[#--           </div>--]
        </div>
    </div>
</div>
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

<script type='text/javascript' src='${base}/static/js/jquery-1.11.3.min.js?ef3d5'></script>
<script type='text/javascript' src='${base}/static/js/lib/easyui/jquery.easyui.min.js?v=e2447'></script>
<script type='text/javascript' src='${base}/static/js/lib/layer/layer.min.js?v=0ca1f'></script>
<script type='text/javascript' src='${base}/static/js/lib/template.js?v=05ca4'></script>
<script type='text/javascript' src='${base}/static/js/plus/watermark.js?v=88d82'></script>
<script type='text/javascript'>
    var baseUrl = '${base}';
    var sysNowTime = '${.now?string("yyyy-MM-dd HH:mm:ss")}';
    var openCloud = '${openCloud}';
    var cloudsSize = [#if openCloud && clouds??]'${clouds?size}';[#else]'0';[/#if]

    setTimeout(function (){
      $('.h1-logo').removeClass('showtip');
    },3000);
</script>
<script type='text/javascript' src='${base}/static/js/index.js?57b6a'></script>
<script type="text/javascript">
$(function () {
[#if isChangePassword == false]
   eyeIndex.popRepairPass('${base}/updatePwd'  ,true);
[/#if]
});

//添加水印
var globalWater = true;
if(globalWater){
  watermark.init({
    [#--watermark_txt: "${login_user.instName}<br />${login_user.deptName}<br />[@shiro.principal/]"--]
    watermark_txt: "[@shiro.principal/]<br />${login_user.loginCode}"
  });
}
</script>
</body>
</html>

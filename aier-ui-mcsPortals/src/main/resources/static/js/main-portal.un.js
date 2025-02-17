var portalUri = portalUri || '';
var jsPUri = portalUri || '';
jsPUri = jsPUri.replace(/(\/$)/g, "");//删除最后一个斜杠
var rqBaseUrl =  jsPUri + '/static/js/';
var jsVersion = '1.0.0';
function comBaseUrl(url){
    return rqBaseUrl + url + '.js?portalVer='+jsVersion;
}
require.config({
    baseUrl: portalUri + '/static/js/',
    map: {
        '*': {'css': 'css.min'}
    },
    paths: {
        'rx' : comBaseUrl('lib/rx'),//rxjs
        'lodash' : comBaseUrl('lib/lodash.min'),//工具包
        'pvue' : comBaseUrl('lib/petite-vue.0.3.0.umd'),//pvue
        'my97': comBaseUrl('lib/my97/WdatePicker'),//日历控件
        'jquery': comBaseUrl('jquery-1.11.3.min'),//jquery
        'easyui': comBaseUrl('lib/easyui/jquery.easyui.min'),//easyui ，细节有修改
        'easyui.extend': comBaseUrl('lib/easyui/jquery.easyui.base.extend'),//基础扩展，包括语言包、官方gridedit、扁平树扩展 和requirejs结合修正
        'jquery.extend': comBaseUrl('plus/jquery.extend'),//jquery基础扩展
        'groupview': comBaseUrl('lib/easyui/datagrid-groupview'),//datagrid-groupview
        'detailview':comBaseUrl('lib/easyui/datagrid-detailview'),//datagrid-detailview
        'transposedview':comBaseUrl('lib/easyui/datagrid-transposedview'),//datagrid-transposedview
        'easyui.dnd': comBaseUrl('lib/easyui/datagrid-dnd'),//可拖放行 datagrid-dnd
        'easyvalidate.extend': comBaseUrl('plus/easyvalidate.extend'),//easyui验证扩展，包括各种验证规则
        'easygridEdit': comBaseUrl('plus/easygridEdit.extend'),//easyui gridedit方法扩展
        'md5': comBaseUrl('lib/md5'),//md5加密脚本，仅供参考
        'zipCfg': comBaseUrl('zipConfig'), //打包配置
        'param': comBaseUrl('plus/param'), //全局公用变量
        'pub': comBaseUrl('plus/pub'),//基于基础扩展写的公共处理脚本库
        'rsa': comBaseUrl('plus/rsaPlus.min'),
        'editor': comBaseUrl('plus/editor'),
        'addrLink' : comBaseUrl('plus/addrLink'),//省市区联动
        'layer.min': comBaseUrl('lib/layer/layer.min'),//layer弹窗控件
        'moment': comBaseUrl('lib/moment.min'),//daterangepicker
        'daterangepicker': comBaseUrl('lib/daterangepicker/daterangepicker'),//daterangepicker
        'template': comBaseUrl('lib/template'),//art template
        'templateWeb': comBaseUrl('lib/template-web'),//art template
        'text': comBaseUrl('lib/text'),//require text 插件
        'hotkeys' : comBaseUrl('lib/hotkeys'),//快捷键注册库
        'pinyin_dict': comBaseUrl('lib/pinyinjs/dict/pinyin_dict'),//拼音库
        'pinyin': comBaseUrl('lib/pinyinjs/pinyinUtil'),//拼音库支持工具
        'echarts': comBaseUrl('lib/echarts/echarts.min'),//echarts完整库
        'echarts.common': comBaseUrl('lib/echarts/echarts.common.min'),//echarts常用库
        'echarts.theme': comBaseUrl('lib/echarts/echarts.theme'),//echarts 主题
        'WebUploader': comBaseUrl('lib/webuploader/webuploader.min'),//百度上传控件
        'myupload': comBaseUrl('lib/webuploader/MyAmdWebUpload'),//上传控件扩展amd版，上传require此文件
        'baidueditor': comBaseUrl('lib/ueditor1.4.3.3-utf8-jsp/myue'),//ue编辑器
        'zeroclipboard': comBaseUrl('lib/ueditor1.4.3.3-utf8-jsp/third-party/zeroclipboard/ZeroClipboard.min'),//ue编辑器
        'bdlang': comBaseUrl('lib/ueditor1.4.3.3-utf8-jsp/lang/zh-cn/zh-cn'),//ue编辑器
        'viewer' : comBaseUrl('lib/viewer.min'),//gallery相册效果
        'dragsort': comBaseUrl('lib/jquery.dragsort'),
        'dragsortS': comBaseUrl('lib/jquery.dragsort-0.5.2.min'),
        'colorPacker': comBaseUrl('plus/jquery.soColorPacker-1.0.min'),
        'qrcode' : comBaseUrl('lib/qrcode.min'),
        'swfobject' : comBaseUrl('plus/swfobject'),
        'web_socket' : comBaseUrl('plus/web_socket')

    },
    shim:{
        'lodash':[],
        'pvue':[],
        'dragsort':['jquery'],
        'dragsortS':['jquery'],
        'layer.min':['jquery'],
        'my97':{deps:[],exports: 'my97'},
        'pinyin': ['pinyin_dict'],
        'echarts':{deps:[],exports: 'echarts'},
        'rsa' :['jquery'],
        'param':['jquery'],
        'swfobject': ['jquery'],
        'web_socket': [ 'swfobject','jquery'],
        'colorPacker':['jquery'],
        'qrcode' : ['jquery'],
        'chinaMap':['echarts','echartsMap'],
        'jquery.extend':['zipCfg','param','layer.min','my97'],//依赖这些基础配置
        'easyui':['jquery'],
        'daterangepicker': {deps: ['jquery', 'moment']},
        'easyui.extend':['easyui'],
        'easyvalidate.extend':['easyui'],
        'groupview':['easyui'],
        'detailview':['easyui'],
        'transposedview':['easyui'],
        'easyui.dnd':['easyui'],
        'easygridEdit':['easyui'],
        'pub':['jquery.extend','easyui.extend','easyvalidate.extend','moment','daterangepicker'],//依赖
        'baidueditor': {deps: ['lib/ueditor1.4.3.3-utf8-jsp/ueditor.config', 'css!lib/ueditor1.4.3.3-utf8-jsp/themes/default/css/ueditor']},
        'bdlang':{deps: ['baidueditor']}
    }
});


require(["layer.min","pub" ],function(){//默认只引入pub及相关依赖的所有文件，个体文件需要依赖，文件内require
    //通过body上的 data-js来寻找执行文件及对应函数格式： data-js="文件名:函数名"，文件统一放置在js/app 扁平目录下，如：<body data-js="sys:group">
    //需要执行多个函数用 || 隔开，如：<body data-js="sys:group||api:init">
    //如果是init函数可直接省略冒号及函数名，如：<body data-js="api:init">可简写为<body data-js="api">
    var crumb=$('body').attr("data-js");
    if(crumb){
        var crumbArr = crumb.split('||');
        $.each(crumbArr,function(i,v){
            var vArr = v.split(":");
            var modId = vArr[0] , funcId = vArr[1]||'init';
//            window.console && console.log('page执行 app/'+modId+'.js中的'+funcId+'方法');//打开页面时，注意看控制台提示执行的是哪个页面的哪个方法
            // require(['/js/app/'+modId+v],function(mod){
            require(['app/'+modId],function(mod){
                if(mod){
                    var init=mod[funcId];
                    if(init&&$.isFunction(init)){
                        mod[funcId](window);
                    }else{
//                        window.console && console.log("请在"+modId+".js文件中定义"+funcId+"方法");//不存在提示
                    }
                }
            });
        });
    }
});

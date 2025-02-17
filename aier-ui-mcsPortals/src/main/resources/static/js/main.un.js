var base = base || '';
var jsPUri = portalUri || '';
jsPUri = jsPUri.replace(/(\/$)/g, "");//删除最后一个斜杠
var rqBaseUrl =  jsPUri + '/static/js/';
function comBaseUrl(url){
    return rqBaseUrl + url;
}
require.config({
    baseUrl: base + '/static/js/',
    map: {
        '*': {'css': 'css.min'}
    },
    paths: {
        'rx' : comBaseUrl('lib/rx.js?v=2c4e5'),//rxjs
        'lodash' : comBaseUrl('lib/lodash.min.js?v=62acd'),//工具包
        'pvue' : comBaseUrl('lib/petite-vue.0.3.0.umd.js?v=80c6b'),//pvue
        'my97': comBaseUrl('lib/my97/WdatePicker.js?v=8c858'),//日历控件
        'jquery': comBaseUrl('jquery-1.11.3.min.js?v=8c858'),//jquery
        'easyui': comBaseUrl('lib/easyui/jquery.easyui.min.js?v=e2447'),//easyui ，细节有修改
        'easyui.extend': comBaseUrl('lib/easyui/jquery.easyui.base.extend.js?v=abf14'),//基础扩展，包括语言包、官方gridedit、扁平树扩展 和requirejs结合修正
        'jquery.extend': comBaseUrl('plus/jquery.extend.js?v=8baa1'),//jquery基础扩展
        'groupview': comBaseUrl('lib/easyui/datagrid-groupview.js?v=36bd0'),//datagrid-groupview
        'detailview':comBaseUrl('lib/easyui/datagrid-detailview.js?v=3202a'),//datagrid-detailview
        'transposedview':comBaseUrl('lib/easyui/datagrid-transposedview.js?v=63c3d'),//datagrid-transposedview
        'easyui.dnd': comBaseUrl('lib/easyui/datagrid-dnd.js?v=54a61'),//可拖放行 datagrid-dnd
        'easyvalidate.extend': comBaseUrl('plus/easyvalidate.extend.js?v=ce7cb'),//easyui验证扩展，包括各种验证规则
        'easygridEdit': comBaseUrl('plus/easygridEdit.extend.js?v=3737c'),//easyui gridedit方法扩展
        'md5': comBaseUrl('lib/md5.js?v=ecf6a'),//md5加密脚本，仅供参考
        'zipCfg': comBaseUrl('zipConfig'), //打包配置
        'param': comBaseUrl('plus/param.js?v=ff37e'), //全局公用变量
        'pub': comBaseUrl('plus/pub.js?v=5090d'),//基于基础扩展写的公共处理脚本库
        'rsa': comBaseUrl('plus/rsaPlus.min.js?v=35481'),
        'editor': comBaseUrl('plus/editor.js?v=c4145'),
        'addrLink' : comBaseUrl('plus/addrLink.js?v=ce461'),//省市区联动
        'layer.min': comBaseUrl('lib/layer/layer.min.js?v=a80ae'),//layer弹窗控件
        'moment': comBaseUrl('lib/moment.min.js?v=8c2de'),//daterangepicker
        'daterangepicker': comBaseUrl('lib/daterangepicker/daterangepicker.js?v=ae0da'),//daterangepicker
        'template': comBaseUrl('lib/template.js?v=05ca4'),//art template
        'templateWeb': comBaseUrl('lib/template-web.js?v=42238'),//art template
        'text': comBaseUrl('lib/text.js?v=45b37'),//require text 插件
        'hotkeys' : comBaseUrl('lib/hotkeys.js?v=b43c7'),//快捷键注册库
        'pinyin_dict': comBaseUrl('lib/pinyinjs/dict/pinyin_dict.js?v=fca9a'),//拼音库
        'pinyin': comBaseUrl('lib/pinyinjs/pinyinUtil.js?v=919da'),//拼音库支持工具
        'echarts': comBaseUrl('lib/echarts/echarts.min.js?v=e6c53'),//echarts完整库
        'echarts.common': comBaseUrl('lib/echarts/echarts.common.min.js?v=7c207'),//echarts常用库
        'echarts.theme': comBaseUrl('lib/echarts/echarts.theme.js?v=404b3'),//echarts 主题
        'WebUploader': comBaseUrl('lib/webuploader/webuploader.min.js?v=43487'),//百度上传控件
        'myupload': comBaseUrl('lib/webuploader/MyAmdWebUpload.js?v=77b7d'),//上传控件扩展amd版，上传require此文件
        'baidueditor': comBaseUrl('lib/ueditor1.4.3.3-utf8-jsp/myue.js?v=4ff1e'),//ue编辑器
        'zeroclipboard': comBaseUrl('lib/ueditor1.4.3.3-utf8-jsp/third-party/zeroclipboard/ZeroClipboard.min.js?v=cd022'),//ue编辑器
        'bdlang': comBaseUrl('lib/ueditor1.4.3.3-utf8-jsp/lang/zh-cn/zh-cn'),//ue编辑器
        'viewer' : comBaseUrl('lib/viewer.min.js?v=1e0ba'),//gallery相册效果
        'dragsort': comBaseUrl('lib/jquery.dragsort.js?v=8672a'),
        'dragsortS': comBaseUrl('lib/jquery.dragsort-0.5.2.min.js?v=97eab'),
        'colorPacker': comBaseUrl('plus/jquery.soColorPacker-1.0.min.js?v=83e13'),
        'qrcode' : comBaseUrl('lib/qrcode.min.js?v=517b5'),
        'swfobject' : comBaseUrl('plus/swfobject.js?v=1bf4b'),
        'web_socket' : comBaseUrl('plus/web_socket.js?v=67b78')
    },
    shim:{
        'lodash':[],
        'viewer':['jquery'],
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

require.config({
    baseUrl: '/static/js/',
    map: {
        '*': {'css': 'css.min'}
    },
     paths: {
      'lodash' : 'lib/lodash.min',//工具包
      'pvue' : 'lib/petite-vue.0.2.3',//pvue
      'rx' : 'lib/rx',
      'my97':'lib/my97/WdatePicker',//日历控件
      'jquery': 'jquery-1.11.3.min',//jquery
      'easyui':'lib/easyui/jquery.easyui.min',//easyui ，细节有修改
      'easyui.extend':'lib/easyui/jquery.easyui.base.extend',//基础扩展，包括语言包、官方gridedit、扁平树扩展 和requirejs结合修正
      'groupview':'lib/easyui/datagrid-groupview',//datagrid-groupview
      'easyui.dnd':'lib/easyui/datagrid-dnd',//可拖放行 datagrid-dnd
      'md5':'lib/md5',//md5加密脚本，仅供参考
      'jquery.extend': 'plus/jquery.extend',//jquery基础扩展
      'easyvalidate.extend': 'plus/easyvalidate.extend',//easyui验证扩展，包括各种验证规则
      'easygridEdit': 'plus/easygridEdit.extend',//easyui gridedit方法扩展
      'zipCfg':'zipConfig', //打包配置
      'param':'plus/param', //全局公用变量
      "addrLink" : "plus/addrLink",//省市区联动
      'pub':'plus/pub',//基于基础扩展写的公共处理脚本库
      'btnExt' : 'plus/btnExt',
      'btnSearch' : 'plus/btnSearch',
      'btnSearchInp' : 'plus/btnSearchInp',
      'readCard' : 'plus/readCard',
      'rsa' : 'plus/rsaPlus.min',
      'editor': 'plus/editor',
      'tabPatientInfo':'app/tabPatientInfo',
      'tabPatientInfoComp' : 'app/tabPatientInfoComp',
      'layer.min':'lib/layer/layer.min',//layer弹窗控件
      'viewer' : 'lib/viewer.min',//gallery相册效果
      'moment':'lib/moment.min',//daterangepicker
      'daterangepicker':'lib/daterangepicker/daterangepicker',//daterangepicker
      'pinyin_dict':'lib/pinyinjs/dict/pinyin_dict',//拼音库
      'pinyin':'lib/pinyinjs/pinyinUtil',//拼音库支持工具
      'template':'lib/template',//art template
      'templateWeb':'lib/template-web',//art template
      'text':'lib/text',//require text 插件
      'hotkeys' : 'lib/hotkeys',//快捷键注册库
      'echarts':'lib/echarts/echarts.min',//echarts完整库
      'echarts.common':'lib/echarts/echarts.common.min',//echarts常用库
      'echarts.theme':'lib/echarts/echarts.theme',//echarts 主题
      'WebUploader':'lib/webuploader/webuploader.min',//百度上传控件
      'myupload':'lib/webuploader/MyAmdWebUpload',//上传控件扩展amd版，上传require此文件
      'fancybox':'lib/fancybox/jquery.fancybox-1.3.4.pack',//fancybox弹窗控件
      'baidueditor': 'lib/ueditor1.4.3.3-utf8-jsp/myue',//ue编辑器
      'bdlang': 'lib/ueditor1.4.3.3-utf8-jsp/lang/zh-cn/zh-cn',//ue编辑器
      'zeroclipboard': 'lib/ueditor1.4.3.3-utf8-jsp/third-party/zeroclipboard/ZeroClipboard.min',//ue编辑器
      'hightlight': 'lib/SyntaxHighlighter/brush',//代码高亮
      'dragsort': 'lib/jquery.dragsort',
      'ocxControl': 'plus/ocxControl',
      'swfobject' : 'plus/swfobject',
      'web_socket' : 'plus/web_socket',
      'readcardPop' : 'plus/readcardPop'
    },
    shim:{
      'lodash':[],
      'viewer':['jquery'],
      'dragsort':['jquery'],
      'layer.min':['jquery'],
      'fancybox':['jquery'],
      'btnExt' :['jquery'],
      'my97':{deps:[],exports: 'my97'},
      'echarts':{deps:[],exports: 'echarts'},
      'rsa' :['jquery'],
      'param':['jquery'],
      'btnSearch' : ['jquery','easyui'],
      'btnSearchInp' : ['jquery','easyui'],
      'swfobject': ['jquery'],
      'web_socket': [ 'swfobject','jquery'],
      'ocxControl' : ['swfobject','web_socket'],
      'tabPatientInfo' :  ['btnExt','btnSearch'],
      'tabPatientInfoComp' : ['jquery','easyui'],
      'hightlight':['jquery'],
      'jquery.extend':['zipCfg','param','layer.min','my97'],//依赖这些基础配置
      'easyui':['jquery'],
      'daterangepicker': {deps: ['jquery', 'moment']},
      'easyui.extend':['easyui'],
      'easyvalidate.extend':['easyui'],
      'groupview':['easyui'],
      'easyui.dnd':['easyui'],
      'easygridEdit':['easyui'],
      'readcardPop': ['jquery','readCard'],
      'bootstrap':['jquery'],
      'pinyin': ['pinyin_dict'],
      'chinaMap':['echarts','echartsMap'],
      'pub':['jquery.extend','easyui.extend','easyvalidate.extend','moment','daterangepicker'],//依赖
      'baidueditor': {deps: ['lib/ueditor1.4.3.3-utf8-jsp/ueditor.config', 'css!lib/ueditor1.4.3.3-utf8-jsp/themes/default/css/ueditor']},
      'bdlang':{deps: ['baidueditor']}
    }
});

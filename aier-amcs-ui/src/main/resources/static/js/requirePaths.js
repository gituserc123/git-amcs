var base = base || '';
var per = '1.0.1';
require.config({
  baseUrl: base + '/static/js/',
  paths: {
    'watermark':'plus/watermark.js?v=88d82',
    'pForm' : 'pvue/form.js?v=a3147',
    'pTabs' : 'pvue/tabs.js?v=833dd',
    'pCreate' : 'pvue/create.js?v=ae13d',
    'pTools' : 'pvue/tools.js?v=c6550',
    'plus' : 'plus/plus.js?v=98496',
    'gojs':'lib/go',
    'eventTableGroup':'app/eventTab/eventTableGroup.js?v=c6550',
    'eventFlowchart':'app/eventTab/eventFlowchart.js?v=c6550',
    'eventImage':'app/eventTab/eventImage.js?v=c6550',
    'eventReview': 'app/eventTab/eventReview.js?v=c6550',
    'uploadImages':'plus/uploadImages.js?v=c6550',
    'vue':"lib/vue.2.7.8.min.js?v=c6550",
    'export' : 'plus/export.js?v=e1ff9',
    'lawOpinionList':'app/law/lawOpinionList.js?v=c6551',

  },
  shim:{
    'eventFlowchart':['gojs','vue','css!app/eventTab/eventFlowchart'],
    'eventReview': ['vue','css!app/eventTab/eventFlowchart'],
    'uploadImages':['WebUploader','jquery','pub','css!plus/uploadImages'],
  }
});

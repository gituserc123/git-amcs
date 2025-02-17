<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>集团</title>
  [#include "/WEB-INF/views/common/include_resources.ftl"]
  <script src="${base}/static/css/iconfont.js"></script>
  <style>
    html,body{background-color: #f2f2f2}
    .flex {display: flex}
    .flex-1{flex: 1 1 0%}
    .flex-col{flex-direction: column}
    .flex-wrap{flex-wrap: wrap}

    .justify-center{justify-content: center}

    .items-center{align-items: center}


    .p-10{padding: 10px;}
    .py-10{padding-top: 10px;padding-bottom: 10px}
    .py-15{padding-top: 15px;padding-bottom: 15px}
    .px-30{padding-left: 30px;padding-right: 30px}
    .pb-10{padding-bottom: 10px;}

    .m-10{margin: 10px;}
    .my-10{margin-top: 10px;margin-bottom: 10px;}


    .text-white{color: #FFFFFF}
    .text-red{color: #ff5630}
    .text-black{color: #101010}

    .bg-blue{background-color: #00a0e9}
    .bg-white{background-color: #FFFFFF}
    .border-0{border-width: 0;}

    .btn-upload:hover{box-shadow: 0px 0 5px 1px #ccc;}
    .normal-btn{
      background-color: #FFFFFF;
      color: #00a0e9;
      padding: 10px 15px;
      cursor: pointer;
      border:1px solid #00a0e9;
    }
    .active-btn{
      background-color: #00a0e9;
      color: #FFFFFF;
      border: 0;
      padding: 10px 15px;
    }
    .icon {
      width: 1em; height: 1em;
      vertical-align: -0.15em;
      fill: currentColor;
      overflow: hidden;
    }
    .cursor-pointer{cursor: pointer}
    .gotab-hover:hover{background-color: #EEEEEE;border-radius: 10px; transition: all .5s;}

    .textbox .textbox-text{
      height:40px!important;
      line-height:40px;
    }
  </style>
</head>
<body>
<div id="hello">
  <div class="bg-white p-10 m-10">
    <form id="sbox" autocomplete="off" >
      <div class="flex mar-15">
        <div class="flex">
          <button type="button" class="normal-btn" :class="{'active-btn':activeBtn==0}" @click="changeTime(0)">上报时间</button>
          <button type="button" class="normal-btn" :class="{'active-btn':activeBtn==1}" @click="changeTime(1)">发生时间</button>
        </div>
        <div class="mar-l15">
          <input id="ipnDate" style="width: 180px;padding: 5px;height: 40px;" type="text" name="date" placeholder="    开始时间   至   结束时间"/>
        </div>
        <div class="mar-l15" style="display: flex;align-items: center">
          <label class="lab-inline pad-r15">省区</label>
          <select class="drop drop-sl-v w-100" name="province" id="province" data-options="clearIcon:true"></select>
          <label class="lab-inline pad-r15 pad-l15">医院级别</label>
          <select class="drop drop-sl-v w-100" name="ehrLevel" id="ehrLevel" data-options="clearIcon:true"></select>
          <label class="lab-inline pad-r15 pad-l15">涉及科室/人员类型</label>
          <select class="drop  w-80" id="tags" name="tags" data-options="multiple:true,clearIcon:true"></select>
          <label class="lab-inline pad-r15 pad-l15">事件分类一级</label>
          <select class="drop w-150" id="gradeOne" name="gradeOne" data-options="multiple:true,clearIcon:true"></select>
          <label class="lab-inline pad-r15 pad-l15">事件分级</label>
          <select class="drop w-100" id="eventLevel" name="eventLevel"  data-options="multiple:true, clearIcon:true" ></select>
        </div>
        <button class="mar-l15 normal-btn" type="button" @click="query">查询</button>
      </div>
    </form>
    <div class="flex" style="border-top: 1px solid #cccccc;">
      <div id="left-com" class="px-30 flex flex-col justify-center my-10" style="border-right: 2px solid #d6d6d6;">
        <div class="flex text-white items-center bg-blue border-0 py-10 px-30 btn-upload" style="border-radius: 5px;cursor: pointer;" @click="goUpload">
          <img style="width: 30px;" src="https://avis-bigfile-1257101260.cos.ap-guangzhou.myqcloud.com/avis/images/icon-tjrs.png" />
          <div type="button" class="mar-l10" >上报总数</div>
        </div>
        <div class="mar-t10">
          <span class="text-black" style="font-size: 20px;">总数：</span>
          <span class="text-red" style="font-size: 30px;">{{allCount}}</span>
        </div>
      </div>
      <div class="right-com flex flex-wrap flex-1 pad-l15 pad-t10">
        <template v-for="(item,cindex) in list">
          <incident-info :data="item"></incident-info>
        </template>
      </div>
    </div>
  </div>

  <div class="p-10 m-10 bg-white">
    <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">不良事件类型分布图</div>
    <div id="echart-groupByGradeOne" style="height: 300px;width: 100%;"></div>
  </div>

  <div class="p-10 m-10 bg-white">
    <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">省区不良事件上报分布图</div>
    <div id="echart-groupByHospId" style="height: 300px;width: 100%;"></div>
  </div>

  <div class="p-10 m-10 bg-white" style="display: flex;">
    <div style="flex: 1;">
      <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">亚专科</div>
      <div id="echart-sec" style="height: 300px;width: 100%;"></div>
    </div>
    <div style="width: 10px"></div>
    <div style="flex: 1;">
      <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">事件分级</div>
      <div id="echart-event" style="height: 300px;width: 100%;"></div>
    </div>
  </div>
  <div class="p-10 m-10 bg-white" style="display: flex;">
    <div style="flex: 1;">
      <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">医院不良事件上报TOP20分布图</div>
      <div id="echart-groupByHospName" style="height: 300px;width: 100%;"></div>
    </div>
    <div style="flex: 1;">
      <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">严重程度</div>
      <div id="echart-severity" style="height: 300px;width: 100%;"></div>
    </div>
  </div>
  <div class="p-10 m-10 bg-white">
    <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">省区审核超10天分布图</div>
    <div id="echart-groupProvReviewOver10Days" style="height: 300px;width: 100%;"></div>
  </div>

</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
  $store.set('parentDataCountIndex', "");
  var eOpt = {
    tooltip:{
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
    },
    grid:{
      // top:"3%",
      // left:"3%",
      left:30,
      right:"3%",
      // bottom:"3%"
    },
    xAxis: {
      type: 'category',
      data: [],//x轴的文本
      axisLabel: {
        // interval: 0,    //强制文字产生间隔
        // rotate: 45,     //文字逆时针旋转45°
        textStyle: {    //文字样式
          // color: "black",
          // fontSize: 16,
          // fontFamily: 'Microsoft YaHei'
        }
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [],//y轴的值
        // barWidth:30,
        barMinWidth:10,
        barMaxWidth:20,
        itemStyle:{
          normal:{
            label:{show:true,position:"top"},
            color:"#00a0e9"
          }
        },
        type: 'bar'
      }
    ]
  };
  var label_info = [
    {img:"icon-shenpi-2",label:'待医院审批',count:'',color:'#3dcea7'},
    {img:"icon-bianjiwenjian",label:'待省区审批',count:'',color:'#e98f36',url:{title:"集团不良事件管理",uri:"${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&node=2"}},
    {img:"icon-a-ziyuan31",label:'待省区审批(超10天)',count:'',color:'#e98f36',url:{title:"集团不良事件管理",uri:"${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&node=210"}},
    {img:"icon-zhanghutuikuan",label:'被回退',count:'',color:'#7cba59',url:{title:"集团不良事件跟踪",uri:"${base}/ui/service/biz/amcs/adverse/common/listTrackPage?page_type=4&is_index=1"}},
    {img:"icon-biaodanwancheng2",label:'归档/完成',count:'',color:'#17abe3'},
    {img:"icon-weishangbao",label:'未完结',count:'',color:'#db639b',url:{title:"未完结列表",uri:"${base}/ui/amcs/countIndex/unfinishedGroup"}},
    {img:"icon-shenhetongguo",label:'未完结(超过90天)',count:'',color:'#ed7d31',url:{title:"未完结(超过90天)列表",uri:"${base}/ui/amcs/countIndex/unfinished90Group"}},
    {img:"icon-genzong",label:'省区审核通过',count:'',color:'#7cba59',url:{title:"集团不良事件管理",uri:"${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&node=3"}},
    {img:"icon-fengxiandengji-",label:'高风险',count:'',color:'#db639b'},
    {img:"icon-zelvxingzhengjiufen",label:'纠纷',count:'',color:'#17abe3',url:{title:"纠纷列表",uri:"${base}/ui/amcs/countIndex/disputeGroup"}},
    {img:"icon-dingdan",label:'完结',count:'',color:'#e98f36'},
    {img:"icon-bg-money",label:'赔偿',count:'',color:'#17abe3',url:{title:"赔偿列表",uri:"${base}/ui/amcs/countIndex/compensationGroup"}},
    {img:"icon-shenpi",label:'超时上报',count:'',color:'#db639b'},
    {img:"icon-shenhebutongguo",label:'事件进展',count:'',color:'#7cba11',url:{title:"事件进展列表",uri:"${base}/ui/amcs/countIndex/eventsProgressGroup"}},
    {img:"icon-shenpi-2",label:'已查阅',count:'',color:'#2070a2',url:{title:"集团不良事件管理",uri:"${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&isGroupReview=1&node=3"}},
    {img:"icon-shenpi",label:'未查阅',count:'',color:'#5e8018',url:{title:"集团不良事件管理",uri:"${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&isGroupReview=0&node=3"}},
    {img:"icon-a-ziyuan31",label:'多发感染事件',count:'',color:'#5a1880',url:{title:"多发感染事件管理",uri:"${base}/ui/amcs/countIndex/infectionGroup"}},
    {img:"icon-dingdan",label:'其他多发事件',count:'',color:'#268018',url:{title:"其他多发事件管理",uri:"${base}/ui/amcs/countIndex/otherMultipleGroup"}},
    {img:"icon-wodeshangbao",label:'重点事件',count:'',color:'#805c18',url:{title:"重点事件",uri:"${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&node=3"}},
  ];

  requirejs(["echarts",'vue', 'moment', 'pub'], function (echarts,Vue,moment) {

    Vue.component("incident-info",{
      props:['data'],
      computed:{
        iconname(){
          return "#"+ this.data.img;
        },
        canclick(){
          if(this.data.url || this.data.onClick){
            return true;
          }else{
            return false;
          }
        }
      },
      methods:{
        goTab(){
          if(this.data.onClick){
            this.data.onClick();
          }else if(this.canclick){
            var params = {};
            var formData = $('#sbox').sovals();
            if(this.$parent.activeBtn == 0){
              params.reportDateBegin = this.$parent.startTime;
              params.reportDateEnd = this.$parent.endTime;
              params.province = $('#province').combobox('getValue');
              params.tags = $('#tags').combobox('getValues').join();
              formData.reportDateBegin = this.$parent.startTime;
              formData.reportDateEnd = this.$parent.endTime;
            }else if(this.$parent.activeBtn == 1){
              params.eventDateBegin = this.$parent.startTime;
              params.eventDateEnd = this.$parent.endTime;
              params.province = $('#province').combobox('getValue');
              params.tags = $('#tags').combobox('getValues').join();
              formData.eventDateBegin = this.$parent.startTime;
              formData.eventDateEnd = this.$parent.endTime;
            }
            if(this.data.url.title=='重点事件'){
              formData.isFocus=true;
            }
            params.formData = encodeURIComponent(JSON.stringify(formData));
            $pop.newTabWindow(this.data.url.title,this.appendUrlParams(this.data.url.uri,params));
          }
        },
        appendUrlParams(url,params){
          var resultUrl = url;
          var keyArr = Object.keys(params);
          if(url.indexOf("?")==-1){
            var firstParams = keyArr.shift();
            var paramsKey = "?"+ firstParams + "=";
            if(params[firstParams]){
              paramsKey += params[firstParams];
            }
            resultUrl += paramsKey;
          }
          keyArr.forEach(function(key){
            var paramsKey = "&"+ key + "=";
            if(params[key]){
              paramsKey += params[key];
            }
            resultUrl += paramsKey;
          });
          return resultUrl;
        }
      },
      template:`
              <div class="flex py-15 justify-left items-center w-200" :class="{'cursor-pointer':canclick,'gotab-hover':canclick}" @click="goTab">
                <div style="font-size: 40px;" :style="{color:data.color}">
                    <svg class="icon" aria-hidden="true">
                        <use :xlink:href="iconname"></use>
                    </svg>
                </div>
                <div class="flex flex-col mar-l10">
                  <span style="color: #676c71;font-size: 14px;">{{data.label}}</span>
                  <div class="text-red pad-t10">
                    <span style="font-size: 16px;">{{data.count}}</span> 件
                  </div>
                </div>
              </div>
            `
    });

    let App = new Vue({
      el: "#hello",
      data() {
        return {
          activeBtn:0,
          allCount: 0,
          list:[]
        }
      },
      mounted() {
        let $this = this;
        $this.startTime = moment().subtract(1, 'months').date(21).format('YYYY-MM-DD');
        $this.endTime = moment().date(20).format('YYYY-MM-DD');
        $form.rangeDate("#ipnDate", {
          val:[moment().subtract(1, 'months').date(21),moment().date(20)],
          maxDate: new Date(),
          maxSpan:{days: 366},
          auto:false,
          callback: function (start, end, label) {
            $this.startTime = start.format('YYYY-MM-DD');
            $this.endTime = end.format('YYYY-MM-DD');
          }
        });
        $("#ipnDate").on('cancel.daterangepicker', function(ev, picker) {
          $this.startTime = "";
          $this.endTime = "";
        });
        var params = {};
        params.reportDateBegin = $this.startTime;
        params.reportDateEnd = $this.endTime;
        $store.set('parentDataCountIndex', params);

        this.queryTopIncident(params);

        this.initGroupByGradeOne();
        this.queryGroupByGradeOne(params);

        this.initGroupByHospId();
        this.queryGroupByHospId(params);


        this.initSec();
        this.querySec(params);

        this.initEvent();
        this.queryEvent(params);

        this.initSeverity();
        this.querySeverity(params);

        this.initGroupByHospName();
        this.queryGroupByHospName(params);

        this.initGroupProvReviewOver10Days();
        this.queryGroupProvReviewOver10Days(params);

        this.initResize();
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
          $('#province').combobox({
            data:rst,
            valueField:'id',
            textField:'name'
          });
          //$('#province').combobox('loadData', rst);
        });
        var param = {paraType : ['tag_type', 'grade_one']};
        $ajax.postSync('${base}/ui/amcs/dict/getAeMoreList',param,false,false).done(function (rst) {
          var dict = rst.data;
          $('#tags').combobox({
            data:dict['tag_type'],
            valueField:'valueCode',
            textField:'valueDesc',
            multiple:true,
            editable:false,
          });
          $('#gradeOne').combobox({
            data:dict['grade_one'],
            valueField:'valueCode',
            textField:'valueDesc',
            multiple:true,
            editable:false
          });
        });
        // 医院级别下拉列表初始化
        $('#ehrLevel').combobox({
          data:eval('[{"valueDesc":"集团总部","valueCode":10,},{"valueDesc":"省区","valueCode":11,},{"valueDesc":"省会级医院","valueCode":12,},{"valueDesc":"地市级医院","valueCode":13,},{"valueDesc":"县市级医院","valueCode":14,},{"valueDesc":"眼科门诊","valueCode":15,},{"valueDesc":"大区","valueCode":20,},{"valueDesc":"区域","valueCode":30,}]'),
          valueField:'valueCode',
          textField:'valueDesc'
        });
        // 医院级别下拉列表初始化
        $('#eventLevel').combobox({
          data:eval('[{"valueDesc":"IV级（隐患事件）","valueCode":"IV级（隐患事件）",},{"valueDesc":"III 级（未造成后果事件）","valueCode":"III 级（未造成后果事件）",},{"valueDesc":"II 级（不良事件）","valueCode":"II 级（不良事件）",},{"valueDesc":"I级（警告事件）","valueCode":"I级（警告事件）",}]'),
          valueField:'valueCode',
          textField:'valueDesc'
        });
      },
      methods:{
        initResize(){
          var $this = this;
          window.onresize = function(){
            $this.echart_groupByGradeOne && $this.echart_groupByGradeOne.resize();
            $this.echart_groupByHospId && $this.echart_groupByHospId.resize();
            $this.echart_sec && $this.echart_sec.resize();
            $this.echart_event && $this.echart_event.resize();
            $this.echart_groupByHospName && $this.echart_groupByHospName.resize();
          }
        },
        initGroupByGradeOne(){
          var dom_groupByGradeOne = document.getElementById("echart-groupByGradeOne");//不良事件类型分布图
          var echart_groupByGradeOne = echarts.init(dom_groupByGradeOne);
          echart_groupByGradeOne.setOption(eOpt);
          this.echart_groupByGradeOne = echart_groupByGradeOne;
          this.echart_groupByGradeOne.on('click', function(params) {
            $pop.newTabWindow("不良事件类型表","${base}/ui/amcs/countIndex/countByParamsPage?condJson=" + encodeURIComponent(JSON.stringify(params.data)));
          });
        },
        initGroupByHospId(){
          var dom_groupByHospId = document.getElementById("echart-groupByHospId");//省区不良事件上报分布图
          var echart_groupByHospId = echarts.init(dom_groupByHospId);
          echart_groupByHospId.setOption(eOpt);
          this.echart_groupByHospId = echart_groupByHospId;
          this.echart_groupByHospId.on('click', function(params) {
            $pop.newTabWindow("省区不良事件上报表","${base}/ui/amcs/countIndex/countByParamsPage?condJson=" + encodeURIComponent(JSON.stringify(params.data)));
          });
        },
        initGroupProvReviewOver10Days(){
          var dom_groupProvReviewOver10Days = document.getElementById("echart-groupProvReviewOver10Days");//省区审核超10天分布图
          var echart_groupProvReviewOver10Days = echarts.init(dom_groupProvReviewOver10Days);
          echart_groupProvReviewOver10Days.setOption(eOpt);
          this.echart_groupProvReviewOver10Days = echart_groupProvReviewOver10Days;
          this.echart_groupProvReviewOver10Days.on('click', function(params) {
            $pop.newTabWindow("省区审核超10天表","${base}/ui/amcs/countIndex/countByParamsPage?condJson=" + encodeURIComponent(JSON.stringify(params.data)));
          });
        },
        initSec(){//亚专科
          var dom_sec = document.getElementById("echart-sec");//不良事件类型分布图
          var echart_sec = echarts.init(dom_sec);
          echart_sec.setOption(eOpt);
          this.echart_sec = echart_sec;
          this.echart_sec.on('click', function(params) {
            // $pop.newTabWindow("不良事件类型表","https://tanglv.myds.me:30000");
            $pop.newTabWindow("亚专科","${base}/ui/amcs/countIndex/countByParamsPage?condJson=" + encodeURIComponent(JSON.stringify(params.data)));
          });
        },
        initEvent(){//事件分级
          var dom_event = document.getElementById("echart-event");//不良事件类型分布图
          var echart_event = echarts.init(dom_event);
          echart_event.setOption({
            tooltip: {
              trigger: 'item'
            },
            grid:{right:30},
            series: [
              {
                // name: 'Access From',
                type: 'pie',
                radius: '44%',
                data: [

                ],
                label:{
                  formatter: '{b} {c}件 ({d}%)',
                  position: 'outside', // 设置标签位置为外部
                  alignTo: 'edge', // 设置标签对齐边缘
                  edgeDistance: '15%', // 设置标签与饼图边缘的距离
                  lineStyle: {
                    color: '#999', // 设置标签线颜色
                    width: 1 // 设置标签线宽度
                  }
                },
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          });
          this.echart_event = echart_event;
        },
        initGroupByHospName(){
          var dom_groupByHospName = document.getElementById("echart-groupByHospName");//医院不良事件上报分布图
          var echart_groupByHospName = echarts.init(dom_groupByHospName);
          echart_groupByHospName.setOption(eOpt);
          this.echart_groupByHospName = echart_groupByHospName;
          this.echart_groupByHospName.on('click', function(params) {
            $pop.newTabWindow("医院不良事件上报TOP20","${base}/ui/amcs/countIndex/countByParamsPage?condJson=" + encodeURIComponent(JSON.stringify(params.data)));
          });
        },
        initSeverity(){
          var dom_severity = document.getElementById("echart-severity");//不良事件类型分布图
          var echart_severity = echarts.init(dom_severity);
          echart_severity.setOption({
            tooltip: {
              trigger: 'item'
            },
            grid:{right:30},
            series: [
              {
                // name: 'Access From',
                type: 'pie',
                radius: '44%',
                data: [

                ],
                label:{
                  formatter: '{b} {c}件 ({d}%)',
                  position: 'outside', // 设置标签位置为外部
                  alignTo: 'edge', // 设置标签对齐边缘
                  edgeDistance: '15%', // 设置标签与饼图边缘的距离
                  lineStyle: {
                    color: '#999', // 设置标签线颜色
                    width: 1 // 设置标签线宽度
                  }
                },
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          });
          this.echart_severity = echart_severity;

        },
        changeTime(type){
          this.activeBtn = type;
        },
        query(){
          var params = {};
          if(this.activeBtn == 0){
            params.reportDateBegin = this.startTime;
            params.reportDateEnd = this.endTime;
            params.province = $('#province').combobox('getValue');
            params.ehrLevel = $('#ehrLevel').combobox('getValue');
            params.tags = $('#tags').combobox('getValues').join();
            params.gradeOneCodeStr = $('#gradeOne').combobox('getValues').join();
            params.eventLevels = $('#eventLevel').combobox('getValues').join();
          }else if(this.activeBtn == 1){
            params.eventDateBegin = this.startTime;
            params.eventDateEnd = this.endTime;
            params.province = $('#province').combobox('getValue');
            params.ehrLevel = $('#ehrLevel').combobox('getValue');
            params.tags = $('#tags').combobox('getValues').join();
            params.gradeOneCodeStr = $('#gradeOne').combobox('getValues').join();
            params.eventLevels = $('#eventLevel').combobox('getValues').join();
          }
          this.queryTopIncident(params);
          this.queryGroupByGradeOne(params);
          this.queryGroupByHospId(params);
          this.querySec(params);
          this.queryEvent(params);
          this.querySeverity(params);
          this.queryGroupByHospName(params);
          this.queryGroupProvReviewOver10Days(params);
          $store.set('parentDataCountIndex', params);
        },
        goUpload(){
        },
        queryTopIncident(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/group",//集团
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst){
                let infoObj = label_info.map((item) => {
                  var count = rst[item.label];
                  if (count !== undefined) {
                    item.count = count;
                    return item;
                  }

                });
                this.allCount = rst['总数'];
                this.list = infoObj;
              }
            },
          });
        },
        queryGroupByGradeOne(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupByGradeOne",//医院上报
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var textList = [];
                var valList = [];
                rst.forEach((item)=>{
                  textList.push(item.text);
                  //valList.push(item.val);
                  let tmpObj = {};
                  tmpObj.value = item.val;
                  tmpObj.gradeOne = item.text;
                  tmpObj.groupType = 'groupByGradeOne';
                  // 将 params 的值推入 valList
                  for (let key in params) {
                    tmpObj[key] = params[key];
                  }
                  valList.push(tmpObj);
                });

                var eOpt = {
                  xAxis: {
                    data: textList,

                    axisLabel: {
                      interval: 0,    //强制文字产生间隔
                      rotate: 25,     //文字逆时针旋转25°
                    }
                  },
                  yAxis: {
                    type: 'value',
                  },
                  series: [{
                    data: valList,
                  }
                  ]
                };
                this.echart_groupByGradeOne.setOption(eOpt);
              }else{
                var eOpt = {
                  xAxis: {
                    data: [],
                  },
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_groupByGradeOne.setOption(eOpt);
              }
            },
          });
        },
        queryGroupByHospId(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupByHospId",//医院上报
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var textList = [];
                var valList = [];
                rst.forEach((item)=>{
                  textList.push(item.province);
                  //valList.push(item.val);
                  let tmpObj = {};
                  tmpObj.value = item.val;
                  tmpObj.strHospIds = item.strHospIds;
                  tmpObj.groupType = 'groupByHospId';
                  // 将 params 的值推入 valList
                  for (let key in params) {
                    tmpObj[key] = params[key];
                  }
                  valList.push(tmpObj);
                });
                var eOpt = {
                  xAxis: {
                    data: textList,
                    axisLabel: {
                      interval: 0,    //强制文字产生间隔
                      // formatter: function(value) {
                      //   return value.split('').join('\n')
                      // }
                      rotate: 25,     //文字逆时针旋转25°
                      // textStyle: {    //文字样式
                      //     // color: "black",
                      //     // fontSize: 16,
                      // }
                    }
                  },
                  yAxis: {
                    type: 'value'
                  },
                  series: [{
                    data: valList,
                  }
                  ]
                };
                this.echart_groupByHospId.setOption(eOpt)
              }else{
                var eOpt = {
                  xAxis: {
                    data: [],
                  },
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_groupByHospId.setOption(eOpt);
              }
            },
          });
        },
        querySec(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupBySubspecialty",//医院上报
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var textList = [];
                var valList = [];
                rst.forEach((item)=>{
                  textList.push(item.subspecialty);
                  //valList.push(item['val']);
                  let tmpObj = {};
                  tmpObj.value = item.val;
                  tmpObj.subspecialty = item.subspecialty;
                  tmpObj.groupType = 'groupBySubspecialty';
                  // 将 params 的值推入 valList
                  for (let key in params) {
                    tmpObj[key] = params[key];
                  }
                  valList.push(tmpObj);
                });
                var eOpt = {
                  xAxis: {
                    data: textList,
                    axisLabel: {
                      interval: 0,    //强制文字产生间隔
                      rotate: 25,     //文字逆时针旋转25°
                    }
                  },
                  yAxis: {
                    type: 'value'
                  },
                  series: [{
                    data: valList,
                  }
                  ]
                };
                this.echart_sec.setOption(eOpt)
              }else{
                var eOpt = {
                  xAxis: {
                    data: [],
                  },
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_sec.setOption(eOpt);
              }
            },
          });
        },
        queryEvent(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupByEventLevel",//医院上报
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var datas = [];
                rst.forEach((item)=>{
                  var o = {
                    value:item['val'],
                    name:item['eventlevel']
                  };
                  datas.push(o);
                });
                var eOpt = {
                  series: [
                    {
                      data: datas,
                    }
                  ]
                };
                this.echart_event.setOption(eOpt)
              }else{
                var eOpt = {
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_event.setOption(eOpt);
              }
            },
          });
        },
        querySeverity(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupBySeverityLevel",//医院上报
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var datas = [];
                rst.forEach((item)=>{
                  var o = {
                    value:item['val'],
                    name:item['severityname']
                  };
                  datas.push(o);
                });
                var eOpt = {
                  series: [
                    {
                      data: datas,
                    }
                  ]
                };
                this.echart_severity.setOption(eOpt)
              }else{
                var eOpt = {
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_severity.setOption(eOpt);
              }
            },
          });

        },
        queryGroupByHospName(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupByHospName",//医院上报
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var textList = [];
                var valList = [];
                rst.forEach((item)=>{
                  textList.push(item.hospName);
                  //valList.push(item.val);
                  let tmpObj = {};
                  tmpObj.value = item.val;
                  tmpObj.hospId = item.hospid;
                  tmpObj.groupType = 'groupByHospName';
                  // 将 params 的值推入 valList
                  for (let key in params) {
                    tmpObj[key] = params[key];
                  }
                  valList.push(tmpObj);
                });
                var eOpt = {
                  xAxis: {
                    data: textList,
                    axisLabel: {
                      interval: 0,    //强制文字产生间隔
                      // formatter: function(value) {
                      //   return value.split('').join('\n')
                      // }
                      rotate: 25,     //文字逆时针旋转25°
                      // textStyle: {    //文字样式
                      //     // color: "black",
                      //     // fontSize: 16,
                      // }
                    }
                  },
                  yAxis: {
                    type: 'value'
                  },
                  series: [{
                    data: valList,
                  }
                  ]
                };
                this.echart_groupByHospName.setOption(eOpt)
              }else{
                var eOpt = {
                  xAxis: {
                    data: [],
                  },
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_groupByHospName.setOption(eOpt);
              }
            },
          });
        },
        queryGroupProvReviewOver10Days(params){
          $ajax.post({
            url:"${base}/ui/amcs/countIndex/groupProvReviewOver10Days",//省区审核超10天分布图
            data:params,
            jsonData : false,//是否采用jsonData格式提交
            sync : false,//是否同步方式提交
            type : 'post',//采用post方式提交
            callback :(rst)=>{
              if(rst && rst.length>0){
                var textList = [];
                var valList = [];
                rst.forEach((item)=>{
                  textList.push(item.province);
                  //valList.push(item.val);
                  let tmpObj = {};
                  tmpObj.value = item.val;
                  tmpObj.strHospIds = item.strHospIds;
                  tmpObj.groupType = 'groupProvReviewOver10Days';
                  // 将 params 的值推入 valList
                  for (let key in params) {
                    tmpObj[key] = params[key];
                  }
                  valList.push(tmpObj);
                });
                var eOpt = {
                  xAxis: {
                    data: textList,
                    axisLabel: {
                      interval: 0,    //强制文字产生间隔
                      // formatter: function(value) {
                      //   return value.split('').join('\n')
                      // }
                      rotate: 25,     //文字逆时针旋转25°
                      // textStyle: {    //文字样式
                      //     // color: "black",
                      //     // fontSize: 16,
                      // }
                    }
                  },
                  yAxis: {
                    type: 'value'
                  },
                  series: [{
                    data: valList,
                  }
                  ]
                };
                this.echart_groupProvReviewOver10Days.setOption(eOpt)
              }else{
                var eOpt = {
                  xAxis: {
                    data: [],
                  },
                  series: [{
                    data: [],
                  }
                  ]
                };
                this.echart_groupProvReviewOver10Days.setOption(eOpt);
              }
            },
          });
        }
      }
    });

  });
</script>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>医院上报</title>
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
  </style>
</head>
<body>
<div id="hello">
  <div class="bg-white p-10 m-10">
    <form autocomplete="off">
      <div class="flex mar-15">
        <div class="flex">
          <button type="button" class="normal-btn" :class="{'active-btn':activeBtn==0}" @click="changeTime(0)">上报时间</button>
          <button type="button" class="normal-btn" :class="{'active-btn':activeBtn==1}" @click="changeTime(1)">发生时间</button>
        </div>
        <div class="mar-l15">
          <input id="demo" style="width: 180px;padding: 5px;height: 40px;" type="text" name="date" placeholder="    开始时间   至   结束时间"/>
        </div>
        <button class="mar-l15 normal-btn" type="button" @click="query">查询</button>
      </div>
    </form>
    <div class="flex" style="border-top: 1px solid #cccccc;">
      <div id="left-com" class="px-30 flex flex-col justify-center my-10" style="border-right: 2px solid #d6d6d6;">
        <div class="flex text-white items-center bg-blue border-0 py-10 px-30 btn-upload" style="border-radius: 5px;cursor: pointer;" @click="goUpload">
          <img style="width: 30px;" src="https://avis-bigfile-1257101260.cos.ap-guangzhou.myqcloud.com/avis/images/icon-tjrs.png" />
          <div type="button" class="mar-l10" >我要上报</div>
        </div>
        <div class="mar-t10">
          <span class="text-black" style="font-size: 20px;">总数：</span>
          <span class="text-red" style="font-size: 30px;">{{allCount}}</span>
        </div>
      </div>
      <div class="right-com flex flex-wrap flex-1 pad-l15 pad-t10">
        <template v-for="(item,cindex) in list">
          <incident-info :data="item" :active-btn="activeBtn" :start-time="startTime" :end-time="endTime"></incident-info>
        </template>
      </div>
    </div>
  </div>

  <div class="p-10 m-10 bg-white">
    <div style="color: #262626;font-size: 14px;border-bottom: 1px solid #ccc;font-weight: bold;" class="pad-l15 pad-b10">不良事件类型分布图</div>
    <div id="echart-hospReportByGradeOne" style="height: 300px;width: 100%;"></div>
  </div>

</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    var eOpt = {
      	tooltip:{
            trigger: 'axis',
              axisPointer: {
              type: 'shadow'
            },
        },
        grid:{
            // top:"3%",
            left:"3%",
            right:"3%",
            // bottom:"3%"
        },
        xAxis: {
            type: 'category',
            data: []//x轴的文本
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: [],//y轴的值
                barWidth:30,
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
        {img:"icon-shenpi-2",label:'待医院审批',count:'',color:'#3dcea7'},//
        {img:"icon-bianjiwenjian",label:'待省区审批',count:'',color:'#e98f36'},//
        {img:"icon-zhanghutuikuan",label:'被回退',count:'',color:'#7cba59',url:{title:"退回事件",uri:"${base}/ui/service/biz/amcs/adverse/common/listReturnPage"}},//
        {img:"icon-biaodanwancheng2",label:'归档/完成',count:'',color:'#17abe3'},//
        {img:"icon-weishangbao",label:'未完结',count:'',color:'#db639b',url:{title:"未完结列表",uri:"${base}/ui/amcs/countIndex/unfinishedHospReport"}},//
        {img:"icon-shenhetongguo",label:'未完结(超过90天)',count:'',color:'#ed7d31',url:{title:"未完结(90天)列表",uri:"${base}/ui/amcs/countIndex/unfinished90HospReport"}},//
        {img:"icon-genzong",label:'省区审核通过',count:'',color:'#7cba59'},//
        {img:"icon-fengxiandengji-",label:'高风险',count:'',color:'#db639b'},//
        {img:"icon-zelvxingzhengjiufen",label:'纠纷',count:'',color:'#17abe3',url:{title:"纠纷列表",uri:"${base}/ui/amcs/countIndex/disputeHospReport"}},//
        {img:"icon-dingdan",label:'完结',count:'',color:'#e98f36'},//
    // {img:"icon-shenhebutongguo",label:'未点评',count:'',color:'#7cba59'},
        {img:"icon-bg-money",label:'赔偿',count:'',color:'#17abe3',url:{title:"赔偿列表",uri:"${base}/ui/amcs/countIndex/compensationHospReport"}},//
        {img:"icon-shenpi",label:'超时上报',count:'',color:'#db639b'},//
      ];

    requirejs(["echarts",'vue', 'pub'], function (echarts,Vue) {
        Vue.component("incident-info",{
            props:['data','activeBtn','startTime','endTime'],
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
                    	if(this.$parent.activeBtn == 0){
	                        params.reportDateBegin = this.$parent.startTime;
	                        params.reportDateEnd = this.$parent.endTime;
	                    }else if(this.$parent.activeBtn == 1){
	                        params.eventDateBegin = this.$parent.startTime;
	                        params.eventDateEnd = this.$parent.endTime;
	                    }
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
              <div class="flex py-15 justify-center items-center w-200" :class="{'cursor-pointer':canclick,'gotab-hover':canclick}" @click="goTab">
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
                    startTime:'',
                    endTime:'',
                    list:[]
                }
            },
            mounted() {
                let $this = this;
                $form.rangeDate("#demo", {
                    maxDate: new Date(),
                    maxSpan:{days: 366},
                    auto:false,
                    val:'',
                    callback: function (start, end, label) {
                        $this.startTime = start.format('YYYY-MM-DD');
                        $this.endTime = end.format('YYYY-MM-DD');
                    }
                });
                $("#demo").on('cancel.daterangepicker', function(ev, picker) {
                    $this.startTime = "";
                    $this.endTime = "";
                });
                this.queryTopIncident();

                this.initHospReportByGradeOne();
                this.queryHospReportByGradeOne();

                this.initResize();
            },
            methods:{
                initResize(){
                    var $this = this;
                    window.onresize = function(){
                        $this.echart_hospReportByGradeOne && $this.echart_hospReportByGradeOne.resize();
                    }
                },
                initHospReportByGradeOne(){
                    var dom_hospReportByGradeOne = document.getElementById("echart-hospReportByGradeOne");//不良事件类型分布图 
                    var echart_hospReportByGradeOne = echarts.init(dom_hospReportByGradeOne);
                    echart_hospReportByGradeOne.setOption(eOpt);
                    this.echart_hospReportByGradeOne = echart_hospReportByGradeOne;
                },
                changeTime(type){
                    this.activeBtn = type;
                },
                query(){
                    var params = {};
                    if(this.activeBtn == 0){
                        params.reportDateBegin = this.startTime;
                        params.reportDateEnd = this.endTime;
                    }else if(this.activeBtn == 1){
                        params.eventDateBegin = this.startTime;
                        params.eventDateEnd = this.endTime;
                    }
                    this.queryTopIncident(params);
                    this.queryHospReportByGradeOne(params);
                },
                goUpload(){
                    $pop.newTabWindow("首次上报","${base}/ui/amcs/adverse/eventManage/eventChoose");
                },
                queryTopIncident(params){
                    $ajax.post({
                        url:"${base}/ui/amcs/countIndex/hospReport",//医院上报
                        data:params,
                        jsonData : false,//是否采用jsonData格式提交
                        type : 'post',//采用post方式提交
                        callback :(rst)=>{
                            if(rst){
                                var keys = Object.keys(rst);
                                var infoObj = [];
                                keys.forEach((item)=>{
                                    if(item == '总数'){
                                        this.allCount = rst[item];
                                    }
                                    var tmpO = label_info.find((c)=>{
                                        return c.label == item;
                                    });
                                    if(tmpO){
                                        tmpO.count = rst[item];
                                        tmpO && infoObj.push(tmpO);
                                    }
                                });
                                this.list = infoObj;
                            }
                        },
                    });
                },
                queryHospReportByGradeOne(params){
                    $ajax.post({
                        url:"${base}/ui/amcs/countIndex/hospReportByGradeOne",//医院上报
                        data:params,
                        jsonData : false,//是否采用jsonData格式提交
                        type : 'post',//采用post方式提交
                        callback :(rst)=>{
                            if(rst && rst.length>0){
                                var textList = [];
                                var valList = [];
                                rst.forEach((item)=>{
                                    textList.push(item.text);
                                    valList.push(item.val);
                                });

                                var eOpt = {
                                    xAxis: {
                                        data: textList,
                                        axisLabel: {
                                            interval: 0,    //强制文字产生间隔
                                            rotate: 25,     //文字逆时针旋转45°
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
                                this.echart_hospReportByGradeOne.setOption(eOpt);
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
                                this.echart_hospReportByGradeOne.setOption(eOpt);
                            }
                        },
                    });
                },
            }
        });

    });
</script>
</html>

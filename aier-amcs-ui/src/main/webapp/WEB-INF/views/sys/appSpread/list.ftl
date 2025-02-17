<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>爱尔医疗云平台应用推广一览表 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
code, pre{font-family:Helvetica Neue,Helvetica,Tahoma,Verdana,Arial,"Microsoft YaHei","SimSun";font-size: 14px;}

.h1-title{font-size: 24px;line-height:30px;text-align: center;padding:25px 0 20px;color:#000;}
.likeTabs{width:auto;}
.likeTabs .tab-last{border-right:0;}
.tabs li a.tabs-inner{padding:0 30px;}


.tabCont{position: absolute;left:0;right:0;top:157px;bottom:5px;}
.tabContHide{visibility: hidden;}
.cont-grid{z-index:2;position:absolute;top:134px;left:0;right:0;bottom:52px;overflow: hidden;zoom: 1;border-top:1px solid #e3e3e3;}
.cont-grid-ex{bottom:0;}
.mainfooter{position:absolute;left:0;right:0;bottom:0;height:50px;}

.h2-title-a{float:left;width:240px;margin:12px 0 5px;}
.absInfo{float:left;padding:20px 10px 10px;overflow: hidden;zoom: 1;}
.p-info{float: left;margin-right:8px;font-size:14px;}
.p-info .s-t{font-weight: bold;}
.p-info .strong-info{font-weight: bold;}
.p-info .em-info{font-weight: bold;text-decoration: underline;padding:0 2px 0 0;cursor:hand;cursor:pointer;display: -moz-inline-stack;display:inline-block;*display:inline;*zoom:1;}
.p-info .strong-info .em-info{font-weight: bold;color:#00A0E9;}
.p-info .strong-info .info-now,
.p-info .info-now{color:#c00;}
.s-one em{font-weight:bold;}
.btn-saveChange,.btn-saveChange:focus{position:absolute;top:50px;right:5px;}

.s-sel-d-box{position: absolute;right: 15px;top: 96px; display:none;}
</style>
</head>

<body>

[#--<h1 class="h1-title">爱尔医疗云平台应用推广一览表</h1>--]
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <input class="txt hide txt-type" id="sysType" type="text" name="sysType" value="ahis" />
        <input class="txt hide txt-type" id="hospType" type="text" name="hospType" value="0" />
        <input class="txt hide txt-type" id="state" type="text" name="state" value="0" />
        <span class="fl">
            <label class="lab-inline">省区：</label>
            <input type="text" class="txt inline w-170" name="likeProvince">
        </span>
        <span class="fl">
            <label class="lab-inline">医院：</label>
            <input type="text" class="txt inline w-170" name="likeHospName">
        </span>
        <span class="fl">
            <label class="lab-inline">上线时间：</label>
            <input class="txt txt-validate txt-rangeDate w-185" type="text" name="onlineDate" value="" />
        </span>
        <span class="fl mar-l5">
            <button type="button" class="btn btn-small btn-primary btn-search">查 询</button>
        </span>
    </form>
</div>
<ul class="tabs likeTabs">
    <li class="tabs-first tabs-selected" rel="ahis"><span class="tabs-inner"><span class="tabs-title">AHIS</span></span></li>
    <li rel="avis"><span class="tabs-inner"><span class="tabs-title">AVIS</span></span></li>
    <li id="li-aemrType" rel="aemr"><span class="tabs-inner"><span class="tabs-title">AEMR</span></span></li>
    <li rel="alis"><span class="tabs-inner"><span class="tabs-title">ALIS</span></span></li>
    <li rel="apacs"><span class="tabs-inner"><span class="tabs-title">APACS</span></span></li>
    <li rel="amrs"><span class="tabs-inner"><span class="tabs-title">AMRS</span></span></li>
</ul>
<button type="button" class="btn btn-small btn-warning btn-saveChange">保存修改</button>
<h2 class="h2-title-a"><span class="s-title">医疗机构清单 <em class="red">(当前数据截止到昨日)</em></span></h2>

<div class="absInfo">
    <p class="p-info"><span class="s-t">注册医疗机构：</span><strong class="strong-info"><em class="em-info info-11 txt-alltype" rel="0,0">0</em> 家</strong> （医院 <em class="em-info info-12 txt-allHos" rel="0,11">0</em>家、门诊 <em class="em-info info-13 txt-allOutp" rel="0,10">0</em>家） </p>
    <p class="p-info"><span class="s-t">上线医疗机构：</span><strong class="strong-info"><em class="em-info info-21 txt-allOnline" rel="1,0">0</em> 家</strong> （医院 <em class="em-info info-22 txt-onlineHosp" rel="1,11">0</em>家、门诊 <em class="em-info info-23 txt-onlineOutp" rel="1,10">0</em>家） </p>
    <p class="p-info"><span class="s-t">待上线医疗机构：</span><strong class="strong-info"><em class="em-info info-31 txt-allWaiting" rel="2,0">20</em> 家</strong> （医院 <em class="em-info info-32 txt-waitingHosp" rel="2,11">0</em>家、门诊 <em class="em-info info-33 txt-waitingOutp" rel="2,10">0</em>家） </p>
</div>

<span class="item-group s-sel-d-box"><button type="button" class="btn btn-small btn-primary active btn-sel-d btn-d-1" rel="aemr">住院</button><button type="button" class="btn btn-small btn-sel-d btn-d-2" rel="aemro">门诊</button></span>

<div class="cont-grid">
[#--    <div id="gridBox"></div>--]
</div>

<div class="mainfooter bot-line-sub clearfix">
    <div class="oneinfo pad-10 center">
        <span class="s-one">
        <b class="blue">营业收入：</b>
        <em>累计</em><em class="one-w txt-totalrevenue"></em>元
        <b class="blue mar-l50">门诊量：</b>
        <em>累计</em><em class="one-w txt-totaloutp"></em>人次,
        <em>日均</em><em class="one-w txt-dailyaverageoutp"></em>人次
       <!-- <em>当日</em><em class="one-w txt-todayoutp">${todayoutp}</em>人次-->
        <b class="blue mar-l50">出院量：</b>
        <em>累计</em><em class="one-w txt-dischargeAmount"></em>人次,
        <em>日均</em><em class="one-w txt-dailydischargeAmount"></em>人次</span>
    </div>
</div>
</body>



[#include "/WEB-INF/views/common/include_js.ftl"]


<script id="apacsFormTem" type="text/html">
    <form id="infoForm" class="soform form-validate pad-t25 pad-l10 pad-r10 solab-s form-enter">
        <input class="hide" type="text" name="id" value="{{id}}">
        <input id="txt-hospType" class="hide" type="text" name="hospType" value="{{hospType}}">
        <input id="txt-hospId" class="hide" type="text" name="hospId" value="{{hospId}}">
        <div class="row">
            <div class="p12">
                <div class="item-one">
                    <label class="lab-item">医疗机构：</label>
                    {{if id}}
                    <input class="txt unedit" type="text" name="hospName" value="{{hospName}}" readonly="readonly" />
                    {{else}}
                    <input class="txt easyui-combobox required" type="text" name="hospName" style="width:100%;" data-options="
                    filter:function(q, row){
                        return row['name'].toLowerCase().indexOf(q.toLowerCase()) >-1 || row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
                    },
                    onSelect : function(record){
                        $('#txt-province').val(record.provinceName);
                        $('#txt-hospType').val(record.hospType);
                        $('#txt-hospId').val(record.hospId);
                    },
                    valueField:'name',textField:'name',required:true,
                url:'${base}/ui/sys/inst/getHospByPlat'"/>
                    {{/if}}
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">省区：</label>
                    <input id="txt-province" class="txt unedit" type="text" name="province" value="{{province}}" readonly="readonly" />
                </div>
            </div>

            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">当前状态：</label>
                    <select class="drop easyui-combobox txt-validate required" style="width:100%;" name="apacsState" data-options="value:'{{apacsState}}',required:true">
                        <option value="1" selected="selected">上线</option>
                        <option value="2">待上线</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">上线时间：</label>
                    <input class="txt txt-validate so-date" type="text" name="apacsOnlineDate" noNull="必填" value="{{apacsOnlineDate}}" />
                </div>
            </div>

            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">实施人：</label>
                    <input class="txt easyui-combogrid" type="text" name="apacsImplementer" style="width:100%;" value="{{apacsImplementer}}" data-options="panelWidth:370,
                panelHeight:'auto',
                panelMaxHeight:150,
                mode : 'remote',
                idField:'NAME',
                textField:'NAME',
                clearIcon:true,
                minRemoteKeyLen :2,
                url:'${base}/ui/sys/inst/hospital/getAllStaffByCondition',
                queryParams: {
                  rows:20
                },
                columns:[[
			      {title:'工号',field:'CODE',width:90},
			      {title:'姓名',field:'NAME',width:90},
			      {title:'医院',field:'INSTNAME',width:180}
                ]]">
                </div>
            </div>
        </div>
        <p class="row-btn center">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" value="确定" />
            <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
        </p>
    </form>
</script>

<script type="text/javascript">
requirejs(['easygridEdit','pub'],function ($e) {
// var sysT = ['ahis','avis','aemr','alis','apacs','amrs'];
// 系统标识  sysType  ahis,avis,aemr,alis,apacs,amrs
// 状态  state  0 所有 1 上线 2待上线
// 医院类型： hospType 0 所有11 医院 10门诊
    var sysType = 'ahis';
    var $sysT = $('#sysType'), $hosT = $('#hospType'), $state = $('#state');
    var tabChange= false;
    var sortType = null;
    var sysSort = {};

    var spreadFn = {
        init : function () {

            this.someInit();
            // this.renderGrid();
        },
        renderGrid : function (){
            var me = this;
            $('.cont-grid').html('<div id="gridBox"></div>');//清空 grid
            $('.cont-grid')[sysType === 'ahis'?'removeClass':'addClass']('cont-grid-ex');//非 ahis底边不显示

            var gridOpt = me.getGridOpts();
            gridOpt.queryParams = $('#sbox').sovals();
            $grid.newGrid("#gridBox",gridOpt);
        },
        someInit : function () {
          var me = this;
          $form.rangeDate('.txt-rangeDate',{
            val:'',
            opens:'center',
            auto : false,
            init : function (s,e,label) {
              me.renderGrid();
            }
          });

          var $tabLi = $('.likeTabs li');//tab切换事件
          $tabLi.click(function () {//切换tab，切换系统搜索条件
            var ix = $tabLi.index(this);
            $tabLi.removeClass('tabs-selected');
            $(this).addClass('tabs-selected');

            sysType = $(this).attr('rel');
            $sysT.val(sysType);//系统类型
            $hosT.val(0);//重置医院类型
            $state.val(0);//重置状态类型
            $('.em-info').removeClass('info-now');
            tabChange = true;
            $('.s-sel-d-box')[sysType.indexOf('aemr')>-1?'show':'hide']();
            // $('.baseToobar')[sysType == 'apacs'?'addClass':'removeClass']('show');

            me.renderGrid();
            return false;
          });


          $('.btn-sel-d').click(function () {//aemr 切换门诊 、 住院
            $('.btn-sel-d').removeClass('btn-primary').removeClass('active');
            $(this).addClass('btn-primary active');
            var t = $(this).attr('rel');
            $('#li-aemrType').attr('rel',t).click();
          });

          $('.btn-search').click(function (){//搜索按钮
            $hosT.val(0);//重置医院类型
            $state.val(0);//重置状态类型
            $('.em-info').removeClass('info-now');
            me.renderGrid();
          });

          $('.btn-saveChange').click(function () {//保存修改
            if($e.endEditing()){
              var  dataRows = $('#gridBox').datagrid('getChanges','updated');
              if(dataRows.length){
                //console.log(sysType);
                var url = '${base}/ui/sys/appSpread/update';
                if(sysType=='apacs'){
                  url = '${base}/ui/sys/apacsSpread/update';
                }
                $.each(dataRows,function (i,v) {
                  v[sysType+'Implementer'] = v.updater;
                  v[sysType+'ImplementationDate'] = v.updateDate;
                });

                $ajax.post({//提交保存数据
                  url : url,
                  data : dataRows,
                  tip: true,
                  jsonData : true,
                  success : function (rst) {

                  }
                });
              }else{
                $pop.msg('没有修改数据，不需要提交！');
              }
            }
          });

          $('.em-info').click(function (){//点击数字搜索
            $('.em-info').removeClass('info-now');
            $(this).addClass('info-now');
            var type = $(this).attr('rel').split(','); //12 [1,2] [上线类型,医院类型]
            $state.val(type[0]);//状态类型
            $hosT.val(type[1]);//医院类型
            me.renderGrid();
          });

        },
        toThousands : function (v) {
          if(v!==undefined) {
            var num = Number(v).toFixed(2);
            console.log(num,num.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,'));
            return num.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
          }
        },
        fmoney : function (s, n) {
            n = n >= 0 && n <= 20 ? n : 2;
            s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
            var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
            t = "";
            for (i = 0; i < l.length; i++) {
              t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
            }
            return t.split("").reverse().join("") + (r?"." +r:'');
        },
        rtVal : function (v,callback){
            if(v!==undefined && v!== '') {
              return typeof(callback)=='function'?callback(v):v;
            }else{
              return '-';
            }
        },
        rtDate : function (v){
            return spreadFn.rtVal(v,function(v){
              return $.fmtDate('yyyy-MM-dd',v);
            });
        },
        sortNum : function (a,b){//排序
            //console.log(a,b);
            if(sortType == 'asc'){
              if(a==undefined)a= 10000000000000;
              if(b==undefined )b= 10000000000000;
              if(a==='') a='2050 01-01-01 00:00:00';
              if(b==='') b='2050 01-01-01 00:00:00';
            }else{
              if(a==undefined)a= -1;
              if(b==undefined)b=-1;
              if(a==='') a='1900 01-01-01 00:00:00';
              if(b==='') b='1900 01-01-01 00:00:00';

            }
            return a>b?1:-1;
        },
        ahisSelfCols : function () {//ahis col 列
            var me = this;
            return [
              {title:'累计营收(元)',field:'totalrevenue',width:95,sortable:true,align:'right',order:'desc',sorter:me.sortNum,formatter:function(v){
                  return me.rtVal(v,function(v){
                    return me.fmoney(v,2);
                  });
                }},
              {title:'日均营收(元)',field:'dailyaveragerevenue',width:110,sortable:true,align:'right',order:'desc',sorter:me.sortNum,formatter:function(v){
                  return me.rtVal(v,function(v){
                    return me.fmoney(v,2);
                  });
                }},
              {title:'累计门诊量(人次)',field:'totaloutp',sortable:true,width:120,order:'desc',sorter:me.sortNum,formatter:me.rtVal},
              {title:'日均门诊量(人次)',field:'dailyaverageoutp',sortable:true,width:120,order:'desc',sorter:me.sortNum,formatter:function(v){
                  return me.rtVal(v,function(v){
                    return Number(v).toFixed(2);
                  });
                }},
              {title:'累计出院量(人次)',field:'dischargeamount',sortable:true,width:120,order:'desc',sorter:me.sortNum,formatter:me.rtVal},
              {title:'日均出院量(人次)',field:'dailydischargeamount',sortable:true,width:120,order:'desc',sorter:me.sortNum,formatter:function(v){
                  return me.rtVal(v,function(v){
                    return Number(v).toFixed(2);
                  });
                }}
            ];
        },
        getCols : function () {//每次初始化，重新获取 grid col 列
            var me = this;
            var cols = [
              {title:'id',field:'id',width:80,hidden:true},
              {title:'医院编码',field:'hospId',width:80},
              {title:'省区',field:'province',width:100,sortable:true},
              {title:'医疗机构名称',field:'hospName',width:270,align:'left'},
			  {title:'投资性质',field:'investNatureTxt',width:80,sortable:true},
            ];

            cols.push({title:'当前状态',field:sysType+'StateTxt',width:80,sortable:true});
            cols.push({title:'上线时间',field:sysType+'OnlineDate',width:100,formatter:me.rtDate,sorter:me.sortNum,sortable:true,order:'desc'});

            if(sysType == 'ahis'){
              cols = cols.concat(me.ahisSelfCols());
            }
            cols.push({title:'实施人',field:'updater',width:110,align:'left',editor:{
                type:'combogrid',
                options:{
                  panelWidth:440,
                  panelHeight:'auto',
                  panelMaxHeight:182,
                  striped:true,
                  mode : 'remote',
                  idField:'NAME',
                  textField:'NAME',
                  clearIcon:true,
                  minRemoteKeyLen :2,
                  url:'${base}/ui/sys/inst/hospital/getAllStaffByCondition',
                  queryParams: {
                    rows:20
                  },
                  columns:[[
                    {title:'工号',field:'CODE',width:90},
                    {title:'姓名',field:'NAME',width:160},
                    {title:'医院',field:'INSTNAME',width:180}
                  ]]
                  // required:true
                }
              }
            });
            return cols;
        },
        getTools : function(){//每次初始化，重新获取 tools，暂时只有apacs有工具栏
          return sysType == 'apacs'?[{iconCls:'plus_sign',text:'新增',click: function () {
              var popForm = $pop.popTemForm({
                title : '新增',
                temId : 'apacsFormTem',
                area : ['560px','250px'],
                beforeSubmit : function(opt,$form,formData){
                  $ajax.post({
                    url : '${base}/ui/sys/apacsSpread/insert',
                    data : formData,
                    // jsonData : true,
                    success : function (rst) {
                      $pop.close(popForm);
                      // $pop.msg.success('添加提交成功！');
                      $('#gridBox').datagrid('reload');
                    }
                  })
                }
              });
            }},
            {iconCls:'pencil',text:'修改',onlyOne:true,notNull:'请选择你要修改的记录!',click: function () {
                var row = $('#gridBox').datagrid('getSelected');
                row.apacsOnlineDate = row.apacsOnlineDate.split(' ')[0];
                var popForm = $pop.popTemForm({
                  title : '修改',
                  temId : 'apacsFormTem',
                  temData : row,
                  area : ['560px','250px'],
                  beforeSubmit : function(opt,$form,formData){
                    delete formData.province;
                    delete formData.hospName;
                    delete formData.hospType;
                    delete formData.hospId;
                    $ajax.post({
                      url : '${base}/ui/sys/apacsSpread/updateById',
                      data : formData,
                      // jsonData : true,
                      success : function (rst) {
                        $pop.close(popForm);
                        // $pop.msg.success('修改成功！');
                        $('#gridBox').datagrid('reload');
                      }
                    })
                  }
                });
              }},
            {iconCls:'trash',text:'删除',onlyOne:true,url:'${base}/ui/sys/apacsSpread/deleteById?id={id}',notNull:'请 <strong class="red">选择</strong> 需要删除的项！', ajax:true}
          ]:null;
        },
        getGridOpts : function () {//每次渲染重新获取 生成 grid 渲染参数
            var me = this;
            var cols = me.getCols();
            var tools = me.getTools();
            var fitColumns = sysType == 'ahis'?true:false;//ahis自适应列宽

            return {
              tools:tools,
              fitParent : true,
              remoteSort: false,
              checkOnSelect : false,
              //pageSize: 1,
              //pageList: [1,10, 20, 50, 100, 200],
              selectOnCheck : false,
              singleSelect : false,
              ctrlSelect : true,
              pagination : false,
              fitColumns : fitColumns,
              columns:[cols],
              loader : function(param,success,err){
                if(param.sort){//删除排序字段，不传排序到后端
                  delete param.sort;
                  delete param.order;
                }
                $ajax.post({
                  url : '${base}/ui/sys/appSpread/grid',
                  data : param,
                  loadingMask : false,
                  callback : function (rst) {
                    success(rst);
                  }
                })
              },
              loadFilter : function(data){
                $.each(data.rows,function (i,v) {
                  v.updater = v[sysType+'Implementer'];
                  v.updateDate = v[sysType+'ImplementationDate'];
                });
                return data;
              },
              onBeforeSortColumn : function(sort, order){
                sysSort[sysType] = sysSort[sysType] || {};
                sysSort[sysType].sortKey = sort;
                sysSort[sysType].sortType = order;
                sortType = order;
              },
              onClickCell: function (index, field, value) {
                $e.editRow({
                  grid: '#gridBox',
                  index: index,
                  cellField: field,
                  focusField: 'updater',
                  canAdd: false
                });
              },
              onBeforeLoad : function (opt){
                if(!opt.sysType){
                  return false;
                }
              },
              onLoadSuccess : function (data) {
                //点击tab更新数据后，保留 当前状态 或 上线时间 的排序能力
                // console.log(sortType);

                var $grid = $('#gridBox');
                if (tabChange) {//如果点击tab切换
                  tabChange = false;
                  var s = sysSort[sysType];
                  if (!s) {
                    $grid.datagrid('sort', {
                      sortName: sysType + 'OnlineDate',
                      sortOrder: 'desc'
                    });
                  } else {
                    $grid.datagrid('sort', {
                      sortName: s.sortKey,
                      sortOrder: s.sortType
                    });
                  }
                }

                // window.console && console.log(sysType,data);
                $.each(data, function (k, v) {
                  if ($('.txt-' + k).length) {
                    $('.txt-' + k).html(v);
                  }
                });

                $('.txt-alltype').html(data.onlineHosp + data.waitingHosp + data.onlineOutp + data.waitingOutp);//全部
                $('.txt-allHos').html(data.onlineHosp + data.waitingHosp); //全部hos
                $('.txt-allOutp').html(data.onlineOutp + data.waitingOutp); //全部outp
                $('.txt-allOnline').html(data.onlineHosp + data.onlineOutp); //全部上线
                $('.txt-allWaiting').html(data.waitingHosp + data.waitingOutp); //全部待上线

                if (sysType === 'ahis') {//ahis 时，计算显示底部数据

                  var dailV = 0, dailD = 0, totalR = 0, totalP = 0, dischA = 0;
                  $.each(data.rows, function (k, v) {//计算日均门诊量
                    if (v.totaloutp) {
                      totalP += v.totaloutp * 10000
                    }
                    if (v.totalrevenue) {
                      totalR += v.totalrevenue * 10000
                    }
                    if (v.dailyaverageoutp) {
                      dailV += v.dailyaverageoutp * 10000;
                    }
                    if (v.dischargeamount) {
                      dischA += v.dischargeamount * 10000;
                    }
                    if (v.dailydischargeamount) {
                      dailD += v.dailydischargeamount * 10000;
                    }
                  });

                  $('.txt-totalrevenue').html(me.fmoney(Number(totalR / 10000).toFixed(2)));//总营收
                  $('.txt-totaloutp').html(me.fmoney(Number(totalP / 10000), 0));//总门诊量
                  $('.txt-dailyaverageoutp').html(me.fmoney(Number(dailV / 10000).toFixed(2)));//累计日均总计
                  $('.txt-dischargeAmount').html(me.fmoney(Number(dischA / 10000), 0));//累计出院量
                  $('.txt-dailydischargeAmount').html(me.fmoney(Number(dailD / 10000).toFixed(2)));//出院日均总计

                }
              },
              offset :0
            };
        }
    };

    $(function () {
      spreadFn.init();//初始化执行
    });


});

</script>
</body>

</html>
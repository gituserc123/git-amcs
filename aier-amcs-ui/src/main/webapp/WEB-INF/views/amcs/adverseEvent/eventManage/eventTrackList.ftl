<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>集团不良事件跟踪 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
  <style type="text/css">
      .likeTabs {
          width: auto;
      }
  </style>
</head>
<body>
<ul class="tabs likeTabs">
  <li class="tabs-first tabs-selected" rel="0">
    <a href="#" class="tabs-inner"><span class="tabs-title">回退事件跟踪</span></a>
  </li>
  <li rel="1">
    <a href="#" class="tabs-inner"><span class="tabs-title">未完结事件跟踪</span></a>
  </li>
</ul>
<div class="tabCont tabCont-0">
  <div class="searchHead">
    <form id="sbox0" class="soform form-enter">
      <input class="txt" name="pageType" value="${pageType}" type="hidden"/>
      <label class="lab-inline">退回状态：</label>
      <select class="drop easyui-combobox w-100" name="rebackStatus" data-options="panelHeight:'auto'">
        <option value=""></option>
        <option value="1" selected="selected">当前被退回</option>
        <option value="0">历史被退回</option>
      </select>
        [#if empType==1]
          <label class="lab-inline">省区：</label>
          <select class="drop drop-sl-v easyui-combobox w-80 province" name="province"
                  data-options="valueField:'id',textField:'name',clearIcon:true"></select>
        [/#if]
        [#if empType==1 || empType==2]
          <label class="lab-inline">医院：</label>
          <select class="drop drop-sl-v easyui-combobox  w-150 hosp tab0-hosp" name="hospId"
                  data-options="valueField:'id',textField:'name',clearIcon:true">
          </select>
        [/#if]

      <label class="lab-inline">事件类型：</label>
      <select class="drop drop-sl-v easyui-combobox  w-150 eventType" name="eventCode"
              data-options="valueField:'eventCode',textField:'eventName',clearIcon:true">
      </select>
      <label class="lab-inline">上报日期：</label>
      <input type="text" class="txt so-rangeDate txt-validate" name="reportDate" autocomplete="off" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>

      <label class="lab-inline">发生日期：</label>
      <input type="text" class="txt so-rangeDate txt-validate" name="eventDate" autocomplete="off" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>

      <label class="lab-inline">退回节点：</label>
      <select class="drop easyui-combobox w-80" id="rebackNode" name="rebackNode" data-options="multiple:false, clearIcon:true" >
        <option value=""></option>
        <option value="2">医院退回</option>
        <option value="3">省区退回</option>
        <option value="4">集团退回</option>
      </select>
      <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox0', scope:'#sbox0'}">查
        询
      </button>
    </form>
  </div>
  <div class="cont-grid">
    <div id="gridBox0"></div>
  </div>
</div>
<div class="tabCont tabCont-1 tabContHide">
  <div class="searchHead">
    <form id="sbox1" class="soform form-enter">
      <input class="txt" name="pageType" value="${pageType}" type="hidden"/>
      <input class="txt" name="reportTimes" value="1" type="hidden"/>
      <input class="txt" name="isPrimary" value="1" type="hidden"/>
      <label class="lab-inline">是否完结：</label>
      <select class="drop easyui-combobox w-80" name="finishSign" data-options="panelHeight:'auto'">
        <option value=""></option>
        <option value="1">是</option>
        <option value="0" selected="selected">否</option>
      </select>
        [#if empType==1]
          <label class="lab-inline">省区：</label>
          <select class="drop drop-sl-v easyui-combobox  w-150 province" name="province"
                  data-options="valueField:'id',textField:'name',clearIcon:true"></select>
        [/#if]
        [#if empType==1 || empType==2]
          <label class="lab-inline">医院：</label>
          <select class="drop drop-sl-v easyui-combobox  w-150 hosp tab1-hosp" name="hospId"
                  data-options="valueField:'id',textField:'name',clearIcon:true">
          </select>
        [/#if]
      <label class="lab-inline">事件类型：</label>
      <select class="drop drop-sl-v easyui-combobox  w-150 eventType" name="eventType"
              data-options="valueField:'eventCode',textField:'eventName',clearIcon:true">
      </select>
      <label class="lab-inline">上报日期：</label>
      <input type="text" class="txt so-rangeDate txt-validate" name="reportDate" autocomplete="off" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>

      <label class="lab-inline">发生日期：</label>
      <input type="text" class="txt so-rangeDate txt-validate" name="eventDate" autocomplete="off" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>

      <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox1', scope:'#sbox1'}">查
        询
      </button>
    </form>
  </div>
  <div class="cont-grid">
    <div id="gridBox1"></div>
  </div>
</div>

</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", "moment", 'plus', 'pub'], function (_, $e, moment, _p) {
        var tabIndex = 0;
        let parentParam = $store.get('parentDataCountIndex');
        initTab();
        reloadEventType();
        if('${isIndex}' == '1') {
          $('#rebackNode').combobox('setValue', ${pageType});
          //_p.setRangeDate('.reportDate', parentParam.reportDateBegin, parentParam.reportDateEnd);
          parentParam.rebackNode = ${pageType};
        }

        function initTab() {
            var tabInit = [true, ''];
            window.$tabLi = $('.likeTabs li');
            $tabLi.click(function () {
                var ix = $tabLi.index(this);

                if (!$(this).hasClass('tabs-selected')) {
                    tabIndex = $(this).attr('rel');
                    $tabLi.removeClass('tabs-selected');
                    $(this).addClass('tabs-selected');
                    $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');

                    if(ix ===1 && !tabInit[ix]){
                        tabInit[ix] = true;
                        initTab2();
                    }
                }

            });
        };

        function reloadEventType() {
            [#if empType==1]
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince', null, false, false).done(function (rst) {
                $('.province').combobox('loadData', rst);
            });
            $('.province').combobox({
                onChange: function (v) {
                    let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                    if (tabIndex == 0) {
                        $('.tab0-hosp').combobox('reload', url);
                    } else {
                        $('.tab1-hosp').combobox('reload', url);
                    }

                }
            });
            [#elseif empType==2]
            var paramInsi = {insiId: '${insiId}'};
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp', paramInsi, false, false).done(function (rst) {
                $('.hosp').combobox('loadData', rst);
            });
            [/#if]
            $ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll', null, false, false).done(function (rst) {
                $('.eventType').combobox('loadData', rst.rows);
            });

        };


        window.popMsg = function (msg, tmpid) {
            $pop.tips(msg, '#' + tmpid, {time: 0, closeBtn: true, maxWidth: 600});
        }
        

        if($.isEmptyObject(parentParam) || '${isIndex}' != 1){
            parentParam = {'pageType': '${pageType}', 'rebackStatus': 1}
	    }else{
	     	parentParam.pageType = '${pageType}';
	     	parentParam.rebackStatus = 1;
            parentParam.rebackNode = 4;
            var nodeCombobox = $('#rebackNode');
            nodeCombobox.combobox('setValue', '4');
	    }

        $grid.newGrid("#gridBox0", {
        	pagination: true,
            columns: [[
                {
                    title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
                        return '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
                    }
                },
                {title: 'id', field: 'id', width: 80, hidden: true},
                {title: 'eventCode', field: 'eventCode', width: 80, hidden: true},
                {title: 'eventId', field: 'eventId', width: 80, hidden: true},
                {title: 'node', field: 'node', width: 80, hidden: true},
                {title: '页面地址', field: 'eventUrl', width: 120, hidden: true},
                {title: '意见', field: 'opinion', width: 80, hidden: true},
                {title: '事件名称', field: 'eventName', width: 200},
                {title: '退回状态', field: 'rebackName', width: 200},
                {title:'省区', field:'hospParent', hidden: false, width:80},
                {title: '医院名称', field: 'hospName', width: 200},
                {title:'患者姓名', field:'patientName', hidden: false, width:80},
                {title: '事件类型', field: 'eventTypeName', width: 120},
                {title: '发生时间', field: 'eventDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '上报人', field: 'creatorName', width: 80},
                {title: '上报时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '回退人', field: 'auditName', width: 80},
                {title: '回退时间', field: 'auditDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '回退节点', field: 'nodeName', width: 120},
                {
                    title: '退回意见', field: 'tip', width: 120, formatter: function (value, row, index) {
                        var tmpId = "tmp_opioin_" + index;
                        return "<span style='cursor:pointer;color:blue;text-decoration:underline;' id='" + tmpId + "' onclick='popMsg(\"" + row.opinion + "\",\"" + tmpId + "\")'>审核意见</span>"
                    }
                },
                
            ]],
            onBeforeLoad: function (param) {

            },
            onLoadSuccess: function (data) {
                $('.s-op-review').click(function () {
                    var ix = $(this).attr('rel');
                    var row = data.rows[ix];
                    let urlSuffix = '?eventCode=' + row.eventCode + '&id=' + row.id + '&page_type=${pageType}' + '&showOperate=false&showLast=true';
                    if (row.id != '') {
                        let title = row.eventTypeName + '查看';
                        let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                    }
                });
            },
            queryParams: parentParam,
            url: '${base}/ui/service/biz/amcs/adverse/common/findReturnList',
        });

        function initTab2(){
            $grid.newGrid("#gridBox1", {
            	pagination: true,
                columns: [[
                    {
                        title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
                            return opStr = '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>&nbsp;&nbsp;&nbsp;';
                            ;
                        }
                    },
                    {title: 'id', field: 'id', width: 80, hidden: true},
                    {title: 'eventCode', field: 'eventCode', width: 80, hidden: true},
                    {title: 'eventId', field: 'eventId', width: 80, hidden: true},
                    {title: 'node', field: 'node', width: 80, hidden: true},
                    {title: '页面地址', field: 'eventUrl', width: 120, hidden: true},
                    {title: '事件名称', field: 'eventName', width: 200},
                    {title:'事件分类一级', field:'gradeOne', hidden: false, width:150},
                    {title:'省区', field:'hospParent', hidden: false, width:80},
                    {title: '医院名称', field: 'hospName', width: 200},
                    {
                        title: '是否完结', field: 'finishSign', width: 120, formatter: function (v, row, index) {
                            if (v == 1) {
                                return "是";
                            } else {
                                return "否";
                            }
                        }
                    },
                    {title:'患者姓名', field:'patientName', hidden: false, width:80},
                    {title: '事件类型', field: 'eventTypeName', width: 120},
                    {title: '上报人', field: 'creatorName', width: 80},
                    {title: '上报时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},
                    {title: '发生时间', field: 'eventDate', width: 120, format: 'yyyy-MM-dd'},
                    {title: '所在节点', field: 'nodeName', width: 80},
                    {title:'状态',field:'status',width:80, formatter: function (v, row, index) {
                            if (v == 2) {
                                return "取消";
                            } else {
                                return "正常";
                            }
                        }
                    },
                ]],
                onBeforeLoad: function (param) {

                },
                onLoadSuccess: function (data) {
                    $('.s-op-review').click(function () {
                        var ix = $(this).attr('rel');
                        var row = data.rows[ix];
                        let urlSuffix = '?eventCode=' + row.eventCode + '&id=' + row.id + '&node=' + row.node + '&page_type=${pageType}';
                        urlSuffix += '&showOperate=false&showLast=true';

                        if (row.id != '') {
                            let title = row.eventTypeName + '查看';
                            let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                            $pop.newTabWindow(title, url);
                        }
                    });
                },
                queryParams: {pageType: '${pageType}', type: '${type}', reportTimes: 1, isPrimary: true, finishSign: 0},
                url: '${base}/ui/service/biz/amcs/adverse/common/findList',
            });
        }


    });


</script>
</html>
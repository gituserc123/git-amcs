<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>不良事件专家绑定- 爱尔医院</title>
  <style type="text/css">
      .mainfooter {
          padding: 12px 10px 0;
          text-align: right;
      }
  </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
<div class="searchHead">
  <form id="sbox" class="soform form-enter">
    <input class="txt" name="types" id="types" type="hidden"/>
    <input class="txt" name="node"  value="3" type="hidden"/>
    <input class="txt" name="pageType"  value="4" type="hidden"/>
    <input class="txt" name="reportTimes"  value="1" type="hidden"/>
    <input class="txt" name="isPrimary"  value="true" type="hidden"/>
    <input class="txt" name="status"  value="0" type="hidden"/>
    <input class="txt" id="subspecialtyCode" name="subspecialtyCode" type="hidden" />
    [#if (isProvince == null || isProvince == false) ]
      <input class="txt" name="isProvince" value="false" type="hidden"/>
      <label class="lab-inline">省区：</label>
      <select class="drop drop-sl-v easyui-combobox  w-150 province" name="province"
              data-options="valueField:'id',textField:'name',clearIcon:true"></select>
    [#else]
        <input class="txt" name="province" value="${provinceCode}" type="hidden"/>
        <input class="txt" name="isProvince" value="true" type="hidden"/>
    [/#if]
    <label class="lab-inline">医院：</label>
    <select class="drop drop-sl-v easyui-combobox w-150 hosp" name="hospId"
            data-options="valueField:'ahisHosp',textField:'name',clearIcon:true">
    </select>
    <label class="lab-inline">上报日期：</label>
    <input type="text" class="txt so-rangeDate txt-validate" name="reportDate"
           data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>
    <label class="lab-inline">发生日期：</label>
    <input type="text" class="txt so-rangeDate txt-validate" name="eventDate"
           data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>
    <label class="lab-inline">表单类型：</label>
    <select class="drop easyui-combobox w-120" id="type" data-options="multiple:true, clearIcon:true">
      <option value="1">医疗</option>
      <option value="2">护理</option>
      <option value="3">院感</option>
    </select>
    <label class="lab-inline">是否指派：</label>
    <select class="drop easyui-combobox w-120" id="hasAssignExpert" name="hasAssignExpert" data-options="multiple:false, clearIcon:true">
      <option value=""></option>
      <option value="0">否</option>
      <option value="1">是</option>
    </select>
    <label class="lab-inline">专家点评状态：</label>
    <select class="drop easyui-combobox w-120" id="eReviewStatus" name="eReviewStatus" data-options="multiple:false, clearIcon:true">
      <option value=""></option>
      <option value="0">未点评</option>
      <option value="1">已点评</option>
    </select>
    <label class="lab-inline">亚专科</label>
    <select class="drop easyui-combobox w-150 subType" id="subspecialty" data-options="editable:false, multiple:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
    </select>
    <input class="txt" name="eventLevels" id="eventLevels" type="hidden" />
    <label class="lab-inline">事件分级：</label>
    <select class="drop easyui-combobox w-100" id="eventLevel" data-options="multiple:true, clearIcon:true" >
		<option value="IV级（隐患事件）">IV级（隐患事件）</option>
        <option value="III 级（未造成后果事件）">III 级（未造成后果事件）</option>
        <option value="II 级（不良事件）">II 级（不良事件）</option>
        <option value="I级（警告事件）">I级（警告事件）</option>
	</select>
	<input class="txt" id="gradeOneCodeStr" name="gradeOneCodeStr" type="hidden" />
	<label class="lab-inline">事件分类一级</label>
    <select class="drop easyui-combobox w-150 gradeOne" id="gradeOne" data-options="multiple:true,loadFilter:function(data){
            var result = data.filter(item=>{
                return true;
            })
            return result;
        },editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
    </select>
	<label class="lab-inline">患者姓名：</label>
	<input type="text" class="txt inline" name="patientName" value="">
    <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询
    </button>
  </form>
</div>
<div class="cont-grid">
  <div id="gridBox"></div>
</div>
</div>
</body>
<script id="chooseExpertTem" type="text/html">
  <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
  <div class="searchHead">
    <form id="ssbox" class="soform form-enter">
        [#if (isProvince == null || isProvince == false) ]
            <input class="txt" name="isProvince" value="false" type="hidden"/>
        [#else]
            <input class="txt" name="isProvince" value="true" type="hidden"/>
        [/#if]
        <input class="txt" name="groups" id="groups" type="hidden" />
        <label class="lab-inline">所属学组：</label>
        <select class="drop easyui-combobox w-100" id="groupSel"  data-options="multiple:true, clearIcon:true, value: ''">
            <option value="1">白内障</option>
            <option value="2">青光眼</option>
            <option value="4">眼底病</option>
            <option value="8">泪道</option>
            <option value="16">屈光</option>
            <option value="32">角膜及眼表</option>
            <option value="64">眼眶与眼整形</option>
            <option value="128">视光</option>
            <option value="256">斜弱视</option>
            <option value="512">特检</option>
            <option value="1024">麻醉</option>
            <option value="2048">药学</option>
            <option value="4096">检验</option>
        </select>
        <input class="txt inline w-250" type="text" name="staffKey" id="staffKey" value="" placeholder="姓名、工号、手机号码">
        <button type="button" class="btn btn-small btn-primary so-search expert-search" data-opt="{grid:'#expertGridBox',scope:'#ssbox'}">查 询</button>
    </form>
  </div>
  <div id="con-exper" style="display: flex;">
    <div class="cont-grid cont-grid-a bot-line-sub" style="flex: 1;">
      <div id="expertGridBox"></div>
    </div>
    <div class="cont-grid" style="flex: 1;border-left: 5px solid #dddddd;">
      <div id="choosedExper"></div>
    </div>
  </div>

  <div class="mainfooter">
    <span class="btn btn-primary btn-ok">绑定</span>
    <span class="btn btn-cancel">取消</span>
  </div>
</script>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {
        this.loadSelData();
        let queryData = {node: 3, pageType: 4, reportTimes: 1, isPrimary: true, status: 0};
        if('${isProvince}' == 'true') {
            queryData.province = '${provinceCode}';
            queryData.isProvince = true;
        }else{
            queryData.isProvince = false;
        }
        $('#type').combobox({
            onChange: function (newValue, oldValue) {
                $("#types").val(newValue);
            }
        });

        $("#type").combobox('setValues', []);
        
        $('#subspecialty').combobox({
            onChange: function(newValue,oldValue){
                $('#subspecialtyCode').val(newValue);
            }
        });
        $('#eventLevel').combobox({
			onChange: function(newValue,oldValue){
			    $("#eventLevels").val(newValue);
			}
		});
		$("#eventLevel").combobox("setValues", []);
		
		$('#gradeOne').combobox({
            onChange: function(newValue,oldValue){
                $('#gradeOneCodeStr').val(newValue);
            }
        });

        $grid.newGrid("#gridBox", {
            pagination: true,
            fitColumns: false,
            singleSelect: false,
            checkOnSelect: true,
            tools: [[
                {
                    iconCls: 'plus', text: '指派专家', click: function () {
                        popExpertTem();
                    }
                }
            ]],
            columns: [[
                {
                    title: "操作", field: "op", width: 75, formatter: function (v, row, index) {
                        return '<span class="s-op s-op-view icon-eye" title="查看" rel="' + index + '"></span>';
                    }
                },
                {title: 'id', field: 'id', width: 80, checkbox: true},
                {title: 'eventCode', field: 'eventCode', width: 80, hidden: true},
                {title: 'eventId', field: 'eventId', width: 80, hidden: true},
                {title: 'node', field: 'node', width: 80, hidden: true},
                {title: '页面地址', field: 'eventUrl', width: 120, hidden: true},
                {title: '事件名称', field: 'eventName', width: 200},
                {title:'是否已指派',field:'expertStatus',width:100, formatter: function (v, row, index) {
                            if (v != "" && v >= 1) {
                                return "是";
                            } else {
                                return "否";
                            }
                        }
                    },
                {title: '省区', field: 'province', hidden: '${isProvince}', width: 80},
                {title:'专家是否点评',field:'isExpertReview',width:80, formatter: function (v, row, index) {
                        if (v > 0) {
                            return "是";
                        } else {
                            return "否";
                        }
                    }
                },
                {title: '医院名称', field: 'hospName', width: 200},
                {title:'亚专科', field:'subspecialty', width:80},
                {title: '事件分类一级', field: 'gradeOne', hidden: false, width: 150},
                {title: '患者姓名', field: 'patientName', hidden: false, width: 80},
                {title: '上报人', field: 'creatorName', width: 80},
                {title: '上报时间', field: 'createDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '发生时间', field: 'eventDate', width: 120, format: 'yyyy-MM-dd'},
                {title: '所在节点', field: 'nodeName', width: 80},
                {
                    title: '是否完结', field: 'finishSign', width: 80, formatter: function (v, row, index) {
                        return ['<span class="red">否</span>', '<span class="green">是</span>'][v];
                    }
                },
                {
                    title: '状态', field: 'status', width: 80, formatter: function (v, row, index) {
                        if (v == 2) {
                            return "取消";
                        } else {
                            return "正常";
                        }
                    }
                }
            ]],
            rowStyler: function (index, row) {
            	if(row.expertStatus != ''&& row.expertStatus >= 1){
                    return "background-color: #ABEBC6";
                    
                }

            },
            onBeforeLoad: function (param) {

            },
            onLoadSuccess: function (data) {
                $('.s-op-view').click(function () {
                    var ix = $(this).attr('rel');
                    var row = data.rows[ix];
                    let urlSuffix = '?eventCode=' + row.eventCode + '&id=' + row.id + '&node=3&showOperate=false&page_type=4';
                    urlSuffix += '&is_review=true';

                    if (row.id != '') {
                        let title = row.eventTypeName;
                        let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                    }
                });
            },
            queryParams: queryData,
            url: '${base}/ui/service/biz/amcs/adverse/expert/findEventList'
        });

        function popExpertTem() {
            //获取选中的事件
            let selRows = $("#gridBox").datagrid("getSelections");
            let eventIds = [];
            let primaryEventId = '';
            if (selRows.length == 0) {
                $pop.alert('请选择事件!');
                return;
            } 
   
            $.each(selRows, function (index, row) {
                eventIds.push(row.id);
            });

            if (selRows.length == 1) {
                primaryEventId = eventIds[0];
            }

            var pop = $pop.popTemForm({
                title: "专家选择",
                temId: 'chooseExpertTem',
                zIndex: 2,
                area: ['1050px', '520px'],
                end: function () {
                    $grid.reload("#gridBox")
                },
                onPop: function ($p) {
                    $("#con-exper").on("click",".s-op-btn",function(e){
                        e.stopPropagation();
                        var $this = $(this);
                        var $leftGrid = $("#expertGridBox"),$rightGrid = $("#choosedExper");
                        var type = $this.data('type')
                        if(type == 'add'){
                            var addData = $leftGrid.datagrid("getRows")[$this.data('index')]
                            var addDataID = addData.id; // 假设数据对象中有一个名为 "id" 的属性
                            var rowIndex = -1;
                            $rightGrid.datagrid('getRows').forEach(function (row, index) {
                                if (row.id == addDataID) {
                                    rowIndex = index;
                                    return false; // 立即退出循环
                                }
                            });

                            if (rowIndex > -1) {
                                // 已存在该记录
                                $pop.msg.warning('已存在该记录');
                            } else {
                                // 不存在该记录，可以执行相应的操作
                                $rightGrid.datagrid('appendRow', addData);
                            }

                        }else if(type=='del'){
                            console.log('从右侧表格删除');
                            $rightGrid.datagrid('deleteRow',$this.data('index'));
                            $rightGrid.datagrid('loadData',$rightGrid.datagrid('getRows'));
                        }
                    });
                    $grid.newGrid("#expertGridBox", {
                        pagination: true,
                        fitColumns: false,
                        grid: '#gridBox',
                        height: '370px',
                        pageSize: 10,
                        columns: [[
                            {title: 'id', field: 'id', width: 80, hidden: true},
                            {field: 'staffId', title: 'staffId', width: 100, hidden: true},
                            {field: 'staffCode', title: '专家工号', width: 80, align: 'center'},
                            {field: 'staffName', title: '专家姓名', width: 80, align: 'center'},
                            {field: 'groupName', title: '所属学组', width: 140, align: 'center'},
                            {field: 'pEventCount', title: '省区累计', width: 60, align: 'center'},
                            {field: 'gEventCount', title: '集团累计', width: 60, align: 'center'},
                            {field: 'op',formatter:function (value,row,index){
                                    return "<span class='s-op s-op-btn icon-arrow-right2' style='cursor: pointer;' data-type='add' data-index='"+index+"'></span>"
                                }}
                        ]],
                        rowStyler: function (index, row) {
                        },
                        onBeforeLoad: function (param) {
                        },
                        onLoadSuccess: function (data) {
                            console.log('success..')
                        },
                        queryParams: {isProvince: '${isProvince}' ? '${isProvince}' : false},
                        url: '${base}/ui/service/biz/amcs/adverse/expert/findList'
                    });
                    $grid.newGrid("#choosedExper", {
                        pagination:false,
                        height: '370px',
                        singleSelect: false,
                        checkOnSelect: true,
                        columns: [[
                            {field: 'op',formatter:function (value,row,index){
                                return "<span class='s-op s-op-btn icon-del' style='cursor: pointer;' data-index='"+index+"' data-type='del'></span>"
                            }},
                            {title: 'id', field: 'id', width: 80, hidden: true},
                            {field: 'staffId', title: 'staffId', width: 100, hidden: true},
                            {field: 'staffCode', title: '专家工号', width: 100, align: 'center'},
                            {field: 'staffName', title: '专家姓名', width: 100, align: 'center'},
                            {field: 'groupName', title: '所属学组', width: 150, align: 'center'}
                        ]],
                        queryParams : {eventId: primaryEventId, isProvince: '${isProvince}'},
                        url: '${base}/ui/service/biz/amcs/adverse/expert/findListByEvent'
                    });
  
                    $('#groupSel').combobox({
						onChange: function(newValue,oldValue){
						    $("#groups").val(newValue);
						}
					});
                    $('.btn-ok').click(function () {
                        var selectData = $("#choosedExper").datagrid('getRows');
                        if(selectData.length == 0){
                            $pop.alert('请选择专家！');
                            return false;
                        }
                        
                        let experts = []
                        $.each(selectData, function (index, row) {
                            experts.push({"expertId": row.staffId, "expertCode": row.staffCode});
                        });
                        let bindData = {"eventIds": eventIds, "experts": experts, "isProvince": '${isProvince}'};
                        $ajax.post('${base}/ui/service/biz/amcs/adverse/expert/bind', bindData, {
                            jsonData: true,
                            tip: false
                        }).done(function (rst) {
                            if (rst.code === '200' || rst.code === '201') {
                                $pop.success('绑定成功!', function (index) {
                                    $pop.closePop({popIndex: pop});
                                });
                            } else {
                                $pop.alert(rst.msg);
                            }
                        });
                    });
                }
            });
        }

    });

    function loadSelData() {
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince', null, false, false).done(function (rst) {
            $('.province').combobox('loadData', rst);
        });
        let instId = "100001";
        //从后端传入的参数provinceCode获取省区ID,如果provinceCode不为空，则赋值给instId;
        if('${provinceCode}' != ''){
            instId = '${provinceCode}';
        }
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=' + instId, null, false, false).done(function (rst) {
            $('.hosp').combobox('loadData', rst);
        });
        $('.province').combobox({
            onChange: function (v) {
                let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                //清空医院下拉框
                $('.hosp').combobox('clear');
                $('.hosp').combobox('reload', url);

            }
        });
        let param = {paraType : ['sub_type', 'grade_one']};	
        $ajax.postSync('${base}/ui/amcs/dict/getAeMoreList',param,false,false).done(function (rst) {
            $('.subType').combobox('loadData', rst.data.sub_type);
            $('.gradeOne').combobox('loadData', rst.data.grade_one);
        });
    };

</script>
</html>
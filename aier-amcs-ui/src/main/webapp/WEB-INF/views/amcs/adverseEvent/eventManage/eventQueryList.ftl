<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>不良事件查询 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            [#if empType==1]
            <label class="lab-inline">省区：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true"></select>
            [/#if]
            [#if empType==1 || empType==2]
            <label class="lab-inline">医院：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true"></select>
            <label class="lab-inline">医院级别：</label>
            <select class="drop w-100"  id="ehrLevel" name="ehrLevel">
                <option value= ></option>
                <option value=10>集团总部</option>
                <option value=11>省区</option>
                <option value=12>省会级医院</option>
                <option value=13>地市级医院</option>
                <option value=14>县市级医院</option>
                <option value=15>眼科门诊</option>
                <option value=20>大区</option>
                <option value=30>区域</option>
            </select>
            [/#if]
            [#if empType == 3 || empType==2]
                <label for="showArchived">显示归档</label>
                <input type="checkbox" id="showArchived" name="showArchived" value="true">
            [/#if]
            [#--<label class="lab-inline">上报开始日期：</label>
            <input type="text" class="txt so-date w-100" name="reportDate"   noNull="请选择上报开始日期"/>
            <label class="lab-inline">上报结束日期：</label>
            <input type="text" class="txt so-date w-100" name="reportEndDate"   noNull="请选择上报结束日期"/>
            <label class="lab-inline">发生开始日期：</label>
            <input type="text" class="txt so-date w-100" name="eventDate"   noNull="请选择发生开始日期"/>
            <label class="lab-inline">发生结束日期：</label>
            <input type="text" class="txt so-date w-100" name="eventEndDate"   noNull="请选择发生结束日期"/>--]
            <label class="lab-inline">上报日期：</label>
            <input id="reportDate" name="reportDate" type="text" class="txt so-rangeDate" data-opt="{showDropdowns:true}"/>
            <label class="lab-inline">发生日期：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="eventDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true}"/>
            <label class="lab-inline">省区审核通过日期：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="provAuditDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true}"/>
            <label class="lab-inline">患者姓名：</label>
            <input type="text" class="txt inline" name="patientName" value="">
            <label class="lab-inline">当事人员姓名：</label>
            <input type="text" class="txt inline" name="staffName" value="">
            <label class="lab-inline">事件名称：</label>
            <input type="text" class="txt inline" name="eventName" value="">
            <label class="lab-inline">赔偿金额区间：</label>
            <input type="text" class="txt inline easyui-numberbox wp-5" data-options="min:0,precision:0" name="compensationAmount">&nbsp;至&nbsp;<input type="text" class="txt inline easyui-numberbox wp-5" data-options="min:0,precision:0" name="compensationUpAmount">
            <label class="lab-inline">减免金额区间：</label>
            <input type="text" class="txt inline easyui-numberbox wp-5" data-options="min:0,precision:0" name="deductionAmount">&nbsp;至&nbsp;<input type="text" class="txt inline easyui-numberbox wp-5" data-options="min:0,precision:0" name="deductionUpAmount">
            [#--<label class="lab-inline">事件类型：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="eventType" id="eventType" data-options="valueField:'eventCode',textField:'eventName',clearIcon:true">
            </select>--]
            <label class="lab-inline">多次上报事件：</label>
            <select class="drop wp-5"  id="maxReportTimes" name="maxReportTimes">
                <option value="" ></option>
                <option value="2">≥2</option>
                <option value="3">≥3</option>
                <option value="4">≥4</option>
            </select>
            [#if empType==1 || empType==2]
            <label class="lab-inline">事件分级：</label>
            [@ui_select class="drop easyui-combobox w-150" uiShowDefault=true  id="eventLevelSelect" name="eventLevelSelect" tag="@ae@event_level" dataOptions="multiple:true" filterkey="firstSpell"/]
            <input class="txt" id="eventLevelDesc" name="eventLevelDesc" type="hidden" />
            [/#if]
            <label class="lab-inline">表单分类：</label>
            <select class="drop w-100"  id="eventType" name="eventType">
	            <option value="" ></option>
	            <option value="1">医疗</option>
	            <option value="2">护理</option>
	            <option value="3">院感</option>
	            <option value="9">其他</option>
            </select>
            [#if empType==1]
            <label class="lab-inline">表单类型：</label>
            <input class="easyui-combobox drop w-100"  id="eventCode" name="eventCode" data-options="valueField:'eventCode',textField:'eventName'"></input>
            [/#if]
            <label class="lab-inline">是否完结：</label>
            <select class="drop w-100"  id="" name="finishSign">
                <option value="" ></option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
            <input class="txt" id="subspecialtyCode" name="subspecialtyCode" type="hidden" />
            <label class="lab-inline">亚专科</label>
            <select class="drop easyui-combobox w-150 subType" id="subspecialty" data-options="editable:false, multiple:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
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
            <input class="txt" id="gradeTwoACodeStr" name="gradeTwoACodeStr" type="hidden" />
            <label class="lab-inline">事件分类二级I</label>
            <select class="drop easyui-combobox w-150 gradeTwoA" id="gradeTwoA" data-options="editable:false, multiple:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
            </select>
            <label class="lab-inline">非计划情况：</label>
            [@ui_select id="unplan"  class="drop easyui-combobox w-120"   uiShowDefault=true   name="unplan"  tag="@ae@unplan" dataOptions="" filterkey="firstSpell" /]
            <input class="txt" name="tags" id="tags" type="hidden" />
        	<label class="lab-inline">涉及科室/人员类型</label>
            <select class="drop easyui-combobox w-80" id="tagSel" data-options="multiple:true, editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
            </select>
            <label class="lab-inline">赔付/减免金额发生时间：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="amountDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true}" />
            <input class="txt" name="severityLevels" id="severityLevels" type="hidden" />
            <label class="lab-inline">严重程度：</label>
	        <select class="drop drop-sl-v easyui-combobox  w-150" name="severityLevel" id="severityLevel" data-options="multiple:true, clearIcon:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
            </select>
            <label class="lab-inline">是否高风险：</label>
            <select class="drop w-100" name="isHighRisk">
                <option value="" ></option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
            <label class="lab-val"><input type="checkbox" class="chk"  name="unOtherReport" value="1"/>剔除旧系统事件</label>
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
            <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
            <button type="button" class="btn btn-small btn-primary s-export-detail">导出明细</button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
    <div class="none">
        <div id="fileUrlDiv"></div>
    </div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", "moment","template",'myupload','pub'], function (_, $e,moment,template,myupload) {
        this.reloadEventData();
        
        $('#severityLevel').combobox({
			onChange: function(newValue,oldValue){
			    $("#severityLevels").val(newValue);
			}
		});

        // 一级分类
        $('#gradeOne').combobox({
            onChange: function(newValue,oldValue){
                debugger;
                let sub = $('#subspecialty').combobox('getValue');
                //filterGradeTwoI(sub, newValue);
                $('#gradeOneCodeStr').val(newValue);
                var paramDict = {type : 'grade_two_a',gradeOneArr:newValue};
                $ajax.postSync('${base}/ui/amcs/dict/getEventQueryAeDict',paramDict,false,false).done(function (rst) {
                    $('.gradeTwoA').combobox('loadData',rst);
                });
            }
        });

        $('#subspecialty').combobox({
            onChange: function(newValue,oldValue){
                let grade = $('#gradeOne').combobox('getValue');
                //filterGradeTwoI(newValue, grade);
                $('#subspecialtyCode').val(newValue);
            }
        });

        // 二级分类
        $('#gradeTwoA').combobox({
            onChange: function(newValue,oldValue){
                $('#gradeTwoACodeStr').val(newValue);
            }
        });
        
        $('#tagSel').combobox({
			onChange: function(newValue,oldValue){
			    $("#tags").val(newValue);
			}
		});

        $('#eventLevelSelect').combobox({
            onChange: function(newValue,oldValue){
                $('#eventLevelDesc').val($('#eventLevelSelect').combobox('getText'));
            }
        });

        function filterGradeTwoI(sub, grade) {
            if(sub != null && grade != "") {
                let filter = sub + ',' + grade;
                let url = base + '/ui/amcs/dict/getAeDict?type=grade_two_a&filter=' + filter;
                $('#gradeTwoA').combobox('reload', url);
            }else{$('#gradeTwoA').combobox('reload', base + '/ui/amcs/dict/getAeDict?type=grade_two_a');}
        }

        $('#eventType').change(function(){
            var sVal = $(this).children('option:selected').val();
            if(sVal){
                /*if(sVal=='1'){
                    $('#eventCode').combobox('loadData',eval('[{"valueDesc":"门诊患者不良事件","valueCode":"101",},{"valueDesc":"住院患者不良事件","valueCode":"102",},{"valueDesc":"角膜接触镜不良事件","valueCode":"103",},{"valueDesc":"框架眼镜不良反应","valueCode":"104",},{"valueDesc":"视觉训练不良反应","valueCode":"105",},{"valueDesc":"其他医疗类不良事件(不涉及患者本人)","valueCode":"106",},{"valueDesc":"其他视光患者不良事件","valueCode":"107",},{"valueDesc":"门诊患者不良事件(手术患者)","valueCode":"108",}]'));
                }
                if(sVal=='2'){
                    $('#eventCode').combobox('loadData',eval('[{"valueDesc":"跌倒/坠床事件","valueCode":"201",},{"valueDesc":"给药错误事件","valueCode":"202",},{"valueDesc":"标本采集不良事件","valueCode":"203",},{"valueDesc":"其他护理不良事件","valueCode":"204",}]'));
                }
                if(sVal=='3'){
                    $('#eventCode').combobox('loadData',eval('[{"valueDesc":"医院感染事件","valueCode":"301",},{"valueDesc":"职业暴露事件","valueCode":"303",}]'));
                }*/
                $ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',{eventType:sVal},false,false).done(function (rst) {
                    $('#eventCode').combobox('loadData', rst.rows);
                });
            }else{
                $ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',null,false,false).done(function (rst) {
                    $('#eventCode').combobox('loadData', rst.rows);
                });
            }
        });

    	$grid.newGrid("#gridBox",{
            pagination: true,
            fitColumns: false,
            pageSize: 100,
	    	columns:[[
                {title: "操作", field: "op", width: 70, formatter: function (v, row, index) {
                        let opStr = '';
                        opStr = '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
                        opStr = opStr + '&nbsp;&nbsp;<span class="s-op s-op-fanruan icon-printer" title="报表" rel="' + index + '"></span>';
                        [#if empType == 3 || attachAuth == 1]
                        opStr = opStr + '&nbsp;&nbsp;<span class="s-op s-op-upload icon-upload" title="上传不良事件根因分析文档" rel="'+row.id+'"></span>';
                        [/#if]
                        return opStr;
                    }
                },
		        {title:'id',field:'id',width:80,hidden:true},
		        {title:'eventId',field:'eventId',width:80,hidden:true},
		        {title:'node',field:'node',width:80,hidden:true},
		        {title:'上报时间', field:'createDate', width:120, format:'yyyy-MM-dd'},
                {title:'发生日期', field:'eventDate', width:120,format:'yyyy-MM-dd'},
                {title:'省区', field:'hospParent', hidden: false, width:80},
                {title:'医院等级', field:'ehrLevel', hidden: false, width:80},
                {title:'医院', field:'hospName', hidden: false, width:150},
                {title:'医院类型', field:'investNature', hidden: false, width:80,formatter:function (v, row, index) {
                    if(v=='10'){return '上市';}else if(v=='11'){return '合伙';}else{return '';}
                }},
                {title:'事件分类一级', field:'gradeOne', hidden: false, width:150},
                {title:'事件分类二级', field:'gradeTwoA', hidden: false, width:150},
		        {title:'事件名称', field:'eventName', hidden: false, width:200},
                {title:'涉及科室/人员类型', field:'tagCodeName', hidden: false, width:150},
                {title:'上报人', field:'creatorName', hidden: false, width:80},
                {title:'患者姓名', field:'patientName', hidden: false, width:80},
                {title:'亚专科', field:'subspecialty', hidden: false, width:80},
                {title:'表单分类', field:'eventCode', hidden: false, width:80,formatter:function (v, row, index) {
                        if($.inArray(v, ['101','102','103','104','105','106','107','108'])>-1){
                            return '医疗类';
                        }
                        if($.inArray(v, ['201','202','203','204','205','206','207','208'])>-1){
                            return '护理类';
                        }
                        if($.inArray(v, ['301','302','303','304','305','306','307','308'])>-1){
                            return '院感类';
                        }
                }},
                {title:'患者性别', field:'patientSex', hidden: false, width:60,formatter: function (v, row, index) { if(v==1) return '男'; else return '女';}},
                {title:'患者年龄', field:'patientAge', hidden: false, width:60},
                {title:'严重程度', field:'severityLevelDesc', hidden: false, width:60},
                {title:'事件分级', field:'eventLevel', hidden: false, width:100},
                {title:'上报次数',field:'maxReportTimes',width:80},
                {title:'是否符合上报时限(≤30天)', field:'isConform', hidden: false, width:120,formatter:function (v, row, index) {
                        if(v==1){
                            return '符合';
                        }else{
                            return '不符合';
                        }
                    }},
                {title:'是否完结', field:'finishSign', hidden: false, width:60,formatter: function (v, row, index) { if(v==1) return '是'; else return '否';}},
                {title:'是否构成纠纷', field:'disputeSign', hidden: false, width:80,formatter: function (v, row, index) { if(v==1) return '是'; else return '否';}},
                {title:'赔偿金额', field:'totalCompensationAmount', hidden: false, width:60},
                {title:'减免金额', field:'totalDeductionAmount', hidden: false, width:60},
                {title:'赔偿减免产生时间', field:'lastAmountDate', width:120, format:'yyyy-MM-dd'},
                {title:'是否高风险', field:'isHighRisk', hidden: false, width:80,formatter: function (v, row, index) { if(v==1) return '是'; else return '否';}},
                {title:'非计划情况', field:'unplanDesc', hidden: false, width:80},
                {title:'根因分析', field:'attachs', hidden: false, width:120,formatter:function (v, row, index) {
                        if (v && v.length) {
                            var returnS = '';
                            v.forEach(function (i, s) {
                                returnS += '<span class="s-attach-preview blue hand" title="预览" rel="' + i.id + '" webUrl="' + i.url + '">附件' + (s + 1) + '</span>&nbsp;'
                                [#if empType == 3 || attachAuth == 1]
                                returnS += '<span class="s-op s-op-del-att icon-del" style="color: red" title="删除" rel="' + i.id + '" webUrl="' + i.url + '"></span>&nbsp;&nbsp;'
                                [/#if]
                            })
                            return returnS;
                        }
                    }},
		    ]],
            queryParams:{
                reportDateBegin: '${lastMonth20}',
                reportDateEnd: '${currentMonth21}',
            },
            rowStyler : function(index, row) {
                if(row.isArchived){
                    return "background-color: darkgrey";
                }

            },
		    onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {
		    	$('.s-op-review').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node + '&page_type=9&showOperate=0&showLast=true';
					
					if(row.id != ''){
					    let title = '查看';
                        if($.inArray(row.eventCode, ['101','102','103','104','105','106','107','108'])>-1){
                            title = '医疗类查看';
                        }
                        if($.inArray(row.eventCode, ['201','202','203','204','205','206','207','208'])>-1){
                            title = '护理类查看';
                        }
                        if($.inArray(row.eventCode, ['301','302','303','304','305','306','307','308'])>-1){
                            title = '院感类查看';
                        }
                        if($.inArray(row.eventCode, ['901','902'])>-1){
                            title = '其他类查看';
                        }
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
				$('.s-op-increase').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					if(row.id != ''){
					    let title = row.eventCode + '进展上报';
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage?eventCode='+ row.eventCode +'&id=' + row.id  +'&isIncrease=true'+ '&node=' + row.node;
                        $pop.newTabWindow(title, url);
                     }
				
				});
                $('.s-op-fanruan').click(function () {
                    var ix = $(this).attr('rel');
                    var row = data.rows[ix];
                    if(row.id != ''){
                        //window.location.href='${base}/fr?url='+row.reportType+'.cpt&op=view&basicid=' + row.id ;
                        //$pop.newTabWindow(row.eventName, '${base}/fr?url='+row.reportType+'.cpt&common&op=view&basicid=' + row.id + '&prev_id=' + row.prevId);
                        $pop.newTabWindow(row.eventName, '${base}/fr?url='+row.reportType+'.cpt&common&basicid=' + row.id + '&prev_id=' + row.prevId);
                    }
                });
                //附件预览
                $('.s-attach-preview').click(function (data) {
                    var id = $(this).attr('rel');
                    var webUrl = $(this).attr('webUrl');

                    if (webUrl) {
                        $pop.iframePop({
                            title: '附件预览',
                            content : encodeURI(webUrl),
                            area: ['800px', '640px']
                        });
                        return false;
                    }
                });
                //附件删除
                $('.s-op-del-att').click(function () {
                    var id = $(this).attr('rel');
                    var webUrl = $(this).attr('webUrl');
                    $ajax.post({
                        url: '${base}/ui/service/biz/amcs/adverse/aeFile/delete?attachmentFileId=' + id + '&attachmentUrl=' + webUrl,
                        jsonData: true,
                        tip: '你确定删除此附件吗？',
                        callback: function (rst) {
                            $('.so-search').click();
                        }
                    });
                });
                //附件上传
                $('.s-op-upload').click(function () {
                    var id = $(this).attr('rel');
                    // 将row.id传递给上传组件
                    upload.options.formData={
                        bizId: id
                    }
                    $('#fileUrlDiv').find('input').click();
                });

            },
      		url: '${base}/ui/service/biz/amcs/adverse/common/queryList',
      		offset : -50
  		});

        $(".s-export").click(function ($e) {
            var formData = $('#sbox').sovals();
            if(formData.reportDateBegin=='' && formData.reportDateEnd=='' &&
                formData.eventDateBegin=='' && formData.eventDateEnd==''){
                $pop.alert('请选择上报日期或发生日期');
                return false;
            }
            $pop.confirm('您确认想要导出记录吗？',function(r){
                if (r){
                    //window.location.href="${base}/ui/amcs/adverse/event/query/exportExcel?paramJson="+encodeURIComponent(JSON.stringify(formData));
                    var url = '${base}/ui/amcs/adverse/event/query/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    downloadExcelProcess(url);
                }
            });
        });

        $(".s-export-detail").click(function ($e) {
            var formData = $('#sbox').sovals();
            if(formData.reportDateBegin=='' && formData.reportDateEnd=='' &&
                formData.eventDateBegin=='' && formData.eventDateEnd==''){
                $pop.alert('请选择上报日期或发生日期');
                return false;
            }
            $pop.confirm('您确认想要导出明细记录吗？',function(r){
                if (r){
                    //var sWidth = $(document).width();
                    //var sHeight = $(document).height();
                    //window.location.href="${base}/ui/amcs/adverse/event/query/exportExcelDetail?paramJson="+encodeURIComponent(JSON.stringify(formData));
                    /*
                    // 构造隐藏的form表单
                    var $form = $("<form id='download' class='hidden' method='post'></form>");
                    $form.attr("action", url);
                    $(document.body).append($form);
                    // 添加提交参数
                    var $input1 = $("<input name='paramJson'  type='hidden'></input>");
                    $input1.attr("value", JSON.stringify(formData));
                    $("#download").append($input1);
                    // 提交表单
                    $form.submit();
                    */
                    var url = '${base}/ui/amcs/adverse/event/query/exportExcelDetail?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    downloadExcelProcess(url);
                }
            });
        });

        var dateV = {
            yesterday : [
                moment().subtract(1, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            week : [
                moment().subtract(7, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            threeMonth : [
                moment().subtract(91, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            thisMonth : [
                moment().startOf('month'),
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
            ],
            prevMonth : [
                moment().subtract(1, 'month').startOf('month'),
                moment().subtract(1, 'month').endOf('month')
            ],
            prevYear: [
                moment().subtract(1, 'years'),
                moment()
            ]
        };

        var ranges ={
            '昨天': dateV.yesterday,
            '最近一周': dateV.week,
            '当月': dateV.thisMonth,
            '上个月': dateV.prevMonth,
            '最近90天': dateV.threeMonth,
            '最近一年': dateV.prevYear,
        };

        $form.rangeDate('#reportDate',{
            val:[moment().subtract(1, 'months').date(21),moment().date(20)],
            auto : false,//auto 为false 会显示确定按钮
            maxSpan:{days: 1095},
            ranges:ranges,
        });

        var upload = $("#fileUrlDiv").powerWebUpload({
            auto: true,
            fileNumLimit: 1,
            btnTxt: '附件上传',
            multiple: false,
            upOpt: {
                accept: {extensions: 'doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar'},
                dupliacate: true,
                server: '${base}/ui/service/biz/amcs/adverse/aeFile/queryPageUpload'
            },
            uploadSuccess: function (file, req) {
                $pop.close(uploadLoading);
                if(req.code == '201'){
                    $pop.msg.success(req.msg);
                }
                if(req.code=='500'){
                    $pop.alert(req.msg);
                }
                $('.so-search').click();
                upload.removeFile(file);
            },
            beforeUpload: function (file) {
                // 允许的文件扩展名列表
                const allowedExtensions = ['xls', 'doc', 'docx', 'ppt', 'pptx', 'xlsx', 'pdf', 'jpg', 'png', 'gif', 'txt', 'zip', 'rar'];
                // 检查文件扩展名是否在允许的列表中
                if (!allowedExtensions.some(ext => file.ext.indexOf(ext) !== -1)) {
                    $pop.msg('上传格式类型不符，请上传doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt,zip,rar格式文件，其他格式暂不支持~');
                }else{
                    uploadLoading = $pop.load(1,{shade:0.1});
                }
            },
            uploadError: function (file, req) {
                console.log(req);
                $pop.msg('文件上传失败！');
                $pop.close(uploadLoading);
                upload.removeFile(file);
            }
        });

    });

    var showMask = function () {
        var wrap = $(".cont-grid");
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: window.width,
            height: window.height
        }).appendTo(wrap);
    };
    var rmMask = function () {
        var wrap = $(".cont-grid");
        wrap.find("div.datagrid-mask").remove();
    }

    function reloadEventData() {
   		/*$ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',null,false,false).done(function (rst) {
    		$('#eventType').combobox('loadData', rst.rows);
    	});*/

        [#if empType==1]
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
                $('#province').combobox('loadData', rst);
            });
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
                $('#hospId').combobox('loadData', rst);
            });
            $('#province').combobox({
                onChange: function(v){
                    let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                    $('#hospId').combobox('reload', url);
                }
            });
        [/#if]
        [#if empType==2]
        var paramInsi = {insiId:'${insiId}'};
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
            $('#hospId').combobox('loadData', rst);
        });
        [/#if]

        var param = {paraType : ['sub_type','grade_one', 'tag_type','grade_two_a', 'severity_level']};
        $ajax.postSync('${base}/ui/amcs/dict/getAeMoreList',param,false,false).done(function (rst) {
            var dict = rst.data;
            $('.gradeOne').combobox('loadData',dict.grade_one);
            $('.subType').combobox('loadData',dict.sub_type);
            $("#tagSel").combobox("loadData", dict.tag_type);
            $('.gradeTwoA').combobox('loadData',dict.grade_two_a);
            $('#severityLevel').combobox('loadData', dict.severity_level);
        });
        $ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',null,false,false).done(function (rst) {
            $('#eventCode').combobox('loadData', rst.rows);
        });

    };

    function downloadExcelProcess(url){
        // 开启遮罩
        showMask();
        //$pop.alert('文件正在导出，请耐心等待....');
        $pop.msg('文件正在导出，请耐心等待....',3000,{icon:2});
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = "blob"; // 返回类型blob
        // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
        xhr.onload = function() {
            // 请求完成
            if (this.status === 200) {
                var type = xhr.getResponseHeader('Content-Type');
                var blob = new Blob([this.response], {
                    type: type
                });
                var fileName = xhr.getResponseHeader("content-disposition");
                fileName = decodeURI(fileName.split(";")[1].split("filename=")[1].trim('"'));
                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    // For IE>=IE10
                    window.navigator.msSaveBlob(blob, fileName);
                } else {
                    // For chrome, firefox
                    var URL = window.URL || window.webkitURL;
                    var objectUrl = URL.createObjectURL(blob);
                    if (fileName) {
                        // 创建一个a标签用于下载
                        var a = document.createElement('a');
                        a.download = fileName;
                        a.href = objectUrl;
                        $("body").append(a);
                        a.click();
                        $(a).remove();
                    } else {
                        window.location = objectUrl;
                    }
                }
            } else {
                $pop.alert('导出失败！');
            }
            // 关闭遮罩代码
            rmMask();
        };
        // 发送ajax请求
        xhr.send();
    };


</script>
</html>
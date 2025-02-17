<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>集团不良事件管理 - 爱尔医院</title>
    <style type="text/css">
		.s-op-unuse{
			color: red !important;
		}
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
	<script type="text/javascript" src='${base}/static/js/lib/moment.min.js?dbda5g'></script>
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input class="txt" name="reportTimes" value="1" type="hidden" />
            <input class="txt" name="isPrimary" value="1" type="hidden" />
            <input class="txt" name="pageType" value="${pageType}" type="hidden" />
        	<input class="txt" id="subspecialtyCode" name="subspecialtyCode" type="hidden" />
        	[#if pageType == 4]
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
            [/#if]
            
            [#if empType==1]
	            <label class="lab-inline">省区：</label>
	            <select class="drop drop-sl-v easyui-combobox  w-150 province" name="province"   data-options="valueField:'id',textField:'name',clearIcon:true"></select>
            [/#if]
            [#if empType==1 || empType==2]
            <label class="lab-inline">医院：</label>
            <select class="drop drop-sl-v easyui-combobox w-150 hosp" name="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
            </select>
            [/#if]
            <label class="lab-inline">上报日期：</label>
			[#if pageType != 4]
				<input id="reportDate" type="text" class="txt so-rangeDate txt-validate" name="reportDate" data-options="{val:'', auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true} "/>
			[#else]
				[#if isGroupReview == 1 || isGroupReview == 0]
					<input id="reportDate" type="text" class="txt so-rangeDate txt-validate" name="reportDate" data-options="{val:'', auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true} "/>
				[#else]
					<input id="reportDate" type="text" class="txt so-rangeDate txt-validate" name="reportDate" data-options="{val: [moment().subtract(1, 'months').date(21), moment().date(20)], auto: false, opens: 'center', maxSpan: {days: 1095},showDropdowns:true} " />
				[/#if]
			[/#if]

			<label class="lab-inline">发生日期：</label>
				<input type="text" class="txt so-rangeDate txt-validate" name="eventDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095},showDropdowns:true} " />
            <label class="lab-inline">所在节点：</label>
            <select class="drop easyui-combobox w-120"  id="node" name="node">
	            <option value="" ></option>
	            [#if pageType == 1]
	            	<option value="0">暂存</option>
		            <option value="1">上报未审核</option>
		            <option value="2">医院审核通过</option>
		            <option value="3">省区审核通过</option>
		        [/#if]
		        [#if pageType == 2]
		            <option value="1" selected="selected">上报未审核</option>
		            <option value="2">医院审核通过</option>
		            <option value="3">省区审核通过</option>
		        [/#if]
		        [#if pageType == 3]
		            <option value="2" selected="selected">医院审核通过</option>
		            <option value="3">省区审核通过</option>
		        [/#if]
		        [#if pageType == 4]
		        	<option value="2">医院审核通过</option>
					[#if isGroupReview == 1 || isGroupReview == 0]
		            	<option value="3">省区审核通过</option>
					[#else]
						<option value="3" selected="selected">省区审核通过</option>
					[/#if]
		        [/#if]
            </select>
			[#if pageType == 2 ]
				<label for="showArchived">显示归档</label>
			<input type="checkbox" id="showArchived" name="showArchived" value="true">
			[/#if]
			<label class="lab-inline">状态：</label>
        	<select class="drop easyui-combobox w-100"  id="status" name="status">
        		<option value=""></option>
				[#if isGroupReview == 1 || isGroupReview == 0]
					<option value="0">正常</option>
				[#else]
        			<option value="0" selected="selected">正常</option>
				[/#if]
	            <option value="2">取消</option>
        	</select>
        	[#if pageType == 4]
	        	<label class="lab-inline">是否查阅：</label>
	        	<select class="drop easyui-combobox w-100"  id="isGroupReview" name="isGroupReview">
					[#if isGroupReview == 1 || isGroupReview == 0]
	        			<option value=""></option>
					[#else]
						<option value="" selected="selected"></option>
					[/#if]
					[#if isGroupReview == 0]
	        			<option value="0" selected="selected">未查阅</option>
					[#else]
						<option value="0">未查阅</option>
					[/#if]
					[#if isGroupReview == 1]
						<option value="1" selected="selected">已查阅</option>
					[#else]
						<option value="1">已查阅</option>
					[/#if]
	        	</select>
	        [/#if]
	   		<input class="txt" name="types" id="types" type="hidden" />
	        <label class="lab-inline">表单类型：</label>
	        <select class="drop easyui-combobox w-120" id="type" data-options="multiple:true, clearIcon:true">
        		<option value="1">医疗</option>
	            <option value="2">护理</option>
	            <option value="3">院感</option>
	            <option value="9">其他</option>
        	</select>
        	[#if pageType == 2 || pageType == 3 || pageType == 4]
	        	<input class="txt" name="tags" id="tags" type="hidden" />
	        	<label class="lab-inline">涉及科室/人员类型</label>
	            <select class="drop easyui-combobox w-80" id="tagSel" data-options="multiple:true, editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
	            </select>
            [/#if]
            [#if pageType == 4 || pageType == 1]
            <label class="lab-inline">患者姓名：</label>
            <input type="text" class="txt inline" name="patientName" value="">
            [/#if]
            <label class="lab-inline">事件类型：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="eventType" id="eventType" data-options="valueField:'eventCode',textField:'eventName',clearIcon:true">
            </select>
            <input class="txt" name="severityLevels" id="severityLevels" type="hidden" />
            <input class="txt" name="eventLevels" id="eventLevels" type="hidden" />
            [#if pageType == 4]
			<label class="lab-inline">事件名称：</label>
			<input type="text" class="txt inline" name="eventName" value="">
	        <label class="lab-inline">严重程度：</label>
	        <select class="drop drop-sl-v easyui-combobox  w-150" name="severityLevel" id="severityLevel" data-options="multiple:true, clearIcon:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
            </select>
            <label class="lab-inline">事件分级：</label>
            <select class="drop easyui-combobox w-100" id="eventLevel" data-options="multiple:true, clearIcon:true" >
        		<option value="IV级（隐患事件）">IV级（隐患事件）</option>
	            <option value="III 级（未造成后果事件）">III 级（未造成后果事件）</option>
	            <option value="II 级（不良事件）">II 级（不良事件）</option>
	            <option value="I级（警告事件）">I级（警告事件）</option>
        	</select>
            [/#if]
            [#if pageType == 4 || pageType == 1]
	        	<label class="lab-inline">是否完结：</label>
	        	<select class="drop easyui-combobox w-100"  id="finishSign" name="finishSign">
	        		<option value="" selected="selected"></option>
	        		<option value="0">否</option>
		            <option value="1">是</option>
	        	</select>
	        [/#if]
			[#if pageType == 4]
				<label for="isFocus">重点事件</label>
				<input type="checkbox" id="isFocus" name="isFocus"[#if isFocus == true] checked = "checked" [/#if] value="true">
			[/#if]
			[#if pageType == 1]
				<label class="lab-inline">上报人：</label>
				<input type="text" class="txt inline" name="createName" value="">
			[/#if]
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">

    requirejs(["lodash", "easygridEdit", "moment", "pub"], function (_, $e,moment) {
		var rowIndexArr = [];
        reloadType();
        /*集团查阅时*/
        var subData = {};
        var gradeOneData = {};
        var tagData = {};
		var severityData = {};
        let param = {paraType : ['sub_type', 'grade_one', 'tag_type', 'grade_two_a', 'severity_level']};	
        $ajax.postSync('${base}/ui/amcs/dict/getAeMoreList',param,false,false).done(function (rst) {
            subData = rst.data.sub_type;
            gradeOneData = rst.data.grade_one;
            tagData = rst.data.tag_type;
			severityData = rst.data.severity_level;
            $("#tagSel").combobox("loadData", tagData);
            $('.subType').combobox('loadData', subData);
            $('.gradeOne').combobox('loadData', gradeOneData);
            $('.gradeTwoA').combobox('loadData',rst.data.grade_two_a);
            $('#severityLevel').combobox('loadData', severityData);
        });
        let node = $('#node').combobox('getValue');
        let queryData = {node: node, pageType: '${pageType}', reportTimes: 1, isPrimary: true, status: 0}
		if('${pageType}' == '4'){
			if(!('${isGroupReview}' == '1' || '${isGroupReview}' == '0')){
				queryData.reportDateBegin = moment().subtract(1, 'months').date(21).format('YYYY-MM-DD');
				queryData.reportDateEnd = moment().date(20).format('YYYY-MM-DD');
			}
		}
		if('${isGroupReview}' == '1' || '${isGroupReview}' == '0'){
			queryData.isGroupReview = '${isGroupReview}';
		}
		const formDataParam = '${formDataParam}'
		if(formDataParam){
			const formDataParamJson = JSON.parse(formDataParam);
			Object.entries(formDataParamJson).forEach(([key, value]) => {
				if (Array.isArray(value)) {
					// 如果是数组，将数组转换为逗号分隔的字符串
					const joinedValue = value.map(item => item.trim()).join(", ");
					if(key=='gradeOne'){
						queryData.gradeOneCodeStr = joinedValue;
					}else{
						queryData[key]=joinedValue;
					}
				} else {
					if(key=='gradeOne'){
						queryData.gradeOneCodeStr = value;
					}else{
						queryData[key]=value;
					}
				}
			});
		}
       	let nodeValue = '${node}'.trim();
       	var nodeCombobox = $('#node').combobox();
		if (nodeValue !== '') {
        	let parentParam = $store.get('parentDataCountIndex');
        	$.extend(queryData, parentParam);
        	if('${node}' == '210'){
            	queryData.node = 2;
            	queryData.overDays = 10;
            	nodeCombobox.combobox('setValue', 2);
            }else{
            	queryData.node = '${node}';
            	nodeCombobox.combobox('setValue', '${node}');
            }
        };
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
        
        $('#type').combobox({
			onChange: function(newValue,oldValue){
			    $("#types").val(newValue);
			}
		});
		
		$('#tagSel').combobox({
			onChange: function(newValue,oldValue){
			    $("#tags").val(newValue);
			}
		});
		
		$('#severityLevel').combobox({
			onChange: function(newValue,oldValue){
			    $("#severityLevels").val(newValue);
			}
		});
		
		$('#eventLevel').combobox({
			onChange: function(newValue,oldValue){
			    $("#eventLevels").val(newValue);
			}
		});
		if('${eventLevel}'){
			let elVal = '${eventLevel}';
			let values = elVal.split(',').map(item => item.trim());
			try {
				$('#eventLevel').combobox('setValues', values);
			} catch (e) {
				console.log(e);
			};
		}else{
			$("#eventLevel").combobox("setValues", []);
		}


		 // 一级分类
        $('#gradeOne').combobox({
            onChange: function(newValue,oldValue){
                let sub = $('#subspecialty').combobox('getValue');
                $('#gradeOneCodeStr').val(newValue);
                var paramDict = {type : 'grade_two_a',gradeOneArr:newValue};
                $ajax.postSync('${base}/ui/amcs/dict/getEventQueryAeDict',paramDict,false,false).done(function (rst) {
                    $('.gradeTwoA').combobox('loadData',rst);
                });
            }
        });
		// 亚专科
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
		
        $("#type").combobox('setValues',[]);
        if('${type}' == '1') {
        	//医疗类不良事件
        	$('#type').combobox('setValues', [1,9]);
        	$("#types").val('1,9');
        	queryData.types = '1,9';
        }else if('${type}' == '2') {
        	//护理类不良事件
        	$('#type').combobox('setValue', '2');
        	$("#types").val(2);
        	queryData.type = 2;
    	}else if('${type}' == '3') {
    		//院感类不良事件
    		$('#type').combobox('setValue', '3');
    		$("#types").val(3);
    		queryData.type = 3;
    	};
		
		$(".cont-grid").on("click",".s-op-increase",function(e){
            var ix = $(this).attr('rel');
            var row = $("#gridBox").datagrid('getRows')[ix];
            //判断进展上报的时间是否跟上次上报是同一天
            let lastDate = row.lastReportDate;
            if(isToday(lastDate)){
                $pop.msg("一天内只可对同一事件上报一次进展，请勿重复上报！");
                return;
            };
            
            if(row.id != ''){
                let title = row.eventTypeName;
                let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage?eventCode='+ row.eventCode +'&id=' + row.id;
                url += '&isIncrease=true'+ '&node=' + row.node + '&showLast=true';
                $pop.iframePop({
                    title:title,
                    content:url,
                    end:function(){
                        var param = $('#sbox').sovals();
                        param.rows = $('#gridBox').datagrid('options').pageSize;
                        param.page = $('#gridBox').datagrid('options').pageNumber;
                        $('#gridBox').datagrid('reload',param);
                    }
                });
            }
		
		});

		$(".cont-grid").on("click",".s-op-fanruan",function(e){
			var ix = $(this).attr('rel');
			var row = $("#gridBox").datagrid('getRows')[ix];
			if(row.id != ''){
				$pop.newTabWindow(row.eventName, '${base}/fr?url=ae_unplReoperation_report.cpt&common&op=view&basicid=' + row.id + '&prev_id=' + row.prevId);
			}
		});

		$(".cont-grid").on("click",".s-op-audit", function(e){
            var ix = $(this).attr('rel');
            var row = $("#gridBox").datagrid('getRows')[ix];
			if(row.id != ''){
				let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node +'&page_type=${pageType}';
				urlSuffix += '&is_review=false&showLast=true';
				let title = row.eventTypeName;
				let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
				$pop.iframePop({
                    title:title,
                    content:url,
                    end:function(){
                        var param = $('#sbox').sovals();
                        param.rows = $('#gridBox').datagrid('options').pageSize;
                        param.page = $('#gridBox').datagrid('options').pageNumber;
                        $('#gridBox').datagrid('reload',param);
                    }
                });
			}

		});

		$(".cont-grid").on("click", ".s-op", function(e) {
			var status = $(this).data("status");
			var ix = $(this).attr('rel');
			var row = $("#gridBox").datagrid('getRows')[ix];
			switch (status + "") {
				case "1":
					e.preventDefault();
					e.stopPropagation();
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node +'&page_type=${pageType}';
					urlSuffix += '&is_review=false&showLast=true';
					if(row.id != ''){
						let title = row.eventTypeName;
						let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
						$pop.iframePop({
							title:title,
							content:url,
							end:function(){
								$('#gridBox').datagrid('getPanel').find('tr[datagrid-row-index="' + ix + '"]').css('background-color', '#ABEBC6');
							}
						});
					}
					break;
				case "2":
					$ajax.post('${base}/ui/service/biz/amcs/adverse/common/groupView', {"basicId": row.id}, { jsonData: false, tip: false }).done(function (rst) {
						if (rst.code==='200'||rst.code==='201') {
							$('#gridBox').datagrid('getPanel').find('tr[datagrid-row-index="' + ix + '"]').css('background-color', '#ABEBC6');
						}else{
							$pop.warning('已读标记异常!');
						};
					});
					break;
				case "3":
					if (row.focusId) {
						performAjaxRequest('${base}/ui/service/biz/amcs/adverse/common/delFocus', { "focusId": row.focusId })
								.then(function (rst) {
									row.focusId = null;
									handleResult(rst, ix, row);
								})
								.catch(function (error) {
									console.error("Error:", error);
								});
					} else {
						performAjaxRequest('${base}/ui/service/biz/amcs/adverse/common/focus', { "basicId": row.id })
								.then(function (rst) {
									row.focusId = rst.focusId;
									handleResult(rst, ix ,row);
								})
								.catch(function (error) {
									console.error("Error:", error);
								});
					}
					break;
				default:
					break;
			}



		});
		
		let toolsVariable = [[]];
		let pageSize = 20;
		if(${pageType} == 4){
			toolsVariable = [[ {iconCls: 'save', text: '保存',click:function (){ saveGrid('保存修改');}}]];
			pageSize = 100;
		};

    	$grid.newGrid("#gridBox",{
    	 	pagination: true,
            fitColumns: false,
            pageSize: pageSize,
			tools: toolsVariable,
	    	columns:[[
				{title: "操作", field: "op", width: 100, formatter: function (v, row, index) {
				        let opStr = '';
				        if(${pageType} == 1){
				          opStr = '<span class="s-op s-op-view icon-eye"  title="查看" rel="' + index + '"></span>&nbsp;&nbsp;&nbsp;';
				          opStr += '<span class="s-op s-op-modify icon-edit" title="修改"  rel="' + index + '"></span>';
				          if (row.node == 0) {
				          	opStr += '&nbsp;&nbsp;&nbsp;<span class="s-op s-op-del icon-del" title="删除"  rel="' + index + '"></span>';
				          }
				          if (row.node >= 2) {
				          	opStr += '&nbsp;&nbsp;&nbsp;<span class="s-op s-op-increase icon-plus_sign" title="进展上报" rel="' + index + '"></span>';
				          }
						  <!--非计划上报显示打印按钮-->
							if (Number(row.eventCode) === 109) {
								opStr += '&nbsp;&nbsp;&nbsp;<span class="s-op s-op-fanruan icon-printer" title="打印" rel="' + index + '"></span>';
							}
				        }else if(${pageType} == 4){
				        	opStr = '<span class="s-op s-op-review icon-group" data-status="1" title="查阅" rel="' + index + '"></span>&nbsp;&nbsp;&nbsp;';
							opStr += '<span class="s-op s-op-read icon-bookmark" title="已读" data-status="2"  rel="' + index + '"></span>&nbsp;&nbsp;&nbsp;';
							opStr += '<span class="s-op s-op-focus icon-heart2 '+setUnuse(row.focusId)+'" data-status="3"  title="重点事件" rel="' + index + '"></span>';
				        }else{
				        	if("${isReview}" == "true"){
				        		opStr = '<span class="s-op s-op-view icon-eye" title="查看" rel="' + index + '"></span>';
				        	}else{
				        		opStr = '<span class="s-op s-op-audit icon-yinzng1" title="审核" rel="' + index + '"></span>';
				        	}
				         	
				        }
						return opStr;
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
		        {title:'最后一次上报时间',field:'lastReportDate',width:80,hidden:true},
                {title:'亚专科编码',field:'subspecialtyCode',width:80,editor:{type:'readonly'},hidden:true},
                {title:'事件分类一级编码',field:'gradeOneCode',width:80,editor:{type:'readonly'},hidden:true},
				{title:'严重程度',field:'severityLevel',width:80,editor:{type:'readonly'},hidden:true},
                {title:'事件分类二级I编码',field:'gradeTwoACode',width:80,editor:{type:'readonly'},hidden:true},
                {title:'事件分类二级II编码',field:'gradeTwoBCode',width:80,editor:{type:'readonly'},hidden:true},
				{title:'重点事件ID',field:'focusId',width:80,editor:{type:'readonly'},hidden:true},
		        {title:'eventCode',field:'eventCode',width:80,hidden:true},
		        {title:'eventId',field:'eventId',width:80,hidden:true},
				{title:'prevId',field:'prevId',width:80,hidden:true},
		        {title:'node',field:'node',width:80,hidden:true},
		        {title:'页面地址', field:'eventUrl', width:120,hidden:true},
		        {title:'上报时间', field:'createDate', width:80, format:'yyyy-MM-dd'},
				{title:'发生时间',field:'eventDate',width:80,format:'yyyy-MM-dd',editor:{type:'my97',options:{opt:{dateFmt: 'yyyy-MM-dd', maxDate:'${sysdate}'},validType:["commonDate['请输入正确的日期']","maxDate['now']"]}}},
				{title:'事件名称', field:'eventName', width:180, editor:{type:'textbox', options:{required:true,missingMessage:'请填写事件名称'}}},
		        {title:'省区', field:'hospParent', hidden: false, width:80},
		        {title:'医院名称', field:'hospName', width:150},
		        {title:'涉及科室/人员类型', field:'tags', hidden: true, width:120,
		        	formatter: function(value,row,index){
                        let tags = "";
                        if(!$.isEmptyObject(value)){
                            tagData.forEach(function(key){
                                if(value.indexOf(key.valueCode) >-1){
                                    tags += "," + key.valueDesc;
                                }
                            });
                            if(!$.isEmptyObject(tags)){
                                tags = tags.substr(1);
                            }
                        }
						return tags;
					},
            	    editor:{
                        type: 'combobox', options: {
                        	multiple:true,
                            valueField: 'valueCode',
                            textField: 'valueDesc',
                            limitToList: true,
                            data: tagData
                        }
                    }
                },
				{title:'严重程度', field:'severityLevelName', hidden: false, width:120,
					formatter: function(value, row, index) {
						let severityLevel = row.severityLevel;
						let severityLevelName = severityData.find(item => item.valueCode == severityLevel);
						return severityLevelName ? severityLevelName.valueDesc : value;
					},
					editor:{
						type: 'combobox', options: {
							multiple:false,
							valueField: 'valueDesc',
							textField: 'valueDesc',
							limitToList: true,
							required: true,
							data: severityData,
							onSelect: function (row) {
								let severityLevelCode = row.valueCode;
								if (severityLevelCode == 1) {
									//A级
									$e.setCellVals({'eventLevel':'IV级（隐患事件）'});
								} else if(severityLevelCode == 2||severityLevelCode == 3||severityLevelCode == 4) {
									//B-D级
									$e.setCellVals({'eventLevel':'III 级（未造成后果事件）'});
								} else if(severityLevelCode == 5||severityLevelCode == 6||severityLevelCode == 7||severityLevelCode == 8) {
									//E-H级
									$e.setCellVals({'eventLevel':'II 级（不良事件）'});
								} else if(severityLevelCode == 9){
									//$("#eventLevel").val('I级（警告事件）');
								}else{
									$e.setCellVals({'eventLevel':''});
								}
								$e.setCellVals({severityLevel: severityLevelCode});
							}
						}
					}
				},
				{title:'事件分级',field:'eventLevel', width:150, editor:{type:'readonly'}},
                {title:'亚专科', field:'subspecialty', hidden: true, width:80,
                	formatter: function(value,row,index){
						if (row.user){
							return row.user.name;
						} else {
							return value;
						}
					},
            	    editor:{
                        type: 'combobox', options: {
                        	multiple:false,
                            valueField: 'valueDesc',
                            textField: 'valueDesc',
                            limitToList: true,
							required: true,
                            data: subData,
                            onSelect: function (row) {
                                $e.setCellVals({'subspecialtyCode':row.valueCode});
                            }
                        }
                    }
                },
		        {title:'事件分类一级', field:'gradeOne', hidden: false, width:120,
            	    editor:{
                        type: 'combobox', options: {
                            valueField: 'valueDesc',
                            textField: 'valueDesc',
                            limitToList: true,
							required: true,
                            data: gradeOneData,
                            onSelect: function (row) {
                                $e.setCellVals({gradeOneCode:row.valueCode});
                                let subspecialtyCode = $e.getCellVals('subspecialtyCode');
                                let filter = subspecialtyCode + ',' + row.valueCode;         
                                let url = base + '/ui/amcs/dict/getAeDict?type=grade_two_a&filter=' + filter;
                                let $gradTwoA = $e.getCell('gradeTwoA');
                                let $gradTwoB = $e.getCell('gradeTwoB');
                                $gradTwoA.combobox('clear');
                                $gradTwoB.combobox('clear');
                                $e.setCellVals({gradeTwoACode: ''});
                                $e.setCellVals({gradeTwoBCode: ''});
                                $gradTwoA.combobox('reload', url);
                            }
                        }
                    }
                },
                {title:'事件分类二级I', field:'gradeTwoA', hidden: true, width:150,
            	    editor:{
                        type: 'combobox', options: {
                            valueField: 'valueDesc',
                            textField: 'valueDesc',
                            limitToList: true,
                            onSelect: function (row) {
                                $e.setCellVals({gradeTwoACode:row.valueCode});
                                let url = base + '/ui/amcs/dict/getAeDict?type=grade_two_b&filter=' + row.valueCode;
                                let $gradTwoB = $e.getCell('gradeTwoB');
                                $gradTwoB.combobox('reload', url);
                            }
                        }
                    }
                },
                {title:'事件分类二级II', field:'gradeTwoB', hidden: true, width:150,
            	    editor:{
                        type: 'combobox', options: {
                            valueField: 'valueDesc',
                            textField: 'valueDesc',
                            limitToList: true,
                            clearIcon:true,
                            onSelect: function (row) {
                                $e.setCellVals({gradeTwoBCode:row.valueCode});
                            }
                        }
                    }
                },
		        {title:'患者姓名', field:'patientName', hidden: false, width:80},
		        {title:'事件类型', field:'eventTypeName', width:120},
		        {title:'上报人', field:'creatorName', width:80},
		        {title:'所在节点',field:'nodeName',width:110,formatter:function (v,row,index){
		        	if(row.isArchived){
		        		return "上报未审核(已归档)"
					}else{
		        		return v
					}
					}},
		        {title:'上报次数',field:'maxReportTimes',width:80},
		        {title: '是否完结',field: 'finishSign',width: 80 ,formatter: function (v,row,index){
					return ['<span class="red">否</span>','<span class="green">是</span>'][v];
                }},
				{title: '是否高风险', field: 'isHighRisk', width: 80, formatter: function (v, row, index) {
						if (${pageType} == 4) {
							return '<input type="radio" name="isHighRisk_' + index + '" value="0" ' + (v === 0 ? 'checked' : '') + '> 否 ' +
									'<input type="radio" name="isHighRisk_' + index + '" value="1" ' + (v === 1 ? 'checked' : '') + '> 是';
						} else {
							return '<input type="radio" name="isHighRisk_' + index + '" value="0" ' + (v === 0 ? 'checked' : '') + ' disabled> 否 ' +
									'<input type="radio" name="isHighRisk_' + index + '" value="1" ' + (v === 1 ? 'checked' : '') + ' disabled> 是';
						}
					}},
                {title:'状态',field:'status',width:80, formatter: function (v, row, index) {
                            if (v == 2) {
                                return "取消";
                            } else {
                                return "正常";
                            }
                        }
                    },
		        {title:'累计赔偿金额',field:'totalCompensationAmount',width:80, hidden: true},
		        {title:'累计减免金额',field:'totalDeductionAmount',width:80, hidden: true},
		        {title: '集团查阅',field: 'isGroupReview',width: 80 ,formatter: function (v,row,index){
                    return ['<span class="red">未查阅</span>','<span class="green">已查阅</span>'][v];
                }},
                {title:'省区审核通过时间', field:'pAuditDate', width:80, format:'yyyy-MM-dd'},
		    ]],
		    rowStyler : function(index, row) {
                if(row.isGroupReview == 1 && '${pageType}' == '4'){
                    return "background-color: #ABEBC6";
                }

                if(row.isArchived){
                	return "background-color: darkgrey";
				}

				if(row.isEhrDeptReview == 1){
					return "background-color: #ABEBC6";
				}

            },
			onClickRow: function(index, row) {
				let severityLevel = row.severityLevel;
				let severityLevelName = severityData.find(item => item.valueCode == severityLevel);
				$e.setCellVals({severityLevelName: severityLevelName ? severityLevelName.valueDesc : ''});


			},
			onClickCell : function (index, field, value){
                $e.editRow({
                    index :index,
                    cellField : field,
                    focusField :'itemName',
                    rowCanEdit : function (row){
                        if(${pageType} == 4) {
                            return true;
                        } else {
                            return false;
                        }
                    },
					onEndEdit : function (index) {
						if(rowIndexArr.indexOf(index) == -1){
							rowIndexArr.push(index);
						}
					}
                });
            },
		    onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {
				// 使用 delegate 方法附加事件处理程序
				$('#gridBox').datagrid('getPanel').delegate('input[name^="isHighRisk_"]', 'change', function () {
					const index = $(this).attr('name').split('_')[1];
					const value = $(this).val();
					var row = $('#gridBox').datagrid('getRows')[index];
					row.isHighRisk = parseInt(value, 10);
					rowIndexArr.push(index);
				});
                if(${pageType} == 4) {
                	$("#gridBox").datagrid("showColumn", "tags");
                    $("#gridBox").datagrid("showColumn", "subspecialty");
                    $("#gridBox").datagrid("showColumn", "gradeTwoA");
                    $("#gridBox").datagrid("showColumn", "gradeTwoB");
                }else if(${pageType} == 3){
                	$("#gridBox").datagrid("showColumn", "tags");
                };
                if(${pageType} != 1){
                	$("#gridBox").datagrid("showColumn", "totalCompensationAmount");
                    $("#gridBox").datagrid("showColumn", "totalDeductionAmount");
                }
                
		    	$('.s-op-modify').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node +'&page_type=${pageType}'+'&modify=true';
					
					if(row.id != ''){
					    let title = row.eventTypeName;
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
				$('.s-op-del').click(function () {
					let ix = $(this).attr('rel');
					let row = data.rows[ix];
					$ajax.post({ url: `${base}/ui/service/biz/amcs/adverse/common/delById`, tip: '是否确认删除当前事件?', data: { "id": row.id } }).done((res) => {
						console.log(res);
						$pop.msg('删除成功！');
						reloadGrid();
					});
				
				});
		    	$('.s-op-view').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node +'&page_type=${pageType}';
					urlSuffix += '&is_review=true&showLast=true';
					
					if(row.id != ''){
					    let title = row.eventTypeName;
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
      		},
      		queryParams : queryData,
      		url: '${base}/ui/service/biz/amcs/adverse/common/findList'
  		});



		function performAjaxRequest(url, postData) {
			return new Promise(function (resolve, reject) {
				$ajax.post(url, postData, { jsonData: false, tip: false }).done(function (rst) {
					resolve(rst);
				}).fail(function (error) {
					reject(error);
				});
			});
		}

		function handleResult(result, ix, row) {
			if (result.code === '200' || result.code === '201') {
				$('#gridBox').datagrid('updateRow', {
					index: ix,
					row: row
				});
				$('#gridBox').datagrid('refreshRow', ix);
			} else {
				$pop.warning('标记异常!');
			}
		}
  		
  		function reloadGrid() {
  			let param = $('#sbox').sovals();
            param.rows = $('#gridBox').datagrid('options').pageSize;
            param.page = $('#gridBox').datagrid('options').pageNumber;
            $('#gridBox').datagrid('reload',param);
  		};

		function setUnuse(status) {
			var unuseStyle = '';
			if(status) {
				unuseStyle = 's-op-unuse';
			}
			return unuseStyle;
		};
  		
  		function isToday(someDateStr){
  			// 获取当前时间
			let currentDate = new Date();
			let currentYear = currentDate.getFullYear();
			let currentMonth = currentDate.getMonth() + 1;
			let currentDay = currentDate.getDate();
			
			let someDate = new Date(someDateStr);
			let someYear = someDate.getFullYear();
			let someMonth = someDate.getMonth() + 1;
			let someDay = someDate.getDate();
			
			if (someYear === currentYear && someMonth === currentMonth && someDay === currentDay) {
			    return true;
			 } else {
			    return false;
			 }		
  		};

		function saveGrid() {
			if(rowIndexArr.length == 0){
				$pop.msg("找不到需要保存的信息！");
				return;
			}else{
			    let continueFlag = true;
				let rows = $("#gridBox").datagrid('getRows');
				let selRows = new Array();
				$.each(rowIndexArr, function(index,value){
					if(rows[value] && $.isEmptyObject(rows[value].gradeTwoA)){
						$pop.warning('无法保存，事件分类二级I未选择!');
						continueFlag = false;
					    return;
					}
					selRows.push(rows[value]);
				});
				if(continueFlag) {
					$ajax.post('${base}/ui/service/biz/amcs/adverse/common/saveBasicList',JSON.stringify(selRows),false,true).done(function (rst) {
						if (rst.code==='200'||rst.code==='201') {
							rowIndexArr = [];
							$pop.msg('保存成功！');
						}else{
							$pop.warning('保存异常!');
						};
					});
				}
			}
		};

		function reloadType() {
			[#if empType==1]
					$ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
						$('.province').combobox('loadData', rst);
					});
					$ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
						$('.hosp').combobox('loadData', rst);
					});
					$('.province').combobox({
						onChange: function(v){
							//let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;

							$('.hosp').combobox('reload', url);
						}
					});
				[#elseif empType==2]
					var paramInsi = {insiId:'${insiId}'};
					$ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
						$('.hosp').combobox('loadData', rst);
					});
				[/#if]
				
			$ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',null,false,false).done(function (rst) {
				$('#eventType').combobox('loadData', rst.rows);
			});
		};
    
    });



    
	

    
</script>
</html>
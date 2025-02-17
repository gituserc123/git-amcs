<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>药品不良反应事件 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input class="txt" name="reportTimes" value="1" type="hidden" />
            <input class="txt" name="isPrimary" value="1" type="hidden" />
			[#if empType==1]
			<label class="lab-inline">省区：</label>
			<select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true">
				[/#if]
			</select>
			[#if empType==1 || empType==2]
				<label class="lab-inline">医院：</label>
				<select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
				</select>
			[/#if]
            <label class="lab-inline">上报日期：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="reportGroup" data-opt="{val:'',auto:false,opens:'center'}" />
			<label class="lab-inline">发生日期：</label>
			<input type="text" class="txt so-rangeDate txt-validate" name="occurDate" data-opt="{val:'',auto:false,opens:'center'}"/>
			<input class="txt" id="typeLevel" name="typeLevel" type="hidden" />
			<label class="lab-inline">不良反应类型：</label>
	        <select class="drop drop-sl-v easyui-combobox w-120" id="typeSelect" data-options="multiple:true, clearIcon:true,editable:false,valueField:'valueCode',textField:'valueDesc'">
        	</select>
			<input class="txt" id="adverseResults" name="adverseResults" type="hidden" />
            <label class="lab-inline">不良反应结果：</label>
            <select class="drop easyui-combobox w-100" id="adverseResultsSelect"  data-options="multiple:true, clearIcon:true,editable:false,valueField:'valueCode',textField:'valueDesc'" >
			</select>
            <label class="lab-inline">科室：</label>
            <input type="text" class="txt inline" id="department"  name="department" value="">
			<label class="lab-inline">怀疑药品名称：</label>
			<input type="text" class="txt inline" id="commonName"  name="commonName" value="">
			<label class="lab-inline">怀疑药品生成厂家：</label>
			<input type="text" class="txt inline" id="manufacturer"  name="manufacturer" value="">
			<label class="lab-inline">状态：</label>
			<select class="drop w-100"  id="reactStatus" name="reactStatus">
				<option value="" ></option>
				<option value="1">正常</option>
				<option value="2">取消</option>
			</select>
			<label class="lab-inline">上报人姓名：</label>
			<input type="text" class="txt inline" id="creatorName"  name="creatorName" value="">
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
			<button type="button" class="btn btn-small btn-primary s-export">导 出</button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(["lodash", "easygridEdit", "pub"], function (_, $e) {
		this.reloadReactData();

		// 不良反应类型
		$('#typeSelect').combobox({
			onChange: function(newValue,oldValue){
				$('#typeLevel').val(newValue);
			}
		});
		$('#typeSelect').combobox('loadData', JSON.parse('[{"valueCode":1,"valueDesc":"新的"},{"valueCode":2,"valueDesc":"严重"},{"valueCode":3,"valueDesc":"一般"}]'));

		// 不良反应结果
		$('#adverseResultsSelect').combobox({
			onChange: function(newValue,oldValue){
				$('#adverseResults').val(newValue);
			}
		});
		$('#adverseResultsSelect').combobox('loadData', JSON.parse('[{"valueCode":1,"valueDesc":"治愈"},{"valueCode":2,"valueDesc":"好转"},{"valueCode":3,"valueDesc":"未好转"},{"valueCode":4,"valueDesc":"有后遗症"},{"valueCode":5,"valueDesc":"死亡"}]'));

    	$grid.newGrid("#gridBox",{
			pagination: true,
			fitColumns: false,
			tools: [
				[
					[#if empType==3]
					{
						iconCls: 'plus',//图标
						text: '上报药品不良反应/事件',//文字
					}
					[/#if]
				]
			],
	    	columns:[[
				{title: "操作", field: "op", width: 100, formatter: function (v, row, index) {
				        let opStr = '';
						[#if empType==3]
						opStr += '<span class="s-op s-op-edit icon-edit" title="编辑" rel="' + index + '"></span>&nbsp;&nbsp;';
						[/#if]
						[#if reactStatus==1]
						if(row.reactStatus == '1'){
							opStr += '<span class="s-op s-op-cancel icon-del" title="是否取消" rel="' + index + '"></span>&nbsp;&nbsp;';
						};
						[/#if]
						opStr += '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
						opStr = opStr + '&nbsp;&nbsp;<span class="s-op s-op-fanruan icon-printer" title="报表" rel="' + index + '"></span>';
						return opStr;
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
				{title:'省区', field:'hospParent', hidden: false, width:80},
		        {title:'医院名称',field:'hospName',width:80},
		        {title:'科室',field:'department',width:80},
				{title:'不良反应名称',field:'adverseReactionsNameDesc',width:80},
		        {title:'不良反应类型',field:'typeLevel',width:80,formatter: function (v, row, index) {if(v=='1'){return '新的';}else if(v=='2'){return '严重';}else{return '一般';}}},
				{title:'不良反应发生时间',field:'occurDate',width:100,format:'yyyy-MM-dd'},
				{title:'是否构成纠纷',field:'dispute',width:80,formatter: function (v, row, index) {if(v=='1'){return '是';}else{return '否';}}},
		        {title:'报告国家日期', field:'reportingCountries', width:100,formatter: function (v, row, index) {return v.substring(0,10)}},
				{title:'是否上报国家', field:'reportingCountries1', width:80,formatter: function (v, row, index) {if(row.reportingCountries){return '是';}else{return '否';}}},
                {title:'上报集团日期', field:'reportGroup', width:100,formatter: function (v, row, index) {return v.substring(0,10)}},
		        {title:'电话', field:'telephone', width:100},
				{title:'病历号/门诊号',field:'medicalRecordNumber',width:80},
				{title:'患者姓名',field:'patientName',width:80},
				{title:'性别',field:'sex',width:40},
				{title:'体重',field:'bodyWeight',width:40},
				{title:'出生日期',field:'birthDate',width:80},
		        {title:'事件的结果', field:'adverseResults', width:80,formatter: function (v, row, index) {if(v=='1'){return '治愈';}else if(v=='2'){return '好转';}else if(v=='3'){return '未好转';}else if(v=='4'){return '有后遗症';}else{return '死亡';}}},
				{title:'表现', field:'performance', width:120,formatter: function (v, row, index) {
					var pStr = '';
					if(row.performance){pStr = pStr + row.performance + ','};
					if(row.causeOfDeath){pStr = pStr + row.causeOfDeath + ','};
					if(row.deathDate){pStr = pStr + row.deathDate.substring(0,10)};
					return pStr;
				}},
				{title:'国内有无类似不良反应',field:'similarInChina',width:80,formatter: function (v, row, index) {if(v=='1'){return '有';}else if(v=='2'){return '无';}else{return '不详';}}},
				{title:'国外有无类似不良反应',field:'similarAbroad',width:80,formatter: function (v, row, index) {if(v=='1'){return '有';}else if(v=='2'){return '无';}else{return '不详';}}},
				{title:'医院评价', field:'hospitalEvaluation', width:80,formatter: function (v, row, index) {if(v=='1'){return '肯定';}else if(v=='2'){return '很可能';}else if(v=='3'){return '可能';}else if(v=='4'){return '可能无关';}else if(v=='5'){return '待评价';}else{return '无法评价';}}},
				{title:'报告人评价', field:'reportEvaluation', width:80,formatter: function (v, row, index) {if(v=='1'){return '肯定';}else if(v=='2'){return '很可能';}else if(v=='3'){return '可能';}else if(v=='4'){return '可能无关';}else if(v=='5'){return '待评价';}else{return '无法评价';}}},
				{title:'过程描述', field:'adverseReactionsDescription', width:100},
				{title:'怀疑药品',field:'smCommonNames',width:180},
				{title:'怀疑药品厂家',field:'smManufacturers',width:280},
				{title:'并用药品',field:'amCommonNames',width:180},
				{title:'并用药品厂家',field:'amManufacturers',width:280},
		        {title:'上报人', field:'creatorName', width:80},
		    ]],
		    rowStyler : function(index, row) {

            },
		    onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {

      		},
      		url: '${base}/ui/amcs/adverse/drugreaction/findList'
  		});

		$(".s-tool").click(function ($e) {
			$pop.iframePop({
				title: '新增药品不良反应基本信息',//标题
				content: '${base}/ui/amcs/adverse/drugreaction/drugReaction',//请求地址
				area: ['100%', '100%'],//窗口大小
				// postData : {mainId:row.id},//往子页面传值
				end : function(iframeSendData){
					//关闭执行函数，子页面可通过 $pop.closePop 返回参数
					$grid.load('#gridBox');
				},
				sureback : function (iframeSendData){

					//表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
				}
			},'#gridBox');
		});

		$(".cont-grid").on("click",".s-op-edit",function(e){
			var idx = $(this).attr('rel');
			var rowData = $("#gridBox").datagrid("getRows")[idx];
			console.log(rowData);
			$pop.iframePop({
				title: '编辑药品不良反应基本信息',//标题
				content: '${base}/ui/amcs/adverse/drugreaction/drugReaction?reactId=' + rowData.id,//请求地址
				area: ['100%', '100%'],//窗口大小
				// postData : {mainId:row.id},//往子页面传值
				end : function(iframeSendData){
					//关闭执行函数，子页面可通过 $pop.closePop 返回参数
					$grid.load('#gridBox');
				},
				sureback : function (iframeSendData){

					//表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
				}
			},'#gridBox');

		});

		$(".cont-grid").on("click",".s-op-review",function(e){
			var idx = $(this).attr('rel');
			var rowData = $("#gridBox").datagrid("getRows")[idx];
			console.log(rowData);
			$pop.iframePop({
				title: '查看药品不良反应基本信息',//标题
				content: '${base}/ui/amcs/adverse/drugreaction/drugReaction?opr=view&reactId=' + rowData.id,//请求地址
				area: ['100%', '100%'],//窗口大小
				// postData : {mainId:row.id},//往子页面传值
				end : function(iframeSendData){
					//关闭执行函数，子页面可通过 $pop.closePop 返回参数
					$grid.load('#gridBox');
				},
				sureback : function (iframeSendData){

					//表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
				}
			},'#gridBox');

		});

		$(".cont-grid").on("click",".s-op-fanruan",function (e) {
			var idx = $(this).attr('rel');
			var row = $("#gridBox").datagrid("getRows")[idx];
			if(row.id != ''){
				$pop.newTabWindow('药品不良反应表单', '${base}/fr?url=ae_drugReaction_report.cpt&common&op=view&basicid=' + row.id);
			}
		});

		$(".s-export").click(function ($e) {
			var formData = $('#sbox').sovals();
			formData.reportGroup=null;
			$pop.confirm('您确认想要导出记录吗？',function(r){
				if (r){
					//window.location.href="${base}/ui/amcs/adverse/event/query/exportExcel?paramJson="+encodeURIComponent(JSON.stringify(formData));
					var url = '${base}/ui/amcs/adverse/drugreaction/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
					downloadExcelProcess(url);
				}
			});
		});

		$(".cont-grid").on("click",".s-op-cancel",function(e){
			var idx = $(this).attr('rel');
			var row = $("#gridBox").datagrid("getRows")[idx];
			$pop.confirm('您确认想要取消吗？',function(r){
				if (r){
					$ajax.postSync('${base}/ui/amcs/adverse/drugreaction/updateReactStatus?reactId='+row.id,null,false,false).done(function (rst) {
						//$grid.load('#gridBox');
						$grid.reload('#gridBox');
					});
				}
			});
		});

    });

	function reloadReactData() {
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
		var paramInsi = {insiId:'${instId}'};
		$ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
			$('#hospId').combobox('loadData', rst);
		});
		[/#if]
	};

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
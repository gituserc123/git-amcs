<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>价格政策列表 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
			[#if empType==1]
			<label class="lab-inline">省区：</label>
			<select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true">
			</select>
			[/#if]
			[#if empType==1 || empType==2]
				<label class="lab-inline">医院：</label>
				<select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
				</select>
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
			<button type="button" class="btn btn-small btn-primary s-export">导 出</button>
			[/#if]
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
		this.reloadEventData();

    	$grid.newGrid("#gridBox",{
			pagination: true,
			fitColumns: false,
	    	columns:[[
				{title: "操作", field: "op", width: 75, formatter: function (v, row, index) {
				        let opStr = '';
						opStr += '<span class="s-op s-op-review icon-eye" title="查看" rel="' + index + '"></span>';
						return opStr;
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
				{title:'省区', field:'hospParent', hidden: false, width:80},
		        {title:'医院名称',field:'hospName',width:80},
				{title:'医院等级', field:'ehrLevel', hidden: false, width:80},
				{title:'医保协议定价等级',field:'ybxyDjdj',width:100},
		        {title:'国际医疗价',field:'gjYlj',width:80,formatter: function (v, row, index) {if(v=='1'){return '有';}else if(v=='2'){return '无';}else{return '';}}},
				{title:'VIP价格',field:'vipJg',width:80,formatter: function (v, row, index) {if(v=='1'){return '有';}else if(v=='2'){return '无';}else{return '';}}},
				{title:'自主定价',field:'zzDj',width:80,formatter: function (v, row, index) {if(v=='1'){return '有';}else if(v=='2'){return '无';}else{return '';}}},
				{title:'医保支付价',field:'ybZfj',width:80,formatter: function (v, row, index) {if(v=='1'){return '有';}else if(v=='2'){return '无';}else{return '';}}},
				{title:'诊疗项目执行公立医院医改价',field:'zlxmZxGlyyygj',width:180,formatter: function (v, row, index) {if(v=='1'){return '是';}else if(v=='2'){return '否';}else{return '';}}},
				{title:'药品采购价零加成',field:'ypCgjljc',width:80,formatter: function (v, row, index) {if(v=='1'){return '是';}else if(v=='2'){return '否';}else{return '';}}},
				{title:'药品中标价零加成',field:'ypZbjljc',width:80,formatter: function (v, row, index) {if(v=='1'){return '是';}else if(v=='2'){return '否';}else{return '';}}},
				{title:'药品加价政策',field:'ypJjzc',width:100},
				{title:'耗材采购价零加成',field:'hcCgjljc',width:80,formatter: function (v, row, index) {if(v=='1'){return '是';}else if(v=='2'){return '否';}else{return '';}}},
				{title:'耗材中标价零加成',field:'hcZbjljc',width:80,formatter: function (v, row, index) {if(v=='1'){return '是';}else if(v=='2'){return '否';}else{return '';}}},
				{title:'耗材加价政策',field:'hcJjzc',width:100},
				{title:'屈光手术执行省区指导价',field:'qgssZxsqzdj',width:80,formatter: function (v, row, index) {if(v=='1'){return '符合省区指导价区间';}else if(v=='2'){return '部分项目低于省区全年最大优惠价格';}else{return '';}}},
				{title:'屈光手术低于指导价是因哪些促销活动',field:'qgssDyzdjSynxcxhd',width:100},
				{title:'视光诊疗项目执行集团指导价',field:'sgzlxmZxjtzdj',width:80,formatter: function (v, row, index) {if(v=='1'){return '基本医疗服务项目执行医保协议价，自费项目执行或高于集团指导价';}else if(v=='2'){return '基本医疗服务项目执行医保协议价，部分自费项目价格低于集团指导价';}else if(v=='3'){return '视光相关诊疗项目自主定价，部分项目低于集团指导价';}else if(v=='4'){return '视光相关诊疗项目自主定价';}else{return '';}}},
				{title:'视光项目低于指导价因哪些促销活动',field:'sgzlxmDyzdjSynxcxhd',width:100},
				{title:'当地医保三大目录中今年新增眼科相关收费项目',field:'ddybsdmlzJnxzykXgsfxm',width:200},
		    ]],
		    rowStyler : function(index, row) {

            },
		    onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {

      		},
      		url: '${base}/ui/service/biz/amcs/fin/finInsPricePolicy/findList'
  		});

		$(".cont-grid").on("click",".s-op-review",function(e){
			var idx = $(this).attr('rel');
			var rowData = $("#gridBox").datagrid("getRows")[idx];
			$pop.iframePop({
				title: '查看价格政策信息',//标题
				content: '${base}/ui/service/biz/amcs/fin/finInsPricePolicy/pricePolicyInfo?id=' + rowData.id,//请求地址
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

		$(".s-export").click(function ($e) {
			var formData = $('#sbox').sovals();
			$pop.confirm('您确认想要导出记录吗？',function(r){
				if (r){
					var url = '${base}/ui/service/biz/amcs/fin/finInsPricePolicy/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
					downloadExcelProcess(url);
				}
			});
		});

    });

	[#include "/WEB-INF/views/amcs/fin/business/subReport/finExport_js.ftl"]

	function reloadEventData() {

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

	};


</script>
</html>
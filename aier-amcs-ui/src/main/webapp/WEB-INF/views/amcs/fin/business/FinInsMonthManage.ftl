<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>医院填报月度主表管理 - 爱尔医院</title>
	[#include '/WEB-INF/views/common/include_resources.ftl']
	<style>
		.chat-item {
			margin-left: 15px;
			margin-bottom: 10px;
			padding: 10px;
			border: 1px solid #ccc;
		}
		.chat-content {
			font-size: 16px;
			margin-bottom: 5px;
		}
		.chat-creator, .chat-create-time {
			font-size: 12px;
			color: #888;
		}
	</style>
</head>
<body>
<div class="cont-grid">
	<div id="gridBox"></div>
</div>
</body>
[#include '/WEB-INF/views/common/include_js.ftl']
<script id="formTem" type="text/html">
	<form id="infoForm" class="soform form-validate pad-t20 pad-r20 form-enter"
		  data-opt="{beforeCallback:'submitEditForm'}" autocomplete="off">

		<div id="chat-history"></div>
		<div class="row">

		</div>
		<p class="row-btn center">
[#--			<input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>--]
			<input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
		</p>
	</form>
</script>
<script id="explanation" type="text/html">
	<div class="mar-10" style="font-size: 18px">
		<h1>填报说明</h1>
		<p>1、首次填报或有新增医保统筹区域，务必先设置好“医院医保类型”，填写“统筹区”和“医保类型”后，再行填报；</p>
		<p>
			2、按主表和不同结算政策分表填写本医院目前存在的不同医保结算政策情况；不同结算政策分表在“住院结算政策”中选择，提交主表后可点亮填写，如无相应医保结算政策不需选择与填写；</p>
		<p>3、注意填报数据的单位“万元”“元”，数据需保留小数点后2位；需要填写原因的项目不能空白；</p>
		<p>
			4、已经执行DIP或DRG医保结算方式的医院请进一步填写“DIP结算方式盈亏分析表”或“DRG结算方式盈亏分析表”。这两张表和“医院医保类型”不做关联，每月只需填写一次；</p>
		<p>5、字段省区、医院简称、医院类型、医院体系、填报人等信息由系统内带出数据；</p>
		<p>6、可通过“复制”功能读取之前已提交的相似表单；</p>
		<p>7、各医院填写提交后由省区医保价格管理者审核/打回/提交至集团；</p>
		<p>8、各项目需填报的内容，请参照填报解说视频正确理解，填写完整。</p>
		<br/>
		<p style="font-weight: bold">其它填报问题，请联系集团财务医保及价格管理部</p>
	</div>
</script>
<script type="text/javascript">
	requirejs(["lodash","pub"], function (_) {
		var formPop = null;

		$grid.newGrid("#gridBox", {
			tools: [
				[{
					iconCls: "plus_sign",
					text: "初始化月报",
					title: "初始化月报",
					click: function () {
						var hospSettings
						$ajax.postSync("${base}/ui/service/biz/amcs/fin/finHospSetting/getList",{hospId:'${hospId}',usingSign:1}).then(
								res=>{
									hospSettings=res
								}
						)
						const extractedData = _.map(hospSettings, (item) => _.pick(item, ['poolingArea', 'typeName']));
						const result = _.map(extractedData, (o) => o.poolingArea+o.typeName).join("<br/>")
						$ajax.post("${base}/ui/service/biz/amcs/fin/finMonthMain/new",{hospId:'${hospId}'},"请确认当前有效的医保统筹区配置：<br/><b>"+result+"</b><br/><p style='color:red' >注意：每月只能初始化一次，初始化数据后不能撤回，请确认当前医保统筹区配置正确！！！</p>是否按上述配置初始化当月数据？",false).then(res => {
							$('#gridBox').datagrid('reload');
						});
					}
				},
					{
						iconCls: "shu1",
						text: "填报说明",
						title: "填报说明",
						click: function () {
							formPop = $pop.popTemForm({
								title: '填报说明',
								temId: 'explanation',
								area: ['800px', '450px'],

							});
						}
					}]
			],
			queryParams:{hospId:'${hospId}'},
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: false,
			ctrlSelect: true,
			fitColumns: false,
			pagination:false,
			columns: [
				[

					{
						title: "操作",
						field: "op",
						width: 90,
						formatter: function (v, row, index) {
							htmlStr= '<span class="s-op s-op-edit " title="进入主表" rel="' + index + '"><svg style="top:2px;position: relative" viewBox="128 128 768 768" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2767" width="14" height="14"><path d="M629.856 550.976l-160 149.76a31.904 31.904 0 0 1-45.216-1.504 31.936 31.936 0 0 1 1.504-45.216l134.496-125.888-134.016-120.32a32 32 0 1 1 42.752-47.616l160 143.616a31.904 31.904 0 0 1 0.48 47.168M512 128C300.256 128 128 300.256 128 512c0 211.712 172.256 384 384 384s384-172.288 384-384c0-211.744-172.256-384-384-384" fill="#1296db" p-id="2768"></path></svg></span>';
							if(row.status==1||row.status==9){
								htmlStr+="&nbsp;&nbsp;"+'<span class="s-op s-op-submit " title="提交审核" rel="' + index + '"><svg t="1680750808182" style="top:2px;position: relative" viewBox="128 128 768 768" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4767" width="14" height="14"><path d="M322.142929 293.741129H701.345327c27.122439 0 49.127436-22.004998 49.127437-49.127436s-22.004998-49.127436-49.127437-49.127436H322.142929c-27.122439 0-49.127436 22.004998-49.127437 49.127436s22.004998 49.127436 49.127437 49.127436zM322.142929 480.527736H701.345327c27.122439 0 49.127436-22.004998 49.127437-49.127436s-22.004998-49.127436-49.127437-49.127436H322.142929c-27.122439 0-49.127436 22.004998-49.127437 49.127436s22.004998 49.127436 49.127437 49.127436z" fill="#1296db" p-id="4768"></path><path d="M876.873563 0H147.126437C86.228886 0 36.589705 49.63918 36.589705 110.536732v802.414792c0 60.897551 49.63918 110.536732 110.536732 110.536732h157.105447c28.657671 0 52.197901-23.028486 52.197901-52.197901 0-28.657671-23.028486-52.197901-52.197901-52.197901H147.126437c-3.582209 0-6.652674-3.070465-6.652674-6.652674V110.536732c0-3.582209 3.070465-6.652674 6.652674-6.652674h729.747126c3.582209 0 6.652674 3.070465 6.652674 6.652674v802.414792c0 3.582209-3.070465 6.652674-6.652674 6.652674h-162.222888c-28.657671 0-52.197901 23.028486-52.197901 52.197901 0 28.657671 23.028486 52.197901 52.197901 52.197901h162.222888c60.897551 0 110.536732-49.63918 110.536732-110.536732V110.536732c0-60.897551-49.63918-110.536732-110.536732-110.536732z" fill="#1296db" p-id="4769"></path><path d="M671.664168 710.812594l-124.865567-124.865568c-19.446277-19.446277-50.662669-19.446277-69.597202 0l-124.865567 124.865568c-19.446277 19.446277-19.446277 50.662669 0 69.597201 19.446277 19.446277 50.662669 19.446277 69.597201 0l40.427787-40.427786v231.308346c0 27.122439 22.004998 49.127436 49.127436 49.127436s49.127436-22.004998 49.127436-49.127436v-231.308346l40.427786 40.427786c9.723138 9.723138 22.516742 14.328836 34.798601 14.328836 12.793603 0 25.075462-4.605697 34.798601-14.328836 20.469765-19.446277 20.469765-50.662669 1.023488-69.597201z" fill="#1296db" p-id="4770"></path></svg></span>'
							}
							if(row.status==9){
								htmlStr+="&nbsp;&nbsp;"+'<span class="s-op s-op-view icon-mail4" title="查看退回原因" rel="' + index + '"></span>'
							}
							return htmlStr
						}
					},
					// {title: "医院名称", field: "hospName", hidden: false, width: 300},
					{title: "年度", field: "year", hidden: false, width: 100,formatter(v,row,index){
						return row.createDate.substring(0, 4)
						}},
					{title: "月度", field: "month", hidden: false, width: 100,formatter(v,row,index){
							return row.createDate.substring(5, 7)
						}},
					{title: "ID", field: "id", hidden: true, width: 100},
						// {title: "创建者ID", field: "creator", hidden: false, width: 100},
						// {title: "修改者ID", field: "modifer", hidden: false, width: 100},
						// {title: "修改时间", field: "modifyDate", hidden: false, width: 100},
						// {title: "医院ID", field: "hospId", hidden: false, width: 100},

						{title: "状态", field: "status", hidden: false, width: 150,formatter(v,row,index){
							if(v==1){
								return "上报中"
							}else if(v==5){
									return "省区审核"
								}else if(v==6){
								return "省区审核完成"
							}else{
								return "退回"
							}
							}},
					{title: "创建者", field: "creatorName", hidden: false, width: 100},
					{title: "创建时间", field: "createDate", hidden: false, width: 150},

				]
			],
			onLoadSuccess: function (data) {
				$('.s-op-view').click(function () {
					var ix = $(this).attr('rel');
					var  row= data.rows[ix];
					var  subData={}
					$ajax.postSync('${base}/ui/service/biz/amcs/fin/finMonthMain/getCommentByAssociatedId', {associatedId:row.id},false,false).done(function (rst) {

						subData=rst
						// 获取列表容器

					});


					formPop = $pop.popTemForm({
						title: '查看沟通记录',
						temId: 'formTem',
						data: subData,
						area: ['800px', '600px'],
						onPop: function () {
							// $("#typeCode").val($("#paraType").getVal());

							var chatHistory = document.getElementById('chat-history');

							// 遍历数据并渲染成聊天记录
							for (var i = 0; i < subData.length; i++) {
								var item = subData[i];
								var chatItem = document.createElement('div');
								chatItem.className = 'chat-item';

								// 渲染聊天内容
								var content = document.createElement('div');
								content.className = 'chat-content';
								content.innerHTML = item.content;
								chatItem.appendChild(content);

								// 渲染聊天者
								var creator = document.createElement('div');
								creator.className = 'chat-creator';
								creator.innerHTML = '审核人：' + item.creatorName;
								chatItem.appendChild(creator);

								// 渲染聊天时间
								var createTime = document.createElement('div');
								createTime.className = 'chat-create-time';
								createTime.innerHTML = '时间：' + item.createdate;
								chatItem.appendChild(createTime);

								chatHistory.appendChild(chatItem);
							}
						}

					});
				});
				$('.s-op-edit').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];


					$pop.newTabWindow('主记录列表','${base}/ui/service/biz/amcs/fin/finInsMain/index?monthId='+row.id);
				});
				$('.s-op-submit').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];

					$ajax.post("${base}/ui/service/biz/amcs/fin/finMonthMain/submitToProvince",{id:row.id},"是否提交到省区审核？",false).then(data=>{
						$grid.load('#gridBox',{hospId:'${hospId}'});
					})
				});
			},
			url: "${base}/ui/service/biz/amcs/fin/finMonthMain/getList",
		});


	});
</script>

</html>

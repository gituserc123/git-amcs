<input id="btnSingleDiseaseImport"  onclick="(function (){
		singleDiseaseImportClickPop = $pop.popTemForm({
			title: '查看历史填报',
			temId: 'singleDiseaseImportLastList',
			data: null,
			area: ['800px', '600px'],
			onPop: function () {
				$grid.newGrid('#singleDiseaseImportGridBox', {
					fitColumns: true,
					pagination:false,
					height:'560px',
					columns: [
					[
						{
						title: '操作',
						field: 'op',
						width: 90,
						formatter: function (v, row, index) {
								let strCls='s-op s-op-singleDiseaseImport icon-arrow-left';
								let strTitle='导入';
								htmlStr='&nbsp;&nbsp;'+'<span class=&#34;'+ strCls + '&#34; title=&#34;' + strTitle + '&#34;  rel=&#34;' + index + '&#34;></span>';
								return htmlStr;
							}
						},
						{title: 'Id', field: 'id', hidden: true, width: 100},
						{title: '统筹区', field: 'poolingArea', hidden: false, width: 100},
						{title: '类型', field: 'typeName', hidden: false, width: 100},
						{title: '创建时间', field: 'createDate', hidden: false, width: 100},
					]
					],
					onLoadSuccess: function (data) {
						$('.s-op-singleDiseaseImport').click(function () {
							var ix = $(this).attr('rel');
							var row = data.rows[ix];
							$ajax.post('${base}/ui/service/biz/amcs/fin/finInsSingleDisease/getFinInsSingleDisease',{id:row.id}).then(
							(data)=>{
							//不能导入的字段
							delete data.id;
							delete data.monthId;
							delete data.mainId;
							delete data.hospId;
							delete data.balanceOrDeficitAmount;
							$('#single-tab form').form('load',data)
							}
							)
							$pop.close(singleDiseaseImportClickPop);
						});
					},
					url:'${base}/ui/service/biz/amcs/fin/finInsSingleDisease/lastList',
				})
			}
		});
	})()"
	type="button" class="btn btn-primary" name="btnSingleDiseaseImport" noclosepop="true" value="从最近历史填报数据导入"/>
<script id="singleDiseaseImportLastList" type="text/html">
	<div class="cont-grid">
		<div id="singleDiseaseImportGridBox"></div>
	</div>
</script>
<form class="soform formA form-validate pad-t10" id="singleform">
    <h2 class="h2-title-a">
        <span class="s-title">单病种</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <input class="hide" type="text" name="id" id="id">
	<input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
	<input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
	<input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
    <div class="row">
        <div class="p8">
            <div class="item-one solab-l">
                <label class="lab-item">单病种结算政策</label>
				<textarea class="txta txt-validate required adaptiveTextarea" type="text" id="settlementPolicy" data-options="required:true"
						  name="settlementPolicy" noNull="必填" placeholder="" validType="maxlength[300]"></textarea>
            </div>
        </div>
		<div class="p4">
			<div class="item-one">
			</div>
		</div>
	</div>
	<div class="row">
        <div class="p8">
            <div class="item-one solab-l">
                <label class="lab-item">单病种耗材结算标准</label>
                <textarea class="txta txt-validate adaptiveTextarea" type="text" name="consumableSettlementStandard"
                       id="consumableSettlementStandard" validType="maxlength[100]" value="" style="width:100%" cols="200" row="10"></textarea>
            </div>
        </div>
		<div class="p4">
			<div class="item-one">
			</div>
		</div>
	</div>
    <div class="row">
        <div class="p8">
            <div class="item-one solab-ld">
                <label class="lab-item">截至填报月预计节余/超支总金额（万元）</label>
                <textarea class="txta txt-validate required adaptiveTextarea" type="text" id="balanceOrDeficitAmount" dataOptions="required:ture"
                          validType="maxlength[100]" noNull="必填"  name="balanceOrDeficitAmount" placeholder="" style="width:100%"></textarea>
            </div>
        </div>
        <div class="p4">
            <div class="item-one">
            </div>
        </div>
    </div>
	<div class="row">
		<div class="p8">
			<div class="item-one solab-ld">
				<label class="lab-item">超单病种结算标准原因</label>
				<textarea class="txta txt-validate adaptiveTextarea" type="text" id="exceededReason" validType="maxlength[100]"
						  name="exceededReason" placeholder=""></textarea>
			</div>
		</div>
		<div class="p4">
			<div class="item-one">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="p4">
			<input id="singleSubmit" :disabled="isDisabled" @click="singleSubmit" type="button" class="btn btn-primary" name="singleSubmit"
				   noclosepop="true" value="提交"/>
		</div>
	</div>
</form>
<h2 class="h2-title-a">
	<span class="s-title">耗材信息：单病种人工晶体/羊膜等耗材建议临床科室选用的价格区间</span>
</h2>
<hr class="mar-l10 mar-r10 mar-t0" style="border-color:#b2def4">
<div class="cont-grid-single-cons">
	<div id="singleCons"></div>
</div>
<script id="editSingleCons" type="text/html">
	<input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
	<form id="singleConsForm" class="soform formA form-validate form-enter pad-t20 singleCons" method="post"
		  action="${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save">
		<input class="hide" type="text" id="id" name="id" value=""/>
		<input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
		<input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
		<input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
		<input class="hide" type="text" name="consumablesType" id="consumablesType" value=1>
		<div class="row" id="singleDiseaseConImportDiv">
			<div class="p8">
				<div class="item-one ">
					<input id="btnSingleDiseaseConImport" onclick="(function (){
							singleDiseaseConImportClickPop = $pop.popTemForm({
								title: '查看历史填报',
								temId: 'singleDiseaseConImportLastList',
								data: null,
								area: ['800px', '600px'],
								onPop: function () {
									$grid.newGrid('#singleDiseaseConImportGridBox', {
										fitColumns: true,
										pagination:false,
										height:'560px',
										columns: [
											[
												{
												title: '操作',
												field: 'op',
												width: 90,
												formatter: function (v, row, index) {
														let strCls='s-op singleDiseaseConImport icon-arrow-left';
														let strTitle='导入';
														htmlStr='&nbsp;&nbsp;'+'<span class=&#34;'+ strCls + '&#34; title=&#34;' + strTitle + '&#34;  rel=&#34;' + index + '&#34;></span>';
														return htmlStr;
													}
												},
												{title: 'Id', field: 'id', hidden: true, width: 100},
												{title: '耗材名称', field: 'consumables', width: 200},
												{title: '最大值', field: 'consumablesMax', width: 300},
												{title: '最小值', field: 'consumablesMin', width: 200}
											]
										],
										onLoadSuccess: function (data) {
											$('.singleDiseaseConImport').click(function () {
												var ix = $(this).attr('rel');
												var row = data.rows[ix];
												$ajax.post('${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/getFinInsConsumablesInfo',{id:row.id}).then(
														(data)=>{
														//不能导入的字段
														delete data.id;
														delete data.monthId;
														delete data.mainId;
														delete data.hospId;
														$('#singleConsForm').form('load',data);
													}
												)
												$pop.close(singleDiseaseConImportClickPop);
											});
										},
										url:'${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/lastList?consumablesType=1',
									})
								}
							});
						})()"
						type="button" class="btn btn-primary" name="btnSingleDiseaseConImport" noclosepop="true" value="从最近历史填报数据导入"/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p8">
				<div class="item-one ">
					<label class="lab-item">耗材名称</label>
					<input class="txt txt-validate  required" type="text" name="consumables" id="singleConsumables"
						   validType="maxlength[10]" noNull="必填项"  style="width:100%" value=""/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">最小值</label>
					<input class="txt txt-validate easyui-numberbox" type="text" name="consumablesMin"
						   id="singleConsumablesMin" value="" style="width:100%" data-options="min:0,precision:2"/><em class="em-at em-dropAt">元</em>
				</div>
			</div>
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">最大值</label>
					<input class="txt txt-validate easyui-numberbox" type="text" name="consumablesMax"
						   id="singleConsumablesMax" value="" style="width:100%" data-options="min:0,precision:2"/><em class="em-at em-dropAt">元</em>
				</div>
			</div>
		</div>
		<p class="row-btn pad-t5">
			<input type="button" class="btn btn-primary btn-easyFormSubmit btnSubmitSingleCons" noclosepop="false" name="btnSubmitSingleCons" value="保 存"/>
			<input type="button" class="btn btn-closeTemPop btn-cancel-cus-singleCons" name="btnCancel" value="取 消"/>
		</p>
	</form>
</script>
<script id="singleDiseaseConImportLastList" type="text/html">
	<div class="cont-grid">
		<div id="singleDiseaseConImportGridBox"></div>
	</div>
</script>
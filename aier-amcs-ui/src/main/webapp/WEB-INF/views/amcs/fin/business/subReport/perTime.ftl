<input id="btnPertimePayImport"  onclick="(function (){
        formPertimePayImportPop = $pop.popTemForm({
            title: '查看历史填报',
            temId: 'pertimePayImportLastList',
            data: null,
            area: ['800px', '600px'],
            onPop: function () {
                $grid.newGrid('#pertimePayImportGridBox', {
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
                                let strCls='s-op s-op-pertimePayImport icon-arrow-left';
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
                        $('.s-op-pertimePayImport').click(function () {
                            var ix = $(this).attr('rel');
                            var row = data.rows[ix];
                            $ajax.post('${base}/ui/service/biz/amcs/fin/finInsPertimePay/getFinInsPertimePay',{id:row.id}).then(
                                (data)=>{
                                    //不能导入的字段
                                    delete data.id
                                    delete data.monthId
                                    delete data.mainId
                                    delete data.hospId
                                    $('#perTime-tab form').form('load',data)
                                }
                            )
                            $pop.close(formPertimePayImportPop);
                        });
                    },
                    url:'${base}/ui/service/biz/amcs/fin/finInsPertimePay/lastList',
                })
            }
        });
    })()"
    type="button" class="btn btn-primary" name="btnPertimePayImport" noclosepop="true" value="从最近历史填报数据导入"/>
<script id="pertimePayImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="pertimePayImportGridBox"></div>
    </div>
</script>
<form class="soform formA form-validate pad-t10" id="perTimeform">
    <h2 class="h2-title-a">
        <span class="s-title">按人头（次均）付费</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <input class="hide" type="text" name="id" id="id">
    <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
    <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
    <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
    <div class="row">
        <div class="p8">
            <div class="item-one  solab-lc">
                <label class="lab-item">按人头（次均）结算政策</label>
                <textarea class="txta txt-validate" type="text" name="settlementPolicy" id="settlementPolicy"
                          validType="maxlength[200]" noNull="必填" value=""></textarea>
            </div>
        </div>
		<div class="p4">
            <div class="item-one  solab-l">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one  solab-l">
                <label class="lab-item">超次均原因</label>
                <input class="txt txt-validate" type="text" name="exceededReason" id="exceededReason"
                       validType="maxlength[100]" value=""/>
            </div>
        </div>
        <div class="p4">
            <div class="item-one  solab-lc">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one  solab-ld">
            <label id="perTimeQlable" onClick="(function (){
                    $pop.tips(`截至填报月预计节余/超支总金额（节余填正，超支填负数），单位（万元）<br/>`,'#perTimeQlable',{time: 0,closeBtn: true,maxWidth: 600});
                    })()"  class="lab-item">截至填报月预计节余/超支总金额<span style="padding-left: 2px"><i class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="balanceOrDeficitAmount" id="balanceOrDeficitAmount"
                       style="width:100%" data-options="precision:2,required:true" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one  solab-ld">
            </div>
        </div>
        <div class="p3">
            <div class="item-one  solab-ld">
            </div>
        </div>
        <div class="p3">
            <div class="item-one  solab-ld">
            </div>
        </div>
    </div>
	<hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
	<div class="row">
		<div class="p4">
			<input id="btnPerTimeSubmit" :disabled="isDisabled" @click="perTimeSubmit" type="button" class="btn btn-primary" name="btnPerTimeSubmit" noclosepop="true" value="提交"/>
		</div>
	</div>
</form>

<input id="btnProjectPayImport"  onclick="(function (){
        formProjectPayImportPop = $pop.popTemForm({
            title: '查看历史填报',
            temId: 'projectPayImportLastList',
            data: null,
            area: ['800px', '600px'],
            onPop: function () {
                $grid.newGrid('#projectPayImportGridBox', {
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
                                let strCls='s-op s-op-projectPayImport icon-arrow-left';
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
                        $('.s-op-projectPayImport').click(function () {
                            var ix = $(this).attr('rel');
                            var row = data.rows[ix];
                            $ajax.post('${base}/ui/service/biz/amcs/fin/finInsProjectPay/getFinInsProjectPay',{id:row.id}).then(
                                (data)=>{
                                    //不能导入的字段
                                    delete data.id
                                    delete data.monthId
                                    delete data.mainId
                                    delete data.hospId
                                    $('#project-tab form').form('load',data)
                                }
                            )
                            $pop.close(formProjectPayImportPop);
                        });
                    },
                    url:'${base}/ui/service/biz/amcs/fin/finInsProjectPay/lastList',
                });
            }
        });
    })()"
   type="button" class="btn btn-primary" name="btnProjectPayImport" noclosepop="true" value="从最近历史填报数据导入"/>
<script id="projectPayImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="projectPayImportGridBox"></div>
    </div>
</script>
<form class="soform formA form-validate pad-t10" id="projectform">
    <h2 class="h2-title-a">
        <span class="s-title">按项目付费</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <input class="hide" type="text" name="id" id="id">
    <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
    <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
    <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">年度总指标</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="totalIndicator" id="totalIndicator"  style="width:100%"
                       validType="" noNull="年度总指标必填" data-options="min:0,precision:2,required:true" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label class="lab-item">截至填报月实际已使用医保总额</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="actualUsedAmount" data-options="min:0,precision:2,required:true"
                       id="actualUsedAmount" validType="" noNull="截至填报月实际已使用医保总额必填" value=""  style="width:100%"/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p12">
            <div class="item-one  solab-l">
                <label class="lab-item">按项目付费结算病种</label>
                <textarea class="txta txt-validate required adaptiveTextarea" type="text" id="settledByProjectPayment"
                          name="settledByProjectPayment" data-options="required:true" validType="maxlength[200]" placeholder=""></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p12">
            <div class="item-one  solab-l">
                <label class="lab-item">具体报销政策</label>
                <textarea class="txta  txt-validate required adaptiveTextarea" type="text"
                       id="reimbursementPolicy" name="reimbursementPolicy" placeholder="" validType="maxlength[200]" data-options="required:true"></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p12">
            <div class="item-one  solab-l">
                <label class="lab-item">超总额原因</label>
                <textarea class="txta txt-validate required adaptiveTextarea" type="text" id="exceedReason"
                          name="exceedReason" data-options="required:true" validType="maxlength[2000]" placeholder=""></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p12">
            <div class="item-one  solab-l">
                <label class="lab-item">超总额整改措施</label>
                <textarea class="txta  txt-validate required adaptiveTextarea" type="text"
                          id="rectificationMeasures" name="rectificationMeasures" placeholder="" validType="maxlength[2000]" data-options="required:true"></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one solab-ld">
                <label class="lab-item t-css">是否向医院ceo、院长、临床科室等书面反馈</label>
                <label class="lab-val r-css"><input type="radio" class="rad reason" name="feedbackToManagement" value=1 />是</label>
                <label class="lab-val r-css"><input type="radio" class="rad reason" name="feedbackToManagement" value=0 />否</label>
            </div>
        </div>
        <div class="p4">
            <div class="item-one">
            </div>
        </div>
    </div>

    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p4">
            <input id="btnProjectSubmit" :disabled="isDisabled" @click="projectSubmit" type="button" class="btn btn-primary" name="btnProjectSubmit" noclosepop="true" value="提交"/>
        </div>
    </div>
</form>
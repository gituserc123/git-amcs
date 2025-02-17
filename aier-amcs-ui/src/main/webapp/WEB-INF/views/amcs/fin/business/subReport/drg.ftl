<input id="btnDrgPayImport"  onclick="(function (){
        formDrgPayImportPop = $pop.popTemForm({
            title: '查看历史填报',
            temId: 'drgPayImportLastList',
            data: null,
            area: ['800px', '600px'],
            onPop: function () {
                $grid.newGrid('#drgPayImportGridBox', {
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
                                let strCls='s-op s-op-drgPayImport icon-arrow-left';
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
                        $('.s-op-drgPayImport').click(function () {
                        var ix = $(this).attr('rel');
                        var row = data.rows[ix];
                        $ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/getFinInsDrgPay',{id:row.id}).then(
                            (data)=>{
                                //不能导入的字段
                                delete data.id
                                delete data.monthId
                                delete data.mainId
                                delete data.hospId
                                delete data.balanceOrOverspend
                                $('#drg-tab form').form('load',data)
                            }
                        )
                        $pop.close(formDrgPayImportPop);
                        });
                    },
                    url:'${base}/ui/service/biz/amcs/fin/finDrgInfo/lastList',
                })
            }
        });
    })()"
    type="button" class="btn btn-primary" name="btnDrgPayImport" noclosepop="true" value="从最近历史填报数据导入"/>
<script id="drgPayImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="drgPayImportGridBox"></div>
    </div>
</script>
<form class="soform formA form-validate pad-t10" id="drgform">
    <h2 class="h2-title-a">
        <span class="s-title">DRG付费表</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
    <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
    <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
    <input class="hide" type="text" name="analysisId" id="analysisId">
    <input class="hide" type="text" name="payId" id="payId">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">实际执行DRG付费的时间</label>
                <input type="text" id="actualExecutionTime" class="txt so-date txt-validate "
                       data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" type="text"
                       name="actualExecutionTime"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">预计执行DRG付费的时间</label>
                <input type="text" id="expectedExecutionTime" class="txt so-date txt-validate "
                       data-options="required:true,format:'yyyy-MM-dd'" type="text"
                       name="expectedExecutionTime"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label class="lab-item">DRG结算系数或机构系数或差异系数</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="differentialCoefficient" id="differentialCoefficient"
                       validType=""  data-options="min:0,precision:2" style="width:100%" value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label  id="drgQlable" onClick="(function (){
                    $pop.tips(`截至填报月预计节余/超支总金额（节余填正，超支填负数），单位（万元）<br/>`,'#drgQlable',{time: 0,closeBtn: true,maxWidth: 600});
                    })()" class="lab-item">截至填报月预计节余/超支总金额<span style="padding-left: 2px"><i class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="balanceOrOverspend" id="balanceOrOverspend"
                       validType=""  data-options="required:true,precision:2" noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
    </div>
    <div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label id="qlable" class="lab-item">DRG总额控制（万元）</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="totalBalanceControl" id="totalBalanceControl"
                       validType=""  data-options="precision:2" style="width:100%" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label id="qlable" class="lab-item">DRG总权重控制</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="overallWeightControl" id="overallWeightControl"
                       validType=""  data-options="precision:0" style="width:100%" value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one solab-lc">
                <label class="lab-item">“三新项目”除外支付政策</label>
                <textarea class="txta txt-validate adaptiveTextarea" type="text" name="excludingThreeNew" id="excludingThreeNew"
                          validType="maxlength[2000]" value="" data-options="required:true"></textarea>
            </div>
        </div>
        <div class="p4">
            <div class="item-one">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one  solab-l">
                <label class="lab-item">DRG超支整改措施</label>
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
    <div class="row">
        <div class="p4">
            <input id="drgSubmit" :disabled="isDisabled" @click="drgSubmit" type="button" class="btn btn-primary"
                   name="drgSubmit" noclosepop="true" value="提交"/>
        </div>
        <div class="p4">
            <div class="item-one solab-lc">
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-lc">
            </div>
        </div>
    </div>
</form>
<h2 class="h2-title-a">
    <span class="s-title">耗材信息：人工晶体/羊膜等耗材建议临床科室选用的价格区间</span>
</h2>
<hr class="mar-l10 mar-r10 mar-t0" style="border-color:#b2def4">
<div class="cont-grid-drg-cons">
    <div id="drgCons"></div>
</div>
<h2 class="h2-title-a">
    <span class="s-title">DRG结算方式盈亏分析表</span>
</h2>
<hr class="mar-l10 mar-r10 mar-t0" style="border-color:#b2def4">
<div class="cont-grid-drganalysis">
    <div id="gridDrgAnalysisBox"></div>
</div>
<script id="editDrgAnalysis" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form id="drgForm" class="soform formA form-validate form-enter pad-t20 drgAnalysis" method="post"
          action="${base}/ui/service/biz/amcs/fin/finDrgInfo/saveDrgAnalysis">
        <input class="hide" type="text" id="id" name="id" value=""/>
        <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
        <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
        <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
        <!--
        <div class="row" id="drgAnalysisImportDiv">
            <div class="p8">
                <div class="item-one ">
                    <input id="btnDrgAnalysisImport"
                           onclick="(function (){
                                   drgAnalysisImportClickPop = $pop.popTemForm({
                                       title: '查看历史填报',
                                       temId: 'drgAnalysisImportLastList',
                                       data: null,
                                       area: ['1200px', '600px'],
                                       onPop: function () {
                                           $grid.newGrid('#drgAnalysisImportGridBox', {
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
                                                               let strCls='s-op drgAnalysisImport icon-arrow-left';
                                                               let strTitle='导入';
                                                               htmlStr='&nbsp;&nbsp;'+'<span class=&#34;'+ strCls + '&#34; title=&#34;' + strTitle + '&#34;  rel=&#34;' + index + '&#34;></span>';
                                                               return htmlStr;
                                                           }
                                                       },
                                                       {title: 'Id', field: 'id', hidden: true, width: 100},
                                                       {title: '病种', field: 'diseaseType', width: 200},
                                                       {title: '主要诊断名称', field: 'mainDiagnosisName', width: 300},
                                                       {title: '2023年总病例数', field: 'totalCases', width: 100},
                                                       {title: '实际住院总费用', field: 'actualHospitalizationCost', width: 100},
                                                       {title: '预计DIP结算医疗总费用', field: 'expectedSettlementCost', width: 150},
                                                       {title: '预计节余/超支总金额', field: 'expectedBalanceAmount', width: 125},
                                                       {title: '例均实际住院费用', field: 'avgActualCost', width: 105},
                                                       {title: '例均预计DIP结算医疗费用', field: 'avgExpectedSettlement', width: 160},
                                                       {title: '例均预计节余/超支金额', field: 'avgExpectedBalanceAmount', width: 150},
                                                       {title: '盈亏情况分析', field: 'profitLossAnalysis', width: 300},
                                                       {title: '创建人', field: 'creatorName', width: 100},
                                                       {title: '创建时间', field: 'createDate', width: 200},
                                                       {title: '最后修改时间', field: 'modifyDate', width: 200}
                                                   ]
                                               ],
                                               onLoadSuccess: function (data) {
                                                   $('.drgAnalysisImport').click(function () {
                                                       var ix = $(this).attr('rel');
                                                       var row = data.rows[ix];
                                                       $ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/getFinInsDrgAnalysis',{id:row.id}).then(
                                                           (data)=>{
                                                               //不能导入的字段
                                                               delete data.id;
                                                               delete data.monthId;
                                                               delete data.mainId;
                                                               delete data.hospId;
                                                               delete data.totalCases;
                                                               delete data.actualHospitalizationCost;
                                                               delete data.expectedSettlementCost;
                                                               delete data.expectedBalanceAmount;
                                                               $('#drgForm').form('load',data);
                                                           }
                                                       )
                                                       $pop.close(drgAnalysisImportClickPop);
                                                   });
                                               },
                                               url:'${base}/ui/service/biz/amcs/fin/finDrgInfo/lastAnalysisList',
                                           })
                                       }
                                   });
                           })()"
                           type="button" class="btn btn-primary" name="btnDrgAnalysisImport" noclosepop="true" value="从最近历史填报数据导入"/>
                </div>
            </div>
            <div class="p4">
                <div class="item-one">
                </div>
            </div>
        </div>
        -->
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">病种</label>
                    <input class="txt txt-validate required" type="text" name="diseaseType" id="diseaseType"
                           validType="maxlength[10]" noNull="必填项" value=""/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">DRG分组编码</label>
                    <input class="txt txt-validate required" type="text" name="groupCode" id="groupCode"
                           validType=many['maxlength[100]'] noNull="必填项" value=""/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p8">
                <div class="item-one solab-lc">
                    <label class="lab-item">DRG分组名称</label>
                    <textarea class="txta txt-validate required adaptiveTextarea" type="text" name="groupName" id="groupName"  dataOptions="required:ture"
                              validType="maxlength[50]" noNull="必填" value=""></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p8">
                <div class="item-one solab-lc">
                    <label class="lab-item">医保统筹区/类别</label>
                    <input id="insurancePlanCategory" class="easyui-combobox" name="insurancePlanCategory"
                           data-options="valueField:'id',textField:'text',url:'${base}/ui/service/biz/amcs/fin/finHospSetting/getSelectList'" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">年度总病例数</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="totalCases" id="drgTotalCases"
                           data-options="min:0,precision:0,required:true,
                           onChange:((v)=>{
                                // 实际住院总费用/年度总病例数=例均实际住院费用
                                if($('#drgActualHospitalizationCost').val()){
                                    $('#drgAvgActualCost').numberbox('setValue',$('#drgActualHospitalizationCost').val()/v);
                                }
                                // 预计DRG结算医疗总费用/年度总病例数=例均预计DRG结算医疗费用
                                if($('#drgExpectedSettlementCost').val()){
                                    $('#drgAvgExpectedSettlement').numberbox('setValue',$('#drgExpectedSettlementCost').val()/v);
                                }
                                // (预计节余/超支总金额)/年度总病例数=(例均预计节余/超支金额)
                                if($('#drgExpectedBalanceAmount').val()){
                                    $('#drgAvgExpectedBalanceAmount').numberbox('setValue',$('#drgExpectedBalanceAmount').val()/v);
                                }
                           })"
                           noNull="必填项" style="width:100%" value=""/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">实际住院总费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="actualHospitalizationCost"
                           id="drgActualHospitalizationCost" data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                if($('#drgTotalCases').val()){
                                    $('#drgAvgActualCost').numberbox('setValue',v/$('#drgTotalCases').val());
                                };
                           })"
                           noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">预计DRG结算医疗总费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="expectedSettlementCost"
                           id="drgExpectedSettlementCost" data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                if($('#drgTotalCases').val()){
                                    $('#drgAvgExpectedSettlement').numberbox('setValue',v/$('#drgTotalCases').val());
                                };
                           })"
                           noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">预计节余/超支总金额</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="expectedBalanceAmount"
                           id="drgExpectedBalanceAmount" data-options="precision:2,required:true,
                           onChange:((v)=>{
                                // (预计节余/超支总金额)/年度总病例数=(例均预计节余/超支金额)
                                if($('#drgTotalCases').val()){
                                    $('#drgAvgExpectedBalanceAmount').numberbox('setValue',v/$('#drgTotalCases').val());
                                };
                           })" noNull="必填项"  style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">例均实际住院费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="avgActualCost" id="drgAvgActualCost"
                           data-options="min:0,precision:2,required:true" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">例均预计DRG结算医疗费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="avgExpectedSettlement"
                           id="drgAvgExpectedSettlement" data-options="min:0,precision:2,required:true" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">例均预计节余/超支金额</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="avgExpectedBalanceAmount"
                           id="drgAvgExpectedBalanceAmount" data-options="precision:2,required:true" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p8">
                <div class="item-one solab-lc">
                    <label class="lab-item">盈亏情况分析</label>
                    <textarea class="txta txt-validate required adaptiveTextarea" type="text" name="profitLossAnalysis" id="profitLossAnalysis"  dataOptions="required:ture"
                           validType="maxlength[200]" noNull="必填" value=""></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p8">
                <div class="item-one solab-lc">
                    <label class="lab-item">医保局年度考核指标</label>
                    <textarea class="txta txt-validate" type="text" name="annualAssessmentTargets"
                           id="annualAssessmentTargets" validType="maxlength[200]" value=""></textarea>
                </div>
            </div>
        </div>
        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-easyFormSubmit btnSubmitDrg" noclosepop="false" name="btnSubmitDrg" value="保 存"/>
            <input type="button" class="btn btn-closeTemPop btn-cancel-cus-drg" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
<script id="editDrgCons" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form id="drgConsForm" class="soform formA form-validate form-enter pad-t20 drgCons" method="post"
          action="${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save">
        <input class="hide" type="text" id="id" name="id" value=""/>
        <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
        <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
        <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
        <input class="hide" type="text" name="consumablesType" id="consumablesType" value=3>
        <div class="row" id="drgAnalysisConImportDiv">
            <div class="p8">
                <div class="item-one ">
                    <input id="btnDrgAnalysisConImport"
                           onclick="(function (){
                                drgAnalysisConImportClickPop = $pop.popTemForm({
                                    title: '查看历史填报',
                                    temId: 'drgAnalysisConImportLastList',
                                    data: null,
                                    area: ['800px', '600px'],
                                        onPop: function () {
                                            $grid.newGrid('#drgAnalysisConImportGridBox', {
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
                                                                let strCls='s-op drgAnalysisConImport icon-arrow-left';
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
                                                    $('.drgAnalysisConImport').click(function () {
                                                        var ix = $(this).attr('rel');
                                                        var row = data.rows[ix];
                                                        $ajax.post('${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/getFinInsConsumablesInfo',{id:row.id}).then(
                                                            (data)=>{
                                                                //不能导入的字段
                                                                delete data.id;
                                                                delete data.monthId;
                                                                delete data.mainId;
                                                                delete data.hospId;
                                                                $('#drgConsForm').form('load',data);
                                                            }
                                                        )
                                                        $pop.close(drgAnalysisConImportClickPop);
                                                    });
                                                },
                                                url:'${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/lastList?consumablesType=3',
                                            })
                                    }
                                });
                           })()"
                           type="button" class="btn btn-primary" name="btnDrgAnalysisConImport" noclosepop="true" value="从最近历史填报数据导入"/>
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
                    <input class="txt txt-validate  required" type="text" name="consumables" id="dipConsumables"
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
                           id="dipConsumablesMin" value="" style="width:100%" data-options="min:0,precision:2"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">最大值</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="consumablesMax"
                           id="dipConsumablesMax" value="" style="width:100%" data-options="min:0,precision:2"/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <p class="row-btn pad-t5">
            <input type="button" class="btn btn-primary btn-easyFormSubmit btnSubmitDrgCons" noclosepop="false" name="btnSubmitDrgCons" value="保 存"/>
            <input type="button" class="btn btn-closeTemPop btn-cancel-cus-drgCons" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
<script id="drgAnalysisImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="drgAnalysisImportGridBox"></div>
    </div>
</script>
<script id="drgAnalysisConImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="drgAnalysisConImportGridBox"></div>
    </div>
</script>





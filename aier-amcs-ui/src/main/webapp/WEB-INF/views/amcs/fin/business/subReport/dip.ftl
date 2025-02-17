<input id="btnDipPayImport"  onclick="(function (){
        formDipPayImportPop = $pop.popTemForm({
            title: '查看历史填报',
            temId: 'dipPayImportLastList',
            data: null,
            area: ['800px', '600px'],
            onPop: function () {
                $grid.newGrid('#dipPayImportGridBox', {
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
                                let strCls='s-op s-op-dipPayImport icon-arrow-left';
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
                        $('.s-op-dipPayImport').click(function () {
                            var ix = $(this).attr('rel');
                            var row = data.rows[ix];
                            $ajax.post('${base}/ui/service/biz/amcs/fin/finDipInfo/getFinInsDipPay',{id:row.id}).then(
                                (data)=>{
                                    //不能导入的字段
                                    delete data.id
                                    delete data.monthId
                                    delete data.mainId
                                    delete data.hospId
                                    delete data.balanceOrOverspend
                                    $('#dip-tab form').form('load',data)
                                }
                            )
                            $pop.close(formDipPayImportPop);
                        });
                    },
                    url:'${base}/ui/service/biz/amcs/fin/finDipInfo/lastList',
                });
            }
        });
    })()"
    type="button" class="btn btn-primary" name="btnDipPayImport" noclosepop="true" value="从最近历史填报数据导入"/>
<script id="dipPayImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="dipPayImportGridBox"></div>
    </div>
</script>
<form class="soform formA form-validate pad-t10" id="dipform">
    <h2 class="h2-title-a">
        <span class="s-title">DIP付费表</span>
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
                <label class="lab-item">实际执行DIP付费的时间</label>
                <input type="text" id="actualExecutionTime" class="txt so-date txt-validate "
                       data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" type="text"
                       name="actualExecutionTime"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">预计执行DIP付费的时间</label>
                <input type="text" id="expectedExecutionTime" class="txt so-date txt-validate "
                       data-options="required:true,format:'yyyy-MM-dd'" type="text"
                       name="expectedExecutionTime"/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label class="lab-item">DIP结算系数或机构系数或差异系数</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="differentialCoefficient" id="differentialCoefficient"
                       validType=""  data-options="min:0,precision:2" style="width:100%" value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label id="qlable" onClick="(function (){
                    $pop.tips(`截至填报月预计节余/超支总金额（节余填正，超支填负数），单位（万元）<br/>`,'#qlable',{time: 0,closeBtn: true,maxWidth: 600});
                    })()" class="lab-item">截至填报月预计节余/超支总金额<span style="padding-left: 2px"><i class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="balanceOrOverspend" id="balanceOrOverspend"
                       validType=""  data-options="required:true,precision:2"  noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
    </div>
    <div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label id="qlable" class="lab-item">DIP总额控制（万元）</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="totalBalanceControl" id="totalBalanceControl"
                       validType=""  data-options="precision:2" style="width:100%" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label id="qlable" class="lab-item">DIP总分值控制</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="totalScoreControl" id="totalScoreControl"
                       validType=""  data-options="precision:0" style="width:100%" value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p8">
            <div class="item-one solab-lc">
                <label class="lab-item">“三新项目”除外支付政策</label>
                <textarea class="txta txt-validate adaptiveTextarea" type="text" name="excludingThreeNew" id="excludingThreeNew"
                          validType="maxlength[200]" value="" data-options="required:true"></textarea>
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
                <label class="lab-item">DIP超支整改措施</label>
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
            <input id="dipSubmit" :disabled="isDisabled" @click="dipSubmit" type="button" class="btn btn-primary" name="dipSubmit"
                   noclosepop="true" value="提交"/>
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
<div class="cont-grid-dip-cons">
    <div id="dipCons"></div>
</div>
<h2 class="h2-title-a">
    <span class="s-title">DIP结算方式盈亏分析表</span>
</h2>
<hr class="mar-l10 mar-r10 mar-t0" style="border-color:#b2def4">
<div class="cont-grid-dipanalysis">
    <div id="gridDipAnalysisBox"></div>
</div>
<script id="editDipAnalysis" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form id="dipForm" class="soform formA form-validate form-enter pad-t20 dipAnalysis" method="post"
          action="${base}/ui/service/biz/amcs/fin/finDipInfo/saveDipAnalysis">
        <input class="hide" type="text" id="id" name="id" value=""/>
        <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
        <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
        <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
        <!--
        <div class="row" id="dipAnalysisImportDiv">
            <div class="p8">
                <div class="item-one ">
                    <input id="btnDipAnalysisImport"
                           onclick="(function (){
                                dipAnalysisImportClickPop = $pop.popTemForm({
                                    title: '查看历史填报',
                                    temId: 'dipAnalysisImportLastList',
                                    data: null,
                                    area: ['1200px', '600px'],
                                    onPop: function () {
                                        $grid.newGrid('#dipAnalysisImportGridBox', {
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
                                                            let strCls='s-op dipAnalysisImport icon-arrow-left';
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
                                                $('.dipAnalysisImport').click(function () {
                                                    var ix = $(this).attr('rel');
                                                    var row = data.rows[ix];
                                                    $ajax.post('${base}/ui/service/biz/amcs/fin/finDipInfo/getFinInsDipAnalysis',{id:row.id}).then(
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
                                                    $('#dipForm').form('load',data);
                                                    }
                                                )
                                                $pop.close(dipAnalysisImportClickPop);
                                            });
                                            },
                                            url:'${base}/ui/service/biz/amcs/fin/finDipInfo/lastAnalysisList',
                                        })
                                    }
                                });
                           })()"
                           type="button" class="btn btn-primary" name="btnDipAnalysisImport" noclosepop="true" value="从最近历史填报数据导入"/>
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
                    <input class="txt txt-validate  required" type="text" name="diseaseType" id="diseaseType"
                           validType="maxlength[10]" noNull="必填项" value=""/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">DIP分组编码</label>
                    <input class="txt txt-validate  required" type="text" name="groupCode" id="groupCode"
                           validType=many['maxlength[100]'] noNull="必填项" value=""/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p8">
                <div class="item-one solab-lc">
                    <label class="lab-item">DIP分组名称</label>
                    <textarea class="txta txt-validate required adaptiveTextarea" type="text" name="groupName" id="groupName"  dataOptions="required:ture"
                              validType="maxlength[50]" noNull="必填" value=""></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">主要诊断名称</label>
                    <input class="txt txt-validate required" type="text" name="mainDiagnosisName" id="mainDiagnosisName"
                           validType="maxlength[30]" noNull="必填项" value=""/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">主要手术及操作名称</label>
                    <input class="txt txt-validate required" type="text" name="mainSurgeryName" id="mainSurgeryName"
                           validType="maxlength[100]" noNull="必填项" value=""/>
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
                    <input class="txt txt-validate easyui-numberbox required" type="text" name="totalCases" id="dipTotalCases"
                           data-options="min:0,precision:0,required:true,
                           onChange:((v)=>{
                                // 实际住院总费用/年度总病例数=例均实际住院费用
                                if($('#dipActualHospitalizationCost').numberbox('getValue')){
                                    $('#dipAvgActualCost').numberbox('setValue',$('#dipActualHospitalizationCost').numberbox('getValue')/v);
                                };
                                // 预计DIP结算医疗总费用/年度总病例数=例均预计DIP结算医疗费用
                                if($('#dipExpectedSettlementCost').numberbox('getValue')){
                                    $('#dipAvgExpectedSettlement').numberbox('setValue',$('#dipExpectedSettlementCost').numberbox('getValue')/v);
                                };
                                // (预计节余/超支总金额)/年度总病例数=(例均预计节余/超支金额)
                                if($('#dipExpectedBalanceAmount').numberbox('getValue')){
                                    $('#dipAvgExpectedBalanceAmount').numberbox('setValue',$('#dipExpectedBalanceAmount').numberbox('getValue')/v);
                                };
                           })"
                           noNull="必填项" style="width:100%" value=""/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">实际住院总费用</label>
                    <input class="txt txt-validate easyui-numberbox required" type="text" name="actualHospitalizationCost"
                           id="dipActualHospitalizationCost" data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                // 实际住院总费用/年度总病例数=例均实际住院费用
                                if($('#dipTotalCases').val()){
                                    $('#dipAvgActualCost').numberbox('setValue',v/$('#dipTotalCases').val());
                                };
                           })"
                           noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">预计DIP结算医疗总费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="expectedSettlementCost"
                           id="dipExpectedSettlementCost" data-options="min:0,precision:2,required:true,
                           onChange:((v)=>{
                                // 预计DIP结算医疗总费用/年度总病例数=例均预计DIP结算医疗费用
                                if($('#dipTotalCases').val()){
                                    $('#dipAvgExpectedSettlement').numberbox('setValue',v/$('#dipTotalCases').val());
                                };
                           })" noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">预计节余/超支总金额</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="expectedBalanceAmount"
                           id="dipExpectedBalanceAmount" data-options="precision:2,required:true,
                           onChange:((v)=>{
                                // (预计节余/超支总金额)/年度总病例数=(例均预计节余/超支金额)
                                if($('#dipTotalCases').val()){
                                    $('#dipAvgExpectedBalanceAmount').numberbox('setValue',v/$('#dipTotalCases').val());
                                };
                           })" noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">例均实际住院费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="avgActualCost" id="dipAvgActualCost"
                           data-options="min:0,precision:2,required:true" noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">例均预计DIP结算医疗费用</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="avgExpectedSettlement"
                           id="dipAvgExpectedSettlement" data-options="min:0,precision:2,required:true" noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one solab-lc">
                    <label class="lab-item">例均预计节余/超支金额</label>
                    <input class="txt txt-validate easyui-numberbox" type="text" name="avgExpectedBalanceAmount"
                           id="dipAvgExpectedBalanceAmount" data-options="precision:2,required:true" noNull="必填项" style="width:100%" value=""/><em class="em-at em-dropAt">元</em>
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
            <input type="button" class="btn btn-primary btn-easyFormSubmit btnSubmitDip" noclosepop="false" name="btnSubmitDip" value="保 存"/>
            <input type="button" class="btn btn-closeTemPop btn-cancel-cus-dip" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
<script id="editDipCons" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input"/>
    <form id="dipConsForm" class="soform formA form-validate form-enter pad-t20 dipCons" method="post"
          action="${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save">
        <input class="hide" type="text" id="id" name="id" value=""/>
        <input class="hide" type="text" name="monthId" id="monthId" value="${monthId}">
        <input class="hide" type="text" name="mainId" id="mainId" value="${mainId}">
        <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}">
        <input class="hide" type="text" name="consumablesType" id="consumablesType" value=2>
        <div class="row" id="dipAnalysisConImportDiv">
            <div class="p8">
                <div class="item-one ">
                    <input id="btnDipAnalysisConImport" onclick="(function (){
                        dipAnalysisConImportClickPop = $pop.popTemForm({
                            title: '查看历史填报',
                            temId: 'dipAnalysisConImportLastList',
                            data: null,
                            area: ['800px', '600px'],
                            onPop: function () {
                                $grid.newGrid('#dipAnalysisConImportGridBox', {
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
                                                let strCls='s-op dipAnalysisConImport icon-arrow-left';
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
                                        $('.dipAnalysisConImport').click(function () {
                                            var ix = $(this).attr('rel');
                                            var row = data.rows[ix];
                                            $ajax.post('${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/getFinInsConsumablesInfo',{id:row.id}).then(
                                                (data)=>{
                                                    //不能导入的字段
                                                    delete data.id;
                                                    delete data.monthId;
                                                    delete data.mainId;
                                                    delete data.hospId;
                                                    $('#dipConsForm').form('load',data);
                                                }
                                            )
                                            $pop.close(dipAnalysisConImportClickPop);
                                        });
                                    },
                                    url:'${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/lastList?consumablesType=2',
                                })
                            }
                            });
                        })()"
                       type="button" class="btn btn-primary" name="btnDipAnalysisConImport" noclosepop="true" value="从最近历史填报数据导入"/>
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
            <input type="button" class="btn btn-primary btn-easyFormSubmit btnSubmitDipCons" noclosepop="false" name="btnSubmitDipCons" value="保 存"/>
            <input type="button" class="btn btn-closeTemPop btn-cancel-cus-dipCons" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
<script id="dipAnalysisImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="dipAnalysisImportGridBox"></div>
    </div>
</script>
<script id="dipAnalysisConImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="dipAnalysisConImportGridBox"></div>
    </div>
</script>

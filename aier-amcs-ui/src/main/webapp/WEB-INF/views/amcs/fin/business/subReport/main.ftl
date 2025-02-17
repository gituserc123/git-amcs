<input id="mainExport"  @click="mainExport" type="button" class="btn btn-primary" name="btnExport" noclosepop="true" value="从最近历史填报数据导入"/>
<script id="mainLastList" type="text/html">
    <div class="row"><p class="mar-5">从最近的提交记录中选取一条复制到当前表单</p></div>
    <div class="cont-grid">
        <div id="mainGridBox"></div>
    </div>
</script>
<form class="soform formA form-validate pad-t10" xmlns="http://www.w3.org/1999/html">
    <h2 class="h2-title-a">
        <span class="s-title">基本信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <input class="hide" type="text" name="id" id="id">
    <input class="hide" type="text" name="hospId" id="hospId"/>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医院信息：</label>
                ${province}-${hospMap.NAME}（${(hospMap.INVEST_NATURE=="10")?string("上市","合伙")}）
                [#--                <input class="txt txt-validate" type="text" name="insuranceSettlementLevel" id="insuranceSettlementLevel" validType="maxlength[50]" noNull="必填" value=""/>--]
            </div>
        </div>

        <div class="p3">
            <div class="item-one">
                <label class="lab-item">统计周期</label>
                [@ui_select id="period" name="period" tag="@amcs@com.aier.cloud.api.amcs.enums.PeriodEnum" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value=""/]

            </div>
        </div>

    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保结算等级</label>
                [@ui_select id="insuranceSettlementLevel" name="insuranceSettlementLevel" tag="@fin@LEVEL" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value=""/]
                [#--                <input class="txt txt-validate" type="text" name="insuranceSettlementLevel" id="insuranceSettlementLevel" validType="maxlength[50]" noNull="必填" value=""/>--]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保统筹区</label>
                <input class="txt txt-validate" type="text" name="poolingArea" id="poolingArea" validType="maxlength[50]" data-options="readonly:true" noNull="必填" value=""/>
            </div>
        </div>


        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保类别</label>
                [@ui_select id="insuranceType" name="insuranceType" tag="@fin@INSURANCE_TYPE" style="width:100%;" dataOptions="readonly:true,editable:false,required:true" filterkey="firstSpell" value=""/]
[#--                <input class="txt txt-validate" type="text" name="insuranceType" id="insuranceType" validType="maxlength[50]" noNull="必填" value=""/>--]
            </div>
        </div>



        <div class="p3">
            <div class="item-one">
                <label class="lab-item">卫健委定级</label>
                [@ui_select id="healthCommissionLevel" name="healthCommissionLevel" tag="@fin@LEVEL" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value=""/]
[#--                <input class="txt txt-validate" type="text" name="healthCommissionLevel" id="healthCommissionLevel" validType="maxlength[50]" noNull="必填" value=""/>--]
            </div>
        </div>
    </div>

    <h2 class="h2-title-a">
        <span class="s-title">应收医保款</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">应收医保款期末余额</label>
                <input class="txt-validate easyui-numberbox"  type="text" name="receivableBalanceEndPeriod" id="receivableBalanceEndPeriod" validType="number['必须为数字']" data-options="required:true,precision:2" style="width: 100%" noNull="必填" value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>


        <div class="p3">
            <div class="item-one solab-l">
                <label class="lab-item">应收医保款回款率</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="receivableCollectionRate" id="receivableCollectionRate" validType="pNumber['金额必须为正数']" data-options="required:true,precision:2,max:100" style="width: 100%" noNull="必填" value=""/><em class="em-at em-dropAt">%</em>
            </div>
        </div>
    </div>

    <h2 class="h2-title-a">
        <span class="s-title">医保/慈善坏账核销情况</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">
        <div class="p2">
            <div class="item-one solab-l">
                <label class="lab-item">医保坏账核销金额</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="insuranceBadDebtAmt" id="insuranceBadDebtAmt" validType="pNumber['金额必须为正数']" data-options="precision:2" style="width: 100%"  value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
        <div class="p10">
            <div class="item-one solab-l">
                <label class="lab-item">医保坏账核销原因</label>
                <input class="txt txt-validate" type="text" name="insuranceBadDebtCause" id="insuranceBadDebtCause" validType="maxlength[100]"  value=""/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="p2">
            <div class="item-one solab-l">
                <label class="lab-item">慈善坏账核销金额</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="charityBadDebtAmt" id="charityBadDebtAmt" validType="pNumber['金额必须为正数']" data-options="precision:2" style="width: 100%"  value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>


        <div class="p8">
            <div class="item-one solab-l">
                <label class="lab-item">慈善坏账核销原因</label>
                <input class="txt txt-validate" type="text" name="charityBadDebtCause" id="charityBadDebtCause" validType="maxlength[100]"  value=""/>
            </div>
        </div>
    </div>

    <h2 class="h2-title-a">
        <span class="s-title">医保扣罚款情况</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">
        <div class="p3">
            <div class="item-one solab-sb">
                <label class="lab-item">扣款金额</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="penaltyDeductionAmount" id="penaltyDeductionAmount" validType="pNumber['金额必须为正数']" data-options="precision:2" style="width: 100%"  value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>


        <div class="p3">
            <div class="item-one solab-sb">
                <label class="lab-item">罚款金额</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="penaltyFeeAmount" id="penaltyFeeAmount" validType="pNumber['金额必须为正数']" data-options="precision:2" style="width: 100%"  value=""/><em class="em-at em-dropAt">万元</em>
            </div>
        </div>
    </div>


    <h2 class="h2-title-a">
        <span class="s-title">住院结算政策</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">
        <div class="p6">
            <div class="item-one">
                <label class="lab-item">住院结算政策</label>
                [@ui_select id="hospSettlementPolicy" name="hospSettlementPolicy" tag="@fin@SETTLEMENT_POLICY" style="width:100%;" uiShowFirst=false uiShowDefaultDesc="" uiShowDefault=false dataOptions="editable:false,required:true,multiple:true" filterkey="firstSpell" value=""/]
[#--                <input class="txt txt-validate" type="text" name="hospSettlementPolicy" id="hospSettlementPolicy" validType="maxlength[50]" noNull="必填" value=""/>--]
            </div>
        </div>

    </div>

    <h2 class="h2-title-a">
        <span class="s-title">医保协议规定</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">



    <div class="row">

        <div class="p3">
            <div class="item-one solab-lb">
                <label class="lab-item">医保协议规定预留金比例</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="agreementReserveRatio" id="agreementReserveRatio" validType="pNumber['金额必须为正数']" data-options="precision:2,max:100" style="width: 100%"  value=""/><em class="em-at em-dropAt">%</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-ld">
                <label class="lab-item">自费率考核指标（≤%）</label>
                <input class="txt txt-validate easyui-numberbox" type="text" name="selfPaymentRate" id="selfPaymentRate" validType="pNumber['金额必须为正数']" data-options="precision:2,max:100" style="width: 100%"  value=""/><em class="em-at em-dropAt">%</em>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p12">
            <div class="item-one">
                <label class="lab-item">自费率超标原因</label>
                <textarea class="txta txt-validate adaptiveTextarea" type="text" name="reasonsForExceeding" id="reasonsForExceeding" validType="maxlength[100]"  value=""></textarea>
            </div>
        </div>
    </div>



    <div class="row">
        <div class="p12">
            <div class="item-one solab-lb">
                <label class="lab-item">定点服务协议限制性条款</label>
                <textarea class="txta txt-validate adaptiveTextarea" type="text" name="designatedServiceAgreement" id="designatedServiceAgreement" validType="maxlength[400]"  value=""></textarea>
            </div>
        </div>
    </div>




    <h2 class="h2-title-a">
        <span class="s-title">慈善协议</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">



        <div class="p4">
            <div class="item-one solab-lc">
                <label class="lab-item">慈善协议是否到当地医保局备案</label>
                [@ui_select id="isAgreement" name="isAgreement" tag="@amcs@com.aier.cloud.api.amcs.enums.YesOrNoEnum"  uiShowDefault=false style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="" /]
[#--                <input class="txt txt-validate" type="text" name="isAgreement" id="isAgreement" validType="maxlength[50]"  value=""/>--]
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-lc">
                <label class="lab-item">备案医保局是否出具书面回执</label>
                [@ui_select id="isWrittenAck" name="isWrittenAck" tag="@amcs@com.aier.cloud.api.amcs.enums.YesOrNoEnum"  uiShowDefault=false style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="" /]
                [#--                <input class="txt txt-validate" type="text" name="isAgreement" id="isAgreement" validType="maxlength[50]"  value=""/>--]
            </div>
        </div>
        <div class="p4">
            <div class="item-one solab-lc">
                <label class="lab-item">是否存在超协议标准优免行为</label>
                [@ui_select id="existenceAgreedStandards" name="existenceAgreedStandards" tag="@amcs@com.aier.cloud.api.amcs.enums.YesOrNoEnum"  uiShowDefault=false style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="" /]
                [#--                <input class="txt txt-validate" type="text" name="existenceAgreedStandards" id="existenceAgreedStandards" validType="maxlength[50]"  value=""/>--]
            </div>
        </div>

    </div>
    <div class="row">

        <div class="p12">
            <div class="item-one">
                <label class="lab-item">未备案原因</label>
                <textarea class="txta txt-validate" type="text" name="unfiledAgreementReasons" id="unfiledAgreementReasons" validType="maxlength[100]"  value=""></textarea>
            </div>
        </div>
    </div>

    <h2 class="h2-title-a">
        <span class="s-title">日间手术</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">



        <div class="p12">
            <div class="item-one solab-lc">
                <label class="lab-item">医院实际开展日间手术的病种</label>
                <textarea class="txta txt-validate" type="text" name="daySurgeryInHospital" id="daySurgeryInHospital" validType="maxlength[50]"  value=""></textarea>
            </div>
        </div>
    </div>
    <div class="row">

        <div class="p12">
            <div class="item-one solab-lc">
                <label class="lab-item">日间手术结算政策</label>
                <textarea class="txta txt-validate" type="text" name="daySurgerySettlementPolicy" id="daySurgerySettlementPolicy" validType="maxlength[200]"  value=""></textarea>
            </div>
        </div>
    </div>



    <h2 class="h2-title-a">
        <span class="s-title">门诊</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p12">
            <div class="item-one solab-l">
                <label class="lab-item">门诊统筹结算政策</label>
                <textarea class="txta txt-validate" type="text" name="outpUnifiedSettlementPolicy" id="outpUnifiedSettlementPolicy" validType="maxlength[200]"  value=""></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p12">
            <div class="item-one solab-l">
                <label class="lab-item">特（慢）结算政策</label>
                <textarea class="txta txt-validate" type="text" name="specialSettlementPolicy" id="specialSettlementPolicy" validType="maxlength[200]"  value=""></textarea>
            </div>
        </div>




    </div>


    <h2 class="h2-title-a">
        <span class="s-title">其它</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">
        <div class="p12">
            <div class="item-one solab-sb">
                <label class="lab-item">其他事项</label>
                <textarea class="txta txt-validate" type="text" name="otherIssues" id="otherIssues" validType="maxlength[200]"  value=""></textarea>
            </div>
        </div>

    </div>


    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p4">
[#--            <input id="submit" :disabled="isDisabled" @click="mainSave" type="button" class="btn btn-primary" name="btnSubmit" noclosepop="true" value="暂存"/>--]
            <input id="submit" :disabled="isDisabled" @click="mainSubmit" type="button" class="btn btn-primary" name="btnSubmit" noclosepop="true" value="提交"/>
        </div>
    </div>


</form>
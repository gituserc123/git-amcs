<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>材料信息 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
</style>
</head>

<body>
<div class="wrapper pad-10">

  <form class="soform form-validate form-enter pad-t10" method="post" id="sbox" data-opt="{beforeCallback:'beforeAction'}" action="${base}/ui/amcs/aps/rule/updateRule">
  	<input name="metricId" type="hidden" value="${bean.metricId}"/>
  	<input name="id" type="hidden" value="${bean.id}"/>
  
      <div class="row">
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">指标编码</label>
					<input class="txt txt-validate" type="text" name="metricCode" id="metricCode" validType="maxlength[50]" noNull="必填" value="${bean.metricCode}" readonly="readonly"/>
				</div>
			</div>
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">指标名称</label>
					<input class="txt txt-validate" type="text" name="metricName" id="metricName" validType="maxlength[50]" noNull="必填" value="${bean.metricName}" readonly="readonly"/ >
				</div>
			</div>
		</div>
		<div class="row">
		
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">规则名称</label>
					<input class="txt txt-validate" type="text" name="ruleName" id="ruleName" validType="maxlength[100]" noNull="必填" value="${bean.ruleName}"/>
				</div>
			</div>
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">规则版本</label>
					<input class="txt txt-validate" type="text" name="ruleVersion" id="ruleVersion" validType="maxlength[30]" noNull="必填" value="${bean.ruleVersion}"/>
				</div>
			</div>
		
		</div>
		<div class="row">
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">规则使用场景</label>
			        [@ui_select uiShowDefault=false class="drop easyui-combobox" value="${bean.isDefaultRule}" id="isDefaultRule" name="isDefaultRule" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.RuleUsingEnum" filterkey="firstSpell" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]
				</div>
			</div>
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">生效时间</label>
					<input class="txt txt-validate inline so-date required" type="text" id="ruleBeginDate" name="ruleBeginDate" value="${bean.ruleBeginDate}" data-opt="{type:'time',format:'yyyy-MM-dd HH:00:00'}" required=true  />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="p12">
				<div class="item-one">
					<label class="lab-item">备注</label>
					<input class="txt txt-validate" type="text" name="remarks" id="remarks" validType="maxlength[500]" value="${bean.remarks}"/>
				</div>
			</div>
		</div>
    <p class="row-btn center">
      <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存" />
      <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
    </p>
  </form>

</div>



</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
require(["pub"],function(){
	  
	  window.beforeAction = function(paramData,$form,data){
	    var BASE_PACKING_UNIT = data.basePackingUnitId;
	    var PACKING_UNIT = data.packingUnit;
	    
	    var OUTP_UNIT_ID = data.outpUnitId;
	    var INP_UNIT_ID = data.inpUnitId;
	    var PRICE_UNIT_ID = data.priceUnitId;
	    if(OUTP_UNIT_ID){
	    	if(OUTP_UNIT_ID!=BASE_PACKING_UNIT && OUTP_UNIT_ID!=PACKING_UNIT){
	    		$pop.alert('【门诊单位】必须是【基本包装单位】或【包装单位】');
	    		return;
	    	}
	    }
	    if(INP_UNIT_ID){
	    	if(INP_UNIT_ID!=BASE_PACKING_UNIT && INP_UNIT_ID!=PACKING_UNIT){
	    		$pop.alert('【住院单位】必须是【基本包装单位】或【包装单位】');
	    		return;
	    	}
	    }
	    if(PRICE_UNIT_ID){
	    	if(PRICE_UNIT_ID!=BASE_PACKING_UNIT && PRICE_UNIT_ID!=PACKING_UNIT){
	    		$pop.alert('【价格单位】必须是【基本包装单位】或【包装单位】');
	    		return;
	    	}
	    }
	    
	    if(!data.importSign){data.importSign='0';}
	    if(!data.highValueMaterialSign){data.highValueMaterialSign='0';}
	    if(!data.chargeSign){data.chargeSign='0';}
        if(!data.foreignFlag){data.foreignFlag='0';}
	    
	  return true;
	}


});

</script>
</body>

</html>

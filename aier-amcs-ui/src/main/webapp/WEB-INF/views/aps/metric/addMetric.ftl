<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>药品信息 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
</style>
</head>

<body>
<div class="wrapper pad-10">

  <form class="soform form-validate form-enter pad-t10" method="post" id="sbox" data-opt="{beforeCallback:'beforeAction'}" action="${base}/ui/amcs/aps/metric/updateMetric">
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
					<input class="txt txt-validate" type="text" name="metricName" id="metricName" validType="maxlength[50]" noNull="必填" value="${bean.metricName}"/>
				</div>
			</div>
		
		
		</div>
		<div class="row">
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">计算频率</label>
			        [@ui_select uiShowDefault=false class="drop easyui-combobox" value="${bean.ruleFrequency}" id="ruleFrequency" name="ruleFrequency" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.ScoreFrequencyTypeEnum" filterkey="firstSpell" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]
				</div>
			</div>
		
		
			<div class="p6">
				<div class="item-one">
					<label class="lab-item">拓展类型</label>
			        [@ui_select uiShowDefault=false class="drop easyui-combobox" value="${bean.ruleExtType}" id="ruleExtType" name="ruleExtType" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum" filterkey="firstSpell" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]
				</div>
			</div>
		
		</div>
		<div class="row">
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">修改人</label>
					<input class="txt txt-validate" type="text" name="modiferName" id="modiferName" validType="maxlength[50]" noNull="必填" value="${bean.modiferName}" readonly="readonly"/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">修改时间</label>
					<input class="txt txt-validate" type="text" name="modifyDate" id="modifyDate" validType="maxlength[50]" noNull="必填" value="${bean.modifyDate}" readonly="readonly"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">启停状态</label>
			        [@ui_select uiShowDefault=false class="drop easyui-combobox" value="${bean.usingSign}" id="usingSign" name="usingSign" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.UsingSignEnum" filterkey="firstSpell" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]
				</div>
			</div>
		</div>
		
		
		<div class="row">
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">计分规则类型</label>
        			[@ui_select value="${bean.allowItemType}" name="allowItemType" tag="@amcs@com.aier.cloud.api.amcs.enums.RuleItemTypeEnum" elf="" class="drop comb-dept easyui-combobox required" style="width:100%;" required=false uiShowDefault=false dataOptions="clearIcon:true,multiple:true,formatter : function(row){
			           return '<span clsss=s-multi><em class=i-s-chk></em>'+row.valueDesc+'</span>';
			        },editable:false" /]				
			    </div>
			</div>
			<div class="p8">
				<div class="item-one">
					<label class="lab-item">备注</label>
					<input class="txt txt-validate" type="text" name="remarks" id="remarks" validType="maxlength[200]" value="${bean.remarks}"/>
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

	  function init(){
			var url = '${base}/ui/amcs/aps/metric/getByCondition';
			$ajax.post(url,{metricId:'${id}'}).done(function (rst) {
				 	//$('#sbox').form('load',rst.data[0]);
                    //$('#manufacId').combogrid('setText', rst.data.manufacName)
		        })
		}
	  init();
	  
});

</script>
</body>

</html>

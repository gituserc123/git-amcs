<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>绩效手术分级配置 - 爱尔医疗云平台</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
</head>

<body>
<div class="pad-20">
  <form class="soform form-validate form-enter mar-b10" method="post" action="${base}/ui/aps/meritslevel/update">
  <input type="hidden" name="id" value="${(meritsLevel.id)!}"/>
    <div class="row">
      <div class="p6"><div class="item-one">
          <label class="lab-item">手术分类：</label>
          <input class="txt txt-validate so-pinyin" type="text" id="operationClassify" name="operationClassify" value="${(meritsLevel.operationClassify)!}" validType="remote['${base}/ui/aps/meritslevel/checkName?previousName=${(meritsLevel.operationClassify)!"-"}','operationClassify']" invalidMessage="手术分类已经存在" maxlength="200" noNull="请输入手术分类" />
      </div></div>
       <div class="p3"><div class="item-one">
          <label class="lab-item">手术分类ID：</label>
          <input class="txt txt-validate so-pinyin" type="text" id="operationClassifyCode" name="operationClassifyCode" value="${(meritsLevel.operationClassifyCode)!}" maxlength="30" noNull="请输入手术ID" />
      </div></div>
      <div class="p3"><div class="item-one">
          <label class="lab-item">手术病种：</label>
          [@ui_select id="diseaseType" name="diseaseType" tag="performance_diseases"
           style="width:100%;" dataOptions="editable:false,limitToList:true" value="${meritsLevel.diseaseType}" uiShowDefault="false"][/@ui_select]
      </div></div>
    </div>
    <div class="row">
      <div class="p3"><div class="item-one">
         <label class="lab-item">手术等级：</label>
         <select id="operationGrade" class="easyui-combobox" name="operationGrade" style="width:100%;">   
			    <option value="1" [#if meritsLevel.operationGrade == 1] selected="selected" [/#if]>1</option>   
			    <option value="2" [#if meritsLevel.operationGrade == 2] selected="selected" [/#if]>2</option>
			    <option value="3" [#if meritsLevel.operationGrade == 3] selected="selected" [/#if]>3</option>   
			    <option value="4" [#if meritsLevel.operationGrade == 4] selected="selected" [/#if]>4</option>     
		 </select>
      </div></div>
      <div class="p3"><div class="item-one">
         <label class="lab-item">优先级：</label>
         <input class="txt txt-validate" type="text" name="priority" placeholder="请输入顺序" value="${(meritsLevel.priority)!}" maxlength="4" validType="pInt" noNull="请输入优先级" invalidMessage="优先级为正整数" />
      </div></div>
      <div class="p6"><div class="item-one">
          <label class="lab-item">护理等级：</label>
           <select id="nursingGrade" class="easyui-combobox" name="nursingGrade" style="width:100%;">   
			    <option value="1" [#if meritsLevel.nursingGrade == 1] selected="selected" [/#if]>1</option>   
			    <option value="2" [#if meritsLevel.nursingGrade == 2] selected="selected" [/#if]>2</option>
			    <option value="3" [#if meritsLevel.nursingGrade == 3] selected="selected" [/#if]>3</option>   
			    <option value="4" [#if meritsLevel.nursingGrade == 4] selected="selected" [/#if]>4</option>     
			</select>
      </div></div>
      <div class="row">
      <div class="p6"><div class="item-one">
          <label class="lab-item">是否全麻+1：</label>
            <select id="anesthesiaSign" class="easyui-combobox" name="anesthesiaSign" style="width:100%;">   
			    <option value="1" [#if meritsLevel.anesthesiaSign == 1] selected="selected" [/#if]>是</option>   
			    <option value="0" [#if meritsLevel.anesthesiaSign == 0] selected="selected" [/#if]>否</option>   
			</select> 
      </div></div>
      <div class="p6"><div class="item-one">
          <label class="lab-item">启停标识：</label>
          [@ui_select id="usingSign" name="usingSign" tag="com.aier.cloud.basic.api.domain.enums.UsingSignEnum"
           style="width:100%;" dataOptions="editable:false,limitToList:true" value="${meritsLevel.usingSign}" uiShowDefault="false"][/@ui_select]
      </div></div>
    </div>
    </div>
    <p class="row-btn">
      <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存" />
      <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
    </p>
  </form>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
</body>
</html>


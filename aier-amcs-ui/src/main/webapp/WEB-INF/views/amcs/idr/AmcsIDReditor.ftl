<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=9"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
<title>传染病上报${idstate}-爱尔医院</title>
[#include '/WEB-INF/views/common/include_resources.ftl']
<style>
	
.tabs li a.tabs-inner{padding:0 45px;}
.tabCont{  }
.tabContHide{visibility: hidden;}
.mainfooter{padding:12px 10px 0;text-align: center;}
</style>
	
</head>
<body>


[#include '/WEB-INF/views/common/include_js.ftl']
 
<div class="searchHead bob-line">


			 
    <form id="sbox" class="soform form-enter">
    <label class="lab-val" style="margin-left:50px">
					疾病诊断：</label>
		<input class="drop diseasecode  w-220" id="diseasecode" name="diseasecode" /> 
       <label class="lab-inline">就诊号：</label>
       <input type="text" class="txt w-220  inline txt-validate"  id="patientName" name="patientName"
       placeholder="门诊号、住院号"  validType="maxlength[20,'搜索框不能超过20个字符']"/>
                                                     
        <!-- diagCodeCombo -->                                             
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="">
            查询
        </button>
        
    </form>
</div> 
<div class="searchHead bob-line">
	
	 
		
	<form id="infoForm" class="soform form-validate pad-t20 pad-r20 form-enter"
		  data-opt="" autocomplete="off"> 
		  <input class="hide" type="text"  id="orderId" name="orderId"   value="${orderId}"/> 
		<div  class="row"> 
			<div class="p4">
			<div class="item-one">
					<label class="lab-item">患者姓名</label>
					<input class="txt w-220  txt-validate w-220" type="text" name="patientname" id="patientname" validType="maxlength[50]" noNull="必填" value="${patientname}"/>
				</div></div>
				<div class="p4">
			
				<div class="item-one">
					<label class="lab-item">证件类型</label>
					<select class="drop easyui-combobox w-220 "  id="idcardtype" name="idcardtype" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true,required:true " value="${idcardtype}"> </select>  
				</div>
			</div> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">证件号码</label>
					<input class="txt w-220  txt-validate" type="text" name="idcardcode" id="idcardcode" validType="maxlength[20]" noNull="请填写证件号码" value="${idcardcode}"/>
				</div>
			</div>
			
		</div>
		
	 
		<div class="row">  
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">民族</label> 
					<select class="drop easyui-combobox w-220 "  id="nationcode" name="nationcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true,required:true " value="${nationcode}"> </select> 
                </div>
			</div>
		 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">工作单位</label>
					<input class="txt w-220  txt-validate" type="text" name="employerorgname" id="employerorgname" validType="maxlength[50]"   value="${employerorgname}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">传染病人群分类</label>
					<select class="drop easyui-combobox w-220 "  id="idrOccupationcode" name="idrOccupationcode" data-options=" required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${idrOccupationcode}"> </select> 
     			</div>
			</div>
		
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">慢病职业</label>
					<select class="drop easyui-combobox w-220 "  id="ncdOccupationcode" name="ncdOccupationcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${ncdOccupationcode}"> </select> 
   	 			</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死者生前职业</label>
					<select class="drop easyui-combobox w-220 "  id="codrisOccupationcode" name="codrisOccupationcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${codrisOccupationcode}"> </select> 
   
			 	</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">其他职业信息</label>
					<input class="txt w-220  txt-validate" type="text" name="otheroccupationname" id="otheroccupationname" validType="maxlength[30]"  value="${codrisOccupationcode}"/>
				</div>
			</div>
		</div>
		<div class="row">
			 
		
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">患者家属姓名</label>
					<input class="txt w-220  txt-validate" type="text" name="guardianname" id="guardianname" validType="maxlength[50]"   value="${guardianname}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">联系电话</label>
					<input class="txt w-220  txt-validate" type="text" name="telecom" id="telecom" validType="maxlength[18]"   value="${telecom}"  noNull="请填写联系电话"/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">报告单位县区</label>
						<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid required"  style="width:100%;" id="orgcountycode"
										name="orgcountycode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true,
										required:true,
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
		 			 
				</div>
			</div>
		</div> 
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现住地址类型</label>
					<select class="drop easyui-combobox w-220 "  id="livingaddressattributioncode" name="livingaddressattributioncode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${livingaddressattributioncode}"> </select> 
    			</div>
			</div> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现住地址</label> 
							<input class="drop easyui-combobox w-220 required"  id="livingaddresscode" name="livingaddresscode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="livingaddresscode"> </select> 
    		 
				
				
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">详细现住地址</label>
					<input class="txt w-220  txt-validate" type="text" name="livingaddressdetails" id="livingaddressdetails" validType="maxlength[50]" value="${livingaddressdetails}" noNull="必填"/>
				</div>
			</div>
		 
		</div>
		<div class="row">
			
	 
		
		<div class="p4"><div class="item-one">
					<label class="lab-item">出生日期</label>
					<input id="birthdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="birthdate" noNull="请选择出生日期"  value="${birthdate}"/>
				 
				</div></div>
			<div class="p4"><div class="item-one">
					<label class="lab-item">性别</label> 
			  		<select class="drop easyui-combobox w-220 required"  id="gendercode" name="gendercode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true" value="${gendercode}"></select> 
                </div></div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">国家或地区</label>
					<input class="txt w-220  txt-validate" type="text" name="country" id="country" validType="maxlength[50]"   value="${country}"/>
				</div>
			</div>
		</div>
		<div class="row">
			
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">婚姻状况</label>
					<select class="drop easyui-combobox w-220 "  id="maritalstatuscode" name="maritalstatuscode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${maritalstatuscode}"> </select>  
		 
				 
				</div>
			</div>
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">文化程度</label>
			 	<select class="drop easyui-combobox w-220 "  id="educationlevelcode" name="educationlevelcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${educationlevelcode}"> </select>  
		  		</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">诊断日期</label>
						<input id="diagnosisdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd HH:mm:ss'}" name="diagnosisdate" noNull="请选择诊断日期"  value="${diagnosisdate}"/>
				
			 	</div>
			</div>
		</div>
	 
		<div class="row">
			 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">备注</label>
					<input class="txt w-220  txt-validate" type="text" name="cardnotes" id="cardnotes" validType="maxlength[50]"   value="${cardnotes}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死亡日期</label>
					 <input id="deathdate" class="txt w-220    w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="deathdate" noNull="请选择死亡日期"  value="${deathdate}"/>
				 
				</div>
			</div>
		
		
			
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">户籍地类型</label>
							<select class="drop easyui-combobox w-220 "  id="domicileaddressattributioncode" name="domicileaddressattributioncode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${domicileaddressattributioncode}"> </select> 
    	
		 		</div>
			</div>
		 
			</div>
		<div class="row">
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">户籍地址</label>
						<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid required"  style="width:100%;" id="domicileaddresscode"
										name="domicileaddresscode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true,
									//	required:true,
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
					
		 			</div>
			</div> 
			
		 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">户籍所在村</label>
					<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid  "  style="width:100%;" id="registervillagecode"
										name="registervillagecode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true, 
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
			 		</div>
			</div>
		
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">户籍地详细地址</label>
					<input class="txt w-220  txt-validate" type="text" name="domicileadrressdetails" id="domicileadrressdetails" validType="maxlength[50]" value="${domicileadrressdetails}"/>
				</div>
			</div>
		
		
			</div>
		<div class="row">
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死者生前常住地</label>
					<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid "  style="width:100%;" id="lifetimezonecode"
										name="lifetimezonecode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true, 
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span>  
				</div>
			</div>
		
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">生前常住村</label>
					<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid "  style="width:100%;" id="lifetimevillagecode"
										name="lifetimevillagecode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true, 
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
		 			</div>
			</div>
		
		 	<div class="p4">
				<div class="item-one">
					<label class="lab-item">常住地址</label>
					<input class="txt w-220  txt-validate" type="text" name="lifetimeaddr" id="lifetimeaddr" validType="maxlength[50]"  value="${lifetimeaddr}"/>
				</div>
			</div>
		</div>
		<div class="row">
			 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">生前常住址类型</label>
					<select class="drop easyui-combobox w-220 "  id="lifetimeaddrtypecode" name="lifetimeaddrtypecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${lifetimeaddrtypecode}"> </select> 
    	 
				</div>
			</div> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死亡地点</label>
					<select class="drop easyui-combobox w-220 "  id="deathplacecode" name="deathplacecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${deathplacecode}"> </select> 
     
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">诊断类型</label>
						<select class="drop easyui-combobox w-220"  id="diagnosistypecode" name="diagnosistypecode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${diagnosistypecode}"> </select> 
      
				</div>
			</div>
			
		</div>
		<div class="row">   
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">住院号</label>
					<input class="txt w-220  txt-validate" type="text" name="hospitalnum" id="hospitalnum" validType="maxlength[50]"   value="${hospitalnum}"  readonly="readonly"/>
				</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">录入人</label>
					<input class="txt w-220  txt-validate" type="text" name="customer" id="customer" validType="maxlength[50]"   value="${customer}"  readonly="readonly"/>
				</div>
			</div>
				<div class="p4">
				<div class="item-one">
					<label class="lab-item">病例分类</label>
								<select class="drop easyui-combobox w-220 "  id="caseclassificationcode" name="caseclassificationcode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${caseclassificationcode}"> </select> 
       		</div>
			</div>
		</div>
		<div class="row">
		
		
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">其他具体疾病</label>
					<input class="txt w-220  txt-validate" type="text" name="otherdiseasename" id="otherdiseasename" validType="maxlength[50]" value=""/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">发病日期</label>
						<input id="onsetdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="onsetdate" noNull="请选择发病日期"  value="${onsetdate}"/>
			 		</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">密接者相同症状</label>
					 <select class="drop easyui-combobox w-220 "  id="closecontactssymptomcode" name="closecontactssymptomcode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${closecontactssymptomcode}"> </select> 
      			</div>
			</div>
		</div>
		<div class="row"> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">舒张压</label>
					<input class="txt w-220  txt-validate" type="num" name="lastxycdzsz" id="lastxycdzsz" validType="number"  value="${lastxycdzsz}"/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">收缩压</label>
					<input class="txt w-220  txt-validate" type="text" name="lastxycdzss" id="lastxycdzss" validType="number"   value="${lastxycdzss}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">空腹血糖测定值</label>
					<input class="txt w-220  txt-validate" type="text" name="lastkfxtcdz" id="lastkfxtcdz" validType="number"   value="${lastkfxtcdz}"/>
				</div>
			</div>
		
		</div>
		<div class="row">
			
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">餐后血糖测定值</label>
					<input class="txt w-220  txt-validate" type="text" name="lastchxtcdz" id="lastchxtcdz" validType="number"   value="${lastchxtcdz}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">转归</label>
						 <select class="drop easyui-combobox w-220 "  id="outcomecode" name="outcomecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${outcomecode}"> </select> 
       		</div>
			</div>
			
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">病理诊断</label>
					<input class="txt w-220  txt-validate" type="text" name="pathology" id="pathology" validType="maxlength[50]"  value="${pathology}"/>
				</div>
			</div>
			
		</div>
		<div class="row"> 
		 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">门诊号</label>
					<input class="txt w-220  txt-validate" type="text" name="patientnum" id="patientnum" validType="maxlength[50]"   value="${patientnum}"  readonly="readonly"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">病理号</label>
					<input class="txt w-220  txt-validate" type="text" name="pathologynum" id="pathologynum" validType="maxlength[50]"  value="${pathologynum}"/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">是否首发病例</label>
					 <select class="drop easyui-combobox w-220 "  id="firstcase" name="firstcase" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${firstcase}"> </select> 
       	 	</div>
			</div>
		</div>
		<div class="row">
			
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">ICD-O-3形态学</label>
					<input class="txt w-220  txt-validate" type="text" name="icdo3morphology" id="icdo3morphology" validType="maxlength[50]"   value="${icdo3morphology}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">ICD-O-3分化程度</label>
					<select class="drop easyui-combobox w-220 "  id="icdo3degree" name="icdo3degree" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${icdo3degree}"> </select> 
       
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肿瘤分期不详</label>
					<select class="drop easyui-combobox w-220 "  id="stagingunknown" name="stagingunknown" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${firstcase}"> </select> 
       	 		</div>
			</div>
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肿瘤分期T</label> 
							 <select class="drop easyui-combobox w-220 "  id="tumorstaget" name="tumorstaget" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${tumorstaget}"> </select>  
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肿瘤分期N</label>
					<input class="txt w-220  txt-validate" type="text" name="tumorstagen" id="tumorstagen" validType="number"  value="${tumorstagen}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肿瘤分期M</label>
					<input class="txt w-220  txt-validate" type="text" name="tumorstagem" id="tumorstagem" validType="number"   value="${tumorstagem}"/>
				</div>
			</div>
		
		
		</div>
		<div class="row">
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肿瘤分期0-Ⅳ</label>
					<input class="txt w-220  txt-validate" type="text" name="tumorstageiv" id="tumorstageiv" validType="number"  value="${tumorstageiv}"/>
				</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">入院时间</label>
						<input id="birthdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="admissiontime"  value="${admissiontime}"/>
			 			</div>
			</div> 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">出院时间</label>
							<input id="birthdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="dischargetime"  value="${dischargetime}"/>
			 
				</div>
			</div>
		
			</div>
		<div class="row">
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死亡原因</label>
								 <select class="drop easyui-combobox w-220 "  id="deathcause" name="deathcause" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${deathcause}"> </select> 
       
				</div>
			</div>
		<div class="p4">
				<div class="item-one">
					<label class="lab-item">具体死亡原因</label>
					<input class="txt w-220  txt-validate" type="text" name="specifideathcause" id="specifideathcause" validType="maxlength[50]"  value="${specifideathcause}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">心脑最高诊断依据</label>
					 <select class="drop easyui-combobox w-220 "  id="heartbraindiagnosis" name="heartbraindiagnosis" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true,multiple:true " value="${heartbraindiagnosis}"> </select> 
 
				</div>
			</div>
		 
		</div>
		<div class="row"> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肿瘤最高诊断依据</label>
						<select class="drop easyui-combobox w-220 "  id="tumordiagnosis" name="tumordiagnosis" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${tumordiagnosis}"> </select> 
      			</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">管理地区</label>
					<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid  "  style="width:100%;" id="managerzonecode"
										name="managerzonecode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true, 
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
					
				 
			 		</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">管理单位</label>
					<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid "  style="width:100%;" id="managerorgcode"
										name="managerorgcode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true, 
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
					
					
		 			</div>
			</div>
			
		</div>
		<div class="row">  
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">最高诊断单位</label>
					<select class="drop easyui-combobox w-220 "  id="highestdiagnosisunit" name="highestdiagnosisunit" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${highestdiagnosisunit}"> </select> 
    	</div>
			</div>
			
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">报告卡状态</label>
							<select class="drop easyui-combobox w-220 "  id="delflag" name="delflag" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${delflag}"> </select> 
  		 
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">用户ID</label>
					<input class="txt w-220  txt-validate" type="text" name="useridcreate" id="useridcreate" validType="maxlength[20]" value="${useridcreate}"/>
				</div>
			</div>
			
		</div>
	 
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">a直接死亡疾病</label>
					<input class="txt w-220  diagCodeCombo " type="text" style="width:100%;"   id="icdcodea" name="icdcodea"  value="${icdcodea}"  />
      		</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">a发病至死间隔</label> 
					<input class="txt w-220  txt-validate" type="text" name="intervaltimea" id="intervaltimea" validType="number" value="${intervaltimea}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">a时间间隔单位</label>
										<select class="drop easyui-combobox w-220 "  id="intervalunitcodea" name="intervalunitcodea" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${intervalunitcodea}"> </select> 
  		 
				</div>
			</div> 
		</div>
		<div class="row">
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">(b)引起(a)的疾病</label>
								<input class="txt w-220  diagCodeCombo " type="text" style="width:100%;"   id="icdcodeb" name="icdcodeb"  value="${icdcodeb}"  />
     
		 			</div>
			</div> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">b发病到死间隔</label>
					<input class="txt w-220  txt-validate" type="text" name="intervaltimeb" id="intervaltimeb" validType="number"  value="${intervaltimeb}"/>
				</div>
			</div>
				<div class="p4">
				<div class="item-one">
					<label class="lab-item">b时间间隔单位</label>
					 <select class="drop easyui-combobox w-220 "  id="intervalunitcodeb" name="intervalunitcodeb" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${intervalunitcodeb}"> </select> 
  		  		</div>
			</div>
		</div>
		<div class="row">
		
		
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">I引起(b)的疾病</label>
						 <input class="txt w-220  diagCodeCombo " type="text" style="width:100%;"   id="icdcodec" name="icdcodec"  value="${icdcodec}"  />
      			</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">c发病到死间隔</label>
					<input class="txt w-220  txt-validate" type="text" name="intervaltimec" id="intervaltimec" validType="number"  value="${intervaltimec}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">c时间间隔单位</label>
								 <select class="drop easyui-combobox w-220 "  id="intervalunitcodec" name="intervalunitcodec" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${intervalunitcodec}"> </select> 
  		 	</div>
			</div>
		 
		</div>
		<div class="row">
		
		 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">d引起c疾病</label>
									 <input class="txt w-220  diagCodeCombo " type="text" style="width:100%;"   id="icdcoded" name="icdcoded"  value="${icdcoded}"  />
       			</div>
			</div>
		 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">d发病到死间隔</label>
					<input class="txt w-220  txt-validate" type="text" name="intervaltimed" id="intervaltimed" validType="number"   value="${intervaltimed}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">d时间间隔单位</label>
							 <select class="drop easyui-combobox w-220 "  id="intervalunitcoded" name="intervalunitcoded" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${intervalunitcoded}"> </select> 
  	 			</div>
			</div>
		
		 
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">其它疾病诊断1</label>
					 <input class="txt w-220  diagCodeCombo " type="text" style="width:100%;"   id="icdcodeother" name="icdcodeother"  value="${icdcodeother}"  />
        			</div>
			</div>
		
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">根本死亡原因</label>
						 <input class="txt w-220  diagCodeCombo  " type="text" style="width:100%;"   id="basicicdcode" name="basicicdcode"  value="${basicicdcode}"  />
     				</div>
			</div>
		<div class="p4">
				<div class="item-one">
					<label class="lab-item">生前诊断单位</label>
								<select class="drop easyui-combobox w-220 "  id="diagnosticunitcode" name="diagnosticunitcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${diagnosticunitcode}"> </select> 
 
			 		</div>
			</div>
		 
		</div>
		<div class="row"> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">妊娠终止后42天内</label>
					 <select class="drop easyui-combobox w-220 "  id="womantypecode" name="womantypecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${womantypecode}"> </select> 
  				</div>
			</div>
		
	
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">生前最高诊断依据</label>
						<select class="drop easyui-combobox w-220 "  id="diagnosticbasiscode" name="diagnosticbasiscode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${diagnosticbasiscode}"> </select> 
  		 		</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">医师签名</label>
					<input class="txt w-220  txt-validate" type="text" name="doctorname" id="doctorname" validType="maxlength[50]"  value="${doctorname}"/>
				</div>
			</div>
		</div>
		<div class="row">
	 
		
		

			<div class="p4">
				<div class="item-one">
					<label class="lab-item">民警签名</label>
					<input class="txt w-220  txt-validate" type="text" name="policename" id="policename" validType="maxlength[50]"  value="${policename}" />
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">填表日期</label>
						<input id="fillcarddate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="fillcarddate"  value="${fillcarddate}"/>
				 
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死者生前病史</label>
					<input class="txt w-220  txt-validate" type="text" name="symptoms" id="symptoms" validType="maxlength[200]"  value="${symptoms}"/>
				</div>
			</div> 
		</div>
		<div class="row">
 
		
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">被调查者姓名</label>
					<input class="txt w-220  txt-validate" type="text" name="investigatedname" id="investigatedname" validType="maxlength[50]"   value="${investigatedname}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">与死者关系</label>
					<input class="txt w-220  txt-validate" type="text" name="relationship" id="relationship" validType="maxlength[10]" value="${relationship}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">被调查者联系电话</label>
					<input class="txt w-220  txt-validate" type="text" name="investigatedtel" id="investigatedtel" validType="maxlength[12]"  value="${investigatedtel}"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">死因推断</label>
					<input class="txt w-220  txt-validate" type="text" name="infercause" id="infercause" validType="maxlength[50]"   value="${infercause}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">调查者签名</label>
					<input class="txt w-220  txt-validate" type="text" name="investigator" id="investigator" validType="maxlength[50]"  value="${investigator}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">调查日期</label>
								<input id="investigatedate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="investigatedate"   value="${investigatedate}"/>
		 			</div>
			</div>
		
		
		</div>
		<div class="row">
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">报告人ID</label>
					<input class="txt w-220  txt-validate" type="text" name="entrypeoplename" id="entrypeoplename" validType="maxlength[50]"  value="${entrypeoplename}"/>
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">审核时间</label>
			 		<input id="validtime" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="validtime"   value="${validtime}"/>
	 		</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">订正状态</label>
				  <select class="drop easyui-combobox w-220 "  id="correctionstate" name="correctionstate" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${correctionstate}"> </select> 
  	 			</div>
			</div>
		
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">审核人</label>
					<input class="txt w-220  txt-validate" type="text" name="auditpeoplename" id="auditpeoplename" validType="maxlength[50]"   value="${auditpeoplename}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">第一次审核时间</label>
							<input id="firstvalidtime" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="firstvalidtime"   value="${firstvalidtime}"/>
				 
				</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">卡片状态</label>
		  <select class="drop easyui-combobox w-220 "  id="auditstate" name="auditstate" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${auditstate}"> </select> 
  	 	
			 
				</div>
			</div>
		</div>
		<div class="row">
			
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">删除类型</label>
							  <select class="drop easyui-combobox w-220 "  id="deletingtypecode" name="deletingtypecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${deletingtypecode}"> </select> 
  	 	 
				</div>
			</div> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">具体删除原因</label>
					<input class="txt w-220  txt-validate" type="text" name="deletingreasondetails" id="deletingreasondetails" validType="maxlength[50]" value="${deletingreasondetails}"/>
				</div>
			</div>
				<div class="p4">
				<div class="item-one">
					<label class="lab-item">XML流水号</label>
					<input class="txt w-220  txt-validate" type="text" name="eventid" id="eventid" validType="maxlength[50]" value="${eventid}"/>
				</div>
			</div>
		
		</div>
		<div class="row">
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">临床严重程度</label>
										  <select class="drop easyui-combobox w-220 "  id="newpneumseveritycode" name="newpneumseveritycode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${newpneumseveritycode}"> </select> 
  	 	 		</div>
			</div>
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">输入来源地</label>
					 <select class="drop easyui-combobox w-220 "  id="placecode" name="placecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${placecode}"> </select> 
  	 	 		 </div>
			</div>
				<div class="p4">
				<div class="item-one">
					<label class="lab-item">输入病例类型</label>
												  <select class="drop easyui-combobox w-220 "  id="foreigntypecode" name="foreigntypecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${foreigntypecode}"> </select> 
  	 			</div>
			</div>
		</div>
		<div class="row">
		
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">其他输入来源地</label>
						 <select class="drop easyui-combobox w-220 "  id="placeother" name="placeother" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${placeother}"> </select> 
  	 	 		</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">出院日期</label>
						<input id="outhosdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="outhosdate"   value="${outhosdate}"/>
			 		</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">收治转诊状态</label>
					<select class="drop easyui-combobox w-220 "  id="mgmtstatuscode" name="mgmtstatuscode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${mgmtstatuscode}"> </select> 
  	 	 			</div>
			</div>
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">收治转诊机构</label>
					<span class="fl w-220" style="margin-right:2%;">
										<select class="easyui-combogrid "  style="width:100%;" id="currmgmtorgcode"
										name="currmgmtorgcode"
										data-options="
										pagination: true,
										delay: 800,    	  // 输入时延迟查询毫秒数
										mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
										panelWidth:'435px',
										panelHeight:'auto',
										panelMaxHeight:'250px',
										//limitToList:true,
										fitColumns:true,
										clearIcon:true, 
										idField:'districtCode',
										textField:'aName',
										url:'${base}/ui/amcs/idr/amcsIDReditor/getList',
										queryParams: {
										},
										onChange : function(newV,oldV){
											if(newV===''){
											  $('#provinceName').val('')
											  $('#cityName').val('')
											  $('#districtName').val('')
											}
										},
										onSelect:function(index, row){
										  $('#provinceName').val(row.provinceName)
										  $('#cityName').val(row.cityName)
										  $('#districtName').val(row.districtName)
										},
										columns:[[
										{title:'省',field:'provinceName',width:'100px',titletip:true,align:'center'},
										{title:'市',field:'cityName',width:'120px',titletip:true,align:'center'},
										{title:'区/县',field:'districtName',width:'210px',titletip:true,align:'center'},
										]]"
										></select>
										<input class="txt w-220 " type="hidden" id="provinceName" name="provinceName" value="" />
										<input class="txt w-220 " type="hidden" id="cityName" name="cityName" value="" />
										<input class="txt w-220 " type="hidden" id="districtName" name="districtName" value="" />
										</span> 
		  		</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">ORF1ab靶标Ct值</label> 
					<input class="txt w-220  txt-validate" type="text" name="ncvorfctv" id="ncvorfctv" validType="number"   value="${ncvorfctv}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">N靶标Ct值</label>
					<input class="txt w-220  txt-validate" type="text" name="ncvnctv" id="ncvnctv" validType="number"   value="${ncvnctv}"/>
				</div>
			</div>
		
		
			
		</div>
		<div class="row">
		<div class="p4">
				<div class="item-one">
					<label class="lab-item">死亡有关新冠感染</label>
							<select class="drop easyui-combobox w-220 "  id="isdeadbythiscode" name="isdeadbythiscode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${isdeadbythiscode}"> </select> 
  	 	   		</div>
			</div>
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">直接死亡诊断</label>
						<select class="drop easyui-combobox w-220 "  id="symptomscode" name="symptomscode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${symptomscode}"> </select>  
				</div>
			</div>
		 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">卡片ID</label>
					<input class="txt w-220  txt-validate" type="text" name="cardid" id="cardid" validType="maxlength[50]"  readonly="readonly" value="${cardid}"/>
				</div>
			</div>
			 
		
		</div> 
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">报告日期</label>
					<input id="cardfillingtime" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="cardfillingtime" noNull="请选择报告日期"  value="${cardfillingtime}"/>
			 
				</div>
			</div>
			 <div class="p4">
				<div class="item-one">
					<label class="lab-item">卡片类型</label>
					 <select class="drop easyui-combobox w-220 "  id="key1" name="key1" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true "noNull="请选择卡片类型"  value="${key1}"> </select>  
		
				</div>
			</div>
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">填卡医生姓名</label>
					<input class="txt w-220  txt-validate" type="text" name="fillingdoctorname" id="fillingdoctorname" validType="maxlength[50]" noNull="请填写填卡医生姓名" value="${fillingdoctorname}"/>
				</div>
			</div
		  
		
		</div> 
	</form>

</div> 
<!-- 切换成不同界面 -->

 
 <!--5.2	艾滋病/HIV等附卡采集数据元-->
 
<div class="tabCont tabCont-0 amcsIdrAids tabContHide"  >
    
	<form id="amcsIdrAids" class="soform form-validate pad-t20 pad-r20 form-enter amcsIdrAids"  data-opt="">
 
			<input class="hide" type="text"  id="amcsIdrAidsid" name="amcsIdrAidsid"   value="${iiid}"/> 
		 
		<div class="row">  
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">样本来源</label>
					<select class="drop easyui-combobox w-220 "  id="specimensourcecode" name="specimensourcecode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${specimensourcecode}"> </select>  
		 		</div>
			</div>
		  
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">最有可能感染途径</label>
					<select class="drop easyui-combobox w-220 required"  id="possibleinfectionroutecode" name="possibleinfectionroutecode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${possibleinfectionroutecode}"> </select>  
		 		</div>
			</div>
		
		 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">样本来源其它</label>
					<input class="txt w-220  txt-validate" type="text" name="othersamplesource" id="othersamplesource" validType="maxlength[40]"  value="${othersamplesource}"/>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">接触史</label>
					<select class="drop easyui-combobox w-220"  id="contacthistorycode" name="contacthistorycode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${contacthistorycode}"> </select>  
			 	</div>
			</div> 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">接触史其它</label>
					<input class="txt w-220  txt-validate" type="text" name="othercontacthistory" id="othercontacthistory" validType="maxlength[40]"   value="${othercontacthistory}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">感染途径其它</label>
					<input class="txt w-220  txt-validate" type="text" name="otherinfectionroute" id="otherinfectionroute" validType="maxlength[100]"  value="${othercontacthistory}"/>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">艾滋病诊断日期</label>
						<input id="birthdate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="aidsdiagnosisdate" noNull="请选择艾滋病诊断日期"  value="${aidsdiagnosisdate}"/>
			 		</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">共用注射器人数</label>
					 
					<input class="txt w-220  txt-validate" type="text" name="injectiontogethernum" id="injectiontogethernum"  validType="number"   value="${injectiontogethernum}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">异性性行为人数 </label>
					<input class="txt w-220  txt-validate" type="text" name="nonmaritalsexnum" id="nonmaritalsexnum" validType="number"   value="${nonmaritalsexnum}"/>
				</div>
			</div>
		</div>
		
      <div class="row"> 
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">男男性行为人数 </label>
					<input class="txt w-220  txt-validate" type="text" name="homosexualsexnum" id="homosexualsexnum" validType="number"   value="${homosexualsexnum}"/>
				</div>
			</div>
		 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">实验室检测结论</label>
							<select class="drop easyui-combobox w-220 required"  id="labortestconclusioncode" name="labortestconclusioncode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${labortestconclusioncode}"> </select>  
	 			</div>
			 </div>
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">检测阳性日期 </label>
					<input id="confirmedtestpositivedate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="confirmedtestpositivedate"   value="${confirmedtestpositivedate}"/>
			 	</div>
			</div>
		
		</div>
		
		<div class="row">		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">性病史</label>
						 <select class="drop easyui-combobox w-220"  id="venerealhistorycode" name="venerealhistorycode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${venerealhistorycode}"> </select>  
	  		 </div>
			</div>
	 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">沙眼衣原体感染</label>
							<select class="drop easyui-combobox w-220"  id="chlamydialtrachomatiscode" name="chlamydialtrachomatiscode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${chlamydialtrachomatiscode}"> </select>  
	  			</div>
			</div> 
	 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">事件状态 </label> 
					 <select class="drop easyui-combobox w-220 "  id="status" name="status" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${status}" readonly="readonly"> </select>  
	  	 		</div>
			</div> 
		</div>  
		
  	 </form>
</div>
 <!-- 5.4	AFP病附卡采集数据元   amcsIdrAfp -->
	<div class="tabCont tabCont-3 amcsIdrAfp soform tabContHide">
	<form id="amcsIdrAfp"> 

		<div class="row">
		 
			<input class="hide" type="text"  id="amcsIdrAfpid" name="amcsIdrAfpid"   value="${iiid}"/>  
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">病人所属地类型</label> 
						 <select class="drop easyui-combobox w-220"  id="patientresidencetypecode" name="patientresidencetypecode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${patientresidencetypecode}"  > </select>  
	  		</div>
			</div>
		 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">麻痹日期</label>
					<input id="palsydate" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="palsydate"  noNull="麻痹日期必填"  value="${palsydate}"/>
		 
				</div>
			</div>
		</div>
		<div class="row"> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">来现就诊地日期</label>
					<input class="txt w-220  txt-validate" type="text" name="treatmentlanddate" id="treatmentlanddate" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现就诊地住址类型编码</label>
					<input class="txt w-220  txt-validate" type="text" name="treatmentlandtypecode" id="treatmentlandtypecode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现就诊地住址类型名称</label>
					<input class="txt w-220  txt-validate" type="text" name="treatmentlandtypename" id="treatmentlandtypename" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现就诊地住址国标编码</label>
					<input class="txt w-220  txt-validate" type="text" name="treatmentlandzonecode" id="treatmentlandzonecode" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现就诊地住址国标名称</label>
					<input class="txt w-220  txt-validate" type="text" name="treatmentlandzonename" id="treatmentlandzonename" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">现就诊地详细住址</label>
					<input class="txt w-220  txt-validate" type="text" name="addressdetails" id="addressdetails" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">麻痹症状</label>
					<input class="txt w-220  txt-validate" type="text" name="palsysymptom" id="palsysymptom" validType="maxlength[50]" noNull="必填" value=""/>
				</div>
			</div>
		</div>
		 
		<div class="row"> 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">事件状态</label>
					 <select class="drop easyui-combobox w-220 "  id="status" name="status" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${status}" readonly="readonly"> </select>  
	   			</div>
			</div>  
		</div> 
	
	</form>	
	</div>   
	
	<!-- 5.3	手足口病附卡采集数据元   amcsIdrHfmd -->
	<div class="tabCont tabCont-1 tabContHide soform amcsIdrHfmd">
	   <form id="amcsIdrHfmd">  
			<input class="hide" type="text"  id="amcsIdrHfmdid" name="amcsIdrHfmdid"   value="${iiid}"/>    
		
		
		<div class="row"> 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">重症患者编码</label>
					 <select class="drop easyui-combobox w-220 "  id="intensivepatientcode" name="intensivepatientcode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${intensivepatientcode}"> </select>  
	   			</div>
			</div> 
			
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">实验室结果</label>
							 <select class="drop easyui-combobox w-220"  id="labortestresultcode" name="labortestresultcode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${labortestresultcode}"> </select>  
	 	 
				 </div> 
			 </div>
			 
			 <div class="p4">
				<div class="item-one">
					<label class="lab-item"> 事件状态</label>
								 <select class="drop easyui-combobox w-220 "  id="status" name="status" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${status}" readonly="readonly"> </select>  
 				</div>
			</div>
			
			</div>  
		 </form> 
	</div>
	<!-- 5.5	HB乙肝副卡采集数据元 amcsIdrHb-->
	<div class="tabCont tabCont-2 tabContHide amcsIdrHb soform">
	 <form id="amcsIdrHb"> 
			<input class="hide" type="text"  id="amcsIdrHbid" name="amcsIdrHbid"   value="${iiid}"/> 
	
		<div class="row"> 
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">HBsAg阳性时间</label>
					<select class="drop easyui-combobox w-220"  id="hbsagtestpositivetypecode" name="hbsagtestpositivetypecode" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${hbsagtestpositivetypecode}"  > </select>  
	   				</div>
			</div>
		
		 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">首次症状时间</label>
					 <input id="firsthbsymptomtime" class="txt w-220  txt-validate w-220 so-date" data-opt="{maxDate:'${sysdate}',format:'yyyy-MM-dd'}" name="firsthbsymptomtime"   value="${firsthbsymptomtime}"/>
				 
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">乙肝本次ALT</label>
					<input class="txt w-220  txt-validate" type="text" name="lastaltvalue" id="lastaltvalue" validType="maxlength[50]"  value="${lastaltvalue}"/>
				</div>
			</div>
			 
		</div>
		<div class="row">
	 
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">抗-HBcIgM1：1000</label>
					 <select class="drop easyui-combobox w-220 "  id="hbcabigmtestcode" name="hbcabigmtestcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${hbcabigmtestcode}"  > </select>  
 		
	 			</div>
			</div>
	
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">肝穿检测结果</label>
							 <select class="drop easyui-combobox w-220 "  id="liverpuncturetestcode" name="liverpuncturetestcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${liverpuncturetestcode}"  > </select>  
 		 			</div>
			</div>
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">HBsAg阴转,抗HBs阳转</label>
					 <select class="drop easyui-combobox w-220 "  id="hbsagnorhbsabpcode" name="hbsagnorhbsabpcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${hbsagnorhbsabpcode}"  > </select>  
 		 	 		</div>
			</div>
		 
		</div>
	 
		<div class="row">
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">无症状/不详</label> 
					<input class="txt w-220  txt-validate" type="text" name="firsthbsymptomunsurecode" id="firsthbsymptomunsurecode" validType="maxlength[50]"   value="${firsthbsymptomunsurecode}"/>
				</div>
			</div>
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">HBsAg阳性时间</label> 
							<select class="drop easyui-combobox w-220 required"  id="hbsagcode" name="hbsagcode" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${hbsagcode}"  > </select>  
	   	 			</div>
			</div>
		 
		
		
			<div class="p4">
				<div class="item-one">
					<label class="lab-item">事件状态 </label>
					 <select class="drop easyui-combobox w-220 "  id="status" name="status" data-options="valueField:'valueCode',textField:'valueDesc',clearIcon:true " value="${status}" readonly="readonly"> </select>  
	   			</div>
			</div>
		</div> 
		</form>
	</div> 
	
	<div class="mainfooter form-validate">
			[@shiro.hasPermission name = "AmcsIDReditor:new"]
		   <input type="button" class="btn btn-primary btn-new"  value="新增"/>
		   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:save"]
			<input type="button" class="btn btn-primary btn-save"  value="保存"/>
			   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:audit"]
		<input type="button" class="btn btn-primary btn-audit"  value="审核"/>
			   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:auditUn"]
		<input type="button" class="btn btn-primary btn-auditUn"  value="反审核"/>
		   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:uploading"]
			<input type="button" class="btn btn-primary btn-uploading"  value="上传初报"/> 
		   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:uploadMod"]
			<input type="button" class="btn btn-primary btn-uploadMod"   value="上传修订"/>
			   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:uploadAud"]
		<input type="button" class="btn btn-primary btn-uploadAud"   value="上传审核"/>
			   [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:uploadRes"]
		<input type="button" class="btn btn-primary btn-uploadRes"   value="上传恢复"/>
		      [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:uploadDel"]
		 <input type="button" class="btn btn-primary btn-uploadDel"   value="上传删除"/>
		     [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:uploadSeach"]
		  <input type="button" class="btn btn-primary btn-uploadSeach"   value="上传查询"/>
		    [/@shiro.hasPermission]
		   [@shiro.hasPermission name = "AmcsIDReditor:delete"]
		 <input type="button" class="btn btn-primary btn-del" value="删除"/>
		    [/@shiro.hasPermission] 
		   <input type="button" class="btn btn-cancel"   value="关闭"/>
	</div>
	</body>
<script type="text/javascript">
	requirejs(["pub"], function () {
		var formPop = null;
		var gridData = {};
  	 	var curId = '${id}';
  	 	
		$("#gendercode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=SexEnum"});
		$("#nationcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=NationEnum"});
		$("#idrOccupationcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=IDROccupationCodeEnum"});
		$("#codrisOccupationcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=OccupationcodeEnum"});
		$("#ncdOccupationcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=OccupationcodeEnum"});
		$("#livingaddressattributioncode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AddressCodeEnum"});
	  
		$("#idcardtype").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=IDCardTypeEnum"});
		$("#key1").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=KeyEnum"});
		 
		  
		$("#maritalstatuscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=MaritalstatuscodeEnum"});
		$("#educationlevelcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=EducationlevelcodeEnum"});
	    $("#domicileaddressattributioncode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AddressCodeEnum"});
	 	$("#lifetimeaddrtypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AddressCodeEnum"});
		$("#deathplacecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=DeathPlaceEnum"});
		$("#diagnosistypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=DiagnosistypeEnum"});
		$("#caseclassificationcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=CaseClassificationEnum"});
		$("#closecontactssymptomcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=CloseContactsSymptomEnum"});
		$("#outcomecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=OutcomeEnum"});
		$("#firstcase").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNoEnum"});
		$("#isdeadbythiscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNoEnum"});
		$("#intensivepatientcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNoEnum"}); 
		$("#foreigntypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNoEnum"});
		$("#womantypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNo2Enum"});
		$("#stagingunknown").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=YesNoEnum"});
		$("#deathcause").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=DeathcauseEnum"}); 
		$("#icdo3degree").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=Icdo3DegreeEnum"});
		$("#tumorstaget").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=TumorStageTEnum"});
		$("#tumordiagnosis").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=TumorDiagnosisEnum"}); 
		$("#heartbraindiagnosis").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HeartbrainDiagnosisEnum"});
		$("#highestdiagnosisunit").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HighestDiagnosisUnitEnum"});
	 	$("#diagnosticunitcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HighestDiagnosisUnitEnum"});
		$("#delflag").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=DelflagEnum"});
		$("#intervalunitcodea").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=IntervalUnitEnum"});
		$("#intervalunitcodeb").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=IntervalUnitEnum"});
		$("#intervalunitcodec").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=IntervalUnitEnum"});
		$("#intervalunitcoded").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=IntervalUnitEnum"});
		$("#diagnosticbasiscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=DiagnosticbasisEnum"});
		$("#correctionstate").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=CorrectionStateEnum"});
		$("#auditstate").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AuditStateEnum"});
		$("#status").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AuditStateEnum"});
		$("#deletingtypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=AuditStateEnum"});
		$("#newpneumseveritycode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=NewPneumSeverityEnum"});
		$("#placecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=PlaceCodeEnum"});
		$("#placeother").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=PlaceCodeEnum"});
		$("#mgmtstatuscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=MgmtStatusEnum"});
		$("#symptomscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=SymptomsEnum"});
		$("#specimensourcecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=SpecimenSourceEnum"});
		$("#possibleinfectionroutecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=PossibleInfectionRouteEnum"});
		$("#contacthistorycode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=ContactHistoryEnum"});
		$("#labortestconclusioncode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=LaborTestConclusionEnum"});
		$("#venerealhistorycode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=VenerealHistoryEnum"});
		$("#chlamydialtrachomatiscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=ChlamydialtrachomatisEnum"});
		$("#patientresidencetypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=PatientResidenceTypeEnum"});
		$("#labortestresultcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=LaborTestResultEnum"});
		$("#hbsagtestpositivetypecode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HbsagtestpositivetypeEnum"});
		$("#hbsagcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HbsagtestpositivetypeEnum"});
		$("#hbcabigmtestcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HBcAbIgMTestEnum"});
		$("#liverpuncturetestcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=LiveRpunctureTestEnum"});
		$("#hbsagnorhbsabpcode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getEnum?valueCode=HBcAbIgMTestEnum"});
		 
		  $("#livingaddresscode").combobox({url:"${base}/ui/amcs/idr/amcsIdrDictType/getTypeCodeForList?typeCode=addresscode&usingSign=1"});
	
	
	  $('#idcardtype').combobox('setValue',"1");
	  $('#nationcode').combobox('setValue',"01");
	  $('#livingaddressattributioncode').combobox('setValue',"1");
	
	
	 //$('#cardfillingtime').val(date());
	
	
 		
 	  $(".so-search").click(function () { 
 	  
 			if(!$("#patientName").val())
 			{
 				//$pop.alert('请输入门诊号、住院号再查询！'); 
    	  		return;
    	  	}
 	  		 $ajax.post('${base}/ui/amcs/idr/amcsIdrDictType/getPatient',{patientName:$("#patientName").val()}).done(function (rst) {
     	       // window.console && console.log(rst);
     	     //  debugger; 
     	       		if(rst) { 
     	       			$('#infoForm').form('load',rst);
     	       		} else { 
     	       			$pop.alert('找不到患者！'); 
     	       		}  
  	 	 
               
          }); 
 	  });	
 		
		$('#diseasecode').combotree({
			idFiled: 'valueCode',
			parentField: 'leveles',
			textFiled: 'valueDesc',
			animate : true, 
	        panelWidth:'200px',
	        panelHeight:'auto',
	        panelMaxHeight:'200px',
	        lines : true,
	        clearIcon :true,
	        onlyLeafCheck : true,
	        flatData: true,
	        url:'${base}/ui/amcs/idr/amcsIdrDictType/getTypeCodeForList?typeCode=DiseaseName&usingSign=1&leveless=1111',
	        onBeforeSelect: function(node){
	        	if(node.children){
	        		return false;
	        	}
	        	
	        },
	        onSelect:function (node) { 
	      //  console.log( $('.diseasecode').combobox('getValue'));
	         
	         //debugger;
     				switchTab(); 
     			if(node.url) {  
		        	var tag = node.url;
		        	$("."+tag).removeClass("tabContHide");
     			}
     			//lrz== "乙肝"
     			if(node.id=="1651481410614157348"){
     			 $('#firsthbsymptomtime').validatebox({ required:true });
     			}
     			else{
     			  $('#firsthbsymptomtime').validatebox({ required:false });
     			}
	        	 
	        },
	        onLoadSuccess:function() { 
	            if($('#diseasecode').val()=="1651481410614157348"){
     			 $('#firsthbsymptomtime').validatebox({ required:true });
     			}
     			else{
     			  $('#firsthbsymptomtime').validatebox({ required:false });
     			}
	        }
		});
		
		
		
		var switchTab = function (){
		   $(".tabCont").addClass("tabContHide");
		} 
  	  
  	 	var doAction = function () { 
  	 		 if('${curData}'=='') { 
    			return; 
    		}
  	 	  	var curData = JSON.parse('${curData}'); 
  	 		if(curData){ 
  	 			$('#infoForm').form('load',curData );
  	 			$('#diseasecode').combotree('setValue',curData.diseasecode);
  	 			 debugger;
  	 	    	var divIds = ["amcsIdrAfp","amcsIdrAids","amcsIdrHb","amcsIdrHfmd"]
  	 	    	for(var i in divIds) { 
  	 	    		if(curData[divIds[i]]) {  
		        		$("."+divIds[i]).removeClass("tabContHide"); 
		        	 
		        		$('#'+divIds[i]).form('load',curData[divIds[i]] );
  	 	    		}
  	 	    	}
  	 		 //lrz
<!--   	 		 	 debugger; -->
<!--   	 		 	if($('#diseasecode').text== "乙肝"){ -->
<!--      			 $('#firsthbsymptomtime').validatebox({ required:true }); -->
<!--      			} -->
<!--      			else{ -->
<!--      			  $('#firsthbsymptomtime').validatebox({ required:false }); -->
<!--      			} -->
  	 		}
  	 		
  	 	}
		 
		  doAction()
	 
    	  	 

	 /**准备数据*/
    var prepareData = function(){
   		var $validataForms = null;
    	$(".tabCont").each(function () { 
    		if($(this).hasClass("tabContHide")) { 
    			return; 
    		}
    	 
    	    $validataForms = $(this).find("form"); 
    		
    	}) 
    	
    	
         var $validate =  $("#infoForm").form('validate'); 
         var $validate2 =  $validataForms && $validataForms.form('validate');
         
        // debugger;
         if($validataForms != null ) { 
         	 if(!$validate || !$validate2) {
	    	 	return console.log("表单验证不通过");
	    	 }
         } else { 
         	 if(!$validate ) {
	    	 	return console.log("表单验证不通过");
	    	 }
         }
    
    	
    
        gridData = $("#infoForm").sovals();
      
        if($validataForms){
    		var formID = $validataForms && $validataForms.attr("id");
    		var form2 = $validataForms && $validataForms.sovals(); 
    	 	gridData[formID]=form2; 
  
    	// gridData = { apply:gridData,formID:form2 };
    	}
      
    	    //报告单位所属县区名称
			 
   	    gridData.orgcountyname = $('#orgcountycode').combobox('getText');
   	    //// 现住地址名称
   	    gridData.livingaddressname = $('#livingaddresscode').combobox('getText'); 
   	    // 户籍地地址
   	    gridData.domicileaddressname = $('#domicileaddresscode').combobox('getText');
   	  // 死者生前常住地址国标名称
   	   gridData.lifetimezonecodename = $('#lifetimezonecode').combobox('getText');
   	  // 户籍所在村名称
   	   gridData.registervillagecodename = $('#registervillagecode').combobox('getText');
   	  //管理地区名称
     gridData.managerzonecodename = $('#managerzonecode').combobox('getText');
   	  //管理单位
   	  gridData.managerorgcodename = $('#managerorgcode').combobox('getText');
   	  
   	    // 生前常住村名称
   	   gridData.lifetimevillagecodename = $('#lifetimevillagecode').combobox('getText');
     	    // 直接死亡原因疾病名称
   	   gridData.causea = $('#icdcodea').combobox('getText');
   
     gridData.causeb = $('#icdcodeb').combobox('getText');
     gridData.causec = $('#icdcodec').combobox('getText');
     gridData.caused = $('#icdcoded').combobox('getText');
     gridData.causeother = $('#icdcodeother').combobox('getText');
     gridData.basiccause = $('#basicicdcode').combobox('getText');
	 gridData.id=curId;
	 // debugger; 
    //  $('#diseasecode').combotree('getValue');
       
      // console.log(gridData.id);
        //console.log(gridData.orderid );
     // debugger;
       
    //    console.log( $('#diseasecode'));
    
        gridData.diseasecode = $('#diseasecode').combobox('getValue');
   	    gridData.diseasename = $('#diseasecode').combobox('getText');
   	    gridData.diseasecause = $('#diseasecode').combobox('getValue');
   	    // debugger;
   	    if(  gridData.diseasecode==null || gridData.diseasecode.length<1)
   	    {
   	   		$pop.alert('疾病诊断不能为空');
   	    	return false;
   	    }
   	   
   	 	//gridData.toDeptName = $('#toDeptId').combobox('getText');
   		//gridData.maker = $('#maker').combobox('getValue');
   		//gridData.receiver = $('#receiver').combobox('getValue');
  		//gridData.receiverName = $('#receiver').combobox('getText'); 
   		//gridData.makeTime = $('#makeTime').val();
   		//gridData.applyType = 1;

   		//gridData.outStroeMethod = 3;
   	   // if(!gridData.toDeptId){
   		//  $pop.alert('退药科室不能为空');
   		//  return false;
   	   // }
   	   // if(!gridData.receiver){
   		//  $pop.alert('退库人不能为空');
   		//  return false;
   	  //  }
   	 	//gridData.remarks = $('#remarks').val();
  	   	return true;
  	}
  	     
    function initDiagCode(){
      // //初始化 诊断
      $('.diagCodeCombo').each(function (o,v){
        var _self = $(this);
        var ix = $('.diagCodeCombo').index(this);
        _self.combogrid({
          panelWidth:540,
          panelHeight:'auto',
          panelMaxHeight:182,
          striped:true,
          delay : 400,
          mode : 'remote',
          fitColumns : true,
          idField:'valueCode',
          textField:'valueDesc',
          // editable : false,  
          clearIcon:true,
         url:'${base}/ui/amcs/idr/amcsIdrDictType/getTypeCodeForList?typeCode=DiseaseName',
          queryParams: {
           // tag:'outp.diagnostic',
            limit:100,
         //   filter:'diag_versions='+diagVersion
          },
          columns:[[
            {title:'诊断编码',field:'valueCode',width:80},
            {title:'同义词诊断名称',field:'valueDesc',width:180},
            {title:'标准诊断名称',field:'valueDesc',width:180},
            {title:'病种',field:'valueRemark',width:80}
          ]],
          onBeforeLoad : function (param){
            if(!param.q){return false;}
          },
          onSelect : function (val,record){
            $('#outpInpatientDiagName-'+ix).val(record.ICD);
            //$('#diagName').val(record.STD_DIAG_NAME);
          }
          // required:true
        });
      });
       
      
    }
    
    initDiagCode(); 
    
    $(".btn-save").click(function(){ //保存
    	 if(!prepareData())
	  	return;
	  	
    	   $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/save',JSON.stringify(gridData),"您确定要保存吗?",true).done(function (rst) {
     	       // window.console && console.log(rst);
     	       if(rst.code==='200'||rst.code==='201'){
     	       		// debugger;
                    curId = rst.data.id;
                    $("#orderId").val(curId);  
     	            $("#amcsIdrHbid").val(rst.data.amcsIdrHb.id);  
            		$("#amcsIdrHfmdid").val(rst.data.amcsIdrHfmd.id);  
             		$("#amcsIdrAfpid").val(rst.data.amcsIdrAfp.id);  
             		$("#amcsIdrAidsid").val(rst.data.amcsIdrAids.id);  
                    
      }
          }); 
    });
    
     $('.btn-audit').click(function () {//审核
    	if(!prepareData())
    	  	return;
        $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/audit',JSON.stringify(gridData),"您确定要审核吗?",true).done(function (rst) {
         // window.console && console.log(rst);
         if(rst.code==='200'||rst.code==='201'){
              curId = rst.data.id;
	       }
       });
   }); 
    
    $('.btn-new').click(function () {//新增  
      $pop.confirm("您确定要新增吗?",function () {
                 curId =null;
       			 $('#id').val(null); 
       			 $('#eventid').val(null); 
       			 
       			 	
       			 	
      })
<!--     if(confirm("您确定要新增吗?")){ -->
<!--  		curId =null; -->
<!--         $('#id').val(null); -->
<!-- 	}else{ -->
<!--     // 取消操作 -->
<!-- 	} -->
<!--         $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/getAmcsIDReditor',{},"您确定要新增吗?").done(function (rst) { -->
<!--            // if(rst.code==='200'||rst.code==='201'){ -->
<!--            		 debugger;  -->
<!--          		curId =null; -->
<!--         		 $('#id').val(null); -->
<!--         	// }  -->
<!--        }); -->
   }); 
    $('.btn-auditUn').click(function () {//反审
    	if(!curId)
    	  	return;
        $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/auditUn',{id:curId},"您确定要反审吗?").done(function (rst) {
         
       });
   });
   $('.btn-uploading').click(function () {//上传
    	if(!curId)
    	  	return;
        $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/upload',{id:curId},"您确定要上传初报吗?").done(function (rst) {
         
       });
   });
   
    $('.btn-del').click(function () {//删除
   		 if(!curId)
    	  	return;    	 
        $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/del',{id:curId},"您确定要删除吗?").done(function (rst) {
          
      	 });
  	 });
    
      $('.btn-uploadDel').click(function () {//上传删除
        if(!curId)
    	  	return;
    	  $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/uploadDel',{id:curId},"您确定要上传删除吗?").done(function (rst) {
          
      	 });
  	 });
  	 
  	   $('.btn-uploadSeach').click(function () {//上传查询
        if(!curId)
    	  	return;
    	  $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/uploadSeach',{id:curId},"您确定要上传查询吗?").done(function (rst) {
          
      	 });
  	 });
      $('.btn-uploadMod').click(function () {//上传修订
        if(!curId)
    	  	return;
        $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/uploadMod',{id:curId},"您确定要上传修订吗?").done(function (rst) {
         
      	 });
  	 });
    
      $('.btn-uploadAud').click(function () {//上传审核
       	  if(!curId)
    	  	return;
    	  $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/uploadAud',{id:curId},"您确定要上传审核吗?").done(function (rst) {
         
      	 });
  	 });
    
      $('.btn-uploadRes').click(function () {//上传恢复
       	if(!curId)
    	  	return;
        $ajax.post('${base}/ui/amcs/idr/amcsIDReditor/uploadRes',{id:curId},"您确定要上传恢复吗?").done(function (rst) {
        
      	 });
  	 }); 
  	 
  	 
  	 
    
    
    	});
    
    
    
</script>
   
</html>

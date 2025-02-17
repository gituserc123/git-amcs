<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>查看/编辑个人信息 - 爱尔医疗平台</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.uploadHeaderWrap,.uploadWrap{position:absolute;right:35px;top:6px;width:260px;height:250px;background-color:#ecfbff;}
.uploadHeaderWrap{width:130px;height:108px;right:340px;overflow:hidden;}
.uploadHeaderWrap img,.uploadWrap img{width:100%;height:100%;object-fit: contain;}
.webuploader-container{position:inherit;}
.webuploader-pick{position:absolute;left:0;top:0;width:130px;height:108px;border:0;opacity:0.4;padding:0;filter:alpha(opacity=40);line-height:100px;font-size:1.3em;}
.uploadWrap .webuploader-pick{width:265px;height:252px;background-color:transparent;font-weight:normal;line-height:240px;font-size:2em;}
</style>
</head>

<body>
<div class="pad-20 pad-b0 pad-t10">
  <form class="soform form-validate form-enter mar-b10" method="post" action="${base}/ui/sys/staff/staffHospEdit">

    <div class="row">
      <div class="p4"><div class="item-one">
          <label class="lab-item">工号：</label>
          <label class="lab-val">${(staffHosp.staff.code)!}</label>
      </div></div>
        <div class="p4"><div class="item-one">
            <label class="lab-item">头像：</label>
            <label class="lab-val">

            </label>
        </div></div>
    </div>
      <div class="row">
      <div class="p4"><div class="item-one">
          <label class="lab-item">邮箱：</label>
          <label class="lab-val">${(staffHosp.staff.email)!'暂无'}</label>
      </div></div>
    </div>
    <div class="row">
        <div class="p4"><div class="item-one">
            <label class="lab-item">真实姓名：</label>
            <label class="lab-val">${(staffHosp.staff.name)!}</label>
        </div></div>
    </div>
      <div class="row">
        <div class="p4"><div class="item-one">
            <label class="lab-item">电话号码：</label>
            <label class="lab-val">${(staffHosp.staff.tel)!'暂无'}</label>
        </div></div>
        <div class="p4"><div class="item-one">
            <label class="lab-item">身份证号：</label>
            <label class="lab-val">${(staffHosp.staff.idCard)!'暂无'}</label>
        </div></div>

    </div>
    <div class="row">
        <div class="p4"><div class="item-one">
            <label class="lab-item">性别：</label>
            ${(staffHosp.staff.sex == 1)?string('男','女')}
        </div></div>
         <div class="p4"><div class="item-one">
        	<label class="lab-item">出生日期：</label>
        	<label class="lab-val">[#if (staffHosp.staff.birthdate)??]${staffHosp.staff.birthdate?string("yyyy-MM-dd")}[#else]暂无[/#if]</label>
        </div></div>
    </div> 
     <div class="row">
        <div class="p4"><div class="item-one">
            <label class="lab-item">首拼码：</label>
            <label class="lab-val">${(staffHosp.staff.firstSpell)!}</label>
        </div></div>
        <div class="p4"><div class="item-one">
            <label class="lab-item">有效期：</label>
            <label class="lab-val">[#if staffHosp.staffInst.expiryDate??]${staffHosp.staffInst.expiryDate?string("yyyy-MM-dd")}[#else]暂无[/#if]</label>
        </div></div>
    </div>
    <div class="row">
        <div class="p8"><div class="item-one">
            <label class="lab-item">来源：</label>
            <label class="lab-val">${(staffHosp.staffInst.fromto)!}</label>
        </div></div>
    </div>
    <div class="row">
        <div class="p12"><div class="item-one">
            <label class="lab-item">科室：</label>
            [#list deptList as dept]
            	<label class="lab-val fl"><input type="checkbox" class="chk" checked="checked" disabled="disabled"/>${dept.name!}</label>
            [/#list]
        </div> </div>
    </div>
      <div class="row">
        <div class="p6"><div class="item-one" [#if staffHosp.staff.busPassword??] title="您已设定业务密码，如需修改，请重新设置" [#else] title="未设置业务密码，请设置" [/#if]>
            <label class="lab-item">业务密码：</label>
            <input class="txt txt-validate" type="password" name="busPassword" value="" [#if staffHosp.staff.busPassword??] placeholder="您已设定业务密码，如需修改，请重新设置" [#else] placeholder="未设置业务密码，请设置" [/#if]/>
        </div></div>
        <div class="p6"><div class="item-one">
            <label class="lab-item">简介：</label>    
            <label class="lab-val">${(staffHosp.remarks)!'暂无'}</label>
        </div></div>
    </div>
    <div class="row">
        <div class="p12"><div class="item-one">
            <label class="lab-item">擅长：</label>
            <label class="lab-val">${(staffHosp.staff.speciality)!'暂无'}</label>
        </div> </div>
    </div>

    <div class="uploadHeaderWrap">
          <img id="img-userHeaderPic" [#if staffHosp.staff.headerPic??] src="${(staffHosp.staff.headerPic)!}" [#else] src="${base}/static/images/empty_bed.png" [/#if] alt="" />
[#--          <div id="uploaderHeader"></div>--]
          <input class="txt txt-userHeaderPic hide" type="text" name="staff.headerPic" value="${(staffHosp.staff.headerPic)!}" />
    </div>

    <div class="uploadWrap">
        <img id="img-userIntroPic" [#if staffHosp.staff.introPic??] src="${(staffHosp.staff.introPic)!}" [#else] src="${base}/static/images/empty_bed.png" [/#if] alt="" />
[#--        <div id="uploader"></div>--]
        <input class="txt txt-userIntroPic hide" type="text" name="staff.introPic" value="${(staffHosp.staff.introPic)!}" /><!-- 用来存储简介图片url表单值 -->
    </div>

    <h4 class="h4-form mar-b20"><span class="s-t">医疗信息</span></h4>
    <div class="row">
        <div class="p6"><div class="item-one">
            <label class="lab-item">职业级别：</label>
            [@ui_select id="certLevel" tag="cert_level" name="certLevel" style="width:100%" value="${staffHosp.certLevel}" disabled="disabled"/]
        </div></div>
        <div class="p6"><div class="item-one">
            <label class="lab-item">毒麻精权限：</label>
            <label class="lab-val">
            <input type="checkbox" class="chk" [#if staffHosp.pamAuth == 1]checked = "checked"[/#if] name="pamAuth" value="1" disabled="disabled"/>有权限
            </label>
          </div></div>
      </div> 
      <div class="row">
        <div class="p6"><div class="item-one">
            <label class="lab-item">抗菌药物权限：</label>
            [@ui_select id="antibioAuth" tag="antimicrobial_level" name="antibioAuth" style="width:100%" value="${staffHosp.antibioAuth}" disabled="disabled"/]
        </div></div>
        <div class="p6"><div class="item-one">
            <label class="lab-item">医生手术级别：</label>
            [@ui_select id="oprLevel" tag="opr_level" name="oprLevel" style="width:100%" value="${staffHosp.oprLevel}" disabled="disabled"/]
        </div></div>
      </div> 
      <div class="row">
      	<div class="p6"><div class="item-one">
            <label class="lab-item">职务：</label>
            [@ui_select id="staffType" tag="staff_type" name="staffType" class="drop easyui-combobox" style="width:100%" value="${staffHosp.staffType}" multiple=true disabled="disabled"/]
        </div></div>
        <div class="p6"><div class="item-one">
            <label class="lab-item">医生执业证号：</label>
            <input class="txt easyui-validatebox" type="text" name="doctorCertNumber" value="${(staffHosp.doctorCertNumber)!}" disabled="disabled"/>
        </div></div> 
      </div> 
      <div class="row">
      	<div class="p6"><div class="item-one">
            <label class="lab-item">上级医生：</label>
            <input id="superiorDoctor" name="superiorDoctor" style="width:100%" value="${(staffHosp.superiorDoctor)!}" disabled="disabled" />  
        </div></div>
        <div class="p6"><div class="item-one">
            <label class="lab-item">治疗方案：</label>
            <select class="drop easyui-combobox" style="width:100%" name="appliedRange" disabled="disabled">
                <option value="3" [#if staffHosp.appliedRange == 3] selected="selected" [/#if]>个人</option>
            	<option value="1" [#if staffHosp.appliedRange == 1] selected="selected" [/#if]>全院级</option>
            	<option value="2" [#if staffHosp.appliedRange == 2] selected="selected" [/#if]>科室</option>
            </select>
        </div></div>
      </div> 
    <p class="row-btn">
      <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存" />
      <input type="button" class="btn btn-cancel" name="btnCancel" value="关 闭" />
    </p>
  </form>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
requirejs(['pub','myupload'],function () {

    $("#staffType").combobox({
    	onChange:function(){
			if($("#staffType").combobox('getValues').join().indexOf('1')>-1) {
	    		$('#superiorDoctor').combobox("enable");
	    	}else{
	    		$('#superiorDoctor').combobox("disable");
	    	}
        }
    });
    
	$('#superiorDoctor').combobox({
	    url: '${base}/ui/sys/staff/getStaffByType',  
	    queryParams: {staffId:'${(staffHosp.staff.id)!}'},
	    valueField:'staffId',    
        textField:'name',
        formatter : function(row){
           return '<span clsss=s-multi><em class=i-s-chk></em>'+row.name+'</span>';
        },   
	    multiple:true   
	}); 
});
</script>
</body>

</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
[#--<meta http-equiv="Content-Security-Policy" content="default-src 'self' open.work.weixin.qq.com; script-src 'self' rescdn.qqmail.com 'unsafe-inline' 'unsafe-eval';">--]
<link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon" />
<title>爱尔眼科云门户-用户登录</title>
<link type="text/css" rel="stylesheet" media="all" href='${base}/static/css/loginAll.css?v=e629c'/>
  <script type="text/javascript">
    var loginType = 'normal';
    [#if weixin??]loginType = 'weixin';[/#if]
  </script>
</head>

[#if profile=='uat' || profile=='sit' || profile=='hotfix']
<body class="development">
[#else]
<body>
[/#if]
<form action="${base}/login" method="post" id="loginFrm">

<div class="loginWrap">
    <h1 class="h1-logo">爱尔眼科云门户</h1>
    <div class="slogo">共享全球 眼科只会</div>
    <div class="loginC">
[#-- <h2 class="h2-title">欢迎登录爱尔医疗平台</h2> ${weixin}  --]
      <h2 class="h2-title"><span class="s-t s-t-now">账号登录</span><span class="s-t">扫码登录</span></h2>
      <div class="loginCont">
        <div class="loginOne login-psw loginOneShow">
          <input class="txt" id="temuser" type="text" name="username" style="display:none;" value="" autocomplete="off" />
          <input class="txt" id="tempassword" type="password" name="password" style="display:none;" value="" autocomplete="off" />
        <p id="errorMsg" class="p-errorMsg [#if msg??]validateErr[/#if]">${msg}</p>
        <div class="p-item"><label class="lab-item lab-user"><input type="text" class="txt-login txt-user" name="username" id="username" value="" maxlength="20" placeholder="请输入用户名" autocomplete="off" /></label></div>
        <div class="p-item p-password"><label class="lab-item lab-psw"><input type="password" class="txt-login txt-pass" name="password" id="password" maxlength="25" placeholder="请输入密码" autocomplete="new-password" /></label>
          <label class="lab-lockTip">大小写锁定已打开</label>
        </div>
        <input id="enPassword" type="hidden" name="enPassword" value=""/><input id="key" type="hidden" name="key" value=""/>
        <input id="medicalInstitutionCode" type="hidden" value=""/>
        <div class="p-item"><label class="lab-item lab-hospital">
          <select class="drop drop-hospital" name="institution" id="institution" disabled="disabled">
            <option value="" selected="selected">请选择医院/集团</option>
          </select>
          </label>
        </div>
        <div class="p-item"><label class="lab-item  lab-dept">
          <select class="drop drop-dept" name="dept" id="dept" disabled="disabled">
            <option value="" selected="selected">请选择科室/部门</option>
          </select>
          </label>
        </div>
        <p class="p-item"><input type="submit" class="btn" name="btnSubmit" value="登 录" /></p>
      </div>

      <div id="login-wx2w" class="loginOne login-2w">
        <div id="wxLoginC"></div>
        <p id="errorMsg-wx" class="p-errorMsg [#if msg??]validateErr[/#if]">${msg}</p>
        [#--                  <p id="errorMsg-wx" class="validateErr">账号已禁用，暂时无法使用！</p>--]
      </div>
	  <div class="hosInsuranceInfo">
          <p id="p-physicianCode">用户国家医保编码：<span id="physicianCode" class="code"></span></p>
          <p id="p-medicalCode">机构国家医保编码：<span id="medicalCode" class="code"></span></p>
      </div>
    </div>
  </div>

  <div class="copyrightInfo">
    <p><span class="s-copyright">爱尔眼科医院集团 版权所有 <em class="em-copyright">Copyright © 2016-2022 Aier EYE Hospital All Rights Reserved. </em></span><!-- <a class="a-icp" href="http://www.miitbeian.gov.cn/" target="_blank">湘公网安备 43010302000139号</a> --></p>
  </div>
</div>
</form>

<script type="text/javascript" src="https://rescdn.qqmail.com/node/ww/wwopenmng/js/sso/wwLogin-1.0.0.js"></script>
<script type='text/javascript' src='${base}/static/js/jquery-1.11.3.min.js?v=ef3d5'></script>
<script type='text/javascript' src='${base}/static/js/plus/rsaPlus.js?v=649aa'></script>
<script type="text/javascript">
//jQuery.cookie
jQuery.cookie=function(a,b,c){var d,e,f,g,h,i,j,k,l;if("undefined"==typeof b){if(i=null,document.cookie&&""!=document.cookie)for(j=document.cookie.split(";"),k=0;k<j.length;k++)if(l=jQuery.trim(j[k]),l.substring(0,a.length+1)==a+"="){i=decodeURIComponent(l.substring(a.length+1));break}return i}c=c||{},null===b&&(b="",c.expires=-1),d="",c.expires&&("number"==typeof c.expires||c.expires.toUTCString)&&("number"==typeof c.expires?(e=new Date,e.setTime(e.getTime()+1e3*60*60*24*c.expires)):e=c.expires,d="; expires="+e.toUTCString()),f=c.path?"; path="+c.path:"",g=c.domain?"; domain="+c.domain:"",h=c.secure?"; secure":"",document.cookie=[a,"=",encodeURIComponent(b),d,f,g,h].join("")};

var $T={getCookie:function(a,b){b=$.cookie(b||"aso");if(null!==b){b=b.split("||");var e=b.length;for(i=0;i<e;i++){var d=b[i].split(":=");if(d[0]===a)return d[1]}}else return null},setCookie:function(a,b,e,d){d=d||"aso";var c=$.cookie(d);if(null!==c)if(-1<c.indexOf(a)){c=c.split("||");var f=c.length;for(i=0;i<f;i++)c[i].split(":=")[0]===a&&(c[i]=a+":="+b);a=c.join("||")}else a=c+"||"+a+":="+b;else a=a+":="+b;$.cookie(d,a,e||{})}};


var $eye = $eye || {};
$eye.login ={
  insts : [],
  init : function () {
    var me = this;
    me.submitLogin();//登录事件
    me.noInWindow();//被内嵌(非法登录或登录超时)，则跳出置顶
    me.staffBlur();
    me.changeInstitution();
    me.chanageLoginType();
    me.pageAutoH();//页面高度超出649则自动适应
    me.initHosInsuranceInfo();

    $('#password').keypress(function (e){
      me.detectCapsLock(e);
    });

    $(window).resize(function () {
      me.pageAutoH();
    });
  },
  detectCapsLock : function  (e){
    var c  =  e.keyCode; // 按键的keyCode
    var isShift  =  e.shiftKey ||(c == 16) || false ; // shift键是否按住
    var isLock = ((c>=65 && c<=90 ) && !isShift) || ((c>=97 && c<=122)&&isShift);
    // Caps Lock 打开，且没有按住shift键  || Caps Lock 打开，且按住shift键
    $('.lab-lockTip')[isLock?'show':'hide']();
  },
  chanageLoginType : function(){
    $('.s-t').click(function () {
      var ix = $('.s-t').index(this);
      $('.s-t').removeClass('s-t-now');
      $(this).addClass('s-t-now');
      $('.loginOne').hide().eq(ix).show();
    });
    if(loginType=='weixin'){
      $('.s-t').eq(1).click();
    }
  },
  
  initHosInsuranceInfo : function() {
  	$('.hosInsuranceInfo').hide();
  },
  
  isEmptyStr : function (s) {
	if (s == undefined || s == null || s == '') {
		return true;
	}
	return false;
  },
  
  setHosInsuranceInfo : function(physicianCode, medicalCode) {
    var me = this;
    var pphysicianCode = $('#p-physicianCode');
    var pmedicalCode = $('#p-medicalCode');
    var $physicianCode = $('#physicianCode');
    var $medicalCode = $('#medicalCode');
  	if (!me.isEmptyStr(physicianCode) && !me.isEmptyStr(medicalCode)) {
  		$('.hosInsuranceInfo').show();
  		pphysicianCode.show(); $physicianCode.html(physicianCode);
  		pmedicalCode.show(); $medicalCode.html(medicalCode);
  	} else if (!me.isEmptyStr(physicianCode) && me.isEmptyStr(medicalCode)) {
  		$('.hosInsuranceInfo').show();
  		pphysicianCode.show(); $physicianCode.html(physicianCode);
  		pmedicalCode.hide(); $medicalCode.html("");
  	} else if (me.isEmptyStr(physicianCode) && !me.isEmptyStr(medicalCode)) {
  		$('.hosInsuranceInfo').show();
  		pphysicianCode.hide(); $physicianCode.html("");
  		pmedicalCode.show(); $medicalCode.html(medicalCode);
  	} else {
  		$('.hosInsuranceInfo').hide();
  		$physicianCode.html("");$medicalCode.html("");
  	}
  },
  
  selectDept : function(staffcode, institution) {
    var me = this;
	if (staffcode && institution) {
    var staffcode = $.trim(staffcode);
    var institution = $.trim(institution);
	var $dept = $('#dept');
	  $.ajax({
		 url : '${base}/login/getDept',
		 dataType : 'json',
		 type : 'POST',
		 data : {loginCode:staffcode,institution:institution},
		 success : function(rst){
		   if(rst.code=='200'){
		     $dept.empty();//清空option
			 if (rst.data && rst.data.length) {
				$dept.removeAttr('disabled');
				var deptHtml = '';
				 $.each(rst.data, function(i,val) {
				   deptHtml += "<option value='"+val.id+"'>"+val.name+"</option>";
				 });
				$dept.append(deptHtml);
			 }else{
				$dept.attr('disabled','disabled');
			 }
		   }else{
			 me._showMsg(rst.msg,2);
		   }
		 },
		 error : function(XMLHttpRequest, textStatus, errorThrown){
		   me._showMsg('连接失败，请联系管理员...',2);
		 }
	   });
	}
  },
  
  getPhysicianCode : function(staffcode, institution, medicalCode) {
    var me = this;
	if (staffcode && institution) {
    var staffcode = $.trim(staffcode);
    var institution = $.trim(institution);
	var physicianCode = '';
	  $.ajax({
		 url : '${base}/login/getPhysicianCode',
		 dataType : 'json',
		 type : 'POST',
		 data : {loginCode:staffcode,institution:institution},
		 success : function(rst){
		   if(rst.code=='201'){
			 physicianCode = rst.data;
			 me.setHosInsuranceInfo(physicianCode, medicalCode);
		   }else{
			 me._showMsg(rst.msg,2);
		   }
		 },
		 error : function(XMLHttpRequest, textStatus, errorThrown){
		   me._showMsg('连接失败，请联系管理员...',2);
		 }
	   });
	}
  },

  staffBlur : function () {
    var me = this;
    $('#username').blur(function(){
      me.getStaffInfo();
  	});
  },
  
  getStaffInfo : function(){
    var me = this;
    var $username    = $('#username');
    var $institution = $('#institution');
    var staffcode    = $.trim($username.val());
    var $dept        = $('#dept');
    
    var firstHosp;
    if (staffcode) {
      $.ajax({
        url : '${base}/login/getInst',
        dataType : 'json',
        type : 'POST',
        data : {loginCode:staffcode},
        success : function(rst) {
          $dept.empty();//清空option
          if(rst.code=='200'){
            $institution.empty();//清空option
            if (rst.data && rst.data.length) {
              $institution.removeAttr('disabled');
              me.insts = rst.data;
              var hosHtml = '';
              var medicalCode = '';
              $.each(rst.data, function(i,val) {
                if (i==0) {
                  //取第一个医院/集团部门
                  firstHosp = val.id;
                  medicalCode = val.medicalInstitutionCode;
                }
                hosHtml += "<option value='"+val.id+"'>"+val.name+"</option>";
              });
              $institution.append(hosHtml);
              if (firstHosp) {
                me.selectDept(staffcode,firstHosp);
                me.getPhysicianCode(staffcode,firstHosp, medicalCode);
              }
            }else{
              $institution.attr('disabled','disabled');
            }
          }else{
            me._showMsg(rst.msg,2);
          }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
          me._showMsg('连接失败，请联系管理员...',2);
        }
      });
    }
  },
  changeInstitution : function () {
     var me = this;
	 var $username = $('#username');
	 var $institution = $('#institution');
	 $institution.change(function(){
		me.selectDept($username.val(), $institution.val());
		if (me.insts.length){
			var instId = $institution.val();
			var curMedicalCode = '';
			$.each(me.insts, function(i,val) {
                if (instId==val.id) {
                  curMedicalCode = val.medicalInstitutionCode;
                }
              });
			me.getPhysicianCode($username.val(),$institution.val(),curMedicalCode);
		}
	 });
  },

  submitLogin : function () {
    var me = this;
    var $user = $("#username");
    var usertemp = $T.getCookie('eyetempuser');

    if(usertemp){//从cookie中获取上一次登录用户名，并请求医院部门
      $user.val(usertemp);
      me.getStaffInfo();
    };

    setTimeout(function () {
      $('#temuser').remove();
      $('#tempassword').remove();
    },200);

    $('#loginFrm').submit(function(){
      var user_code=$("#username").val(), p=$('#p').val(), password=$('#password').val(), hos = $('.drop-hospital').val(),dept = $('.drop-dept').val();
      [@ui_rsa/]
      if(!user_code){
        me._showMsg('请输入用户名',2);
        $("#username").focus();
        return false;
      }
      if(!password){
        me._showMsg('请输入密码!',2);
        $("#password").focus();
        return false;
      }
      if(!hos){
        me._showMsg('请选择当前用户本次登录医院/集团!',2);
        $(".drop-hospital").focus();
        return false;
      }
      if(!dept){
        me._showMsg('请选择当前用户本次登录科室/部门!',2);
        $(".drop-dept").focus();
        return false;
      }
      $T.setCookie('eyetempuser',user_code,{expires: 15});
      me._showMsg('登录中，请稍候...',1);
    });
  },
  noInWindow : function () {
    if(window.top !== window.self){window.top.location = window.location;}
  },
  pageAutoH : function () {
    var wh = $(window).height();
    $('body')[wh>649?'addClass':'removeClass']('body-superH');
  },
  $msg : loginType=='normal'?$('#errorMsg'):$('#errorMsg-wx'),
  _showMsg : function (msg,type) {
    var $msg = this.$msg;
    $('.p-errorMsg').removeClass('validateOk').removeClass('validateErr');
    if (type==1) {
      $msg.removeClass('validateErr').addClass('validateOk').text(msg);
    }
    if (type==2) {
      $msg.removeClass('validateOk').addClass('validateErr').text(msg);
    };
    if (type==3) {
      $msg.removeClass('validateOk').removeClass('validateErr').text(msg);
    };
  }
}

$(function () {
  $eye.login.init();//登录页面初始化
});

//微信扫描登录配置
window.WwLogin && window.WwLogin({
  id : 'wxLoginC',
  appid : '${corpid}',
  agentid : '${agentId}',
  redirect_uri :'${weiXinRedirectUri}/qywexin/getCode',
  state : '',
  href : '${weiXinRedirectUri}/static/css/wxloginStyle.css?v=57cf7'
});

</script>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="${base}/static/images/logo.ico" type="image/x-icon" />
<title>爱尔医疗平台-用户登录</title>
<link type="text/css" rel="stylesheet" media="all" href='${base}/static/css/login-portal.css?1183e'/>
</head>
<body>
<form action="${base}/login" method="post" id="loginFrm">

<div class="loginWrap">
    <h1 class="h1-logo">欢迎登录爱尔医疗平台</h1>
    <div class="loginBox">
      <div class="wrapper">
        <div class="loginC">
          <div class="wx2v-success-tip"><span class="s-successTip">扫码认证成功！</span><span>请选择科室/部门后进入系统</span></div>
            <div class="afterWx2v">
              <input id="username" type="hidden" name="username" value=""/>
              <input id="key" type="hidden" name="key" value=""/>
              <input id="loginToken" type="hidden" name="loginToken" value="${loginToken}"/>
              
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
              <p class="p-item"><input type="submit" class="btn" name="btnSubmit" value="进 入" /></p>
[#--              <p class="p-item p-reback"><a href="${base}/login" class="a-rebackLogin"><em class="em-f">&lt;&lt;</em> 返回登录界面</a></p>--]
            </div>
        </div>
    </div>
  </div>

  <div class="copyrightInfo">
    <p><span class="s-copyright">爱尔眼科医院集团 版权所有 <em class="em-copyright">Copyright © 2016-2022 Aier EYE Hospital All Rights Reserved. </em></span><!-- <a class="a-icp" href="http://www.miitbeian.gov.cn/" target="_blank">湘公网安备 43010302000139号</a> --></p>
  </div>
</div>
</form>

[#--<script type="text/javascript" src="${base}/static/js/lib/md5.js"></script>--]
<script type='text/javascript' src='${base}/static/js/jquery-1.11.3.min.js?ef3d5'></script>
<script type='text/javascript' src='${base}/static/js/plus/rsaPlus.min.js'></script>
<script type="text/javascript">
//jQuery.cookie
jQuery.cookie=function(a,b,c){var d,e,f,g,h,i,j,k,l;if("undefined"==typeof b){if(i=null,document.cookie&&""!=document.cookie)for(j=document.cookie.split(";"),k=0;k<j.length;k++)if(l=jQuery.trim(j[k]),l.substring(0,a.length+1)==a+"="){i=decodeURIComponent(l.substring(a.length+1));break}return i}c=c||{},null===b&&(b="",c.expires=-1),d="",c.expires&&("number"==typeof c.expires||c.expires.toUTCString)&&("number"==typeof c.expires?(e=new Date,e.setTime(e.getTime()+1e3*60*60*24*c.expires)):e=c.expires,d="; expires="+e.toUTCString()),f=c.path?"; path="+c.path:"",g=c.domain?"; domain="+c.domain:"",h=c.secure?"; secure":"",document.cookie=[a,"=",encodeURIComponent(b),d,f,g,h].join("")};

var $T={getCookie:function(a,b){b=$.cookie(b||"aso");if(null!==b){b=b.split("||");var e=b.length;for(i=0;i<e;i++){var d=b[i].split(":=");if(d[0]===a)return d[1]}}else return null},setCookie:function(a,b,e,d){d=d||"aso";var c=$.cookie(d);if(null!==c)if(-1<c.indexOf(a)){c=c.split("||");var f=c.length;for(i=0;i<f;i++)c[i].split(":=")[0]===a&&(c[i]=a+":="+b);a=c.join("||")}else a=c+"||"+a+":="+b;else a=a+":="+b;$.cookie(d,a,e||{})}};

var $eye = $eye || {};
$eye.login ={
  init : function () {
    var me = this;
    // window.console && console.log('login init');
    me.submitLogin();//登录事件
    me.noInWindow();//被内嵌(非法登录或登录超时)，则跳出置顶
    me.staffBlur();
    me.changeInstitution();
    me.pageAutoH();//页面高度超出649则自动适应
    $(window).resize(function () {
      me.pageAutoH();
    });
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

  staffBlur : function () {
    var me = this;
    me.getStaffInfo();
  },
  getStaffInfo : function(){
    var me = this;
    var $institution = $('#institution');
    var staffcode    = '${staffcode}';
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
              var hosHtml = '';
              $.each(rst.data, function(i,val) {
                if (i==0) {
                  //取第一个医院/集团部门
                  firstHosp = val.id;
                }
                hosHtml += "<option value='"+val.id+"'>"+val.name+"</option>";
              });
              $institution.append(hosHtml);
              if (firstHosp) {
                me.selectDept(staffcode,firstHosp);
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
	 var $username = '${staffcode}';
	 var $institution = $('#institution');
	 $institution.change(function(){
		me.selectDept($username, $institution.val());
	 });
  },
  
  submitLogin : function () {
    var me = this;
	$('#username').val('${staffcode}');
	
    $('#loginFrm').submit(function(){
      var hos = $('.drop-hospital').val(),dept = $('.drop-dept').val();
     
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
      $T.setCookie('eyetempuser','${staffcode}',{expires: 15});
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
  $msg : $('#errorMsg'),
  _showMsg : function (msg,type) {
    var $msg = this.$msg;
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
</script>
</body>
</html>
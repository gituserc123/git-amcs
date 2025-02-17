//jQuery.cookie
jQuery.cookie=function(a,b,c){var d,e,f,g,h,i,j,k,l;if("undefined"==typeof b){if(i=null,document.cookie&&""!=document.cookie)for(j=document.cookie.split(";"),k=0;k<j.length;k++)if(l=jQuery.trim(j[k]),l.substring(0,a.length+1)==a+"="){i=decodeURIComponent(l.substring(a.length+1));break}return i}c=c||{},null===b&&(b="",c.expires=-1),d="",c.expires&&("number"==typeof c.expires||c.expires.toUTCString)&&("number"==typeof c.expires?(e=new Date,e.setTime(e.getTime()+1e3*60*60*24*c.expires)):e=c.expires,d="; expires="+e.toUTCString()),f=c.path?"; path="+c.path:"",g=c.domain?"; domain="+c.domain:"",h=c.secure?"; secure":"",document.cookie=[a,"=",encodeURIComponent(b),d,f,g,h].join("")};
var $T={getCookie:function(a,b){b=$.cookie(b||"aso");if(null!==b){b=b.split("||");var e=b.length;for(i=0;i<e;i++){var d=b[i].split(":=");if(d[0]===a)return d[1]}}else return null},setCookie:function(a,b,e,d){d=d||"aso";var c=$.cookie(d);if(null!==c)if(-1<c.indexOf(a)){c=c.split("||");var f=c.length;for(i=0;i<f;i++)c[i].split(":=")[0]===a&&(c[i]=a+":="+b);a=c.join("||")}else a=c+"||"+a+":="+b;else a=a+":="+b;$.cookie(d,a,e||{})}};

$.fn.hoverClass=function(b){var a=this;a.each(function(c){a.eq(c).mouseenter(function(){$(this).addClass(b)});a.eq(c).mouseleave(function(){$(this).removeClass(b)})});return a};
!function(t){t.fn.soMarquee=function(n){var s={direction:"left",wrapClass:"",width:null,height:null,step:1,speed:10,pause:1e3,overStop:!0,overEvent:function(){},clickEvent:function(){}},r={};for(i in s)t(this).attr(i)&&(r[i]=t(this).attr(i));var a=t(this).data("marquee")||{};return t.extend(s,a,r,n||{}),new e(this,s).init(),this};var e=function(e,i){this.o=t(e),this.opt=i};e.prototype={init:function(){function e(){var t,e=0;if(u<2?(e=parseInt(n.css("marginLeft")),t=a,0==u?n.css({marginLeft:-e>p?0:e+s.step+"px"}):n.css({marginLeft:e>0?-p:e+s.step+"px"})):(e=parseInt(n.css("marginTop")),t=o,2==u?n.css({marginTop:-e>c?0:e+s.step+"px"}):n.css({marginTop:e>0?-c:e+s.step+"px"})),s.pause>0&&e%t==0){var i=s.step;s.step=0,setTimeout(function(){s.step=i},s.pause)}}var n=this.o,s=this.opt,r=n.find("li"),a=r.outerWidth(!0),o=r.outerHeight(!0),h=r.length,p=parseInt(a)*h,c=parseInt(o)*h,u=jQuery.inArray(s.direction,["left","right","up","down"]);s.step=u%2?s.step:-s.step,s.width=s.width?s.width:n.parent().width(),s.height=s.width?s.height:n.parent().height();var d=t('<div style="position:relative;overflow:hidden;"></div>');if(s.wrapClass&&d.addClass(s.wrapClass),u<2&&p>s.width)n.width(2*p),d.css({width:s.width}),s.step>0&&n.css({marginLeft:-p});else{if(!(u>1&&c>s.height))return;n.height(2*c),d.css({height:s.height}),s.step>0&&n.css({marginTop:-c})}n.wrap(d).append(r.clone());var f;clearInterval(f),f=setInterval(e,s.speed),1!=s.overStop&&"true"!=s.overStop||n.hover(function(){clearInterval(f)},function(){f=setInterval(e,s.speed)}),n.find("li").mouseover(function(){return i=n.find("li").index(t(this))%h,s.overEvent(this,i)}),n.find("li").click(function(){return i=n.find("li").index(t(this))%h,s.clickEvent(this,i)})}},t(function(){t(".soMarquee").length&&t(".soMarquee").each(function(){t(this).soMarquee()})})}(jQuery);

function Storage(name){this.name=name;if(!localStorage.getItem(this.name)){localStorage.setItem(this.name,'{}');}}
Storage.prototype={
  getStore: function(){var store=localStorage.getItem(this.name);return JSON.parse(store);},
  coverStore: function(o){localStorage.setItem(this.name,JSON.stringify(o));},
  getVal:function(k){var store=this.getStore();return store[k];},
  setVal:function(k,v){var store=this.getStore();store[k]=v;this.coverStore(store);},
  removeVal:function(k){var store=this.getStore();delete store[k];this.coverStore(store);}
}

var eyeIndex = {
      init : function () {
        var me = this;
        $('.li-mainnav').hoverClass('li-mainnav-over');
        me.setIframeH();//设置iframeH
        me.mainnavClick();
        me.sideNavE('.s-subnav','s-subnav-now');//侧边导航点击链接事件
        me.sideNavE('.s-hnav','s-hnav-now');//侧边导航点击链接事件
	    me.sideNavE('.s-feedback','s-feedback');//顶部问题反馈点击链接事件
        me.repairPass();//修改密码
        me.noInWindow();//不包含在iframe中
        me.loginOut();//退出登录
        me.switchCompany();//切换公司医院
        me.tabCloseEven();
        me.menuClick();
        me.rightmenu();
        me.extendArea();
        me.extendNav();
		me.mainResize();
        // me.favItemsInit();//我的工作台事件
        // me.workspacesInit();//我的工作台事件
        me.indexNewsScroll();//新闻滚动
        me.tabOpenCloseFn();//tab打开关闭处理事件
        me.searchKey();
        me.justOneUser();//当前只能有一个登录用户
        //me.eventShow();//新闻显示
        me.appendLoginInfo();//加入登录信息显示在tab的位置
        me.changeDept();
        if(openCloud==='true' && cloudsSize*1>1){
          me.showPlatforms();
        }
        me.toAnotherPlatform(); //切换到其他平台
      },
      //切换到其他平台
      toAnotherPlatform: function (){
        var wName = 'amcsCloud';
        window.name = wName;
        var rPlats = new Storage('rPlats');
        // if(rPlats.getVal('show')){
          $('.h1-logo').addClass('newChangePfm');
        // }
        rPlats.setVal(wName, '1');
        window.onbeforeunload =  function (){
          rPlats.removeVal(wName);
        }

        var platsTimer = null;
        $('.newChangePfm').mouseleave(function (){
          platsTimer && clearTimeout(platsTimer);
          platsTimer = setTimeout(function (){
            $('.ul-plats').hide();
          },600);
        }).mouseenter(function (){
          platsTimer && clearTimeout(platsTimer);
        });

        $(document).on('click', function (e) {
          var $t = $(e.target);
          // console.log($t, $t.parents('.newChangePfm').length);
          if ($t.hasClass('newChangePfm') || $t.parents('.newChangePfm').length) {
            $('.ul-plats').show();
          } else {
            $('.ul-plats').hide();
          };
        });

        $('.ul-plats').on('click','.i-t',function (){
          var plat = $(this).attr('rel');
          if(rPlats.getVal(plat)){ //已打开
            window.open("", plat);
          }else{ // 未打开
            window.open( $(this).attr('href'), plat);
          }
          return false;
        });

      },
      workspacesInit : function(){
        var me = this;
        // me.loadWorkspaceList();//初始加载
        // me.exEditWorkspace();//点击开启编辑
        me.workspaceOneItem();//点击收藏
      },
      workspaceOneItem : function(){
        var me = this;
        $('.em-favor').click(function () {
          var id = $(this).attr('name');
          var popEditHtml = template('tem-popFavItem', { moduleId: id });
          $.getJSON('/ui/sys/myworkspace/list',function (rst){
            // console.log(rst);
            if(rst.code == '200'){
              var catesList = rst.data.map(function (text){
                return {text: text};
              })
              layer.open({
                type:1,
                zIndex: 50,
                content : popEditHtml,
                title :'收藏',
                area : ['360px','180px'],
                success  : function ($p,index){

                  $('#moduleCategorize').combobox({ //下拉框初始化
                    valueField:'text',
                    textField:'text',
                    editable: true,
                    required: true,
                    validType:['length[0,40]'],
                    prompt: '可选择或自定义输入分类',
                    limitToList : false,
                    reversed:false,
                    data: catesList
                  });

                  $('.btn-closePopFav').click(function (){ //关闭
                    layer.close(index);
                  });

                  var postState = false; //防反复提交
                  $('.btn-saveFav').click(function (){ //提交
                    if($('.form-favItem').form('validate')){
                      if(!postState){
                        postState = true;
                        $.ajax({
                          url : baseUrl +'/ui/sys/myworkspace/create',
                          //dataType : 'json',
                          type : 'POST',
                          data : { moduleId: id, moduleCategorize: $('#moduleCategorize').combobox('getValue') },
                          success : function(rst){
                            // me.loadWorkspaceList();//更新收藏列表
                            postState = false;
                            layer.close(index);
                            layer.msg('添加到我的工作台!',{icon:1,time:2000});
                          },
                          error : function(XMLHttpRequest, textStatus, errorThrown){
                            var rst = JSON.parse(XMLHttpRequest.responseText);
                            var msg = '菜单添加失败!';
                            postState = false;
                            layer.close(index);
                            if(rst&&rst.msg){msg= rst.msg;}
                            layer.msg(msg,{icon:7,time:3000});
                          }
                        });
                      }
                    }
                  });
                }
              });
            }
          });
        });
      },
      showPlatforms:function (){
        $('.h1-logo').addClass('canChangePfm showtip');

        setTimeout(function (){
          $('.h1-logo').removeClass('showtip');
        },3000);
        var rPlats = new Storage('rPlats');
        if(!rPlats.getVal('show')) {
          // $('.img-logo').click(function () {
          //   layer.open({
          //     type: 1,
          //     skin: 'popPlatform',
          //     title: null, btn: null,
          //     shadeClose: true,
          //     content: template('tem-changePlatforms', {}),
          //     area: ['880px', '410px']
          //   })
          // });
        }
      },
      changeDept: function(){
        var me = this;
        $('.nowCompany').click(function (){
          var changePopHtml = template('tem-changeDept',{});
          layer.open({//切换机构部门弹窗
            type:1,
            content : changePopHtml,
            title :'切换部门',
            area : ['360px','220px'],
            success  : function ($p,index){
              $p.find('.btn-cancel').click(function (){//取消
                layer.close(index);
              });
              $p.find('.btn-submit').click(function (){//表单提交
                var deptId = $('#dept').val();
                var institution = $('#institution').val();
                if(deptId){//提交后执行切换部门事件
                  $.post(baseUrl +'/switch',{deptId:deptId, instId:institution}).done(function () {
                    window.location.reload();//刷新页面
                  });
                }else{
                  layer.msg('请选择科室/部门...',{icon:0});
                }
              });
              me.getInstance();
            }
          })
        });

      },
      getInstance : function (){//获取机构列表初始化
        var me = this;
        var $institution = $('#institution');
        var $dept = $('#dept');
        $.ajax({
          url : base + '/login/getInst',
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
                  hosHtml += "<option value='"+val.id+"'>"+val.name+"</option>";
                });
                $institution.append(hosHtml);
                $institution.val(userNowInstId);

                me.selectDept(staffcode,userNowInstId,true);
                $institution.change(function(){//切换机构，获取部门列表
                  me.selectDept(staffcode, $(this).val());
                });

              }else{
                $institution.attr('disabled','disabled');
              }
            }
          },
          error : function(XMLHttpRequest, textStatus, errorThrown){
            layer.msg('连接失败，请联系管理员...',{icon:0});
          }
        });
      },
      selectDept : function(staffcode, institution,firstInit) {//获取部门列表
        var me = this;
        if (staffcode && institution) {
          var staffcode = $.trim(staffcode);
          var institution = $.trim(institution);
          var $dept = $('#dept');
          $.ajax({
            url : base + '/login/getDept',
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
                  firstInit && $dept.val(userNowDeptId);//初始化时设置初始部门
                }else{
                  $dept.attr('disabled','disabled');
                }
              }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
              layer.msg('连接失败，请联系管理员...',{icon:0});
            }
          });
        }
      },
      justOneUser : function (){
        var nowEyeUser= staffcode;
        $T.setCookie('eyetempuser',staffcode,{expires: 15});
        // console.log(nowEyeUser);
        var loopUserInfo = setInterval(function (){
          var newUserId= $T.getCookie('eyetempuser');
          if(nowEyeUser!==newUserId){
            clearInterval(loopUserInfo);
            layer.alert('当前账号已退出，如要操作请重新登录！',{
              title : null,resize : false,closeBtn : false,
              icon: 0, btnAlign: 'c',
              area : ['340px']
            },function (){
              window.location.reload();
            });

          }
          // else{
          //   console.log(newUserId,'ok');
          // }
        },5000);
      },
      tabOpenCloseFn : function (){
        $('#tabs').tabs({
          onSelect: function (title, index){
            var bFn = false;
            try{
              if($('.iframe-page').length && $('.iframe-page').eq(index-1).get(0)){
                var contWin = $('.iframe-page').eq(index-1).get(0).contentWindow;
                bFn = contWin.eye_onOpen || contWin.eye_onTabPageSelect;//子页面是否有 eye_onOpen 或者 eye_onTabPageSelect 函数
              }
            }catch(e){
              window.console && console.log(e);
            }
            if(bFn) {
              bFn();
            }
          },
          onUnselect: function (title, index){
            var bFn = false;
            try{
              if($('.iframe-page').length && $('.iframe-page').eq(index-1).get(0)){
                var contWin = $('.iframe-page').eq(index-1).get(0).contentWindow;
                bFn = contWin.eye_onTabPageUnselect;//子页面是否有 eye_onTabPageUnselect 函数
              }
            }catch(e){
              window.console && console.log(e);
            }
            if(bFn) {
              bFn();
            }
          },
          onAdd: function (title, index){
            var bFn = false;
            try{
              if($('.iframe-page').length && $('.iframe-page').eq(index-1).get(0)){
                var contWin = $('.iframe-page').eq(index-1).get(0).contentWindow;
                bFn = contWin.eye_onTabPageOpen;//子页面是否有 eye_onTabPageOpen 函数
              }
            }catch(e){
              window.console && console.log(e);
            }
            if(bFn) {
              bFn();
            }
          },
          onClose: function (title, index){
            var bFn = false;
            try{
              if($('.iframe-page').length && $('.iframe-page').eq(index-1).get(0)){
                var contWin = $('.iframe-page').eq(index-1).get(0).contentWindow;
                bFn = contWin.eye_onTabPageClose;//子页面是否有 eye_onTabPageClose 函数
              }
            }catch(e){
              window.console && console.log(e);
            }
            if(bFn) {
              bFn();
            }
          },
          onBeforeClose : function (title,index){
            var aFn = false, bFn = false;
            try{
              var contWin = $('.iframe-page').eq(index-1).get(0).contentWindow;
              aFn = conWin.eye_onTabPageBeforeClose;//子页面是否有 eye_onTabPageBeforeClose 函数
              bFn = contWin.eye_bfParTabWindowCloseTipMsg;//子页面是否有 eye_bfParTabWindowCloseTipMsg 函数
            }catch(e){
              window.console && console.log(e);
            }
            if(aFn){
              return aFn();
            }
            // window.console&&console.log(bFn);
            if(bFn){
              bFn().then(function(yes){
                if(yes){
                  $('#tabs').tabs('close',index);
                }
              });
              return false;
            }else{
              return true;
            };
          }
        });
      },
      popQueue : [],
      popNear : false,
      appendLoginInfo : function (){
        $('#tabs .tabs-wrap').prepend($('.userLoginInfo'));
      },
      nearExpire : function(){//近效期药品检查
        var me = this;
        var ashiBase = platforms.ahis || '/ahis';
        $.ajax({
          url : ashiBase +'/ui/drug/stock/checkNearExpire',//检查是否需要显示近效期药口  大于0则显示
          type: "post",
          async : false,//同步请求
          success : function (rst) {
            if(rst>0){
              me.popNear = true;
            }
          }
        });
      },
      openNearExpire : function (){//近效期药品显示
        var ashiBase = platforms.ahis || '/ahis';
        if(this.popNear){
          layer.open({
            title : '药品预警信息',
            // skin : 'mustRepairPop',
            type:2,
            content : ashiBase +'/ui/drug/stock/nearExpireIndex',
            area : ['1160px','500px']
          });
        }
      },
      eventShow: function(){
        var me = this;
        $.ajax({
          url : base+'/ui/sys/index/news',
          type: "post",
          success : function (rst) {
            if(rst.code == '200'){
              var h = '';
              sysNowTime = sysNowTime.replace(/-/g,"/");
              var nowTimeString = Date.parse(new Date(sysNowTime));//当前时间戳
              $.each(rst.data.rows,function (i,v) {
                var d = v.announceDate.substring(5,10);//截取只要 MM-dd
                var dstr = Date.parse(new Date(v.announceDate.replace(/-/g,"/"))); //新闻时间转化成时间戳
                var isNew = Math.floor((nowTimeString - dstr)/(3600*24*1000))<7; //新闻发布时间是否小于7天
                var newStr = isNew ? '<em class="em-new"></em>':''; //小于7天显示 new图标
                if(v.popFlag == 1){me.popQueue.push(v.id);}
                h += '<li class="li-event" title="'+v.titleInfo+'" rel="'+v.id+'"><span class="s-info">'+v.titleInfo+'</span>'+newStr+'<span class="s-date">'+d+'</span></li>';
              });
              $('#ul-events').html(h);
              me.popQueueRun();//弹窗队列执行
              $('.li-event').click(function () {
                var id = $(this).attr('rel');
                me.popOneNews(id);
              });

            }
          },
          error : function(XMLHttpRequest, textStatus, errorThrown){
            console.log(textStatus );
            me.popQueueRun();//弹窗队列执行
          }
      });
      },

      onlyOpenTitle: "功能导航",//不允许关闭的标签的标题
      favItemsInit : function(){
        var me = this;
        me.loadFavList();//初始加载收藏列表
        me.exEditFav();//点击开启编辑
        me.favOneItem();//点击收藏
      },
      runNowTime : function(){
          function rzero(v) {
            return v<10?('0'+v):v;
          }

          function toDateVal(dateS){
            var dt = new Date(dateS);
            var y = dt.getFullYear();    //获取完整的年份(4位,1970-????)
            var mo = rzero(dt.getMonth()+1);       //获取当前月份(0-11,0代表1月)
            var d = rzero(dt.getDate());        //获取当前日(1-31)
            // var da = dt.getDay();         //获取当前星期X(0-6,0代表星期天)
            // var t = myDate.getTime();        //获取当前时间(从1970.1.1開始的毫秒数)
            var h = rzero(dt.getHours());       //获取当前小时数(0-23)
            var m = rzero(dt.getMinutes());     //获取当前分钟数(0-59)
            var s = rzero(dt.getSeconds());     //获取当前秒数(0-59)

            return y+'-'+mo+'-'+d+' '+h+':'+m+':'+s;
          }

          sysNowTime = sysNowTime.replace(/-/g,"/");
          var timestamp = Date.parse(new Date(sysNowTime));
          var localteimStr = Date.parse(new Date());
          var timeL = timestamp-localteimStr;

          setInterval(function () {
            var nowTimeStr = Date.parse(new Date())+timeL;
            var nowtimeV = toDateVal(nowTimeStr);
            // timestamp = timestamp*1+1000;
            $('#sysTime').text(nowtimeV);
          },1000);
        },
        msgHub : {//msg参数hub
          loopTime : -1024, //轮序数据周期(ms) >0开始轮序
          flashTimes : 3, // 有消息时，闪动次数
          cur : 0, //当前消息面板指针
          sum : 0, //当前消息总数
          needRest : false , //是否休息(为true不请求)
          restTime : 1000, //频繁请求休息间隔(ms)
          autoHide : {//自动关闭msgbox
            o : null, //setTimeout 对象
            time : 500 //自动延迟关闭时间
          }
        },
        msgAlarmInit : function (){
          var me = this;
          if($('.div-msgInfo').length) {
            me.getMsgSum(function () {//获取消息数量
              if(me.msgHub.loopTime>0){//如果循环时间大于0，开始轮序取回消息数量
                $('.div-msgInfo').show();
                setInterval(function () {
                  me.getMsgSum();
                },me.msgHub.loopTime);

                $('.div-msgInfo').on('click',function () {//显示消息

                  if(!me.msgHub.needRest){//如果不在间歇休息
                    me.getMsgDetails(function () {//获取完消息信息后显示消息面板
                      if(me.msgHub.sum>0){//当消息总量大于0时显示消息
                        me.msgBoxShow(true);
                      }else{//显示无消息提示
                        me.msgBoxShow(false);
                      }
                    });//获取消息详情
                    me.getMsgRest();//激活请求间歇
                  }
                });

                $('.userMsgBox').on('mouseenter',function () {
                  me.clearMsgBoxAutoHide();
                }).on('mouseleave',function () {
                  me.msgBoxAutoHide();
                });

                $(document).on('click',function (e) {//关闭消息
                  var $tar = $(e.target);
                  var t = $tar.hasClass('.div-msgInfo') || $tar.hasClass('.layui-layer') || $tar.parents('.msgBox').length || $tar.parents('.layui-layer').length;
                  // window.console && console.log($tar,t);
                  if(!t){
                    me.msgBoxHide();
                  }
                });
              }else{//如果不循环，就是没有权限，直接不显示铃铛
                $('.div-msgInfo').hide();
              }
            },true);//获取消息总数
          }
        },
        msgBoxShow : function (hasMsg){
          $('.userMsgBox')[hasMsg?'removeClass':'addClass']('msgShowNoMsg');
          $('.userMsgBox').addClass('msgShow');
        },
        msgBoxHide : function (){
          $('.userMsgBox').removeClass('msgShow');
          clearTimeout(this.msgHub.autoHide.o);
        },
        msgBoxAutoHide : function (){//无反馈，autoHide.time毫秒后自动关闭msgbox
          var me = this;
          me.msgHub.autoHide.o = setTimeout(function () {
            me.msgBoxHide();
          },me.msgHub.autoHide.time);
        },
        clearMsgBoxAutoHide : function(){
          var me = this;
          clearTimeout(me.msgHub.autoHide.o);
        },
        flashMsg : function (){//如果有消息，闪动消息
          var me = this;
          var replay = me.msgHub.flashTimes,n = 1;
          var $msg = $('.div-msgInfo');
          var alarmIn = setInterval(function (){
            $msg.addClass('alarm');
            setTimeout(function (){
              $msg.removeClass('alarm');
            },400);
            n++;
            if(n>replay){clearInterval(alarmIn)}
          },800);
        },
        getMsgRest : function (){//消息请求间歇
          var me = this;
          me.msgHub.needRest = true;
          setTimeout(function () {
            me.msgHub.needRest = false;
          },me.msgHub.restTime);
        },
        setBadge : function(size){//设置badge状态和值
          var me = this;
          var $badge = $('.div-msgInfo .badge'),$dot = $('.div-msgInfo .dot');
          $badge.html(size);
          if(size>0){
            $badge.show();
            $dot.hide();
          }else{
            $badge.hide();
            $dot.show();
          }
          me.msgHub.sum = size;
        },
        getMsgSum : function (onceback,first){//获取总数
          var me = this;
          var param = {type:'sum'};
          var first = first || false;

          first && (param.state = 1);
          $.ajax({
            url : base+'/ui/sys/sysMessage/getMessage',
            type: "post",
            data : param,
            success : function (rst) {
              var size = rst.data.size;
              // size  = 5;
              me.setBadge(size);//设置badge状态和值
              if(size>0){
                me.flashMsg();
              }
              if(first){//如果是初始状态
                // interval = interval<10?10:interval;
                me.msgHub.loopTime = rst.data.interval*1000;
                onceback && onceback();
              }
            }
          });
        },
        getMsgDetails : function (callback){//获取消息详情
          // window.console && console.log('getMsgDetails');
          var me = this;
          var data = null;
          $.ajax({
            url : base+'/ui/sys/sysMessage/getMessage',
            type: "post",
            async : false,
            data : {type:'detail'},
            success : function (rst) {
              data  = rst.data;
            }
          });
          // /ui/sys/sysMessage/getMessage
          // type : 'sum' || 'detail'

          // data = {
          //     size: 5, interval: 5,
          //     biz: [{
          //       type: 1,size: 2,desc :'医嘱',data:  [
          //       {id:'11',area : '一病区',dept:'白内障科',bed:'5床',name:'张三',detail:'新开 停嘱 取消'},
          //       {id:'12',area : '二病区',dept:'白内障科',bed:'8床',name:'李四',detail:'新开'},
          //       ]//此处为医嘱消息格式
          //     },{
          //     type: 2,size: 3,desc :'病历',data:[{
          //       "bedNumber": "22床","inpDeptName": "白内障科","inpWardName": "一病区","patientName": "张三",
          //         "patientURL": "/ui/doc/writer/writeMedicalRecord?inpNumber=ZY20190629003",
          //         "eventContents": [
          //           {"contentOrUrl": "/ui/doc/writer/writeMedicalRecord?inpNumber=ZY20190629003",
          //             "promptingContent": "西医处方,剩余书写时间16小时53分钟"
          //           }, {
          //             "contentOrUrl": "/ui/doc/writer/writeMedicalRecord?inpNumber=ZY20190629003",
          //             "promptingContent": "麻醉同意书,剩余书写时间40小时53分钟"
          //           }
          //         ]
          //       }]//此处为病历消息格式
          //   }]
          // };

          me.setBadge(data.size);//设置badge状态和值
          var $Title = $('.msgTabTitle') , $cont = $('.msgTabInfo');
          var titleHtml='',msgHtml = '';

          data.biz[me.msgHub.cur].cur = true; //设置当前

          titleHtml = template('tem-msgTitle',data);
          $.each(data.biz,function (i,msg) {
            msgHtml += template('tem-msgType-'+msg.type,msg);
          });
          $Title.html(titleHtml);
          $cont.html(msgHtml);
          callback && callback();

          $('.s-msgTitle').on('click', function () {//切换消息
            var ix = $('.s-msgTitle').index(this);
            me.msgHub.cur = ix;//设置当前 ix
            $('.s-msgTitle').removeClass('s-msgTitle-now');
            $(this).addClass('s-msgTitle-now');
            $('.ul-msgInfo').removeClass('ul-msgInfo-now').eq(ix).addClass('ul-msgInfo-now');
          });

          me.sideNavE('.li-msg','li-msg-now');//侧边导航点击链接事件

          $('.li-msg').bind('click',function () {
            $(this).addClass('li-msgAlready');
            me.msgBoxHide();
          });
      },
      loadFavList : function(){
        var me = this;
        $.ajax({
          url : baseUrl +'/ui/sys/index/getFavor',
          dataType : 'json',
          type : 'POST',
          success : function(rst){
            if(rst.code==='200'){
               var liHtml = '';
               if(rst.data&&rst.data.menu&&rst.data.menu.length){
                  $('.menuNavBox').show();
                  $.each(rst.data.menu,function (i,v) {
                    var ic = v.icon?v.icon:'icon-box';
                    liHtml +='<li class="li-hnav" title="'+v.moduleName+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.moduleName+'</em></span><em class="em-del-item icon-minus_sign" rel="'+v.id+'"></em><div class="delMaskHandler" rel="'+v.id+'"></div></li>';
                  });
                  $('.ul-menuNav').html(liHtml);
                  me.sideNavE('.ul-menuNav .s-hnav','s-hnav-now');//重置点击事件
                  me.delFavItem();//重置点击删除事件
               }else{
                  $('.menuNavBox').hide();
               }

              if(rst.data&&rst.data.report&&rst.data.report.length){
                  var liRHtml0 = '';
                  var liRHtml1 = '';
                  var liRHtml2 = '';
                  var liRHtml3 = '';
                  $('.reportNavBox').show();
                  $.each(rst.data.report,function (i,v) {
                    var ic = v.icon?v.icon:'icon-box';
                    if(v.REPORTTYPE == '0'){
                        liRHtml0 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }else if(v.REPORTTYPE == '1'){
                        liRHtml1 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }else if(v.REPORTTYPE == '3'){
                      liRHtml3 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }else{
                        liRHtml2 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }
                  });
                  $('.ul-reportNav0').html(liRHtml0);
                  $('.ul-reportNav1').html(liRHtml1);
                  $('.ul-reportNav2').html(liRHtml2);
                  $('.ul-reportNav3').html(liRHtml3);
                  if($('.ul-reportNav3 li').length==0){$('.h4-title-k3').hide();}//管理报表没有数据，不显示标题
                  me.sideNavE('.ul-reportNav0 .s-hnav','s-hnav-now');//重置点击事件
                  me.sideNavE('.ul-reportNav1 .s-hnav','s-hnav-now');//重置点击事件
                  me.sideNavE('.ul-reportNav2 .s-hnav','s-hnav-now');//重置点击事件
                  me.sideNavE('.ul-reportNav3 .s-hnav','s-hnav-now');//重置点击事件
                }else{
                  $('.reportNavBox').hide();

                }
            }

            // layer.msg('菜单收藏成功!',{icon:1,time:3000});
          },
          error : function(XMLHttpRequest, textStatus, errorThrown){
            var rst = JSON.parse(XMLHttpRequest.responseText);
            var msg = '收藏菜单列表加载失败!';
            if(rst&&rst.msg){msg= rst.msg;}
            layer.msg(msg,{icon:7,time:3000});
          }
        });
      },
      extendNav : function (){
        $('.h3-mainNavTitle').click(function (){
          $(this).parent().toggleClass('li-mainnav-ex');
        });
      },
      indexNewsScroll : function(){
          $('.ul-news-Scroll').soMarquee({
            // wrapClass : 'newsScrollWrap',
            direction : 'up',
            speed : 100,
            pause : 0
          });
      },
      exEditFav : function(){
        $('.s-quickEdit').click(function () {
          var _self =$(this);
          var $ul = $('.ul-menuNav');
          var active = _self.hasClass('active');
          if(active){
            _self.removeClass('active').attr('title','编辑快捷图标');
            $ul.removeClass('ul-active');
          }else{
            _self.addClass('active').attr('title','退出编辑');
            $ul.addClass('ul-active');
          }
        });
      },
      delFavItem : function(){//取消收藏
        $('.delMaskHandler').click(function () {
          var _self = $(this);
          var itemId = _self.attr('rel');
            $.ajax({
              url : baseUrl +'/ui/sys/sysModuleFavorite/delete',
              dataType : 'json',
              type : 'POST',
              data : {moduleId:itemId},
              success : function(rst){
                if(rst.code==='200'){
                  var $li = _self.parents('.li-hnav');
                  $li.remove();
                  layer.msg('从我的快捷中移除成功!',{icon:1,time:2000});
                }
              },
              error : function(XMLHttpRequest, textStatus, errorThrown){
                var rst = JSON.parse(XMLHttpRequest.responseText);
                var msg = '删除请求失败，请检查网络，仍有问题请联系管理员！';
                if(rst&&rst.msg){msg= rst.msg;}
                layer.msg(msg,{icon:7,time:3000});
              }
          });
        });
      },
      favOneItem : function(){
        var me = this;
        $('.em-favor').click(function () {
          var id = $(this).attr('name');
          $.ajax({
            url : baseUrl +'/ui/sys/sysModuleFavorite/create',
            //dataType : 'json',
            type : 'POST',
            data : {moduleId:id},
            success : function(rst){
              me.loadFavList();//更新收藏列表
              layer.msg('添加到我的快捷成功!',{icon:1,time:2000});
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
              var rst = JSON.parse(XMLHttpRequest.responseText);
              var msg = '菜单添加失败!';
              if(rst&&rst.msg){msg= rst.msg;}
              layer.msg(msg,{icon:7,time:3000});
            }
          });
        });
      },
      exScreen : function () {
        $('.s-exScreen').click(function () {
          if ($(this).hasClass('s-inScreen')) {
            $.fullscreen(false);
            $(this).removeClass('s-inScreen');
          }else {
            $.fullscreen(true);
            $(this).addClass('s-inScreen');
          }
        });
      },
    extendArea : function(){
      $('.handlerAreaSide').click(function () {
        var _self = $(this);
         var ex = _self.hasClass('extend');
         if(ex){
           _self.removeClass('extend');
           $('.index-area').removeClass('areaIntend');
           $('.mainnav').removeClass('mainnavIntend');
         }else{
           _self.addClass('extend');
           $('.index-area').addClass('areaIntend');
           $('.mainnav').addClass('mainnavIntend');
         };
      });
    },
    noInWindow : function () {
      if(window.top !== window.self){window.top.location = window.location;}
    },
	mainResize : function(){
		$('.s-winResize').click(function () {
			var _self = $(this);
			if (_self.hasClass('inWinsize')) {
				_self.removeClass('inWinsize');
                $('#topHead').panel('expand');
                $('#mainCont').panel('restore');
				// $('.mainCont').animate({top:'46px'},function () {
				// 	$('.mainCont').css({zIndex:0});
				// });
			}else {
				_self.addClass('inWinsize');
                $('#topHead').panel('collapse');
                $('#mainCont').panel('maximize');
				// $('.mainCont').css({zIndex:8}).animate({top:0});
			}

		});
	},
      switchCompany : function () {
        var $comList = $('.ul-companyList');
        if ($comList.length) {
          $(document).on('click', function (e) {
              var $t = $(e.target);
              if ($t.hasClass('nowCompany')) {
                  $comList.show();
                  $t.addClass('nowCompany-over');
              } else {
                  $comList.hide();
                  $('.nowCompany').removeClass('nowCompany-over');
              };
          });
          $('.ul-companyList .s-c').click(function() {
            var rel = $(this).attr('rel');
            var url = $comList.attr('rel');
            $.post(baseUrl +'/switch',{deptId:rel}).done(function () {
              window.location.reload();
            });
          });

          $('.allCompanyNav').mouseleave(function () {
              $('.nowCompany').removeClass('nowCompany-over');
              $comList.hide();
          });

          var $nav = $('.allCompanyNav');
          setTimeout(function () {$nav.addClass('allCompanyNav-over');}, 300);
          setTimeout(function () {$nav.removeClass('allCompanyNav-over');}, 600);
          setTimeout(function () {$nav.addClass('allCompanyNav-over');}, 900);
          setTimeout(function () {$nav.removeClass('allCompanyNav-over');}, 1200);
          setTimeout(function () {$nav.addClass('allCompanyNav-over');}, 1500);
          setTimeout(function () {$nav.removeClass('allCompanyNav-over');}, 1800);

        };
      },
      repairPass : function () {
        var me = this;
        $('.s-repairPass').click(function () {
          var url = $(this).attr('rel');
          me.popRepairPass(url);
        });

        $('.s-repairStaffPass').click(function(){//修改个人信息
            layer.open({
            	type:2,
            	content : $(this).attr('rel'),
            	title :'我的个人信息',
            	area : ['875px','90%']
            });
        });
      },
      popRepairPass : function(url,mustRepair){
        var layerOpt = {
          type:2,
          content : url,
          title : '修改密码',
          area : ['400px','220px']
        };
        if(mustRepair){
          layerOpt.title='修改初始密码';
          layerOpt.skin='mustRepairPop';
        }
        layer.open(layerOpt);
      },
      loginOut : function () {
		    $('.a-loginOut').click(function () {
	          var href = $(this).attr("rel");
	          layer.confirm('你确定退出系统吗？', {
	              icon: 0, title:false,btnAlign: 'c'
	              }, function(){
	                $T.setCookie('eyetempuser','');
	                window.location.href=href;
	          });
	          return false;
	      });
      },
      searchKey : function () {
        var me = this;
        var nowI = -1;
        var $ul = $('.ul-searchList');
        var $li = $('.li-searchlist');

        $(document).on('click','.li-searchlist',function () {//点击列表处理
            //
            var url  = $(this).find("span").attr('url');
            if(url == undefined ) {
              return false;
            }
            $('.txt-search').val($(this).text());
            me.addTab($(this).text(),url);
            $ul.hide();
            nowI = -1;
            return false;
        });

        $(document).on('click',function(e){
            var $target = $(e.target);
            if (!($target.hasClass('li-searchlist')||$target.hasClass('s-searchkey'))) {
                $ul.hide();
            };
          });
        function  buildSearchList(val) {
          $ul.html("");
          var finder = false;
          for(var i=0;i< searchMenus.length; i++ ) {
            if( searchMenus[i].moduleName.indexOf(val)  >= 0  ) {
                finder = true;
                var $li = $('<li class="li-searchlist"><span title="' + searchMenus[i].moduleName+ '" class="s-searchkey" url="' +  searchMenus[i].url+ '">' + searchMenus[i].moduleName+ '</span></li>');
                $ul.append($li);
            }
          }
          if(!finder) {
              var $li = $('<li class="li-searchlist"><span class="s-searchkey" >没有查到符合的菜单...</span></li>');
              $ul.append($li);
          }

        }

        $('.txt-search').keyup(function (e) {
          var _self = $(this);
          var val = $.trim(_self.val());
          var keycode = e.keyCode;
          if (val!=='') {
            buildSearchList(val);
            var offset = $(".txt-search").offset();
            $ul.show().css({
              top :offset.top + 25,
              left: offset.left
            });
            var  lock = (keycode>36&&keycode<41||keycode==13)?true:false;//是否在操作光标键
            if (lock) {
                var keyLen = $li.length;
                if (keycode==38) {//键盘上键事件
                  nowI = (nowI>0)?(nowI-1):0;
                  liEvent(_self);
                }
                if (keycode==40) {//键盘下键事件
                  nowI = (nowI<(keyLen-1))?(nowI+1):(keyLen-1);
                  liEvent(_self);
                }
                if (keycode==13) {//键盘回车键事件
                  if (nowI!=-1) {
                    var v = $li.eq(nowI).text();
                    _self.val(v);
                    me.addTab(v,'booking.html');
                    $ul.hide();
                    nowI = -1;
                  }
                }
            };

          };
        });

      function liEvent(_self) {//键盘上下处理
        $li.removeClass('li-searchlist-now');
        var nowLi = $li.eq(nowI);
        _self.val(nowLi.text());
        nowLi.addClass('li-searchlist-now');
      }



      },
      mainnavClick : function () {
        var me = this;
        var delayE = null;
        $('.s-mainnav').mouseenter(function() {
          var _self = $(this);
          delayE = setTimeout(function(){
            if (!_self.hasClass('s-mainnav-now')) {
              var rel = _self.attr('rel');
              $('.s-mainnav-now').removeClass('s-mainnav-now');
              _self.addClass('s-mainnav-now');
              // if (rel=='#subnav-my') {
              //     $('.subnav').show();
              //   }else{
                $('.li-mainnav-p').removeClass('li-mainnav-p-now');
                _self.parents('.li-mainnav-p').addClass('li-mainnav-p-now');
                $('#submain-my,.ul-submainnav').hide();
                $('.subnav').hide();
                $(rel).show();
                // };
            };
          },300);
        }).mouseleave(function(){
          delayE&&clearTimeout(delayE);
        });

        $('.s-submainnav').click(function() {
          if (!$(this).hasClass('s-submainnav-now')) {
            var rel = $(this).attr('rel');
            if (rel) {
              $('.s-submainnav-now').removeClass('s-submainnav-now');
              $(this).addClass('s-submainnav-now');
              $('.subnav').hide();
              $(rel).show();
            };
          };
        });


      },

    menuTarget : null,
      rightmenu : function () {
        var me = this;
        $(".s-hnav").bind('contextmenu',function(e){
          $('#mm2').menu('show', {
            left: e.pageX,
            top: e.pageY
          });
          me.menuTarget = $(this).parent('li');
          e.preventDefault();
          return false;
        });
      },
      menuClick : function () {
        var me = this;
          $('#mm2').menu({
              onClick: function (item) {
                var $li = me.menuTarget;
                switch (item.id) {
                  case  'up':
                    var $prev = $li.prev('li');
                    if ($prev) {
                      $li.after($prev);
                    };
                    break;
                  case  'down':
                    var $next = $li.next('li');
                    if ($next) {
                      $li.before($next);
                    };
                    break;
                  case  'first':
                    var $ul = $li.parent();
                    $ul.prepend($li);
                    break;
                  case  'last':
                    var $ul = $li.parent();
                    $ul.append($li);
                    break;
                  case  'del':
                    $li.remove();
                    break;

                }
              }
          });
      },
      _setIframeH : function () {
          var iframeH = $('.mainCont').height()-40;
          $('#mainIframe').height(iframeH);
      },
      setIframeH : function () {
        var me = this;
        me._setIframeH();
        $(window).resize(function() {
          me._setIframeH();
        });
      },
      sideNavE : function ($t,nowCls,beforeAddTab,afterAddTab) {
        var me = this;
        $($t).click(function() {
          var _self = $(this);
          var url = _self.attr('rel');
          if (url&&url!='/#') {
            if(url.indexOf('gx?url=')>-1){
              window.open(url);
              return;
            }
            $('.'+nowCls).removeClass(nowCls);
            _self.addClass(nowCls);

            var tabTitle = _self.attr('stitle')|| _self.attr('title');
            tabTitle = tabTitle||_self.find('.em-nav').text()||_self.text();
            beforeAddTab && beforeAddTab(tabTitle);
            me.addTab(tabTitle,url);
            afterAddTab && afterAddTab(tabTitle)
            // $('#mainIframe').attr('src',url);
          }else{
            layer.msg('没有为菜单配置url地址!',{icon:7,time:3000});
          };
        });
      },
      tabIfExit : function(title,url){
        var tabI = $('#tabs').tabs('getTab',tabTitle)
        var ta = $('#tabs').tabs('exists',tabTitle);
        if(ta){
          var tab
        }
      },
      urlPKey :'pgv',
      tabExist : function(tabTitle){
        return $('#tabs').tabs('exists',tabTitle);
      },
      selectTabByTitle : function(tabTitle){
        var $tab = $('#tabs');
        if($tab.tabs('exists',tabTitle)){
          $tab.tabs('select',tabTitle);
        }
      },
      addTab : function(tabTitle,url,unselected){
        var me = this;
        // var randomNum = Math.floor(Math.random()*10000000000);
        // var linkStr = url.indexOf('?')>-1?'&':'?';
        // var newUrl = url+linkStr+me.urlPKey+'='+randomNum;
        var newUrl = url;
        if(!$('#tabs').tabs('exists',tabTitle)){
          var $iframe = me._createFrame(newUrl);
          $('#tabs').tabs('add',{
            title:tabTitle,
            content:$iframe,
            selected : !unselected,
            closable:true
          });
        }else{
          var $tab = $('#tabs').tabs('getTab',tabTitle);
          $tab.find('.iframe-page').attr('src',newUrl);
          !unselected && $('#tabs').tabs('select',tabTitle);
        // }else{
        //   $('#tabs').tabs('select',tabTitle);
        //   $('#refresh').click();
        }

        me.tabClose();
      },
      closeTab : function(tabTitle){
        $('#tabs').tabs('close',tabTitle);
      },
      tabClose : function(){
        /*双击关闭TAB选项卡*/
        $(".tabs-inner").dblclick(function(){
          var subtitle = $(this).children(".tabs-closable").text();
          $('#tabs').tabs('close',subtitle);
        })
        /*为选项卡绑定右键*/
        $(".tabs-inner").bind('contextmenu',function(e){
          var ix = $(".tabs-inner").index(this);
          if(ix>0){
            $('#mm').menu('show', {
              left: e.pageX,
              top: e.pageY
            });
          }

          var subtitle =$(this).children(".tabs-closable").text();

          $('#mm').data("currtab",subtitle);
          $('#tabs').tabs('select',subtitle);
          return false;
        });
      },
      tabCloseEven : function() {//绑定右键菜单事件
        var me = this;
        $('#mm').menu({
          onClick: function (item) {
            me._closeTab(item.id);
          }
        });
        return false;
      },
      _createFrame : function(url){
        var $iframe = '<iframe class="iframe-page" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        return $iframe;
      },
      _closeTab : function(action){
        var me = this;
        var alltabs = $('#tabs').tabs('tabs');
        var currentTab =$('#tabs').tabs('getSelected');
        var allTabtitle = [];
        $.each(alltabs,function(i,n){
          allTabtitle.push($(n).panel('options').title);
        })

        switch (action) {
          case "refresh":
            var ifOpt = currentTab.panel('options');
            if(ifOpt.title==='单据打印')return;//如果是打印页面，不允许右键菜单刷新
            var src = $(currentTab.find('iframe')).attr('src');
            var newSrc = src;
            // var splitStr = me.urlPKey+'=';
            // var randomNum = Math.floor(Math.random()*10000000000);
            // if(newSrc.indexOf(splitStr)>-1){
            //   newSrc = src.split(splitStr)[0]+splitStr+randomNum;
            // }
            $('#tabs').tabs('update', {
              tab: currentTab,
              options: {
                content: me._createFrame(newSrc)
              }
            })
            break;
          case "close":
            var currtab_title = currentTab.panel('options').title;
              if (currtab_title != me.onlyOpenTitle){
                  $('#tabs').tabs('close', currtab_title);
              }
            break;
          case "closeall":
            $.each(allTabtitle, function (i, n) {
              if (n != me.onlyOpenTitle){
                $('#tabs').tabs('close', n);
              }
            });
            break;
          case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
              if (n != currtab_title && n != me.onlyOpenTitle){
                $('#tabs').tabs('close', n);
              }
            });
            break;
          case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
              return false;
            }
            $.each(allTabtitle, function (i, n) {
              if (i > tabIndex) {
                if (n != me.onlyOpenTitle){
                  $('#tabs').tabs('close', n);
                }
              }
            });

            break;
          case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
              return false;
            }
            $.each(allTabtitle, function (i, n) {
              if (i < tabIndex) {
                if (n != me.onlyOpenTitle){
                  $('#tabs').tabs('close', n);
                }
              }
            });

            break;
          case "exit":
            $('#closeMenu').menu('hide');
            break;
        }
      }
};

window.addEventListener('message',function(e){
  // window.console&&console.log(e);
  var data = e.data;
  if(typeof(data)=='string'){
    data = data.split('^^^');
    if(data[1]){
      eyeIndex.addTab(data[0],data[1]);
    }
  }
});

var $topTools = {
  tabExist : function(tabTitle){
    return $('#tabs').tabs('exists',tabTitle);
  },
  getCurTabTitle: function (){//获取当前tab的title
    return $('.tabs .tabs-selected').text();
  },
  selectTabByTitle : function(tabTitle){//根据title 选中tab
    var $tab = $('#tabs');
    if($tab.tabs('exists',tabTitle)){
      $tab.tabs('select',tabTitle);
    }
  },
  getTabWindowByTitle : function (tabTitle){//根据title 获取 tab页的window
    var $tab = $('#tabs').tabs('getTab',tabTitle);
    if($tab){
      return $tab.find('.iframe-page')[0].contentWindow;
    }
  },
  popMsg : function (msg,type,time){
    type = type || 7;
    time = time || 2000;
    var iconType = {
      warning : 7,
      success : 1
    };
    layer.msg(msg,{icon:iconType[type],time:time});
  },
  popConfirm : function (msg,ok,cancel){//全局confirm
    layer.confirm(msg,{
      icon: 0, title:false,btnAlign: 'c'
    },ok,cancel);
  },
  closeTabByTitle : function(tabTitle){//根据title 关闭tab页
    $('#tabs').tabs('close',tabTitle);
  }
};


var eyeStore = {//数据中心
  comData : {}//暂存子页面间通讯数据

};


$(function () {
  eyeIndex.init();
});



//设置一个全局 ws对象.

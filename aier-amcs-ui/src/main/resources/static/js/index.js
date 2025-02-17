$.fn.hoverClass=function(b){var a=this;a.each(function(c){a.eq(c).mouseenter(function(){$(this).addClass(b)});a.eq(c).mouseleave(function(){$(this).removeClass(b)})});return a};
!function(t){t.fn.soMarquee=function(n){var s={direction:"left",wrapClass:"",width:null,height:null,step:1,speed:10,pause:1e3,overStop:!0,overEvent:function(){},clickEvent:function(){}},r={};for(i in s)t(this).attr(i)&&(r[i]=t(this).attr(i));var a=t(this).data("marquee")||{};return t.extend(s,a,r,n||{}),new e(this,s).init(),this};var e=function(e,i){this.o=t(e),this.opt=i};e.prototype={init:function(){function e(){var t,e=0;if(u<2?(e=parseInt(n.css("marginLeft")),t=a,0==u?n.css({marginLeft:-e>p?0:e+s.step+"px"}):n.css({marginLeft:e>0?-p:e+s.step+"px"})):(e=parseInt(n.css("marginTop")),t=o,2==u?n.css({marginTop:-e>c?0:e+s.step+"px"}):n.css({marginTop:e>0?-c:e+s.step+"px"})),s.pause>0&&e%t==0){var i=s.step;s.step=0,setTimeout(function(){s.step=i},s.pause)}}var n=this.o,s=this.opt,r=n.find("li"),a=r.outerWidth(!0),o=r.outerHeight(!0),h=r.length,p=parseInt(a)*h,c=parseInt(o)*h,u=jQuery.inArray(s.direction,["left","right","up","down"]);s.step=u%2?s.step:-s.step,s.width=s.width?s.width:n.parent().width(),s.height=s.width?s.height:n.parent().height();var d=t('<div style="position:relative;overflow:hidden;"></div>');if(s.wrapClass&&d.addClass(s.wrapClass),u<2&&p>s.width)n.width(2*p),d.css({width:s.width}),s.step>0&&n.css({marginLeft:-p});else{if(!(u>1&&c>s.height))return;n.height(2*c),d.css({height:s.height}),s.step>0&&n.css({marginTop:-c})}n.wrap(d).append(r.clone());var f;clearInterval(f),f=setInterval(e,s.speed),1!=s.overStop&&"true"!=s.overStop||n.hover(function(){clearInterval(f)},function(){f=setInterval(e,s.speed)}),n.find("li").mouseover(function(){return i=n.find("li").index(t(this))%h,s.overEvent(this,i)}),n.find("li").click(function(){return i=n.find("li").index(t(this))%h,s.clickEvent(this,i)})}},t(function(){t(".soMarquee").length&&t(".soMarquee").each(function(){t(this).soMarquee()})})}(jQuery);

var eyeIndex = {
      init : function () {
        var me = this;
        $('.li-mainnav').hoverClass('li-mainnav-over');
        // me.exScreen();//全屏
        me.setIframeH();//设置iframeH
        me.mainnavClick();
        me.sideNavE('.s-routnav','s-routnav-now');//侧边导航点击链接事件
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
        me.searchKey();
        me.extendArea();
        me.extendNav();
		    me.mainResize();
        me.favItemsInit();//收藏事件
        // me.runNowTime();//头部区域动态当前时间
        me.indexNewsScroll();//新闻滚动
        me.tabBeforeCloseFn();//tab关闭前处理事件
        me.dutyAlarm();//任务消息提醒
        //me.addTab('系统首页',baseUrl+'/ui/common/map/index');
        // me.addTab('挂号清单','bookingList.html',true);
        // $('#tabs').tabs('select','功能导航');

        if(openCloud==='true' && cloudsSize*1>1){
          me.showPlatforms();
        }
      },
      showPlatforms:function (){
        $('.h1-logo').addClass('canChangePfm showtip');

        setTimeout(function (){
          $('.h1-logo').removeClass('showtip');
        },3000);

        $('.img-logo').click(function (){
          layer.open({
            type:1,
            skin: 'popPlatform',
            title : null,btn:null,
            shadeClose:true,
            content: template('tem-changePlatforms',{}),
            area : ['880px','410px']
          })
        });
      },
      tabBeforeCloseFn : function (){
        $('#tabs').tabs({
          onBeforeClose : function (title,index){
            var bFn = false;
            try{
              bFn = $('.iframe-page').eq(index-1).get(0).contentWindow.eye_bfParTabWindowCloseTipMsg;//子页面是否有 eye_bfParTabWindowCloseTipMsg 函数
            }catch(e){
              window.console && console.log(e);
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
        dutyRenderUl : function(rows){//渲染消息列表
          var msgHtml = '';
          if(rows.length){
            $.each(rows,function (i,v){
              var sCls = v.messageType==2?'s-duty-to':'s-duty-details';
              var msg = v.messageContent;
              if(msg.length>35){
                msg = msg.substr(0,35)+'...';
              }
              var url  = '/'+v.platformCode.toLowerCase()+v.messageUrl;
              msgHtml += '<li class="li-duty"><span id="s-'+v.id+'" class="s-duty s-warning '+sCls+'" type="'+v.messageType+'" stitle="'+v.messageTitle+'" title="'+v.messageContent+'" rel="'+url+'" ><strong class="s-title"><em class="icon icon-exclamation_sign"></em>['+v.createDate+'] </strong>'+msg+'</span></li>';
            });
          }else{
            msgHtml = '<li class="li-dityHasNoMsg">暂无消息...</li>'
          }
          return msgHtml;
        },
        dutyAlarm : function (){
          var me = this;
          if($('.div-dutyInfo').length) {
            $('.s-dutyTitle').on('mouseenter', function () {
              var ix = $('.s-dutyTitle').index(this);
              $('.s-dutyTitle').removeClass('s-dutyTitle-now');
              $(this).addClass('s-dutyTitle-now');
              $('.ul-dutyInfo').removeClass('ul-dutyInfo-now').eq(ix).addClass('ul-dutyInfo-now');
            });
            me.dutyAlarmDo();
            setInterval(function () {//每隔10分钟请求一次
              me.dutyAlarmDo();
            }, 600000);
          }
        },
        dutyAlarmDo : function (){//消息请求
          var me = this;
          var $duty = $('.div-dutyInfo');
//获取消息分页列表
// /ui/sys/sysMessage/getMyList
//{"id":null,"beginDate":null,"bizId":null,"bizType":null,"createDate":null,"creator":null,"endDate":null,"gapSeconds":null,"hospId":null,"messageContent":null,"messageTitle":null,"messageType":null,"messageUrl":null,"modifer":null,"modifyDate":null,"platformCode":null,"state":"0未读 1已读"},
//设置消息状态为已读
// /ui/sys/sysMessage/updateMessageState
// {id:'消息id',type:1 }
          var unRMsgData = [],reMsgData = [];
          var q1 = $.ajax({
            url : base+'/ui/sys/sysMessage/getMyList',
            type: "post",
            data : {state:0}
          });
          var q2 = $.ajax({
            url : base+'/ui/sys/sysMessage/getMyList',
            // async : false,
            type: "post",
            data : {state:1,page:1,rows:1}
          });

          $.when(q1,q2).then(function (rst1,rst2) {//请求已读消息和未读消息
            // window.console && console.log(rst1[0].data,rst2[0].data);
            unRMsgData = rst1[0].data.rows;//未读消息
            reMsgData = rst2[0].data.rows;//已读消息
            $('.ul-dutyUnRead').html('<li class="li-setState"><span class="s-allReaded"><em class="icon-ok_sign"></em>全部设为已读</span></li>');//渲染未读消息
            $('.ul-dutyUnRead').append(me.dutyRenderUl(unRMsgData));//渲染未读消息
            $('.ul-dutyAlready').html(me.dutyRenderUl(reMsgData));//渲染已读消息
            var urMsgLen = unRMsgData.length;

            if(urMsgLen){//如果有未读消息，闪动
              $duty.find('.badge').html(urMsgLen);
              var replay = 8,n = 1;
              var alarmIn = setInterval(function (){
                $duty.addClass('alarm');
                setTimeout(function (){
                  $duty.removeClass('alarm');
                },400);
                n++;
                if(n>replay){clearInterval(alarmIn)}
              },800);
              $duty.on('mouseenter',function (){
                clearInterval(alarmIn);
              });
              // $('.em-allReaded').show();

              $('.s-allReaded').unbind('click').bind('click',function () {
                var ids = [];
                $.each(unRMsgData,function (i,v) {
                  ids.push(v.id);
                });
                $.ajax({//全部设为已读
                  url : base+'/ui/sys/sysMessage/updateMessageState',
                  type : 'POST',
                  headers: {//json格式
                    Accept: "application/json; charset=utf-8",
                    'Content-Type' :'application/json'
                  },
                  data : JSON.stringify({ids:ids,type:1}),//json格式
                  success : function (rst) {
                    if(rst.code=='200'){
                      $('.ul-dutyUnRead').find('.li-duty').addClass('li-dutyAlready');
                    }
                  }
                });

              });

            }else{
              $duty.find('.badge').html('0');//没有消息设为0
            }

            me.sideNavE('.s-duty-to','s-duty-now');//侧边导航点击链接事件


            $('.s-duty').bind('click',function () {
              //设置为已读
              var id = $(this).attr('id').split('-')[1];
              var _self = $(this);
              $.ajax({
                url : base+'/ui/sys/sysMessage/updateMessageState',
                type : 'POST',
                headers: {//json格式
                  Accept: "application/json; charset=utf-8",
                  'Content-Type' :'application/json'
                },
                data : JSON.stringify({id:id,type:1}),//json格式
                success : function (rst) {
                  if(rst.code=='200'){
                    _self.parent('.li-duty').addClass('li-dutyAlready');
                  }
                }
              });

              if($(this).hasClass('s-duty-details')){//查看详情
                var title = $(this).attr('stitle');
                var content = $(this).attr('title');
                // window.console&&console.log(title,content);
                layer.open({
                  type: 1,
                  title : title,
                  content : '<div class="dutyPopContent">'+content+'</div>',
                  area : ['400px','auto']
                });
              };

              // $(this).parent('.li-duty').addClass('li-dutyAlready');
            });

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
                  $('.reportNavBox').show();
                  $.each(rst.data.report,function (i,v) {
                    var ic = v.icon?v.icon:'icon-box';
                    if(v.REPORTTYPE == '0'){
                        liRHtml0 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }else if(v.REPORTTYPE == '1'){
                        liRHtml1 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }else{
                        liRHtml2 +='<li class="li-hnav" title="'+v.NAME+'"><span class="s-hnav" rel="'+v.url+'"><em class="em-icon '+ic+'"></em><em class="em-nav">'+v.NAME+'</em></span></li>';
                    }
                  });
                  $('.ul-reportNav0').html(liRHtml0);
                  $('.ul-reportNav1').html(liRHtml1);
                  $('.ul-reportNav2').html(liRHtml2);
                  me.sideNavE('.ul-reportNav0 .s-hnav','s-hnav-now');//重置点击事件
                  me.sideNavE('.ul-reportNav1 .s-hnav','s-hnav-now');//重置点击事件
                  me.sideNavE('.ul-reportNav2 .s-hnav','s-hnav-now');//重置点击事件
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
        var offset = $(".txt-search").offset();
        $ul.css({
              top :offset.top + 25,
              left: offset.left
         });


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
                var $li = $('<li class="li-searchlist"><span class="s-searchkey" url="' +  searchMenus[i].url+ '">' + searchMenus[i].moduleName+ '</span></li>');
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
              $ul.show();
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
            if(url.indexOf('/jt/bi/')>-1){
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

(function () {

  var msgs = {
    AHIS_1 : {
      type : 1,
      bizType : 'AHIS_1',
      desc : '医嘱',
      size : 0,
      data : []
    },
    AEMR_Admission : {
      type : 2,
      bizType : "AEMR_Admission",
      desc : '病历',
      size : 0,
      data : {}
    },
    ALIS_CV : {
      type : 3,
      bizType : "ALIS_CV",
      desc : '危急值',
      size : 0,
      data : {}
    }
  };

  var $d = {
    msgWrap : null,
    msgBox : null,
    badge : null,
    dot : null,
    title : null,
    cont : null
  };

  function countEndTime (oneRow){
    // var endTimeStr = Math.ceil((new Date(onerow.endDate.replace(/-/g, "/")) - new Date(sysNowTime.replace(/-/g, "/")))/(3600*1000));
    var endTimeStr = Math.ceil((new Date(oneRow.endDate.replace(/-/g, "/")) - new Date())/(3600*1000));
    if(endTimeStr<1){endTimeStr = 1;}
    // console.log(msg.endDate,endTimeStr)
    oneRow.endHourLast = '时间不足'+endTimeStr+'小时';
  };

  function IMsg(opt) {
    var o = opt;
    this.el = $(o.el);
    this.cur = 0; // 当前显示的tab标签
    this.msgSize = 0; //当前总消息数量
    this.alwaysFlash = null; //是否总闪动
    this.kinds = []; //所有存在分类
    this.flashTimes = o.flashTimes || 4; // 闪动重复次数
    this.autoHide = {//自动关闭msgbox
      o : null, //setTimeout 对象
      time : 500 //自动延迟关闭时间
    }
    this.getDom(); //初始化dom对象

    this.initData();//初始化数据
    this.msgInlayout();//消息在布局中的显示和隐藏处理
    this.goMsgInit();//消息连接初始化
  };

  IMsg.prototype = {
    getDom : function(){
      var $el = this.el;
      $d = {
        msgWrap: $el.find('.userMsgBox'),
        msgBox : $el.find('.div-msgInfo'),
        badge : $el.find('.badge'),
        dot : $el.find('.dot'),
        title : $el.find('.msgTabTitle'),
        cont : $el.find('.msgTabInfo')
      };
    },
    initData : function (){//消息 初始化显示
      var me = this;
      $.ajax({
        url : base+'/ui/sys/sysMessage/getMessagePush',
        type: "post",
        success : function (rst) {
          // var rst = temData;
          // var rst = temData2;
          // console.log(rst);
          me.setBadgeState('num',rst.data.length);//设置badge

          $d.msgBox.show().on('click',function () {//显示消息
            if(me.msgSize>0){
              me.msgBoxShow(true);
              me.reDomAemr();//点击铃铛时，重新渲染aemr病历部分
            }else{
              me.msgBoxShow(false);
            }
          });

          $.each(rst.data,function (i,v){//处理消息，合并到 msgs 消息集中
            // console.log(JSON.parse(v.messageContent));
            var thisMsg = me.mergeMsg(v);
            if(!thisMsg.cont){
              me.setBadgeState('state',0);//更新badge
            }
            // console.log(thisMsg);
          });

          me.alwaysFlashMsg();//是否 有危急值，有则一直闪动
          console.log(msgs);
          $.each(msgs,function (k,v){// 根据消息集 初始化渲染 tab内容
            if(v.size>0){
              me.renderKind(v);
            }
          });
        }
      });
    },
    goMsgInit : function (){
      var me = this;
      if(goeasyKey == ''){
    	  goeasyKey = 'BS-fce4fc90cc3b4337b713b942d2549d62';
      }

      GEcontrol.init(goeasyKey,pushChannel,function (evMsg){
        me.receiveMsg(evMsg);
      });
    },
    mergeMsg : function (msg){//合并新消息到消息集 msgs 中
      var me = this;
      //AHIS_1
      //AEMR_Admission
      if(msg.bizType == 'AEMR_Discharge'){
        msg.bizType = 'AEMR_Admission';
      }
      var msgBox = msgs[msg.bizType];//msg 对应的数据集
      var state = (msg.state*1===0)?1:0;// 1 新增或更新 ，0 删除
      return me.actionType[msg.bizType](msgBox,msg,state,me);
    },
    reDomAemr : function (){//重新渲染 病历 部分
      var me = this;
      // console.log(this.actionType.countEndTime);
      var aemrData = msgs.AEMR_Admission.data;
      // console.log(aemrData);
      $.each(aemrData,function (k,v){
        $.each(v.eventContents,function (j,w){//根据当前时间，重新计算倒计时
          countEndTime(w);
        });
      });
      me.renderItemsByType(msgs.AEMR_Admission.type,msgs.AEMR_Admission.data,true);//重新渲染 AEMR_Admission
    },
    actionType : {//分类 处理函数
      //接收参数：( msgBox: msgs中对应的msg对象， msg: 消息内容， state ： 1 (新增或更新) / 0 (删除) )
      //返回 returnMsg : {cont：新增更新时为数据本身，删除时为需要删除的节点 ，state : 0(删除) / 1(新增) / 2(更新)}
      AHIS_1 : function (msgBox,msg,state){ // AHIS_1
        msg.messageContent = msg.messageContent || '{}';
        var cont = JSON.parse(msg.messageContent);
        var returnMsg = {cont:null,state:state};//cont:返回内容，state: 0 删除 , 1 新增 ,2 更新
        cont.bizId = '' + msg.bizId;
        cont.messageUrl = msg.messageUrl;
        cont.messageTitle = msg.messageTitle;

        var totalS = (cont.state2 || 0) + (cont.state3 || 0) + (cont.state5 || 0) + (cont.state6 || 0) ;
        //totalS>0 为需要处理的新增或更新数据

        if(state){//新增
          var stateIsUpdate = -1;//是否是 更新
          $.each(msgBox.data,function (i,v){//轮询查验bizId是否存在，存在 说明是更新此条数据
            if(v.bizId == cont.bizId){stateIsUpdate = i;return false;}
          });

          if(stateIsUpdate>-1){//如果存在此用户记录
            //如果存在，预先删除
            var $liT = $('#msgOne-' + cont.bizId);
            if($liT.length){//删除已存在的那一行， 更新也是重新插入
              $liT.remove();
            }

            if(totalS>0) {//状态 总和大于0更新，等于0删除
              returnMsg.state = 2; //状态设置为 更新
              msgBox.data[stateIsUpdate] = cont;
            }else{//删除
              msgBox.size = msgBox.size>0?msgBox.size:0;
              var delIx = -1;
              $.each(msgBox.data,function (i,v){
                if(v.bizId === cont.bizId){
                  delIx = i;
                }
              });
              if(delIx>-1){
                msgBox.size--;
                msgBox.data.splice(delIx,1);
                returnMsg.state = 0;//状态设置为删除
                cont = $('#msgOne-' + cont.bizId);//删除返回的是需要删除的 dom对象
              }else{
                returnMsg.state = 2;
                cont = null;
              }
            }
          }else{//是全新记录
            if(totalS>0){//状态 总和大于0 新增 ，等于0忽略
              msgBox.size++;
              msgBox.data.push(cont);
            }else{//忽略
              cont = null;
              returnMsg.state = 0;
            }
          }
          returnMsg.cont = cont;
        }else{//删除
          var delIx = -1;
          $.each(msgBox.data,function (i,v){
            if(v.bizId === cont.bizId){
              delIx = i;
            }
          });
          if(delIx>-1){
            msgBox.size--;
            msgBox.size = msgBox.size>0?msgBox.size:0;
            returnMsg.cont = $('#msgOne-' + cont.bizId);//删除返回的是需要删除的 dom对象
            msgBox.data.splice(delIx,1);
          }else{
            returnMsg.state = 2;
          }
        }
        return returnMsg;
      },
      AEMR_Admission : function (msgBox,msg,state){ // AEMR_Admission
        var cont = JSON.parse(msg.messageContent);
        var returnMsg = {cont:null,state:state};//cont:返回内容，state: 0 删除 , 1 新增 ,2 更新
        var key = cont.key = cont.uniquePersonKey; //用户信息唯一key

        cont.eventContents[0].bizId = msg.bizId;
        cont.eventContents[0].endDate = msg.endDate;
        //sysNowTime
        countEndTime(cont.eventContents[0]);
        //西医处方剩余书写时间不足1小时

        // console.log(msg.endDate,cont.eventContents[0]);
        if(msgBox.data[key]){//存在此人的信息，合并到此人信息中  (包含删除逻辑)
          var contents = msgBox.data[key].eventContents;

          if(state){//新增 或 更新
            var stateIsUpdate = -1;//是否是 更新
            $.each(contents,function (i,v){//轮询查验bizId是否存在，存在 说明是更新此条数据
              if(v.bizId == msg.bizId){stateIsUpdate = i;return false;}
            });
            if(stateIsUpdate>-1){//如果是更新
              returnMsg.state = 2; //状态设置为 更新

              var cloneCont = $.extend(true,cont);//复制一个没有 eventContents的对象进行合并
              delete cloneCont.eventContents;//删除 eventContents
              $.extend(msgBox.data[key],cloneCont);//合并

              contents[stateIsUpdate] = cont.eventContents[0];//更新对应 指针 的数据
            }else{//是新增
              msgBox.size++;
              contents.push(cont.eventContents[0]);
            }
            var $liT = $('#li-msg-'+cont.key);
            if($liT.length){//删除已存在的那一行，方便后面重新整体插入
              $liT.remove();
            }
            returnMsg.cont = msgBox.data[key];
          }else{//删除
            msgBox.size--;
            msgBox.size = msgBox.size>0?msgBox.size:0;
            var delIx = null;
            $.each(contents,function (i,v){
              if(v.bizId == msg.bizId){
                delIx = i;
              }
            });

            returnMsg.cont = $('#msgOne-' + msg.bizId);//删除返回的是需要删除的 dom对象
            contents.splice(delIx,1);//删除
            if(contents.length==0){//如果没有一条数据，删除整行
              delete msgBox.data[key];
              returnMsg.cont = returnMsg.cont.parent('li');//删除对象为父 li
            }
          }

        }else{//新增一条人信息
          msgBox.size++;
          msgBox.data[key] = cont;
          returnMsg.cont = msgBox.data[key];
        }
        // console.log(returnMsg)
        return returnMsg;
      },
      ALIS_CV : function (msgBox,msg,state){
        var cont = JSON.parse(msg.messageContent);
        var returnMsg = {cont:null,state:state};//cont:返回内容，state: 0 删除 , 1 新增 ,2 更新

        var key = cont.key = ''+msg.bizId; //用户信息唯一key
        cont.messageUrl = msg.messageUrl;
        if(!state && !msgBox.data[key]){//如果是删除危急值，但是又不存在本条消息，则不处理，不更新消息数量(因为消息推送延迟造成)
          returnMsg.state = 2;
          return returnMsg;
        }
        if(msgBox.data[key]) {//存在此人的信息，合并到此人信息中  (包含删除逻辑)
          // console.log(cont)
          if(state) {//更新
            var $liT = $('#msgOne-' + key);
            if($liT.length){//删除已存在的那一行， 更新也是重新插入
              $liT.remove();
            }
            returnMsg.state = 2; //状态设置为 更新
            returnMsg.cont = msgBox.data[key] = cont;
          }else{//删除
            msgBox.size--;
            msgBox.size = msgBox.size>0?msgBox.size:0;
            delete msgBox.data[key];
            returnMsg.cont = $('#msgOne-' + key);//删除返回的是需要删除的 dom对象
          }
        }else{//新增
          msgBox.size++;
          msgBox.data[key] = cont;
          returnMsg.cont = msgBox.data[key];
        }
        return returnMsg;
      }
    },
    removeKind : function (){//检验是否需要移除 整个tab
      $('.ul-msgInfo').each(function (i,v){
        if($(v).find('li').length==0){
          $(v).remove();
          $('.s-msgTitle').eq(i).remove();
          $('.s-msgTitle').eq(0).click();
        }
      });
    },
    renderKind : function (v){//渲染 分类msg
      var me = this;
      var tData = v.data;
      var kind = {//推入title
        type : v.type,
        bizType : v.bizType,
        desc : v.desc,
        size : v.size,
        cur : (me.kinds.length == me.cur) ? true : false
      };
      me.kinds.push(kind);

      $d.title.append(template('tem-msgT',kind));
      $d.cont.append(template('tem-msgC',kind));

      me.titleClick(me.el);//点击title切换tab
      me.renderItemsByType(v.type,tData);//渲染 每一项 消息 到 cont里的ul中
    },
    renderItemsByType : function (type,itemData,isReplace){
      var me = this;
      var itemHtml = '';
      var itemLen = 0;
      // console.log(itemData);
      $.each(itemData, function (k,v) {//itemsData 可能为数组，可能为对象
        itemLen++;
        itemHtml += template('tem-msgTC-' + type, {data: [v]});
      });
      // console.log($('.ul-msgType-'+v.type),itemHtml)
      var $ul = $('.ul-msgType-' + type);
      $ul[isReplace?'html':'append'](itemHtml);

      var $item = $ul.find('.li-msg').slice(-itemLen);// $item 为插入$ul中的最后  itemLen 项
      if($item.eq(0).hasClass('li-t-3')){
        me.itemClickOpenPop($item);//弹窗显示
      }else{
        eyeIndex.sideNavE($item,'li-msg-now',function (tabTitle){
          if(tabTitle == '在院患者处理' || tabTitle== '住院医生站'){//诊断 打开之前先关闭现存页面
            eyeIndex.closeTab(tabTitle);
          }
        });//侧边导航点击链接事件
      }


      $item.bind('click',function () {
        $(this).addClass('li-msgAlready');
        me.msgBoxHide();
      });

    },
    itemClickOpenPop : function ($item){
      $item.click(function (){
        var $t = $(this);
        var url = $t.attr('rel');
        var title = $t.attr('stitle');
        layer.open({
          type:2,
          content : url,
          title : title,
          area : ['700px','460px']
        });
      });
    },
    receiveMsg : function (evMsg) {//接收消息处理
      var me = this;
      console.log(JSON.stringify(evMsg))
      // console.log(evMsg);
      if(evMsg.msg.content){
        // if(evMsg.msg.code!==200){//如果报错
        //   return;
        // }
        var cont = JSON.parse(evMsg.msg.content);
        // console.log(cont);
        var thisMsgData = me.mergeMsg(cont);//这次更新需要渲染的数据
        var thisCont = thisMsgData.cont;
        var state = thisMsgData.state;

        // if(cont.bizType){
        var msgBox = msgs[cont.bizType];
        //更新dom 开始
        var $title = $('.s-msgTitle-'+msgBox.type);
        if(state && thisCont){//新增 或 编辑
          if($title.length){//已渲染过的tab
            // $title.find('b').html(msgBox.size);//更新title上的数字
             me.renderItemsByType(msgBox.type,[thisCont]);//渲染单条数据消息 到 cont里的ul中
          }else{//初始化的时候不存在数据，没渲染过的tab，需新加入，直接重新初始化整个 单tab
            me.renderKind(msgBox);
          }
        }else{//删除
          // $title.find('b').html(msgBox.size);//更新title上的数字
          // console.log(thisCont)
          thisCont && thisCont.length && thisCont.remove();
          me.removeKind();//检验是否需要移除 整个tab
        };

        me.setBadgeState('state',state);//更新badge
        $('.s-msgTitle-'+msgBox.type).find('b').html(msgBox.size);//更新title上的数字
        $('.s-msgTitle-'+msgBox.type).click();//切换到 新增消息 的tab窗口
        // }

        me.alwaysFlashMsg(cont.bizType);//是否 有危急值，有则一直闪动
      }

    },
    send : function (msg) {

    },
    titleClick : function (){//title 点击事件
      var me = this;
      var $el = me.el;
      var $t = $el.find('.s-msgTitle') , $ul = $el.find('.ul-msgInfo');
      $t.unbind('click').bind('click', function () {//切换消息
        var ix = $t.index(this);
        me.cur = ix;//设置当前 ix
        $t.removeClass('s-msgTitle-now');
        $(this).addClass('s-msgTitle-now');
        $ul.removeClass('ul-msgInfo-now').eq(ix).addClass('ul-msgInfo-now');
      });
    },
    msgInlayout : function (){
      var me = this;
      $d.msgWrap.on('mouseenter',function () {//防抖动
        me.msgBoxAutoHide(false);
      }).on('mouseleave',function () {//离开msg区域
        me.msgBoxAutoHide(true);
      });

      $(document).on('click',function (e) {//关闭消息
        var $tar = $(e.target);
        var t = $tar.hasClass('.div-msgInfo') || $tar.hasClass('.layui-layer') || $tar.parents('.msgBox').length || $tar.parents('.layui-layer').length;
        if(!t){
          me.msgBoxHide();
        }
      });
    },
    setBadgeState : function(type,num){//设置badge状态和值
      var me = this;
      if(type=='state'){// state: 0 删除 , 1 新增 ,2 更新
         me.msgSize = num >0?(num==2?me.msgSize:(++me.msgSize)):(--me.msgSize);//num : 0（删除--） / 1 (新增 ++) / 2(更新 不变)，
         me.msgSize = me.msgSize>0?me.msgSize:0; //不可小于0
      }else{
        me.msgSize = num;
      }
      $d.badge.html(me.msgSize);

      setTimeout(function (){//新宏任务 执行
        if(me.msgSize>0){
          $d.badge.show();
          $d.dot.hide();
          me.flashMsg();//闪动
        }else{
          $d.badge.hide();
          $d.dot.show();
        }
      },200);

    },
    flashMsg : function (){//如果有消息，闪动消息
      var replay = this.flashTimes,n = 1;
      var alarmIn = setInterval(function (){
        $d.msgBox.addClass('alarm');
        setTimeout(function (){
          $d.msgBox.removeClass('alarm');
        },400);
        n++;
        if(n>replay){clearInterval(alarmIn)}
      },800);
    },
    alwaysFlashMsg : function (bizType){// (ALIS_CV 危急值) || ( AHIS_1中数据(urgent>0)) 时总闪动
      var me = this;
      var needFlash = 0;//需要总闪动
      var needShowAhis = 0;//直接显示
      for (k in msgs.ALIS_CV.data){//有危急值就要总闪动
        if(k){needFlash = 1}//如果有值
      }
      $.each(msgs.AHIS_1.data,function (i,v){
        if(v.urgent && v.urgent>0){// AHIS_1中数据 urgent>0 时需要总闪动，并且直接展开消息面板
          // console.log(v)
          needFlash = 1;
          needShowAhis++;
        }
      });

      if(needShowAhis){//AHIS_1 不仅要总闪动，还要直接展开消息面板
        if(!bizType || bizType =='AHIS_1'){//如果 是新增消息，并且新增消息类型为 医嘱
          me.msgBoxShow(true);
        }
      }

      if(needFlash>0){
        if(!this.alwaysFlash){//如果没有闪动
          this.alwaysFlash= setInterval(function (){
            $d.msgBox.addClass('alwaysAlarm');
            setTimeout(function (){
              $d.msgBox.removeClass('alwaysAlarm');
            },400);
          },1000);
        }
      }else{
        clearInterval(this.alwaysFlash);
        this.alwaysFlash = null;
      }
    },
    msgBoxShow : function (hasMsg){//消息盒子 下拉显示
      $d.msgWrap[hasMsg?'removeClass':'addClass']('msgShowNoMsg');
      $d.msgWrap.addClass('msgShow');
    },
    msgBoxHide : function (){//消息盒子 下拉隐藏
      $d.msgWrap.removeClass('msgShow');
      clearTimeout(this.autoHide.o);
    },
    msgBoxAutoHide : function (hide){//无反馈，autoHide.time毫秒后自动关闭msgbox
      var me = this;
      if(hide){
        me.autoHide.o = setTimeout(function () {
          me.msgBoxHide();
        },me.autoHide.time);
      }else{
        clearTimeout(this.autoHide.o);
      }
    }
  }


if(isPushMode){
  window.aierMsg =new IMsg({
    el : '.msgBox'
  });
  // console.log('================',aierMsg,msgs)
}




})();



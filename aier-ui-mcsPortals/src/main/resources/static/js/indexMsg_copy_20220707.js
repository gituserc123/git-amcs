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
    },
    AVIS_SALES_MESSAGE : {
      type : 4,
      bizType : "AVIS_SALES_MESSAGE",
      desc : '视光',
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

  function debounce(func, wait) {//防抖函数
    let timer;
    return function() {
      let context = this; // 注意 this 指向
      let args = arguments; // arguments中存着e

      if (timer) clearTimeout(timer);

      timer = setTimeout(() => {
        func.apply(this, args)
      }, wait)
    }
  }

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

    this.init();

  };

  IMsg.prototype = {
    init : function (){
      var me = this;

      me.getDom(); //初始化dom对象
      me.initData(function (){
        if(me.msgSize>0){
          me.flashMsg();//闪动
        }
        me.alwaysFlashMsg();//是否 有危急值，有则一直闪动
      });//初始化数据

      me.getAhisMsg();

      me.msgInlayout();//消息在布局中的显示和隐藏处理
      me.goMsgInit();//消息连接初始化
    },
    getAhisMsg: function (){
      console.log('getAhisMsg');
      $.ajax({
        url : base + '/ui/sys/sysMessage/getInpOrderMsgPush',
        type : 'post',
        success : function (rst){
          console.log(rst);
        }
      })
    },
    reset : function (){
      msgs.AHIS_1.size = 0;
      msgs.AHIS_1.data = [];

      msgs.AEMR_Admission.size = 0;
      msgs.AEMR_Admission.data = {};

      msgs.ALIS_CV.size = 0;
      msgs.ALIS_CV.data = {};

      msgs.AVIS_SALES_MESSAGE.size = 0;
      msgs.AVIS_SALES_MESSAGE.data = {};

      this.cur = 0;
      this.msgSize = 0;
      this.kinds = [];
      // this.el.find('.userMsgBox').html('');
      this.el.find('.userMsgBox').html(template('tem-msgHtml',{}));
      //清除总闪动
      clearInterval(this.alwaysFlash);
      this.alwaysFlash = null;
    },
    getDom : function(){
      this.reset();
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
    initData : function (callback){//消息 初始化显示
      var me = this;
      $.ajax({
        url : base+'/ui/sys/sysMessage/getMessagePush',
        type: "post",
        success : function (rst) {
          // var rst = temData;
          // console.log(rst);

          // $d.msgBox.show();//显示消息
          $d.msgBox.on('click',function () {
            if(me.msgSize>0){
              me.msgBoxShow(true);
              me.reDomAemr();//点击铃铛时，重新渲染aemr病历部分
            }else{
              me.msgBoxShow(false);
            }
          });

          var reduceTotalNum = 0;
          $.each(rst.data,function (i,v){//处理消息，合并到 msgs 消息集中
            // console.log(JSON.parse(v.messageContent));
            var thisMsg = me.mergeMsg(v);
            if(!thisMsg.cont){
              reduceTotalNum++;
            }
            // console.log(thisMsg);
          });

          me.setBadgeState(rst.data.length - reduceTotalNum);//更新badge

          // console.log(msgs);
          $.each(msgs,function (k,v){// 根据消息集 初始化渲染 tab内容
            if(v.size>0){
              me.renderKind(v);
            }
          });
          callback && callback();
        }
      });
    },
    goMsgInit : function (){
      var me = this;
      if(goeasyKey == ''){
    	  goeasyKey = 'BS-fce4fc90cc3b4337b713b942d2549d62';
      }

      GEcontrol.init(goeasyKey,pushChannel,debounce(function (evMsg){
          me.receiveMsg(evMsg);
      },600));
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
      if(me.actionType[msg.bizType]){//存在此类型
        return me.actionType[msg.bizType](msgBox,msg,state,me);
      }else{
        return {cont:'此消息无对应类型',state:0};
      }

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
        console.log(msg)
        msg.messageContent = msg.messageContent || '{}';
        var cont = JSON.parse(msg.messageContent);
        var returnMsg = {cont:null,state:state};//cont:返回内容，state: 0 删除 , 1 新增 ,2 更新
        cont.bizId = '' + msg.bizId;
        cont.messageUrl = msg.messageUrl;
        cont.messageTitle = msg.messageTitle;

        var totalS = (cont.state2 || 0) + (cont.state3 || 0) + (cont.state5 || 0) + (cont.state6 || 0) ;
        //totalS>0 为需要处理的新增或更新数据
        if(state){//新增
            if(totalS>0 && cont.currentWardName == userNowDept){//状态 总和大于0 新增 ，等于0忽略 ,并且是当前科室
              msgBox.size++;
              msgBox.data.push(cont);
            }else{//忽略
              cont = null;
              returnMsg.state = 0;
            }

          // console.log('userNowDept:',userNowDept,',cont:',cont);
          returnMsg.cont = cont;
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
        if(msgBox.data[key]){//存在此人的信息，合并到此人信息中
          var contents = msgBox.data[key].eventContents;

          if(state){//新增 或 更新
            msgBox.size++;
            contents.push(cont.eventContents[0]);
            returnMsg.cont = msgBox.data[key];
          }

        }else{//新增一条人信息
          msgBox.size++;
          msgBox.data[key] = cont;
          returnMsg.cont = msgBox.data[key];
        }
        return returnMsg;
      },
      ALIS_CV : function (msgBox,msg,state){
        var cont = JSON.parse(msg.messageContent);
        var returnMsg = {cont:null,state:state};//cont:返回内容，state: 0 删除 , 1 新增 ,2 更新

        var key = cont.key = ''+msg.bizId; //用户信息唯一key
        cont.messageUrl = msg.messageUrl;

        //新增
        msgBox.size++;
        msgBox.data[key] = cont;
        returnMsg.cont = msgBox.data[key];

        return returnMsg;
      },
      AVIS_SALES_MESSAGE : function (msgBox,msg,state){
        var cont = JSON.parse(msg.messageContent);
        var returnMsg = {cont:null,state:state};//cont:返回内容，state: 0 删除 , 1 新增 ,2 更新

        var key = msg.id; //用户信息唯一key
        cont.messageUrl = msg.messageUrl;
        cont.messageTitle = msg.messageTitle;

        //新增
        msgBox.size++;
        msgBox.data[key] = cont;
        returnMsg.cont = msgBox.data[key];

        return returnMsg;

      }
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
      if(evMsg.msg.content) {
        var cont = JSON.parse(evMsg.msg.content);
        // console.log(cont.state);
        me.getDom(); //初始化dom对象
        me.initData(function (){//重新渲染消息
          var msgBox = msgs[cont.bizType];
          if(cont.state==0){//新增消息闪动
            me.flashMsg();
          }
          me.alwaysFlashMsg(cont.bizType);//判断时候总闪动
          $('.s-msgTitle-'+msgBox.type).click();//切换到 新增消息 的tab窗口
        });//初始化数据
      }
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
    msgInlayout : function (){//在布局中点击响应事件
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
    setBadgeState : function(num){//设置badge状态和值
      var me = this;
      me.msgSize = num;
      $d.badge.html(me.msgSize);

      // console.log('setBadgeState');

      setTimeout(function (){//新宏任务 执行
        if(me.msgSize>0){
          $d.badge.show();
          $d.dot.hide();
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



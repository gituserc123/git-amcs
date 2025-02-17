/* 以下几个方法为 easyui form组件获取值方法 */
$.fn.extend({
  /**
   * @description: 聚焦输入框通用方法
   */
  easyFocus : function(){
    var _self = $($(this)[0]);//只 foucs 单个dom，如果有多个指向第一个
    var $txt = _self;
    if(_self.hasClass('textbox-f')){
        $txt = _self.next().find('.textbox-text');
    }
    $txt.focus();
  },
  /**
   * @description: 输入框通用验证方法
   */
  easyValid : function (){
    var valid = true;
    $(this).each(function (i,v){
      var _self = $(v);
      if(_self.hasClass('textbox-f')){
        if(!_self.textbox('isValid')){
          _self.next().find('.textbox-text').focus();
          valid = false;
          return false;
        }
      }
    });
    return valid;
  },
  /**
   * @description: 获取输入框文本，普通文本框text 和value一致，combo text为输入框值，value为真实返回值
   */
  getText: function () {
    var _self = $(this);
    var cls = _self.attr('class');
    // console.log(cls)
    if (cls.indexOf('textbox-f') > -1) {
      return _self.textbox('getText');
    }
    return _self.val();
  },
  /**
   * @description: 获取输入框值，普通文本框text 和value一致，combo text为输入框值，value为真实返回值
   * @param {boolean} returnArr 为true，combo组件以数组形式返回值
   */
  getVal: function (returnArr) {
    //1. 获取输入框值，普通文本框text 和value一致，combo text为输入框值，value为真实返回值
    //2. returnArr 为 true，combo组件以数组形式返回值
    var _self = $(this);
    var cls = _self.attr('class');
    if (cls.indexOf('combo-f') > -1) {
      var vs = _self.combo('getValues');
      return returnArr ? vs : vs.join(',');
    } else if (cls.indexOf('textbox-f') > -1) {
      return _self.textbox('getValue');
    }
    return _self.val();
  },
    /**
   * @description: 为数字、字符串、对象为单选赋值，数组 为多选赋值，主要用在combo组件中
   * @param {number | string | object | Array} v 值
   */
  setVal: function (v) {
    var _self = $(this);
    var cls = _self.attr('class');
    var setVFn = v.constructor === Array ? 'setValues' : 'setValue';//数组用setValues,字符串或对象用 setValue，
    if (cls.indexOf('combogrid-f') > -1) {//combogrid
      _self.combogrid(setVFn, v);
    } else if (cls.indexOf('combobox-f') > -1) {//combobox
      _self.combobox(setVFn, v);
    } else if (cls.indexOf('combotree-f') > -1) {//combotree
      _self.combotree(setVFn, v);
    } else if (cls.indexOf('textbox-f') > -1) {// textbox / validatebox / numberbox /numberspinner
      _self.textbox('setValue', v);
    } else {//普通
      _self.val(v);
    }
    return _self;
  },
  /**
   * @description: 获取组件text和value值，返回： {text:,value:},多选值用逗号隔开，普通输入框返回输入框值
   * @param {boolean} returnArr 为true时，返回数组
   * @returns { {text: string; value: string} | string }
   */
  getTV: function (returnArr) {
    var _self = $(this);
    var cls = _self.attr('class');
    // console.log(cls)
    if (cls.indexOf('combo-f') > -1) {
      var vs = _self.combo('getValues');
      var ts = _self.combo('getText').split(',');
      // console.log(vs,ts);
      if (returnArr) {
        var tv = [];
        $.each(vs, function (i, v) {
          tv.push({
            text: ts[i],
            value: v
          })
        });
        return tv;
      }
      return {
        text: ts.join(','),
        value: vs.join(',')
      }
    } else if (cls.indexOf('textbox-f') > -1) {
      return _self.textbox('getValue');
    }
    return _self.val();
  },
  /**
   * @description: setVal 和 getVal综合方法，v存在为set,否则为get
   * @param { string | number | Array | null } v - 有值则设置，无值则获取
   * @returns {string | Array | null}
   */
  easyVal: function (v) {//setVal 和 getVal综合方法，v存在为set,否则为get
    var _self = $(this);
    if (v===undefined) {
      return _self.getVal();
    } else {
      return _self.setVal(v)
    }
  }
});

var $util = {
    /**
     * @description: 检测浏览器类型方法
     * @return {string} 返回浏览器类型名称
     */
    browserType : function (){
        var userAgent = navigator.userAgent,
            rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
            rFirefox = /(firefox)\/([\w.]+)/,
            rOpera = /(opera).+version\/([\w.]+)/,
            rChrome = /(chrome)\/([\w.]+)/,
            rSafari = /version\/([\w.]+).*(safari)/;
        var browser;
        var version;
        var ua = userAgent.toLowerCase();
        function uaMatch(ua){
            var match = rMsie.exec(ua);
            if(match != null){
                return { browser : "IE", version : match[2] || "0" };
            }
            var match = rFirefox.exec(ua);
            if (match != null) {
                return { browser : match[1] || "", version : match[2] || "0" };
            }
            var match = rOpera.exec(ua);
            if (match != null) {
                return { browser : match[1] || "", version : match[2] || "0" };
            }
            var match = rChrome.exec(ua);
            if (match != null) {
                return { browser : match[1] || "", version : match[2] || "0" };
            }
            var match = rSafari.exec(ua);
            if (match != null) {
                return { browser : match[2] || "", version : match[1] || "0" };
            }
            if (match != null) {
                return { browser : "", version : "0" };
            }
        }
        var browserMatch = uaMatch(userAgent.toLowerCase());
        return browserMatch;
    },
    /**
     * @description: 下载方法
     * @param {object} opt - 参数
     * @param {string} opt.url - 下载地址
     * @param {object} [opt.data = {}] - 下载附加数据
     * @param {'post' | 'get'} [opt.method = 'post'] - 提交数据方式
     * @param {string} [opt.exportIframe = 'export_frame'] - 内置默认导出iframe名称，非必要不需设置
     * @param {boolean} [opt.open = false] - 是否新窗口打开下载
     */
    down: function (opt) {//下载方法
        var o = $.extend({
          url : null,//下载请求url
          data : {},//下载传参
          method : 'post',//下载默认请求方式
          exportIframe: 'export_frame',//内置默认导出iframe名称
          open : false//是否新窗口打开下载，即不走目标iframe下载
        },opt||{});
        if(!o.url){return;}
        var inputs = [];
        var fTarget = o.open?'_blank':o.exportIframe;//下载目标对象
        if (o.data) {
            $.each(o.data, function (k, v) {
                inputs.push($T.format("<input type='hidden' name='#{name}' value='#{value}'>", {name: k, value: v}));
            });
        }
        if ($('#_exportBox').length) {$('#_exportBox').remove();}
          $('<div id="_exportBox" style="display:none;"></div>').append('<iframe id="'+o.exportIframe+'" name="'+o.exportIframe+'"></iframe>')
              .append('<form action="' + o.url + '" method="' + o.method + '" target="'+fTarget+'">' + inputs.join('') + '</form>')
              .appendTo('body');
        // } else {
        //     $("#_exportBox form").attr({
        //       url : o.url,
        //       method : o.method,
        //       target : fTarget
        //     }).html(inputs.join(''));
        // }
        $("#_exportBox form").submit();
    },
    excel: function (url, titles, fields, param) {//导出excel，需要后台对应配置
        param = param || {};
        $.applyIf(param, {
            _start: 0,
            _pagin: 1,
            _limit: 6000,
            _export_titles: titles.join(","),
            _export_fields: fields.join(",")
        });
        var frame = $("#export_frame");
        if (frame.length == 0) {
            frame = $("<iframe id='export_frame' class='hide' name='export_frame' style='display:none'></iframe>");
            $('body').append(frame);
        }
        // if (Ext.isIE) frame.src = Ext.SSL_SECURE_URL;
        var form = $("#export_form");
        if (form.length == 0) {
            form = $("<form method='post' id='export_form' target='export_frame' class='hide'></form>");
            $('body').append(form);
            if ($.browser.msie)
                document.frames["export_frame"].name = "export_frame";
        }
        form.attr("action", url);
        $.each(param, function (k, v) {
            form.append($T.format(
                "<input type='hidden' name='#{name}' value='#{value}'>", {
                    name: k,
                    value: v
                }));
        });
        form.submit().html("");
    },
    tabs: function (tab, events, cfg) {
        events = events || [];
        $(tab).tabs({
            onSelect: function (t, ix) {
                //console.log("init tab" + ix);
                $('.tabs', this).attr('class', 'tabs tab-state-' + ix);
                var init = $(this).attr("data-init" + ix);
                if (!init) {
                    var fn = events[ix];
                    if (fn && $.isFunction(fn))fn();
                    $(this).attr("data-init" + ix, true);
                }
            }
        });
    },
/*
合并列方法
grid,
data:数据,
aStr:值相同的字段,
bStr:需要合并的字段(不设置，则使用aStr)
 */
    gridMergeCols : function (grid,data,aStr,bStr) {
        if (data&&data.rows.length) {
        var bStr = bStr?bStr:aStr;
        var merges =[{index:0}];
        var ix = 0;
        var span = 0;
          var nowStr = data.rows[0][aStr];
          $.each(data.rows,function (i,v) {
            if (v[aStr]!= nowStr) {
                ix++;
                merges[ix] = {index:i};
                merges[ix-1].rowspan = span;
                span = 1;
                nowStr = v[aStr];
            }else{
              span++;
            };
          });
          merges[ix].rowspan = span;
          //window.console && console.log('merges 数组：',merges);

          for(var i=0; i<merges.length; i++){
                if(merges[i].rowspan>1){
                  if (bStr instanceof Array) {//如果有多个字段，为数组
                      $.each(bStr,function(j,v){
                        $(grid).datagrid('mergeCells',{
                          index: merges[i].index,
                          field: v,
                          rowspan: merges[i].rowspan
                        });
                      });
                  }else{//单个字段，字符串
                    $(grid).datagrid('mergeCells',{
                      index: merges[i].index,
                      field: bStr,
                      rowspan: merges[i].rowspan
                    });
                  };
                }
            }
        };
    }
};


var $pop= $pop ||{};
/**
 * @description: iframePop弹窗
 * @param {object} opt - 参数
 * @param {string | string[]} grid - 需刷新的grid Id
 * @param {string} [opt.title = '提示'] - 弹窗标题
 * @param {string} opt.content - 弹窗iframe的url
 * @param {object} opt.postData - 向子页面传递数据
 * @param {number} opt.skin - 主题
 * @param {number} [opt.zIndex = 19891014] - z轴
 * @param {number} [opt.shade = 0.3] - 遮罩透明度 0-1
 * @param {string[] | string | 'auto'} [opt.area = [ '100%', '100%']] - 弹层的宽高
 * @param {number} opt.maxWidth - 弹层的最大宽度。当 area 属性值为默认的 auto' 时有效。
 * @param {number} opt.maxHeight - 弹层的最大高度。当 area 属设置高度自适应时有效。
 * @param {(backParams: object | null) => void} opt.end - 弹窗关闭执行事件，参数为子页面返回数据
 * @param {(backParams: object | null) => void} opt.sureback - 弹窗关闭(成功)执行事件，参数为子页面返回数据
 * @param {(backParams: object | null) => void} opt.cancelback - 弹窗关闭(取消)执行事件，参数为子页面返回数据
 * @return {number<layer.open>}
 */
$pop.iframePop = function (opt,grid) {//pop的方式打开iframePop
      window._refreshParent = false;
      if (typeof(opt)=='string') {
          opt = {content:opt};
      };
      var layerOpt = $.extend({//layer
        type: 2,
        title :'提示',
        // content:url,
        area :['100%', '100%']
      },opt||{});


      if(opt.postData){//传递到iframe子页面复杂数据
          window.postDIndex = 'post'+Number(new Date());
          $store.set(postDIndex,opt.postData);
      }
      window.receiveIframeDContainer = {};//存放子页面传递过来的数据
      layerOpt.end = function (){
            // window.console&&console.log('layerOpt.end');
	        opt.end&&opt.end(window.receiveIframeDContainer);
	        if (window._refreshParent){//如果执行提交事件
            opt.sureback&&opt.sureback(window.receiveIframeDContainer);
	          if(grid){//有grid
              if(grid instanceof Array){
                $.each(grid,function (i,v) {
                  $grid.reload(v);
                })
              }else{
                $grid.reload(grid);
              }
            }
	        }else{
            opt.cancelback&&opt.cancelback(window.receiveIframeDContainer);
          }
      };
      // if(layerOpt.type==2){layerOpt.content = encodeURI(layerOpt.content);}
      return layer.open(layerOpt);
  };
  require(["template"],function(template){
  /**
   * @description: 模板表单弹窗
   * @param {object} opt - 参数
   * @param {string} [opt.title = '编辑'] - 弹窗标题
   * @param {string} opt.temId - 指向模板id
   * @param {object} [opt.temData = {}] - 渲染模板数据
   * @param {object} opt.data - 模板表单加载数据
   * @param {string | string[]} opt.grid - 表单提交后需要刷新的grid Id
   * @param {number} [opt.zIndex = 100000] - z轴
   * @param {string[] | string | 'auto'} [opt.area = ['500px','300px']] - 弹层的宽高
   * @param {string} opt.action - 表单提交请求地址，参数里配置优先级高于模板表单的action属性
   * @param {() => void} opt.beforePop - 弹窗前执行事件
   * @param {(layerO, index:number) => void} opt.onPop - 弹窗打开执行事件，时机同layer.open的success事件
   * @param {() => void} opt.end - 弹窗关闭事件，时机同layer.open的end事件
   * @param {(formOpt: object,$form: HTMLElement, formData: object) => void} opt.beforeSubmit - 表单提交前事件 (formOpt: 表单配置参数, $form: 表单节点, formData: 表单数据)
   * @param {(rst:object,formData:object) => void} opt.afterSubmit - 表单提交后事件 (rst: 提交异步返回数据, formData: 表单提交数据)
   * @return {number<layer.open>}
   */
    $pop.popTemForm = function (opt){
        var opt = $.extend({
        title : '编辑',//标题
        temId : '',//template id
        temData : {},
        data : null,
        zIndex : 100000,
        grid : null,
        btn : null,
        area : ['500px','300px'],
		    action : '',//pop form的action属性，即提交地址
        beforePop : function(){},
        onPop : function(){},
        end : function (){},
        beforeSubmit : null,//function (formData,$form){}
        afterSubmit : null//提交之后的事件, function (rst,$formBox) {}
        },opt||{});

            var rad = Math.floor(Math.random()*100000000000);
            var beforeSubmitPopFormName = 'beforeSubmitPopForm'+rad;
            var submitPopFormName = 'submitPopForm'+rad;
            opt.beforePop();
            var temPop =  $pop.open({
                title : opt.title,
                skin : opt.skin ||'popTemFormBox',
                content : template(opt.temId,opt.temData),
                zIndex:opt.zIndex,
                area : opt.area,
                btn : opt.btn,
                success : function ($p,index){
                        var $form = $p.find('.form-validate');
                        var alreadyD = $T.data($form);
						if(opt.action){$form.attr('action',opt.action);};
                        if($.isEmptyObject(alreadyD)){//如果form上没有绑定事件，则使用函数事件
                            $form.attr("data-opt","{'beforeCallback':'"+beforeSubmitPopFormName+"','callback':'"+submitPopFormName+"'}");
                        }
                        $page.contInit($p);
                        opt.data&&$form.form('load',opt.data);//有数据加载数据
                        $p.find('.btn-cancel,.btn-closeTemPop').click(function (){
                            layer.close(index);
                        });
                        opt.onPop($p,index);
                },
                end : function (){
                    opt.end();
                }
            });

            window[beforeSubmitPopFormName] =  function (formOpt,$form,data) {
                return opt.beforeSubmit?opt.beforeSubmit(formOpt,$form,data,temPop):true;
            };
            window[submitPopFormName] = function (rst,formData) {
                if (rst.code=='200'||rst.code=='201') {
                    layer.close(temPop);
                    if(opt.grid instanceof Array){
                        $.each(opt.grid,function (i,v) {
                            $grid.reload(v);
                        })
                    }else if(opt.grid){
                        $grid.reload(opt.grid);
                    }
                    opt.afterSubmit&&opt.afterSubmit(rst,formData);
                };
            }
            return temPop;
    };

    });
  $pop.popForm = function (opt) {//pop form, opt是所有参数
      var opt =$.extend({
          target : null,//需要弹出的对象class或者id
          zIndex : 100000,//这里设置主要为了改变组件重叠显示的冲突问题
          refreshGrid : true,//是否刷新grid
          grid : 'gridBox',//需要刷新grid的id
          width : 400,height:300,//pop宽高
          beforePop : function ($formBox) {},//弹窗之前的事情
          beforeSubmit : null,//function (formData,$form){}
          afterSubmit : null//提交之后的事件, function (rst,$formBox) {}
      },opt||{});
      var temPop;
      var $formBox = $(opt.target);
      // window.console && console.log($formBox.find('.form-validate').attr("data-opt"));
      var rad = Math.floor(Math.random()*100000000000);
      var beforeSubmitPopFormName = 'beforeSubmitPopForm'+rad;
      var submitPopFormName = 'submitPopForm'+rad;
      $formBox.find('.form-validate').attr("data-opt","{'beforeCallback':'"+beforeSubmitPopFormName+"','callback':'"+submitPopFormName+"'}");
      opt.beforePop($formBox);
      temPop = layer.open($.extend({
          type:1,
          zIndex : opt.zIndex,
          area:[opt.width+'px',opt.height+'px'],
          content : $formBox,
          end: function () {
              // $formBox.clear();
          }
      },opt));
      $formBox.find('.btn-closePop').click(function () {
          layer.close(temPop);
      });

      window[beforeSubmitPopFormName] =  function (formData,$form) {
        return opt.beforeSubmit?opt.beforeSubmit(formData,$form,temPop):true;
      };
      window[submitPopFormName] = function (rst) {
          if (rst.code=='200'||rst.code=='201') {
              layer.close(temPop);
              if (opt.refreshGrid) {$grid.reload(opt.grid);};
              opt.afterSubmit&&opt.afterSubmit(rst,$formBox);
          };
      }

      return temPop;//返回layer的序列
  };
  var bpUri = window.portalUri?portalUri:'';
  $pop.reloginOpt = {
    title : null,
    type: 1,
    closeBtn : 0,
    zIndex:201000,
    content : '<div class="reloginPop"><form class="soform form-reloginPop form-enter pad-t10" method="post" action="/login"><input type="hidden" name="ajax" value="true"/><div class="pad-20 pad-b0"><p class="pad-l20 pad-b20 bold red">业务操作超时，请重新输入账号信息：</p><p class="pad-l20 pad-r20 pad-b10"><input type="text" class="txt txt-popLoginName" name="username" placeholder="请输入用户名" /></p><p class="pad-l20 pad-r20"><input type="password" class="txt txt-popLoginPassword" name="password" placeholder="请输入密码" /></p><p class="row-btn pad-t20"><button type="button" class="btn btn-primary btn-popLoinIn">确认登录</button><button type="button" class="btn btn-popLoginOut">注销退出</button></p></div></form></div>',
    area : ['350px','210px'],
    success : function (layero, index) {
      $('body').data('unValidEdit',true);
      $('.txt-popLoginName').focus();
      layero.find('.btn-popLoginOut').click(function () {
        window.location.href=bpUri+'/login.html';
      });

      var $form = layero.find('.form-reloginPop');
      layero.find('.txt-popLoginName').validatebox({
        required: true,
        missingMessage: '请输入用户名'
      });
      layero.find('.txt-popLoginPassword').validatebox({
        required: true,
        missingMessage: '请输入密码'
      }).bind('keyup',function (e) {
          if(e.keyCode==13){
            layero.find('.btn-popLoinIn').click();
          }
      });
      layero.find('.btn-popLoinIn').click(function () {
        //window.console && console.log($form);
        var passValidate = $form.form('validate');
        if(passValidate){
          var sData = $form.sovals();
          $ajax.post('/login',sData).done(function (rst) {
            if(rst.code==='200'||rst.code==='201'){
              $pop.msg.success('登录成功，欢迎回来~');
              $pop.close(index);
            }
          });
        }
      });
    },
    end : function () {
      $('body').data('unValidEdit',false);
    }
  }
  $pop.reLogin = function(){
    var me = this;
    if(!$('.reloginPop').length) {
      layer.open(me.reloginOpt);
    }
  };

  $pop.popGrid = function (opt,target) {//弹窗grid
      opt = opt || {};
      // if (!opt.url && !opt.code) {
      //     $pop.alert("请配置表格数据源参数url或者code");
      //     return;
      // }
      if (!opt.code && !opt.gridId) {
          $pop.alert("请配置表格数据参数gridId");
          return;
      }
      var data = opt || {};
      data.gridCfg = data.gridCfg ||{};
      var gridId = data.gridId || 'grid_' + data.code.replace(/[\^@]/g, '')
          , url = data.url
          , init = $('#' + gridId).length > 0;
          data.gridCfg.singleSelect = data.gridCfg.singleSelect  || !data.muti;
      var muti = !data.gridCfg.singleSelect;
          //window.console && console.log(muti);
      if (init && $('#pop_' + gridId).length == 0) $pop.alert("请另外指定gridId," + gridId + "已存在!");
      if (!init) {
          var searchName = data.searchName || 'searchValue';
          var searchLabel = data.searchLabel || '';
          var boxTpl = "<div id='pop_{gridId}' style='display:none'>"+
          "<form class='form-inline popGridHead pad10'>"+
          "<div class='form-group'><input type='text' class='txt' name='"+searchName+"' placeholder='"+searchLabel+"'><button type='button' class='btn btn-small btn-primary fnSearch'>查 询</button> </div>"+
          "<button type='button' class='btn btn-warning fnSure"+(muti?'':' none')+"'>确 定</button>"+
              // "<span><input type='button' class='btn btn-submit fnSearch' value='查 询' /></span>"+
              // "<input type='button' class='btn btn-submit fnSure' value='确 定' />"+
          "</form>"+
          "<div class='pad-l10 pad-r10 pad-b5'><div id='{gridId}'></div></div></div>";
          $('body').append($T.format(boxTpl, {gridId: gridId}));
      }

      var boxOpt = {
          type :1,
          title: muti?'选择后点击确定按钮':'请双击选择行',
        area : ['500px','494px'],
          content: $('#pop_' + gridId)
      };
      $.extend(true, boxOpt, data.boxOpt || {});
      if (boxOpt.width) {boxOpt.area[0] = boxOpt.width+'px'};
      if (boxOpt.height) {boxOpt.area[1] = boxOpt.height+'px'};
      //清除验证的tooltip
      // var $form = $(target).parents('.form-validate');
      // if ($form) {
      //     $form.find(".form-validate .txta,:input").tooltip("destroy");
      // };
      $pop[gridId] = layer.open(boxOpt);
      if (!init) {
          var valueId = data.valueId, textId = data.textId
          ,valueVal = data.valueVal||'id', textVal = data.textVal||'text'
              , gridCfg = {height: (boxOpt.height - 90), width: '100%'};
          $.extend(true, gridCfg, data.gridCfg || {});
          gridCfg.columns = gridCfg.columns || data.cols;
          if (!gridCfg.columns && data.code) {
              var cType = data.code.replace(/[\^@]/g, '');
              if (!$cols[cType]) {
                $pop.alert('请在param.js里面定义' + cType + '表格列信息!');
                  return;
              }
              gridCfg.columns = $cols[cType];
          }
          if (!gridCfg.columns) {
            $pop.alert("请配置表格列信息!");
              return;
          }
          gridCfg.pageSize = 10;
          gridCfg.fitColumns = (opt.fitCol?opt.fitCol:true);
          gridCfg.onDblClickRow = function (index, row) {
              //window.console && console.log(textId,valueId,row);
              if (valueId)$('#' + valueId).val(row[valueVal]);
              if (textId)$('#' + textId).val(row[textVal]);
              if (boxOpt.onOk)boxOpt.onOk([row]);
              layer.close($pop[gridId]);
              if(data.values){
                  $.each(data.values,function (key,val) {
                      $('#'+key).val(row[val]);
                  });
              }
              // $pop[gridId].removePop();
          }
          $grid.newGrid('#' + gridId, gridCfg);
          $('.fnSearch', '#pop_' + gridId).click(function () {
              var ps = $('#pop_' + gridId).find('.popGridHead').sovals();
              $grid.load('#' + gridId, ps);
          });
          if (muti) {
              $('.fnSure', '#pop_' + gridId).show().click(function () {
                  var rows = muti ? ($('#' + gridId).datagrid("getChecked") || []) : [$('#' + gridId).datagrid("getSelected")];
                  var id = [], text = [];
                  for (var i = 0; i < rows.length; i++) {
                      var row = rows[i];
                      id.push(row[valueVal]);
                      text.push(row[textVal]);
                  }
                  if (valueId)$('#' + valueId).val(id.join(','));
                  if (textId)$('#' + textId).val(text.join(','));
                  if (boxOpt.onOk)boxOpt.onOk(rows);
                  layer.close($pop[gridId]);
              });
          };

      }
      var params = data.gridParams || data.params || {};
      if (typeof(params) == "function") {
          params = params();
      }
      var urlParams = data.urlParams || '';
      if (typeof(urlParams) == "function") {
          urlParams = urlParams();
      }
      // urlParams = $T.parseParam(urlParams);
      if(url){params.$url = url;}
      if (urlParams) {params.$url = params.$url+urlParams};
      $grid.load('#' + gridId, params);
  };

var $grid = {
    /**
     * @description: 获取grid rows数据
     * @param {string} grid - grid Id
     * @return {object[]}
     */
    getRows: function (grid) {//获取rows
        return $(grid).datagrid("getData").rows;
    },
    getRowsByCols : function(grid) {//根据grid的columns来获取rows
      var rows = $(grid).datagrid('getRows');
      var gridCols = $(grid).datagrid('options').columns||[];
      var gridFCols = $(grid).datagrid('options').frozenColumns||[];
      var aCols =[];
      $.each(gridFCols,function (i,v) {
        aCols = aCols.concat(v);
      });
      $.each(gridCols,function (i,v) {
        aCols = aCols.concat(v);
      });
      var rRows = [];
      $.each(rows,function (i,v) {
        var row = {};
        $.each(aCols,function (j,k) {
          var field = k.field;
          if(v.hasOwnProperty(field)){
            row[k.field] = v[k.field];
          }
        });
        rRows.push(row);
      });
      return rRows;
    },
    /**
     * @description: 加载grid数据，可更新 datagrid 和 treegrid
     * @param {string} grid - grid Id
     * @param {object} param - grid 请求参数
     * @param {boolean} [mixParams = false] - 是否带上 grid 配置的 queryParams 参数
     */
    load: function (grid, param,mixParams) {//grid 更新参数后load，返回第一页
        param = param || {};
        var ui = $(grid).attr("data-ui");
        var gridT =  ui == 'datagrid'?'datagrid':'treegrid';
        if (param.$url) $(grid)[gridT]("options").url = param.$url;
        if(mixParams){
          param = $.extend(true,$(grid)[gridT]("options").queryParams,param);
        }
        $(grid)[gridT]("load", param);
    },
    /**
     * @description: 更新grid数据，可更新 datagrid、treegrid、tree
     * @param {string} grid - grid Id
     * @param {object} param - grid 请求参数
     */
    reload: function (grid, param) {//grid 更新参数后reload，保留在刷新前的页码
        var $grid = $(grid);
        var ui = $grid.attr("data-ui");
        if (ui == 'datagrid') {
            $grid.datagrid("reload", param);
        } else {
            if($grid.hasClass('tree')){
                $grid.tree("options").queryParams = param;
                $grid.tree("reload");
            }else{
                $(grid).treegrid("reload", param);
            };
        }
    },
    /**
     * @description: 清空 datagrid 数据
     * @param {string} grid - grid Id
     */
    clear: function (grid) {//grid 清空数据
        $(grid).datagrid("loadData", []);
    },
    /**
     * @description: 删除选择行
     * @param {string} grid - grid Id
     */
    deleteSelected: function (grid) {//grid 删除选择行
        //指定idField
        var rows = $(grid).datagrid("getSelections");
        for (var i = 0; i < rows.length; i++) {
            var inx = $(grid).datagrid("getRowIndex", rows[i]);
            $(grid).datagrid("deleteRow", inx);
        }
        $(grid).datagrid("clearSelections");
    },

    /**
     * @typedef {Object} DatagridColumns
     * @property {string} title - 列标题
     * @property {string} field - 列数据字段,可不存在，但不可重复
     * @property {number} width - 列宽
     * @property {number} rowspan - 合并行单元格数量
     * @property {number} colspan - 合并列行数
     * @property {'left' | 'right' | 'center'} align - 内容对齐
     * @property {'left' | 'right' | 'center'} halign - 标题对齐，无设置则以align为准
     * @property {boolean} checkbox - 如果为true，则显示复选框。该复选框列固定宽度
     * @property {boolean} hidden - 如果为true，则隐藏列
     * @property {boolean} sortable - 如果为true，则允许列使用排序
     * @property {'asc' | 'desc'} order - 默认排序数序,只能是'asc'或'desc'
     * @property {'yyyy-MM-dd' | 'yyyy-MM-dd HH:mm:ss' | 'yyyy-MM-dd HH:mm'} format - 日期格式化，如： 'yyyy-MM-dd'
     * @property {boolean} price - 是否显示为金额
     * @property {boolean} fixed - 如果为true，在"fitColumns"设置为true的时候阻止其自适应宽度
     * @property {boolean} [resizable = true] - 如果为true，允许列改变大小
     * @property {(value: any,row: object[],index: number)=>string} formatter - 单元格格式化
     * @property {(value: any,row: object[],index: number)=>string} styler - 定义单元格样式，返回样式style
     * @property {(a: any, b: any)=>string} sorter - 用来做本地排序的自定义字段排序函数，a:第一个字段值，b:第二个字段值
     * @property {{type:'text' | 'textarea' | 'checkbox' | 'numberbox' | 'validatebox' | 'datebox' | 'combobox' | 'combotree' | 'my97' | 'diy' | 'colorPicker' | 'readonly',options: object }} editor - 指明编辑类型
     *
     */

    /**
     * @typedef {Object} DatagridTools
     * @property {string | ($par: HTMLElement, $grid:HTMLElement, opt: object) => string} html - 自定义工具html，结合render使用
     * @property {($par: HTMLElement,$grid:HTMLElement,opt: object) =>void} render - 渲染html属性生成的html代码
     * @property {string} iconCls - icon的class,在图标集中查找，去除前缀 'icon-'
     * @property {string} btnTitle - 按钮标题
     * @property {string} text - 按钮文本
     * @property {string} url - 按钮点击显示的iframe请求地址，如 ：userForm.html?id={id}{}里为行返回的field值，如选择的是多行，返回的field为,隔开的字符串(ajax:true ajax模式，newWin:true 弹窗模式)
     * @property {boolean | string} [notNull = false] - 为true时，grid必须选择行，为提示文字(string)时，没有选择行弹出提示文字
     * @property {boolean} [check = false] - 是否返回是check的值，即勾选行，默认返回select的值，即选择行
     * @property {boolean} [onlyOne = false] - 为true时，grid操作行只能选择一行
     * @property {boolean} [ajax = false] - 为true时，url对应的为ajax请求(ajax模式)
     * @property {string} [ajaxMsg = '你确定提交此操作吗？'] - ajax请求是的提示文字(ajax:true ajax模式)
     * @property {boolean} [post = false] - 当ajax为true时，默认url请求传递参数的方法为：userForm.html?id={id}{}里为行返回的field值， 当post为true时，传参方式通过post值给出，返回，如：`url:'userForm.html',post:'userId=id&name=name'(ajax:trueajax模式)
     * @property {(rst: object)=>void} ajaxBack - 关闭时返回事件(ajax:true ajax模式)
     * @property {boolean} [newWin = false] - 为true时，url对应的页面在新窗口打开(弹窗模式)
     * @property {boolean} [popMax = false] - iframe弹窗模是否最大化(newWin:true 弹窗模式)
     * @property {string} [title = '信息窗口'] - iframe弹窗标题(newWin:true 弹窗模式)
     * @property {number} [popWidth = 560] - iframe弹窗宽(newWin:true 弹窗模式)
     * @property {number} [popHeight = 300] - iframe弹窗高(newWin:true 弹窗模式)
     * @property {function} endBack - 关闭时返回事件(newWin:true 弹窗模式)
     * @property {()=>void} click - 自定义click事件，之前设置的ajax模式和newWin模式参数皆为浮云
     */
     /**
     * @typedef {Object} DatagridOpt
     * @property {boolean} [fitColumns = true] - 列是否自动调整撑满grid宽度
     * @property {boolean} [singleSelect = true] - 是否单选
     * @property {boolean} [ctrlSelect = false] - 在启用多行选择的时候允许使用Ctrl键+鼠标点击的方式进行多选操作
     * @property {boolean} [checkOnSelect = false] - 如果为true，当用户点击行的时候该复选框就会被选中或取消选中
     * @property {boolean} [selectOnCheck = true] - 如果为true，当用户点击行的时候该复选框就会被选中或取消选中
     * @property {boolean} [pagination = true] - 是否有分页
     * @property {'top' | 'bottom' | 'both'} [pagePosition = 'bottom'] - 分页工具栏的位置
     * @property {DatagridColumns[][]} frozenColumns - 冻结列配置
     * @property {DatagridColumns[][]} columns - 列配置
     * @property {'post' | 'get'} [method ='post'] - 请求远程数据方法类型
     * @property {boolean} [nowrap = true] - 是否不换行
     * @property {string} idField - 指明哪一个字段是标识字段
     * @property {string} url - 数据请求地址
     * @property {{total: number, rows: object[]} | object[]} data - 表格数据
     * @property {number} [pageSize = 20] - 一页显示记录条数(有分页生效)
     * @property {boolean} [onceTotal = false] - 是否只第一次请求记录总数
     * @property {number[]} [pageList = [10, 20, 50, 100, 200, 500]] - 分页数量数组
     * @property {object} queryParams - 请求参数
     * @property {string} sortName - 定义哪些列可以进行排序
     * @property {'asc' | 'desc'} sortOrder - 定义列的排序顺序，只能是'asc'或'desc'
     * @property {boolean} [multiSort = false] - 定义是否允许多列排序
     * @property {boolean} [remoteSort = true] - 定义从服务器对数据进行排序
     * @property {boolean} [showHeader = true] - 定义是否显示行头
     * @property {boolean} [showFooter = false] - 定义是否显示行脚
     * @property {number} [scrollbarSize = 18] - 滚动条的宽度、高度
     * @property {boolean} [rownumbers = true] - 行编号
     * @property {number} [rownumberWidth = 30] - 行号列宽度
     * @property {number} [editorHeight = 24] - 编辑器默认高度
     * @property {string} [loadMsg = '拼命加载中，请稍候...'] - 加载中提示
     * @property {number} height - 表格高度
     * @property {(index: number,row: object[])=>string} rowStyler - 返回行样式style
     * @property {(data: {rows: object[],total:number})=>object | object[]} loadFilter - 返回过滤数据显示。该函数带一个参数'data'用来指向源数据（即：获取的数据源，比如Json对象）。您可以改变源数据的标准数据格式。这个函数必须返回包含'total'和'rows'属性的标准数据对象
     * @property {(param: object,success: (data: object | object[])=>void,error: ()=>void)=>string} loader - 自定义如何从远程服务器加载数据
     * @property {DatagridTools[] | DatagridTools[][]} tools - 工具栏，二维数组为分组工具栏
     * @property {boolean} [fitHeight = false] - 自动适应窗口高度，height有值时，自动为 false
     * @property {number} [offset = 0] - fitHeight = true时，自适应高度偏差
     *
     * @property {(data:{rows: object[],total:number}) => void } onLoadSuccess - 在数据加载成功的时候触发
     * @property {(param:object) => boolean | void } onBeforeLoad - 在载入请求数据数据之前触发，如果返回false可终止载入数据操作
     * @property {(index: number, row:object) => void } onClickRow - 在用户点击一行的时候触发
     * @property {(index: number, row:object) => void } onSelect - 在用户选择一行的时候触发
     * @property {(index: number, row:object) => void } onDblClickRow - 在用户双击一行的时候触发
     * @property {(index: number, row:object) => void } onBeforeCheck - 在用户校验一行之前触发，返回false则取消该动作
     * @property {(index: number, row:object) => void } onCheck - 在用户勾选一行的时候触发
     * @property {(index: number, row:object) => void } onBeforeUncheck - 在用户取消勾选一行的时候触发
     * @property {(index: number, row:object) => void } onUncheck - 在用户取消勾选一行的时候触发
     * @property {(index: number, row:object) => void } onBeforeEdit - 在用户开始编辑一行的时候触发
     * @property {(index: number, row:object) => void } onBeginEdit - 在一行进入编辑模式的时候触发
     * @property {(index: number, row:object) => void } onCancelEdit - 在用户取消编辑一行的时候触发
     * @property {(index: number, row:object, changes: object) => void } onEndEdit - 在完成编辑但编辑器还没有销毁之前触发,changes：更改的字段(键)/值对
     * @property {(index: number, row:object, changes: object) => void } onAfterEdit - 在用户完成编辑一行的时候触发,changes：更改后的字段(键)/值对
     * @property {(rows:object[]) => void } onSelectAll - 在用户选择所有行的时候触发
     * @property {(rows:object[]) => void } onUnselectAll - 在用户取消选择所有行的时候触发
     * @property {(rows:object[]) => void } onCheckAll - 在用户勾选所有行的时候触发
     * @property {(rows:object[]) => void } onUncheckAll - 在用户取消勾选所有行的时候触发
     * @property {(index: number, row:object) => boolean | void } onBeforeSelect - 在用户选择一行之前触发，返回false则取消该动作
     * @property {(index: number, row:object) => boolean | void } onBeforeUnselect - 在用户取消选择一行之前触发，返回false则取消该动作
     * @property {(index: number, field: string, value: any) => void } onClickCell - 在用户双击一行的时候触发
     * @property {(index: number, field: string, value: any) => void } onDblClickCell - 在用户双击一个单元格的时候触发
     * @property {(sort: string, order: 'asc' | 'desc') => boolean | void } onBeforeSortColumn - 在用户排序一个列之前触发，返回false可以取消排序
     * @property {(e:Event, field:string) => void } onHeaderContextMenu - 在鼠标右击DataGrid表格头的时候触发
     * @property {(e:Event, index: number, row:object) => void } onRowContextMenu - 在鼠标右击一行记录的时候触发
     *
     */

    /**
     * @description: 初始化datagrid
     * @param {string} grid - grid Id
     * @param { DatagridOpt } cfg 配置参数
     */
    newGrid: function (grid, cfg) {//二次封装的grid方法，cfg参数相当于easyui的参数对象，具体方法请参考api手册
        if (!$(grid).length) {
            $pop.alert("页面不存在" + grid,null,{icon: 2, title:false,btnAlign: 'c'});
            return;
        }
        var $g = $(grid);
        var top = $g.position().top;
        var gridCfg = {
            fitColumns: true,//自动列款
            singleSelect: true,//单选
            pagination: true,
            pageSize: 20,
            onceTotal: false,
            // pageList: [10, 20, 50, 100, 200, 500],
            autoRowHeight: true,
            striped: true,//单双背景
            rownumbers: true,
            width: '100%',
            // auto : true,//自动刷新
            // excel : false,//导出excel
            loadMsg : '拼命加载中，请稍候...',
            fitHeight: true,//自动适应窗口高度
            // height: $(window).height()-top,
            offset : 0,
            onLoadError : function (err) {//加载错误事件
              // var rst = err.responseJSON;
              //window.console && console.log(err);
              // if(rst.timeout&&rst.timeout===301){
              //   $pop.reLogin();
              //   return;
              // }
              // var msg = rst.msg || '数据请求失败！';

              // $pop.alert(msg);
              // $pop.msg(msg);
            }
        };
        $.extend(true, gridCfg, cfg);
        if(!gridCfg.pageList){gridCfg.pageList = [10, 20, 50, 100, 200, 500];}
        if(typeof(gridCfg.height) == 'number'){gridCfg.fitHeight = false;}//有设置高度则不谁window.resize变化而变化
        if(!gridCfg.height){gridCfg.height=$(window).height()-top;}//没有设置高度，先设置高度为window 高度
        var titles = [], fields = [];
        var $gridP = null;
        if (gridCfg.fitParent) {
          $gridP = $g.parent();
          gridCfg.height = $gridP.height();
        };
        if (gridCfg.offset&&gridCfg.height!=='auto') gridCfg.height += gridCfg.offset;
        // gridCfg.height = gridCfg.height;
        var colLen = gridCfg.columns.length;
        for (var i = 0; i < colLen; i++) {
            var cols = gridCfg.columns[i];
            $.each(cols, function (inx, col) {
                if (col.checkbox)return;
                $.applyIf(col, {align: 'center'});
                if (col.format) {
                    col.formatter = function (v, r, inx) {
                        return v ? $.fmtDate(col.format, v) : '';
                    }
                }
                if (!col.width)col.width = 60;
                if (col.title && col.field) {
                    titles.push(col.title);
                    fields.push(col.field);
                }
                if (col.editor) {
                    col.styler = function (v, r, inx) {
                        return {'class': 'x-editor'};
                    }
                }
              // if (col.price) {
              //   col.align = 'right';
              //   col.formatter=function(r){
              //     if(r!==undefined){return Number(r).toFixed(2);}
              //   }
              // }
              //   if(col.titletip){
              //       col.formatter=function(r){
              //         if(r){return '<span class="nowrap" title="'+r+'">'+r+'</span>';}
              //
              //        }
              //   }
            });
        }
        //onceTotal
        if(gridCfg.onceTotal){
            gridCfg.queryParams = gridCfg.queryParams || {};
            gridCfg.queryParams.onceTotal = '1'; // 往服务端传递增加参数 onceTotal
            var loadParams = {};
            // onBeforeLoad
            var cfgOnBeforeLoad = gridCfg.onBeforeLoad;//缓存配置中的onBeforeLoad事件
            gridCfg.onBeforeLoad =  function (p1, p2){
                loadParams = gridCfg.treeField ? p2 : p1; //treegrid取p2, datagrid取p1
                return cfgOnBeforeLoad && cfgOnBeforeLoad.call(this, p1, p2);
            }
            // loadFilter
            var tempLoadFilter = gridCfg.loadFilter; //缓存配置中的loadFilter事件
            gridCfg.loadFilter = function(data, parent){
                if(data.total && loadParams.page === 1){ //存在就存储
                    $g.data('oncetotal', data.total);
                }

                if(!data.total && loadParams.page !== 1 && $g.data('oncetotal')){ //没有total且本地有值就赋值
                    data.total = $g.data('oncetotal');
                }
                return tempLoadFilter? tempLoadFilter.call(this, data, parent) : data;
            };
        }
        //console.log("初始化" + grid, gridCfg);
        if (gridCfg.toolbar)gridCfg.toolbar = $grid.initToolBar(grid, gridCfg.toolbar);
        if (gridCfg.tools){
            var toolsId = $grid.initTools(grid, gridCfg.tools);
            gridCfg.toolbar = '#'+toolsId;
        };
        if (gridCfg.treeField) {
            gridCfg.pagination = false;
            gridCfg.animate = false;
            $g.treegrid(gridCfg);
            $g.attr("data-ui", "treegrid");
        } else {
            $g.datagrid(gridCfg);
            $g.attr("data-ui", "datagrid");
        }

        var pager = $g.datagrid('getPager'), btns = [];
        if (cfg.excel) {
            btns.push({
                iconCls: 'icon-excel',
                handler: function () {
                    var ps = $g.datagrid("options").queryParams;
                    $util.excel(cfg.excel, titles, fields, ps);
                }
            });
        }

        if (cfg.auto) {
            var btnAutoId = $g.attr("id") + "_auto", auto = cfg.auto;
            auto = (auto === true) ? 60 * 1000 : auto * 1000;
            btns.push({
                id: btnAutoId,
                handler: function () {
                    var btn = $(this);
                    var taskId = btn.attr("data-task");
                    if (!taskId) {
                        taskId = setInterval(function () {
                            $grid.reload(grid);
                        }, auto);
                        btn.attr("data-task", taskId);
                        btn.addClass("icon-autofreshing");
                    } else {
                        clearInterval(taskId);
                        btn.removeAttr("data-task");
                        btn.removeClass("icon-autofreshing");
                    }
                }
            });
            $('#' + btnAutoId).addClass("icon-autofresh icon-autofreshing");
            $('#' + btnAutoId).click();
        }

        if (btns.length > 0) {
            pager.pagination({buttons: btns});
        }
        if (gridCfg.height !== 'auto' && gridCfg.fitHeight) {
          $(window).resize(function () {
              var wh = $(window).height();
              var gridTop = $g.parents('.datagrid').position().top;
              var gridH = wh - gridTop;
              if (gridCfg.fitParent) {
                gridH = $gridP.height();
              }
              // window.console && console.log(gridTop);
              $g.datagrid('resize',{height:(gridH+gridCfg.offset)});
          });
        };

    },
    renderTools : function (grid,btnArr,$par,singerMode) {
      var me = this;
      var $gridO = $(grid);
       $.each(btnArr, function (i, opt) {
         if(opt.html){
           var $html = typeof(opt.html)== 'function'?opt.html($par,$gridO,opt):opt.html;
           $par.append($html);
           opt.render && opt.render($par,$gridO,opt);
           return;
         }
          //iconCls:'icon-add',text:'新增',url:'form.html',noMax: true,popHeight:350,title:'用户信息-新增'
          var o= $.extend({
              iconCls :'plus',//默认按钮图标
              // btnCls : 'default',//默认按钮类型
              btnCls : '',//按钮自定义cls
              text : '新增',//按钮文本
              btnTitle : null,//按钮标题
              url : null,//请求地址
              popMax : false,//是否最大化
              popWidth : 560,//弹窗宽度
              popHeight : 300,//弹窗高度
              ajaxMsg : '你确定提交此操作吗？',
              title : '信息窗口',//默认弹窗标题
              check:false,//是否返回是check的值，即勾选行，默认返回select的值，即选择行
              notNull : false,//不能不选择行
              onlyOne : false,//只能选择一行
              newWin : false,//在新窗口打开
              ajax : false,//ajax事件
              post : false,//ajax改为 post参数方式
              endBack : function () {},
              ajaxBack : function (data) {},
              click : function () {}
          },opt||{});
          // var $btn = $('<span class="btn s-tool'+(singerMode?" s-tool-singer":"")+' btn-default"><b class="icon icon-'+o.iconCls+'"></b> '+o.text+'</span>');
          var $btn = $('<span class="s-tool '+o.btnCls+' '+(singerMode?" s-tool-singer":"")+'"'+(o.btnTitle?'title="'+o.btnTitle+'"':'')+'><b class="icon icon-'+o.iconCls+'"></b> '+o.text+'</span>');
          $btn.click(function () {
              var _self = $(this);
              var rows = $gridO.datagrid(o.check?"getChecked":"getSelections");
              if (o.notNull && rows.length == 0) {
                  if (o.notNull === true) o.notNull = "请选择记录!";
                  $pop.msg(o.notNull);
                  // $.sobox.warning(o.notNull);
                   _self.blur();
                  return;
              }
              if (o.onlyOne && rows.length != 1) {
                  if (o.onlyOne === true)o.onlyOne = "请选择需要操作的一条记录!";
                  $pop.msg(o.onlyOne);
                  // $.sobox.warning(o.onlyOne);
                   _self.blur();
                  return;
              }
              var url = o.url;
              if (url) {
                  if (typeof url == 'function') {
                      url = url();
                  };
                  if (o.post) {
                      if (o.post.constructor !== String) {o.post = 'id=id'};//默认取id
                      var map= [];
                      if (rows.length>0) {
                          var ps = [],keyArr = [];
                          ps = o.post.split('&');
                          for (var c = 0; c < ps.length; c++) {
                              keyArr.push(ps[c].split('='));
                              // map[keyArr[c][0]]=[];
                          }
                          for (var i = 0; i < rows.length; i++) {
                              var tt = rows[i];
                              for (var j = 0; j < ps.length; j++) {
                                  map.push(keyArr[j][0]+'='+tt[keyArr[j][1]]);
                              }
                          }
                          map = map.join('&');

                      };
                  }else{
                      var ps = [], re = /\{(\w+)\}/g, c, map = {};
                      while (c = re.exec(url)) {
                          ps.push(c[1]);
                          map[c[1]] = [];
                      }
                      if (ps.length > 0 && rows.length > 0) {
                          for (var i = 0; i < rows.length; i++) {
                              var tt = rows[i];
                              for (var j = 0; j < ps.length; j++) {
                                  map[ps[j]].push(tt[ps[j]]);
                              }
                          }
                          for (var k in map) {
                              map[k] = map[k].join(",");
                          }
                          url = $T.format(url, map);
                      }
                  };
                  // window.console && console.log(url);
                  // window.console && console.log(map);
                  if(o.newWin){
                      window.open(url);
                      _self.blur();
                      return;
                  }
                  if (o.ajax) {
                      var ajaxData = o.post?map:{};
                      $ajax.post(url, ajaxData, o.ajaxMsg).done(function (rst) {
                          o.ajaxBack(rst);
                          if (rst.code==='200'||rst.code==='201') {
                              $grid.reload(grid);
                          }
                      });
                      _self.blur();
                  } else {
                      window._refreshParent = false;
                      var areaVal = o.popMax?['100%', '100%']:[(o.popWidth+'px') || '560px',(o.popHeight+'px') || '300px'];
                      layer.open({//layer
                        type: 2,
                        title : o.title,
                        content:url,
                        area :areaVal,
                        end : function () {
                            if (window._refreshParent){
                              $grid.reload(grid);
                            }
                            o.endBack();
                        }
                      });
                      _self.blur();
                  }
              }else{
                  if (o.onlyOne) {rows = rows[0]};
                  if (o.click) {
                      o.click($gridO,rows);
                      _self.blur();
                      return;
                  };
              }
          });
          $par.append($btn);
       });
      // return $par;
    },
    initTools : function (grid,cfg) {//newGrid的分支方法，初始化工具栏
      var me = this;
      var randomId = 'tool-'+Math.ceil(Math.random()*100000000);
      var $wrap = $('<div id="'+randomId+'" class="baseToobar"></div>');
      var $gridO = $(grid);
      if (cfg[0] instanceof Array) {
          $.each(cfg,function (h,btnArr) {
              var $btnGroup = $('<div class="item-group'+(cfg.length>1&&(h!=cfg.length-1)?' toolGroup':'')+'"></div>');
              me.renderTools(grid,btnArr,$btnGroup);
              $wrap.append($btnGroup);
          });
      }else{
          me.renderTools(grid,cfg,$wrap,true);
      };
      var $none = $('<div class="none"></div>');
      $none.append($wrap);
      $('body').append($none);
      return randomId;
    },
    initToolBar: function (grid, cfg) {//newGrid的分支方法，初始化工具栏方式2
      $.each(cfg, function (i, opt) {
          if (opt == '-')return;
          if (!opt.handler) {
              opt.handler = function () {
                  var _self = $(this);
                  var rows = $(grid).datagrid(opt.check?"getChecked":"getSelections");
                  if (opt.notNull && rows.length == 0) {
                      if (opt.notNull === true) opt.notNull = "请选择记录!";
                      $pop.msg(opt.notNull);
                      // $.sobox.warning(opt.notNull);
                      _self.blur();
                      return;
                  }
                  if (opt.onlyOne && rows.length != 1) {
                      if (opt.onlyOne === true)opt.onlyOne = "请选择需要操作的一条记录!";
                      $pop.msg(opt.onlyOne);
                      _self.blur();
                      // $.sobox.warning(opt.onlyOne);
                      return;
                  }
                  var url = opt.url;
                  if (url) {
                      var ps = [], re = /\{(\w+)\}/g, c, map = {};
                      while (c = re.exec(url)) {
                          ps.push(c[1]);
                          map[c[1]] = [];
                      }
                      if (ps.length > 0 && rows.length > 0) {
                          for (var i = 0; i < rows.length; i++) {
                              var tt = rows[i];
                              for (var j = 0; j < ps.length; j++) {
                                  map[ps[j]].push(tt[ps[j]]);
                              }
                          }
                          for (var k in map) {
                              map[k] = map[k].join(",");
                          }
                          url = $T.format(url, map);
                      }
                      if(opt.newWin){
                          window.open(url);
                          _self.blur();
                          return;
                      }
                      if (opt.ajax) {
                          $ajax.post(url, {}, true).done(function (rst) {
                          if (rst.code==='200'||rst.code==='201') {
                                  $grid.reload(grid);
                              }
                          });
                          _self.blur();
                      } else {
                          window._refreshParent = false;
                          opt.popWidth = opt.popWidth || 560;
                          opt.popHeight = opt.popHeight || 300;
                          var areaVal = opt.popMax?['100%', '100%']:[(opt.popWidth+'px'),(opt.popHeight+'px')];
                          layer.open({//layer
                            type: 2,
                            title : opt.title,
                            content:url,
                            area :areaVal,
                            end : function () {
                                if (window._refreshParent)$grid.reload(grid);
                            }
                          });
                          _self.blur();
                      }
                  } else {
                      if (opt.onlyOne) {rows = rows[0]};
                      if (opt.click){
                          opt.click($(grid), rows);
                          _self.blur();
                      };
                  }
              }
          }
      });
      return cfg;
    }
};


var $form = {
    /**
     * 页面表格查询功能绑定，主要用在列表的搜索栏
     * @param {string} [btnCls = '.so-search'] - 需要绑定事件的按钮class
     */
    search: function (btnCls) {
        var cls = btnCls || '.so-search';
        if ($(cls).length) {
            $(cls).each(function () {
                var _self = $(this);
                var data = $T.data(this);
                var scope = data.scope;
                var aIsS = data.isString;
                if (scope != null ){
                    _self.click(function () {
                        if (!$(scope).form('validate')) {
                            return;
                        }
                        if (data.beforeSearch){
                            var state = window[data.beforeSearch]($(scope));
                            if(!state){return;}
                        }
                        var param = $(scope).sovals(aIsS?true:false), gridId = data.grid;
                        if (data.tab) {
                            var sli = $('li.tabs-selected', data.tab), inx = $('.tabs li', data.tab).index(sli);
                            gridId += (inx + 1);
                        }
                        $grid.load(gridId, param,data.mixParams);
                        return false;
                    });
                    //   .keyup(function(e){
                    //   var keycode = e.keyCode;
                    //   if(keycode === 13){
                    //     $(this).click();
                    //   }
                    // });
                }
            });
        }
    },
    /**
     * 表单清除按钮绑定
     * @param {string} [btnCls = '.so-clear'] - 需要绑定事件的按钮class
     */
    clear : function (btnCls){
        var cls = btnCls || '.so-clear';
        if ($(cls).length) {
            $(cls).click(function (){
                var $form = $(this).parents('form');
                if($form){
                    // window.console&&console.log($form.find(':input'));
                    $form.find(':input').not('.txt-unclear').val('');
                    // $form.form('clear');
                }
            });
        }
    },
    /**
     * 表单内元素全部只读,disabled
     * @param {string} form - 需要绑定事件的表单标识(id/class)
     */
    readonly : function (form){
        var $form = $(form);
        if($form.length){
            $form.find(':input').prop('disabled',true);
        }
    },
    /**
     * 输入框自动生成首拼码绑定
     * @param {string} [txtCls = '.so-pinyin'] - 需要绑定事件的按钮class
     */
    soGeitPinyin : function(txtCls){
      var cls = txtCls || '.so-pinyin';
      if($(cls).length){
        $(cls).each(function () {
          var _self = $(this);
          var data = $T.data(this);
          // window.console && console.log(data);
          var target  = data.target;
          if($(target).length){
            var $target = $(target);
            _self.blur(function () {
              var txt = $.trim($(this).val());
              var need = data.need ? data.need():true;
              if(!need){return;}
              if (txt.length) {
                $ajax.post(data.url, {input:txt}).done(function (rst) {
                  $target.val(rst.pinyin);
                });
              } else {
                $target.val(txt);
              }
            });
          }
        });
      }
    },
    soDrop : function(txtCls){
        var cls = txtCls || '.so-drop';
        if($(cls).length){
            $(cls).each(function () {
                var _self = $(this);
                var url = _self.attr('url');
                var required = _self.hasClass('required');
                _self.css({width:'100%'}).combobox({
                    url:url,
                    valueField:'value',
                    textField:'text',
                    onBeforeLoad : function (d) {
                        if (required) {
                            var $newTxt = _self.next('.combo').find('.textbox-text');
                            $newTxt.attr('placeholder','请选择...').addClass('required {required:true,messages:{required:"此项为必选"}}');
                        };
                    }
                });
            });
        }
    },
    soPop : function (txtCls){
        var cls = txtCls || '.so-pop';
        if($(cls).length){
            $(cls).each(function () {
                var _self = $(this);
                var rdm = Math.floor(Math.random()*1000000);
                var myOpt = $T.data(_self);

                if (myOpt.type=='tree') {
                    var pData = $.extend({
                        // type: null,//'tree'
                        url : null,//json url
                        valueId : null,
                        valuePid : null,
                        selectedId : null,
                        width:'400px',height:'300px',
                        title : '请双击选择',
                        value:'text',
                        justLeaf: false,
                        data : null,
                        flatData : true,
                        onDblClick : function (node) {}
                    },myOpt||{});

                    $('body').append('<div id="popTreeP-'+rdm+'" class="pad15 none"><ul id="ul-Tree-'+rdm+'"></ul></div>');
                    var alreadyRenderTree = false,treePop= null;
                    _self.click(function() {
                        treePop = layer.open({
                            type: 1,
                            content: $('#popTreeP-'+rdm),
                            area : [pData.width,pData.height],
                            title :pData.title,
                            btn:null
                        });

                        var treeOpt = {
                            animate : true,
                            lines : true,
                            url : pData.url,
                            data : pData.data,
                            flatData: pData.flatData,
                            onDblClick : function (node) {
                                //window.console && console.log(node);
                                if (pData.justLeaf&&node.children!=null) {return false;};
                                _self.val(node[pData.value]);
                                pData.selectedId = node.id;
                                if (pData.valueId) {$('#'+pData.valueId).val(node.id)};
                                if (pData.valuePid&&node.pid) {$('#'+pData.valuePid).val(node.pid)};
                                layer.close(treePop);
                                pData.onDblClick(node);
                            },
                            onLoadSuccess : function (node,data) {
                                pData.data = data;
                            }
                        }

                        if (!alreadyRenderTree) {
                            $('#ul-Tree-'+rdm).tree(treeOpt);
                            alreadyRenderTree = true;
                        }

                    });

                };

                if (myOpt.type =='grid') {//初始化popGrid
                    _self.click(function() {
                        myOpt.textId = myOpt.textId || this.name;
                        $pop.popGrid(myOpt,this);
                    });
                };

            });
        }
    },
    /**
     * @description:表单输入框回车事件支持，跳过 .txt-noEnter输入框
     * @param {function} callback - 绑定后事件
     * @param {string} formCls - form标识
     * @param {boolean} firstFocus - 是否默认foucs到第一个输入框
     */
    formAEnterFun : function(callback,formCls,firstFocus){//表单输入框回车事件支持
      setTimeout(function () {
          var $form = $(formCls?formCls:'.form-enter');
          $form.find('.textbox-text').each(function (){
            var _self = $(this);
            var $sourInput = _self.parents('.textbox').prev('.required');//查找源对象是否有class required
            if ($sourInput.length) {_self.addClass('required')};
          });

          $form.find(':text').unbind('blur.a').bind('blur.a',function () {
            $(this).val($.trim($(this).val()));
          });

          var $input = $form.find(':input:visible:enabled,.btn-easyFormSubmit').not('.txt-noEnter');
          $input.unbind('keydown.a').bind('keydown.a',function(e) {
              if (e.keyCode == 13) {
                  if ($(this).hasClass('btn-easyFormSubmit')) {return;};
                  var ix = $input.index(this);
                  // window.console && console.log(ix);
                  $input.eq(ix+1).focus();
                  return false;
              };
          });
          // $form.find('.textbox-text').focus(function () {//获取焦点时自动下拉
          //   var $prev = $(this).parents('.combo').prev();
          //   if ($prev.hasClass('easyui-combogrid')||$prev.hasClass('easyui-combobox')||$prev.hasClass('easyui-combotree')) {
          //     $prev.combo('showPanel');
          //   }
          // });

          firstFocus&&$input&&$input.eq(0).focus();//如果开启首输入自动获得焦点则执行
          callback&&callback();
          $('.tooltip').remove();
      },400);
    },
    /**
     * @description:表单输入框回车事件支持，部分特殊输入框的处理
     * @param {function} callback - 绑定后事件
     * @param {string} formCls - form标识
     */
    formAEnterFunB : function (callback,formCls) {//表单输入框回车事件支持，部分特殊输入框的处理

      setTimeout(function () {//重置输入框回车事件

          var $form = $(formCls?formCls:'.form-enter');

          $form.find('.textbox-text').each(function (){
            var _self = $(this);
            var $sourInput = _self.parents('.combo').prev('.required');
            if ($sourInput.length) {_self.addClass('required')};
          });

          var $input = $form.find(':input.required,.btn-easyFormSubmit').filter(':visible');
          //$("input:disabled")
          // window.console && console.log($input);
          $input.unbind('keydown.a').bind('keydown.a',function(e) {//required输入框进入获取下一焦点
              if (e.keyCode == 13) {
                  if ($(this).hasClass('btn-easyFormSubmit')) {return;};
                  var ix = $input.index(this);
                  // window.console && console.log(ix);
                  $input.eq(ix+1).focus();
                  return false;
              };
          });

          $form.find('.textbox-text').focus(function () {//获取焦点时自动下拉
            var $prev = $(this).parents('.combo').prev();
            if ($prev.hasClass('easyui-combogrid')||$prev.hasClass('easyui-combobox')||$prev.hasClass('easyui-combotree')) {
              $prev.combo('showPanel');
            }
          });
          $input&&$input.eq(0).focus();
          // window.console && console.log($input.eq(0));
          callback&&callback();
      },500);
    },
    /**
     * @description: 表单 reset
     * @param {string} formCls - 表单标识
     */
    reset : function (formCls) {
      $(formCls).form('reset');
    },
    /**
     * @description: 按钮绑定点击事件表单reset
     * @param {string} btnCls - 按钮标识
     */
    clickResetForm : function (btnCls) {
        $(btnCls).click(function () {
            var $form = $(this).parents('form');
            $form.form('reset');
        });
    },
    /**
     * @description: my97日期控件扩展
     * @param {string} cls - 输入框class
     * @param {object} opt - 参数
     * @param {'date' | 'time' | 'month'} opt.type - 日期框快捷类型，复杂的用format定义
     * @param {'yyyy-MM-dd' | 'yyyy-MM-dd HH:mm:ss' | 'yyyy-MM' | 'MM' | 'HH:mm:ss' | 'HH:mm'} opt.format - 日期框格式
     * @param {boolean} opt.autoFormatVal - 是否自动格式化初始值，默认为false
     * @param {boolean} [opt.inline = false] - 是否内联，控制宽度
     * @param {boolean} [opt.isShowClear = true] - 是否显示清除按钮
     * @param {string | function} [opt.minDate = '1920-01-01'] - 最小日期
     * @param {string | function} [opt.minDate = '2060-01-01'] - 最大日期
     * @return {*}
     */
    soDate : function(cls,opt){
      var dateFmtType = {
        date : 'yyyy-MM-dd',
        time : 'yyyy-MM-dd HH:mm:ss',
        month : 'yyyy-MM'
      }

      $(cls).addClass('Wdate').each(function () {
        var _self = $(this);
        _self.attr('autocomplete','off');
        var no = $.extend(true,opt||{},$T.data(_self)||{});
        var data = $.extend(true,{
          el : this,
          type : 'date',//类型，决定日期格式，优先级低于format，设置为time且内联 输入框宽度 160
          format : '',//格式
          autoFormatVal : false,//是否自动格式化初始值，默认为false
          minDate:'1920-01-01',//最小值
          maxDate:'2060-01-01',//最大值
          errDealMode : 1,//自动纠错
          // bindShow : false,
          inline : false//是否内联
        },no);
        data.onpicked = function () {
          if(_self.hasClass('validatebox-text')){_self.validatebox('validate');}
          no.onpicked&&no.onpicked(_self);
        };
        if (_self.hasClass('inline')|| data.inline) {
          var sw = 100;
          if(data.type=='time'){sw = 160;}
          _self.css('width', sw);
        };

        data.dateFmt = data.format || dateFmtType[data.type];
        if(data.maxDate instanceof Date){
          data.maxDate = $.fmtDate(data.dateFmt,data.maxDate);
        }
        if(data.minDate instanceof Date){
          data.minDate = $.fmtDate(data.dateFmt,data.minDate);
        }
        // if (_self.hasClass('required')) {};
        // window.console && console.log(data);
        // _self.unbind('click').bind('click',function () {
        //   WdatePicker(data);
        // });
        // data.bindShow && WdatePicker.call(this,data);
        var val = _self.val();
        if(val && data.autoFormatVal){_self.val($.fmtDate(data.dateFmt,val))}//初始值按指定类型格式化
        _self.unbind('focus.a').bind('focus.a',bindWadte);
        _self.unbind('click.a').bind('click.a',bindWadte);
        function bindWadte(){
          var unuse = $(this).attr('unuse');
          if(unuse!=='true'){
            WdatePicker(data);
          }
        }
      });
    },
    rangeWeek : function ($weeker,opt) {
        var me = this;
        var $weeker =$weeker?$($weeker) : $('.so-weekDate');
        require(['moment'],function (moment) {

            var defOpt = {
                  locale :{
                    separator: ' 至 ',
                    format : 'YYYY-MM-DD'
                  },
                  weekPacker : true,
                  prevNextBtn : false,
                  beginStr : 'Begin',
                  endStr : 'End',
                  readonly : true,
                  width: 180,
                  // showCustomRangeLabel : false,
                  // alwaysShowCalendars:true,
                  autoApply : true,//日期范围不用确认，自动选择
                  linkedCalendars: false,//两个日历不一起联动，保证可以选择两个月以上的日期
                  // showDropdowns : true,//日期显示下拉框
                  alwaysShowCalendars : true,
                  singleDatePicker: true,
                  // showWeekNumbers:true,
                  minDate:'1920-01-01',
                  maxDate:'2060-01-01',
                  opens:'center',
                  autoUpdateInput : false,//关闭输入框自动赋值
                  init : function () {},
                  callback : function () {}
              };

              $weeker.each(function () {
                  var _self  = $(this);
                  var data = $.extend(true,defOpt,opt,$T.data(this)||{});
                  _self.width(data.width);//设置宽度
                  if(data.readonly){_self.attr('readonly','readonly');};//设置只读

                  var format = data.locale.format;
                  data.minDate = moment(data.minDate).day(0); //最小可选择日设置为当前日期的周日
                  data.maxDate = moment(data.maxDate).day(6);//最大可选择日设置为当前日期的周六


                  var txtVal = data.value?data.value : new Date();//初始化输入框值
                  var txtValArr = getWeekByday(data.value,format);
                  data.startDate = moment(txtValArr.start);
                  data.endDate = moment(txtValArr.end);

                  var  dataName = _self.attr('name');
                  var sname = dataName+data.beginStr;
                  var ename = dataName+data.endStr;
                  var rangeD = '<input type="text" class="hide '+sname+'" name="'+sname+'"><input type="text" class="hide '+ename+'" name="'+ename+'">';
                  _self.after(rangeD);
                  var $beginTxt = _self.next('.'+sname);
                  var $endTxt = $beginTxt.next('.'+ename);

                  //window.console && console.log(data);
                  _self.daterangepicker(data,function(s,e,label){
                    dropPicker.call(this,s,e,label,_self,data,$beginTxt,$endTxt);
                  });



                  if(data.prevNextBtn){
                      var $prevBtn = $('<span class="btn btn-small"><i class="icon icon-chevron_left"></i></span>');
                      var $nextBtn = $('<span class="btn btn-small"><i class="icon icon-chevron_right"></i></span>');
                      _self.before($prevBtn);
                      _self.after($nextBtn);

                      $prevBtn.click(function () {//上一周
                          prevNextE(_self,data,sname,ename,$beginTxt,$endTxt,0);
                      });

                      $nextBtn.click(function () {//下一周
                          prevNextE(_self,data,sname,ename,$beginTxt,$endTxt,1);
                      });
                  }

                // if (data.value) {//初始化输入框值

                setRangeDate(_self,txtValArr.start,txtValArr.end);
                _self.val(txtValArr.start + data.locale.separator + txtValArr.end);
                $beginTxt.val(txtValArr.start);
                $endTxt.val(txtValArr.end);
                data.init.call(_self,moment(txtValArr.start),moment(txtValArr.end),data);
                // };
              });

              function prevNextE(_self,data,sname,ename,$beginTxt,$endTxt,eType){//eType 0:prev,1:next;
                  var format = data.locale.format;
                  var separator = data.locale.separator;
                  var v = _self.val().split(separator);
                  var optype = ['subtract','add'][eType];//新日期是减还是加操作
                  v[0] = moment(v[0])[optype](7,'day');
                  v[1] = moment(v[1])[optype](7,'day');
                  var minRDate = data.minDate.clone().subtract(7, 'days');
                  var setIf = !eType? (v[0].diff(minRDate)>=0):(data.maxDate.diff(v[1])>=0);//可设置值条件是(1?小于最大值:大于最小值)
                  if(setIf){//如果 prev : 大于最小日期, next:小于最大日期，即在日期范围内才可重置取值
                        _self.daterangepicker($.extend(data,{
                              startDate : v[0],
                              endDate : v[1]
                            }),function(s,e,label){
                              dropPicker.call(this,s,e,label,_self,data,$beginTxt,$endTxt);
                        });
                        setRangeDate(_self,v[0],v[1]);
                        data.callback.call(_self,v[0],v[1],data);
                        v[0] = v[0].format(format);
                        v[1] = v[1].format(format);
                        $beginTxt.val(v[0]);//赋值隐藏input
                        $endTxt.val(v[1]);
                        _self.val(v.join(separator));
                  }
              }
              function setRangeDate($o,start,end){//能为控件添加日期范围，用默认的data方式只能添加起始日期
                $o.data('daterangepicker').setStartDate(start);
                $o.data('daterangepicker').setEndDate(end);
              }

              function dropPicker(s,e,label,_self,data,$beginTxt,$endTxt) {
                  var sday = s.day();//获取当前周几，周日为0，周一为1，周二为2，类推
                  var startDay,endDay;
                  var format = data.locale.format;
                  var separator = data.locale.separator;
                    startDay = s.day(0).format(format);//本周日
                    endDay = e.day(6).format(format);//本周六
                    setRangeDate(_self,startDay,endDay);
                  _self.val(startDay + separator + endDay);
                  $beginTxt.val(startDay);//赋值隐藏input
                  $endTxt.val(endDay);
                  // window.console && console.log(this);
                  data.callback.call(_self,s,e,data);
              }

              function getWeekByday (day,format) {
                  var sday = moment(day).day();//获取当前周几，周日为0，周一为1，周二为2，类推
                  var week = {
                    start : null,
                    end : null
                  }
                    week.start = moment(day).day(0).format(format); //本周日
                    week.end = moment(day).day(6).format(format); //本周六
                  // window.console && console.log(week);
                  return week;
              }

          });

    },
    /**
     * @description: 范围日期控件，api参考 Date Range Picker
     * @param {string | HTMLElement} [$range = '.so-rangeDate'] - 受控对象
     * @param {object} opt - 参数
     * @param {string} [opt.format = 'YYYY-MM-DD'] - 日期格式
     * @param {boolean} [opt.readonly =  true] - 是否只读
     * @param {boolean} [opt.timePicker =  false] - 带不带时间选择
     * @param {boolean} [opt.single =  false] - 是否单日历
     * @param {boolean} [opt.auto =  true] - 是否自动填写，不带清空输入框功能
     * @param {boolean} [opt.isShowClear =  true] - 是否显示清除按钮
     * @param {string} [opt.minDate =  '1920-01-01'] - 最小日期
     * @param {string} [opt.beginStr =  'Begin'] - 控件自动生成开始日期框的name后缀
     * @param {string} [opt.endStr =  'End'] - 控件自动生成结束日期框的name后缀
     * @param {{days: number}} [opt.maxSpan =  {days: 90}] - 日期跨度最大值
     * @param {'today' | 'day' | 'yesterday' | 'week' | 'month' | 'thisMonth' | 'prevMonth' | [number, number] | '' | null} opt.val - 初始值，在 dateV 中选择
     * @param {boolean} opt.autoApply - 日期范围不用确认，自动选择
     * @param {boolean} [opt.linkedCalendars = false] - 两个日历不一起联动，保证可以选择两个月以上的日期
     * @param {object[]} [opt.ranges] - 快速选择范围
     * @param {boolean} [opt.autoUpdateInput = true] - 自动更新输入值
     * @param {boolean} [opt.alwaysShowCalendars = true] - 总是显示日历面板
     * @param {'left' | 'center' | 'right'} [opt.opens = 'center'] - 面板显示位置
     * @param {(s: object,e: object)=>void} [opt.init] - 初始化事件, s: 起始时间，e:结束事件
     * @param {(s: object,e: object,label: string,_self:HTMLElement)=>void} [opt.callback] - 选择后回调事件,s: 起始时间，e:结束事件
     */
    rangeDate : function ($range,opt) {
      var $range =$range?$($range) : $('.so-rangeDate');
        require(['moment'],function (moment) {
            var dateV = {
              today : [
                moment().hour(0).minute(0).second(0) ,
                moment()
              ],
              day : [
                moment().hour(0).minute(0).second(0) ,
                moment().hour(23).minute(59).second(59)
              ],
              yesterday : [
                moment().subtract(1, 'days').hour(0).minute(0).second(0) ,
                moment().subtract(1, 'days').hour(23).minute(59).second(59)
              ],
              week : [
                moment().subtract(6, 'days').hour(0).minute(0).second(0) ,
                moment().hour(23).minute(59).second(59)
              ],
              month : [
                moment().subtract(30, 'days').hour(0).minute(0).second(0) ,
                moment().hour(23).minute(59).second(59)
              ],
              thisMonth : [
                moment().startOf('month'),
                moment().endOf('month')
              ],
              prevMonth : [
                moment().subtract(1, 'month').startOf('month'),
                moment().subtract(1, 'month').endOf('month')
              ]
            }
            var ranges = {
                '今天': dateV.today,
                '整今天': dateV.day,
                '昨天': dateV.yesterday,
                '最近一周': dateV.week,
                '最近30天': dateV.month,
                '当月': dateV.thisMonth,
                '上个月': dateV.prevMonth
              };
              var defOpt = {
                    // singleDatePicker : singleDatePicker,
                    // startDate: moment().subtract(6, 'days'),
                    // endDate: moment(),
                    // showWeekNumbers :true,
                    // showCustomRangeLabel : false,
                    // alwaysShowCalendars:true,
                    // showDropdowns : true,//日期显示下拉框
                    format : 'YYYY-MM-DD',//默认格式
                    readonly:true,
                    timePicker : false,//带不带时间选择
                    single : false,//是否单日历
                    auto : true,//是否自动填写，不带清空输入框功能
                    isShowClear : true,//是否显示清除按钮
                    minDate : '1920-01-01',
                    maxDate : '2060-01-01',
                    beginStr : 'Begin',
                    endStr : 'End',
                    maxSpan:{days: 90},
                    val : null,//初始值，在 dateV 中选择
                    autoApply : true,//日期范围不用确认，自动选择
                    linkedCalendars: false,//两个日历不一起联动，保证可以选择两个月以上的日期
                    ranges : ranges,
                    autoUpdateInput : true,
                    alwaysShowCalendars : true,
                    opens : 'center',
                    init : function () {},
                    callback : function () {}
                };
              $range.each(function () {
                var _self  = $(this);
                _self.attr('autocomplete','off');
                var data = $.extend(true,{},defOpt,opt||{},$T.data(this)||{});
                if(data.diyRanges){data.ranges = data.diyRanges;}
                if(!data.isShowClear){//不显示清除按钮 添加 class hideCancelBtn
                  data.cancelButtonClasses = 'hideCancelBtn';
                }
                var format = data.format;
                data.singleDatePicker = data.singleDatePicker || data.single;//是否单选日期
                if(data.timePicker){data.timePicker24Hour= true;}
                if(data.readonly){_self.attr('readonly','readonly');}

                data = $.extend({
                  locale :{
                    separator: ' 至 ',
                    format : format,
                    cancelLabel: '清空'
                  }
                },data||{});
                if(data.width){_self.width(data.width);}

                if (!data.singleDatePicker) {//如果是选取范围，这里需要插入隐藏域来获取值
                  var  dataName = _self.attr('name');
                  var sname = dataName+data.beginStr;
                  var ename = dataName+data.endStr;
                  if(!(_self.next().hasClass(sname))){//如果没有插入过
                    var rangeD = '<input type="text" class="hide '+sname+'" name="'+sname+'"><input type="text" class="hide '+ename+'" name="'+ename+'">';
                  }
                  _self.after(rangeD);
                };


                var val = data.val===null? 'today':data.val;

                var valtype = typeof(val);
                var $beginTxt = _self.next('.'+sname);
                var $endTxt = $beginTxt.next('.'+ename);
                if(val){
                    if(valtype==='string'){
                        data.startDate = dateV[val][0];
                        data.endDate = dateV[val][1];
                    }else if(valtype ==='object'){
                        data.startDate = typeof(val[0])=='object'?val[0]:moment().subtract(-val[0]*1, 'days');
                        data.endDate = typeof(val[1])=='object'?val[1]:moment().subtract(-val[1]*1, 'days');
                    }
                    var s = data.startDate.format(format),e = data.endDate.format(format);
                    if (data.singleDatePicker) {//range初始化值
                        (!data.auto)&&_self.val(s);
                    }else{
                        (!data.auto)&&_self.val( s + data.locale.separator + e);
                        $beginTxt.val(s);
                        $endTxt.val(e);
                    }
                }
                //window.console && console.log(s,e);
                data.init.call(_self,s,e);

                // window.console && console.log(data);
                if(data.auto){
                    _self.daterangepicker(data,function (s,e,label) {
                      if (!data.singleDatePicker) {
                        var stxt = s.format(format),etxt = e.format(format);
                          if(!data.auto){
                            _self.val( stxt + data.locale.separator + etxt)
                          }
                          $beginTxt.val(stxt);
                          $endTxt.val(etxt);
                      }
                      //window.console && console.log(this,s,e,label,_self);
                      data.callback.call(this,s,e,label,_self);
                    });
                }else{//是否自动填写，不带清空输入框功能
                  data.autoApply = false;
                  data.autoUpdateInput = false;
                  _self.daterangepicker(data);
                  _self.on('apply.daterangepicker', function(ev, picker) {
                        var s = picker.startDate,e = picker.endDate;
                        var stxt = s.format(format),etxt = e.format(format);
                         if (data.singleDatePicker) {
                              $(this).val(stxt);
                          }else{
                              $(this).val( stxt + data.locale.separator + etxt);
                              $beginTxt.val(stxt);
                              $endTxt.val(etxt);
                          }
                          data.callback.call(picker,s,e,picker.chosenLabel,_self);
                  });
                  _self.on('cancel.daterangepicker', function(ev, picker) {
                          $(this).val('');
                         if (!data.singleDatePicker) {
                            $beginTxt.val('');
                            $endTxt.val('');
                          }
                  });
                }
              });
          })
    },
  /**
   * 统一的表单验证
   */
  validate : function ($par) {
    var me = this;
    var $par = $par? $($par) : $('body');

    $('.btn-easyFormSubmit',$par).bind('click',function () {
      submitFn(this);
    });

    function submitFn(btn) {
      var $btn = $(btn);
      var $pForm = $btn.parents('.form-validate');
      var validate = $pForm.form("validate");
      if(validate){
        /*
          可配参数有(可以加在 按钮上，也可以加在form的data-opt中，按钮属性优先)：
          action : form提交地址，按钮上如果有action，按钮属性优先
          noconfirm : 不需要确认框就提示 为'true'执行，非布尔值
          msg : 自定义提示框信息，非 noconfirm状态显示
          noclosepop : 提交成功后，不执行关闭pop弹窗
          unTargetFocus : 为true时，弹窗时不夺走提交按钮焦点
          unConfirmFocus : 为true时，弹窗时不聚焦在确定按钮上
          arrMode : 为'true'时， name相同的值以数组的形式返回，默认以 , 隔开的字符串
          ajaxJson : ajax方式提交表单数据

          data-opt中还可以配置 beforeCallback 与 callback(表单提交前、后返回事件)，值为全局里的函数名，多个函数用 || 隔开
         */
        var formData = $T.data($pForm);//form个性化附加数据

        var arrMode = $btn.attr("arrMode") || formData.arrMode;
        var unConfirmFocus = $btn.attr("unConfirmFocus") || formData.unConfirmFocus;
        var unTargetFocus = $btn.attr("unTargetFocus") || formData.unTargetFocus;
        arrMode = (arrMode==='true'?true:false);
        var sendData = $pForm.sovals(!arrMode);

        var callSumbit = true;
        if (formData.beforeCallback){//提交之前事件函数名，多个可用 || 隔开
          var callName = formData.beforeCallback.split('||');
          $.each(callName,function (i,v) {
            if(window[v]){
              callSumbit = window[v](formData,$pForm,sendData);
              return !!callSumbit;//为false提前跳出循环
            }
          });
        }
        if (!callSumbit) {return;};

        // window.console && console.log(formData);
        var action = formData.action || $btn.attr("action") || $pForm.attr('action');//表单请求地址
        var noconfirm = formData.noconfirm || $btn.attr("noconfirm");//获取不弹窗提示确认，只能为true才不提示
        var nocalltip = formData.nocalltip || $btn.attr("nocalltip");//提交后提示，只能为true才不提示
        var msg = formData.msg || $btn.attr("msg") ||"您确定要提交吗?";//确认框提示信息
        var noclosepop = formData.noclosepop || $btn.attr("noclosepop");//表单提交后是否自动关闭弹窗
        nocalltip = (nocalltip==='true' || nocalltip===true)?true:false;
        noclosepop = (noclosepop==='true'||noclosepop===true)?true:false;
        msg = (noconfirm==='true'||noconfirm===true) ? false : msg;
        var jsonData = formData.json || $btn.attr("json");

        sendData = $.extend(sendData,formData.params ||{});
        // window.console && console.log(jsonData);
        //window.console && console.log(formData,noconfirm);
        $btn.unbind('click');
        $ajax.post(action,sendData,{tip:msg, calltip:!nocalltip, targetFocus: !unTargetFocus, confirmFocus: !unConfirmFocus, jsonData:jsonData,callback:function(){
            $btn.bind('click',function(){submitFn(this)});
          },cancelback:function(){
            $btn.bind('click',function(){submitFn(this)});
          },errback:function(req){
            if (formData.errback){
              window[formData.errback]&&window[formData.errback](req,formData);
            }
            $btn.bind('click',function(){submitFn(this)});
          }}).done(function (rst) {
          if (rst.code==='200'||rst.code==='201') {
            //window.console && console.log(formData.callback);
            if (formData.callback){//提交之后事件函数名，多个可用 || 隔开
              var callName = formData.callback.split('||');
              $.each(callName,function (i,v) {
                window[v]&&window[v](rst,formData);
              });
            }
            if (formData.submitClear)$(formData.submitClear).val("");
            if(!noclosepop){//如果没有禁用关闭弹窗，则关闭自身
              parent.window._refreshParent = true;
              setTimeout(function(){
                $pop.closePop();
              },400);
            }
          };
          // if (rst.code==='200'&&!msg) {//多余判断，$ajax.post里已经有了
          //   $pop.msg('信息提交成功',{icon:1,time: 1000});
          // };
          if(rst.code=='500'){
            if (formData.code500back){
              window[formData.code500back]&&window[formData.code500back](rst,formData);
            }
          }
        });
      }
    }

  },
     wdDate: function (cls) {//日期范围选择组件
        cls = cls || '.wd_date';
        if (!$(cls).length) {
        } else {
            var start = $(cls).find("#" + $(cls).attr("data-start"));
            var end = $(cls).find("#" + $(cls).attr("data-end"));
            var target = $($(cls).attr("data-bind"));//关联按钮会触发
            if (end.length == 0)end = start;
            $(".first", cls).click(function () {
                var startDate = new Date(start.val().replace(/-/g, '/'));
                startDate.setDate(1);
                start.val($.fmtDate('yyyy-MM-dd', startDate));
                if (target.length)target.click();
            });
            $(".prev", cls).click(function () {
                var startDate = new Date(start.val().replace(/-/g, '/'));
                startDate.setDate(startDate.getDate() - 1);
                start.val($.fmtDate('yyyy-MM-dd', startDate));
                if (target.length)target.click();
            });
            $(".next", cls).click(function () {
                var endDate = new Date(end.val().replace(/-/g, '/'));
                endDate.setDate(endDate.getDate() + 1);
                end.val($.fmtDate('yyyy-MM-dd', endDate));
                if (target.length)target.click();
            });
            $(".last", cls).click(function () {
                var endDate = new Date(end.val().replace(/-/g, '/'));
                endDate.setMonth(endDate.getMonth() + 1);
                endDate.setDate(0);
                end.val($.fmtDate('yyyy-MM-dd', endDate));
                if (target.length)target.click();
            });
        }
    }
};

var $page = {
    /**
     * @description: 页面区域初始化
     * @param {string} [par = 'body'] - 初始化的dom父元素
     */
    init : function (par){//页面初始化
        var p = par? par : 'body';
        $page.someMix(p);//零碎事件初始化
    },
    /**
     * @description: 容器初始化函数,包括解析模板
     * @param {string} contId - 初始化的dom父元素
     */
    contInit : function (contId){//容器初始化函数
        $.parser.parse(contId);
        $page.init(contId);
    },
    inLoadFun : function (){//载入pageload函数
        var me = this;
        require(['plus/pageload'],function (pageload){
            me.load = pageload.load;
            me.loadAfter  = pageload.loadAfter;
        });
    },
    clickLoad : function (cls){//点击加载页面函数
        var me = this;
        var cls = cls || '.so-loadpage';
        // console.log(pageload);
        var loadO = function (o){
            this.o = $(o);
            this.url  = this.o.attr('rel');
            this.opt = this.attr();
            var me = this;
            this.o.click(function (){
                me.open();
            });
        }

        loadO.prototype = {
            attr : function (){
                var $o = this.o;
                var opt = $T.data($o);
                var exOpt = {
                    title : $o.attr('pageTitle'),
                    $el : $o.attr('target')
                }
                return $.extend(exOpt,opt||{});
            },
            open : function (){
                me.load(this.url,this.opt);
            }
        }
        new loadO(cls);
    },
    someMix: function (par) {//页面控件初始化集合
        var me = this;
        var $p = par? $(par) : $('body');
        function $$(c){
            return $p.find(c);
        }

        $T.placeHolder.init($p);//对低版本浏览器placeholder属性的兼容
        if($$('.so-loadpage').length){//加载页面按钮
            $page.clickLoad($$('.so-loadpage'));
        }
        if($$('.so-search').length){//grid search
            $form.search($$('.so-search'));
        }
        if($$('.so-clear').length){//form clear
            $form.clear($$('.so-clear'));
        }
        if($$('.so-pinyin').length){//获取拼音输入框初始化
            $form.soGeitPinyin($$('.so-pinyin'));
        }
        if ($$('.so-date').length) {//日期控件初始化
            $form.soDate($$('.so-date'));
        }
        if ($$('.so-rangeDate').length) {//日期范围控件初始化
            $form.rangeDate($$('.so-rangeDate'));
        }
        if($$('.so-weekDate').length){//日期选择周控件初始化
            $form.rangeWeek($$('.so-weekDate'));
        }
        if ($$(".btn-resetForm").length) {//重置表单
            $form.clickResetForm($$(".btn-resetForm"));
        }
        if ($$(".btn-cancel").length) {//表单里的关闭按钮，关闭事件
            $$(".btn-cancel").click(function () {
                $pop.closePop();
            });
        }
        if ($$(".btn-closePop").length) {//表单里的关闭按钮，关闭事件
            $$(".btn-closePop").click(function () {
                $pop.closePop();
            });
        }
        if ($$('.form-enter').length) {//回车替代tab事件
            $form.formAEnterFun(null,$$('.form-enter'));
          // me.formAEnterFunB();
        };
        if ($$('.btn-easyFormSubmit').length) {//表单提交事件
            $form.validate($p);
        };
        if ($$(".so-select").length) {//soSelect下拉框初始化
            $$(".so-select").soSelect();
        }
        if ($$('.so-drop').length) {//简单的easyui下拉控件初始化
            $form.soDrop($$('.so-drop'));
        };
        if ($$('.so-pop').length) {//sopop控件初始化，慢慢被easyui的comb控件替换，保留是为了兼容一些旧的事件
            $form.soPop($$('.so-pop'));
        };
        if ($$('.drop').length) {//drop通过rel来初始化选择值
            $$('.drop').each(function () {
                var v = $(this).attr('rel');
                if (v) {$(this).val(v);};
            })
        };
        if ($$('.op-newTab').length) {//在新tab页面打开url
            $$('.op-newTab').click(function () {
                var _self = $(this);
                var title = _self.attr('title') || _self.text();
                var url = _self.attr('rel');
                $pop.newTabWindow(title,url);
                return false;
            });
        };
        if ($$(':input[noNull],.required').length) {//时间和选择控件对应的必填输入框添加必填小三角样式
            $$(':input[noNull],.required').each(function () {
                var _self = $(this);
                if (_self.hasClass('so-choice') || _self.hasClass('so-pop')) {
                    _self.addClass('so-requirePop');
                }
            });
        }

    }
}

/**
主框架关闭tab页之前执行的提示事件
默认执行 window.closeWinTabTip 事件
可自己将函数名 data到body上，$('body').data('closeWinTabTip',函数名)
函数返回布尔值(是否需要提示)，也可以返回对象 {state:true/false,tip: string}//tip：需要提示的信息
**/
window.eye_bfParTabWindowCloseTipMsg = function (){
    var dtd = $.Deferred();
    var fn = $('body').data('closeWinTabTip');//获取绑定在body上的 closeWinTabTip 参数是否有函数名（close页面时执行的函数名）
    if(!fn&&window.closeWinTabTip){fn = 'closeWinTabTip';};//如果 window.closeWinTabTip 存在，退出前执行 closeWinTabTip 函数
    if(fn){
        var tip = '页面有信息未保存，是否退出页面？';//默认提示信息
        var fnc = window[fn]();//可以直接返回布尔值(是否需要提示)，也可以返回对象 {state:true/false,tip: string}
        if(typeof(fnc) ==='object'){
            tip = fnc.tip || tip;
            callback = fnc.okBack;
            fnc = fnc.state;
        };
        // window.console&&console.log(fnc);
        if(fnc === true){//如果退出前需要提示
            $pop.confirm(tip,function (){
              doCallback(callback);
            },function (){
                returnState(false);
            });
        }else{//如果退出不需要提示
          doCallback(callback);
        };
    }else{
        returnState(true);
    };
    return dtd.promise();

    function doCallback(callback) {
      var dcClose = false;//不同步关闭，false 表示同步关闭
      if(callback){
        dcClose = callback(function () {//函数无返回或返回false则执行同步关闭事件
          returnState(true);//回传关闭事件，异步后需页面中手动执行关闭
        });
      }
      !dcClose && returnState(true);
    }

    function returnState(state){
        if(state){
            window.eye_bfParTabWindowCloseTipMsg = null;
            dtd.resolve(true);
        }else{
            dtd.resolve(false);
        };
    }
};

window.eyeStore = {//数据中心
  comData : {}//暂存子页面间通讯数据
};

$(function () {
    $page.init();
    $page.inLoadFun();
});

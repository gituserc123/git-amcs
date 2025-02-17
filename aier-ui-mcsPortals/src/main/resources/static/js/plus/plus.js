define(['pub'] ,function(){
  (function () {
    var replaceAll = function (str, substr, replacement, ignoreCase) {
      if (!substr || substr == replacement) { return str; }
      var regexp = new RegExp(String(substr), ignoreCase ? "gm" : "igm");
      return str.replace(regexp, replacement);
    };

    var liveSearch = function (target, param) {
      var t = $(target), panel = t.datagrid("getPanel"), cells, field, value = param,scrollToFirst = false,  regexp;
      if ($.isPlainObject(param)) {
        value = param.value;
        field = param.field;
        scrollToFirst  = param.scrollToFirst;
        cells = panel.find("div.datagrid-body tr.datagrid-row td[" + (field ? "field=" + field : "field") + "] div.datagrid-cell");
      } else {
        cells = panel.find("div.datagrid-body tr.datagrid-row td[field] div.datagrid-cell");
      }

      var firstIx = -1;
      cells.each(function () {
        var cell = $(this);
        cell.find("span.datagrid-cell-hightlight").replaceWith(function () { return $(this).text(); });

        if (!value) { return; }
        if(!/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_-]|)*$/.test(value)){return;}
        var text = cell.html(); if (!text) {return;}
        if(firstIx===-1 && text.indexOf(value)>-1){firstIx = cell.parents('.datagrid-row').attr('datagrid-row-index')};
        cell.html(replaceAll(text, value, '<span class="datagrid-cell-hightlight">' + value + '</span>'));
      });
      if(scrollToFirst && firstIx>-1){
        t.datagrid('scrollTo',firstIx);
      }
    };

    $.extend($.fn.datagrid.methods, {
      liveSearch: function (jq, param) { return jq.each(function () { return liveSearch(this, param); }); },
    });

    $.fn.extend({
      popTimer: function (opt){
        var o = $.extend(true,{
          title: null,
          time: '',
          format: 'yyyy-MM-dd HH:mm',
          minDate: '', // yyyy-MM-dd HH:mm:ss
          maxDate: '', // yyyy-MM-dd HH:mm:ss
          // validator: null,
          hasClear: true,
          secondSuffix: false,
          saveClosePop: true,
          autoSetVal: true,
          submit: null,
        }, opt || {});

        $(this).attr('autocomplete', 'off').click(function(){
          var _self = $(this);
          var time = o.time || _self.val();
          if(_self.hasClass('unedit')){return false;}
          plus.popTimer({
            title: o.title,
            time: time,
            format: o.format,
            hasClear: o.hasClear,
            minDate: o.minDate, // yyyy-MM-dd HH:mm:ss
            maxDate: o.maxDate,// yyyy-MM-dd HH:mm:ss
            // validator: o.validator,
            secondSuffix: o.secondSuffix,
            saveClosePop: o.saveClosePop,
            submit: function (data){
              o.autoSetVal && _self.val(data.time);
              o.submit && o.submit(data);
            }
          });
        });

        return $(this);
      },
      /**
       * @description: soPopComboGrid：combgrid多选弹窗
       * @param {object} opt - 参数
       * @param {string} [opt.title = null] - 默认不要标题
       * @param {string | [string, string]} [opt.area = ['750px', '500px'] ]- 弹窗宽高，同layer area属性
       * @param {number} [opt.popType = 1] - 弹窗种类，同layer popType属性
       * @param {string} [opt.skin = 'noXPop soPopGrid soPopComboGrid'] - 弹窗皮肤class，同layer skin属性
       * @param {number} [opt.zIndex = 100000] - 弹窗皮肤xIndex，默认 100000
       * @param {number} [opt.panelWidth = null] - combogrid panelWidth属性
       * @param {boolean} [opt.showHeader = true] - combogrid showHeader属性, 可不显示grid header
       * @param {boolean} [opt.fitColumns = true] - combogrid fitColumns属性, 默认自动适应panel宽度
       * @param {[][]} [opt.columns = null] - grid columns，必填属性
       * @param {{}} [opt.queryParams = null] - grid queryParams属性
       * @param {string} [opt.url = ''] - grid url属性，和data属性二选一，为grid提供数据
       * @param {[] | {}} [opt.data = null] - grid data属性，和data属性二选一，为grid提供数据
       // * @param {boolean} [opt.pagination = false] - grid 分页属性，默认false 不分页
       * @param {string} [opt.textField = ''] - 输入框text对应grid的Field
       * @param {string} [opt.valueField = ''] - 输入框value对应grid的Field
       * @param {string} [opt.valueInput = ''] - 输入框value对应 的input id或class,如 '#valInput', '.vTxt'
       * @param {string} [opt.valueSplit = ','] - 多选值分隔符
       * @param {(valTxt, txtTxt, vals, texts)=>{}} opt.sureback - 点击确定后回调函数
       * @return { jQueryDomArray } input 自身JQuery对象
       */
      soPopComboGrid: function(opt){
        var opts = $.extend({
          title: null,
          area: ['600px', '200px'],
          popType: 1,
          skin: 'noXPop soPopGrid soPopComboGrid',
          zIndex : 100000,
          showHeader: true,
          fitColumns: true,
          columns: null,
          queryParams: null,
          url: '',
          data: null,
          // panelWidth: 650,
          textField: '',
          valueField: '',
          hideValueField: false,
          valueInput: '',
          valueSplit: ',',
          sureback: function(){},
        }, opt || {});

        var $this = $(this);

        $this.each(function (i, v){
          var $v = $(v);
          var o = $.extend(true, opts||{} ,$T.data(v)||{});

          if(!o.columns){
            o.columns = [[]];
            o.showHeader =  false;
            if(o.valueField) {
              if(o.valueField != o.textField && !o.hideValueField){
                o.columns[0].push({title:o.valueField,field:o.valueField,width:100});
              }
            }
            if(o.textField){
              o.columns[0].push({title:o.textField,field:o.textField,width:160});
            }
            if(!(o.valueField || o.textField)){
              console.log('没有显示列');return;
            }
          }
          if(!o.url && !o.data){console.log('没有url或数据，无法显示');return;}
          if(!o.textField && !o.valueField){console.log('没有field，无法赋值');return;}
          if(!o.valueField){o.valueField = o.textField;}
          if(!o.textField){o.textField = o.valueField;}
          o.opening = false; //判断是否打开

          $v.prop('readonly', true);
          $v.click(function (){
            vFn.call(this, o);
          }).focus(function (){
            vFn.call(this, o);
          });
        });


        function vFn (o){
          var _self = $(this);
          //如果已经打开，则不执行
          if(o.opening){return;}
          // if(!o.valueInput){//如果没有赋值输入框，则自动生成
          //   var already = _self.attr('already');
          //   if(!already){
          //     var inputName = _self.attr('name');
          //     _self.removeAttr('name').attrs({
          //       already: 'true',
          //       txtName: inputName
          //     });
          //     $val = $('<input type="hidden" name="'+inputName+'">');
          //     _self.after($val);
          //   }
          // }else{
          //   $val = $(o.valueInput);
          // }

          $pop.open({
            title: o.title,
            type: o.popType,
            zIndex: o.zIndex,
            skin: o.skin,
            area: o.area,
            btn: null,
            content: '<div class="spgWrap">' +
                '    <div class="spgSearch spgComboSearch">' +
                '        <form class="soform formA form-validate form-spgSearch">' +
                '            <div class="spgSearchMainArea comboSearchArea"><input class="txt txt-comboQ" style="width:100%;" type="text" name="q" value="" /></div>' +
                '            <button type="button" class="btn btn-small btn-primary btn-spgSave">确定</button>' +
                '            <button type="button" class="btn btn-small btn-warning btn-spgClear">清空</button>' +
                '            <button type="button" class="btn btn-small btn-spgClose">关闭</button>' +
                '        </form>' +
                '    </div>' +
                '    <div class="spgValsArea spgComboValsArea">' +
                '         <p class="spgVals"></p>' +
                '     </div>' +
                '</div>',
            end: function (){
              //关闭后，输入框聚焦，但不再触发当前弹窗事件
              setTimeout(function (){
                o.opening = false;
              },300);
              _self.focus();
            },
            success: function ($p, popIndex){
              o.opening = true;
              var vals = [], texts = [];
              var txtsTxt = _self.val();
              if(txtsTxt){
                texts =  vals = txtsTxt.split(o.valueSplit);
              }
              if(o.valueInput && $(o.valueInput).length && $(o.valueInput).val()){
                vals = $(o.valueInput).val().split(o.valueSplit);
              }
              // console.log(texts, vals);
              var $combo = $p.find('.txt-comboQ');
              //grid选项
              var gridOpt = {
                striped:true,
                mode : o.url ? 'remote' : 'local',
                showHeader: o.showHeader,
                fitColumns: o.fitColumns,
                idField: o.valueField,
                textField: o.textField,
                limitToList : true,
                reversed : true,
                clearIcon: true,
                columns: o.columns,
                queryParams: o.queryParams,
                onSelect: function (val,record){
                  fn.selectRecord(record);
                }
              }
              if(o.panelWidth){ gridOpt.panelWidth = o.panelWidth; }
              if(o.url){ gridOpt.url = o.url; }
              if(o.data){ gridOpt.data = o.data; }

              //初始化grid
              $combo.combogrid(gridOpt);

              $p.find('.textbox-text').focus();

              var fn = {
                selectRecord: function (record){ //选择记录处理
                  var val = record[o.valueField];
                  var have = false;
                  $.each(vals, function (j, w){
                    if(w == val){
                      have = true;
                      return false;
                    }
                  });
                  if(!have){
                    vals.push(val);
                    texts.push(record[o.textField]);
                  }
                  fn.renderVals($p);
                  $combo.combogrid('setValue', '');
                  setTimeout(function (){ //选择后重新打开下拉，无需用户再次输入触发
                    $combo.combogrid('showPanel');
                  },100);
                },
                renderVals: function($p){ //渲染已选值
                  var textsHtml = '';
                  $.each(texts, function (i, v){
                    textsHtml += '<span class="s-val" rel="'+ i +'"><em class="em-val">'+ v +'</em><em class="em-x" rel="'+ i +'"></em></span>';
                  });
                  $p.find('.spgVals').html(textsHtml);
                  $p.find('.spgValsArea').scrollTop($p.find('.spgValsArea').prop("scrollHeight"));
                  $p.find('.em-x').click(function (){
                    var ix = $(this).attr('rel');
                    var rowV = vals.splice(ix, 1);
                    texts.splice(ix, 1);
                    $(this).parent().remove();
                  });
                }
              };

              fn.renderVals($p);
              //清空
              $p.find('.btn-spgClear').click(function (){
                vals = [];
                texts = [];
                $p.find('.spgVals').html('');
              });
              //保存
              $p.find('.btn-spgSave').click(function (){
                var txts = texts.join(',');
                _self.val(txts).attr('title',txts);
                if(o.valueInput && $(o.valueInput).length){
                  $(o.valueInput).val(vals.join(','));
                }
                if(o.sureback){
                  o.sureback(vals.join(','), txts, vals, texts);
                }
                $pop.close(popIndex);
              });
              //关闭
              $p.find('.btn-spgClose').click(function (){
                $pop.close(popIndex);
              })
            }
          });
        };
        return $this;

      },
      /**
       * @description: soPopSearchGrid：带搜索功能的grid弹窗
       * @param {object} opt - 参数
       * @param {string} [opt.title = '请选择'] - 弹窗标题
       * @param {string | [string, string]} [opt.area = ['750px', '500px'] ]- 弹窗宽高，同layer area属性
       * @param {number} [opt.popType = 1] - 弹窗种类，同layer popType属性
       * @param {string} [opt.skin = 'soPopGrid soPopSearchGrid'] - 弹窗皮肤class，同layer skin属性
       * @param {number} [opt.zIndex = 100000] - 弹窗皮肤xIndex，默认 100000
       * @param {string} [opt.searchHtml = '<input class="txt txt-validate" type="text" name="q" value="" />'] - 搜索区域 html，默认为 name="q"的单输入框，可自定义替换
       * @param {[][]} [opt.columns = null] - grid columns，必填属性，
       * @param {boolean} [opt.fitColumns = true] - grid fitColumns属性, 默认自动适应panel宽度
       * @param {boolean} [opt.insertCheckColumn = true] - grid 是否自动插入checkbox列，默认true，否则用户自己插入
       * @param {string} [opt.url = ''] - grid url属性，和data属性二选一，为grid提供数据
       * @param {[] | {}} [opt.data = null] - grid data属性，和data属性二选一，为grid提供数据
       * @param {{}} [opt.queryParams = null] - grid queryParams属性
       * @param {boolean} [opt.pagination = false] - grid 分页属性，默认false 不分页
       * @param {string} [opt.textField = ''] - 输入框text对应grid的Field
       * @param {string} [opt.valueField = ''] - 输入框value对应grid的Field
       * @param {string} [opt.valueInput = ''] - 输入框value对应 的input id或class,如 '#valInput', '.vTxt'
       * @param {string} [opt.valueSplit = ','] - 多选值分隔符
       * @return { jQueryDomArray } input 自身JQuery对象
       */
      soPopSearchGrid: function(opt){
        var opts = $.extend({
          title: '请选择',
          area: ['750px', '500px'],
          popType: 1,
          skin: 'soPopGrid soPopSearchGrid',
          zIndex : 100000,
          searchHtml: '<input class="txt txt-validate" type="text" name="q" value="" />',
          columns: null,
          fitColumns: true,
          insertCheckColumn: true,
          url: '',
          data: null,
          queryParams: null,
          pagination: false,
          textField: '',
          valueField: '',
          valueInput: '',
          valueSplit: ',',
        }, opt || {});

        var $this = $(this);

        $this.each(function (i, v){
          var $v = $(v);
          var o = $.extend(true, opts||{} ,$T.data(v)||{});

          if(!o.columns){console.log('没有显示列');return;}
          if(!o.url && !o.data){console.log('没有url或数据，无法显示');return;}
          if(!o.textField && !o.valueField){console.log('没有field，无法赋值');return;}
          if(!o.valueField){o.valueField = o.textField;}
          if(!o.textField){o.textField = o.valueField;}
          o.opening = false; //判断是否正在关闭

          $v.prop('readonly', true);

          $v.click(function (){
            vFn.call(this, o);
          }).focus(function (){
            vFn.call(this, o);
          });

        });

        function vFn (o){
          var _self = $(this);
          if(o.opening){return;}
          var randomId = Math.floor(new Date().getTime() / 10000);

          $pop.open({
            title: o.title,
            type: o.popType,
            zIndex: o.zIndex,
            skin: o.skin,
            area: o.area,
            btn: null,
            content: '<div class="spgWrap">' +
                '    <div class="spgSearch">' +
                '        <form class="soform formA form-validate form-spgSearch">' +
                '            <div id="spgSearchMainArea" class="spgSearchMainArea"></div>' +
                '            <button type="button" class="btn btn-small btn-primary btn-spgSearch">搜索</button>' +
                '            <button type="button" class="btn btn-small btn-warning btn-spgClear">清空</button>' +
                '            <button type="button" class="btn btn-small btn-primary btn-spgSave">确定</button>' +
                '        </form>' +
                '    </div>' +
                '    <div class="spgTop">' +
                '        <div class="spgValsArea">' +
                '            <p class="spgVals"></p>' +
                '        </div>' +
                '    </div>' +
                '    <div class="spgGridBox">' +
                '        <div id="spgGrid'+ randomId +'"></div>' +
                '    </div>' +
                '</div>',
            end: function (){
              setTimeout(function (){
                o.opening = false;
              },300);
              _self.focus();
            },
            success: function ($p, popIndex){
              o.opening = true;
              $page.contInit($p.find('.spgWrap')); //初始化整个区域
              var $g = $('#spgGrid'+randomId);
              $('#spgSearchMainArea').html(o.searchHtml);

              var vals = [], texts = [];
              var gridInit = false, checkedLoad = false;

              var txtsTxt = _self.val();
              if(txtsTxt){
                texts =  vals = txtsTxt.split(o.valueSplit);
              }
              if(o.valueInput && $(o.valueInput).length && $(o.valueInput).val()){
                vals = $(o.valueInput).val().split(o.valueSplit);
              }

              // console.log(texts, vals);
              var fn = {
                initGridOpt: function ($p){
                  var gridH = $p.find('.spgWrap').height() - $p.find('.spgSearch').height() - $p.find('.spgTop').height() -12;
                  var gridOpt = {
                    checkOnSelect : true,
                    selectOnCheck : true,
                    singleSelect : false,
                    // ctrlSelect : true,
                    pagination : o.pagination,

                    fitColumns : o.fitColumns,
                    columns: o.columns,
                    queryParams: o.queryParams,
                    onCheck: function (index, row){
                      checkedLoad && fn.checkRows($p, [row], true);
                    },
                    onCheckAll: function (rows){
                      fn.checkRows($p, rows, true);
                    },
                    onUncheck: function (index, row){
                      checkedLoad && fn.checkRows($p, [row], false);
                    },
                    onUncheckAll: function (rows){
                      fn.checkRows($p, rows, false);
                    },
                    onLoadSuccess: function (data){
                      checkedLoad = false;
                      fn.checkedRowByVals(data.rows);
                      checkedLoad = true;
                      if(!gridInit){
                        fn.renderVals($p);
                        gridInit = true;
                      }
                    },
                    height: gridH,
                    offset: 0
                  };
                  if(o.url){ gridOpt.url = o.url; }
                  if(o.data){ gridOpt.data = o.data; }
                  if(o.insertCheckColumn){ gridOpt.frozenColumns = [[{title:'',field:'checkboxField',checkbox:true}]]; }
                  return gridOpt;
                },
                checkedRowByVals: function(rows){
                  var rowVals = [];
                  $.each(rows, function (i, v){
                    rowVals.push(v[o.valueField]);
                  });
                  $.each(vals, function (i, v){
                    var ix = rowVals.indexOf(v);
                    if(ix > -1){
                      $g.datagrid('checkRow', ix);
                    }
                  });
                },
                getRowByVal: function(val, rows){
                  var rr = rows || $g.datagrid('getRows');
                  var r = { row: null, rowIndex: -1 };
                  $.each(rr, function (i, v){
                    if(v[o.valueField] == val){
                      r.row = v;
                      r.rowIndex = i;
                      return false;
                    }
                  });
                  return r;
                },
                checkRows: function($p, rows, state){
                  // console.log('checkRows');
                  if(!gridInit){return;}
                  $.each(rows, function (i, v){
                    var have = false, vIdx = -1;
                    $.each(vals, function (j, w){
                      if(w == v[o.valueField]){
                        have = true;
                        vIdx = j;
                        return false;
                      }
                    });
                    if(state && !have){
                      vals.push(v[o.valueField]);
                      texts.push(v[o.textField]);
                    }
                    if(!state && vIdx > -1){
                      vals.splice(vIdx, 1);
                      texts.splice(vIdx, 1);
                    }
                  });
                  fn.renderVals($p);
                },
                renderVals: function($p){
                  var textsHtml = '';
                  $.each(texts, function (i, v){
                    textsHtml += '<span class="s-val" rel="'+ i +'"><em class="em-val">'+ v +'</em><em class="em-x" rel="'+ i +'"></em></span>';
                  });
                  $p.find('.spgVals').html(textsHtml);
                  $p.find('.spgValsArea').scrollTop($p.find('.spgValsArea').prop("scrollHeight"));
                  $p.find('.em-x').click(function (){
                    var ix = $(this).attr('rel');
                    var rowV = vals.splice(ix, 1);
                    texts.splice(ix, 1);
                    var rIdx = fn.getRowByVal(rowV[0]).rowIndex;
                    $g.datagrid('uncheckRow', rIdx);
                    fn.renderVals($p);
                  });
                }
              };

              $grid.newGrid($g, fn.initGridOpt($p));

              //搜索
              $p.find('.btn-spgSearch').click(function (){
                var searchData = $p.find('.form-spgSearch').sovals();
                $g.datagrid('load',searchData);
              });

              //清空
              $p.find('.btn-spgClear').click(function (){
                vals = [];
                texts = [];
                $p.find('.spgVals').html('');
                $g.datagrid('uncheckAll');
              });

              //保存
              $p.find('.btn-spgSave').click(function (){
                var txts = texts.join(',');
                _self.val(txts).attr('title',txts);
                if(o.valueInput && $(o.valueInput).length){
                  $(o.valueInput).val(vals.join(','));
                }
                $pop.close(popIndex);
              });
            }
          });
        }
        return $this;
      }
    });

  })(jQuery);

  var plus = {
    gridChangeRowByUpDownKey: function (grid){
      var $grid = $(grid || '#gridBox');
      var rowLen = 0;
      $(document).keyup(function (e){
        if(e.keyCode == 38 || e.keyCode == 40){
          rowLen = $grid.datagrid('getRows').length;
          gridSelectRow(e.keyCode);
        }
      });

      function gridSelectRow(keycode){
        var sr = $grid.datagrid('getSelected');
        if(sr){
          var rowIdx = $grid.datagrid('getRowIndex',sr);
          $grid.datagrid('clearSelections');
          var nextIdx = rowIdx+1;
          if(keycode == 38){
            nextIdx = rowIdx-1;
            nextIdx = nextIdx<0? 0: nextIdx;
          }else{
            nextIdx = nextIdx>=rowLen ? rowLen-1: nextIdx;
          }
          $grid.datagrid('selectRow',nextIdx);
        }
      }
    },
    setRangeDate(o,start,end){//能为控件添加日期范围，用默认的data方式只能添加起始日期
      $(o).data('daterangepicker').setStartDate(start||new Date());
      $(o).data('daterangepicker').setEndDate(end||new Date());
    },
    formEnterEx: function (form,firstFocus){// 排斥 readonly 输入框 回车下一个
      setTimeout(function (){
        var $form = $(form || '.form-enter');

        $form.find('.textbox-text').each(function (){
          var _self = $(this);
          var $sourInput = _self.parents('.textbox').prev('.required');//查找源对象是否有class required
          if ($sourInput.length) {_self.addClass('required')};
        });

        $form.find(':text').unbind('blur.a').bind('blur.a',function () {
          $(this).val($.trim($(this).val()));
        });

        var $input = $form.find(':input:visible:enabled,.btn-easyFormSubmit').not('.txt-noEnter').not(':input[readonly]');
        // console.log($input);
        $input.unbind('keydown.a').bind('keydown.a',function(e) {
          if (e.keyCode == 13) {
            if ($(this).hasClass('btn-easyFormSubmit')) {return;};
            var ix = $input.index(this);
            $input.eq(ix+1).focus();
            return false;
          };
        });
        firstFocus && $input.eq(0).focus();
        $('.tooltip').remove();
      },400);
    },
    /**
     * @description: 弹窗时间选择器
     * @param {object} opt - 参数
     * @param {string} opt.time - 传入时间，格式： yyyy-MM-dd HH:mm
     * @param {string} opt.minDate - 时间下限，格式： yyyy-MM-dd HH:mm:ss
     * @param {string} opt.maxDate - 时间上限，格式： yyyy-MM-dd HH:mm:ss
     * @param {string} opt.format - 时间格式，默认格式： yyyy-MM-dd HH:mm
     * @param {boolean} [opt.secondSuffix = false] - time是否带秒后缀，为true返回 yyyy-MM-dd HH:mm:00
     * @param {boolean} [opt.saveClosePop = false] - 保存时自动关闭弹窗
     // * @param {(date)=>boolean} opt.validator - 验证是否可选择函数
     * @param {(data)=> {}} opt.submit - 返回事件
     * @return {number} pop index
     */
    popTimer(opt){
      var o = $.extend({
        title: null,
        time: '',
        hasClear: true,
        format: 'yyyy-MM-dd HH:mm',
        // validator: null,
        minDate: '', // 时间下限
        maxDate: '', // 时间上限
        secondSuffix: false,
        saveClosePop: false,
        submit: function (data){}
      },opt||{});
      // console.log(o);
      var time = $.fmtDate('yyyy-MM-dd HH:mm', o.time) || $.fmtDate('yyyy-MM-dd HH:mm', new Date());
      const t = time.split(' ');
      const hm = t[1].split(':');
      const data = {time: time, date: t[0],HH: hm[0], mm: hm[1]};
      // console.log(data);
      return $pop.open({
        title: o.title,
        skin: 'popTimer',
        area: ['450px', o.title? '235px': '200px'],
        btn: null,
        content: `<form class="form-timer form-validate form-enter pad-10">
                <div class="picker">
                    <div class="myDater"></div>
                    <input class="txt myDate" type="hidden" name="date" value="" />
                </div>
                <span id="myDate"></span>
                <div class="daterBox">
                    <div class="txtBox"><input class="txt txt-date txt-dater" style="width:100%;" type="text" name="time" autocomplete="off" readonly /></div>
                    <div class="txtBox">
                        <div class="dater timer" style="margin-right:3%;"><input class="txt txt-timeSpinner timer-HH" type="text" rel="0" name="HH" style="width:100%;" data-options="precision:0" /></div>
                        <div class="dater timer"><input class="txt txt-timeSpinner timer-mm" type="text" rel="1" name="mm" style="width:100%;" data-options="precision:0" /></div>
                    </div>
                    <p class="center">
                        <input type="button" class="btn btn-primary btn-save" value="确定" />
                        ${o.hasClear? '<input type="button" class="btn btn-warning btn-clear" value="清空" />': ''}
                        <input type="button" class="btn btn-cancel" value="关闭" />
                    </p>
                </div>
            </form>`,
        success: function ($p,popIndex){
          var $f = $p.find('.form-timer');
          $f.form('load', data);
          $form.formAEnterFun(null,'.form-timer');

          var $myDater = $p.find('.myDater');
          var $dater = $p.find('.txt-dater');
          var validator = function (){return true;}
          if(o.minDate || o.maxDate){ //当有最大最小值的时候
            validator = function (date){
              var can = true;
              if(o.minDate){
                let minD = o.minDate.split(' ')[0];
                if(new Date(date).getTime()  < new Date(minD).getTime() - 86400000){can = false;}
              }
              if(o.maxDate){
                if(new Date(date).getTime() > new Date(o.maxDate).getTime()){can = false;}
              }
              return can;
            }
          }
          $myDater.calendar({
            width: 190, height:180,
            validator: validator,
            onChange: function(){
              setV();
            }
          });
          $myDater.calendar('moveTo', new Date(data.date));

          $p.find('.txt-timeSpinner').numberspinner({
            formatter: function(value) {
              if (value * 1 < 10) {
                return '0' + value;
              } else {
                return value;
              }
            },
            onChange: function (val, oldVal){
              //轮回
              // console.log(val,oldVal);
              var type = $(this).attr('spinnername');
              var max = type == 'mm' ? 59:23;
              var min = 0;
              if(oldVal*1<val*1 && val*1>max){
                $(this).setVal(min);
                return false;
              }
              if(oldVal*1>val*1 && val*1<min){
                $(this).setVal(max);
                return false;
              }
              //轮回结束
              setV();
            }
          });

          function setV(){
            var date = $.fmtDate('yyyy-MM-dd', $myDater.calendar('options').current);
            var HH = $('.timer-HH').getVal();
            HH = HH*1<10 ? '0' + HH : HH;
            var mm = $('.timer-mm').getVal();
            mm = mm*1<10 ? '0' + mm : mm;
            // console.log('setV:', date, HH, mm);
            var newV = date +' ' + HH + ':' + mm;
            if(o.minDate && new Date(newV).getTime() < new Date(o.minDate).getTime()){
                $pop.tips('最小时间值: ' + o.minDate, '.txt-dater');
                return false;
            }
            if(o.maxDate && new Date(newV).getTime() > new Date(o.maxDate).getTime()){
              $pop.tips('最大时间值: ' + o.maxDate, '.txt-dater');
              return false;
            }
            $p.find('.myDate').val(date);
            $dater.val(newV);

          }

          // 关闭
          $p.find('.btn-cancel').click(function (){
            $pop.close(popIndex);
          });

          // 清除
          $p.find('.btn-clear').click(function (){
            o.submit && o.submit({
              date: '',
              time: '',
              HH: '',
              mm: '',
              popIndex: popIndex,
            });
            if(o.saveClosePop){
              $pop.close(popIndex);
            }
          });

          // 保存
          $p.find('.btn-save').click(function (){
            var data = $f.sovals();
            if(o.secondSuffix){
              data.time = data.time + ':00';
            }
            // console.log(data);
            data.popIndex = popIndex;
            o.submit && o.submit(data);
            if(o.saveClosePop){
              $pop.close(popIndex);
            }
          });
        }
      });
    },

    outpCheckRecordTip: function(opt) {
      var o = $.extend({
        url: base + '/ui/outp/outpCheckRecord/execBySceneCode',
        //sceneCode: '',
        needPop: true,
        param: {},
        callback1: function (rst){},
        callback2: function (rst){},
      },opt||{});
      // console.log(o);
      var icons = ['icon-bell_alt', 'icon-exclamation_sign', 'icon-remove_sign'];
      $ajax.postJson(o.url, JSON.stringify(o.param)).done(function (rst) {
        // debugger;
        if (rst.code==='200'||rst.code==='201') {
          if(o.needPop){
            if(rst.data.length > 0 ){

              var tipInfo = {
                all: 0,
                t0: 0,
                t1: 0,
                t2: 0,
                html: '',
              };
              $.each(rst.data, function (i, v){
                tipInfo.all++;
                tipInfo['t'+ v.result]++;
                tipInfo.html += '<li class="li-chkRecordOne li-chkRecord-'+ v.result +'"><h4 class="h4-t h4-t'+ v.result +'"><em class="em-icon '+ icons[v.result] +'"></em>' + v.recordName + '</h4>' +
                  // '<p class="p-abs"><span class="s-c1">'+ v.c1 +'</span><span class="s-c2">'+ v.c2 +'</span><span class="s-c3">'+ v.c3 +'</span></p>' +
                  '<p class="p-info">'+ v.c1 +'</p><p class="p-info">'+ v.c2 +'</p></li>'
              });

              $pop.open({
                title: '提示',
                area: '650px',
                btn: null,
                content: `
<style type="text/css">
.outpCheckRecordWrap{font-size:14px;}
.chkTipHeader{display:flex;}
.chkTipHeader .li-h{width:15%;flex-grow:1;margin:0 5px;height:45px;position:relative;padding: 5px 5px 5px 62px;color:#fff;border-radius:4px;cursor:pointer;}
.chkTipHeader .li-h:hover{opacity:0.75;}
.chkTipHeader .li-h-all{background-color:#0072FF;}
.chkTipHeader .li-h-2{background-color:#E73827;}
.chkTipHeader .li-h-1{background-color:#ED8F03;}
.chkTipHeader .li-h-0{background-color:#4cb8c4;}
.chkTipHeader .em-icon{position:absolute;left: 22px;top:12px;width:32px;height:32px;font-size:27px;border-radius:50%;}
.chkTipHeader .em-t{display:block;font-size:16px;}
.chkTipHeader .em-num{display:block;font-size:18px;font-weight:bold;margin-left:6px;}

.ul-chkRecords{min-height:250px;max-height:360px;overflow:auto;margin:10px 0;}
.li-chkRecordOne{padding:0 0 15px;line-height: 1.5em;}
.li-chkRecordOne .h4-t{border-top:1px dotted #ccc;padding:6px 10px;font-size:14px;margin-bottom:10px;background-color:#EFF5F8;}
.li-chkRecordOne .h4-t .em-icon{margin-right:4px;}
.li-chkRecordOne .p-abs{display:flex;padding:0 10px;}
.li-chkRecordOne .p-abs span{width:30%;flex-grow:1;}
.li-chkRecordOne .s-c1{color:#3169c5;}
.li-chkRecordOne .s-c2{color:#A66400;}
.li-chkRecordOne .s-c3{color:#7daeb3;text-align:right;margin-right:10px;}
.li-chkRecordOne .h4-t2{color:#E73827;}
.li-chkRecordOne .h4-t1{color:#ED8F03;}
.li-chkRecordOne .h4-t0{color:#4cb8c4;}
.li-chkRecordOne .p-info{margin:10px;}
</style>
<form class="form-timer form-validate form-enter pad-10">
	                                <div class="outpCheckRecordWrap">
	                                  <ul class="chkTipHeader">
                                      <li class="li-h li-h-all" rel="all"><em class="em-icon icon-info-r"></em><em class="em-t">全部</em><em class="em-num">${tipInfo.all}</em></li>
                                      <li class="li-h li-h-2" rel="2"><em class="em-icon icon-remove_sign"></em><em class="em-t">禁止</em><em class="em-num">${tipInfo.t2}</em></li>
                                      <li class="li-h li-h-1" rel="1"><em class="em-icon icon-exclamation_sign"></em><em class="em-t">警示</em><em class="em-num">${tipInfo.t1}</em></li>
                                      <li class="li-h li-h-0" rel="0"><em class="em-icon icon-bell_alt"></em><em class="em-t">提示</em><em class="em-num">${tipInfo.t0}</em></li>
                                    </ul>
                                    <ul class="ul-chkRecords">
                                     ${tipInfo.html}
                                    </ul>
	                                </div>
	                                <p class="p-btn center">
	                                	<input type="button" class="btn btn-primary btn-ok" ${tipInfo.t2 ? "disabled":""} value="继续" />
	                                	<input type="button" class="btn btn-back" value="返回" />
	                                </p>
	                            </form>`,
                success: function($p, index){
                  $p.find('.li-h').click(function (){
                    var idx = $(this).attr('rel');
                    if(idx == 'all'){
                      $('.li-chkRecordOne').show();
                    }else{
                      $('.li-chkRecordOne').hide();
                      $('.li-chkRecord-'+ idx).show();
                    }
                  });
                  $p.find('.btn-ok').click(function(){
                    //事件
                    o.callback1(o.param.callback1Param);
                    //关闭弹窗
                    $pop.close(index);
                  });

                  $p.find('.btn-back').click(function(){
                    //事件
                    o.callback2(o.param.callback2Param);
                    //关闭弹窗
                    $pop.close(index);
                  });
                }
              });
            }else{
              o.callback1(o.param.callback1Param);
            }
          }else{
            if(rst.data.length){
              o.callback1(o.param.callback1Param);
            }else{
              o.callback2(o.param.callback2Param);
            }
          }
        }
      });
    },
    loadScript: function(url, callback) {
      var script = document.createElement("script");
      script.type = "text/javascript";
      if (script.readyState) {  // 兼容IE
        script.onreadystatechange = function() {
          if (script.readyState == "loaded" || script.readyState == "complete") {
            script.onreadystatechange = null;
            callback && callback();
          }
        };
      } else {  // 其他浏览器
        script.onload = function() {
          callback && callback();
        };
      }
      script.src = url;
      document.getElementsByTagName("head")[0].appendChild(script);
    },
    loadDCJS: function (){
      // if(!$store.get('dc_CreateWriterControlForWASM')){
      plus.loadScript(portalUri + '/aemr/ui/doc/dcwriter/document?wasmres=dcwriter5.js');
      // }
      window.DCWriter5Started = function () {
        console.log('DCWriter5Started');
        $store.set('dc_CreateWriterControlForWASM', window.CreateWriterControlForWASM);
      }
    }

  }
  return plus;
});




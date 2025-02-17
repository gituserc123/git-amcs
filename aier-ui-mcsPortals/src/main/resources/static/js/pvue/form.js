window.define && define(['pTools','pub'],function ($t) {

  const isEasy = {
    all (input){//easy控件及so-date控件
      const $input = $(input);
      return ($input.hasClass('textbox-f') || $input.hasClass('co-date'));
    },
    numberbox (input){
      const $input = $(input);
      return $input.hasClass('numberbox-f');
    },
    comboByTxt (input){//通过 textbox-text 查找 combo
      const $input = $(input);
      if($input.hasClass('textbox-text')){
        return $input.parent().hasClass('combo');
      }
    }
  }

  /**
   * 根据 输入框是否多选和值类型返回设置值
   * @param input
   * @param val
   * @returns {*}
   */
  function getEasyVal(input,val){
    if(!val){return val;}
    const $input = $(input);
    let selfOpts = $input.hasClass('textbox-f')?$(input).textbox('options'):'';
    if(selfOpts && selfOpts.multiple && val.constructor === String){
      return val.split(',');
    }
    return val;
  }

  /**
   * 给必填输入框添加 required 样式
   * @param form
   */
  function inputAddRequired(form){
    const $form = $(form);
    $inputs =  $form.find(':input:visible:not(:radio):not(:checkbox)');
    $inputs.each(function (i,v){
      const _self =$(v);
      let $oInput = _self;
      if(_self.hasClass('textbox-text')){
        $oInput = _self.parent().prev();
      }
      const rule = $oInput.attr('rule');
      if(rule && rule.indexOf('req')>-1){
        _self.addClass('required');
      }
    })
  }

  function formCreate (form,options){//创建表单 组件
    /*
    * 所有参数：
    *  model : 表单验证在 vue实例(scope)中的对象,也可以从指令中设置
    *  autofocus : 是否首输入框自动获取焦点
    *  errStr(err-str): 显示错误字段，对应在vue实例(scope)中的字段
    *  tips(tips) : 为false不显示随框提示
    *  keyupValidate(keyup-validate): 为true 输入框keyup时不验证，只 focus 和回车对输入框验证；输入框可以单独加此属性，优先级高于form上设置
    * 全部参数设置方式示例：
    *  <form class="form" v-form-create:autofocus=“form” model="form" err-str="errInfo" tips="false" autofocus="true" keyup-validate="false" data-options="{model:'form',errStr:'errInfo',keyupValidate:false,tips:false,autofocus:true,callback:'afterSubmitFn',beforeCallback:'beforeSubmitFn'}"
      action="json/true.js">
    *
    * dom标签可执行事件：
    *  rule : 绑定验证规则 ，方式 rule="规则1|规则2[参数1，参数2,...]"
    *  keyenter = 'func' : 回车执行 vue实例(scope)中的func事件，不带参数
    *  clickSubmit(clicksubmit,click-submit) 点击提交表单
    *  clickReset(clickreset,click-reset) 点击重置表单
    * 示例：
    *  <input class="txt-item" type="password" name="rePassword" v-model="form.rePassword" rule="req|equals['#loginPass','输入密码不一致']" keyupValidate="true" keyenter="submitForm" autocomplete="off" />
    * <span class="btn-submit" clickSubmit >提交</span>
    * <button type="button" class="btn-reset" clickreset >重置</button>
    * */
    let $f = $(form);
    let opts = $T.data(form) || {};
    opts.model = opts.model ||  $f.attr('model'); //表单对象
    opts.autofocus = opts.autofocus ||  $f.attr('autofocus');//是否自动焦点
    opts.errStr = opts.errStr ||$f.attr('errStr') || $f.attr('err-str') ;//消息提示字段，对应 scope[errStr]
    opts.tips = opts.tips === false?false:($f.attr('tips') === 'false'?false:true);//为false不显示随框提示
    opts.requiredMark = opts.requiredMark === false?false:(($f.attr('requiredMark') === 'false'||$f.attr('required-mark') === 'false')?false:true);//为false不显示随框提示
    opts.keyupValidate = opts.keyupValidate === false?false:(($f.attr('keyupValidate') === 'false'||$f.attr('keyup-validate') === 'false')?false:true);//默认keyup 验证，keyupValidate为false ，只blur和回车验证输入框
    opts = $.extend(opts,options);//手动传入的options权级比html上获取的高
    $f.data('fcOpts',opts);//将参数绑定在form 的 fcOpts data对象上
    // console.log('参数：',opts);
    formInputsBind(form,true);
  }

  /*
  *  表单重新重置创立函数(之前已经创建过才有效)
  * form : 表单 dom
  * opts : Object{noResetState:false} 是否清除验证状态
  * */
  function formReCreate(form,opts){
    let $f = $(form);
    let o = $f.data('fcOpts');
    if(!opts.noResetState){//默认清除表单验证状态
      formResetState(form,o.scope);
    }
    formInputsBind(form);
  }

  /* 根据输入框的name值和model获取对应的value值
  *  model : form数据 响应式对象
  * */
  function getModelValByName(name,model){
    // console.log(name,model);
    let nameArr = name.split('.'),val;
    if (nameArr.length >= 2) {
      val = model[nameArr[0]];
      for (let i = 1; i < nameArr.length; i++) {
        val = val[nameArr[i]];
      }
    } else {
      val = model[name]
    }
    return val;
  }

  /* 根据输入框的name值和model设置对应的value值
*  model : form数据 响应式对象
* */
  function setModelValByName(name,model,val){
    // console.log(name,model,val)
    let nameArr = name.split('.'),tempK;
    if (nameArr.length >= 2) {
      tempK = model[nameArr[0]];
      for (let i = 1; i < nameArr.length-1; i++) {
        tempK = tempK[nameArr[i]];
      }
      tempK = val;
    } else {
      model[name] = val
    }
    // console.log(5,'name:',name,',val:',val);
  }

  function formEasyLoadData(form,model){
    // console.log($(form).find('.textbox-text'));
    $(form).find('.textbox-text').each(function (i,v){
      let _self = $(v);
      let oSelf = _self.parent().prev();
      let name = oSelf.attr('textboxname');
      let val = getModelValByName(name,model);
      if(val){
        val = getEasyVal(oSelf,val);
        oSelf.setVal(val);
      }

    });
  }


  /* 设置input对应在model中的值，并验证有效性
  *
  * */
  function setModelVal({input,opts,validate = true}){
    const name = input.attr('name') || input.attr('textboxname');
    let val = input.getVal();
    const formS = opts.model?opts.scope[opts.model]:opts.scope;
    setModelValByName(name,formS,val);
    validate && validateOne(input,opts,'setVal');
  }

  /* 表单绑定验证
  * 只需传入form dom对象，参数挂载在dom的data属性 fcOpts 上
  * */
  function formInputsBind(form,init= true){
    setTimeout(function (){ // 这里没有 nextTick函数
      let $f = $(form);
      let opts = $f.data('fcOpts');
      let $inputs =  $f.find(':input:visible:enabled:not(:radio):not(:checkbox)');//需要回车的inputs
      opts.inputs = $inputs;
      // console.log('输入框数组：',$inputs);
      //默认第一个聚焦
      if(opts.autofocus){
        $inputs.eq(0).focus();
      }

      if(opts.model){
        $f.data('originalFormData',$t.deepClone(opts.scope[opts.model]));
      }

      // if(init){
        $f.form({
          onChange:function (tar){
            // console.info('form onChange',tar);
            let $tar = $(tar);
            if(isEasy.all($tar)){
              setModelVal({input:$tar,opts});
            }

          }
        });
      // }

      formEasyLoadData($f,opts.scope[opts.model]);//easy控件 load数据
      opts.requiredMark && inputAddRequired($f); // input 添加 required 标识

      var $soDate = $f.find('.co-date');
      $soDate.each(function (){//日历控件
        var _self = $(this);
        var opt = $T.data(_self);
        $form.soDate(_self,{
          onpicked: function(dp){
            setModelVal({input:_self,opts});
            if(opt.onpicked){opt.onpicked(dp);}
          },
          oncleared : function (dp){
            setModelVal({input:_self,opts});
            if(opt.oncleared){opt.oncleared(dp);}
          }
        });

      });

      //回车处理
      let dalayV = false;
      $inputs.unbind('.fc');
      $inputs.bind('keyup.fc',function (e){//keyup验证
        let _self = $(this);
        let k = $inputs.index(_self);
        let keyupValidate = (_self.attr('keyupValidate')==='false'||_self.attr('keyup-validate')==='false')?false:opts.keyupValidate;

        if(e.keyCode == 13){//回车处理
          dalayV = true;//避免回车和blur验证2次
          let state = validateOne(_self,{...opts, focus:true},'keyEnter').state;
          setTimeout(()=>{dalayV = false;},20);
          if(!state){return;}

          setTimeout(()=>{
            let keyEnter = _self.attr('keyEnter') || _self.attr('keyenter') || _self.attr('key-enter');
            if(keyEnter && opts.scope[keyEnter]){
              opts.scope[keyEnter]();
            }
          });
          if($inputs.eq(k+1)){
            $inputs.eq(k+1).focus();
          }
        }else{
          if(keyupValidate && (!isEasy.comboByTxt(_self))){
            validateOne(_self,opts,'keyup');
          }
        }

      }).bind('blur.fc',function (){//blur 处理验证
        var _self = $(this);
        (!dalayV) && validateOne(_self,opts,'blur');
      }).bind('focus.fc',function (){//聚焦清除非本输入框的验证
        var _self = $(this);
        setTimeout(()=>{
          if(_self.hasClass('input-err') && opts.tips){
            $inputs.tooltip('hide');
             _self.tooltip('show');
          }
        });
      });


      //提交处理
      let $btn = $f.find('*[clickSubmit],*[clicksubmit],*[click-submit]');
      $btn.unbind('.fc').bind('click.fc',function (){//提交表单
        var _self = $(this);
        if(formValidate(form,opts)){//表单验证
          let formData = {};
          if(opts.model){
            formData = opts.scope[opts.model];
            $form.submitForm(_self, $f,opts,formData);//调用 通用的(旧版)按钮提交事件
          }else{
            console.log('没有指定提交的model对象，无法提交');
          }
        }
      });

      //重置表单
      let $reset = $f.find('*[clickReset],*[clickreset],*[click-reset]');
      $reset.unbind('.fc').bind('click.fc',function (){
        formReset(form,opts.scope);
      });
    },400);
  }

  /**
   * @param input
   * @param opts : Object : {
   * scope, // 组件scope
   * model, // form 在scope中名称
   * errStr, // 在scope 中的错误字段
   * tips, // 验证错误时 是否提示
   * focus, // 验证错误时 是否聚焦错误字段
   * inputs // 回车 inputs
   * }
   * @param inputOpt
   * @returns {{msg: string, state: boolean}}
   */
  function validateOne(input,opts,vStyle){//验证单个输入框函数
    let {scope,model,errStr,tips,focus,inputs} = opts;
    let $input = $(input);//输入框
    let $cInput = $input;//切换class 的input
    let $oInput = $input;//原始 input
    // console.log('vStyle:',vStyle);

    const isEasybox = $input.hasClass('textbox-f');//是否是easy组件
    const isEasyTxt = $input.hasClass('textbox-text');//是否是easy组件生成的输入框
    // console.log('isEasybox:',isEasybox,',isEasyTxt:',isEasyTxt);

    if(isEasybox){
      $cInput = $cInput.next();
      $input = $cInput.find('.textbox-text');
    }
    if(isEasyTxt){
      $cInput = $input.parent();
      $oInput = $cInput.prev();
    }

    // console.log('isEasybox:',isEasybox,',isEasyTxt:',isEasyTxt);
    const $inputs = inputs && $(inputs);//form范围内输入框
    let rule = $oInput.attr('rule');
    let result = {state:true, msg:'',input:$input,cInput:$cInput,oInput:$oInput};

    if(rule){
      //get modelVal begin
      let modelVal = $input.val();
      // console.log('============val:',$input.val());
      // let modelVal = $oInput.getVal();
      // if(isEasy.numberbox($oInput)){
      //   modelVal = $input.val();
      // }
      let modelName = $oInput.attr('name') || $oInput.attr('textboxname');
      let formO = model?scope[model]:scope;//如果有model，直接从scope[model]中 查找 输入框对象
      // if(modelName && (!isEasyTxt) && (!$oInput.hasClass('co-date'))){//不为easy输入框，不为 so-date控件，从响应对象中取值
      if(modelName && (!isEasyTxt) ){//不为easy输入框，从响应对象中取值
        modelVal = getModelValByName(modelName,formO);
      }

      // console.log('modelVal:',modelVal)
      //get modelVal end

      rule = rule.split('|');

      let success = true,errInfo = '';
      // console.log(rule);
      $.each(rule,function (i,v){
        let r = v.match(/(\w*)\[(.*)\]/) , ruleOpt;
        if (r) {
          v = r[1];
          ruleOpt = eval(`[${r[2]}]`);
        }
        // console.log(v,ruleOpt,$.rules[v]);
        if($.rules[v]){
          if(!$.rules[v].validator(modelVal,ruleOpt)){
            if(v == 'req'){//必填提示区分处理 errInfo = req['必填提示信息'] || placeholder || missingMessage || 请输入必填字段
              errInfo= (ruleOpt && ruleOpt[0]) || $oInput.attr('placeholder') || $oInput.attr('missingMessage') || '请输入必填字段'
            }else{
              errInfo = $oInput.attr('invalidMessage') || $T.format($.rules[v].message,ruleOpt ||[]);
            }
            success = false;return false;
          }
        }
      });

      // console.log(scope[errStr])
      // console.log(success);
      if(!success){
        // console.log('err',$input);
        $cInput.addClass('validatebox-invalid input-err');
        if(errStr){
          scope[errStr] = errInfo;
        }
        if(tips){
          if($inputs){
            $inputs.each(function (){
              var _s = $(this);
              if(_s.hasClass('textbox-text')){
                _s.parent().tooltip('hide');
              }else{
                _s.tooltip('hide');
              }
            });
          }
          $cInput.tooltip({
            position: 'right',
            content: errInfo,
          }).tooltip('show');
        }
        focus && $input.focus();
        result.state = false;
        result.msg = errInfo;
      }else{
        // console.log('success',$input);
        $cInput.removeClass('validatebox-invalid input-err');
        if(errStr){
          scope[errStr] = '';
        }
        if(tips){
          $cInput.tooltip('destroy');
        }
        result.state = true;
        result.msg = '';
      }
    }

    return result;
  }

  function formValidate (form,options){//form 验证函数 scope 为 vue实例
    const $form =$(form);
    let $inputs = $form.find(':input[rule]');
    let {scope} = options;
    let opts = $T.data($form);
    opts.model = opts.model || $form.attr('model'); //表单对象
    opts.errStr = opts.errStr || $form.attr('errStr') || $form.attr('err-str') ;//消息提示字段，对应 scope[errStr]
    opts.tips = opts.tips === false?false:($form.attr('tips') === 'false'?false:true); //默认toolTip 显示错误新，tips 为 false，不显示tooltips

    opts = $.extend(opts,options);
    opts.inputs = $inputs;

    // console.log(opts);

    let success = true;
    $inputs.each(function (i,v){
      let r = validateOne(v,{...opts},'validate');
      if(!r.state){//验证不通过
        r.input.focus();
        if(opts.errStr){//如果有指定 显示错误的字段
          scope[opts.errStr] = r.msg;//显示错误信息
        }
        if(opts.tips){//通过 tooltip提示
          r.cInput.tooltip({
            position: 'right',
            content: r.msg,
          }).tooltip('show');
        }
        success = false;
        return false;
      }
    });
    return success;
  }

  function formLoadData (form,data,{scope,clear,justSelf}){//form 数据加载 scope 为 vue实例
    const $form =$(form);
    let opts = $T.data($form);
    opts.model = opts.model || $form.attr('model'); //表单对象
    $t.assign(scope[opts.model],data,clear,justSelf);
    formEasyLoadData($form,scope[opts.model]);
  }

  function formReset (form,scope){
    const $form =$(form);
    const opts = $T.data($form)
    opts.model = opts.model ||  $f.attr('model'); //表单对象
    if(opts.model){
      formLoadData($form,$form.data('originalFormData'),{scope,clear:true});//重置数据
      formResetState($form,scope);
    }else{
      console.log('没有指定model对象，reset无法执行');
    }
  }

  function formResetState(form,scope){//重置表单状态
    const $form =$(form);
    const opts = $T.data($form);
    const $inputs = $form.find(':input[rule]');
    $inputs.removeClass('validatebox-invalid input-err');
    opts.errStr = opts.errStr || $form.attr('errStr') ;//消息提示字段，对应 scope[errStr]
    $inputs.tooltip('destroy');
    if(opts.errStr){
      scope[opts.errStr] = '';
    }
  }

  const pForm = {
    create (cont){//v-soform
      // console.log(cont);
      let {ctx, el,exp,arg} = cont;
      let {scope} = ctx;
      let opts = {scope};
      if(exp){opts.model = exp}//如果exp有值，model为exp
      if(arg == 'autofocus'){opts.autofocus = true}//如果exp有值，model为exp
      formCreate(el,opts);
    },
    methods : {
      formReCreate(form,opts={}){
        formReCreate(form,opts);
      },
      formCreate(form,opts){//创建form
        formCreate(form,{scope:this,...opts});//直接绑在vue实例对象上，scope 指向 vue实例
      },
      formValidate(form,opts){//单独调用表单验证
        return formValidate(form,{scope:this,...opts});
      },
      formLoad(form,data,opts){//load数据
        return formLoadData(form,data,{scope:this,...opts});
      },
      formReset(form){//重置表单
        formReset(form,this);
      },
      formResetState(form){//重置表单验证状态
        formResetState(form,this);
      }
    }

  };

  return pForm;
});

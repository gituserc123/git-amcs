define(['pvue','pub'],function (pvue) {
  const {createApp} = pvue;

  const _f = {
    getTemplate : function (opt){//返回获取模板
      return new Promise(function (resolve,reject){
        if(opt.template){
          resolve(opt.template);
        }else if(opt.temUrl || opt.templateUrl){//获取 templates文件路径中的模板
          let url = opt.temUrl || opt.templateUrl;
          if(!url.endsWith('.html')){url = url + '.html'}
          require(['text!templates/'+url], function (template) {
            // opt.template = template;
            resolve(template);
          });
        }else if( opt.temId || opt.templateId){//获取页面中 id 为templateId 的模板
          let tid = opt.temId || opt.templateId;
          if(!tid.startsWith('#')){ tid = `#${tid}`;}
          resolve($(tid).html());
        }else{
          // console.log('没有template可以初始化');
          reject('没有template可以初始化');
        }

      });
    },
    getRadom : function (len = 6){//返回不重复的随机数  根据 时间戳截取值 + 随机数
      const timeS = (new Date().getTime()+'').slice(-len);
      return timeS + ''+ Math.floor(Math.random()*Math.pow(10,len));
    },
  };

  return {
    component :function (o,el){
      let opt = $.extend({
        $el : '',//挂载点
        wrapCls : 'vueCpt',//外包装class
        wrapId : '',//外包装Id ,没有设置为随机数
        replace : false,//是否替换挂载节点
        // template : '',//可能存在
        // temId : '', temUrl : '',//可能存在
        // templateId : '', templateUrl : '',//可能存在
        beforeMounted : function (){},//挂载前，return false不挂载
        mounted : function(){},//加载完执行
        unmounted : function(){}//卸载时执行
      },o || {});

      if(el){opt.$el = el;}//第二个参数存在，即为挂载dom
      let $el = el || opt.$el;
      if(!$el){console.error('没有挂载点,无法初始化！');return;}

      const $cpt = _f.getTemplate(opt).then(function (template){

        if(opt.beforeMounted){//挂载前
          if(opt.beforeMounted(opt,$el)===false){return false;}
        }
        let cptId = opt.wrapId || 'vueCpt-'+ _f.getRadom();
        let cpt = `<div id="${cptId}" class="${opt.wrapCls}" @mounted="onMounted" @unmounted="onUnmounted" v-scope>${template}</div>`;

        if(opt.replace){//是否替换节点
          $($el).after(cpt);
          $($el).remove();
        }else{
          $($el).append(cpt);
        }

        let $parent = $(`#${cptId}`);
        let attrs = $.extend(true,{},opt);//深度复制opt
        ['beforeMounted','$el','wrapId','wrapCls','template','temId','temUrl','templateId','templateUrl','replace'].forEach(v => {
          delete attrs[v];
        });

        const app = createApp({
          $parent,
          ...attrs,
          onMounted() {
            $page.contInit($parent);
            opt.mounted && opt.mounted(opt, $parent);//挂载初始化执行
          },
          onUnmounted(){
            opt.unmounted && opt.unmounted(opt,$parent);//销毁执行
          }
        }).mount(`#${cptId}`);

        return Promise.resolve({app,$parent});
      });
      return $cpt;
    },
    pop : function (o){
      let me = this;
      let opt = $.extend({
        title : '编辑',//标题
        skin :  '',//pop自定义class
        temId : '',//template id
        temUrl : '',//template
        template : '',
        bind : {},//绑定 模板数据及事件
        data : null,
        zIndex : 100000,
        grid : null,
        btn : null,
        area : ['500px','300px'],
        shade:0.5,
        shadeClose:false,
        // maxWidth : '900px',
        // maxHeight : '600px',
        action : '',//pop form的action属性，即提交地址
        beforePop : function(){},
        onPop : function(){},
        end : function (){},
        beforeSubmit : null,//function (formData,$form){}
        afterSubmit : null//提交之后的事件, function (rst,$formBox) {}
      },o||{});

      let rad = _f.getRadom();
      let popCpnId = 'vuePopCpt-' + rad;
      let beforeSubmitPopFormName = 'beforeSubmitPopForm'+rad;
      let submitPopFormName = 'submitPopForm'+rad;

      opt.beforePop && opt.beforePop();
      var temPop =  $pop.open({
        // id : 'popCreateOne',
        title : opt.title,
        skin : opt.skin ||'popTemFormBox',
        content : '<div id="'+popCpnId+'"></div>',
        zIndex:opt.zIndex,
        area : opt.area,
        shade : opt.shade,
        shadeClose  : opt.shadeClose ,
        // maxWidth : opt.maxWidth,
        // maxHeight : opt.maxHeight,
        btn : opt.btn,
        success : function ($p,index){

          opt.bind = {
            $popIndex : index,
            replace : true,
            template : opt.template,
            templateId : opt.templateId,
            templateUrl : opt.templateUrl,
            temId : opt.temId,
            temUrl : opt.temUrl,
            wrapCls : 'vuePopCpt',
            wrapId : popCpnId,
            ...opt.bind
          };

          me.component({
            ...opt.bind,
            mounted() {
              var $form = opt.$form = $p.find('.form-validate');
              var alreadyD = $T.data($form);
              if(opt.action){$form.attr('action',opt.action);};
              if($.isEmptyObject(alreadyD)){//如果form上没有绑定事件，则使用函数事件
                $form.attr("data-opt","{'beforeCallback':'"+beforeSubmitPopFormName+"','callback':'"+submitPopFormName+"'}");
              }
              // $page.contInit($p);
              opt.data&&$form.form('load',opt.data);//有数据加载数据
              $p.find('.btn-cancel,.btn-close,.btn-closeTemPop').click(function (){
                layer.close(index);
              });
              opt.onPop($p,index);
              opt.bind.mounted && opt.bind.mounted(opt,$p,index);
            },
          },'#'+popCpnId);

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
          opt.afterSubmit&&opt.afterSubmit(rst,temPop,formData);
        };
      };

      return temPop;
    },
  };


});

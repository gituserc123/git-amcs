define(['pvue'],function (pvue) {

  function isObject(obj){
    return Object.prototype.toString.call(obj) === '[object Object]' && obj !== null;
  }
  const {createApp, reactive, nextTick} = pvue;
  const tempCls = {
    default : {
      title : { outWrap:'navTabsWrap', wrap:'navTabs', item:'tabTitle', cur:'tabTitleNow',  span:'' },
      cont : {item:'tabCont',cur:'tabShow'}
    },
    a : {
      title : { outWrap:'navTabsWrap', wrap:'tabs likeTabs mar-l5 mar-b5', item:'', cur:'tabs-selected', span:'tabs-inner'},
      cont : {item:'tabCont',cur:'tabShow'}
    },
    b : {
      title : { outWrap:'navTabsWrap', wrap:'nav-tabs', item:'', cur:'now', span:''},
      cont : {item:'tabCont',cur:'tabShow'}
    }
  };

  const navBlock = {//tabs 导航 组件
    renderTab({wrap ='navTabs',item='tabTitle',cur='tabTitleNow',span=''}){
      return `<ul class="${wrap}">
           <li v-for="(nav , index) in navsData" class="${item}" :class="[curIdx == index?'${cur}':'',nav.pteCls]"  @click="change(index)"  ><span class="${span}" v-html="nav.title"></span>{{nav.pteCls}}</li>
      </ul>`;
    },
    tabs(opts) {
      const template = this.renderTab(opts.title);
      return function(){
        return {
          curIdx: opts.initIndex || 0,
          navsData: opts.navs,
          $template: template,
          change(index) {
            if(opts.changeFn(index)){//执行切换事件
              this.curIdx = index;
            }
          }
        }
      }
    }
  };

  /* opt : {
  *   el : 作用对象id
  *   autoNav : true, //自动插入 tabNav
  *   justChangeCls : false， //如果只切换class，不执行 hide show 事件
  *   navs : [{title :''}],
  *   initIndex : Number, //初始index
  *   tabInit ： [Function], //初始切换事件数组， index相对应
  *   beforeTabChange : Function(newIndex,oldIndex) 切换前事件，返回flase不切换
  *   tabChange : Function //切换事件
  *   style : 'a'|'b'| {title,cont}, //nav 样式类型，或者为对象自定义
  *   {//默认值
  *      title : {outWrap:'navTabsWrap',wrap:'navTabs',item:'tabTitle',cur:'tabTitleNow',span:''}
  *      cont : {item:'tabCont',cur:'tabShow'}
  *   }
  *   bind : Object //绑定到范围内可执行的数据, data|function
  * }
  *
  * navTabs 组件：
  * <div class="navTabsWrap" v-scope="navTabs()"></div>
  *
  */
  function tabs (opts,el) {
    const $el = el || opts.el || 'body';
    const $jqEl = $($el);
    const {
      autoNav = true,navs, tabInit,
      initIndex = 0,
      justChangeCls = false,
      style = 'a',
      beforeTabChange = function(){return true;},
      tabChange = function(){},
      bind ={}
    } = opts;

    const tempClsOpt = isObject(style)?{//自定义样式
      title: style.title || {},
      cont: style.cont || {}
    }:(tempCls[style]||tempCls.a);

    const { outWrap= 'navTabsWrap'} = tempClsOpt.title;
    const { item= 'tabCont', cur= 'tabShow' } = tempClsOpt.cont;

    $jqEl.attr({
      'v-scope' : ''
    });
    const hasnavs = $jqEl.find('.'+outWrap).length;

    if(autoNav && !hasnavs){$jqEl.prepend('<div class="'+outWrap+'" v-scope="navTabs()"></div>');}

    const $cont = $jqEl.find(`.${item}`);
    const store = reactive({//数据
      navs,
      navIndex: initIndex,
    });

    function changeFn(index){//change 事件
      if(index!== store.navIndex && beforeTabChange(index,store.navIndex)!==false){
        // console.log(index);
        $cont.removeClass(cur).eq(index).addClass(cur);
        !justChangeCls && $cont.hide().eq(index).show();
        store.navIndex = index;
        nextTick(()=>{
          if(!store.navs[index].isAlreadyInit){//执行初始化事件
            tabInit && tabInit[index] && tabInit[index]();
          }
          tabChange && tabChange(index,store.navs[index].isAlreadyInit);
          store.navs[index].isAlreadyInit = true;
        });
        return true;
      }else{return false;}
    }


    const app = createApp({
      ...bind,
      navTabs : navBlock.tabs({//navTabs组件
        title:tempClsOpt.title,
        initIndex,navs,changeFn
      })
    }).mount($el);

    nextTick(()=>{
      //默认选中 显示 initIndex 面板
      let ix = store.navIndex;
      $cont.removeClass(cur).eq(ix).addClass(cur);
      !justChangeCls && $cont.hide().eq(ix).show();
      tabInit && tabInit[ix] && tabInit[ix]();
      store.navs[ix].isAlreadyInit = true;
      bind.mounted && bind.mounted(store);//执行 bind中的 mounted
    });

    return app;

  }
  return tabs;
});

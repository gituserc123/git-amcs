window.define && define(function () {
  const tools = {
    isNumber(num){//number类型判断
      return num === +num;
    },
    isArray(arr){//数组类型判断
      return Object.prototype.toString.call(arr) === '[object Array]';
    },
    isObj (obj){//对象类型判断
      return Object.prototype.toString.call(obj) === '[object Object]' && obj !== null;
    },
    isTrue(cond,msg){//是否是真
      if(cond){
        return Promise.resolve(true);
      }else{
        console.log('error',msg);
        return Promise.reject(false);
      }
    },
    arrHasVal : function (arr,val) {//数组是否有某个值
      var l = arr.length;
      for (i = 0; i < l; i++) {
        if (arr[i] === val) {
          return i;
        }
      }
      return -1;
    },
    getUrlParams : function (param) {//获取url中的传参
      var query = window.location.search.substring(1);
      var vars = query.split("&");
      if(typeof(param) == 'string'){
        param = [param];
      };
      var vl = vars.length , pl = param.length,kv={};
      for (var j=0;j<pl;j++) {
        kv[param[j]] = '';
        for (var i=0;i<vl;i++) {
          var pair = vars[i].split("=");
          if(pair[0] == param[j]){kv[param[j]] = pair[1];break;};
        }
      };
      return pl==1?kv[param[0]]:kv;
    },
    changQueryParam(data){
      if(data!=null&&typeof data==="object"){
        let rst={};
        Object.keys(data).forEach(key=>{
          let vl=data[key];
          if(vl!=null&&Array.isArray(vl)){
            vl.forEach((data,index)=>{
              rst[key+index]=data;
            })
          }else{
            rst[key]=vl;
          }
        });
        return rst;
      }
      return data;
    },
    copy(obj){//简单的深度复制
      return JSON.parse(JSON.stringify(obj));
    },
    deepClone(obj){//深度克隆
      // 对常见的“非”值，直接返回原来值
      if([null, undefined, NaN, false].includes(obj)) return obj;
      if(typeof obj !== "object" && typeof obj !== 'function') {
        //原始类型直接返回
        return obj;
      }
      var o = this.isArray(obj) ? [] : {};
      for(let i in obj) {
        if(obj.hasOwnProperty(i)){
          o[i] = typeof obj[i] === "object" ? this.deepClone(obj[i]) : obj[i];
        }
      }
      return o;
    },
    assign(to,from,clear=false,justSelf=false){//数据合并
      if(this.isObj(clear)){
        justSelf = clear.justSelf || justSelf;
        clear = clear.clear || false;
      }
      if(clear)Object.keys(to).forEach(key=>to[key]=null);
      let aim = justSelf?to:from;
      Object.keys(aim).forEach(key=>{
        let f = from[key];
        if(justSelf && (typeof(f)=='undefined' || f===null)){
          return;
        }
        to[key]= f
      });
    },
    clear(obj){//对象全部置空
      Object.keys(obj).forEach(key=>obj[key]=null);
    },
    defaultVals (to,defaultData){//为空的值取默认值
      console.log(to,defaultData)
      Object.keys(defaultData).forEach((key)=>{
        let v = to[key];
        console.log(key,v);
        if(v==='' || typeof(v)=='undefined' || v=== null){
          to[key] = defaultData[key];
        }
      });
    },
    deepMerge(target = {}, source = {}){//深度合并
      target = this.deepClone(target);
      if (typeof target !== 'object' || typeof source !== 'object') return false;
      for (var prop in source) {
        if (!source.hasOwnProperty(prop)) continue;
        if (prop in target) {
          if (typeof target[prop] !== 'object') {
            target[prop] = source[prop];
          } else {
            if (typeof source[prop] !== 'object') {
              target[prop] = source[prop];
            } else {
              if (target[prop].concat && source[prop].concat) {
                target[prop] = target[prop].concat(source[prop]);
              } else {
                target[prop] = this.deepMerge(target[prop], source[prop]);
              }
            }
          }
        } else {
          target[prop] = source[prop];
        }
      }
      return target;
    },
    debounce(func, wait){//防抖
      let timeout;
      return function () {
        let arg=arguments;
        let context=this;
        clearTimeout(timeout);
        timeout = setTimeout(func.bind(context,arg),wait);
      }
    },
    fmtDate (date=new Date(),format="yyyy-MM-dd HH:mm:ss") {//格式化日期
      if(!date){return ''};
      let str = date.toString();
      str = str.replace(/-/g, "/");
      if(/^([1-9]\d*)$/.test(str)){str = str*1};//时间戳处理
      date = new Date(str);
      const o = {
        "M+": date.getMonth() + 1, //month
        "d+": date.getDate(), //day
        "h+": date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, //hour
        "H+": date.getHours(), //hour
        "m+": date.getMinutes(), //minute
        "s+": date.getSeconds(), //second
        "q+": Math.floor((date.getMonth() + 3) / 3), //quarter
        "S": date.getMilliseconds() //millisecond
      };
      const week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
      };
      if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
      }

      if (/(E+)/.test(format)) {
        format = format.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
      }
      for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
          format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
      }
      return format;
    },
    getIdcardInfo (psidno){//身份证获取信息函数
      let birthdayno,birthdaytemp,sex,sexno,sextemp;
      if(psidno.length==18){
        birthdayno=psidno.substring(6,14);
        sexno=psidno.substring(16,17);
      }else if(psidno.length==15){
        birthdaytemp=psidno.substring(6,12);
        birthdayno="19"+birthdaytemp;
        sexno=psidno.substring(14,15);
      }else{
        window.console&&console.log("错误的身份证号码，请核对！");
        return false
      }

      let birY = birthdayno.substring(0,4);
      let birM = birthdayno.substring(4,6);
      let birD = birthdayno.substring(6,8);
      let birthday=birY+"-"+birM+"-"+birD;

      let now = new Date();

      now =  this.fmtDate(now,'yyyy-MM-dd');
      // window.console && console.log(now);
      now = now.split(' ')[0].split('-');
      let age = (now[0]-birY)*100+(now[1]-birM)/12*10*10+(now[2]-birD)/30*10;
      age = Math.floor(age / 100);
      age = age <= 0?1:age;

      sextemp = sexno%2;
      sex = (sextemp==0)?'2':'1';

      return {birthday,age,sex};
    },
    getLocalTime :function (nS) {// 转时间戳为本地时间，nS为时间戳字符串，错误格式无法返回
      return new Date(nS*1).toLocaleString().replace(/年|月/g, "-").replace(/日/g," ");
    },
    toHour: function (v) {//转换成小时，返回整数小时
      v = v * 1 || 0;
      return Math.ceil((v * 100) / (1000 * 60 * 60)) / 100;
    },
    toDay: function (v) {//转换成天，返回整数小时
      v = v * 1 || 0;
      return Math.ceil((v * 100) / (1000 * 60 * 60 * 24)) / 100;
    },
    getTimeLong : function (s) {//将秒转化成倒计时的时分秒
      var h = Math.floor(s/3600);
      h = h>9?h:('0'+h);
      var m = Math.floor(s%3600/60);
      m = m>9?m:('0'+m);
      var s = s%3600%60;
      s = s>9?s:('0'+s);
      return h==0?(m+':'+s):(h+':'+m+':'+s);
    },
    getMonthDay : function (year, month) {//获取月
      var days = new Date(year, month, 0).getDate();
      return days;
    },
    getAgeByBir : function (date,monthAndDay) {//根据日期返回年龄，返回满岁，小于1岁算1岁;monthAndDay 为true时，小于1岁返回 月 和 天
      var me = this;
      if(date==='')return '';
      var sD = $.fmtDate('yyyy-MM-dd', date).split('-');
      var nD = $.fmtDate('yyyy-MM-dd').split('-');
      var age = nD[0] - sD[0] - 1;//年相减
      var m,d,bm;
      if(sD[1] < nD[1] || sD[1] == nD[1] && sD[2] <= nD[2]){//(生日 月小于当前月  || 月相同时，生日 日 小于等于当前日)+1岁
        age++;
      }
      if(monthAndDay){
        age = {type:1,value:age};//默认返回多少岁,type:1
        if(age.value == 0){
          m = (nD[0] - sD[0])*12 + (nD[1] - sD[1]);//年相减*12 + 月相减
          d = nD[2] - sD[2];//日相减
          bm = me.getMonthDay(sD[0],sD[1]);//获取生日当前月
          if(d<0){//如果 生日 日 小于当前日期 日，则月-1,并且算出日差
            m --;
            d = bm + d;
          }
          //小于一岁，返回 M月D天，并且type:2
          age.type = 2;
          age.value = (m==0?'':(m+ '月')) + d + '天';
        }
        // console.log(m,'月',d,'天');
      }else{
        age = age <= 0?1:age;//小于1岁算1岁
      }
      return age;
    },
    mul : function (a, b) {//修正版乘法
      var c = 0,
        d = a.toString(),
        e = b.toString();
      try {
        c += d.split(".")[1].length;
      } catch (f) {}
      try {
        c += e.split(".")[1].length;
      } catch (f) {}
      return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
    },
    add : function (a,b) {//修正版加法
      var me = this;
      var c, d, e;
      try {
        c = a.toString().split(".")[1].length;
      } catch (f) {
        c = 0;
      }
      try {
        d = b.toString().split(".")[1].length;
      } catch (f) {
        d = 0;
      }
      return e = Math.pow(10, Math.max(c, d)), (me.mul(a, e) + me.mul(b, e)) / e;
    },
    sub : function (a,b) {//修正版减法
      var me = this;
      var c, d, e;
      try {
        c = a.toString().split(".")[1].length;
      } catch (f) {
        c = 0;
      }
      try {
        d = b.toString().split(".")[1].length;
      } catch (f) {
        d = 0;
      }
      return e = Math.pow(10, Math.max(c, d)), (me.mul(a, e) - me.mul(b, e)) / e;
    },
    div : function (a,b) {//修正版除法
      var me = this;
      return me.mul(a,100000)/me.mul(b,100000);
      // var c, d, e = 0,
      //     f = 0;
      // try {
      //     e = (a.toString().split(".")[1]).length;
      // } catch (g) {}
      // try {
      //     f = b.toString().split(".")[1].length;
      // } catch (g) {}
      // window.console && console.log(e,f,a.toString().replace(".", ""),b.toString().replace(".", ""));
      // return c = Number(a.toString().replace(".", "")), d = Number(b.toString().replace(".", "")), me.mul(c / d, Math.pow(10, f - e));
    },
    valid(obj,arr,fail){
      for (let i = 0; i <arr.length; i++) {
        let vl=obj[arr[i].prop];
        if(vl===''||vl===null||vl===undefined){
          if(fail)fail(arr[i]);
          let msg=arr[i].msg;
          if(msg){
            console.log('error',msg);
          }
          return false;
        }
      }
      return true;
    },
    parseAddr(addr){
      if(!addr)return null;
      let rst=[];
      addr=addr.replace(/\s+/g,"");
      let arr=addr.split(/,|，/);
      rst[0]=arr[0];
      if(arr.length>2){
        m=arr[2].match(/.+?(省|市|自治区|自治州|县|区)/g);
        if(m){
          let tt=m[0]+"/"+(m[1]||'')+"/"+(m[2]||'');
          rst[2]=tt;
        }
      }
      let m=addr.match(/1[0-9]{10}/);
      if(m){
        rst[1]=m[0];
      }
      return rst;
    }
  };

  return tools;

})

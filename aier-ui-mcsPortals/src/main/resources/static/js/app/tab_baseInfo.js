//院内提醒 页面逻辑
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit"], function($e) {
    /**
     * 依赖数据
     * b
     *
     */
    // ---------------------  初始化 基本组件功能  --------------------------------------

    var b=null;
    var url; // b.base
    var setVal = function () {
        // 处理内部依赖的赋值
        if(b) {
        }
    }

    var init = function (domId,outData) {
         if(outData) b =  outData;
        // 由于较多 服务器组件 无法注入 只能抽离js
        // var actionUrl = b.base +  "/ui/outp/patientInfo/updateById";
        // // 注入模板
        // $(domId).append(html);
        // $(domId).find(".form-userInfo").attr("action",actionUrl);
        setVal();
        // setTimeout(function () {
        //
        // }, 200);

   // --------------------------   组件内部业务逻辑  -------------------------------------------
        function cardAddValidate() {
            var $cardNo = $('#cardNo');
            var cardType = $('#cardType').combobox('getValue');
            if (cardType === '1') {
                $cardNo.validatebox('addRule', 'idCardNo[""]');
            } else {
                $cardNo.validatebox('removeRule', 'idCardNo[""]');
                // $cardNo.validatebox('disableValidation');
            };
        }

        // 身份证选项 动态验证规则 初始化后 设置
        setTimeout(function () {
            $('#cardType').combobox('setValue', '1');
            //cardAddValidate();
        }, 100);



   // ------------------------  end-------- -----------------------------------------
    };




    // -----------------------------  外部方法   -------------------------------------------------
    // 重载表单
    function formLoadData(data) {
        if(data.birthday){ data.birthday = data.birthday.split(' ')[0];}
        $('.form-userInfo').form('load',data);
        if(data.birthday) {
            var birthday =  data.birthday.substring(0,10);
            $("#age").val($T.getAgeByBir(birthday));
            $("#birthday").val(birthday);
        }
        if(data.provinceName){
        	$('#itemId').combogrid('setValue', {id:'justEmpty',aName:data.provinceName + "-" + data.cityName + "-"+data.districtName});
        }
        // $('#name').trigger('blur');
        //data.birthday


        b.patientId = data.id; // 检查是否是外部引用

    }
    // 获取表单数据  替换 $('.form-userInfo').sovals
    function getFormData () {
        return  $('.form-userInfo').sovals();
    }
    function formValidate () {
        return $(".form-userInfo").form('validate');
    }
    window.beforeSendPInfo = function () {
        var pid = b.patientId //$('#pid').val();
        if (!pid) {
            $pop.alert('该患者未建档，请先建档发卡！');
        } else {
            return true;
        }
    }
    window.afterSendPInfo = function (rst) {
        if (rst.code == '200' || rst.code == '201') {
            $('#pid').val(rst.data.id);
            b.patientId = rst.data.id;
            hasPatId(); // 重复依赖
        }
    }



    var refresh = function (b) {
        b = b;
        setVal();

    }
    // 清除PID
    var reset = function () {
        $('.form-userInfo').form('clear');
        $('#pid').val('');
        // 特定组件选中默认值
        var data1=  $("#visit_identity").combobox("getData");
        $("#visit_identity").combobox("setValue",data1[1].valueCode);
        var data2 =  $("#cardType").combobox("getData");
        $("#cardType").combobox("setValue",data2[1].valueCode);
        var data3 =  $("#country").combobox("getData");
        $("#country").combobox("setValue",data3[1].valueCode);
        var data4 =  $("#nation").combobox("getData");
        $("#nation").combobox("setValue",data4[1].valueCode);
        var data5 =  $("#cost_type").combobox("getData");
        $("#cost_type").combobox("setValue",data5[0].valueCode);

    }

    var readyOnly = function () {
        // name tel1  cardType cardNo  sex
        // $("#name").attr("readonly","readonly").addClass("unedit");
        // 对于身份证发卡后要求把 电话号码解开 新需求
       // $("#tel1").attr("readonly","readonly").addClass("unedit");
        // $("#cardType").combobox('disable'); // .attr("disabled",true).addClass("unedit");
        // $("#cardNo").attr("disabled",true).addClass("unedit");
        $("#sex").combobox('readonly',true); //.attr("disabled",true).addClass("unedit");
    }

    var formEditing = function () {
        // $("#name").removeAttr("readonly").removeClass("unedit");
       // $("#tel1").removeAttr("readonly").removeClass("unedit");
        // $("#cardType").combobox('enable');
        // $("#cardNo").removeAttr("disabled").removeClass("unedit");
        $("#sex").combobox('readonly',false);
    }





   function cardAddValidate() {
        var $cardNo = $('#cardNo');
        var cardType = $('#cardType').combobox('getValue');
        if (cardType === '1') {
            $cardNo.validatebox('addRule', 'idCardNo[""]');
        } else {
            $cardNo.validatebox('removeRule', 'idCardNo[""]');
            // $cardNo.validatebox('disableValidation');
        };
   }


    // ------------------------------  组件内部事件  ------------------------------------------
    $('#birthday').blur(function(){//根据生日计算年龄
        var bir = $(this).val();
        $('#age').val($T.getAgeByBir(bir));
    });

    var oAge = null;
    $('#age').focus(function(){
      oAge = $(this).val();
    }).blur(function(){
        // window.console && console.log(oAge);
        var newAge = $(this).val();
        if(oAge!=newAge){
          //不处理1岁以内的患者，1岁以内从birthday日期控件中选
          if($(this).hasClass("validatebox-invalid")){
            return;
          }
          var age = $(this).val();

          var date = new Date(sysNowTimeB);
          var seperator1 = "-";

          var month = date.getMonth() + 1;
          var strDate = date.getDate();
          if(month >= 1 && month <= 9) {
            month = "0" + month;	}
          if(strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
          }
          var end = date.getFullYear() - age  + seperator1 + month + seperator1 + strDate;

          $('#birthday').val(end);
        }
    });

    $('#cardNo').blur(function () {
        var val = $(this).val();
        // window.console && console.log(val);
        if($('#cardType').val() == 1 ){
            isIdCardNo(val,"birthday,sex,age");
        }
    });


    $('#cardType').combobox({
        onChange: function (newV, oldV) {
            cardAddValidate();
            $("#cardNo").val("");  // 福哥确认需要清空 ，后续导致无法读取
            // if(newV == 1) {
            //     var val = $("#cardNo").val();
            //     isIdCardNo(val,"birthday,sex,age");
            // }
        }
    });


    // 清空按钮无
    // $('.btn-clearUserF').click(function () {
    //     $('.form-userInfo').form('reset');
    //     $('#cardType').combobox('setValue', '1');
    //     // 保留 内部清空按钮 保留 pid
    //     $('#pid').val(b.patientId);
    // });

    return {
        init : init,
        refresh : refresh,
        setFormData :formLoadData,
        getFormData :getFormData,
        formValidate :formValidate,
        reset : reset,
        formReadyOnly : readyOnly,
        formEditing : formEditing


    }
});

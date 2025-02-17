//院内提醒 页面逻辑
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit",'text!./template/tab_medicalSurance.html'], function($e,html) {
    /**
     * 依赖数据
     * b
     */
    // ---------------------  初始化 基本组件功能  --------------------------------------

    var b=null;
    var init = function (domId,outData) {
        b = outData;
        // 注入模板
        $(domId).append(html);


        $('.btn-updateInsurance').click(function () {//医保卡更新操作
            $ajax.post(b.base + '/ui/outp/patientMedicalInsure/readMedicareCard').done(function (rst) {
                if (rst.code === '200') {
                    $('.form-insurance').form('load', rst.data);
                }
            })
        });


    };
    var refresh = function (b) {
        b = b;

    };
    var reset = function () {
        $('.form-insurance').form('clear');
    }

    return {
        init : init,
        refresh : refresh,
        reset : reset
    }
});
//院内提醒 页面逻辑
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit",'text!./template/tab_prepayGrid.html'], function($e,html) {
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

        // #grid-l-money 初始化
        // 依赖 useage
        $grid.newGrid("#grid-l-money", {
            fit: true,
            pagination: false,
            // fitColumns : false,
            columns: [[
                {title: '交易时间', field: 'transTime', width: 80},
                {title: '交易类型', field: 'transChangeTypeName', width: 80},
                {title: '交易单号', field: 'transNumber', width: 80},
                {title: '交易场景', field: 'transSceneName', width: 80},
                {title: '交易金额', field: 'transAmount', width: 80},
                {title: '余额', field: 'transBalance', width: 80}
                //{title: '创建医院', field: 'hospId', width: 80}
            ]],
            onLoadSuccess: function () {
            },
            onBeforeLoad: function (param) {
                if (!param.patientId) {
                    return false;
                }
            },
            url: b.base + '/ui/trans/prepayment/getByPatientId',
            height: 'auto'
            // offset :0
        });

    };
    var refresh = function (b) {
        b = b;
        $('#grid-l-money').datagrid('reload', {patientId: b.patientId});
    }

    return {
        init : init,
        refresh : refresh
    }
});
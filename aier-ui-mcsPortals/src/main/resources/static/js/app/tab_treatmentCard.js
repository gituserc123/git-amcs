// 就诊卡 tab_treatmentCard.js
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit",'text!./template/tab_treatmentCard.html'], function($e,html) {
    /**
     * 依赖数据
     * b
     * windowHeight 向外部 传递依赖
     */
        // ---------------------  初始化 基本组件功能  --------------------------------------

    var b=null;
    var kinsfolkAppelArr;
    var certTypeArr;
    var contactEditInitData = {};
    var setVal = function () {
        if(b) {
            kinsfolkAppelArr = b.kinsfolkAppelArr;
            certTypeArr = b.certTypeArr;
            contactEditInitData.patientId =  b.patientId;
        }
    }

    var init = function (domId,outData,gridOpt) {
        b = outData;
        // 注入模板
        $(domId).append(html);
        setVal();

        var defaultOpt = {
            fit: true,
            pagination: false,
            // fitColumns : false,
            columns: [[
                {title: 'id', field: 'id', hidden: true},
                {title: '患者ID', field: 'patientId', hidden: true},
                {title:'操作' ,field:'opt', width:80 ,formatter: function (value,row,index) {
                    return '<span class="s-op op-lock icon-lock" title="挂失" style="font-size:16px;"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="s-op s-op-run op-unlock icon-unlock_alt" style="font-size:16px;top:1px;" title="解挂"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="s-op op-refund icon-credit_card" title="退卡"></span>';
                }}, // , hidden : b.pagePath !== "cardMgr"
                {title: '类型', field: 'cardTypeName', width: 80},
                {title: '卡号', field: 'cardNumber', width: 80},
                {title: '押金', field: 'cardDeposit', width: 80},
                {title: '状态', field: 'cardStateName', width: 80},
                {title: 'cardState', field: 'cardState', width: 80, hidden: true},
                //{title: '发卡机构', field: 'hospName', width: 80},
                {title: '发卡时间', field: 'issuingDate', width: 140},
                {title: '发卡人', field: 'issuingOperatorName', width: 80},
                {title: '备注', field: 'remarks', width: 80}
            ]],
            onBeforeLoad: function (param) {
                if (!param.patientId) {
                    return false;
                }
            },
            onLoadSuccess: function (data) {
                window.windowHeight = data.windowHeight;
            },
            url: b.base + '/ui/outp/patientCard/list',
            height: 'auto'
            // ,offset :-55
        };
        if(gridOpt) $.extend(defaultOpt, gridOpt);
        $grid.newGrid("#grid-l-card",defaultOpt);

    };
    var refresh = function (b) {
        b = b;
        setVal();
        $('#grid-l-card').datagrid('reload', {patientId: b.patientId});

    }

    return {
        init : init,
        refresh : refresh
    }
});

//院内提醒 页面逻辑
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit",'text!./template/tab_baseExamine.html'], function($e,html) {
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
        }
    }

    var init = function (domId,outData,gridOpt) {
        b = outData;
        // 注入模板
        $(domId).append(html);
        setVal();
        var defaultOpt ={
            fitParent: true,
            pagination : false,
            fitColumns : false,
            fit: true,
            columns:[[
                {title:'操作',field:'op',width:70, rowspan :2 ,formatter: function (v,row,index) {
                    return '<span class="s-op s-op-del icon-del" title="删除"></span>';
                }},
                {title:'记录时间', align :"center",field:'modifyDate' ,rowspan :2 ,align :'center' ,width:140  },
                {title:'远视力',  field:'id3', colspan:4, align :'center' },
                {title:'近视力',  field:'id2', colspan:2, align :'center',width:100},
                {title:'矫正视力',  field:'id4', colspan:2, align :'center' },
                {title:'眼压mmHg',field:'id5',  colspan:2,align :'center' },
                {title:'',field:'id6',  colspan:4,align :'center',hidden:true }
            ],[
                {title:'左眼',field:'osDistVision',width:70 },
                {title:'说明',field:'osDistVisionRemark',width:100 },
                {title:'右眼',field:'odDistVision',width:70 },
                {title:'说明',field:'odDistVisionRemark',width:100 },

                {title:'左眼',field:'osNearVision',width:70 },
                {title:'右眼',field:'odNearVision',width:70 },

                {title:'左眼',field:'osCorrVision',width:70},
                {title:'右眼',field:'odCorrVision',width:70},
                {title:'左眼',field:'osIop',width:70},
                {title:'右眼',field:'odIop',width:70},
                {title:'id',field:'id',hidden:true},
                {title:'patientId',field:'patientId',hidden:true},
                {title:'patientName',field:'patientName',hidden:true},
                {title:'regNumber',field:'regNumber',hidden:true}
            ]],

            onDblClickRow : function (index,row) {
            },
            onBeforeLoad : function(data){
                // debugger;
                if(!b.patientId){
                    return false;
                }
            },
            onSelect : function (i,row) {
                // todo 暂不知道在哪 被调用
                $("#examination-form").form('load',row);
            },
            onLoadSuccess:function(data){
            },
            // queryParams : {
            //     patientId :  $('#pid').val()
            // },
            url:  b.base + '/ui/outp/nurse/getPhysicExamListByPatientId',
            offset :-78
        };
        if(gridOpt) $.extend(defaultOpt ,gridOpt);
        $grid.newGrid("#examination-grid",defaultOpt);


    };
    var refresh = function (b) {
        b = b;
        setVal();
        $('#examination-grid').datagrid('reload', {patientId: b.patientId});

    }

    return {
        init : init,
        refresh : refresh
    }
});

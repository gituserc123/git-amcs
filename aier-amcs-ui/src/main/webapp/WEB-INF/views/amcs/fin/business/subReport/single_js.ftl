    //------------------------------------------单病种single 耗材信息初始化------------------------------------------
    $grid.newGrid("#singleCons", {
        pagination: false,
        fitColumns: false,
        tools: [
            [
                [#if status==1 || status==9]
                {
                    iconCls: 'plus',//图标
                    text: '单病种耗材',//文字
                }
                [/#if]
            ]
        ],
        columns: [[
            {title: 'id', field: 'id', hidden: true},
            {
                title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                    var chkHtml = '';
                    [#if status==1 || status==9]
                    chkHtml += '<span rel="' + row.id + '" relIndex="' + index  + '" class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
                    [/#if]
                    return chkHtml;
                }
            },
            {title: '耗材名称', field: 'consumables', width: 200},
            {title: '最大值', field: 'consumablesMax', width: 300},
            {title: '最小值', field: 'consumablesMin', width: 200},
            {title: '创建人', field: 'creatorName', width: 100},
            {title: '创建时间', field: 'createDate', width: 200},
            {title: '最后修改时间', field: 'modifyDate', width: 200}
        ]],
        rowStyler: function (index, row) {

        },
        queryParams : {
            mainId:'${mainId}',
            hospId:'${hospId}',
            consumablesType:1
        },
        onBeforeLoad: function (param) {
            return true;
        },
        onLoadSuccess: function (data) {

        },
        url: '${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/getAllCons',
        height: 200,
        offset: -5
    });

    $("span:contains('单病种耗材')").click(function ($e) {
        var varSingle;
        $ajax.postSync('${base}/ui/service/biz/amcs/fin/finInsSingleDisease/getByMainId',{mainId:'${mainId}'},false,false).done(function (rst) {
            varSingle = rst;
        });
        if(!varSingle.id){
            $pop.alert('请先填写单病种信息！');
            return false;
        }
        var pSingleConsNew = $pop.popTemForm({
            title: "新增耗材",
            temId: 'editSingleCons',
            area: ['800px', '300px'],
            temData: {},
            zIndex: 2,
            grid: '#singleCons',
            onPop: function ($p) {
                $('form .singleCons').form('reset');
                $(".btn-cancel-cus-singleCons").click(function (){
                    $pop.close(pSingleConsNew)
                });
                $('.btnSubmitSingleCons').click(function () {
                    if ($('#singleConsForm').form('validate')) {
                        var data = $(".singleCons").sovals();
                        $ajax.post({
                            url: "${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save",
                            data: data,
                            tip:'是否保存？',
                            calltip: true
                        }).done(function (rst) {
                            $pop.closePop({popIndex: pSingleConsNew});
                        });
                    };
                });
            },
            end: function () {
                $grid.reload('#singleCons');
            },
            beforeSubmit: function (opt, $form, formData) {
                return true;
            },
            sureback : function (iframeSendData){
            }
        },'#singleCons');
    });

    $('.cont-grid-single-cons').on('click', '.s-op-edit', function () {
        var id = $(this).attr('rel');
        var idx = $(this).attr('relIndex');
        var rowData = $("#singleCons").datagrid("getRows")[idx];
        var pSingleConsEdit = $pop.popTemForm({
            title: "编辑耗材",
            temId: 'editSingleCons',
            area: ['800px', '200px'],
            temData: rowData,
            data: rowData,
            zIndex: 2,
            grid: '#singleCons',
            onPop: function ($p) {
                $('#singleDiseaseConImportDiv').hide();
                $(".btn-cancel-cus-singleCons").click(function (){
                    $pop.close(pSingleConsEdit)
                });
                $('.btnSubmitSingleCons').click(function () {
                    if ($('#singleConsForm').form('validate')) {
                        var data = $(".singleCons").sovals();
                        $ajax.post({
                            url: "${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save",
                            data: data,
                            tip:'是否保存？',
                            calltip: true
                        }).done(function (rst) {
                            $pop.closePop({popIndex: pSingleConsEdit});
                        });
                    };
                });
            },
            end: function () {
                $grid.reload('#singleCons');
            },
            beforeSubmit: function (opt, $form, formData) {
                return true;
            },
            afterSubmit : function (iframeSendData){
            }
        });
    });
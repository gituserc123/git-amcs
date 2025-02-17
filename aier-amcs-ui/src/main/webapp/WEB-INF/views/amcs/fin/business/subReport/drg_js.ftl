    //------------------------------------------DRG grid初始化------------------------------------------
    // 当前已行编辑记录
    var rowEditIndexArr = [];
    $grid.newGrid("#gridDrgAnalysisBox", {
        pagination: true,
        fitColumns: false,
        tools: [
            [
                [#if status==1 || status==9]
                {
                    iconCls: 'plus',//图标
                    text: '新增DRG盈亏',//文字
                },
                {
                    iconCls: 'plus',//图标
                    text: '导入历史DRG盈亏',//文字
                },
                {
                    iconCls: 'save',
                    text: '保存',
                    click: function () {
                        saveDrgGrid('保存修改');
                    }
                },
                [/#if]
                {
                    iconCls: 'play22',//图标
                    text: 'DRG导出',//文字
                }
            ]
        ],
        columns: [[
            {title: 'id', field: 'id', hidden: true},
            {
                title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                    var chkHtml = '';
                    [#if status==1 || status==9]
                    chkHtml += '<span rel="' + row.id + '" relIndex="' + index + '" class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
                    chkHtml += '&nbsp;&nbsp;<span rel="' + row.id + '" relindex="' + index + '" class="s-op s-op-del icon-del" title="删除" aier="del"></span>';
                    [/#if]
                    return chkHtml;
                }
            },
            {title:'医保类型Id',field:'insurancePlanCategory',width:80,editor:{type:'readonly'},hidden:true},
            {title: '病种', field: 'diseaseType', width: 100},
            {title: '分组编码', field: 'groupCode', width: 100},
            {title: '分组名称', field: 'groupName', width: 150},
            {title: '医保类型', field: 'poolingAreaText', width: 100,
                editor:{
                      type: 'combobox', options: {
                      multiple:false,
                      valueField: 'text',
                      textField: 'text',
                      limitToList: true,
                      required: true,
                      url:'${base}/ui/service/biz/amcs/fin/finHospSetting/getSelectList',
                      onSelect: function (row) {
                            $e.setCellVals({'insurancePlanCategory':row.id}, "#gridDrgAnalysisBox");
                         }
                      }
                }
            },
            {
                title: '年度总病例数', field: 'totalCases', width: 100, editor: {
                    type: 'numberbox', options: {
                        min: 0, precision: 0, required: true, missingMessage: '请填写年度总病例数',
                        onChange: ((v) => {
                            // 例均实际住院费用编辑器
                            var actualHospitalizationCostCell = $e.getCellVals(['actualHospitalizationCost'], '#gridDrgAnalysisBox');
                            $e.setCellVals({'avgActualCost': actualHospitalizationCostCell.actualHospitalizationCost / v}, "#gridDrgAnalysisBox");
                            // 例均预计DRG结算医疗费用编辑器
                            var expectedSettlementCostCell = $e.getCellVals(['expectedSettlementCost'], '#gridDrgAnalysisBox');
                            $e.setCellVals({'avgExpectedSettlement': expectedSettlementCostCell.expectedSettlementCost / v}, "#gridDrgAnalysisBox");
                            // 例均预计节余/超支金额
                            var expectedBalanceAmountCostCell = $e.getCellVals(['expectedBalanceAmount'], '#gridDrgAnalysisBox');
                            $e.setCellVals({'avgExpectedBalanceAmount': expectedBalanceAmountCostCell.expectedBalanceAmount / v}, "#gridDrgAnalysisBox");
                        })
                    }
                }
            },
            {
                title: '实际住院总费用', field: 'actualHospitalizationCost', width: 100, editor: {
                    type: 'numberbox', options: {
                        min: 0, precision: 2, required: true, missingMessage: '请填写实际住院总费用',
                        onChange: ((v) => {
                            var totalCasesCell = $e.getCellVals(['totalCases'], '#gridDrgAnalysisBox');
                            $e.setCellVals({'avgActualCost': v / totalCasesCell.totalCases}, "#gridDrgAnalysisBox");
                        })
                    }
                }
            },
            {
                title: '预计DRG结算医疗总费用', field: 'expectedSettlementCost', width: 150, editor: {
                    type: 'numberbox', options: {
                        min: 0, precision: 2, required: true, missingMessage: '请填写预计DRG结算医疗总费用',
                        onChange: ((v) => {
                            var totalCasesCell = $e.getCellVals(['totalCases'], '#gridDrgAnalysisBox');
                            $e.setCellVals({'avgExpectedSettlement': v / totalCasesCell.totalCases}, "#gridDrgAnalysisBox");
                        })
                    }
                }
            },
            {
                title: '预计节余/超支总金额',
                field: 'expectedBalanceAmount',
                width: 125,
                editor: {
                    type: 'numberbox',
                    options: {precision: 2, required: true, missingMessage: '请填写预计节余/超支总金额',
                        onChange: ((v) => {
                            var totalCasesCell = $e.getCellVals(['totalCases'], '#gridDrgAnalysisBox');
                            $e.setCellVals({'avgExpectedBalanceAmount': v / totalCasesCell.totalCases}, "#gridDrgAnalysisBox");
                        })
                    }
                }
            },
            {
                title: '例均实际住院费用',
                field: 'avgActualCost',
                width: 105,
                editor: {
                    type: 'numberbox',
                    options: {min: 0, precision: 2, required: true, missingMessage: '请填写例均实际住院费用'}
                }
            },
            {
                title: '例均预计DRG结算医疗费用',
                field: 'avgExpectedSettlement',
                width: 160,
                editor: {
                    type: 'numberbox',
                    options: {min: 0, precision: 2, required: true, missingMessage: '请填写例均预计DRG结算医疗费用'}
                }
            },
            {
                title: '例均预计节余/超支金额',
                field: 'avgExpectedBalanceAmount',
                width: 150,
                editor: {
                    type: 'numberbox',
                    options: {precision: 2, required: true, missingMessage: '请填写例均预计节余/超支金额'}
                }
            },
            {
                title: '盈亏情况分析',
                field: 'profitLossAnalysis',
                width: 300,
                editor: {type: 'textbox', options: {required: true, missingMessage: '请填写盈亏情况分析'}}
            },
            {title: '创建人', field: 'creatorName', width: 100},
            {title: '创建时间', field: 'createDate', width: 200},
            {title: '最后修改时间', field: 'modifyDate', width: 200}
        ]],
        rowStyler: function (index, row) {
            if (row.usingSign === 0) {
                return 'color:red';
            }
        },
        queryParams: {
            monthId: '${monthId}',
            hospId: '${hospId}'
        },
        onBeforeLoad: function (param) {
            return true;
        },
        onClickCell: function (index, field, value) {
            editingIndex = index;
            $e.editRow({
                grid: '#gridDrgAnalysisBox',
                index: index,
                cellField: field,
                focusField: 'itemName',
                rowCanEdit: function (row) {
                    return true;
                },
                onEndEdit: function (index) {
                    if (rowEditIndexArr.indexOf(index) == -1) {
                        rowEditIndexArr.push(index);
                    }
                }
            });
        },
        onLoadSuccess: function (data) {

        },
        url: '${base}/ui/service/biz/amcs/fin/finDrgInfo/getAllDrgAnalysis',
        height: 380,
        offset: -5
    });

    $grid.newGrid("#drgCons", {
        pagination: false,
        fitColumns: false,
        tools: [
            [
                [#if status==1 || status==9]
                {
                    iconCls: 'plus',//图标
                    text: 'DRG耗材',//文字
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
                    chkHtml += '<span rel="' + row.id + '" relIndex="' + index + '" class="s-op s-op-edit  icon-edit" title="编辑" aier="edit" ></span>';
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
        queryParams: {
            mainId: '${mainId}',
            hospId: '${hospId}',
            consumablesType: 3
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

    $("span:contains('新增DRG盈亏')").click(function ($e) {
        var varDrg;
        $ajax.postSync('${base}/ui/service/biz/amcs/fin/finDrgInfo/getByMainId', {mainId: '${mainId}'}, false, false).done(function (rst) {
            varDrg = rst;
        });
        if (!varDrg.id) {
            $pop.alert('请先填写DRG付费信息！');
            return false;
        }
        var pDrgNew = $pop.popTemForm({
            title: "新增盈亏分析",
            temId: 'editDrgAnalysis',
            area: ['1000px', '650px'],
            temData: {},
            zIndex: 2,
            grid: '#gridDrgAnalysisBox',
            onPop: function ($p) {
                $('form .drgAnalysis').form('reset');
                $(".btn-cancel-cus-drg").click(function () {
                    $pop.close(pDrgNew)
                });
                $('.btnSubmitDrg').click(function () {
                    if ($('#drgForm').form('validate')) {
                        var data = $(".drgAnalysis").sovals();
                        $ajax.post({
                            url: "${base}/ui/service/biz/amcs/fin/finDrgInfo/saveDrgAnalysis",
                            data: data,
                            tip: "是否保存？",
                            calltip: true
                        }).done(function (rst) {
                            $pop.closePop({popIndex: pDrgNew});
                        });
                    }
                    ;
                });
                $('#drgAvgActualCost').numberbox('readonly', true);
                $('#drgAvgExpectedSettlement').numberbox('readonly', true);
                $('#drgAvgExpectedBalanceAmount').numberbox('readonly', true);
            },
            end: function () {
                $grid.reload('#gridDrgAnalysisBox');
            },
            beforeSubmit: function (opt, $form, formData) {
                return true;
            },
            sureback: function (iframeSendData) {
                //表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
                //$grid.reload('#gridDrgAnalysisBox');
                //$pop.closePop({refreshGrid:true})
            }
        }, '#gridDrgAnalysisBox');
    });

    $("span:contains('导入历史DRG盈亏')").click(function ($e) {
        var varDrg;
        $ajax.postSync('${base}/ui/service/biz/amcs/fin/finDrgInfo/getByMainId', {mainId: '${mainId}'}, false, false).done(function (rst) {
            varDrg = rst;
        });
        if (!varDrg.id) {
            $pop.alert('请先填写DRG付费信息！');
            return false;
        }
        var drgAnalysisImportClickPop = $pop.popTemForm({
            title: '查看历史填报',
            temId: 'drgAnalysisImportLastList',
            data: null,
            area: ['1200px', '600px'],
            onPop: function () {
                $grid.newGrid('#drgAnalysisImportGridBox', {
                    fitColumns: true,
                    pagination: false,
                    height: '560px',
                    tools:
                        [
                            [{
                                iconCls: 'plus', text: '一键导入上次填报', click: function () {
                                    $pop.confirm('您确认想要一键导入上次填报记录吗？', function (r) {
                                        if (r) {
                                            $ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/saveLastCommitData', {monthId:'${monthId}',mainId:'${mainId}'}, false, true).done(function (rst) {
                                                if (rst.code === '200' || rst.code === '201') {
                                                    $pop.close(drgAnalysisImportClickPop);
                                                    $grid.reload('#gridDrgAnalysisBox');
                                                } else {
                                                    $pop.warning('导入异常!');
                                                };
                                            });
                                        }
                                    });
                                }
                            }]
                        ],
                    columns: [
                        [
                            {
                                title: '操作',
                                field: 'op',
                                width: 90,
                                formatter: function (v, row, index) {
                                    htmlStr = '<span rel="' + row.id + '" relIndex="' + index + '" class="s-op drgAnalysisImport icon-arrow-left" title="导入" aier="edit" ></span>';
                                    return htmlStr;
                                }
                            },
                            {title: 'Id', field: 'id', hidden: true, width: 100},
                            {title: '病种', field: 'diseaseType', width: 200},
                            {title: '统筹区', field: 'poolingAreaText', width: 300},
                            {title: '分组编码', field: 'groupCode', width: 150},
                            {title: '分组名称', field: 'groupName', width: 180},
                            {title: '年度总病例数', field: 'totalCases', width: 100},
                            {title: '实际住院总费用', field: 'actualHospitalizationCost', width: 150},
                            {title: '预计DRG结算医疗总费用', field: 'expectedSettlementCost', width: 150},
                            {title: '预计节余/超支总金额', field: 'expectedBalanceAmount', width: 125},
                            {title: '例均实际住院费用', field: 'avgActualCost', width: 105},
                            {title: '例均预计DRG结算医疗费用', field: 'avgExpectedSettlement', width: 160},
                            {title: '例均预计节余/超支金额', field: 'avgExpectedBalanceAmount', width: 150},
                            {title: '盈亏情况分析', field: 'profitLossAnalysis', width: 300},
                            {title: '创建人', field: 'creatorName', width: 100},
                            {title: '创建时间', field: 'createDate', width: 200},
                            {title: '最后修改时间', field: 'modifyDate', width: 200}
                        ]
                    ],
                    onLoadSuccess: function (data) {
                        $('.drgAnalysisImport').click(function () {
                            var ix = $(this).attr('relIndex');
                            var row = data.rows[ix];
                            $ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/getFinInsDrgAnalysis', {id: row.id}).then(
                                (data) => {
                                    //不能导入的字段
                                    delete data.id;
                                    delete data.mainId;
                                    delete data.monthId;
                                    data.mainId = '${mainId}';
                                    data.monthId = '${monthId}';
                                    $ajax.post({
                                        url: "${base}/ui/service/biz/amcs/fin/finDrgInfo/saveDrgAnalysis",
                                        data: data,
                                        calltip: false
                                    }).done(function (rst) {
                                    });
                                }
                            )
                            $pop.close(drgAnalysisImportClickPop);
                            $grid.reload('#gridDrgAnalysisBox');
                        });
                    },
                    url: '${base}/ui/service/biz/amcs/fin/finDrgInfo/lastAnalysisList',
                })
            }
        });
    });

    $('.cont-grid-drganalysis').on('click', '.s-op-edit', function () {
        var id = $(this).attr('rel');
        var idx = $(this).attr('relIndex');
        var rowData = $("#gridDrgAnalysisBox").datagrid("getRows")[idx];
        var pDrgEdit = $pop.popTemForm({
            title: "编辑盈亏分析",
            temId: 'editDrgAnalysis',
            area: ['1000px', '600px'],
            temData: rowData,
            data: rowData,
            zIndex: 2,
            grid: '#gridDrgAnalysisBox',
            onPop: function ($p) {
                $('#drgAnalysisImportDiv').hide();
                $(".btn-cancel-cus-drg").click(function () {
                    $pop.close(pDrgEdit)
                });
                $('.btnSubmitDrg').click(function () {
                    if ($('#drgForm').form('validate')) {
                        var data = $(".drgAnalysis").sovals();
                        data.monthId = rowData.monthId;
                        data.mainId = rowData.mainId;
                        data.hospId = rowData.hospId;
                        $ajax.post({
                            url: "${base}/ui/service/biz/amcs/fin/finDrgInfo/saveDrgAnalysis",
                            data: data,
                            tip: "是否保存？",
                            calltip: true
                        }).done(function (rst) {
                            $pop.closePop({popIndex: pDrgEdit});
                        });
                    }
                    ;
                });
                $('#insurancePlanCategory').combobox('setValue', rowData.insurancePlanCategory);
                $('#drgAvgActualCost').numberbox('readonly', true);
                $('#drgAvgExpectedSettlement').numberbox('readonly', true);
                $('#drgAvgExpectedBalanceAmount').numberbox('readonly', true);
            },
            end: function () {
                $grid.reload('#gridDrgAnalysisBox');
            },
            beforeSubmit: function (opt, $form, formData) {
                return true;
            },
            afterSubmit: function (iframeSendData) {
                //表单提交| 或成功 执行函数，子页面可通过 $pop.closePop 返回参数
                //$grid.reload('#gridDrgAnalysisBox');
                //console.log("afterSubmit!!");
                //$pop.closePop({refreshGrid:true,popIndex:pDrgEdit});
            }
        });
    });

    $('.cont-grid-drganalysis').on('click', '.s-op-del', function () {
        var id = $(this).attr('rel');
        var idx = $(this).attr('relIndex');
        var rowData = $("#gridDrgAnalysisBox").datagrid("getRows")[idx];
        console.log("afterSubmit!!  " + rowData.id);
        $pop.confirm('是否删除该记录？', function () {//确定
            $ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/del', rowData).done(function (rst) {
                $grid.reload('#gridDrgAnalysisBox');
            });
            return true;
        }, function () {//取消
            return true;
        });
    });

    $("span:contains('DRG耗材')").click(function ($e) {
        var varDrg;
        $ajax.postSync('${base}/ui/service/biz/amcs/fin/finDrgInfo/getByMainId', {mainId: '${mainId}'}, false, false).done(function (rst) {
            varDrg = rst;
        });
        if (!varDrg.id) {
            $pop.alert('请先填写DRG付费信息！');
            return false;
        }
        var pDrgConsNew = $pop.popTemForm({
            title: "新增DRG耗材",
            temId: 'editDrgCons',
            area: ['800px', '300px'],
            temData: {},
            zIndex: 2,
            grid: '#drgCons',
            onPop: function ($p) {
                $('form .drgCons').form('reset');
                $(".btn-cancel-cus-drgCons").click(function () {
                    $pop.close(pDrgConsNew)
                });
                $('.btnSubmitDrgCons').click(function () {
                    if ($('#drgConsForm').form('validate')) {
                        var data = $(".drgCons").sovals();
                        $ajax.post({
                            url: "${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save",
                            data: data,
                            tip: '是否保存？',
                            calltip: true
                        }).done(function (rst) {
                            $pop.closePop({popIndex: pDrgConsNew});
                        });
                    }
                    ;
                });
            },
            end: function () {
                $grid.reload('#drgCons');
            },
            beforeSubmit: function (opt, $form, formData) {
                return true;
            },
            sureback: function (iframeSendData) {
            }
        }, '#drgCons');
    });

    $('.cont-grid-drg-cons').on('click', '.s-op-edit', function () {
        var id = $(this).attr('rel');
        var idx = $(this).attr('relIndex');
        var rowData = $("#drgCons").datagrid("getRows")[idx];
        var pDrgConsEdit = $pop.popTemForm({
            title: "编辑DRG耗材",
            temId: 'editDrgCons',
            area: ['800px', '200px'],
            temData: rowData,
            data: rowData,
            zIndex: 2,
            grid: '#drgCons',
            onPop: function ($p) {
                $('#drgAnalysisConImportDiv').hide();
                $(".btn-cancel-cus-drgCons").click(function () {
                    $pop.close(pDrgConsEdit)
                });
                $('.btnSubmitDrgCons').click(function () {
                    if ($('#drgConsForm').form('validate')) {
                        var data = $(".drgCons").sovals();
                        $ajax.post({
                            url: "${base}/ui/service/biz/amcs/fin/finInsConsumablesInfo/save",
                            data: data,
                            tip: '是否保存？',
                            calltip: true
                        }).done(function (rst) {
                            $pop.closePop({popIndex: pDrgConsEdit});
                        });
                    }
                    ;
                });
            },
            end: function () {
                $grid.reload('#drgCons');
            },
            beforeSubmit: function (opt, $form, formData) {
                return true;
            },
            afterSubmit: function (iframeSendData) {
            }
        });
    });

    [#include "/WEB-INF/views/amcs/fin/business/subReport/finExport_js.ftl"]

    $("span:contains('DRG导出')").click(function ($e) {
        $pop.confirm('您确认想要导出DRG分析记录吗？', function (r) {
            if (r) {
                var url = '${base}/ui/service/biz/amcs/fin/finDrgInfo/drgAnalysisExportExcel?monthId=${monthId}&hospId=${hospId}';
                downloadExcelProcess(url);
            }
        });
    });

    function saveDrgGrid() {
        if (rowEditIndexArr.length == 0) {
            $pop.msg("找不到需要保存的信息！");
            return;
        } else {
            let rows = $("#gridDrgAnalysisBox").datagrid('getRows');
            let selRows = new Array();
            $.each(rowEditIndexArr, function (index, value) {
                selRows.push(rows[value]);
            });
            $ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/saveDrgAnalysisList', JSON.stringify(selRows), false, true).done(function (rst) {
                if (rst.code === '200' || rst.code === '201') {
                    rowEditIndexArr = [];
                    $pop.msg('保存成功！');
                } else {
                    $pop.warning('保存异常!');
                }
                ;
            });
        }
    };
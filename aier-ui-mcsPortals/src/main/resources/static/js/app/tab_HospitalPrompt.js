//院内提醒 页面逻辑
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit",'text!./template/tab_HospitalPrompt.html'], function($e,html) {
    /**
     * 依赖数据
     * specialInfoTypeD
     * b.patientId 双工
     */
    // ---------------------  初始化 基本组件功能  --------------------------------------
    // 院内提醒 grid


    var b=null;
    var promptEditInitData;
    var specialInfoTypeD,specialInfoD;
    var setVal = function () {
        if(!b) return;

        promptEditInitData = {
            patientId: b.patientId,
            hospital: '长沙爱尔眼科医院',
            inputer: '王天'
        };
        specialInfoTypeD = b.specialInfoTypeD;
        specialInfoD = b.specialInfoD;


    }

    var init = function (domId,outData) {
        b = outData;

        setVal();
        // 注入模板
        $(domId).append(html);

        $grid.newGrid("#grid-l-prompt", {
            fit: true,
            pagination: false,
            // fitColumns : false,
            columns: [[
                {title: 'id', field: 'id', width: 50, hidden: true},
                {title: 'modifer', field: 'modifer', width: 50, hidden: true},
                {title: 'hospId', field: 'hospId', width: 50, hidden: true},
                {
                    title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                    return '<span class="s-op s-op-del icon-del" title="删除"></span>'
                    //'<span class="s-op s-op-up icon-arrow-up" title="向上"></span> <span class="s-op s-op-down icon-arrow-down" title="向下"></span>'
                }
                },{
                    title: '提示类型', field: 'specialInfoType', width: 120, formatter: function (v, row, index) {
                        var rval = '';
                        $.each(specialInfoTypeD, function () {
                            if (this.valueCode == v) {
                                rval = this.valueDesc;
                                return false;
                            }
                        });
                        return rval;
                    }, editor: {
                        type: 'combobox', options: {
                            valueField: 'valueCode',
                            textField: 'valueDesc',
                            limitToList: true,
                            reversed: true,
                            data: specialInfoTypeD,
                            //url : b.base + '/ui/sys/dict/getListForComb?tag=special_info_type',
                            missingMessage: '请选择提示类型',
                            required: true
                        }
                    }
                },{
                    title: '提示内容', field: 'specialInfo', width: 120, formatter: function (v, row, index) {
                        var rval = '';
                        $.each(specialInfoD, function () {
                            if (this.valueCode == v) {
                                rval = this.valueDesc;
                                return false;
                            }
                        });
                        return rval;
                    }, editor: {
                        type: 'combobox', options: {
                            valueField: 'valueCode',
                            textField: 'valueDesc',
                            limitToList: true,
                            reversed: true,
                            data: specialInfoD,
                            //url : b.base + '/ui/sys/dict/getListForComb?tag=special_info_type',
                            missingMessage: '请选择提示内容',
                            required: true
                        }
                    }
                },
                {title: '备注', field: 'remarks', width: 200, editor: {type: 'validatebox' , options: { validType:'maxlength[50]'  } }},
                {title: '录入医院', field: 'hospName', width: 120},
                {title: '录入人', field: 'modiferName', width: 80},
                {title: '录入时间', field: 'modifyDate', width: 110}
            ]],
            onClickCell: function (index, field, value) {
                promptEditInitData.patientId = b.patientId;
                $e.editRow({
                    grid: '#grid-l-prompt',
                    index: index,
                    cellField: field,
                    focusField: 'content',
                    canAdd: true,
                    initData: promptEditInitData
                });
            },
            onBeforeLoad: function (param) {
                if (!param.patientId) {
                    return false;
                }
            },
            onLoadSuccess: function () {
                var  rowsData = $('#grid-l-prompt').datagrid('getRows');
                if(rowsData.length > 0) {
                    showTabTip();
                }else {
                    hideTabTip();
                }
            },
            url: b.base + '/ui/outp/patientSpecialWarn/list',
            height: 'auto'
            // ,offset :-55
        });
        // 院内信息 新增
        $('.btn-prompt-add').click(function () {
            // promptEditInitData.patientId = b.patientId;
            $e.addNewRow({
                grid: '#grid-l-prompt',
                focusField: 'content',
                initData: promptEditInitData
            });
        });
        // 院内信息 删除
        $('.userPromptGridBox').on('click', '.s-op-del', function () {
            //删除单条
            var row = $('#grid-l-prompt').datagrid('getSelected');
            var ix = $('#grid-l-prompt').datagrid('getRowIndex', row);
            if (row.id) {
                $ajax.post(b.base + '/ui/outp/patientSpecialWarn/delete', {id: row.id}, '你确定删除此记录吗？').done(function (rst) {
                    // window.console && console.log(rst);
                    if (rst.code === '200' || rst.code === '201') {
                        $('#grid-l-prompt').datagrid('deleteRow', ix);
                    };
                });
            } else {
                $('#grid-l-prompt').datagrid('deleteRow', ix);
            }
            ;
            return false;
        });
        //院内信息  保存
        $('.btn-prompt-save').click(function () {
            $e.ifEndEdit(function () {//判断是否可以结束编辑状态
                var updateData = $('#grid-l-prompt').datagrid('getChanges', 'updated');//获取grid编辑的行值
                var insertData = $('#grid-l-prompt').datagrid('getChanges', 'inserted');//获取grid插入的值
                var changetData = updateData.concat(insertData);
                // window.console && console.log(updateData, insertData, changetData);
                if (changetData.length) {//只获取变化值，回传changetData
                    $ajax.post(b.base + '/ui/outp/patientSpecialWarn/save', JSON.stringify(changetData), true, true).done(function (rst) {
                        // window.console && console.log(rst);
                        if (rst.code === '200' || rst.code === '201') {
                            $('#grid-l-prompt').datagrid('reload');
                        }
                        ;
                    });
                } else {
                    $pop.msg('请填写院内提示！');
                };
            }, '#grid-l-prompt');
        });

    };
    var refresh = function (b) {
        b = b;
        setVal();
        //1 觸發院内提示检查
        $('#grid-l-prompt').datagrid('reload', {patientId: b.patientId});

    }



    function showTabTip () {
        $(".icon-exclamation").removeClass("hide");
    }
    function hideTabTip () {
        $(".icon-exclamation").addClass("hide");
    }


    return {
        init : init,
        refresh : refresh
    }
});
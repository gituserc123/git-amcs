//院内提醒 页面逻辑
// $('#target').html(require("text!目标按钮对应的页面.html"));
define(["easygridEdit",'text!./template/tab_userContact.html'], function($e,html) {
    /**
     * 依赖数据
     * b
     * kinsfolkAppelArr
     * certTypeArr
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
    // var btnControle = function () {
    //     if(b.patientId) {
    //         $('.btn-contact-save').
    //     }else {
    //
    //     }
    //
    // }
    var init = function (domId,outData) {
        b = outData;
        // 注入模板
        $(domId).append(html);
        setVal();

        $grid.newGrid("#grid-l-contact", {
            fit: true,
            //checkOnSelect : false,
            //selectOnCheck : false,
            //singleSelect : false,
            //ctrlSelect : true,
            pagination: false,
            fitColumns: false,
            columns: [[
                {title: 'id', field: 'id', width: 50, hidden: true},
                {title: 'creator', field: 'creator', width: 50, hidden: true},
                {
                    title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                    return '<span class="s-op s-op-del icon-del" title="删除"></span>'
                    //'<span class="s-op s-op-up icon-arrow-up" title="向上"></span> <span class="s-op s-op-down icon-arrow-down" title="向下"></span>'
                }
                },
                {title: '患者', field: 'patientId', width: 50, hidden: true},
                {
                    title: '患者关系', field: 'relation', width: 150, formatter: function (v, row, index) {

                    var rval = '';
                    $.each(kinsfolkAppelArr, function () {
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
                        data: kinsfolkAppelArr,
                        //url : b.base + '/ui/sys/dict/getList?paraType=relation',
                        missingMessage: '请选择患者关系',
                        required: true
                    }
                }
                },
                {title: '联系人姓名', field: 'name', width: 80, editor: {type: 'validatebox', options: {required: true,validType:'maxlength[30]'}}},
                {
                    title: '证件类型', field: 'certType', width: 110, formatter: function (v, row, index) {
                    var rval = '';
                    $.each(certTypeArr, function () {
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
                        data: certTypeArr,
                        //url : b.base + '/ui/sys/dict/getList?paraType=document_type',
                        onSelect: function (record) {
                            var $code = $e.getTxt($(this), 'certNumber');
                            if (record.valueCode == '1') {
                                $code.validatebox('addRule', 'idCardNo[""]');
                            } else {
                                $code.validatebox('removeRule', 'idCardNo[""]');
                            };
                        },
                        missingMessage: '请选择证件类型',
                        required: true
                    }
                }
                },
                {title: '证件号码', field: 'certNumber', width: 150, editor: {type: 'diy', options: {required: true,validType:'maxlength[25]'}}},
                {title: '联系电话', field: 'tel', width: 110, editor: {type: 'validatebox', options: {required: true,validType:'maxlength[15]'}}},
                {title: '登记人', field: 'creatorName', width: 80},
                {title: '登记时间', field: 'createDate', width: 130},
                {title: '备注', field: 'remarks', width: 320, align: 'left', titletip:true,  editor: {type: 'validatebox' , options: { validType:'maxlength[50]'  } }}
            ]],
            onClickCell: function (index, field, value) {
                contactEditInitData.patientId = b.patientId;
                $e.editRow({
                    grid: '#grid-l-contact',
                    index: index,
                    cellField: field,
                    focusField: 'kinsfolkAppel',
                    canAdd: true,
                    initData: contactEditInitData
                });
            },
            onBeforeLoad: function (param) {
                if (!param.patientId) {
                    return false;
                }
            },
            onLoadSuccess: function () {
            },
            url: b.base + '/ui/outp/patientContacts/list',
            height: 'auto'
            // ,offset :-55
        });

        // #grid-l-contact   联系人 保存按钮
        $('.btn-contact-save').click(function () {
            $e.ifEndEdit(function () {//判断是否可以结束编辑状态
                // var allData = $e.getGridData('#grid-l-contact');//获取grid所有行的值
                var updateData = $('#grid-l-contact').datagrid('getChanges', 'updated');//获取grid编辑的行值
                var insertData = $('#grid-l-contact').datagrid('getChanges', 'inserted');//获取grid插入的值
                var changetData = updateData.concat(insertData);
                if (changetData.length) {//只获取变化值，回传changetData
                    $ajax.post(b.base + '/ui/outp/patientContacts/save', JSON.stringify(changetData), true, true).done(function (rst) {
                        if (rst.code === '200' || rst.code === '201') {
                            $('#grid-l-contact').datagrid('reload');
                        };
                    });
                } else {
                    $pop.msg('请填写完整联系人信息！');
                };
            }, '#grid-l-contact');
        });

        //  #grid-l-contact   联系人 删除按钮
        $('.userContactGridBox').on('click', '.s-op-del', function () {//删除单条
            var row = $('#grid-l-contact').datagrid('getSelected');
            var ix = $('#grid-l-contact').datagrid('getRowIndex', row);
            if (row.id) {
                $ajax.post(b.base + '/ui/outp/patientContacts/delete', {id: row.id}, '你确定删除此记录吗？').done(function (rst) {
                    if (rst.code === '200' || rst.code === '201') {
                        $('#grid-l-contact').datagrid('deleteRow', ix);
                    };
                });
            } else {
                $('#grid-l-contact').datagrid('deleteRow', ix);
            }
            ;
            return false;
        });
        //  #grid-l-contact   联系人 新增按钮
        $('.btn-contact-add').click(function () {
            contactEditInitData.certType = 1;
            $e.addNewRow({
                grid: '#grid-l-contact',
                focusField: 'kinsfolkAppel',
                initData: contactEditInitData
            });
        });


    };
    var refresh = function (b) {
        b = b;
        setVal();
        $('#grid-l-contact').datagrid('reload', {patientId: b.patientId});

    }

    return {
        init : init,
        refresh : refresh
    }
});
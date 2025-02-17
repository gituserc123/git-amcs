<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>手术费用模板明细编辑 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
    </style>
</head>

<body>
<div class="wrapper">

    <form id="sbox" class="soform form-enter pad-t10 pad-b10 bob-line-sub">
        <input class="txt" type="hidden" name="id" value="${entity.id}" />
        <label class="lab-inline">规则名称：</label>
        <input class="txt txt-validate required unedit" type="text" name="templName" style="width:30%;" value="${entity.templName}" readonly />
        <input class="txt w-100" type="hidden" id="oprTypeName" name="oprTypeName" />
    </form>

    <div class="cont-grid">
        <div id="gridBox"></div>
    </div>

    <p class="row-btn center bot-line-sub">
        <input type="button" class="btn btn-primary btn-edit" name="btnSubmit" value="保 存" />
        <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
    </p>

</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">

    require(["easygridEdit","pub"],function($e) {

        var id = '${entity.id}';

        window.repeatValid = function(value){
            var rowsData = $('#gridBox').datagrid('getRows');
            var rowData = $e.getCellVals(['payItemId']);
            for(var i=0;i<rowsData.length;i++){
                if (i != rowData.index && rowData.payItemId === rowsData[i].payItemId) {
                    return {msg:'不能选择重复数据',result:false};
                }
            }
            return true;
        };

        $grid.newGrid("#gridBox",{
            tools:[
                [{iconCls:'plus_sign',text:'新增',title:'新增',click:function(){
                        $e.addNewRow({
                            focusField : 'payItemName',
                            rowCanEdit: function (row, index) {
                                if (row && row.usingSign == '0') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        });
                    }}
                ]],
            // fitParent: true,
            singleSelect:true,
            rownumbers : false,
            pagination : false,
            showFooter : true,
            columns:[[
                {title:'操作',field:'op',width:'60px',formatter:function (value,row,index) {
                    return '<span class="s-op s-op-del icon-del" title="删除" rel="'+index+'"></span>';
                    }},
                {title:'收费项目',field:'itemName',align:'left',width:'220px',editor:{type:'combogrid',options : {
                            clearIcon: true,
                            delay: 800,    	  // 输入时延迟查询毫秒数
                            mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
                            panelWidth: 500,
                            striped:true,
                            idField:'VALUE_DESC',
                            textField:'VALUE_DESC',
                            url:'${base}/ui/amcs/aps/metric/autoComplete/query',
                            required: true,
                            validType: 'fnValid["repeatValid"]',
                            queryParams: {
                                tag: 'inp.inpnurse.dchPayItem',
                                limit:50
                            },
                            columns:[[
                                {field: 'groupServiceCode', title: '集团计分项目编码', width:'280px'},
                                {field: 'groupServiceName', title: '集团计分项目名称', width:'280px'},
                                {field: 'financeCodeValue', title: '财务分类', width: 80},
                            ]],
                            filter: function(q, row){
                                return row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
                            },
                            onSelect: function(index, r){
                                var newData = {
                                    itemId : r.groupServiceCode,
                                    itemName : r.groupServiceName,
                                    financeCodeValue: r.financeCodeValue,
                                    brand: r.BRAND,
                                    price: r.PRICE,
                                    packingUnitCode : r.UNIT,
                                    packingUnitName : r.UNIT_NAME
                                };
                                $e.setCellVals(newData,'#gridBox');//更新需要同步的单元格值
                            }
                        }}},
                {title:'编码',field:'itemId',width:'100px',hidden:false,editor:{type:'readonly'}},
                {title:'财务分类',field:'financeCodeValue',width:'100px',editor:{type:'readonly'}},
                {title:'集团标准分值/比例',field:'quantity',width:'120px',editor:{type:'diy',options:{validType:'rangeNum[0, 99999, 2,true, false, ""]',required:true,missingMessage:'请填写集团标准分值/比例'}}},
                {title:'状态',field:'usingSign',hidden:true}
            ]],
            rowStyler: function (index, row) {
                if (row.usingSign == '0') {
                    return 'background-color:#eee;color:#999';
                }
            },
            onClickCell: function (index, field, value) {
                $e.editRow({
                    grid: '#gridBox',
                    index: index,
                    cellField: field,
                    focusField: 'payItemName',
                    rowCanEdit: function (row, index) {
                        if (row && row.usingSign == '0') {
                            return false;
                        } else {
                            return true;
                        }
                    },
                    canAdd: true
                });
            },
            onLoadSuccess : function (data) {
            },
            onBeforeLoad : function(param){
                if(!param.chargeTemplId) return;
            },
            queryParams:{
                chargeTemplId: id,
            },
            url: '${base}/ui/amcs/aps/metric/getRuleDetail',
            offset : -60
        });

        $('.cont-grid').on('click','.s-op-del',function () {
            var  $tr = $(this).parents('.datagrid-btable').find('tr.datagrid-row');
            var $thisTr = $(this).parents('tr.datagrid-row');
            var ix = $tr.index($thisTr);
            $('#gridBox').datagrid('deleteRow',ix);
            return false;
        });

        $('.btn-edit').click(function () {
            if($('#sbox').form('validate')&&$e.validateGrid()){
                var formData = $('#sbox').sovals();
                $('#gridBox').datagrid('acceptChanges');
                var data = $('#gridBox').datagrid('getData');
                formData.rows = data.rows;
                // window.console && console.log(formData);
                $ajax.postJson('${base}/ui/opr/oprChargeTempl/doDetailEdit',JSON.stringify(formData),"确定保存?").done(function (rst) {
                    if(rst.code==='200'){
                        $pop.closePop({refreshGrid:true});//请求成功，关闭弹窗，刷新父级详情grid
                    }
                });
            }
        });

    });
</script>
</body>

</html>
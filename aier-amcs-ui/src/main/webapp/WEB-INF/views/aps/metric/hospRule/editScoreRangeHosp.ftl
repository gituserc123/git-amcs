<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>规则明细区间维护</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
    </style>
</head>

<body>
<div class="wrapper">

    <form id="sbox" class="soform form-enter pad-t10 pad-b10 bob-line-sub">
        <label class="lab-inline">名称：</label>
        <input class="txt txt-validate required unedit" type="text" name="itemName" id="itemName" style="width:80%;" value="${entity.ruleName}" readonly />
    </form>

    <div class="cont-grid">
        <div id="gridBox"></div>
    </div>

    <p class="row-btn center bot-line-sub">
        <input type="button" class="btn btn-primary btn-edit" name="btnSubmit" value="确   定1" />
        <input type="button" class="btn btn-cancel" name="btnCancel" value="取  消" />
    </p>

</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">

    require(["easygridEdit","pub"],function($e) {
		var rowDataParent = $store.getPostData();
		var rangeJson = JSON.parse(rowDataParent.rangeJson)
    	$store.set("editScoreRange-rangeJson", rangeJson);
        var id = '${entity.id}';
        var lastSelectExt = {};
        $('#itemName').val(rowDataParent.itemName)
        
        var LAST_VALUE = 0;

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
        	data:rangeJson,
            tools:[
                [{iconCls:'plus_sign',text:'新增',title:'新增',click:function(){
	                var rowsData = $('#gridBox').datagrid('getRows');
	                var lastv = 0;
	                for(var i=0;i<rowsData.length;i++){
	                	LAST_VALUE = rowsData[i].maxRange
		            }
                		
                    $e.addNewRow({
                        focusField : 'maxRange',
                        rowCanEdit: function (row, index) {
                                return true;
                        },
                        initData: {
			                minRange:LAST_VALUE
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
                
                //{title:'序号',field:'rangeOrder',width:'40px',hidden:false,editor:{type:'readonly'}},
                {title:'最小值',field:'minRange',width:'80px',editor:{type:'diy',options:{validType:'rangeNum[0, 99999, 7,false, false, ""]',required:true,missingMessage:'请填写'}}},
                {title:'最大值(含)',field:'maxRange',width:'80px',editor:{type:'diy',options:{validType:'rangeNum[0, 99999, 7,false, false, ""]',required:true,missingMessage:'请填写'}}},
                {title:'分值/比例',field:'rangeScore',width:'120px',editor:{type:'diy',options:{validType:'rangeNum[0, 99999, 7,false, false, ""]',required:true,missingMessage:'请填写标准分值/比例'}}},
                {title:'状态',field:'detailId',hidden:true},
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
                    focusField: 'minRange',
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
                if(!param.id) return;
            },
            queryParams:{
                id: id,
            },
            //url: '${base}/ui/amcs/aps/rule/getRuleDetail',
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
                
                var rowsData = data.rows;
                var lastv = 0;
                for(var i=0;i<rowsData.length;i++){
                	if(lastv > rowsData[i].minRange){
                		$pop.alert('第'+(i+1)+'行区间的最小值有错误，请从小到大的顺序维护，且范围不得有交叉！');
	            		return false;
                	}else{
                		lastv = rowsData[i].minRange
                	}
                	
                	if(lastv > rowsData[i].maxRange){
                		$pop.alert('第'+(i+1)+'行区间最大值有错误，请从小到大的顺序维护，且范围不得有交叉！');
	            		return false;
                	}else{
                		lastv = rowsData[i].maxRange
                	}
	            }
                $store.set("editScoreRange-rangeJson", data.rows);
                formData.rds = data.rows;
                formData.ruleId = id;
                $pop.closePop({refreshGrid:false});
                //$ajax.postJson('${base}/ui/amcs/aps/rule/updateRuleDetail',JSON.stringify(formData),"确定保存?").done(function (rst) {
                  //  if(rst.code==='200'){
                        //$pop.closePop({refreshGrid:true});//请求成功，关闭弹窗，刷新父级详情grid
                  //  }
                //});
            }
        });

    });
</script>
</body>

</html>
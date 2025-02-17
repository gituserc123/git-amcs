<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>D("D", "手术分类")</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
    </style>
</head>

<body>
<div class="wrapper">

    <form id="sbox" class="soform form-enter pad-t10 pad-b10 bob-line-sub">
        <input class="txt" type="hidden" name="ruleId" value="${entity.id} />
        <label class="lab-inline">规则名称：</label>
        <input class="txt txt-validate required unedit" type="text" name="ruleName" style="width:15%;" value="${entity.ruleName}" readonly />
        <label class="lab-inline">版本：</label>
        <input class="txt txt-validate required unedit" type="text" name="ruleVersion" style="width:15%;" value="${entity.ruleVersion}" readonly />
        <label class="lab-inline">备注：</label>
        <input class="txt txt-validate required unedit" type="text" name="remarks" style="width:15%;" value="${entity.remarks}" readonly />
        <label class="lab-inline">计分拓展类型：</label>
        <input class="txt txt-validate required unedit" type="text" name="ruleExtTypeName" style="width:15%;" value="${entity.ruleExtTypeName}" readonly />
    </form>

    <div class="cont-grid">
        <div id="gridBox"></div>
    </div>

    <p class="row-btn center bot-line-sub">
        <input type="button" class="btn btn-primary btn-edit" name="btnSubmit" value="保 存" />
        <input type="button" class="btn btn-cancel" name="btnCancel" value="关  闭" />
    </p>


<script id="batchSet" type="text/html">
	    <form class="soform formA form-validate form-enter form-setFangStop pad-t25" action="" >
	        <div class="row">
	        	<div class="p6">
	                <div class="item-one">
	                 <label class="lab-item">手术等级：</label>
					[@ui_select uiShowDefault=false class="drop easyui-combobox" uiShowDefault=true uiShowDefaultDesc="全部" value="" id="oprLevel" name="oprLevel" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.OprLevelEnum" filterkey="" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]	                
					</div>
	            </div>
	        			        

	            <div class="p6">
	                <div class="item-one">
	                    <label class="lab-item">病种：</label>
					[@ui_select uiShowDefault=false class="drop easyui-combobox" uiShowDefault=true uiShowDefaultDesc="全部"  value="" id="oprIcd" name="oprIcd" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.OprIcdEnum" filterkey="" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200"/]	                
	                </div>
	            </div>
	        </div>

	        <div class="row">
	            <div class="p12">
	                <div class="item-one">
	                    <label class="lab-item">计分规则类型：</label>
					[@ui_select uiShowDefault=false class="drop easyui-combobox" uiShowDefault=true uiShowDefaultDesc="全部"  value="" id="ruleItemType" name="ruleItemType" style="width:100%;" tag="@amcs@com.aier.cloud.api.amcs.enums.RuleItemTypeEnum" filterkey="" dataOptions="limitToList:true,reversed:true,panelHeight:'auto',panelMaxHeight:200,
					onLoadSuccess : function(){
					},"/]	                
	                </div>
	            </div>
	        </div>
	        <div class="row">
	            <div class="p12">
	                <div class="item-one">
	                    <label class="lab-item">分值/比例：</label>
	                    <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:100%" name="itemScore" id="itemScore" value="0" data-options="min:0,max:99999,precision:6" />
	                </div>
	            </div>
	        </div>
	
	        <p class="row-btn center">
	            <input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" noclosepop="false" value="确定" />
	        </p>
		</form>
	</script>
</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">

    require(["easygridEdit","pub"],function($e) {

        var id = '${entity.id}';
        var lastSelectExt = {};

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
                    }},

                {iconCls:'retweet',text:'刷新配置',title:'新增',click:function(){
                	$ajax.post('${base}/ui/amcs/aps/ruleDetail/refreshGroupRule?id='+id,{},{jsonData:false,calltip:false, tip:"缺失配置的项目将刷新进当前规则，确定吗?"}).done(function(rst){
//     				$ajax.postJson('${base}/ui/amcs/aps/ruleDetail/refreshGroupRule?id='+id,{},"缺失配置的项目将刷新进当前规则，确定吗?").done(function(rst){
    					$pop.alert(rst.data.msg);
    					$grid.reload('#gridBox');
    				}); 
                }},

                {iconCls:'plus_sign',text:'批量设置分数',title:'批量设置分数',click:function(){
                      var popId = $pop.popTemForm({
						title: '批量设置分数',
				        temId : 'batchSet',
				        data : {},
				        area : ['500px','250px'],
				        grid : '#gridBox',
						beforeSubmit : function (opt,$form, data) {
						    var chkRows = $('#gridBox').datagrid('getRows');
		                    $.each(chkRows,function(i,v){
		                    	if(data.oprLevel == ''){
		                    		if(data.oprIcd == ''){
		                    			if(data.ruleItemType == ''){
			                    			v.itemScore = data.itemScore
			                    		}else if(data.ruleItemType == v.ruleItemType){
			                    			v.itemScore = data.itemScore
			                    		}
		                    		}else if(data.oprIcd == v.itemIcd){
		                    			if(data.ruleItemType == ''){
			                    			v.itemScore = data.itemScore
			                    		}else if(data.ruleItemType == v.ruleItemType){
			                    			v.itemScore = data.itemScore
			                    		}
		                    		}
		                    	}else if(v.itemLevel == data.oprLevel){
		                    		if(data.oprIcd == ''){
		                    			if(data.ruleItemType == ''){
			                    			v.itemScore = data.itemScore
			                    		}else if(data.ruleItemType == v.ruleItemType){
			                    			v.itemScore = data.itemScore
			                    		}
		                    		}else if(data.oprIcd == v.itemIcd){
		                    			if(data.ruleItemType == ''){
			                    			v.itemScore = data.itemScore
			                    		}else if(data.ruleItemType == v.ruleItemType){
			                    			v.itemScore = data.itemScore
			                    		}
		                    		}
				                }
			                });
			                
			                //需要这样先取出来 然后在设置回来，才能使得界面刷新  曦哥给的骚操作
						    var allRows = $('#gridBox').datagrid('getRows');
						    $('#gridBox').datagrid('loadData',allRows);
			                $pop.close(popId);
						}
				      });
                }}
                ]],
            // fitParent: true,
            singleSelect:true,
            rownumbers : false,
            pagination : false,
            showFooter : true,
            remoteSort: false,
            columns:[[
                {title:'操作',field:'op',width:'100px',formatter:function (value,row,index) {
                    return '<span class="s-op s-op-del icon-del" title="删除" rel="'+index+'"></span>'+
                '&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon icon-file_text_alt s-setting blue hand" title="编辑区间" rel="'+row.id+'"></span>';
                    }},
                {title:'计分项目名称',field:'itemName',align:'left',width:'220px',editor:{type:'combogrid',options : {
            				pagination : true,
                            clearIcon: true,
                            delay: 800,    	  // 输入时延迟查询毫秒数
                            mode: 'remote',   // 远程查询设置，不设置不会走远程获取查询数据
                            panelWidth: 560,
                            striped:true,
                            idField:'OPERATIONCLASSIFYCODE',
                            textField:'OPERATIONCLASSIFYCODE',
                            url:'${base}/ui/amcs/aps/metric/autoComplete/queryOpr',
                            required: true,
                            //validType: 'fnValid["repeatValid"]',
                            queryParams: {
                                tag: 'inp.inpnurse.dchPayItem',
                                limit:50
                            },
                            columns:[[
                                {field: 'OPERATIONCLASSIFY', title: '计分项目名称', width:'280px'},
                                {field: 'ID', title: '计分项目编码', width:'150px'},
                                {field: 'OPERATIONGRADE', title: '手术等级', width: '60px'},
                                {field: 'DISEASETYPENAME', title: '病种', width: '60px'},
                            ]],
                            filter: function(q, row){
                                return row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
                            },
                            onSelect: function(index, r){
                                var newData = {
                                    itemId : r.ID,
                                    itemCode : r.OPERATIONCLASSIFYCODE,
                                    itemName : r.OPERATIONCLASSIFY,
                                    itemLevel : r.OPERATIONGRADE,
                                    financeCodeValue: r.DISEASETYPENAME,
                                    itemIcd: r.DISEASETYPENAME,
                                    ruleId : '${entity.id}',
                                };
                                $e.setCellVals(newData,'#gridBox');//更新需要同步的单元格值
                            }
                        }}},
                {title:'计分项目编码',field:'itemCode',width:'130px',hidden:false,editor:{type:'readonly'}},
                {title:'手术等级',field:'itemLevel',width:'130px',editor:{type:'readonly'}},
                {title:'病种',field:'itemIcd',width:'130px',editor:{type:'readonly'}},
                {title:'计分规则类型',field:'ruleItemType',hidden:true,width:0,editor:{type:'readonly'}},
				{title:'计分规则类型',field:'ruleItemTypeDesc',width:120,editor:{
                        type: 'combobox', options: {
                            valueField: 'valueDesc',
                            textField: 'valueDesc',
                            limitToList: true,
                            reversed: true,
                            panelWidth:120,
                            panelHeight:"auto",
                            panelMaxHeight:200,
                            data : [{valueCode:'A', valueDesc: "金额-固定比例"},{valueCode:'B', valueDesc: "金额-区间分值"},
                            {valueCode:'C', valueDesc: "金额-区间比例"},{valueCode:'D', valueDesc: "数量-固定分值"},{valueCode:'E', valueDesc: "数量-区间分值"},],
                            required: true,
							 missingMessage:'请选择',
                            onSelect : function (record){
                                var newData = {
                                    ruleItemType : record.valueCode
                                };
                                $e.setCellVals(newData);//更新需要同步的单元格值
                            },
                            onChange : function (newV,oldV){
                           		console.log(lastSelectExt)
                            }
                        }}},
                {title:'集团标准分值/比例',field:'itemScore',width:'120px',editor:{type:'diy',options:{validType:'rangeNum[0, 99999, 6,false, false, ""]',required:false,missingMessage:'请填写'}}},
                {title:'状态',field:'usingSign',hidden:true,width:0,editor:{type:'readonly'}},
                {title:'ruleId',field:'ruleId',hidden:true,width:0,editor:{type:'readonly'}},
                {title:'itemId',field:'itemId',hidden:true,width:0,editor:{type:'readonly'}},
                {title:'区间分值',field:'rangeJson',width:'250px',hidden:true,editor:{type:'readonly'}},
                {title:'区间分值',field:'rangeJsonShow',width:'250px',editor:{type:'readonly'},
                	formatter: function(val,row,index){
	                	if(!row.rangeJson){row.rangeJson = "[]"};
                		var rangeRows = JSON.parse(row.rangeJson);
	                	var rangeString = row.rangeJson;
	                	var rangeJsonShow = "";
                	    for(var i = 0, len = rangeRows.length; i < len; i ++){
					        rangeJsonShow += '[' + rangeRows[i].minRange + '~' + rangeRows[i].maxRange + ']=' +  rangeRows[i].rangeScore + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
					    }
					    row.rangeJsonShow = rangeJsonShow;
              			return '<span class="red">'+rangeJsonShow+'</span>';
           			},
                
                },
            ]],
            rowStyler: function (index, row) {
                if (row.usingSign == '0') {
                    return 'background-color:#eee;color:#999';
                }
            },
            onBeginEdit: function (rowIndex, rowData) {
            	lastSelectExt = rowData;
            },
            onClickCell: function (index, field, value) {
            	if(field === 'op'){
            		return;
            	}
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
                if(!param.id) return;
            },
            queryParams:{
                id: id,
            },
            url: '${base}/ui/amcs/aps/rule/getRuleDetail',
            offset : -60
        });

        $('.cont-grid').on('click','.s-op-del',function () {
            var  $tr = $(this).parents('.datagrid-btable').find('tr.datagrid-row');
            var $thisTr = $(this).parents('tr.datagrid-row');
            var ix = $tr.index($thisTr);
            $('#gridBox').datagrid('deleteRow',ix);
            return false;
        });
        
        
        //编辑区间事件
        $('.cont-grid').on('click','.s-setting',function () {
            var  $tr = $(this).parents('.datagrid-btable').find('tr.datagrid-row');
            var $thisTr = $(this).parents('tr.datagrid-row');
            var ix = $tr.index($thisTr);
            var reportDetailsRows = $('#gridBox').datagrid('getRows');
            var rowData = reportDetailsRows[ix]
            if(rowData.ruleItemType === 'B' || rowData.ruleItemType === 'C' || rowData.ruleItemType === 'E'){
            	if(!rowData.rangeJson){rowData.rangeJson = "[]"}
                $pop.iframePop({
	            	postData: rowData,
	            	type:2,
	                title: '区间编辑',
	                content: '${base}/ui/amcs/aps/rule/page/editScoreRange',
	                area: ['600px', '400px'],
	                end:function(){
	                	var rangeRows = $store.get("editScoreRange-rangeJson");
	                	$store.set("editScoreRange-rangeJson",[]);
	                	var rangeString =JSON.stringify(rangeRows);
	                	$('#gridBox').datagrid('updateRow',{index:ix,row:{rangeJson:rangeString}});
	                }
	            }, ['']);
	        }else{
	        	$pop.alert("该项目积分规则为固定值，无需编辑区间");
	        }
            
            return false;
        });

        $('.btn-edit').click(function () {
            if($('#sbox').form('validate')&&$e.validateGrid()){
                var formData = $('#sbox').sovals();
                $('#gridBox').datagrid('acceptChanges');
                var data = $('#gridBox').datagrid('getData');
                formData.rds = data.rows;
                formData.ruleId = id;
                $ajax.postJson('${base}/ui/amcs/aps/rule/updateRuleDetail',JSON.stringify(formData),"确定保存?").done(function (rst) {
                    if(rst.code==='200'){
                        //$pop.closePop({refreshGrid:true});//请求成功，关闭弹窗，刷新父级详情grid
                    }
                });
            }
        });

    });
</script>
</body>

</html>
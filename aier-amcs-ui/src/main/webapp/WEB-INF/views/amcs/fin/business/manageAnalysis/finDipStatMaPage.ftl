<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>DIP统计 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
<!-- <div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <label class="lab-inline">应收医保款回款率：</label>
        <select class="drop w-100"  id="" name="receivableCollectionRateSelectValue">
            <option value="" ></option>
            <option value="1">大于等于95%</option>
            <option value="2">90%≤X<95%</option>
            <option value="3">80%≤X<90%</option>
            <option value="4">60%≤X<80%</option>
            <option value="5">60%以下</option>
        </select>
        <label class="lab-inline">DIP节余/超支：</label>
        <select class="drop w-100"  id="" name="dipBo">
            <option value="" ></option>
            <option value="1">正→结余</option>
            <option value="2">负→超支</option>
        </select>
        <label class="lab-inline">DRG节余/超支：</label>
        <select class="drop w-100"  id="" name="drgBo">
            <option value="" ></option>
            <option value="1">正→结余</option>
            <option value="2">负→超支</option>
        </select>
        <label class="lab-inline">按人头节余/超支：</label>
        <select class="drop w-100"  id="" name="perBo">
            <option value="" ></option>
            <option value="1">正→结余</option>
            <option value="2">负→超支</option>
        </select>
        <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
        <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
        <button type="button" class="btn btn-small btn-primary s-export-detail">导出明细</button>
    </form>
</div> -->
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {


    	$grid.newGrid("#gridBox",{
            pagination: true,
            fitColumns : false,
            rownumbers: true,
	    	columns:[[
                {title:'省区',field:'hospParent',width:80,rowspan:2},
                {title:'医院简称',field:'hospName',width:80,rowspan:2},
                {title:'医院类型',field:'ehrLevel',width:80,rowspan:2},
                {title:'医保结算方式',field:'settlementMethod',width:80,rowspan:2},
                {title:'卫健委定级',field:'healthCommissionLevelDesc',width:80,rowspan:2},
                {title:'医保结算等级',field:'insuranceSettlementLevelDesc',width:80,rowspan:2},
                {title:'政策是否明确',field:'mispClearFlag',width:80,rowspan:2,formatter(v,row,index){
                        if(v==1){return "是";}else if(v==2){return "否";}else{return "";}
                    }},
                {title:'年季',field:'maQuarter',width:85,rowspan:2,formatter(v,row,index){
                        if(v==1){return row.maYear+"第一季度";}else if(v==2){return row.maYear+"第二季度";}else if(v==3){return row.maYear+"第三季度";}else if(v==4){return row.maYear+"第四季度";}else{return "";}
                    }},
                {title:'医保类型',field:'insuranceType',width:80,rowspan:2,formatter(v,row,index){
                        if(v==1){return "职工";}else if(v==2){return "居民";}else{return "";}
                }},
                {title:'老年性白内障结算标准(元)',field:'f8',align:'center',width:180, colspan:8},
                {title:'翼状胬肉结算标准(元)',field:'f9',align:'center', width:180,colspan:8},
                {title:'眼底病结算标准(元)',field:'f10',align:'center', width:180,colspan:8}
            ],[
                {field:'',title:'诊断名称(不含飞白)',field:'diagName1',width:120},
                {field:'',title:'手术名称(不含飞白)',field:'operationName1',width:120},
                {field:'',title:'单眼(不含飞白)',field:'singleEye1',width:100},
                {field:'',title:'双眼(不含飞白)',field:'coupleEye1',width:100},
                {field:'',title:'诊断名称(含飞白)',field:'diagName2',width:120},
                {field:'',title:'手术名称(含飞白)',field:'operationName2',width:120},
                {field:'',title:'单眼(含飞白)',field:'singleEye2',width:100},
                {field:'',title:'双眼(含飞白)',field:'coupleEye2',width:100},
                {field:'',title:'诊断名称(不含羊膜移植)',field:'diagName3',width:120},
                {field:'',title:'手术名称(不含羊膜移植)',field:'operationName3',width:120},
                {field:'',title:'单眼(不含羊膜移植)',field:'singleEye3',width:100},
                {field:'',title:'双眼(不含羊膜移植)',field:'coupleEye3',width:100},
                {field:'',title:'诊断名称(含羊膜移植)',field:'diagName4',width:120},
                {field:'',title:'手术名称(含羊膜移植)',field:'operationName4',width:120},
                {field:'',title:'单眼(含羊膜移植)',field:'singleEye4',width:100},
                {field:'',title:'双眼(含羊膜移植)',field:'coupleEye4',width:100},
                {field:'',title:'诊断名称(眼底注药)',field:'diagName5',width:120},
                {field:'',title:'手术名称(眼底注药)',field:'operationName5',width:120},
                {field:'',title:'单眼(眼底注药)',field:'singleEye5',width:100},
                {field:'',title:'双眼(眼底注药)',field:'coupleEye5',width:100},
                {field:'',title:'诊断名称(眼底玻切)',field:'diagName6',width:120},
                {field:'',title:'手术名称(眼底玻切)',field:'operationName6',width:120},
                {field:'',title:'单眼(眼底玻切)',field:'singleEye6',width:100},
                {field:'',title:'双眼(眼底玻切)',field:'coupleEye6',width:100},
            ]],
            rowStyler : function(index, row) {

            },
		    onBeforeLoad: function(param){
               
            },
            onLoadSuccess:function(data){
                if (data.rows.length>0)
                {
                    //调用mergeCellsByField()合并单元格
                    mergeCellsByField("gridBox","hospParent,hospName,ehrLevel,settlementMethod,healthCommissionLevelDesc,insuranceSettlementLevelDesc,mispClearFlag,maQuarter");
                }
            },
      		url: '${base}/ui/amcs/fin/dip/ma/getDipAll',
      		offset : -50
  		});

        window.refreshGridbox = function(param){
            $grid.load('#gridBox', {maQuarter:param.maQuarter});
        };

    });


    /**
     * EasyUI DataGrid根据字段动态合并单元格
     * @param tableID 要合并table的id
     * @param colList 要合并的列,用逗号分隔(例如："name,department,office");
     */
    function mergeCellsByField(tableID, colList) {
        var ColArray = colList.split(",");
        var tTable = $('#' + tableID);
        var TableRowCnts = tTable.datagrid("getRows").length;

        // 扩展：包括 rownumbers 列在合并中
        tTable.datagrid('mergeCells', {
            index: 0,
            field: 'rownumbers',
            rowspan: TableRowCnts
        });

        for (var i = 0; i < ColArray.length; i++) {
            var mergeIndex = 0; // 记录当前合并的起始行
            while (mergeIndex < TableRowCnts) {
                // 每次合并2行，若表格剩余行数不足2行，则合并剩余的所有行
                var rowspan = (TableRowCnts - mergeIndex) > 2 ? 2 : TableRowCnts - mergeIndex;
                tTable.datagrid('mergeCells', {
                    index: mergeIndex,
                    field: ColArray[i],
                    rowspan: rowspan
                });
                mergeIndex += rowspan; // 更新下一次合并的起始行位置
            }
        }
    };

    function mergeCellsByField2(tableID, colList) {
        var ColArray = colList.split(",");
        var tTable = $('#' + tableID);
        var TableRowCnts = tTable.datagrid("getRows").length;

        for (var i = 0; i < ColArray.length; i++) { // 遍历所有指定的列
            var mergeIndex = 0; // 初始化合并起始行的索引
            while (mergeIndex < TableRowCnts - 1) { // 确保不超出行数范围
                tTable.datagrid('mergeCells', {
                    index: mergeIndex,
                    field: ColArray[i],
                    rowspan: 2 // 每次合并2行
                });
                mergeIndex += 2; // 更新下一次合并的起始行索引，跳过已合并的行
            }
        }
    };

    function mergeCellsByField1(tableID,colList){
        var ColArray = colList.split(",");
        var tTable = $('#'+tableID);
        var TableRowCnts=tTable.datagrid("getRows").length;
        var tmpA;
        var tmpB;
        var PerTxt = "";
        var CurTxt = "";
        var alertStr = "";
        for (j=ColArray.length-1;j>=0 ;j-- )
        {
            PerTxt="";
            tmpA=1;
            tmpB=0;

            for (i=0;i<=TableRowCnts ;i++ )
            {
                if (i==TableRowCnts)
                {
                    CurTxt="";
                }
                else
                {
                    CurTxt=tTable.datagrid("getRows")[i][ColArray[j]];
                }
                if (PerTxt==CurTxt)
                {
                    tmpA+=1;
                }
                else
                {
                    tmpB+=tmpA;
                    tTable.datagrid('mergeCells',{
                        index:i-tmpA,
                        field:ColArray[j],
                        rowspan:tmpA,
                        colspan:null
                    });
                    tmpA=1;
                }
                PerTxt=CurTxt;
            }
        }
    };

</script>
</html>
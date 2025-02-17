<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>汇总统计 - 爱尔医院</title>
    <style type="text/css">
        .datagrid-wrap.panel-body.panel-body-noheader {
            height: 400px !important;
        }
        .mar-b20 {
            margin-bottom: 3px !important;
        }
        .sideBarBox {
            width: 150px !important;
        }
        [#if empType==1]
        .mainContBox {
            left: 151px !important;
        }
        [/#if]
        [#if empType!=1]
        .mainContBox {
            left: 0px !important;
        }
        [/#if]
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
<div class="wrapper">
    [#if empType==1]
    <div class="sideBarBox">
        <div class="sideTreeC">
            <ul id="ul-moduleTree"></ul>
        </div>
    </div>
    [/#if]
    <div class="mainContBox">
        <h2 class="h2-title-a">
            <span class="s-title">DIP统计</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="cont-grid-dip">
            <div id="gridBoxDip"></div>
        </div>
        <h2 class="h2-title-a">
            <span class="s-title">DRG统计</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="cont-grid-drg">
            <div id="gridBoxDrg"></div>
        </div>
    </div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {

        var popW = ($(window).width()) + 'px';
        $(window).resize(function () {
            popW = ($(window).width()) + 'px';
        });
        [#if empType==1]
        $('#ul-moduleTree').tree({
            animate: true,
            lines: true,
            url: '${base}/ui/amcs/fin/ma/tab/tree',
            flatData: true,
            onClick: function (node) {
                var formData = $('#sbox').sovals();
                if(node.pid=='0'){
                    if(node.id=='99'){
                        formData.hospId=null;
                        formData.province=null;
                    }else{
                        formData.hospId=null;
                        formData.province=node.id;
                    }
                }else{
                    formData.hospId=node.hospId;
                    formData.province=null;
                }
                var maQuarter = parent.document.getElementById('maQuarter').value;
                $grid.load('#gridBoxDip', {province:node.id,settlementMethod:'DIP',provinceName:node.name,maQuarter:maQuarter});
                $grid.load('#gridBoxDrg', {province:node.id,settlementMethod:'DRG',provinceName:node.name,maQuarter:maQuarter});
            },
            onLoadSuccess:function(){
                $('#ul-moduleTree').tree('collapseAll');
            }
        });
        [/#if]

        $grid.newGrid("#gridBoxDip",{
            pagination: true,
            fitColumns : false,
            rownumbers: true,
            columns:[[
                {title:'省区',field:'hospParent',width:80,rowspan:2},
                {title:'医保结算方式',field:'settlementMethod',width:80,rowspan:2},
                {title:'卫健委定级',field:'healthCommissionLevel',width:80,rowspan:2},
                {title:'机构数量',field:'instCount',width:80,rowspan:2},
                {title:'医保类型',field:'insuranceType',width:80,rowspan:2},
                {title:'老年性白内障结算标准(元)',field:'f8',align:'center',width:180, colspan:5},
                {title:'飞秒白内障结算标准(元)',field:'f9',align:'center', width:180,colspan:5},
                {title:'翼状胬肉结算标准(元)',field:'f10',align:'center', width:180,colspan:5},
                {title:'羊膜移植胬肉结算标准(元)',field:'f11',align:'center', width:180,colspan:5},
                {title:'眼底注药结算标准(元)',field:'f12',align:'center', width:180,colspan:5},
                {title:'眼底玻切结算标准(元)',field:'f12',align:'center', width:180,colspan:5},
            ],[
                {title:'省区均值',field:'singleEyeAvg1',width:120},
                {title:'最高值',field:'singleEyeMax1',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName1',width:100},
                {title:'最低值',field:'singleEyeMin1',width:100},
                {title:'医院名称',field:'singleEyeMinHospName1',width:120},
                {title:'省区均值',field:'singleEyeAvg2',width:120},
                {title:'最高值',field:'singleEyeMax2',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName2',width:100},
                {title:'最低值',field:'singleEyeMin2',width:100},
                {title:'医院名称',field:'singleEyeMinHospName2',width:120},
                {title:'省区均值',field:'singleEyeAvg3',width:120},
                {title:'最高值',field:'singleEyeMax3',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName3',width:100},
                {title:'最低值',field:'singleEyeMin3',width:100},
                {title:'医院名称',field:'singleEyeMinHospName3',width:120},
                {title:'省区均值',field:'singleEyeAvg4',width:120},
                {title:'最高值',field:'singleEyeMax4',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName4',width:100},
                {title:'最低值',field:'singleEyeMin4',width:100},
                {title:'医院名称',field:'singleEyeMinHospName4',width:120},
                {title:'省区均值',field:'singleEyeAvg5',width:120},
                {title:'最高值',field:'singleEyeMax5',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName5',width:100},
                {title:'最低值',field:'singleEyeMin5',width:100},
                {title:'医院名称',field:'singleEyeMinHospName5',width:120},
                {title:'省区均值',field:'singleEyeAvg6',width:120},
                {title:'最高值',field:'singleEyeMax6',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName6',width:100},
                {title:'最低值',field:'singleEyeMin6',width:100},
                {title:'医院名称',field:'singleEyeMinHospName6',width:120},
            ]],
            rowStyler : function(index, row) {

            },
            onBeforeLoad: function(param){

            },
            queryParams: {settlementMethod: 'DIP'},
            onLoadSuccess:function(data){
                if (data.rows.length>0)
                {
                    //调用mergeCellsByField()合并单元格
                    mergeCellsByField1("gridBoxDip","hospParent,settlementMethod,healthCommissionLevel");
                    mergeCellsByField("gridBoxDip","instCount");
                }
            },
            url: '${base}/ui/amcs/fin/dip/ma/getStatDipAll',
            offset : -50
        });

        $grid.newGrid("#gridBoxDrg",{
            pagination: true,
            fitColumns : false,
            rownumbers: true,
            columns:[[
                {title:'省区',field:'hospParent',width:80,rowspan:2},
                {title:'医保结算方式',field:'settlementMethod',width:80,rowspan:2},
                {title:'卫健委定级',field:'healthCommissionLevel',width:80,rowspan:2},
                {title:'机构数量',field:'instCount',width:80,rowspan:2},
                {title:'医保类型',field:'insuranceType',width:80,rowspan:2},
                {title:'老年性白内障结算标准(元)',field:'f8',align:'center',width:180, colspan:5},
                {title:'翼状胬肉结算标准(元)',field:'f10',align:'center', width:180,colspan:5},
                {title:'眼底注药结算标准(元)',field:'f12',align:'center', width:180,colspan:5},
                {title:'眼底玻切结算标准(元)',field:'f12',align:'center', width:180,colspan:5},
            ],[
                {title:'省区均值',field:'singleEyeAvg1',width:120},
                {title:'最高值',field:'singleEyeMax1',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName1',width:100},
                {title:'最低值',field:'singleEyeMin1',width:100},
                {title:'医院名称',field:'singleEyeMinHospName1',width:120},
                {title:'省区均值',field:'singleEyeAvg2',width:120},
                {title:'最高值',field:'singleEyeMax2',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName2',width:100},
                {title:'最低值',field:'singleEyeMin2',width:100},
                {title:'医院名称',field:'singleEyeMinHospName2',width:120},
                {title:'省区均值',field:'singleEyeAvg3',width:120},
                {title:'最高值',field:'singleEyeMax3',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName3',width:100},
                {title:'最低值',field:'singleEyeMin3',width:100},
                {title:'医院名称',field:'singleEyeMinHospName3',width:120},
                {title:'省区均值',field:'singleEyeAvg4',width:120},
                {title:'最高值',field:'singleEyeMax4',width:120},
                {title:'医院名称',field:'singleEyeMaxHospName4',width:100},
                {title:'最低值',field:'singleEyeMin4',width:100},
                {title:'医院名称',field:'singleEyeMinHospName4',width:120},
            ]],
            rowStyler : function(index, row) {

            },
            onBeforeLoad: function(param){

            },
            queryParams: {settlementMethod: 'DRG'},
            onLoadSuccess:function(data){
                if (data.rows.length>0)
                {
                    //调用mergeCellsByField()合并单元格
                    mergeCellsByField1("gridBoxDrg","hospParent,settlementMethod,healthCommissionLevel");
                    mergeCellsByField("gridBoxDrg","instCount");
                }
            },
            url: '${base}/ui/amcs/fin/drg/ma/getStatDrgAll',
            offset : -50
        });

    });

    window.refreshGridbox = function(param){
        var checkNode = $('#ul-moduleTree').tree('getSelected');
        if(checkNode){
            $grid.load('#gridBoxDip', {province:checkNode.id,settlementMethod:'DIP',provinceName:checkNode.name,maQuarter:param.maQuarter});
            $grid.load('#gridBoxDrg', {province:checkNode.id,settlementMethod:'DRG',provinceName:checkNode.name,maQuarter:param.maQuarter});
        }else{
            $grid.load('#gridBoxDip', {settlementMethod:'DIP',maQuarter:param.maQuarter});
            $grid.load('#gridBoxDrg', {settlementMethod:'DRG',maQuarter:param.maQuarter});
        }
    };

    window.getModuleTreeSelected = function(){
        return $('#ul-moduleTree').tree('getSelected');
    };

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
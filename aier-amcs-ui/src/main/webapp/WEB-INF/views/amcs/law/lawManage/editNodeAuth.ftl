<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>节点授权</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.mainfooter{padding:12px 10px 0;text-align: right;}
.octa-flex{display:grid;grid-template-columns:repeat(4,1fr);gap:10px;padding-left:25px;}
.octa-flex label{display:flex;align-items:center;justify-content:flex-start;margin:0;}
.octa-flex input[type="checkbox"]{margin-right:5px;}
.octa-flex label input[type="checkbox"]{margin-top:0;}
</style>
</head>

<body>
<div class="wrapper">
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <label class="lab-inline">医院/总部：</label>
        <select id="instId" name="instId" style="width:150px;" data-options="required:false"></select>
        <input class="txt inline w-150" type="text" name="staffKey" placeholder="工号、姓名、手机号码">
       
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
    </form>
</div>

<div class="gridCont bob-line">
  <div id="gridBox"></div>
</div>
<div class="pageHead">
    <form id="queryMemberForm" class="soform personForm" autocomplete="off">
        <div class="row">
            <div class="p12">
                <input class="txt hide" type="text" id="staffId" name="staffId" value=""/>
                <input class="txt hide" type="text" id="staffInstId" name="staffInstId" value=""/>
                <input class="txt hide" type="text" id="staffInstName" name="staffInstName" value=""/>
                <label class="lab-inline">姓名：</label>
                <input type="text" id="staffName" name="staffName" class="txt w-80 txt-validate unedit" readonly="readonly">
                <label class="lab-inline">工号：</label>
                <input type="text" id="staffCode" name="staffCode" class="txt w-120 txt-validate unedit" readonly="readonly">
            </div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="p12">
                <label style="margin-left: 12px;">请选择省区：</label>
                <div class="item-one octa-flex" style="padding-left: 25px;margin-top: 5px;">
                    [#list nodeInfoList as nodeInfo]
                    <label><input type="checkbox" name="nodeAuthCheckbox" value="${nodeInfo.id}">${nodeInfo.nodeName}</label>
                    [/#list]
                </div>
            </div>
        </div>
    </form>
</div>

<div class="mainfooter" style="text-align: center;">
    <span class="btn btn-primary btn-ok">保 存</span>
    <span class="btn btn-cancel">取 消</span>
</div>
</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
require(["pub"],function(){

    var gridH = $(window).height() - $(window).height()*0.5;

    let staffNodeIds = '${nodeIds}';
    // 判断 staffProvince 是否是有效的 JSON 字符串
    let nodeIdsLoop;
    if (staffNodeIds) {
        try {
            // 将 staffProvince 转换为 JavaScript 对象
            nodeIdsLoop = JSON.parse(staffNodeIds);
            // Check the checkboxes based on staffProvinceObject
            nodeIdsLoop.forEach(function(item) {
                $('input[name="nodeAuthCheckbox"][value="' + item + '"]').prop('checked', true);
            });
            $('#staffId').val('${staffId}');
            $('#staffName').val('${staffName}');
            $('#staffCode').val('${staffCode}');
            $('#staffInstId').val('${staffInstId}');
            $('#staffInstName').val('${staffInstName}');
        } catch (e) {
            console.error("解析 JSON 失败：", e);
        }
    }

	$('#instId').combogrid({
		delay: 500,    	  
		mode: 'remote',   
		panelWidth:250,  
		fitColumns:true,
        clearIcon:true,
		idField:'ID',   
		textField:'NAME', 
		url:'${base}/ui/sys/autoComplete/query', 
		queryParams: {
			tag:'sys.institution',   
		},
		onSelect: function(v,record){
            var g = $('#instId').combogrid('grid');	// 获取数据表格对象
            var row = g.datagrid('getSelected');	// 获取选择的行
            $('#staffInstId').val(row.ID);
            $('#staffInstName').val(row.NAME);
		},
		columns:[[
			{field:'NAME',title:'名称',width:100},
			{field:'SHORT_NAME',title:'简称',width:60}
		]],
        height: gridH
	});

	/** 查询出还未关联当前医院当前角色的用户 */
	$grid.newGrid("#gridBox",{
	      singleSelect : true,
	      ctrlSelect : true,
	      pagination : true,
	      fitColumns : false,
	      columns:[[
	        {title:'id',field:'ID',checkbox:true},
            {title:'staffInstId',field:'STAFF_INST_ID',hidden:true},
            {title:'工号',field:'CODE',width:120},
            {title:'姓名',field:'NAME',width:100},
            {title:'性别',field:'SEX',width:100},
            {title:'科室/部门',field:'DEPT_NAME',width:120},
            {title:'所属机构',field:'INSTITUTION_ID',width:180}
	      ]],
	      onBeforeLoad: function (param) {
              // param.instId=100002;
	          if(!param.instId){
                return false;
              }
	      },
         onCheck: function (index, row) {
            $('#staffId').val(row.ID);
            $('#staffName').val(row.NAME);
            $('#staffCode').val(row.CODE);
         },
         url:'${base}/ui/sys/staff/getStaffByCondition',
         offset : -55
	});

    $('.btn-ok').click(function () {
        var staffId = $('#staffId').val();
        var staffCode = $('#staffCode').val();
        var staffName = $('#staffName').val();
        var staffInstId = $('#staffInstId').val();
        var staffInstName = $('#staffInstName').val();
        var nodeAuthChecked = $('input[name="nodeAuthCheckbox"]:checked').length > 0;

        if (!staffId || !staffName) {
            $pop.alert('请选择姓名、工号信息');
            return;
        }
        if (!nodeAuthChecked) {
            $pop.alert('请选择至少一个节点');
            return;
        }

        var lawNodeAuths = [];
        $('input[name="nodeAuthCheckbox"]:checked').each(function() {
            lawNodeAuths.push({
                nodeId: $(this).val(),
                nodeName: $(this).parent().text().trim()
            });
        });

        var sendData = {
            staffId: staffId,
            staffName: staffName,
            staffCode: staffCode,
            staffInstId: staffInstId,
            staffInstName: staffInstName,
            lawNodeAuths: lawNodeAuths
        };

        $ajax.post('${base}/ui/amcs/law/manage/node/saveNodeAuth', sendData, {
            jsonData: true,
            tip: false
        }).done(function (rst) {
            if (rst.code === '200' || rst.code === '201') {
                $pop.success('保存成功!', function (index) {
                    $pop.closePop();
                });
            } else {
                $pop.alert(rst.msg);
            }
        });
    });


    // 页面加载完成后，设置高度
    $('.textbox.combo').css('height', '26px');

});

</script>
</body>

</html>

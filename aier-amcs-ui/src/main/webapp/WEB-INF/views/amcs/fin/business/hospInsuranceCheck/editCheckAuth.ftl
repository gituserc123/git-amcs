<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>省区授权</title>
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
                    [#list provinceList as province]
                    <label><input type="checkbox" name="provinceDist" value="${province.id}">${province.name}</label>
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

    let staffProvince = '${staffProvince}';
    // 判断 staffProvince 是否是有效的 JSON 字符串
    let staffProvinceObject;
    if (staffProvince) {
        try {
            // 将 staffProvince 转换为 JavaScript 对象
            staffProvinceObject = JSON.parse(staffProvince);
            // Check the checkboxes based on staffProvinceObject
            staffProvinceObject.forEach(function(item) {
                if (item.provinceId === 99999999) {
                    $('input[name="provinceDist"]').prop('checked', true);
                } else {
                    $('input[name="provinceDist"][value="' + item.provinceId + '"]').prop('checked', true);
                }
            });
            $('#staffId').val(staffProvinceObject[0].staffId);
            $('#staffName').val(staffProvinceObject[0].staffName);
            $('#staffCode').val(staffProvinceObject[0].staffCode);
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
        var staffName = $('#staffName').val();
        var staffCode = $('#staffCode').val();
        var provinceChecked = $('input[name="provinceDist"]:checked').length > 0;

        if (!staffId || !staffName || !staffCode) {
            $pop.alert('请选择姓名、工号信息');
            return;
        }
        if (!provinceChecked) {
            $pop.alert('请选择至少一个省区');
            return;
        }

        var provinceDist = [];
        $('input[name="provinceDist"]:checked').each(function() {
            provinceDist.push({
                provinceId: $(this).val(),
                provinceName: $(this).parent().text().trim()
            });
        });

        var sendData = {
            staffId: staffId,
            staffName: staffName,
            staffCode: staffCode,
            provinceDist: provinceDist
        };

        $ajax.post('${base}/ui/amcs/fin/hospInsuranceCheck/saveAuth', sendData, {
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

    // Add event listener for the "全部省区" checkbox
    $('input[name="provinceDist"][value="99999999"]').change(function () {
        var isChecked = $(this).is(':checked');
        $('input[name="provinceDist"]').prop('checked', isChecked);
    });

    // Add event listener for other "provinceDist" checkboxes
    $('input[name="provinceDist"]').not('[value="99999999"]').change(function () {
        let pLen = "${provinceList.size()}";
        var allChecked = (parseInt(pLen)-1) === $('input[name="provinceDist"]').filter('[value!="99999999"]:checked').length;
        $('input[name="provinceDist"][value="99999999"]').prop('checked', allChecked);
    });

    // 页面加载完成后，设置高度
    $('.textbox.combo').css('height', '26px');

});

</script>
</body>

</html>

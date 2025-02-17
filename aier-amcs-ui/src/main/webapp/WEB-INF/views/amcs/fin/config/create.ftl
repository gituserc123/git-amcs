<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>模块管理 - 爱尔医院AHIS管理系统</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
        .mainfooter{padding:12px 10px 0;text-align: right;}
    </style>
</head>

<body>
<div class="wrapper pad-10">
    <div class="searchHead bob-line">
        <input type="hidden" class="txt" name="provinceId" id="provinceId" value="${provinceId!}" />
        <input type="hidden" class="txt" name="roleId" id="roleId" value="${roleId!}" />
        <input type="hidden" class="txt" name="provinceName" id="provinceName" value="${provinceName!}" />
        <input type="hidden" class="txt" name="roleName" id="roleName" value="${roleName!}" />
        <form id="sbox" class="soform form-enter">
            <input class="txt inline w-150" type="text" name="staffKey" id="staffKey" placeholder="工号、姓名">
            <input class="txt inline w-150 hide" type="text" name="instId" id="instId" value="${provinceId!}">
            <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
        </form>
    </div>

    <div class="gridCont">
        <div id="gridBox"></div>
    </div>

    <div class="mainfooter">
        <span class="btn btn-primary btn-ok">添加</span>
        <span class="btn btn-cancel">取消</span>
    </div>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
        requirejs(['pub'],function () {
            $grid.newGrid("#gridBox",{
                checkOnSelect : false,
                selectOnCheck : false,
                singleSelect : false,
                ctrlSelect : true,
                pagination: true,
                pageSize: 10,
                fitColumns : false,
                columns:[[
                    {title:'id',field:'id',checkbox:true},
                    {title:'工号',field:'code',width:120},
                    {title:'姓名',field:'name',width:100},
                    {title:'联系方式',field:'tel',width:100},
                ]],
                onLoadSuccess : function (data) {

                    if (data.rows.length > 0) {
                        //循环判断操作为新增的不能选择
                        for (var i = 0; i < data.rows.length; i++) {
                            //根据isFinanceExamine让某些行不可选
                            if (data.rows[i].deleteFlag == 1) {

                                $("input[type='checkbox']")[i + 1].disabled = true;
                                $($("input[type='checkbox']")[i + 1]).attr("style", "display:none;");
                                $(".datagrid-header-row input[type='checkbox']").attr("style", "display:none;");
                                // $("input[type='checkbox']")[i + 1].remove();
                            }
                        }
                    }

                },
                onClickRow: function(rowIndex, rowData){
                    //加载完毕后获取所有的checkbox遍历
                    $("input[type='checkbox']").each(function(index, el){
                        //如果当前的复选框不可选，则不让其选中
                        if (el.disabled == true) {
                            $('#dg').datagrid('unselectRow', index - 1);
                        }
                    })
                },
                onBeforeLoad:function (){
                    var firstLoad = $(this).attr("firstLoad");
                    if (firstLoad == "false" || typeof (firstLoad) == "undefined")
                    {
                        $(this).attr("firstLoad","true");
                        return false;
                    }
                    return true;
                },
                offset : -55
                ,url: '${base}/ui/amcs/fin/config/staffList'
            });
            $('.btn-ok').click(function () {

                var chkRows = $('#gridBox').datagrid('getChecked');
                var idArr = [];
                $.each(chkRows,function(i,v){
                    idArr.push(v.id);
                });
                var sendData = {
                    provinceId : $('#provinceId').val(),
                    provinceName:$('#provinceName').val(),
                    roleName : $('#roleName').val(),
                    roleId : $('#roleId').val(),
                    staffIds : idArr.join(',')
                };

                if (!sendData.staffIds) {
                    $pop.alert('请选择用户添加到当前角色！');
                    return false;
                }
                $ajax.post('${base}/ui/amcs/fin/config/staffBindRole',sendData,true).done(function (rst) {
                    if (rst.code==='200'||rst.code==='201') {
                        setTimeout(function () {
                            $pop.closePop({ refreshGrid: true });//关闭弹窗
                        }, 400);
                    };
                });
            });
        });
</script>
</body>
</html>

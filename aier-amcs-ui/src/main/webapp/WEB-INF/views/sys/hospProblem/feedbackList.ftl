<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医院反馈列表 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
    </style>
</head>

<body>
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <select class="drop easyui-combobox w-90" name="tType">
            <option value="1">提报时间</option>
            <option value="2">解决时间</option>
        </select>
        <input type="text" class="txt so-rangeDate w-210" name="date" data-opt="{val:'month',opens:'center',format:'YYYY-MM-DD',auto:false}">
        [@ui_select_plat id="osCode" name="platformCode"  class="drop drop-ra changeValidCombo easyui-combobox w-60" uiShowDefaultDesc="系统" dataOptions="
			                onSelect:function(r){
			                    if(!r.valueCode){
			                        return;
			                    }
				                $ajax.post('${base}/ui/sys/index/getModuleByPlatform?platformCode='+r.valueCode).done(function (rst) {
				                    rst.data.splice(0, 0, {'moduleCode': '', 'moduleName': '模块'});
									$('#moduleCode').combobox('loadData', rst.data);
								});
							}"/]
            <input id="moduleCode" name="moduleCode" class="drop drop-ra changeValidCombo easyui-combobox w-120" data-options="valueField:'moduleCode',textField:'moduleName',
                        	onSelect:function(r){
				                $('#moduleName').val(r.moduleName)
							},
							filter:function(q, row){return String(row['moduleName']).toUpperCase().indexOf(String(q).toUpperCase()) >= 0 || String(row['firstSpell']).toUpperCase().indexOf(String(q).toUpperCase()) >= 0},
							data: [{'moduleCode': '', 'moduleName': '模块'}]" />
        [@ui_select id="typeCode" name="typeCode" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$TypeEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefaultDesc="类型"/]
        [@ui_select id="problemLevel" name="problemLevel" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$LevelEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefaultDesc="级别"/]
        [@ui_staff id="personLiable" name="personLiable" param='{deptIds:[100078,100075,100077]}' class="drop drop-ra changeValidCombo easyui-combobox w-110" filterkey="firstSpell,name" uiShowDefaultDesc="负责人"/]
        [@ui_select id="state" name="state" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$StateEnum" class="drop drop-ra changeValidCombo easyui-combobox w-80" uiShowDefaultDesc="状态"/]
        <input class="txt txt-validate inline w-220" type="text" name="keywords" placeholder="标题/提报人/手机号码/医院"/>
        <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查
            询
        </button>
    </form>
</div>

<div class="cont-grid">
    <!--<h2 class="h2-title-a"><span class="s-title">领用记录</span></h2>-->
    <div id="gridBox"></div>
</div>
</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['pub'], function () {

        $grid.newGrid("#gridBox", {
            // fitParent: true,
            // fit: true,
            checkOnSelect: false,
            selectOnCheck: false,
            singleSelect: false,
            ctrlSelect: true,
            pagination: true,
            fitColumns: false,
            columns: [[
                {title: 'id', field: 'id', width: 60, hidden: true},
                [@shiro.hasPermission name = "GroupProblem:reply"]
                {
                    title: '操作', field: 'op', width: 50, formatter: function (v, row, index) {
                        var back = '';
                        if (row.state == 0) {
                            back = '<span class="icon-magic s-op s-op-confirm" rel="' + row.id + '" title="确认"></span>';
                        } else if (row.state == 1) {
                            back = '<span class="icon-checkmark s-op s-op-run s-op-solve" rel="' + row.id + '" title="解决"></span>';
                        } else if (row.state == 2) {
                            back = '<span class="icon-plane2 s-op s-op-run s-op-publish" rel="' + row.id + '" title="发布"></span>';
                        } else {
                            back = '<span class="icon-magic s-op s-op-active" rel="' + row.id + '" title="激活"></span>';
                        }
                        return back;
                    }
                },
                [/@shiro.hasPermission]
                {title: '编号', field: 'problemCode', width: 120, titletip: true},
                {title: '禅道Id', field: 'zentaoId', width: 80, titletip: true},
                {
                    title: '状态', field: 'state', width: 60, formatter: function (val, row, index) {
                        return ['<span class="red">待确认</span>', '<span class="blue">处理中</span>', '<span class="green">待发布</span>', '<span class="green">已完成</span>', '<span>已关闭</span>'][val];
                    }
                },
                {title: '问题级别', field: 'problemLevelName', width: 80, titletip: true},
                {title: '问题类型', field: 'typeName', width: 120},
                {title: '系统', field: 'platformCode', width: 60, titletip: true},
                {title: '模块', field: 'moduleName', width: 160, titletip: true},
                {title: '医院', field: 'hospName', width: 120, titletip: true},
                {title: '标题', field: 'title', width: 160, align: 'left', titletip: true},
                {title: '当前负责人', field: 'personLiableName', width: 70, formatter: function (val, row, index) {
                    if (val) {
                        if (row.personLiable == ${loginId}){
                            return '<span class="red">'+val+'</span>';
                        }
                        else{
                            return '<span>'+val+'</span>';
                        }
                    }
                }},
                {title: '创建时间', field: 'createDate', width: 80, format: 'yyyy-MM-dd'},
                {title: '开发负责人', field: 'resolverName', width: 70},
                {title: '解决日期', field: 'resolveDate', width: 80, format: 'yyyy-MM-dd'},
                {title: '提报人', field: 'declarerName', width: 60},
                {title: '分发时间', field: 'throwDate', width: 135},
                {title: '手机号码', field: 'phone', width: 100},
                {title: '最后修改人', field: 'modiferName', width: 80},
                //{title: '最后修改时间', field: 'modifyDate', width: 135},
                {
                    title: '处理周期', field: 'duringTime', width: 110, formatter: function (v, row, index) {
                        var back = '';
                        if(row.resolveDate){
                            var resolveDate = row.resolveDate.replace(/-/g,"/");
                            var createDate = row.createDate.replace(/-/g,"/");
                            var useTime = new Date(resolveDate) - new Date(createDate);
                            var days = (useTime / 86400000).toFixed(0);
                            // var hours = ((useTime % 86400000) / 3600000).toFixed(0);
                            // var minute = (((useTime % 86400000) % 3600000) / 60000).toFixed(0);
                            if (days > 0) {
                                back = back + days + "天";
                            } else {
                                back = "1天";
                            }
                        }
                        else{
                            back = "--";
                        }

                        /**
                         if( hours > 0){
                      back = back + hours + "小时";
                  }
                         if( minute > 0){
                      back = back + minute + "分钟";
                  }*/
                        return back;
                    }
                },
                {title: '操作系统', field: 'osName', width: 80},
                {title: 'IE版本', field: 'browserName', width: 80},
                {title: '网络运营商', field: 'netOperatorName', width: 80, titletip: true},
                {title: '备注', field: 'groupContent', width: 180, align: 'left', titletip: true}
            ]],
            onDblClickRow: function (index, row) {
                // window.console&&console.log(index);
                $pop.iframePop({
                    title: "编号:" + row.problemCode + ' 详情',
                    content: '${base}/ui/sys/hospProblem/detail?id=' + row.id
                    , area: ['100%', '100%'],
                    end: function () {
                        $('#gridBox').datagrid("reload");
                    }
                }, '#gridBox');
            },
            onLoadSuccess: function (data) {
                [@shiro.hasPermission name = "GroupProblem:reply"]
                $('.s-op-solve').click(function () {//删除事件
                    var id = $(this).attr('rel');
                    $ajax.post('${base}/ui/sys/hospProblem/reply', {
                        id: id,
                        state: 2
                    }, "是否确认该问题已解决？", true).done(function (rst) {
                        if (rst.code === '200' || rst.code === '201') {
                            $('#gridBox').datagrid('reload');
                        }
                        ;
                    });
                    return false;
                });

                $('.s-op-publish').click(function () {//删除事件
                    var id = $(this).attr('rel');
                    $ajax.post('${base}/ui/sys/hospProblem/reply', {
                        id: id,
                        state: 3
                    }, "是否确认该问题已发布？", true).done(function (rst) {
                        if (rst.code === '200' || rst.code === '201') {
                            $('#gridBox').datagrid('reload');
                        }
                        ;
                    });
                    return false;
                });

                $('.s-op-active').click(function () {//删除事件
                    var id = $(this).attr('rel');
                    $ajax.post('${base}/ui/sys/hospProblem/reply', {
                        id: id,
                        state: 1
                    }, "该问题已解决或关闭，是否确认重新激活？", true).done(function (rst) {
                        if (rst.code === '200' || rst.code === '201') {
                            $('#gridBox').datagrid('reload');
                        }
                        ;
                    });
                    return false;
                });

                $('.s-op-confirm').click(function () {//删除事件
                    var id = $(this).attr('rel');
                    $ajax.post('${base}/ui/sys/hospProblem/reply', {id: id, state: 1}, false, true).done(function (rst) {
                        if (rst.code === '200' || rst.code === '201') {
                            $('#gridBox').datagrid('reload');
                        }
                        ;
                    });
                    return false;
                });
                [/@shiro.hasPermission]
            },
            url: '${base}/ui/sys/hospProblem/getForGroupList',
            // height: 'auto',
            offset: 0
        });


    });

</script>
</body>

</html>

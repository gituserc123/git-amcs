<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>专家列表 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
.mainfooter{padding:12px 10px 0;text-align: right;}
</style>
</head>
<script type="text/javascript">
</script>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input type="text" class="txt inline" name="staffKey" id="staffKey" placeholder="专家姓名、工号">
            <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询
            </button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
<script id="editExpertTem" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input" />
    <div class="searchHead">
        <form id="ssbox" class="soform form-enter">
           	<input class="txt" type="hidden" name="instId" value="100001"></input>
            <input class="txt inline w-250" type="text" name="staffKey" id="staffKey" value="" placeholder="姓名、工号、手机号">
            <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#staffGridBox',scope:'#ssbox'}">查 询</button>
	    </form>    
    </div>
	<div class="cont-grid cont-grid-a bot-line-sub">
        <div id="staffGridBox"></div>
	</div>
	
	<div class="mainfooter">
	    <span class="btn btn-primary btn-ok">添加</span>
	    <span class="btn btn-cancel">取消</span>
	</div>
</script>
<script id="editGroupTem" type="text/html">
     <form class="soform form-validate formG">
        <div class="row mar-t15">
            <div class="p12">
            <span class="fl">
                <label class="lab-inline bold w-60">所属学组</label>
                <label class="lab-val"><input type="radio" class="rad" name="g1" value="1" />白内障 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g2" value="2" />青光眼 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g3" value="4" />眼底病 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g4" value="8" />泪道 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g5" value="16" />屈光 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g6" value="32" />角膜及眼表 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g7" value="64" />眼眶与眼整形 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g8" value="128" />视光 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g9" value="256" />斜弱视 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g10" value="512" />特检</label>
                <label class="lab-val"><input type="radio" class="rad" name="g11" value="1024" />麻醉 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g12" value="2048" />药学 </label>
                <label class="lab-val"><input type="radio" class="rad" name="g13" value="4096" />检验 </label>
            </span>
            </div>
        </div>
        <div class="mainfooter">
            <span class="btn btn-primary btn-group-ok">保存</span>
            <span class="btn btn-cancel">取消</span>
        </div>
    </form>
</script>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
	requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {
        var provinceData = {};
        loadProvince();
        var modifyIndexArray = [];

        let selectedProvinces = [];

        // 初始化 selectedProvinces
        function initializeSelectedProvinces(provinceCodes) {
            if (provinceCodes) {
                selectedProvinces = provinceCodes.split(',');
            } else {
                selectedProvinces = [];
            }
        }

        // 选择省份时更新 selectedProvinces
        function onSelect(row) {
            if (!selectedProvinces.includes(row.instCode)) {
                selectedProvinces.push(row.instCode); // 添加选中的省份
            }
            $e.setCellVals({ 'provinceCodes': selectedProvinces.join(',') });
        }

        // 取消选择省份时更新 selectedProvinces
        function onUnselect(row) {
            selectedProvinces = selectedProvinces.filter(code => code !== row.instCode); // 移除未选中的省份
            $e.setCellVals({ 'provinceCodes': selectedProvinces.join(',') });
        }

        // 在每次选择某行时初始化 selectedProvinces
        function onClickCell(index, field, value) {
            if (field === 'provinceNames') {
                let row = $('#gridBox').datagrid('getRows')[index];
                initializeSelectedProvinces(row.provinceCodes);
            }
            $e.editRow({
                index: index,
                cellField: field,
                focusField: 'province',
                rowCanEdit: function (row) {
                    return true;
                },
                onEndEdit: function (index) {
                    if (modifyIndexArray.indexOf(index) == -1) {
                        modifyIndexArray.push(index);
                    }
                }
            });
        }

        $grid.newGrid("#gridBox",{
    	 	pagination: true,
            fitColumns: false,
            tools: [[
                {
                    iconCls: 'plus', text: '新增',click:function (){ popExpertTem('新增专家');}
			    },
                {
                    iconCls: 'save', text: '保存',click:function (){ saveGrid('保持');}
                },
                {
                    iconCls: 'print', text: '导出',click:function (){ exportGrid();}
                }
			]],
	    	columns:[[
				{title: "操作", field: "op", width: 75, formatter: function (v, row, index) {
				         let opStr = '<span class="s-op s-op-group icon-group" title="关联学组" rel="' + index + '"></span>&nbsp;&nbsp;&nbsp;';
                         opStr += '<span class="s-op s-op-refresh icon-refresh" title="更新机构" rel="' + index + '"></span>';
				         opStr += '<span class="s-op s-op-del icon-del" title="删除" rel="' + index + '"></span>';
				         return opStr;
                    }
				},
		        {title:'id',field:'id',width:80, hidden:true},
                {title:'groupValue',field:'groupValue',width:80, hidden:true},
		        {title:'专家姓名', field:'staffName', width:200},
                {title:'专家工号', field:'staffCode', width:200},
                {title:'所属学组', field:'groupName', width:200},
                {title:'所属省区编码', field:'provinceCodes', editor:{type:'readonly'},hidden:true},
                {title:'所属省区', field:'provinceNames', hidden: false, width:200,
                    editor:{
                        type: 'combobox', options: {
                            multiple:true,
                            valueField: 'name',
                            textField: 'name',
                            data: provinceData,
                            onSelect: onSelect,
                            onUnselect: onUnselect
                        }
                    }
                }

            ]],
		    rowStyler : function(index, row) {
                if(row.groupValue == ''){
                    return "background-color: #F8C471";
                    
                }
            },
            onClickCell : onClickCell,
            onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {
                $('.s-op-group').click(function () {
					let ix = $(this).attr('rel');
					let row = data.rows[ix];
                    popGroupTem(row.id);
                });
                 $('.s-op-del').click(function () {
                 	let ix = $(this).attr('rel');
                 	let row = data.rows[ix];
                 	$ajax.post({ url: `${base}/ui/service/biz/amcs/adverse/expert/delById`, tip: '是否确认删除当前专家?', data: { "id": row.id } }).done((res) => {
						$pop.msg('删除成功！');
						reloadGrid();
					});
                 });
                $('.s-op-refresh').click(function () {
                    let ix = $(this).attr('rel');
                    let row = data.rows[ix];
                    $ajax.post({ url: `${base}/ui/service/biz/amcs/adverse/expert/refresh`, tip: '是否确认刷新机构授权?', data: { "staffCode": row.staffCode } }).done((res) => {
                        $pop.msg('刷新成功！');
                        reloadGrid();
                    });
                });

            },
            queryParams : {},
      		url: '${base}/ui/service/biz/amcs/adverse/expert/findList'
  		});
  		
  		function reloadGrid() {
  			$('#gridBox').datagrid('reload');
  		};

        function loadProvince() {
            $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
                //增加集团到rst列表中，且集团总部放入列表的首行
                rst.unshift({name: '集团总部', instCode: '100002'});
                provinceData = rst;
            });
        };

        function popGroupTem(dataId){
            var popGroup = $pop.popTemForm({
                title : '关联学组', 
                temId : 'editGroupTem', 
                zIndex : 2,
                area : ['980px','150px'],
                end:function(){
                	$grid.reload("#gridBox")
                },
            })
            $('.btn-group-ok').click(function () {
                let groupVal = 0;
                $.each($(".rad").filter(":checked"), function () {
                    groupVal += Number($(this).val());
                });
                if(groupVal > 0){
                    let groupData = {'id': dataId, 'groupValue': groupVal};
                    $ajax.post('${base}/ui/service/biz/amcs/adverse/expert/updateGroup', groupData, {jsonData:false,tip:false}).done(function (rst) {
                        if (rst.code==='200'||rst.code==='201') {
                                $pop.success('学组绑定成功!',function(index){
                                $pop.closePop({ popIndex:popGroup });
                            });
                        }else{
                            $pop.alert("绑定学组失败");
                        }
                    });
                }
            });
            $('input:radio').click(function(){
                let domName = $(this).attr('name');
                let $radio = $(this);
                if ($radio.data('waschecked') == true) {
                    $radio.prop('checked', false);
                    $("input:radio[name='" + domName + "']").data('waschecked', false);
                } else {
                    $radio.prop('checked', true);
                    $("input:radio[name='" + domName + "']").data('waschecked', false);
                    $radio.data('waschecked', true);
                }         
            })
        };

        function saveGrid(){
            if(modifyIndexArray.length == 0){
                $pop.msg("找不到需要保存的信息！");
                return;
            }else{
                let rows = $("#gridBox").datagrid('getRows');
                let selRows = new Array();
                $.each(modifyIndexArray, function(index,value){
                    selRows.push(rows[value]);
                });
                $ajax.post('${base}/ui/service/biz/amcs/adverse/expert/updateList',JSON.stringify(selRows),false,true).done(function (rst) {
                    if (rst.code==='200'||rst.code==='201') {
                        rowIndexArr = [];
                        $pop.msg('保存成功！');
                    }else{
                        $pop.warning('保存异常!');
                    };
                });

            }
        }

        function exportGrid(){
            $pop.confirm('您确认想要导出记录吗？',function(r) {
                if (r) {
                    var url = '${base}/ui/service/biz/amcs/adverse/expert/exportExcel';
                    downloadExcelProcess(url);
                }
            });
        }

        function downloadExcelProcess(url){
            // 开启遮罩
            showMask();
            $pop.msg('文件正在导出，请耐心等待....',3000,{icon:2});
            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.responseType = "blob"; // 返回类型blob
            // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
            xhr.onload = function() {
                // 请求完成
                if (this.status === 200) {
                    var type = xhr.getResponseHeader('Content-Type');
                    var blob = new Blob([this.response], {
                        type: type
                    });
                    var fileName = xhr.getResponseHeader("content-disposition");
                    fileName = decodeURI(fileName.split(";")[1].split("filename=")[1].trim('"'));
                    if (typeof window.navigator.msSaveBlob !== 'undefined') {
                        // For IE>=IE10
                        window.navigator.msSaveBlob(blob, fileName);
                    } else {
                        // For chrome, firefox
                        var URL = window.URL || window.webkitURL;
                        var objectUrl = URL.createObjectURL(blob);
                        if (fileName) {
                            // 创建一个a标签用于下载
                            var a = document.createElement('a');
                            a.download = fileName;
                            a.href = objectUrl;
                            $("body").append(a);
                            a.click();
                            $(a).remove();
                        } else {
                            window.location = objectUrl;
                        }
                    }
                } else {
                    $pop.alert('导出失败！');
                }
                // 关闭遮罩代码
                rmMask();
            };
            // 发送ajax请求
            xhr.send();
        };

        var showMask = function () {
            var wrap = $(".cont-grid");
            $("<div class=\"datagrid-mask\"></div>").css({
                display: "block",
                width: window.width,
                height: window.height
            }).appendTo(wrap);
        };
        var rmMask = function () {
            var wrap = $(".cont-grid");
            wrap.find("div.datagrid-mask").remove();
        }


        function popExpertTem(title) {
            var pop = $pop.popTemForm({
                title : title, 
                temId : 'editExpertTem', 
                zIndex : 2,
                area : ['900px','500px'],
                end:function(){
                	$grid.reload("#gridBox")
                },
                onPop: function($p){
                    $grid.newGrid("#staffGridBox",{
                        pagination: true,
                        fitColumns: false,
                        grid: '#gridBox',
                        height: 'auto',
                        pageSize:10,
                        columns:[[
                        	{title:'ID',field:'ID',width:80,hidden:true},
                            {field:'CODE',title:'工号',width:100,align:'center'}, 
					        {field:'NAME',title:'姓名',width:100,align:'center'},
					        {field:'SEX',title:'性别',width:35,align:'center'},
					        {field:'DEPT_NAME',title:'部门',width:200,align:'center'},
					        {field:'TEL',title:'手机号',width:100,align:'center'},
					        {field:'INSTITUTION_ID',title:'所属机构',width:200,align:'center'},
					        {field:'LOCKED',title:'是否锁定',width:60,align:'center' ,formatter: function (v,row,index){
                            	return ['<span class="green">否</span>','<span class="red">是</span>'][v];
                        	}},
					        {field:'STATUS',title:'是否启用',width:60,align:'center' ,formatter: function (v,row,index){
                            	return ['<span class="red">否</span>','<span class="green">是</span>'][v];
                        	}},
                        ]],
                        rowStyler : function(index, row) {
                        },
                        onBeforeLoad : function (param) {
			            	if(!param.instId){
			                	return false;
			              	}
				        },
		                onLoadSuccess : function (data) {
                        },
                        url: '${base}/ui/sys/staff/getStaffByCondition'
                    });
                    $('.btn-ok').click(function () {
                        let selRow = $('#staffGridBox').datagrid('getSelected');
                        if(selRow == null) {
                        	$pop.alert('请选择人员！');
                            return false;
                        }
                        $ajax.post('${base}/ui/service/biz/amcs/adverse/expert/save',JSON.stringify(selRow), {jsonData:true,tip:false}).done(function (rst) {
                            if (rst.code==='200'||rst.code==='201') {
                            	 $pop.success('添加成功!',function(index){
								    $pop.closePop({ popIndex:pop });
								});
                            }else{
                                $pop.alert(rst.msg);
                            }
                        });
                    });
                }
            });
        };
        
    });
</script>
</html>
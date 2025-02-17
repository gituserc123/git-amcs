<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医生手术分类表 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input class="txt" type="hidden" name="surgType" id="surgType"  />
            
            <label class="lab-inline">省区：</label>
            [#if "${isGroup!}" == 1]
            	<select class="drop drop-sl-v easyui-combobox  w-150 province" name="provId"   data-options="valueField:'id',textField:'name',clearIcon:true"></select>
            [#else]
            	<input class="txt txt-validate w-150" type="text" name="hospName" value="${instName!}" readonly="readonly" data-options="editable:false"/>
            	<input type="hidden" name="provId" value="${instId!}" />
            [/#if]
		    
            <label class="lab-inline">医院：</label>
            <select class="drop drop-sl-v easyui-combobox w-150 hosp" name="hospId"  data-options="valueField:'ahisHosp',textField:'name',clearIcon:true">
            </select>
            <label class="lab-inline">手术医生</label>
            <select class="drop easyui-combobox w-100 doctor" name="doctorId" 
               data-options="editable:true,valueField:'id',textField:'name',clearIcon:true,
                  filter: function(q, row){
                     return (row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1  || row['name']&&row['name'].indexOf(q) >-1 )
                  }">
            </select>
            <label class="lab-inline">手术分类</label>
            <input class="drop easyui-combotree w-200 surgType" data-options="textField:'name', valueField:'code', flatData:true,multiple:true,onlyLeafCheck:false,panelWidth:'240px',panelMinHeight:'320px'" />

            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
            <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {
        let url = '${base}/ui/amcs/coll/hosp/docsurg/findListByHospDoctor';
        let param = {};
    	if('${isHosp}' == '1') {
        	url = '';
        };
        if('${isGroup!}' == 0){
        	param = {'provId': '${instId}'};
        	$ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=${instId}',null,false,false).done(function (rst) {
	            $('.hosp').combobox('loadData', rst);
	        });
        }else{
	        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
	            $('.hosp').combobox('loadData', rst);
	        });
        }
        
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
            $('.province').combobox('loadData', rst);
        });
       

        $ajax.postSync('${base}/ui/amcs/coll/hosp/docsurg/findOprTypeList', null, false, true).done(function (rst) {
            $('.surgType').combotree('loadData', rst);
        });


        $('.province').combobox({
            onChange: function(v){
                let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                $('.hosp').combobox('reload', url);
            }
        });

        $('.hosp').combobox({
            onChange: function(v){
                let staffParam = {instId: v, queryType: "1", isDistinct: true};
                $ajax.postSync(base + '/ui/sys/staff/getHospStaff?_easyui_=COMB', staffParam, false, true).done(function (rst) {
                $('.doctor').combobox('loadData', rst);
                });
            }
        });

        $('.surgType').combotree({
            onChange: function(v){
                let codeValues = [];
                $.each(v, function(index, value){
			       let node = $('.surgType').combotree('tree').tree('find', value);
			       codeValues.push(node.code);
			    });
                $("#surgType").val(codeValues);
            }
        });
        
        $(".s-export").click(function ($e) {
            var formData = $('#sbox').sovals();
            $pop.confirm('您确认想要导出记录吗？',function(r){
                if (r){
                    var url = '${base}/ui/amcs/coll/hosp/docsurg/exportExcel?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    downloadExcelProcess(url);
                }
            });
        });

        $grid.newGrid("#gridBox",{
            columns:[[
                {title:'省区', field:'provinceName', width:80},
                {title:'医院', field:'hospName', width:150},
                {title:'手术医生', field:'doctorName', width:150},
                {title:'医生职称', field:'deptName', width:150},
                {title:'手术亚专科', field:'pSurgTypeName', width:150},
                {title:'亚专科分类', field:'surgTypeName', width:120},
                {title:'修改人', field:'modiferName', width:150},
                {title:'修改时间', field:'modifyDate', width:150, format:'yyyy-MM-dd'}
            ]],
            rowStyler : function(index, row) {
                
            },
            onBeforeLoad: function(param){
                
            },
            onLoadSuccess : function (data) {
                
            },
            queryParams : param,
            url: url,
            offset : -50
        });
    
   });
   
   var showMask = function () {
        var wrap = $(".cont-grid");
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: window.width,
            height: window.height
        }).appendTo(wrap);
    };
   
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


</script>
</html>
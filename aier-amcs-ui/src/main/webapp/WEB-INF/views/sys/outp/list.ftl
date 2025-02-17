<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>爱尔门诊量一览表 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
code, pre{font-family:Helvetica Neue,Helvetica,Tahoma,Verdana,Arial,"Microsoft YaHei","SimSun";font-size: 14px;}

.h1-title{font-size: 24px;line-height:30px;text-align: center;padding:25px 0 20px;color:#000;}
.tabs li a.tabs-inner{padding:0 30px;}


.tabCont{position: absolute;left:0;right:0;top:157px;bottom:5px;}
.tabContHide{visibility: hidden;}
.cont-grid{z-index:2;position:absolute;top:44px;left:0;right:0;bottom:52px;overflow: hidden;zoom: 1;border-top:1px solid #e3e3e3;}
.cont-grid-ex{bottom:0;}
.mainfooter{position:absolute;left:0;right:0;bottom:0;height:50px;}

.h2-title-a{float:left;width:255px;margin:12px 0 5px;}
.p-info{float: left;margin-right:10px;font-size:14px;}
.p-info .s-t{font-weight: bold;}
.p-info .strong-info{font-weight: bold;}
.p-info .em-info{font-weight: bold;text-decoration: underline;padding:0 5px;cursor:hand;cursor:pointer;display: -moz-inline-stack;display:inline-block;*display:inline;*zoom:1;}
.p-info .strong-info .em-info{font-weight: bold;color:#00A0E9;}
.p-info .strong-info .info-now,
.p-info .info-now{color:#c00;}
.s-one em{font-weight:bold;}

</style>
</head>

<body>

[#--<h1 class="h1-title">爱尔门诊量一览表</h1>--]
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <input class="txt hide txt-type" id="sysType" type="text" name="sysType" value="ahis" />
        <input class="txt hide txt-type" id="hospType" type="text" name="hospType" value="0" />
        <input class="txt hide txt-type" id="state" type="text" name="state" value="0" />
        <span class="fl">
            <label class="lab-inline">省区：</label>
             [@ui_inst id="instId" name="instId" param='{queryType:"1",instId:100001, instType:"3",isLoginInst:"false"}' /]
        </span>
        <span class="fl">
            <label class="lab-inline">医院：</label>
            <input type="text" class="txt inline w-170" id="hospName" name="hospName">
        </span>
        <span class="fl">
            <label class="lab-inline">挂号时间：</label>
            <input class="txt txt-validate so-date w-185" type="text" name="regDateEnd" value="" />
        </span>
        <span class="fl mar-l5">
            <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#gridBox',scope:'#sbox'}">查 询</button>
        </span>
    </form>
</div>
<div class="cont-grid">
    <div id="gridBox"></div>
</div>

<div class="mainfooter bot-line-sub clearfix">
    <div class="oneinfo pad-10 center">
        <span class="s-one">
        <b class="blue">当日实时门诊量：</b>
        <em></em><em class="one-w txt-outpsToday"></em>人次
    </div>
</div>
</body>


[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
requirejs(['pub'],function () {

	var allData = null;
	var sortType = null;

    function searchGrad (){
        var instName = $('#instId').combobox('getText');
        var hospName = $.trim($('#hospName').val());
        var sData = [],tData = [];
		if(instName){
	        $.each(allData,function(i,v){
	        	if(v.province == instName){
	        		sData.push(v);
	        	}
	        });
        }else{
    		sData = allData;
    	}
    	
		if(hospName){
	        $.each(sData,function(i,v){
	        	if(v.hospName.indexOf(hospName)>-1){
	        		tData.push(v);
	        	}
	        });
        }else{
    		tData = sData;
    	}
		
        //console.log(instName,hospName,tData);
       $('#gridBox').datagrid('loadData',tData);
    };


    function toThousands(v) {
        if(v!==undefined) {
            var num = Number(v).toFixed(2);
            console.log(num,num.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,'));
            return num.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
        }
    }

    function fmoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	    t = "";
	    for (i = 0; i < l.length; i++) {
	        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	}

    $('.btn-search').click(function (){//搜索按钮
        searchGrad();
    });


    function rtVal(v,callback){
		if(v!==undefined && v!== '') {
	     	return typeof(callback)=='function'?callback(v):v;
	     }else{
	     //<div class="center">-</div>
	     	return '0';
	     }
	}

	function rtDate(v){
    	return rtVal(v,function(v){
    		return $.fmtDate('yyyy-MM-dd',v);
    	});
	}

	function sortNum(a,b){
		//console.log(a,b);
    	if(sortType == 'asc'){
        	if(a==undefined)a= 10000000000000;
        	if(b==undefined )b= 10000000000000;
        	if(a==='') a='2050 01-01-01 00:00:00';
        	if(b==='') b='2050 01-01-01 00:00:00';
    	}else{
        	if(a==undefined)a= -1;
        	if(b==undefined)b=-1;
        	if(a==='') a='1900 01-01-01 00:00:00';
        	if(b==='') b='1900 01-01-01 00:00:00';

    	}
    	return a>b?1:-1;
	}


	$ajax.post({
		url : '${base}/ui/outp/regist/grid',
		sync : true,
		callback : function(rst){
			allData = rst;
			//console.log(allData);
		}
	});

    $grid.newGrid("#gridBox",{
        fitParent : true,
        remoteSort: false,
        checkOnSelect : false,
        //pageSize: 1,
        //pageList: [1,10, 20, 50, 100, 200],
        selectOnCheck : false,
        singleSelect : false,
        ctrlSelect : true,
       	pagination : false,
        fitColumns : false,
        columns:[[
        {title:'医疗机构ID',field:'hospId',width:100,hidden:true},
        {title:'省区',field:'province',width:120,sortable:true},
        {title:'医疗机构名称',field:'hospName',width:280,align:'left'},
        {title:'当日门诊量(人次)',field:'outpsToday',sortable:true,width:125,order:'desc',sorter:sortNum,formatter:rtVal}
        ]],
        onBeforeSortColumn : function(sort, order){
        	//console.log(sort, order);
        	sortType = order;
        },
        //onBeforeLoad : function (opt){
            //if(!opt.sysType){
            //    return false;
            //}
        //},
        onLoadSuccess : function (data) {
          var $grid = $('#gridBox');
           //window.console && console.log(sysType,data);
           var dayOutpNum = 0,allOutpNum = 0;
            $.each(data.rows,function(k,v){
            	dayOutpNum += v.outpsToday?v.outpsToday*1:0;
            });
            
            $('.txt-outpsToday').text(dayOutpNum);
            
            
          //var gridH = $('.cont-grid').height();
          //$grid.datagrid('resize',{height:gridH});
        },
        //data : allData,
         url:'${base}/ui/outp/regist/grid',
    //   height: 370,
    //   fitHeight : false,
        offset :0
    });



});

</script>
</body>

</html>
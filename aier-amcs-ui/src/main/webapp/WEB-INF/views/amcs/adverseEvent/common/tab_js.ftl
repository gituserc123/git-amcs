/**
* 页签切换相关
*/
// js部分

//生成蒙版不让点击
var html='<div class="panel-mask"><p>此事件已经归档！！！</p></div>'
var showMask=function(){$('.tabCont').append(html)};
<#if "${ae.isArchived}"==1>
    $(document).ready(function() {
    showMask();
    });
</#if>


function makeExpandingArea(el) {
var timer = null;
//由于ie8有溢出堆栈问题，故调整了这里
var setStyle = function(el, auto) {
if (auto) el.style.height = 'auto';
el.style.height = el.scrollHeight + 'px';
}
var delayedResize = function(el) {
if (timer) {
clearTimeout(timer);
timer = null;
}
timer = setTimeout(function() {
setStyle(el)
}, 200);
}
if (el.addEventListener) {
el.addEventListener('input', function() {
setStyle(el, 1);
}, false);
setStyle(el)
} else if (el.attachEvent) {
el.attachEvent('onpropertychange', function() {
setStyle(el)
})
setStyle(el)
}
if (window.VBArray && window.addEventListener) { //IE9
el.attachEvent("onkeydown", function() {
var key = window.event.keyCode;
if (key == 8 || key == 46) delayedResize(el);

});
el.attachEvent("oncut", function() {
delayedResize(el);
}); //处理粘贴
}
}

$(".fix-print-btn").click(function(){
    debugger;
    let id = $("#basicId").val();
    let prevId = $("#prevId").val();
    let eventName = $("#eventName").val();
    $pop.newTabWindow(eventName, '${base}/fr?url=ae_unplReoperation_report.cpt&common&op=view&basicid=' + id + '&prev_id=' + prevId);

});


var adaptiveTextarea = document.getElementsByClassName('adaptiveTextarea');


for(a in adaptiveTextarea){
makeExpandingArea(adaptiveTextarea[a]);
}

function initTab(){
    var tabIndex = 0;
    var tabInit = [true,'','','',''];
    var tabInitE = [
        function one() {
        },
        function two() {
            tabInit[1] = true;
            let params = window.params || {};
            params.types = "1,6"
            params.type = null;
            eventFlowchart.init({url:base+"/ui/amcs/adverse/eventConfig/getNodeListById",urlParams:params,leaderParams:params});
            console.log(window.eventFlowchart);
        },
        function three() {
            debugger;
            let pageType = $(".tabCont-0").find("#pageType").val()
            tabInit[2] = true;
            let params = window.params || {};
            params.pageType = pageType;
            params.isReview = '${isReview}';
            if($.isEmptyObject('${showOperate!}')){
            	params.showOperate = 0;
            }else{
            	params.showOperate = '${showOperate!}';
            }

            eventTableGroup.init({url:base+"/ui/service/biz/amcs/adverse/common/findMultiList",urlParams: params});
        },
        function four(){
        	//获取id值
        	let id = $(".tabCont-0").find("#basicId").val()
        	let prevId = $(".tabCont-0").find("#prevId").val()
        	let url = base+"/ui/service/biz/amcs/adverse/aeFile/findByCond";
            let params = {
            	urlObj: {url},
            	urlObj: { url: url, urlParam: {'basicId': id, 'prevId': prevId} },
            	other: { id: id },
            	pagetType:'${pageType}'
            };
            tabInit[3] = true;
            eventImage.init(params);
        },
        function five() {
        	tabInit[4] = true;
        	let params = window.params || {};
        	params.types = null;
        	params.type = 7;
        	eventReview.init(params);
        
        }
    ];
    window.$tabLi = $('.likeTabs li');
    $tabLi.click(function () {
        var ix = $tabLi.index(this);
        if(ix!== 0 && ix!==4){// ix!=4 禅道号 12885
            $("#op-div").hide();
        }else {//仅在事件明细显示 右侧 操作按钮
            $("#op-div").show();
            
        }
        if(!$(this).hasClass('tabs-selected')){
            tabIndex = $(this).attr('rel');
            $tabLi.removeClass('tabs-selected');
            $(this).addClass('tabs-selected');
            $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
            if (!tabInit[ix]) {//初始化
            tabInitE[ix]();
            }
            if(ix==1){
                console.log('流程图业务处理');
                if(window.myDiagram){
                    console.log('reload flow');
                    //eventFlowchart.loadData(); 
                }
            }else if(ix==2){
                console.log('表格业务处理');
                //eventTableGroup.loadData();
            }else if(ix==4){
            	console.log("点评");
            
            }
        }
        return false;
    });
}
initTab();
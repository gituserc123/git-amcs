<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医疗服务信息/数据质量指标 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
        .likeTabs{width:auto;}
        .likeTabs .tab-last{border-right:0;}
        .tabs li a.tabs-inner{padding:0 30px;}
        .tabContBox{position: absolute;left:0;right:0;top:0px;bottom:0px;overflow: hidden;}

        .tabCont{position: absolute;left:0;right:0;top:34px;bottom:5px;}
        .tabContHide{visibility: hidden;}

        .cont-grid{overflow: hidden;zoom: 1;}
        .table-header{
            padding: 10px;
            text-align: center;
            background-color: #e5f4fb;
        }
        .searchHead{
            position: relative;
            top:-40px;
            left:330px;
        }
    </style>
</head>

<body>
<div class="tabContBox">
    <ul class="tabs likeTabs">
        <li class="tabs-first tabs-selected">
            <a href="#" class="tabs-inner"><span class="tabs-title">汇总统计</span></a>
        </li>
        <li  rel="0">
            <a href="#" class="tabs-inner"><span class="tabs-title">DIP统计</span></a>
        </li>
        <li  rel="1">
            <a href="#" class="tabs-inner"><span class="tabs-title">DRG统计</span></a>
        </li>
    </ul>
    <div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <label class="lab-inline">季度：</label>
            <select class="drop w-100"  id="maQuarter" name="maQuarter">
                <option value="" ></option>
                <option value="1">第一季度</option>
                <option value="2">第二季度</option>
                <option value="3">第三季度</option>
                <option value="4">第四季度</option>
            </select>
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="">查 询</button>
            <button type="button" class="btn btn-small btn-primary s-export">导 出</button>
        </form>
    </div>
    <div class="tabCont tabCont-6 ">
        <iframe scrolling="yes" frameborder="0" id="firstFrame" src="" data-url="${base}/ui/amcs/fin/ma/tab/sumStatPage" style="width:100%;height:100%;"></iframe>
    </div>
    <div class="tabCont tabCont-0 tabContHide" >
        <iframe scrolling="yes" frameborder="0" id="secondFrame"  src="" data-url="${base}/ui/amcs/fin/dip/ma/dipStatMaPage" style="width:100%;height:100%;"></iframe>
    </div>
    <div class="tabCont tabCont-0 tabContHide" >
        <iframe scrolling="yes" frameborder="0" id="thirdFrame"  src="" data-url="${base}/ui/amcs/fin/drg/ma/drgStatMaPage" style="width:100%;height:100%;"></iframe>
    </div>
</div>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript" src='${base}/static/js/config.require.js?12441'></script>
<script type="text/javascript">
    requirejs(['pub'], function () {
        $("#firstFrame").attr('src',$("#firstFrame").data("url"));
        var tabIndex = 0;
        var $tabLi = $('.likeTabs li');
        //页签点击事件
        $tabLi.click(function() {
            var $this = this;
            var ix = $tabLi.index(this);
            if (tabIndex !== ix) {
                    tabIndex = ix;
                    $tabLi.removeClass('tabs-selected');
                    $($this).addClass('tabs-selected');
                    $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');

                    $tabCont = $('.tabCont').eq(ix);
                    $iframe = $tabCont.find("iframe");
                    var url  = $iframe.data("url");
                    if(!$iframe.attr("src")){
                        $iframe.attr("src",url);
                    }
            }
        });

        $(".so-search").click(function () {
            var formData = $('#sbox').sovals();
            var selectedTab = $('.likeTabs li.tabs-selected');

            // 检查rel属性来区分其他iframe
            if (selectedTab.attr('rel') === '0') {
                $("#secondFrame")[0].contentWindow.refreshGridbox(formData);
            } else if (selectedTab.attr('rel') === '1') {
                $("#thirdFrame")[0].contentWindow.refreshGridbox(formData);
            } else {
                $("#firstFrame")[0].contentWindow.refreshGridbox(formData);
            }
        });

        $(".s-export").click(function () {
            var formData = $('#sbox').sovals();
            var selectedTab = $('.likeTabs li.tabs-selected');

            $pop.confirm('您确认想要导出记录吗？',function(r){
                if (r){
                    // 检查rel属性来区分其他iframe
                    if (selectedTab.attr('rel') === '0') {
                        var url = '${base}/ui/amcs/fin/dip/ma/downLoadList?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    } else if (selectedTab.attr('rel') === '1') {
                        var url = '${base}/ui/amcs/fin/drg/ma/downLoadList?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    } else {
                        var checkNode = $("#firstFrame")[0].contentWindow.getModuleTreeSelected();
                        if(checkNode){
                            formData.province=checkNode.id;
                            formData.provinceName=checkNode.name;
                        }
                        var url = '${base}/ui/amcs/fin/ma/tab/downLoadList?paramJson='+encodeURIComponent(JSON.stringify(formData));
                    }
                    window.open(url);
                }
            });
        });
    });
</script>
</body>

</html>

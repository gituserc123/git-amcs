<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>法务信息查询 - 爱尔医院</title>
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
    </style>
</head>

<body>
<div class="tabContBox">
    <ul class="tabs likeTabs">
        <li class="tabs-first tabs-selected">
            <a href="#" class="tabs-inner"><span class="tabs-title">民事诉讼仲裁案</span></a>
        </li>
        <li  rel="0">
            <a href="#" class="tabs-inner"><span class="tabs-title">刑事案件</span></a>
        </li>
        <li  rel="1">
            <a href="#" class="tabs-inner"><span class="tabs-title">行政处罚</span></a>
        </li>
        <li  rel="2">
            <a href="#" class="tabs-inner"><span class="tabs-title">纠纷事项</span></a>
        </li>
    </ul>
    <div class="tabCont tabCont-6 ">
        <iframe scrolling="yes" frameborder="0" id="firstFrame" src="" data-url="${base}/ui/amcs/law/civilCase/listPage" style="width:100%;height:100%;"></iframe>
    </div>
    <div class="tabCont tabCont-0 tabContHide" >
        <iframe scrolling="yes" frameborder="0"  src="" data-url="${base}/ui/amcs/law/criminalCase/listPage" style="width:100%;height:100%;"></iframe>
    </div>
    <div class="tabCont tabCont-0 tabContHide" >
        <iframe scrolling="yes" frameborder="0"  src="" data-url="${base}/ui/amcs/law/adminPenalty/listPage" style="width:100%;height:100%;"></iframe>
    </div>
    <div class="tabCont tabCont-0 tabContHide" >
        <iframe scrolling="yes" frameborder="0"  src="" data-url="${base}/ui/amcs/law/disputeMatter/listPage" style="width:100%;height:100%;"></iframe>
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
                    //if(!$iframe.attr("src")){
                        $iframe.attr("src",url);
                    //}
            }
        });
    });

</script>
</body>

</html>

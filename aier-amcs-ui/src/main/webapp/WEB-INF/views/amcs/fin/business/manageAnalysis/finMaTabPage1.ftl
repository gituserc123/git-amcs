<!DOCTYPE html>
<html>
<style type="text/css">
    [v-cloak]{
        display: none;
    }
    .layui-layer.layui-layer-tips .layui-layer-close1 {
        background-position: -188px -40px !important;
    }

    .tabCont{position: absolute;left:0;right:0;top:34px;bottom:5px;}

</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>医保管理分析1 - 爱尔医院</title>
    <style>
        .panel-body.panel-body-noheader.panel-body-noborder {
            height: calc(100vh - 40px)!important;
            overflow-y: hidden;!important;
        }
    </style>

</head>
<body>
<div class="easyui-tabs" style="width:100%;">
    <div title="医保管理分析DRG">
        <iframe scrolling="yes" frameborder="0" src="${base}/ui/amcs/fin/drg/ma/maDrgForm" style="width:100%;height:100%;"></iframe>
    </div>
    <div title="医保管理分析DIP">
        <iframe scrolling="yes" frameborder="0"  src="${base}/ui/amcs/fin/dip/ma/maDipForm" style="width:100%;height:100%;"></iframe>
    </div>
</div>
</body>

[#include '/WEB-INF/views/common/include_js.ftl']
<script type="text/javascript">
    requirejs(['pvue',"easygridEdit","pub"], function (pvue, $e) {

    });
</script>
</html>
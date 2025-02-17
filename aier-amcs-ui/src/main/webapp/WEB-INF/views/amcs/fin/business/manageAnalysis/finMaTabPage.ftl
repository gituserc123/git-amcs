<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医保管理分析 - 爱尔医院</title>
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

        .tabs-disabled {
            opacity: 0.3;
            filter: alpha(opacity=30);
        }

        .btn-switch {
            float: right;
            margin: 3px 20px 3px 5px!important;
        }
    </style>
</head>

<body>
<div class="tabContBox">
    <input type="button" class="btn btn-small btn-primary mar-5 btn-switch" value="切 换"/>
    <ul class="tabs likeTabs">
        <li class="">
            <a href="#" class="tabs-inner"><span class="tabs-title">医保管理分析DIP</span></a>
        </li>
        <li class="">
            <a href="#" class="tabs-inner"><span class="tabs-title">医保管理分析DRG</span></a>
        </li>
        <!-- <input type="button" class="btn btn-small btn-primary mar-5 btn-switch" value="切 换"/> -->
    </ul>
    <div class="tabCont tabCont-6 tabContHide" >
        <iframe scrolling="yes" frameborder="0" id="dipFrame" src="" data-url="${base}/ui/amcs/fin/dip/ma/maDipForm" style="width:100%;height:100%;"></iframe>
    </div>
    <div class="tabCont tabCont-0 tabContHide ">
        <iframe scrolling="yes" frameborder="0" id="drgFrame" src="" data-url="${base}/ui/amcs/fin/drg/ma/maDrgForm" style="width:100%;height:100%;"></iframe>
    </div>
</div>
<script id="choosePop" type="text/html">
    <form id="choosePopForm" class="soform form-validate form-enter pad-t30" method="post" action="" data-opt="">
        <div class="row">
            <div class="p11">
                <div class="item-one">
                    <label class="lab-item">请选择：</label>
                    <select class="drop easyui-combobox" style="width:100%;" name="chooseVal" id="chooseVal" data-options="editable:false, required:true">
                        <option value="" ></option>
                        <option value="1" >DIP</option>
                        <option value="2" >DRG</option>
                    </select>
                </div>
            </div>
        </div>
        <p class="row-btn center">
            <input type="button" class="btn btn-primary btn-easyFormSubmit btnSubmitChoose" name="btnSubmit" value="确定" />
            <input type="button" class="btn btn-closePop btn-cancel-cus" name="btnCancel" value="取 消" />
        </p>
    </form>
</script>

[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript" src='${base}/static/js/config.require.js?12441'></script>
<script type="text/javascript">
    requirejs(['pub'], function () {
        if('${chooseVal}'){
            initTab('${chooseVal}');
        }else{
            var p = $pop.popTemForm({
                title: "请选择DIP/DRG",
                temId: 'choosePop',
                area: ['400px', '160px'],
                onPop: function ($p) {
                    $('.btnSubmitChoose').click(function () {
                        var formValidate = $("#choosePopForm").form('validate');
                        if(formValidate){
                            var formData = $('#choosePopForm').sovals();
                            initTab(formData.chooseVal);
                            $pop.close(p);
                        }
                    });
                    $(".btn-cancel-cus").click(function () {
                        $pop.close(p)
                    });
                },
                end: function () {},
                beforeSubmit: function (opt, $form, formData) {}
            });
        }

        // 当提交按钮被点击时执行
        $(".btn-switch").click(function(e) {
            $pop.confirm('您确认想要切换医保结算方式吗？',function(r){
                if (r){
                    if('${chooseVal}'){
                        if('${curPeriod}'){
                            $pop.msg('当前季度已填写，不能切换！');
                        }
                        if('${prePeriod}'){
                            var $tabLi = $('.likeTabs li');
                            if($($tabLi[0]).hasClass('tabs-disabled')){
                                initTab('1');
                            }else{
                                initTab('2');
                            }
                        }
                    }else{
                        $pop.msg('当前季度填写中，如需切换，请刷新！');
                    }
                }
            });
        });

    });

    function initTab(chooseVal){
        var $tabLi = $('.likeTabs li');
        var $tabCont = $('div .tabCont');
        if(chooseVal == '1'){
            $("#dipFrame").attr('src',$("#dipFrame").data("url"));
            $("#drgFrame").attr('src','');
            $($tabCont[1]).addClass('tabContHide');
            $($tabCont[0]).removeClass('tabContHide');
            $($tabLi[0]).removeClass('tabs-disabled');
            $($tabLi[1]).addClass('tabs-disabled');
        }
        if(chooseVal == '2'){
            $("#drgFrame").attr('src',$("#drgFrame").data("url"));
            $("#dipFrame").attr('src','');
            $($tabCont[0]).addClass('tabContHide');
            $($tabCont[1]).removeClass('tabContHide');
            $($tabLi[1]).removeClass('tabs-disabled');
            $($tabLi[0]).addClass('tabs-disabled');
        }
    }

</script>
</body>

</html>

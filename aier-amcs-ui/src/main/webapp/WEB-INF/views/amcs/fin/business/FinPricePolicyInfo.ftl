<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>价格政策 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">

</style>

<script type="text/javascript">
</script>
<body>
<form id="finPricePolicyForm" class="soform formA form-validate pad-t20 finPricePolicy"
      data-opt="{beforeCallback:'submitEditForm'}">
    <input type="hidden" id="id" name="id" value="${id!}"/>
    <input type="hidden" id="hospId" name="hospId" value="${hospId!}"/>
    <input type="hidden" id="hospName" name="hospName" value="${hospName!}"/>
    [#if id == null]
        <input id="btnPricePolicyImport" onclick="(function (){
            formPricePolicyImportPop = $pop.popTemForm({
                    title: '查看历史填报',
                    temId: 'pricePolicyImportLastList',
                    data: null,
                    area: ['800px', '600px'],
                    onPop: function () {
                        $grid.newGrid('#pricePolicyImportGridBox', {
                        fitColumns: true,
                        pagination:false,
                        height:'560px',
                        columns: [
                        [
                            {
                                title: '操作',
                                field: 'op',
                                width: 30,
                                formatter: function (v, row, index) {
                                    let strCls='s-op s-op-pricePolicyImport icon-arrow-left';
                                    let strTitle='导入';
                                    htmlStr='&nbsp;&nbsp;'+'<span class=&#34;'+ strCls + '&#34; title=&#34;' + strTitle + '&#34;  rel=&#34;' + index + '&#34;></span>';
                                    return htmlStr;
                                }
                            },
                            {title: 'Id', field: 'id', hidden: true, width: 100},
                            {title:'填报时间', field:'createDate', hidden: false, width:80},
                        ]
                        ],
                        onLoadSuccess: function (data) {
                        $('.s-op-pricePolicyImport').click(function () {
                            var ix = $(this).attr('rel');
                            var row = data.rows[ix];
                            $ajax.post('${base}/ui/service/biz/amcs/fin/finInsPricePolicy/getFinInsPricePolicy',{id:row.id}).then(
                            (data)=>{
                                //不能导入的字段
                                delete data.id;
                                delete data.monthId;
                                delete data.mainId;
                                delete data.hospId;
                                $('#finPricePolicyForm').form('load',data)
                            })
                            $pop.close(formPricePolicyImportPop);
                        });},
                        url:'${base}/ui/service/biz/amcs/fin/finInsPricePolicy/findList',
                    });
                }
            });
        })()"
        type="button" class="btn btn-primary" name="btnPricePolicyImport" noclosepop="true" value="从历史填报数据导入"/>
    [/#if]
    <h2 class="h2-title-a">
        <span class="s-title">医保协议定价等级</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">医保协议定价等级：</label>
                <input class="txt txt-validate" type="text" id="ybxyDjdj" name="ybxyDjdj"
                       value="${pricePolicy.ybxyDjdj!}" validType="maxlength[20]" autocomplete="off"
                       data-options="required:true"/>
            </div>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">医院价格体系</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">国际医疗价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="gjYlj" id="gjYlj"
                        data-options="editable:false, required:true, value:'${pricePolicy.gjYlj}'">
                    <option value="1" [#if pricePolicy.gjYlj==1]selected="selected"[/#if] >有</option>
                    <option value="2" [#if pricePolicy.gjYlj==2]selected="selected"[/#if] >无</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">VIP价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="vipJg" id="vipJg"
                        data-options="editable:false, required:true, value:'${pricePolicy.vipJg}'">
                    <option value="1" [#if pricePolicy.vipJg==1]selected="selected"[/#if] >有</option>
                    <option value="2" [#if pricePolicy.vipJg==2]selected="selected"[/#if] >无</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">自主定价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="zzDj" id="zzDj"
                        data-options="editable:false, required:true, value:'${pricePolicy.zzDj}'">
                    <option value="1" [#if pricePolicy.zzDj==1]selected="selected"[/#if] >有</option>
                    <option value="2" [#if pricePolicy.zzDj==2]selected="selected"[/#if] >无</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">医保支付价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="ybZfj" id="ybZfj"
                        data-options="editable:false, required:true, value:'${pricePolicy.ybZfj}'">
                    <option value="1" [#if pricePolicy.ybZfj==1]selected="selected"[/#if] >有</option>
                    <option value="2" [#if pricePolicy.ybZfj==2]selected="selected"[/#if] >无</option>
                </select>
            </div>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">诊疗项目执行公立医院医改价</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">诊疗项目执行公立医院医改价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="zlxmZxGlyyygj" id="zlxmZxGlyyygj"
                        data-options="editable:false, required:true, value:'${pricePolicy.zlxmZxGlyyygj}'">
                    <option value="1" [#if pricePolicy.zlxmZxGlyyygj==1]selected="selected"[/#if] >是</option>
                    <option value="2" [#if pricePolicy.zlxmZxGlyyygj==2]selected="selected"[/#if] >否</option>
                </select>
            </div>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">药品价格政策</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">药品采购价零加成：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="ypCgjljc" id="ypCgjljc"
                        data-options="editable:false, required:true, value:'${pricePolicy.ypCgjljc}'">
                    <option value="1" [#if pricePolicy.ypCgjljc==1]selected="selected"[/#if] >是</option>
                    <option value="2" [#if pricePolicy.ypCgjljc==2]selected="selected"[/#if] >否</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">药品中标价零加成：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="ypZbjljc" id="ypZbjljc"
                        data-options="editable:false, required:true, value:'${pricePolicy.ypZbjljc}'">
                    <option value="1" [#if pricePolicy.ypZbjljc==1]selected="selected"[/#if] >是</option>
                    <option value="2" [#if pricePolicy.ypZbjljc==2]selected="selected"[/#if] >否</option>
                </select>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-lc">
                <label class="lab-item">药品加价政策：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%" dataOptions="required:ture"
                          noNull="请填写药品加价政策" id="ypJjzc" name="ypJjzc" cols="200"
                          row="10">${pricePolicy.ypJjzc!}</textarea>
            </div>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">医用耗材价格政策</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">耗材采购价零加成：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="hcCgjljc" id="hcCgjljc"
                        data-options="editable:false, required:true, value:'${pricePolicy.hcCgjljc}'">
                    <option value="1" [#if pricePolicy.hcCgjljc==1]selected="selected"[/#if] >是</option>
                    <option value="2" [#if pricePolicy.hcCgjljc==2]selected="selected"[/#if] >否</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">耗材中标价零加成：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="hcZbjljc" id="hcZbjljc"
                        data-options="editable:false, required:true, value:'${pricePolicy.hcZbjljc}'">
                    <option value="1" [#if pricePolicy.hcZbjljc==1]selected="selected"[/#if] >是</option>
                    <option value="2" [#if pricePolicy.hcZbjljc==2]selected="selected"[/#if] >否</option>
                </select>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-lc">
                <label class="lab-item">耗材加价政策：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%" dataOptions="required:ture"
                          noNull="请填写耗材加价政策" id="hcJjzc" name="hcJjzc" cols="200"
                          row="10">${pricePolicy.hcJjzc!}</textarea>
            </div>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">屈光、视光项目执行指导价情况</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">屈光手术执行省区指导价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="qgssZxsqzdj" id="qgssZxsqzdj"
                        data-options="editable:false, required:true, value:'${pricePolicy.qgssZxsqzdj}'">
                    <option value="1" [#if pricePolicy.qgssZxsqzdj==1]selected="selected"[/#if] >符合省区指导价区间</option>
                    <option value="2" [#if pricePolicy.qgssZxsqzdj==2]selected="selected"[/#if] >部分项目低于省区全年最大优惠价格</option>
                </select>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-ld">
                <label class="lab-item">屈光手术低于指导价因哪些促销活动：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%" dataOptions=""
                          validType="maxlength[300]" id="qgssDyzdjSynxcxhd" name="qgssDyzdjSynxcxhd" cols="200"
                          row="10">${pricePolicy.qgssDyzdjSynxcxhd!}</textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one solab-lc">
                <label class="lab-item">视光诊疗项目执行集团指导价：</label>
                <select class="drop easyui-combobox" style="width:100%;" name="sgzlxmZxjtzdj" id="sgzlxmZxjtzdj"
                        data-options="editable:false, required:true, value:'${pricePolicy.sgzlxmZxjtzdj}'">
                    <option value="1" [#if pricePolicy.sgzlxmZxjtzdj==1]selected="selected"[/#if] >基本医疗服务项目执行医保协议价，自费项目执行或高于集团指导价</option>
                    <option value="2" [#if pricePolicy.sgzlxmZxjtzdj==2]selected="selected"[/#if] >基本医疗服务项目执行医保协议价，部分自费项目价格低于集团指导价</option>
                    <option value="3" [#if pricePolicy.sgzlxmZxjtzdj==3]selected="selected"[/#if] >视光相关诊疗项目自主定价，部分项目低于集团指导价</option>
                    <option value="4" [#if pricePolicy.sgzlxmZxjtzdj==4]selected="selected"[/#if] >视光相关诊疗项目自主定价</option>
                </select>
            </div>
        </div>
        <div class="p6">
            <div class="item-one solab-ld">
                <label class="lab-item">视光项目低于指导价因哪些促销活动：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:100%" dataOptions=""
                          id="sgzlxmDyzdjSynxcxhd" validType="maxlength[100]" name="sgzlxmDyzdjSynxcxhd" cols="200"
                          row="10">${pricePolicy.sgzlxmDyzdjSynxcxhd!}</textarea>
            </div>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">当地医保三大目录中今年新增眼科相关收费项目</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div>
        <div class="p6">
            <div class="item-one solab-lc">
                <label class="lab-item">当地医保三大目录中今年新增眼科相关收费项目：</label>
                <textarea class="txta txt-validate adaptiveTextarea" style="width:1000px" dataOptions=""
                          id="ddybsdmlzJnxzykXgsfxm" name="ddybsdmlzJnxzykXgsfxm" cols="200"
                          row="10">${pricePolicy.ddybsdmlzJnxzykXgsfxm!}</textarea>
            </div>
        </div>
    </div>
    <p class="row-btn pad-t5">
        <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="保 存"/>
        <input type="button" class="btn btn-cancel-cus" name="btnCancel" value="取 消"/>
    </p>
</form>
<script id="pricePolicyImportLastList" type="text/html">
    <div class="cont-grid">
        <div id="pricePolicyImportGridBox"></div>
    </div>
</script>
</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['lodash', 'pub'], function (_) {

        window.submitEditForm = function (opt, $form, data) {

            $ajax.post({
                url: '${base}/ui/service/biz/amcs/fin/finInsPricePolicy/save',
                data: data,
                tip: '你确定提交吗？',
                jsonData: true,//是否采用jsonData格式提交
                sync: false,//是否同步方式提交
                type: 'post',//采用post方式提交
                loadingMask: true,//进行异步请求中，是否显示mask
                calltip: true,//提交成功后显示请求成功信息
                success: function (rst) {
                    window.location.href='${base}/ui/service/biz/amcs/fin/finInsPricePolicy/pricePolicyInfo';
                    /*if(rst.code==='200'||rst.code==='201'){
                        $pop.closePop();
                        window.parent.window.reflash = true;
                    }*/
                },//请求成功后，code===200或者201返回事件
                callback: function (rst) {
                },//请求成功后返回事件
                cancelback: function () {
                },//确认框点取消返回事件
                errback: function (req, textStatus, errorThrown) {
                    //$pop.alert('保存失败111！');
                }//出现错误时返回事件
            });
            return false;
        };

        $(".btn-cancel-cus").click(function () {
            $pop.closeTabWindow('价格政策填报');
        });

    });

</script>



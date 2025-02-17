<!DOCTYPE html>
<html>
<style type="text/css">
    [v-cloak]{
        display: none;
    }
    .tabs-panels{
        border: 0!important;
        margin-top: 30px!important;
    }
    .tabs-header{
        position: fixed!important;
        z-index: 999!important;
    }
    #insTab{
        position: relative;
        z-index: 3;
    }
    .layui-layer.layui-layer-tips .layui-layer-close1 {
        background-position: -188px -40px !important;
    }
    .t-css{
        text-align: left !important;
        width: 249px !important;
    }
    .r-css{
        margin: 0 0px 0 30px!important;
    }
</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    <title>月度上报表 - 爱尔医院</title>

</head>
<body>
<div @mounted="mounted"  style="width:100%">
    <div id="insTab" class="easyui-tabs win-mask" style="width:100%">
        <div title="主表" id="main-tab" style="padding:10px;">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/main.ftl']
        </div>
        <div title="按项目付费" disabled id="project-tab" style="padding:10px;">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/project.ftl']
        </div>
        <div title="总额预付"  disabled id="total-tab" style="padding:10px;">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/total.ftl']
        </div>
        <div title="单病种付费" disabled id="single-tab" style="padding:10px;">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/single.ftl']
        </div>
        <div title="DIP付费"  disabled id="dip-tab" style="padding:10px;" class="dip-win-mask">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/dip.ftl']
        </div>
        <div title="DRG付费"  disabled id="drg-tab" style="padding:10px;">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/drg.ftl']
        </div>
        <div title="按人头付费"  disabled id="perTime-tab" style="padding:10px;">
            [#include '/WEB-INF/views/amcs/fin/business/subReport/perTime.ftl']
        </div>
    </div>
</div>

</body>

[#include '/WEB-INF/views/common/include_js.ftl']
<script type="text/javascript">
    requirejs(['pvue',"easygridEdit","pub"], function (pvue, $e) {
        const {createApp, reactive } = pvue;


        createApp({
            // toggleRecord(item){
            //     item.intend = !item.intend;
            // },
            //主表数据
            main: {},
            //项目付费
            project:{},
            //总额付费
            total:{},
            //单病种付费
            single:{},
            //dip
            dip:{},
            //drg
            drg:{},
            //按人头
            perTime:{},
            get isDisabled(){
              if(${status}==1||${status}==9){
                  return false
              }  else{
                  return true
              }
            },
            showTab(){
                let policyData
                if(this.main.hospSettlementPolicy){
                     policyData=  Array.from(this.main.hospSettlementPolicy)
                }

                for (var i = 1; i <= 6; i++) {
                    $('#insTab').tabs('disableTab', i);
                }
                if (policyData){
                    policyData.forEach((v,i)=>{

                        $('#insTab').tabs('enableTab', +v);
                    })
                }
            },
            mainExport(){
                [#--var subData--]
                [#--$ajax.postSync("${base}/ui/service/biz/amcs/fin/finInsMain/lastList",null).then(--]
                [#--    res=>{--]
                [#--        subData=res--]
                [#--    }--]
                [#--)--]
                formPop = $pop.popTemForm({
                    title: '查看历史填报',
                    temId: 'mainLastList',
                    data: null,
                    area: ['800px', '600px'],
                    onPop: function () {
                        // $("#typeCode").val($("#paraType").getVal());
                        $grid.newGrid("#mainGridBox", {
                            fitColumns: true,
                            pagination:false,
                            height:'530px',
                            columns: [
                                [

                                    {
                                        title: "操作",
                                        field: "op",
                                        width: 90,
                                        formatter: function (v, row, index) {

                                            htmlStr="&nbsp;&nbsp;"+'<span class="s-op s-op-mainExport icon-arrow-left" title="导入" rel="' + index + '"></span>'

                                            return htmlStr
                                        }
                                    },
                                    {title: "Id", field: "id", hidden: true, width: 100},
                                    {title: "统筹区", field: "poolingArea", hidden: false, width: 100},
                                    {title: "类型", field: "typeName", hidden: false, width: 100},
                                    {title: "创建时间", field: "createDate", hidden: false, width: 100},
                                ]
                            ],
                            onLoadSuccess: function (data) {
                                $('.s-op-mainExport').click(function () {
                                    var ix = $(this).attr('rel');
                                    var row = data.rows[ix];

                                    $ajax.post('${base}/ui/service/biz/amcs/fin/finInsMain/getFinInsMain',{id:row.id}).then(
                                        (data)=>{

                                            if(data.hospSettlementPolicy){
                                                data.hospSettlementPolicy=eval(data.hospSettlementPolicy)
                                            }
                                            //不能导入的字段
                                            delete data.id
                                            delete data.receivableBalanceEndPeriod
                                            delete data.receivableCollectionRate
                                            delete data.charityBadDebtAmt
                                            delete data.penaltyDeductionAmount
                                            delete data.penaltyFeeAmount
                                            delete data.insuranceBadDebtAmt
                                            delete data.insuranceType
                                            delete data.poolingArea



                                            $("#main-tab form").form('load',data)
                                            // this.showTab()
                                        }
                                    )
                                    $pop.close(formPop);
                                });
                            },

                            url:"${base}/ui/service/biz/amcs/fin/finInsMain/lastList",
                        })

                    }

                });
            },
            mainClick(title){

            },
            projectClick(title){
                !this.project.id&&$ajax.post('${base}/ui/service/biz/amcs/fin/finInsProjectPay/getByMainId',{mainId:'${mainId}'}).then(
                    (data)=>{
                        this.project=data
                        if(!this.project.mainId){
                            this.project.mainId='${mainId}'
                        }
                        if(!this.project.monthId){
                            this.project.monthId=this.main.monthId
                        }
                        if(!this.project.hospId){
                            this.project.hospId=this.main.hospId
                        }
                        $("#project-tab form").form('load',this.project)
                    }
                )
            },
            totalClick(title){
                !this.total.id&&$ajax.post('${base}/ui/service/biz/amcs/fin/finInsAdvanceTotal/getByMainId',{mainId:'${mainId}'}).then(
                    (data)=>{
                        this.total=data
                        if(!this.total.mainId){
                            this.total.mainId='${mainId}'
                        }
                        if(!this.total.monthId){
                            this.total.monthId=this.main.monthId
                        }
                        if(!this.total.hospId){
                            this.total.hospId=this.main.hospId
                        }
                        $("#total-tab form").form('load',this.total)
                    }
                )
            },
            singleClick(title){
                !this.single.id&&$ajax.post('${base}/ui/service/biz/amcs/fin/finInsSingleDisease/getByMainId',{mainId:'${mainId}'}).then(
                    (data)=>{
                        this.single=data
                        if(!this.single.mainId){
                            this.single.mainId='${mainId}'
                        }
                        if(!this.single.monthId){
                            this.single.monthId=this.main.monthId
                        }
                        if(!this.single.hospId){
                            this.single.hospId=this.main.hospId
                        }
                        $("#single-tab form").form('load',this.single)
                    }
                )
            },
            dipClick(title){
                !this.dip.analysisId&&!this.dip.payId&&$ajax.post('${base}/ui/service/biz/amcs/fin/finDipInfo/getByMainId',{mainId:'${mainId}'}).then(
                    (data)=>{
                        this.dip=data
                        if(!this.dip.mainId){
                            this.dip.mainId='${mainId}'
                        }
                        if(!this.dip.monthId){
                            this.dip.monthId=this.main.monthId
                        }
                        if(!this.dip.hospId){
                            this.dip.hospId=this.main.hospId
                        }
                        $("#dip-tab #dipform").form('load',this.dip)
                    }
                )
            },
            drgClick(title){
                !this.drg.analysisId&&!this.drg.payId&&$ajax.post('${base}/ui/service/biz/amcs/fin/finDrgInfo/getByMainId',{mainId:'${mainId}'}).then(
                    (data)=>{
                        this.drg=data
                        if(!this.drg.mainId){
                            this.drg.mainId='${mainId}'
                        }
                        if(!this.drg.monthId){
                            this.drg.monthId=this.main.monthId
                        }
                        if(!this.drg.hospId){
                            this.drg.hospId=this.main.hospId
                        }
                        $("#drg-tab form").form('load',this.drg)
                    }
                )
            },
            perTimeClick(title){
                !this.perTime.id&&$ajax.post('${base}/ui/service/biz/amcs/fin/finInsPertimePay/getByMainId',{mainId:'${mainId}'}).then(
                    (data)=>{
                        this.perTime=data
                        if(!this.perTime.mainId){
                            this.perTime.mainId='${mainId}'
                        }
                        if(!this.perTime.monthId){
                            this.perTime.monthId=this.main.monthId
                        }
                        if(!this.perTime.hospId){
                            this.perTime.hospId=this.main.hospId
                        }
                        $("#perTime-tab form").form('load',this.perTime)
                    }
                )
            },
            mainSubmit(){
                if (!$("#main-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                const formData=$("#main-tab form").sovals()
                this.main=formData
                //处理医保政策数组逻辑，如果单个也保存成数组，并去掉逗号
                if(typeof(this.main.hospSettlementPolicy)=='string'){
                    this.main.hospSettlementPolicy=new Array(this.main.hospSettlementPolicy)
                }else{
                    this.main.hospSettlementPolicy=this.main.hospSettlementPolicy.filter(Boolean)
                }

                this.showTab()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finInsMain/save",formData,"是否提交数据？",true)
            },
            mainSave(){
                const formData=$("#main-tab form").sovals()
                formData["mainId"]=formData["id"]
                formData["formName"]="main"
                formData["modifyDate"]=new Date()
                this.showTab()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finInsMain/saveToRedis",formData,"是否暂存数据？",true)
            },
            projectSubmit(){
                if (!$("#project-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                const formData=$("#project-tab form").sovals()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finInsProjectPay/saveFinInsProjectPay",formData,"是否提交项目数据？",true).then((data)=>{this.projectClick('project')});
            },
            totalSubmit(){
                if (!$("#total-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                const formData=$("#total-tab form").sovals()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finInsAdvanceTotal/saveFinInsAdvanceTotal",formData,"是否提交总额预付费数据？",true).then((data)=>{this.totalClick('total')});
            },
            singleSubmit(){
                if (!$("#single-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                const formData=$("#single-tab form").sovals()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finInsSingleDisease/saveFinInsSingleDisease",formData,"是否提交单病种付费数据？",true).then((data)=>{this.singleClick('single')});
            },
            dipSubmit(){
                if (!$("#dip-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                if (!$('input[name="feedbackToManagement"]:checked').length) {
                    $pop.alert('是否向医院ceo、院长、临床科室等书面反馈,请至少选择一个选项。');
                    return false; // 阻止表单提交
                }
                const formData=$("#dip-tab form").sovals()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finDipInfo/saveFinInsDipInfo",formData,"是否提交DIP数据？",true).then((data)=>{this.dipClick('dip')});
            },
            drgSubmit(){
                if (!$("#drg-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                if (!$('input[name="feedbackToManagement"]:checked').length) {
                    $pop.alert('是否向医院ceo、院长、临床科室等书面反馈,请至少选择一个选项。');
                    return false; // 阻止表单提交
                }
                const formData=$("#drg-tab form").sovals()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finDrgInfo/saveFinInsDrgInfo",formData,"是否提交DRG数据？",true).then((data)=>{this.drgClick('drg')});
            },
            perTimeSubmit(){
                if (!$("#perTime-tab form").form('validate')){
                    throw new Error("验证失败")
                }
                const formData=$("#perTime-tab form").sovals()
                $ajax.post("${base}/ui/service/biz/amcs/fin/finInsPertimePay/saveFinInsPertimePay",formData,"是否提交按人头付费数据？",true).then((data)=>{this.perTimeClick('perTime')});
            },
            mounted(){
                let redisData={}
                redisData["mainId"]='${mainId}'
                redisData["formName"]="main"
                $ajax.postSync("${base}/ui/service/biz/amcs/fin/finInsMain/loadFromRedis",redisData,false,true).then(
                    res=>{
                        redisData=res
                    }
                )
                $ajax.post('${base}/ui/service/biz/amcs/fin/finInsMain/getFinInsMain',{id:'${mainId}'}).then(
                    (data)=>{
                        // console.log(new Date(redisData["modifyDate"]),new Date(data["modifyDate"]),new Date(redisData["modifyDate"])>new Date(data["modifyDate"]))
                        // 如果暂存数据的时间比较新，用暂存的数据加载，否则用数据库中数据加载
                        if (new Date(redisData["modifyDate"])>new Date(data["modifyDate"])){
                            this.main=redisData
                        }else{
                            this.main=data
                        }

                        if(data.hospSettlementPolicy){
                            this.main.hospSettlementPolicy=eval(data.hospSettlementPolicy)
                        }
                        $("#main-tab form").form('load',this.main)
                        this.showTab()
                    }
                )


                $(".easyui-tabs").tabs({
                    onSelect:(title,index)=>{
                        switch (index) {
                            case 0:
                                this.mainClick(title);
                                break;
                            case 1:
                                this.projectClick(title);
                                break;
                            case 2:
                                this.totalClick(title);
                                break;
                            case 3:
                                this.singleClick(title);
                                break;
                            case 4:
                                this.dipClick(title);
                                break;
                            case 5:
                                this.drgClick(title);
                                break;
                            case 6:
                                this.perTimeClick(title);
                                break;
                            default:
                                break;
                        }
                    }
                })
            }
        }).mount();

        [#include "/WEB-INF/views/amcs/fin/business/subReport/dip_js.ftl"]
        [#include "/WEB-INF/views/amcs/fin/business/subReport/drg_js.ftl"]
        [#include "/WEB-INF/views/amcs/fin/business/subReport/single_js.ftl"]
    })
</script>
</html>
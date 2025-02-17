<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>信息填报 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
    .p6{
        text-align: right;
        line-height: initial;
    }
    .outpatientVolume{

        padding: 0px 20px 0px 20px;
    }
    .inHospital {

        padding: 0px 20px 0px 20px;
    }
    .hospitalSurgery  {

        padding: 0px 20px 0px 20px;
    }
    .outpatientSurgery   {

        padding: 0px 20px 0px 20px;
    }
    .statisticsDate {
        font-size: 20px;
        color: #009ADA;
        font-weight: bold;
        text-align: center;
        padding: 20px;
        margin-bottom: 2px;
        border-bottom: 2px solid #dfe2e6;
    }

    .h2-title {
        font-size: 16px;
        text-align: center;
        color: #009ADA;
        padding-bottom: 20px;
    }

    .info-wrap {
        margin-top: 0px;
    }
</style>

<script type="text/javascript">
    var caculate = function () {
        if ($('#morningOutpatient').getVal() > 0) {
            $('#a').setVal(1)
        } else {
            $('#a').setVal(0)
        }
        if ($('#morningOutpatient').getVal() >= 15) {
            $('#c').setVal(1)
        } else {
            $('#c').setVal(0)
        }

        if ($('#afternoonOutpatient').getVal() > 0) {
            $('#b').setVal(1)
        } else {
            $('#b').setVal(0)
        }
        if ($('#afternoonOutpatient').getVal() >= 15) {
            $('#d').setVal(1)
        } else {
            $('#d').setVal(0)
        }
        $("#totalOutpatient").setVal(_.toNumber($('#morningOutpatient').getVal())+_.toNumber($('#afternoonOutpatient').getVal()))
        $("#e").setVal(_.toNumber($('#morningOutpatient').getVal())*_.toNumber($('#c').getVal()))
        $("#f").setVal(_.toNumber($('#afternoonOutpatient').getVal())*_.toNumber($('#d').getVal()))

    }
</script>
<body>
<div class="searchHead bob-line">


    <div style="float: right;margin-right: 10px">
        <button type="button" class="btn btn-primary btn-save" >提交
        </button>
        <button type="button" class="btn btn-cancel" >取消
        </button>
    </div>


</div>

<div class="main">

    <div class="statisticsDate">${hospName}&nbsp;&nbsp;&nbsp;&nbsp;${statisticsDate}</div>

    <form class="form-validate form-enter pad-t20 doctor-form" method="post"
          action="${base}/ui/amcs/jtr/doctorStatistics/saveDoctorStatistics">
        <div class="row hide">
            <input id="id" name="id" class="txt">
            <input name="hospId" value="${hospId}">
            <input name="hospName" value="${hospName}">
            <input name="statisticsDate" value="${statisticsDate}">
        </div>
        <div class="info-wrap row">
            <div class="outpatientVolume p4">
                <h2 class="h2-title">门诊量</h2>

                <div class="row mar-b5">
                    <div class="p4">
                        <label class="lab-item solab-s"> &nbsp;</label>
                    </div>
                    <div class="p4">
                        <label class="lab-item solab-s"> 上午</label>

                    </div>
                    <div class="p4">
                        <label class="lab-item solab-s">下午</label>
                    </div>

                </div>
                <div class="row">
                    <div class="p3" style="text-align: right">

                        <label class="lab-item solab-s">排班单元数：</label>


                    </div>
                    <div class="p4 item-one solab-s">

                        <input id="a" class="txt" disabled type="text"
                               name="a"
                               value=0
                               style="width:90%"/>

                    </div>
                    <div class="p4 item-one solab-s">

                        <input id="b" class="txt" disabled type="text"
                               name="b"
                               value=0
                               style="width:90%"/>


                    </div>
                </div>

                <div class="row">
                    <div class="p3" style="text-align: right">
                        <label class="lab-item solab-s" >实际门诊量：</label>
                    </div>
                    <div class="p4">
                        <div class="item-one solab-s">
                            <input id="morningOutpatient" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="morningOutpatient"
                                   value=0
                                   data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0,onChange:caculate"
                                   style="width:90%"/>
                        </div>
                    </div>
                    <div class="p4">
                        <div class="item-one solab-s">
                            <input id="afternoonOutpatient" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="afternoonOutpatient"
                                   value=0
                                   data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0,onChange:caculate"
                                   style="width:90%"/>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="p3" style="text-align: right">
                        <label class="lab-item solab-s">有效单元数：</label>
                    </div>
                    <div class="p4">
                        <div class="item-one solab-s">
                            <input id="c" class="txt" disabled type="text"
                                   name="c"
                                   value=0
                                   style="width:90%"/>
                        </div>
                    </div>
                    <div class="p4">
                        <div class="item-one solab-s">
                            <input id="d" class="txt" disabled type="text"
                                   name="d"
                                   value=0
                                   style="width:90%"/>

                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="p3" style="text-align: right">
                        <label class="lab-item solab-s">有效单元门诊量：</label>
                    </div>
                    <div class="p4">
                        <div class="item-one solab-s">
                            <input id="e" class="txt" disabled type="text"
                                   name="e"
                                   value=0
                                   style="width:90%"/>
                        </div>
                    </div>
                    <div class="p4">
                        <div class="item-one solab-s">
                            <input id="f" class="txt" disabled type="text"
                                   name="f"
                                   value=0
                                   style="width:90%"/>

                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="p3" style="text-align: right">
                        <label class="lab-item solab-s">总门诊量：</label>
                    </div>
                    <div class="p8">
                        <div class="item-one solab-s">
                            <input id="totalOutpatient" class="txt" disabled type="text"
                                   name="totalOutpatient"
                                   value=0
                                   style="width:95%"/>
                        </div>
                    </div>

                </div>
            </div>

            <div class="other p8">
                <div class="inHospital p4">
                    <h2 class="h2-title">住院人次</h2>

                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">总住院人次：</label>
                        </div>
                        <div class="p5">
                            <input  id="totalHospitalization" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="totalHospitalization"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            />
                        </div>


                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本病种住院人次：</label>
                        </div>
                        <div class="p5">
                            <input  id="basicHospitalization" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="basicHospitalization"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            />
                        </div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本病种覆盖数：</label></div>
                        <div class="p5">
                            <input  id="realBasicHosSpecies" class="txt" disabled type="text"
                                   name="realBasicHosSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难病种住院人次：</label></div>
                        <div class="p5">
                            <input  id="difficultHospitalization" class="easyui-numberbox required" noNull="必填"
                                   type="text"
                                   name="difficultHospitalization"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难病种覆盖数：</label></div>
                        <div class="p5">
                            <input  id="realBasicHosSpecies" class="txt" disabled type="text"
                                   name="realBasicHosSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本病种数：</label></div>
                        <div class="p5">
                            <input  id="basicHosSpecies" class="txt" disabled type="text"
                                   name="basicHosSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本病种覆盖率：</label></div>
                        <div class="p5">
                            <input  id="Coverage1" class="txt" disabled type="text"
                                   name="Coverage1"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难病种数：</label></div>
                        <div class="p5">
                            <input  id="difficultHosSpecies" class="txt" disabled type="text"
                                   name="difficultHosSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难病种覆盖率：</label></div>
                        <div class="p5">
                            <input  id="Coverage2" class="txt" disabled type="text"
                                   name="Coverage2"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                </div>
                <div class="hospitalSurgery p4">
                    <h2 class="h2-title">住院手术量</h2>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">总手术人次：</label></div>
                        <div class="p5">
                            <input  id="totalOperation" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="totalOperation"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术人次：</label></div>
                        <div class="p5">
                            <input  id="basicOperation" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="basicOperation"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术覆盖数：</label></div>
                        <div class="p5">
                            <input  id="realBasicOpSpecies" class="txt" disabled type="text"
                                   name="realBasicOpSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术人次：</label></div>
                        <div class="p5">
                            <input  id="difficultOperation" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="difficultOperation"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"/>
                        </div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术覆盖数：</label></div>
                        <div class="p5">
                            <input  id="realDifficultOpSpecies" class="txt" disabled type="text"
                                   name="realDifficultOpSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术种类：</label></div>
                        <div class="p5">
                            <input  id="basicOpSpecies" class="txt" disabled type="text"
                                   name="basicOpSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术覆盖率：</label></div>
                        <div class="p5">
                            <input  id="Coverage3" class="txt" disabled type="text"
                                   name="Coverage3"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术种类：</label></div>
                        <div class="p5">
                            <input  id="difficultOperationSpecies" class="txt" disabled type="text"
                                   name="difficultOperationSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术覆盖率：</label></div>
                        <div class="p5">
                            <input  id="Coverage4" class="txt" disabled type="text"
                                   name="Coverage4"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                </div>
                <div class="outpatientSurgery p4">
                    <h2 class="h2-title">门诊手术量</h2>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">总手术人次：</label></div>
                        <div class="p5">
                            <input  id="totalOpOperation" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="totalOpOperation"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术人次：</label></div>
                        <div class="p5">
                            <input  id="opBasicOperation" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="opBasicOperation"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术覆盖数：</label></div>
                        <div class="p5">
                            <input  id="opRealBasicOpSpecies" class="txt" disabled type="text"
                                   name="opRealBasicOpSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术人次：</label></div>
                        <div class="p5">
                            <input  id="opDifficultOpSpecies" class="easyui-numberbox required" noNull="必填" type="text"
                                   name="opDifficultOpSpecies"
                                   value=0 data-options="missingMessage:'必填',required:true,min:0,max:200,precision:0"
                                   style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术覆盖数：</label></div>
                        <div class="p5">
                            <input  id="opRealDifficultOpSpecies" class="txt" disabled type="text"
                                   name="opRealDifficultOpSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术种类：</label></div>
                        <div class="p5">
                            <input  id="opBasicOpSpecies" class="txt" disabled type="text"
                                   name="opBasicOpSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">基本手术覆盖率：</label></div>
                        <div class="p5">
                            <input  id="Coverage5" class="txt" disabled type="text"
                                   name="Coverage5"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术种类：</label></div>
                        <div class="p5">
                            <input  id="opDifficultOperationSpecies" class="txt" disabled type="text"
                                   name="opDifficultOperationSpecies"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                    <div class="item-one solab-l">
                        <div class="p6">
                            <label class="lab-item solab-l">疑难手术覆盖率：</label></div>
                        <div class="p5">
                            <input  id="Coverage6" class="txt" disabled type="text"
                                   name="Coverage6"
                                   value=0 style="width: 100%"
                            /></div>
                    </div>
                </div>
            </div>
        </div>
        <input id="submitA" type="button" class="btn btn-primary btn-easyFormSubmit hidden" name="btnSubmit"
               value="保 存"/>
    </form>


</div>


</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">

    requirejs(["lodash", 'pub'], function (_) {
            //批量赋值
        var resultData;
        $ajax.post({
            url:"${base}/ui/amcs/jtr/doctorStatistics/getDoctorStatistics",
            data: {statisticsDate:'${statisticsDate}',userCode: '${userCode}',hospId: ${hospId}},
            calltip: false,
            sync:true,
            callback : function(rst){
                resultData=rst[0]

            },

        })
            if (resultData){
                for (var key in resultData) {
                    if($("#" + key + "").length > 0){
                        if(resultData[key]){
                            $("#" + key + "").setVal(resultData[key]);
                        }else{
                            $("#" + key + "").setVal("0");
                        }
                    }


                }

                if(resultData.statisticsType=="1"){
                    $("#id").setVal("");
                }
            }



            $('.btn-save').click(function () {

                $("#submitA").click();
                [#--let personData = $('form.doctor-form').sovals();--]

                [#--$ajax.post({--]
                [#--    url: "${base}/ui/amcs/doctorStatistics/saveDoctorStatistics",//请求地址--]
                [#--    data: personData,//请求数据--]
                [#--    tip: true,//提交是否需要确认，true或string需要确认--]
                [#--    jsonData: true,//是否采用jsonData格式提交--]
                [#--    sync: false,//是否同步方式提交--]
                [#--    type: 'post',//采用post方式提交--]
                [#--    loadingMask: true,//进行异步请求中，是否显示mask--]
                [#--    calltip: true,//提交成功后显示请求成功信息--]
                [#--    success: function (rst) {--]
                [#--        if (rst.code === '200' || rst.code === '201') {--]
                [#--            $pop.alert(rst.msg);--]

                [#--        }--]
                [#--    },//请求成功后，code===200或者201返回事件--]
                [#--    callback: function (rst) {--]
                [#--    },//请求成功后返回事件--]
                [#--    cancelback: function () {--]
                [#--    },//确认框点取消返回事件--]
                [#--    errback: function (req, textStatus, errorThrown) {--]
                [#--        $pop.alert('保存失败！');--]
                [#--    }//出现错误时返回事件--]
                [#--});--]
            });

        }
    )
    ;

</script>



<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>门诊患者不良事件</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
    [#include "/WEB-INF/views/amcs/adverseEvent/common/css.ftl"]
    <style>

        .common-table .no-shadow {
            box-shadow: none;
        }

        .not-allow {
            background-color: #eeeeee;
            cursor: not-allowed;
        }

        .common-table {
            text-align: center;
            background-color: #e8f6fd;
            border: solid #d3d3d3;
            border-width: 1px 0px 0px 1px;
            margin-left: 50px;
        }

        .common-table td {
            height: 26px;
            border: solid #d3d3d3;
            border-width: 0px 1px 1px 0px;
        }

        .common-table td input, .common-table td .textbox {
            border: 0;
        }

        .common-table > thead {
            font-weight: bold;
        }

        .common-table > thead, .common-table tr > td:first-child, .common-table .hasBG {
            color: #000000;
            background-color: #e8f6fd;
        }

        .common-table .textbox.numberbox,
        .common-table .textbox.combo,
        .common-table .textbox-text.validatebox-text.validatebox-readonly,
        .common-table .textbox-text.validatebox-text {
            width: 100% !important;
        }

        .common-table .item-group .textbox.numberbox {
            width: 50px !important;
        }

        .common-table input[type="text"]:focus {
            position: static;
        }

        .examination-form h4 {
            margin-bottom: 5px;
        }

        .examination-form .row {
            margin: 0 0 10px 0;
        }

        .item-nomargin .lab-inline {
            margin: 0;
        }

        .bottom-line {
            border-bottom: 5px solid #dfe2e6;
        }

        .cont-grid-fixL {
            position: relative;
            padding-left: 300px;
        }

        .cont-grid-fix-l {
            position: absolute;
            left: 0;
            top: 80px;
            bottom: 0;
            width: 350px;
        }

        .cont-grid-fix-l .area-a {
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
        }

        .cont-grid-fix-l .area-b {
            position: absolute;
            top: 50%;
            left: 0;
            bottom: 0;
            right: 0;
        }

        .datagrid-view-top {
            position: absolute;
            top: 0;
            bottom: 50%;
            left: 0;
            right: 0;
            border-bottom: 5px solid #dfe2e6;
        }

        .datagrid-view-bottom {
            position: absolute;
            bottom: 0;
            top: 50%;
            left: 0;
            right: 0;
        }

        .mask-historyCont {
            position: absolute;
            top: 0px;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #eee;
            filter: alpha(opacity=60);
            opacity: 0.6;
            z-index: 15;
        }

        .mask-hide {
            display: none;
        }

        .cont-grid-fix-r {
            position: absolute;
            left: 350px;
            top: 80px;
            bottom: 0;
            right: 0;
        }

        .width-fixed {
            width: 1130px;
        }

        .basicexam_table td {
            padding: 5px 10px;
        }

        .patient_list {
            position: absolute;
            top: 20px;
        }

        .h2-form {
            overflow: hidden;
        }

        .h2-form .s-t {
            float: left;
            width: 50%;
            text-indent: 3.7em;
            font-size: 1.18em;
            color: #00A0E9;
        }

        .item-one {
            padding-bottom: 4px;
        }

        .lightPosBox {
            display: none;
            width: 120px;
            height: 120px;
            border-right: 1px solid #eee;
            border-bottom: 1px solid #eee;
        }

        .colorBox {
            display: none;
        }

        .s-oc {
            display: -moz-inline-stack;
            display: inline-block;
            *display: inline;
            *zoom: 1;
            width: 39px;
            height: 39px;
            text-align: center;
            line-height: 39px;
            cursor: hand;
            cursor: pointer;
            font-size: 1.5em;
            font-family: "Microsoft YaHei";
            font-weight: bold;
        }

        .s-oc:hover {
            color: #c00;
        }

        .lightPosBox .s-oc {
            float: left;
            border-left: 1px solid #eee;
            border-top: 1px solid #eee;
        }

        .lightPosBox .s-oc:hover {
            background-color: #eee;
        }

        /*.row-colorF,.row-colorF .lab-item{line-height:42px;}*/
        .row-colorF .s-oc {
            line-height: 20px;
            height: 20px;
        }

        /*.s-sl-v{float: left;width:60%;}*/
        .em-dropAt {
            right: 40px;
            top: 0;
        }

        .eyePressItem {
            padding-bottom: 8px;
        }

        .eyePressItem .item-one {
            height: 26px;
            min-height: 20px;
            overflow: hidden;
            padding-bottom: 0;
        }

        .s-eyePressW-0 {
            position: relative;
        }

        .s-eyePressWOs-0 {
            position: relative;
        }
    </style>
</head>

<body>

[#include "/WEB-INF/views/amcs/adverseEvent/common/tab.ftl"]
<div class="tabCont tabCont-0">
    <form class="soform formA form-validate pad-t10" id="outpForm">
        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper.ftl"]
    </form>
    <form class="soform formA form-validate">
        <h2 class="h2-title-a">
            <span class="s-title">事件经过和疾病诊断</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

        <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline w-80">门诊时间：</label>
                    <input id="visitDate" class="txt txt-validate so-date" type="text" style="width:100px"
                           name="visitDate"
                           dataOptions="editable:false,required:ture, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'"
                           noNull="请填写入院时间" value="[#if ae.visitDate??]${ae.visitDate?date("yyyy-MM-dd")}[/#if]"/>
                </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline  w-80">门诊诊断：</label>
                    <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写入门诊断"
                              style="width:1000px" id="visitDiagnose" name="visitDiagnose" maxlength="500" >${ae.visitDiagnose!}</textarea>
                </span>
        </div>
        <div class="mar-l20">
            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                裸眼远视力
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
                    <span class="fl">
                      <label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
                    </span>
                    <span class="fl w-80">
                      <select class="drop drop-sl-k easyui-combobox" style="width:100%" name="nakedFarVodType"
                              id="nakedFarVodType" value="${ae.nakedFarVodType!}">
                       <option value="1" selected="selected">数值</option>
                       <option value="2">描述</option>
                      </select>
                    </span>
                    <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="nakedFarVod"
                                name="nakedFarVod" value="${ae.nakedFarVod!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#nakedFarVodName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="nakedFarVodName" name="nakedFarVodName"
                               value="${ae.nakedFarVodName!}"/>
                    </span>
                </div>
                <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
					<span class="fl">
                        <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                    </span>
                    <span class="fl w-80">
						<select class="drop drop-sl-k easyui-combobox" style="width:100%" name="nakedFarVosType"
                                id="nakedFarVosType" value="${ae.nakedFarVosType!}">
							<option value="1" selected="selected">数值</option>
							<option value="2">描述</option>
						</select>
					</span>
                    <span class="fl w-120">
						<select class="drop drop-sl-v easyui-combobox farEyesight" style="width:100%" id="nakedFarVos"
                                name="nakedFarVos" value="${ae.nakedFarVos!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#nakedFarVosName').val(record.valueDesc);
						}">
						</select>
						<input class="txt" type="hidden" id="nakedFarVosName" name="nakedFarVosName"
                               value="${ae.nakedFarVosName!}"/>
					</span>
                </div>
            </div>
            <div style="font-size: 14px;display: inline-block;" class="mar-l10 mar-b10">
                <span style="height: 6px;display: inline-block;width: 6px;background: #00a0e9;border-radius: 50%;margin-right: 10px;"></span>
                眼压
                <div class="clearfix mar-l10 mar-b10 mar-t10 p6" style="font-size: 12px;">
					<span class="fl">
						<label class="lab-inline" style="width: 20px;color:#00A0E9">OD</label>
					</span>
                    <div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="iopTypeOd"
                                id="iopTypeOd" value="${ae.iopTypeOd!}"
                                data-options="valueField:'valueCode',textField:'valueDesc'">
                        </select>
                    </div>
                    <div class="s-eyePressW-0 fl">
                        <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px"
                               name="iopOd" id="iopOd1" value="${ae.iopOd!}" data-options="min:1,max:100,precision:1"/>
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="s-eyePressW-1 fl">
                        <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px"
                                name="iopOd" id="iopOd2" value="${ae.iopOd!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#iopOdName').val(record.valueDesc);
						}">
                        </select>
                        <input class="txt" type="hidden" id="iopOdName" name="iopOdName" value="${ae.iopOdName!}"/>
                    </div>
                </div>
                <div class="clearfix mar-l10 mar-b10 p6" style="font-size: 12px;">
                <span class="fl">
                    <label class="lab-inline" style="width: 20px;color:#00A0E9">OS</label>
                </span>
                    <div class="fl">
                        <select class="drop easyui-combobox drop-eyePressExamType" style="width:100px" name="iopTypeOs"
                                id="iopTypeOs" value="${ae.iopTypeOs!}"
                                data-options="valueField:'valueCode',textField:'valueDesc'">
                        </select>
                    </div>
                    <div class="s-eyePressWOs-0 fl">
                        <input class="txt txt-pressExamNum easyui-numberspinner" type="text" style="width:200px"
                               name="iopOs" id="iopOs1" value="${ae.iopOs!}" data-options="min:1,max:100,precision:1"/>
                        <em class="em-at em-dropAt">mmHg</em>
                    </div>
                    <div class="s-eyePressWOs-1 fl">
                        <select class="drop easyui-combobox drop-pressExam easyui-combobox" style="width:200px"
                                name="iopOs" id="iopOs2" value="${ae.iopOs!}"
                                data-options="valueField:'valueCode',textField:'valueDesc',limitToList:true,clearIcon:true,filter:function (q,row){
							var l = row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1;
							var n = row['valueDesc']&&row['valueDesc'].indexOf(q) >-1;
							return l || n;
						  },onSelect:function(record){
							$('#iopOsName').val(record.valueDesc);
						}">
                        </select>
                        <input class="txt" type="hidden" id="iopOsName" name="iopOsName" value="${ae.iopOsName!}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline  w-80">治疗情况：</label>
                    <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写治疗情况诊断"
                              style="width:1000px" id="treatSituation" name="treatSituation" maxlength="500" cols="200"
                              row="10">${ae.treatSituation!}</textarea>
                </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline  w-80">目前情况：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写目前情况"
                              style="width:1000px" id="curSituation" name="curSituation" maxlength="500" cols="200"
                              row="10">${ae.curSituation!}</textarea>
                </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline  w-80">患者及家属诉求：</label>
                    <textarea class="txt txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写患者及家属诉求"
                              style="width:1000px" id="patientAppeal" name="patientAppeal" maxlength="500" cols="200"
                              row="10">${ae.patientAppeal!}</textarea>
                </span>
        </div>
        <div class="clearfix mar-l20 mar-b10">
                <span class="fl">
                    <label class="lab-inline  w-80">事件经过描述：</label>
                    <textarea class="txta txt-validate adaptiveTextarea" dataOptions="required:ture" noNull="请填写事件经过描述"
                              style="width:1000px" id="procText" name="procText" maxlength="5000" cols="200"
                              row="10">${ae.procDescr!}${ae.procDescrOne!}${ae.procDescrTwo!}</textarea>
                </span>
        </div>
        <input class="txt" type="hidden" id="procDescr" name="procDescr" value=""/>
        <input class="txt" type="hidden" id="procDescrOne" name="procDescrOne" value=""/>
        <input class="txt" type="hidden" id="procDescrTwo" name="procDescrTwo" value=""/>
    </form>
    <form class="soform formA form-validate">
        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom.ftl"]
    </form>
</div>
<div class="tabCont tabCont-1 tabContHide tab-flow"></div>
<div class="tabCont tabCont-2 tabContHide tab-report"></div>
<div class="tabCont tabCont-3 tabContHide tab-img pad-t10"></div>
<div class="tabCont tabCont-4 tabContHide tab-review"></div>
[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(["eventReview", "eventTableGroup", "eventFlowchart", "uploadImages", "eventImage",'myupload', 'pub', "btnSearch"], function (eventReview, eventTableGroup, eventFlowchart, uploadImages, eventImage) {
        //暂存前执行事件 false中断保存
        window.beforeStash = function(){
            if($("#procText").val()){
                var str = $("#procText").val();
                var splitLen = Math.ceil(str.length / 3);
                var result = []; //结果将会存储到 长度为 3 的数组里
                for (var i = 0; i < 3; i++) {
                    result.push(str.substr(0, splitLen));
                    str = str.substr(splitLen, str.length+1);
                }
                $("#procDescr").val(result[0]);
                $("#procDescrOne").val(result[1]);
                $("#procDescrTwo").val(result[2]);
            }else{
                $("#procText").val(null);
                $("#procDescr").val(null);
                $("#procDescrOne").val(null);
                $("#procDescrTwo").val(null);
            }
            return true
        }

        //保存前执行事件 false中断保存
        window.beforeSubmit = function(){
            if($("#procText").val()){
                var str = $("#procText").val();
                var splitLen = Math.ceil(str.length / 3);
                var result = []; //结果将会存储到 长度为 3 的数组里
                for (var i = 0; i < 3; i++) {
                    result.push(str.substr(0, splitLen));
                    str = str.substr(splitLen, str.length+1);
                }
                $("#procDescr").val(result[0]);
                $("#procDescrOne").val(result[1]);
                $("#procDescrTwo").val(result[2]);
            }else{
                $("#procText").val(null);
                $("#procDescr").val(null);
                $("#procDescrOne").val(null);
                $("#procDescrTwo").val(null);
            }
            return true;
        }

        [#include "/WEB-INF/views/amcs/adverseEvent/common/bottom_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/upper_js.ftl"]
        [#include "/WEB-INF/views/amcs/adverseEvent/common/tab_js.ftl"]


        /**
         * 从接口获取数据后表单赋值
         * */
        window.afterSerachPatient=function (){
            //门诊接口数据
            var opDomain=window.opDomain;

            // 门诊时间
            $("#visitDate").setVal(!!opDomain?opDomain.regDate.substr(0,10):"");
            // 门诊诊断
            $('#visitDiagnose').setVal(!!opDomain.diagName?opDomain.diagName:"");
            // 裸眼远视力 vod
            $('#nakedFarVod').setVal(!!opDomain.dvaOd?opDomain.dvaOd:"");
            // 裸眼远视力 vos
            $('#nakedFarVos').setVal(!!opDomain.dvaOs?opDomain.dvaOs:"");
            // 门诊眼压 vod
            $('#iopOd1').setVal(!!opDomain.iopOd?opDomain.iopOd:"");
            // 门诊眼压 vos
            $('#iopOs1').setVal(!!opDomain.iopOs?opDomain.iopOs:"");
            
            var emrUrl =opDomain.emrUrl;
			if(emrUrl){
				$('.btn-aemr').removeClass('hide');
				$('.btn-aemr').click(function () {
					window.open(emrUrl,'_blank');
				});
			}else{
				$('.btn-aemr').addClass('hide');
			}

        }

        /**远视力列表*/
        var farEyesight = null;
        /**近视力列表*/
        var nearEyesight = null;
        /**眼压类型*/
        var iopType = null;
        /**指测眼压结果*/
        var fingerTonometry = null;

        var param = {paraType : ['far_eyesight','near_eyesight','eyesight_desc', 'iop_type', 'finger_tonometry']};
        $ajax.postSync('${base}/ui/base/dict/getMoreList',param,false,false).done(function (rst) {
            var dict = rst.data;
            farEyesight=[dict.far_eyesight, dict.eyesight_desc];
            nearEyesight=dict.near_eyesight;
            iopType=dict.iop_type;
            fingerTonometry=dict.finger_tonometry;
            //console.log('farEyesight=',farEyesight);
        });

        var setValue=function(){
            $('#nakedFarVodType').setVal('${ae.nakedFarVodType!}')
            $('#nakedFarVod').setVal('${ae.nakedFarVod!}')
            $('#nakedFarVodName').setVal('${ae.nakedFarVodName!}')
            $('#nakedFarVosType').setVal('${ae.nakedFarVosType!}')
            $('#nakedFarVos').setVal('${ae.nakedFarVos!}')
            $('#nakedFarVosName').setVal('${ae.nakedFarVosName!}')
            $('#iopTypeOd').setVal('${ae.iopTypeOd!}')
			$('#iopOd1').setVal('${ae.iopOd!}')
			$('#iopOd2').setVal('${ae.iopOd!}')
			$('#iopOdName').setVal('${ae.iopOdName!}')
			
			$('#iopTypeOs').setVal('${ae.iopTypeOs!}')
			$('#iopOs1').setVal('${ae.iopOs!}')
			$('#iopOs2').setVal('${ae.iopOs!}')
			$('#iopOsName').setVal('${ae.iopOsName!}')
				
        }
        var baseExam = {
            init : function (){
                var me = this;
                $('.farEyesight').combobox('loadData',farEyesight[0]);
                $('.drop-eyePressExamType').combobox('loadData',iopType);
                $('.drop-pressExam').combobox('loadData',fingerTonometry);
                me.farVisionFn();//远视力切换数据
                me.eyePressFn();//眼压检查
                setValue();
            },
            farVisionFn : function (){//远视力切换数据
                $('.drop-sl-k').combobox({
                    onChange : function (val){
                        if (!val){
                            $(this).combobox('setValue','1');//值空
                        }
                        else{
                            var $v = $(this).parents('.p6').find('.drop-sl-v');
                            $v.combobox('setValue','');//值空
                            $v.combobox('loadData',farEyesight[val-1]);//loadData
                        }
                    }
                });
            },
            eyePressFn : function (){//眼压检查
                $('#iopTypeOd').combobox({
                    onChange : function (val){
                        if(val ==='05'){
                            $('.s-eyePressW-0').hide();
                            $('.s-eyePressW-1').show();
                            $('#iopOd1').numberspinner('disable');
                            $('#iopOd2').combobox('enable')
                        }else{
                            $('.s-eyePressW-0').show();
                            $('.s-eyePressW-1').hide();
                            $('#iopOd1').numberspinner('enable');
                            $('#iopOd2').combobox('disable');
                        }
                        $form.formAEnterFun();
                    }
                });
                $('#iopTypeOs').combobox({
                    onChange : function (val){
                        if(val ==='05'){
                            $('.s-eyePressWOs-0').hide();
                            $('.s-eyePressWOs-1').show();
                            $('#iopOs1').numberspinner('disable');
                            $('#iopOs2').combobox('enable')
                        }else{
                            $('.s-eyePressWOs-0').show();
                            $('.s-eyePressWOs-1').hide();
                            $('#iopOs1').numberspinner('enable');
                            $('#iopOs2').combobox('disable');
                        }
                        $form.formAEnterFun();
                    }
                });
            }
        };

        $(function () {
            //初始化表单
            baseExam.init();
            if(!'${ae.id}'){
            	$('.drop-eyePressExamType').combobox('setValue','01');//眼压检查默认第一个值
            	if(!$.isEmptyObject('${ae.patientNo}')){
					queryPatientInfo('${ae.patientNo}');
				}
        	}
        });

    })
</script>
</body>

</html>
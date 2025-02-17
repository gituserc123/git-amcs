    <input type="hidden" name="basicId" id="basicId" value="${basicId!}"/>
    <input type="hidden" name="pageType" id="pageType" value="${pageType!}" />
    <input type="hidden" name="eventId" id="eventId" value="${ae.eventId!}" />
    <input type="hidden" name="creator" id="creator"  value="${ae.creator!}"/>
    <input type="hidden" name="prevId" id="prevId"  value="${ae.prevId!}"/>
    <input type="hidden" name="masterId" id="masterId"  value="${ae.masterId!}"/>
    <input type="hidden" name="eventCode" id="eventCode"  value="${eventCode!}"/>
    <input type="hidden" name="hospId" value="${hospId!}" />
    <input type="hidden" name="instId" value="${instId!}" />
    <input type="hidden" id="curNode" name="curNode" value="${node!}" />
    <input type="hidden" id="node"  name="node" value="${node!}" />
    <input type="hidden" id="patientId"  name="patientId" value="${ae.patientId!}" />
    <input type="hidden" id="subspecialtyDesc" name="subspecialty" value="${ae.subspecialty}" />
    <input type="hidden" id="gradeOneDesc" name="gradeOne" value="${ae.gradeOne}" />
    <input type="hidden" id="gradeTwoADesc" name="gradeTwoA" value="${ae.gradeTwoA}" />
    <input type="hidden" id="emrUrl" name="emrUrl" value="${ae.emrUrl!}" />
    <input type="hidden" id="showOperate" name="showOperate" value="${showOperate!}" />
    <input type="hidden" id="tags" name="tags" value="${tags!}" />
    <input type="hidden" id="oaRequestId" name="oaRequestId" value="${ae.oaRequestId!}" />
    <input type="hidden" id="amountDate" name="amountDate" value="${ae.amountDate!}" />
    <input type="hidden" id="totalAmount" name="totalAmount" value="${allCompensationAmount!}" />
    <input type="hidden" id="patientVisitNo" name="patientVisitNo" value="${patientVisitNo!}" />
    <input type="hidden" id="isEhrDeptReview" name="isEhrDeptReview" value="${isEhrDeptReview!}" />

    
    <h2 class="h2-title-a">
        <span class="s-title">事件概况</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医院名称</label>
                [#if "${pageType!}" == 5]
                    <input class="txt txt-validate" type="text" value="*********" readonly="readonly"/>
                [#else]
                    <input class="txt txt-validate" type="text" name="hospName" value="${hospName!}" readonly="readonly"/>
                [/#if]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">患者本次事件  </label>
                <input class="txt inline txt-validate wp-75 required" type="text" id="reportTimes" name="reportTimes" noNull="请填写次数" placeholder="" value="${ae.reportTimes}"  validType="sInt['次数必须是正整数']" maxlength="2"  data-options="editable:false"/>
                <label class="wp-25"> 次上报</label>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">事件名称</label>
                <input class="txt txt-validate required" type="text" id="eventName" name="eventName" placeholder="" value="${ae.eventName}"  data-options="required:true" />
            </div>
        </div>
       <div class="p3">
            <div class="item-one">
            <label id="slable"   onClick="(function (){
                    $pop.tips(`A级 客观环境或条件可能引发不良事件（不良事件隐患）<br/>
B级 不良事件发生但未累及患者<br/>
C级 不良事件累及到患者但没有造成伤害<br/>
D级 不良事件累及到患者需要进行监测以确保患者不被伤害，或需通过干预阻止伤害发生<br/>
E级 不良事件造成患者暂时性伤害并需要进行治疗或干预<br/>
F级 不良事件造成患者暂时性伤害并需要住院或延长住院时间<br/>
G级 不良事件造成患者永久性伤害，但不需要治疗挽救生命<br/>
H级 不良事件发生并导致患者需要治疗挽救生命<br/>
I级 不良事件发生导致患者死亡，包括损害程度Ⅰ级`,'#slable',{time: 0,closeBtn: true,maxWidth: 600});
                    })()" class="lab-item">严重程度<span style="padding-left: 2px"  ><i  class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                <select class="drop drop-sl-v easyui-combobox required severity_level" style="width:100%; color: gray;" name="severityLevel" id="severityLevel" data-options="required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,value:'${ae.severityLevel}'">
                </select>
            </div>
        </div>
        
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">事件分级</label>
                <input class="txt txt-validate" type="text" style="background-color: #f2f2f2;" id="eventLevel" name="eventLevel" placeholder="该项目不填，根据严重程度自动生成" value="${ae.eventLevel}"  data-options="readonly:true, editable:false" />
                </select>
            </div>
        </div> 
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">发生时段</label>
                <select class="drop drop-sl-v easyui-combobox period required" style="width:100%" name="period" id="period" data-options="required:true,valueField:'valueDesc',textField:'valueDesc',clearIcon:true,value:'${ae.period}'">
                </select>
            </div>
        </div>
        
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">发生日期</label> 
                <input type="text" id="eventDate" class="txt so-date required txt-validate ae-datebox eventDate" data-options="required:true, maxDate:new Date(),format:'yyyy-MM-dd'" type="text" name="eventDate" value="[#if ae.eventDate??]${ae.eventDate?date("yyyy-MM-dd")}[/#if]" />
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">日期类型</label>
                <select class="drop drop-sl-v easyui-combobox dateType required"  style="width:100%" name="dateType" id="dateType" data-options="editable:false,required:true,valueField:'valueDesc',textField:'valueDesc',clearIcon:true,value:'${ae.dateType}'">
                </select>
            </div>
        </div>
       
    </div>
    <div class="row">
    	<div class="p3">
            <div class="item-one">
                <label class="lab-item">亚专科</label>
                <select class="drop drop-sl-v easyui-combobox subType required" style="width:100%" name="subspecialtyCode" id="subspecialty" data-options="editable:false,required:true, valueField:'valueCode',textField:'valueDesc',clearIcon:true,value:'${ae.subspecialtyCode}'">
	            </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label id="gradeOneLable" class="lab-item">事件分类一级<span style="padding-left: 2px"  ><i class="icon icon-question_sign grade_one_sign" style="color: #00a0e9;font-weight: bold; display:none;" ></i></span></label>
                <select class="drop drop-sl-v easyui-combobox gradeOne required" style="width:100%" name="gradeOneCode" id="gradeOne" data-options="loadFilter:function(data){
                    var result = data.filter(item=>{
                        return ${comboboxFilter}&item.comboboxFilter;
                    })
                    return result;
                },editable:false,required:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true,value:'${ae.gradeOneCode}'">
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">事件分类二级I</label>
                <select class="drop drop-sl-v easyui-combobox required" style="width:100%" name="gradeTwoACode" id="gradeTwoA" data-options="loadFilter:function(data){
                    var result = data.filter(item=>{
                        return ${comboboxFilter}&item.comboboxFilter;
                    })
                    return result;
                },editable:false,required:true, valueField:'valueCode',textField:'valueDesc',clearIcon:true,value:'${ae.gradeTwoACode}'">
                </select>
            </div>
        </div>
        <div class="p3" id="gradeTwoItemDiv">
            <div class="item-one">
                <label class="lab-item">事件分类二级II</label>
            	<input class="txt easyui-validatebox required" type="text" id="gradeTwoRemark" name="gradeTwoRemark" value="${ae.gradeTwoRemark!}" style="display:none;" data-options="required:true">
            	<div id="gradeTwoDiv">
            		<select class="drop drop-sl-v easyui-combobox required" style="width:100%" name="gradeTwoB" id="gradeTwoB" data-options="loadFilter:function(data){
                    var result = data.filter(item=>{
                        return ${comboboxFilter}&item.comboboxFilter;
                    })
                    return result;
                },editable:false,required:true, valueField:'valueDesc',textField:'valueDesc',clearIcon:true,value:'${ae.gradeTwoB}'">
               		</select>
            	</div>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="p3">
            <div class="item-one">
                <label class="lab-item">发生科室</label>
                <input class="txt txt-validate required" type="text" name="department" placeholder="" value="${department}" data-options="editable:true,required:true" />
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">发现者</label>
                <select class="drop drop-sl-v easyui-combobox personType required" style="width:100%" name="finder" id="finder" data-options="valueField:'valueDesc',textField:'valueDesc',clearIcon:true,value:'${ae.finder}',required:true">
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">发生地点</label>
                <select class="drop drop-sl-v easyui-combobox addressType dictSel required" style="width:50%" name="address" id="address" data-options="required:true,valueField:'valueDesc',textField:'valueDesc',clearIcon:true,value:'${ae.address}',
                        onChange:((v)=>{
                            if($('#eventCode').val()=='201'){
                                if($.trim(v) == '其他（）'){
                                    $('#addressRemark').show();
                                    $('#addressRemark').css('height', '');
                                    $('#addressInareaRemark').val('');
                                    $('#addressInareaRemark').hide();
                                    $('#addressOperroomRemark').val('');
                                    $('#addressOperroomRemark').hide();
                                }else if($.trim(v) == '住院部'){
                                    $('#addressRemark').val('');
                                    $('#addressRemark').hide();
                                    $('#addressInareaRemark').css('height','');
                                    $('#addressInareaRemark').show();
                                    $('#addressOperroomRemark').val('');
                                    $('#addressOperroomRemark').hide();
                                }else if($.trim(v) == '手术室'){
                                    $('#addressRemark').val('');
                                    $('#addressRemark').hide();
                                    $('#addressInareaRemark').val('');
                                    $('#addressInareaRemark').hide();
                                    $('#addressOperroomRemark').show();
                                    $('#addressOperroomRemark').css('height', '');
                                }else{
                                    $('#addressRemark').val('');
                                    $('#addressRemark').hide();
                                    $('#addressInareaRemark').val('');
                                    $('#addressInareaRemark').hide();
                                    $('#addressOperroomRemark').val('');
                                    $('#addressOperroomRemark').hide();
                                }
                            }else{
                                if($.trim(v) == '其他（）'){
                                    $('#addressRemark').show();
                                    $('#addressRemark').css('height', '');
                                }else{
                                    $('#addressRemark').val('');
                                    $('#addressRemark').hide();
                                }
                                $('#addressInareaRemark').val('');
                                $('#addressInareaRemark').hide();
                                $('#addressOperroomRemark').val('');
                                $('#addressOperroomRemark').hide();
                            }
                        })">
                </select>
                <textarea class="txt txt-validate adaptiveTextarea hide" style="width:45%;" type="text" id="addressRemark" name="addressRemark" placeholder="请填写其他" ></textarea>
                <textarea class="txt txt-validate adaptiveTextarea hide" style="width:45%;" type="text" id="addressInareaRemark" name="addressInareaRemark" placeholder="请填写病区" ></textarea>
                <textarea class="txt txt-validate adaptiveTextarea hide" style="width:45%;" type="text" id="addressOperroomRemark" name="addressOperroomRemark" placeholder="请填写手术室" ></textarea>
            </div>
        </div>
        <div class="p3" id="damageDiv" style="visibility: hidden;">
            <div class="item-one">
                <label id="dlable" onClick="(function (){
                    $pop.tips(`全身严重并发症包括：<br/>
                                循环系统障碍(血压异常、严重的心律不齐)、<br/>
                                呼吸系统障碍(吸入性肺炎、肺栓塞)、<br/>
                                中枢或周围神经系统障碍、过敏反应、高热、感染及麻醉药品毒性作用等在内的严重并发症`,
                                '#dlable',{time: 0,closeBtn: true,maxWidth: 600});
                    })()" class="lab-item">伤害类型<span style="padding-left: 2px"  ><i  class="icon icon-question_sign" style="color: #00a0e9;font-weight: bold;"></i></span></label>
                <select class="drop drop-sl-v easyui-combobox damageType required" style="width:100%" name="damageType" id="damageType" data-options="editable:false,required:true, valueField:'valueCode',textField:'valueDesc',clearIcon:true,value:'${ae.damageTypeCode}'">
                </select>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="p9">
            <label class="lab-inline w-105 text-right" id="tag-pointer">涉及科室/人员类型</label>
        </div>
    </div>
    <h2 class="h2-title-a">
        <span class="s-title">处理结果</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">是否完结</label>
                <select class="drop easyui-combobox" style="width:100%;" name="finishSign" id="finishSign" data-options="editable:false, required:true" data-options="value:'${ae.finishSign}'">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>

            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">是否构成纠纷</label>
                <select class="drop easyui-combobox" style="width:100%;" name="disputeSign" id="disputeSign" data-options="editable:false, required:true, value:'${ae.disputeSign}'" >
                    <option value="1">是</option>
                    <option value="0" selected="selected">否</option>
                </select>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">本次赔偿金额</label>
                <span style="position: relative;display: inline;line-height: 0px">
                    [#if ae.node==1]
                        <input [#if ae.oaRequestId]disabled[/#if] id="compensationAmount" class="txt easyui-validatebox amountInp wp-55" type="text" validType="pNumber['金额必须为正数']" name="compensationAmount" data-options="precision:2" value="${ae.compensationAmount }" />
                        <em class="em-at em-dropAt">元</em>
                    [#else]
                        [@shiro.hasPermission name = "eventQuery:amountDisplay"]
                            <input id="compensationAmount" [#if ae.oaRequestId]disabled[/#if] class="txt easyui-validatebox amountInp" type="text" validType="pNumber['金额必须为正数']" name="compensationAmount" data-options="precision:2" value="${ae.compensationAmount }" />
                            <em class="em-at em-dropAt">元</em>
                        [/@shiro.hasPermission]
                        [@shiro.lacksPermission name = "eventQuery:amountDisplay"]
                            <input disabled class="txt easyui-validatebox amountInp wp-55" type="password" value="******" />
                            <em class="em-at em-dropAt">元</em>
                        [/@shiro.lacksPermission]
                    [/#if]
                </span>
            </div>

        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">本次减免金额</label>
                <span style="position: relative;display: inline;line-height: 0px">
                    <input id="deductionAmount" class="txt easyui-validatebox amountInp dAmountInp" type="text" validType="pNumber['金额必须为正数']" name="deductionAmount"   data-options="precision:2" value="${ae.deductionAmount }" />
                    <em class="em-at em-dropAt">元</em>
                </span>


            </div>
        </div>


    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">其他处理结果</label>
                <input id="otherProcessResult" type="text"
                       class="txt txt-validate" name="otherProcessResult"
                       style="width: 100%" value="${ae.otherProcessResult!}">
            </div>
        </div>
        <div class="p3">
            <label style="color:red;">如需发起AE-医疗纠纷审批流程，请把金额填至“赔偿金额”处,发起后请到当前登录人的OA中完成后续流程</label>
        </div>

        [@shiro.hasPermission name = "eventQuery:amountDisplay"]
            <div class="p3">
                <label id="acLabel" style="margin-left: 12px;">累计赔偿金额:  ${allCompensationAmount }元</label>
                [@shiro.hasPermission name = "IncreaseEvent:oa"]
                    [#if ae.node==3&&ae.compensationAmount>0&&!ae.oaRequestId]
                        <input type="button" class="btn btn-warning btn-small wp-40" name="oa" id="oa" value="OA">
                    [/#if]
                [/@shiro.hasPermission]
                    [#if ae.oaRequestId]
                        <span class="wp-35" style="font-weight: bold;margin-left:5px;" >OA号：${ae.oaRequestId}</span>
                    [/#if]
                [@shiro.hasPermission name = "IncreaseEvent:admin"]
                    [#if ae.oaRequestId]
                        <input type="button" class="btn btn-danger btn-small wp-25" name="delOa" id="delOa"  value="删除OA上报">
                    [#else]
                        [#if ae.compensationAmount>0]
                            <input type="button" class="btn btn-danger btn-small wp-30 amount-btn" data-type="compensation"  id="delCompensation"  value="删除赔偿金额">
                        [/#if]
                    [/#if]
                [/@shiro.hasPermission]
            </div>
            <div class="p3">
                <label id="adLabel" style="margin-left: 12px;">累计减免金额:  ${allDeductionAmount }元</label>
                [@shiro.hasPermission name = "IncreaseEvent:admin"]
                    [#if ae.deductionAmount>0]
                        <input type="button" class="btn btn-danger btn-small wp-30 amount-btn" data-type="deduction"  value="删除减免金额">
                    [/#if]
                [/@shiro.hasPermission]
            </div>
        [/@shiro.hasPermission]


    </div>
    <div class="row">
        <div class="p3" id="firstReportDiv">
        </div>
    </div>

    <div id="patient-info-wrapper">
        <h2 class="h2-title-a">
            <span class="s-title">患者基本信息</span>
        </h2>
        <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
        <div class="row">
            <div class="p4">
                <div class="item-one item-group" style="padding-left: 105px;">
                    <label class="lab-item" style="width:100px;" id="patitentNoLabel">病案/住院/门诊号</label>
                    [#if "${ae.hasEmr}" == "true" || eventCode == '109']
	                    <input class="txt txt-validate wp-50 required patient-inp" type="text" name="patientNo"  id="patientNo" value="${ae.patientNo }"  [#if eventCode != '901' && eventCode != '902'  && eventCode != '204'] data-options="required:true,editable:false" style="background-color: #f2f2f2 !important;" [/#if]  validType=many['fnValid["patientNoChk"]'] />
                    [#else]
                    	<input class="txt txt-validate wp-50 required patient-inp" type="text" name="patientNo"  id="patientNo" value="${ae.patientNo }" [#if eventCode != '901' && eventCode != '902' && eventCode != '204'] data-options="required:true" [/#if]  validType=many['fnValid["patientNoChk"]'] />
	                    <input type="button" class="btn btn-warning btn-small wp-18 fix-search-btn" name="btnSearch" value="查询" />
                        <input type="button" class="btn btn-warning btn-small wp-20 switch-mode-btn" name="btnSwitch" value="手动输入" style="padding-left: 8px !important;"/>
	                    <button type="button" class="btn btn-aemr hide">相关病历</button>
                    [/#if]                
                </div>
            </div>
            <div class="p2">
                <div class="item-one">
                    <label class="lab-item">患者姓名</label>
                    [#if "${pageType!}" == 5]
                    	<input class="txt txt-validate" type="text"  value="*********" readonly="readonly"/>
                	[#else]
                        <input class="txt txt-validate required patient-inp" type="text" name="patientName" id="patientName" placeholder="" style="width:100%;"  value="${ae.patientName }" [#if eventCode != '901' && eventCode != '902' && eventCode != '204'] data-options="required:true" [/#if] />
                    [/#if]
                </div>
            </div>
            <div class="p2">
                <div class="item-one">
                    <label class="lab-item">出生年月</label>
                    <input id="patientBirth" class="easyui-datebox patient-inp-textbox" style="width:100%;"  type="text" name="patientBirth" [#if eventCode == '901' || eventCode == '902' || eventCode == '204'] data-options="editable:false, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'" [#else] data-options="editable:false,required:true, maxDate:new Date(),format:'yyyy-MM-dd',type:'date'"  [/#if] noNull="请填写发生日期"  value="${ae.patientBirth}"  />
                </div>
            </div>
            <div class="p2">
                <div class="item-one">
                    <label class="lab-item">患者性别</label>
                    [#if eventCode == '901' || eventCode == '902'  || eventCode == '204']
                        [@ui_select id="patientSex" name="patientSex" tag="sex" style="width:100%;"   class="easyui-combobox patient-inp-textbox" filterkey="firstSpell" uiShowDefault=false  value="${ae.patientSex }"/]
                    [#else]
                        [@ui_select id="patientSex" name="patientSex" tag="sex" style="width:100%;"   class="easyui-combobox patient-inp-textbox" dataOptions="required:true" filterkey="firstSpell" uiShowDefault=false  value="${ae.patientSex }"/]
                    [/#if]
                </div>
            </div>
            <div class="p2">
                <div class="item-one">
                    <label class="lab-item">患者年龄</label>
                    <input class="txt txt-validate patient-inp" type="text" name="patientAge" id="patientAge" validType="sInt['次数必须是正整数']" placeholder="" style="width:100%;" value="${ae.patientAge }" [#if eventCode != '901' && eventCode != '902' && eventCode != '204']  data-options="required:true,readonly:true"  [/#if]/>
                    <em id="emYear" class="em-at em-dropAt hide">岁</em>
                    <em id="emMonth" class="em-at em-dropAt hide">月</em>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label style="color:red;">输入病案号/门诊号/住院号点击查询调取患者信息！</label>
                </div>
            </div>
        </div>
    </div>
    [#if eventCode == '201' || eventCode == '203' || eventCode == '204' ]
        <div class="row">
            <div class="p3">
                <div class="item-one">
                    <label class="lab-item">身份证号</label>
                    <input class="txt txt-validate required" type="text" name="patientIdNumber" id="patientIdNumber" placeholder="" value="${ae.patientIdNumber }" [#if eventCode != '204'] data-options="required:true"[/#if] />
                </div>
            </div>
            <div class="p2">
                <div class="item-one">
                    <label class="lab-item">职业</label>
                    <input class="txt txt-validate required" type="text" name="patientJob" id="patientJob" placeholder="" value="${ae.patientJob }" [#if eventCode != '204']  data-options="required:true" [/#if] />
                </div>
            </div>
            <div class="p2">
                <div class="item-one">
                    <label class="lab-item">国籍</label>
                    <input class="txt txt-validate required" type="text" name="patientNationality" id="patientNationality" placeholder="" value="${ae.patientNationality }" [#if eventCode != '204'] data-options="required:true" [/#if] />
                </div>
            </div>
        </div>
    [/#if]
    <div class="row hide cls-manual-input-reason">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">手动输入原因</label>
                <input class="txt txt-validate required" type="text" name="manualInputReason" id="manualInputReason" placeholder="" value="${ae.manualInputReason }"   [#if eventCode != '901' && eventCode != '902' && eventCode != '204']  data-options="required:true"  [/#if] />
            </div>
        </div>
        <div class="p4">
        </div>
        <div class="p4">
        </div>
    </div>
    <!-------------- pop page ------------------->
    <script id="opinionFormTem" type="text/html">
    	<form id="infoForm" class="soform form-validate solab-l form-enter" data-opt="{beforeCallback:'submitEditForm'}">
    	    <input type="hidden" name="popId" id="popId" />
    	    <div class="row risk-row">
	    	    <div class="p9" style="margin-left: -40px">
	                <div class="item-one">
	                    <label class="lab-item">是否高风险</label>
	                     <label class="lab-val"><input type="radio" class="rad reason" name="isHighRisk" value="0"   [#if '${ae.isHighRisk }' == 0 ] checked="checked" [/#if] />否</label>
                    	 <label class="lab-val"><input type="radio" class="rad reason" name="isHighRisk" value="1"  [#if '${ae.isHighRisk }' == 1 ] checked="checked" [/#if]  />是</label>
	                </div>
	            </div>
    	    </div>
    		<div class="row">
    			<div class="p12">
                    <textarea class="txta txt-validate" id="popOpinion" name="popOpinion" maxlength="1000"></textarea>
               	</div> 
    		</div>
    		<p class="row-btn center">
            	<input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定" />
            	<input type="button" class="btn btn-cancel-pop" name="btnCancel" value="取 消" />
        	</p>
    	</form>
    </script>
    <script id="mergeGridTem" type="text/html">
		<div id="gridBox"></div>
	</script>
	<script id="backGridTem" type="text/html">
		<div id="backGridBox"></div>
	</script>
	<style>
	    .layui-layer-tips .layui-layer-close1{
	        background-position: -188px -40px !important;
	    }
	    #Uploadthelist{display:flex;}
	    #Uploadthelist > .item {
		    margin: 10px;
		}
        .amount-btn {
            margin-right: 10px;
            float: right;
        }
        
    </style>
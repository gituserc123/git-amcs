    <h2 class="h2-title-a">
        <span class="s-title">当事人员</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">姓名</label>			
				[#if "${pageType!}" == 1||"${pageType!}" == 0]
                	<input  type="hidden" id="staffName" name="staffName" value="${ae.staffName}" /> 
                	<select class="drop drop-doctorIda required" style="width:100%;" data-options="required:true" id="staffId" name="staffId" value="${ae.staffId}"></select>
                [#else]
                	[#if "${pageType!}" == 5]
                		<input class="txt txt-validate" type="text" value="****" readonly="readonly"/>
                	[#else]
                		<input class="txt txt-validate" type="text" name="staffName" value="${ae.staffName!}" readonly="readonly"/>
                	[/#if]
                 		
                [/#if]
            </div>
            
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">性别</label>
                [@ui_select id="staffSex" name="staffSex" tag="sex" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.staffSex}"/]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">年龄</label>
                <input class="easyui-numberbox txt txt-validate required" type="text"  style="width:100%;" name="staffAge" validType="sInt['次数必须是正整数']" placeholder=""  data-options="required:true"  value="${ae.staffAge}"/>
                <em class="em-at em-dropAt">岁</em>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">学历</label>
                [@ui_select id="staffDegree" name="staffDegree" tag="@ae@education_type" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.staffDegree}"/]
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">类别</label>
                [@ui_select id="staffType" name="staffType" tag="@ae@staff_type" style="width:100%;" filterkey="firstSpell" value="${ae.staffType}" dataOptions="editable:false,required:true,
                onChange:((v)=>{
                  if(v == '2') {
                    $('.staffYearsInEye').css('display','');
                    $('#staffYearsInEye').numberbox('textbox').validatebox({
                      required: true,
                      missingMessage: '眼科工作年限必填'
                    });
                  }else{
                    $('.staffYearsInEye').css('display','none');
                    $('#staffYearsInEye').numberbox('textbox').validatebox({
                      required: false
                    });
                  }
                })" /]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">在职情况</label>
                [@ui_select id="staffWork" name="staffWork" tag="@ae@work_type" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.staffWork}"/]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">专业技术职称</label>
                [@ui_select id="technicalPost" name="technicalPost" tag="@ae@technical_post_type" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.technicalPost}"/]
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">职称获得时间</label>
                <input type="text" class="txt so-date w-100" name="technicalPostDate" style="width:100%;" data-opt="{opens:'left',format:'yyyy-MM'}" value="${ae.technicalPostDate}">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p6">
            <div class="item-one">
                <label class="lab-item">本专业工作年限</label>
                [@ui_select id="staffYears" name="staffYears" tag="@ae@work_year_type" style="width:30%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${ae.staffYears}" /]
                <span class="staffYearsInEye" style="display: none" >其中眼科工作年限
                    <input id="staffYearsInEye" class="txt txt-validate easyui-numberbox" data-options="min:1,precision:0" type="text"
                           name="staffYearsInEye" style="width:35%;" placeholder="" value="${ae.staffYearsInEye}" />
                    年</span>
            </div>
        </div>
    </div>
    [#if !("${eventCode}" == '109' && "${ae.reportTimes}" == '1')]
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row" id="common_reason">
        <div class="p8">
            <div class="item-one">
                <label class="lab-item">原因分析</label>
                <textarea class="txt txt-validate required adaptiveTextarea" type="text" name="reason" placeholder=""  data-options="required:true">${ae.reason}</textarea>
            </div>
        </div>
    </div>
    <div class="row" id="common_opinion">
        <div class="p8">
            <div class="item-one">
                <label class="lab-item">整改意见及结果</label>
                <textarea class="txt txt-validate required adaptiveTextarea" type="text" name="opinion" placeholder="" maxlength='1000'  data-options="required:true">${ae.opinion}</textarea>
            </div>
        </div>
    </div>
    [/#if]
    [#if "${pageType!}" == 4]
    <div class="row" id="common_opinion">
        <div class="p8">
            <div class="item-one">
                <label class="lab-item">省区点评</label>
                <textarea class="txt txt-validate required adaptiveTextarea" readonly="readonly" type="text" name="aeOperationRecord.opinion" placeholder="" maxlength='1000' >${aeOperationRecord.opinion}</textarea>
            </div>
        </div>
    </div>
    [/#if]

/*判断页面是否为进展上报页面，进展上报页面需要更新其父事件ID*/
var fatherId = '';
var gradeOneMsg = [
    `1、手术并发症包括包括术中、术后并发症等；<br/>
    2、重点关注群发事件 <br/>
    3、其他可能（或已经）引起患者额外经济损失、产生医疗赔偿、影响医院声誉事件，以及患者向相关部门投诉/申请医疗鉴定或者向法院起诉不良事件等`,
    `诊疗评估问题，如：错误的诊疗行为、未落实首诊负责制等造成的医疗安全事件。`,
    ``,
    `针刺伤、刀割伤等`,
    `1、含医护药技类依法执业问题；<br/>
    2、医疗、护理工作中已经发现问题，但未及时处理导致的不良事件等。`,
    `操作规程缺陷含医护药技操作类；`,
    `1、手术查对缺陷包括手术患者、手术参数、手术部位、手术方式、手术操作、手术植入物等；<br/>
    2、医嘱制度执行缺陷指与医嘱执行相关的缺陷，如医嘱执行错误或者漏执行；（用药除外）<br/>
    3、给药错误指用药剂量、配液错误等；<br/>
    4、医疗文书缺陷不良事件指未造成严重后果的所有医疗文书上的书写错误：包括性别、视力、年龄、出入院时间、医嘱开立等（包括医护药技）`,
    `因意外死亡、有纠纷苗头；`,
    `基础疾病、突发意外等；`,
    `医患沟通不良、医患言语不良、医患行为冲突等，包括医患争吵、身体攻击、打架、暴力行为等；`,
    ``,
    `1、大型医疗设备及生命支持相关设备仪器故障；<br/>
    2、医疗仪器设备操作、使用不良导致的不良事件等；<br/>
    3、医疗器械不良事件，器械不符合无菌要求，医用耗材在临床使用中的不良反应。<br/>
    4、其它医用耗材相关不良事件（针头折断、PICC断裂）等；`,
    `1、标本采集不良事件：比如标本采集时患者身份错误，空管、标本种类错误、血液凝固/溶血、标本量太多或太少、标本容器错误、采血方式有误（输血端或输液端采血）、标本相关类的其他；<br/>
    2、不良治疗及其他：①医嘱给药药物安全不良反应、输液/（输血）反应、液体外渗；②患者检查或运送中或后病情突变或出现意外；③其他包括误吸/窒息、咽入异物、院内压疮、医源性皮肤损伤。<br/>
    3、供应室不良事件包括消毒灭菌物品未达要求（含手术室）、热源试验阳性、操作中发现器械包器械物品不符；`,
    ``,
    ``,
    `1、不适当约束，或执行合理约束导致的不良事件<br/>
    2、患者走失、烫伤、烧伤、自残、窒息、火灾、失窃、咬破体温表等不良事件<br/>
     `,
     `1、医护人员劳动纪律不良事件，上班或值班时间擅自离岗、脱岗，班前班中饮酒影响正常工作为患者进行诊疗服务过程中，不遵守职业礼仪，聊天打手机等。<br/>
      2、医务人员职业操守不良事件，违反职业道德和医疗保护原则，不负责任地透露或散布有关患者的情况；不负责任地任意解释医院规定和其他科室、其他医务人员的工作，造成患方误会或不满；出具与自己执业范围无关或与执业类别不相符的医学证明文件等；<br/>`,
     `1、网络设备故障，服务器故障，使用部门电脑、打印机硬件、软件不良事件；信息中心不良事件<br/>
     2、通用设备故障不良事件，电梯故障事件，危及医、患等人员人身安全及财产损失的因素和事件；意外事件，医院建筑、通道不良问题，停水、停电、停气、泛水事件等引起设备故障影响医疗工作的正常运行和危及医患人员人身安全的因素和事件 ；恶劣天气、自然灾害影响医疗工作的正常运行和危及医患人员人身安全的因素和事件。<br/>`,
     ``,
     `1、治安不良事件，院内暴力事件、扰乱公共秩序、骚扰、财物损失事件（包括药品丢失或批量损毁、医疗器械缺失、患者及家属财务丢失、医务人员财务丢失）；<br/>
     2、公共安全事件，发生严重工伤等安全事件未及时处置、上报等，火灾隐患或事件；<br/>
     3、突发公共卫生事件，食物中毒等突发公共卫生事件。`
]

if (!$.isEmptyObject('${ae.prevId}')) {
    fatherId = '${ae.prevId}';
} else {
    fatherId = '${basicId!}';
}


/*全局变量*/
var opinionFormPop = null;
/* 公用组件相关JS */
window.eventFlowchart = eventFlowchart;
let masterId = "${ae.masterId!}";
if ($.isEmptyObject(masterId)) {
    masterId = "${basicId!}";
}
window.params = { basicId: "${basicId!}", eventCode: "${eventCode!}", prevId: "${ae.prevId!}", masterId: masterId, id: "${basicId!}" };


$('#subspecialty').combobox({
    onChange: function (newValue, oldValue) {
        $('#subspecialtyDesc').val($('#subspecialty').combobox('getText'));
        let grade = $('#gradeOne').combobox('getValue');
        filterGradeTwoI(newValue, grade);
    }
});
//oa提交
$("#oa").click(function () {
    let id = $("#basicId").val();
<#--    if ($.isEmptyObject(id)) {-->
<#--      id = "${ae.prevId!}";-->
<#--	};-->
    $ajax.post(`${base}/ui/service/biz/amcs/adverse/common/payCreate`, { "id": id }).done((res) => {
        if (res.status == 'success') {
            $pop.msg('提交OA成功！');
            $("#oa").addClass("hide");
            $("#oaRequestId").val(res.oaRequestId);
        } else {
            $pop.msg('提交失败！');
        }
    })
})

//删除oa提交
$("#delOa").click(function () {
let id = $("#basicId").val();
<#--    if ($.isEmptyObject(id)) {-->
<#--      id = "${ae.prevId!}";-->
<#--	};-->
$ajax.post({url:`${base}/ui/service/biz/amcs/adverse/common/delOa`,tip:"确定要删除该事件的OA上报记录，以及赔付金额。【高危操作，确定你知道这样做的结果！】这项操作会记录到审计日志！！！", data:{ "id": id }}).done((res) => {
if (res) {
$pop.msg('删除OA成功！');
//刷新页面
setTimeout(function () {location.reload();}, 1000);


} else {
$pop.msg('提交失败！');
}
})
})
//清除赔偿金额和减免金额
$(".amount-btn").click(function () {
    let id = $("#basicId").val();
    //判断是删除赔偿金额按钮还是删除减免金额按钮
    let type = $(this).attr("data-type");
    let typeName = "赔偿金额";
    if(type == "deduction"){
        typeName = "减免金额";
    }
    $ajax.post({url:`${base}/ui/service/biz/amcs/adverse/common/delAmount`,
        tip:"确定要删除${typeName}吗？此操作将不可逆！！！", data:{ "id": id, "type": type}
    }).done((res) => {
        if (res) {
            $pop.msg('删除${typeName}成功！');
            setTimeout(function () {location.reload();}, 1000);
        } else {
            $pop.msg('删除失败！');
        }
    })
})
//归档
$(".fix-archived-btn").click(function () {
    $ajax.post({ url: `${base}/ui/service/biz/amcs/adverse/common/archivedById`, tip: '归档后，该不良事件仅内部可查阅，省区/集团无法审核，是否确认归档?', data: { "id": '${basicId}' } }).done((res) => {
        if (res) {
            $pop.msg('归档成功！');
            showMask();
        } else {
            $pop.msg('归档失败！');
        }

    })
})

// 一级分类
$('#gradeOne').combobox({
    onChange: function (newValue, oldValue) {
        //当勾选医院感染不良事件时，自动勾选涉及科室为院感
        if(newValue == 3) {
        	$("input[name='tag5']").prop("checked", true);
        }
        $('#gradeOneDesc').val($('#gradeOne').combobox('getText'));
        let sub = $('#subspecialty').combobox('getValue');
        filterGradeTwoI(sub, newValue);
        //根据选择的不同选项获取相应的提示信息
        if($.isEmptyObject(newValue)){
            $(".grade_one_sign").hide();
        }else{
            let msgIndex = newValue -1;
            let tipMsg = gradeOneMsg[msgIndex];
            if($.isEmptyObject(tipMsg)){
                $(".grade_one_sign").hide();
            }else{
                $(".grade_one_sign").show();
                $("#gradeOneLable").click(function() {
                    $pop.tips(tipMsg,'#gradeOneLable',{time: 0,closeBtn: true,maxWidth: 600});
                });
            }
        }
        /*
        if($('#eventCode').val() == '902'){
        	$('#gradeTwoA').combobox('setValue', '110');
        }
        */
 
    }
});

// 二级分类I
$('#gradeTwoA').combobox({
    onChange: function (newValue, oldValue) {
        console.log(newValue);
        if(newValue == 111){
            $('#adverseLevel').combobox('setValue', '1');
        }else if(newValue == 112){
            $('#adverseLevel').combobox('setValue', '2');
        }else if(newValue == 113){
            $('#adverseLevel').combobox('setValue', '3');
        }else if(newValue == 76){
            // 死完事件时，严重程度自动选择I级， 事件分级自动选择 Ⅰ级（警告事件）
            $('#severityLevel').combobox('setValue', '9');
        }


        $('#gradeTwoADesc').val($('#gradeTwoA').combobox('getText'));
        $("#gradeTwoItemDiv").show();
        let newTxt = $('#gradeTwoA').combobox('getText');
        if (newTxt == '其他') {
            $("#gradeTwoRemark").show();
            $("#gradeTwoDiv").hide();
            $("#gradeTwoB").combobox("disableValidation");
            $("#gradeTwoRemark").validatebox("enableValidation");
        } else {
            $("#gradeTwoRemark").hide();
            $("#gradeTwoDiv").show();
            $("#gradeTwoB").combobox("enableValidation");
            $("#gradeTwoRemark").validatebox("disableValidation");
            filterGradeTwoII(newValue);

        }


    }
});

function updateAge(date) {
    var curDate = new Date();
    if (date > curDate) {
        $pop.msg("不能大于当前日期！！！");
        return false;
    } else {
        var diffYear = DIFFTIME(date, curDate, 'y');
        var diffMonth = DIFFTIME(date, curDate, 'M');
        if (parseInt(diffYear) == 0 && parseInt(diffMonth) == 0) {
            $pop.msg("选择须小于当月！！！");
            return false;
        }
        if (parseInt(diffYear) > 0) {
            $('#patientAge').textbox('setValue','');
            $('#patientAge').textbox('setValue',parseInt(diffYear));
            $('#emYear').show();
            $('#emMonth').hide();
        } else {
            $('#patientAge').textbox('setValue','');
            $('#patientAge').textbox('setValue',parseInt(diffMonth));
            $('#emYear').hide();

            $('#emMonth').show();
        }
    }
}
$('#patientBirth').datebox({
    onSelect: function (date) {
        updateAge(date)
    }
});



//是否发生纠纷
$('#disputeSign').combobox({
    onChange: function (newValue, oldValue) {
        if (newValue == 1) {
            changeAmount(1);
        } else {
            changeAmount(0);
        }
    }
});

//页面初始化时，伤害类别配置
if($('#severityLevel').combobox('getValue') >= 7 ) {
    $("#damageDiv").css('visibility', 'visible');;
}else{
    $("#damageType").combobox("disableValidation");
}

//严重程度发生改变时触发事件分级变化
$('#severityLevel').combobox({
    onChange: function (newValue, oldValue) {
        if (newValue == 1) {
            //A级
            //$('#eventLevel').combobox('setValue', 'IV级（隐患事件）');
            $("#eventLevel").val('IV级（隐患事件）')
        } else if(newValue == 2||newValue == 3||newValue == 4) {
            //B-D级
            $("#eventLevel").val('III 级（未造成后果事件）');
        } else if(newValue == 5||newValue == 6||newValue == 7||newValue == 8) {
            //E-H级
            $("#eventLevel").val('II 级（不良事件）');
        } else if(newValue == 9){
            $("#eventLevel").val('I级（警告事件）');
            // 如果事件分类二级I选择了 死亡事件，则不弹出alert
            if($('#gradeTwoA').combobox('getValue') != 76){
                $pop.alert('严重程度I级：不良事件发生导致患者死亡，包括损害程度1级，请确认');
            }
        }else{
            $("#eventLevel").val('');
        }
        //严重程度为G、H、I时，显示损害类型
        if (newValue >= 7) {
            $("#damageDiv").css('visibility', 'visible');;
            $("#damageType").combobox("enableValidation");
        }else{
            $("#damageDiv").css('visibility', 'hidden');;
            $("#damageType").combobox("disableValidation");
        }
    }
});

if ('${ae.disputeSign}' == 0) {
    $(".amountInp").each(function () {
        $(this).validatebox('disable');
    });
}
//对于非计划住院的事件,首次上报禁用一些字段
if ('${eventCode}' == '109' && '${ae.reportTimes}' == '1') {
    $('#severityLevel').combobox('disable');
    $('#gradeOne').combobox('disable');
    $('#gradeTwoA').combobox('disable');
    $('#gradeTwoB').combobox('disable');
    $('#finishSign').combobox('disable');
    $('#disputeSign').combobox('disable');

    $("#severityLevel").combobox("disableValidation");
    $('#gradeOne').combobox('disableValidation');
    $('#gradeTwoA').combobox('disableValidation');
    $('#gradeTwoB').combobox('disableValidation');
    $("#gradeTwoRemark").validatebox("disableValidation");
    $('#finishSign').combobox('disableValidation');
    $('#disputeSign').combobox('disableValidation');
    $('#finishSign').combobox('setValue', '');
};

if ($('#adLabel').length > 0) {
    const adLabelText = $('#adLabel').text();
    const match = adLabelText.match(/[\d.]+/);
    const currentAllDeductionAmount = match ? parseFloat(match[0]) || 0 : 0;
    $('#deductionAmount').on('input', function() {
        const deductionAmount = parseFloat($(this).val()) || 0;
        const newAllDeductionAmount = currentAllDeductionAmount + deductionAmount;
        if (!isNaN(newAllDeductionAmount)) {
        $('#adLabel').text('累计减免金额: ' + newAllDeductionAmount + '元');
        } else {
            $('#adLabel').text('累计减免金额: 0.00元');
        }
    });
}

if ($('#acLabel').length > 0) {
    const acLabelText = $('#acLabel').text();
    const match = acLabelText.match(/[\d.]+/);
    const currentAllCompensationAmount = match ? parseFloat(match[0]) || 0 : 0;
    $('#compensationAmount').on('input', function() {
        const compensationAmount = parseFloat($(this).val()) || 0;
        const newAllCompensationAmount = currentAllCompensationAmount + compensationAmount;
        if (!isNaN(newAllCompensationAmount)) {
            $('#acLabel').text('累计赔偿金额: ' + newAllCompensationAmount + '元');
        } else {
            $('#acLabel').text('累计赔偿金额: 0.00元');
        }
    });
}





function changeAmount(status) {
    if (status == 1) {
        if($.isEmptyObject('${ae.oaRequestId}')){
        	$(".amountInp").each(function () {
	            $(this).validatebox('enable');
	        });
        }else{
	        $(".dAmountInp").validatebox('enable');
        }
    } else {
        $(".amountInp").each(function () {
            $(this).validatebox('disable');
        });
    }
}


function filterGradeTwoI(sub, grade) {
    if (sub != null && grade != "") {
        let filter = sub + ',' + grade;
        let url = base + '/ui/amcs/dict/getAeDict?type=grade_two_a&filter=' + filter;
        $('#gradeTwoA').combobox('reload', url);
    }
}

function filterGradeTwoII(gradeI) {
    let url = base + '/ui/amcs/dict/getAeDict?type=grade_two_b&filter=' + gradeI;
    $ajax.postSync(url, false, false).done(function (data) {
        if (data.length > 0) {
            $('#gradeTwoB').combobox("loadData", data);
            $("#gradeTwoB").combobox("enableValidation");
            $("#gradeTwoRemark").validatebox("disableValidation");
        } else {
            $("#gradeTwoItemDiv").hide();
            $("#gradeTwoB").combobox("disableValidation");
            $("#gradeTwoRemark").validatebox("disableValidation");
        };
    });

}

// 字典数据加载
var operateType = 0;
var addressType = null;
var periodType = null;
var param = {
    paraType: ['address_type', 'address_type_1', 'address_type_2', 'period_type', 'date_type', 'sub_type', 'grade_one',
        'person_type', 'progress_type', 'process_type', 'severity_level', 'damage_type']
};

$ajax.postSync('${base}/ui/amcs/dict/getAeMoreList', param, false, false).done(function (rst) {
    var dict = rst.data;
    if ($('#eventCode').val() == '202' || $('#eventCode').val() == '203' || $('#eventCode').val() == '204') {
        // 护理类不良事件表单发生地段
        $('.addressType').combobox('loadData', dict.address_type);
    } else if ($('#eventCode').val() == '101' || $('#eventCode').val() == '102' || $('#eventCode').val() == '103' || $('#eventCode').val() == '104'
        || $('#eventCode').val() == '105' || $('#eventCode').val() == '106' || $('#eventCode').val() == '107' || $('#eventCode').val() == '108'
        || $('#eventCode').val() == '301') {
        // 医疗类、医院感染不良事件表单发生地点
        $('.addressType').combobox('loadData', dict.address_type_1);
    } else if ($('#eventCode').val() == '201') {
        // 跌倒坠床事件发生地点
        $('.addressType').combobox('loadData', dict.address_type_2);
    } else {
        $('.addressType').combobox('loadData', dict.address_type);
    }
    $('.period').combobox('loadData', dict.period_type);
    $('.dateType').combobox('loadData', dict.date_type);
    
    $('.subType').combobox('loadData', dict.sub_type);
    $('.damageType').combobox('loadData', dict.damage_type);

    $('.gradeOne').combobox('loadData', dict.grade_one);
    initGradeTwoListData();
    if($('#eventCode').val() == '901'||$('#eventCode').val() == '902'){
     	$("#gradeTwoItemDiv").hide();
     	$('.subType').combobox('select', '18');
     	$('.subType').combobox('readonly', true);
    	if($('#eventCode').val() == '902'){
    		$('.gradeOne').combobox('select', '21');
    		$('#gradeTwoA').combobox('select', '105');
    	}
    }
    $('.personType').combobox('loadData', dict.person_type);
    $('.personType').combobox('loadData', dict.person_type);
    $('.progressType').combobox('loadData', dict.progress_type);
    $('.processType').combobox('loadData', dict.process_type);
    $('.severity_level').combobox('loadData', dict.severity_level);
    
});

function initGradeTwoListData() {
 	let sub = $('#subspecialty').combobox('getValue');
    let grade = $('#gradeOne').combobox('getValue');
    filterGradeTwoI(sub, grade);
    setTimeout(function () {
        var val = $("#gradeTwoA").combobox('getValue');
        if (val) {
            handleGradeTwoChange(val);
        }
    }, 600);
}

function handleGradeTwoChange(newValue) {
    $('#gradeTwoADesc').val($('#gradeTwoA').combobox('getText'));
    $("#gradeTwoItemDiv").show();
    let newTxt = $('#gradeTwoA').combobox('getText');
    if (newTxt == '其他') {
        $("#gradeTwoRemark").show();
        $("#gradeTwoDiv").hide();
        $("#gradeTwoB").combobox("disableValidation");
        $("#gradeTwoRemark").validatebox("enableValidation");
    } else {
        $("#gradeTwoRemark").hide();
        $("#gradeTwoDiv").show();
        $("#gradeTwoB").combobox("enableValidation");
        $("#gradeTwoRemark").validatebox("disableValidation");
        filterGradeTwoII(newValue);
    }
}

/*
保存按钮事件
*/
$(".fix-save-btn").click(function () {
    operateType = 1;
    var allPass = true;
    var formValidate = true;
    $('.formA').each(function () {
        if (!$(this).form('validate')) {
            formValidate = false;
            return false;
        }
    })
    if (!formValidate) {
        return false; // 终止外部函数的执行
    }
    formValidate = $("#outpForm").form('validate');
    if (!formValidate) {
        return false;
    }
    if (!!window.beforeSubmit) {
        let flag = window.beforeSubmit();
        if (flag === false) {
            formValidate = false;
            return false;
        }
    }
    if (formValidate) {
        submitOpion('提交审核');
    }
});

/*
暂存按钮事件
 */
$(".fix-stash-btn").click(function () {
    // 判断事件名称和发生日期是否填写
    if ($.isEmptyObject($("#eventName").val()) || $.isEmptyObject($("#eventDate").val())) {
        $pop.warning('必须填写事件名称和发生日期！');
        return false;
    };
    if (!!window.beforeStash) {
        var flag = window.beforeStash();
        if (flag === false) {//返回false 不在執行之後的邏輯
            return;
        }
    }
    operateType = 2;
    doSave();

})

/*
取消按钮事件
 */
$(".fix-cancel-btn").click(function () {
    operateType = 5;
    submitOpion('取消事件');
})

/*
点评按钮事件
 */
$(".fix-review-btn").click(function () {
    operateType = 7;
    submitOpion('事件点评');
})

/*
合并按钮事件
 */
$(".fix-merge-btn").click(function () {
    let eventDate = $("#eventDate").val();
    let mergePopTem = $pop.popTemForm({
        title: "待合并事件列表",
        temId: 'mergeGridTem',
        area: ['600px', '350px'],
        onPop: function () {
            $grid.newGrid("#gridBox", {
                tools: [//工具栏按钮
                    [//按钮组
                        {
                            iconCls: 'plus',//图标
                            text: '合并',//文字
                            click: function () {
                                let selectRows = $("#gridBox").datagrid("getSelections");
                                if (selectRows.length == 0) {
                                    $pop.alert('请选择待合并的数据!');
                                    return;
                                }
                                let mergeIds = [];
                                $.each(selectRows, function (index, row) {
                                    mergeIds.push(row.id);
                                });
                                let mergeData = {}
                                mergeData.mergeIds = mergeIds;
                                mergeData.masterId = fatherId;

                                $ajax.post('${base}/ui/service/biz/amcs/adverse/common/merge', mergeData, { jsonData: false, tip: false }).done(function (rst) {
                                    if (rst.code === '200' || rst.code === '201') {
                                        $pop.success('合并成功!');
                                    }
                                });

                                $pop.close(mergePopTem);
                            }
                        }
                    ]
                ],
                height: 'auto',
                rownumbers: true,
                singleSelect: false,
                pagination: false,
                fitColumns: true,
                columns: [[//数据行
                    { title: 'id', field: 'id', checkbox: true },
                    { title: '不良事件名称', field: 'eventName', sortable: true, width: 100 },
                    { title: '录入人', field: 'creatorName', sortable: true, width: 100 },
                    { title: '录入时间', field: 'createDate', sortable: true, width: 100 }
                ]],
                queryParams: { patientName: '${ae.patientName}', eventDate: eventDate, masterId: fatherId, isMerge: true },
                url: '${base}/ui/service/biz/amcs/adverse/common/findMergeList',
                offset: -50
            });
        },
        beforeSubmit: function (formData, $form) {//提交前处理
            return true;
        },
        afterSubmit: function (rst, $formBox) {//提交服务端后返回数据处理
            console.log(rst, $formBox);
        }
    });
});

$(".fix-back-btn").click(function () {
    operateType = 6;
    submitOpion('回退事件');
});

//查询按钮点击
$(".fix-search-btn").click(function () {
    let patientNo = $("#patientNo").val();
    if (patientNo == null || patientNo == "") {
        $pop.alert('请输入查询信息');
        return;
    }
    queryPatientInfo(patientNo);
});

//赔偿金额输入后触发
$('.amountInp').blur(function(){
    //判断是否赔偿总金额是否有值
    let totalAmount = parseFloat($('#totalAmount').val());
    let amount =  parseFloat($('.amountInp').val());
    if(amount > 0 && totalAmount > 0) {
        $pop.alert('该患者历史已产生赔付' + totalAmount + '元，请确认本次为新增赔付');
    }
});

function queryPatientInfo(patientNo){
    if (patientNo.startsWith("MZ")) {
        //门诊接口
        queryOpDomain(patientNo);
        $('#patientVisitNo').val(patientNo);
    } else if (patientNo.startsWith("ZY")) {
        //住院接口
        queryInDomain(patientNo);
        $('#patientVisitNo').val(patientNo);
    } else {
        $ajax.post('${base}/ui/amcs/adverse/event/api/getMedicalNumber', { "medialNumber": patientNo }, { jsonData: false, tip: false, sync: true, loadingBar: true, loadingMask: true }).done(function (rstData) {
            if (!$.isEmptyObject(rstData)) {
                if(rstData.length == 1) {
                    queryInDomain(rstData[0].inpNumber);
                    $('#patientVisitNo').val(rstData[0].inpNumber);
                }else{
	                $('#patientNo').combogrid({
	                    panelWidth:480,
	                    panelHeight:'auto',
	                    panelMaxHeight:182,
	                    delay: 200,
	                    width:170,
	                    fitColumns:true,
	                    idField:'medicalNumber',
	                    textField:'medicalNumber',
	                    multiple:false,//下拉多选
	                    data: rstData,
	                    columns:[[
	                        {field:'patientId',title:'patientId',hidden:true},
                            {field:'inpNumber',title:'住院编号',hidden:true},
	                        {field:'medicalNumber',title:'病案号',width:80,titletip:true},
	                        {field:'inpTimes',title:'次数'},
	                        {field:'patientName',title:'姓名',width:40,titletip:true},
	                        {field:'plvhTime',title:'出院时间',width:40,titletip:true}
	                    ]],
	                    onSelect : function (val,record){
                            queryInDomain(record.inpNumber);
                            $('#patientVisitNo').val(record.inpNumber);
                            setTimeout(function() {
                                $('#patientNo').combo('destroy');
	                            $('#patitentNoLabel').after("<input class=\"txt txt-validate wp-70 required\" type=\"text\" name=\"patientNo\" id=\"patientNo\"  data-options=\"required:true\" validType=many['fnValid[\"patientNoChk\"]'] />");
	                            $('#patientNo').val(patientNo);
                              }, 1000);
	                        
	                    },
	                    required:true,
	                });
	
	                $('#patientNo').combo('showPanel');
                }
            }

        });     
    };

}

function queryOpDomain(opNumber){
    $ajax.post('${base}/ui/amcs/adverse/event/api/getOpDomain', { "opNumber": opNumber }, { jsonData: false, tip: false, sync: false, loadingBar: true, loadingMask: true }).done(function (rst) {
        window.opDomain = rst
        loadEmrUrl(rst.emrUrl);
        loadPatientInfo(rst.patientId);
    });
}


function queryInDomain(inNumber){
    $ajax.post('${base}/ui/amcs/adverse/event/api/getInDomain', { "inNumber": inNumber }, { jsonData: false, tip: false, sync: false, loadingBar: true, loadingMask: true }).done(function (rst) {
        debugger;
        window.inDomain = rst
        if(!$.isEmptyObject(rst.patientId)) {
	        loadOprInfo(rst);
	        loadEmrUrl(rst.emrUrl);
	        loadPatientInfo(rst.patientId);
            if ($('#eventCode').val() == '109'){
                loadUnplReoprts();
            };
        }
        
    });
}

function loadOprInfo(cond){
    $ajax.post('${base}/ui/amcs/adverse/event/api/getOprInfo', cond, { jsonData: false, tip: false, sync: true, loadingBar: true, loadingMask: true }).done(function (rst) {
        window.opr = rst;
    });
};

function loadEmrUrl(emrUrl){
    $("#emrUrl").val(emrUrl)
    if (!!window.afterSerachPatient) {
        try {
            window.afterSerachPatient();
        } catch (error) {
            console.log(error)
        }
    }
}

function loadUnplReoprts(){
    if (!!window.afterSerachInpnumber) {
        try {
            window.afterSerachInpnumber();
        } catch (error) {
            console.log(error)
        }
    }
}

function loadPatientInfo(patientId){
    if(!patientId){
        $pop.alert('查询不到患者信息,请检查门诊号/住院号是否正确!!!');
    }
    window.patientId = patientId
    $ajax.post('${base}/ui/service/biz/amcs/adverse/common/getPatientInfo', { "patientId": patientId }, { jsonData: false, tip: false, sync: true, loadingBar: true, loadingMask: true }).done(function (rst) {
        if ($.isEmptyObject(rst)) {
            $("#patientName").val('');
            $("#patientSex").combobox('setValue', '');
            $("#patientBirth").setVal('');
            $("#patientId").val('');

            return;
        } else {
            $("#patientName").textbox('setValue',rst.name);
            $("#patientBirth").setVal(rst.birthday.substr(0, 10));
            $("#patientId").val(patientId);
            $("#patientSex").combobox('setValue', rst.sex);
            updateAge(rst.birthday)
            if ($('#eventCode').val() == '201' || $('#eventCode').val() == '203' || $('#eventCode').val() == '204') {
	            $("#patientIdNumber").val(rst.idNumber);
	            $("#patientJob").val(rst.occuTypeName);
	            $("#patientNationality").val(rst.countryName);
            }
            
            
        }
    });
}

/**
审核事件
参数： 1 提交 2 暂存 5 取消 6 回退 7 点评
*/

function submitOpion(title) {
    //点评事件再次点评时，获取之前的点评信息
    let opinionData = {};
    let basicId = $("#basicId").val();
    if (!$.isEmptyObject($("#prevId").val())) {
        basicId = $("#prevId").val();
    }
    let queryData = { basicId: basicId, node: '${pageType!}', type: 7 };
    let areaSize = ['400px', '260px'];
    if('${pageType!}' == 2 || '${pageType!}' == 3){
    	areaSize = ['400px', '280px'];
    };
    if (operateType == 7) {
        $ajax.post('${base}/ui/service/biz/amcs/adverse/common/findOpinionForReview', queryData, { jsonData: false, tip: false }).done(function (data) {
            $("#popOpinion").val(data.opinion);
            $("#popId").val(data.id);
        });
        areaSize = ['800px', '400px'];
    };
    
    

    opinionFormPop = $pop.popTemForm({
        title: title,
        temData: opinionData,
        temId: 'opinionFormTem',
        area: areaSize,
        onPop: function (formData, $form) {//提交前处理
        	$(".btn-cancel-pop").click(function (){
        		layer.close(opinionFormPop);
        	});
        	$(".risk-row").hide();
        	$("#infoForm").addClass("pad-t20");
            if (operateType == 1) {
                $("#popOpinion").removeAttr("noNull");
                if('${pageType!}' == 2 || '${pageType!}' == 3) {
                	$(".risk-row").show();
                	$("#infoForm").removeClass("pad-t20");
                }
            } else if (operateType == 5 || operateType == 7 || operateType == 6) {
                $("#popOpinion").addClass("required")
                $("#popOpinion").attr("noNull", '请填写意见');
            }
            if (operateType == 7) {
                $("#popOpinion").css({ "height": "280px" });
            } else {
                $("#popOpinion").css({ "height": "140px" });
            }
            return true;
        },
        afterSubmit: function(rst, $formBox) {

        }
    });

};


function doSave() {
    let formData = $('.formA').sovals();
    formData.tags = getTags();
    formData.operateType = operateType;
    $ajax.post('${base}/ui/service/biz/amcs/adverse/common/save', formData, { jsonData: true, tip: false }).done(function (rst) {
        if (rst.code === '200' || rst.code === '201') {
            if (operateType == 2) {
                $("#eventId").val(rst.data.eventId);
                $("#basicId").val(rst.data.basicId);
                $pop.success('保存成功!');
            } else if (operateType == 1) {
                $pop.success('保存成功!', function (index) {
                    $pop.closeTabWindow();
                });
            } else {
                location.href = location.href;
                $pop.success('保存成功!');
            }

        }
    });
}


function getTags() {
    let tags = '';
    $.each($(".tags").filter(":checked"), function () {
        tags += $(this).val() + ",";
    });
    return tags;
}

window.submitEditForm = function (opt, $form, data) {
	if($('.risk-row').is(':visible')) {
    	if(!data.isHighRisk){
		 	$pop.alert('请选择是否高风险');
			return;
		}; 
    };
    $.extend(data, $('.formA').sovals());
    
    data.operateType = operateType;
    data.tags = getTags();
    $ajax.post({
        url: '${base}/ui/service/biz/amcs/adverse/common/save',
        jsonData: true,
        data: data,
        tip: '确认提交？',
        success: function (rst) {
            $pop.close(opinionFormPop);
            if (operateType == 1) {
                $pop.success('保存成功!', function (index) {
                    refreshList();
                    //非计划事件，保存后显示打印按钮
                    if('${eventCode}' == '109'){
                        if('${pageType!}' == 0){
                            //保存按钮置灰，不可再点击, 避免重复提交
                            $(".fix-save-btn").attr("disabled", true);
                        }
                        //$(".fix-print-btn").removeClass("hidden");
                    }
                });
            } else if (operateType == 5) {
                $pop.success('取消成功!', function (index) {
                    refreshList();
                });
            } else if (operateType == 6) {
                $pop.success('回退成功!', function (index) {
                    refreshList();
                });
            } else if (operateType == 7) {
                $pop.success('点评成功!', function (index) {
                    $("#reviewTab").click();
                    eventReview.reload();
                });
            } else {
                $pop.success('操作成功！');
            };
        }
    });
};

function refreshList() {
    $pop.closePop();
    if (operateType == 1) {
        $pop.closeTabWindow();
    };
    return;
    //刷新审核列表页面
    if ('${sourceType}' == 1) {
        let title = '退回事件';
        $pop.newTabWindow(title, '${base}/ui/service/biz/amcs/adverse/common/listReturnPage');
    } else {
        if ('${pageType!}' == 2) {
            let auditTitle = '医院不良事件管理';
            if ('${type}' == 1) {
                auditTitle = '医疗类不良事件审核';
            } else if ('${type}' == 2) {
                auditTitle = '护理类不良事件审核';
            } else if ('${type}' == 3) {
                auditTitle = '院感不良事件审核';
            }
            $pop.newTabWindow(auditTitle, '${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=2&type=${type}');
        } else if ('${pageType!}' == 3) {
            if ('${isReview!}' == 'true') {
                $pop.newTabWindow('省区不良事件点评', '${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=3&is_review=1');
            } else {
                $pop.newTabWindow('省区不良事件管理', '${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=3');
            }

        } else if ('${pageType!}' == 4) {
            if ('${isReview!}' == 'true') {
                $pop.newTabWindow('集团不良事件点评', '${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4&is_review=1');
            } else {
                $pop.newTabWindow('集团不良事件管理', '${base}/ui/service/biz/amcs/adverse/common/listPage?page_type=4');
            }
        }
    }
    $pop.closeTabWindow();
}

/**
*装载顶部数据
*/
function loadUpperData() {
    let UPPER_DATA = `${aeJson}`;
    if (UPPER_DATA) {
        UPPER_DATA = JSON.parse(UPPER_DATA);
        if (UPPER_DATA.eventDate) {
            UPPER_DATA.eventDate = UPPER_DATA.eventDate.split(" ")[0];
        }
        $('#outpForm').form('load', UPPER_DATA);
    }
}
loadUpperData();


/**
 * 查询标签信息 
 */
function queryTagsList() {
    let url = base + '/ui/amcs/dict/getAeDict?type=tag_type';
    $ajax.post(url, false, false).done((res) => {
        initTagDOM(res);
    });
}
queryTagsList();

/**
 * 初始化标签节点
 */
function initTagDOM(list) {
    var checkVal = '${tags!}';
    var domStr = '';
    list.forEach(function (item) {
        let property = "";
        if (checkVal == -1) {
            property = "disabled=true;";
        } else {
            if (checkVal.indexOf(item.valueCode) > -1) {
                property = "checked";
            };
            if ('${ae.reportTimes!}' > 1) {
                property += " disabled=true;";
            };
        }

        domStr += '<label class="lab-val"><input type="radio" class="rad tags" ' + property + ' name="tag' + item.valueCode + '" value="' + item.valueCode + '" />' + item.valueDesc + '</label>';
    });
    $("#tag-pointer").after(domStr);
    $('.tags').click(function () {
        let domName = $(this).attr('name');
        let $radio = $(this);

        if ($radio.data('waschecked') == true) {
            $radio.prop('checked', false);
            $("input:radio[name='" + domName + "']").data('waschecked', false);
        } else {
            $radio.prop('checked', true);
            $("input:radio[name='" + domName + "']").data('waschecked', false);
            $radio.data('waschecked', true);
        }
    })

}


function DIFFTIME(startTime, endTime, unit) {
    // 判断当前月天数
    function getDays(mouth, year) {
        let days = 30
        if (mouth === 2) {
            days = year % 4 === 0 ? 29 : 28
        } else if (mouth === 1 || mouth === 3 || mouth === 5 || mouth === 7 || mouth === 8 || mouth === 10 || mouth === 12) {
            // 月份为：1,3,5,7,8,10,12 时，为大月.则天数为31；
            days = 31
        }
        return days
    }
    const start = new Date(startTime)
    const end = new Date(endTime)
    // 计算时间戳的差
    const diffValue = end - start
    // 获取年
    const startYear = start.getFullYear()
    const endYear = end.getFullYear()
    // 获取月
    const startMouth = start.getMonth() + 1
    const endMouth = end.getMonth() + 1
    // 获取日
    const startDay = start.getDate()
    const endDay = end.getDate()
    // 获取小时
    const startHours = start.getHours()
    const endHours = end.getHours()
    // 获取分
    const startMinutes = start.getMinutes()
    const endMinutes = end.getMinutes()
    // 获取秒
    const startSeconds = start.getSeconds()
    const endSeconds = end.getSeconds()
    // 下方注释两行为调试用
    // console.log('start:', startYear, startMouth, startDay, startHours, startMinutes, startSeconds)
    // console.log('end:', endYear, endMouth, endDay, endHours, endMinutes, endSeconds)
    if (unit === 'y' || unit === 'M') {
        // 相差年份月份
        const diffYear = endYear - startYear
        // 获取当前月天数
        const startDays = getDays(startMouth, startYear)
        const endDays = getDays(endMouth, endYear)
        const diffStartMouth = (startDays - (startDay + ((startHours * 60 + startMinutes + startSeconds / 60) / 60 / 24) - 1)) / startDays
        const diffEndMouth = (endDay + ((endHours * 60 + endMinutes + endSeconds / 60) / 60 / 24) - 1) / endDays
        const diffMouth = diffStartMouth + diffEndMouth + (12 - startMouth - 1) + endMouth + (diffYear - 1) * 12
        if (unit === 'y') {
            return Math.floor(diffMouth / 12 * 100) / 100
        } else {
            return diffMouth
        }
    } else if (unit === 'd') {
        const d = parseInt(diffValue / 1000 / 60 / 60 / 24)
        return d
    } else if (unit === 'h') {
        const h = parseInt(diffValue / 1000 / 60 / 60)
        return h
    } else if (unit === 'm') {
        const m = parseInt(diffValue / 1000 / 60)
        return m
    } else if (unit === 's') {
        const s = parseInt(diffValue / 1000)
        return s
    } else {
        console.log('请输入正确的单位')
    }
}


// 针对病历外的 复制和拷贝
$(window.document.body).bind({
    paste: function (e) {
        patientId = window.patientId
        var clipboardData = window.clipboardData; // IE
        if (!clipboardData) { //chrome
            clipboardData = e.originalEvent.clipboardData
        }
        var copyStr = clipboardData.getData('Text');

        if (copyStr.indexOf('<<<<<') > -1) {//如果拷贝的是病历内容
            var strArr = copyStr.split("<<<<<");
            var copyPid = strArr[2];
            var str = unescape(strArr[1]);
            str = str.replace(/[\[\]\{\}]/g, "");
            // console.log(patientId)
            if (patientId == copyPid) {//只可以拷贝患者自己的病历内容
                if (str) {//有内容才粘贴
                    $(e.target).val(str);
                }
            } else {
                $pop.msg('非本人病历内容，不允许拷贝！');
            }
            return false;
        }
    }
});

// 发生地点显示/隐藏
if ('${ae.addressRemark}' == null || '${ae.addressRemark}' == '') {
    $('#addressRemark').val('');
    $('#addressRemark').hide();
} else {
    $('#addressRemark').show();
    $('#addressRemark').css('height', '');
}
if ('${ae.addressInareaRemark}' == null || '${ae.addressInareaRemark}' == '') {
    $('#addressInareaRemark').val('');
    $('#addressInareaRemark').hide();
} else {
    $('#addressInareaRemark').show();
    $('#addressInareaRemark').css('height', '');
}
if ('${ae.addressOperroomRemark}' == null || '${ae.addressOperroomRemark}' == '') {
    $('#addressOperroomRemark').val('');
    $('#addressOperroomRemark').hide();
} else {
    $('#addressOperroomRemark').show();
    $('#addressOperroomRemark').css('height', '');
}

// 手动输入原因显示
if ('${ae.manualInputReason}' == null || '${ae.manualInputReason}' == '') {
    $('.cls-manual-input-reason').hide();
    $('#manualInputReason').validatebox('disableValidation');
}else{
    $('.cls-manual-input-reason').show();
    $('#manualInputReason').validatebox('enableValidation');
}
$(".switch-mode-btn").click(function () {
 $("#patientName").textbox({readonly: false});
 $("#patientBirth").textbox({readonly: false});
 $("#patientSex").combobox('readonly', false);
 $("#patientAge").textbox({readonly: false});
 $('.cls-manual-input-reason').show();
 $('#manualInputReason').validatebox('enableValidation');
});
// 设置只读
$("#patientName").textbox({readonly: true});
$("#patientBirth").textbox({readonly: true});
$("#patientSex").combobox('readonly', true);
$("#patientAge").textbox({readonly: true});


window.patientNoChk = function (patientNo) {
    var res = {};
    var reg = new RegExp("'");
    if (reg.test(patientNo)) {
        res.result = false;
        res.msg = "住院/门诊号不能包含字符'";
    } else {
        res.result = true;
    }
    return res;
}
// 初始化表单元素
$('#patientNo').validatebox({
    required: true,
    validType: 'many["fnValid[\'patientNoChk\']"]'
});




/** 页面初始化时判断是否需要 */
let eventDate = new Date($("#eventDate").val());
showFirstReport(eventDate);

$form.soDate('.eventDate', {
    onpicked: function (obj) {
        let selDate = new Date($(obj).val());
     	const isWeekday = (paramDate) => paramDate.getDay() % 6 !== 0;
	    if(isWeekday(selDate)){
	        $('#dateType').combobox('setValue', '工作日');
	    }else{
	        $('#dateType').combobox('setValue', '周末');
	    }
	    showFirstReport(selDate);   
    }
});

function showFirstReport(eventDate){
	let targetDate = new Date('2023-01-01');
    let newItem = $('<div class="item-one" style="padding-left:130px;">' +
        '<label class="lab-item" style="width:120px;">是否首次在该系统上报</label>' +
        '<select class="drop easyui-combobox" style="width:100%;" name="isFirstReport" data-options="editable:false, required:true">' +
        '<option value="1">是</option>' +
        '<option value="0">否,已在旧系统上报</option>' +
        '</select></div>');
    newItem.find('select[name="isFirstReport"]').val(${ae.isFirstReport});

    let $firstReportDiv = $('#firstReportDiv');
    $firstReportDiv.empty();

    if (eventDate < targetDate) {
        $firstReportDiv.append(newItem);
    }
}





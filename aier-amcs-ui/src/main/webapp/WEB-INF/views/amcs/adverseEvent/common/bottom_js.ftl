$('.drop-doctorIda').combobox({
    //首拼码检索
    limitToList: true,
    reversed: true,
    panelMaxHeight: 200,
    panelHeight: 'auto',
    filter: function(q, row){
        return (row['firstSpell']&&row['firstSpell'].toLowerCase().indexOf(q.toLowerCase()) >-1  || row['name']&&row['name'].indexOf(q) >-1 )
    },
    onSelect: function (row) {
        if(this.id == 'operDoctCode') {
        	$("#operDoctCode").val(row.id);
        	$("#operDoctName").val(row.name);
        }else{
        	$("#staffId").val(row.id);
        	$("#staffName").val(row.name);
        }
        
        
    },
    loader: function (param, success, error) {
        $.ajax({
            type: 'post',
            url: '${base}/ui/sys/staff/getHospStaff?_easyui_=COMB',
            dataType: 'json',
            contentType: 'application/json;charset=utf-8', // 设置请求头信息
            data: JSON.stringify(param),
            success: function (result) {
                //查询成功，将医生信息添加到集合中， 以便新增操作时，直接给医生列表绑定值
                //新增离职人员和外部人员,后勤人员
                result.push({name: '后勤人员', id: '-3'});
                result.push({name: '外部人员', id: '-2'});
                result.push({name: '已离职', id: '-1'});
                success(result);
            }
        });
    },
    queryParams: {isDistinct: true},
    method: "post",
    valueField: "id",
    textField: "name",
    value :"${ae.staffId}"
});
let staffType = $('#staffType').combobox('getValue')
if(staffType == '2') {
    $('.staffYearsInEye').css('display','');
    $('#staffYearsInEye').numberbox('textbox').validatebox({
        required: true,
        missingMessage: '眼科工作年限必填'
    });
} else {
    $('.staffYearsInEye').css('display','none');
    $('#staffYearsInEye').numberbox('textbox').validatebox({
        required: false
    });
}
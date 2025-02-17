<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>不良事件点评 - 爱尔医院</title>
    <style type="text/css">
    </style>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
            <input class="txt" name="reportTimes" value="1" type="hidden" />
            <input class="txt" name="isPrimary" value="1" type="hidden" />
            <input class="txt" name="pageType" value="${pageType}" type="hidden" />
            <input class="txt" name="node" value="${pageType}" type="hidden" />
            <input class="txt" name="isExpert" value="${isExpert}" type="hidden" />
            [#if pageType != 5]
	            <label class="lab-inline">事件类型：</label>
	            <select class="drop drop-sl-v easyui-combobox  w-150" name="eventType" id="eventType" data-options="valueField:'eventCode',textField:'eventName',clearIcon:true">
	            </select>
	            [#if empType==1]
		            <label class="lab-inline">省区：</label>
		            <select class="drop drop-sl-v easyui-combobox  w-150 province" name="province"   data-options="valueField:'id',textField:'name',clearIcon:true"></select>
	            [/#if]
	            [#if empType==1 || empType==2]
	            <label class="lab-inline">医院：</label>
	            <select class="drop drop-sl-v easyui-combobox w-150 hosp" name="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
	            </select>
	            [/#if]
	            <label class="lab-inline">上报日期：</label>
           	    <input type="text" class="txt so-rangeDate txt-validate" name="reportDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}" />
           	    <label class="lab-inline">省区审核通过日期：</label>
	            <input type="text" class="txt so-rangeDate txt-validate" name="provAuditDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>
	            <label class="lab-inline">省区点评状态：</label>
	            <select class="drop easyui-combobox w-100"  name="pReviewStatus">
		            <option value="" ></option>	         
		            	<option value="0">未点评</option>
			            <option value="1">已点评</option>
	            </select>
	            <label class="lab-inline">集团点评状态：</label>
	            <select class="drop easyui-combobox w-100"  name="gReviewStatus">
		            <option value="" ></option>	         
		            	<option value="0">未点评</option>
			            <option value="1">已点评</option>
	            </select>
	            <input class="txt" name="tags" id="tags" type="hidden" />
	        	<label class="lab-inline">涉及科室/人员类型</label>
	            <select class="drop easyui-combobox w-80" id="tagSel" data-options="multiple:true, editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
	            </select>
	       [#else]
	            <label class="lab-inline">指派日期：</label>
	            <input type="text" class="txt so-rangeDate txt-validate" name="assignDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>
	            <label class="lab-inline">专家点评状态：</label>
			    <select class="drop easyui-combobox w-120" id="eReviewStatus" name="eReviewStatus" data-options="multiple:false, clearIcon:true">
			      <option value=""></option>
			      <option value="0">未点评</option>
			      <option value="1">已点评</option>
			    </select>	       
	       [/#if]
            <label class="lab-inline">发生日期：</label>
            <input type="text" class="txt so-rangeDate txt-validate" name="eventDate" data-opt="{val:'',auto:false,opens:'center', maxSpan:{days: 1095}}"/>

            [#if pageType == 4]
                <input class="txt" name="eventLevels" id="eventLevels" type="hidden" />
                <label class="lab-inline">事件分级：</label>
                <select class="drop easyui-combobox w-100" id="eventLevel" data-options="multiple:true, clearIcon:true" >
                    <option value="IV级（隐患事件）">IV级（隐患事件）</option>
                    <option value="III 级（未造成后果事件）">III 级（未造成后果事件）</option>
                    <option value="II 级（不良事件）">II 级（不良事件）</option>
                    <option value="I级（警告事件）">I级（警告事件）</option>
                </select>
                <input class="txt" id="gradeOneCodeStr" name="gradeOneCodeStr" type="hidden" />
                <label class="lab-inline">事件分类一级</label>
                <select class="drop easyui-combobox w-150 gradeOne" id="gradeOne" data-options="multiple:true,editable:false,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
                </select>
                <input class="txt" id="gradeTwoACodeStr" name="gradeTwoACodeStr" type="hidden" />
                <label class="lab-inline">事件分类二级I</label>
                <select class="drop easyui-combobox w-150 gradeTwoA" id="gradeTwoA" data-options="editable:false, multiple:true,valueField:'valueCode',textField:'valueDesc',clearIcon:true">
                </select>
            [/#if]
            <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
    <script id="opinionFormTem" type="text/html">
    	<form id="infoForm" class="soform form-validate pad-t20 solab-l form-enter" data-opt="{beforeCallback:'submitOpinion'}">
    	    <input type="hidden" name="opinionId" id="opinionId" />
            <input type="hidden" name="basicId" id="basicId" />
            <input type="hidden" name="hospId" id="hospId" />
    		<div class="row">
    			<div class="p12">
                    <textarea class="txta txt-validate" id="opinion" name="opinion" maxlength="500" style="height: 280px;"></textarea>
               	</div> 
    		</div>
    		<p class="row-btn center">
            	<input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定" />
            	<input type="button" class="btn btn-cancel-pop" name="btnCancel" value="取 消" />
        	</p>
    	</form>
    </script>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    var isExpert = false;
    if($.trim('${isExpert}') != ''){
    	isExpert = '${isExpert}';
    }
    var opinionFormPop;
    requirejs(['lodash', "easygridEdit", 'pub'], function (_, $e) {
        this.reloadEventType();
        var tagData = {};
        let param = {paraType : ['tag_type', 'grade_one', 'grade_two_a']};	
        $ajax.postSync('${base}/ui/amcs/dict/getAeMoreList',param,false,false).done(function (rst) {
            tagData = rst.data.tag_type;
            $("#tagSel").combobox("loadData", tagData);
            $('.gradeOne').combobox('loadData', rst.data.grade_one);
            $('.gradeTwoA').combobox('loadData',rst.data.grade_two_a);
        });

        $('#eventLevel').combobox({
			onChange: function(newValue,oldValue){
			    $("#eventLevels").val(newValue);
			}
		});

        $("#eventLevel").combobox("setValues", []);
        
        $('#gradeOne').combobox({
            onChange: function(newValue,oldValue){
                $('#gradeOneCodeStr').val(newValue);
                var paramDict = {type : 'grade_two_a',gradeOneArr:newValue};
                $ajax.postSync('${base}/ui/amcs/dict/getEventQueryAeDict',paramDict,false,false).done(function (rst) {
                    $('.gradeTwoA').combobox('loadData',rst);
                });
            }
        });
        

         $('#gradeTwoA').combobox({
            onChange: function(newValue,oldValue){
                $('#gradeTwoACodeStr').val(newValue);
            }
        });

    	$grid.newGrid("#gridBox",{
	    	columns:[[
				{title: "操作", field: "op", width: 60, formatter: function (v, row, index) {
				        return  '<span class="s-op s-op-review icon-message2" title="点评" rel="' + index + '"></span>';
					}
				},
		        {title:'id',field:'id',width:80,hidden:true},
		        {title:'eventCode',field:'eventCode',width:80,hidden:true},
		        {title:'eventId',field:'eventId',width:80,hidden:true},
		        {title:'node',field:'node',width:80,hidden:true},
                {title:'hospId',field:'hospId',width:80,hidden:true},
                {title:'页面地址', field:'eventUrl', width:120,hidden:true},
		        {title:'事件名称', field:'eventName', width:200},
		        {title:'亚专科', field:'subspecialty', width:80},
		        {title:'涉及科室/人员类型', field:'tags', hidden: true, width:145,
		        	formatter: function(value,row,index){
                        let tags = "";
                        if(!$.isEmptyObject(value)){
                            tagData.forEach(function(key){
                                if(value.indexOf(key.valueCode) >-1){
                                    tags += "," + key.valueDesc;
                                }
                            });
                            if(!$.isEmptyObject(tags)){
                                tags = tags.substr(1);
                            }
                        }
						return tags;
					}
                },
		        {title:'事件分类一级', field:'gradeOne', hidden: false, width:150},
		        {title:'省区', field:'hospParent', hidden: false, width:80},
		        {title:'医院名称', field:'hospName', width:190, formatter: function(v, row, index){
                    if('${pageType}' == '5'){
                        return '*********';
                    }else{
                        return v;
                    }
                }},
		        {title:'患者姓名', field:'patientName', hidden: false, width:80, formatter: function(v, row, index){
                    if('${pageType}' == '5'){
                        return '*********';
                    }else{
                        return v;
                    }
                }},
		        {title:'上报人', field:'creatorName', width:80},
		        {title:'上报时间', field:'createDate', width:115, format:'yyyy-MM-dd'},
		        {title:'指派时间', field:'assignDate', width:115, format:'yyyy-MM-dd', hidden: true},
		        {title:'发生时间', field:'eventDate', width:115, format:'yyyy-MM-dd', sortable: true},
		        {title:'省区审核通过日期', field:'provAuditDate', width:115, format:'yyyy-MM-dd', sortable: true},
		        {title:'专家点评状态',field:'eReviewStatus',width:80, formatter: function (v, row, index) {
                        if (v > 0) {
                            return "已点评";
                        } else {
                            return "未点评";
                        }
                    }
                },
                {title:'省区点评状态',field:'pReviewStatus',width:80, formatter: function (v, row, index) {
                        if (v > 0) {
                            return "已点评";
                        } else {
                            return "未点评";
                        }
                    }
                },
                {title:'集团点评状态',field:'gReviewStatus',width:80, formatter: function (v, row, index) {
                        if (v > 0) {
                            return "已点评";
                        } else {
                            return "未点评";
                        }
                    }
                },
		        {title:'所在节点',field:'nodeName',width:80},
		        {title:'事件类型', field:'eventTypeName', width:120}
		    ]],
            rowStyler : function(index, row) {
                if('${pageType}' == '3') {
	                if(row.pReviewStatus > 0){
	                    return "background-color: #82E0AA";
	                }
                }else if('${pageType}' == '4') {
                	if(row.gReviewStatus > 0){
	                    return "background-color: #82E0AA";
	                }
                }else if('${pageType}' == '5') {
                	if(row.eReviewStatus > 0){
	                    return "background-color: #82E0AA";
	                }
                }                
            },
		    onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {
		    	$('.s-op-review').click(function () {
					var ix = $(this).attr('rel');
					var row = data.rows[ix];
					let urlSuffix = '?eventCode='+ row.eventCode +'&id=' + row.id + '&node=' + row.node +'&page_type=${pageType}'+'&modify=false';
					urlSuffix += '&is_review=${isReview}&is_expert=${isExpert}&showLast=true';
					
					if(row.id != ''){
					    let title = row.eventTypeName;
					    let url = '${base}/ui/service/biz/amcs/adverse/common/indexPage' + urlSuffix;
                        $pop.newTabWindow(title, url);
                     }
				});
				if('${pageType}' == '4'||'${pageType}' == '3') {
                	$("#gridBox").datagrid("showColumn", "tags");
                }else if('${pageType}' == '5') {
                    $("#gridBox").datagrid("hideColumn", "pReviewStatus");
                    $("#gridBox").datagrid("hideColumn", "gReviewStatus");
                    $("#gridBox").datagrid("hideColumn", "hospParent");
                    $("#gridBox").datagrid("hideColumn", "creatorName");
                    $("#gridBox").datagrid("hideColumn", "createDate");
                    $("#gridBox").datagrid("showColumn", "assignDate");
                    
                }else{
                    $("#gridBox").datagrid("hideColumn", "reviewStatus");
                    $("#gridBox").datagrid("hideColumn", "eReviewStatus");
                    
                }      
      		},
            onClickCell : function (index, field, value) {
                //当点击集团点评列时弹出相应点评窗
                var row = $('#gridBox').datagrid('getRows')[index];
                let basicId = row.id;
                let hospId = row.hospId;
                let queryData = { basicId: basicId, type: 7 };                
                if (field == 'gReviewStatus') {
                    queryData.node = 4;
                    if(row.gReviewStatus == 1){
                        $ajax.post('${base}/ui/service/biz/amcs/adverse/common/findOpinionForReview', queryData, { jsonData: false, tip: false }).done(function (data) {
                            $("#opinion").val(data.opinion);
                            $("#opinionId").val(data.id);
                        });
                    }
                    opinionFormPop = $pop.popTemForm({
                        title: "集团不良事件点评",
                        temId: 'opinionFormTem',
                        area: ['800px', '400px'],
                        onPop: function (formData, $form) {//提交前处理
                            $(".btn-cancel-pop").click(function (){
                                layer.close(opinionFormPop);
                            });
                            $("#basicId").val(basicId);
                            $("#hospId").val(hospId);
                        },
                        afterSubmit: function (rst, $formBox) {//提交服务端后返回数据处理
                            console.log('提交。。。。。。');
                        }
                    });
                }else if(field == 'pReviewStatus'){
                    if(row.pReviewStatus == 0){
                        return;
                    };
                    queryData.node = 3;
                    $ajax.post('${base}/ui/service/biz/amcs/adverse/common/findOpinionForReview', queryData, { jsonData: false, tip: false }).done(function (data) {
                        let pReview = layer.open({//打开页面中内容层
                            type: 1,
                            title :'省区点评内容',
                            content: data.opinion,
                            area :['600px', '420px']
                        });
                        $(".layui-layer-content").css("background-color", "#e2e2e2");
                        $('.layui-layer-shade').bind('mousedown', function(){
                            layer.close(pReview);
                        }); 

                    });
                };
            },
      		queryParams : {node: '${pageType}', pageType: '${pageType}', reportTimes: 1, isPrimary: true, isExpert: isExpert},
      		url: '${base}/ui/service/biz/amcs/adverse/common/findReviewList',
      		offset : -50
  		});
    
    });
    
    $('#tagSel').combobox({
		onChange: function(newValue,oldValue){
		    $("#tags").val(newValue);
		}
	});

    function submitOpinion() {
        let data = $('#infoForm').sovals();
        //点评类型
        data.operateType = 7;
        //省区节点
        data.node = 4;
        $ajax.post({
            url: '${base}/ui/service/biz/amcs/adverse/record/saveOpinion',
            jsonData: true,
            data: data,
            tip: '确认提交？',
            success: function (rst) {
            	console.log(rst);
                $pop.close(opinionFormPop);
                var param = $('#sbox').sovals();
                param.rows = $('#gridBox').datagrid('options').pageSize;
                param.page = $('#gridBox').datagrid('options').pageNumber;
                $('#gridBox').datagrid('reload',param);
            }
        });

    };
    
    function reloadEventType() {
    	[#if empType==1]
                $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
                    $('.province').combobox('loadData', rst);
                });
                $('.province').combobox({
                    onChange: function(v){
                        let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                        $('.hosp').combobox('reload', url);
                    }
                });
            [#elseif empType==2]
                var paramInsi = {insiId:'${insiId}'};
                $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
                    $('.hosp').combobox('loadData', rst);
                });
            [/#if]
            
   		$ajax.postSync('${base}/ui/amcs/adverse/eventConfig/getAll',null,false,false).done(function (rst) {
    		$('#eventType').combobox('loadData', rst.rows);
    	});
    
    };
</script>
</html>
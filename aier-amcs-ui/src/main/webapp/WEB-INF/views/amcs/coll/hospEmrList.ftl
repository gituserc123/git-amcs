<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医院电子病历应用水平采集 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
.mainfooter{padding:12px 10px 0;text-align: right;}
</style>
</head>
<script type="text/javascript">
</script>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
</div>
</body>
<script id="editExpertTem" type="text/html">
    <input type="text" style="position: absolute; left: -1000px;" id="hide-input" />
    <div class="searchHead">
        <form id="ssbox" class="soform form-enter">
            <input class="txt inline w-250" type="text" name="hospKey" id="hospKey" value="" placeholder="医院名称、编号">
            <button type="button" class="btn btn-small btn-primary so-search" data-opt="{grid:'#staffGridBox',scope:'#ssbox'}">查 询</button>
	    </form>    
    </div>
	<div class="cont-grid bot-line-sub">
        <div id="gridBox"></div>
	</div>
	
	<div class="mainfooter">
	    <span class="btn btn-primary btn-ok">添加</span>
	    <span class="btn btn-cancel">取消</span>
	</div>
</script>
<script id="editTem" type="text/html">
     <form class="soform form-validate formColl pad-t20">
     	<input type="hidden" name="id" value="${id!}" />
     	<input type="hidden" name="hospId" value="${hospId!}" />
     	<input type="hidden" id="gradeName" name="gradeName" value="${gradeName!}" />
     	<input type="hidden" id="applyGradeName" name="applyGradeName" value="${applyGradeName!}" />
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">医院名称</label>
                    <input class="txt txt-validate" type="text" name="hospName" value="${hospName!}" readonly="readonly"/>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">已评级别</label>
                    [@ui_select id="grade" name="grade" tag="emr_level" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value=""/]
                </div>
            </div>
        </div>
        <div class="row">
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">是否准备申报</label>
                    <select class="drop easyui-combobox" style="width:100%;" name="isApply" id="isApply" data-options="editable:false, required:true" data-options="value:'${isApply}'">
	                    <option value="1">是</option>
	                    <option value="0">否</option>
	                </select>
                </div>
            </div>
            <div class="p6">
                <div class="item-one">
                    <label class="lab-item">申报意向</label>
                    [@ui_select id="applyGrade" name="applyGrade" tag="emr_level" style="width:100%;" dataOptions="editable:false,required:true" filterkey="firstSpell" value="${applyGrade }"/]
                </div>
            </div>
        </div>
        <div class="row">
             <div class="item-one">
             	<label class="lab-item">备注</label>
				<textarea class="txta txt-validate adaptiveTextarea"  id="remark" name="remark" maxlength="500" cols="200" row="10"></textarea>
             </div>
        </div>
        <div class="row" id="wrap">
            <div class="wrapper">
                <div class="container">
                    <div id="uploader" class="GE-uploader">
                        <div class="queueList">
                            <div id="dndArea" class="placeholder">
                                <div id="filePicker" name="pickers"></div>
                            </div>
                        </div>
                        <div class="statusBar" style="display:none;">
                            <div class="progress">
                                <span class="text">0%</span>
                                <span class="percentage"></span>
                            </div><div class="info"></div>
                            <div class="btns" style="top: 0;">
                                <div id="filePicker2"></div>
                                <div class="uploadBtn" style="display: none;">开始上传</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	    </div>
        
        <div class="mainfooter">
            <span class="btn btn-primary btn-save">保存</span>
            <span class="btn btn-cancel">取消</span>
        </div>
    </form>
</script>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
	requirejs(['lodash', "easygridEdit", "uploadImages", 'myupload', 'pub'], function (_, $e, uploadImages) {
	   

        $grid.newGrid("#gridBox",{
    	 	pagination: true,
            fitColumns: false,
            tools: [[
                {
                    iconCls: 'plus', text: '新增',click:function (){ popTem('医院电子病例应用水平填报', null, 'add', null);}
			    }
			]],
	    	columns:[[
	    		{title: "操作", field: "op", width: 75, formatter: function (v, row, index) {
	    	        let opStr = '<span class="s-op s-op-view icon-eye" title="查看" rel="' + index + '"></span>&nbsp;&nbsp;&nbsp;';
	    	    	opStr += '<span class="s-op s-op-modify icon-edit" title="修改" rel="' + index + '"></span>';
	    	    	return opStr
					}
				},
		        {title:'id',field:'id',width:80, hidden:true},
		        {title:'医院名称', field:'hospName', width:200},
                {title:'已评级别', field:'gradeName', width:200},
                {title:'是否准备申报', field:'isApply', width:200,formatter: function (v,row,index){
                	return ['<span class="red">否</span>','<span class="green">是</span>'][v];
            	}},
                {title:'申报意向', field:'applyGradeName', width:200},
                {title:'年度', field:'modifyYear', width:120},
                {title:'填写时间', field:'modifyDate', width:120, format:'yyyy-MM-dd'}

            ]],
		    rowStyler : function(index, row) {

            },
            onBeforeLoad: function(param){
               
            },
		    onLoadSuccess : function (data) {
		        $('.s-op-view').click(function () {
					var ix = $(this).attr('rel');
					var id = data.rows[ix].id;
					popTem('医院电子病例应用水平查看', id, 'view', ix)
				});
				$('.s-op-modify').click(function () {
					var ix = $(this).attr('rel');
					var id = data.rows[ix].id;
					popTem('医院电子病例应用水平编辑', id, 'edit', ix)
				});
            },
            queryParams : {},
      		url: '${base}/ui/amcs/coll/hosp/emr/findList'
  		});

        var adaptiveTextarea = document.getElementsByClassName('adaptiveTextarea');

        for(a in adaptiveTextarea){
            makeExpandingArea(adaptiveTextarea[a]);
        }

        function popTem(title, id, op, index){
            var attachmentsArr = [];
            let areaSize = ['750px','455px'];
            if(op == 'view'){
            	areaSize = ['750px','420px'];
            }else if(op == 'add'){
                // 新增上报时判断本年度是否已存在上报
                let rows = $("#gridBox").datagrid("getRows");
                if(!$.isEmptyObject(rows)){
                    let firstRow = rows[0];
                    var currentYear = (new Date()).getFullYear();
                    if(firstRow.modifyYear == currentYear){
                        $pop.alert('当年年度已有上报信息，不能新增！！！');
                        return;
                    }
                }
            }else if(op == 'edit'){
                let rows = $("#gridBox").datagrid("getRows");
                if(!$.isEmptyObject(rows)) {
                    let selRow = rows[index];
                    var currentYear = (new Date()).getFullYear();
                    if(selRow.modifyYear < currentYear) {
                        $pop.alert('只能修改当前年度上报信息！！！');
                        return;
                    }
                }
            }
            
            var popTem = $pop.popTemForm({
                title : title, 
                temId : 'editTem', 
                zIndex : 2,
                area : areaSize,
                onPop:function(){
                	$("#isApply").combobox({
		                onChange: function (newValue, oldValue) {
		                    if(newValue == 0) {
		                        $("#applyGrade").combobox("disableValidation");
		                    }else{
		                         $("#applyGrade").combobox("enableValidation");
		                    }
		                }
		            });
                    window.uploadObj = new uploadImages({outWrap:'#uploader',
                        addBtn:'#filePicker',
                        addMoreBtn:'#filePicker2',
                        formData:{'name':'test'},
                        maxFileNum:10,
                        serversUrl:`${base}/ui/service/biz/amcs/adverse/aeFile/upload`
                    },null,{onDelCallBack:function (dom, obj) {
                        if(!!obj){
                            attachmentsArr = $.grep(attachmentsArr, function(item) {
                                return item.id != obj.id;
                            });
                        }
                    },onUploadSuccess:function(data){
                        let attachment = {};
                        attachment.fileId = data.id;
                        attachment.tag = 'emr';
                	    attachment.id = null;
                		attachment.filesize = data.filesize;
                		attachment.url = data.url;
                		attachment.filename = data.fileName;
                        attachmentsArr.push(attachment);
                    }});
                    
                    if(op == 'view' || op == 'edit') {
                    	uploadImages.initView();
                    	$.ajax({
                            url : "${base}/ui/amcs/coll/hosp/emr/findById",
                            data : { id : id },
                            type: "post"
                        }).done(function (res) {
                        	res.grade+="";
                            $(".formColl").form("load",res);
                            attachmentsArr = res.attachments;
                            window.uploadObj.loadExitsImgs(attachmentsArr);
                        });
                        
                        if(op == 'view') {
                        	$(".statusBar").hide();
                            $(".mainfooter").hide();
                            $('.file-panel').remove();
                            $('form :input, textarea, imgWrap').prop('disabled', true);
                        }
                    };
 
                },
                end:function(){
                	$grid.reload("#gridBox")
                },
            })
            $('.btn-save').click(function () {
            	let gradeLevel = $('#grade').combobox('getValue');
                if(attachmentsArr.length < 1 && gradeLevel > 0){
                    $pop.alert('请上传图片！');
                	return;
                }
                let validate = $(".formColl").form('validate');
                if(validate){
	                let formData = $('.formColl').sovals();
	            	formData.attachments = attachmentsArr;
	            	$ajax.post('${base}/ui/amcs/coll/hosp/emr/save', formData,{jsonData:true,tip:false}).done(function(rst){
	        			if (rst.code === '200' || rst.code === '201') {
	        				$pop.success('保存成功!');
	        				$pop.closePop({ popIndex:popTem });
	        			};
	        		});
                }
            	
            });
             $('#grade').combobox({
				onChange: function(newValue,oldValue){
					$('#gradeName').val($('#grade').combobox('getText'));
				}
			});
			
			$('#applyGrade').combobox({
				onChange: function(newValue,oldValue){
					$('#applyGradeName').val($('#applyGrade').combobox('getText'));
				}
			});
           
        };

        function makeExpandingArea(el) {
            var timer = null;
            //由于ie8有溢出堆栈问题，故调整了这里
            var setStyle = function (el, auto) {
                if (auto) el.style.height = 'auto';
                el.style.height = el.scrollHeight + 'px';
            }
            var delayedResize = function (el) {
                if (timer) {
                    clearTimeout(timer);
                    timer = null;
                }
                timer = setTimeout(function () {
                    setStyle(el)
                }, 200);
            }
            if (el.addEventListener) {
                el.addEventListener('input', function () {
                    setStyle(el, 1);
                }, false);
                setStyle(el)
            } else if (el.attachEvent) {
                el.attachEvent('onpropertychange', function () {
                    setStyle(el)
                })
                setStyle(el)
            }
            if (window.VBArray && window.addEventListener) { //IE9
                el.attachEvent("onkeydown", function () {
                    var key = window.event.keyCode;
                    if (key == 8 || key == 46) delayedResize(el);

                });
                el.attachEvent("oncut", function () {
                    delayedResize(el);
                }); //处理粘贴
            }
        };
        
    });
</script>
</html>
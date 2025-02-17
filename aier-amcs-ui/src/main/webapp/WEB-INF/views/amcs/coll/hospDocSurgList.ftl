<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医生手术权限填报 - 爱尔医院</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<style type="text/css">
.mainfooter{padding:12px 10px 0;text-align: right;}
.surg-panel{display: flex;}
.panel-left-item{ flex: 1;}
.panel-right-item{ flex: 1; border-left: 5px solid #dddddd;}

</style>
<body>
	<div class="searchHead">
        <form id="sbox" class="soform form-enter">
        </form>
    </div>
    <div class="cont-grid">
		<div id="gridBox"></div>
	</div>
    <script id="editTem" type="text/html">
        <form id="infoForm" class="soform form-validate form-enter" data-opt="{beforeCallback:'submitFormEdit'}">
            	 <input class="txt" type="hidden" name="hospId" value="${hospId}" />
            	 <input class="txt" type="hidden" name="hospName" value="${hospName}" />
            	 <input class="txt" type="hidden" name="provinceId" value="${provinceId}" />
            	 <input class="txt" type="hidden" name="provinceName" value="${provinceName}" />
        </form>
        
        <div style="height: 100%;display: flex;flex-direction: column;">
	        <div class="searchHead" id="deptDiv">
	          <form class="soform form-enter">
	            <label class="lab-inline" style="display: inline-block; margin-right: 10px;">科室</label>
	            [@ui_inst uiShowDefault=false param='{queryType:2}' class="drop easyui-combobox" style="display: inline-block; width:200px; "  filterkey="FIRST_SPELL" dataOptions="clearIcon:true, multiple:true, panelMaxHeight:300,onChange: function(rec, orec){refreshStaff(rec); }"  /]
	          </form>
	        </div>
            <div style="display: flex;flex: 1;">
                <div style="flex: 1;border-right: 1px solid #cccccc;">
                    <div id="staffGrid"></div>
                </div>
                <div style="flex: 1;" id="surgDiv">
                    <div id="surgGrid"></div>
                </div>
            </div>
            <div style="display: flex;justify-content: center;padding:10px 0;border-top: 1px solid #ccc;">
                <button type="button" class="btn btn-primary btn-save mar-r25" >保 存</button>
                <button type="button" class="btn btn-clear btn-cancel-pop" >关闭</button>
            </div>
        </div>
    </script>
</body>


[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
	requirejs(['lodash', "easygridEdit", 'template', 'pub'], function (_, $e, template) {
        var formPop = null;

        $ajax.postSync(base + '/ui/amcs/coll/hosp/docsurg/getHospStaff?_easyui_=GRID', {staffType: "1", isDistinct: true}, false, true).done(function (rst) {
            staffData = rst;
        });

        $grid.newGrid("#gridBox",{
    	 	pagination: true,
            fitColumns: false,
            tools: [[
                {
                    iconCls: 'plus', text: '新增',click:function (){ popTem(null);}
			    }
			]],
	    	columns:[[
	    		{title: "操作", field: "op", width: 75, formatter: function (v, row, index) {
	    	        return '<span class="s-op s-op-modify icon-edit" title="修改" rel="' + index + '"></span>';
					}
				},
		        {title:'doctorId',field:'doctorId',width:80, hidden:true},
                {title:'doctorCode',field:'doctorCode',width:80, hidden:true},
                {title:'手术分类编号', field:'surgTypeCode', width:200, hidden:true},
		        {title:'手术医生', field:'doctorName', width:200},
                {title:'手术分类', field:'surgTypeName', width:200},
                {title:'修改时间', field:'modifyDate', width:200, format:'yyyy-MM-dd'},
                {title:'修改人', field:'modiferName', width:200}
            ]],
            url: '${base}/ui/amcs/coll/hosp/docsurg/findListByHosp',
            rowStyler : function(index, row) {

            },
            onBeforeLoad: function(param){
            },
            onLoadSuccess : function (data) {
                //获取所有的手术列表
                $('.s-op-modify').click(function () {
                let ix = $(this).attr('rel');
                let row = data.rows[ix];
                popTem(row);
                });
                
            }
        });

        function popTem(row) {
          let surgTypeArr = [];
          
          formPop = $pop.popTemForm({
              title: "医生手术权限填报",
              temId: 'editTem',
              zIndex: 2,
              area: ['950px', '600px'],
              end: function () {
                  $grid.reload("#gridBox")
              },
              onPop: function ($p) {
                $grid.newGrid("#staffGrid",{
                    fitColumns: false,
                    pagination: false,
                    singleSelect : false,
                    height: 460,
                    columns:[[
                      {title:'id',field:'id',width:80, checkbox:true},
                      {title:'是否赋权',field:'hasPriv',width:80, hidden:true},
                      {title:'工号',field:'code', width: 100},
                      {title:'医生姓名',field:'name', width: 120},
                      {title:'职称',field:'certLevelName', width: 180},
                    ]],
                    rowStyler : function(index, row) {
		                if(row.hasPriv == true){
		                    return "background-color: #ABEBC6";
		                }		
		            }
                });

                if(!$.isEmptyObject(row)){
                    surgTypeArr = row.surgTypeCode.split(", ");
                    let filterStaff = $.grep(staffData.rows, function(elem ) {
                      return parseFloat(elem.id) === parseFloat(row.doctorId);
                    });
                    $('#staffGrid').datagrid('loadData', {total: 1, rows: filterStaff});
                    $('#staffGrid').datagrid('checkRow', 0);
                    $('#deptDiv').hide();
                  }else{
                    $('#staffGrid').datagrid('loadData',staffData);
                    $('#deptDiv').show();
                  }
                                    
                  $('#surgGrid').treegrid({
                      height: 460,
                      idField:'code',
                      treeField:'name',
                      fitColumns : true,
                      flatData : true,
                      checkbox: true,
                      method:'post',
                      url: '${base}/ui/amcs/coll/hosp/docsurg/findOprTypeList',
                      columns:[[
                          {title:'手术类型',field:'name', width:'100%'},
                      ]],
                      onLoadSuccess: function(row, data) {
                        if(surgTypeArr.length > 0) {
                          $.each(surgTypeArr, function(index, value) {
                            var node = $('#surgGrid').treegrid('find', value);
                            if (node) { 
                              $('#surgGrid').treegrid('checkNode', node.code, true);
                            };
                          });
                        };
                    
                      }
                  });
                  
                  $(".btn-cancel-pop").click(function (){
                      $pop.close(formPop);
                  });

                  $(".btn-save").click(function(){
                      let saveData = $("#infoForm").sovals();
                      saveData.staffs = $e.getCheckedRows({grid: '#staffGrid'});
                      saveData.surgs = $('#surgGrid').treegrid('getCheckedNodes');
                      if(saveData.staffs.length == 0){
                      	$pop.alert('请选择医生！');
                      	return;
                      }
                      if(saveData.surgs.length == 0){
                      	$pop.alert('请选择手术类型！');
                      	return;
                      }
                      $ajax.post({
                          url: '${base}/ui/amcs/coll/hosp/docsurg/save',
                          jsonData: true,
                          data: saveData,
                          tip: '确认保存？',
                          success: function (rst) {
                          	$pop.success('保存成功！');
                            $ajax.postSync(base + '/ui/amcs/coll/hosp/docsurg/getHospStaff?_easyui_=GRID', {staffType: "1", isDistinct: true}, false, true).done(function (rst) {
                                $('#staffGrid').datagrid('loadData', rst);
                            });
                            $("#surgGrid").treegrid('clearChecked');
                            console.log(rst);
                          }
                      });
                  })
              }
          });
        };

        window.refreshStaff = function (row) {
            if(!$.isEmptyObject(row)){
                $ajax.postSync(base + '/ui/amcs/coll/hosp/docsurg/getHospStaff?_easyui_=GRID', {deptIds: row, staffType: "1", isDistinct: true}, false, true).done(function (rst) {
                   $('#staffGrid').datagrid('loadData',rst);
                });
            }else{
                $('#staffGrid').datagrid('loadData',staffData);
            }
        };
        
	});


</script>
</html>

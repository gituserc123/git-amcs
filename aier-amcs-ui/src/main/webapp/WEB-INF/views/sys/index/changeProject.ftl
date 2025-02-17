<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title></title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>

<div class="wrapper-popForm">
<form class="soform form-validate form-enter pad-t15 solab-lb" method="post" action="${base}/ui/sys/index/switchProject" data-opt="{beforeCallback:'bcallBack',callback:'callBack'}">
      <div class="row">
        <div class="p10"><div class="item-one">
          <label class="lab-item">请选择项目：</label>
	          [@ui_project id="projectId" name="projectId" param='{}' uiShowDefault=false  dataOptions="required:true,limitToList:true" style="width:150px"/]
        </div></div>
     </div>

    <p class="row-btn center">
      <input type="button" class="btn btn-primary btn-easyFormSubmit"  name="btnSubmit" value="切换" noclosepop="true"/>
    </p>

</form>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
requirejs(['pub'],function () {
	window.bcallBack =  function(rst){
		$ajax.post({
			url: '${base}/ui/sys/index/switchProject',
			data: {'projectId':$('#projectId').val()},
			tip: "切换项目将刷新页面，请做好保存！"
		}).done(function (result) {
			window.parent.location.reload();
		});
    }
	
});
</script>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>编辑用户报表权限</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
</head>

<body>
<div class="wrapper">
<div class="cont-grid">
	<div id="dl"></div>  
</div>
<p class="row-btn center bot-line-sub">
  <input type="button" class="btn btn-primary btn-submitRole" name="btnSubmit" value="保 存" />
  <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
</p>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['pub'],function () {
//         $('#ul-powerTree').tree({
//             animate : true,
//             url : '${base}/ui/sys/report/getStaffReport?id=${id}', 
//             onlyLeafCheck : true,
//             flatData:true,
//             checkbox : true,
//             lines : true
//         });
        $('#dl').datalist({ 
		    url: '${base}/ui/sys/report/getStaffReport?id=${id}&type=1', 
		    checkbox: true, 
		    lines: true ,
		    valueField: 'ID',
		    textField: 'remark',
		    singleSelect:false,
		    onLoadSuccess : function(data){
		    	for(var i = 0; i < data.rows.length; i ++){
		    		if(data.rows[i].state){
		    			$('#dl').datalist('checkRow',i);
		    		}
		    	}
		    },
			showFooter : true,
		    offset :-60,
		    height: '410px'
		});  

        $('.btn-submitRole').click(function () {
        	var checked = $('#dl').datalist('getSelections');
            var chkIds = [];
            $.each(checked,function (i,v) {
              chkIds.push(v.ID);
            });
            
            var sendData = {
		     	id : '${id}',
		     	ids : chkIds
		    };
		    
            $ajax.post('${base}/ui/sys/report/updatePerm',JSON.stringify(sendData),true, true).done(function (rst) {
              if (rst.code==='200'||rst.code==='201') {
                setTimeout(function () {
		           $pop.closePop({refreshGrid:true});
		          }, 400);
              };
            });

        });
    });
</script>

</html>

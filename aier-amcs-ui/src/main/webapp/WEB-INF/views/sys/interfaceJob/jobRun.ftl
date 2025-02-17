<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>作业上报</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
</head>

<body>
<div class="pad-20">
  <form class="soform form-validate form-enter mar-b10" method="post" action="${base}/ui/sys/interfaceJob/updateValue">
          <input class="txt txt-validate" type="hidden" name="hospId" value="${hospId}" />
          <input class="txt txt-validate" type="hidden" name="jobClass" value="${jobClass}" />
    <div class="row">
      <div class="p12"><div class="item-one">
				<label class="lab-item">执行时间：</label>
            <input class="txt txt-validate txt-rangeDate " type="text" name="date" value="" required=true/>
            </div></div>
    </div>
    <div class="row">
      <div class="p12"><div class="item-one">
          <label class="lab-item">作业名称：</label>
          <input class="txt " type="text" name="jobName" value="${jobName}"  disabled />
      </div></div>
    </div>
    
    <p class="row-btn">
      <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="上 报" />
      <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
    </p>
  </form>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
require(["pub"],function(){
    function searchGrad (){
        sData = $('#sbox').sovals();
       $('#gridBox').datagrid('load',sData);
    };

    $form.rangeDate('.txt-rangeDate',{
        val:'',
        opens:'center',
        auto : false,
        init : function (s,e,label) {
            searchGrad();
        }
    });

});

</script>
</body>
</html>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>用户密码修改</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
</head>
<body>

<div class="wrapper-popForm">
<form class="soform form-validate form-enter pad-t15 solab-lb" method="post" action="${base}/updatePwd">
      <div class="row">
        <div class="p10"><div class="item-one">
          <label class="lab-item">原密码：</label>
          <input  id="plainPassword" type="password" name="plainPassword" maxlength="20" class="txt txt-validate" placeholder="请输入原密码" noNull="请输入原密码" />
        </div></div>
     </div>

      <div class="row">
        <div class="p10"><div class="item-one">
          <label class="lab-item">新密码：</label>
          <input  id="newPassword" type="password" name="newPassword" maxlength="20" class="txt txt-validate" placeholder="请输入新密码" noNull="请输入新密码" validType="many['normalPass','length[6,16]']" />
        </div></div>
     </div>

      <div class="row">
        <div class="p10"><div class="item-one">
          <label class="lab-item">确认密码：</label>
          <input class="txt txt-validate" type="password" name="rePassword" maxlength="20" placeholder="再输入一次密码" noNull="再输入一次密码" validType="equals['#newPassword','新密码不一致']" />
        </div></div>
     </div>

    <p class="row-btn center">
      <input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确认修改" />
    </p>

</form>
</div>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
requirejs(['pub'],function () {});
</script>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>医院反馈查看 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
.table-form{border:0;width:100%;border-collapse: collapse;}
.table-form th{background-color:#f9f9f9;border: 1px solid #e9e9e9;vertical-align: top;}
.table-form td{border: 1px solid #e9e9e9;}
.table-form th{text-align:right;width:6%;min-width:80px;padding:5px 8px 5px 5px;font-weight: bold;}
.table-form td{padding:3px;width:17%;}
.table-form td .blue { padding: 5px 0 0 0; display: inline-block; }
.feedDetails{margin:10px;font-size:14px;line-height:1.5em;padding:20px;margin-top:10px;background-color:#eff5fb;}
.feedDetails:hover{background-color:#e4f1fd;}
.h2-feedTitle{font-size:18px;font-weight:normal;border-bottom:1px dotted #76b4f1;padding:10px 0 20px;}
.feedDetailsCont{padding:20px 0;}
.feedDetails img{max-width:830px;height:auto;}

.h2-recommendTitle{}
.recommendDetails{background-color:#fdf9f3;margin:10px;}
.recommendDetails img{max-width:830px;height:auto;}
.recomOne{font-size:12px;line-height:1.5em;padding:20px 20px;cursor:hand;cursor:pointer;border-top:1px dotted #e8c59f;}
.recomOne:first-child{border-top-width:0;}
.recomOne:hover{background-color:#fcf3ec;}
.h4-recomOne{border-left:4px solid #e68455;color:#000;display:inline-block;padding:0 20px 0 5px;}
.h4-recomOne .s-date{color:#987ae0;margin-left:20px;font-weight:normal;}
.h4-recomOne .s-user{color:#e68455;font-size:14px;}
.recomOneCont{margin:10px 0 0;line-height:1.8em;}
</style>
</head>

<body>

  <div>
	  <form class="soform form-validate form-enter pad-10" method="post" action="${base}/ui/sys/hospProblem/reply">
      <input type="hidden" id="id" name="id" value="${obj.id}">
      <table class="table-form" cellspacing="0" cellpadding="0">
        <tr>
          <th>医院</th><td><span class="s-txtVal">${obj.hospId} - ${obj.hospName}</td>
          <th>提报人</th><td><span class="s-txtVal">${obj.declarerName}</span></td>
          <th>电话</th><td><span class="s-txtVal">${obj.phone}</span></td>
          <th>提报时间</th><td><span class="s-txtVal">${obj.createDate}</span></td>
        </tr>
        <tr>
          <th>网络运营商</th><td><span class="s-txtVal">${obj.netOperatorName}</span></td>
          <th>系统环境</th><td><span class="s-txtVal">${obj.osName} ${obj.browserName}</span></td>
          <th>解决时间</th><td><input type="text" class="txt inline so-date" data-opt="{type:'Date', format:'yyyy-MM-dd'}" name="resolveDate" id="resolveDate" value="[#if obj.resolveDate]${(obj.resolveDate?date('yyyy-MM-dd'))!}[/#if]" [#if obj.state!=2&&obj.state!=3]disabled="disabled"[/#if]></td>
            <th>模块</th><td><span class="s-txtVal">
                    [@ui_select_plat id="platformCode" name="platformCode"  class="drop drop-ra changeValidCombo easyui-combobox w-70"  uiShowDefault=false dataOptions="
			                onSelect:function(r){
			                     $ajax.post('${base}/ui/sys/index/getModuleByPlatform?platformCode='+r.valueCode).done(function (rst) {
                                        $('#moduleCode').combobox('loadData', rst.data);
                                    });
					}"/]
          <input type="hidden" id="moduleName" name="moduleName" value="">
          <input id="moduleCode" name="moduleCode" class="drop drop-ra changeValidCombo easyui-combobox w-160" uiShowDefault=false data-options="valueField:'moduleCode',textField:'moduleName',
                        	onSelect:function(r){
				                $('#moduleName').val(r.moduleName)
							},
							filter:function(q, row){return String(row['moduleName']).toUpperCase().indexOf(String(q).toUpperCase()) >= 0 || String(row['firstSpell']).toUpperCase().indexOf(String(q).toUpperCase()) >= 0}" />
                </span></td>
        </tr>
        <tr>
          [#if obj.state lt 3]
          <th>问题级别</th><td>[@ui_select id="problemLevel" name="problemLevel" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$LevelEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefault=false value="${obj.problemLevel!2}" dataOptions="required:true,limitToList:true"/]</td>
          [#else]
          <th>问题级别</th><td>[@ui_select id="problemLevel" name="problemLevel" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$LevelEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefault=false value="${obj.problemLevel}" disabled="disabled"/]</td>
          [/#if]
            [#if obj.state lt 3]
                <th>问题类型</th><td>[@ui_select id="typeCode" name="typeCode" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$TypeEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefault=false  value="${obj.typeCode}" dataOptions="required:true,limitToList:true"/]</td>
                <th>状态</th><td>[@ui_select id="state" name="state" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$StateEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefault=false value="${obj.state}"  dataOptions="required:true,limitToList:true"/]</td>
                <th>当前负责人</th><td>[@ui_staff id="personLiable" name="personLiable" param='{deptIds:[100078,100075,100077]}' value="${obj.personLiable}" uiShowDefault=false  dataOptions="required:true,limitToList:true" style="width:90px" disabled="disabled"/]
                    <span style="line-height: 26px">指派</span> [#if obj.personLiable??][@ui_staff id="personLiable" name="personLiable" param='{deptIds:[100078,100075,100077]}' uiShowDefault=false  dataOptions="limitToList:true" style="width:90px" filterkey="firstSpell,name" /]
                    [#else][@ui_staff id="personLiable" name="personLiable" param='{deptIds:[100078,100075,100077]}' uiShowDefault=false  dataOptions="required:true,limitToList:true" style="width:90px"/][/#if]</td>
            [#else]
                <th>问题类型</th><td>[@ui_select id="typeCode" name="typeCode" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$TypeEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefault=false  value="${obj.typeCode}" disabled="disabled"/]</td>
                <th>状态</th><td>[@ui_select id="state" name="state" tag="com.aier.cloud.basic.api.domain.enums.HospProblemEnums$StateEnum" class="drop drop-ra changeValidCombo easyui-combobox w-120" uiShowDefault=false value="${obj.state}"  disabled="disabled"/]</td>
                <th>业务负责人</th><td>[@ui_staff id="personLiable" name="personLiable" param='{deptIds:[100078,100075,100077]}' value="${obj.personLiable}" disabled="disabled"/]</td>
            [/#if]
        </tr>
        <tr>
            [#if obj.state lt 3]
                [#--<th>开发负责人</th><td>[@ui_staff id="resolverId" name="resolverId" param='{deptIds:[100078,100075,100077]}' value="${obj.resolverId}" filterkey="firstSpell,name" dataOptions="required:true,limitToList:true" uiShowDefault=true uiShowDefaultDesc="无" dataOptions="limitToList:true"/]</td>--]
                <th>预计解决时间</th><td><input type="text" class="txt inline so-date" data-opt="{type:'Date',minDate:'${sysdate}', format:'yyyy-MM-dd'}" name="estimatedDate" id="estimatedDate" value="[#if obj.estimatedDate]${(obj.estimatedDate?date('yyyy-MM-dd'))!}[/#if]"></td>
                <th>禅道Id</th><td colspan="3"><input class="txt easyui-validatebox" type="text" name="zentaoId" value="${obj.zentaoId}" maxlength="20" style="width: 150px"/></td>
            [#else]
                [#--<th>开发负责人</th><td>[@ui_staff id="resolverId" name="resolverId" param='{deptIds:[100078,100075,100077]}' value="${obj.resolverId}" disabled="disabled"/]</td>--]
                <th>预计解决时间</th><td><input type="text" class="txt inline so-date" data-opt="{type:'Date',minDate:'${sysdate}', format:'yyyy-MM-dd'}" name="estimatedDate" id="estimatedDate" value="[#if obj.estimatedDate]${(obj.estimatedDate?date('yyyy-MM-dd'))!}[/#if]"  disabled="disabled"></td>
                <th>禅道Id</th><td colspan="3"><input class="txt easyui-validatebox" type="text" name="zentaoId" value="${obj.zentaoId}" disabled="disabled" style="width: 150px"/></td>
            [/#if]
          </tr>
        <tr>
          <th>处理结果</th><td colspan="7">
                [#if obj.state lt 3]
                    <script id="editor" class="so-editor editorkey_comment" name="groupContent" type="text/plain" style="width:100%;height:180px;"></script>
                    [#--<textarea class="txta txt-validate" name="groupContent" maxlength="2000">${obj.groupContent}</textarea>--]
                [#else]
                    ${obj.groupContent}
                [/#if]
          </td>
        </tr>
      </table>
      <p class="row-btn center">
        [@shiro.hasPermission name = "GroupProblem:reply"]
        [#if obj.state lt 3]
        <input type="button" class="btn btn-primary btn-update" value="确认" />
        [/#if]
        [#--[#if obj.state lt 3]--]
        [#--<input type="button" class="btn btn-primary btn-solve" value="解决" />--]
        [#--[/#if]--]
        [#if obj.state gte 3]
        <input type="button" class="btn btn-primary btn-active" value="激活" />
        [/#if]
        [/@shiro.hasPermission]
        <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消" />
      </p>
    </form>

    <div class="feedDetails">
        <h2 class="h2-feedTitle"><span style="font-weight: bold;">标题：</span>${obj.title}</h2>
        <div class="feedDetailsCont">
            ${obj.hospContent}
        </div>
    </div>

    [#if obj.replyList?? && obj.replyList?size>0]

    <div class="recommendDetails">
[#--        <h2 class="h2-recommendTitle">处理记录</h2>--]
        [#list obj.replyList as reply]
        <div class="recomOne">
            <h4 class="h4-recomOne"><span class="s-user">${reply.creatorName} </span>处理意见<span class="s-date">${reply.createDate} </span></h4>
            <div class="recomOneCont">
                ${reply.groupContent}
            </div>
        </div>
        [/#list]
    </div>
    [/#if]
  </div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
requirejs(["editor",'pub'],function (editor) {
    editor();

    $("#platformCode").combobox('setValue', '${obj.platformCode}');
    $("#moduleCode").combobox('setValue', '${obj.moduleCode}');

    $('.btn-active').click(function () {
        var id = $("#id").val();
        $ajax.post('${base}/ui/sys/hospProblem/reply',{id:id, state:1}, false, true).done(function (rst) {
            if (rst.code==='200'||rst.code==='201') {
                window.location.reload();
            };
        });
    });

    $('#state').combobox({
        onChange: function(v){
            if (v == '3' || v == '2'){
                $("#resolveDate").removeAttr("disabled");
            }
            else{
                $("#resolveDate").attr("disabled", "disabled");
            }
        }
    });

    $('.btn-update').click(function () {
        if (!$(".soform").form('validate')) {
            return;
        }
        $ajax.post('${base}/ui/sys/hospProblem/reply',$('.soform').sovals(), false, true).done(function (rst) {
            if (rst.code==='200'||rst.code==='201') {
                window.location.reload();
            }
        });
    });

    $('.btn-solve').click(function () {
        $("#state").combobox('setValue', '2');
        $ajax.post('${base}/ui/sys/hospProblem/reply',$('.soform').sovals(), false, true).done(function (rst) {
            if (rst.code==='200'||rst.code==='201') {
                window.location.reload();
            };
        });
    });
});

</script>
</body>

</html>

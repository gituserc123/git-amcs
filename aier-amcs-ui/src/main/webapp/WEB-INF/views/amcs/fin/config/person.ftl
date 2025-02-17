<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>人员管理 - 爱尔AMCS</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
    <style type="text/css">
        .mainfooter{padding:12px 10px 0;text-align: right;}
    </style>
</head>

<body>
<form id="person-form" class="soform formA form-validate pad-t10" xmlns="http://www.w3.org/1999/html">
    <h2 class="h2-title-a">
        <span class="s-title">财务负责人</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <input class="hide" type="text" name="id" id="id">
    <input class="hide" type="text" name="hospId" id="hospId" value="${hospId}"/>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">姓名</label>
                <input class="txt txt-validate" type="text" name="finPerInCharge" id="finPerInCharge" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">职务</label>
                <input class="txt txt-validate" type="text" name="posi" id="posi" validType="maxlength[10]"  value=""/>
            </div>
        </div>


        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="contInfo" id="contInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>

    </div>

    <h2 class="h2-title-a">
        <span class="s-title">医保管理人员信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保负责人姓名</label>
                <input class="txt txt-validate" type="text" name="medInsPerInCharge" id="medInsPerInCharge" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="insContInfo" id="insContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保专员1</label>
                <input class="txt txt-validate" type="text" name="medInsSpe1" id="medInsSpe1" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="spe1ContInfo" id="spe1ContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保专员2</label>
                <input class="txt txt-validate" type="text" name="medInsSpe2" id="medInsSpe2" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="spe2ContInfo" id="spe2ContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">医保专员3</label>
                <input class="txt txt-validate" type="text" name="medInsSpe3" id="medInsSpe3" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="spe3ContInfo" id="spe3ContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>

    <h2 class="h2-title-a">
        <span class="s-title">价格管理人员信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">价格负责人姓名</label>
                <input class="txt txt-validate" type="text" name="priPerInCharge" id="priPerInCharge" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="priContInfo" id="priContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">价格专干1</label>
                <input class="txt txt-validate" type="text" name="priSpe1" id="priSpe1" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="spe1PriContInfo" id="spe1PriContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">价格专干2</label>
                <input class="txt txt-validate" type="text" name="priSpe2" id="priSpe2" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="spe2PriContInfo" id="spe2PriContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">价格专干3</label>
                <input class="txt txt-validate" type="text" name="priSpe3" id="priSpe3" validType="maxlength[10]"  value=""/>
            </div>
        </div>
        <div class="p3">
            <div class="item-one">
                <label class="lab-item">联系方式</label>
                <input class="txt txt-validate" type="text" name="spe3PriContInfo" id="spe3PriContInfo" validType="maxlength[11]"  value=""/>
            </div>
        </div>
    </div>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
        <div class="p4">
            [#--            <input id="submit" :disabled="isDisabled" @click="mainSave" type="button" class="btn btn-primary" name="btnSubmit" noclosepop="true" value="暂存"/>--]
            <input id="submit"   type="button" class="btn btn-primary" name="btnSubmit" noclosepop="true" value="提交"/>
        </div>
    </div>
</form>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">

        requirejs(['pub'],function () {
            const data=${person}
            if(data){
                const form = document.querySelector('form'); // 假设你只有一个form，否则请更精确地选择你的form

                for (let key in data) {
                    const input = form.querySelector(`input[name="`+key+`"]`);
                    if (input) {
                        input.value=data[key].toString();
                    }
                }
            }

            $("#submit").click(function () {
                const formData=$("#person-form").sovals()
                $ajax.post("${base}/ui/amcs/fin/config/person/save",formData,"是否提交",true)
            })
        });
</script>
</body>
</html>

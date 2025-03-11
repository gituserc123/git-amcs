<div id="op-div" style="position: fixed;
        z-index: 1;
        top: 40%;
        right: 0;
        display: ${opDivDisplay!};
        flex-direction: column;">
    <button type="button" class="btn btn-large btn-warning fix-stash-btn" style="display: ${stashBtnDisplay!};">暂存
    </button>
    <!-- 初始化提交按钮 -->
    <button type="button" class="btn btn-large btn-primary fix-init-btn" style="display: ${initBtnDisplay!};">提交
    </button>
    <!-- 流程中提交按钮 -->
    <button type="button" class="btn btn-large btn-primary fix-save-btn" style="display: ${saveBtnDisplay!};">提交
    </button>
    <button type="button" class="btn btn-large btn-inverse fix-back-btn" style="display: ${backBtnDisplay!};">退回
    </button>
</div>
<div style="height: 34px;"></div>
<ul class="tabs likeTabs" style="position: fixed;width: 100%;top:0;z-index: 999;">
    <li class="tabs-first tabs-selected" rel="0">
        <a href="#" class="tabs-inner"><span class="tabs-title">基本信息</span></a>
    </li>
    <li rel="1">
        <a href="#" class="tabs-inner"><span class="tabs-title">审核意见</span></a>
    </li>
    <!-- <li rel="2">
        <a href="#" class="tabs-inner"><span class="tabs-title">标签3</span></a>
    </li>

    <li rel="3">
        <a href="#" class="tabs-inner"><span class="tabs-title">标签4</span></a>
    </li>
    <li rel="4" id="reviewTab">
        <a href="#" class="tabs-inner"><span class="tabs-title">标签5</span></a>
    </li> -->
</ul>
<script id="opinionFormTem" type="text/html">
    <form id="infoForm" class="soform form-validate solab-l form-enter"
          data-opt="{beforeCallback:'submitFlowDataForm'}">
        <!--
        [#if nodeSelectEle=='true']
            <div class="row">
                <div class="item-one">
                    <label class="lab-inline">选择提交节点：</label>
                    <select class="drop w-100" id="commitNode" name="commitNode">
                        <option value=""></option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
            </div>
        [/#if]
        -->
        <div class="row">
            <div class="item-one" style="padding-left: 47px;margin-top: 10px;">
                <label class="lab-item" style="text-align: left;">提交意见:</label>
                <div class="p12">
                    <textarea class="txta txt-validate" id="opinion" name="opinion" maxlength="1000"></textarea>
                </div>
            </div>
        </div>
        <p class="row-btn center">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>
            <input type="button" class="btn btn-cancel-pop" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>

<script id="rejectFormTem" type="text/html">
    <form id="rejectForm" class="soform form-validate solab-l form-enter clsRejectForm"
          data-opt="{beforeCallback:'rejectFlowDataForm'}">
            <div class="row">
                <div class="item-one" style="padding-left: 47px;margin-top: 10px;">
                    <label class="lab-item" style="text-align: left;">选择退回节点：</label>
                    <!-- <select class="drop w-100" id="commitNode" name="commitNode"> -->
                    <div class="p12" style="margin-left: 10%;">
                    <select class="drop easyui-combobox" style="width:80%;" name="commitNode" id="commitNode" data-options="valueField:'nodeId',textField:'nodeName',editable:false, required:true" >
                    </select>
                    </div>
                </div>
            </div>
        <div class="row">
            <div class="item-one" style="padding-left: 47px;margin-top: 10px;">
                <label class="lab-item" style="text-align: left;">退回意见:</label>
                <div class="p12">
                    <textarea class="txta txt-validate" id="opinion" name="opinion" maxlength="1000"></textarea>
                </div>
            </div>
        </div>
        <p class="row-btn center">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>
            <input type="button" class="btn btn-cancel-pop" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
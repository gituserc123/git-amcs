<div id="op-div" style="position: fixed;
	    z-index: 1;
	    top: 40%;
	    right: 0;
	    display: flex;
	    flex-direction: column;">
	<#if "${eventCode}" == '109' >
		<button type="button" class="btn btn-large btn-info fix-print-btn hidden" >打印</button>
	</#if>
    <#if '${showOperate!}' == true>
		<#if "${pageType!}" == 1||"${pageType!}" == 0>
			 <#if "${operate}" == true>
			 	<#if "${isIncrease!}" == false>
                    <#if  "${isEhrDeptReview!}" == 0 || "${isEhrDeptReview!}" == null>
					    <button type="button" class="btn btn-large btn-warning fix-stash-btn">暂存</button>
                    </#if>
				</#if>

				<button type="button" class="btn btn-large btn-primary fix-save-btn">提交</button>
			<#else>
				<button type="button" class="btn btn-large btn-cancel fix-btn">暂存</button>
				<button type="button" class="btn btn-large btn-cancel fix-btn">提交</button>
			</#if>
		<#else>
	    	<#if "${isReview!}" == true>
	    	 	<#if "${operate}" == true>
	    			<button type="button" class="btn btn-large btn-warning fix-review-btn">点评</button>
	    		<#else>
	    			<button type="button" class="btn btn-large btn-cancel fix-btn">点评</button>
	    		</#if>
		    <#else>
		        <#if "${operate}" == true && "${ae.isArchived}"!=1>
			    	<#if "${pageType!}" != 4>
			    		<button type="button" class="btn btn-large btn-primary fix-save-btn">通过</button>
			    		<button type="button" class="btn btn-large btn-danger fix-cancel-btn">取消</button>
			    	<#else>
			    		<button type="button" class="btn btn-large btn-warning fix-review-btn">点评</button>
			    	</#if>
                        <button type="button" class="btn btn-large btn-inverse fix-back-btn">回退</button>
			    		<button type="button" class="btn btn-large btn-info fix-merge-btn">合并</button>
                        <!--
                        <button type="button" class="btn btn-large btn-success fix-archived-btn">归档</button>
                        -->
			    <#else>
			        <#if "${pageType!}" == 3>
			        	<button type="button" class="btn btn-large btn-info fix-merge-btn">合并</button>
			        <#else>
			        	<button type="button" class="btn btn-large btn-cancel fix-btn">合并</button>
			        </#if>
			        <button type="button" class="btn btn-large btn-cancel fix-btn">通过</button>
			    	<button type="button" class="btn btn-large btn-cancel fix-btn">回退</button>
			    	<button type="button" class="btn btn-large btn-cancel fix-btn">取消</button>
			    </#if>
		    </#if>
		</#if>
	</#if>

</div>
<div style="height: 34px;"></div>
<ul class="tabs likeTabs" style="position: fixed;width: 100%;top:0;z-index: 999;">
    <li class="tabs-first tabs-selected" rel="0">
        <a href="#" class="tabs-inner"><span class="tabs-title">事件明细</span></a>
    </li>
    <li rel="1">
        <a href="#" class="tabs-inner"><span class="tabs-title">审核意见</span></a>
    </li>
    <li rel="2">
        <a href="#" class="tabs-inner"><span class="tabs-title">多次上报</span></a>
    </li>
  
    <li rel="3">
        <a href="#" class="tabs-inner"><span class="tabs-title">相关病历</span></a>
    </li>
    <li rel="4" id="reviewTab">
        <a href="#" class="tabs-inner"><span class="tabs-title">点评</span></a>
    </li>
</ul>
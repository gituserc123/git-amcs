package com.aier.cloud.biz.ui.template.directive;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.basic.web.template.directive.DirectiveHandler;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;

import freemarker.template.TemplateException;

/**
 * 机构下拉选择组件
 *
 * @author xiaokek
 * @since 2018年3月26日 下午3:49:19
 */
@Component("ui_inst")
public class SelectInstDirective extends SelectDirective {

	@Autowired
	private InstitutionService s;

	@Override
	protected List<Map> remoteData(DirectiveHandler h) throws TemplateException {
		String param = h.getString("param", "{}");
		InstCondition sc = JsonUtil.fromJson(param, InstCondition.class);
		if(sc.isLoginInst()) {
			sc.setInstId(ShiroUtils.getInstId());
		}
		sc.setInstType(InstEnum.MEDICAL.getEnumCode());
		if(!sc.isContainsLoginDept()) {
			sc.setLoginDeptId(ShiroUtils.getDeptId());
		}
		return s.getInstByConditionForSelect(sc);
	}

	@Override
	protected String valueDesc() {
		return "NAME";
	}

	@Override
	protected String valueField() {
		return "ID";
	}
	@Override
	protected String filter() {
		return "FIRST_SPELL";
	}
}

package com.aier.cloud.ui.template.directive;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aier.cloud.basic.api.request.domain.sys.Platform;
import com.aier.cloud.basic.web.template.directive.BaseSelectDirective;
import com.aier.cloud.basic.web.template.directive.DirectiveHandler;
import com.aier.cloud.ui.biz.sys.service.PlatformService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import freemarker.template.TemplateException;

/**
 * 标准下拉组件
 * 通过<select></select>元素创建一个预定义结构的下拉列表框。
 * 支持数据量不大的码表
 * @author xiaokek
 * @since 2018年2月6日 下午5:07:18
 */
@Component("ui_select_plat")
public class SelectPlatDirective extends BaseSelectDirective {
	
	
	@Autowired
	private PlatformService s;
	
	
	/**
	 * 子类可通过覆盖数据得到
	 * @param handler
	 * @return
	 * @throws TemplateException
	 */
	protected List<Map> remoteData(DirectiveHandler handler) throws TemplateException {
		List<Platform> ps = s.list();
		List<Map> result = Lists.newArrayList();
		for(Platform p : ps){
			Map m = Maps.newHashMap();
			m.put(this.valueField(), p.getCode());
			m.put(this.valueDesc(), p.getName());
			result.add(m);
		}
		return result;
	}
	
}


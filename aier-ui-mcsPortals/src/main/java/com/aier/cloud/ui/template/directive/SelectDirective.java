package com.aier.cloud.ui.template.directive;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aier.cloud.basic.web.template.directive.BaseSelectDirective;
import com.aier.cloud.basic.web.template.directive.DirectiveHandler;
import com.aier.cloud.ui.biz.common.DictService;

import freemarker.template.TemplateException;

/**
 * 标准下拉组件
 * 通过<select></select>元素创建一个预定义结构的下拉列表框。
 * 支持数据量不大的码表
 * @author xiaokek
 * @since 2018年2月6日 下午5:07:18
 */
@Component("ui_select")
public class SelectDirective extends BaseSelectDirective {
	
	
	@Autowired
	private DictService s;
	
	
	/**
	 * 子类可通过覆盖数据得到
	 * @param handler
	 * @return
	 * @throws TemplateException
	 */
	protected List<Map> remoteData(DirectiveHandler handler) throws TemplateException {
		String tag = handler.getString("tag");
		String filter = handler.getString("elf", "");
		if(StringUtils.isNoneBlank(tag)) {
			return s.getList(tag,filter);
		}
		return Collections.EMPTY_LIST;
	}
	
}


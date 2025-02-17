package com.aier.cloud.biz.ui.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import com.aier.cloud.basic.web.template.directive.BaseDirective;
import com.aier.cloud.basic.web.template.directive.DirectiveHandler;

import freemarker.template.TemplateException;

/**
 * 标准下拉组件
 * 通过<select></select>元素创建一个预定义结构的下拉列表框。
 * 支持数据量不大的码表
 * @author xiaokek
 * @since 2018年2月6日 下午5:07:18
 */
@Component("ui_dict")
public class DictDirective extends BaseDirective {
	
//	
//	@Autowired
//	private AmcsDictService s;

	@Override
	public void execute(DirectiveHandler handler) throws TemplateException, IOException {

		StringBuilder html = new StringBuilder();
		List<Map> result = this.remoteData(handler);
		String type = handler.getString("type", "radio");
		String name = handler.getString("name", type);
		String value = handler.getString("value", type);

		if("radio".equalsIgnoreCase(type)) {
			result.stream().forEach(r -> {
				String checked=MapUtils.getString(r, "valueCode","").equals(value)?"checked":"";
				html.append("<label class='lab-val'><input type='radio'  name='"+name+"' value='"+MapUtils.getString(r, "valueCode","")+"' "+checked+"/> "+MapUtils.getString(r, "valueDesc")+"</label>");
			});
		}else if("checkbox".equalsIgnoreCase(type)) {
			result.stream().forEach(r -> {
				html.append("<label class='lab-val'><input type='checkbox'  name='"+name+"' value='"+MapUtils.getString(r, "valueCode","")+"' /> "+MapUtils.getString(r, "valueDesc")+"</label>");
			});
		}
		
		handler.print(html.toString());
		handler.render();
	}

	
	/**
	 * 子类可通过覆盖数据得到
	 * @param handler
	 * @return
	 * @throws TemplateException
	 */
	protected List<Map> remoteData(DirectiveHandler handler) throws TemplateException {
		String tag = handler.getString("tag");
		String filter = handler.getString("elf", "");
//		List<Map> r = s.getList(tag,filter,"");
		return null;
	}
	
}

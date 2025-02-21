package com.aier.cloud.biz.ui.template.directive;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawDictTypeFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aier.cloud.basic.web.template.directive.BaseSelectDirective;
import com.aier.cloud.basic.web.template.directive.DirectiveHandler;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseDictService;
import com.aier.cloud.biz.ui.biz.common.feign.DictService;
import com.google.common.collect.Lists;

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
	
	@Autowired
	private AdverseDictService aes;

	@Autowired
	private FinDictFeignService finDict;

	@Autowired
	private LawDictTypeFeignService lawDictTypeFeignService;
	
	@Resource EnumDictResovler r;
	
	
	
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
			if(tag.startsWith("@amcs@")) {
				return Lists.newArrayList(r.getDictList(tag.replace("@amcs@", "")));
			}else if(tag.startsWith("@ae@")){ 
				tag = tag.replace("@ae@", "");
				List<Map> list =  aes.getList(tag,filter);
				list.sort(Comparator.comparing((Map h) -> (Integer.valueOf(String.valueOf(h.get("valueCode"))))));
				return list;
			}else if(tag.startsWith("@fin@")){
				tag = tag.replace("@fin@", "");
				List<Map> list =  finDict.getList(tag);
				list.sort(Comparator.comparing((Map h) -> (Integer.valueOf(String.valueOf(h.get("valueCode"))))));
				return list;
			}else if(tag.startsWith("@law@")){
				tag = tag.replace("@law@", "");
				List<Map> list =  lawDictTypeFeignService.selectByTypeCode(tag);
				list.sort(Comparator.comparing((Map h) -> (Integer.valueOf(String.valueOf(h.get("valueCode"))))));
				return list;
			}else {
				List<Map> list = s.getList(tag,filter);
				return list;
			}
		}
		return Collections.EMPTY_LIST;
	}
	
}


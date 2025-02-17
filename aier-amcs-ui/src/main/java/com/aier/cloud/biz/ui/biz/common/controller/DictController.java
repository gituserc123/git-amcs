package com.aier.cloud.biz.ui.biz.common.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseDictService;
import com.aier.cloud.biz.ui.biz.common.feign.AmcsDictService;

/**
 * @program: accs
 * @description: 查自有数据库表 For combobox
 * @author: hsw
 * @create: 2021-04-22 15:08
 **/
@Controller
@RequestMapping(value="/ui/amcs/dict")
public class DictController extends BaseController{
    @Autowired
    private AmcsDictService as;
    
    @Autowired
	private AdverseDictService aes;
    
    @RequestMapping(value="/getDict")
    @ResponseBody
    public List<Map> getDict(@RequestParam("type") String type, @RequestParam("filter") String filter){
        return as.getList(type, filter);
    }
    
    @RequestMapping(value="/getAeDict")
    @ResponseBody
    public List<Map> getAeDict(@RequestParam("type") String type,  @RequestParam(value = "filter", required = false) String filter){
        return aes.getList(type, filter);
    }

    @RequestMapping(value="/getAeDictFilters")
    @ResponseBody
    public List<Map> getAeDictFilters(@RequestParam("type") String type,  @RequestParam(value = "filters", required = false) String filters){
        return aes.getListByFilters(type, filters);
    }

    
    @RequestMapping(value="/getAeMoreList")
    @ResponseBody
    public Object getAeMoreList(@RequestParam("paraType[]") List<String> paraTypes){
    	if(CollectionUtils.isNotEmpty(paraTypes)) {
			return this.success(aes.getMoreList(paraTypes));
		}
    	return this.success(Collections.EMPTY_MAP);
    }

    @RequestMapping(value="/getEventQueryAeDict")
    @ResponseBody
    public List<Map> getEventQueryAeDict(@RequestParam("type") String type,  @RequestParam(value = "filter", required = false) String filter,
                                         @RequestParam(value = "gradeOneArr[]", required = false) List<String> gradeOneArr){
        List<Map> dictList = aes.getList(type, filter);
        if(Objects.nonNull(gradeOneArr) && gradeOneArr.size()>0){
            List<Map> retDictList = dictList.stream().map(dl -> {
                dl.put("filter",String.valueOf(dl.get("filter")).split(",")[1]);
                return dl;
            }).filter(item -> gradeOneArr.contains(String.valueOf(item.get("filter")))).collect(Collectors.toList());
            return retDictList;
        }else{
            return dictList;
        }
    }
}

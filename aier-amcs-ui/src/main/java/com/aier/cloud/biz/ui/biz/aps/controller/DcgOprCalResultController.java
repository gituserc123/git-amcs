/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.ui.biz.aps.controller;

import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.ui.biz.aps.feign.DcgOprCalResultService;
import com.aier.cloud.biz.ui.biz.aps.util.DcgOprCalResultVO;
import com.aier.cloud.biz.ui.biz.aps.util.ExcelUtiles;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aier.cloud.basic.api.request.condition.amcs.OprCalResultCondition;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * DcgOprCalResult
 * 人资手术计算结果表
 * @author 爱尔眼科
 * @since 2022-03-18 08:23:06
 */
@Controller
@RequestMapping("/ui/aps/dcgOprCalResult")
public class DcgOprCalResultController extends BaseController{
	private static final String LIST = "aps/operation/cal_list";
	@Autowired
	private DcgOprCalResultService calService;
	@Autowired
	private InstitutionService instService;

	//http://localhost:8056/ui/aps/dcgOprCalResult/list#
	@RequiresPermissions("ApsDcgOprCalResult:view")
	@GetMapping(value="/list")
	public String list(Map<String, Object> map) {
		return LIST;
	}

	@RequiresPermissions("ApsDcgOprCalResult:view")
	@PostMapping(value="/page")
	public @ResponseBody
	PageResponse<Map<String, Object>> page(Map<String, Object> map, OprCalResultCondition condition) {
	    // 日期范围是按这个 字段 ACTUAL_OPR_START_TIME  查询
		PageResponse<Map<String, Object>> page = calService.getPage(condition);

		return page;
	}

	@PostMapping(value = "/getHospByPlat")
	public @ResponseBody
	List<Map> getHospByPlat(@RequestParam(value = "key", required = false) String key,
							@RequestParam(value = "platformCode", required = false) String platformCode) {
		return instService.getHospByPlat(key, platformCode);
	}
	@RequiresPermissions("ApsDcgOprCalResult:edit")
	@PostMapping(value="/calOprData")
	@ResponseBody
	public Object calOprData(@RequestBody OprCalResultCondition condition) {
		Object obj = calService.calOprData(condition);
		return obj;
	}

	//===OprCalResultCondition condition,
    //  localhost:8056/ui/aps/dcgOprCalResult/exportExcel
//	@RequiresPermissions("ApsDcgOprCalResult:view")
	@RequestMapping(value="/exportExcel")
	@ResponseBody
	public void exportExcel(OprCalResultCondition condition, HttpServletResponse response){
        condition.setRows(10000); //最多1w条
        PageResponse<Map<String, Object>> page = calService.getPage(condition);

		List listData=page.getRows();
		List listVo=new ArrayList();
		for(int i=0;i<listData.size();i++){

			Map map=(Map)listData.get(i);
			DcgOprCalResultVO vo=new DcgOprCalResultVO();
			try {
				BeanUtils.populate(vo,map);
				listVo.add(vo);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		ExcelUtiles.exportExcel(listVo, null, "test", DcgOprCalResultVO.class, "病历浏览患者列表.xls", response,false);

//        List<PersonVO>  list=new ArrayList<>();
//        PersonVO v1=new PersonVO();
//        v1.setUsername("aaaaa");
//        v1.setAge("23");
//        v1.setSex("M");
//        v1.setHospId(9999L);
//
//        PersonVO v2=new PersonVO();
//        v2.setUsername("bbbbb");
//        v2.setAge("23");
//        v2.setSex("M");
//        v2.setHospId(9999L);
//
//        list.add(v1);
//        list.add(v2);


	}



//    @RequestMapping(value="/exportTest")
//    @ResponseBody
//    public void exportTest(  HttpServletResponse response){
//        List<PersonVO>  list=new ArrayList<>();
//        PersonVO v1=new PersonVO();
//        v1.setUsername("aaaaa");
//        v1.setAge("23");
//        v1.setSex("M");
//        v1.setHospId(9999L);
//
//        PersonVO v2=new PersonVO();
//        v2.setUsername("bbbbb");
//        v2.setAge("23");
//        v2.setSex("M");
//        v2.setHospId(9999L);
//
//        list.add(v1);
//        list.add(v2);
//
//        ExcelUtiles.exportExcel(list, null, "test", PersonVO.class, "病历浏览患者列表.xls", response,false);
//    }

//	@GetMapping(value="/list")
//	public String list(Map<String, Object> map) {
//		return LIST;
//	}
}

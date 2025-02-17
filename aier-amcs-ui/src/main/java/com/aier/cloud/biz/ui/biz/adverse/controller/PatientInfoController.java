package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.basic.api.request.condition.base.PatientInfoCondition;
import com.aier.cloud.basic.api.request.condition.outp.PatientCardCondition;
import com.aier.cloud.basic.api.request.domain.outp.PatientInfo;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.outp.PatientCard.CardState;
import com.aier.cloud.basic.api.response.domain.outp.PatientCard.CardType;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.InpPatientInfoService;
import com.aier.cloud.biz.ui.biz.adverse.feign.OutpPatientInfoService;
import com.aier.cloud.biz.ui.biz.adverse.utils.JudgmentUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright: Copyright(c) 2018 爱尔公司-版权所有
 *
 * @author lic
 * @date 2018/01/29
 **/
@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/patientInfo")
public class PatientInfoController extends BaseController {

	@Resource
	private OutpPatientInfoService outService;
	
	@Resource
	private InpPatientInfoService inpService;
	


	
	/**
	 * @param
	 * @Title: list
	 * @Description: 患者列表页面
	 * @retur Map<String , Object> 返回类型 @throws
	 */
	@RequestMapping(value = "/outpSearch", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> outpSearch(@RequestParam("searchValue") String searchValue) {
		
		
		if (StringUtils.isNotBlank(searchValue)) {
			PatientInfoCondition cond = new PatientInfoCondition();
			cond.setSearchValue(searchValue);
			
			if (JudgmentUtil.isPatientId(searchValue)) {
				cond.setId(Long.valueOf(searchValue));
			} else if (JudgmentUtil.isBarCode(searchValue)) {
				PatientCardCondition pcCond = new PatientCardCondition();
				pcCond.setCardNumber(searchValue);
				pcCond.setCardType(CardType.BARCODE.getValue());
				pcCond.setCardState(CardState.VALID.getValue());
				return outService.pageFindByCardNumber(pcCond);
			} else if (JudgmentUtil.isIdCard(searchValue)) {
				cond.setDocumentType(PatientInfo.DocumentType.IDCARD.getValue());
				cond.setIdNumber(searchValue);
			} else if (JudgmentUtil.isMobile(searchValue)) {
				cond.setTel1(searchValue);
			} else if (JudgmentUtil.isEnglish(searchValue)) {
				cond.setFirstSpell(searchValue.toUpperCase());
			} else {
				cond.setName(searchValue);
			}
			if (cond.getIsAll() || cond.getIsInp() || cond.getIsOutp()) {
				cond.setHospId(ShiroUtils.getTenantId());
			}
			cond.setArchiveHospId(ShiroUtils.getTenantId());
			return outService.blurUnionFindByParam(cond);
		}
		return null;
	}
	
	
    @RequestMapping(value = "/inpSearch", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> inpSearch(@RequestParam("searchValue") String searchValue) {
        return this.list(inpService.selectPageByParams(searchValue));
    }

}

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
package com.aier.cloud.biz.ui.biz.amcs.idr.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.idr.AmcsIdrBaseInfo;
import com.aier.cloud.basic.api.request.condition.based.BasedCommonCondition;
import com.aier.cloud.basic.api.request.condition.based.DcgDiagCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils; 
import com.aier.cloud.biz.ui.biz.amcs.idr.service.AmcsIdrDictTypeService;  
import com.aier.cloud.biz.ui.biz.amcs.idr.service.AmcsIdrBaseInfoService;

/**
 * AmcsIdrHb HB乙肝副卡
 * 
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@Controller
@RequestMapping("/ui/amcs/idr/amcsIDReditor")
public class AmcsIDReditorController extends BaseController {
	static final String P_new = "AmcsIDReditor:new";// 新增
	static final String P_view = "AmcsIDReditor:view";// 查询
	static final String P_save = "AmcsIDReditor:save";// 保存
	static final String P_audit = "AmcsIDReditor:audit";// 审核
	static final String P_auditUn = "AmcsIDReditor:auditUn";// 反审核
	static final String P_uploading = "AmcsIDReditor:uploading";// 上传初报
	static final String P_uploadMod = "AmcsIDReditor:uploadMod";// 上传修订
	static final String P_uploadAud = "AmcsIDReditor:uploadAud";// 上传审核
	static final String P_uploadRes = "AmcsIDReditor:uploadRes";// 上传恢复
	static final String P_uploadDel = "AmcsIDReditor:uploadDel";// 上传删除
	static final String P_uploadSeach = "AmcsIDReditor:uploadSeach";// 上传查询
	static final String P_delete = "AmcsIDReditor:delete";// 删除
///
	static final String P_seaview = "AmcsIDRsearch:view";// 查询
	static final String P_seaaudit = "AmcsIDRsearch:audit";// 审核
	static final String P_seaauditUn = "AmcsIDRsearch:auditUn";// 反审核
	static final String P_seauploading = "AmcsIDRsearch:uploading";// 上传初报
	static final String P_seauploadMod = "AmcsIDRsearch:uploadMod";// 上传修订
	static final String P_seauploadAud = "AmcsIDRsearch:uploadAud";// 上传审核
	static final String P_seauploadRes = "AmcsIDRsearch:uploadRes";// 上传恢复
	static final String P_seauploadDel = "AmcsIDRsearch:uploadDel";// 上传删除
	static final String P_seauploadSeach = "AmcsIDRsearch:uploadSeach";// 上传查询
	static final String P_seadelete = "AmcsIDRsearch:delete";// 删除

	@Autowired
	private AmcsIdrBaseInfoService amcsIdrBaseInfoService;
	@Autowired
	private AmcsIdrDictTypeService amcsIdrDictTypeService;

	/**
	 * 打开——传染病报告
	 * 
	 * @return
	 */
	@RequiresPermissions(P_view)
	@RequestMapping(value = "/getAmcsIDReditor", method = { RequestMethod.GET, RequestMethod.POST })
	public String getAmcsIDReditor(HttpServletRequest request, Long id, String visitNumber) {

		request.setAttribute("id", id);
		Object curData = null;
		String idstate = "编辑";
		if (id != null) {
			curData = amcsIdrBaseInfoService.getByMainId(id);
		}
		else if (visitNumber!=null)
		{
			curData =  amcsIdrDictTypeService.getPatient(visitNumber) ;
		}
		else {
			idstate = "新增";
			request.setAttribute("idstate", idstate);
			request.setAttribute("country", "中国大陆");
			request.setAttribute("placecode", "中国");
			request.setAttribute("nationcode", "01");
			request.setAttribute("livingaddressattributioncode", "1");
			request.setAttribute("fillingdoctorname", ShiroUtils.getLoginName());
			String iDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			request.setAttribute("diagnosisdate", iDate);
			request.setAttribute("onsetdate", iDate);
			request.setAttribute("cardfillingtime", iDate);
		}
		request.setAttribute("curData", curData);
		return "amcs/idr/AmcsIDReditor";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@RequiresPermissions(P_save)
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody AmcsIdrBaseInfo apply) {

		return this.success(amcsIdrBaseInfoService.save(apply, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_uploading, P_seauploading }, logical = Logical.OR)
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map<String, Object> upload(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.upload(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_auditUn, P_seaauditUn }, logical = Logical.OR)
	@RequestMapping(value = "/auditUn")
	@ResponseBody
	public Map<String, Object> auditUn(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.auditUn(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_uploadDel, P_seauploadDel }, logical = Logical.OR)
	@RequestMapping(value = "/uploadDel")
	@ResponseBody
	public Map<String, Object> uploadDel(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.uploadDel(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_uploadSeach, P_seauploadSeach }, logical = Logical.OR)
	@RequestMapping(value = "/uploadSeach")
	@ResponseBody
	public Map<String, Object> uploadSeach(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.uploadSeach(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_uploadRes, P_uploadRes }, logical = Logical.OR)
	@RequestMapping(value = "/uploadRes")
	@ResponseBody
	public Map<String, Object> uploadRes(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.uploadRes(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_uploadAud, P_seauploading }, logical = Logical.OR)
	@RequestMapping(value = "/uploadAud")
	@ResponseBody
	public Map<String, Object> uploadAud(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.uploadAud(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_uploadMod, P_seauploading }, logical = Logical.OR)
	@RequestMapping(value = "/uploadMod")
	@ResponseBody
	public Map<String, Object> uploadMod(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.uploadMod(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_delete, P_seadelete }, logical = Logical.OR)
	@RequestMapping(value = "/del")
	@ResponseBody
	public Map<String, Object> del(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.del(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	/**
	 * 审核
	 * 
	 * @param apply
	 * @return
	 */
	@RequiresPermissions(value = { P_audit, P_seaaudit }, logical = Logical.OR)
	@RequestMapping(value = "/audit")
	@ResponseBody
	public Map<String, Object> audit(@RequestBody AmcsIdrBaseInfo apply) {
		/** 判断是否有保存权限，有保存权限则进行一次保存操作 */
		if (ShiroUtils.hasPermission(P_save)) {
			apply = amcsIdrBaseInfoService.save(apply, ShiroUtils.getId(), ShiroUtils.getLoginName());
		}
		return this.success(amcsIdrBaseInfoService.audit(apply, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequiresPermissions(value = { P_audit, P_seaaudit }, logical = Logical.OR)
	@RequestMapping(value = "/auditById")
	@ResponseBody
	public Map<String, Object> auditById(@NotNull String id) {
		return this.success(amcsIdrBaseInfoService.auditById(id, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	/**
	 * 根据主表Id查询相关信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getByMainId")
	@ResponseBody
	public Object getByMainId(Long id) {
		return amcsIdrBaseInfoService.getByMainId(id);
	}

	/**
	 * 获取省市区
	 * 
	 * @param d
	 * @return
	 */
	@RequestMapping(value = "/getList")
	public @ResponseBody Object getList(BasedCommonCondition d) {
		return amcsIdrBaseInfoService.getList(d);
	}

	/**
	 * 获取ICD
	 * 
	 * @param d
	 * @return
	 */
	@RequestMapping(value = "/getDcgDiagList")
	public @ResponseBody Object getDcgDiagList(DcgDiagCondition d) {
		return amcsIdrBaseInfoService.getDcgDiagList(d);
	}

	/**
	 * 根据条件该机构下所有医院用户
	 * 
	 * @param d
	 * @return
	 */
	@RequestMapping(value = "/getHospStaff")
	public @ResponseBody List<Map> getHospStaff(StaffCondition d) {
		return amcsIdrBaseInfoService.getHospStaff(d);
	}

}

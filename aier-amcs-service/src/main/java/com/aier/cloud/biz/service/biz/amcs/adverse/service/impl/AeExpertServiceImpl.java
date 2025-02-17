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
package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeExpertEventMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpertEvent;
import com.aier.cloud.biz.service.biz.sys.feign.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeExpertService;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeExpertMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpert;


/**
 * AeExpert
 * 专家记录表
 * @author 爱尔眼科
 * @since 2023-02-07 15:54:34
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AeExpertServiceImpl extends ServiceImpl<AeExpertMapper, AeExpert> implements AeExpertService {

	private static final Logger logger = LoggerFactory.getLogger(AeExpertServiceImpl.class);

	@Value("${amcs.adverse.expertRole}")
	private Long expertRoleId;

	@Autowired
	private AeExpertEventMapper aeExpertEventMapper;

	@Autowired
	private InstitutionService institutionService;

	@Autowired
	private AuthorizeService authorizeService;

	@Autowired
	private StaffService staffService;


	@Override
	public List<Map<String, Object>> findListByCond(Page<Map<String, Object>> page, AeExpertCondition cond) {
		// TODO Auto-generated method stub
		if(!ObjectUtils.isEmpty(cond.getGroups())) {
			cond.setGroupsValue(this.transToOrGroups(cond.getGroups()));
		}
		return this.baseMapper.findListByCond(page, cond);
	}

	private Integer transToOrGroups(String groups) {
		Integer result = 0;
		String[] groupArray = groups.split(",");
        
        for (String groupStr : groupArray) {
            int decimalNumber = Integer.parseInt(groupStr); // 将字符串转换为整数
            int binaryNumber = Integer.parseInt(Integer.toBinaryString(decimalNumber), 2); // 将整数转换为二进制再转换为整数
            result |= binaryNumber; // 执行或运算
        }

        return result;

	}

	@Override
	public Integer save(Map<String, Object> mData) throws Exception {
		// TODO Auto-generated method stub
		if(!ObjectUtils.isEmpty(mData.get("ID"))) {
			Long staffId = Long.parseLong(mData.get("ID").toString());
			delAuthByStaff(staffId);
		}
		String staffCode = mData.get("CODE").toString();
		List<Institution> list=institutionService.getListByStaffCode(staffCode);
		list.forEach(institution -> {
			authorizeService.create(expertRoleId,institution.getId(), mData.get("ID").toString(), Constants.PlatformCode);
		});
		AeExpert aeExpert = new AeExpert();
		aeExpert.setStaffId(Long.parseLong(mData.get("ID").toString()));
		aeExpert.setStaffCode(mData.get("CODE").toString());
		aeExpert.setStaffName(mData.get("NAME").toString());
		aeExpert.setUsingSign(1);
		EntityUtils.addOperatorInfo(aeExpert);
		return this.baseMapper.insert(aeExpert);
	}

	@Override
	public Integer del(Long id) throws Exception {
		try {
			AeExpert expert = this.baseMapper.selectById(id);
			EntityWrapper<AeExpertEvent> ew = new EntityWrapper<>();
			ew.eq("expert_id", expert.getStaffId());
			int count = aeExpertEventMapper.selectCount(ew);
			if (count > 0) {
				aeExpertEventMapper.delete(ew);
			}
			//删除用户授权
			delAuthByStaff(expert.getStaffId());

			return this.baseMapper.deleteById(id);
		} catch (Exception e) {
			System.out.println("无法获取专家事件记录");
		}
		return 0;
	}

	@Override
	public void refresh(String staffCode) throws Exception {
        Staff staff = staffService.getByCode(staffCode);
		List<Institution> insts = institutionService.getListByStaffCode(staffCode);
		if(ObjectUtils.isEmpty(insts)) return;
		for(Institution inst: insts) {
			List<Map<String, Object>> staffRoleList = authorizeService.selectRolesByStaffIdAndInstId(staff.getId(), inst.getId());
			//判断staffRoleList是否有expertRoleId，没有的话则执行create
			if(staffRoleList != null && staffRoleList.stream().noneMatch(staffRole -> staffRole.get("roleId").equals(expertRoleId))) {
				authorizeService.create(expertRoleId, inst.getId(), staff.getId().toString(), Constants.PlatformCode);
			}
		}
	}

	//通过工号删除专家角色
	private void delAuthByStaff(Long staffId) throws Exception{
		Staff staff = staffService.getById(staffId);
		List<Institution> insts = institutionService.getListByStaffCode(staff.getCode());
		if(ObjectUtils.isEmpty(insts)) return;
		for(Institution inst: insts){
			List<Map<String, Object>> staffRoleList = authorizeService.selectRolesByStaffIdAndInstId(staffId, inst.getId());
			for(Map<String, Object> staffRole: staffRoleList){
				if(staffRole.get("roleId").equals(expertRoleId)){
					authorizeService.delete(Long.parseLong(staffRole.get("id").toString()));
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> findListByCond(AeExpertCondition cond) {
		// TODO Auto-generated method stub
		return this.baseMapper.findListByCond(cond);
	}

	@Override
	public List<AeExpert> getExpertsByCond(AeExpertCondition cond) {
		EntityWrapper<AeExpert> ew = new EntityWrapper<>();
		ew.eq("using_sign", 1);
		return this.baseMapper.selectList(ew);
	}




}


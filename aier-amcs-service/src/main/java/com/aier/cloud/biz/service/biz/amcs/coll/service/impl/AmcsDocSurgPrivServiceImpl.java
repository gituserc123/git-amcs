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
package com.aier.cloud.biz.service.biz.amcs.coll.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsDocSurgPrivService;
import com.aier.cloud.center.common.context.UserContext;
import com.aier.cloud.api.amcs.condition.DocSurgCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.biz.service.biz.amcs.coll.dao.AmcsDocSurgPrivMapper;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsDocSurgPriv;

/**
 * AmcsDocSurgPriv
 * 医生手术权限表
 * @author 爱尔眼科
 * @since 2023-05-16 10:36:22
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AmcsDocSurgPrivServiceImpl extends ServiceImpl<AmcsDocSurgPrivMapper, AmcsDocSurgPriv> implements AmcsDocSurgPrivService {

	@Override
	public void save(Map<String, Object> mData) throws Exception {
		// TODO Auto-generated method stub		
		List<Map<String, Object>> surgsList = (List<Map<String, Object>>)mData.get("surgs");
		List<Map<String, Object>> staffList = (List<Map<String, Object>>)mData.get("staffs");
		
		//获取所有医生编号
		AmcsDocSurgPriv basicDocSurg = EntityUtils.transMapToObject(mData, AmcsDocSurgPriv.class);
		basicDocSurg.setModifer(UserContext.getUserId());
		basicDocSurg.setModifyDate(new Date());
		
		//删除所有非子节点的元素
		surgsList.removeIf(surgItem -> surgItem.containsKey("children"));
		
		if(ObjectUtils.isEmpty(staffList)) return;
		int staffSize = staffList.size();
		
		staffList.stream().forEach(staffItem -> {
			//获取当前医生已授权的手术类型
			Long staffId = Long.parseLong(staffItem.get("id").toString());
			String staffName = staffItem.get("name").toString();
			String certLevelName = staffItem.get("certLevelName").toString();
			List<AmcsDocSurgPriv> docSurgList = this.findListByDoctor(staffId);
			List<String> existSurgCode = Lists.newArrayList();
			if(!docSurgList.isEmpty()) {
				docSurgList.stream().forEach(curDocSurg -> {
					//判断之前的医生职务是否发生变更，以变更职务的医生需要更新职务信息
					if(!certLevelName.equals(curDocSurg.getDeptName())) {
						curDocSurg.setDeptName(certLevelName);
						this.baseMapper.updateById(curDocSurg);
					}
					existSurgCode.add(curDocSurg.getSurgTypeCode());
				});
			}

			surgsList.stream().forEach(surgItem -> {
				String surgCode = surgItem.get("code").toString();
				//判断是否是包含子元素，如果包含子元素则直接删除
				if(!existSurgCode.contains(surgCode)) {
					AmcsDocSurgPriv amcsDocSurg = new AmcsDocSurgPriv();
					BeanUtils.copyProperties(basicDocSurg, amcsDocSurg);
					amcsDocSurg.setSurgTypeCode(surgCode);
					amcsDocSurg.setSurgTypeName(surgItem.get("name").toString());
					amcsDocSurg.setDoctorId(staffId);
					amcsDocSurg.setDoctorName(staffName);
					amcsDocSurg.setDeptName(certLevelName);
					this.baseMapper.insert(amcsDocSurg);
				}else {
					existSurgCode.remove(surgCode);
				}
			});
			//如果只选择了一个人员，则判断此人员是否有之前选这次未勾选的数据，存在则删除
			if(staffSize == 1 && existSurgCode.size() > 0) {
				List<Long> delIds = Lists.newArrayList();
				existSurgCode.stream().forEach(srugCode -> {
					List<Long> filteredList = docSurgList.stream()
			                .filter(obj -> obj.getSurgTypeCode().equals(srugCode))
			                .map(AmcsDocSurgPriv::getId)
			                .collect(Collectors.toList());
					delIds.addAll(filteredList);
				});
				this.baseMapper.deleteBatchIds(delIds);
			};
		});
		
		return;
	}

	@Override
	public List<AmcsDocSurgPriv> findListByDoctor(Long doctorId) {
		// TODO Auto-generated method stub
		EntityWrapper<AmcsDocSurgPriv> ew = new EntityWrapper<>();
		ew.eq("hosp_id", UserContext.getTenantId());
		ew.eq("doctor_id", doctorId);
		ew.eq("using_sign", 1);
		return this.baseMapper.selectList(ew);
	}

	@Override
	public List<AmcsDocSurgPriv> findListByHosp(Long hospId) {
		// TODO Auto-generated method stub
		EntityWrapper<AmcsDocSurgPriv> ew = new EntityWrapper<>();
		ew.eq("hosp_id", hospId);
		ew.eq("using_sign", 1);
		return this.baseMapper.selectList(ew);
	}

	@Override
	public List<Map<String, Object>> findCountByProv(Page<Map<String, Object>> page, DocSurgCondition cond) {
		// TODO Auto-generated method stub
		return this.baseMapper.findCountByProv(page, cond);
	}

	@Override
	public List<AmcsDocSurgPriv> findListByDoctor(Page<Map<String, Object>> page, DocSurgCondition cond) {
		// TODO Auto-generated method stub
		return this.baseMapper.findListByDoctor(page, cond);
	}

	@Override
	public List<Long> findStaffIdByHosp(Long hospId) {
		// TODO Auto-generated method stub
		return this.baseMapper.findStaffIdByHosp(hospId);
	}
	
	


	
	
	
	
	
}


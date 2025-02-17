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
package com.aier.cloud.biz.service.biz.amcs.idr.service;


import java.util.List;
import java.util.Map;

import com.aier.cloud.basic.api.request.condition.based.BasedCommonCondition;
import com.aier.cloud.basic.api.request.condition.based.DcgDiagCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.domain.based.DcgDiag;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrBaseInfo;
import com.aier.cloud.biz.service.biz.amcs.idr.enums.OperateTypeEnum;
import com.baomidou.mybatisplus.service.IService;

/**
 * AmcsIdrBaseInfo
 * 传染病基本信息表
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
public interface AmcsIdrBaseInfoService extends IService<AmcsIdrBaseInfo>{

	AmcsIdrBaseInfo save(AmcsIdrBaseInfo apply, Long loginUserId, String loginUserName);

	PageResponse<Map> getList(BasedCommonCondition d);

	 List<DcgDiag>  getDcgDiagList(DcgDiagCondition d);

	 List<Map> getHospStaff(StaffCondition d);

	AmcsIdrBaseInfo getByMainId(Long id);

	List<AmcsIdrBaseInfo> getMainByCondForList(AmcsIdrBaseInfo apply);

	AmcsIdrBaseInfo audit(AmcsIdrBaseInfo apply, Long loginUserId, String loginUserName);

	String del(String id, Long loginUserId, String loginUserName);

	String upload(String id, Long loginUserId, String loginUserName,OperateTypeEnum operateTypeEnum); 
	
	
	String auditById(String id, Long loginUserId, String loginUserName);

	String auditUn(String id, Long loginUserId, String loginUserName); 

}
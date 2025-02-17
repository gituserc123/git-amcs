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
package com.aier.cloud.biz.service.biz.amcs.jtr.service;

import com.aier.cloud.biz.service.biz.amcs.jtr.dto.JobStatisticsDto;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorTitle;
import com.baomidou.mybatisplus.service.IService;

/**
 * DoctorTitle
 * 员工职称（执业证）信息
 * @author 爱尔眼科
 * @since 2021-11-12 17:28:03
 */
public interface DoctorTitleService extends IService<DoctorTitle>{

	void saveJobDoctorTitle(JobStatisticsDto d);

	String getJobDoctorTitle(String userCode);
	
}
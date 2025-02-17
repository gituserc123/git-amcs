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
package com.aier.cloud.api.amcs.adverse.domain;


import com.aier.cloud.basic.api.domain.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * 
 * 
 * @author 爱尔眼科
 * @since 2022-10-31 15:24:55
 */

@Data
public class Attachment extends BaseEntity {
	private String fileId;
	
	/** 不良事件基础表ID*/
	private Long basicId;
	
	/** 启停标识(1:启用,0:停用)*/
	Integer usingSign = 1;
	
	/** 附件名称*/
	private String fileName;
	
	/** 附件路径*/
	private String url;
	
	/** 附件大小*/
	private Long fileSize;
	
	/** 附件类型(业务类型)*/
	private String tag;

	/** 是否已加密(1:是,0:否)*/
	private Integer isEncrypt;

	
}
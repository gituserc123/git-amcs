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
package com.aier.cloud.biz.service.biz.amcs.adverse.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * T_AE_ATTACHMENT
 * 
 * @author 爱尔眼科
 * @since 2022-11-07 09:24:29
 */
@Data
@TableName("T_AE_ATTACHMENT")
public class AeAttachment extends BaseEntity<AeAttachment> {
	/** 附件文件ID*/
    @Comment(value="附件文件ID")
	@TableField(value="file_id")
	@Length(max=50) private String fileId;
	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;
	
	/** 启停标识(1:启用,0:停用)*/
    @Comment(value="启停标识(1:启用,0:停用)")
	@TableField(value="using_sign")
	private Integer usingSign;
	
	/** 附件名称*/
    @Comment(value="附件名称")
	@TableField(value="file_name")
	@Length(max=100) private String fileName;
	
	/** 附件路径*/
    @Comment(value="附件路径")
	@TableField(value="url")
	@Length(max=200) private String url;
	
	/** 附件大小*/
    @Comment(value="附件大小")
	@TableField(value="file_size")
	private Long fileSize;
	
	/** 附件类型(业务类型)*/
    @Comment(value="附件类型(业务类型)")
	@TableField(value="tag")
	@Length(max=20) private String tag;

	/** 是否已加密(1:是,0:否)*/
	@Comment(value="是否已加密(1:是,0:否)")
	@TableField(value="is_encrypt")
	private Integer isEncrypt;

}

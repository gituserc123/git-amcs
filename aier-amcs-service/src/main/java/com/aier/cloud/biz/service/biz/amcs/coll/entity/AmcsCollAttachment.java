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
package com.aier.cloud.biz.service.biz.amcs.coll.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_COLL_ATTACHMENT
 * 医院采集信息附件表
 * @author 爱尔眼科
 * @since 2023-03-29 09:43:33
 */
@TableName("T_AMCS_COLL_ATTACHMENT")
public class AmcsCollAttachment extends BaseEntity<AmcsCollAttachment> {
	/** 采集信息ID*/
	@TableField(value="coll_id")
	@NotBlank private Long collId;
	
	/** 附件名称*/
	@TableField(value="file_name")
	@Length(max=50) private String fileName;
	
	/** 附件路径*/
	@TableField(value="url")
	@Length(max=200) private String url;
	
	/** 附件大小*/
	@TableField(value="file_size")
	private Long fileSize;
	
	/** 附件类型(业务类型)*/
	@TableField(value="tag")
	@Length(max=20) private String tag;
	
	/** 附件文件ID*/
	@TableField(value="file_id")
	@Length(max=50) private String fileId;
	
	/** 启停标识(1:启用,0:停用)*/
	@TableField(value="using_sign")
	private Integer usingSign = 1;
	

	public Long getCollId() {
		return collId;
	}
	public void setCollId(Long collId) {
		this.collId = collId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
}
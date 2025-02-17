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
package com.aier.cloud.api.amcs.domain.idr;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_AMCS_IDR_HFMD 手足口病附卡
 * 
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
public class AmcsIdrHfmd extends BaseEntity {
	/** 卡片ID */
	private String hfmdcardid;

	/** 重症患者编码 */

	private Integer intensivepatientcode;

	/** 重症患者名称 */
	private String intensivepatientname;

	/** 实验室结果编码 */

	private Integer labortestresultcode;

	/** 实验室结果名称 */
	private String labortestresultname;

	/** key1 */
	private String key1;

	/** key2 */
	private String key2;

	/** key3 */
	private String key3;

	/** key4 */
	private String key4;

	/** key5 */
	private String key5;

	/** key6 */
	private String key6;

	/** key7 */
	private String key7;

	/** 医院ID */
	private Long hospId;

	/** 医院名称 */
	private String hospName;

	/**
	 * "事件状态 （0 未审 1 已审 2 删除） "
	 */

	private Integer status;

	/** 创建者ID */
	private Long creator;

	/** 创建时间 */
	private java.util.Date createDate;

	/** T_ID */

	private Long tId;

	public String getHfmdcardid() {
		return hfmdcardid;
	}

	public void setHfmdcardid(String hfmdcardid) {
		this.hfmdcardid = hfmdcardid;
	}

	public Integer getIntensivepatientcode() {
		return intensivepatientcode;
	}

	public void setIntensivepatientcode(Integer intensivepatientcode) {
		this.intensivepatientcode = intensivepatientcode;
	}

	public String getIntensivepatientname() {
		return intensivepatientname;
	}

	public void setIntensivepatientname(String intensivepatientname) {
		this.intensivepatientname = intensivepatientname;
	}

	public Integer getLabortestresultcode() {
		return labortestresultcode;
	}

	public void setLabortestresultcode(Integer labortestresultcode) {
		this.labortestresultcode = labortestresultcode;
	}

	public String getLabortestresultname() {
		return labortestresultname;
	}

	public void setLabortestresultname(String labortestresultname) {
		this.labortestresultname = labortestresultname;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	public String getKey4() {
		return key4;
	}

	public void setKey4(String key4) {
		this.key4 = key4;
	}

	public String getKey5() {
		return key5;
	}

	public void setKey5(String key5) {
		this.key5 = key5;
	}

	public String getKey6() {
		return key6;
	}

	public void setKey6(String key6) {
		this.key6 = key6;
	}

	public String getKey7() {
		return key7;
	}

	public void setKey7(String key7) {
		this.key7 = key7;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public Long getTId() {
		return tId;
	}

	public void setTId(Long tId) {
		this.tId = tId;
	}
}
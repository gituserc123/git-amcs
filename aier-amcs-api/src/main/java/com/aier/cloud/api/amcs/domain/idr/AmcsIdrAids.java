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
 * T_AMCS_IDR_AIDS 艾滋病/HIV附卡
 * 
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
public class AmcsIdrAids extends BaseEntity {
	/** T_ID */
	private Long tId;

	/** 卡片ID */
	private String aidscardid;

	/** 样本来源编码 */
	private Integer specimensourcecode;

	/** 样本来源名称 */
	private String specimensourcename;

	/** 最有可能感染途径编码 */
	private Integer possibleinfectionroutecode;

	/** 最有可能感染途径名称 */
	private String possibleinfectionroutename;

	/** 样本来源其它 */
	private String othersamplesource;

	/** 接触史编码 */
	private String contacthistorycode;

	/** 接触史名称 */
	private String contacthistoryname;

	/** 接触史其它 */
	private String othercontacthistory;

	/** 感染途径其它 */
	private String otherinfectionroute;

	/** 艾滋病诊断日期 */
	private java.util.Date aidsdiagnosisdate;

	/** 注射毒品史 与病人共用过注射器的人数 */
	private Long injectiontogethernum;

	/** 非婚异性性接触史 与病人有非婚性行为的人数 */
	private Long nonmaritalsexnum;

	/** 男男性行为史 发生同性性行为的人数 */
	private Long homosexualsexnum;

	/** 实验室检测结论编码 */
	private Integer labortestconclusioncode;

	/** 实验室检测结论名称 */
	private String labortestconclusionname;

	/** 确认（替代策略）检测阳性日期 */
	private java.util.Date confirmedtestpositivedate;

	/** 性病史编码 */
	private Integer venerealhistorycode;

	/** 性病史名称 */
	private String venerealhistoryname;

	/** 生殖道沙眼衣原体感染编码 */
	private Integer chlamydialtrachomatiscode;

	/** 生殖道沙眼衣原体感染名称 */
	private String chlamydialtrachomatisname;

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

	public Long getTId() {
		return tId;
	}

	public void setTId(Long tId) {
		this.tId = tId;
	}

	public String getAidscardid() {
		return aidscardid;
	}

	public void setAidscardid(String aidscardid) {
		this.aidscardid = aidscardid;
	}

	public Integer getSpecimensourcecode() {
		return specimensourcecode;
	}

	public void setSpecimensourcecode(Integer specimensourcecode) {
		this.specimensourcecode = specimensourcecode;
	}

	public String getSpecimensourcename() {
		return specimensourcename;
	}

	public void setSpecimensourcename(String specimensourcename) {
		this.specimensourcename = specimensourcename;
	}

	public Integer getPossibleinfectionroutecode() {
		return possibleinfectionroutecode;
	}

	public void setPossibleinfectionroutecode(Integer possibleinfectionroutecode) {
		this.possibleinfectionroutecode = possibleinfectionroutecode;
	}

	public String getPossibleinfectionroutename() {
		return possibleinfectionroutename;
	}

	public void setPossibleinfectionroutename(String possibleinfectionroutename) {
		this.possibleinfectionroutename = possibleinfectionroutename;
	}

	public String getOthersamplesource() {
		return othersamplesource;
	}

	public void setOthersamplesource(String othersamplesource) {
		this.othersamplesource = othersamplesource;
	}

	public String getContacthistorycode() {
		return contacthistorycode;
	}

	public void setContacthistorycode(String contacthistorycode) {
		this.contacthistorycode = contacthistorycode;
	}

	public String getContacthistoryname() {
		return contacthistoryname;
	}

	public void setContacthistoryname(String contacthistoryname) {
		this.contacthistoryname = contacthistoryname;
	}

	public String getOthercontacthistory() {
		return othercontacthistory;
	}

	public void setOthercontacthistory(String othercontacthistory) {
		this.othercontacthistory = othercontacthistory;
	}

	public String getOtherinfectionroute() {
		return otherinfectionroute;
	}

	public void setOtherinfectionroute(String otherinfectionroute) {
		this.otherinfectionroute = otherinfectionroute;
	}

	public java.util.Date getAidsdiagnosisdate() {
		return aidsdiagnosisdate;
	}

	public void setAidsdiagnosisdate(java.util.Date aidsdiagnosisdate) {
		this.aidsdiagnosisdate = aidsdiagnosisdate;
	}

	public Long getInjectiontogethernum() {
		return injectiontogethernum;
	}

	public void setInjectiontogethernum(Long injectiontogethernum) {
		this.injectiontogethernum = injectiontogethernum;
	}

	public Long getNonmaritalsexnum() {
		return nonmaritalsexnum;
	}

	public void setNonmaritalsexnum(Long nonmaritalsexnum) {
		this.nonmaritalsexnum = nonmaritalsexnum;
	}

	public Long getHomosexualsexnum() {
		return homosexualsexnum;
	}

	public void setHomosexualsexnum(Long homosexualsexnum) {
		this.homosexualsexnum = homosexualsexnum;
	}

	public Integer getLabortestconclusioncode() {
		return labortestconclusioncode;
	}

	public void setLabortestconclusioncode(Integer labortestconclusioncode) {
		this.labortestconclusioncode = labortestconclusioncode;
	}

	public String getLabortestconclusionname() {
		return labortestconclusionname;
	}

	public void setLabortestconclusionname(String labortestconclusionname) {
		this.labortestconclusionname = labortestconclusionname;
	}

	public java.util.Date getConfirmedtestpositivedate() {
		return confirmedtestpositivedate;
	}

	public void setConfirmedtestpositivedate(java.util.Date confirmedtestpositivedate) {
		this.confirmedtestpositivedate = confirmedtestpositivedate;
	}

	public Integer getVenerealhistorycode() {
		return venerealhistorycode;
	}

	public void setVenerealhistorycode(Integer venerealhistorycode) {
		this.venerealhistorycode = venerealhistorycode;
	}

	public String getVenerealhistoryname() {
		return venerealhistoryname;
	}

	public void setVenerealhistoryname(String venerealhistoryname) {
		this.venerealhistoryname = venerealhistoryname;
	}

	public Integer getChlamydialtrachomatiscode() {
		return chlamydialtrachomatiscode;
	}

	public void setChlamydialtrachomatiscode(Integer chlamydialtrachomatiscode) {
		this.chlamydialtrachomatiscode = chlamydialtrachomatiscode;
	}

	public String getChlamydialtrachomatisname() {
		return chlamydialtrachomatisname;
	}

	public void setChlamydialtrachomatisname(String chlamydialtrachomatisname) {
		this.chlamydialtrachomatisname = chlamydialtrachomatisname;
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
}
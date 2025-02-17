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
package com.aier.cloud.biz.service.biz.amcs.idr.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_IDR_AIDS
 * 艾滋病/HIV附卡
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@TableName("T_AMCS_IDR_AIDS")
public class AmcsIdrAids extends BaseEntity<AmcsIdrAids> {
	/** T_ID*/
	@TableField(value="t_id")
	private Long tId;
	
	/** 卡片ID*/
	@TableField(value="aidscardid")
	@Length(max=40) private String aidscardid;
	
	/** 样本来源编码*/
	@TableField(value="specimensourcecode")
	private Integer specimensourcecode;
	
	/** 样本来源名称*/
	@TableField(value="specimensourcename")
	@Length(max=40) private String specimensourcename;
	
	/** 最有可能感染途径编码*/
	@TableField(value="possibleinfectionroutecode")
	private Integer possibleinfectionroutecode;
	
	/** 最有可能感染途径名称*/
	@TableField(value="possibleinfectionroutename")
	@Length(max=40) private String possibleinfectionroutename;
	
	/** 样本来源其它*/
	@TableField(value="othersamplesource")
	@Length(max=80) private String othersamplesource;
	
	/** 接触史编码*/
	@TableField(value="contacthistorycode")
	@Length(max=80) private String contacthistorycode;
	
	/** 接触史名称*/
	@TableField(value="contacthistoryname")
	@Length(max=300) private String contacthistoryname;
	
	/** 接触史其它*/
	@TableField(value="othercontacthistory")
	@Length(max=40) private String othercontacthistory;
	
	/** 感染途径其它*/
	@TableField(value="otherinfectionroute")
	@Length(max=100) private String otherinfectionroute;
	
	/** 艾滋病诊断日期*/
	@TableField(value="aidsdiagnosisdate")
	private java.util.Date aidsdiagnosisdate;
	
	/** 注射毒品史  与病人共用过注射器的人数  */
	@TableField(value="injectiontogethernum")
	private Long injectiontogethernum;
	
	/** 非婚异性性接触史 与病人有非婚性行为的人数 */
	@TableField(value="nonmaritalsexnum")
	private Long nonmaritalsexnum;
	
	/** 男男性行为史 发生同性性行为的人数 */
	@TableField(value="homosexualsexnum")
	private Long homosexualsexnum;
	
	/** 实验室检测结论编码*/
	@TableField(value="labortestconclusioncode")
	private Integer labortestconclusioncode;
	
	/** 实验室检测结论名称*/
	@TableField(value="labortestconclusionname")
	@Length(max=40) private String labortestconclusionname;
	
	/** 确认（替代策略）检测阳性日期 */
	@TableField(value="confirmedtestpositivedate")
	private java.util.Date confirmedtestpositivedate;
	
	/** 性病史编码*/
	@TableField(value="venerealhistorycode")
	private Integer venerealhistorycode;
	
	/** 性病史名称*/
	@TableField(value="venerealhistoryname")
	@Length(max=40) private String venerealhistoryname;
	
	/** 生殖道沙眼衣原体感染编码*/
	@TableField(value="chlamydialtrachomatiscode")
	private Integer chlamydialtrachomatiscode;
	
	/** 生殖道沙眼衣原体感染名称*/
	@TableField(value="chlamydialtrachomatisname")
	@Length(max=40) private String chlamydialtrachomatisname;
	
	/** key1*/
	@TableField(value="key1")
	@Length(max=100) private String key1;
	
	/** key2*/
	@TableField(value="key2")
	@Length(max=100) private String key2;
	
	/** key3*/
	@TableField(value="key3")
	@Length(max=100) private String key3;
	
	/** key4*/
	@TableField(value="key4")
	@Length(max=100) private String key4;
	
	/** key5*/
	@TableField(value="key5")
	@Length(max=100) private String key5;
	
	/** key6*/
	@TableField(value="key6")
	@Length(max=100) private String key6;
	
	/** key7*/
	@TableField(value="key7")
	@Length(max=100) private String key7;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	 
	
	/** "事件状态 （0 未审 1 已审 2 删除）
"*/
	@TableField(value="status")
	private Integer status;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	

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
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
package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * T_FIN_INS_PRICE_POLICY
 * 价格政策表
 * @author 爱尔眼科
 * @since 2023-08-07 15:09:57
 */
public class FinInsPricePolicyCondition  extends PageCondition implements Serializable {

	Long id;

	/** 医保协议定价等级*/
	private String ybxyDjdj;
	
	/** 国际医疗价*/
	Integer gjYlj;
	
	/** VIP价格*/
	private Integer vipJg;
	
	/** 自主定价*/
	private Integer zzDj;
	
	/** 医保支付价*/
	private Integer ybZfj;
	
	/** 诊疗项目执行公立医院医改价*/
	private Integer zlxmZxGlyyygj;
	
	/** 药品采购价零加成*/
	private Integer ypCgjljc;
	
	/** 药品中标价零加成*/
	private Integer ypZbjljc;
	
	/** 药品加价政策*/
	@Length(max=500) private String ypJjzc;
	
	/** 耗材采购价零加成*/
	private Integer hcCgjljc;
	
	/** 耗材中标价零加成*/
	private Integer hcZbjljc;
	
	/** 耗材加价政策*/
	private String hcJjzc;
	
	/** 屈光手术执行省区指导价*/
	private Integer qgssZxsqzdj;
	
	/** 屈光手术低于指导价是因哪些促销活动*/private String qgssDyzdjSynxcxhd;
	
	/** 视光诊疗项目执行集团指导价*/
	private Integer sgzlxmZxjtzdj;
	
	/** 视光诊疗项目低于指导价是因哪些促销活动*/
	private String sgzlxmDyzdjSynxcxhd;
	
	/** 当地医保三大目录中今年新增眼科相关收费项目*/
	private String ddybsdmlzJnxzykXgsfxm;
	
	/** */
	private Long creator;
	
	/** */
	private java.util.Date createDate;
	
	/** */
	private Long hospId;

	private Date modifyDate;

	private Long modifer;

	// 医院列表
	private List<Long> hospList;

	// 省区Id
	private Long province;

	// 当前年份
	private String curYear;


	private String hospName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getYbxyDjdj() {
		return ybxyDjdj;
	}
	public void setYbxyDjdj(String ybxyDjdj) {
		this.ybxyDjdj = ybxyDjdj;
	}
	public Integer getGjYlj() {
		return gjYlj;
	}
	public void setGjYlj(Integer gjYlj) {
		this.gjYlj = gjYlj;
	}
	public Integer getVipJg() {
		return vipJg;
	}
	public void setVipJg(Integer vipJg) {
		this.vipJg = vipJg;
	}
	public Integer getZzDj() {
		return zzDj;
	}
	public void setZzDj(Integer zzDj) {
		this.zzDj = zzDj;
	}
	public Integer getYbZfj() {
		return ybZfj;
	}
	public void setYbZfj(Integer ybZfj) {
		this.ybZfj = ybZfj;
	}
	public Integer getZlxmZxGlyyygj() {
		return zlxmZxGlyyygj;
	}
	public void setZlxmZxGlyyygj(Integer zlxmZxGlyyygj) {
		this.zlxmZxGlyyygj = zlxmZxGlyyygj;
	}
	public Integer getYpCgjljc() {
		return ypCgjljc;
	}
	public void setYpCgjljc(Integer ypCgjljc) {
		this.ypCgjljc = ypCgjljc;
	}
	public Integer getYpZbjljc() {
		return ypZbjljc;
	}
	public void setYpZbjljc(Integer ypZbjljc) {
		this.ypZbjljc = ypZbjljc;
	}
	public String getYpJjzc() {
		return ypJjzc;
	}
	public void setYpJjzc(String ypJjzc) {
		this.ypJjzc = ypJjzc;
	}
	public Integer getHcCgjljc() {
		return hcCgjljc;
	}
	public void setHcCgjljc(Integer hcCgjljc) {
		this.hcCgjljc = hcCgjljc;
	}
	public Integer getHcZbjljc() {
		return hcZbjljc;
	}
	public void setHcZbjljc(Integer hcZbjljc) {
		this.hcZbjljc = hcZbjljc;
	}
	public String getHcJjzc() {
		return hcJjzc;
	}
	public void setHcJjzc(String hcJjzc) {
		this.hcJjzc = hcJjzc;
	}
	public Integer getQgssZxsqzdj() {
		return qgssZxsqzdj;
	}
	public void setQgssZxsqzdj(Integer qgssZxsqzdj) {
		this.qgssZxsqzdj = qgssZxsqzdj;
	}
	public String getQgssDyzdjSynxcxhd() {
		return qgssDyzdjSynxcxhd;
	}
	public void setQgssDyzdjSynxcxhd(String qgssDyzdjSynxcxhd) {
		this.qgssDyzdjSynxcxhd = qgssDyzdjSynxcxhd;
	}
	public Integer getSgzlxmZxjtzdj() {
		return sgzlxmZxjtzdj;
	}
	public void setSgzlxmZxjtzdj(Integer sgzlxmZxjtzdj) {
		this.sgzlxmZxjtzdj = sgzlxmZxjtzdj;
	}
	public String getSgzlxmDyzdjSynxcxhd() {
		return sgzlxmDyzdjSynxcxhd;
	}
	public void setSgzlxmDyzdjSynxcxhd(String sgzlxmDyzdjSynxcxhd) {
		this.sgzlxmDyzdjSynxcxhd = sgzlxmDyzdjSynxcxhd;
	}
	public String getDdybsdmlzJnxzykXgsfxm() {
		return ddybsdmlzJnxzykXgsfxm;
	}
	public void setDdybsdmlzJnxzykXgsfxm(String ddybsdmlzJnxzykXgsfxm) {
		this.ddybsdmlzJnxzykXgsfxm = ddybsdmlzJnxzykXgsfxm;
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
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public List<Long> getHospList() {
		return hospList;
	}

	public void setHospList(List<Long> hospList) {
		this.hospList = hospList;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public String getCurYear() {
		return curYear;
	}

	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
}
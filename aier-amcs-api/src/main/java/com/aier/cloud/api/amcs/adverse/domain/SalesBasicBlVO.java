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

import lombok.Data;

/**
 * 不良事件配镜数据实体VO
 * @author xiejun
 * @since 2022-12-03 10:27:10
 */
@Data
public class SalesBasicBlVO {

	/** 右眼球镜*/
	private String sphOd;

	/** 左眼球镜*/
	private String sphOs;

	/** 右眼柱镜 */
	private String cylOd;

	/** 左眼柱镜*/
	private String cylOs;

	/** 右眼轴向*/
	private String axisOd;

	/** 左眼轴向*/
	private String axisOs;

	/** 矫正视力*/
	private String dvaOd;

	/** 矫正视力*/
	private String dvaOs;

	/** 右眼下加光*/
	private String addOd;

	/** 左眼下加光*/
	private String addOs;

	/** 右眼单侧瞳距*/
	private String dpdOd;

	/** 左眼单侧瞳距*/
	private String dpdOs;

	/** 右眼瞳高*/
	private String pupilHeightOd;

	/** 左眼瞳高*/
	private String pupilHeightOs;

	/** 镜片类型*/
	private String recipeType;

	private String recipeTypeValue;

	public String getRecipeTypeValue() {
		return recipeTypeValue;
	}

	public void setRecipeTypeValue(String recipeTypeValue) {
		this.recipeTypeValue = recipeTypeValue;
	}

	public String getSphOd() {
		return sphOd;
	}

	public void setSphOd(String sphOd) {
		this.sphOd = sphOd;
	}

	public String getSphOs() {
		return sphOs;
	}

	public void setSphOs(String sphOs) {
		this.sphOs = sphOs;
	}

	public String getCylOd() {
		return cylOd;
	}

	public void setCylOd(String cylOd) {
		this.cylOd = cylOd;
	}

	public String getCylOs() {
		return cylOs;
	}

	public void setCylOs(String cylOs) {
		this.cylOs = cylOs;
	}

	public String getAxisOd() {
		return axisOd;
	}

	public void setAxisOd(String axisOd) {
		this.axisOd = axisOd;
	}

	public String getAxisOs() {
		return axisOs;
	}

	public void setAxisOs(String axisOs) {
		this.axisOs = axisOs;
	}

	public String getDvaOd() {
		return dvaOd;
	}

	public void setDvaOd(String dvaOd) {
		this.dvaOd = dvaOd;
	}

	public String getDvaOs() {
		return dvaOs;
	}

	public void setDvaOs(String dvaOs) {
		this.dvaOs = dvaOs;
	}

	public String getAddOd() {
		return addOd;
	}

	public void setAddOd(String addOd) {
		this.addOd = addOd;
	}

	public String getAddOs() {
		return addOs;
	}

	public void setAddOs(String addOs) {
		this.addOs = addOs;
	}

	public String getDpdOd() {
		return dpdOd;
	}

	public void setDpdOd(String dpdOd) {
		this.dpdOd = dpdOd;
	}

	public String getDpdOs() {
		return dpdOs;
	}

	public void setDpdOs(String dpdOs) {
		this.dpdOs = dpdOs;
	}

	public String getPupilHeightOd() {
		return pupilHeightOd;
	}

	public void setPupilHeightOd(String pupilHeightOd) {
		this.pupilHeightOd = pupilHeightOd;
	}

	public String getPupilHeightOs() {
		return pupilHeightOs;
	}

	public void setPupilHeightOs(String pupilHeightOs) {
		this.pupilHeightOs = pupilHeightOs;
	}

	public String getRecipeType() {
		return recipeType;
	}

	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}
}
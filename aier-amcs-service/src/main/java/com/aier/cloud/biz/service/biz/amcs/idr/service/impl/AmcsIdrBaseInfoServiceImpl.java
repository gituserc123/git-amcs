
package com.aier.cloud.biz.service.biz.amcs.idr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.idr.service.AmcsIdrBaseInfoService;
import com.aier.cloud.biz.service.biz.amcs.idr.service.AmcsIdrDictTypeService;
import com.aier.cloud.center.common.context.UserContext;
import com.aier.cloud.basic.api.request.condition.based.BasedCommonCondition;
import com.aier.cloud.basic.api.request.condition.based.DcgDiagCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.domain.based.DcgDiag;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.biz.service.biz.amcs.idr.dao.AmcsIdrAfpMapper;
import com.aier.cloud.biz.service.biz.amcs.idr.dao.AmcsIdrAidsMapper;
import com.aier.cloud.biz.service.biz.amcs.idr.dao.AmcsIdrBaseInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.idr.dao.AmcsIdrHbMapper;
import com.aier.cloud.biz.service.biz.amcs.idr.dao.AmcsIdrHfmdMapper;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrAfp;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrAids;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrBaseInfo;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrDictType;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrHb;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrHfmd;
import com.aier.cloud.biz.service.biz.amcs.idr.enums.*;
import com.aier.cloud.biz.service.biz.amcs.idr.feign.BaseFeignService;
import com.aier.cloud.biz.service.biz.amcs.idr.sdk.SDKidr;
import com.aier.cloud.biz.service.biz.amcs.idr.sdk.IDRhelper;

/**
 * AmcsIdrBaseInfo 传染病基本信息表
 * 
 * @author Luorz
 * @since 2023-04-24 10:31:27
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AmcsIdrBaseInfoServiceImpl extends ServiceImpl<AmcsIdrBaseInfoMapper, AmcsIdrBaseInfo>
		implements AmcsIdrBaseInfoService {
	private static final AtomicInteger in = new AtomicInteger(0);
	@Autowired
	private BaseFeignService baseFeignService;
	@Autowired
	private AmcsIdrDictTypeService amcsIdrDictTypeService;
	@Autowired
	private AmcsIdrBaseInfoMapper amcsIdrBaseInfoMapper;

	@Autowired
	private AmcsIdrAidsMapper amcsIdrAidsMapper;
	@Autowired
	private AmcsIdrAfpMapper amcsIdrAfpMapper;
	@Autowired
	private AmcsIdrHfmdMapper amcsIdrHfmdMapper;
	@Autowired
	private AmcsIdrHbMapper amcsIdrHbMapper;
	@Resource
	SDKidr sdk;

	/**
	 * 保存传染病基本信息表
	 */
	@Override
	public AmcsIdrBaseInfo save(AmcsIdrBaseInfo apply, Long loginUserId, String loginUserName) {

		apply.setModifer(loginUserId);
		apply.setModifyDate(new Date());
		// 填卡医生姓名
		// apply.setFillingdoctorname(loginUserName);
		if (apply.getCardfillingtime() == null) {
			apply.setCardfillingtime(new Date());
		}
		// 保存性别等枚举类型的名称
		if (SexEnum.isInclude("K" + apply.getGendercode())) {
			apply.setGendername(SexEnum.valueOf("K" + apply.getGendercode()).getEnumDesc());
		}
		if (IDROccupationCodeEnum.isInclude("K" + apply.getIdrOccupationcode())) {
			apply.setOccupationname(IDROccupationCodeEnum.valueOf("K" + apply.getIdrOccupationcode()).getEnumDesc());
		}
		if (NationEnum.isInclude("K" + apply.getNationcode())) {
			apply.setNationname(NationEnum.valueOf("K" + apply.getNationcode()).getEnumDesc());
		}
		if (OccupationcodeEnum.isInclude("K" + apply.getCodrisOccupationcode())) {
			apply.setOrgcountyname(OccupationcodeEnum.valueOf("K" + apply.getCodrisOccupationcode()).getEnumDesc());
		}
		// 现住地址类型编码
		if (AddressCodeEnum.isInclude("K" + apply.getLivingaddressattributioncode())) {
			apply.setLivingaddressattributionname(
					AddressCodeEnum.valueOf("K" + apply.getLivingaddressattributioncode()).getEnumDesc());
		}

		// 有效身份证件类型
		if (IDCardTypeEnum.isInclude("K" + apply.getIdcardtype())) {
			apply.setIdcardtypename(IDCardTypeEnum.valueOf("K" + apply.getIdcardtype()).getEnumDesc());
		}
		// 婚姻状况代码
		if (MaritalstatuscodeEnum.isInclude("K" + apply.getMaritalstatuscode())) {
			apply.setMaritalstatusname(MaritalstatuscodeEnum.valueOf("K" + apply.getMaritalstatuscode()).getEnumDesc());
		}
		// 文化程度代码
		if (EducationlevelcodeEnum.isInclude("K" + apply.getEducationlevelcode())) {
			apply.setEducationlevelname(
					EducationlevelcodeEnum.valueOf("K" + apply.getEducationlevelcode()).getEnumDesc());
		}
		// 户籍地类型编码
		if (AddressCodeEnum.isInclude("K" + apply.getDomicileaddressattributioncode())) {
			apply.setDomicileaddressattributionname(
					AddressCodeEnum.valueOf("K" + apply.getDomicileaddressattributioncode()).getEnumDesc());
		}
		// 生前常住址类型
		if (AddressCodeEnum.isInclude("K" + apply.getLifetimeaddrtypecode())) {
			apply.setLifetimeaddrtypecodename(
					AddressCodeEnum.valueOf("K" + apply.getLifetimeaddrtypecode()).getEnumDesc());
		}
		// 死亡地点代码
		if (DeathPlaceEnum.isInclude("K" + apply.getDeathplacecode())) {
			apply.setDeathplacecodename(DeathPlaceEnum.valueOf("K" + apply.getDeathplacecode()).getEnumDesc());
		}
		// 7.5 传染病诊断类型代码
		if (DiagnosistypeEnum.isInclude("K" + apply.getDiagnosistypecode())) {
			apply.setDiagnosistypename(DiagnosistypeEnum.valueOf("K" + apply.getDiagnosistypecode()).getEnumDesc());
		}
		// 7.7 病例分类代码
		if (CaseClassificationEnum.isInclude("K" + apply.getCaseclassificationcode())) {
			apply.setCaseclassificationname(
					CaseClassificationEnum.valueOf("K" + apply.getCaseclassificationcode()).getEnumDesc());
		}
		// 7.7 病例分类代码
		if (CloseContactsSymptomEnum.isInclude("K" + apply.getClosecontactssymptomcode())) {
			apply.setClosecontactssymptomname(
					CloseContactsSymptomEnum.valueOf("K" + apply.getClosecontactssymptomcode()).getEnumDesc());
		}
		// 7.22 转归代码
		if (OutcomeEnum.isInclude("K" + apply.getOutcomecode())) {
			apply.setOutcomecodename(OutcomeEnum.valueOf("K" + apply.getOutcomecode()).getEnumDesc());
		}
		// 死亡原因
		if (DeathcauseEnum.isInclude("K" + apply.getDeathcause())) {
			apply.setDeathcausename(DeathcauseEnum.valueOf("K" + apply.getDeathcause()).getEnumDesc());
		}
		// 7.34 最高诊断单位代码
		if (HighestDiagnosisUnitEnum.isInclude("K" + apply.getHighestdiagnosisunit())) {
			apply.setHighestdiagnosisunitname(
					HighestDiagnosisUnitEnum.valueOf("K" + apply.getHighestdiagnosisunit()).getEnumDesc());
		}
		if (HighestDiagnosisUnitEnum.isInclude("K" + apply.getDiagnosticunitcode())) {
			apply.setDiagnosticunitcodename(
					HighestDiagnosisUnitEnum.valueOf("K" + apply.getDiagnosticunitcode()).getEnumDesc());
		}
		// 发病至死亡时间间隔单位名称
		if (IntervalUnitEnum.isInclude("K" + apply.getIntervalunitcodea())) {
			apply.setIntervalunitcodeaname(IntervalUnitEnum.valueOf("K" + apply.getIntervalunitcodea()).getEnumDesc());
		}
		// 发病至死亡时间间隔单位名称
		if (IntervalUnitEnum.isInclude("K" + apply.getIntervalunitcodeb())) {
			apply.setIntervalunitcodebname(IntervalUnitEnum.valueOf("K" + apply.getIntervalunitcodeb()).getEnumDesc());
		}
		if (IntervalUnitEnum.isInclude("K" + apply.getIntervalunitcodec())) {
			apply.setIntervalunitcodecname(IntervalUnitEnum.valueOf("K" + apply.getIntervalunitcodec()).getEnumDesc());
		}
		if (IntervalUnitEnum.isInclude("K" + apply.getIntervalunitcoded())) {
			apply.setIntervalunitcodedname(IntervalUnitEnum.valueOf("K" + apply.getIntervalunitcoded()).getEnumDesc());
		}
		if (DiagnosticbasisEnum.isInclude("K" + apply.getDiagnosticbasiscode())) {
			apply.setDiagnosticbasiscodename(
					DiagnosticbasisEnum.valueOf("K" + apply.getDiagnosticbasiscode()).getEnumDesc());
		}
		if (AuditStateEnum.isInclude("K" + apply.getDeletingtypecode())) {
			apply.setDeletingtypename(AuditStateEnum.valueOf("K" + apply.getDeletingtypecode()).getEnumDesc());
		}
		if (NewPneumSeverityEnum.isInclude("K" + apply.getNewpneumseveritycode())) {
			apply.setNewpneumseverityname(
					NewPneumSeverityEnum.valueOf("K" + apply.getNewpneumseveritycode()).getEnumDesc());
		}
		AmcsIdrDictType apply1 = new AmcsIdrDictType();
		apply1.setUsingSign(1);
		apply1.setTypeCode(AmcsIdrDictEnum.Ktoken.getEnumCode());
		List<AmcsIdrDictType> applyDict = amcsIdrDictTypeService.getAmcsIdrDictType(apply1);
		if (applyDict != null && applyDict.size() > 0) {
			apply1 = applyDict.get(0);
			if (apply1.getLeveles() == null || apply1.getValueCode() == null) {
				throw new BizException("请先配置好传染病参数。");
			}
		} else {
			throw new BizException("请先配置好传染病参数");
		}
		apply.setOrgcode(apply1.getLeveles());

		apply.setOrgname(apply1.getValueCode());
		// 需要删掉现在没有其他表格
		amcsIdrAidsMapper.delTid(apply.getId());
		amcsIdrAfpMapper.delTid(apply.getId());
		amcsIdrHfmdMapper.delTid(apply.getId());
		amcsIdrHbMapper.delTid(apply.getId());
		// apply.setId(null);
		if (apply.getId() == null) {
			apply.setCreator(loginUserId);
			apply.setStatus(0);
			apply.setCardid(makeHospSerialNo(apply.getOrgcode()));
			apply.setMaker(loginUserId);
			apply.setMakedTime(new Date());
			apply.setMakerName(loginUserName);
			amcsIdrBaseInfoMapper.insert(apply);
		} else {
			AmcsIdrBaseInfo amcsIdrBaseInfo = amcsIdrBaseInfoMapper.selectById(apply.getId());
			if (!amcsIdrBaseInfo.getStatus().equals(0)) {
				// throw new BizException(
				// "患者姓名【" + amcsIdrBaseInfo.getPatientname() + "】,卡片ID【" +
				// amcsIdrBaseInfo.getCardid() + "】 已经 "
				// + (AuditStateEnum.valueOf("K" + amcsIdrBaseInfo.getStatus()).getEnumDesc()));
			}
			amcsIdrBaseInfoMapper.updateById(apply);

		}
		if (apply.getAmcsIdrAids() != null) {
			saveAmcsIdrAids(apply.getAmcsIdrAids(), apply, loginUserId);
		}
		if (apply.getAmcsIdrAfp() != null) {
			saveAmcsIdrAfp(apply.getAmcsIdrAfp(), apply, loginUserId);
		}
		if (apply.getAmcsIdrHfmd() != null) {
			saveAmcsIdrHfmd(apply.getAmcsIdrHfmd(), apply, loginUserId);
		}
		if (apply.getAmcsIdrHb() != null) {
			saveAmcsIdrHb(apply.getAmcsIdrHb(), apply, loginUserId);
		}
		return apply;
	}

	/**
	 * 5.4 AFP病附卡采集数据元 amcsIdrAfp 不为空时，才保存
	 * 
	 * @param amcsIdrAids
	 * @param apply
	 */
	private void saveAmcsIdrAfp(AmcsIdrAfp amcsIdrAfp, AmcsIdrBaseInfo apply, Long loginUserId) {
		if (amcsIdrAfp.getPatientresidencetypecode() != null) {
			amcsIdrAfp.setAfpcardid(apply.getCardid());
			amcsIdrAfp.setTId(apply.getId());
			if (PatientResidenceTypeEnum.isInclude("K" + amcsIdrAfp.getPatientresidencetypecode())) {
				amcsIdrAfp.setPatientresidencetypename(
						PatientResidenceTypeEnum.valueOf("K" + amcsIdrAfp.getPatientresidencetypecode()).getEnumDesc());
			}

			if (amcsIdrAfp.getId() == null) {
				amcsIdrAfp.setCreator(loginUserId);
				amcsIdrAfpMapper.insert(amcsIdrAfp);
			} else {
				amcsIdrAfp.setModifer(loginUserId);
				amcsIdrAfp.setModifyDate(new Date());
				amcsIdrAfpMapper.updateById(amcsIdrAfp);
			}
		}

	}

	/**
	 * 5.3 手足口病附卡采集数据元
	 * 
	 * @param amcsIdrAids
	 * @param apply
	 */
	private void saveAmcsIdrHfmd(AmcsIdrHfmd amcsIdrHfmd, AmcsIdrBaseInfo apply, Long loginUserId) {
		if (amcsIdrHfmd.getIntensivepatientcode() != null) {
			amcsIdrHfmd.setHfmdcardid(apply.getCardid());
			amcsIdrHfmd.setTId(apply.getId());
			if (YesNoEnum.isInclude("K" + amcsIdrHfmd.getIntensivepatientcode())) {
				amcsIdrHfmd.setIntensivepatientname(
						YesNoEnum.valueOf("K" + amcsIdrHfmd.getIntensivepatientcode()).getEnumDesc());
			}
			if (LaborTestResultEnum.isInclude("K" + amcsIdrHfmd.getLabortestresultcode())) {
				amcsIdrHfmd.setLabortestresultname(
						LaborTestResultEnum.valueOf("K" + amcsIdrHfmd.getLabortestresultcode()).getEnumDesc());
			}
			amcsIdrHfmd.setModifer(loginUserId);
			amcsIdrHfmd.setModifyDate(new Date());

			if (amcsIdrHfmd.getId() == null) {
				amcsIdrHfmd.setCreator(loginUserId);
				amcsIdrHfmdMapper.insert(amcsIdrHfmd);
			} else {
				amcsIdrHfmdMapper.updateById(amcsIdrHfmd);
			}
		}

	}

	/**
	 * 5.5 HB乙肝副卡采集数据元 amcsIdrHb
	 * 
	 * @param amcsIdrAids
	 * @param apply
	 */
	private void saveAmcsIdrHb(AmcsIdrHb amcsIdrHb, AmcsIdrBaseInfo apply, Long loginUserId) {
		if (amcsIdrHb.getHbsagtestpositivetypecode() != null) {
			amcsIdrHb.setHbcardid(apply.getCardid());
			amcsIdrHb.setTId(apply.getId());
			if (HbsagtestpositivetypeEnum.isInclude("K" + amcsIdrHb.getHbsagtestpositivetypecode())) {
				amcsIdrHb.setHbsagtestpositivetypename(HbsagtestpositivetypeEnum
						.valueOf("K" + amcsIdrHb.getHbsagtestpositivetypecode()).getEnumDesc());
			}
			if (HBcAbIgMTestEnum.isInclude("K" + amcsIdrHb.getHbcabigmtestcode())) {
				amcsIdrHb.setHbcabigmtestname(
						HBcAbIgMTestEnum.valueOf("K" + amcsIdrHb.getHbcabigmtestcode()).getEnumDesc());
			}
			if (LiveRpunctureTestEnum.isInclude("K" + amcsIdrHb.getLiverpuncturetestcode())) {
				amcsIdrHb.setLiverpuncturetestname(
						LiveRpunctureTestEnum.valueOf("K" + amcsIdrHb.getLiverpuncturetestcode()).getEnumDesc());
			}
			if (HBcAbIgMTestEnum.isInclude("K" + amcsIdrHb.getHbsagnorhbsabpcode())) {
				amcsIdrHb.setHbsagnorhbsabpname(
						HBcAbIgMTestEnum.valueOf("K" + amcsIdrHb.getHbsagnorhbsabpcode()).getEnumDesc());
			}
			amcsIdrHb.setModifer(loginUserId);
			amcsIdrHb.setModifyDate(new Date());
			if (amcsIdrHb.getId() == null) {
				amcsIdrHb.setCreator(loginUserId);
				amcsIdrHbMapper.insert(amcsIdrHb);
			} else {
				amcsIdrHbMapper.updateById(amcsIdrHb);
			}
		}

	}

	/**
	 * 当5.2 艾滋病/HIV等附卡采集数据元 AmcsIdrAids 不为空时，才保存
	 * 
	 * @param amcsIdrAids
	 * @param apply
	 */
	private void saveAmcsIdrAids(AmcsIdrAids amcsIdrAids, AmcsIdrBaseInfo apply, Long loginUserId) {
		if (amcsIdrAids.getSpecimensourcecode() != null) {
			amcsIdrAids.setAidscardid(apply.getCardid());
			amcsIdrAids.setTId(apply.getId());
			if (SpecimenSourceEnum.isInclude("K" + amcsIdrAids.getSpecimensourcecode())) {
				amcsIdrAids.setSpecimensourcename(
						SpecimenSourceEnum.valueOf("K" + amcsIdrAids.getSpecimensourcecode()).getEnumDesc());
			}
			if (PossibleInfectionRouteEnum.isInclude("K" + amcsIdrAids.getPossibleinfectionroutecode())) {
				amcsIdrAids.setPossibleinfectionroutename(PossibleInfectionRouteEnum
						.valueOf("K" + amcsIdrAids.getPossibleinfectionroutecode()).getEnumDesc());
			}
			if (ContactHistoryEnum.isInclude("K" + amcsIdrAids.getContacthistorycode())) {
				amcsIdrAids.setContacthistoryname(
						ContactHistoryEnum.valueOf("K" + amcsIdrAids.getContacthistorycode()).getEnumDesc());
			}
			if (LaborTestConclusionEnum.isInclude("K" + amcsIdrAids.getLabortestconclusioncode())) {
				amcsIdrAids.setLabortestconclusionname(
						LaborTestConclusionEnum.valueOf("K" + amcsIdrAids.getLabortestconclusioncode()).getEnumDesc());
			}
			if (LaborTestConclusionEnum.isInclude("K" + amcsIdrAids.getVenerealhistorycode())) {
				amcsIdrAids.setVenerealhistoryname(
						LaborTestConclusionEnum.valueOf("K" + amcsIdrAids.getVenerealhistorycode()).getEnumDesc());
			}
			if (LaborTestConclusionEnum.isInclude("K" + amcsIdrAids.getChlamydialtrachomatiscode())) {
				amcsIdrAids.setChlamydialtrachomatisname(LaborTestConclusionEnum
						.valueOf("K" + amcsIdrAids.getChlamydialtrachomatiscode()).getEnumDesc());
			}
			amcsIdrAids.setModifer(loginUserId);
			amcsIdrAids.setModifyDate(new Date());
			if (amcsIdrAids.getId() == null) {
				amcsIdrAids.setCreator(loginUserId);
				amcsIdrAidsMapper.insert(amcsIdrAids);
			} else {
				amcsIdrAidsMapper.updateById(amcsIdrAids);
			}
		}

	}

	@Override
	public PageResponse<Map> getList(BasedCommonCondition d) {
		return baseFeignService.getList(d);
	}

	@Override
	public List<DcgDiag> getDcgDiagList(DcgDiagCondition d) {
		d.setHospId(UserContext.getTenantId());
		List<DcgDiag> m = baseFeignService.findByHospId(d);
		return m;
	}

	@Override
	public List<Map> getHospStaff(StaffCondition d) {
		// 去重复数据， 一人多岗时只显示一条数据
		d.setDistinct(true);
		// staffCondition.setStaffId(Long.valueOf(doctorId));
		// d.setInstId(UserContext.getInstId());
		d.setInvalidEnabled(true);
		return baseFeignService.getHospStaff(d);
	}

	@Override
	public AmcsIdrBaseInfo getByMainId(Long id) {
		AmcsIdrBaseInfo amcsIdrBaseInfo = amcsIdrBaseInfoMapper.selectById(id);
		if (amcsIdrBaseInfo.getIdrOccupationcode().length() == 1) {
			amcsIdrBaseInfo.setIdrOccupationcode("0" + amcsIdrBaseInfo.getIdrOccupationcode());
		}
		AmcsIdrAfp amcsIdrAfp = amcsIdrAfpMapper.selectByTid(id);
		AmcsIdrAids amcsIdrAids = amcsIdrAidsMapper.selectByTid(id);
		AmcsIdrHb amcsIdrHb = amcsIdrHbMapper.selectByTid(id);
		AmcsIdrHfmd amcsIdrHfmd = amcsIdrHfmdMapper.selectByTid(id);
		amcsIdrBaseInfo.setAmcsIdrAfp(amcsIdrAfp);
		amcsIdrBaseInfo.setAmcsIdrAids(amcsIdrAids);
		amcsIdrBaseInfo.setAmcsIdrHb(amcsIdrHb);
		amcsIdrBaseInfo.setAmcsIdrHfmd(amcsIdrHfmd);
		return amcsIdrBaseInfo;
	}

	@Override
	public List<AmcsIdrBaseInfo> getMainByCondForList(AmcsIdrBaseInfo apply) {

		List<AmcsIdrBaseInfo> amcsIdrBaseInfo = amcsIdrBaseInfoMapper.getMainByCondForList(apply);
		for (AmcsIdrBaseInfo x : amcsIdrBaseInfo) {
			x.setNationname(x.getStatus().toString());
			if (AuditStateEnum.isInclude("K" + x.getStatus())) {
				x.setNationname(AuditStateEnum.valueOf("K" + x.getStatus()).getEnumDesc());
			}

		}
		return amcsIdrBaseInfo;
	}

	/**
	 * 规则：定点医药机构编号(12)+时间(14)+ 顺序号(4) 时间格式yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String makeHospSerialNo(String currentHosp) {

		int num = in.incrementAndGet();

		if (num > 9999) {
			// 最大4位 超过则重置
			in.getAndSet(0);
			num = in.incrementAndGet();
		}
		String serialNo = String.format("%04d", num);
		return String.format("%s%s%s", currentHosp, DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"), serialNo);
	}

	public String makeHospSerialNo1() {
		int num = in.incrementAndGet();
		String currentHosp = getAmcsIdrDictType().getLeveles();
		if (num > 9999) {
			// 最大4位 超过则重置
			in.getAndSet(0);
			num = in.incrementAndGet();
		}
		String serialNo = String.format("%03d", num);
		return String.format("%s-%s-%s", currentHosp, DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"), serialNo);
	}

	@Override
	public AmcsIdrBaseInfo audit(AmcsIdrBaseInfo apply, Long loginUserId, String loginUserName) {
		AmcsIdrBaseInfo amcsIdrBaseInfo = auditByid1(apply.getId(), loginUserId, loginUserName);
		return amcsIdrBaseInfo;
	}

	@Override
	public String del(String id, Long loginUserId, String loginUserName) {
		String[] othS = id.split(",");
		for (String x : othS) {
			AmcsIdrBaseInfo apply = amcsIdrBaseInfoMapper.selectById(Long.parseLong(x));
			if (apply == null) {
				throw new BizException(id.toString() + " 记录不存在。");
			}
			if (apply.getStatus().equals(5) || apply.getStatus().equals(3)) {
				throw new BizException("患者姓名【" + apply.getPatientname() + "】,卡片ID【" + apply.getCardid() + "】已经 "
						+ (AuditStateEnum.valueOf("K" + apply.getStatus()).getEnumDesc()));
			}
//		amcsIdrBaseInfo.setStatus(4);
//		amcsIdrBaseInfo.setUploador(loginUserId);
//		amcsIdrBaseInfo.setUploadTime(new Date());
//		amcsIdrBaseInfo.setUploadName(loginUserName); 
			amcsIdrAidsMapper.delTid(apply.getId());
			amcsIdrAfpMapper.delTid(apply.getId());
			amcsIdrHfmdMapper.delTid(apply.getId());
			amcsIdrHbMapper.delTid(apply.getId());
			amcsIdrBaseInfoMapper.delId(apply.getId());
		}
		return "删除完成。";
	}

	@Override
	public String upload(String id, Long loginUserId, String loginUserName, OperateTypeEnum operateTypeEnum) {
		String[] othS = id.split(",");
		String bcall = "";

		for (String x : othS) {
			AmcsIdrBaseInfo amcsIdrBaseInfo = amcsIdrBaseInfoMapper.selectById(Long.parseLong(x));
			if (amcsIdrBaseInfo == null) {
				throw new BizException(id.toString() + " 记录不存在。");
			}

			try {
				AmcsIdrDictType apply = getAmcsIdrDictType();
				bcall = uploadOperateType(Long.parseLong(x), loginUserId, loginUserName, operateTypeEnum, apply);
			} catch (NumberFormatException e) {

				throw new BizException(e.toString());
			} catch (Exception e) {
				throw new BizException(e.toString());

			}

			// AuditStateEnum
			int iStatus = 5;
			switch (operateTypeEnum) {
			case KAdd:
				iStatus = Integer.parseInt(AuditStateEnum.K5.getEnumCode());
				break;
			case KMod:
				iStatus = Integer.parseInt(AuditStateEnum.K6.getEnumCode());
				break;
			case KAud:
				iStatus = Integer.parseInt(AuditStateEnum.K7.getEnumCode());
				break;
			case KDel:
				iStatus = Integer.parseInt(AuditStateEnum.K3.getEnumCode());
				break;
			case KRes:
				iStatus = Integer.parseInt(AuditStateEnum.K9.getEnumCode());
				break;
			case KSea:
				bcall = operateTypeEnum.getEnumDesc() + "成功";
				return bcall;
			}
			amcsIdrBaseInfo.setStatus(iStatus);
			amcsIdrBaseInfo.setUploador(loginUserId);
			amcsIdrBaseInfo.setUploadTime(new Date());
			amcsIdrBaseInfo.setUploadName(loginUserName);
			amcsIdrBaseInfo.setEventid(bcall);
			amcsIdrBaseInfoMapper.updateById(amcsIdrBaseInfo);
		}
		bcall = operateTypeEnum.getEnumDesc() + "成功";
		return bcall;
	}

	private AmcsIdrDictType getAmcsIdrDictType() {
		AmcsIdrDictType apply = new AmcsIdrDictType();
		apply.setUsingSign(1);
		apply.setTypeCode(AmcsIdrDictEnum.Ktoken.getEnumCode());
		List<AmcsIdrDictType> applyDict = amcsIdrDictTypeService.getAmcsIdrDictType(apply);
		if (applyDict != null && applyDict.size() > 0) {
			apply = applyDict.get(0);

		}
		return apply;
	}

	/**
	 * 组织上传代码
	 * 
	 * @param id
	 * @param loginUserId
	 * @param loginUserName
	 * @param valueOf
	 * @throws Exception
	 */
	private String uploadOperateType(Long id, Long loginUserId, String loginUserName, OperateTypeEnum valueOf,
			AmcsIdrDictType apply) throws Exception {
		Map<String, Object> DataExchange = new LinkedHashMap<String, Object>();
		AmcsIdrBaseInfo amcsIdrBaseInfo = amcsIdrBaseInfoMapper.selectById(id);
		if (amcsIdrBaseInfo == null || amcsIdrBaseInfo.getStatus().equals(4)) {
			throw new BizException(id.toString() + " 记录不存在。");
		}
		Map<String, Object> eventHead = new LinkedHashMap<String, Object>();
		// <!—文件ID（9位机构编码+年月日时分秒+3位流水号）
		if (amcsIdrBaseInfo.getEventid() == null || amcsIdrBaseInfo.getEventid().length() < 1) {
			amcsIdrBaseInfo.setEventid(makeHospSerialNo1());
		}
		List<Map<String, Object>> param = new ArrayList();
		if (valueOf.equals(OperateTypeEnum.KSea) == false) {
			eventHead.put("eventId", NNZ(amcsIdrBaseInfo.getEventid()));
//		  <!—交换公用的传染病IDR、慢病数据NCD、死因CODRIS数据 
			eventHead.put("entityName", NNZ(amcsIdrBaseInfo.getKey1()));

			// <!operateType—Add:初报、Mod:修订、Aud:审核、Del:删除、Res:恢复
			eventHead.put("operateType", valueOf == OperateTypeEnum.KSea ? "Add" : valueOf.getEnumCode());
			DataExchange.put("eventHead", eventHead);
			// 设置一些常量
			amcsIdrBaseInfo.setOrgcode(apply.getLeveles());
			amcsIdrBaseInfo.setOrgname(apply.getValueCode());
			amcsIdrBaseInfo.setCustomer(apply.getTypeDesc());
			Map<String, Object> eventBody = getbaseInfoNew(amcsIdrBaseInfo, valueOf);

			DataExchange.put("eventBody", eventBody);
			Map<String, Object> mDataExchange = new LinkedHashMap<String, Object>();
			mDataExchange.put("DataExchange", DataExchange);

			param.add(mDataExchange);
		}
		String bcall = sdk.call(valueOf == OperateTypeEnum.KSea ? "getChinaXml" : "transferDataToDmp", param, apply,
				amcsIdrBaseInfo.getEventid());
		bcall = amcsIdrBaseInfo.getEventid();
		return bcall;
	}

	@Override
	public String auditById(String id, Long loginUserId, String loginUserName) {
		String[] othS = id.split(",");
		for (String x : othS) {
			auditByid1(Long.parseLong(x), loginUserId, loginUserName);
		}
		return "审核完成";
	}

	@Override
	public String auditUn(String id, Long loginUserId, String loginUserName) {
		String[] othS = id.split(",");
		String iId = "";
		for (String x : othS) {
			AmcsIdrBaseInfo amcsIdrBaseInfo = amcsIdrBaseInfoMapper.selectById(Long.parseLong(x));
			if (amcsIdrBaseInfo.getStatus().equals(3)) {
				amcsIdrBaseInfo.setStatus(0);
				amcsIdrBaseInfo.setAuditor(null);
				amcsIdrBaseInfo.setAuditTime(null);
				amcsIdrBaseInfo.setAuditorName(null);
				amcsIdrBaseInfoMapper.updateById(amcsIdrBaseInfo);
			} else {
				throw new BizException(
						"患者姓名【" + amcsIdrBaseInfo.getPatientname() + "】,卡片ID【" + amcsIdrBaseInfo.getCardid() + "】已经 "
								+ (AuditStateEnum.valueOf("K" + amcsIdrBaseInfo.getStatus()).getEnumDesc()));
			}

			iId = "反审核完成";
		}
		return iId;
	}

	private AmcsIdrBaseInfo auditByid1(Long id, Long loginUserId, String loginUserName) {
		AmcsIdrBaseInfo amcsIdrBaseInfo = amcsIdrBaseInfoMapper.selectById(id);
		if (!amcsIdrBaseInfo.getStatus().equals(0)) {
			throw new BizException("患者姓名【" + amcsIdrBaseInfo.getPatientname() + "】,卡片ID【" + amcsIdrBaseInfo.getCardid()
					+ "】已经 " + (AuditStateEnum.valueOf("K" + amcsIdrBaseInfo.getStatus()).getEnumDesc()));
		}
		amcsIdrBaseInfo.setStatus(3);
		amcsIdrBaseInfo.setAuditor(loginUserId);
		amcsIdrBaseInfo.setAuditTime(new Date());
		amcsIdrBaseInfo.setAuditorName(loginUserName);
		amcsIdrBaseInfoMapper.updateById(amcsIdrBaseInfo);
		return amcsIdrBaseInfo;
	}

	private Map<String, Object> getbaseInfoNew(AmcsIdrBaseInfo amcsIdrBaseInfo, OperateTypeEnum valueOf) {
		Map<String, Object> eventBody = new LinkedHashMap();
		Map<String, Object> baseInfo = new LinkedHashMap();
		String iCode = amcsIdrBaseInfo.getOrgcode().substring(6, 9);// "000";//
		baseInfo.put("PatientName", NNZ(amcsIdrBaseInfo.getPatientname()));// 刘邦会");// PatientName", amcsIdrBaseInfo);//
		baseInfo.put("BirthDate", transDateStr(amcsIdrBaseInfo.getBirthdate(), DateFORMAT));// 1972-10-06");//
																							// BirthDate",
																							// amcsIdrBaseInfo);//
		baseInfo.put("GenderCode", NNZ(amcsIdrBaseInfo.getGendercode()));// 2");// GenderCode", amcsIdrBaseInfo);//
		baseInfo.put("GenderName", NNZ(amcsIdrBaseInfo.getGendername()));// 女");// GenderName", amcsIdrBaseInfo);//
		baseInfo.put("NationCode", NNZ(amcsIdrBaseInfo.getNationcode()));// ");// NationCode", amcsIdrBaseInfo);//
		baseInfo.put("NationName", NNZ(amcsIdrBaseInfo.getNationname()));// ");// NationName", amcsIdrBaseInfo);//
		baseInfo.put("EmployerOrgName", NNZ(amcsIdrBaseInfo.getEmployerorgname()));// 爱尔眼科");// EmployerOrgName",

		String igetIdrOccupationcode = (String) NNZ(amcsIdrBaseInfo.getIdrOccupationcode()).toString();
		if (igetIdrOccupationcode.length() == 1) {
			igetIdrOccupationcode = "0" + igetIdrOccupationcode;
		}
		baseInfo.put("IDR_OccupationCode", igetIdrOccupationcode);// 人群分类编码

		baseInfo.put("OccupationName", NNZ(amcsIdrBaseInfo.getOccupationname()));// 医务人员");// OccupationName",

		baseInfo.put("OtherOccupationName", NNZ(amcsIdrBaseInfo.getOtheroccupationname()));// ");//
																							// OtherOccupationName",

		baseInfo.put("GuardianName", NNZ(amcsIdrBaseInfo.getGuardianname()));// ");// GuardianName", amcsIdrBaseInfo);//
		baseInfo.put("TeleCom", NNZ(amcsIdrBaseInfo.getTelecom()));// ");// TeleCom", amcsIdrBaseInfo);//
		baseInfo.put("OrgCountyCode", NNZ(amcsIdrBaseInfo.getOrgcountycode() + "000"));// OrgCountyCode",
																						// amcsIdrBaseInfo);//
		baseInfo.put("OrgCountyName", NNZ(amcsIdrBaseInfo.getOrgcountyname()));// OrgCountyName", amcsIdrBaseInfo);//
		baseInfo.put("OrgCode", NNZ(amcsIdrBaseInfo.getOrgcode()));// OrgCode", amcsIdrBaseInfo);//
		baseInfo.put("OrgName", NNZ(amcsIdrBaseInfo.getOrgname()));// OrgName", amcsIdrBaseInfo);//
		baseInfo.put("LivingAddressAttributionCode", NNZ(amcsIdrBaseInfo.getLivingaddressattributioncode()));// 01");//
		baseInfo.put("LivingAddressAttributionName", NNZ(amcsIdrBaseInfo.getLivingaddressattributionname()));// 本县区");//

		String ivingaddresscode = amcsIdrBaseInfo.getLivingaddresscode();

		baseInfo.put("LivingAddressCode", ivingaddresscode);// 52038206"); LivingAddressCode现住地址编码

		baseInfo.put("LivingAddressName", NNZ(amcsIdrBaseInfo.getLivingaddressname()));// amcsIdrBaseInfo.getLivingaddressname());//
		baseInfo.put("LivingAddressDetails", NNZ(amcsIdrBaseInfo.getLivingaddressdetails()));// 爱尔眼科");//
		baseInfo.put("IDCardType", NNZ(amcsIdrBaseInfo.getIdcardtype()));// 1");// IDCardType", amcsIdrBaseInfo);//
		baseInfo.put("IDCardCode", NNZ(amcsIdrBaseInfo.getIdcardcode()));// 510523197210063063");// IDCardCode",
		baseInfo.put("DiagnosisDate", IDRhelper.transDateStr(amcsIdrBaseInfo.getDiagnosisdate(), IDRhelper.GJ_DATE));// 2023-06-10
																														// 00");//
																														// DiagnosisDate",
		String iseasecode = amcsIdrBaseInfo.getDiseasecode();
		String iseasecode1 = amcsIdrBaseInfo.getDiseasecode();
		if (amcsIdrBaseInfo.getDiseasecode() != null) {
			AmcsIdrDictType apply = new AmcsIdrDictType();
			apply.setId(Long.parseLong(amcsIdrBaseInfo.getDiseasecode()));

			List<AmcsIdrDictType> amcsIdrDictType = amcsIdrDictTypeService.getTypeCodeForList(apply);

			if (amcsIdrDictType != null) {
				iseasecode = amcsIdrDictType.get(0).getValueRemark();
				if (iseasecode == null) {
					throw new BizException("请先选择好可以上传的疾病诊断！");
				} else if (iseasecode.length() < 4) {
					iseasecode = "0" + iseasecode;
				}
				iseasecode1 = amcsIdrDictType.get(0).getValueCode();
				if (iseasecode1 != null) {
					iseasecode1 = iseasecode1.split(" ")[0];
				}
			}
		}
		baseInfo.put("DiseaseCode", iseasecode);// 疾病病种编码
		baseInfo.put("DiseaseCause", iseasecode1);// 疾病病种编码

		baseInfo.put("DiseaseName", NNZ(amcsIdrBaseInfo.getDiseasename()));// 丙肝");// DiseaseName", amcsIdrBaseInfo);//
		baseInfo.put("CardNotes", NNZ(amcsIdrBaseInfo.getCardnotes()));// ");// CardNotes", amcsIdrBaseInfo);//
		baseInfo.put("DeathDate", NNZ(amcsIdrBaseInfo.getDeathdate()));// ");// DeathDate", amcsIdrBaseInfo);//
		baseInfo.put("FillingDoctorName", NNZ(amcsIdrBaseInfo.getFillingdoctorname()));// 李建军");// FillingDoctorName",

		baseInfo.put("CardFillingTime",
				IDRhelper.transDateStr(amcsIdrBaseInfo.getCardfillingtime(), IDRhelper.GJ_DATE1));// 2023-06-11");//
																									// CardFillingTime",

		baseInfo.put("DomicileAddressCode", NNZ(amcsIdrBaseInfo.getDomicileaddresscode()));// ");//
		if (baseInfo.get("DomicileAddressCode") != "") {
			baseInfo.put("DomicileAddressCode", baseInfo.get("DomicileAddressCode") + "000");
		} // DomicileAddressCode",

		baseInfo.put("DomicileAddressName", NNZ(amcsIdrBaseInfo.getDomicileaddressname()));// ");//
																							// DomicileAddressName",

		baseInfo.put("DomicileAdrressDetails", NNZ(amcsIdrBaseInfo.getDomicileadrressdetails()));// ");//
		// DomicileAdrressDetails",

		baseInfo.put("DomicileAddressAttributionCode", NNZ(amcsIdrBaseInfo.getDomicileaddressattributioncode()));// ");//

		// DomicileAddressAttributionCode",

		baseInfo.put("DomicileAddressAttributionName", NNZ(amcsIdrBaseInfo.getDomicileaddressattributionname()));// ");//
		// DomicileAddressAttributionName",

		baseInfo.put("Country", NNZ(amcsIdrBaseInfo.getCountry(), "中国大陆"));// );// Country", amcsIdrBaseInfo);//
		baseInfo.put("DiagnosisTypeCode", NNZ(amcsIdrBaseInfo.getDiagnosistypecode()));// 1");// DiagnosisTypeCode",

		baseInfo.put("DiagnosisTypeName", NNZ(amcsIdrBaseInfo.getDiagnosistypename()));// 临床诊断病例");//
																						// DiagnosisTypeName",

		if (valueOf.equals(OperateTypeEnum.KAdd)) {
			baseInfo.put("CardID", null);// ");// CardID", amcsIdrBaseInfo);//

		} else {
			baseInfo.put("CardID", NNZ(amcsIdrBaseInfo.getEventid()));// ");// CardID", amcsIdrBaseInfo);//

		}
		baseInfo.put("CreatingTime", IDRhelper.transDateStr(amcsIdrBaseInfo.getCreateDate(), IDRhelper.GJ_DATE2));// 2023-06-10
																													// 11:24:56");//
																													// CreatingTime",

		baseInfo.put("MaritalStatusCode", NNZ(amcsIdrBaseInfo.getMaritalstatuscode()));// ");// MaritalStatusCode",

		baseInfo.put("MaritalStatusName", NNZ(amcsIdrBaseInfo.getMaritalstatusname()));// ");// MaritalStatusName",

		baseInfo.put("EducationLevelCode", NNZ(amcsIdrBaseInfo.getEducationlevelcode()));// ");// EducationLevelCode",

		baseInfo.put("EducationLevelName", NNZ(amcsIdrBaseInfo.getEducationlevelname()));// ");// EducationLevelName",
		// 24.12.4新增的字段
		baseInfo.put("Id", baseInfo.get("CardID"));// "");// ICR01.01.160数据IDN..40数据ID操作类型为新增时该字段必须为空，操作类型不为新增时该字段必填。
		baseInfo.put("CardCode", baseInfo.get("CardID"));// "");//
															// ICR01.01.161卡片编号AN..40卡片编号操作类型为新增时该字段必须为空，操作类型不为新增时该字段必填。
		baseInfo.put("TreatTypeCode", "");// ICR01.01.1621病例来源N1病例来源疾病诊断为人呼吸道合胞病毒感染时填写，必填，取值为：“1=门急诊，2=住院”值域范围。
		baseInfo.put("DepartmentCode", "");// ICR01.01.163科室N1科室疾病诊断为人呼吸道合胞病毒感染时填写，必填，取值为附录7.52《附录：科室代码表》值域范围。14岁（不含）以上不能选儿内科。
		baseInfo.put("DepartmentOther", "");// ICR01.01.164科室-其他AN..100科室-其他疾病诊断为人呼吸道合胞病毒感染并且科室选择其他时填写，必填，最长为50个字。
		baseInfo.put("ClinicalCodeList", "");// ICR01.01.165临床表现临床表现临床表现集合
		baseInfo.put("ClinicalCode", "");// ICR01.01.166临床表现N1临床表现疾病诊断为人呼吸道合胞病毒感染时填写，必填，取值为附录7.53《附录：临床表现代码表》值域范围
		baseInfo.put("ClinicalOther", "");// ICR01.01.167临床表现-其他AN..100临床表现-其他疾病诊断为人呼吸道合胞病毒感染并且临床表现选择其他时填写，必填，最长为50个字。
		baseInfo.put("FeverNum", "");// ICR01.01.168发热体温N..4发热体温疾病诊断为人呼吸道合胞病毒感染并且临床表现选择发热时填写，必填，体温范围是大于等于35小于等于45，小数点后最多一位数字。
		baseInfo.put("IsBabyApneaCode", "");// ICR01.01.169婴儿是否有呼吸暂停 N1婴儿是否有呼吸暂停
											// 疾病诊断为人呼吸道合胞病毒感染时且患者年龄在0-6月时填写，必填，取值为：“1=是、2=否”值域范围
		baseInfo.put("IsBabyShockCode", "");// ICR01.01.170婴儿是否有脓毒症/休克 N1婴儿是否有脓毒症/休克
											// 疾病诊断为人呼吸道合胞病毒感染时且患者年龄在0-6月时填写，必填，取值为：“1=是、2=否”值域范围
		baseInfo.put("DiagnoseCodeList", "");// ICR01.01.171初步诊断初步诊断初步诊断集合
		baseInfo.put("DiagnoseCode", "");// ICR01.01.172初步诊断N1初步诊断疾病诊断为人呼吸道合胞病毒感染时填写，必填，取值为附录7.54《附录：初步诊断代码表》值域范围
		baseInfo.put("DiagnoseOther", "");// ICR01.01.173初步诊断-其他AN..100初步诊断-其他疾病诊断为人呼吸道合胞病毒感染时且初步诊断为其他时填写，必填，最长为50个字。
		baseInfo.put("CODRIS_OccupationCode", "");// 死者生前职业编码
		baseInfo.put("NCD_OccupationCode", "");// 慢病职业编码
		baseInfo.put("NCD_OccupationCode", "");// 慢病职业编码
		baseInfo.put("DeathPlaceCode", "");// 慢病职业编码
		baseInfo.put("DeathPlaceCode", NNZ(amcsIdrBaseInfo.getDeathplacecode()));// 死亡地点代码
		baseInfo.put("DeathPlaceCodeName", NNZ(amcsIdrBaseInfo.getDeathplacecodename()));// 死亡地点代码
		baseInfo.put("patientnum", NNZ(amcsIdrBaseInfo.getPatientnum()));// 门诊号
		baseInfo.put("HospitalNum", NNZ(amcsIdrBaseInfo.getHospitalnum()));// 住院号
		baseInfo.put("LifeTimeAddr", NNZ(amcsIdrBaseInfo.getLifetimeaddr()));// 死者生前住址
		baseInfo.put("LifeTimeAddrTypeCode", NNZ(amcsIdrBaseInfo.getLifetimeaddrtypecode()));// 死者生前住址
		baseInfo.put("LifeTimeAddrTypeCodeName", NNZ(amcsIdrBaseInfo.getLifetimeaddrtypecodename()));// 死者生前住址
		baseInfo.put("LifeTimeVillageCode", NNZ(amcsIdrBaseInfo.getLifetimevillagecode()));// 死者生前住址
		baseInfo.put("LifeTimeVillageCodeName", NNZ(amcsIdrBaseInfo.getLifetimevillagecodename()));// 死者生前住址
		baseInfo.put("LifeTimeZoneCode", NNZ(amcsIdrBaseInfo.getLifetimezonecode()));// 死者生前住址
		baseInfo.put("LifeTimeZoneCodeName", NNZ(amcsIdrBaseInfo.getLifetimezonecodename()));// 死者生前住址
		baseInfo.put("RegisterVillageCode", NNZ(amcsIdrBaseInfo.getRegistervillagecode()));// 死者生前住址
		baseInfo.put("RegisterVillageCodeName", NNZ(amcsIdrBaseInfo.getRegistervillagecodename()));// 死者生前住址

		eventBody.put("BaseInfo", baseInfo);
		Map<String, Object> baseInfo1 = new LinkedHashMap();
		baseInfo1.put("CardID", baseInfo.get("CardID"));// ");// MaritalStatusCode",

		baseInfo1.put("EducationLevelCode", NNZ(amcsIdrBaseInfo.getEducationlevelcode()));// ");// EducationLevelCode",

		baseInfo1.put("EducationLevelName", NNZ(amcsIdrBaseInfo.getEducationlevelname()));// ");// EducationLevelName",

		baseInfo1.put("CaseClassificationCode", NNZ(amcsIdrBaseInfo.getCaseclassificationcode()));// 3");//

		baseInfo1.put("CaseClassificationName", NNZ(amcsIdrBaseInfo.getCaseclassificationname()));// 未分型");//

		baseInfo1.put("OtherDiseaseName", NNZ(amcsIdrBaseInfo.getOtherdiseasename()));// ");// OtherDiseaseName",

		baseInfo1.put("OnsetDate", IDRhelper.transDateStr(amcsIdrBaseInfo.getOnsetdate(), IDRhelper.GJ_DATE1));// 2023-06-10");//
																												// OnsetDate",

		baseInfo1.put("CloseContactsSymptomCode", NNZ(amcsIdrBaseInfo.getClosecontactssymptomcode()));// 1");//

		baseInfo1.put("CloseContactsSymptomName", NNZ(amcsIdrBaseInfo.getClosecontactssymptomname()));// 有");//
		// CloseContactsSymptomName",

		baseInfo1.put("Customer", NNZ(amcsIdrBaseInfo.getCustomer()));// 511523198712272872");// Customer",

		baseInfo1.put("NewPneumSeverityCode", NNZ(amcsIdrBaseInfo.getNewpneumseveritycode()));// ");//
																								// NewPneumSeverityCode",

		baseInfo1.put("NewPneumSeverityName", NNZ(amcsIdrBaseInfo.getNewpneumseverityname()));// ");//
																								// NewPneumSeverityName",

		baseInfo1.put("ForeignTypeCode", NNZ(amcsIdrBaseInfo.getForeigntypecode()));// ");// ForeignTypeCode",
		if (YesNoEnum.isInclude("K" + amcsIdrBaseInfo.getLivingaddressattributioncode())) {
			baseInfo1.put("ForeignTypeName",
					YesNoEnum.valueOf("K" + amcsIdrBaseInfo.getLivingaddressattributioncode()).getEnumDesc());// ");//
		} // ForeignTypeName",

		baseInfo1.put("MgmtStatusCode", NNZ(amcsIdrBaseInfo.getMgmtstatuscode()));// ");// MgmtStatusCode",

		baseInfo1.put("IsDeadByThisCode", NNZ(amcsIdrBaseInfo.getIsdeadbythiscode()));// ");// IsDeadByThisCode",

		baseInfo1.put("SymptomsCode", NNZ(amcsIdrBaseInfo.getSymptomscode()));// ");// SymptomsCode",
																				// amcsIdrBaseInfo);//
//新增字段
		baseInfo1.put("CurrmgmtOrgCode", NNZ(amcsIdrBaseInfo.getCurrmgmtorgcode()));// ");// SymptomsCode",
		baseInfo1.put("DeletingReasonDetails", NNZ(amcsIdrBaseInfo.getDeletingreasondetails()));// ");// SymptomsCode",
		baseInfo1.put("DeletingTypeCode", NNZ(amcsIdrBaseInfo.getDeletingtypecode()));// ");// SymptomsCode",
		baseInfo1.put("DeletingTypeName", NNZ(amcsIdrBaseInfo.getDeletingtypename()));// ");// SymptomsCode",
		baseInfo1.put("NcvNCtv", NNZ(amcsIdrBaseInfo.getNcvnctv()));// ");// SymptomsCode",
		baseInfo1.put("NcvOrfCtv", NNZ(amcsIdrBaseInfo.getNcvorfctv()));// ");// SymptomsCode",
		baseInfo1.put("OutHosDate", NNZ(amcsIdrBaseInfo.getOuthosdate()));// ");// SymptomsCode",
		baseInfo1.put("PlaceCode", NNZ(amcsIdrBaseInfo.getPlacecode()));// ");// SymptomsCode",
		baseInfo1.put("PlaceOther", NNZ(amcsIdrBaseInfo.getPlaceother()));// ");// SymptomsCode",

		Map<String, Object> baseInfoAIDS = new LinkedHashMap();
		AmcsIdrAids amcsIdrAids = amcsIdrAidsMapper.selectByTid(amcsIdrBaseInfo.getId());
		if (amcsIdrAids == null) {
			baseInfoAIDS.put("AIDSCardID", "");// AIDSCardID","");//
			baseInfoAIDS.put("ContactHistoryCode", "");// ");// ContactHistoryCode","");//
			baseInfoAIDS.put("ContactHistoryName", "");// ");// ContactHistoryName","");//
			baseInfoAIDS.put("InjectionTogetherNum", "");// ");// InjectionTogetherNum","");//
			baseInfoAIDS.put("NonmaritalSexNum", "");// ");// NonmaritalSexNum","");//
			baseInfoAIDS.put("HomosexualSexNum", "");// ");// HomosexualSexNum","");//
			baseInfoAIDS.put("OtherContactHistory", "");// ");// OtherContactHistory","");//
			baseInfoAIDS.put("VenerealHistoryCode", "");// ");// VenerealHistoryCode","");//
			baseInfoAIDS.put("VenerealHistoryName", "");// ");// VenerealHistoryName","");//
			baseInfoAIDS.put("PossibleInfectionRouteCode", "");// ");// PossibleInfectionRouteCode","");//
			baseInfoAIDS.put("PossibleInfectionRouteName", "");// ");// PossibleInfectionRouteName","");//
			baseInfoAIDS.put("OtherInfectionRoute", "");// ");// OtherInfectionRoute","");//
			baseInfoAIDS.put("SpecimenSourceCode", "");// ");// SpecimenSourceCode","");//
			baseInfoAIDS.put("SpecimenSourceName", "");// ");// SpecimenSourceName","");//
			baseInfoAIDS.put("OtherSampleSource", "");// ");// OtherSampleSource","");//
			baseInfoAIDS.put("LaborTestConclusionCode", "");// ");// LaborTestConclusionCode","");//
			baseInfoAIDS.put("LaborTestConclusionName", "");// ");// LaborTestConclusionName","");//
			baseInfoAIDS.put("ConfirmedTestPositiveDate", "");// ");// ConfirmedTestPositiveDate","");//
			baseInfoAIDS.put("ConfirmedTestPositiveOrgName", "");// ");// ConfirmedTestPositiveOrgName","");//
			baseInfoAIDS.put("AIDSDiagnosisDate", "");// ");// AIDSDiagnosisDate","");//
			baseInfoAIDS.put("ChlamydialTrachomatisCode", "");// ");// ChlamydialTrachomatisCode","");//
			baseInfoAIDS.put("ChlamydialTrachomatisName", "");// ");// ChlamydialTrachomatisName","");//
		} else {
			baseInfoAIDS.put("AIDSCardID", baseInfo.get("CardID"));// amcsIdrAids.getAidscardid())");// AIDSCardID",
																	// amcsIdrBaseInfo);//
			baseInfoAIDS.put("ContactHistoryCode", amcsIdrAids.getContacthistorycode());// ");// ContactHistoryCode"
			baseInfoAIDS.put("ContactHistoryName", amcsIdrAids.getContacthistoryname());// ");// ContactHistoryName"
			baseInfoAIDS.put("InjectionTogetherNum", amcsIdrAids.getInjectiontogethernum());// ");//
																							// InjectionTogetherNum"
			baseInfoAIDS.put("NonmaritalSexNum", amcsIdrAids.getNonmaritalsexnum());// ");// NonmaritalSexNum"
			baseInfoAIDS.put("HomosexualSexNum", amcsIdrAids.getHomosexualsexnum());// ");// HomosexualSexNum"
			baseInfoAIDS.put("OtherContactHistory", amcsIdrAids.getOthercontacthistory());// ");// OtherContactHistory"
			baseInfoAIDS.put("VenerealHistoryCode", amcsIdrAids.getVenerealhistorycode());// ");// VenerealHistoryCode"
			baseInfoAIDS.put("VenerealHistoryName", amcsIdrAids.getVenerealhistoryname());// ");// VenerealHistoryName"
			baseInfoAIDS.put("PossibleInfectionRouteCode", amcsIdrAids.getPossibleinfectionroutecode());// ");//
																										// PossibleInfectionRouteCode"
			baseInfoAIDS.put("PossibleInfectionRouteName", amcsIdrAids.getPossibleinfectionroutename());// ");//
																										// PossibleInfectionRouteName"
			baseInfoAIDS.put("OtherInfectionRoute", amcsIdrAids.getOtherinfectionroute());// ");// OtherInfectionRoute"
			baseInfoAIDS.put("SpecimenSourceCode", amcsIdrAids.getSpecimensourcecode());// ");// SpecimenSourceCode"
			baseInfoAIDS.put("SpecimenSourceName", amcsIdrAids.getSpecimensourcename());// ");// SpecimenSourceName"
			baseInfoAIDS.put("OtherSampleSource", amcsIdrAids.getOthersamplesource());// ");// OtherSampleSource"
			baseInfoAIDS.put("LaborTestConclusionCode", amcsIdrAids.getLabortestconclusioncode());// ");//
																									// LaborTestConclusionCode"
			baseInfoAIDS.put("LaborTestConclusionName", amcsIdrAids.getLabortestconclusionname());// ");//
																									// LaborTestConclusionName"
			baseInfoAIDS.put("ConfirmedTestPositiveDate",
					IDRhelper.transDateStr(amcsIdrAids.getConfirmedtestpositivedate(), IDRhelper.GJ_DATE1));// ");//
																											// ConfirmedTestPositiveDate"
			baseInfoAIDS.put("ConfirmedTestPositiveOrgName", "");// ");//
																	// ConfirmedTestPositiveOrgName"
			baseInfoAIDS.put("AIDSDiagnosisDate",
					IDRhelper.transDateStr(amcsIdrAids.getAidsdiagnosisdate(), IDRhelper.GJ_DATE1));// ");//
																									// AIDSDiagnosisDate"
			baseInfoAIDS.put("ChlamydialTrachomatisCode", amcsIdrAids.getChlamydialtrachomatiscode());// ");//
																										// ChlamydialTrachomatisCode"
			baseInfoAIDS.put("ChlamydialTrachomatisName", amcsIdrAids.getChlamydialtrachomatisname());// ");//
																										// ChlamydialTrachomatisName"

		}
		baseInfo1.put("AIDS", baseInfoAIDS);
		baseInfoAIDS = new LinkedHashMap();
		AmcsIdrHfmd amcsIdrHfmd = amcsIdrHfmdMapper.selectByTid(amcsIdrBaseInfo.getId());
		if (amcsIdrHfmd == null) {
			baseInfoAIDS.put("HFMDCardID", "");// HFMDCardID", amcsIdrBaseInfo);//
			baseInfoAIDS.put("IntensivePatientCode", "");// IntensivePatientCode", amcsIdrBaseInfo);//
			baseInfoAIDS.put("IntensivePatientName", "");// IntensivePatientName", amcsIdrBaseInfo);//
			baseInfoAIDS.put("LaborTestResultCode", "");// LaborTestResultCode", amcsIdrBaseInfo);//
			baseInfoAIDS.put("LaborTestResultName", "");// LaborTestResultName", amcsIdrBaseInfo);//
		} else {
			baseInfoAIDS.put("HFMDCardID", baseInfo.get("CardID"));// amcsIdrHfmd.getHfmdcardid());// ");// HFMDCardID"
			baseInfoAIDS.put("IntensivePatientCode", amcsIdrHfmd.getIntensivepatientcode());
			baseInfoAIDS.put("IntensivePatientName", amcsIdrHfmd.getIntensivepatientname());
			baseInfoAIDS.put("LaborTestResultCode", amcsIdrHfmd.getLabortestresultcode());// ");// LaborTestResultCode"
			baseInfoAIDS.put("LaborTestResultName", amcsIdrHfmd.getLabortestresultname());// ");// LaborTestResultName"

		}
		baseInfo1.put("HFMD", baseInfoAIDS);
		baseInfoAIDS = new LinkedHashMap();
		AmcsIdrHb amcsIdrHb = amcsIdrHbMapper.selectByTid(amcsIdrBaseInfo.getId());
		if (amcsIdrHb == null) {
			baseInfoAIDS.put("HBCardID", "");// ");// HBCardID", "");//
			baseInfoAIDS.put("HBsAgTestPositiveTypeCode", "");// ");// HBsAgTestPositiveTypeCode",
			baseInfoAIDS.put("HBsAgTestPositiveTypeName", "");// ");// HBsAgTestPositiveTypeName",
			baseInfoAIDS.put("FirstHBSymptomTime", "");// ");// FirstHBSymptomTime", "");//
			baseInfoAIDS.put("LastAltValue", "");// ");// LastAltValue", "");//
			baseInfoAIDS.put("HBcAbIgMTestCode", "");// ");// HBcAbIgMTestCode", "");//
			baseInfoAIDS.put("HBcAbIgMTestName", "");// ");// HBcAbIgMTestName", "");//
			baseInfoAIDS.put("LiveRpunctureTestCode", "");// ");// LiveRpunctureTestCode", "");//
			baseInfoAIDS.put("LiveRpunctureTestName", "");// ");// LiveRpunctureTestName", "");//
			baseInfoAIDS.put("HBsAgNorHBsAbpCode", "");// ");// HBsAgNorHBsAbpCode", "");//
			baseInfoAIDS.put("HBsAgNorHBsAbpName", "");// ");// HBsAgNorHBsAbpName", "");//
			baseInfoAIDS.put("FirstHBSymptomUnsureCode", "");// ");// FirstHBSymptomUnsureCode",
		} else {
			baseInfoAIDS.put("HBCardID", baseInfo.get("CardID"));// amcsIdrHb.getHbcardid());// ");// HBCardID",
																	// amcsIdrHb);//
			baseInfoAIDS.put("HBsAgTestPositiveTypeCode", NNZ(amcsIdrHb.getHbsagtestpositivetypecode()));// ");//
			// HBsAgTestPositiveTypeCode",
			baseInfoAIDS.put("HBsAgTestPositiveTypeName", NNZ(amcsIdrHb.getHbsagtestpositivetypename()));// ");//
			// HBsAgTestPositiveTypeName",
			baseInfoAIDS.put("FirstHBSymptomTime",
					IDRhelper.transDateStr(amcsIdrHb.getFirsthbsymptomtime(), IDRhelper.GJ_DATE1));// ");//
																									// FirstHBSymptomTime",

			baseInfoAIDS.put("LastAltValue", NNZ(amcsIdrHb.getLastaltvalue()));// ");// LastAltValue", amcsIdrHb);//
			baseInfoAIDS.put("HBcAbIgMTestCode", NNZ(amcsIdrHb.getHbcabigmtestcode()));// ");// HBcAbIgMTestCode",

			baseInfoAIDS.put("HBcAbIgMTestName", NNZ(amcsIdrHb.getHbcabigmtestname()));// ");// HBcAbIgMTestName",

			baseInfoAIDS.put("LiveRpunctureTestCode", NNZ(amcsIdrHb.getLiverpuncturetestcode()));// ");//
			// LiveRpunctureTestCode",

			baseInfoAIDS.put("LiveRpunctureTestName", NNZ(amcsIdrHb.getLiverpuncturetestname()));// ");//
			// LiveRpunctureTestName",

			baseInfoAIDS.put("HBsAgNorHBsAbpCode", NNZ(amcsIdrHb.getHbsagnorhbsabpcode()));// ");// HBsAgNorHBsAbpCode",

			baseInfoAIDS.put("HBsAgNorHBsAbpName", NNZ(amcsIdrHb.getHbsagnorhbsabpname()));// ");// HBsAgNorHBsAbpName",

			baseInfoAIDS.put("FirstHBSymptomUnsureCode", NNZ(amcsIdrHb.getFirsthbsymptomunsurecode(), "N"));// ");//
			// FirstHBSymptomUnsureCode",

		}

		baseInfo1.put("HB", baseInfoAIDS);
		eventBody.put("IDRCard", baseInfo1);
		return eventBody;
	}

	private Object NNZ(Object country) {

		return NNZ(country, "");

	}

	private Object NNZ(Object country, String de) {
		if (country == null) {
			return de;
		} else {
			return country;
		}

	}

	static final String DateFORMAT = "yyyy-MM-dd";

	public static String transDateStr(Date dt, String Formate) {
		return DateFormatUtils.format(dt, Formate);
	}
}

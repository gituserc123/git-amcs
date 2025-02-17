package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *<!—Add:初报、Mod:修订、Aud:审核、Del:删除、Res:恢复
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum OperateTypeEnum implements EnumDict<OperateTypeEnum> {
	
	
	KAdd("Add", "上传初报"),  
	KMod("Edit", "上传修订"),
	KAud("Aud", "上传审核"),  
	KDel("Del", "上传删除"),   
	KRes("Res", "上传恢复"),
	KSea("Sea", "上传查询");

	private String code;
	private String value;

	private OperateTypeEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public String getEnumCode() {
		return code;
	}

	@Override
	public String getEnumDesc() {
		return value;
	}

	public static boolean isInclude(String key) {
		boolean include = false;
		for (OperateTypeEnum e : OperateTypeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}

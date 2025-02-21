package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDictType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 法务字典表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawDictTypeService extends IService<LawDictType> {

    /**
     * 根据字典类型编码查询字典列表
     *
     * @param typeCode 字典类型编码
     * @return 字典列表
     */
    List<LawDictType> selectByTypeCode(String typeCode);

    /**
     * 查询子字典列表
     *
     * @param typeCode
     * @return 子字典列表
     */
    List<LawDictType> selectSubDicts(String typeCode, String valueCode);

    /**
     * 根据参数值编码查询字典信息
     *
     * @param valueCode 参数值编码
     * @return 字典信息
     */
    LawDictType selectByValueCode(String valueCode);

    /**
     * 根据启停标识查询字典列表
     *
     * @param usingSign 启停标识（1启用，0停用）
     * @return 字典列表
     */
    List<LawDictType> selectByUsingSign(Integer usingSign);
}
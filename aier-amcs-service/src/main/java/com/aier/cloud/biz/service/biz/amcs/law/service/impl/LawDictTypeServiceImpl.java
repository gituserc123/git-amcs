package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.biz.service.biz.amcs.law.dao.LawDictTypeMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDictType;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawDictTypeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 法务字典表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawDictTypeServiceImpl extends ServiceImpl<LawDictTypeMapper, LawDictType> implements LawDictTypeService {

    /**
     * 根据字典类型编码查询字典列表
     *
     * @param typeCode 字典类型编码
     * @return 字典列表
     */
    @Override
    public List<LawDictType> selectByTypeCode(String typeCode) {
        EntityWrapper<LawDictType> ew = new EntityWrapper<>();
        ew.eq("type_code", typeCode);
        return this.baseMapper.selectList(ew);
    }

    /**
     * 根据父级字典ID查询子字典列表
     *
     * @param typeCode
     * @return 子字典列表
     */
    @Override
    public List<LawDictType> selectSubDicts(String typeCode, String valueCode) {
        LawDictType cond = new LawDictType();
        cond.setTypeCode(typeCode);
        cond.setValueCode(valueCode);
        LawDictType parent = this.baseMapper.selectOne(cond);
        EntityWrapper<LawDictType> ew = new EntityWrapper<>();
        ew.eq("parent_id", parent.getId());
        return this.baseMapper.selectList(ew);
    }

    /**
     * 根据参数值编码查询字典信息
     *
     * @param valueCode 参数值编码
     * @return 字典信息
     */
    @Override
    public LawDictType selectByValueCode(String valueCode) {
        //return baseMapper.selectByValueCode(valueCode);
        return null;
    }

    /**
     * 根据启停标识查询字典列表
     *
     * @param usingSign 启停标识（1启用，0停用）
     * @return 字典列表
     */
    @Override
    public List<LawDictType> selectByUsingSign(Integer usingSign) {
        //return baseMapper.selectByUsingSign(usingSign);
        return null;
    }
}
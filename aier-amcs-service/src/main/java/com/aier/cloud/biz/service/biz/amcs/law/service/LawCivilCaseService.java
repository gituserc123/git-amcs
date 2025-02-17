package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCivilCase;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 民事诉讼仲裁案件主表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawCivilCaseService extends IService<LawCivilCase> {

    /**
     * 根据案件ID查询案件标签列表
     *
     * @param caseId 案件ID
     * @return 案件标签列表
     */
    List<LawCivilCase> findListByCaseId(Long caseId);

    /**
     * 根据案件ID查询案件标签字符串
     *
     * @param caseId 案件ID
     * @return 案件标签字符串
     */
    String findTagsById(Long caseId);

    /**
     * 根据案件ID查询案件标签描述字符串
     *
     * @param caseId 案件ID
     * @return 案件标签描述字符串
     */
    String findTagDescsById(Long caseId);

}
package com.aier.cloud.biz.service.biz.amcs.law.service.impl;


import com.aier.cloud.biz.service.biz.amcs.law.dao.LawCivilCaseMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCivilCase;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawCivilCaseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 民事诉讼仲裁案件主表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawCivilCaseServiceImpl extends ServiceImpl<LawCivilCaseMapper, LawCivilCase> implements LawCivilCaseService {


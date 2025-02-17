package com.aier.cloud.biz.ui.biz.adverse.feign;

import com.aier.cloud.api.amcs.adverse.condition.EventConfigCondition;
import com.aier.cloud.api.amcs.adverse.condition.ReviewCondition;
import com.aier.cloud.api.amcs.adverse.domain.Review;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-08-02 15:14
 **/

@FeignClient(name="aier-amcs-service")
public interface ReviewService {
    @RequestMapping("/api/service/biz/amcs/adverse/review/getAll")
    PageResponse<Map<String, Object>> getReview(@RequestParam("id") Long id);

    @RequestMapping("/api/service/biz/amcs/adverse/review/save")
    Boolean save(@RequestBody Review review);

    @RequestMapping("/api/service/biz/amcs/adverse/review/getAll")
    PageResponse <Map<String, Object>> getAll(@RequestBody ReviewCondition cond);
}

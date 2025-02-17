package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.api.amcs.adverse.condition.ReviewCondition;
import com.aier.cloud.api.amcs.adverse.domain.Review;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-08-02 15:18
 **/

@Controller
@RequestMapping("/ui/amcs/adverse/review/")
public class ReviewController extends BaseController {

    @Autowired
    ReviewService reviewService;


    @RequestMapping(value="/save",method = { RequestMethod.POST})
    @ResponseBody
    public Object save(Review review) {
        if (reviewService.save(review)){
            return this.success();
        }else{
            return this.fail();
        }

    }
    @RequestMapping(value = "/getAll",method = {  RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(ReviewCondition cond){
        return reviewService.getAll(cond);
    }
}

package com.aier.ccs;

import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.utils.NodeUtil;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.NodeConfigService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-07-07 15:21
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= AierAmcsService.class)
public class NodeTest {
    @Autowired
    NodeConfigService nodeConfigService;
    @Test
    public void test(){
        System.out.println(NodeUtil.NodeList(59));
        System.out.println(NodeUtil.NextNode(NodeEnum.HOSPITAL_REVIEWS,59));
    }
}

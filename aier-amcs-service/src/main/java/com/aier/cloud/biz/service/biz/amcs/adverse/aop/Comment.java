package com.aier.cloud.biz.service.biz.amcs.adverse.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Comment {
    String value();
}

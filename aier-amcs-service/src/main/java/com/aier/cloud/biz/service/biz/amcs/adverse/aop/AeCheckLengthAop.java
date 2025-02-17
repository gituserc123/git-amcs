package com.aier.cloud.biz.service.biz.amcs.adverse.aop;


import cn.hutool.core.util.ObjectUtil;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.basic.core.base.util.SysUtil;
import com.aier.cloud.center.common.context.UserContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 09:10
 **/

@Aspect
@Component
public class AeCheckLengthAop implements Ordered {

    @Autowired
    RedisService redisService;
    public AeCheckLengthAop() {
    }

    @Pointcut("execution(* com.baomidou.mybatisplus.mapper.BaseMapper.insert*(..)) || execution(* com.baomidou.mybatisplus.mapper.BaseMapper.update*(..))|| execution(* com.baomidou.mybatisplus.service.impl.ServiceImpl.insert*(..)) || execution(* com.baomidou.mybatisplus.service.impl.ServiceImpl.update*(..))")
    private void cut() {
    }

    @Before("cut()")
    public void doCut(JoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (args != null && args.length != 0 ) {
            Object[] var3 = args;
            int var4 = args.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Object a = var3[var5];
                if (a != null) {
                    this.setDict(a);
                }
            }

        }
    }

    private void setDict(Object a) {
        if (a instanceof BaseEntity) {
            this.doBaseEntity((BaseEntity)a);
        } else if (a instanceof Collection) {
            Collection c = (Collection)a;
            if (SysUtil.isNotEmpty(c)) {
                c.forEach(new Consumer<Object>() {
                    @Override
                    public void accept(Object t) {
                        if (t instanceof BaseEntity) {
                            AeCheckLengthAop.this.doBaseEntity((BaseEntity)t);
                        }

                    }
                });
            }
        }

    }

    private void doBaseEntity(final BaseEntity a) {
        final Class c = a.getClass();
        ReflectionUtils.doWithFields(c, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field fn) throws IllegalArgumentException, IllegalAccessException {
                Length dn = (Length)fn.getAnnotation(Length.class);
                Comment cm = (Comment)fn.getAnnotation(Comment.class);
                //检查String类型数据的长度是否符合要求，有超出的将msg记录在redis,如果有数据报错将msg抛出前端显示
                if (fn.getType().getName().equals("java.lang.String")&&dn != null && !ObjectUtil.isEmpty(dn.max())) {
                    String fieldName=cm==null?fn.getName():cm.value().split("（")[0].split("\\(")[0]+"】长度不能超过"+dn.max();
                    ReflectionUtils.makeAccessible(fn);
                    try {
                        String s=(String)fn.get(a);
                        if(s!=null&&s.length() >dn.max()){
                            redisService.set("AE:SAVEMESSAGE:"+ UserContext.getUserCode(),"【"+fieldName, TimeUnit.SECONDS.toSeconds(5));
//                            throw new BizException(fn.getName()+"长度不能超过"+dn.max());
                        }
                        //超出oracle4000字节的限制
                        if(s!=null&&s.getBytes(StandardCharsets.UTF_8).length>4000){
                            redisService.set("AE:SAVEMESSAGE:"+ UserContext.getUserCode(),"【"+fieldName+"】长度超过数据库允许的最大值", TimeUnit.SECONDS.toSeconds(5));
                        }
                    } catch (BizException e){
                        throw e;
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    @Override
    public int getOrder() {
        return 2147483646;
    }
}

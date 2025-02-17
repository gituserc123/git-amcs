package com.aier.cloud.biz.service.biz.amcs.adverse.aop;


import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.basic.core.base.util.SysUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 09:10
 **/

@Aspect
@Component
public class AeTransCodeAop implements Ordered {
    @Autowired(
            required = false
    )
    AeTransCodeService ds;

    public AeTransCodeAop() {
    }

    @Pointcut("execution(* com.baomidou.mybatisplus.mapper.BaseMapper.insert*(..)) || execution(* com.baomidou.mybatisplus.mapper.BaseMapper.update*(..))|| execution(* com.baomidou.mybatisplus.service.impl.ServiceImpl.insert*(..)) || execution(* com.baomidou.mybatisplus.service.impl.ServiceImpl.update*(..))")
    private void cut() {
    }

    @Before("cut()")
    public void doCut(JoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (args != null && args.length != 0 && this.ds != null) {
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
                            AeTransCodeAop.this.doBaseEntity((BaseEntity)t);
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
                AeDictName dn = (AeDictName)fn.getAnnotation(AeDictName.class);
                if (dn != null && !SysUtil.isBlank(dn.from()) && !SysUtil.isBlank(dn.type())) {
                    ReflectionUtils.makeAccessible(fn);
                    Object valuex = null;
                    if (dn.override()) {
                        valuex = ReflectionUtils.getField(fn, a);
                        if (valuex != null && SysUtil.isNotBlank(valuex.toString())) {
                            return;
                        }
                    }

                    Field f = ReflectionUtils.findField(c, dn.from());
                    if (f != null) {
                        ReflectionUtils.makeAccessible(f);
                        Object code = ReflectionUtils.getField(f, a);
                        String value;
                        if (code != null && !SysUtil.isBlank(code.toString())) {
                            value = AeTransCodeAop.this.ds.getValue(dn.type(), code.toString());
                        } else {
                            value = null;
                        }

                        ReflectionUtils.setField(fn, a, value);
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

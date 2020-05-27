package com.ht.oa.jk.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodDesc {

    /**
     * 方法描述
     *
     * @return
     */
    String desc() default "";
}



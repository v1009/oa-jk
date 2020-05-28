package com.ht.oa.jk.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiDesc {

    /**
     * 接口名称
     *
     * @return
     */
    String name();

    /**
     * 接口代码
     *
     * @return
     */
    String code() default "";

    /**
     * 接口描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 是否需要认证
     *
     * @return
     */
    boolean auth() default true;

    /**
     * 是否锁定方法（主要用作并发编程）
     *
     * @return
     */
    boolean lock() default false;

}



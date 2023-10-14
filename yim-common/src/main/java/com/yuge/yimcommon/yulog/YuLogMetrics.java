package com.yuge.yimcommon.yulog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface YuLogMetrics {

    /**
     * 是否记录请求参数
     */
    boolean logParameters() default true;

    /**
     * 是否在执行方法成功后打点
     */
    boolean logAfterExecuteMethodSuccess() default true;

    /**
     * 是否在执行方法失败后打点
     */
    boolean logAfterExecuteMethodFail() default true;

    /**
     * 是否记录返回值
     */
    boolean logReturn() default true;

    /**
     * 是否记录异常
     */
    boolean logException() default true;

    /**
     * 是否屏蔽异常返回默认值
     */
    boolean ignoreExcepReturnDef() default false;
}

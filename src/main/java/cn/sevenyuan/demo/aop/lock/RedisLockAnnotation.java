package cn.sevenyuan.demo.aop.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置必要参数
 *
 * @author JingQ at 4/4/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RedisLockAnnotation {

    /**
     * 特定参数识别，默认取第 0 个下标
     */
    int lockFiled() default 0;

    /**
     * 超时重试次数
     */
    int tryCount() default 3;

    /**
     * 自定义加锁类型
     */
    RedisLockTypeEnum typeEnum();

    /**
     * 释放时间，秒 s 单位
     */
    long lockTime() default 30;
}

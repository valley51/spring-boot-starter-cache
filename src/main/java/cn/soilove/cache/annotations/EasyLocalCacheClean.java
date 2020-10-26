package cn.soilove.cache.annotations;

import java.lang.annotation.*;

/**
 * 本地缓存清空注解
 * @author: Chen GuoLin
 * @create: 2020-10-26 19:32
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyLocalCacheClean {
    /**
     * 缓存命名空间
     * @return
     */
    String namespace();

    /**
     * 缓存key - spel表达式
     * <pre>
     * 示例：
     *  key="#id"
     *  key="#user.id"
     *  key="'name:' + #user.name"
     * </pre>
     * @return
     */
    String key() default "";
}

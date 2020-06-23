package cn.practices.strategy;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @Description: 策略注解
 * @Author: jingwentao
 * @Date: 2020/6/23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@Component
public @interface Strategy {

    String value() default "";

    EnumStrategy key();
}

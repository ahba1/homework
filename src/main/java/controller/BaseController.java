package controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the base controller, all the controller must implement this
 * created by zhouliang on 2020/7/17
 * @author zhouliang
 * @version 1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseController {

    String type() default "";

}

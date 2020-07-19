package ormliked;

import java.lang.annotation.*;

/**
 * use this to label the ormliked interface
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlMapper {
}

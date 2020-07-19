package ormliked;

import java.lang.annotation.*;


/**
 * use this annotation to map the java method to sql operation
 * sqlProcess: the kind of the sql operation(select, insert, delete, update, create……)
 * sqlStatement: the sql statement before process, use #{} to label the parameter,
 *              the order of the parameters of the labeled method must be the same
 *              with the #{} order
 * resultType: the sql result
 * created by zhouliang on 2020/7/17
 * @author zhouliang
 * @version 1.0
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sql {

    String sqlProcess();

    String sqlStatement();

    String resultType() default "";

}

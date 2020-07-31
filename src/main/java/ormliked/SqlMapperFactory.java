package ormliked;


import ormliked.exception.UnAnnotatedTypeException;

import java.lang.reflect.Proxy;

/**
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.1
 */

@SuppressWarnings("unchecked")
public class SqlMapperFactory {

    public static <T> T getMapper(Class<T> tClass){
        check(tClass);
        MapperProxy proxy = new MapperProxy(tClass);
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[] {tClass}, proxy);
    }

    /**
     * this method added to check the type of tClass, the class must be annotated with SqlMapper
     * updated by zhouliang on 2020/7/31
     * @param tClass
     * @param <T>
     */
    private static  <T> void check(Class<T> tClass){
        if (tClass.getAnnotation(SqlMapper.class)==null){
            throw new UnAnnotatedTypeException();
        }
    }

}

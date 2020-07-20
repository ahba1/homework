package ormliked;


import java.lang.reflect.Proxy;

/**
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.0
 */

public class SqlMapperFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getMapper(Class<T> tClass){
        MapperProxy proxy = new MapperProxy(tClass);

        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[] {tClass}, proxy);
    }


}

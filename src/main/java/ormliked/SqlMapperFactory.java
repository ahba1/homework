package ormliked;


import java.lang.reflect.Proxy;

public class SqlMapperFactory {


    public static <T> T getMapper(Class<T> tClass){
        MapperProxy proxy = new MapperProxy(tClass);

        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[] {tClass}, proxy);
    }


}

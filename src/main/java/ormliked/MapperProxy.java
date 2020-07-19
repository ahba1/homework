package ormliked;

import dao.SqlManager;
import ormliked.exception.ManyResultException;
import ormliked.exception.SqlAnnotationNotExistException;
import util.LoggerManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * I package the cumbersome jdbc operation in an orm liked frame
 * it's the core of the frame
 * created by zhouliang on 2020/7/19
 * @param <T>
 * @author zhouliang
 * @version 1.0
 */

class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    private final static String PATTERN = "#\\{.*\\}";

    MapperProxy(Class<T> mapperInterface){
        this.mapperInterface = mapperInterface;
    }

    /**
     *
     * @param proxy the proxy object
     * @param method the invoked method
     * @param args the parameters of the invoked method
     * @return the sql result
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this, args);
        }


        Annotation annotation = method.getAnnotation(Sql.class);

        if(annotation!=null){
            Sql sql = (Sql)annotation;
            ResultSet result= SqlManager.getStatement().executeQuery(genStatement(sql.sqlStatement(), args));
            Class clazz = Class.forName(sql.resultType());
            if (method.getReturnType().equals(List.class)){
                return select(result, clazz);
            }else {
                return selectOne(result, clazz);
            }
        }else{
            throw new SqlAnnotationNotExistException();
        }
    }

    /**
     * this method is used to process the sql statements, replace the parameter in the statement with the parameters
     * of the invoked method
     * @param preProcessStatement the sql statements before process
     * @param args the parameters of the invoked method
     * @return the real sql statements
     */
    private String genStatement(String preProcessStatement, Object[] args){
        if (args == null){
            return preProcessStatement;
        }
        String result = "";
        for (Object arg:args){

            result = preProcessStatement.replaceFirst(PATTERN, arg.toString());
        }

        return result;
    }

    /**
     * map the select operation in sql
     * @param resultSet
     * @param clazz
     * @param <V>
     * @return
     */
    private <V> List<V> select(ResultSet resultSet, Class<V> clazz){
        List<V> res = Collections.synchronizedList(new LinkedList<>());
        try {
            while (resultSet.next()){
                V bean = clazz.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();

                for(int i = 0;i<metaData.getColumnCount();i++){
                    String name = metaData.getColumnName(i+1);
                    Field field = bean.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(bean, resultSet.getObject(name));
                }

                res.add(bean);
            }
        }catch (InstantiationException | IllegalAccessException | SQLException | NoSuchFieldException e){
            LoggerManager.getLoggerManager().error(e.getClass()+"  "+e.getMessage());
        }
        return res;
    }

    private <V> V selectOne(ResultSet resultSet, Class<V> clazz){
        List<V> list = select(resultSet, clazz);
        if(list.size()==1){
            return list.get(0);
        }else if(list.size()==0){
            return null;
        }else {
            throw new ManyResultException();
        }
    }
}

package ormliked;

import dao.SqlManager;
import ormliked.exception.ManyResultException;
import ormliked.exception.SqlAnnotationNotExistException;
import ormliked.exception.UnsupportedSqlOperationException;
import util.LoggerManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * package the cumbersome jdbc operation in an orm liked frame
 * it's the core of the frame
 * created by zhouliang on 2020/7/19
 * @param <T>
 * @author zhouliang
 * @version 1.0
 */

class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    private final static String PATTERN = "#\\{.*?\\}";

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

            switch (sql.sqlProcess()){
                case "select":
                    ResultSet result= SqlManager.getStatement().executeQuery(genStatement(sql.sqlStatement(), args));
                    Class clazz = Class.forName(sql.resultType());
                    if (method.getReturnType().equals(List.class)){
                        return select(result, clazz);
                    }else {
                        return selectOne(result, clazz);
                    }

                case "insert":
                    if (method.getReturnType().equals(Void.TYPE)){
                        SqlManager.getStatement().executeUpdate(genStatement(sql.sqlStatement(), args));
                        return Void.TYPE;
                    }else{
                        String statement = genStatement(sql.sqlStatement(), args);
//                         System.out.println(sql.sqlStatement());
//                         for (Object o:args){
//                             System.out.println(o);
//                         }
//                         System.out.println(statement);
                        int res = SqlManager.getStatement().executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
                        ResultSet resultSet = SqlManager.getStatement().getGeneratedKeys();
                        if (method.getReturnType().equals(String.class)){
                            if (resultSet.next()){
                                return resultSet.getString(1);
                            }
                        }else if (method.getReturnType().equals(Boolean.class)){
                            return res==1;
                        }else if(method.getReturnType().equals(Integer.class)){
                            if (resultSet.next()){
                                return resultSet.getInt(1);
                            }
                        }
                    }
                case "delete":
                    String statement =genStatement(sql.sqlStatement(),args);
                    int res=SqlManager.getStatement().executeUpdate(statement,Statement.RETURN_GENERATED_KEYS);
                    if(method.getReturnType().equals(Boolean.class)){
                        if(res==0){
                            return false;
                        }
                        else return true;
                    }

                case "update":
                    if (method.getReturnType().equals(Void.TYPE)){
                        SqlManager.getStatement().executeUpdate(genStatement(sql.sqlStatement(), args));
                        return Void.TYPE;
                    }else if(method.getReturnType().equals(Integer.class)){
                        return SqlManager.getStatement().executeUpdate(genStatement(sql.sqlStatement(), args));
                    }else if(method.getReturnType().equals(Boolean.class)){
                        String statement1 =genStatement(sql.sqlStatement(),args);
//                        System.out.println(sql.sqlStatement());
//                        for (Object o:args){
//                            System.out.println(o);
//                        }
//                        System.out.println(statement1);
                        int res1=SqlManager.getStatement().executeUpdate(statement1,Statement.RETURN_GENERATED_KEYS);
                        if(res1==0){
                            return false;
                        }
                        else return true;
                    } else{
                        throw new UnsupportedSqlOperationException("the return type is unsupported");
                    }
                    
                default:
                    throw new UnsupportedSqlOperationException();

        }else{
            throw new SqlAnnotationNotExistException();
        }
    }

    /**
     * this method is used to process the sql statements, replace the parameter in the statement with the parameters
     * of the invoked method
     * @param statement the sql statements before process
     * @param args the parameters of the invoked method
     * @return the real sql statements
     */
    private String genStatement(String statement, Object[] args){
        if (args == null){
            return statement;
        }

        String result="";

        for (Object arg:args){

            result = statement.replaceFirst(PATTERN, arg.toString());
            statement = result;
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
}

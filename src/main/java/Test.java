import controller.BaseController;
import dao.SqlManager;
import dao.mapper.AdminSqlMapper;
import ormliked.SqlMapper;
import pojo.Admin;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
//        String pattern = "#\\{.*\\}";
//
//        String sql = "select * from user where id = #{id}";
//
//        System.out.println(sql.replaceFirst(pattern, "2"));
//
//        Method[] methods = Test.class.getDeclaredMethods();
//
//        for (Method method:methods){
//            System.out.println(method.getReturnType().equals(Void.TYPE));
//        }
//        try {
//            Class clazz = Class.forName("java.lang.String");
//            System.out.println(clazz.getName());
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }

        System.out.println(SqlManager.getAdminSqlMapper().insertRegisterInfo("zl","ahba1", "123456"));
        //func();
    }

    private static void func(){
        SqlManager.getAdminSqlMapper().selectAll();
    }
}

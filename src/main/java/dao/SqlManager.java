package dao;


import dao.mapper.AdminSqlMapper;
import ormliked.SqlMapperFactory;
import util.LoggerManager;

import java.sql.*;
import java.util.Properties;

/**
 * created by zhouliang on 2020/7/17
 * @author zhouliang
 * @version 1.0
 */
public class SqlManager {

    private static Connection connection;
    private static Statement statement=null;
    private static ResultSet resultSet=null;
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/ahba1?useSSL=false&serverTimezone=UTC";
    private static String user="root";
    private static  String pwd="18108045080zZ";

    private AdminSqlMapper adminSqlService;

    //静态代码块初始化JDBC配置
    static {
        try {

            Properties properties = new Properties();
//            properties.load(SqlManager.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//            driver = properties.getProperty("driver");
//            url = properties.getProperty("url");
//            user = properties.getProperty("user");
//            pwd = properties.getProperty("pwd");

            //加载驱动
            Class.forName(driver);

            connection = DriverManager.getConnection(url, user ,pwd);

            statement = connection.createStatement();
        }catch (ClassNotFoundException e){
            LoggerManager.getLoggerManager().error("failed to load the driver, please check the driver\nreason: "+e.getMessage());

        }catch (SQLException e){
            LoggerManager.getLoggerManager().error("failed to get the connection\nreason: "+e.getMessage());

        }
    }

    /**
     * get the connection
     * @return
     */
    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static void closeAll(){
        try {
            if (resultSet!=null)
                resultSet.close();
            if(statement!=null)
                statement.close();
            if (connection!=null)
                connection.close();
        }catch (SQLException e){
            LoggerManager.getLoggerManager().error("failed to close the connection\nreason: "+e.getMessage());
        }
    }

    public static AdminSqlMapper getAdminSqlService(){
        return createService(AdminSqlMapper.class);
    }

    private static <T> T createService(Class<T> tClass){

        return SqlMapperFactory.getMapper(tClass);
    }
}
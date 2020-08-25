package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.Admin;
import pojo.Password;

import java.util.List;

/**
 * this is a test SqlMapper
 */
@SqlMapper
public interface AdminSqlMapper {

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from admin",
            resultType = "pojo.Admin")
    List<Admin> selectAll();

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from admin where id = #{id}",
            resultType = "pojo.Admin")
    Admin selectByPrimaryKey(Integer id);

    @Sql(sqlProcess = "insert",
            sqlStatement = "insert into admin(id) values(#{id})",
            resultType = "java.lang.Integer")
    Integer insert(Integer id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select password from admin where username = #{username}",
            resultType = "pojo.Password")
    Password selectPasswordByUsername(String username);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into admin(name,username,password)values('#{name}','#{username}','#{password}')",
            resultType = "java.lang.Boolean")
    Boolean insertRegisterInfo(String name,String username,String password);


}

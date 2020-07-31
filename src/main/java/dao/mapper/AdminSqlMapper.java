package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.Admin;

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

    @Sql(sqlProcess = "select",
            sqlStatement = "select password from admin where username = #{username}",
            resultType = "pojo.admin")
    Admin selectPasswordByUsername(String username);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into Admin(#{name},#{username},#{password})",
            resultType = "boolean")
    boolean updateRegisterInfo(String name,String username,String password);

}


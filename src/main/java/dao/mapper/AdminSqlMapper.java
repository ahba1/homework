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

}

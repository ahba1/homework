package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.Admin;
import pojo.Interviewer;

import java.util.List;

@SqlMapper
public interface InterviewerSqlMapper {

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from Interviewer",
            resultType = "pojo.Interviewer")
    List<Interviewer> selectAll();

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from Interviewer where id = #{id}",
            resultType = "pojo.Interviewer")
    Interviewer selectByPrimaryKey(Integer id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select password from Interviewer where username = #{username}",
            resultType = "pojo.Password")
    Password selectPasswordByUsername(String username);
    
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into Interviewer(#{name},#{username},#{password})",
            resultType = "java.lang.Boolean")
    Boolean insertRegisterInfo(String name,String username,String password);
    
     @Sql(sqlProcess ="select",
            sqlStatement = "select * from recruitment where recruitment.position=position",
            resultType = "pojo.Recruitment")
    List<Recruitment> query(int position, String startDate, String endDate);
    
    
}

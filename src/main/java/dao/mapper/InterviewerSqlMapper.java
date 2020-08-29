package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.Admin;
import pojo.Interviewer;

import java.util.List;

@SqlMapper
public interface InterviewerSqlMapper {

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from interviewer",
            resultType = "pojo.Interviewer")
    List<Interviewer> selectAll();

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from interviewer where id = #{id}",
            resultType = "pojo.Interviewer")
    Interviewer selectByPrimaryKey(Integer id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select password from interviewer where username = #{username}",
            resultType = "pojo.Password")
    Password selectPasswordByUsername(String username);
    
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer values('#{name}','#{username}','#{password}')",
            resultType = "java.lang.Boolean")
    Boolean insertRegisterInfo(String name,String username,String password);
    
     @Sql(sqlProcess ="select",
            sqlStatement = "select * from recruitment where recruitment.position=position",
            resultType = "pojo.Recruitment")
    List<Recruitment> query(int position, String startDate, String endDate);
    
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer_info values('#{username}','#{description}')",
            resultType = "java.lang.Boolean")
    Boolean insertInterviewerInfo(String username,String description);
    
    
}

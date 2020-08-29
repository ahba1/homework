package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.*;

import java.util.List;

@SqlMapper
public interface InterviewerSqlMapper {

//    @Sql(sqlProcess = "select",
//            sqlStatement = "select * from Interviewer",
//            resultType = "pojo.Interviewer")
//    List<Interviewer> selectAll();


//    @Sql(sqlProcess = "select",
//            sqlStatement = "select * from Interviewer where id = #{id}",
//            resultType = "pojo.Interviewer")
//    Interviewer selectByPrimaryKey(Integer id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from interviewer_info where username = '#{username}'",
            resultType = "pojo.Interviewer_Info")
    Interviewer_Info selectByPrimaryKey(String username);

    @Sql(sqlProcess = "select",
            sqlStatement = "select password from interviewer where username = '#{username}'",
            resultType = "pojo.Password" )
    Password selectPasswordByUsername(String username);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment where position = #{position}",
            resultType = "pojo.Recruitment" )
    List<Recruitment> selectByPosition(int position);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer values('#{username}','#{name}','#{password}')",
            resultType = "java.lang.Boolean")
    Boolean insertRegisterInfo(String username,String name,String password);

    @Sql(sqlProcess ="select",
            sqlStatement = "select * from recruitment where recruitment.position=position",
            resultType = "pojo.Recruitment")
    List<Recruitment> query(int position, String startDate, String endDate);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer_info values('#{username}',#{re_id},#{status},'#{description}')",
            resultType = "java.lang.Boolean")
    Boolean insertInterviewerInfo(String username,int re_id,int status,String description);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into apply_info(username,re_id)values('#{username}',#{re_id})",
            resultType = "java.lang.Boolean")
    Boolean insertApplyInfo(String username,int re_id);


    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer_pos(username,position,info)values('#{username}',#{position},'#{info}')",
            resultType = "java.lang.Boolean")
    Boolean insertAbilityInfo(String username,int position,String info);


}


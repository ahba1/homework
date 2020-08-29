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
            sqlStatement = "select * from interviewer,recruitment where interviewer.position=recruitment.position",
            resultType = "pojo.Interviewer")
    List<Interviewer> screen(int id);

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

    @Sql(sqlProcess = "insert",
            sqlStatement = "insert into recruitment(re_id,position,start_date,end_date)values(#{id},#{position},'#{StartDate}','#{EndDate}')",
            resultType = "java.lang.Boolean"
    )
    Boolean publishRecruitment(int id, int position, String StartDate, String EndDate);
    
     @Sql(sqlProcess = "insert",
            sqlStatement = "insert into delivery_info(username,recruitment,status,info)values('#{interviewerUsername}',recruitment,isRecruited,'#{info}')",
            resultType = "pojo.DeliveryInfo")
    DeliveryInfo remark(String interviewerUsername, int recruitment, int isRecruited, String info);
    
    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment where re_id=id",
            resultType = "pojo.Recruitment")
    Recruitment query(int id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment where username='#{username}'",
            resultType = "pojo.Interviewer")
    Interviewer query(String username);

     @Sql(sqlProcess = "select",
            sqlStatement = "select (case when max(re_id) is null then 0 else max(re_id) end) as max_id from recruitment",
            resultType = "pojo.Recruitment_Max_id")
    Recruitment_Max_id selectMax_id();

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into position values(#{id},#{position},#{number})",
            resultType = "java.lang.Boolean")
    Boolean insertPositionNumber(int id, int position, int number);
}

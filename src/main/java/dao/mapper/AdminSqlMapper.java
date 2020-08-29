package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.*;

import java.lang.Boolean;

import java.util.List;

/**
 * this is a test SqlMapper
 */
@SqlMapper
public interface AdminSqlMapper {

//    @Sql(sqlProcess = "select",
//            sqlStatement = "select * from admin",
//            resultType = "pojo.Admin")
//    List<Admin> selectAll();

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from apply_info where re_id=#{re_id}",
            resultType = "pojo.Apply_Info")
    List<Apply_Info> screen(int re_id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from apply_info",
            resultType = "pojo.Apply_Info")
    List<Apply_Info> screen();


//    @Sql(sqlProcess = "select",
//            sqlStatement = "select * from admin where id = #{id}",
//            resultType = "pojo.Admin")
//    Admin selectByPrimaryKey(Integer id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from admin where username = '#{username}'",
            resultType = "pojo.Admin")
    Admin selectByAPrimaryKey(String username);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from apply_info where username = '#{username}'",
            resultType = "pojo.Interviewer")
    Interviewer selectByIPrimaryKey(String username);

    @Sql(sqlProcess = "select",
            sqlStatement = "select password from admin where username = '#{username}'",
            resultType = "pojo.Password")
    Password selectPasswordByUsername(String username);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into admin(name,username,password,com_id)values('#{name}','#{username}','#{password}',#{com_id})",
            resultType = "java.lang.Boolean")
    Boolean insertRegisterInfo(String name,String username,String password,int com_id);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into company(com_id,info)values(#{com_id},'')",
            resultType = "java.lang.Boolean")
    Boolean insertCompanyInfo(int com_id);

    @Sql(sqlProcess = "insert",
            sqlStatement = "insert into recruitment(re_id,position,start_date,end_date,isfull)values(#{id},#{position},'#{StartDate}','#{EndDate}',0)",
            resultType = "java.lang.Boolean"
    )
    Boolean publishRecruitment(int id, int position, String StartDate, String EndDate);

//    @Sql(sqlProcess = "insert",
//            sqlStatement = "insert into delivery_info(username,recruitment,status,info)values('#{interviewerUsername}',recruitment,isRecruited,'#{info}')",
//            resultType = "pojo.DeliveryInfo")
//    DeliveryInfo remark(String interviewerUsername, int recruitment, int isRecruited, String info);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment where re_id=id",
            resultType = "pojo.Recruitment")
    Recruitment query(int id);

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment",
            resultType = "pojo.Recruitment")
    List<Recruitment> re_all_screen();

    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment where username='#{username}'",
            resultType = "pojo.Interviewer")
    Interviewer query(String username);



    @Sql(sqlProcess = "select",
            sqlStatement = "select (case when max(re_id) is null then 0 else max(re_id) end) as max_id from recruitment",
            resultType = "pojo.Recruitment_Max_id")
    Recruitment_Max_id selectMax_id();

    @Sql(sqlProcess ="select",
            sqlStatement = "select isfull from recruitment where re_id=#{id}",
            resultType = "pojo.IsFull")
    IsFull SelectByFull(int id);

    @Sql(sqlProcess ="select",
            sqlStatement = "select number from position where re_id=#{id}",
            resultType = "pojo.Position_Number")
     Position_Number selectPositionNumber(int id);

    @Sql(sqlProcess ="select",
            sqlStatement = "select * from interviewer_pos where position=#{position}",
            resultType = "pojo.Interviewer_Pos")
    List<Interviewer_Pos> SelectByPosition(int position);

    @Sql(sqlProcess ="select",
            sqlStatement = "select com_id from admin where username='#{username}'",
            resultType = "pojo.Company_id")
    Company_id SelectCompanyId(String username);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into position values(#{id},#{position},#{number})",
            resultType = "java.lang.Boolean")
    Boolean insertPositionNumber(int id, int position, int number);

    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into company_re values(#{com_id},#{re_id})",
            resultType = "java.lang.Boolean")
    Boolean insertCompanyRe(int com_id, int re_id);

    @Sql(sqlProcess ="delete",
            sqlStatement = "delete from recruitment where re_id=#{id}",
            resultType = "java.lang.Boolean")
    Boolean deleteByre_id(int id);



    @Sql(sqlProcess ="update",
            sqlStatement = "update position  set number=(case when number>0 then number-1 else 0 end) where re_id=#{id}",
            resultType = "java.lang.Boolean")
    Boolean updatePositionNumber(int id);

    @Sql(sqlProcess ="update",
            sqlStatement = "update interviewer_info set re_id=#{re_id},isRecruited=#{status},description='#{des}' where username='#{interviewerUsername}'",
            resultType = "java.lang.Boolean")
    Boolean updateHireInfo(int re_id,int status,String des,String interviewerUsername);

    @Sql(sqlProcess ="update",
            sqlStatement = "update recruitment set isfull=1 where re_id=#{re_id}",
            resultType = "java.lang.Boolean")
    Boolean updateFull(int re_id);

    @Sql(sqlProcess ="update",
            sqlStatement = "update Company set info='#{info}' where com_id=#{com_id}",
            resultType = "java.lang.Boolean")
    Boolean updateCompanyInfo(String info,int com_id);
}

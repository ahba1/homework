package dao.mapper;

import ormliked.Sql;
import ormliked.SqlMapper;
import pojo.*;

import java.util.List;

/**
 * created by wutao on 2020/8/15
 * @author wutao
 * @version 1.0
 */



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
//按用户名查找用户信息
    @Sql(sqlProcess = "select",
            sqlStatement = "select * from interviewer_info where username = '#{username}'",
            resultType = "pojo.Interviewer_Info")
    Interviewer_Info selectByPrimaryKey(String username);
//按用户名查找密码
    @Sql(sqlProcess = "select",
            sqlStatement = "select password from interviewer where username = '#{username}'",
            resultType = "pojo.Password" )
    Password selectPasswordByUsername(String username);
//按职位id查找所有招聘信息
    @Sql(sqlProcess = "select",
            sqlStatement = "select * from recruitment where position = #{position}",
            resultType = "pojo.Recruitment" )
    List<Recruitment> selectByPosition(int position);
//插入注册信息
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer values('#{username}','#{name}','#{password}')",
            resultType = "java.lang.Boolean")
    Boolean insertRegisterInfo(String username,String name,String password);

//用于创建初始录用信息
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer_info values('#{username}',#{re_id},#{status},'#{description}')",
            resultType = "java.lang.Boolean")
    Boolean insertInterviewerInfo(String username,int re_id,int status,String description);
//插入面试信息
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into apply_info(username,re_id)values('#{username}',#{re_id})",
            resultType = "java.lang.Boolean")
    Boolean insertApplyInfo(String username,int re_id);

//插入职位能力
    @Sql(sqlProcess ="insert",
            sqlStatement = "insert into interviewer_pos(username,position,info)values('#{username}',#{position},'#{info}')",
            resultType = "java.lang.Boolean")
    Boolean insertAbilityInfo(String username,int position,String info);


}


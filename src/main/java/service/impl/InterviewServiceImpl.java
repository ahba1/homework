package service.impl;

import dao.SqlManager;
import pojo.*;
import service.InterviewerService;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * created by bianshiran on 2020/8/26
 * @author bianshiran
 * @version 1.0
 */

public class InterviewServiceImpl implements InterviewerService {
//登录
    @Override
    public boolean login(String username, String password) {
        Password p=SqlManager.getInterviewerSqlMapper().selectPasswordByUsername(username);
        if (p!=null) {
            if(p.getPassword().equals(password))
                return true;
            else return false;
        } else {
            return false;
        }
    }
//注册
    @Override
    public boolean register(String name, String username, String password) {
        boolean result = SqlManager.getInterviewerSqlMapper().insertRegisterInfo(name, username, password);
        if(result==false)
            return false;
        else {
            boolean result1 = setInterviewerInfo(username, 0, 0, "");
            if (result1 == true)
                return true;
            else return false;
        }
    }
//添加职位能力    
    @Override
    public boolean addAbilityInfo(String username,int position,String info){
        boolean res=SqlManager.getInterviewerSqlMapper().insertAbilityInfo(username,position,info);
        if(res==true)
            return true;
        else return false;
    }

//创建录用信息
    @Override
    public  boolean setInterviewerInfo(String username,int re_id,int status,String description){
        boolean result=SqlManager.getInterviewerSqlMapper().insertInterviewerInfo(username,re_id,status,description);
        return result;
    }


//按职位id查找所有招聘信息
    @Override
    public List<Recruitment> query(int position) {
        return SqlManager.getInterviewerSqlMapper().selectByPosition(position);
    }
//提交面试信息    
       @Override
    public boolean apply(int recruitment_id, String username) {
        return SqlManager.getInterviewerSqlMapper().insertApplyInfo(username,recruitment_id);
    }

//显示录用状态
    @Override
    public Interviewer_Info show_status(String username){
        return SqlManager.getInterviewerSqlMapper().selectByPrimaryKey(username);
    }

}

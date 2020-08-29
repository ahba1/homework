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
    
    @Override
    public boolean addAbilityInfo(String username,int position,String info){
        boolean res=SqlManager.getInterviewerSqlMapper().insertAbilityInfo(username,position,info);
        if(res==true)
            return true;
        else return false;
    }

        //new
    @Override
    public  boolean setInterviewerInfo(String username,String description){
        boolean result=SqlManager.getInterviewerSqlMapper().insertInterviewerInfo(username,description);
        return result;
    }

    
    @Override
    public List<Recruitment> query(int position, String startDate, String endDate) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date sDate = bartDateFormat.parse(startDate);
            java.util.Date eDate = bartDateFormat.parse(endDate);
            java.sql.Date sqlStartDate = new java.sql.Date(sDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(eDate.getTime());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
//         return SqlManager.getInterviewerSqlMapper().query(position,sqlStartDate,sqlEndDate);
        return null;
    }

       @Override
    public boolean apply(int recruitment_id, String username) {
        return SqlManager.getInterviewerSqlMapper().insertApplyInfo(username,recruitment_id);
    }


    @Override
    public Interviewer_Info show_status(String username){
        return SqlManager.getInterviewerSqlMapper().selectByPrimaryKey(username);
    }

}

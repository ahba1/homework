package service.impl;

import dao.SqlManager;
import pojo.Recruitment;
import service.InterviewerService;

import java.text.SimpleDateFormat;
import java.util.List;

public class InterviewServiceImpl implements InterviewerService {

    @Override
    public boolean login(String username, String password) {
        if (SqlManager.getInterviewerSqlMapper().selectPasswordByUsername(username).equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean register(String name, String username, String password) {
        boolean result = SqlManager.getInterviewerSqlMapper().insertRegisterInfo(name, username, password);
        return result;
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
        return SqlManager.getInterviewerSqlMapper().query(position,sqlStartDate,sqlEndDate);
        return null;
    }

    @Override
    public List<Recruitment> query(int position) {
        return SqlManager.getInterviewerSqlMapper().query(position);
    }

    @Override
    public boolean apply(int recruitment, String username) {
        return SqlManager.getInterviewerSqlMapper().apply(recruitment,username);
    }

}

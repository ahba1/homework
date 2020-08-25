package service.impl;

import dao.SqlManager;
import pojo.Interviewer;
import pojo.Position;
import pojo.Recruitment;
import service.InterviewerService;

import java.sql.Date;
import java.util.List;

public class InterviewServiceImpl implements InterviewerService {

    @Override
    public boolean login(String username, String password) {
        if(SqlManager.getInterviewerSqlMapper().selectPasswordByUsername(username).equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean register(String name, String username, String password) {
        boolean result=SqlManager.getInterviewerSqlMapper().insertRegisterInfo(name,username,password);
        return result;
    }

    @Override
    public List<Recruitment> query(int position, String startDate, String endDate) {
        java.sql.Date startSqlDate = new java.sql.Date(startDate.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(endDate.getTime());
        return SqlManager.getInterviewerSqlMapper().query(position, startSqlDate, endSqlDate) ;
    }

    @Override
    public List<Recruitment> query(int position) {
        return SqlManager.getInterviewerSqlMapper().query(position);
    }

    @Override
    public boolean apply(Recruitment recruitment, Interviewer interviewer) {
        String inerviewerUsername=interviewer.getUsername();
        int recruitmentID=recruitment.getId();
        return SqlManager.getInterviewerSqlMapper().apply(inerviewerUsername,inerviewerUsername);
    }

    @Override
    public Interviewer query(String username) {
        return null;
    }
}

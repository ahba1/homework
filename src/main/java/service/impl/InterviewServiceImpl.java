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
    public List<Recruitment> query(Position position, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Recruitment> query(Position position) {
        return null;
    }

    @Override
    public boolean apply(Recruitment recruitment, Interviewer interviewer) {
        return false;
    }


    @Override
    public Interviewer query(String username) {
        return null;
    }
}

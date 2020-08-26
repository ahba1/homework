package service.impl;

import dao.SqlManager;
import pojo.Recruitment;
import service.InterviewerService;

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

    @Override
    public List<Recruitment> query(int position, String startDate, String endDate) {
        return null;
    }

    @Override
    public List<Recruitment> query(int position) {
        return null;
    }

    @Override
    public boolean apply(int recruitment, String username) {
        return false;
    }

}

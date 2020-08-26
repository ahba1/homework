package service.impl;

import dao.SqlManager;
import pojo.Interviewer;
import pojo.Recruitment;
import service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public boolean login(String username, String password) {
        if(SqlManager.getAdminSqlMapper().selectPasswordByUsername(username).equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean register(String name, String username, String password) {
        boolean result=SqlManager.getAdminSqlMapper().insertRegisterInfo(name,username,password);
        return result;
    }

    @Override
    public Recruitment publishRecruitment(int position, String startDate, String endDate) {
        return null;
    }

    @Override
    public List<Interviewer> screen(int id) {
        return null;
    }

    @Override
    public void remark(String interviewerUsername, int recruitment, int isRecruited, String info) {
    }

    @Override
    public Recruitment query(int id) {
        return null;
    }

    @Override
    public Interviewer query(String username) {
        return null;
    }

}

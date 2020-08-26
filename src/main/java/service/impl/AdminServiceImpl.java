package service.impl;

import dao.SqlManager;
import pojo.Interviewer;
import pojo.Recruitment;
import service.AdminService;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        return SqlManager.getAdminSqlMapper().publishRecruitment(position,sqlStartDate,sqlEndDate);
    }

    @Override
    public List<Interviewer> screen(int id) {
        return SqlManager.getAdminSqlMapper().screen(id);
    }

    @Override
    public void remark(String interviewerUsername, int recruitment, int isRecruited, String info) {
        SqlManager.getAdminSqlMapper().remark(interviewerUsername,recruitment,isRecruited,info);
    }

    @Override
    public Recruitment query(int id) {
        return SqlManager.getAdminSqlMapper().query(id);
    }

    @Override
    public Interviewer query(String username) {
        return SqlManager.getAdminSqlMapper().query(username);
    }

}

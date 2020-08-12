package controller;

import pojo.Interviewer;
import pojo.Position;
import pojo.Recruitment;
import service.impl.ServiceManager;

import java.sql.Date;
import java.util.List;

@BaseController(type = "Admin")
public class AdminController {

    public boolean login(String username, String password){
        return ServiceManager.getAdminService()
                .login(username, password);
    }

    public boolean register(String name, String username, String password, String passwordAgain){
        return password.equals(passwordAgain) &&ServiceManager.getAdminService()
                .register(name, username, password);
    }

    public Recruitment publishRecruitment(int position, String startDate, String endDate){
        return ServiceManager.getAdminService()
                .publishRecruitment(position, startDate, endDate);
    }

    public List<Interviewer> screen(int id){
        return ServiceManager.getAdminService()
                .screen(id);
    }

    public void mark(String interviewerUsername, int recruitment, int isRecruited, String info){
        ServiceManager.getAdminService()
                .remark(interviewerUsername, recruitment, isRecruited, info);
    }

    public Recruitment query(int id){
        return ServiceManager.getAdminService()
                .query(id);
    }
}

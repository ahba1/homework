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

//改
    public boolean publishRecruitment(int position, int number, String startDate, String endDate){
        return ServiceManager.getAdminService()
                .publishRecruitment(position, number, startDate, endDate);
    }

    public List<Interviewer> screen(int id){
        return ServiceManager.getAdminService()
                .screen(id);
    }

    public void mark(String interviewerUsername, int recruitment, int isRecruited, String info){
        return ServiceManager.getAdminService()
                .hire_confirmed(interviewerUsername, recruitment, isRecruited);
    }

    public Recruitment query(int id){
        return ServiceManager.getAdminService()
                .query(id);
    }

    public Interviewer query(String username){
        return ServiceManager.getAdminService()
                .query(username);
    }
    
    public boolean deleteFull(int re_id){
        return ServiceManager.getAdminService()
                .deleteFull(re_id);
    }

    public boolean delete(int re_id){
        return ServiceManager.getAdminService()
                .delete(re_id);
    }
}

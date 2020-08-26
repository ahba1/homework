package controller;


import pojo.Interviewer;
import pojo.Recruitment;
import service.impl.ServiceManager;

import java.sql.Date;
import java.util.List;

@BaseController(type = "Interviewer")
public class InterviewerController {

    public boolean login(String username, String password){
        return ServiceManager.getInterviewerService()
                .login(username, password);
    }

    public boolean register(String name, String username, String password, String passwordAgain){
        return password.equals(passwordAgain)
                &&ServiceManager.getInterviewerService()
                .register(name, username, password);
    }

    public boolean apply(int recruitment, String username){
        return ServiceManager.getInterviewerService()
                .apply(recruitment, username);
    }

    public List<Recruitment> query(int position){
        return ServiceManager
                .getInterviewerService()
                .query(position);
    }

    public List<Recruitment> query(int position, String startDate, String endDate){
        return ServiceManager
                .getInterviewerService()
                .query(position, startDate, endDate);
    }
}

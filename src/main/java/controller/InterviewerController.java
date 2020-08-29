package controller;


import pojo.Interviewer;
import pojo.Interviewer_Info;
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

    public  boolean addAbilityInfo(String username,int position,String info){
        return ServiceManager.getInterviewerService().addAbilityInfo(username,position,info);
    }

    
    public boolean apply(int recruitment_id, String username){
        return ServiceManager.getInterviewerService()
                .apply(recruitment_id, username);
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
    
    public Interviewer_Info show_status(String username){
        return ServiceManager.getInterviewerService().show_status(username);
    }
}

package controller;


import pojo.Interviewer;
import service.impl.ServiceManager;

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

    public Interviewer query(String username){
        return ServiceManager.getInterviewerService()
                .query(username);
    }
}

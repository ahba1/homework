package service.impl;

import service.AdminService;
import service.InterviewerService;

public class ServiceManager {

    private static AdminService adminService;

    private static InterviewerService interviewerService;

    public static AdminService getAdminService(){
        if (adminService==null){
            adminService = new AdminServiceImpl();
        }
        return adminService;
    }

    public static InterviewerService getInterviewerService(){
        if (interviewerService==null){
            interviewerService = new InterviewServiceImpl();
        }
        return interviewerService;
    }
}

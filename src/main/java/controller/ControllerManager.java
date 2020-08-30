package controller;

/**
 * created by caizhengheng on 2020/8/17
 * @author caizhengheng
 * @version 1.0
 */

public class ControllerManager {

    private static AdminController adminController = new AdminController();
    private static InterviewerController interviewerController = new InterviewerController();

    public static AdminController getAdminController(){
        return adminController;
    }

    public static InterviewerController getInterviewerController(){
        return interviewerController;
    }
}

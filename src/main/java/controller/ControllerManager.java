package controller;

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

import controller.ControllerManager;
import pojo.Interviewer;
import pojo.Recruitment;

import java.util.List;
import java.util.Scanner;

/**
 * created by zhouliang on 2020/8/12
 * @author zhouliang
 * @version 1.0
 */
public class Application {

    private static Scanner scanner = new Scanner(System.in);
    private static int identify;
    private static String user;
    public static void main(String[] args) {
        System.out.println("==========welcome to use this project==========");
        System.out.println("please chose your identify(admin(0) or interviewer(1)?)");
        identify = scanner.nextInt();
        System.out.println("register(0) or login(1)?");
        int chose = scanner.nextInt();
        if (chose==0){
            register();
        }
        login();
        showMenu();
    }

    private static void register(){
        String username;
        String password;
        String passwordAgain;
        String name;

        switch (identify){
            case 0:
                do {
                    System.out.println("name:");
                    name = scanner.next();
                    System.out.println("username:");
                    username = scanner.next();
                    System.out.println("password:");
                    password = scanner.next();
                    System.out.println("password again:");
                    passwordAgain = scanner.next();
                }while (ControllerManager.getAdminController().register(name, username, password, passwordAgain));
                break;
            case 1:
                do {
                    System.out.println("name:");
                    name = scanner.next();
                    System.out.println("username:");
                    username = scanner.next();
                    System.out.println("password:");
                    password = scanner.next();
                    System.out.println("password again:");
                    passwordAgain = scanner.next();
                }while (ControllerManager.getInterviewerController().register(name, username, password, passwordAgain));
                break;
        }
    }
    private static void login(){
        String username;
        String password;

        switch (identify){
            case 0:
                do {
                    System.out.println("login");
                    System.out.println("username:");
                    username = scanner.next();
                    System.out.println("password");
                    password = scanner.next();
                }while (ControllerManager.getAdminController()
                    .login(username, password));
                user = username;
                break;
            case 1:
                do {
                    System.out.println("login");
                    System.out.println("username:");
                    username = scanner.next();
                    System.out.println("password");
                    password = scanner.next();
                }while (ControllerManager.getInterviewerController()
                        .login(username, password));
                user = username;
                break;
        }
    }

    private static void showMenu(){
        int order;
        switch (identify){
            case 0:
                do {
                    System.out.println("0:publish recruitment");
                    System.out.println("1:queryRecruitment");
                    System.out.println("2:mark");
                    System.out.println("3:queryInterViewer");
                    System.out.println("4:exit");
                    order = scanner.nextInt();
                    adminHandle(order);
                }while (order!=4);
                break;
            case 1:
                System.out.println("0:delivery");
                System.out.println("1:query");
        }
    }

    private static void adminHandle(int order){
        switch (order){
            case 0:
                System.out.println("position:");
                System.out.println("JAVA(0)");
                System.out.println("C(1)");
                System.out.println("PYTHON(2)");
                System.out.println("PHP(3)");
                System.out.println("JS(4)");
                int position = scanner.nextInt();
                System.out.println("startDate(yyyy-MM-dd HH-mm-ss)");
                String startDate = scanner.next();
                System.out.println("endDate(yyyy-MM-dd HH-mm-ss)");
                String endDate = scanner.next();
                Recruitment recruitment = ControllerManager.getAdminController()
                        .publishRecruitment(position, startDate, endDate);
                System.out.println("this recruitment's id is"+recruitment.getId());
                break;
            case 1:
                System.out.println("the recruitment's id");
                int recruitmentId = scanner.nextInt();
                Recruitment publishedRecruitment = ControllerManager.getAdminController()
                        .query(recruitmentId);
                List<Interviewer> interviewers = ControllerManager.getAdminController()
                    .screen(recruitmentId);
                showInfo(publishedRecruitment, interviewers);
                break;
            case 2:
                System.out.println("the id of the recruitment");
                int id = scanner.nextInt();
                System.out.println("the username of the marked interviewer");
                String markedUsername = scanner.next();
                System.out.println("description");
                String description = scanner.next();
                System.out.println("recruit he(she)?(0:recruit, 1:don't)");
                int isRecruited = scanner.nextInt();
                ControllerManager.getAdminController()
                        .mark(markedUsername, id, isRecruited, description);
            case 3:
                System.out.println("the username of the interviewer");
                String username = scanner.next();
                Interviewer interviewer = ControllerManager.getInterviewerController()
                        .query(username);
                showInfo(interviewer);
        }
    }

    private static void showInfo(Recruitment recruitment, List<Interviewer> interviewers){
        System.out.println("position:"+recruitment.getPosition().toString());
        for (Interviewer interviewer:interviewers){
            System.out.println(interviewer.getName()+"  "+interviewer.getUsername());
        }
    }

    private static void showInfo(Interviewer interviewer){
        System.out.println(interviewer.getName()+" "+interviewer.getUsername());
    }
}

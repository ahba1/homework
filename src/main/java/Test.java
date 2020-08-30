import controller.BaseController;
import controller.ControllerManager;
import dao.mapper.AdminSqlMapper;
import ormliked.SqlMapper;
import pojo.*;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * created by liushirui on 2020/8/21
 * @author liushirui
 * @version 1.0
 */


public class Test {

    private static String iver;
    private static int com;
    public static void main(String[] args) {
//        String pattern = "#\\{.*\\}";
//
//        String sql = "select * from user where id = #{id}";
//
//        System.out.println(sql.replaceFirst(pattern, "2"));
//
//        Method[] methods = Test.class.getDeclaredMethods();
//
//        for (Method method:methods){
//            System.out.println(method.getReturnType().equals(Void.TYPE));
//        }

        System.out.println("欢迎使用招聘系统：请选择服务类型 1：管理员 2：用户");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                func_admin();
                break;
            case 2:
                func_interviewer();
                break;
            default:
                System.exit(0);
        }
    }



    private static void func_admin(){

        System.out.println("请选择管理员功能：1:登录后操作 2：注册");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 1: Alogin_then_func();
                    break;
            case 2: Aregister();
                    break;
            default:
                System.exit(0);
        }
    }

    private static void Aregister() {
        System.out.println("注册请输入：昵称 用户名 密码 确认密码 公司id");
        Scanner sc=new Scanner(System.in);
        String name=sc.next();
        String username=sc.next();
        String password=sc.next();
        String passwordAgain=sc.next();
        int com_id=sc.nextInt();
        boolean res=ControllerManager.getAdminController().register(name,username,password,passwordAgain,com_id);
        com=com_id;
        if(res==false) {
            while (res != true) {
                System.out.println("重新输入：昵称 用户名 密码 确认密码 公司id");
                sc = new Scanner(System.in);
                name = sc.next();
                username = sc.next();
                password = sc.next();
                passwordAgain = sc.next();
                com_id = sc.nextInt();
                res = ControllerManager.getAdminController().register(name, username, password, passwordAgain, com_id);
                com = com_id;
            }
        }
        System.out.println("注册成功!请返回登录");
        func_admin();
    }


    private static void Alogin_then_func(){
        System.out.println("请输入：用户名 密码");
        Scanner sc=new Scanner(System.in);
        String username=sc.next();
        String password=sc.next();
        boolean res=ControllerManager.getAdminController().login(username,password);
        com=ControllerManager.getAdminController().getCompanyId(username);
//        System.out.println(com);
        if(res==false) {
            while (res != true) {
                System.out.println("登陆失败，密码错误或者用户不存在");
                System.out.println("请选择：1：重新输入 2：返回");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        Scanner s=new Scanner(System.in);
                        System.out.println("重新输入：用户名 密码");
                        username = s.next();
                        password = s.next();
                        res = ControllerManager.getAdminController().login(username, password);
                        com=ControllerManager.getAdminController().getCompanyId(username);
                        break;
                    case 2:
                        func_admin();
                        break;
                    default:
                        System.out.println("退出");
                        System.exit(0);
                }
            }
        }else if(res==true){
            System.out.println("登录成功！");
            admin_service();
        }
    }

    private static void admin_service(){
        System.out.println("请选择功能：\n0: 更改公司信息 9:显示公司信息 10：查看公司所有招聘信息id\n1：添加招聘信息 2：查看所有申请信息 3:按用户名查询面试者 4:确认招聘面试者\n" +
                "5:查看所有招聘信息 6：查询适合职位id的所有面试者 7:邀请参加面试 8:删除招聘信息");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 0:
                updateCompanyInfo(sc);
            case 1:
                publish_re(sc);
                break;
            case 2:
                request_screen();
                break;
            case 3:
                queryByInterviewerUsername(sc);
                break;
            case 4:
                hired(sc);
                break;
            case 5:
                re_all_screen();;
                break;
            case 6:
                auto_select(sc);
            case 7:
                ask_apply(sc);
            case 8:
                delete_re(sc);
                break;
            case 9:
                showCompanyInfo(com);
                break;
            case 10:
                showCompanyRe(com);
            default:
                System.exit(0);

        }
    }

    private static void showCompanyRe(int com_id) {
        System.out.println("本公司发布的所有招聘信息的id：");
        List<Company_Re> cr=ControllerManager.getAdminController().selectCompanyRe(com_id);
        for(Company_Re a:cr){
            System.out.println("com_id: "+a.getCom_id()+" re_id: "+a.getRe_id());
        }
        admin_service();
    }

    private static void showCompanyInfo(int com_id) {
        System.out.println("显示公司信息：");
        Company c=ControllerManager.getAdminController().selectCompanyInfo(com_id);
        System.out.println(c.getInfo());
        admin_service();
    }

    private static void updateCompanyInfo(Scanner sc) {
        System.out.println("请输入更改后的公司信息：");
        String info=sc.next();
        boolean res=ControllerManager.getAdminController().updateCompanyInfo(com,info);
        if(res==true)
            System.out.println("更改成功！");
        else System.out.println("更改失败！");
        admin_service();
    }

    private static void ask_apply(Scanner sc) {
        System.out.println("请输入：招聘信息id 面试者用户名");
        int re_id=sc.nextInt();
        String username=sc.next();
        boolean res=ControllerManager.getInterviewerController().apply(re_id,username);
        if(res==true){
            System.out.println("邀请成功!");

        }else{
            System.out.println("邀请失败！");;
        }
        admin_service();
    }

    private static void auto_select(Scanner sc) {
        System.out.println("请输入职位id：");
        int pos=sc.nextInt();
        List<Interviewer_Pos> i=ControllerManager.getAdminController().auto_select(pos);
        if(i.isEmpty()) {
            System.out.println("找不到符合职位的面试者");
        } else{
            for (Interviewer_Pos p : i) {
                System.out.println("username: " + p.getUsername() + " position: " + p.getPosition() + " info: " + p.getInfo());
            }
        }
        admin_service();
    }

    private static void re_all_screen() {
        System.out.println("显示所有招聘信息：");
        List<Recruitment> re=ControllerManager.getAdminController().re_all_screen();
        for(Recruitment a:re){
            System.out.print("re_id: "+a.getId()+" position: "+a.getPosition()+" StartDate: "
                    +a.getStartDate()+" EndDate: "+a.getEndDate()+" isFull: "+a.getStatus());
            System.out.println(" Position number: "+ControllerManager.getAdminController().position_num(a.getId()));
        }
        admin_service();
    }

    private static void queryByInterviewerUsername(Scanner sc) {
        System.out.println("请输入想查询用户的用户名：");
        String username=sc.next();
        Interviewer_Info ifo=ControllerManager.getAdminController().query_interviewer(username);
        System.out.println("re_id: "+ifo.getRe_id()+" IsRecruited: "+ifo.getIsRecruited()+" Description: "+ifo.getDescription());
        admin_service();
    }

    private static void publish_re(Scanner sc){
        System.out.println("添加招聘信息：（时间格式：yyyy-mm-dd）\n请输入：职位id 数目 起始时间 结束时间 ");
        int position=sc.nextInt();
        int number=sc.nextInt();
        String s_date=sc.next();
        String e_date=sc.next();
        boolean res=ControllerManager.getAdminController().publishRecruitment(position,number,s_date,e_date,com);
        if(res==false) {
            while (res != true) {
                System.out.println("添加失败！");
                System.out.println("请选择：1：重新添加 2：返回");
                int cho = sc.nextInt();
                switch (cho) {
                    case 1:
                        Scanner s=new Scanner(System.in);
                        System.out.println("请重新添加招聘信息：（时间格式：yyyy-mm-dd）\n请输入：职位id 数目 起始时间 结束时间 ");
                        position=s.nextInt();
                        number=s.nextInt();
                        s_date=s.next();
                        e_date=s.next();
                        res=ControllerManager.getAdminController().publishRecruitment(position,number,s_date,e_date,com);
                        break;
                    case 2:
                        admin_service();
                        break;
                    default:
                        System.exit(0);
                }
            }
        }else if(res==true){
            System.out.println("添加成功！");
            admin_service();
        }
    }
    private static void request_screen(){
        System.out.println("所有应聘请求：");
        List<Apply_Info> res=ControllerManager.getAdminController().screen();
        for(Apply_Info a:res){
            System.out.println("username: "+a.getUsername()+" re_id: "+a.getRe_id());
        }
        admin_service();
    }

    private static void hired(Scanner sc){
        System.out.println("输入确认雇佣信息：（状态：1：雇佣 0：未雇佣）\n面试者用户名 招聘信息id 状态 备注");
        String username=sc.next();
        int id=sc.nextInt();
        int status=sc.nextInt();
        String des=sc.next();
        boolean res=ControllerManager.getAdminController().mark(username,id,status,des);
        if(res==false) {
            while (res != true) {
                System.out.println("添加失败！");
                System.out.println("请选择：1：重新添加 2：返回");
                int cho = sc.nextInt();
                switch (cho) {
                    case 1:
                        System.out.println("重新输入雇佣信息：（状态：1：雇佣 2：未雇佣）\n面试者用户名 招聘信息id 状态");
                        username=sc.next();
                        id=sc.nextInt();
                        status=sc.nextInt();
                        des=sc.next();
                        res=ControllerManager.getAdminController().mark(username,id,status,des);
                        break;
                    case 2:
                        admin_service();
                        break;
                    default:
                        System.exit(0);
                }
            }
        }else if(res==true){
            System.out.println("雇佣成功！");
            admin_service();
        }

    }
    private static void delete_re(Scanner sc){
        System.out.println("请输入要删除的招聘信息id：");
        int id=sc.nextInt();
        Boolean res=ControllerManager.getAdminController().delete(id);
        if(res==false){
            System.out.println("删除失败！");
            System.out.println("请选择：1：重新删除 2：返回");
            int cho = sc.nextInt();
            switch (cho) {
                case 1:
                    System.out.println("重新输入要删除的招聘信息id：");
                    id=sc.nextInt();
                    res=ControllerManager.getAdminController().delete(id);
                    break;
                case 2:
                    admin_service();
                    break;
                default:
                    System.exit(0);
            }
        }else if(res==true){
            System.out.println("删除成功！");
            admin_service();
        }

    }

    private static void func_interviewer(){
        System.out.println("请选择用户功能：1:登录后操作 2：注册");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                Ilogin_then_func();
                break;
            case 2:
                Iregister();
                break;
            default:
                System.exit(0);
        }
    }

    private static void Iregister() {
        System.out.println("注册请输入：昵称 用户名 密码 确认密码");
        Scanner sc=new Scanner(System.in);
        String name=sc.nextLine();
        String username=sc.nextLine();
        String password=sc.nextLine();
        String passwordAgain=sc.nextLine();
        boolean res=ControllerManager.getInterviewerController().register(name,username,password,passwordAgain);
        if(res==false){
            while(res!=true){
                System.out.println("注册失败！");
                System.out.println("请重新输入：昵称 用户名 密码 确认密码");
                sc=new Scanner(System.in);
                name=sc.nextLine();
                username=sc.nextLine();
                password=sc.nextLine();
                passwordAgain=sc.nextLine();
                res=ControllerManager.getInterviewerController().register(name,username,password,passwordAgain);
            }
        }
        System.out.println("注册成功！");
        func_interviewer();
    }

    private static void Ilogin_then_func() {
        System.out.println("请输入：用户名 密码");
        Scanner sc=new Scanner(System.in);
        String username=sc.next();
        String password=sc.next();
        iver=username;
        boolean res=ControllerManager.getInterviewerController().login(username,password);

        if(res==false) {
            while (res != true) {
                System.out.println("登陆失败，密码错误或者用户不存在");
                System.out.println("请选择：1：重新输入 2：返回");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("重新输入：用户名 密码");
                        Scanner s=new Scanner(System.in);
                        username = s.next();
                        password = s.next();
                        res = ControllerManager.getInterviewerController().login(username, password);
                        iver=username;
                        break;
                    case 2:
                        func_interviewer();
                        break;
                    default:
                        System.out.println("退出");
                        System.exit(0);
                }
            }
        }else if(res==true){
            System.out.println("登录成功！");
            interviewer_service();
        }
    }

    private static void interviewer_service() {
        System.out.println("请选择功能：1: 添加能力 2:按职位查询 3：提交面试申请 4:查看录用信息");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 1:
               add_ability(sc);
               break;
            case 2:
                queryByPosition(sc);
                break;
            case 3:
                apply_job(sc);
                break;
            case 4:
                show_status();
                break;
            default:
                System.exit(0);

        }
    }

    private static void show_status() {
        Interviewer_Info info=ControllerManager.getInterviewerController().show_status(iver);
        System.out.println("username: "+info.getUsername()+" re_id: "+info.getRe_id()
                +" isRecruited: "+info.getIsRecruited()+" Description: "+info.getDescription());
        interviewer_service();
    }

    private static void add_ability(Scanner sc) {
        System.out.println("请输入：适合的职位id 对该职位能力的描述");
        String username=iver;
        int position=sc.nextInt();
        String info=sc.next();
        boolean res=ControllerManager.getInterviewerController().addAbilityInfo(username,position,info);
        if(res==true){
            System.out.println("添加成功！");
            interviewer_service();
        }else{
            System.out.println("添加失败！");
            interviewer_service();
        }

    }

    private static void apply_job(Scanner sc) {
        System.out.println("请输入想申请面试的招聘信息id：");
        int re_id=sc.nextInt();
        boolean res=ControllerManager.getInterviewerController().apply(re_id,iver);
        if(res==true){
            System.out.println(" 申请成功!");

        }else{
            System.out.println("申请失败！");;
        }
        interviewer_service();
    }

    private static void queryByPosition(Scanner sc) {
        System.out.println("请输入想查询的职位id：");
        int position=sc.nextInt();
        List<Recruitment> res=ControllerManager.getInterviewerController().query(position);
        for(Recruitment re:res){
            System.out.println("re_id: "+re.getId()+" position: "+re.getPosition()
                    +" StartDate: "+re.getStartDate()+" EndDate: "+re.getEndDate());
        }
        interviewer_service();

    }

}




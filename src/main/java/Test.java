import controller.BaseController;
import dao.SqlManager;
import dao.mapper.AdminSqlMapper;
import ormliked.SqlMapper;
import pojo.Admin;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {

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
//        try {
//            Class clazz = Class.forName("java.lang.String");
//            System.out.println(clazz.getName());
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
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
        System.out.println("注册请输入：昵称 用户名 密码 确认密码");
        Scanner sc=new Scanner(System.in);
        String name=sc.next();
        String username=sc.next();
        String password=sc.next();
        String passwordAgain=sc.next();
        boolean res=ControllerManager.getAdminController().register(name,username,password,passwordAgain);
        if(res==false){
            while(res!=true){
                System.out.println("重新输入：昵称 用户名 密码 确认密码");
                sc=new Scanner(System.in);
                name=sc.next();
                username=sc.next();
                password=sc.next();
                passwordAgain=sc.next();
                res=ControllerManager.getAdminController().register(name,username,password,passwordAgain);
            }
        } else if(res==true){
            System.out.println("注册成功!请返回登录");
            func_admin();
        }
    }


    private static void Alogin_then_func(){
        System.out.println("请输入：用户名 密码");
        Scanner sc=new Scanner(System.in);
        String username=sc.next();
        String password=sc.next();
        boolean res=ControllerManager.getAdminController().login(username,password);
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
        System.out.println("请选择功能：1：添加招聘信息 2：查看所有申请信息");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                publish_re(sc);
                break;
            case 2:
                request_screen();
                break;
            case 3:
                hired(sc);
                break;
            case 4:

            case 5:
                delete_re(sc);
                break;
            default:
                System.exit(0);

        }
    }
    private static void publish_re(Scanner sc){
        System.out.println("添加招聘信息：（时间格式：yyyy-mm-dd）\n请输入：职位id 数目 起始时间 结束时间 ");
        int position=sc.nextInt();
        int number=sc.nextInt();
        String s_date=sc.next();
        String e_date=sc.next();
        boolean res=ControllerManager.getAdminController().publishRecruitment(position,number,s_date,e_date);
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
                        res=ControllerManager.getAdminController().publishRecruitment(position,number,s_date,e_date);
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
        System.out.println("输入确认雇佣信息：（状态：1：雇佣 2：未雇佣）\n面试者用户名 招聘信息id 状态");
        String username=sc.nextLine();
        int id=sc.nextInt();
        int status=sc.nextInt();
        boolean res=ControllerManager.getAdminController().mark(username,id,status);
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
                        res=ControllerManager.getAdminController().mark(username,id,status);
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
        System.out.println();

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
        } else if(res==true){
            System.out.println("注册成功！");
            func_interviewer();
        }
    }

    private static void Ilogin_then_func() {
        System.out.println("请输入：用户名 密码");
        Scanner sc=new Scanner(System.in);
        String username=sc.nextLine();
        String password=sc.nextLine();
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
                        username = s.nextLine();
                        password = s.nextLine();
                        res = ControllerManager.getInterviewerController().login(username, password);
                        iver.setUsername(username);
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
        System.out.println("请选择功能：1:按职位查询 2：提交申请");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                queryByPosition(sc);
                break;
            case 2:
                apply_job(sc);
                break;
            case 3:

            case 4:

            case 5:

                break;
            default:
                System.exit(0);

        }
    }

    private static void apply_job(Scanner sc) {
        int re_id=sc.nextInt();
        boolean res=ControllerManager.getInterviewerController().apply(re_id,iver.getUsername());
        if(res==true){
            System.out.println("apply succeeded!");

        }else{
            System.out.println("error");;
        }
        interviewer_service();
    }

    private static void queryByPosition(Scanner sc) {
        int position=sc.nextInt();
        List<Recruitment> res=ControllerManager.getInterviewerController().query(position);
        for(Recruitment re:res){
            System.out.println("re_id:"+re.getId()+"position:"+re.getPosition()
                    +"StartDate:"+re.getStartDate()+"EndDate"+re.getEndDate());
        }
        interviewer_service();

    }
    
//         System.out.println(SqlManager.getAdminSqlMapper().insertRegisterInfo("zl","ahba1", "123456"));
//         //func();
//     }

//     private static void func(){
//         SqlManager.getAdminSqlMapper().selectAll();
//     }
}

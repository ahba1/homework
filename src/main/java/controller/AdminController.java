package controller;



import pojo.*;
import service.impl.ServiceManager;
import java.sql.Date;
import java.util.List;

/**
 * created by caizhengheng on 2020/8/17
 * @author caizhengheng
 * @version 1.0
 */

@BaseController(type = "Admin")
public class AdminController {
//登录
    public boolean login(String username, String password){
        return ServiceManager.getAdminService()
                .login(username, password);
    }
//注册
   public boolean register(String name, String username, String password, String passwordAgain,int com_id){
        return password.equals(passwordAgain) &&ServiceManager.getAdminService()
                .register(name, username, password,com_id);
    }

//添加招聘信息
    public boolean publishRecruitment(int position, int number, String startDate, String endDate,int com_id){
        return ServiceManager.getAdminService()
                .publishRecruitment(position, number, startDate, endDate,com_id);
    }
//按id查找所有面试信息    
    public List<Apply_Info> screen(int re_id){
        return ServiceManager.getAdminService()
                .screen(re_id);
    }
//查找所有面试信息
    public List<Apply_Info> screen(){
        return ServiceManager.getAdminService()
                .screen();
    }
//确认招聘情况
    public boolean mark(String interviewerUsername, int recruitment, int isRecruited,String des){
       return ServiceManager.getAdminService()
                .hire_confirmed(interviewerUsername, recruitment, isRecruited,des);
    }

//     public Recruitment query(int id){
//         return ServiceManager.getAdminService()
//                 .query(id);
//     }

//     public Interviewer query(String username){
//         return ServiceManager.getAdminService()
//                 .query(username);
//     }
    
//查找所有招聘信息    
    public List<Recruitment> re_all_screen(){
        return ServiceManager.getAdminService().re_all_screen();
    }
//获得职位剩余数目    
    public int position_num(int re_id){
        return ServiceManager.getAdminService().position_num(re_id);
    }
//按职位id查找所有招聘信息
    public List<Interviewer_Pos> auto_select(int position){
        return ServiceManager.getAdminService().auto_select(position);
    }
//按用户名查找录用信息    
    public Interviewer_Info query_interviewer(String username){
        return ServiceManager.getAdminService()
                .query_interviewer(username);
    }
    
//     public boolean deleteFull(int re_id){
//         return ServiceManager.getAdminService()
//                 .deleteFull(re_id);
//     }
//按id删除招聘信息
    public boolean delete(int re_id){
        return ServiceManager.getAdminService()
                .delete(re_id);
    }
        public boolean updateCompanyInfo(int com_id,String info){
        return ServiceManager.getAdminService().updateCompanyInfo(com_id,info);
    }
//获得公司id
    public int getCompanyId(String username){
        return ServiceManager.getAdminService().getCompanyId(username);
    }
//查看公司发布的所有招聘信息id    
     public List<Company_Re> selectCompanyRe(int com_id){
        return ServiceManager.getAdminService().selectCompanyRe(com_id);
    }
//查找公司信息
    public Company selectCompanyInfo(int com_id){
        return ServiceManager.getAdminService().selectCompanyInfo(com_id);
    }
}

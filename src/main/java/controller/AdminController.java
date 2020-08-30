package controller;



import pojo.*;
import service.impl.ServiceManager;
import java.sql.Date;
import java.util.List;


@BaseController(type = "Admin")
public class AdminController {

    public boolean login(String username, String password){
        return ServiceManager.getAdminService()
                .login(username, password);
    }

   public boolean register(String name, String username, String password, String passwordAgain,int com_id){
        return password.equals(passwordAgain) &&ServiceManager.getAdminService()
                .register(name, username, password,com_id);
    }

//改
    public boolean publishRecruitment(int position, int number, String startDate, String endDate,int com_id){
        return ServiceManager.getAdminService()
                .publishRecruitment(position, number, startDate, endDate,com_id);
    }
    
    public List<Apply_Info> screen(int re_id){
        return ServiceManager.getAdminService()
                .screen(re_id);
    }
    
    public List<Apply_Info> screen(){
        return ServiceManager.getAdminService()
                .screen();
    }

    public boolean mark(String interviewerUsername, int recruitment, int isRecruited,String des){
       return ServiceManager.getAdminService()
                .hire_confirmed(interviewerUsername, recruitment, isRecruited,des);
    }

    public Recruitment query(int id){
        return ServiceManager.getAdminService()
                .query(id);
    }

//     public Interviewer query(String username){
//         return ServiceManager.getAdminService()
//                 .query(username);
//     }
    
    public List<Recruitment> re_all_screen(){
        return ServiceManager.getAdminService().re_all_screen();
    }

    public List<Interviewer_Pos> auto_select(int position){
        return ServiceManager.getAdminService().auto_select(position);
    }
    
    public Interviewer_Info query_interviewer(String username){
        return ServiceManager.getAdminService()
                .query_interviewer(username);
    }
    
//     public boolean deleteFull(int re_id){
//         return ServiceManager.getAdminService()
//                 .deleteFull(re_id);
//     }

    public boolean delete(int re_id){
        return ServiceManager.getAdminService()
                .delete(re_id);
    }
        public boolean updateCompanyInfo(int com_id,String info){
        return ServiceManager.getAdminService().updateCompanyInfo(com_id,info);
    }

    public int getCompanyId(String username){
        return ServiceManager.getAdminService().getCompanyId(username);
    }
    
     public List<Company_Re> selectCompanyRe(int com_id){
        return ServiceManager.getAdminService().selectCompanyRe(com_id);
    }

    public Company selectCompanyInfo(int com_id){
        return ServiceManager.getAdminService().selectCompanyInfo(com_id);
    }
}

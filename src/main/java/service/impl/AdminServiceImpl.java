package service.impl;

import dao.SqlManager;
import pojo.*;
import service.AdminService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * created by bianshiran on 2020/8/26
 * @author bianshiran
 * @version 1.0
 */

public class AdminServiceImpl implements AdminService {

    @Override
    public boolean login(String username, String password) {
        Password p=SqlManager.getAdminSqlMapper().selectPasswordByUsername(username);
        if(p!=null){
            if(p.getPassword().equals(password))
                return true;
            else return false;
        }else{
            return false;
        }
    }

    @Override
    public boolean register(String name, String username, String password,int com_id) {
        boolean result=SqlManager.getAdminSqlMapper().insertRegisterInfo(name,username,password,com_id);
        if(result==false)
            return false;
        else {
            boolean result1 = SqlManager.getAdminSqlMapper().insertCompanyInfo(com_id);
            if (result1 == true)
                return true;
            else return false;
        }
    }

    @Override
    public boolean publishRecruitment(int position,int number, String startDate, String endDate,int com_id) {
//        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            java.util.Date sDate = bartDateFormat.parse(startDate);
//            java.util.Date eDate = bartDateFormat.parse(endDate);
//            java.sql.Date sqlStartDate = new java.sql.Date(sDate.getTime());
//            java.sql.Date sqlEndDate = new java.sql.Date(eDate.getTime());
//        }
//        catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
        //return SqlManager.getAdminSqlMapper().publishRecruitment(position,sqlStartDate,sqlEndDate);
        Recruitment_Max_id insert_id=SqlManager.getAdminSqlMapper().selectMax_id();
        long lid=insert_id.getMax_id();
        int id=(int)lid;
        id++;
        boolean case1=SqlManager.getAdminSqlMapper().publishRecruitment(id,position,startDate,endDate);
        if(case1==false)
            return false;
        else {
            boolean case2=SqlManager.getAdminSqlMapper().insertCompanyRe(com_id,id);
            if(case2==false)
                return false;
            else {
                boolean case3 = SqlManager.getAdminSqlMapper().insertPositionNumber(id, position, number);
                if (case3 == true)
                    return true;
                else return false;
            }
        }
    }

      @Override
    public List<Apply_Info> screen(int re_id) {
        return SqlManager.getAdminSqlMapper().screen(re_id);
    }
    //add
    public List<Apply_Info> screen(){
        return SqlManager.getAdminSqlMapper().screen();
    }
//改
 @Override
    public boolean hire_confirmed(String interviewerUsername, int recruitment_id, int isRecruited,String des) {
        if(SqlManager.getAdminSqlMapper().selectPositionNumber(recruitment_id).getNumber()>0) {
            if (isRecruited == 1) {
                boolean result = SqlManager.getAdminSqlMapper().updateHireInfo(recruitment_id, isRecruited,des, interviewerUsername);
                if(result==false)
                    return false;
                else{
                    boolean result1 = SqlManager.getAdminSqlMapper().updatePositionNumber(recruitment_id);
                    if (result1 == true)
                        return true;
                    else return false;
                }
            } else {
                return SqlManager.getAdminSqlMapper().updateHireInfo(recruitment_id, isRecruited,des, interviewerUsername);
            }
        }
        else{
            SqlManager.getAdminSqlMapper().updateFull(recruitment_id);
            return false;
        }
    }
    

    @Override
    public List<Recruitment> re_all_screen(){
        return SqlManager.getAdminSqlMapper().re_all_screen();
    }

    @Override
    public List<Interviewer_Pos> auto_select(int position){
        return SqlManager.getAdminSqlMapper().SelectByPosition(position);
    }
    
    @Override
    public Recruitment query(int id) {
        return SqlManager.getAdminSqlMapper().query(id);
    }

    @Override
    public Interviewer_Info query_interviewer(String username) {
        return SqlManager.getInterviewerSqlMapper().selectByPrimaryKey(username);
    }

     @Override
    public  boolean deleteFull(int re_id){
        if(SqlManager.getAdminSqlMapper().SelectByFull(re_id).getIsfull()==1)
            return SqlManager.getAdminSqlMapper().deleteByre_id(re_id);
        else return false;
    }

    @Override
    public boolean delete(int re_id){
        return SqlManager.getAdminSqlMapper().deleteByre_id(re_id);
    }

    @Override
    public boolean updateCompanyInfo(int com_id,String info){
        return SqlManager.getAdminSqlMapper().updateCompanyInfo(info,com_id);
    }

    @Override
    public int getCompanyId(String username){
        Company_id id=SqlManager.getAdminSqlMapper().SelectCompanyId(username);
        if(id==null)
            return -1;
        else{
        int i=id.getCom_id();
        return i;
        }
    }
    
    @Override
    public List<Company_Re> selectCompanyRe(int com_id){
        return SqlManager.getAdminSqlMapper().SelectCompanyRe(com_id);
    }

    @Override
    public Company selectCompanyInfo(int com_id){
        return SqlManager.getAdminSqlMapper().SelectCompanyInfo(com_id);
    }
    
    @Override
    public int position_num(int re_id){
        return SqlManager.getAdminSqlMapper().selectPositionNumber(re_id).getNumber();
    }
}

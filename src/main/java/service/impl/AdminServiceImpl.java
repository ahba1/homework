package service.impl;

import dao.SqlManager;
import pojo.Interviewer;
import pojo.Recruitment;
import service.AdminService;
import pojo.Recruitment_Max_id;

import java.text.SimpleDateFormat;
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
    public boolean register(String name, String username, String password) {
        boolean result=SqlManager.getAdminSqlMapper().insertRegisterInfo(name,username,password);
        return result;
    }

    @Override
    public boolean publishRecruitment(int position,int number, String startDate, String endDate) {
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
//      return SqlManager.getAdminSqlMapper().publishRecruitment(position,sqlStartDate,sqlEndDate);
        Recruitment_Max_id insert_id=SqlManager.getAdminSqlMapper().selectMax_id();
        long lid=insert_id.getMax_id();
        int id=(int)lid;
        id++;
        boolean case1=SqlManager.getAdminSqlMapper().publishRecruitment(id,position,startDate,endDate);
        boolean case2=SqlManager.getAdminSqlMapper().insertPositionNumber(id,position,number);
        if(case1==true &&case2==true)
            return true;
        else return false;
    }

    @Override
    public List<Interviewer> screen(int id) {
        return SqlManager.getAdminSqlMapper().screen(id);
    }
//改
    @Override
    public boolean hire_confirmed(String interviewerUsername, int recruitment_id, int isRecruited) {
        if(SqlManager.getAdminSqlMapper().selectPositionNumber(recruitment_id).getNumber()>0) {
            if (isRecruited == 1) {
                boolean result = SqlManager.getAdminSqlMapper().updateHireInfo(recruitment_id, isRecruited, interviewerUsername);
                boolean result1 = SqlManager.getAdminSqlMapper().updatePositionNumber(recruitment_id);
                if (result == true && result1 == true)
                    return true;
                else return false;
            } else {
                return SqlManager.getAdminSqlMapper().updateHireInfo(recruitment_id, isRecruited, interviewerUsername);
            }
        }
        else{
            SqlManager.getAdminSqlMapper().updateFull(recruitment_id);
            return false;
        }
    }

    
    
    @Override
    public Recruitment query(int id) {
        return SqlManager.getAdminSqlMapper().query(id);
    }

    @Override
    public Interviewer query(String username) {
        return SqlManager.getAdminSqlMapper().query(username);
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
}

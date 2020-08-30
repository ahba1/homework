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
//登录操作
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
//注册操作
    @Override
    public boolean register(String name, String username, String password,int com_id) {
        boolean result=SqlManager.getAdminSqlMapper().insertRegisterInfo(name,username,password,com_id);
        if(result==false)
            return false;
        else {
            boolean result1 = SqlManager.getAdminSqlMapper().insertCompanyInfo(com_id);//注册时完成公司信息的创建
            if (result1 == true)
                return true;
            else return false;
        }
    }
//添加招聘信息
    @Override
    public boolean publishRecruitment(int position,int number, String startDate, String endDate,int com_id) {
        Recruitment_Max_id insert_id=SqlManager.getAdminSqlMapper().selectMax_id();//先获得最大招聘信息id进行+1操作后插入招聘信息
        long lid=insert_id.getMax_id();
        int id=(int)lid;
        id++;
        boolean case1=SqlManager.getAdminSqlMapper().publishRecruitment(id,position,startDate,endDate);
        if(case1==false)
            return false;
        else {
            boolean case2=SqlManager.getAdminSqlMapper().insertCompanyRe(com_id,id);//插入公司发布信息表
            if(case2==false)
                return false;
            else {
                boolean case3 = SqlManager.getAdminSqlMapper().insertPositionNumber(id, position, number);//添加职位数目
                if (case3 == true)
                    return true;
                else return false;
            }
        }
    }
//按id查看所有招聘信息
      @Override
    public List<Apply_Info> screen(int re_id) {
        return SqlManager.getAdminSqlMapper().screen(re_id);
    }
//查看所有招聘信息
    public List<Apply_Info> screen(){
        return SqlManager.getAdminSqlMapper().screen();
    }
//确认招聘
 @Override
    public boolean hire_confirmed(String interviewerUsername, int recruitment_id, int isRecruited,String des) {
        if(SqlManager.getAdminSqlMapper().selectPositionNumber(recruitment_id).getNumber()>0) {//判断职位是否非满
            if (isRecruited == 1) {
                boolean result = SqlManager.getAdminSqlMapper().updateHireInfo(recruitment_id, isRecruited,des, interviewerUsername);//更新录用信息
                if(result==false)
                    return false;
                else{
                    boolean result1 = SqlManager.getAdminSqlMapper().updatePositionNumber(recruitment_id);//更新剩余职位数目
                    if (result1 == true)
                        return true;
                    else return false;
                }
            } else {
                return SqlManager.getAdminSqlMapper().updateHireInfo(recruitment_id, isRecruited,des, interviewerUsername);//没被录取则不用对职位信息做变动
            }
        }
        else{
            SqlManager.getAdminSqlMapper().updateFull(recruitment_id);//插入失败并将招聘信息状态更新
            return false;
        }
    }
    
//查看所有招聘信息
    @Override
    public List<Recruitment> re_all_screen(){
        return SqlManager.getAdminSqlMapper().re_all_screen();
    }
//按职位查看符合的所有面试者信息
    @Override
    public List<Interviewer_Pos> auto_select(int position){
        return SqlManager.getAdminSqlMapper().SelectByPosition(position);
    }

//按用户名查看录用信息
    @Override
    public Interviewer_Info query_interviewer(String username) {
        return SqlManager.getInterviewerSqlMapper().selectByPrimaryKey(username);
    }
//按id删除招聘信息
     @Override
    public  boolean deleteFull(int re_id){
        if(SqlManager.getAdminSqlMapper().SelectByFull(re_id).getIsfull()==1)
            return SqlManager.getAdminSqlMapper().deleteByre_id(re_id);
        else return false;
    }
//按id删除招聘信息
    @Override
    public boolean delete(int re_id){
        return SqlManager.getAdminSqlMapper().deleteByre_id(re_id);
    }
//更新公司信息
    @Override
    public boolean updateCompanyInfo(int com_id,String info){
        return SqlManager.getAdminSqlMapper().updateCompanyInfo(info,com_id);
    }
//获得公司id
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
//查看公司发布的所有招聘信息id    
    @Override
    public List<Company_Re> selectCompanyRe(int com_id){
        return SqlManager.getAdminSqlMapper().SelectCompanyRe(com_id);
    }
//查找公司信息
    @Override
    public Company selectCompanyInfo(int com_id){
        return SqlManager.getAdminSqlMapper().SelectCompanyInfo(com_id);
    }
//获得职位数目    
    @Override
    public int position_num(int re_id){
        return SqlManager.getAdminSqlMapper().selectPositionNumber(re_id).getNumber();
    }
}

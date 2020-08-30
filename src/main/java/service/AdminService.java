package service;

import pojo.*;


import java.sql.Date;
import java.util.List;

/**
 * created by bianshiran on 2020/7/25
 * @author bianshiran
 * @version 1.0
 */
@Service
public interface AdminService {

    /**登录
     * @param username
     * @param password
     * @return is the login successful.
     */
    boolean login(String username, String password);

    /**注册
     * @param name
     * @param username
     * @param password
     * @return is register successful.
     */
    boolean register(String name, String username, String password,int com_id);

    //更新公司信息
    boolean updateCompanyInfo(int com_id,String info);


    /**添加招聘信息
     * @param position
     * @param startDate
     * @param endDate
     * @return boolean
     */
    //改
    boolean publishRecruitment(int position, int number,String startDate, String endDate,int com_id);

    /**
     * @param id
     * @return the interviewers who satisfy the demand
     */
    List<Apply_Info> screen(int re_id);

    List<Apply_Info> screen();

    /**确认招聘
     * @param interviewerUsername
     * @param recruitment_id
     * @param isRecruited
     * @param des
     */
    boolean hire_confirmed(String interviewerUsername, int recruitment_id, int isRecruited,String des);

    /**按id查找招聘信息
     *@param id
     * @return
     */
    Recruitment query(int id);
    //查看所有招聘信息
    List<Recruitment> re_all_screen();
    //获得职位数目
    int position_num(int re_id);
    //查看所有符合职位id的面试者
    List<Interviewer_Pos> auto_select(int position);
    //查看公司发布的所有招聘信息id
    List<Company_Re> selectCompanyRe(int com_id);
    //查找公司信息
    Company selectCompanyInfo(int com_id);

    /**查看录用信息
     *
     * @param username
     * @return Interviewer_Info
     */
    Interviewer_Info query_interviewer(String username);
    //删除招聘信息
    boolean deleteFull(int re_id);
    //删除招聘信息
    boolean delete(int re_id);
    //获得公司id
    int getCompanyId(String username);

}

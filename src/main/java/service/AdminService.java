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

    /**
     * @param username
     * @param password
     * @return is the login successful.
     */
    boolean login(String username, String password);

    /**
     * @param name
     * @param username
     * @param password
     * @return is register successful.
     */
    boolean register(String name, String username, String password,int com_id);

    boolean updateCompanyInfo(int com_id,String info);


    /**
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

    /**
     *
     * @param interviewerUsername
     * @param recruitment_id
     * @param isRecruited
     * @param info
     */
    boolean hire_confirmed(String interviewerUsername, int recruitment_id, int isRecruited,String des);

    /**
     *
     * @return
     */
    Recruitment query(int id);

    List<Recruitment> re_all_screen();
    
    int position_num(int re_id);

    List<Interviewer_Pos> auto_select(int position);
    
    List<Company_Re> selectCompanyRe(int com_id);

    Company selectCompanyInfo(int com_id);

    /**
     *
     * @param username
     * @return
     */
    Interviewer_Info query_interviewer(String username);

    boolean deleteFull(int re_id);

    boolean delete(int re_id);

    int getCompanyId(String username);

}

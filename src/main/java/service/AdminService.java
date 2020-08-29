package service;

import pojo.DeliveryInfo;
import pojo.Interviewer;
import pojo.Position;
import pojo.Recruitment;

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
    boolean register(String name, String username, String password);

    /**
     * @param position
     * @param startDate
     * @param endDate
     * @return boolean
     */
    //改
    boolean publishRecruitment(int position, int number,String startDate, String endDate);

    /**
     * @param id
     * @return the interviewers who satisfy the demand
     */
    List<Interviewer> screen(int id);

    /**
     *
     * @param interviewerUsername
     * @param recruitment_id
     * @param isRecruited
     * @param info
     */
    boolean hire_confirmed(String interviewerUsername, int recruitment_id, int isRecruited);

    /**
     *
     * @return
     */
    Recruitment query(int id);

    /**
     *
     * @param username
     * @return
     */
    Interviewer query(String username);
    
    boolean deleteFull(int re_id);

    boolean delete(int re_id);
}

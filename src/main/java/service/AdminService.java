package service;

import pojo.DeliveryInfo;
import pojo.Interviewer;
import pojo.Position;
import pojo.Recruitment;

import java.sql.Date;
import java.util.List;

/**
 * created by zhouliang on 2020/7/25
 * @author zhouliang
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
     * @return the recruitment info
     */
    Recruitment publishRecruitment(int position, String startDate, String endDate);

    /**
     * @param id
     * @return the interviewers who satisfy the demand
     */
    List<Interviewer> screen(int id);

    /**
     *
     * @param interviewerUsername
     * @param recruitment
     * @param isRecruited
     * @param info
     */
    void remark(String interviewerUsername, int recruitment, int isRecruited, String info);

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
}

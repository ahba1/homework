package service;

import pojo.Recruitment;

import java.util.List;

/**
 * created by zhouliang on 2020/7/25
 * @author zhouliang
 * @version 1.0
 */
@Service
public interface InterviewerService {

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
    
    //new
    boolean setInterviewerInfo(String username,String description);

    /**
     *
     * @param position
     * @param startDate
     * @param endDate
     * @return
     */
    List<Recruitment> query(int position, String startDate, String endDate);

    /**
     *
     * @param position
     * @return
     */
    List<Recruitment> query(int position);

    /**
     * @param recruitment_id the recruitment's id
     * @param username the interviewer's username
     * @return
     */
     boolean apply(int recruitment_id, String username);
}

package service;

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

    /**
     *
     * @param position
     * @param startDate
     * @param endDate
     * @return
     */
    List<Recruitment> query(Position position, Date startDate, Date endDate);

    /**
     *
     * @param position
     * @return
     */
    List<Recruitment> query(Position position);

    /**
     * @param recruitment
     * @param interviewer
     * @return
     */
    boolean apply(Recruitment recruitment, Interviewer interviewer);

    /**
     *
     * @param username
     * @return
     */
    Interviewer query(String username);
}

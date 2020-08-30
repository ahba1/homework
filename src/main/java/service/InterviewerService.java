package service;

import pojo.*;

import java.util.List;

/**
 * created by bianshiran on 2020/7/25
 * @author bianshiran
 * @version 1.0
 */
@Service
public interface InterviewerService {

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
    boolean register(String name, String username, String password);

    //创建录用信息
    boolean setInterviewerInfo(String username,int re_id,int status,String description);
    //添加职位能力
    boolean addAbilityInfo(String username,int position,String info);


    /**按职位id查询招聘信息
     *
     * @param position
     * @return
     */
    List<Recruitment> query(int position);
   
    /**提交面试申请
     * @param recruitment_id the recruitment's id
     * @param username the interviewer's username
     * @return
     */
    boolean apply(int recruitment_id, String username);
    //查看录用状态
    Interviewer_Info show_status(String username);
}

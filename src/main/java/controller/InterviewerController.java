package controller;


import pojo.Interviewer;
import pojo.Interviewer_Info;
import pojo.Recruitment;

import service.impl.ServiceManager;

import java.sql.Date;

import java.util.List;

/**
 * created by caizhengheng on 2020/8/17
 * @author caizhengheng
 * @version 1.0
 */

@BaseController(type = "Interviewer")
public class InterviewerController {
//登录
    public boolean login(String username, String password){
        return ServiceManager.getInterviewerService()
                .login(username, password);
    }
//注册
    public boolean register(String name, String username, String password, String passwordAgain){
        return password.equals(passwordAgain)
                &&ServiceManager.getInterviewerService()
                .register(name, username, password);
    }
//添加职位能力
    public  boolean addAbilityInfo(String username,int position,String info){
        return ServiceManager.getInterviewerService().addAbilityInfo(username,position,info);
    }

//添加面试请求    
    public boolean apply(int recruitment_id, String username){
        return ServiceManager.getInterviewerService()
                .apply(recruitment_id, username);
    }
    
    
//按职位查询所有招聘信息id
    public List<Recruitment> query(int position){
        return ServiceManager
                .getInterviewerService()
                .query(position);
    }
//显示录用信息  
    public Interviewer_Info show_status(String username){
        return ServiceManager.getInterviewerService().show_status(username);
    }
}

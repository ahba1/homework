package service.impl;

import pojo.Interviewer;
import pojo.Position;
import pojo.Recruitment;
import service.InterviewerService;

import java.sql.Date;
import java.util.List;

public class InterviewServiceImpl implements InterviewerService {
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean register(String name, String username, String password) {
        return false;
    }

    @Override
    public List<Recruitment> query(Position position, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Recruitment> query(Position position) {
        return null;
    }

    @Override
    public boolean apply(Recruitment recruitment, Interviewer interviewer) {
        return false;
    }
}

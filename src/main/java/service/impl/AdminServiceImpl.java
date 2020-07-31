package service.impl;

import pojo.DeliveryInfo;
import pojo.Interviewer;
import pojo.Position;
import pojo.Recruitment;
import service.AdminService;

import java.sql.Date;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean register(String name, String username, String password) {
        return false;
    }

    @Override
    public Recruitment publishRecruitment(Position position, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Interviewer> screen(Recruitment recruitment) {
        return null;
    }

    @Override
    public void remark(DeliveryInfo info) {

    }

    @Override
    public Recruitment query() {
        return null;
    }
}

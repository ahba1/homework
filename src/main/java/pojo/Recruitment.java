package pojo;

import java.sql.Date;

public class Recruitment {

    private int id;
    private Position[] positions;
    private Date startDate;
    private Date endDate;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package pojo;

import java.sql.Date;

public class Recruitment {
    private int re_id;
    private int position;
    private String start_date;
    private String end_date;
    private int isfull;
    
    public int getId() {
        return re_id;
    }
    
    public void setId(int id) {
        this.re_id = id;
    }
    
    public int getPosition() {
        return position;
    }



    public void setPositions(int position) {
        this.position = position;
    }



    public String getStartDate() {
        return start_date;
    }
    
    public void setStartDate(String startDate) {
        this.start_date = startDate;
    }
    
    public String getEndDate() {
        return end_date;
    }
    
    public void setEndDate(String endDate) {
        this.end_date = endDate;
    }

    public int getStatus() {
        return isfull;
    }
    
    public void setStatus(int status) {
        this.isfull = status;
    }
}

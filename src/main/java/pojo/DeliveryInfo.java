package pojo;

public class DeliveryInfo {

    private Interviewer interviewer;
    private Recruitment recruitment;
    private boolean isRecruited;
    private String info;

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public boolean isRecruited() {
        return isRecruited;
    }

    public void setRecruited(boolean recruited) {
        isRecruited = recruited;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

package pojo;

public class Interviewer {

    private String name;
    private String username;
    private String password;
    private Position[] positions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Position p:positions){
            sb.append(p.getName()).append("\\");
        }
        return name+" "+username+" position: "+sb.toString();
    }
}

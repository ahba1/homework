package pojo;

public enum Position {

    JAVA("Java", "Java后端开发工程师，需要熟练掌握JavaSE和JavaEE，以及常用JavaWeb框架"),
    C("C", "C语言嵌入式工程师，需要熟练掌握使用C语言，FPGA，汇编等嵌入式方向使用的语言和工具"),
    PYTHON("Python", "python算法工程师，需要熟练掌握python语言以及tensorflow或pytorch其中任一框架"),
    PHP("php", "php后端工程师，需要熟练掌握php语言"),
    JS("JavaScript", "js前端工程师，需要熟练掌握js");

    Position(String name, String description){
        this.name = name;
        this.description = description;
    }

    private String name;
    private String description;

    @Override
    public String toString() {
        return super.toString();
    }
}

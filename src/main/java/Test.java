public class Test {

    public static void main(String[] args) {
        String pattern = "#\\{.*\\}";

        String sql = "select * from user where id = #{id}";

        System.out.println(sql.replaceFirst(pattern, "2"));

    }
}

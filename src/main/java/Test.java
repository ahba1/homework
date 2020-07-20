import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {
        String pattern = "#\\{.*\\}";

        String sql = "select * from user where id = #{id}";

        System.out.println(sql.replaceFirst(pattern, "2"));

        Method[] methods = Test.class.getDeclaredMethods();

        for (Method method:methods){
            System.out.println(method.getReturnType().equals(Void.TYPE));
        }
    }

    private void func(){

    }
}

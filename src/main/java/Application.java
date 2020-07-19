import dao.SqlManager;
import pojo.Admin;

import java.util.List;


public class Application {


    public static void main(String[] args) {
        Admin admin = SqlManager.getAdminSqlService()
                .selectByPrimaryKey(2);
        System.out.println(admin.getId());

        List<Admin> lists = SqlManager.getAdminSqlService()
                .selectAll();

        for (Admin ad:lists){
            System.out.println(ad.getId());
        }
    }
}

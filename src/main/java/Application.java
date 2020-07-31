import dao.SqlManager;
import dao.mapper.AdminSqlMapper;
import pojo.Admin;

import java.util.List;


public class Application {


    public static void main(String[] args) {
        Admin admin = SqlManager.getAdminSqlMapper()
                .selectByPrimaryKey(2);
        System.out.println(admin.getId());

        List<Admin> lists = SqlManager.getAdminSqlMapper()
                .selectAll();

        for (Admin ad:lists){
            System.out.println(ad.getId());
        }
    }
}

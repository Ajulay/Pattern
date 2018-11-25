import com.patterns.DataBaseHandler;
import com.patterns.OrgMapper;
import com.patterns.Organization;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        OrgMapper orgMapper = new OrgMapper(DataBaseHandler.getConnection());


        Organization organization = new Organization();
        organization.setName("MTS");
        organization.setAddress("Moscow");


            orgMapper.insert(organization);


        Organization organization2 = orgMapper.findById(organization.getId());
        //Проверка в консоли
        System.out.println(organization2==null);

    }
}

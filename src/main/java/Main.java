import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SQLException {

        OrgMapper   orgMapper = new OrgMapper(DataBaseHandler.getConnection());


        Organization organization = new Organization();
        organization.setName("MTS");
        organization.setAddress("Moscow");


            orgMapper.insert(organization);


        Organization organization2 = orgMapper.findById(organization.getId());
        //Проверка в консоли
        System.out.println(organization2==null);

    }
}

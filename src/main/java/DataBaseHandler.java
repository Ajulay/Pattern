import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler {

    public static Connection getConnection() throws SQLException {

    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/organizations");
    return conn;
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class OrgMapper {

    private final Connection connection;

    public OrgMapper(Connection connection) {
        this.connection  = connection;
    }

    public Organization findById(int id) throws SQLException{

        Organization organization = IdentityMap.getOrganization(id);
       if(organization != null){
           return organization;
       }

        PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, address FROM organization WHERE id = ?");
        statement.setInt(1,id);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                organization = new Organization();
                organization.setId(resultSet.getInt(1));
                organization.setName(resultSet.getString(2));
                organization.setAddress(resultSet.getString(3));
                return organization;
            }
        }

        throw new SQLException("Record(s) not found...");
    }

    public boolean insert(Organization organization) throws SQLException {


        PreparedStatement statement = connection.prepareStatement(
                                    "INSERT INTO organizations(`name`, address) VALUES (?,?)");
        statement.setString(1, organization.getName());
        statement.setString(2, organization.getAddress());
        boolean b = statement.execute();
        statement.close();
        if(b) {
            IdentityMap.addOrganization(organization);
        }
        return b;
    }

    public boolean update(Organization organization) throws SQLException {
        boolean b = updateOne(organization);
        if(b) {
            IdentityMap.addOrganization(organization);
        }
        return b;

    }

    public boolean delete(Organization organization) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM organizations  WHERE id=?");
        statement.setInt(1, organization.getId());
        boolean b = statement.execute();
        statement.close();
        if(b) {
            IdentityMap.addOrganization(organization);
        }

        return b;
    }

  public void close() throws SQLException {
    updateAll();

        try {
          connection.close();
      } catch (SQLException e) {
            Logger.getLogger("Ошибка записи в базу данных");
      }
  }

  private boolean updateAll() throws SQLException {
      Map<Integer, Organization> map = IdentityMap.getInstance();

      for (Map.Entry<Integer, Organization> pair: map.entrySet()) {
          updateOne(pair.getValue());
      }

  return true;}

  private boolean updateOne(Organization organization) throws SQLException {
      PreparedStatement statement = connection.prepareStatement(
              "UPDATE organizations SET ?,? WHERE ?");
      statement.setInt(3, organization.getId());
      statement.setString(1, organization.getName());
      statement.setString(2, organization.getAddress());
      boolean b = statement.execute();
      statement.close();
        return b;
  }

}

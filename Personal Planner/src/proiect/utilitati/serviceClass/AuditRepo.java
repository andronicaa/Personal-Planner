package proiect.utilitati.serviceClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AuditRepo {
    public void addAuditService(int userId, String actiune) {
//        generarea timestampului
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String insertTimeStamp = "INSERT INTO audit VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(insertTimeStamp);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, actiune);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

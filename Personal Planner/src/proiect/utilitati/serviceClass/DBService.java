package proiect.utilitati.serviceClass;

import proiect.DBConfiguration.DataBaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// ---------- facem o clasa de tip SINGLETON pentru a se face conexiunea la o baza de date
public class DBService {
    private DBService() {}
    public static PreparedStatement getStatement(String sql) {
        Connection connection = DataBaseConfiguration.getDatabaseConnection();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            return stm;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

package proiect.DBConfiguration;

import java.sql.*;

public class DataBaseConfiguration {
//    ------------------Clasa ce stabileste conexiunea la baza de date --------------------------
    private static final String DB_URL = "jdbc:mysql://localhost:3306/datatest?" + "autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection databaseConnection;

    private DataBaseConfiguration() {

    }
// ------------ Returnam un obiect de tipul connection-----------
    public static Connection getDatabaseConnection() {
        try {
            if (databaseConnection ==  null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // s-a stabilit conexiunea
         return databaseConnection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}

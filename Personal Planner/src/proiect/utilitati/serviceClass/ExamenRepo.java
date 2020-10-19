package proiect.utilitati.serviceClass;
import proiect.utilitati.DataCurenta;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamenRepo {
//    creare tabel pentru examene
    public void creatExamTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS examen" +
                "(idExam int PRIMARY KEY AUTO_INCREMENT," +
                "userId int NOT NULL," +
                "materie VARCHAR(20)," +
                "locatie VARCHAR(20)," +
                "data DATE)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(createTable);
            preparedStatement.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    inserare nou examen in tabel
    public void insertExamen(int userId, String materie, String locatie, String data) {
        int maxId = 0;
        Date dataExamen = Date.valueOf(data);
        String selectIdSql = "SELECT MAX(idExam) AS `max_id` FROM examen";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectIdSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                maxId = resultSet.getInt("max_id");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String insertExam = "INSERT INTO examen VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(insertExam);
            preparedStatement.setInt(1, maxId +  1);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, materie);
            preparedStatement.setString(4, locatie);
            preparedStatement.setDate(5, dataExamen);
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//  afisare examen luna prezizata
    public void exameneLunaPrec(int userId, int luna) {

        boolean examen = false;
        String cautaExamen = "SELECT * FROM examen WHERE userId = ? AND MONTH(data) = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(cautaExamen);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, luna);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("In luna " + luna + " ai examene la: ");
            while (resultSet.next()) {
                examen = true;
                System.out.println(resultSet.getString("materie") + " in " + resultSet.getString("locatie") + " pe data de " + resultSet.getDate("data"));


            }
            if (!examen) {
                System.out.println("N-ai niciun examen in luna " + luna + " .Netflix te asteapta!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}

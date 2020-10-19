package proiect.utilitati.serviceClass;

import proiect.utilitati.DataCurenta;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntalniriRepo {
    public void createTable() {
        String createIntalniriTable = "CREATE TABLE IF NOT EXISTS `datatest`.`intalniri` (\n" +
                "  `idIntalnire` INT NOT NULL,\n" +
                "  `userId` VARCHAR(45) NOT NULL,\n" +
                "  `data` VARCHAR(45) NOT NULL,\n" +
                "  `locatie` VARCHAR(45) NOT NULL,\n" +
                "  `nume` VARCHAR(45) NOT NULL,\n" +
                "  `prenume` VARCHAR(45) NOT NULL,\n" +
                "  `ora` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`idIntalnire`),\n" +
                "  UNIQUE INDEX `idIntalnire_UNIQUE` (`idIntalnire` ASC) VISIBLE);";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(createIntalniriTable);
            preparedStatement.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



public void adaugaIntalnire(int userId, String data, String locatie, String nume, String prenume, int ora) {
    int maxId = 0;
    Date dataIntalnire = Date.valueOf(data);
    String selectIdSql = "SELECT MAX(idIntalnire) AS `max_id` FROM intalniri";
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

    String insertIntalnire = "INSERT INTO intalniri VALUES(?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement preparedStatement = DBService.getStatement(insertIntalnire);
        preparedStatement.setInt(1, maxId +  1);
        preparedStatement.setInt(2, userId);
        preparedStatement.setDate(3, dataIntalnire);
        preparedStatement.setString(4, locatie);
        preparedStatement.setString(5, nume);
        preparedStatement.setString(6, prenume);
        preparedStatement.setInt(7, ora);

        preparedStatement.executeUpdate();


    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    public void afiseazaIntalniriCurente(int userId, String dataCurenta) {
        DataCurenta dataCurr = new DataCurenta();
        String data = dataCurr.dataCurenta();
        PreparedStatement preparedStatement;
        String cautaIntalnire = "SELECT * FROM intalniri WHERE userId = ? AND data = ?";
        String cautaIntalnireGeneral = "SELECT * FROM intalniri WHERE userId = ?";

        try {
            if (dataCurenta.equals("y"))
                {
                    preparedStatement = DBService.getStatement(cautaIntalnire);
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setString(2, data);
                }
            else
                {
                    preparedStatement = DBService.getStatement(cautaIntalnireGeneral);
                    preparedStatement.setInt(1, userId);
                }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Aveti o intalnire cu " + resultSet.getString("nume") + " " + resultSet.getString("prenume") + " la " +
                        resultSet.getString("locatie") + " pe data de" + resultSet.getString("data") + " la ora " + resultSet.getInt("ora"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


package proiect.utilitati.serviceClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelefonRepo {
//    Metoda ce afiseaza toate numerele din lista de contacte ale unui utilizator in ordine alfabetica a numelui de familie
    public void displayAgenda(int userId) {
        String displayContacts = "SELECT * FROM telefon WHERE userId = ? ORDER BY last_name";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(displayContacts);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println(resultSet.getInt("contactId") + ". " + resultSet.getString("last_name") + " " + resultSet.getString("first_name") + " " + resultSet.getString("nrTelefon"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


//    Metoda ce adauga un numar de telefon in agenda
    public void adaugaNrTelefon(int userId, String lastName, String firstName, String nrTelefon) {

//        trebuie ca determinam id_ul maxim
        int maxId = 0;
        String selectIdSql = "SELECT MAX(contactId) AS `max_id` FROM telefon";
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

        String insertNrTelefon = "INSERT INTO telefon VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(insertNrTelefon);
            preparedStatement.setInt(1, maxId + 1);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, nrTelefon);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

//    Metoda ce sterge un numar de telefon din agenda
    public void stergeNrTelefon(int contactId) {
        String deleteContact = "DELETE FROM telefon WHERE contactId = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(deleteContact);
            preparedStatement.setInt(1, contactId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

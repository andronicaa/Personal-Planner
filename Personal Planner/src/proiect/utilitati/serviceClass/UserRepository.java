package proiect.utilitati.serviceClass;

import proiect.utilitati.DataCurenta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {
    public void updateBugetUser() {
//        trebuie sa verific daca s-a depasit ziua de 15 a lunii curente, daca da, se va actualiza bugetul lunar
        DataCurenta dataCurr = new DataCurenta();
        int zi = Integer.parseInt(dataCurr.ziLuna());

        if (zi >= 15) {
            String updateSalariu = "UPDATE person SET buget_lunar = salariu + buget_lunar";
            try {
                PreparedStatement preparedStatement = DBService.getStatement(updateSalariu);

                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
//  daca un user exista in baza de date, i se va returna ID-ul, daca nu, se va returna 0
    public int cautaUser(String userName) {
        String selectSql = "SELECT * FROM person WHERE username = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectSql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
//                exista deja utilizatorul in baza de date
                return resultSet.getInt("idPerson");}


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
 //                nu exista utilizatorul in baza de date -> returnam 0
        return 0;
    }

//    adaugare utilizator in baza de date
    public int addUserToDB(String lastName, String firstName, String email, int salary, String userName) {
//        trebuie sa vedem ce id ii atribuim
//        executam o comanda select in care se va gasi cel mai mare id din baza de date
        int maxId = 0;
        String selectIdSql = "SELECT MAX(idPerson) AS `max_id` FROM person";
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
//       dupa ce am gasit id-ul maxim, trebuie sa adaugam noul user in sistem
        maxId += 1;
        String insertUser = "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(insertUser);
            preparedStatement.setInt(1, maxId);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, salary);
            preparedStatement.setInt(6, salary);
            preparedStatement.setString(7, userName);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        returnam ID-ul user-ului adaugat
        return maxId;

    }

    public int getSalary(int userId) {

        String selectSalary = "SELECT buget_lunar from person WHERE idPerson = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectSalary);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("buget_lunar");}


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;

    }

    public ArrayList<String> getUserName(String email) {
        String selectUserName = "SELECT first_name, last_name, username FROM person WHERE email = ?";
        ArrayList<String> userInfo = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectUserName);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
//                System.out.print("Datele dumneavoastra sunt: " + resultSet.getString("last_name") + resultSet.getString("first_name") + " cu username-ul " + resultSet.getString("username"));
                userInfo.add(resultSet.getString("last_name"));
                userInfo.add(resultSet.getString("first_name"));
                userInfo.add(resultSet.getString("username"));
            }



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userInfo;

    }


}

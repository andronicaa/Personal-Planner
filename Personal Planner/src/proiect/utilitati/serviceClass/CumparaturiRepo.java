package proiect.utilitati.serviceClass;

import proiect.DBConfiguration.DataBaseConfiguration;
import proiect.lista_cumparaturi.ListaCumparaturi;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CumparaturiRepo {

//  afisare lista de cumparaturi
    public void displayLista(int UserId) {
//        se va face un select pentru a se lista produsele pentru un anumit user
        String selectSql = "SELECT * FROM cumparaturi WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectSql);
            preparedStatement.setInt(1, UserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Produsele sunt: ");
            while (resultSet.next())
                System.out.println(resultSet.getString("produs") + " " + resultSet.getInt("cantitate"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

//    adaugare in lista de cumparaturi
    public void addProduct(int UserId, String produs, int cantitate) {
        String insertSql = "INSERT INTO cumparaturi VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(insertSql);
            preparedStatement.setInt(1, UserId);
            preparedStatement.setString(2, produs);
            preparedStatement.setInt(3, cantitate);
//            inseram in baza de date noul produs
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//      sterge produs din lista de cumparaturi
    public void deleteProduct(int UserId, String produs) {
        String deleteSql = "DELETE FROM cumparaturi WHERE user_id = ? AND produs = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(deleteSql);
            preparedStatement.setInt(1, UserId);
            preparedStatement.setString(2, produs);

//            stergem din baza de date
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}

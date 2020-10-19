package proiect.utilitati.serviceClass;
import proiect.utilitati.DataCurenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;

public class PlataRepo {
//    listeaza facturile
    public void listeazaFacturi(int userId) {
        String selectSql = "SELECT * FROM plata WHERE userId = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectSql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
//                listam facturile
                System.out.println("Factura cu id-ul " + resultSet.getInt("idFactura") + " la " + resultSet.getString("tip_factura") + " cu data de facturare pe " + resultSet.getString("data_facturare") +
                        " si cea scadenta " + resultSet.getString("data_scadenta") + " costa " + resultSet.getInt("pret") + " lei");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    plateste o factura(factura respectiva se va sterge din baza de date, iar din bugetul user-ului se va scadea acest pret)
    public void platesteFactura(int userId, int idFactura) {
//        se va sterge factura cu id-ul IdFactura pentru user-ul cu id-ul UserId
//        sa cauta factura care se doreste a fi platita
        String cautaFactura = "SELECT pret FROM plata WHERE idFactura = ? AND userId = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(cautaFactura);
            preparedStatement.setInt(1, idFactura);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
//                extrag valoarea facturii
                int pretFactura = resultSet.getInt("pret");
                System.out.println("Pretul este " + pretFactura);
//                trebuie sa stergem factura din baza de date si sa actualizam bugetul user-ului
//                mai intai verificam daca exista suficient buget pentru a plati factura
//                cautam bugetul in tabelul person
                String cautaBuget = "SELECT buget_lunar FROM person WHERE idPerson = ?";
                try {
                    preparedStatement = DBService.getStatement(cautaBuget);
                    preparedStatement.setInt(1, userId);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next())
                    {
                        int buget = resultSet.getInt("buget_lunar");
                        if (buget - pretFactura < 0)
                            System.out.println("Nu aveti suficient buget pentru a plati aceasta factura");
                        else
                        {
//                            stergem factura din tabel si actualizam salariu in person
                            String deleteFactura = "DELETE FROM plata WHERE idFactura = ? AND userId = ?";
                            try {
                                preparedStatement = DBService.getStatement(deleteFactura);
                                preparedStatement.setInt(1, idFactura);
                                preparedStatement.setInt(2, userId);
                                preparedStatement.executeUpdate();

                            }  catch (SQLException ex) {
                                ex.printStackTrace();
                            }
//                            trebuie sa actualizam salariul utilizatorului
                            String updateBuget = "UPDATE person SET buget_lunar = ? WHERE idPerson = ?";
                            try {
                                preparedStatement = DBService.getStatement(updateBuget);
                                preparedStatement.setInt(1, buget - pretFactura);
                                preparedStatement.setInt(2, userId);
                                preparedStatement.executeUpdate();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    listam facturile ce trebuie platite pe luna curenta
    public void facturiLunaCurenta(int userId) {
//        determinam data curenta(luna)
        boolean facturiDePlatit = false;
        DataCurenta dataCurr = new DataCurenta();
        int data = Integer.parseInt(dataCurr.lunaCurenta());
//        cautam facturile corespunzatoare unui anumit user(extragem doar luna)
        String dataFacturare = "SELECT * FROM plata WHERE userId = ? AND MONTH(data_facturare) = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(dataFacturare);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, data);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                facturiDePlatit = true;
//                listam facturile din luna curenta
                System.out.println("Factura la: " + resultSet.getString("tip_factura") + " cu data de facturare pe " + resultSet.getDate("data_facturare") + " si data scadenta pe " + resultSet.getDate("data_scadenta") + " cu valoarea de " + resultSet.getInt("pret") + " lei trebuie platita in cadrul acestei luni ");
            }
            if (!facturiDePlatit) {
                System.out.println("Nu mai aveti nicio factura de platit in acesta luna");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //    listam facturile scadente
    public void facturiScadenta(int userId) throws ParseException {

//        determinam data curenta
        boolean facturiScadente = false;
        DataCurenta dataCurr = new DataCurenta();
        String data = dataCurr.dataCurenta();

//        cautam facturile corespunzatoare unui anumit user
        String dataFacturare = "SELECT tip_factura, CONVERT(data_scadenta, CHAR) AS `dataScadenta`, pret FROM plata WHERE userId = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(dataFacturare);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                if (resultSet.getString("dataScadenta").compareTo(data) < 0) {
                    System.out.println("Factura la: " + resultSet.getString("tip_factura") + " este scadenta pe " + resultSet.getString("dataScadenta") + " cu valoarea de " + resultSet.getInt("pret") + " lei");
                    facturiScadente = true;
                }

            }
            if (!facturiScadente) {
                System.out.println("Nu mai aveti nicio factura scadenta");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void adaugaFactura(int userId, String tipFactura, String dataFacturare, String dataScadenta, int pret ) throws ParseException {
//        trebuie sa convertim Stringurile ce contin date in Date
//            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dFacturare = Date.valueOf(dataFacturare);
            Date dScadenta = Date.valueOf(dataScadenta);
//            trebuie sa aflam id-ul Facturii
//        trebuie sa vedem ce id ii atribuim noii facturi
//        executam o comanda select in care se va gasi cel mai mare id din baza de date
            int maxId = 0;
            String selectIdSql = "SELECT MAX(idFactura) AS `max_id` FROM plata";
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
            System.out.println("Max id este" + maxId);
            String insertFactura = "INSERT INTO plata VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = DBService.getStatement(insertFactura);
                preparedStatement.setInt(1, maxId);
                preparedStatement.setInt(2, userId);
                preparedStatement.setString(3, tipFactura);
                preparedStatement.setDate(4, dFacturare);
                preparedStatement.setDate(5, dScadenta);
                preparedStatement.setInt(6, pret);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }


}

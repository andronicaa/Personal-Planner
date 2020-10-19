package proiect.utilitati.Fisiere;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// Clasa Singleton pentru citirea din fisier
public class ReadFromCSVFile {

    private static ReadFromCSVFile single_instance = null;
    private ReadFromCSVFile(){}
    public static ReadFromCSVFile ReadFromCSVFile() {
        if (single_instance == null)
            single_instance = new ReadFromCSVFile();
        return single_instance;
    }
    public static ArrayList<String> ReadCSV(String file)
    {
        ArrayList<String> listaObiecte = new ArrayList<>();
        String csvFile = file;
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null)
            {
                listaObiecte.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return listaObiecte;

    }
}

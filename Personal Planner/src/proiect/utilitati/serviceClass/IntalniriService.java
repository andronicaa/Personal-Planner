package proiect.utilitati.serviceClass;

import proiect.activitati.ActivitatiExtrascolare;
import proiect.activitati.Intalniri;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.Fisiere.WriteInCSVFile;
import proiect.utilitati.SortIntalniri;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class IntalniriService implements CSVMapInterface {

    public Intalniri process(String line) {
        String[] atributeIntalnire = line.split(",");
        Intalniri intalnire = new Intalniri(atributeIntalnire[0], atributeIntalnire[1], atributeIntalnire[2], atributeIntalnire[3], atributeIntalnire[4], atributeIntalnire[5]);
        return intalnire;
    }

    // -----sorteaza intalnirile in functie de data si prioritatea pe care o au intr-o zi daca doua date sunt egale
    public ArrayList<Intalniri> sortIntalniri(ArrayList<Intalniri> intalniri) {
        Collections.sort(intalniri, new SortIntalniri());
        return intalniri;
    }

    public void afisareIntalniri(ArrayList<Intalniri> intalniri)
    {System.out.println("Intalnirile sunt: ");
        for (int i = 0; i < intalniri.size(); i++)
        {
            System.out.println("Data " + intalniri.get(i).getData() + " la " + intalniri.get(i).getLocatie() + " cu " + intalniri.get(i).getNume() + " " + intalniri.get(i).getPrenume());
        }
    }

    public void updateFileIntalniri(String numeFisier, ArrayList<Intalniri> hobby) throws IOException {
        BufferedWriter writeIntalnire = new BufferedWriter(new FileWriter(numeFisier));
        WriteInCSVFile<Intalniri> intalnireCSV = new WriteInCSVFile<>(writeIntalnire, new Intalniri());
        for (Intalniri h : hobby) {
            intalnireCSV.writeInFile(h);
        }
        writeIntalnire.flush();
        writeIntalnire.close();
    }
}

package proiect.utilitati.serviceClass;

import proiect.activitati.ActivitatiExtrascolare;
import proiect.activitati.Intalniri;
import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.Fisiere.WriteInCSVFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HobbyService implements CSVMapInterface {

    public ActivitatiExtrascolare process(String line) {
        String[] atributeHobby = line.split(",");
        ActivitatiExtrascolare hobby = new ActivitatiExtrascolare(atributeHobby[0], atributeHobby[1], atributeHobby[2], atributeHobby[3], atributeHobby[4]);
        return hobby;
    }

    public void afisareHobby(ArrayList<ActivitatiExtrascolare> hobby)
    {System.out.println("Hobby-urile sunt: ");
        for (int i = 0; i < hobby.size(); i++)
        {
            System.out.println("Data " + hobby.get(i).getData() + " la " + hobby.get(i).getLocatie() + " facand " + hobby.get(i).getTipActivitate());
        }
    }

    public void updateFileHobby(String numeFisier, ArrayList<ActivitatiExtrascolare> hobby) throws IOException {
        BufferedWriter writeHobby = new BufferedWriter(new FileWriter(numeFisier));
        WriteInCSVFile<ActivitatiExtrascolare> hobbyCSV = new WriteInCSVFile<>(writeHobby, new ActivitatiExtrascolare());
        for (ActivitatiExtrascolare h : hobby) {
            hobbyCSV.writeInFile(h);
        }
        writeHobby.flush();
        writeHobby.close();
    }

}

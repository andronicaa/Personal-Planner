package proiect.utilitati.serviceClass;

import proiect.activitati.Activitate;
import proiect.activitati.ActivitatiExtrascolare;
import proiect.activitati.Examene;
import proiect.activitati.Intalniri;
import proiect.plati.Plata;
import proiect.utilitati.DataCurenta;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.SortIntalniri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

// --------------------clasa de serviciu pentru activitati-------------------
public class ActivitatiService  {


//    bounded wildcards
    public void listeazaActivitati(ArrayList<? extends Activitate> listaActivitati) {
        for(Activitate item : listaActivitati)
            System.out.println(item.toString());
    }

}

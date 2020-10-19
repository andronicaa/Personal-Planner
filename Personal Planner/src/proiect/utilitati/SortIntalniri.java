package proiect.utilitati;

import proiect.activitati.Intalniri;
import proiect.persoana.Persoana;

import java.util.Comparator;
//metoda ce sorteaza crescator dupa data, si daca doua intalniri au aceeasi data le sorteaza crescator dupa prioritatea lor
public class SortIntalniri implements Comparator<Intalniri> {
    public int compare(Intalniri a, Intalniri b) {

        if (a.getData().compareTo(b.getData()) != 0)
            return (a.getData().compareTo(b.getData()));
        else
            return (a.getPrioritate().compareTo(b.getPrioritate()));
    }
}

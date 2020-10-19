package proiect.utilitati;

import proiect.persoana.Persoana;

import java.util.Comparator;
//metoda care sorteaza TreeMap-ul dupa nume, respectiv prenume
public class SortByName implements Comparator<Persoana> {
public int compare(Persoana a, Persoana b) {
   if (a.getNume().compareTo(b.getNume()) != 0)
       return (a.getNume().compareTo(b.getNume()));
   else
       return (a.getPrenume().compareTo(b.getPrenume()));
}
}

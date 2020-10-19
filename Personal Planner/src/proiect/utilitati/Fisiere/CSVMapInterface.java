package proiect.utilitati.Fisiere;
// Interfata ce contine o metoda ce mapeaza o linie in functie de clasa in care se apeleaza
public interface CSVMapInterface<E> {
    public E process(String line);
}

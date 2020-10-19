package proiect.persoana;

import proiect.utilitati.Fisiere.WriteCSV;

public class Persoana implements WriteCSV {
    private String nume;
    private String prenume;


    public Persoana(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }

    @Override
    public String[] toStringArray() {
        return new String[]{nume, prenume};
    }
}

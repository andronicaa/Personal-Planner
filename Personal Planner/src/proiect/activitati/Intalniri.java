package proiect.activitati;

import proiect.utilitati.Fisiere.WriteCSV;

public class Intalniri extends Activitate implements WriteCSV {

//    numele si prenumele persoanei cu care se relizeaza intalnirea

    private String nume;
    private String prenume;

    public Intalniri(){
        super();
    }
    public Intalniri(String numeActivitate, String data, String prioritate, String locatie, String nume, String prenume) {
        super(numeActivitate, data, prioritate, locatie);
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
        return "Intalniri{" +
                "Nume Activitate='" + super.getNumeActivitate() + '\'' +
                ", prioritate='" + super.getPrioritate() + '\'' +
                "data='" + super.getData() + '\'' +
                ", locatie='" + super.getLocatie() + '\'' +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }

    @Override
    public String[] toStringArray() {
        return new String[]{super.getNumeActivitate(), super.getData(), super.getPrioritate(), super.getLocatie(), nume, prenume};
    }
}

package proiect.activitati;
// clasa parinte pentru activitati de tipul: Intalniri, Examene, ActivitatiExtrascolare
public class Activitate {

    private String numeActivitate;
    private String data;
    private String prioritate;
    private String locatie;

    public Activitate(String numeActivitate, String data, String prioritate, String locatie) {
        this.numeActivitate = numeActivitate;
        this.data = data;
        this.prioritate = prioritate;
        this.locatie = locatie;
    }

    public Activitate() {

    }

    public String getNumeActivitate() {
        return numeActivitate;
    }

    public String getData() {
        return data;
    }
    public String getPrioritate() {
        return prioritate;
    }
    public String getLocatie() {return locatie;}
    public void setNumeActivitate(String numeActivitate) {
        this.numeActivitate = numeActivitate;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void setPrioritate(String prioritate) {
        this.prioritate = prioritate;
    }
    public void setLocatie(String locatie) {this.locatie = locatie;}

    @Override
    public String toString() {
        return "{" +
                " numeActivitate='" + numeActivitate + '\'' +
                ", data='" + data + '\'' +
                ", prioritate='" + prioritate + '\'' +
                ", locatie='" + locatie + '\'' +
                '}';
    }


}

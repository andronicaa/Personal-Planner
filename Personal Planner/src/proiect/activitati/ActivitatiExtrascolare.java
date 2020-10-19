package proiect.activitati;

import proiect.utilitati.Fisiere.WriteCSV;

public class ActivitatiExtrascolare extends Activitate implements WriteCSV {
    private String tipActivitate;
    public ActivitatiExtrascolare() {
        super();
    }
    public ActivitatiExtrascolare(String numeActivitate, String data, String prioritate, String locatie, String tipActivitate) {
        super(numeActivitate, data, prioritate, locatie);
        this.tipActivitate = tipActivitate;
    }

    public String getTipActivitate() {
        return tipActivitate;
    }

    public void setTipActivitate(String tipActivitate) {
        this.tipActivitate = tipActivitate;
    }

    @Override
    public String toString() {
        return "ActivitatiExtrascolare{" +
                " Data= " + super.getData() +
                " Locatie= " + super.getLocatie() +
                " tipActivitate='" + tipActivitate + '\'' +
                '}';
    }

    @Override
    public String[] toStringArray() {
        return new String[]{super.getNumeActivitate(), super.getData(), super.getPrioritate(), super.getLocatie(), tipActivitate};
    }
}

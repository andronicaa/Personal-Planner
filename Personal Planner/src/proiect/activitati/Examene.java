package proiect.activitati;

import proiect.utilitati.Fisiere.WriteCSV;

public class Examene extends Activitate implements WriteCSV {

    private String curs;

    public Examene(){
        super();
    }
    public Examene(String numeActivitate, String data, String prioritate, String locatie, String curs) {
        super(numeActivitate, data, prioritate, locatie);
        this.curs = curs;
    }



    public String getCurs() {
        return curs;
    }



    public void setCurs(String curs) {
        this.curs = curs;
    }

    @Override
    public String toString() {
        return "Examene{" +
                "Data='" + super.getData() + '\'' +
                ", Curs='" + curs + '\'' +
                '}';
    }

    @Override
    public String[] toStringArray() {
        return new String[]{super.getNumeActivitate(), super.getData(), super.getPrioritate(), super.getLocatie(), curs};
    }
}

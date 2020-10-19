package proiect.utilitati.serviceClass;

import proiect.utilitati.Fisiere.WriteCSV;

import java.sql.Timestamp;
public class AuditUtils{
    String actiune;
    String timeStamp;
    public AuditUtils(){}
    public AuditUtils(String actiune, String timeStamp) {
        this.actiune = actiune;
        this.timeStamp = timeStamp;
    }

    public String getActiune() {
        return actiune;
    }

    public void setActiune(String actiune) {
        this.actiune = actiune;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "AuditUtils{" +
                "actiune='" + actiune + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }


}

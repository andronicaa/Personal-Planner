package proiect.lista_cumparaturi;
import proiect.utilitati.Fisiere.WriteCSV;

import java.util.ArrayList;
import java.util.Objects;

public class ListaCumparaturi implements WriteCSV {
    private String produs;
    private String cantitate;
    public ListaCumparaturi() {}
    public ListaCumparaturi(String produs, String cantitate) {
        this.produs = produs;
        this.cantitate = cantitate;
    }

    public String getProdus() {
        return produs;
    }

    public String getCantitate() {
        return cantitate;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public void setCantitate(String cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListaCumparaturi)) return false;
        ListaCumparaturi that = (ListaCumparaturi) o;
        return Objects.equals(getProdus(), that.getProdus()) &&
                Objects.equals(getCantitate(), that.getCantitate());
    }



    @Override
    public String toString() {
        return "ListaCumparaturi{" +
                "produs='" + produs + '\'' +
                ", cantitate='" + cantitate + '\'' +
                '}';
    }

    @Override
    public String[] toStringArray() {
        return new String[]{produs, cantitate};
    }



}

package proiect.utilitati;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//clasa ce determina data curenta in diferite formate(data in totalitate sau doar luna ce imi va folosi la anumite metode)
public class DataCurenta {
//    extrag doar luna din data curenta
    public String lunaCurenta()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDate localDate = LocalDate.now();
        String data = dtf.format(localDate);
        return data;
    }
    public String ziLuna() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDate localDate = LocalDate.now();
        String zi = dtf.format(localDate);
        return zi;
    }
    //    iau toata data de forma dd/MM/yyyy
    public String dataCurenta()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String data = dtf.format(localDate);
        return data;
    }


}

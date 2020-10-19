package proiect.utilitati.serviceClass;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.File;
// serviciu singleton in care voi implementa o metoda ce va fi folosita pentru scrierea in fisierul de audit
public class AuditService {
    private static AuditService instance = null;
    private AuditService() {}
    public static AuditService AuditService() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }
    public static FileWriter OpenFile() throws IOException {
        File auditFile = new File("audit.csv");
        auditFile.createNewFile();
        FileWriter writer = new FileWriter(auditFile, true);
//        adaugam acest header doar daca fisierul este gol
        if (auditFile.length() == 0) {
            System.out.println();
            CSVUtils.writeLine(writer, Arrays.asList("Actiune", "TimeStamp"));
        }

        return writer;
    }
    public static void LogActionInAuditFile(String action, FileWriter writer) throws Exception{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String timeStamp  = dateFormat.format(new Date());

//        trebuie sa citesc tot din fisier si sa adaug actiunea mea

            AuditUtils actionsTimeStamp = new AuditUtils(action, timeStamp);

            List<AuditUtils> list = new ArrayList<AuditUtils>();
            list.add(actionsTimeStamp);
            for (AuditUtils d : list) {
                List<String> listaDeScris = new ArrayList<>();
                listaDeScris.add(d.getActiune());
                listaDeScris.add(d.getTimeStamp());
                CSVUtils.writeLine(writer, listaDeScris);
            }


        list.removeAll(list);

        writer.flush();

    }
}

package proiect.utilitati.Fisiere;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.File;

// -------- Clasa pentru citirea din fisier(generica)

public class WriteInCSVFile<E extends WriteCSV> {
    private BufferedWriter writer;

    public WriteInCSVFile(BufferedWriter writer, E objToWrite) throws IOException{
        this.writer = writer;

    }
    public void writeInFile(E obj) throws IOException{
        String[] rows = obj.toStringArray();
        String line = String.join(",", rows) + '\n';
        writer.write(line);
    }
}

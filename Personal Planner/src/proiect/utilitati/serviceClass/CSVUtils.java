package proiect.utilitati.serviceClass;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
public class CSVUtils {
    private static final char SEPARATOR = ',';
    public static void writeLine(Writer w, List<String> actions) throws IOException {
        writeLine(w, actions, SEPARATOR, ' ');
    }
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }
    public static void writeLine(Writer w, List<String> actions, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();

        for (String value : actions) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
}

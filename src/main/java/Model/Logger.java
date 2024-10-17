package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static final File fileInit = new File("log.txt");
    public static FileWriter file = null;

    public Logger() {
        try {
            file = new FileWriter("log.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //syncronized  to ensure that only one thread can execute it at a time
    // when multiple threads attempt to write to the log file simultaneously
    // there is a risk of data corruption or inconsistency.
    public static synchronized void log(String message) {
        try {
            file.write(message + "\n");
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void closeFile() throws IOException {
        file.close();
    }
}

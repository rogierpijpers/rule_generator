package data.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void log(String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String logMessage = dateFormat.format(date) + ": \t" + message;

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("errorLog.txt", true)))) {
            out.println(logMessage);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

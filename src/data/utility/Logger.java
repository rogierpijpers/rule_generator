package data.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	public static void log(Exception exception){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String logMessage = dateFormat.format(date) + ": \t" + getStackTrace(exception);
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("errorLog.txt", true)))) {
		    out.println(logMessage);
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}

}

package data.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

	public static void writeFile(String script, String name, String directory,
			String extention) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(directory + "\\" + name
					+ "." + extention));
			writer.write(script);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
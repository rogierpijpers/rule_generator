package domain.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONController {

	public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream input = new URL(url).openStream();
		
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
			
			String jsonText = "";
			while((reader.ready())){
				jsonText += reader.readLine();
			}
			
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			input.close();
		}
	}
}
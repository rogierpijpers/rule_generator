package domain.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONController {

	public JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream input = new URL(url).openStream();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input, Charset.forName("UTF-8")));

			String jsonText = "";
			while (reader.ready()) {
				jsonText += reader.readLine();
			}

			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			input.close();
		}
	}

	/*
	 * !!! RETURNS THE NAME OF THE SET AND THEN THE ID OF THE BUSINESS RULE SO
	 * IT IS ALTERNATING NAMES AND ID`S JUST LIKE THIS <setname>, <id>,
	 * <setname>, <id> AND SO ON AND SO FORTH!!!
	 */
	public ArrayList<String> jsonToStrings(JSONObject json) {
		ArrayList<String> results = new ArrayList<String>();

		JSONArray jsonArray = json.toJSONArray(new JSONArray());
		for (int i = 0; i < jsonArray.length(); i++) {
			results.add(jsonArray.getString(i));
		}

		return results;
	}
}
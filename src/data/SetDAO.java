package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.target.DatabaseConnection;

public class SetDAO {

	public SetDAO(){
		
	}
	
	public static ArrayList<String> getAllSetNames() {
		ArrayList<String> setNames = new ArrayList<String>();
		
		try (DatabaseConnection connection = new DatabaseConnection()) {
			ResultSet result = connection.query("SELECT name FROM SETB ORDER BY id");
			result.last();
			int lastResult = result.getRow();
			result.first();
			
			for(int i = 1; i <= lastResult; i++){
				setNames.add(result.getString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return setNames;
	}
}
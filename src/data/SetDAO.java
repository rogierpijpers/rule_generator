package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DatabaseConnection;

public class SetDAO {

	public SetDAO(){
		
	}
	
	public static ArrayList<String> getAllSetNames() {
		ArrayList<String> setNames = new ArrayList<String>();
		
		try (DatabaseConnection connection = new DatabaseConnection()) {
			ResultSet result = connection.query("SELECT name FROM SETB ORDER BY id");
			
			while(result.next()){
				setNames.add(result.getString(1));
			}
			result.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return setNames;
	}
}
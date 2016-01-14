package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DatabaseConnection;

public class SetDAO {

	public SetDAO(){
		
	}
	
	public static ArrayList<String> getAllSetNames() throws Exception {
		ArrayList<String> setNames = new ArrayList<String>();
		DatabaseConnection connection = null;		
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query("SELECT name FROM SETB ORDER BY id");
			
			while(result.next()){
				setNames.add(result.getString(1));
			}
			result.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return setNames;
	}
}
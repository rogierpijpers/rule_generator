package data.target;

public class TargetDAO {

	public TargetDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void executeScript(String script) {
		try(DatabaseConnection connection = new DatabaseConnection()){
			connection.insertQuery(script);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
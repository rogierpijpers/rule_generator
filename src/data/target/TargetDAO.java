package data.target;

public class TargetDAO {

	public TargetDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void executeScript(String script) {
		DatabaseConnection connection = null;
		try{
			connection = new DatabaseConnection();
			connection.insertQuery(script);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
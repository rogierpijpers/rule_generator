package domain.businessrule.database;



import data.target.TargetDAO;

public class TargetDatabase{
	private String name;
	private DatabaseType type;
	private TargetDAO targetDAO;
	
	public TargetDatabase(){
		targetDAO = new TargetDAO();
	}

	public TargetDAO getTargetDAO() {
		return targetDAO;
	}

	public DatabaseType getType() {
		return type;
	}

	public void setType(DatabaseType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

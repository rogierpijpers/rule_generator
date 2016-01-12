package domain.businessrule.database;



import data.target.TargetDAO;

public class OracleTargetDatabase implements TargetDatabase{
	private String name;
	private DatabaseType type;
	private TargetDAO targetDAO;
	
	public OracleTargetDatabase(){
		targetDAO = new TargetDAO();
	}
	
	@Override
	public TargetDAO getTargetDAO() {
		return targetDAO;
	}

	@Override
	public DatabaseType getType() {
		return type;
	}

	@Override
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

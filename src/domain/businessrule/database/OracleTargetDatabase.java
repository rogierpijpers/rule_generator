package domain.businessrule.database;



import data.target.TargetDAO;

public class OracleTargetDatabase implements TargetDatabase{
	private String name;
	private DatabaseType type;

	@Override
	public TargetDAO getTargetDAO() {
		// TODO Auto-generated method stub
		return null;
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

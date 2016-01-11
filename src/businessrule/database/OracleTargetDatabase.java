package businessrule.database;

public class OracleTargetDatabase implements TargetDatase{

	private String name;
	private ArrayList<Table> tables = new ArrayList<>();

	@Override
	public TargetDAO getTargetDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatabaseType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(DatabaseType type) {
		// TODO Auto-generated method stub
		
	}
}

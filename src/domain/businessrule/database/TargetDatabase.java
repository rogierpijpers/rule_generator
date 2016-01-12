package domain.businessrule.database;

import data.target.TargetDAO;

public interface TargetDatabase {

	public TargetDAO getTargetDAO();
	public DatabaseType getType();
	public void setType(DatabaseType type);
	public String getName();
	public void setName(String name);
}

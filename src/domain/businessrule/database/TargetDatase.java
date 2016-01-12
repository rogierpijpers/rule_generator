package domain.businessrule.database;

import data.target.TargetDAO;

public interface TargetDatase {

	public TargetDAO getTargetDAO();
	public DatabaseType getType();
	public void setType(DatabaseType type);
}

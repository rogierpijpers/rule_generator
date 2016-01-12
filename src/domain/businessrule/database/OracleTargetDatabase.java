package domain.businessrule.database;


import java.util.ArrayList;

import data.target.TargetDAO;

public class OracleTargetDatabase implements TargetDatase{
	private String name;
	private DatabaseType type;
	private ArrayList<Table> tables = new ArrayList<>();

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

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}
}

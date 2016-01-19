package domain.businessrule.database;


public class Table {
	private String name;
	private TargetDatabase targetDatabase;
	private String primaryKey;
	private String foreignKey;
	
	public Table(String name, TargetDatabase targetDatabase){
		this.setName(name);
		this.setTargetDatabase(targetDatabase);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TargetDatabase getTargetDatabase() {
		return targetDatabase;
	}

	public void setTargetDatabase(TargetDatabase targetDatabase) {
		this.targetDatabase = targetDatabase;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
	
	
	
}

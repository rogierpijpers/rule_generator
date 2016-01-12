package domain.businessrule.database;

public class Column extends Attribute{
	private Table table;
	
	public Column(String name, Table table){
		super(name);
		this.setTable(table);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
}

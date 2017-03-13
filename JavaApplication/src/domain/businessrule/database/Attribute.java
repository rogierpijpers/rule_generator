package domain.businessrule.database;

public abstract class Attribute {
	private String name;
	
	public Attribute(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}

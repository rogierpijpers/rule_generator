package domain.businessrule.rule;

public class Operator {

	private String name;
	private String character;
	
	public Operator(String name, String character){
		this.name = name;
		this.character = character;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	
}

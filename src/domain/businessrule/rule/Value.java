package domain.businessrule.rule;

import domain.businessrule.database.Attribute;

public class Value extends Attribute{
	private AttributeType type;
	
	public Value(String name){
		super(name);
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}
}

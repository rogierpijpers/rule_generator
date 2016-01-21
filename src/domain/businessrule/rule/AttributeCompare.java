package domain.businessrule.rule;


import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeCompare extends BusinessRule {
	private String value;
	private Operator operator;
	private Attribute attribute;

	public AttributeCompare(String code, String name, String value, Operator operator, BusinessRuleType businessRuleType, Attribute attribute) {
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.value = value;
		this.operator = operator;
		this.attribute = attribute;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Value attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}


	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute();
		return column.getTable().getTargetDatabase();
	}

}

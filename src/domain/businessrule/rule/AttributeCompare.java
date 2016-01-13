package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeCompare extends BusinessRule {
	private double value;
	private Operator operator;
	private Attribute attribute;

	public AttributeCompare(String code, String name, double value, Operator operator, Attribute attribute) {

		super.setCode(code);
		super.setName(name);
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		Column column = (Column) this.getAttribute();
		ruleTemplate.add("code", this.getCode());
		ruleTemplate.add("targetTable", column.getTable().getName());
		ruleTemplate.add("column", column.getName());
		ruleTemplate.add("operator", this.getOperator().getCharacter());
		ruleTemplate.add("value", this.getValue());
		return ruleTemplate;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute();
		return column.getTable().getTargetDatabase();
	}

}

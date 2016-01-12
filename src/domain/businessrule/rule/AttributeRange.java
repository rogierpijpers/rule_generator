package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;

public class AttributeRange extends BusinessRule{
	private double minValue;
	private double maxValue;
	private Operator operator;
	private Attribute attribute;

	public AttributeRange(String code, String name, double minValue, double maxValue, Operator operator, Attribute attribute){
		super.setCode(code);
		super.setName(name);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.operator = operator;
		this.attribute = attribute;
	}
	

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}


	public double getMaxValue() {
		return maxValue;
	}


	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}


	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}


	public Attribute getAttribute() {
		return attribute;
	}


	public void setAttribute(Value attribute) {
		this.attribute = attribute;
	}
	


	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		Column column = (Column) this.getAttribute();
		ruleTemplate.add("code", this.getCode());
		ruleTemplate.add("targetTable", column.getTable().getName());
		ruleTemplate.add("column", column.getName());
		ruleTemplate.add("operator", this.getOperator().getCharacter());
		ruleTemplate.add("minValue", this.getMinValue());
		ruleTemplate.add("maxValue", this.getMaxValue());
		return ruleTemplate;
	}

}

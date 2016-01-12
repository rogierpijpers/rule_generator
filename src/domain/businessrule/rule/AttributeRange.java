package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;

public class AttributeRange extends BusinessRule{

	private String code;
	private String name;
	private double minValue;
	private double maxValue;
	private Operator operator;
	private Attribute attribute;

	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Column column = (Column) attribute;
		ruleTemplate.add("targetTable", "");
		ruleTemplate.add("column", "");
		ruleTemplate.add("operator", operator.getCharacter());
		ruleTemplate.add("minValue", minValue);
		ruleTemplate.add("maxValue", maxValue);
		return ruleTemplate;
	}

}

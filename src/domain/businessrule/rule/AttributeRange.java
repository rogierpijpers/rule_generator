package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeRange extends BusinessRule{
	private String minValue;
	private String maxValue;
	private Operator operator;
	private Attribute attribute;

	public AttributeRange(String code, String name, String minValue, String maxValue, Operator operator, BusinessRuleType businessRuleType, Attribute attribute){
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.operator = operator;
		this.attribute = attribute;
	}
	

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}


	public String  getMaxValue() {
		return maxValue;
	}


	public void setMaxValue(String maxValue) {
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


	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute();
		return column.getTable().getTargetDatabase();
	}

}

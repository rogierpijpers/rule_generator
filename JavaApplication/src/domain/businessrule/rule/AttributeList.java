package domain.businessrule.rule;

import java.util.ArrayList;



import domain.businessrule.BusinessRule;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeList extends BusinessRule {
	private Attribute attribute;
	private Operator operator;
	private ArrayList<String> value;

	public AttributeList(AttributeListParameterObject parameterObject) {
		super.setCode(parameterObject.getCode());
		super.setName(parameterObject.getName());
		super.setType(parameterObject.getBusinessRuleType());
		this.attribute = parameterObject.getAttribute();
		this.operator = parameterObject.getOperator();
		this.value = parameterObject.getValue();
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Value attribute) {
		this.attribute = attribute;
	}
	
	public ArrayList<String> getValue() {
		return value;
	}

	public void setValue(ArrayList<String> value) {
		this.value = value;
	}
	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	
	
	public String getValueStr(){
		String valueStr = "";
		for(String val : value){
			valueStr += "'"+val+"', ";
		}
		
		if (valueStr.endsWith(", ")) {
			valueStr = valueStr.substring(0, valueStr.length() - 2);
		}
		return valueStr;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute();
		return column.getTable().getTargetDatabase();
	}

	

}

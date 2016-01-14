package domain.businessrule.rule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeList extends BusinessRule {
	private Attribute attribute;
	private ArrayList<String> value;

	public AttributeList(String code, String name, Attribute attribute, BusinessRuleType businessRuleType, ArrayList<String> value) {
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.attribute = attribute;
		this.value = value;
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
	

	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		
		Column column = (Column) this.getAttribute();
		ruleTemplate.add("code", this.getCode());
		ruleTemplate.add("targetTable", column.getTable().getName());
		ruleTemplate.add("column", column.getName());
		ruleTemplate.add("value", this.getValueStr());

		return ruleTemplate;
	}
	
	private String getValueStr(){
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

package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeList extends BusinessRule {
	private Attribute attribute;
	private String value;

	public AttributeList(String code, String name, Attribute attribute) {

		super.setCode(code);
		super.setName(name);
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
	

	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		Column column = (Column) this.getAttribute();
		ruleTemplate.add("code", this.getCode());
		ruleTemplate.add("targetTable", column.getTable().getName());
		ruleTemplate.add("column", column.getName());
		ruleTemplate.add("value", this.getValue());

		return ruleTemplate;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute();
		return column.getTable().getTargetDatabase();
	}

}

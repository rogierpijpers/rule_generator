package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.TargetDatabase;

public class AttributeOther extends BusinessRule {
	private Attribute attribute;

	public AttributeOther(String code, String name,
			BusinessRuleType businessRuleType, Attribute attribute) {
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.attribute = attribute;
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
		/*
		 * TO DO: Column column = (Column) this.getAttribute();
		 * ruleTemplate.add("code", this.getCode());
		 * ruleTemplate.add("targetTable", column.getTable().getName());
		 * ruleTemplate.add("column", column.getName());
		 */
		return ruleTemplate;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		/*
		 * TO DO Column column = (Column) this.getAttribute(); return
		 * column.getTable().getTargetDatabase();
		 */
		return null;
	}

}

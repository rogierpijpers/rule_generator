package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class AttributeOther extends BusinessRule {
	private Attribute attribute;
	private String plsql;

	public AttributeOther(String code, String name,
			BusinessRuleType businessRuleType, Attribute attribute, String plsql) {
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.attribute = attribute;
		this.plsql = plsql;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Value attribute) {
		this.attribute = attribute;
	}
	
	public String getPlsql(){
		return plsql;
	}
	
	public void setPlsql(String plsql){
		this.plsql = plsql;
	}

	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		
		 Column column = (Column) this.getAttribute();
		 ruleTemplate.add("code", this.getCode());
		 ruleTemplate.add("targetTable", column.getTable().getName());
		 ruleTemplate.add("plsql", this.getPlsql());

		return ruleTemplate;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute(); 
		return column.getTable().getTargetDatabase();
	}

}

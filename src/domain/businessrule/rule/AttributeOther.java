package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;

public class AttributeOther extends BusinessRule{

	private String code;
	private String name;
	private Value attribute;

	
	
	
	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ST fillTargetTemplate(ST template) {
		// TODO Auto-generated method stub
		return null;
	}
}
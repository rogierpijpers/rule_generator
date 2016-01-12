package domain.businessrule.rule;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;

public class AttributeRange extends BusinessRule{

	private String code;
	private String name;
	private double minValue;
	private double maxValue;
	private Operator operator;
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

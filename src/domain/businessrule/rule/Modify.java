package domain.businessrule.rule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;

public class Modify extends BusinessRule{

	private String code;
	private String name;
	private ArrayList<Value> attributes = new ArrayList<>();

	
	
	
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

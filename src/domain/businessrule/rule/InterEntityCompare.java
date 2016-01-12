package domain.businessrule.rule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.TargetDatabase;

public class InterEntityCompare extends BusinessRule{

	private String code;
	private String name;
	private Operator operator;
	private ArrayList<Value> attributes = new ArrayList<>();

	
	
	
	// ---- fill templates for generation
	@Override
	public ST fillTemplate(ST ruleTemplate) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public TargetDatabase getTargetDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

}

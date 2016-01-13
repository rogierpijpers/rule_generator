package domain.businessrule;

import java.util.ArrayList;

import util.RuleHolder;

public interface BusinessRuleService {
	
	public ArrayList<RuleHolder> getAllBusinessRules();
	public BusinessRule getBusinessRule(String ruleCode);
	public ArrayList<String> getAllCategoryNames();
	public ArrayList<String> getAllTypeCodes();
}

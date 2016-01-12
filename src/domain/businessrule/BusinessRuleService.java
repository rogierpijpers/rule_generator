package domain.businessrule;

import java.util.ArrayList;

public interface BusinessRuleService {
	
	public ArrayList<RuleHolder> getAllBusinessRules();
	public BusinessRule getBusinessRule(String ruleCode);
	public ArrayList<String> getAllCategoryNames();
	public ArrayList<String> getAllTypeCodes();
}

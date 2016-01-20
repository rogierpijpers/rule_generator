package domain.businessrule;

import java.util.ArrayList;

import util.RuleHolder;

public class BusinessRuleServiceImpl implements BusinessRuleService {

	@Override
	public ArrayList<RuleHolder> getAllBusinessRules() {
		return BusinessRule.getAllCodesAndNames();
	}

	@Override
	public BusinessRule getBusinessRule(String ruleCode) {
		return BusinessRule.getDetails(ruleCode);
	}

	@Override
	public ArrayList<RuleHolder> getCodesAndNamesFromSet(String setName) {
		return BusinessRule.getAllCodesAndNamesFromSet(setName);
	}

	@Override
	public ArrayList<String> getAllSetNames() {
		return BusinessRule.getAllSetNames();
	}
	
	@Override
	public ArrayList<String> getAllTypeCodes() {
		return BusinessRuleType.getAllCodes();
	}
	
	@Override
	public ArrayList<String> getAllCategoryNames() {
		return Category.getAll();
	}

}

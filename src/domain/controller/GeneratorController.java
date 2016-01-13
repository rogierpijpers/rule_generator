package domain.controller;

import java.io.File;
import java.io.IOException;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleService;
import domain.businessrule.BusinessRuleServiceImpl;
import domain.businessrule.database.TargetDatabase;
import domain.generator.GeneratorServiceImpl;

public class GeneratorController {
	
	public GeneratorController() {
	}
	
	public String generateBusinessRule(String ruleCode, String directory, boolean execute){
		BusinessRuleService ruleService = new BusinessRuleServiceImpl();
		BusinessRule businessRule = ruleService.getBusinessRule(ruleCode);
		
		GeneratorServiceImpl service = new GeneratorServiceImpl(businessRule.getTargetDatabase());
		
		String script = service.generateBusinessRule(businessRule);
		service.writeFile(script, ruleCode, directory, "sql");
		
		if(execute){
			service.executeScript(script);
		}
		
		return script;
	}

}

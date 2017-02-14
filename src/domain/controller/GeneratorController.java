package domain.controller;



import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleService;
import domain.businessrule.BusinessRuleServiceImpl;
import domain.generator.GeneratorServiceImpl;
import domain.generator.GeneratorService;

public class GeneratorController extends GeneratorControllerTemplate{

	public GeneratorController() {
	}
	
	public String generateSaveAndExecuteBusinessRule(String ruleCode, String directory, boolean execute){
		BusinessRule businessRule = getRuleAndCreateService(ruleCode);
		String script = createScript(businessRule, directory, "sql");
		if(execute){
			service.executeScript(script);
		}
		return script;
	}

	@Override
	protected void instantiateGeneratorService(BusinessRule businessRule){
		GeneratorServiceImpl service = new GeneratorServiceImpl(businessRule.getTargetDatabase());
	}

	@Override
	protected BusinessRule getBusinessRuleFromDatabase(String ruleCode){
		BusinessRuleService ruleService = new BusinessRuleServiceImpl();
		return ruleService.getBusinessRule(ruleCode);
	}

	private String createScript(BusinessRule businessRule, String directory, String extention){
		String script = service.generateBusinessRule(businessRule);
		service.writeFile(script, businessRule.getCode(), directory, extention);
		return script;
	}

}

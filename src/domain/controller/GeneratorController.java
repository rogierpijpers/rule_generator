package domain.controller;



import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleService;
import domain.businessrule.BusinessRuleServiceImpl;
import domain.generator.GeneratorServiceImpl;
import domain.generator.GeneratorService;

public class GeneratorController extends GeneratorControllerTemplate{
	private String scriptDirectory;
	private boolean executeScript;

	public String getScriptDirectory(){
		return scriptDirectory;
	}

	public void setScriptDirectory(String scriptDirectory){
		this.scriptDirectory = scriptDirectory;
	}

	public boolean getExecuteScript(){
		return executeScript;
	}

	public void setExecuteScript(boolean executeScript){
		this.executeScript = executeScript;
	}

	public GeneratorController() {
	}
	
	public String generateSaveAndExecuteBusinessRule(String ruleCode){
		BusinessRule businessRule = getRuleAndCreateService(ruleCode);
		String script = createScript(businessRule, scriptDirectory, "sql");
		if(executeScript){
			service.executeScript(script);
		}
		return script;
	}

	@Override
	protected void instantiateGeneratorService(BusinessRule businessRule){
		new GeneratorServiceImpl(businessRule.getTargetDatabase());
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

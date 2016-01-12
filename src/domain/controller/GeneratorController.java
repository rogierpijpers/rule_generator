package domain.controller;

import java.io.File;
import java.io.IOException;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.TargetDatabase;
import domain.generator.GeneratorServiceImpl;

public class GeneratorController {
	
	public GeneratorController() {
	}
	
	public String generateBusinessRule(String ruleCode, boolean execute){
		BusinessRule businessRule = BusinessRule.getDetails(ruleCode);
		
		GeneratorServiceImpl service = new GeneratorServiceImpl(businessRule.getTargetDatabase());
		
		String script = service.generateBusinessRule(businessRule);
		service.writeFile(script, ruleCode, getApplicationPath(), "sql");
		
		if(execute){
			service.executeScript(script);
		}
		
		return script;
	}
	
	private String getApplicationPath(){
		String path = "";
		try {
			path = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

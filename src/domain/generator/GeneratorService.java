package domain.generator;

import domain.businessrule.BusinessRule;

public interface GeneratorService {
	public String generateBusinessRule(BusinessRule businessRule);

	public void executeScript(String script);

	public void writeFile(String script, String ruleCode, String directory,
			String extention);
}
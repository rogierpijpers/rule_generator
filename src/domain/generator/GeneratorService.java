package domain.generator;

public interface GeneratorService {
	public String generateBusinessRule(BusinessRule businessRule);

	public void executeScript(String script);

	public void writeFile(String script, String ruleCode, String directory,
			String extention);
}
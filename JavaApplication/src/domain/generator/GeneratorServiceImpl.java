package domain.generator;

import data.target.TargetDAO;
import data.utility.FileSaver;
import domain.businessrule.BusinessRule;
import domain.businessrule.database.DatabaseType;
import domain.businessrule.database.TargetDatabase;

public class GeneratorServiceImpl implements GeneratorService {
	private TargetDatabase targetDatabase;
	private ScriptBuilder scriptBuilder;

	public GeneratorServiceImpl(TargetDatabase targetDatabase) {
		this.targetDatabase = targetDatabase;
		if (targetDatabase.getType().equals(DatabaseType.ORACLE)) {
			scriptBuilder = new OracleScriptBuilder();
		}
	}

	@Override
	public String generateBusinessRule(BusinessRule businessRule) {
		return scriptBuilder.createScript(businessRule);
	}

	@Override
	public void executeScript(String script) {
		TargetDAO dao = targetDatabase.getTargetDAO();
		dao.executeScript(script);
	}

	@Override
	public void writeFile(String script, String ruleCode, String directory,
			String extention) {
		FileSaver.writeFile(script, ruleCode, directory, extention);
	}

}
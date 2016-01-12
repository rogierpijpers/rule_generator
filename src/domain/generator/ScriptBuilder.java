package domain.generator;

import domain.businessrule.BusinessRule;

public interface ScriptBuilder {
	public String createScript(BusinessRule businessRule);
}
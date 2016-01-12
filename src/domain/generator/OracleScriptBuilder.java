package domain.generator;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;

public class OracleScriptBuilder implements ScriptBuilder {
	private TemplateBuilder templateBuilder;

	public OracleScriptBuilder() {
		templateBuilder = new OracleTemplateBuilder();
	}

	@Override
	public String createScript(BusinessRule businessRule) {
		ST template = templateBuilder.getTemplate(businessRule.getCode(),
				businessRule.getCodeType());
		// fill template

		// return template
		return null;
	}
}
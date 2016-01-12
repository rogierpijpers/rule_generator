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
		ST template = templateBuilder.getTemplate(businessRule.getType().getCode(),
				businessRule.getCodeType());

		template = businessRule.fillTemplate(template);

		return template.render();
	}
}
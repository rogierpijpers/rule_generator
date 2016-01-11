package domain.generator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import util.TemplateDTO;
import data.TemplateDAO;

public class OracleTemplateBuilder implements TemplateBuilder {
	private TemplateDAO templateDAO;
	private String bodyName;
	private STGroup group;

	public OracleTemplateBuilder() {
		templateDAO = new TemplateDAO();
	}

	public ST getTemplate(String typeCode, CodeType codeType) {
		return build(typeCode, codeType);
	}

	private ST build(String typeCode, CodeType codeType) {
		bodyName = "PLSQL_" + codeType + "_BODY_TEMPLATE";

		String arguments = bodyName + "(";

		TemplateDTO bodyDTO = templateDAO.getTemplate(bodyName);
		TemplateDTO ruleDTO = templateDAO.getTemplate("PLSQL_" + typeCode
				+ "_TEMPLATE");
		TemplateDTO targetDTO = templateDAO.getTemplate("PLSQL_" + typeCode
				+ "_TARGET_TEMPLATE");

		String bodyTemplate = bodyDTO.getTemplate();
		String ruleTemplate = ruleDTO.getTemplate();
		String targetTemplate = targetDTO.getTemplate();

		arguments += bodyDTO.getArgument() + ", " + ruleDTO.getArgument()
				+ ", " + targetDTO.getArgument() + ") ::= ";

		group = new STGroupString(arguments + bodyTemplate);
		ST template = group.getInstanceOf(bodyName);
		template.add("target", targetTemplate);
		template.add("ruleCode", ruleTemplate);

		return template;
	}

}
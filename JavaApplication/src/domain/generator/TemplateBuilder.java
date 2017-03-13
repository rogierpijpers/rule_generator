package domain.generator;

import org.stringtemplate.v4.ST;

import domain.businessrule.CodeType;

public interface TemplateBuilder {
	public ST getTemplate(String typeCode, CodeType codeType);
}
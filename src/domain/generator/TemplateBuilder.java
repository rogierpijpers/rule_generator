package domain.generator;

import org.stringtemplate.v4.ST;

public interface TemplateBuilder {
	public ST getTemplate(String typeCode, CodeType codeType);
}
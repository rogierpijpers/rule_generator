package domain.generator;

public interface TemplateBuilder {
	public ST getTemplate(String typeCode, CodeType codeType);
}
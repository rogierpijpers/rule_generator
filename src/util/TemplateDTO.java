package util;

public class TemplateDTO {
	private String template;
	private String argument;

	public TemplateDTO(String template, String argument) {
		this.template = template;
		this.argument = argument;
	}

	public String getTemplate() {
		return template;
	}

	public String getArgument() {
		return argument;
	}
}
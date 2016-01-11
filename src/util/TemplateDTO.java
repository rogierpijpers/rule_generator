package util;

public class TemplateDTO {
	private String name;
	private String template;
	private String argument;

	public TemplateDTO(String name, String template, String argument) {
		this.name = name;
		this.template = template;
		this.argument = argument;
	}
	
	public String getName(){
		return name;
	}

	public String getTemplate() {
		return template;
	}

	public String getArgument() {
		return argument;
	}
}
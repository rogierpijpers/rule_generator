package util;

public class RuleTypeDTO {
	private String code;
	private String name;
	private String example;
	private String codeType;
	private String categoryName;
	
	public RuleTypeDTO(String code, String name, String example, String codeType, String categoryName) {
		this.code = code;
		this.name = name;
		this.example = example;
		this.codeType = codeType;
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}

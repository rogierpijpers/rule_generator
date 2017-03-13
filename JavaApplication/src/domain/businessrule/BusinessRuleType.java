package domain.businessrule;

import java.util.ArrayList;

public class BusinessRuleType {

	private String code;
	private String description;
	private String example;
	private Category category;
	private CodeType codeType;
	
	
	public BusinessRuleType(String code, String description, String example, Category category, CodeType codeType) {
		this.code = code;
		this.description = description;
		this.example = example;
		this.category = category;
		this.codeType = codeType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setCodeType(CodeType codeType){
		this.codeType = codeType;
	}
	public CodeType getCodeType(){
		return codeType;
	}
	
	// ---- for retrieving from database
	
	public static ArrayList<String> getAllCodes(){
		return null;
	}
	public static BusinessRuleType getDetails(String code){
		return null;
	}
	
	
}

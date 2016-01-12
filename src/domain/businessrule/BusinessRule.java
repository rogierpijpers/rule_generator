package domain.businessrule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

public abstract class BusinessRule {

	private String code;
	private String name;
	private String failureMessage;
	private BusinessRuleType type;
	private CodeType codeType;
	
	public void setCodeType(CodeType codeType){
		
	}
	public CodeType getCodeType(){
		return null;
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
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	public BusinessRuleType getType() {
		return type;
	}
	public void setType(BusinessRuleType type) {
		this.type = type;
	}
	
	// ---- fill templates for generation
	
	public abstract ST fillTemplate(ST ruleTemplate);
	
	public abstract ST fillTargetTemplate(ST template);
	
	// ---- for retrieving from database
	
	public static ArrayList<RuleHolder> getAllCodesAndNames(){
		return null;
	}
	public static BusinessRule getDetails(String code){
		return null;
	}
}

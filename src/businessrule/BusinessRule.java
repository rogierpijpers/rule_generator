package businessrule;

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
	public String fillTemplate(ST ruleTemplate){
		
	}
	public String fillTargetTemplate(ST template){
		
	}
	public ArrayList<RuleHolder> getAllCodesAndNames(){
		
	}
	public BusinessRule getDetails(String code){
		return null;
	}
}
